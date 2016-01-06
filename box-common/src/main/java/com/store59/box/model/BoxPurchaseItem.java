/*
 * Copyright 2015 © 59store.com.
 *
 * BoxPurchaseItem.java
 *
 */
package com.store59.box.model;

import com.store59.base.common.model.Repo;

/**
 * Created by shanren on 7/18/15.
 */
public class BoxPurchaseItem implements java.io.Serializable {

    private Integer id;
    private Integer purchaseId;
    private Integer rid;
    private Integer quantity;
    private Repo repo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
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

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }
}
