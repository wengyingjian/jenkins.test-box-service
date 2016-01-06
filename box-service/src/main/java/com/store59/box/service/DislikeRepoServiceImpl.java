package com.store59.box.service;

import com.store59.box.dao.DislikeRepoDao;
import com.store59.box.model.DislikeRepo;
import com.store59.box.remoting.DislikeRepoService;
import com.store59.box.utils.RPCResultUtils;
import com.store59.kylin.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangwangyong on 15/10/9.
 */
@Service("dislikeRepoService")
public class DislikeRepoServiceImpl implements DislikeRepoService{
    @Autowired
    private DislikeRepoDao dislikeRepoDao;

    @Override
    public Result<Boolean> addDislikeRepo(DislikeRepo dislikeRepo) {
        //校验是否已经存在该记录
        if(dislikeRepoDao.countByUid(dislikeRepo.getUid(),dislikeRepo.getRid()) > 0){
            return RPCResultUtils.genResultWithSuccess(true);
        }
        if(dislikeRepoDao.addDislikeRepo(dislikeRepo) > 0){
            return RPCResultUtils.genResultWithSuccess(true);
        }
        return RPCResultUtils.genResultWithSuccess(false);
    }

    @Override
    public Result<List<DislikeRepo>> findDislikeRepoListByUid(long uid) {
        return RPCResultUtils.genResultWithSuccess(dislikeRepoDao.findDislikeRepoListByUid(uid));
    }

    @Override
    public Result<Boolean> removeDislikeRepo(Long uid, Integer rid) {
        if(dislikeRepoDao.removeDislikeRepo(uid,rid) > 0){
            return RPCResultUtils.genResultWithSuccess(true);
        }
        return RPCResultUtils.genResultWithSuccess(false);
    }

    @Override
    public Result<Boolean> removeDislikeRepo(Long uid, List<Integer> rids) {
        if(dislikeRepoDao.removeDislikeRepo(uid,rids) > 0){
            return RPCResultUtils.genResultWithSuccess(true);
        }
        return RPCResultUtils.genResultWithSuccess(false);
    }
}
