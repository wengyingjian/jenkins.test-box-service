/*
 * Copyright 2015 © 59store.com.
 *
 * BoxDormStockDao.java
 *
 */
package com.store59.box.dao;

import com.store59.box.canstants.StockChangeType;
import com.store59.box.dao.mapper.DormStockMapper;
import com.store59.box.model.BoxDormStock;
import com.store59.box.model.BoxDormStockChange;
import com.store59.box.model.BoxTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shanren on 15/7/22.
 */
@Repository("dormStockDao")
public class DormStockDao {

    @Autowired
    private DormStockMapper slaveDormStockMapper;
    @Autowired
    private DormStockMapper masterDormStockMapper;

    private int addLog(StockChangeType type, String remark, BoxDormStock stock) {
        if (stock.getStock() == null) {
            return 0;
        }
        BoxDormStock newStock = null;
        if (stock.getId() != null) {
            newStock = findStockByIdFromMaster(stock.getId());
        } else {
            newStock = findStockByRidFromMaster(stock.getDormId(), stock.getRid());
        }
        BoxDormStockChange change = new BoxDormStockChange();
        change.setDormId(newStock.getDormId());
        change.setRid(newStock.getRid());
        change.setType(type.ordinal());
        change.setNewQuantity(newStock.getStock());
        change.setRemark(remark);
        if (type.equals(StockChangeType.ADD)) {
            change.setChangeQuantity(stock.getStock());
        } else if (type.equals(StockChangeType.SUB)) {
            change.setChangeQuantity(-stock.getStock());
        } else if (type.equals(StockChangeType.FIXED)) {
            change.setChangeQuantity(stock.getStock() - stock.getOldStock());
        } else {
            return -1;
        }
        change.setOldQuantity(change.getNewQuantity() - change.getChangeQuantity());
        return masterDormStockMapper.addStockChangeLog(change);
    }

    // 如果存在则累加
    @CacheEvict(value = "box_stock", allEntries = true)
    public int addStock(BoxDormStock boxDormStock, String remark) {
        int ret = masterDormStockMapper.addStock(boxDormStock);
        if (ret > 0) {
            this.addLog(StockChangeType.ADD, remark, boxDormStock);
        }
        return ret;
    }

    @CacheEvict(value = "box_stock", allEntries = true)
    public int subStock(int dormId, int rid, int quantity, String remark) {
        int ret = masterDormStockMapper.subStock(dormId, rid, quantity);
        if (ret > 0) {
            BoxDormStock boxDormStock = new BoxDormStock();
            boxDormStock.setRid(rid);
            boxDormStock.setDormId(dormId);
            boxDormStock.setStock(quantity);
            this.addLog(StockChangeType.SUB, remark, boxDormStock);
        }
        return ret;
    }

    @CacheEvict(value = "box_stock", allEntries = true)
    public int updateStock(BoxDormStock boxDormStock, String remark) {
        BoxDormStock old = null;
        if (boxDormStock.getId() != null) {
            old = masterDormStockMapper.findStockById(boxDormStock.getId(), Boolean.TRUE);
        } else {
            old = masterDormStockMapper.findStockByRid(boxDormStock.getDormId(), boxDormStock.getRid(), Boolean.TRUE);
        }
        int ret = masterDormStockMapper.updateStock(boxDormStock);
        if (ret > 0) {
            boxDormStock.setOldStock(old.getStock());
            boxDormStock.setOldSafeStock(old.getSafeStock());
            this.addLog(StockChangeType.FIXED, remark, boxDormStock);
        }
        return ret;
    }

    public BoxDormStock findStockByIdFromMaster(int id) {
        return masterDormStockMapper.findStockById(id, Boolean.FALSE);
    }

    public BoxDormStock findStockByRidFromMaster(int dormId, int rid) {
        return masterDormStockMapper.findStockByRid(dormId, rid, Boolean.FALSE);
    }

    @Cacheable(value = "box_stock", key = "#id + '_findStockById'")
    public BoxDormStock findStockById(int id) {
        return slaveDormStockMapper.findStockById(id, Boolean.FALSE);
    }

    @Cacheable(value = "box_stock", key = "#dormId+'_' + #rid + '_findStockByRid'")
    public BoxDormStock findStockByRid(int dormId, int rid) {
        return slaveDormStockMapper.findStockByRid(dormId, rid, Boolean.FALSE);
    }

    @Cacheable(value = "box_stock", key = "#dormId + '_findStockList'", condition = "#needLock==false")
    public List<BoxDormStock> findStockList(int dormId, boolean needLock) {
        if (needLock) {
            return masterDormStockMapper.findStockList(dormId, Boolean.TRUE);
        } else {
            return slaveDormStockMapper.findStockList(dormId, Boolean.FALSE);
        }
    }

    @Cacheable(value = "box_temp", key = "#templateId + '_id'")
    public List<BoxTemplate> findBoxTemplateList(int templateId) {
        return slaveDormStockMapper.findBoxTemplateList(templateId);
    }

    @Cacheable(value = "box_temp", key = "#templateId+'_map'")
    public Map<Integer, BoxTemplate> findBoxTemplateMap(int templateId) {
        List<BoxTemplate> boxTemplateList = this.findBoxTemplateList(templateId);
        HashMap<Integer, BoxTemplate> map = new HashMap<Integer, BoxTemplate>();
        for (BoxTemplate boxTemplate : boxTemplateList) {
            map.put(boxTemplate.getRid(), boxTemplate);
        }
        return map;
    }

}