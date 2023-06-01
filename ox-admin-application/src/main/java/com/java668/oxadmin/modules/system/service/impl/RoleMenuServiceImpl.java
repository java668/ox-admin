package com.java668.oxadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java668.oxadmin.modules.system.entity.RoleMenu;
import com.java668.oxadmin.modules.system.mapper.RoleMenuMapper;
import com.java668.oxadmin.modules.system.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色关联(RoleMenu)表服务实现类
 *
 * @author jerry.chen
 * @since 2023-03-25 19:58:21
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public List<RoleMenu> listByRoleId(Long id) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = Wrappers.<RoleMenu>lambdaQuery()
                .eq(RoleMenu::getRoleId, id);
        return list(queryWrapper);
    }

    @Override
    public Integer batchSave(Long roleId, List<Long> menuIds) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = Wrappers.<RoleMenu>lambdaQuery()
                .eq(RoleMenu::getRoleId, roleId);
        remove(queryWrapper);
        List<RoleMenu> roleMenuList = menuIds.stream().map(item -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(item);
            roleMenu.setRoleId(roleId);
            roleMenu.setIsDeleted(0);
            roleMenu.setCreateBy("admin");
            roleMenu.setUpdateBy("admin");
            roleMenu.setCreateTime(new Date());
            roleMenu.setUpdateTime(new Date());
            return roleMenu;
        }).collect(Collectors.toList());
        return baseMapper.insertBatchSomeColumn(roleMenuList);
    }

    @Override
    public Integer deleteByRoleIds(List<Long> ids) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = Wrappers.<RoleMenu>lambdaQuery()
                .in(RoleMenu::getRoleId, ids);
        return baseMapper.delete(queryWrapper);
    }

    @Override
    public Integer deleteByMenuIds(List<Long> ids) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = Wrappers.<RoleMenu>lambdaQuery()
                .in(RoleMenu::getMenuId, ids);
        return baseMapper.delete(queryWrapper);
    }
}

