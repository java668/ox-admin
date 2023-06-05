package com.java668.oxadmin.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java668.common.model.PageResult;
import com.java668.oxadmin.system.dto.request.SystUserPageReqDTO;
import com.java668.oxadmin.system.dto.request.SystUserReqDTO;
import com.java668.oxadmin.system.dto.response.SystUserRespDTO;
import com.java668.oxadmin.system.entity.SystUser;
import com.java668.oxadmin.system.mapper.SystUserMapper;
import com.java668.oxadmin.system.service.ISystUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统用户Service业务层处理
 *
 * @author jerry.chen
 * @date 2023-06-04 17:20:10
 */
@Service
@RequiredArgsConstructor
public class SystUserServiceImpl extends ServiceImpl<SystUserMapper, SystUser> implements ISystUserService {

    @Override
    public int add(SystUserReqDTO body) {
        SystUser entity = BeanUtil.copyProperties(body, SystUser.class);
        return baseMapper.insert(entity);
    }

    @Override
    public int remove(List<Long> ids) {
        return baseMapper.deleteByIds(ids);
    }

    @Override
    public int update(SystUserReqDTO body) {
        SystUser entity = BeanUtil.copyProperties(body, SystUser.class);
        return baseMapper.updateById(entity);
    }

    @Override
    public SystUserRespDTO detail(Long id) {
        SystUser entity = baseMapper.selectById(id);
        return BeanUtil.copyProperties(entity, SystUserRespDTO.class);
    }

    @Override
    public PageResult<SystUserRespDTO> page(SystUserPageReqDTO req) {
        LambdaQueryWrapper<SystUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StrUtil.isNotBlank(req.getUsername()), SystUser::getUsername, req.getUsername());
        Page<SystUser> page = baseMapper.selectPage(req.buildPage(), queryWrapper);
        return PageResult.of(page, SystUserRespDTO.class);
    }
}
