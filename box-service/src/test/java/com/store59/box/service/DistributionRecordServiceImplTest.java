package com.store59.box.service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.store59.box.canstants.DistributionRecordAddBy;
import com.store59.box.canstants.DistributionRecordStatus;
import com.store59.box.model.distribution.DistributionRecordAddRequest;
import com.store59.box.model.distribution.DistributionRecordUpgradeRequest;
import com.store59.box.model.query.DistributionRecordQuery;
import com.store59.box.remoting.DislikeRepoService;
import com.store59.box.remoting.DistributionRecordService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwangyong on 15/10/9.
 */
public class DistributionRecordServiceImplTest {
    Gson gson = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays().create();
    @Autowired
    DistributionRecordService distributionRecordService;

    @Before
    public void setUp() throws Exception {
        HessianProxyFactory hessianProxyFactoryBean = new HessianProxyFactory();
        String url = "http://localhost:8790/distributionrecord";
        hessianProxyFactoryBean.setOverloadEnabled(true);
//        String url = "http://192.168.30.162:8080/boxservice/distributionrecord";
        distributionRecordService = (DistributionRecordService) hessianProxyFactoryBean.create(DistributionRecordService.class, url);
    }

    @Test
    public void testAddDistributionRecord(){
        DistributionRecordAddRequest distributionRecordAddRequest = new DistributionRecordAddRequest();
        distributionRecordAddRequest.setRid(11);
        distributionRecordAddRequest.setBoxId(11);
        distributionRecordAddRequest.setQuantity((short) 1);
        distributionRecordAddRequest.setDistributionRecordAddBy(DistributionRecordAddBy.OWNER);
        System.out.println(gson.toJson(distributionRecordService.addDistributionRecord(distributionRecordAddRequest)));
    }

    @Test
    public void testFindDistributionRecordList(){
        DistributionRecordQuery distributionRecordQuery = new DistributionRecordQuery();
        distributionRecordQuery.setBoxId(1);
        distributionRecordQuery.setDormId(218);
        System.out.println(gson.toJson(distributionRecordService.findDistributionRecordList(distributionRecordQuery)));
    }

    @Test
    public void testFindDistributionRecordList1() throws Exception {
        List<Long> list = new ArrayList<>();
        list.add(1l);list.add(2l);
        System.out.println(gson.toJson(distributionRecordService.findDistributionRecordList(list)));
    }

    @Test
    public void testAddDistributionRecordList() throws Exception {
        List<DistributionRecordAddRequest> list = new ArrayList<>();
        DistributionRecordAddRequest distributionRecordAddRequest = new DistributionRecordAddRequest();
        distributionRecordAddRequest.setRid(11);
        distributionRecordAddRequest.setBoxId(2);
        distributionRecordAddRequest.setQuantity((short) 1);
        distributionRecordAddRequest.setDistributionRecordAddBy(DistributionRecordAddBy.OWNER);
        DistributionRecordAddRequest distributionRecordAddRequest2 = new DistributionRecordAddRequest();
        distributionRecordAddRequest2.setRid(12);
        distributionRecordAddRequest2.setBoxId(2);
        distributionRecordAddRequest2.setQuantity((short) 1);
        distributionRecordAddRequest2.setDistributionRecordAddBy(DistributionRecordAddBy.DORM);
        list.add(distributionRecordAddRequest);
        list.add(distributionRecordAddRequest2);
        System.out.println(gson.toJson(distributionRecordService.addDistributionRecordList(list)));
    }

    @Test
    public void testUpdateDistributionRecord() throws Exception {
        List<DistributionRecordUpgradeRequest> list = new ArrayList<>();
        DistributionRecordUpgradeRequest upgradeRequest = new DistributionRecordUpgradeRequest();
        upgradeRequest.setRecordId((long) 1);
        upgradeRequest.setDistributionRecordStatus(DistributionRecordStatus.DELIVERED);
        upgradeRequest.setPrice(5.32);
        upgradeRequest.setRid(22);
        upgradeRequest.setBoxId(2);
        upgradeRequest.setQuantity((short) 10);

        DistributionRecordUpgradeRequest upgradeRequest2 = new DistributionRecordUpgradeRequest();
        upgradeRequest2.setRecordId((long) 2);
        upgradeRequest2.setDistributionRecordStatus(DistributionRecordStatus.DELIVERED);
        upgradeRequest2.setPrice(3.78);
        upgradeRequest2.setRid(32);
        upgradeRequest2.setBoxId(2);
        upgradeRequest2.setQuantity((short) 7);

        list.add(upgradeRequest);
        list.add(upgradeRequest2);
        System.out.println(gson.toJson(distributionRecordService.updateDistributionRecord(list)));
    }

    @Test
    public void testUpdateDistributionRecord1() throws Exception {

    }
}
