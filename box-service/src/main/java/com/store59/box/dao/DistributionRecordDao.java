package com.store59.box.dao;

import com.store59.box.dao.mapper.DistributionRecordMapper;
import com.store59.box.model.distribution.DistributionRecord;
import com.store59.box.model.distribution.DistributionRecordAddRequest;
import com.store59.box.model.distribution.DistributionRecordUpgradeRequest;
import com.store59.box.model.query.DistributionRecordQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangwangyong on 15/10/9.
 */
@Repository
public class DistributionRecordDao {

    @Autowired
    private DistributionRecordMapper masterDistributionRecordMapper;
    @Autowired
    private DistributionRecordMapper slaveDistributionRecordMapper;

    public int addDistributionRecord(DistributionRecord record){
        return masterDistributionRecordMapper.insertSelective(record);
    }

    public List<DistributionRecord> findDistributionRecordList(DistributionRecordQuery query){
        return slaveDistributionRecordMapper.selectDistributionRecordList(query);
    }

    public int updateDistributionRecord(DistributionRecord distributionRecord) {
        return masterDistributionRecordMapper.updateDistributionRecord(distributionRecord);
    }

    public int cancleDistributionRecords(int boxId) {
        return masterDistributionRecordMapper.cancleDistributionRecords(boxId);
    }

    public int cancleDistributionRecordsByRid(int boxId,int rid){
        return masterDistributionRecordMapper.cancleDistributionRecordsByRid(boxId,rid);
    }

    public DistributionRecord findDistributionRecordByIdMaster(long recordId) {
        return masterDistributionRecordMapper.findDistributionRecordById(recordId);
    }

    public DistributionRecord findDistributionRecordById(long recordId) {
        return slaveDistributionRecordMapper.findDistributionRecordById(recordId);
    }

    public List<DistributionRecord> findDistributionRecordByIdList(List<Long> idList) {
        return slaveDistributionRecordMapper.findDistributionRecordByIdList(idList);
    }
}
