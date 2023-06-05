package com.java668.oxadmin.test.dto.request;

import lombok.Data;
import lombok.ToString;

/**
 * 系统用户对象 syst_user
 * 
 * @author jerry.chen
 * @date 2023-06-05 22:45:17
 */
@Data
@ToString
public class SystUserReqDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
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
     * 昵称
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
     * 状态：0启用、1禁用
     */
    private Long enabled;
    /**
     * 是否删除（0:否，1：是）
     */
    private Long isDeleted;
}
