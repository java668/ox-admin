package com.java668.oxadmin.test3.service;

import java.util.List;

import com.java668.oxadmin.test3.entity.SystMenu;
import com.java668.oxadmin.test3.dto.request.SystMenuPageReqDTO;
import com.java668.oxadmin.test3.dto.request.SystMenuReqDTO;
import com.java668.oxadmin.test3.dto.response.SystMenuRespDTO;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.common.model.PageResult;


/**
 * 系统菜单Service接口
 *
 * @author jerry.chen
 * @date 2023-06-06 23:32:17
 */
public interface ISystMenuService extends IService<SystMenu> {

    /**
     * 新增系统菜单
     *
     * @param body
     * @return
     */
    Integer add(SystMenuReqDTO body);

    /**
     * 删除系统菜单
     *
     * @param ids
     * @return
     */
    Integer remove(List<Long> ids);

    /**
     * 更新系统菜单
     *
     * @param body
     * @return
     */
    Integer update(SystMenuReqDTO body);

    /**
     * 获取系统菜单详细信息
     *
     * @param id
     * @return
     */
     SystMenuRespDTO detail(Long id);

    /**
     * 查询系统菜单列表
     *
     * @param req
     * @return
     */
    List<SystMenuRespDTO> list(SystMenuPageReqDTO req);
}
