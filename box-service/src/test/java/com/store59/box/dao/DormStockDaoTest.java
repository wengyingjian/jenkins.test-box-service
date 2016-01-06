package com.store59.box.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store59.box.BoxServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by shanren on 15/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BoxServiceApplication.class)
@Transactional
public class DormStockDaoTest {

    @Autowired
    DormStockDao dormStockDao;
    Gson gson;

    @Before
    public void setUp() throws Exception {
        GsonBuilder builder = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays();
        gson = builder.create();
    }

    @Test
    public void testUpdateStock() throws Exception {

    }

    @Test
    public void testFindStockById() throws Exception {

    }

    @Test
    public void testFindStockList() throws Exception {

    }
}