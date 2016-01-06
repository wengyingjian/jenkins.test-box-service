package com.store59.box.dao;

import com.store59.box.dao.mapper.OrderMapper;
import com.store59.box.model.Box;
import com.store59.box.model.Order;
import com.store59.box.model.OrderRefundUpdate;
import com.store59.box.model.query.BoxOrderQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangwangyong on 15/7/20.
 */
@Repository
public class OrderDao {

    @Autowired
    private OrderMapper masterOrderMapper;
    @Autowired
    private OrderMapper slaveOrderMapper;

    public Order addOrder(Order order){
        masterOrderMapper.insertSelective(order);
        return order;
    }

    public double findSumOfPaidOrderAmount(int dormId, Integer startPayTime, Integer endPayTime) {
        return slaveOrderMapper.findSumOfPaidOrderAmount(dormId, startPayTime, endPayTime);
    }

    public List<Order> getOrderListByUid(Long uid){
        return slaveOrderMapper.getOrderListByUid(uid);
    }

    public List<Order> getOrderListByOrderIds(List<Long> orderIds){
        return slaveOrderMapper.getOrderListByOrderIds(orderIds);
    }

    public Order getOrderById(Long orderId){
        return slaveOrderMapper.getOrderById(orderId);
    }

    public Order getOrderBySn(String orderSn){
        return slaveOrderMapper.getOrderBySn(orderSn);
    }

    public int update(Order order){
        return masterOrderMapper.updateByPrimaryKeySelective(order);
    }

    public int updateBySn(Order order){ return masterOrderMapper.updateBySnSelective(order); }

    public int orderRefund(OrderRefundUpdate orderRefundUpdate){
        return masterOrderMapper.orderRefund(orderRefundUpdate);
    }

    public int getOrderCount(BoxOrderQuery boxOrderQuery){
        return slaveOrderMapper.getOrderCount(boxOrderQuery);
    }

    public List<Order> getOrderListByDormId(Integer dormId,Integer beginTime,Integer endTime){
        return slaveOrderMapper.getOrderListByDormId(dormId,beginTime,endTime);
    }
}
