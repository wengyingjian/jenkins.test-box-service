/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.store59.box.service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.store59.box.remoting.BoxService;
import com.store59.box.remoting.JobService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;

/**
 *
 * @author <a href="mailto:zhangwangy@59store.com">阿勇</a>
 * @version 1.0 15/12/28
 * @since 1.0
 */
public class JobServiceTest {

    Gson gson = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays().create();
    @Autowired
    JobService jobService;

    @Before
    public void setUp() throws Exception {
        HessianProxyFactory hessianProxyFactoryBean = new HessianProxyFactory();
//        String url = "http://localhost:8080/job";
        hessianProxyFactoryBean.setOverloadEnabled(true);
        String url = "http://192.168.30.158:8080/boxservice/job";
//                String url = "http://localhost:9899/boxservice/box";
        jobService = (JobService) hessianProxyFactoryBean.create(JobService.class, url);
    }

    @Test
    public void testSendCouponForNewBox(){
        jobService.sendCouponForNewBox();
    }
}
