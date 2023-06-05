package com.java668.oxadmin.modules.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.java668.common.exception.BusinessException;
import com.java668.common.utils.AuthUtils;
import com.java668.oxadmin.modules.system.dto.request.MenuReqDTO;
import com.java668.oxadmin.modules.system.dto.response.MenuRespDTO;
import com.java668.oxadmin.modules.system.entity.Menu;
import com.java668.common.enums.MenuTypeEnum;
import com.java668.oxadmin.modules.system.mapper.MenuMapper;
import com.java668.oxadmin.modules.system.service.MenuService;
import com.java668.oxadmin.modules.system.service.RoleMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单(Menu)表服务实现类
 *
 * @author jerry.chen
 * @since 2023-03-25 19:55:38
 */
@Slf4j
@RequiredArgsConstructor
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final MenuMapper menuMapper;
    private final RoleMenuService roleMenuService;
    /**
     * treeNodeConfig treeUtil 配置
     */
    private static TreeNodeConfig treeNodeConfig;

    static {
        // 配置
        treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名，即返回列表里对象的字段名
        treeNodeConfig.setIdKey("id");
        treeNodeConfig.setNameKey("title");
        treeNodeConfig.setParentIdKey("pid");
        treeNodeConfig.setWeightKey("sort");
        treeNodeConfig.setChildrenKey("children");
    }

    @Override
    public Integer add(MenuReqDTO body) {
        checkParams(body);
        Menu menu = BeanUtil.copyProperties(body, Menu.class);
        return baseMapper.insert(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(List<Long> ids) {
        // 查询是否有子菜单
        List<Menu> menuList = listByPids(ids);
        if (CollUtil.isNotEmpty(menuList)) {
            throw new BusinessException("不能删除含有子节点的菜单");
        }
        // 删除菜单关联关系
        roleMenuService.deleteByMenuIds(ids);
        // 删除菜单
        return baseMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer update(MenuReqDTO body) {
        checkParams(body);
        Menu menu = BeanUtil.copyProperties(body, Menu.class);
        return baseMapper.updateById(menu);
    }

    @Override
    public MenuRespDTO get(Long id) {
        Menu menu = baseMapper.selectById(id);
        return BeanUtil.copyProperties(menu, MenuRespDTO.class);
    }

    @Override
    public List<Tree<Long>> treeList() {
        List<Menu> menuList = list();
        if (CollUtil.isEmpty(menuList)) {
            return Lists.newArrayList();
        }
        return buildTree(menuList);
    }

    @Override
    public List<Menu> findByUserId(Long userId, Boolean isRouter) {
        return menuMapper.selectMenuByUserId(userId, isRouter);
    }


    @Override
    public List<MenuRespDTO> lazy(Long pid) {
        List<Menu> menuList = listByPid(pid);
        return BeanUtil.copyToList(menuList, MenuRespDTO.class);
    }

    @Override
    public List<Tree<Long>> superior(List<Long> ids) {
        List<Menu> menuList = Lists.newArrayList();
        ids.forEach(id -> {
            Menu menu = baseMapper.selectById(id);
            getSuperior(menu, menuList);
        });
        return buildTree(menuList);
    }

    @Override
    public List<Tree<Long>> routerList() {
        Long userId = AuthUtils.getUserId();
        log.info("userId: {}", userId);
        List<Menu> menuList = findByUserId(userId, Boolean.TRUE);
        List<Tree<Long>> treeList = TreeUtil.<Menu, Long>build(menuList, 0L, treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setName(treeNode.getTitle());
            tree.setParentId(treeNode.getPid());
            tree.setWeight(treeNode.getSort());
            // 扩展属性
            tree.putExtra("name", treeNode.getName());
            tree.putExtra("path", treeNode.getPath());
            tree.putExtra("hidden", treeNode.getHidden());

            if (0 == treeNode.getPid()) {
                tree.putExtra("name", treeNode.getTitle());
                tree.putExtra("alwaysShow", true);
                tree.putExtra("redirect", "noRedirect");
                tree.putExtra("component", "Layout");
            } else if (0 == treeNode.getType()) {
                tree.putExtra("name", treeNode.getTitle());
                tree.putExtra("alwaysShow", true);
                tree.putExtra("redirect", "noRedirect");
                tree.putExtra("component", "ParentView");
            } else {
                tree.putExtra("component", treeNode.getComponent());
            }
            Map<String, Object> meta = Maps.newHashMapWithExpectedSize(3);
            meta.put("title", treeNode.getTitle());
            meta.put("icon", treeNode.getIcon());
            meta.put("noCache", !treeNode.getCache());
            tree.putExtra("meta", meta);
        });
        return CollUtil.isEmpty(treeList) ? Lists.newArrayList() : treeList;
    }

    private List<Menu> getSuperior(Menu menu, List<Menu> menuList) {
        Long pid = menu.getPid();
        if (ObjectUtil.isNull(pid) || pid == 0) {
            menuList.addAll(listByPid(pid));
            return menuList;
        }
        menuList.addAll(listByPid(menu.getPid()));
        return getSuperior(getById(menu.getPid()), menuList);
    }

    private List<Menu> listByPid(Long pid) {
        LambdaQueryWrapper<Menu> queryWrapper = Wrappers.<Menu>lambdaQuery().eq(Menu::getPid, pid);
        return baseMapper.selectList(queryWrapper);
    }

    private List<Menu> listByPids(List<Long> pids) {
        LambdaQueryWrapper<Menu> queryWrapper = Wrappers.<Menu>lambdaQuery().in(Menu::getPid, pids);
        return baseMapper.selectList(queryWrapper);
    }

    private List<Tree<Long>> buildTree(List<Menu> menuList) {
        List<Tree<Long>> treeList = TreeUtil.<Menu, Long>build(menuList, 0L, treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setName(treeNode.getTitle());
            tree.setParentId(treeNode.getPid());
            tree.setWeight(treeNode.getSort());
            // 扩展属性 ...
            tree.putExtra("type", treeNode.getType());
            tree.putExtra("name", treeNode.getName());
            tree.putExtra("label", treeNode.getTitle());
            tree.putExtra("component", treeNode.getComponent());
            tree.putExtra("sort", treeNode.getSort());
            tree.putExtra("icon", treeNode.getIcon());
            tree.putExtra("path", treeNode.getPath());
            tree.putExtra("iframe", treeNode.getIframe());
            tree.putExtra("cache", treeNode.getCache());
            tree.putExtra("hidden", treeNode.getHidden());
            tree.putExtra("permission", treeNode.getPermission());
            tree.putExtra("createTime", treeNode.getCreateTime());
        });
        return CollUtil.isEmpty(treeList) ? Lists.newArrayList() : treeList;
    }

    /**
     * 参数校验
     * @param body
     */
    private void checkParams(MenuReqDTO body) {
        Integer type = body.getType();
        if (MenuTypeEnum.DIRECTORY.getCode().equals(type) || MenuTypeEnum.MENU.getCode().equals(type)) {
            if (ObjectUtil.isNull(body.getIcon())) {
                throw new BusinessException("菜单图标必填");
            }
            if (StrUtil.isBlank(body.getTitle())) {
                throw new BusinessException("菜单标题必填");
            }
            if (StrUtil.isBlank(body.getPath())) {
                throw new BusinessException("路由地址必填");
            }
            if (StrUtil.isBlank(body.getName())) {
                throw new BusinessException("组件名称必填");
            }
            if (MenuTypeEnum.MENU.getCode().equals(type)) {
                if (StrUtil.isBlank(body.getComponent())) {
                    throw new BusinessException("组件路径必填");
                }
            }
        } else {
            if (StrUtil.isBlank(body.getTitle())) {
                throw new BusinessException("按钮名称必填");
            }
        }
    }
}

