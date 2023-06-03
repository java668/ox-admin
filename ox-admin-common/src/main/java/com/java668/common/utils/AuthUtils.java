package com.java668.common.utils;

import com.java668.common.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author Jerry.chen
 * @desc 获取用户授权信息
 * @date 2023/04/10 12:21
 **/
@Slf4j
public class AuthUtils {

    /**
     * 获取登录用户
     *
     * @return
     */
    public static SysUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = authentication.getDetails();
        Object principal = authentication.getPrincipal();
        SysUser user = null;
        if (principal instanceof SysUser) {
            user = ((SysUser) principal);
        }
        return user;
    }

    /**
     * 获取登陆的用户id
     *
     * @return
     */
    public static Long getUserId() {
        SysUser currentUser = getCurrentUser();
        return Optional.ofNullable(currentUser).map(SysUser::getId).orElse(null);
    }

    /**
     * 获取登陆的用户名
     *
     * @return
     */
    public static String getUsername() {
        SysUser currentUser = getCurrentUser();
        return Optional.ofNullable(currentUser).map(SysUser::getUsername).orElse("");
    }

}