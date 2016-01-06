/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.store59.box.service.remote;

import com.store59.box.remoting.JobService;
import com.store59.rpc.utils.server.annotation.RemoteService;
import com.store59.rpc.utils.server.annotation.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author <a href="mailto:zhangwangy@59store.com">阿勇</a>
 * @version 1.0 15/12/28
 * @since 1.0
 */
@RemoteService(serviceType = ServiceType.HESSIAN, serviceInterface = JobService.class, exportPath = "/job")
public class JobServiceRemoting implements JobService{

    @Autowired
    private JobService jobService;

    @Override
    public void sendCouponForNewBox() {
        jobService.sendCouponForNewBox();
    }
}
