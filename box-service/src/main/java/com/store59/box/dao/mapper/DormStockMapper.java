/*
 * Copyright 2015 © 59store.com.
 *
 * BoxDormStockMapper.java
 *
 */
package com.store59.box.dao.mapper;

import com.store59.box.model.BoxDormStock;
import com.store59.box.model.BoxDormStockChange;
import com.store59.box.model.BoxTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shanren on 15/7/22.
 */
public interface DormStockMapper {

    int addStock(BoxDormStock boxDormStock);

    int addStockChangeLog(BoxDormStockChange boxDormStockChange);

    int subStock(@Param("dormId")int dormId,@Param("rid")int rid,@Param("quantity")int quantity);

    int updateStock(BoxDormStock boxDormStock);

    BoxDormStock findStockById(@Param("id")int id, @Param("needLock")boolean needLock);

    BoxDormStock findStockByRid(@Param("dormId")int dormId,@Param("rid")int rid, @Param("needLock")boolean needLock);

    List<BoxDormStock> findStockList(@Param("dormId")int dormId, @Param("needLock")boolean needLock);

    List<BoxTemplate> findBoxTemplateList(int templateId);

}
