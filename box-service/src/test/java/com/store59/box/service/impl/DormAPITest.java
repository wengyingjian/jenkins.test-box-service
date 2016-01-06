/**
 * This document and its contents are protected by copyright 2005 and owned by 59store.com Inc. The
 * copying and reproduction of this document and/or its content (whether wholly or partly) or any
 * incorporation of the same into any other material in any media or format of any kind is strictly
 * prohibited. All rights are reserved.
 * <p>
 * Copyright (c) 59store.com Inc. 2015
 */
package com.store59.box.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store59.box.BoxServiceApplication;
import com.store59.dorm.common.api.DormApi;
import com.store59.dorm.common.model.DormTransactionRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by shanren on 15/8/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BoxServiceApplication.class)
public class DormAPITest {

    Gson gson;
    @Autowired
    DormApi dormApi;

    @Before
    public void setUp() throws Exception {
        GsonBuilder builder = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays();
        gson = builder.create();
    }

    @Test
    public void testAddDormTransactionRecord() {
        DormTransactionRecord record = new DormTransactionRecord();
        record.setChange((float) -50);
        record.setType((short) 105);
        record.setDormId(218);
        record.setRemark("测试----扣除盒子押金, 盒子ID:" + 10);
        record.setTime((int) (new Date().getTime() / 1000));
        System.out.println(gson.toJson(dormApi.addDormTransactionRecord(record)));
    }
}
