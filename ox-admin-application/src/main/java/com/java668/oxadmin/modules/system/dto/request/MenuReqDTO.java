package com.java668.oxadmin.modules.system.dto.request;

import com.java668.oxadmin.modules.system.dto.request.groups.Insert;
import com.java668.oxadmin.modules.system.dto.request.groups.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * MenuReqDTO
 *
 * @author jerry.chen
 * @since 2023-03-25 19:55:38
 */
@Data
public class
MenuReqDTO {

    @NotNull(message = "菜单id必填", groups = {Update.class})
    private Long id;

    private Long pid;

    @NotNull(message = "菜单类型必填", groups = {Insert.class, Update.class})
    @Range(max = 2, message = "菜单类型必须在{min}-{max}范围内", groups = {Insert.class, Update.class})
    private Integer type;

    @Length(max = 255, message = "权限长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String permission;

    @Length(max = 255, message = "菜单标题长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String title;

    @Length(max = 255, message = "组件名称必须在{max}以内", groups = {Insert.class, Update.class})
    private String name;

    private Integer sort;

    @Length(max = 255, message = "链接地址长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String path;

    @Length(max = 255, message = "组件长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String component;

    private Boolean iframe;

    private Boolean cache;

    private Boolean hidden;

    @Length(max = 255, message = "图标长度必须在{max}以内", groups = {Insert.class, Update.class})
    private String icon;

}
