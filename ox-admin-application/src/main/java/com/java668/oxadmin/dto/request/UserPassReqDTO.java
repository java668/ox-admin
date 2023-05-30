package com.java668.oxadmin.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Jerry.chen
 * @desc UserPassReqDTO
 * @date 2023/03/29 18:05
 **/
@Data
public class UserPassReqDTO {

    @NotBlank(message = "旧密码必填")
    private String oldPass;

    @NotBlank(message = "新密码必填")
    private String newPass;

}