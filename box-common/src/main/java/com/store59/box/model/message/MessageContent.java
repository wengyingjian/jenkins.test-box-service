package com.store59.box.model.message;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by doris on 15/9/24.
 */
public class MessageContent implements Serializable{
    //消息内容
    private String msg;

    //参数列表
    private Map<String, Object> data;

    //推送类型,actionId
    private Long code;
    //dorm’给楼主端推送  ‘store‘给用户端推送
    private String type;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
