/*
 * Copyright 2015 © 59store.com.
 *
 * BoxTemplate.java
 *
 */
package com.store59.box.model;

import java.io.Serializable;

/**
 * Created by shanren on 15/7/25.
 */
public class BoxTemplate implements Serializable{

    private Integer templateId;
    private Integer rid;
    private Integer quantity;
    private Double price;
    private Integer modifyTime;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Integer modifyTime) {
        this.modifyTime = modifyTime;
    }
}
