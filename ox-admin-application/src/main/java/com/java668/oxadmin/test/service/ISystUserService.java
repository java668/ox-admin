package com.java668.oxadmin.test.service;

import java.util.List;

import com.java668.oxadmin.test.entity.SystUser;
import com.java668.oxadmin.test.dto.request.SystUserPageReqDTO;
import com.java668.oxadmin.test.dto.request.SystUserReqDTO;
import com.java668.oxadmin.test.dto.response.SystUserRespDTO;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.common.model.PageResult;


/**
 * 系统用户Service接口
 *
 * @author jerry.chen
 * @date 2023-06-05 22:45:17
 */
public interface ISystUserService extends IService<SystUser> {

    /**
     * 新增系统用户
     *
     * @param body
     * @return
     */
    Integer add(SystUserReqDTO body);

    /**
     * 删除系统用户
     *
     * @param ids
     * @return
     */
    Integer remove(List<Long> ids);

    /**
     * 更新系统用户
     *
     * @param body
     * @return
     */
    Integer update(SystUserReqDTO body);

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
