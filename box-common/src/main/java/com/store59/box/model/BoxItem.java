package com.store59.box.model;

import com.store59.base.common.model.Repo;
import com.store59.box.canstants.BoxItemStatus;

/**
 * Created by shanren on 7/10/15.
 */
public class BoxItem implements java.io.Serializable {

    protected Integer id;
    protected Integer boxId;
    protected Integer rid;
    protected Integer stock;
    protected Double price;
    protected Integer updatedTime;
    protected BoxItemStatus boxItemStatus;
    protected Repo repo;

    public BoxItemStatus getBoxItemStatus() {
        return boxItemStatus;
    }

    public void setBoxItemStatus(BoxItemStatus boxItemStatus) {
        this.boxItemStatus = boxItemStatus;
    }

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

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Integer updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }
}
