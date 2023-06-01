package com.java668.oxadmin.modules.system.dto.request;

import com.java668.common.model.BaseDTO;
import com.java668.oxadmin.modules.system.dto.request.groups.Insert;
import com.java668.oxadmin.modules.system.dto.request.groups.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Jerry.chen
 * @desc UserPageReqDTO
 * @date 2023/03/29 18:05
 **/
@Data
public class UserReqDTO extends BaseDTO {
    /**
     * 用户id
     */
    @NotNull(message = "用户id必填", groups = {Update.class})
    private Long id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名必填", groups = {Insert.class, Update.class})
    @Length(max = 10, message = "用户名长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "用户密码必填", groups = {Insert.class})
    @Length(max = 20, message = "用户名长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String pass;
    /**
     * 确认密码
     */
    @NotBlank(message = "用户确认密码必填", groups = {Insert.class})
    @Length(max = 20, message = "用户名长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String checkPass;
    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称必填", groups = {Insert.class, Update.class})
    @Length(max = 10, message = "用户昵称长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String nickName;
    /**
     * 性别
     */
    @NotBlank(message = "性别必填", groups = {Insert.class, Update.class})
    @Length(max = 1, message = "用户昵称长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String gender;
    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码必填", groups = {Insert.class, Update.class})
    @Length(max = 12, message = "用户昵称长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String phone;
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱必填", groups = {Insert.class, Update.class})
    @Length(max = 30, message = "用户昵称长度必须在{max}以内", groups = {Insert.class, Update.class})
    @Email(message = "请输入正确的Email")
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 状态：1禁用、0启用
     */
    @NotNull(message = "状态必填", groups = {Insert.class, Update.class})
    @Range(min = 0, max = 1, message = "状态必须在{min}-{max}范围内", groups = {Insert.class, Update.class})
    private Integer enabled;
    /**
     * 用户角色
     */
    @NotNull(message = "用户角色必填", groups = {Insert.class, Update.class})
    @Size(min = 1, max = 100, message = "用户角色最多添加{min}-{max}个", groups = {Insert.class, Update.class})
    private List<Long> roles;

}
