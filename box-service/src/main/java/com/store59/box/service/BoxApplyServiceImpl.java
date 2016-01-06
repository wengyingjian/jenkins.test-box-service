package com.store59.box.service;

import com.store59.base.common.api.DormentryApi;
import com.store59.base.common.model.Dormentry;
import com.store59.box.canstants.BoxApplyStatus;
import com.store59.box.dao.BoxApplyDao;
import com.store59.box.model.BoxApply;
import com.store59.box.model.query.BoxApplyQuery;
import com.store59.box.remoting.BoxApplyService;
import com.store59.box.utils.RPCResultUtils;
import com.store59.kylin.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangwangyong on 15/7/19.
 */
@Service("boxApplyService")
public class BoxApplyServiceImpl implements BoxApplyService {

    @Autowired
    private BoxApplyDao boxApplyDao;
    @Autowired
    private DormentryApi dormentryApi;

    @Override
    @Transactional
    public Result addBoxApply(BoxApply boxApply) {
        Result result = new Result();
        Dormentry dormentry = dormentryApi.getDormentry(boxApply.getDormentryId()).getData();
        boxApply.setDormId(dormentry.getDormId());
        int rows = boxApplyDao.addBoxApply(boxApply);
        if(rows > 0){
            result.setMsg("SUCCESS");
        }else {
            result.setMsg("FAIL");
        }
        return result;
    }

    @Override
    @Transactional
    public Result<Boolean> updateBoxApplyStatus(Integer boxApplyId, BoxApplyStatus boxApplyStatus) {
        return RPCResultUtils.genResultWithSuccess(boxApplyDao.updateBoxApplyStatus(boxApplyId, boxApplyStatus,null) > 0);
    }

    @Override
    public Result<Integer> findBoxApplyCount(Integer dormId, BoxApplyStatus boxApplyStatus) {
        return RPCResultUtils.genResultWithSuccess(boxApplyDao.findBoxApplyCount(dormId, boxApplyStatus));
    }

    @Override
    public Result<List<BoxApply>> findBoxApplyList(BoxApplyQuery boxApplyQuery) {
        return RPCResultUtils.genResultWithSuccess(boxApplyDao.findBoxApplyList(boxApplyQuery));
    }

    @Override
    public Result<BoxApply> findBoxApply(Integer id) {
        return RPCResultUtils.genResultWithSuccess(boxApplyDao.findBoxApplyById(id));
    }

}
