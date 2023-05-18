package ${package.Entity}

<#list table.importPackages as pkg>
import ${pkg}
</#list>

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author ${author}
 * @date ${date}
 */
<#if table.convert>
@TableName("${table.name}")
</#if>
<#if superEntityClass??>
class ${entity} : ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
class ${entity} : Model<${entity}>() {
<#else>
class ${entity} : Serializable {
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
<#if field.keyFlag>
    <#assign keyPropertyName="${field.propertyName}"/>
</#if>

<#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
</#if>
<#if field.keyFlag>
<#-- 主键 -->
<#if field.keyIdentityFlag>
    @TableId(value = "${field.name}", type = IdType.AUTO)
<#elseif idType ??>
    @TableId(value = "${field.name}", type = IdType.${idType})
<#elseif field.convert>
    @TableId("${field.name}")
</#if>
<#-- 普通字段 -->
<#elseif field.fill??>
<#-- -----   存在字段填充设置   ----->
<#if field.convert>
    @TableField(value = "${field.name}", fill = FieldFill.${field.fill})
<#else>
    @TableField(fill = FieldFill.${field.fill})
</#if>
<#elseif field.convert>
    @TableField("${field.name}")
</#if>
<#-- 乐观锁注解 -->
<#if (versionFieldName!"") == field.name>
    @Version
</#if>
<#-- 逻辑删除注解 -->
<#if (logicDeleteFieldName!"") == field.name>
    @TableLogic
</#if>
    <#if field.propertyType == "Integer">
    var ${field.propertyName}: Int? = null
    <#else>
    var ${field.propertyName}: ${field.propertyType}? = null
    </#if>
</#list>
<#-- ----------  END 字段循环遍历  ---------->


<#if entityColumnConstant>
    companion object {
<#list table.fields as field>

        const val ${field.name.toUpperCase()} : String = "${field.name}"

</#list>
    }

</#if>
<#if activeRecord>
    override fun pkVal(): Serializable {
<#if keyPropertyName??>
        return ${keyPropertyName}
<#else>
        return null
</#if>
    }

</#if>
    override fun toString(): String {
        return "${entity}{" +
<#list table.fields as field>
<#if field_index==0>
        "${field.propertyName}=" + ${field.propertyName} +
<#else>
        ", ${field.propertyName}=" + ${field.propertyName} +
</#if>
</#list>
        "}"
    }
}
