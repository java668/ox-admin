package com.java668.oxadmin.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.common.model.PageResult;
import com.java668.oxadmin.modules.system.dto.request.UserPageReqDTO;
import com.java668.oxadmin.modules.system.dto.request.UserPassReqDTO;
import com.java668.oxadmin.modules.system.dto.request.UserReqDTO;
import com.java668.oxadmin.modules.system.dto.response.UserRespDTO;
import com.java668.oxadmin.modules.system.entity.User;

import java.util.List;

/**
 * @author Jerry.chen
 * @desc
 * @date 2023/02/01 16:35
 **/
public interface UserService extends IService<User> {

    /**
     * 新增用户
     *
     * @param body
     * @return
     */
    Boolean add(UserReqDTO body);

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    Boolean delete(List<Long> ids);

    /**
     * 更新用户
     *
     * @param body
     * @return
     */
    Boolean update(UserReqDTO body);

    /**
     * 获取用户详情
     *
     * @param id
     * @return
     */
    UserRespDTO get(Long id);

    /**
     * 分页
     *
     * @param params
     * @return
     */
    PageResult<UserRespDTO> page(UserPageReqDTO params);

    /**
     * 根据username查询用户
     *
     * @param userId
     * @param username
     * @return
     */
    User getByUsername(Long userId, String username);

    /**
     * 启用停用
     *
     * @param userId
     * @param status
     * @return
     */
    Boolean changeStatus(Long userId, Integer status);

    /**
     * 修改密码
     *
     * @param dto
     * @return
     */
    Boolean modifyPass(UserPassReqDTO dto);
}