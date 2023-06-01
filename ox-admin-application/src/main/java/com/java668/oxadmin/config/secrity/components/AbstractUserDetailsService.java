package com.java668.oxadmin.config.secrity.components;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.java668.common.model.SysUser;
import com.java668.oxadmin.modules.system.entity.Menu;
import com.java668.oxadmin.modules.system.entity.User;
import com.java668.oxadmin.modules.system.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户登录信息查询抽象Service
 *
 * @author jerry.chen
 * @since 2023-03-25 19:55:38
 */
@RequiredArgsConstructor
public abstract class AbstractUserDetailsService implements UserDetailsService {

    private final static String DEFAULT_ROLE_PREFIX = "ROLE_";

    private final MenuService menuService;

    /**
     * 这个方法交给子类去实现它，查询用户信息
     *
     * @param principal 用户名或者手机号
     * @return
     */
    public abstract User findUser(String principal);

    @Override
    public UserDetails loadUserByUsername(String principal) throws UsernameNotFoundException {
        // 1. 通过请求的用户名去数据库中查询用户信息
        User user = findUser(principal);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 2. 通过用户id去获取权限信息
        UserDetails userDetails = findPermission(user);
        return userDetails;
    }

    private UserDetails findPermission(User user) {
        SysUser sysUser = SysUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .nickName(user.getNickName())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .email(user.getEmail())
                .authorities(Sets.newHashSet())
                .enabled(0 == user.getEnabled())
                .accountNonLocked(Boolean.TRUE)
                .accountNonExpired(Boolean.TRUE)
                .credentialsNonExpired(Boolean.TRUE)
                .build();

        // 3. 查询该用户有哪一些权限
        List<Menu> menuList = menuService.findByUserId(user.getId(), Boolean.FALSE);
        if (CollUtil.isEmpty(menuList)) {
            return sysUser;
        }
        // 4. 封装权限信息
        Set<GrantedAuthority> authoritiesSet = new HashSet<>();
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        for (Menu menu : menuList) {
            // 权限标识
            String permission = menu.getPermission();
            // 角色编码
            String code = menu.getCode();
            if (StrUtil.isNotBlank(permission)) {
                permissions.add(permission);
                authoritiesSet.add(new SimpleGrantedAuthority(permission));
            }
            if (StrUtil.isNotBlank(code)) {
                roles.add(code);
                authoritiesSet.add(new SimpleGrantedAuthority(DEFAULT_ROLE_PREFIX + code));
            }
        }
        sysUser.setAuthorities(authoritiesSet);
        sysUser.setRoles(roles);
        sysUser.setPermissions(permissions);
        return sysUser;
    }
}
