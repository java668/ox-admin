package com.java668.oxadmin.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.java668.common.db.entity.SuperEntity;
import lombok.Data;

/**
 * 用户角色关联(RoleMenu)表实体类
 *
 * @author jerry.chen
 * @since 2023-03-25 19:58:20
 */
@Data
@SuppressWarnings("serial")
@TableName("sys_role_menu")
public class RoleMenu extends SuperEntity<RoleMenu> {
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 是否删除（0:否，1：是）
     */
    private Integer isDeleted;
}

