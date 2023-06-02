package com.java668.oxadmin.modules.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.java668.common.db.entity.SuperEntity;
import lombok.Data;

/**
 * 用户角色关联(UserRole)表实体类
 *
 * @author jerry.chen
 * @since 2023-03-25 19:58:21
 */
@Data
@TableName("syst_user_role")
public class UserRole extends SuperEntity<UserRole> {
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

