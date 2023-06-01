package com.java668.oxadmin.modules.system.dto.request;

import com.java668.oxadmin.modules.system.dto.request.groups.Insert;
import com.java668.oxadmin.modules.system.dto.request.groups.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Jerry.chen
 * @desc RoleReqDTO
 * @date 2023/03/29 18:05
 **/
@Data
public class RoleReqDTO {

    /**
     * 主键id
     */
    @NotNull(message = "角色id必填", groups = {Update.class})
    private Long id;
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称必填", groups = {Insert.class, Update.class})
    @Length(max = 10, message = "角色名长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String name;

    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码必填", groups = {Insert.class, Update.class})
    @Length(max = 10, message = "角色编码长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String code;

    /**
     * 角色描述
     */
    @NotBlank(message = "角色描述必填", groups = {Insert.class, Update.class})
    private String description;

}
