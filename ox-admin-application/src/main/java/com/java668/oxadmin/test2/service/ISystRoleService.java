package com.java668.oxadmin.test2.service;

import java.util.List;

import com.java668.oxadmin.test2.entity.SystRole;
import com.java668.oxadmin.test2.dto.request.SystRolePageReqDTO;
import com.java668.oxadmin.test2.dto.request.SystRoleReqDTO;
import com.java668.oxadmin.test2.dto.response.SystRoleRespDTO;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.common.model.PageResult;


/**
 * 角色Service接口
 *
 * @author jerry.chen
 * @date 2023-06-06 16:00:03
 */
public interface ISystRoleService extends IService<SystRole> {

    /**
     * 新增角色
     *
     * @param body
     * @return
     */
    Integer add(SystRoleReqDTO body);

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    Integer remove(List<Long> ids);

    /**
     * 更新角色
     *
     * @param body
     * @return
     */
    Integer update(SystRoleReqDTO body);

    /**
     * 获取角色详细信息
     *
     * @param id
     * @return
     */
     SystRoleRespDTO detail(Long id);

    /**
     * 查询角色列表
     *
     * @param req
     * @return
     */
    PageResult<SystRoleRespDTO> page(SystRolePageReqDTO req);

}
