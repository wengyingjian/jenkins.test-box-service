package com.store59.box.service;

import com.google.gson.Gson;
import com.store59.base.common.api.OrderPayAbnormalRecordApi;
import com.store59.base.common.model.OrderPayAbnormalRecord;
import com.store59.base.common.model.Repo;
import com.store59.box.canstants.BoxItemStatus;
import com.store59.box.canstants.OrderRefundStatus;
import com.store59.box.canstants.OrderStatus;
import com.store59.box.canstants.RetCode;
import com.store59.box.dao.BoxDao;
import com.store59.box.dao.BoxItemDao;
import com.store59.box.dao.OrderDao;
import com.store59.box.dao.OrderItemDao;
import com.store59.box.exceptions.BoxException;
import com.store59.box.model.*;
import com.store59.box.model.query.BoxOrderQuery;
import com.store59.box.remoting.OrderService;
import com.store59.box.utils.RPCResultUtils;
import com.store59.dorm.common.api.DormApi;
import com.store59.dorm.common.api.OrderbackRecordApi;
import com.store59.dorm.common.model.DormTransactionRecord;
import com.store59.dorm.common.model.OrderbackRecord;
import com.store59.kylin.common.model.Result;
import com.store59.kylin.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhangwangyong on 15/7/19.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private        Gson   gson   = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays().create();
    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao                  orderDao;
    @Autowired
    private BoxDao                    boxDao;
    @Autowired
    private BoxItemDao                boxItemDao;
    @Autowired
    private RepoServiceImpl           repoService;
    @Autowired
    private DormApi                   dormApi;
    @Autowired
    private OrderPayAbnormalRecordApi orderPayAbnormalRecordApi;
    @Autowired
    private OrderbackRecordApi        orderbackRecordApi;
    @Value("${box.order_back_record_type.box_profit:11}")
    private byte                      ORDER_BACK_RECORD_TYPE_BOX_PROFIT;
    @Value("${box.order_back_record_type.box_subsidy:12}")
    private byte                      ORDER_BACK_RECORD_TYPE_BOX_SUBSIDY;
    @Value("${box.user_pay_order_in_dorm_record_type:208}")
    private short                     USER_PAY_ORDER_IN_DORM_RECORD_TYPE;
    @Value("${box.image.url.pre}")
    private String                    BOX_IMAGE_PREX;
    @Value("${box.image.url.suf}")
    private String                    BOX_IMAGE_SUFX;

    private final byte ORDER_TYPE_OF_BOX    = 2;
    private final byte REFUND_RECORD_STATUS = 0;

    @Override
    @Transactional
    public Result<Order> addOrder(Order order) {
        Result<Order> result = new Result<>();
        orderDao.addOrder(perfectOrderInfo(order));
        if (!CollectionUtils.isEmpty(order.getOrderItemAddList())) {
            List<Integer> rids = new ArrayList<>();
            for (OrderItemAdd orderItem : order.getOrderItemAddList()) {
                rids.add(orderItem.getRid());

                int rows = boxItemDao.subItemStock(order.getBoxId(), orderItem.getRid(), orderItem.getQuantity());
                if (rows < 1) {
                    throw new BoxException(RetCode.BOX_STOCK_NOT_ENOUGH, "盒子库存不足");
                }
            }
            List<Repo> repos = repoService.findRepoListByIds(rids);
            for (OrderItemAdd orderItemAdd : order.getOrderItemAddList()) {
                for (Repo repo : repos) {
                    if (repo.getRid().compareTo(orderItemAdd.getRid()) == 0) {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setQuantity(orderItemAdd.getQuantity());
                        orderItem.setPrice(orderItemAdd.getPrice());
                        orderItem.setAmount(orderItemAdd.getAmount());
                        orderItem.setRid(orderItemAdd.getRid());
                        orderItem.setRname(repo.getName());
                        orderItem.setImg(repo.getDefaultImage());
                        orderItem.setImg(BOX_IMAGE_PREX + repo.getDefaultImage() + BOX_IMAGE_SUFX);
                        if (null == order.getList()) {
                            order.setList(new ArrayList<>());
                        }
                        order.getList().add(orderItem);
                        break;
                    }
                }
            }
        }
        result.setData(order);
        return result;
    }

    private Order perfectOrderInfo(Order order){
        Box box = boxDao.findBoxById(order.getBoxId());
        if(null != box) {
            order.setDormId(box.getDormId());
            order.setDormentryId(box.getDormentryId());
        }

        order.setDetailJson(JsonUtil.getJsonFromObject(order.getOrderItemAddList()));
        return order;
    }

    @Override
    public Result<List<Order>> getOrderList(Long uid) {
        Result<List<Order>> result = new Result<>();
        List<Order> orderList = orderDao.getOrderListByUid(uid);
        result.setData(orderList);
        return result;
    }

    @Override
    public Result<List<Order>> getOrderList(List<Long> orderIdList){
        Result<List<Order>> result = new Result<>();
        List<Order> orderList = orderDao.getOrderListByOrderIds(orderIdList);
        result.setData(orderList);
        return result;
    }

    @Override
    public Result<Order> getOrderInfo(Long orderId) {
        Result<Order> result = new Result<>();
        Order order = orderDao.getOrderById(orderId);
        result.setData(order);
        return result;
    }

    @Override
    public Result<Order> getOrderInfo(String orderSn) {
        Result<Order> result = new Result<>();
        Order order = orderDao.getOrderBySn(orderSn);
        result.setData(order);
        return result;
    }

    @Override
    public Result<Boolean> orderRefund(String orderSn) {
        Order checkOrder = orderDao.getOrderBySn(orderSn);
        if(null == checkOrder){
            return RPCResultUtils.genResult(RetCode.BOX_ORDER_NOT_FOUND,"盒子订单["+orderSn+"]未找到");
        }
        if(checkOrder.getRefundStatusCode().intValue() == OrderRefundStatus.NO_REFUND.ordinal()){
            return RPCResultUtils.genResult(RetCode.BOX_ORDER_NOT_REFUND,"盒子订单["+orderSn+"]不需退款");
        }
        if(checkOrder.getRefundStatusCode().intValue() == OrderRefundStatus.REFUND.ordinal()){
            return RPCResultUtils.genResult(RetCode.BOX_ORDER_REFUND,"盒子订单["+orderSn+"]已退款");
        }
        if (checkOrder.getRefundStatusCode().intValue() == OrderRefundStatus.REFUNDING.ordinal()) {
            OrderRefundUpdate orderRefundUpdate = new OrderRefundUpdate();
            orderRefundUpdate.setRefundStatusCode(OrderRefundStatus.REFUND.ordinal());
            orderRefundUpdate.setRefundStatusMsg(OrderRefundStatus.REFUND.getDesc());
            orderRefundUpdate.setSn(orderSn);
            orderRefundUpdate.setOldRefundStatusCode(OrderRefundStatus.REFUNDING.ordinal());
            if (orderDao.orderRefund(orderRefundUpdate) > 0) {
                return RPCResultUtils.genResultWithSuccess(true);
            }
        }
        return RPCResultUtils.genResultWithSuccess(false);
    }

    @Override
    public Result<Boolean> orderRefunding(Order order) {
        Order checkOrder = orderDao.getOrderBySn(order.getSn());
        if(null == checkOrder){
            return RPCResultUtils.genResult(RetCode.BOX_ORDER_NOT_FOUND,"盒子订单["+order.getSn()+"]未找到");
        }
        if(checkOrder.getRefundStatusCode().intValue() == OrderRefundStatus.REFUNDING.ordinal()){
            return RPCResultUtils.genResult(RetCode.BOX_ORDER_NOT_REFUND,"盒子订单["+order.getSn()+"]不需退款");
        }
        if(checkOrder.getRefundStatusCode().intValue() == OrderRefundStatus.REFUND.ordinal()){
            return RPCResultUtils.genResult(RetCode.BOX_ORDER_REFUND,"盒子订单["+order.getSn()+"]已退款");
        }
        if (checkOrder.getRefundStatusCode().intValue() == OrderRefundStatus.NO_REFUND.ordinal()) {
            OrderRefundUpdate orderRefundUpdate = new OrderRefundUpdate();
            orderRefundUpdate.setRefundStatusCode(OrderRefundStatus.REFUNDING.ordinal());
            orderRefundUpdate.setRefundStatusMsg(OrderRefundStatus.REFUNDING.getDesc());
            orderRefundUpdate.setSn(order.getSn());
            orderRefundUpdate.setOldRefundStatusCode(OrderRefundStatus.NO_REFUND.ordinal());
            //退款记录表中增加记录
            OrderPayAbnormalRecord orderPayAbnormalRecord = new OrderPayAbnormalRecord();
            orderPayAbnormalRecord.setOrderType(ORDER_TYPE_OF_BOX);
            orderPayAbnormalRecord.setOrderSn(order.getSn());
            orderPayAbnormalRecord.setOrderAmount(checkOrder.getOrderAmount());
            orderPayAbnormalRecord.setUid(checkOrder.getUid());
            orderPayAbnormalRecord.setPayType(Byte.parseByte(order.getPayType().toString()));
            orderPayAbnormalRecord.setPayTime(order.getPayTime().intValue());
            orderPayAbnormalRecord.setPayTradeNo(order.getPayTradeNo());
            orderPayAbnormalRecord.setStatus(REFUND_RECORD_STATUS);
            orderPayAbnormalRecordApi.addOrderPayAbnormalRecord(orderPayAbnormalRecord);
            if (orderDao.orderRefund(orderRefundUpdate) > 0) {
                return RPCResultUtils.genResultWithSuccess(true);
            }
        }
        return RPCResultUtils.genResultWithSuccess(false);
    }

    @Override
    public Result<Integer> getOrderCount(BoxOrderQuery boxOrderQuery) {
        if(null == boxOrderQuery || null == boxOrderQuery.getUid()){
            return RPCResultUtils.genResult(RetCode.PARAM_UID_NOT_EXIST,"参数uid不存在");
        }
        return RPCResultUtils.genResultWithSuccess(orderDao.getOrderCount(boxOrderQuery));
    }

    @Override
    public Result<List<OrderRidNum>> getOrderRidListByDormIdAndRid(Integer dormId, Integer rid,Integer beginTime,Integer endTime) {
        if(null == dormId || null == rid){
            return RPCResultUtils.genResult(RetCode.PARAM_ERROR,"参数错误");
        }
        //获取店长对应的所有订单列表
        List<Order> orderList = orderDao.getOrderListByDormId(dormId,beginTime,endTime);
        if(CollectionUtils.isEmpty(orderList)){
            return RPCResultUtils.genResultWithSuccess(0);
        }
        Repo repo = repoService.findRepoByRid(rid);
        if(null == repo){
            return RPCResultUtils.genResultWithSuccess(0);
        }
        //遍历所有订单，取出对应商品数量
        List<OrderRidNum> orderRidNums = new ArrayList<>();
        for (Order order : orderList){
            if(!StringUtils.isEmpty(order.getDetailJson())){
                List<LinkedHashMap> maps = null;
                try{
                    maps = JsonUtil.getObjectFromJson(order.getDetailJson(), ArrayList.class);
                }catch (Exception e){
                    //如果转换失败则不显示明细信息
                    logger.error("订单{}明细解析失败",order.getSn());
                }
                if(!CollectionUtils.isEmpty(maps)){
                    for(LinkedHashMap map : maps){
                        if(null != map.get("rid") && (int)map.get("rid") == rid){
                            OrderRidNum orderRidNum = new OrderRidNum();
                            orderRidNum.setNumber((int)map.get("quantity"));
                            orderRidNum.setOrderSn(order.getSn());
                            orderRidNum.setRid(rid);
                            orderRidNum.setRname(repo.getName());
                            orderRidNum.setTime(order.getCreateTime().intValue());
                            orderRidNums.add(orderRidNum);
                        }
                    }
                }
            }
        }
        return RPCResultUtils.genResultWithSuccess(orderRidNums);
    }

    @Override
    @Transactional
    public Result<Boolean> update(Order order) {
        Result<Boolean> result = new Result<>();
        int res = orderDao.updateBySn(order);
        logger.error("订单状态更新为："+order.getStatus()+",订单更新数量："+res);
        //如果是取消订单需要将盒子库存加回
        if(OrderStatus.CANCEL.ordinal() == order.getStatus() && res > 0){
            Order oldOrder = orderDao.getOrderBySn(order.getSn());
//            List<OrderItem> orderItems = orderItemDao.getOrderItemListByOrderId(order.getOrderId());
            List<OrderItem> orderItems = getOrderItemList(oldOrder.getDetailJson());
            if(!CollectionUtils.isEmpty(orderItems)){
                for (OrderItem orderItem : orderItems){
                    //获取盒子明细
                    BoxItem boxItem = boxItemDao.findOfflineBoxItemByRid(oldOrder.getBoxId(), orderItem.getRid());
                    if(null != boxItem){
                        boxItemDao.updateBoxItemStatus(oldOrder.getBoxId(),boxItem.getId(),BoxItemStatus.ONLINE);
                    }
                    //加回库存
                    boxItemDao.subItemStock(oldOrder.getBoxId(), orderItem.getRid(), (0-orderItem.getQuantity()));
                }
            }
        } else if (OrderStatus.PAID.ordinal() == order.getStatus() && res > 0) {
            Order oldOrder = orderDao.getOrderBySn(order.getSn());
            try {
                // 增加楼主冻结金额
                Result freeRet = dormApi.addFreezeMoney(oldOrder.getDormId(), oldOrder.getOrderAmount());
                if (freeRet.getStatus() != 0) {
                    logger.error("盒子订单付款后，增加楼主冻结金额失败：" + gson.toJson(freeRet));
                }
                // 增加楼主账户金额
                DormTransactionRecord dormTransactionRecord = new DormTransactionRecord();
                dormTransactionRecord.setDormId(oldOrder.getDormId());
                dormTransactionRecord.setType(USER_PAY_ORDER_IN_DORM_RECORD_TYPE);
                dormTransactionRecord.setRemark("盒子订单收入, 订单ID：" + oldOrder.getOrderId() + ", 订单SN: " + oldOrder.getSn());
                dormTransactionRecord.setChange(oldOrder.getOrderAmount().floatValue());
                dormTransactionRecord.setTime((int) (System.currentTimeMillis() / 1000));
                Result dormTransactionRet = dormApi.addDormTransactionRecord(dormTransactionRecord);
                if (dormTransactionRet.getStatus() != 0) {
                    logger.error("盒子订单付款后，增加楼主账户金额失败：" + gson.toJson(dormTransactionRet));
                }

                List<OrderItem> orderItems = getOrderItemList(oldOrder.getDetailJson());
                if(!CollectionUtils.isEmpty(orderItems)) {
                    Map<Integer, Repo> repoMap = repoService.findRepoMapByIds(orderItems.parallelStream().map(p -> p.getRid()).collect(Collectors.toList()));
                    double dormProfit = 0.0;
                    double orderRealAmount = 0.0;
                    for (OrderItem orderItem : orderItems){
                        // 计算回扣
                        if (repoMap.containsKey(orderItem.getRid())) {
                            dormProfit += orderItem.getAmount() * repoMap.get(orderItem.getRid()).getProfitRatio();
                            orderRealAmount += orderItem.getAmount();
                        } else {
                            logger.error("盒子订单付款后， 计算订单回扣时找不到对应rid：" + orderItem.getId());
                        }
                    }

                    //订单优惠补贴计算
                    if(orderRealAmount > oldOrder.getOrderAmount().doubleValue()){
                        // 盒子返利记录
                        OrderbackRecord orderbackRecord = new OrderbackRecord();
                        orderbackRecord.setDormId(oldOrder.getDormId());
                        orderbackRecord.setAmount(orderRealAmount-oldOrder.getOrderAmount().doubleValue());
                        orderbackRecord.setOrderId(String.valueOf(oldOrder.getOrderId()));
                        orderbackRecord.setType(ORDER_BACK_RECORD_TYPE_BOX_SUBSIDY);
                        Result orderBackRecordRet = orderbackRecordApi.addOrderbackRecord(orderbackRecord);
                        if (orderBackRecordRet.getStatus() != 0) {
                            logger.error("盒子订单付款后，盒子补贴记录失败：" + gson.toJson(orderBackRecordRet));
                        }
                    }

                    //订单利润计算
                    if (dormProfit > 0) {
                        // 盒子返利记录
                        OrderbackRecord orderbackRecord = new OrderbackRecord();
                        orderbackRecord.setDormId(oldOrder.getDormId());
                        orderbackRecord.setAmount(dormProfit);
                        orderbackRecord.setOrderId(String.valueOf(oldOrder.getOrderId()));
                        orderbackRecord.setType(ORDER_BACK_RECORD_TYPE_BOX_PROFIT);
                        Result orderBackRecordRet = orderbackRecordApi.addOrderbackRecord(orderbackRecord);
                        if (orderBackRecordRet.getStatus() != 0) {
                            logger.error("盒子订单付款后，盒子返利记录失败：" + gson.toJson(orderBackRecordRet));
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("盒子订单付款后, 出现异常：" + e.getMessage(), e);
            }
        }


        if(res > 0){
            result.setData(true);
        }

        return result;
    }

    /**
     * 根据订单的明细JSON获取明细列表
     * */
    private List<OrderItem> getOrderItemList(String detailJson){
        List<OrderItem> list = new ArrayList<>();
        if(StringUtils.isEmpty(detailJson)){
            return list;
        }
        List<LinkedHashMap> maps = null;
        try{
            maps = JsonUtil.getObjectFromJson(detailJson, ArrayList.class);
        }catch (Exception e){
            //如果转换失败则不显示明细信息
            logger.error("订单明细解析失败");
        }
        if(!CollectionUtils.isEmpty(maps)){
            for(LinkedHashMap map : maps){
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity((int) map.get("quantity"));
                orderItem.setRid((int) map.get("rid"));
                orderItem.setAmount((Double) map.get("amount"));
                list.add(orderItem);
            }
        }

        return list;
    }

    @Override
    public Result<Double> findSumOfPaidOrderAmount(int dormId, Integer startPayTime, Integer endPayTime) {
        return RPCResultUtils.genResultWithSuccess(orderDao.findSumOfPaidOrderAmount(dormId, startPayTime, endPayTime));
    }
}
