package com.store59.box.dao.mapper;

import com.store59.box.canstants.BoxApplyStatus;
import com.store59.box.model.BoxApply;
import com.store59.box.model.query.BoxApplyQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangwangyong on 15/7/20.
 */
public interface BoxApplyMapper {

    int insertSelective(BoxApply boxApply);

    int updateBoxApplyStatus(Map<String,Object> map);

    int findBoxApplyCount(@Param("dormId") int dormId, @Param("boxApplyStatus")BoxApplyStatus boxApplyStatus);

    BoxApply findBoxApplyById(int boxApplyId);

    List<BoxApply> findBoxApplyList(BoxApplyQuery boxApplyQuery);

}