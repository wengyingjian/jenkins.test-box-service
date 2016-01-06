package com.store59.box.service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.store59.box.model.DislikeRepo;
import com.store59.box.remoting.DislikeRepoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwangyong on 15/10/9.
 */
public class DislikeRepoServiceImplTest {
    Gson gson = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays().create();
    @Autowired
    DislikeRepoService dislikeRepoService;

    @Before
    public void setUp() throws Exception {
        HessianProxyFactory hessianProxyFactoryBean = new HessianProxyFactory();
        String url = "http://localhost:8790/dislikerepo";
        hessianProxyFactoryBean.setOverloadEnabled(true);
//        String url = "http://172.16.107.218:3000/boxservice/box";
//        String url = "http://localhost:9899/boxservice/box";
        dislikeRepoService = (DislikeRepoService) hessianProxyFactoryBean.create(DislikeRepoService.class, url);
    }

    @Test
    public void testAddDislikeRepo(){
        DislikeRepo dislikeRepo = new DislikeRepo();
        dislikeRepo.setRid(13);
        dislikeRepo.setUid(11l);
        dislikeRepoService.addDislikeRepo(dislikeRepo);
    }

    @Test
    public void testFindDislikeRepoListByUid(){
        System.out.println(gson.toJson(dislikeRepoService.findDislikeRepoListByUid(11l)));
    }

    @Test
    public void testRemoveDislikeRepo(){
        dislikeRepoService.removeDislikeRepo(11l, 13);
    }

    @Test
    public void testRemovedislikeRepoList(){
        List<Integer> rids = new ArrayList<>();
        rids.add(12);
        rids.add(13);
        dislikeRepoService.removeDislikeRepo(11l,rids);
    }
}
