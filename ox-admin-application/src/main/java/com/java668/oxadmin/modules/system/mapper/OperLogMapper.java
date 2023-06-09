package com.java668.oxadmin.modules.system.mapper;

import java.util.List;
import com.java668.common.db.mapper.EasyBaseMapper;
import com.java668.oxadmin.modules.system.entity.OperLog;

/**
 * 操作日志Mapper接口
 * 
 * @author jerry.chen
 * @date 2023-06-09 11:17:20
 */
public interface OperLogMapper extends EasyBaseMapper<OperLog> {

    /**
     * 批量删除操作日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteByIds(List<Long> ids);
}
