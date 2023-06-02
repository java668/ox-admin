package com.java668.oxadmin.modules.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.java668.common.db.entity.SuperEntity;
import lombok.Data;

/**
 * 系统用户(User)表实体类
 *
 * @author jerry.chen
 * @since 2023-03-25 19:39:34
 */
@Data
@TableName("syst_user")
public class User extends SuperEntity<User> {
    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 密码
     */
    private String nickName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 状态：1启用、0禁用
     */
    private Integer enabled;

}

