package com.java668.oxadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java668.oxadmin.modules.system.entity.UserRole;
import com.java668.oxadmin.modules.system.mapper.UserRoleMapper;
import com.java668.oxadmin.modules.system.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色关联(UserRole)表服务实现类
 *
 * @author jerry.chen
 * @since 2023-03-25 19:58:21
 */
@Slf4j
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public Integer batchSave(Long id, List<Long> roles) {
        List<UserRole> userRoleList = roles.stream().map(item -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(id);
            userRole.setRoleId(item);
            userRole.setCreateBy("");
            userRole.setUpdateBy("");
            userRole.setCreateTime(new Date());
            userRole.setUpdateTime(new Date());
            return userRole;
        }).collect(Collectors.toList());
        Integer batchSaveRes = baseMapper.insertBatchSomeColumn(userRoleList);
        log.info("保存用户角色关联关系 result：{}", batchSaveRes);
        return batchSaveRes;
    }

    @Override
    public List<UserRole> findByUserId(Long id) {
        LambdaQueryWrapper<UserRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(UserRole::getUserId, id);
        return list(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateByUserId(Long id, List<Long> roles) {
        LambdaQueryWrapper<UserRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(UserRole::getUserId, id);
        remove(queryWrapper);
        return batchSave(id, roles);
    }

    @Override
    public Integer deleteByRoleIds(List<Long> ids) {
        LambdaQueryWrapper<UserRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(UserRole::getRoleId, ids);
        return baseMapper.delete(queryWrapper);
    }

    @Override
    public Integer deleteByUserIds(List<Long> ids) {
        LambdaQueryWrapper<UserRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(UserRole::getUserId, ids);
        return baseMapper.delete(queryWrapper);
    }

}

