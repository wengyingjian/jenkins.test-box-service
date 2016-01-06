package com.store59.box.service;

import com.store59.box.canstants.DistributionRecordStatus;
import com.store59.box.canstants.RetCode;
import com.store59.box.dao.BoxDao;
import com.store59.box.dao.BoxItemDao;
import com.store59.box.dao.DistributionRecordDao;
import com.store59.box.exceptions.BoxException;
import com.store59.box.model.Box;
import com.store59.box.model.BoxItem;
import com.store59.box.model.distribution.DistributionRecord;
import com.store59.box.model.distribution.DistributionRecordAddRequest;
import com.store59.box.model.distribution.DistributionRecordUpgradeRequest;
import com.store59.box.model.query.DistributionRecordQuery;
import com.store59.box.remoting.DistributionRecordService;
import com.store59.box.utils.RPCResultUtils;
import com.store59.kylin.common.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhangwangyong on 15/10/9.
 */
@Service("distributionRecordService")
public class DistributionRecordServiceImpl implements DistributionRecordService {

    private static Logger logger = LoggerFactory.getLogger(DistributionRecordServiceImpl.class);

    @Autowired
    private DistributionRecordDao distributionRecordDao;
    @Autowired
    private BoxItemDao            boxItemDao;
    @Autowired
    private BoxDao                boxDao;

    private DistributionRecord translate2DRecord(DistributionRecordAddRequest recordAddRequest) {
        DistributionRecord distributionRecord = new DistributionRecord();
        distributionRecord.setBoxId(recordAddRequest.getBoxId());
        distributionRecord.setQuantity(recordAddRequest.getQuantity());
        distributionRecord.setDistributionRecordAddBy(recordAddRequest.getDistributionRecordAddBy());
        distributionRecord.setRid(recordAddRequest.getRid());
        return distributionRecord;
    }

    private DistributionRecord translate2DRecord(DistributionRecordUpgradeRequest recordUpgradeRequest, String sn) {
        DistributionRecord distributionRecord = new DistributionRecord();
        distributionRecord.setDistributionRecordStatus(recordUpgradeRequest.getDistributionRecordStatus());
        distributionRecord.setBoxId(recordUpgradeRequest.getBoxId());
        distributionRecord.setQuantity(recordUpgradeRequest.getQuantity());
        distributionRecord.setRid(recordUpgradeRequest.getRid());
        distributionRecord.setRecordId(recordUpgradeRequest.getRecordId());
        distributionRecord.setPrice(recordUpgradeRequest.getPrice());
        if (recordUpgradeRequest.getDistributionRecordStatus() != null && recordUpgradeRequest.getDistributionRecordStatus().equals(DistributionRecordStatus.DELIVERED)) {
            distributionRecord.setSn(sn);
        }
        return distributionRecord;
    }

    private boolean existsNewRecord(Integer boxId, Integer rid) {
        DistributionRecordQuery query = new DistributionRecordQuery();
        query.setBoxId(boxId);
        query.setRid(rid);
        query.setDistributionRecordStatus(DistributionRecordStatus.UNDELIVERED);
        List<DistributionRecord> list = distributionRecordDao.findDistributionRecordList(query);
        return list != null && list.size() > 0;
    }

    @Override
    @Transactional
    public Result<Boolean> addDistributionRecord(DistributionRecordAddRequest recordAddRequest) {
        if (existsNewRecord(recordAddRequest.getBoxId(), recordAddRequest.getRid())) {
            return RPCResultUtils.genResult(RetCode.DISTRIBUTION_RECORD_DUPLICATE, "记录已存在");
        }
        if (distributionRecordDao.addDistributionRecord(translate2DRecord(recordAddRequest)) > 0) {
            return RPCResultUtils.genResultWithSuccess(true);
        }
        return RPCResultUtils.genResultWithSuccess(false);
    }

