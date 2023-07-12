package com.java668.oxadmin.modules.generator.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.common.model.PageResult;
import com.java668.oxadmin.modules.generator.dto.request.TablePageReqDTO;
import com.java668.oxadmin.modules.generator.dto.request.TableReqDTO;
import com.java668.oxadmin.modules.generator.dto.response.TableRespDTO;
import com.java668.oxadmin.modules.generator.entity.Table;

import java.util.List;
import java.util.Map;

/**
 * 代码生成业务表(Table)表服务接口
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:29
 */
public interface ITableService extends IService<Table> {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    PageResult<TableRespDTO> page(TablePageReqDTO params);

    /**
     * 详情
     *
     * @param tableId
     * @return
     */
    JSONObject getInfo(Long tableId);


    /**
     * 数据库表分页查询
     *
     * @param params
     * @return
     */
    PageResult<TableRespDTO> dbTablePage(TablePageReqDTO params);

    /**
     * 导入表结构（保存）
     *
     * @param tables
     * @return
     */
    boolean importTable(List<String> tables);

    /**
     * 更新
     *
     * @param genTable
     * @return
     */
    Integer update(TableReqDTO genTable);

    /**
     * 预览
     *
     * @param tableId
     * @return
     */
    Map<String, String> previewCode(Long tableId);

    /**
     * 下载
     *
     * @param tableName
     * @return
     */
    byte[] downloadCode(String tableName);

    /**
     * 批量生成
     *
     * @param tableNames
     * @return
     */
    byte[] downloadCode(String[] tableNames);

    /**
     * 生成代码
     *
     * @param tableName
     * @return
     */
    Boolean generatorCode(String tableName);

    /**
     * 同步数据库
     *
     * @param tableName
     */
    void syncDb(String tableName);

    /**
     * 删除
     *
     * @param tableIds
     * @return
     */
    Boolean delete(List<Long> tableIds);
}

