package com.store59.box.service;

import com.store59.base.common.model.Repo;
import com.store59.box.canstants.*;
import com.store59.box.dao.BoxApplyDao;
import com.store59.box.dao.BoxDao;
import com.store59.box.dao.BoxItemDao;
import com.store59.box.dao.DistributionRecordDao;
import com.store59.box.exceptions.BoxException;
import com.store59.box.model.*;
import com.store59.box.model.query.BoxQuery;
import com.store59.box.remoting.BoxService;
import com.store59.box.utils.RPCResultUtils;
import com.store59.box.helper.SendMQMessage;
import com.store59.dorm.common.api.DormApi;
import com.store59.dorm.common.model.Dorm;
import com.store59.dorm.common.model.DormTransactionRecord;
import com.store59.kylin.common.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by shanren on 7/14/15.
 */
@Service("boxService")
public class BoxServiceImpl implements BoxService {

    private static Logger logger                = LoggerFactory.getLogger(BoxServiceImpl.class);
    public static  String SPLIT_SYMBOL_BOX_CODE = "-";
    @Autowired
    private BoxDao                boxDao;
    @Autowired
    private BoxItemDao            boxItemDao;
    @Autowired
    private RepoServiceImpl       repoService;
    @Autowired
    private DistributionRecordDao distributionRecordDao;
    @Autowired
    private BoxApplyDao           boxApplyDao;
    @Autowired
    private DormApi               dormApi;
    @Autowired
    private SendMQMessage         sendMQMessage;

    @Value("${box.deposit:0}")
    private int                   boxDeposit;
    @Value("${box.pay_deposit_in_dorm_record_type:105}")
    private short                 PAY_DEPOSIT_IN_DORM_RECORD_TYPE;
    @Value("${box.back_deposit_in_dorm_record_type:106}")
    private short                 BACK_DEPOSIT_IN_DORM_RECORD_TYPE;

    @Value("${box.image.url.pre}")
    private String BOX_IMAGE_PREX;
    @Value("${box.image.url.suf}")
    private String BOX_IMAGE_SUFX;

    private void fillBoxItem(List<Box> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        List<BoxItem> boxItemList = boxItemDao.findBoxIdtemListByBoxIds(list);
        if (CollectionUtils.isEmpty(boxItemList)) {
            return;
        }
        Map<Integer, Box> map = new HashMap(list.size());
        for (Box box : list) {
            map.put(box.getId(), box);
        }
        for (BoxItem boxItem : boxItemList) {
            if (map.containsKey(boxItem.getBoxId())) {
                Repo repo = repoService.findRepoByRid(boxItem.getRid());
                if(null != repo) {
                    repo.setDefaultImage(BOX_IMAGE_PREX + repo.getDefaultImage() + BOX_IMAGE_SUFX);
                    boxItem.setRepo(repo);
                }
                map.get(boxItem.getBoxId()).getItemList().add(boxItem);
            }
        }
    }

