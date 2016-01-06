/*
 * Copyright 2015 © 59store.com.
 *
 * RetCode.java
 *
 */
package com.store59.box.canstants;

/**
 * Created by shanren on 15/7/24.
 */
public interface RetCode {

    static int UNKNOWN_ERROR = -1;
    static int NORMAL_ERROR = 1;
    static int TOKEN_EMPTY = 2;
    static int SIGN_ERROR = 5;
    static int INVALID_PARAMTERS = 3;
    static int DORM_ID_NOT_FOUND = 1000;
    static int DORM_ACCOUNT_NOT_ENOUGH = 1003;
    static int STOCK_NOT_FOUND = 1500;
    static int STOCK_INIT_FAIL = 1502;
    static int STOCK_NOT_ENOUGH = 1503;
    static int STOCK_ADD_FAIL = 1504;
    static int STOCK_UPDATE_OLD_SAFE_NULL = 1505;
    static int STOCK_UPDATE_OLD_STOCK_NULL = 1506;
    static int STOCK_SUB_FAIL = 1507;
    static int BOX_STOCK_RID_NOT_IN_TEMPLATE = 1508; // 库存中商品不再template中
    static int PURCHASE_NOT_FOUND = 1602;
    static int BOX_ID_NOT_FOUND = 1700;
    static int BOX_BACK_DEPOSIT_FAIL = 1703;
    static int BOX_PAY_DEPOSIT_FAIL = 1704;
    static int BOX_NOT_PAY_DEPOSIT = 1705;
    static int BOX_NOT_SIGHED = 1706;
    static int BOX_ITEM_RID_NOT_FOUND = 1707;
    static int BOX_ITEM_UPDATE_FAIL = 1708;
    static int BOX_ITEM_INIT_FAIL = 1709;
    static int BOX_IS_CLOSED = 1710;
    static int BOX_IS_OPENED = 1711;
    static int BOX_TEMPLATE_NOT_FOUND = 1712;  //
    static int BOX_STOCK_NOT_ENOUGH = 1713;      // 盒子库存不足
    static int BOX_ITEM_NOT_IN_TEMPLATE = 1714; //盒子商品和模板不对应
    static int BOX_ORDER_NOT_FOUND = 1715;  //订单未找到
    static int BOX_ORDER_NOT_REFUND = 1716;     //订单不需退款
    static int BOX_ORDER_REFUND = 1717;     //订单已退款
    static int BOX_ORDER_REFUNDING = 1718;  //订单退款中
    static int BOX_ITEM_NOT_FOUND = 1727;  // 盒子详情不存在
    static int DISTRIBUTION_RECORD_DUPLICATE = 1728; // 配货商品请求已存在
    static int PARAM_UID_NOT_EXIST = 1733;  //参数uid不存在
    static int PARAM_ERROR = 1734;  //参数错误

}
