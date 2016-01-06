/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.store59.box.model.distribution;

import com.store59.box.canstants.DistributionRecordAddBy;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/10/6
 * @since 1.0
 */
public class DistributionRecordAddRequest extends DistributionRecordBase {

    private DistributionRecordAddBy distributionRecordAddBy;

    public DistributionRecordAddBy getDistributionRecordAddBy() {
        return distributionRecordAddBy;
    }

    public void setDistributionRecordAddBy(DistributionRecordAddBy distributionRecordAddBy) {
        this.distributionRecordAddBy = distributionRecordAddBy;
    }
}
