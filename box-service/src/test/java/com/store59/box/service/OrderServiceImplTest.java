package com.store59.box.service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store59.box.BoxServiceApplication;
import com.store59.box.canstants.OrderStatus;
import com.store59.box.model.Order;
import com.store59.box.model.OrderItemAdd;
import com.store59.box.model.query.BoxOrderQuery;
import com.store59.box.remoting.BoxService;
import com.store59.box.remoting.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by shanren on 15/8/6.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = BoxServiceApplication.class)
public class OrderServiceImplTest {

    Gson gson;
    OrderService orderService;

    @Before
    public void setUp() throws Exception {
        GsonBuilder builder = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays();
        gson = builder.create();
        HessianProxyFactory hessianProxyFactoryBean = new HessianProxyFactory();
        hessianProxyFactoryBean.setOverloadEnabled(true);
                String url = "http://localhost:8790/order";
//        String url = "http://172.16.107.218:3000/boxservice/order";
//                String url = "http://172.16.107.130:8080/boxservice/order";
        orderService = (OrderService) hessianProxyFactoryBean.create(OrderService.class, url);
    }

    @Test
    public void testAddOrder() throws Exception {
        Order order = new Order();
        order.setBoxId(3);
        OrderItemAdd orderItemAdd = new OrderItemAdd();
        order.getOrderItemAddList().add(orderItemAdd);
    }

    @Test
    public void testGetOrderList() throws Exception {
    }

    @Test
    public void testGetOrderInfo() throws Exception {

        for (int i = 0;i<10;i++) {
            System.out.println(gson.toJson(orderService.getOrderInfo(16l)));
        }

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testFindSumOfPaidOrderAmount() throws Exception {
        System.out.println(gson.toJson(orderService.findSumOfPaidOrderAmount(1, (Integer)(int)System.currentTimeMillis()/1000 - 12 * 60 * 60, (Integer)(int)System.currentTimeMillis()/1000)));
    }

    @Test
    public void testGetOrderCount(){
        BoxOrderQuery boxOrderQuery = new BoxOrderQuery();
        boxOrderQuery.setUid(1411258069l);
        boxOrderQuery.setStatus(OrderStatus.CANCEL.ordinal());
        System.out.println(gson.toJson(orderService.getOrderCount(boxOrderQuery)));
    }

    @Test
    public void testGetOrderRidListByDormIdAndRid(){
        System.out.println(gson.toJson(orderService.getOrderRidListByDormIdAndRid(218, 8, null, null)));
    }
}