/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.store59.box.model.query;

import com.store59.box.canstants.DistributionRecordStatus;

import java.util.List;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/10/6
 * @since 1.0
 */
public class DistributionRecordQuery extends PageQuery {

    private Integer rid;
    private Integer dormId;
    private Integer startTime;
    private Integer endTime;
    private Integer boxId;
    private List<Integer> boxIdList;
    private DistributionRecordStatus distributionRecordStatus;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public List<Integer> getBoxIdList() {
        return boxIdList;
    }

    public void setBoxIdList(List<Integer> boxIdList) {
        this.boxIdList = boxIdList;
    }

    public DistributionRecordStatus getDistributionRecordStatus() {
        return distributionRecordStatus;
    }

    public void setDistributionRecordStatus(DistributionRecordStatus distributionRecordStatus) {
        this.distributionRecordStatus = distributionRecordStatus;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }
}
