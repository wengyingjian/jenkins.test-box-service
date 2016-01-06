package com.store59.box.dao.mapper;

import com.store59.box.canstants.BoxPurchaseStatus;
import com.store59.box.model.BoxPurchase;
import com.store59.box.model.BoxPurchaseItem;
import com.store59.box.model.query.PurchaseQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shanren on 15/7/23.
 */
public interface PurchaseMapper {

//    static String TABLE_NAME_BOX_PURCHASE = "box_purchase";
//    static String TABLE_NAME_BOX_PURCHASE_ITEM = "box_purchase_item";
//    static String TABLE_NAME_BOX_PURCHASE_DELIVERED = "box_purchase_delivered";
//    static String TABLE_NAME_BOX_PURCHASE_ITEM_DELIVERED = "box_purchase_item_delivered";

    int addPurchase(@Param("boxPurchase")BoxPurchase boxPurchase, @Param("isDelivered")boolean isDelivered);

    int addPurchaseItem(@Param("boxPurchaseItem")BoxPurchaseItem boxPurchaseItem, @Param("isDelivered")boolean isDelivered);

    int updatePurchaseStatus(@Param("purchaseId")int purchaseId, @Param("boxPurchaseStatus")BoxPurchaseStatus boxPurchaseStatus);

    int deletePurchase(int purchaseId);

    int deletePurchaseItem(int puchaseId);

    BoxPurchase findPurchaseById(int purchaseId);

    int findPurchaseCount(@Param("dormId")int dormId, @Param("boxPurchaseStatus")BoxPurchaseStatus boxPurchaseStatus);

    List<BoxPurchaseItem> findPurchaseItemList(int purchaseId);

    List<BoxPurchaseItem> findItemListByPurchaseIdListStr(@Param("purchaseIdListStr")String purchaseIdListStr, @Param("isDelivered")boolean isDelivered);

    List<BoxPurchase> findPurchaseList(PurchaseQuery queryParams);

}
