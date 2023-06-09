package com.java668.oxadmin.modules.system.service.impl;

import java.util.List;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.java668.common.model.PageResult;
import com.java668.oxadmin.modules.system.dto.request.OperLogReqDTO;
import com.java668.oxadmin.modules.system.dto.response.OperLogRespDTO;
import com.java668.oxadmin.modules.system.dto.request.OperLogPageReqDTO;

import com.java668.oxadmin.modules.system.mapper.OperLogMapper;
import com.java668.oxadmin.modules.system.entity.OperLog;
import com.java668.oxadmin.modules.system.service.IOperLogService;
import lombok.RequiredArgsConstructor;

/**
 * 操作日志Service业务层处理
 * 
 * @author jerry.chen
 * @date 2023-06-09 11:17:20
 */
@Service
@RequiredArgsConstructor
public class OperLogServiceImpl extends ServiceImpl<OperLogMapper, OperLog> implements IOperLogService {

    /**
     * 新增操作日志
     *
     * @param body 操作日志
     * @return 结果
     */
    @Override
    public Integer add(OperLogReqDTO body) {
        OperLog entity = BeanUtil.copyProperties(body, OperLog.class);
        return baseMapper.insert(entity);
    }

    /**
     * 批量删除操作日志
     *
     * @param ids 需要删除的操作日志主键
     * @return 结果
     */
    @Override
    public Integer remove(List<Long> ids) {
        return baseMapper.deleteByIds(ids);
    }

    /**
     * 修改操作日志
     * 
     * @param body 操作日志
     * @return 结果
     */
    @Override
    public Integer update(OperLogReqDTO body) {
        OperLog entity = BeanUtil.copyProperties(body, OperLog.class);

        return baseMapper.updateById(entity);
    }


    /**
     * 查询操作日志
     *
     * @param id 操作日志主键
     * @return 操作日志
     */
    @Override
    public OperLogRespDTO detail(Long id) {
        OperLog entity = baseMapper.selectById(id);
        return BeanUtil.copyProperties(entity, OperLogRespDTO.class);
    }

    /**
     * 查询操作日志列表
     *
     * @param req
     * @return 操作日志
     */
    @Override
    public PageResult<OperLogRespDTO> page(OperLogPageReqDTO req) {
        LambdaQueryWrapper<OperLog> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StrUtil.isNotBlank(req.getTitle()), OperLog::getTitle, req.getTitle());
        queryWrapper.eq(ObjectUtil.isNotNull(req.getBusinessType()), OperLog::getBusinessType, req.getBusinessType());
        queryWrapper.eq(StrUtil.isNotBlank(req.getMethod()), OperLog::getMethod, req.getMethod());
        queryWrapper.eq(StrUtil.isNotBlank(req.getRequestMethod()), OperLog::getRequestMethod, req.getRequestMethod());
        queryWrapper.eq(ObjectUtil.isNotNull(req.getOperatorType()), OperLog::getOperatorType, req.getOperatorType());
        queryWrapper.like(StrUtil.isNotBlank(req.getOperName()), OperLog::getOperName, req.getOperName());
        queryWrapper.eq(StrUtil.isNotBlank(req.getOperUrl()), OperLog::getOperUrl, req.getOperUrl());
        queryWrapper.eq(StrUtil.isNotBlank(req.getOperIp()), OperLog::getOperIp, req.getOperIp());
        queryWrapper.eq(StrUtil.isNotBlank(req.getBrowser()), OperLog::getBrowser, req.getBrowser());
        queryWrapper.eq(StrUtil.isNotBlank(req.getOs()), OperLog::getOs, req.getOs());
        queryWrapper.eq(StrUtil.isNotBlank(req.getOperLocation()), OperLog::getOperLocation, req.getOperLocation());
        queryWrapper.eq(StrUtil.isNotBlank(req.getOperParam()), OperLog::getOperParam, req.getOperParam());
        queryWrapper.eq(StrUtil.isNotBlank(req.getJsonResult()), OperLog::getJsonResult, req.getJsonResult());
        queryWrapper.eq(ObjectUtil.isNotNull(req.getStatus()), OperLog::getStatus, req.getStatus());
        queryWrapper.eq(ObjectUtil.isNotNull(req.getCostTime()), OperLog::getCostTime, req.getCostTime());
        Page<OperLog> page = baseMapper.selectPage(req.buildPage(), queryWrapper);
        return PageResult.of(page, OperLogRespDTO.class);
    }

}
