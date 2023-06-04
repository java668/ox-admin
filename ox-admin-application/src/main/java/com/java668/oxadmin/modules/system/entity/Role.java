package com.java668.oxadmin.modules.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.java668.common.db.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * 角色表(Role)表实体类
 *
 * @author jerry.chen
 * @since 2023-03-25 19:48:41
 */
@Data
@ToString
@TableName("syst_role")
public class Role extends BaseEntity<Role> {
    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色级别
     */
    private Integer level;
    /**
     * 描述
     */
    private String description;
    /**
     * 数据权限
     */
    private String dataScope;

}

