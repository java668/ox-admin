package com.java668.oxadmin.modules.system.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.java668.common.db.entity.SuperEntity;
import lombok.Data;

/**
 * 角色表(Role)表实体类
 *
 * @author jerry.chen
 * @since 2023-03-25 19:48:41
 */
@Data
@TableName("sys_role")
public class Role extends SuperEntity<Role> {
    /**
     * 主键id
     */
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

