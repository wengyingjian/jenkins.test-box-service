package com.store59.box.dao;

import com.store59.box.dao.mapper.BoxMessageMapper;
import com.store59.box.model.BoxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangwangyong on 15/7/20.
 */
@Repository
public class BoxMessageDao {
    @Autowired
    private BoxMessageMapper masterBoxMessageMapper;

    public int addBoxMessage(BoxMessage boxMessage){
        return masterBoxMessageMapper.insertSelective(boxMessage);
    }
}
