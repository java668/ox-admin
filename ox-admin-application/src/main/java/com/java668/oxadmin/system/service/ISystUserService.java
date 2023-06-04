package com.java668.oxadmin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.common.model.PageResult;
import com.java668.oxadmin.system.dto.request.SystUserPageReqDTO;
import com.java668.oxadmin.system.dto.request.SystUserReqDTO;
import com.java668.oxadmin.system.dto.response.SystUserRespDTO;
import com.java668.oxadmin.system.entity.SystUser;

import java.util.List;

/**
 * 系统用户Service接口
 *
 * @author jerry.chen
 * @date 2023-06-04 17:20:10
 */
public interface ISystUserService extends IService<SystUser> {

    /**
     * 新增系统用户
     *
     * @param body
     * @return
     */
    Boolean add(SystUserReqDTO body);

    /**
     * 删除系统用户
     *
     * @param ids
     * @return
     */
    Boolean remove(List<Long> ids);

    /**
     * 更新系统用户
     *
     * @param body
     * @return
     */
    Boolean update(SystUserReqDTO body);

    /**
     * 获取系统用户详细信息
     *
     * @param id
     * @return
     */
    SystUserRespDTO detail(Long id);

    /**
     * 查询系统用户列表
     *
     * @param req
     * @return
     */
    PageResult<SystUserRespDTO> page(SystUserPageReqDTO req);
}
