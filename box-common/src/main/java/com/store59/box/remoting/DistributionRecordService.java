/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.store59.box.remoting;

import com.store59.box.model.distribution.DistributionRecord;
import com.store59.box.model.distribution.DistributionRecordAddRequest;
import com.store59.box.model.distribution.DistributionRecordUpgradeRequest;
import com.store59.box.model.query.DistributionRecordQuery;
import com.store59.kylin.common.model.Result;

import java.util.List;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/10/6
 * @since 1.0
 */
public interface DistributionRecordService {

    Result<Boolean> addDistributionRecord(DistributionRecordAddRequest recordAddRequest);

    Result<List<DistributionRecord>> addDistributionRecordList(List<DistributionRecordAddRequest> recordAddRequestList);

    Result<Boolean> updateDistributionRecord(DistributionRecordUpgradeRequest recordUpgradeRequest);

    Result<Boolean> updateDistributionRecord(List<DistributionRecordUpgradeRequest> recordUpgradeRequestList);

    Result<DistributionRecord> findDistributionRecord(long recordId);

    Result<List<DistributionRecord>> findDistributionRecordList(List<Long> recoredIdList);

    Result<List<DistributionRecord>> findDistributionRecordList(DistributionRecordQuery query);

}
