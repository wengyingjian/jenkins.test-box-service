package com.store59.box.service.remote;

import com.store59.box.canstants.BoxApplyStatus;
import com.store59.box.model.BoxApply;
import com.store59.box.model.query.BoxApplyQuery;
import com.store59.box.remoting.BoxApplyService;
import com.store59.kylin.common.model.Result;
import com.store59.rpc.utils.server.annotation.RemoteService;
import com.store59.rpc.utils.server.annotation.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhangwangyong on 15/7/19.
 */
@RemoteService(serviceType = ServiceType.HESSIAN, serviceInterface = BoxApplyService.class, exportPath = "/boxapply")
public class BoxApplyServiceRemoting implements BoxApplyService {

    @Autowired
    private BoxApplyService boxApplyService;

    @Override
    public Result addBoxApply(BoxApply boxApply) {
        return boxApplyService.addBoxApply(boxApply);
    }

    @Override
    public Result<Boolean> updateBoxApplyStatus(Integer boxApplyId, BoxApplyStatus boxApplyStatus) {
        return boxApplyService.updateBoxApplyStatus(boxApplyId, boxApplyStatus);
    }

    @Override
    public Result<Integer> findBoxApplyCount(Integer dormId, BoxApplyStatus boxApplyStatus) {
        return boxApplyService.findBoxApplyCount(dormId, boxApplyStatus);
    }

    @Override
    public Result<List<BoxApply>> findBoxApplyList(BoxApplyQuery boxApplyQuery) {
        return boxApplyService.findBoxApplyList(boxApplyQuery);
    }

    @Override
    public Result<BoxApply> findBoxApply(Integer id) {
        return boxApplyService.findBoxApply(id);
    }

}
