package com.store59.box.service.remote;

import com.store59.box.model.distribution.DistributionRecord;
import com.store59.box.model.distribution.DistributionRecordAddRequest;
import com.store59.box.model.distribution.DistributionRecordUpgradeRequest;
import com.store59.box.model.query.DistributionRecordQuery;
import com.store59.box.remoting.DistributionRecordService;
import com.store59.kylin.common.model.Result;
import com.store59.rpc.utils.server.annotation.RemoteService;
import com.store59.rpc.utils.server.annotation.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhangwangyong on 15/10/9.
 */
@RemoteService(serviceType = ServiceType.HESSIAN, serviceInterface = DistributionRecordService.class, exportPath = "/distributionrecord")

public class DistributionRecordServiceRemoting implements DistributionRecordService {
    @Autowired
    private DistributionRecordService distributionRecordService;

    @Override
    public Result<Boolean> addDistributionRecord(DistributionRecordAddRequest recordAddRequest) {
        return distributionRecordService.addDistributionRecord(recordAddRequest);
    }

    @Override
    public Result<List<DistributionRecord>> addDistributionRecordList(List<DistributionRecordAddRequest> recordAddRequestList) {
        return distributionRecordService.addDistributionRecordList(recordAddRequestList);
    }

    @Override
    public Result<Boolean> updateDistributionRecord(DistributionRecordUpgradeRequest recordUpgradeRequest) {
        return distributionRecordService.updateDistributionRecord(recordUpgradeRequest);
    }

    @Override
    public Result<Boolean> updateDistributionRecord(List<DistributionRecordUpgradeRequest> recordUpgradeRequestList) {
        return distributionRecordService.updateDistributionRecord(recordUpgradeRequestList);
    }

    @Override
    public Result<DistributionRecord> findDistributionRecord(long recordId) {
        return distributionRecordService.findDistributionRecord(recordId);
    }

    @Override
    public Result<List<DistributionRecord>> findDistributionRecordList(List<Long> recoredIdList) {
        return distributionRecordService.findDistributionRecordList(recoredIdList);
    }

    @Override
    public Result<List<DistributionRecord>> findDistributionRecordList(DistributionRecordQuery query) {
        return distributionRecordService.findDistributionRecordList(query);
    }
}
