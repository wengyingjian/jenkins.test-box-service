/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.store59.box.model.distribution;

import com.store59.box.canstants.DistributionRecordAddBy;
import com.store59.box.canstants.DistributionRecordStatus;

/**
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/10/6
 * @since 1.0
 */
public class DistributionRecord extends DistributionRecordBase {

    private Long                     recordId;
    // 盒子信息
    private Integer                  dormentryId;
    private String                   boxCode;
    private String                   room;
    private Short                    boxStock;
    private Double                   price;
    //记录详情
    private DistributionRecordStatus distributionRecordStatus;
    private DistributionRecordAddBy  distributionRecordAddBy;
    private Integer                  addTime;
    private Integer                  deliveryTime;
    private Integer                  cancelTime;
    private Integer                  modifyTime;
    private String                   sn;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Integer modifyTime) {
        this.modifyTime = modifyTime;
    }

    public DistributionRecordStatus getDistributionRecordStatus() {
        return distributionRecordStatus;
    }

    public void setDistributionRecordStatus(DistributionRecordStatus distributionRecordStatus) {
        this.distributionRecordStatus = distributionRecordStatus;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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

    public Short getBoxStock() {
        return boxStock;
    }

    public void setBoxStock(Short boxStock) {
        this.boxStock = boxStock;
    }

    public DistributionRecordAddBy getDistributionRecordAddBy() {
        return distributionRecordAddBy;
    }

    public void setDistributionRecordAddBy(DistributionRecordAddBy distributionRecordAddBy) {
        this.distributionRecordAddBy = distributionRecordAddBy;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Integer cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
