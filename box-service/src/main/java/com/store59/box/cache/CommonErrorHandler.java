/**
 * This document and its contents are protected by copyright 2005 and owned by 59store.com Inc. The
 * copying and reproduction of this document and/or its content (whether wholly or partly) or any
 * incorporation of the same into any other material in any media or format of any kind is strictly
 * prohibited. All rights are reserved.
 * <p>
 * Copyright (c) 59store.com Inc. 2015
 */
package com.store59.box.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * Created by shanren on 15/8/27.
 */
public class CommonErrorHandler implements CacheErrorHandler {

    private static Logger logger = LoggerFactory.getLogger(CommonErrorHandler.class);

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        logger.error("cache get error: " + exception.getMessage());
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        logger.error("cache put error: " + exception.getMessage());
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        logger.error("cache evict error: " + exception.getMessage());
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        logger.error("cache clear error: " + exception.getMessage());
    }
}
