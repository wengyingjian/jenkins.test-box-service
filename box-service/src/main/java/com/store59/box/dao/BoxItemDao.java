package com.store59.box.dao;

import com.store59.box.canstants.BoxItemStatus;
import com.store59.box.canstants.BoxItemStatusQuery;
import com.store59.box.dao.mapper.BoxItemMapper;
import com.store59.box.model.Box;
import com.store59.box.model.BoxItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by zhangwangyong on 15/7/20.
 */
@Repository
public class BoxItemDao {
    @Autowired
    private BoxItemMapper slaveBoxItemMapper;
    @Autowired
    private BoxItemMapper masterBoxItemMapper;

    public int addBoxItem(BoxItem boxItem) {
        return masterBoxItemMapper.addBoxItem(boxItem);
    }

    public int addItemStock(int boxId, int rid, int quantity) {
        return masterBoxItemMapper.addItemStock(boxId, rid, quantity);
    }

    public int subItemStock(int boxId, int rid, int quantity) {
        return masterBoxItemMapper.subItemStock(boxId, rid, quantity);
    }

    public int updateBoxItemStatus(Integer boxId, Integer boxItemId, BoxItemStatus status) {
        return masterBoxItemMapper.updateBoxItemStatus(boxId, boxItemId, status);
    }

    public int updateBoxItemPrice(int rid, double price, List<Box> boxList) {
        if (CollectionUtils.isEmpty(boxList)) {
            return 0;
        }
        return masterBoxItemMapper.updateBoxItemPrice(rid, price, boxList);
    }

    public BoxItem findBoxItemById(int boxItemId) {
        return slaveBoxItemMapper.findBoxItemById(boxItemId);
    }

    public BoxItem findBoxItemByRid(int boxId, int rid){
        return slaveBoxItemMapper.findBoxItemByRid(boxId, rid);
    }

    public BoxItem findOfflineBoxItemByRid(int boxId,int rid){
        return slaveBoxItemMapper.findOfflineBoxItemByRid(boxId, rid);
    }

    public List<BoxItem> findBoxItemListByBoxId(int boxId, boolean needLock){
        if (needLock) {
            return masterBoxItemMapper.findBoxItemListByBoxId(boxId, Boolean.TRUE);
        }
        return slaveBoxItemMapper.findBoxItemListByBoxId(boxId, Boolean.FALSE);
    }

    public List<BoxItem> findBoxIdtemListByBoxIds(List<Box> boxList){
        return slaveBoxItemMapper.findBoxIdtemListByBoxIds(boxList);
    }

    public List<BoxItem> findBoxItemListByBoxIdWithItemStatus(int boxId, BoxItemStatusQuery boxItemStatusQuery) {
        return masterBoxItemMapper.findBoxItemListByBoxIdWithItemStatus(boxId, boxItemStatusQuery);
    }

    public int findSumStockByBoxsAndRid(List<Box> boxes, Integer rid){
        return slaveBoxItemMapper.findSumStockByBoxsAndRid(boxes,rid);
    }

}
