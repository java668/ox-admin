package com.java668.oxadmin.modules.system.dto.response;

import com.java668.common.model.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * @author Jerry.chen
 * @desc UserRespDTO
 * @date 2023/03/29 18:05
 **/
@Data
public class UserRespDTO extends BaseDTO {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;
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
    /**
     * 用户角色
     */
    private List<Long> roles;

}
