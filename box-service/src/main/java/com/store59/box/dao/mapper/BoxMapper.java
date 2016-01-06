package com.store59.box.dao.mapper;

import com.store59.box.canstants.BoxStatus;
import com.store59.box.model.Box;
import com.store59.box.model.BoxItem;
import com.store59.box.model.DormentryBoxNum;
import com.store59.box.model.query.BoxQuery;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface BoxMapper {

    int addBox(Box box);

    int updateBox(Box box);

    int openBox(@Param("boxId")int boxId, @Param("openUser")Long openUser);

    int closeBox(@Param("boxId")int boxId);

    Box findBoxById(int boxId);

    Box findBoxByUid(long uid);

    List<Box> findBoxList(BoxQuery boxQuery);

    int findBoxCount(@Param("dormId")int dormId, @Param("boxStatus")BoxStatus boxStatus);

    List<Box> findBoxListByIds(@Param("idList")List<Integer> idList);

    List<Box> findBoxListByDormentryIdAndRoom(@Param("dormentryId") int dormentryId,@Param("room") String room);

    List<DormentryBoxNum> findBoxNumByDormentryIds(@Param("dormentryIds")List<Integer> dormentryIds);
}
