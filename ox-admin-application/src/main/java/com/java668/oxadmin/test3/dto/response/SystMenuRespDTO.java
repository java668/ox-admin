package com.java668.oxadmin.test3.dto.response;

import lombok.Data;
import lombok.ToString;
import com.java668.common.model.BaseDTO;

/**
 * 系统菜单对象 syst_menu
 * 
 * @author jerry.chen
 * @date 2023-06-06 23:32:17
 */
@Data
@ToString
public class SystMenuRespDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 上级菜单ID
     */
    private Long pid;
    /**
     * 菜单类型
     */
    private Long type;
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 组件名称
     */
    private String name;
    /**
     * 组件
     */
    private String component;
    /**
     * 排序
     */
    private Long sort;
    /**
     * 图标
     */
    private String icon;
    /**
     * 链接地址
     */
    private String path;
    /**
     * 是否外链
     */
    private Integer iframe;
    /**
     * 缓存
     */
    private Integer cache;
    /**
     * 隐藏
     */
    private Integer hidden;
    /**
     * 权限
     */
    private String permission;
    /**
     * 是否删除（0:否，1：是）
     */
    private Long isDeleted;
}
