package com.store59.box.dao.mapper;

import com.store59.box.model.Order;
import com.store59.box.model.OrderRefundUpdate;
import com.store59.box.model.query.BoxOrderQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangwangyong on 15/7/20.
 */
public interface OrderMapper {
    int insertSelective(Order order);

    double findSumOfPaidOrderAmount(@Param("dormId")int dormId, @Param("startPayTime")Integer startPayTime, @Param("endPayTime")Integer endPayTime);

    List<Order> getOrderListByUid(Long uid);

    List<Order> getOrderListByOrderIds(List<Long> orderIds);

    Order getOrderById(long orderId);

    Order getOrderBySn(String orderSn);

    int updateByPrimaryKeySelective(Order order);

    int updateBySnSelective(Order order);

    int orderRefund(OrderRefundUpdate orderRefundUpdate);

    int getOrderCount(BoxOrderQuery boxOrderQuery);

    List<Order> getOrderListByDormId(@Param("dormId")Integer dormId,@Param("beginTime")Integer beginTime,@Param("endTime")Integer endTime);
}
