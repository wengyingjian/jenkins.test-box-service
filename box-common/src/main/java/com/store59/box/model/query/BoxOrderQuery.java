package com.store59.box.model.query;

import com.store59.box.canstants.OrderStatus;

/**
 * Created by zhangwangyong on 15/11/11.
 */
public class BoxOrderQuery extends PageQuery{

    private Long uid;
    private Long createTimeBegin;   //查询盒子订单的创建时间范围的起始时间
    private Integer status;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Long createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Integer getStatus() {
        if(null == this.status){
            return OrderStatus.PAID.ordinal();
        }
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
