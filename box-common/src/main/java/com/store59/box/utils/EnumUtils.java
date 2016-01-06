/*
 * Copyright 2015 © 59store.com.
 *
 * EnumUtils.java
 *
 */
package com.store59.box.utils;

/**
 * Created by shanren on 15/7/21.
 */
public class EnumUtils {

    public static <E extends Enum> E getEnumObjByCode(Class<E> clazz, int code) {
        try {
            return clazz.getEnumConstants()[code];
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert status code " + code + " to " + clazz.getSimpleName() + " by ordinal value.", ex);
        }
    }

}
