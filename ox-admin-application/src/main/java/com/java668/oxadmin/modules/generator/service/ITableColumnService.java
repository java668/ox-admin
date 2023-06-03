package com.java668.oxadmin.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.oxadmin.modules.generator.dto.request.TableColumnReqDTO;
import com.java668.oxadmin.modules.generator.dto.response.TableColumnRespDTO;
import com.java668.oxadmin.modules.generator.entity.TableColumn;

import java.util.List;

/**
 * 代码生成业务表字段(TableColumn)表服务接口
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:30
 */
public interface ITableColumnService extends IService<TableColumn> {

    List<TableColumnRespDTO> listByTableId(Long tableId);

    List<TableColumn> listByTableName(String tableName);

    Boolean update(TableColumnReqDTO tableColumn);
}

