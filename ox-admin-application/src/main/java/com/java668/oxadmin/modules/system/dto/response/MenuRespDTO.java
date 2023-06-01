package com.java668.oxadmin.modules.system.dto.response;

import com.java668.common.model.BaseDTO;
import lombok.Data;

/**
 * @author Jerry.chen
 * @desc MenuRespDTO
 * @date 2023/03/29 18:05
 **/
@Data
public class MenuRespDTO extends BaseDTO {

    private Long id;

    private Long pid;

    private Integer type;

    private String permission;

    private String title;

    private String name;

    private Integer sort;

    private String path;

    private String component;

    private Boolean iframe;

    private Boolean cache;

    private Boolean hidden;

    private String componentName;

    private String icon;

    public String getLabel() {
        return title;
    }

}
