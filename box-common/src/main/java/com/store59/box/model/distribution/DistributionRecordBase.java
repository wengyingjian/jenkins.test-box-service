/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.store59.box.model.distribution;

import java.io.Serializable;

/**
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/10/6
 * @since 1.0
 */
public class DistributionRecordBase implements Serializable {

    protected Integer boxId;
    protected Integer rid;
    protected Short   quantity;

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

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }
}
