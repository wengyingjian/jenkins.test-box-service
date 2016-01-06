/*
 * Copyright 2015 © 59store.com.
 *
 * PurchaseQuery.java
 *
 */
package com.store59.box.model.query;

import com.store59.box.canstants.BoxPurchaseStatus;
import com.store59.box.model.Box;

import java.util.List;

/**
 * Created by shanren on 15/7/23.
 */
public class PurchaseQuery extends PageQuery {

    private Integer purchaseId;
    private Integer boxId;
    private Integer dormId;
    private BoxPurchaseStatus boxPurchaseStatus;
    private Integer deliveryTime;

    private Integer dormentryId;
    private String boxCode;
    private String room;
    private List<Box> boxList;

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
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

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public BoxPurchaseStatus getBoxPurchaseStatus() {
        return boxPurchaseStatus;
    }

    public void setBoxPurchaseStatus(BoxPurchaseStatus boxPurchaseStatus) {
        this.boxPurchaseStatus = boxPurchaseStatus;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }
}
