/**
 * Copyright (c) 2016, 59store. All rights reserved.
 */
package com.store59.box.canstants;

/**
 *
 * @author <a href="mailto:zhangwangy@59store.com">阿勇</a>
 * @version 1.0 16/1/4
 * @since 1.0
 */
public enum BoxMessageEnum {
    OFF_LINE_BOXITEM(153,"您有1个补货中的商品因缺货暂时无法补货","非常抱歉，您补货中的{repoName}商品因缺货下架暂时无法给您补货，您可以再选择其他商品。");

    private Integer actionId;

    private String message;

    private String title;

    BoxMessageEnum(Integer actionId, String title, String message) {
        this.actionId = actionId;
        this.title = title;
        this.message = message;
    }

    public static BoxMessageEnum getBoxMessageByOrderSn(int orderSn){
        for (BoxMessageEnum boxMessageEnum : values()){
            if(boxMessageEnum.ordinal() == orderSn){
                return boxMessageEnum;
            }
        }
        return null;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
