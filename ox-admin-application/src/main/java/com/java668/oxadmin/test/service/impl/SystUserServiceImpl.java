package com.java668.oxadmin.test.service.impl;

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
import com.java668.oxadmin.test.dto.request.SystUserReqDTO;
import com.java668.oxadmin.test.dto.response.SystUserRespDTO;
import com.java668.oxadmin.test.dto.request.SystUserPageReqDTO;

import com.java668.oxadmin.test.mapper.SystUserMapper;
import com.java668.oxadmin.test.entity.SystUser;
import com.java668.oxadmin.test.service.ISystUserService;
import lombok.RequiredArgsConstructor;

/**
 * 系统用户Service业务层处理
 * 
 * @author jerry.chen
 * @date 2023-06-05 22:45:17
 */
@Service
@RequiredArgsConstructor
public class SystUserServiceImpl extends ServiceImpl<SystUserMapper, SystUser> implements ISystUserService {

    /**
     * 新增系统用户
     *
     * @param body 系统用户
     * @return 结果
     */
    @Override
    public Integer add(SystUserReqDTO body) {
        SystUser entity = BeanUtil.copyProperties(body, SystUser.class);
        return baseMapper.insert(entity);
    }

    /**
     * 批量删除系统用户
     *
     * @param ids 需要删除的系统用户主键
     * @return 结果
     */
    @Override
    public Integer remove(List<Long> ids) {
        return baseMapper.deleteByIds(ids);
    }

    /**
     * 修改系统用户
     * 
     * @param body 系统用户
     * @return 结果
     */
    @Override
    public Integer update(SystUserReqDTO body) {
        SystUser entity = BeanUtil.copyProperties(body, SystUser.class);
        return baseMapper.updateById(entity);
    }


    /**
     * 查询系统用户
     *
     * @param id 系统用户主键
     * @return 系统用户
     */
    @Override
    public SystUserRespDTO detail(Long id) {
        SystUser entity = baseMapper.selectById(id);
        return BeanUtil.copyProperties(entity, SystUserRespDTO.class);
    }

    /**
     * 查询系统用户列表
     *
     * @param req
     * @return 系统用户
     */
    @Override
    public PageResult<SystUserRespDTO> page(SystUserPageReqDTO req) {
        LambdaQueryWrapper<SystUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StrUtil.isNotBlank(req.getUsername()), SystUser::getUsername, req.getUsername());
        queryWrapper.like(StrUtil.isNotBlank(req.getNickName()), SystUser::getNickName, req.getNickName());
        queryWrapper.eq(ObjectUtil.isNotNull(req.getEnabled()), SystUser::getEnabled, req.getEnabled());
        Page<SystUser> page = baseMapper.selectPage(req.buildPage(), queryWrapper);
        return PageResult.of(page, SystUserRespDTO.class);
    }

}
