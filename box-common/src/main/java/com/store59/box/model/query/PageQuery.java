/*
 * Copyright 2015 © 59store.com.
 *
 * PageQuery.java
 *
 */
package com.store59.box.model.query;

import java.io.Serializable;

/**
 * Created by shanren on 15/7/23.
 */
public class PageQuery implements Serializable {

    protected Integer pageNumber = 1;
    protected Integer pageSize = 2000;
    protected String sortType;
    protected String sortFiled;
    private static int MAX_PAGE_SIZE = Integer.MAX_VALUE >> 10;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortFiled() {
        return sortFiled;
    }

    public void setSortFiled(String sortFiled) {
        this.sortFiled = sortFiled;
    }

    public Integer getOffset() {
        return pageNumber != null && pageNumber > 0 ? (pageNumber - 1) * this.getLimit() : 0;
    }

    public Integer getLimit() {
        return pageSize == null ? MAX_PAGE_SIZE : (pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize);
    }

}
