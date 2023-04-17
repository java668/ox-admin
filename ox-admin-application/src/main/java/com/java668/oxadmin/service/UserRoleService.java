package com.java668.oxadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.oxadmin.entity.UserRole;

import java.util.List;

/**
 * 用户角色关联(UserRole)表服务接口
 *
 * @author jerry.chen
 * @since 2023-03-25 19:58:21
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 保存用户角色关联关系
     * @param id
     * @param roles
     * @return
     */
    Integer batchSave(Long id, List<Long> roles);

    /**
     * 查询角色集合
     * @param id
     * @return
     */
    List<UserRole> findByUserId(Long id);

    /**
     *
     * @param id
     * @param roles
     * @return
     */
    Integer updateByUserId(Long id, List<Long> roles);

    /**
     * 删除用户角色关系
     * @param ids
     * @return
     */
    Integer deleteByRoleIds(List<Long> ids);

    /**
     * 删除用户角色
     * @param ids
     * @return
     */
    Integer deleteByUserIds(List<Long> ids);
}

