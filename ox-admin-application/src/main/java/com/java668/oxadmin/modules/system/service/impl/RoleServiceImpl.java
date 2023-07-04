package com.java668.oxadmin.modules.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java668.common.exception.BizException;
import com.java668.common.model.PageResult;
import com.java668.oxadmin.modules.system.dto.request.RolePageReqDTO;
import com.java668.oxadmin.modules.system.dto.request.RoleReqDTO;
import com.java668.oxadmin.modules.system.dto.response.RoleRespDTO;
import com.java668.oxadmin.modules.system.entity.Role;
import com.java668.oxadmin.modules.system.entity.RoleMenu;
import com.java668.oxadmin.modules.system.mapper.RoleMapper;
import com.java668.oxadmin.modules.system.service.RoleMenuService;
import com.java668.oxadmin.modules.system.service.RoleService;
import com.java668.oxadmin.modules.system.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色表(Role)表服务实现类
 *
 * @author jerry.chen
 * @since 2023-03-25 19:48:41
 */
@Slf4j
@RequiredArgsConstructor
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMenuService roleMenuService;
    private final UserRoleService userRoleService;

    @Override
    public Integer add(RoleReqDTO body) {
        checkParams(body);
        Role role = BeanUtil.copyProperties(body, Role.class);
        return baseMapper.insert(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(List<Long> ids) {
        // 删除角色菜单关系
        roleMenuService.deleteByRoleIds(ids);
        // 删除用户角色关系
        userRoleService.deleteByRoleIds(ids);
        // 删除角色
        return baseMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer update(RoleReqDTO body) {
        checkParams(body);
        Role role = BeanUtil.copyProperties(body, Role.class);
        return baseMapper.updateById(role);
    }

    @Override
    public RoleRespDTO get(Long id) {
        Role role = baseMapper.selectById(id);
        List<RoleMenu> roleMenuList = roleMenuService.listByRoleId(id);
        Set<Long> menuList = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
        RoleRespDTO roleRespDTO = BeanUtil.copyProperties(role, RoleRespDTO.class);
        roleRespDTO.setMenuList(menuList);
        return roleRespDTO;
    }

    @Override
    public List<RoleRespDTO> findList() {
        List<Role> list = list();
        return BeanUtil.copyToList(list, RoleRespDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveMenu(Long roleId, List<Long> menuIds) {
        if (CollUtil.isEmpty(menuIds)) {
            throw new BizException("请选择菜单");
        }
        return roleMenuService.batchSave(roleId, menuIds);
    }

    @Override
    public PageResult<RoleRespDTO> page(RolePageReqDTO params) {
        Page<Role> page = params.buildPage();
        LambdaQueryWrapper<Role> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.and(
                StrUtil.isNotBlank(params.getQ()),
                wrapper -> wrapper.like(Role::getName, params.getQ())
                        .or()
                        .like(Role::getDescription, params.getQ())
        );
        queryWrapper.ge(StrUtil.isNotBlank(params.getStartTime()), Role::getCreateTime, params.getStartTime());
        queryWrapper.le(StrUtil.isNotBlank(params.getEndTime()), Role::getCreateTime, params.getEndTime());
        Page<Role> resultPage = baseMapper.selectPage(page, queryWrapper);
        return PageResult.of(resultPage, RoleRespDTO.class);
    }

    private void checkParams(RoleReqDTO body) {
        // 检查角色名是否重复
        if (ObjectUtil.isNotNull(getByName(body.getId(), body.getName()))) {
            throw new BizException("角色名称已存在，请重新输入");
        }
        // 检查角色编码是否重复
        if (ObjectUtil.isNotNull(getByCode(body.getId(), body.getCode()))) {
            throw new BizException("角色编码已存在，请重新输入");
        }
    }

    private Role getByName(Long roleId, String name) {
        LambdaQueryWrapper<Role> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Role::getName, name);
        queryWrapper.ne(ObjectUtil.isNotNull(roleId), Role::getId, roleId);
        return baseMapper.selectOne(queryWrapper);
    }

    private Role getByCode(Long roleId, String code) {
        LambdaQueryWrapper<Role> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Role::getCode, code);
        queryWrapper.ne(ObjectUtil.isNotNull(roleId), Role::getId, roleId);
        return baseMapper.selectOne(queryWrapper);
    }
}

