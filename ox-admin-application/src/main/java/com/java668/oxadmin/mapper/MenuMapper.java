package com.java668.oxadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java668.oxadmin.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统菜单(Menu)表数据库访问层
 *
 * @author jerry.chen
 * @since 2023-03-25 19:55:38
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 查询用户
     * @param userId
     * @param isRouter
     * @return
     */
    List<Menu> selectMenuByUserId(@Param("userId") Long userId, @Param("isRouter") Boolean isRouter);

}

