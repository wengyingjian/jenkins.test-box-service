package com.store59.box.dao.mapper;

import com.store59.box.model.OrderItem;

import java.util.List;

/**
 * Created by zhangwangyong on 15/7/27.
 */
public interface OrderItemMapper {
    int insertSelective(OrderItem orderItem);

    List<OrderItem> getOrderItemListByOrderId(Long orderId);

    List<OrderItem> getOrderItemsByOrderIds(List<Long> orderIds);
}
