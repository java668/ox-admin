package com.java668.oxadmin.modules.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("syst_role_menu")
public class RoleMenu extends SuperEntity<RoleMenu> {
    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
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

