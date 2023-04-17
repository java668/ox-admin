package com.java668.common.model;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jerry.chen
 * @desc 分页结果
 * @date 2023/03/29 18:05
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -275582248840137389L;
    /**
     * 总数
     */
    private Integer total;

    /**
     * 当前页结果集
     */
    private List<T> list;


    public static <T> PageResult<T> of(Page page, Class<T> clazz) {
        List<T> list = BeanUtil.copyToList(page.getRecords(), clazz);
        return PageResult.<T>builder().list(list).total((int) page.getTotal()).build();
    }
}
