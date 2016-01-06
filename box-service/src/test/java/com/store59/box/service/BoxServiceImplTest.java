/*
 * Copyright 2015 © 59store.com.
 *
 * BoxServiceImplTest.java
 *
 */
package com.store59.box.service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.store59.box.BoxServiceApplication;
import com.store59.box.canstants.BoxItemStatus;
import com.store59.box.canstants.BoxItemStatusQuery;
import com.store59.box.canstants.BoxStatus;
import com.store59.box.model.Box;
import com.store59.box.model.query.BoxQuery;
import com.store59.box.remoting.BoxApplyService;
import com.store59.box.remoting.BoxService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shanren on 7/20/15.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = BoxServiceApplication.class)
public class BoxServiceImplTest {

    Gson gson = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays().create();
    @Autowired
    BoxService boxService;

    @Before
    public void setUp() throws Exception {
        HessianProxyFactory hessianProxyFactoryBean = new HessianProxyFactory();
//        String url = "http://localhost:8080/box";
        hessianProxyFactoryBean.setOverloadEnabled(true);
        String url = "http://192.168.30.158:8080/boxservice/box";
//                String url = "http://localhost:9899/boxservice/box";
        boxService = (BoxService) hessianProxyFactoryBean.create(BoxService.class, url);
    }

    @Test
    public void testFindBoxList() throws MalformedURLException {
        BoxQuery box = new BoxQuery();

//        box.setDormId(21625);
//        box.setPageSize(null);
//        box.setRoom("202");
//        box.setBoxStatus(BoxStatus.APPROVED);
//        for (int i = 0;i<50;i++) {
//            System.out.println(gson.toJson(boxService.findBoxList(box, Boolean.TRUE)));
//        }
        List<Box> boxList = boxService.findBoxList(box, false).getData();
        System.out.println(boxList.size());

        System.out.println(boxList.parallelStream().map(p->p.getId()).collect(Collectors.toList()));
//        System.out.println(boxList.parallelStream().collect());
    }

    @Test
    public void testFindBoxById() throws Exception {
        System.out.println(gson.toJson(boxService.findBoxById(22, true)));
    }

    @Test
    public void testFindBoxList1() throws Exception {
        System.out.println(gson.toJson(boxService.findBoxList(1488, "202")));
    }

    @Test
    @Transactional
    public void testAddBox() throws Exception {
        Box box = new Box();
        box.setDormId(218);
        box.setRoom("202");
        box.setDormentryId(1488);
        box.setOwner("jewelknife");
        box.setPhone("1319453111");
        box.setRemark("test 3");
        box.setBoxApplyId(12);
        System.out.println(gson.toJson(boxService.addBox(box)));
    }

    @Test
    public void testUpdateBox() throws Exception {
        Box box = new Box();
        box.setDormId(218);
        box.setDormentryId(1498);
        box.setRoom("232");
        box.setId(88);
        box.setBoxStatus(BoxStatus.APPROVED);
        boxService.updateBox(box);
    }

    @Test
    public void testUpdateBoxStatus() throws Exception {
        Box box = boxService.findBoxById(1, false).getData();
        box.setSigned(true);
        box.setPayDeposit(true);
        System.out.println(gson.toJson(boxService.updateBox(box)));
    }

    @Test
    public void testOpenBox() throws Exception {
//        System.out.println(gson.toJson(boxService.openBox(1, 1412057569l)));
    }

    @Test
    public void testCloseBox() throws Exception {
        System.out.println(gson.toJson(boxService.closeBox(10)));
    }

    @Test
    public void testHasEnoughDeposit() throws Exception {
        System.out.println(gson.toJson(boxService.hasEnoughDeposit(1)));
    }

    @Test
    public void testFindBoxCount() throws Exception {
        System.out.println(gson.toJson(boxService.findBoxCount(1, BoxStatus.APPROVED)));
    }

    @Test
    public void testFindDormentryBoxNum() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1488);
        list.add(1);
        System.out.println(gson.toJson(boxService.findDormentryBoxNum(list)));
    }

    @Test
    public void testFindBoxItemList() throws Exception {
        System.out.println(gson.toJson(boxService.findBoxItemList(10, BoxItemStatusQuery.DORM_OFFLINE)));
    }

    @Test
    public void testupdateBoxItemStatus() {
        boxService.updateBoxItemStatus(91, 809, BoxItemStatus.USER_OFFLINE);
    }


    @Test
    public void testFindDormStockAmount() throws Exception {
        System.out.println(gson.toJson(boxService.findDormStockAmount(-1)));
    }

    @Test
    public void testFindSumStockByDormIdAndRid()throws Exception{
        System.out.println(gson.toJson(boxService.findSumStockByDormIdAndRid(218,10)));
    }
}
