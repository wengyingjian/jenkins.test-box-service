package com.store59.box.dao;

import com.store59.box.dao.mapper.DislikeRepoMapper;
import com.store59.box.model.DislikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangwangyong on 15/10/9.
 */
@Repository
public class DislikeRepoDao {

    @Autowired
    private DislikeRepoMapper masterDislikeRepoMapper;
    @Autowired
    private DislikeRepoMapper slaveDislikeRepoMapper;

    public int addDislikeRepo(DislikeRepo dislikeRepo){

        return masterDislikeRepoMapper.insertSelective(dislikeRepo);
    }

    public List<DislikeRepo> findDislikeRepoListByUid(Long uid){
        return slaveDislikeRepoMapper.selectDislikeRepoListByUid(uid);
    }

    public int countByUid(Long uid,Integer rid){
        return slaveDislikeRepoMapper.countByUid(uid, rid);
    }

    public int removeDislikeRepo(Long uid,Integer rid){
        return masterDislikeRepoMapper.deleteDislikeRepo(uid, rid);
    }

    public int removeDislikeRepo(Long uid,List<Integer> rids){
        return masterDislikeRepoMapper.deleteDislikeRepoList(uid,rids);
    }
}
