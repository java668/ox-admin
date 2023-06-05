package com.java668.oxadmin.test.mapper;

import java.util.List;
import com.java668.common.db.mapper.EasyBaseMapper;
import com.java668.oxadmin.test.entity.SystUser;

/**
 * 系统用户Mapper接口
 * 
 * @author jerry.chen
 * @date 2023-06-05 22:45:17
 */
public interface SystUserMapper extends EasyBaseMapper<SystUser> {

    /**
     * 批量删除系统用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteByIds(List<Long> ids);
}
