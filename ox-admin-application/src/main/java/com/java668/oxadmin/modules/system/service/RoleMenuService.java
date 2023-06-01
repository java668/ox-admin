package com.java668.oxadmin.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.oxadmin.modules.system.entity.RoleMenu;

import java.util.List;

/**
 * 用户角色关联(RoleMenu)表服务接口
 *
 * @author jerry.chen
 * @since 2023-03-25 19:58:20
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 查询角色菜单
     * @param id
     * @return
     */
    List<RoleMenu> listByRoleId(Long id);

    /**
     * 批量保存
     * @param roleId
     * @param menuIds
     * @return
     */
    Integer batchSave(Long roleId, List<Long> menuIds);

    /**
     * 删除角色菜单关系
     * @param ids
     * @return
     */
    Integer deleteByRoleIds(List<Long> ids);

    /**
     * 删除角色菜单关联关系
     * @param ids
     * @return
     */
    Integer deleteByMenuIds(List<Long> ids);
}

