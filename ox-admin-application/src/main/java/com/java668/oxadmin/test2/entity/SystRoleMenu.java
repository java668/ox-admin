package com.java668.oxadmin.test2.entity;

import lombok.Data;
import lombok.ToString;
import com.baomidou.mybatisplus.annotation.TableName;
import com.java668.common.db.entity.BaseEntity;

/**
 * 用户角色关联对象 syst_role_menu
 * 
 * @author jerry.chen
 * @date 2023-06-06 16:00:03
 */
@Data
@ToString
@TableName("syst_role_menu")
public class SystRoleMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 是否删除（0:否，1：是）
     */
    private Integer isDeleted;
}
