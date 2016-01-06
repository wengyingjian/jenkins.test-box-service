/*
 * Copyright 2015 © 59store.com.
 *
 * BoxDaoTest.java
 *
 */
package com.store59.box.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store59.box.BoxServiceApplication;
import com.store59.box.canstants.BoxStatus;
import com.store59.box.model.Box;
import com.store59.box.model.query.BoxQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by shanren on 7/19/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BoxServiceApplication.class)
public class BoxDaoTest {

    @Autowired
    private BoxDao boxDao;

    static GsonBuilder builder = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays();
    static Gson gson = builder.create();

    @Test
    public void testFindBoxList() {
        BoxQuery param = new BoxQuery();
        param.setDormId(1);
        List<Box> boxList = boxDao.findBoxList(param);

        for (Box box : boxList) {
            System.out.println(gson.toJson(box));
        }
        assert boxList.size() >= 0;
    }

    @Transactional
    @Test
    public void testAddBox() {
        Box box = new Box();
        box.setRoom("702");
        box.setCode("702-3");
        box.setDormId(1);
        box.setDormentryId(2);
        box.setBoxStatus(BoxStatus.NEW);
        int ret = boxDao.addBox(box);
        System.out.println(gson.toJson(box));
        assert ret > 0;
    }

    @Test
    public void testUpdateBox() {
        Box box = new Box();
//        box.setRoom("701");
//        box.setCode("701-1");
        box.setDormId(1);
        box.setDormentryId(2);
        box.setId(2);
        box.setPayDeposit(true);
        box.setSigned(true);
        int ret = boxDao.updateBox(box);
        assert ret > 0;
    }

    @Test
    @Transactional
    public void testOpenBox() {
        int ret = boxDao.openBox(2, 2L);
        assert ret > 0;
    }

    @Test
    public void testFindBoxById() {
        Box box = boxDao.findBoxById(1);
        System.out.println(gson.toJson(box));
        assert box != null && box.getId() > 0;
    }

}
