/*
 * Copyright 2015 © 59store.com.
 *
 * BoxException.java
 *
 */
package com.store59.box.exceptions;

import com.store59.kylin.common.exception.BaseException;

/**
 * Created by shanren on 15/7/21.
 */
public class BoxException extends BaseException {

    public BoxException() {
    }

    public BoxException(int status) {
        super(status, (String) null);
    }

    public BoxException(int status, String msg) {
        super(status, msg);
    }

    public BoxException(Throwable cause) {
        super(cause);
    }

    public BoxException(int status, String msg, Throwable cause) {
        super(status, msg, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage() == null ? super.getMsg() : super.getMessage();
    }
}
