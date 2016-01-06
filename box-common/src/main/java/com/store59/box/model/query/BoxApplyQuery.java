/*
 * Copyright 2015 © 59store.com. BoxApplyQuery.java
 */
package com.store59.box.model.query;

import com.store59.box.canstants.BoxApplyStatus;

/**
 * Created by shanren on 15/7/24.
 */
public class BoxApplyQuery extends PageQuery {

    private Integer        dormId;
    private Integer        dormentryId;
    private BoxApplyStatus boxApplyStatus;
    private String         room;
    private Long           uid;           // 申请人id
    private Integer        boxId;       //盒子id

    /**
     * @return the uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public Integer getDormentryId() {
        return dormentryId;
    }

    public void setDormentryId(Integer dormentryId) {
        this.dormentryId = dormentryId;
    }

    public BoxApplyStatus getBoxApplyStatus() {
        return boxApplyStatus;
    }

    public void setBoxApplyStatus(BoxApplyStatus boxApplyStatus) {
        this.boxApplyStatus = boxApplyStatus;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }
}
