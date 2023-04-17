package com.java668.oxadmin.dto.response;

import com.java668.common.model.BaseDTO;
import lombok.Data;

import java.util.Set;

/**
 * @author Jerry.chen
 * @desc RoleRespDTO
 * @date 2023/03/29 18:05
 **/
@Data
public class RoleRespDTO extends BaseDTO {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 名称
     */
    private String code;
    /**
     * 角色级别
     */
    private Integer level;
    /**
     * 描述
     */
    private String description;
    /**
     * 数据权限
     */
    private String dataScope;
    /**
     * 角色菜单
     */
    private Set<Long> menuList;

}
