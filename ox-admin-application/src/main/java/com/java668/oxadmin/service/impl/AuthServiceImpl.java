package com.java668.oxadmin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.google.common.collect.Lists;
import com.java668.common.model.PageParam;
import com.java668.common.model.PageResult;
import com.java668.common.model.SysUser;
import com.java668.oxadmin.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Jerry.chen
 * @desc
 * @date 2023/04/17 11:36
 **/
@Slf4j
@RequiredArgsConstructor
@Service("authService")
public class AuthServiceImpl implements AuthService {

    private final SessionRegistry sessionRegistry;

    @Override
    public PageResult<SysUser> onlineUser(PageParam pageParam) {
        List<SysUser> users = sessionRegistry.getAllPrincipals().stream()
                .map(principal -> {
                    if (principal instanceof SysUser) {
                        SysUser sysUser = (SysUser) principal;
                        return sysUser;
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
        List<SysUser> results = ListUtil.page(pageParam.getPage().intValue() - 1, pageParam.getLimit().intValue(), users);
        return PageResult.<SysUser>builder().list(results).total(results.size()).build();
    }

    @Override
    public void removeUserSession(Long userId) {
        removeUserSession(Lists.newArrayList(userId));
    }

    @Override
    public void removeUserSession(List<Long> userIds) {
        // 获取session中所有的用户信息
        List<Object> users = sessionRegistry.getAllPrincipals();
        for (Object principal : users) {
            if (principal instanceof SysUser) {
                final SysUser sysUser = (SysUser) principal;
                if (CollUtil.contains(userIds, sysUser.getId())) {
                    // false代表不包含过期session
                    List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                    if (null != sessionsInfo && sessionsInfo.size() > 0) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            sessionInformation.expireNow();
                        }
                    }
                }
            }
        }
    }
}