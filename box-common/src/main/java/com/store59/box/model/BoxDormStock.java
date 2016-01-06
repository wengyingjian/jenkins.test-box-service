/*
 * Copyright 2015 © 59store.com.
 *
 * DormStock.java
 *
 */
package com.store59.box.model;

import com.store59.base.common.model.Repo;

/**
 * Created by shanren on 7/18/15.
 */
public class BoxDormStock implements java.io.Serializable {

    private Integer id;
    private Integer dormId;
    private Integer rid;
    private Integer safeStock;
    private Integer stock;
    private Integer modifyTime;

    private Repo repo;
    private Double price;
    private Integer oldSafeStock;
    private Integer oldStock;
    private Integer shortageQuantity;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getShortageQuantity() {
        return shortageQuantity;
    }

    public void setShortageQuantity(Integer shortageQuantity) {
        this.shortageQuantity = shortageQuantity;
    }

    public Integer getOldSafeStock() {
        return oldSafeStock;
    }

    public void setOldSafeStock(Integer oldSafeStock) {
        this.oldSafeStock = oldSafeStock;
    }

    public Integer getOldStock() {
        return oldStock;
    }

    public void setOldStock(Integer oldStock) {
        this.oldStock = oldStock;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public Integer getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Integer modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getSafeStock() {
        return safeStock;
    }

    public void setSafeStock(Integer safeStock) {
        this.safeStock = safeStock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
