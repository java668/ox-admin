package com.java668.oxadmin.modules.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.java668.common.db.entity.SuperEntity;
import lombok.Data;

/**
 * 系统菜单(Menu)表实体类
 *
 * @author jerry.chen
 * @since 2023-03-25 19:55:38
 */
@Data
@TableName("syst_menu")
public class Menu extends SuperEntity<Menu> {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 上级菜单ID
     */
    private Long pid;
    /**
     * 菜单类型
     */
    private Integer type;
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
    private Integer sort;
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
    private Boolean iframe;
    /**
     * 缓存
     */
    private Boolean cache;
    /**
     * 隐藏
     */
    private Boolean hidden;
    /**
     * 权限
     */
    private String permission;
    /**
     * 角色编码
     */
    @TableField(exist = false)
    private String code;


}

