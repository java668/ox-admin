package com.java668.common.model;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jerry.chen
 * @desc PageParam
 * @date 2023/03/29 18:58
 **/
@Data
public class PageParam {

    private static final String DESCENDING = "descending";
    private static final String ASCENDING = "ascending";

    private Long page = 1L;

    private Long limit = 10L;

    private Boolean searchCount;

    private List<OrderItemDTO> orders;

    public static <T> Page<T> convertPage(PageParam pageParam) {
        Page<T> page = new Page<>();
        List<OrderItemDTO> orders = pageParam.getOrders();
        if (CollUtil.isNotEmpty(orders)) {
            List<OrderItem> orderItemList = orders.stream().map(PageParam::convertOrderItem).collect(Collectors.toList());
            page.setOrders(orderItemList);
        }
        page.setCurrent(pageParam.getPage());
        page.setSize(pageParam.getLimit());
        if (null != pageParam.getSearchCount()) {
            page.setSearchCount(pageParam.getSearchCount());
        }
        return page;
    }

    private static OrderItem convertOrderItem(OrderItemDTO itemDTO) {
        String column = StrUtil.toUnderlineCase(itemDTO.getColumn());
        String order = itemDTO.getOrder();
        if (StrUtil.equals(DESCENDING, order)) {
            return OrderItem.desc(column);
        } else if (StrUtil.equals(ASCENDING, order)) {
            return OrderItem.asc(column);
        } else {
            return null;
        }
    }

    @Data
    static class OrderItemDTO {
        private String column;
        private String order;
    }

}