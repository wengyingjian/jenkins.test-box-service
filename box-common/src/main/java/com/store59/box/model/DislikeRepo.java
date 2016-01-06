/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.store59.box.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/10/7
 * @since 1.0
 */
public class DislikeRepo implements Serializable {

    private Integer id;
    private Long    uid;
    private Integer rid;
    private Integer createTime;
    private Integer modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Integer modifyTime) {
        this.modifyTime = modifyTime;
    }
}
