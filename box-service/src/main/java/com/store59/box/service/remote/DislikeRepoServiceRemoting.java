package com.store59.box.service.remote;

import com.store59.box.dao.DislikeRepoDao;
import com.store59.box.model.DislikeRepo;
import com.store59.box.remoting.DislikeRepoService;
import com.store59.kylin.common.model.Result;
import com.store59.rpc.utils.server.annotation.RemoteService;
import com.store59.rpc.utils.server.annotation.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhangwangyong on 15/10/9.
 */
@RemoteService(serviceType = ServiceType.HESSIAN, serviceInterface = DislikeRepoService.class, exportPath = "/dislikerepo")
public class DislikeRepoServiceRemoting implements DislikeRepoService{
    @Autowired
    private DislikeRepoService dislikeRepoService;

    @Override
    public Result<Boolean> addDislikeRepo(DislikeRepo dislikeRepo) {

        return dislikeRepoService.addDislikeRepo(dislikeRepo);
    }

    @Override
    public Result<List<DislikeRepo>> findDislikeRepoListByUid(long uid) {
        return dislikeRepoService.findDislikeRepoListByUid(uid);
    }

    @Override
    public Result<Boolean> removeDislikeRepo(Long uid, Integer rid) {
        return dislikeRepoService.removeDislikeRepo(uid,rid);
    }

    @Override
    public Result<Boolean> removeDislikeRepo(Long uid, List<Integer> rids) {
        return dislikeRepoService.removeDislikeRepo(uid,rids);
    }
}
