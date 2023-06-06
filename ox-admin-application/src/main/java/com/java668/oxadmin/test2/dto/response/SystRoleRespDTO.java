package com.java668.oxadmin.test2.dto.response;

import java.util.List;
import lombok.Data;
import lombok.ToString;
import com.java668.common.model.BaseDTO;

/**
 * 角色对象 syst_role
 * 
 * @author jerry.chen
 * @date 2023-06-06 16:00:03
 */
@Data
@ToString
public class SystRoleRespDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色级别
     */
    private Long level;
    /**
     * 描述
     */
    private String description;
    /**
     * 数据权限
     */
    private String dataScope;
    /**
     * 是否删除（0:否，1：是）
     */
    private Integer isDeleted;
    /**
     * 用户角色关联信息
     */
    private List<SystRoleMenuRespDTO> systRoleMenuList;
}
