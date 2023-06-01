package com.java668.oxadmin.modules.system.service;

import com.java668.common.model.PageParam;
import com.java668.common.model.PageResult;
import com.java668.common.model.SysUser;

import java.util.List;

/**
 * @author Jerry.chen
 * @desc
 * @date 2023/04/17 11:36
 **/
public interface AuthService {

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    PageResult<SysUser> onlineUser(PageParam pageParam);

    /**
     * 强制用户下线
     *
     * @param userId
     */
    void removeUserSession(Long userId);

    /**
     * 批量强制用户下线
     *
     * @param userIds
     */
    void removeUserSession(List<Long> userIds);

}