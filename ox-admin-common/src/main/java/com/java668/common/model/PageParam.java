package com.java668.common.model;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
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

    private Long page;

    private Long limit;

    private Boolean searchCount;

    private List<OrderItemDTO> orders;

    public Long getPage() {
        if (ObjectUtil.isNull(page)) {
            return 1L;
        }
        return this.page;
    }

    public Long getLimit() {
        if (ObjectUtil.isNull(limit)) {
            return 10L;
        }
        return this.limit;
    }

    public <T> Page<T> buildPage() {
        Page<T> page = new Page<>();
        List<OrderItemDTO> orders = this.getOrders();
        if (CollUtil.isNotEmpty(orders)) {
            List<OrderItem> orderItemList = orders.stream().map(this::convertOrderItem).collect(Collectors.toList());
            page.setOrders(orderItemList);
        }
        page.setCurrent(this.getPage());
        page.setSize(this.getLimit());
        if (null != this.getSearchCount()) {
            page.setSearchCount(this.getSearchCount());
        }
        return page;
    }

    private OrderItem convertOrderItem(OrderItemDTO itemDTO) {
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