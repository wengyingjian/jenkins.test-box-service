package com.store59.box.service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store59.box.canstants.BoxApplyStatus;
import com.store59.box.canstants.Gender;
import com.store59.box.model.BoxApply;
import com.store59.box.model.query.BoxApplyQuery;
import com.store59.box.remoting.BoxApplyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by shanren on 15/7/24.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = BoxServiceApplication.class)
public class BoxApplyServiceImplTest {

    Gson gson;
    BoxApplyService boxApplyService;

    @Before
    public void setUp() throws Exception {
        GsonBuilder builder = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays();
        gson = builder.create();
        HessianProxyFactory hessianProxyFactoryBean = new HessianProxyFactory();
        String url = "http://localhost:8080/boxapply";
//        String url = "http://192.168.30.162:8080/boxservice/boxapply";
        boxApplyService = (BoxApplyService) hessianProxyFactoryBean.create(BoxApplyService.class, url);
    }

    @Test
    public void testAddBoxApply() throws Exception {
        BoxApply boxApply = new BoxApply();
//        boxApply.setDormId(218);
        boxApply.setDormentryId(1498);
        boxApply.setGender(Gender.FEMALE);
        boxApply.setOwner("jewelknife");
        boxApply.setPhone("13956711111");
        boxApply.setRoom("201");
        boxApply.setUid(11111l);
        boxApply.setRids("{1,2,3}");
        System.out.println(gson.toJson(boxApplyService.addBoxApply(boxApply)));
    }

    @Test
    public void testUpdateBoxApplyStatus() throws Exception {
        int applyId = 2;
        System.out.println(gson.toJson(boxApplyService.findBoxApply(applyId)));
        System.out.println(gson.toJson(boxApplyService.updateBoxApplyStatus(applyId, BoxApplyStatus.REFUSED)));
        System.out.println(gson.toJson(boxApplyService.findBoxApply(applyId)));
        System.out.println(gson.toJson(boxApplyService.updateBoxApplyStatus(applyId, BoxApplyStatus.APPROVED)));
        System.out.println(gson.toJson(boxApplyService.findBoxApply(applyId)));
    }

    @Test
    public void testFindBoxApplyList() throws Exception {
        BoxApplyQuery boxApplyQuery = new BoxApplyQuery();
//        boxApplyQuery.setDormentryId(444);
//        boxApplyQuery.setDormId(218);
        boxApplyQuery.setBoxId(99);
        System.out.println(gson.toJson(boxApplyService.findBoxApplyList(boxApplyQuery)));
    }

    @Test
    public void testFindBoxApply() throws Exception {
        System.out.println(gson.toJson(boxApplyService.findBoxApply(2)));
    }

    @Test
    public void testFindBoxApplyCount() throws Exception {
        System.out.println(gson.toJson(boxApplyService.findBoxApplyCount(218, BoxApplyStatus.NEW)));
    }
}
