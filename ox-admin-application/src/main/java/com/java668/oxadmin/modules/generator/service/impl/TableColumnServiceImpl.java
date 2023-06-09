package com.java668.oxadmin.modules.generator.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java668.oxadmin.modules.generator.dto.request.TableColumnReqDTO;
import com.java668.oxadmin.modules.generator.dto.response.TableColumnRespDTO;
import com.java668.oxadmin.modules.generator.mapper.TableColumnMapper;
import com.java668.oxadmin.modules.generator.entity.TableColumn;
import com.java668.oxadmin.modules.generator.service.ITableColumnService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 代码生成业务表字段(TableColumn)表服务实现类
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:30
 */
@Service("tableColumnService")
public class TableColumnServiceImpl extends ServiceImpl<TableColumnMapper, TableColumn> implements ITableColumnService {

    @Override
    public List<TableColumnRespDTO> listByTableId(Long tableId) {
        List<TableColumn> list = lambdaQuery().eq(TableColumn::getTableId, tableId).list();
        return BeanUtil.copyToList(list, TableColumnRespDTO.class);
    }

    @Override
    public List<TableColumn> listByTableName(String tableName) {
        return baseMapper.listByTableName(tableName);
    }

    @Override
    public Boolean update(TableColumnReqDTO tableColumn) {
        TableColumn entity = BeanUtil.copyProperties(tableColumn, TableColumn.class);
        return updateById(entity);
    }

    @Override
    public Boolean removeByTableIds(List<Long> tableIds) {
        LambdaQueryWrapper<TableColumn> queryWrapper = Wrappers.<TableColumn>lambdaQuery()
                .in(TableColumn::getTableId, tableIds);
        return remove(queryWrapper);
    }

    @Override
    public int updateGenTableColumn(TableColumn column) {
        return baseMapper.updateGenTableColumn(column);
    }

    @Override
    public int insertGenTableColumn(TableColumn column) {
        return baseMapper.insertGenTableColumn(column);
    }

    @Override
    public int deleteGenTableColumns(List<TableColumn> delColumns) {
        return baseMapper.deleteGenTableColumns(delColumns);
    }

}

