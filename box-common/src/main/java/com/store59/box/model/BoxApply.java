/*
 * Copyright 2015 © 59store.com.
 *
 * BoxApply.java
 *
 */
package com.store59.box.model;

import com.store59.box.canstants.BoxApplyStatus;
import com.store59.box.canstants.Gender;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shanren on 7/18/15.
 */
public class BoxApply implements Serializable {

    protected Integer id;
    protected Integer dormentryId;
    protected Integer dormId;
    protected String room;
    protected String owner;
    protected String phone;
    protected BoxApplyStatus boxApplyStatus;
    protected Gender gender;
    private Integer createTime;
    private Integer modifyTime;
    private Long uid;
    private String rids;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDormentryId() {
        return dormentryId;
    }

    public void setDormentryId(Integer dormentryId) {
        this.dormentryId = dormentryId;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BoxApplyStatus getBoxApplyStatus() {
        return boxApplyStatus;
    }

    public void setBoxApplyStatus(BoxApplyStatus boxApplyStatus) {
        this.boxApplyStatus = boxApplyStatus;
    }

    public Integer getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Integer modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getRids() {
        return rids;
    }

    public void setRids(String rids) {
        this.rids = rids;
    }
}
