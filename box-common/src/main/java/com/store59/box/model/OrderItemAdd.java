package com.store59.box.model;

import java.io.Serializable;

/**
 * Created by zhangwangyong on 15/8/18.
 */
public class OrderItemAdd implements Serializable{
    private Integer rid;
    private Integer quantity;
    private Double  price;
    private Double  amount;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
