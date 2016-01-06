package com.store59.box.remoting;

import com.store59.box.canstants.BoxApplyStatus;
import com.store59.box.model.BoxApply;
import com.store59.box.model.query.BoxApplyQuery;
import com.store59.kylin.common.model.Result;

import java.util.List;

/**
 * Created by shanren on 7/18/15.
 */
public interface BoxApplyService {

    Result<BoxApply> addBoxApply(BoxApply boxApply);

    /**
     *
     *  You can get BoxApplyStatus object by EnumUtils.getEnumObjByCode(BoxApplyStatus.class, statusCode)
     *
     * */
    Result<Boolean> updateBoxApplyStatus(Integer boxApplyId, BoxApplyStatus boxApplyStatus);

    Result<Integer> findBoxApplyCount(Integer dormId, BoxApplyStatus boxApplyStatus);

    Result<BoxApply> findBoxApply(Integer id);

    Result<List<BoxApply>> findBoxApplyList(BoxApplyQuery queryParams);

}
