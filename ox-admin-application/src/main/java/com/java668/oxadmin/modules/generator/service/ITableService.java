package com.java668.oxadmin.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.common.model.PageResult;
import com.java668.oxadmin.modules.generator.dto.request.TableReqDTO;
import com.java668.oxadmin.modules.generator.dto.response.TableColumnRespDTO;
import com.java668.oxadmin.modules.generator.entity.Table;
import com.java668.oxadmin.modules.system.dto.response.UserRespDTO;

import java.util.List;

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
     * @param tableReqDTO
     * @return
     */
    PageResult<UserRespDTO> page(TableReqDTO tableReqDTO);

    /**
     * 数据库表分页查询
     *
     * @param tableReqDTO
     * @return
     */
    PageResult<UserRespDTO> dbTablePage(TableReqDTO tableReqDTO);

    List<TableColumnRespDTO> columnList(Long tableId);

    boolean importTableSave(String tables);
}

