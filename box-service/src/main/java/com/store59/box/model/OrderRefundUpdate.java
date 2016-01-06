package com.store59.box.model;

import java.io.Serializable;

/**
 * Created by zhangwangyong on 15/9/21.
 */
public class OrderRefundUpdate implements Serializable{
    private Integer refundStatusCode;   //退款状态（0:未退款；1:退款中；2:已退款）
    private String  refundStatusMsg;    //退款说明
    private String  sn;                 //订单SN
    private Integer oldRefundStatusCode;    //更新前状态

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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getOldRefundStatusCode() {
        return oldRefundStatusCode;
    }

    public void setOldRefundStatusCode(Integer oldRefundStatusCode) {
        this.oldRefundStatusCode = oldRefundStatusCode;
    }
}
