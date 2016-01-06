package com.store59.box.dao;

import com.store59.box.canstants.BoxApplyStatus;
import com.store59.box.dao.mapper.BoxApplyMapper;
import com.store59.box.model.BoxApply;
import com.store59.box.model.query.BoxApplyQuery;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by zhangwangyong on 15/7/20.
 */
@Repository
public class BoxApplyDao {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BoxApplyMapper masterBoxApplyMapper;
    @Autowired
    private BoxApplyMapper slaveBoxApplyMapper;

    public int addBoxApply(BoxApply boxApply) {
        return masterBoxApplyMapper.insertSelective(boxApply);
    }

    public int updateBoxApplyStatus(int boxApplyId, BoxApplyStatus boxApplyStatus, Integer boxId) {
        Map<String,Object> map = new HashMap<>();
        map.put("boxApplyId",boxApplyId);
        map.put("boxApplyStatus",boxApplyStatus);
        map.put("boxId",boxId);
        return masterBoxApplyMapper.updateBoxApplyStatus(map);
    }

    public int findBoxApplyCount(int dormId, BoxApplyStatus boxApplyStatus) {
        return slaveBoxApplyMapper.findBoxApplyCount(dormId, boxApplyStatus);
    }

    public BoxApply findBoxApplyById(int boxApplyId) {
        return slaveBoxApplyMapper.findBoxApplyById(boxApplyId);
    }

    public List<BoxApply> findBoxApplyList(BoxApplyQuery boxApplyQuery) {
        return slaveBoxApplyMapper.findBoxApplyList(boxApplyQuery);
    }

}
