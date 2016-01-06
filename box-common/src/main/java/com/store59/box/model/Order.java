/*
 * Copyright 2015 © 59store.com.
 *
 * Order.java
 *
 */
package com.store59.box.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by shanren on 7/18/15.
 */
public class Order implements Serializable {
    private Long orderId;           //订单id
    private String sn;              //订单sn
    private Integer status;         //订单状态
    private Integer payType;        //支付类型
    private String payTradeNo;      //交易流水号
    private Integer source;         //订单来源
    private Integer boxId;          //盒子id
    private Integer dormId;         //楼主id
    private Integer dormentryId;    //楼栋id
    private String couponCode;      //优惠券号
    private Double couponDiscount;  //优惠金额
    private Double foodAmount;      //商品总金额
    private Double orderAmount;     //订单总金额
    private Long createTime;        //创建时间
    private Long payTime;           //支付时间
    private Long cancelTime;        //取消时间
    private Long uid;               //用户id
    private String ip;              //IP地址
    private Integer refundStatusCode;   //退款状态（0:未退款；1:退款中；2:已退款）
    private String  refundStatusMsg;    //退款说明
    private String detailJson;      //订单明细
    private List<OrderItem> list;
    private List<OrderItemAdd> orderItemAddList;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTradeNo() {
        return payTradeNo;
    }

    public void setPayTradeNo(String payTradeNo) {
        this.payTradeNo = payTradeNo;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public Integer getDormentryId() {
        return dormentryId;
    }

    public void setDormentryId(Integer dormentryId) {
        this.dormentryId = dormentryId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public Double getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(Double foodAmount) {
        this.foodAmount = foodAmount;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<OrderItem> getList() {
        return list;
    }

    public void setList(List<OrderItem> list) {
        this.list = list;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public Long getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Long cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getSn() {
        return sn;
    }

    public Integer getRefundStatusCode() {
        return refundStatusCode;
    }

    public void setRefundStatusCode(Integer refundStatusCode) {
        this.refundStatusCode = refundStatusCode;
    }

    public String getRefundStatusMsg() {
        return refundStatusMsg;
    }

    public void setRefundStatusMsg(String refundStatusMsg) {
        this.refundStatusMsg = refundStatusMsg;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }



    public String getDetailJson() {
        return detailJson;
    }

    public void setDetailJson(String detailJson) {
        this.detailJson = detailJson;
    }

    public List<OrderItemAdd> getOrderItemAddList() {
        return orderItemAddList;
    }

    public void setOrderItemAddList(List<OrderItemAdd> orderItemAddList) {
        this.orderItemAddList = orderItemAddList;
    }
}
