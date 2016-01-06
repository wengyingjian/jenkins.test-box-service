/*
 * Copyright 2015 © 59store.com.
 *
 * PurchaseDao.java
 *
 */
package com.store59.box.dao;

import com.store59.box.canstants.BoxPurchaseStatus;
import com.store59.box.dao.mapper.PurchaseMapper;
import com.store59.box.model.BoxPurchase;
import com.store59.box.model.BoxPurchaseItem;
import com.store59.box.model.query.PurchaseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shanren on 15/7/23.
 */
@Repository
public class PurchaseDao {

    @Autowired
    private PurchaseMapper masterPurchaseMapper;
    @Autowired
    private PurchaseMapper slavePurchaseMapper;

    public int addPurchase(BoxPurchase boxPurchase, boolean isDelivered) {
        return masterPurchaseMapper.addPurchase(boxPurchase, isDelivered);
    }

    public int addPurchaseItem(BoxPurchaseItem boxPurchaseItem, boolean isDelivered) {
        return masterPurchaseMapper.addPurchaseItem(boxPurchaseItem, isDelivered);
    }

    public int addPurchaseItem(List<BoxPurchaseItem> boxPurchaseItems, boolean isDelivered) {
        int ret = 0;
        for (BoxPurchaseItem item : boxPurchaseItems) {
            ret += masterPurchaseMapper.addPurchaseItem(item, isDelivered);
        }
        return ret;
    }

    public int updatePurchaseStatus(int boxPurchaseId, BoxPurchaseStatus boxPurchaseStatus) {
        return masterPurchaseMapper.updatePurchaseStatus(boxPurchaseId, boxPurchaseStatus);
    }

    public int deletePurchase(int purchaseId) {
        return masterPurchaseMapper.deletePurchase(purchaseId);
    }

    public int deletePurchaseItem(int puchaseId) {
        return masterPurchaseMapper.deletePurchaseItem(puchaseId);
    }

    public BoxPurchase findPurchaseById(int boxPurchaseId) {
        return slavePurchaseMapper.findPurchaseById(boxPurchaseId);
    }

    public int findPurchaseCount(int dormId, BoxPurchaseStatus boxPurchaseStatus) {
        return slavePurchaseMapper.findPurchaseCount(dormId, boxPurchaseStatus);
    }

    public List<BoxPurchaseItem> findPurchaseItemList(int boxPurchaseId) {
        return slavePurchaseMapper.findPurchaseItemList(boxPurchaseId);
    }

    public List<BoxPurchaseItem> findItemListByPurchaseIdListStr(String purchaseIdListStr, boolean isDelivered) {
        return slavePurchaseMapper.findItemListByPurchaseIdListStr(purchaseIdListStr, isDelivered);
    }

    public List<BoxPurchase> findPurchaseList(PurchaseQuery queryParams) {
        return slavePurchaseMapper.findPurchaseList(queryParams);
    }

}
