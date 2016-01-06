package com.store59.box.dao.mapper;

import com.store59.box.canstants.BoxItemStatus;
import com.store59.box.canstants.BoxItemStatusQuery;
import com.store59.box.model.Box;
import com.store59.box.model.BoxItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangwangyong on 15/7/20.
 */
public interface BoxItemMapper {

    int addBoxItem(BoxItem boxItem);

    int addItemStock(@Param("boxId") int boxId, @Param("rid") int rid, @Param("quantity")int quantity);

    int subItemStock(@Param("boxId") int boxId, @Param("rid") int rid, @Param("quantity")int quantity);

    int updateBoxItemStatus(@Param("boxId")Integer boxId, @Param("boxItemId")Integer boxItemId, @Param("status")BoxItemStatus status);

    int updateBoxItemPrice(@Param("rid")Integer rid, @Param("price")double price, @Param("boxList") List<Box> boxList);

    BoxItem findBoxItemById(int boxItemId);

    BoxItem findBoxItemByRid(@Param("boxId") int boxId, @Param("rid") int rid);

    BoxItem findOfflineBoxItemByRid(@Param("boxId") int boxId, @Param("rid") int rid);

    List<BoxItem> findBoxItemListByBoxId(@Param("boxId") int boxId, @Param("needLock") boolean needLock);

    List<BoxItem> findBoxIdtemListByBoxIds(@Param("boxList") List<Box> boxList);

    List<BoxItem> findBoxItemListByBoxIdWithItemStatus(@Param("boxId")int boxId, @Param("boxItemStatus")BoxItemStatusQuery boxItemStatus);

    int findSumStockByBoxsAndRid(@Param("boxList")List<Box> boxes,@Param("rid")Integer rid);
}
