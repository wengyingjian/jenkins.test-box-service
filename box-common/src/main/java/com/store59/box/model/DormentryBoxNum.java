package com.store59.box.model;

import java.io.Serializable;

/**
 * Created by zhangwangyong on 15/7/30.
 */
public class DormentryBoxNum implements Serializable {
    private Integer dormentryId;
    private Integer boxNum;

    public Integer getDormentryId() {
        return dormentryId;
    }

    public void setDormentryId(Integer dormentryId) {
        this.dormentryId = dormentryId;
    }

    public Integer getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Integer boxNum) {
        this.boxNum = boxNum;
    }
}
