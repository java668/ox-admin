package com.java668.oxadmin.test3.mapper;

import java.util.List;
import com.java668.common.db.mapper.EasyBaseMapper;
import com.java668.oxadmin.test3.entity.SystMenu;

/**
 * 系统菜单Mapper接口
 * 
 * @author jerry.chen
 * @date 2023-06-06 23:32:17
 */
public interface SystMenuMapper extends EasyBaseMapper<SystMenu> {

    /**
     * 批量删除系统菜单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteByIds(List<Long> ids);
}
