package com.java668.oxadmin.modules.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.java668.common.db.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * 用户角色关联(UserRole)表实体类
 *
 * @author jerry.chen
 * @since 2023-03-25 19:58:21
 */
@Data
@ToString
@TableName("syst_user_role")
public class UserRole extends BaseEntity<UserRole> {
    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
}

