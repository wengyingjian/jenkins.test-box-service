/*
 * Copyright 2015 © 59store.com.
 *
 * BoxPurchase.java
 *
 */
package com.store59.box.model;

import com.store59.box.canstants.BoxPurchaseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanren on 7/18/15.
 */
public class BoxPurchase implements java.io.Serializable {

    private Integer id;
    private Integer boxId;
    private Integer dormId;
    private BoxPurchaseStatus boxPurchaseStatus;
    private Integer createTime;
    private Integer deliveryTime;
    private Integer modifyTime;
    private Box box;
    private List<BoxPurchaseItem> itemList = new ArrayList<BoxPurchaseItem>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BoxPurchaseStatus getBoxPurchaseStatus() {
        return boxPurchaseStatus;
    }

    public void setBoxPurchaseStatus(BoxPurchaseStatus boxPurchaseStatus) {
        this.boxPurchaseStatus = boxPurchaseStatus;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Integer modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<BoxPurchaseItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<BoxPurchaseItem> itemList) {
        this.itemList = itemList;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }
}
