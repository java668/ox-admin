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
import com.java668.common.utils.AuthUtils;
import com.java668.oxadmin.modules.system.dto.request.UserPageReqDTO;
import com.java668.oxadmin.modules.system.dto.request.UserPassReqDTO;
import com.java668.oxadmin.modules.system.dto.request.UserReqDTO;
import com.java668.oxadmin.modules.system.dto.response.UserRespDTO;
import com.java668.oxadmin.modules.system.entity.User;
import com.java668.oxadmin.modules.system.entity.UserRole;
import com.java668.oxadmin.modules.system.mapper.UserMapper;
import com.java668.oxadmin.modules.system.service.AuthService;
import com.java668.oxadmin.modules.system.service.UserRoleService;
import com.java668.oxadmin.modules.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Jerry.chen
 * @desc 用户管理 service
 * @date 2023/02/01 16:35
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final AuthService authService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(UserReqDTO body) {
        checkParams(body);
        User entity = BeanUtil.copyProperties(body, User.class, "id");
        entity.setPassword(passwordEncoder.encode(body.getPass()));
        Integer result = baseMapper.insert(entity);
        userRoleService.batchSave(entity.getId(), body.getRoles());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(List<Long> ids) {
        // 不能删除自己
        Long userId = AuthUtils.getUserId();
        if (CollUtil.contains(ids, userId)) {
            throw new BizException("不能删除自己登录的账号");
        }
        // 删除用户角色
        userRoleService.deleteByUserIds(ids);
        // 删除用户
        int result = baseMapper.deleteBatchIds(ids);
        // 清除 session
        authService.removeUserSession(ids);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update(UserReqDTO body) {
        checkParams(body);
        User user = BeanUtil.copyProperties(body, User.class);
        int result = baseMapper.updateById(user);
        List<Long> roles = body.getRoles();
        if (CollUtil.isNotEmpty(roles)) {
            userRoleService.updateByUserId(user.getId(), roles);
        }
        if (body.getEnabled() == 1) {
            authService.removeUserSession(body.getId());
        }
        return result;
    }

    @Override
    public UserRespDTO get(Long id) {
        User user = getById(id);
        List<UserRole> userRoles = userRoleService.findByUserId(id);
        List<Long> roles = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        UserRespDTO userRespDTO = BeanUtil.copyProperties(user, UserRespDTO.class);
        userRespDTO.setRoles(roles);
        return userRespDTO;
    }

    @Override
    public PageResult<UserRespDTO> page(UserPageReqDTO params) {
        Page<User> page = params.buildPage();
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.and(
                StrUtil.isNotBlank(params.getQ()),
                wrapper -> wrapper.like(User::getUsername, params.getQ())
                        .or()
                        .like(User::getNickName, params.getQ())
                        .or()
                        .like(User::getEmail, params.getQ())
        );
        queryWrapper.ge(StrUtil.isNotBlank(params.getStartTime()), User::getCreateTime, params.getStartTime());
        queryWrapper.le(StrUtil.isNotBlank(params.getEndTime()), User::getCreateTime, params.getEndTime());
        queryWrapper.eq(Objects.nonNull(params.getEnabled()), User::getEnabled, params.getEnabled());
        Page<User> resultPage = baseMapper.selectPage(page, queryWrapper);
        return PageResult.of(resultPage, UserRespDTO.class);
    }

    @Override
    public User getByUsername(Long userId, String username) {
        if (StrUtil.isEmpty(username)) {
            return null;
        }
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username)
                .ne(ObjectUtil.isNotNull(userId), User::getId, userId);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Integer changeStatus(Long userId, Integer status) {
        User entity = new User();
        entity.setId(userId);
        entity.setEnabled(status);
        int result = baseMapper.updateById(entity);
        if (status == 1) {
            authService.removeUserSession(userId);
        }
        return result;
    }

    @Override
    public Integer modifyPass(UserPassReqDTO dto) {
        Long userId = AuthUtils.getUserId();
        User user = getById(userId);
        if (!passwordEncoder.matches(dto.getOldPass(), user.getPassword())) {
            throw new BizException("修改失败，旧密码错误");
        }
        if (passwordEncoder.matches(dto.getNewPass(), user.getPassword())) {
            throw new BizException("新密码不能与旧密码相同");
        }
        User entity = new User();
        entity.setId(userId);
        entity.setPassword(passwordEncoder.encode(dto.getNewPass()));
        return baseMapper.updateById(entity);
    }

    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    private User getByEmail(Long userId, String email) {
        if (StrUtil.isEmpty(email)) {
            return null;
        }
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getEmail, email)
                .ne(ObjectUtil.isNotNull(userId), User::getId, userId);
        return baseMapper.selectOne(wrapper);
    }

    /**
     * 参数校验
     *
     * @param body
     */
    private void checkParams(UserReqDTO body) {
        // 检查用户名是否重复
        if (ObjectUtil.isNotNull(getByUsername(body.getId(), body.getUsername()))) {
            throw new BizException("用户名已存在，请重新输入");
        }
        // 检查邮箱是否重复
        if (ObjectUtil.isNotNull(getByEmail(body.getId(), body.getEmail()))) {
            throw new BizException("邮箱已存在，请重新输入");
        }
        // 检查密码和确认密码是否一致
        String pass = body.getPass();
        String checkPass = body.getCheckPass();
        if (ObjectUtil.isNull(body.getId()) && !StrUtil.equals(pass, checkPass)) {
            throw new BizException("密码和确认密码不一致，请重新输入");
        }
    }

}