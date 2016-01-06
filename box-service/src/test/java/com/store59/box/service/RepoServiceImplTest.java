package com.store59.box.service;

import com.google.gson.Gson;
import com.store59.box.BoxServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by shanren on 15/8/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BoxServiceApplication.class)
public class RepoServiceImplTest {

    Gson gson = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays().create();
    @Autowired
    RepoServiceImpl repoService;

    @Test
    public void testFindRepoByRid() throws Exception {
        System.out.println(gson.toJson(repoService.findRepoByRid(18)));
    }

    @Test
    public void testFindRepoListAll() throws Exception {
        System.out.println(gson.toJson(repoService.findRepoListAll()));
    }

    @Test
    public void testFindRepoListByIds() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(50);list.add(18);
        System.out.println(gson.toJson(repoService.findRepoListByIds(list)));
    }
}