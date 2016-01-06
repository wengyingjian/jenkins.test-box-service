package com.store59.box.model;

import com.store59.box.canstants.BoxStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanren on 7/10/15.
 */
public class Box implements Serializable {

    public static Byte CONSTANTS_BOX_STATUS_CODE_NEW = 1;
    public static Byte CONSTANTS_BOX_STATUS_CODE_APPROVED = 2;
    public static Byte CONSTANTS_BOX_STATUS_CODE_CLOSED = 3;

    protected Integer id;
    protected String code;
    protected Integer dormId;
    protected Integer dormentryId;
    protected String room;
    protected String owner;
    protected String phone;
    protected BoxStatus boxStatus;
    protected String remark;
    protected Integer openTime;
    protected Long openUser;
    protected Boolean signed;
    protected Boolean payDeposit;
    protected Integer deposit;
    protected Integer boxTemplateId;
    protected Integer createTime;
    protected Integer modifyTime;
    protected List<BoxItem> itemList = new ArrayList<BoxItem>();

    protected Integer boxApplyId;
    protected Long uid;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getBoxTemplateId() {
        return boxTemplateId;
    }

    public void setBoxTemplateId(Integer boxTemplateId) {
        this.boxTemplateId = boxTemplateId;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getBoxApplyId() {
        return boxApplyId;
    }

    public void setBoxApplyId(Integer boxApplyId) {
        this.boxApplyId = boxApplyId;
    }

    public Integer getDormentryId() {
        return dormentryId;
    }

    public void setDormentryId(Integer dormentryId) {
        this.dormentryId = dormentryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public BoxStatus getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(BoxStatus boxStatus) {
        this.boxStatus = boxStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Integer openTime) {
        this.openTime = openTime;
    }

    public Long getOpenUser() {
        return openUser;
    }

    public void setOpenUser(Long openUser) {
        this.openUser = openUser;
    }

    public Boolean getSigned() {
        return signed;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }

    public Boolean getPayDeposit() {
        return payDeposit;
    }

    public void setPayDeposit(Boolean payDeposit) {
        this.payDeposit = payDeposit;
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

    public List<BoxItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<BoxItem> itemList) {
        this.itemList = itemList;
    }
}
