package com.java668.oxadmin.modules.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.oxadmin.modules.system.dto.request.MenuReqDTO;
import com.java668.oxadmin.modules.system.dto.response.MenuRespDTO;
import com.java668.oxadmin.modules.system.entity.Menu;

import java.util.List;

/**
 * 系统菜单(Menu)表服务接口
 *
 * @author jerry.chen
 * @since 2023-03-25 19:55:38
 */
public interface MenuService extends IService<Menu> {

    /**
     * 新增菜单
     * @param body
     * @return
     */
    Integer add(MenuReqDTO body);

    /**
     * 删除菜单
     * @param ids
     * @return
     */
    Integer delete(List<Long> ids);

    /**
     * 修改菜单
     * @param body
     * @return
     */
    Integer update(MenuReqDTO body);

    /**
     * 查询菜单详情
     * @param id
     * @return
     */
    MenuRespDTO get(Long id);

    /**
     * 查询树型列表
     * @return
     */
    List<Tree<Long>> treeList();

    /**
     * 根据 userId 查询菜单
     * @param userId
     * @return
     */
    List<Menu> findByUserId(Long userId, Boolean isRouter);

    /**
     * 懒加载菜单数据
     * @param pid
     * @return
     */
    List<MenuRespDTO> lazy(Long pid);

    /**
     * 根据ID获取同级与上级数据
     * @param ids
     * @return
     */
    List<Tree<Long>> superior(List<Long> ids);

    /**
     * 查询路由列表
     * @return
     */
    List<Tree<Long>> routerList();

}

