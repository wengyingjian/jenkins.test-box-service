/*
 * Copyright 2015 © 59store.com.
 *
 * RemotingServiceResultUtils.java
 *
 */
package com.store59.box.utils;

import com.store59.kylin.common.model.Result;

/**
 * Created by shanren on 15/7/22.
 */
public class RPCResultUtils {

    public static Result genResult(Object obj, int retCode, String msg) {
        Result ret = new Result();
        ret.setData(obj);
        ret.setMsg(msg);
        ret.setStatus(retCode);
        return ret;
    }

    public static Result genResult(int retCode, String msg) {
        Result ret = new Result();
        ret.setMsg(msg);
        ret.setStatus(retCode);
        return ret;
    }

    public static Result genResultWithSuccess(Object obj) {
        return genResult(obj, 0, "");
    }

    public static Result genResultWithSuccess() {
        return genResult(0, "");
    }
}
