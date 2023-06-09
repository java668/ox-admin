package com.java668.oxadmin.modules.system.service;

import java.util.List;

import com.java668.oxadmin.modules.system.entity.OperLog;
import com.java668.oxadmin.modules.system.dto.request.OperLogPageReqDTO;
import com.java668.oxadmin.modules.system.dto.request.OperLogReqDTO;
import com.java668.oxadmin.modules.system.dto.response.OperLogRespDTO;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java668.common.model.PageResult;


/**
 * 操作日志Service接口
 *
 * @author jerry.chen
 * @date 2023-06-09 11:17:20
 */
public interface IOperLogService extends IService<OperLog> {

    /**
     * 新增操作日志
     *
     * @param body
     * @return
     */
    Integer add(OperLogReqDTO body);

    /**
     * 删除操作日志
     *
     * @param ids
     * @return
     */
    Integer remove(List<Long> ids);

    /**
     * 更新操作日志
     *
     * @param body
     * @return
     */
    Integer update(OperLogReqDTO body);

    /**
     * 获取操作日志详细信息
     *
     * @param id
     * @return
     */
     OperLogRespDTO detail(Long id);

    /**
     * 查询操作日志列表
     *
     * @param req
     * @return
     */
    PageResult<OperLogRespDTO> page(OperLogPageReqDTO req);
}
