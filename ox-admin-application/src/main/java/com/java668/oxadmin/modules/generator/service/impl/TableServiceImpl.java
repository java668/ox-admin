package com.java668.oxadmin.modules.generator.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java668.common.constant.Constants;
import com.java668.common.constant.GenConstants;
import com.java668.common.exception.BusinessException;
import com.java668.common.model.PageResult;
import com.java668.oxadmin.modules.generator.dto.request.TableColumnReqDTO;
import com.java668.oxadmin.modules.generator.dto.request.TablePageReqDTO;
import com.java668.oxadmin.modules.generator.dto.request.TableReqDTO;
import com.java668.oxadmin.modules.generator.dto.response.TableColumnRespDTO;
import com.java668.oxadmin.modules.generator.dto.response.TableRespDTO;
import com.java668.oxadmin.modules.generator.entity.Table;
import com.java668.oxadmin.modules.generator.entity.TableColumn;
import com.java668.oxadmin.modules.generator.mapper.TableMapper;
import com.java668.oxadmin.modules.generator.service.ITableColumnService;
import com.java668.oxadmin.modules.generator.service.ITableService;
import com.java668.oxadmin.modules.generator.utils.GenUtils;
import com.java668.oxadmin.modules.generator.utils.VelocityInitializer;
import com.java668.oxadmin.modules.generator.utils.VelocityUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成业务表(Table)表服务实现类
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:29
 */
