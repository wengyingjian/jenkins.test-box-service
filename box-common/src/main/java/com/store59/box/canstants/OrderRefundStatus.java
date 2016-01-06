package com.store59.box.canstants;

/**
 * Created by zhangwangyong on 15/9/21.
 */
public enum OrderRefundStatus {
    NO_REFUND(0,"未退款"),REFUNDING(1,"退款中"),REFUND(2,"已退款"),UNKNOW(-1,"未知");

    private int status;
    private String desc;

    OrderRefundStatus (int status,String desc){
        this.status = status;
        this.desc = desc;
    }

    public void setStatus(int status){ this.status = status; }

    public int getStatus(){ return this.status; }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static OrderRefundStatus getStatusEnumByValue(String desc){
        for(OrderRefundStatus statusEnum : values()){
            if(statusEnum.getDesc().equals(desc)){
                return statusEnum;
            }
        }
        return UNKNOW;
    }
}
