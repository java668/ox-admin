package com.java668.oxadmin.modules.generator.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java668.common.db.mapper.EasyBaseMapper;
import com.java668.oxadmin.modules.generator.dto.request.TablePageReqDTO;
import com.java668.oxadmin.modules.generator.entity.Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 代码生成业务表(Table)表数据库访问层
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:28
 */
public interface TableMapper extends EasyBaseMapper<Table> {

    Page<Table> dbTablePage(Page<Table> page, @Param("params") TablePageReqDTO params);

    List<Table> dbTableListByNames(@Param("list") List<String> names);

    Table selectTableByName(@Param("tableName") String subTableName);

    Table selectTableById(@Param("tableId") Long tableId);

    List<Table> selectTableAll();

}

