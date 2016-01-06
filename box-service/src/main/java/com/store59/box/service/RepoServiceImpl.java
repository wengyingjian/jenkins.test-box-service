/*
 * Copyright 2015 © 59store.com.
 *
 * RepoServiceImpl.java
 *
 */
package com.store59.box.service;

import com.store59.base.common.api.RepoApi;
import com.store59.base.common.model.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shanren on 15/7/26.
 */
@Service("repoService")
public class RepoServiceImpl {

    @Autowired
    private RepoApi repoApi;

    @Cacheable(value = "repo", key = "'rid_' + #rid")
    public Repo findRepoByRid(int rid) {
        return repoApi.findRepoByRid(rid).getData();
    }

    @Cacheable(value = "repo", key = "'all'")
    public List<Repo> findRepoListAll() {
        return repoApi.findRepoListAll().getData();
    }

    @Cacheable(value = "repo", key = "'ridlist_' + #ridList.toString()")
    public List<Repo> findRepoListByIds(List<Integer> ridList) {
        return repoApi.findRepoListByIds(ridList).getData();
    }

    @Cacheable(value = "repo", key = "'ridmap_' + #ridList.toString()")
    public Map<Integer, Repo> findRepoMapByIds(List<Integer> ridList) {
        List<Repo> list = this.findRepoListByIds(ridList);
        Map<Integer, Repo> map = new HashMap<Integer, Repo>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Repo repo : list) {
                map.put(repo.getRid(), repo);
            }
        }
        return map;
    }
}
