package com.store59.box.service.remote;

import com.store59.box.canstants.BoxItemStatus;
import com.store59.box.canstants.BoxItemStatusQuery;
import com.store59.box.canstants.BoxStatus;
import com.store59.box.model.Box;
import com.store59.box.model.BoxItem;
import com.store59.box.model.DormentryBoxNum;
import com.store59.box.model.query.BoxQuery;
import com.store59.box.remoting.BoxService;
import com.store59.kylin.common.model.Result;
import com.store59.rpc.utils.server.annotation.RemoteService;
import com.store59.rpc.utils.server.annotation.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by shanren on 7/14/15.
 */
@RemoteService(serviceType = ServiceType.HESSIAN, serviceInterface = BoxService.class, exportPath = "/box")
public class BoxServiceRemoting implements BoxService {

    @Autowired
    private BoxService boxService;

    @Override
    public Result<Box> addBox(Box box) {
        return boxService.addBox(box);
    }

    @Override
    public Result<Boolean> updateBox(Box box) {
        return boxService.updateBox(box);
    }

    @Override
    public Result<Boolean> openBox(Integer boxId, Long openUserId, List<BoxItem> boxItemList) {
        return boxService.openBox(boxId, openUserId, boxItemList);
    }

    @Override
    public Result<List<BoxItem>> closeBox(Integer boxId) {
        return boxService.closeBox(boxId);
    }

    @Override
    public Result<Boolean> hasEnoughDeposit(Integer dormId) {
        return boxService.hasEnoughDeposit(dormId);
    }

    @Override
    public Result<Integer> findBoxCount(Integer dormId, BoxStatus boxStatus) {
        return boxService.findBoxCount(dormId, boxStatus);
    }

    @Override
    public Result<Box> findBoxById(Integer boxId, Boolean needItemDetail) {
        return boxService.findBoxById(boxId, needItemDetail);
    }

    @Override
    public Result<List<Box>> findBoxList(Integer dormentryId, String room) {
        return boxService.findBoxList(dormentryId, room);
    }

    @Override
    public Result<List<Box>> findBoxList(BoxQuery boxQuery, Boolean needItemDetail) {
        return boxService.findBoxList(boxQuery, needItemDetail);
    }

    @Override
    public Result<List<DormentryBoxNum>> findDormentryBoxNum(List<Integer> dormentryIds) {
        return boxService.findDormentryBoxNum(dormentryIds);
    }

    @Override
    public Result<List<Box>> findBoxList(List<Integer> boxIds) {
        return boxService.findBoxList(boxIds);
    }

    @Override
    public Result<List<BoxItem>> findBoxItemList(int boxId, BoxItemStatusQuery boxItemStatusQuery) {
        return boxService.findBoxItemList(boxId,boxItemStatusQuery);
    }

    @Override
    public Result<BoxItem> findBoxItem(int boxItemId) {
        return boxService.findBoxItem(boxItemId);
    }

    @Override
    public Result<Boolean> updateBoxItemStatus(int boxId, int boxItemId, BoxItemStatus boxItemStatus) {
        return boxService.updateBoxItemStatus(boxId,boxItemId,boxItemStatus);
    }

    @Override
    public Result<Boolean> updateBoxItemPrice(int dormId, int rid, double price) {
        return boxService.updateBoxItemPrice(dormId, rid, price);
    }

    @Override
    public Result<Box> findBoxByUid(long uid, boolean needItemDetail) {
        return boxService.findBoxByUid(uid,needItemDetail);
    }

    @Override
    public Result<Boolean> addBoxItemList(List<BoxItem> boxItems) {
        return boxService.addBoxItemList(boxItems);
    }

    @Override
    public Result<Double> findDormStockAmount(Integer dormId) {
        return boxService.findDormStockAmount(dormId);
    }

    @Override
    public Result<Integer> findSumStockByDormIdAndRid(Integer dormId, Integer rid) {
        return boxService.findSumStockByDormIdAndRid(dormId, rid);
    }
}