    @Override
    @Transactional
    public Result<List<DistributionRecord>> addDistributionRecordList(List<DistributionRecordAddRequest> recordAddRequestList) {
        List<DistributionRecord> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(recordAddRequestList)) {
            for (DistributionRecordAddRequest distributionRecordAddRequest : recordAddRequestList) {
                if (existsNewRecord(distributionRecordAddRequest.getBoxId(), distributionRecordAddRequest.getRid())) {
                    continue;
                }
                DistributionRecord record = translate2DRecord(distributionRecordAddRequest);
                if (distributionRecordDao.addDistributionRecord(record) > 0) {
                    DistributionRecord newRecord = distributionRecordDao.findDistributionRecordByIdMaster(record.getRecordId());
                    fillBoxInfo(newRecord);
                    list.add(newRecord);
                }
            }
        }
        return RPCResultUtils.genResultWithSuccess(list);
    }

    private boolean updateDistributionRecord(DistributionRecord recordUpgradeRequest) {
        int ret = distributionRecordDao.updateDistributionRecord(recordUpgradeRequest);
        if (ret > 0 && recordUpgradeRequest.getDistributionRecordStatus() != null && recordUpgradeRequest.getDistributionRecordStatus().equals(DistributionRecordStatus.DELIVERED)) {
            // 如果是完成配送
            DistributionRecord distributionRecord = distributionRecordDao.findDistributionRecordByIdMaster(recordUpgradeRequest.getRecordId());
            if (boxItemDao.findBoxItemByRid(distributionRecord.getBoxId(), distributionRecord.getRid()) == null) {
                BoxItem boxItem = new BoxItem();
                boxItem.setBoxId(recordUpgradeRequest.getBoxId());
                boxItem.setRid(recordUpgradeRequest.getRid());
                boxItem.setStock(distributionRecord.getQuantity().intValue());
                boxItem.setPrice(recordUpgradeRequest.getPrice());
                if (boxItemDao.addBoxItem(boxItem) <= 0) {
                    logger.error("配货完成增加盒子库存失败! 新增盒子详情，boxid: {}, rid: {}, quantity: {}", recordUpgradeRequest.getBoxId(), recordUpgradeRequest.getRid(), recordUpgradeRequest.getQuantity());
                    throw new BoxException(RetCode.BOX_ITEM_INIT_FAIL, "配货完成新增盒子详情失败!");
                }
            } else {
                if (boxItemDao.addItemStock(distributionRecord.getBoxId(), distributionRecord.getRid(), distributionRecord.getQuantity()) <= 0) {
                    logger.error("配货完成增加盒子库存失败! boxid: {}, rid: {}, quantity: {}", recordUpgradeRequest.getBoxId(), recordUpgradeRequest.getRid(), recordUpgradeRequest.getQuantity());
                    throw new BoxException(RetCode.BOX_ITEM_UPDATE_FAIL, "配货完成增加盒子库存失败!");
                }
            }
        }
        return ret > 0;
    }

    @Override
    @Transactional
    public Result<Boolean> updateDistributionRecord(DistributionRecordUpgradeRequest recordUpgradeRequest) {
        DistributionRecord updateRecord = this.translate2DRecord(recordUpgradeRequest, UUID.randomUUID().toString());
        return RPCResultUtils.genResultWithSuccess(this.updateDistributionRecord(updateRecord));
    }

    @Override
    @Transactional
    public Result<Boolean> updateDistributionRecord(List<DistributionRecordUpgradeRequest> recordUpgradeRequestList) {
        Boolean ret = Boolean.FALSE;
        if (!CollectionUtils.isEmpty(recordUpgradeRequestList)) {
            String uuid = UUID.randomUUID().toString();
            for (DistributionRecordUpgradeRequest upgradeRequest : recordUpgradeRequestList) {
                if (updateDistributionRecord(this.translate2DRecord(upgradeRequest, uuid))) {
                    ret = Boolean.TRUE;
                }
            }
        }
        return RPCResultUtils.genResultWithSuccess(ret);
    }

    private void fillBoxInfo(DistributionRecord distributionRecord) {
        if (distributionRecord != null && distributionRecord.getBoxId() != null) {
            Box box = boxDao.findBoxById(distributionRecord.getBoxId());
            if (box != null) {
                BoxItem boxItem = boxItemDao.findBoxItemByRid(distributionRecord.getBoxId(), distributionRecord.getRid());
                distributionRecord.setBoxCode(box.getCode());
                distributionRecord.setDormentryId(box.getDormentryId());
                distributionRecord.setRoom(box.getRoom());
                distributionRecord.setBoxStock((short) (boxItem != null ? boxItem.getStock() : 0));
            }
        }
    }

    @Override
    public Result<DistributionRecord> findDistributionRecord(long recordId) {
        DistributionRecord distributionRecord = distributionRecordDao.findDistributionRecordById(recordId);
        fillBoxInfo(distributionRecord);
        return RPCResultUtils.genResultWithSuccess(distributionRecord);
    }

    @Override
    public Result<List<DistributionRecord>> findDistributionRecordList(List<Long> recoredIdList) {
        List<DistributionRecord> list = distributionRecordDao.findDistributionRecordByIdList(recoredIdList);
        if (!CollectionUtils.isEmpty(list)) {
            for (DistributionRecord distributionRecord : list) {
                fillBoxInfo(distributionRecord);
            }
        }
        return RPCResultUtils.genResultWithSuccess(list);
    }

    @Override
    public Result<List<DistributionRecord>> findDistributionRecordList(DistributionRecordQuery query) {
        if (query.getDormId() != null) {
            List<Integer> idList = boxDao.findBoxIdListByDormId(query.getDormId());
            if (CollectionUtils.isEmpty(idList)) {
                idList = new ArrayList<>();
                idList.add(-1);
            }
            query.setBoxIdList(idList);
        }
        List<DistributionRecord> list = distributionRecordDao.findDistributionRecordList(query);
        if (!CollectionUtils.isEmpty(list)) {
            for (DistributionRecord distributionRecord : list) {
                fillBoxInfo(distributionRecord);
            }
        }
        return RPCResultUtils.genResultWithSuccess(list);
    }
}