@Service("tableService" )
@RequiredArgsConstructor
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements ITableService {

    private final ITableColumnService tableColumnService;

    @Override
    public PageResult<TableRespDTO> page(TablePageReqDTO params) {
        LambdaQueryWrapper<Table> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StrUtil.isNotBlank(params.getTableName()), Table::getTableName, params.getTableName());
        queryWrapper.like(StrUtil.isNotBlank(params.getTableComment()), Table::getTableComment, params.getTableComment());
        queryWrapper.le(ObjectUtil.isNotNull(params.getBeginTime()), Table::getCreateTime, params.getBeginTime());
        queryWrapper.ge(ObjectUtil.isNotNull(params.getEndTime()), Table::getCreateTime, params.getEndTime());
        Page<Table> page = page(params.<Table>buildPage(), queryWrapper);
        return PageResult.of(page, TableRespDTO.class);
    }

    @Override
    public JSONObject getInfo(Long tableId) {
        Table table = baseMapper.selectById(tableId);
        setTableFromOptions(table);
        List<Table> tables = baseMapper.selectTableAll();
        List<TableColumnRespDTO> list = tableColumnService.listByTableId(tableId);
        JSONObject info = new JSONObject();
        info.put("info" , table);
        info.put("rows" , list);
        info.put("tables" , tables);
        return info;
    }

    @Override
    public PageResult<TableRespDTO> dbTablePage(TablePageReqDTO params) {
        Page<Table> page = params.buildPage();
        // 不优化 count 查询
        page.setOptimizeCountSql(false);
        baseMapper.dbTablePage(page, params);
        return PageResult.of(page, TableRespDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean importTable(List<String> tables) {
        List<Table> tableList = baseMapper.dbTableListByNames(tables);
        for (Table table : tableList) {
            GenUtils.initTable(table);
            int row = baseMapper.insert(table);
            if (row == 0) {
                throw new BusinessException("插入失败" );
            }
            // 保存列信息
            String tableName = table.getTableName();
            List<TableColumn> tableColumns = tableColumnService.listByTableName(tableName);
            for (TableColumn column : tableColumns) {
                GenUtils.initColumnField(column, table);
                tableColumnService.save(column);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update(TableReqDTO genTable) {
        validate(genTable);
        String options = JSON.toJSONString(genTable.getParams());
        genTable.setOptions(options);
        Table entity = BeanUtil.copyProperties(genTable, Table.class);
        int row = baseMapper.updateById(entity);
        if (row > 0) {
            for (TableColumnReqDTO tableColumn : genTable.getColumns()) {
                tableColumnService.update(tableColumn);
            }
        }
        return row;
    }

    @Override
    public Map<String, String> previewCode(Long tableId) {
        Map<String, String> dataMap = new LinkedHashMap<>();
        // 查询表信息
        Table table = baseMapper.selectTableById(tableId);
        // 设置主子表信息
        setSubTable(table);
        // 设置主键列信息
        setPkColumn(table);
        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            dataMap.put(template, sw.toString());
        }
        return dataMap;
    }

    @Override
    public byte[] downloadCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generatorCode(tableName, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    @Override
    public Boolean generatorCode(String tableName) {
        // 查询表信息
        Table table = baseMapper.selectTableByName(tableName);
        // 设置主子表信息
        setSubTable(table);
        // 设置主键列信息
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            if (!StringUtils.containsAny(template, "sql.vm" , "api.js.vm" , "index.vue.vm" , "index-tree.vue.vm" )) {
                // 渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, Constants.UTF8);
                tpl.merge(context, sw);
                try {
                    String path = getGenPath(table, template);
                    FileUtils.writeStringToFile(new File(path), sw.toString(), Constants.UTF8);
                } catch (IOException e) {
                    throw new BusinessException("渲染模板失败，表名：" + table.getTableName());
                }
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncDb(String tableName) {
        Table table = baseMapper.selectTableByName(tableName);
        List<TableColumn> tableColumns = table.getColumns();
        Map<String, TableColumn> tableColumnMap = tableColumns.stream().collect(Collectors.toMap(TableColumn::getColumnName, Function.identity()));

        List<TableColumn> dbTableColumns = tableColumnService.listByTableName(tableName);
        if (CollUtil.isEmpty(dbTableColumns)) {
            throw new BusinessException("同步数据失败，原表结构不存在" );
        }
        List<String> dbTableColumnNames = dbTableColumns.stream().map(TableColumn::getColumnName).collect(Collectors.toList());

        dbTableColumns.forEach(column -> {
            GenUtils.initColumnField(column, table);
            if (tableColumnMap.containsKey(column.getColumnName())) {
                TableColumn prevColumn = tableColumnMap.get(column.getColumnName());
                column.setColumnId(prevColumn.getColumnId());
                if (column.isList()) {
                    // 如果是列表，继续保留查询方式/字典类型选项
                    column.setDictType(prevColumn.getDictType());
                    column.setQueryType(prevColumn.getQueryType());
                }
                if (StringUtils.isNotEmpty(prevColumn.getIsRequired()) && !column.isPk()
                        && (column.isInsert() || column.isEdit())
                        && ((column.isUsableColumn()) || (!column.isSuperColumn()))) {
                    // 如果是(新增/修改&非主键/非忽略及父属性)，继续保留必填/显示类型选项
                    column.setIsRequired(prevColumn.getIsRequired());
                    column.setHtmlType(prevColumn.getHtmlType());
                }
                tableColumnService.updateGenTableColumn(column);
            } else {
                tableColumnService.insertGenTableColumn(column);
            }
        });

        List<TableColumn> delColumns = tableColumns.stream().filter(column -> !dbTableColumnNames.contains(column.getColumnName())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(delColumns)) {
            tableColumnService.deleteGenTableColumns(delColumns);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<Long> tableIds) {
        tableColumnService.removeByTableIds(tableIds);
        return removeBatchByIds(tableIds);
    }

    /**
     * 设置代码生成其他选项值
     *
     * @param genTable 设置后的生成对象
     */
    public void setTableFromOptions(Table genTable) {
        com.alibaba.fastjson2.JSONObject paramsObj = JSON.parseObject(genTable.getOptions());
        if (ObjectUtil.isNotNull(paramsObj)) {
            String treeCode = paramsObj.getString(GenConstants.TREE_CODE);
            String treeParentCode = paramsObj.getString(GenConstants.TREE_PARENT_CODE);
            String treeName = paramsObj.getString(GenConstants.TREE_NAME);
            String parentMenuId = paramsObj.getString(GenConstants.PARENT_MENU_ID);
            String parentMenuName = paramsObj.getString(GenConstants.PARENT_MENU_NAME);

            genTable.setTreeCode(treeCode);
            genTable.setTreeParentCode(treeParentCode);
            genTable.setTreeName(treeName);
            genTable.setParentMenuId(parentMenuId);
            genTable.setParentMenuName(parentMenuName);
        }
    }

    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     */
    public void setPkColumn(Table table) {
        for (TableColumn column : table.getColumns()) {
            if (column.isPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (ObjectUtil.isNull(table.getPkColumn())) {
            table.setPkColumn(table.getColumns().get(0));
        }
        if (GenConstants.TPL_SUB.equals(table.getTplCategory())) {
            for (TableColumn column : table.getSubTable().getColumns()) {
                if (column.isPk()) {
                    table.getSubTable().setPkColumn(column);
                    break;
                }
            }
            if (ObjectUtil.isNull(table.getSubTable().getPkColumn())) {
                table.getSubTable().setPkColumn(table.getSubTable().getColumns().get(0));
            }
        }
    }

    /**
     * 设置主子表信息
     *
     * @param table 业务表信息
     */
    public void setSubTable(Table table) {
        String subTableName = table.getSubTableName();
        if (StringUtils.isNotEmpty(subTableName)) {
            table.setSubTable(baseMapper.selectTableByName(subTableName));
        }
    }

    public void validate(TableReqDTO genTable) {
        if (GenConstants.TPL_TREE.equals(genTable.getTplCategory())) {
            String options = JSON.toJSONString(genTable.getParams());
            com.alibaba.fastjson2.JSONObject paramsObj = JSON.parseObject(options);
            if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_CODE))) {
                throw new BusinessException("树编码字段不能为空" );
            } else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_PARENT_CODE))) {
                throw new BusinessException("树父编码字段不能为空" );
            } else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_NAME))) {
                throw new BusinessException("树名称字段不能为空" );
            } else if (GenConstants.TPL_SUB.equals(genTable.getTplCategory())) {
                if (StringUtils.isEmpty(genTable.getSubTableName())) {
                    throw new BusinessException("关联子表的表名不能为空" );
                } else if (StringUtils.isEmpty(genTable.getSubTableFkName())) {
                    throw new BusinessException("子表关联的外键名不能为空" );
                }
            }
        }
    }

    /**
     * 查询表信息并生成代码
     */
    private void generatorCode(String tableName, ZipOutputStream zip) {
        // 查询表信息
        Table table = baseMapper.selectTableByName(tableName);
        // 设置主子表信息
        setSubTable(table);
        // 设置主键列信息
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, Constants.UTF8);
                IOUtils.closeQuietly(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }

    /**
     * 获取代码生成地址
     *
     * @param table    业务表信息
     * @param template 模板文件路径
     * @return 生成地址
     */
    public static String getGenPath(Table table, String template) {
        String genPath = table.getGenPath();
        if (StringUtils.equals(genPath, "/" )) {
            return System.getProperty("user.dir" ) + File.separator + "src" + File.separator + VelocityUtils.getFileName(template, table);
        }
        return genPath + File.separator + VelocityUtils.getFileName(template, table);
    }
}

