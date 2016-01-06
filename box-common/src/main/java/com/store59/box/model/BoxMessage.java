/*
 * Copyright 2015 © 59store.com.
 *
 * BoxMessage.java
 *
 */
package com.store59.box.model;

import java.io.Serializable;

/**
 * Created by shanren on 7/18/15.
 */
public class BoxMessage implements Serializable {

    private Integer id;
    private Integer boxId;
    private String room;
    private Long uid;
    private String message;
    private Integer createdTime;

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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }
}
