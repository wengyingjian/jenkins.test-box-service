/*
 * Copyright 2015 © 59store.com.
 *
 * BoxStockException.java
 *
 */
package com.store59.box.exceptions;

/**
 * Created by shanren on 15/7/26.
 */
public class BoxStockException extends BoxException {

    public BoxStockException() {
    }

    public BoxStockException(int errCode, String message) {
        super(errCode, message);
    }

}