    //生成box_code
    private String genBoxCode(String room, Integer dormentryId) {
        String boxCode = null;
        try {
            BoxQuery boxQuery = new BoxQuery();
            boxQuery.setDormentryId(dormentryId);
            boxQuery.setRoom(room);
            boxQuery.setPageSize(Integer.MAX_VALUE);
            List<Box> blist = boxDao.findBoxList(boxQuery); //默认顺序
            int num = CollectionUtils.isEmpty(blist) ? 1 : (Integer.parseInt(blist.get(0).getCode().split(SPLIT_SYMBOL_BOX_CODE)[1])) + 1;
            boxCode = room + SPLIT_SYMBOL_BOX_CODE + num;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return boxCode;
    }

    private Result validateBox(Box box) {
        if (box.getDormId() == null) {
            return RPCResultUtils.genResult(RetCode.DORM_ID_NOT_FOUND, "不存在该楼主！");
        }
        if (box.getDormentryId() == null) {
            return RPCResultUtils.genResult(RetCode.NORMAL_ERROR, "楼栋ID不能为空！");
        }
        if (box.getRoom() == null) {
            return RPCResultUtils.genResult(RetCode.NORMAL_ERROR, "寝室号不能为空！");
        }
        return null;
    }

    private Result payDeposit(Integer dormId, Integer boxId) {
        DormTransactionRecord record = new DormTransactionRecord();
        record.setChange((float) -boxDeposit);
        record.setType(PAY_DEPOSIT_IN_DORM_RECORD_TYPE);
        record.setDormId(dormId);
        record.setRemark("扣除盒子押金, 盒子ID:" + boxId);
        record.setTime((int) (System.currentTimeMillis() / 1000));
        return dormApi.addDormTransactionRecord(record);
    }

    private Result backDeposit(Integer dormId, Integer boxId, int deposit) {
        DormTransactionRecord record = new DormTransactionRecord();
        record.setChange((float) deposit);
        record.setType(BACK_DEPOSIT_IN_DORM_RECORD_TYPE);
        record.setDormId(dormId);
        record.setRemark("退还盒子押金, 盒子ID:" + boxId);
        record.setTime((int) (System.currentTimeMillis() / 1000));
        return dormApi.addDormTransactionRecord(record);
    }

    @Override
    @Transactional
    public Result<Box> addBox(Box box) {
        Result ret = this.validateBox(box);
        if (ret != null) {
            return ret;
        }
        box.setCode(this.genBoxCode(box.getRoom(), box.getDormentryId()));
        box.setDeposit(boxDeposit);
        if (boxDao.addBox(box) > 0) {
            if (box.getBoxApplyId() != null && box.getBoxApplyId() > 0) {
                boxApplyDao.updateBoxApplyStatus(box.getBoxApplyId(), BoxApplyStatus.APPROVED,box.getId());
            }
            return RPCResultUtils.genResultWithSuccess(box);
        } else {
            return RPCResultUtils.genResult(RetCode.NORMAL_ERROR, "add box fail");
        }
    }

    @Transactional
    @Override
    public Result<Boolean> updateBox(Box box) {
        Result ret = this.validateBox(box);
        if (ret != null) {
            ret.setData(Boolean.FALSE);
            return ret;
        }
        Box old = boxDao.findBoxById(box.getId());
        if (!old.getRoom().equals(box.getRoom())) {
            box.setCode(this.genBoxCode(box.getRoom(), box.getDormentryId()));
        }
        if (boxDao.updateBox(box) > 0) {
            return RPCResultUtils.genResultWithSuccess(Boolean.TRUE);
        } else {
            return RPCResultUtils.genResult(Boolean.FALSE, RetCode.NORMAL_ERROR, "update box fail");
        }
    }

    @Override
    @Transactional
    public Result<Boolean> openBox(Integer boxId, Long openUserId, List<BoxItem> boxItemList) {
        if (CollectionUtils.isEmpty(boxItemList)) {
            return RPCResultUtils.genResult(RetCode.BOX_ITEM_NOT_FOUND, "盒子详情不能为空");
        }
        // 条件锁
        if (boxDao.openBox(boxId, openUserId) <= 0) {
            Box box = boxDao.findBoxById(boxId);
            if (box == null) {
                return RPCResultUtils.genResult(Boolean.FALSE, RetCode.BOX_ID_NOT_FOUND, "盒子不存在！");
            }
            if (box.getBoxStatus().equals(BoxStatus.CLOSED)) {
                return RPCResultUtils.genResult(Boolean.FALSE, RetCode.BOX_IS_CLOSED, "盒子已关闭！");
            }
            if (box.getBoxStatus().equals(BoxStatus.APPROVED)) {
                return RPCResultUtils.genResult(Boolean.FALSE, RetCode.BOX_IS_OPENED, "盒子已经开通过了！");
            }
            if (!box.getSigned()) {
                return RPCResultUtils.genResult(Boolean.FALSE, RetCode.BOX_NOT_SIGHED, "盒子还没有签字！");
            }
            if (!box.getPayDeposit()) {
                return RPCResultUtils.genResult(Boolean.FALSE, RetCode.BOX_NOT_PAY_DEPOSIT, "盒子还未付押金!");
            }
            return RPCResultUtils.genResult(Boolean.FALSE, RetCode.UNKNOWN_ERROR, "盒子开通失败！");
        } else {
            Box box = boxDao.findBoxById(boxId);
            for (BoxItem boxItem : boxItemList) {
                if (boxItemDao.addBoxItem(boxItem) <= 0) {
                    logger.error("新增boxitem失败, dormId: {}, rid:{}, room: {}, dormentryId: {}", box.getDormId(), boxItem.getRid(), box.getRoom(), box.getDormentryId());
                    throw new BoxException(RetCode.BOX_ITEM_INIT_FAIL, "盒子初始化失败，新增Item失败！");
                }
            }
            // 提交准备扣钱消息
            Result payRet = this.payDeposit(box.getDormId(), box.getId());
            if (payRet == null) {
                return RPCResultUtils.genResult(Boolean.FALSE, -1, "押金扣取失败！");
            }
            if (payRet != null && payRet.getStatus() != 0) {
                return RPCResultUtils.genResult(Boolean.FALSE, payRet.getStatus(), payRet.getMsg());
            }
            // 提交扣钱成功消息
        }
        return RPCResultUtils.genResultWithSuccess(Boolean.TRUE);
    }

    @Override
    @Transactional
    public Result<List<BoxItem>> closeBox(Integer boxId) {
        // 条件锁
        if (boxDao.closeBox(boxId) <= 0) {
            Box box = boxDao.findBoxById(boxId);
            if (box == null) {
                return RPCResultUtils.genResult(Boolean.FALSE, RetCode.BOX_ID_NOT_FOUND, "盒子不存在！");
            }
            if (box.getBoxStatus().equals(BoxStatus.CLOSED)) {
                return RPCResultUtils.genResult(Boolean.FALSE, RetCode.BOX_IS_CLOSED, "盒子已关闭！");
            }
            return RPCResultUtils.genResult(Boolean.FALSE, RetCode.UNKNOWN_ERROR, "盒子关闭失败！");
        } else {
            Box box = boxDao.findBoxById(boxId);
            List<BoxItem> boxItemList = boxItemDao.findBoxItemListByBoxId(boxId, false);
            boxItemDao.updateBoxItemStatus(boxId, null, BoxItemStatus.DORM_OFFLINE);
            distributionRecordDao.cancleDistributionRecords(boxId);
            Result ret = this.backDeposit(box.getDormId(), boxId, box.getDeposit());
            if (ret == null || ret.getStatus() != 0) {
                int status = ret == null ? RetCode.BOX_BACK_DEPOSIT_FAIL : ret.getStatus();
                String msg = ret == null ? "退还押金失败！" : ret.getMsg();
                throw new BoxException(status, msg);
            }
            return RPCResultUtils.genResultWithSuccess(boxItemList);
        }
    }

    @Override
    public Result<Boolean> hasEnoughDeposit(Integer dormId) {
        Result<Dorm> result = dormApi.getDorm(dormId);
        return RPCResultUtils.genResultWithSuccess(result != null && result.getData() != null && result.getData().getMoney() >= boxDeposit);
    }

    @Override
    public Result<Integer> findBoxCount(Integer dormId, BoxStatus boxStatus) {
        return RPCResultUtils.genResultWithSuccess(boxDao.findBoxCount(dormId, boxStatus));
    }

    @Override
    public Result<Box> findBoxById(Integer boxId, Boolean needItemDetail) {
        Box box = boxDao.findBoxById(boxId);
        if (needItemDetail) {
            List<Box> list = new ArrayList<>();
            list.add(box);
            fillBoxItem(list);
        }
        return RPCResultUtils.genResultWithSuccess(box);
    }

    @Override
    public Result<Box> findBoxByUid(long uid, boolean needItemDetail) {
        Box box = boxDao.findBoxByUid(uid);
        if (needItemDetail) {
            List<Box> list = new ArrayList<>();
            list.add(box);
            fillBoxItem(list);
        }
        return RPCResultUtils.genResultWithSuccess(box);
    }

    @Override
    public Result<List<Box>> findBoxList(Integer dormentryId, String room) {
        List<Box> list = boxDao.findBoxListByDormentryIdAndRoom(dormentryId, room);
        fillBoxItem(list);
        return RPCResultUtils.genResultWithSuccess(list);
    }

    @Override
    public Result<List<Box>> findBoxList(BoxQuery boxQuery, Boolean needItemDetail) {
        List<Box> list = boxDao.findBoxList(boxQuery);
        if (needItemDetail) {
            fillBoxItem(list);
        }
        return RPCResultUtils.genResultWithSuccess(list);
    }

    @Override
    public Result<List<DormentryBoxNum>> findDormentryBoxNum(List<Integer> dormentryIds) {
        List<DormentryBoxNum> list = boxDao.findBoxNumByDormentryIds(dormentryIds);
        return RPCResultUtils.genResultWithSuccess(list);
    }

    @Override
    public Result<List<Box>> findBoxList(List<Integer> boxIds) {
        List<Box> list = boxDao.findBoxListByIds(boxIds);
        return RPCResultUtils.genResultWithSuccess(list);
    }

    @Override
    public Result<List<BoxItem>> findBoxItemList(int boxId, BoxItemStatusQuery boxItemStatusQuery) {
        return RPCResultUtils.genResultWithSuccess(boxItemDao.findBoxItemListByBoxIdWithItemStatus(boxId, boxItemStatusQuery));
    }

    @Override
    public Result<BoxItem> findBoxItem(int boxItemId) {
        return RPCResultUtils.genResultWithSuccess(boxItemDao.findBoxItemById(boxItemId));
    }

    @Override
    public Result<Boolean> updateBoxItemStatus(int boxId, int boxItemId, BoxItemStatus boxItemStatus) {
        if (boxItemStatus.compareTo(BoxItemStatus.DORM_OFFLINE) == 0 ||
                boxItemStatus.compareTo(BoxItemStatus.USER_OFFLINE) == 0) {
            BoxItem boxItem = boxItemDao.findBoxItemById(boxItemId);
            if (null != boxItem) {
                int rows = distributionRecordDao.cancleDistributionRecordsByRid(boxId, boxItem.getRid());
//                logger.info("盒子商品下架，取消补货申请："+rows);
//                if(rows > 0){
//                    Repo repo = repoService.findRepoByRid(boxItem.getRid());
//                    Box box = boxDao.findBoxById(boxId);
//                    Map paras = new HashMap<>();
//                    paras.put("repoName",repo.getName());
//                    logger.info("发送推送信息，用户ID："+box.getUid()+" 商品名称："+repo.getName());
//                    sendMQMessage.sendMessage(BoxMessageEnum.OFF_LINE_BOXITEM,box.getUid(),paras);
//                }
            }
        }
        return RPCResultUtils.genResultWithSuccess(boxItemDao.updateBoxItemStatus(boxId, boxItemId, boxItemStatus) > 0);
    }

    @Override
    public Result<Boolean> updateBoxItemPrice(int dormId, int rid, double price) {
        List<Box> boxList = boxDao.findBoxListByDormId(dormId);
        return RPCResultUtils.genResultWithSuccess(boxItemDao.updateBoxItemPrice(rid, price, boxList) > 0);
    }

    @Override
    public Result<Boolean> addBoxItemList(List<BoxItem> boxItems) {
        if (!CollectionUtils.isEmpty(boxItems)) {
            for (BoxItem boxItem : boxItems) {
                boxItemDao.addBoxItem(boxItem);
            }
        }
        return RPCResultUtils.genResultWithSuccess(true);
    }

    @Override
    public Result<Double> findDormStockAmount(Integer dormId) {
        List<Box> boxList = boxDao.findBoxListByDormId(dormId);
        double amount = 0.0;
        if (!CollectionUtils.isEmpty(boxList)) {
            List<BoxItem> boxItemList = boxItemDao.findBoxIdtemListByBoxIds(boxList);
            if (!CollectionUtils.isEmpty(boxItemList)) {
                for (BoxItem boxItem : boxItemList) {
                    if (boxItem.getStock() > 0) {
                        amount += boxItem.getStock() * boxItem.getPrice();
                    }
                }
            }
        }
        return RPCResultUtils.genResultWithSuccess(amount);
    }

    @Override
    public Result<Integer> findSumStockByDormIdAndRid(Integer dormId, Integer rid) {
        if(null == dormId || null == rid){
            return RPCResultUtils.genResult(RetCode.PARAM_ERROR,"参数错误");
        }
        //查询楼主下的所有盒子列表
        BoxQuery boxQuery = new BoxQuery();
        boxQuery.setDormId(dormId);
        boxQuery.setBoxStatus(BoxStatus.APPROVED);
        List<Box> boxList = boxDao.findBoxList(boxQuery);
        if(CollectionUtils.isEmpty(boxList)){
            return RPCResultUtils.genResultWithSuccess(0);
        }

        return RPCResultUtils.genResultWithSuccess(boxItemDao.findSumStockByBoxsAndRid(boxList,rid));
    }
}
