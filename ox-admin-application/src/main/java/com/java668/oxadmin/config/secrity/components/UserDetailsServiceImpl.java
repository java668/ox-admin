package com.java668.oxadmin.config.secrity.components;

import com.java668.oxadmin.entity.User;
import com.java668.oxadmin.service.MenuService;
import com.java668.oxadmin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户登录信息查询Service
 *
 * @author jerry.chen
 * @since 2023-03-25 19:55:38
 */
@Slf4j
@Component
public class UserDetailsServiceImpl extends AbstractUserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(MenuService menuService, UserService userService) {
        super(menuService);
        this.userService = userService;
    }

    @Override
    public User findUser(String principal) {
        return userService.getByUsername(null, principal);
    }

}
