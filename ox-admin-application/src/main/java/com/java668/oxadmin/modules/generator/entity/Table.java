package com.java668.oxadmin.modules.generator.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.java668.common.db.entity.SuperEntity;
import lombok.Data;

import java.util.List;

/**
 * 代码生成业务表(Table)表实体类
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:28
 */
@Data
@TableName("gene_table")
public class Table extends SuperEntity<Table> {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long tableId;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableComment;
    /**
     * 关联子表的表名
     */
    private String subTableName;
    /**
     * 子表关联的外键名
     */
    private String subTableFkName;
    /**
     * 实体类名称
     */
    private String className;
    /**
     * 使用的模板（crud单表操作 tree树表操作）
     */
    private String tplCategory;
    /**
     * 生成包路径
     */
    private String packageName;
    /**
     * 生成模块名
     */
    private String moduleName;
    /**
     * 生成业务名
     */
    private String businessName;
    /**
     * 生成功能名
     */
    private String functionName;
    /**
     * 生成功能作者
     */
    private String functionAuthor;
    /**
     * 生成代码方式（0zip压缩包 1自定义路径）
     */
    private String genType;
    /**
     * 生成路径（不填默认项目路径）
     */
    private String genPath;
    /**
     * 其它生成选项
     */
    private String options;


    /** 子表信息 */
    @TableField(exist = false)
    private Table subTable;

    /** 表列信息 */
    @TableField(exist = false)
    private List<TableColumn> columns;

    /** 主键信息 */
    @TableField(exist = false)
    private TableColumn pkColumn;

}

