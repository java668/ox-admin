package com.java668.oxadmin.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.common.model.PageResult;
import com.java668.oxadmin.modules.system.dto.request.RolePageReqDTO;
import com.java668.oxadmin.modules.system.dto.request.RoleReqDTO;
import com.java668.oxadmin.modules.system.dto.response.RoleRespDTO;
import com.java668.oxadmin.modules.system.entity.Role;

import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author jerry.chen
 * @since 2023-03-25 19:48:41
 */
public interface RoleService extends IService<Role> {

    /**
     * 添加
     * @param body
     * @return
     */
    Integer add(RoleReqDTO body);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Integer delete(List<Long> ids);

    /**
     * 修改角色
     * @param body
     * @return
     */
    Integer update(RoleReqDTO body);

    /**
     * 查询角色详情
     * @param id
     * @return
     */
    RoleRespDTO get(Long id);

    /**
     * 分页
     * @param params
     * @return
     */
    PageResult<RoleRespDTO> page(RolePageReqDTO params);

    /**
     * 查询角色
     * @return
     */
    List<RoleRespDTO> findList();

    /**
     * 保存角色菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    Integer saveMenu(Long roleId, List<Long> menuIds);
}

