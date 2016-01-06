package com.store59.box.remoting;

import com.store59.box.model.Order;
import com.store59.box.model.OrderRidNum;
import com.store59.box.model.query.BoxOrderQuery;
import com.store59.kylin.common.model.Result;

import java.util.List;

/**
 * Created by shanren on 7/18/15.
 */
public interface OrderService {

    Result<Order> addOrder(Order order);

    Result<Boolean> update(Order order);

    Result<Double> findSumOfPaidOrderAmount(int dormId, Integer startPayTime, Integer endPayTime);

    Result<List<Order>> getOrderList(Long uid);

    Result<List<Order>> getOrderList(List<Long> orderIdList);

    Result<Order> getOrderInfo(Long orderId);

    Result<Order> getOrderInfo(String orderSn);

    Result<Boolean> orderRefund(String orderSn);

    Result<Boolean> orderRefunding(Order order);

    Result<Integer> getOrderCount(BoxOrderQuery boxOrderQuery);

    Result<List<OrderRidNum>> getOrderRidListByDormIdAndRid(Integer dormId,Integer rid,Integer beginTime,Integer endTime);
}
