package com.store59.box.canstants;

/**
 * Created by zhangwangyong on 15/8/14.
 */
public enum PayType {
    CASH_ON_DELIVERY(0,"delivery"),ALIPAY(1,"alipay"),WEIXINPAY(2,"wxpay"),SPEND(3,"spend"),ALIPAYSCAN(4,"alipayscan"),
    WXPAY_SWIPE_CARD(5,"wxpay_swipe_card"),WEIXINPAY_APP(6,"wxpay_app"),UNKNOW(-1,"unknow");

    private int type;
    private String value;

    PayType(int type,String value){
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static PayType getPayTypeByValue(String value){
        for(PayType payType : values()){
            if(payType.getValue().equals(value)){
                return payType;
            }
        }
        return UNKNOW;
    }
}
