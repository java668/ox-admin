package com.java668.oxadmin.modules.generator.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java668.common.db.mapper.EasyBaseMapper;
import org.apache.ibatis.annotations.Param;
import com.java668.oxadmin.modules.generator.entity.TableColumn;

/**
 * 代码生成业务表字段(TableColumn)表数据库访问层
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:29
 */
public interface TableColumnMapper extends EasyBaseMapper<TableColumn> {


    List<TableColumn> listByTableName(@Param("tableName") String tableName);

    int updateGenTableColumn(TableColumn column);

    int insertGenTableColumn(TableColumn column);

    int deleteGenTableColumns(List<TableColumn> delColumns);
}

