package com.store59.box.service.remote;


import com.store59.box.model.Order;
import com.store59.box.model.OrderRidNum;
import com.store59.box.model.query.BoxOrderQuery;
import com.store59.box.remoting.OrderService;
import com.store59.kylin.common.model.Result;
import com.store59.rpc.utils.server.annotation.RemoteService;
import com.store59.rpc.utils.server.annotation.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhangwangyong on 15/7/19.
 */
@RemoteService(serviceType = ServiceType.HESSIAN, serviceInterface = OrderService.class, exportPath = "/order")
public class OrderServiceRemoting implements OrderService {

    @Autowired
    private OrderService orderService;

    @Override
    public Result<Order> addOrder(Order order) {
        return orderService.addOrder(order);
    }

    @Override
    public Result<Boolean> update(Order order) {
        return orderService.update(order);
    }

    @Override
    public Result<Double> findSumOfPaidOrderAmount(int dormId, Integer startPayTime, Integer endPayTime) {
        return orderService.findSumOfPaidOrderAmount(dormId, startPayTime, endPayTime);
    }

    @Override
    public Result<List<Order>> getOrderList(Long uid) {
        return orderService.getOrderList(uid);
    }

    @Override
    public Result<List<Order>> getOrderList(List<Long> orderIdList) {
        return orderService.getOrderList(orderIdList);
    }

    @Override
    public Result<Order> getOrderInfo(Long orderId) {
        return orderService.getOrderInfo(orderId);
    }

    @Override
    public Result<Order> getOrderInfo(String orderSn) {
        return orderService.getOrderInfo(orderSn);
    }

    @Override
    public Result<Boolean> orderRefund(String orderSn) {
        return orderService.orderRefund(orderSn);
    }

    @Override
    public Result<Boolean> orderRefunding(Order order) {
        return orderService.orderRefunding(order);
    }

    @Override
    public Result<Integer> getOrderCount(BoxOrderQuery boxOrderQuery) {
        return orderService.getOrderCount(boxOrderQuery);
    }

    @Override
    public Result<List<OrderRidNum>> getOrderRidListByDormIdAndRid(Integer dormId, Integer rid,Integer beginTime,Integer endTime) {
        return orderService.getOrderRidListByDormIdAndRid(dormId, rid,beginTime,endTime);
    }
}
