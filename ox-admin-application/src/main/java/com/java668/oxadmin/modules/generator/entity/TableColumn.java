package com.java668.oxadmin.modules.generator.entity;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.java668.common.db.entity.SuperEntity;
import com.java668.oxadmin.modules.generator.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * 代码生成业务表字段(TableColumn)表实体类
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:29
 */
@Data
@TableName("gene_table_column")
public class TableColumn extends SuperEntity<TableColumn> {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long columnId;
    /**
     * 归属表编号
     */
    private Long tableId;
    /**
     * 列名称
     */
    private String columnName;
    /**
     * 列描述
     */
    private String columnComment;
    /**
     * 列类型
     */
    private String columnType;
    /**
     * JAVA类型
     */
    private String javaType;
    /**
     * JAVA字段名
     */
    private String javaField;
    /**
     * 是否主键（1是）
     */
    private String isPk;
    /**
     * 是否自增（1是）
     */
    private String isIncrement;
    /**
     * 是否必填（1是）
     */
    private String isRequired;
    /**
     * 是否为插入字段（1是）
     */
    private String isInsert;
    /**
     * 是否编辑字段（1是）
     */
    private String isEdit;
    /**
     * 是否列表字段（1是）
     */
    private String isList;
    /**
     * 是否查询字段（1是）
     */
    private String isQuery;
    /**
     * 查询方式（等于、不等于、大于、小于、范围）
     */
    private String queryType;
    /**
     * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    private String htmlType;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 排序
     */
    private Integer sort;

    public boolean isPk() {
        return isPk(this.isPk);
    }

    public boolean isPk(String isPk) {
        return isPk != null && StrUtil.equals("1", isPk);
    }

    public boolean isSuperColumn() {
        return isSuperColumn(this.javaField);
    }

    public static boolean isSuperColumn(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(javaField,
                // BaseEntity
                "createBy", "createTime", "updateBy", "updateTime", "remark",
                // TreeEntity
                "parentName", "parentId", "orderNum", "ancestors");
    }

    public boolean isList() {
        return isList(this.isList);
    }

    public boolean isList(String isList) {
        return isList != null && StringUtils.equals("1", isList);
    }


}

