package com.java668.oxadmin.test3.service.impl;

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
import com.java668.oxadmin.test3.dto.request.SystMenuReqDTO;
import com.java668.oxadmin.test3.dto.response.SystMenuRespDTO;
import com.java668.oxadmin.test3.dto.request.SystMenuPageReqDTO;

import com.java668.oxadmin.test3.mapper.SystMenuMapper;
import com.java668.oxadmin.test3.entity.SystMenu;
import com.java668.oxadmin.test3.service.ISystMenuService;
import lombok.RequiredArgsConstructor;

/**
 * 系统菜单Service业务层处理
 * 
 * @author jerry.chen
 * @date 2023-06-06 23:32:17
 */
@Service
@RequiredArgsConstructor
public class SystMenuServiceImpl extends ServiceImpl<SystMenuMapper, SystMenu> implements ISystMenuService {

    /**
     * 新增系统菜单
     *
     * @param body 系统菜单
     * @return 结果
     */
    @Override
    public Integer add(SystMenuReqDTO body) {
        SystMenu entity = BeanUtil.copyProperties(body, SystMenu.class);
        return baseMapper.insert(entity);
    }

    /**
     * 批量删除系统菜单
     *
     * @param ids 需要删除的系统菜单主键
     * @return 结果
     */
    @Override
    public Integer remove(List<Long> ids) {
        return baseMapper.deleteByIds(ids);
    }

    /**
     * 修改系统菜单
     * 
     * @param body 系统菜单
     * @return 结果
     */
    @Override
    public Integer update(SystMenuReqDTO body) {
        SystMenu entity = BeanUtil.copyProperties(body, SystMenu.class);

        return baseMapper.updateById(entity);
    }


    /**
     * 查询系统菜单
     *
     * @param id 系统菜单主键
     * @return 系统菜单
     */
    @Override
    public SystMenuRespDTO detail(Long id) {
        SystMenu entity = baseMapper.selectById(id);
        return BeanUtil.copyProperties(entity, SystMenuRespDTO.class);
    }

    /**
     * 查询系统菜单列表
     *
     * @param req
     * @return 系统菜单
     */
    @Override
    public List<SystMenuRespDTO> list(SystMenuPageReqDTO req) {
        LambdaQueryWrapper<SystMenu> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ObjectUtil.isNotNull(req.getPid()), SystMenu::getPid, req.getPid());
        queryWrapper.eq(ObjectUtil.isNotNull(req.getType()), SystMenu::getType, req.getType());
        queryWrapper.eq(StrUtil.isNotBlank(req.getTitle()), SystMenu::getTitle, req.getTitle());
        queryWrapper.like(StrUtil.isNotBlank(req.getName()), SystMenu::getName, req.getName());
        queryWrapper.eq(StrUtil.isNotBlank(req.getComponent()), SystMenu::getComponent, req.getComponent());
        queryWrapper.eq(ObjectUtil.isNotNull(req.getSort()), SystMenu::getSort, req.getSort());
        queryWrapper.eq(StrUtil.isNotBlank(req.getIcon()), SystMenu::getIcon, req.getIcon());
        queryWrapper.eq(StrUtil.isNotBlank(req.getPath()), SystMenu::getPath, req.getPath());
        queryWrapper.eq(ObjectUtil.isNotNull(req.getIframe()), SystMenu::getIframe, req.getIframe());
        queryWrapper.eq(ObjectUtil.isNotNull(req.getCache()), SystMenu::getCache, req.getCache());
        queryWrapper.eq(ObjectUtil.isNotNull(req.getHidden()), SystMenu::getHidden, req.getHidden());
        queryWrapper.eq(StrUtil.isNotBlank(req.getPermission()), SystMenu::getPermission, req.getPermission());
        return BeanUtil.copyToList(baseMapper.selectList(queryWrapper), SystMenuRespDTO.class);
    }

}
