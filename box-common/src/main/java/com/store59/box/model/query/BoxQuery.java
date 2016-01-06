/*
 * Copyright 2015 © 59store.com.
 *
 * BoxQuery.java
 *
 */
package com.store59.box.model.query;

import com.store59.box.canstants.BoxStatus;

/**
 * Created by shanren on 15/7/23.
 */
public class BoxQuery extends PageQuery {

    protected Integer boxId;
    protected String boxCode;
    protected Integer dormId;
    protected Integer dormentryId;
    protected String room;
    protected Integer owner;
    protected BoxStatus boxStatus;
    protected Long uid;
    protected Long openTimeBigin;
    protected Long openTimeEnd;

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public BoxStatus getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(BoxStatus boxStatus) {
        this.boxStatus = boxStatus;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getOpenTimeBigin() {
        return openTimeBigin;
    }

    public void setOpenTimeBigin(Long openTimeBigin) {
        this.openTimeBigin = openTimeBigin;
    }

    public Long getOpenTimeEnd() {
        return openTimeEnd;
    }

    public void setOpenTimeEnd(Long openTimeEnd) {
        this.openTimeEnd = openTimeEnd;
    }
}
