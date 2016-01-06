package com.store59.box.dao.mapper;

import com.store59.box.model.distribution.DistributionRecord;
import com.store59.box.model.distribution.DistributionRecordAddRequest;
import com.store59.box.model.distribution.DistributionRecordUpgradeRequest;
import com.store59.box.model.query.DistributionRecordQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangwangyong on 15/10/9.
 */
public interface DistributionRecordMapper {

    int insertSelective(DistributionRecord record);

    List<DistributionRecord> selectDistributionRecordList(DistributionRecordQuery query);

    int updateDistributionRecord(DistributionRecord distributionRecord);

    int cancleDistributionRecords(int boxId);

    int cancleDistributionRecordsByRid(@Param("boxId")int boxId,@Param("rid")int rid);

    DistributionRecord findDistributionRecordById(long recordId);

    List<DistributionRecord> findDistributionRecordByIdList(@Param("recoredIdList") List<Long> recoredIdList);

}
