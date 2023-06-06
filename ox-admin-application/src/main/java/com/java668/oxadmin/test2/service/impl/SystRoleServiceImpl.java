package com.java668.oxadmin.test2.service.impl;

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
import com.java668.oxadmin.test2.dto.request.SystRoleReqDTO;
import com.java668.oxadmin.test2.dto.response.SystRoleRespDTO;
import com.java668.oxadmin.test2.dto.request.SystRolePageReqDTO;

import java.util.ArrayList;
import cn.hutool.core.collection.CollUtil;
import com.java668.common.utils.AuthUtils;
import org.springframework.transaction.annotation.Transactional;
import com.java668.oxadmin.test2.entity.SystRoleMenu;
import com.java668.oxadmin.test2.mapper.SystRoleMapper;
import com.java668.oxadmin.test2.entity.SystRole;
import com.java668.oxadmin.test2.service.ISystRoleService;
import lombok.RequiredArgsConstructor;

/**
 * 角色Service业务层处理
 *
 * @author jerry.chen
 * @date 2023-06-06 18:27:45
 */
@Service
@RequiredArgsConstructor
public class SystRoleServiceImpl extends ServiceImpl<SystRoleMapper, SystRole> implements ISystRoleService {

    /**
     * 新增角色
     *
     * @param body 角色
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(SystRoleReqDTO body) {
        SystRole entity = BeanUtil.copyProperties(body, SystRole.class);
        int rows = baseMapper.insert(entity);
        insertSystRoleMenu(entity);
        return rows;
    }

    /**
     * 批量删除角色
     *
     * @param ids 需要删除的角色主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer remove(List<Long> ids) {
        baseMapper.deleteSystRoleMenuByRoleIds(ids);
        return baseMapper.deleteByIds(ids);
    }

    /**
     * 修改角色
     *
     * @param body 角色
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update(SystRoleReqDTO body) {
        SystRole entity = BeanUtil.copyProperties(body, SystRole.class);
        baseMapper.deleteSystRoleMenuByRoleId(entity.getId());
        insertSystRoleMenu(entity);

        return baseMapper.updateById(entity);
    }


    /**
     * 查询角色
     *
     * @param id 角色主键
     * @return 角色
     */
    @Override
    public SystRoleRespDTO detail(Long id) {
        SystRole entity = baseMapper.selectById(id);
        return BeanUtil.copyProperties(entity, SystRoleRespDTO.class);
    }

    /**
     * 查询角色列表
     *
     * @param req
     * @return 角色
     */
    @Override
    public PageResult<SystRoleRespDTO> page(SystRolePageReqDTO req) {
        LambdaQueryWrapper<SystRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StrUtil.isNotBlank(req.getName()), SystRole::getName, req.getName());
        queryWrapper.eq(StrUtil.isNotBlank(req.getCode()), SystRole::getCode, req.getCode());
        queryWrapper.eq(ObjectUtil.isNotNull(req.getLevel()), SystRole::getLevel, req.getLevel());
        queryWrapper.eq(StrUtil.isNotBlank(req.getDescription()), SystRole::getDescription, req.getDescription());
        queryWrapper.eq(StrUtil.isNotBlank(req.getDataScope()), SystRole::getDataScope, req.getDataScope());
        Page<SystRole> page = baseMapper.selectPage(req.buildPage(), queryWrapper);
        return PageResult.of(page, SystRoleRespDTO.class);
    }


    /**
     * 新增用户角色关联信息
     *
     * @param systRole 角色对象
     */
    public void insertSystRoleMenu(SystRole systRole) {
        List<SystRoleMenu> systRoleMenuList = systRole.getSystRoleMenuList();
        Long id = systRole.getId();
        if (CollUtil.isNotEmpty(systRoleMenuList)) {
            String username = AuthUtils.getUsername();
            List<SystRoleMenu> list = new ArrayList<SystRoleMenu>();
            for (SystRoleMenu systRoleMenu : systRoleMenuList) {
                systRoleMenu.setRoleId(id);
                systRoleMenu.setCreateBy(username);
                systRoleMenu.setUpdateBy(username);
                systRoleMenu.setIsDeleted(0);
                list.add(systRoleMenu);
            }
            if (list.size() > 0) {
                baseMapper.batchSystRoleMenu(list);
            }
        }
    }
}
