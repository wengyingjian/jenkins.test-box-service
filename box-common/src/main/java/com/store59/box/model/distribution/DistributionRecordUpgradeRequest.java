/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.store59.box.model.distribution;

import com.store59.box.canstants.DistributionRecordStatus;

/**
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/10/6
 * @since 1.0
 */
public class DistributionRecordUpgradeRequest extends DistributionRecordBase {

    private Long                     recordId;
    private DistributionRecordStatus distributionRecordStatus;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

}
