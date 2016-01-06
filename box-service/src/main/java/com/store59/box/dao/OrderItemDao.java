package com.store59.box.dao;

import com.store59.box.dao.mapper.OrderItemMapper;
import com.store59.box.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangwangyong on 15/7/27.
 */
@Repository
public class OrderItemDao {
    @Autowired
    private OrderItemMapper masterOrderItemMapper;
    @Autowired
    private OrderItemMapper slaveOrderItemMapper;

    public int addOrderItem(OrderItem orderItem){
        return masterOrderItemMapper.insertSelective(orderItem);
    }

    public List<OrderItem> getOrderItemListByOrderId(Long orderId){
        return slaveOrderItemMapper.getOrderItemListByOrderId(orderId);
    }

    public List<OrderItem> getOrderItemsByOrderIds(List<Long> orderIds){
        return slaveOrderItemMapper.getOrderItemsByOrderIds(orderIds);
    }
}
