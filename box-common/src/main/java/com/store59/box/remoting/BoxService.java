package com.store59.box.remoting;

import com.store59.box.canstants.BoxItemStatus;
import com.store59.box.canstants.BoxItemStatusQuery;
import com.store59.box.canstants.BoxStatus;
import com.store59.box.model.Box;
import com.store59.box.model.BoxItem;
import com.store59.box.model.DormentryBoxNum;
import com.store59.box.model.query.BoxQuery;
import com.store59.kylin.common.model.Result;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * Created by shanren on 7/14/15.
 */
public interface BoxService {

    Result<Box> addBox(Box box);

    Result<Boolean> updateBox(Box box);

    Result<Boolean> openBox(Integer boxId, Long openUserId, List<BoxItem> boxItemList);

    Result<List<BoxItem>> closeBox(Integer boxId);

    Result<Boolean> hasEnoughDeposit(Integer dormId);

    Result<Integer> findBoxCount(Integer dormId, BoxStatus boxStatus);

    Result<Box> findBoxById(Integer boxId, Boolean needItemDetail);

    Result<List<Box>> findBoxList(Integer dormentryId, String room);

    Result<List<Box>> findBoxList(BoxQuery boxQuery, Boolean needItemDetail);

    Result<List<DormentryBoxNum>> findDormentryBoxNum(List<Integer> dormentryIds);

    Result<List<Box>> findBoxList(List<Integer> boxIds);

    Result<List<BoxItem>> findBoxItemList(int boxId, BoxItemStatusQuery boxItemStatusQuery);

    Result<BoxItem> findBoxItem(int boxItemId);

    Result<Boolean> updateBoxItemStatus(int boxId, int boxItemId, BoxItemStatus boxItemStatus);

    Result<Boolean> updateBoxItemPrice(int dormId, int rid, double price);

    Result<Box> findBoxByUid(long uid, boolean needItemDetail);

    Result<Boolean> addBoxItemList(List<BoxItem> boxItems);

    Result<Double> findDormStockAmount(Integer dormId);

    Result<Integer> findSumStockByDormIdAndRid(Integer dormId,Integer rid);
}
