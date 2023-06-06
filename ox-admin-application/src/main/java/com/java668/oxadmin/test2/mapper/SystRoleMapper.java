package com.java668.oxadmin.test2.mapper;

import java.util.List;
import com.java668.common.db.mapper.EasyBaseMapper;
import com.java668.oxadmin.test2.entity.SystRole;
import com.java668.oxadmin.test2.entity.SystRoleMenu;

/**
 * 角色Mapper接口
 * 
 * @author jerry.chen
 * @date 2023-06-06 16:00:03
 */
public interface SystRoleMapper extends EasyBaseMapper<SystRole> {

    /**
     * 批量删除角色
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteByIds(List<Long> ids);

    /**
     * 批量删除用户角色关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSystRoleMenuByRoleIds(List<Long> ids);
    
    /**
     * 批量新增用户角色关联
     * 
     * @param systRoleMenuList 用户角色关联列表
     * @return 结果
     */
    int batchSystRoleMenu(List<SystRoleMenu> systRoleMenuList);

    /**
     * 通过角色主键删除用户角色关联信息
     * 
     * @param id 角色ID
     * @return 结果
     */
     int deleteSystRoleMenuByRoleId(Long id);
}
