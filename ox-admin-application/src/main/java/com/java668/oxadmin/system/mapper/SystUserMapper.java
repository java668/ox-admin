package com.java668.oxadmin.system.mapper;

import com.java668.common.db.mapper.EasyBaseMapper;
import com.java668.oxadmin.system.entity.SystUser;

import java.util.List;

/**
 * 系统用户Mapper接口
 *
 * @author jerry.chen
 * @date 2023-06-04 17:20:10
 */
public interface SystUserMapper extends EasyBaseMapper<SystUser> {

    /**
     * 批量删除系统用户
     *
     * @param list 需要删除的数据主键集合
     * @return 结果
     */
    int deleteByIds(List<Long> list);
    
}
