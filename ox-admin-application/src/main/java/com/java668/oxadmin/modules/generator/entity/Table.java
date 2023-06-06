package com.java668.oxadmin.modules.generator.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.java668.common.constant.GenConstants;
import com.java668.common.db.entity.BaseEntity;
import com.java668.oxadmin.modules.generator.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

/**
 * 代码生成业务表(Table)表实体类
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:28
 */
@TableName("gene_table")
public class Table extends BaseEntity<Table> {
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

    /**
     * 子表信息
     */
    @TableField(exist = false)
    private Table subTable;

    /**
     * 表列信息
     */
    @TableField(exist = false)
    private List<TableColumn> columns;

    /**
     * 主键信息
     */
    @TableField(exist = false)
    private TableColumn pkColumn;

    /** 树编码字段 */
    @TableField(exist = false)
    private String treeCode;

    /** 树父编码字段 */
    @TableField(exist = false)
    private String treeParentCode;

    /** 树名称字段 */
    @TableField(exist = false)
    private String treeName;

    /** 上级菜单ID字段 */
    @TableField(exist = false)
    private String parentMenuId;

    /** 上级菜单名称字段 */
    @TableField(exist = false)
    private String parentMenuName;

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getSubTableName() {
        return subTableName;
    }

    public void setSubTableName(String subTableName) {
        this.subTableName = subTableName;
    }

    public String getSubTableFkName() {
        return subTableFkName;
    }

    public void setSubTableFkName(String subTableFkName) {
        this.subTableFkName = subTableFkName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTplCategory() {
        return tplCategory;
    }

    public void setTplCategory(String tplCategory) {
        this.tplCategory = tplCategory;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionAuthor() {
        return functionAuthor;
    }

    public void setFunctionAuthor(String functionAuthor) {
        this.functionAuthor = functionAuthor;
    }

    public String getGenType() {
        return genType;
    }

    public void setGenType(String genType) {
        this.genType = genType;
    }

    public String getGenPath() {
        return genPath;
    }

    public void setGenPath(String genPath) {
        this.genPath = genPath;
    }

    public TableColumn getPkColumn() {
        return pkColumn;
    }

    public void setPkColumn(TableColumn pkColumn) {
        this.pkColumn = pkColumn;
    }

    public Table getSubTable() {
        return subTable;
    }

    public void setSubTable(Table subTable) {
        this.subTable = subTable;
    }

    public List<TableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<TableColumn> columns) {
        this.columns = columns;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    public String getTreeParentCode() {
        return treeParentCode;
    }

    public void setTreeParentCode(String treeParentCode) {
        this.treeParentCode = treeParentCode;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    public boolean isSub() {
        return isSub(this.tplCategory);
    }

    public static boolean isSub(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_SUB, tplCategory);
    }

    public boolean isTree() {
        return isTree(this.tplCategory);
    }

    public static boolean isTree(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public boolean isCrud() {
        return isCrud(this.tplCategory);
    }

    public static boolean isCrud(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSuperColumn(String javaField) {
        return isSuperColumn(this.tplCategory, javaField);
    }

    public static boolean isSuperColumn(String tplCategory, String javaField) {
        if (isTree(tplCategory)) {
            return StringUtils.equalsAnyIgnoreCase(javaField,
                    ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
        }
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }

}

