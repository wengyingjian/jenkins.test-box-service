package com.store59.box.dao;

import com.store59.box.canstants.BoxStatus;
import com.store59.box.dao.mapper.BoxMapper;
import com.store59.box.model.Box;
import com.store59.box.model.DormentryBoxNum;
import com.store59.box.model.query.BoxQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shanren on 7/18/15.
 */
@Repository("boxDao")
@CacheConfig(cacheNames = {"box"})
public class BoxDao {

    @Autowired
    private BoxMapper masterBoxMapper;
    @Autowired
    private BoxMapper slaveBoxMapper;

    @CacheEvict(allEntries = true)
    public int addBox(Box box) {
        return masterBoxMapper.addBox(box);
    }

    @CacheEvict(allEntries = true)
    public int updateBox(Box box) {
        return masterBoxMapper.updateBox(box);
    }

    public int openBox(int boxId, Long openUser) {
        return masterBoxMapper.openBox(boxId, openUser);
    }

    @CacheEvict(allEntries = true)
    public int closeBox(int boxId) {
        return masterBoxMapper.closeBox(boxId);
    }

    public Box findBoxById(int boxId){
        return slaveBoxMapper.findBoxById(boxId);
    }

    public Box findBoxByUid(long uid){
        return slaveBoxMapper.findBoxByUid(uid);
    }

    public int findBoxCount(Integer dormId, BoxStatus boxStatus) {
        return slaveBoxMapper.findBoxCount(dormId, boxStatus);
    }

    public List<Integer> findBoxIdListByDormId(int dormId) {
        List<Box> list = this.findBoxListByDormId(dormId);
        return CollectionUtils.isEmpty(list) ? null : list.parallelStream().map(p -> p.getId()).collect(Collectors.toList());
    }

    public List<Box> findBoxListByDormId(int dormId) {
        BoxQuery boxQuery = new BoxQuery();
        boxQuery.setDormId(dormId);
        boxQuery.setPageSize(null);
        return slaveBoxMapper.findBoxList(boxQuery);
    }

    public List<Box> findBoxList(BoxQuery boxQuery) {
        return slaveBoxMapper.findBoxList(boxQuery);
    }

    public List<Box> findBoxListByIds(List<Integer> idList) {
        return slaveBoxMapper.findBoxListByIds(idList);
    }

    @Cacheable(key = "'fblDormentryIdAndRoom_' + #dormentryId + '_' + #room" )
    public List<Box> findBoxListByDormentryIdAndRoom(int dormentryId, String room){
        return slaveBoxMapper.findBoxListByDormentryIdAndRoom(dormentryId, room);
    }

    @Cacheable(key = "'fbnDormentryIds_' + #dormentryIds.toString()" )
    public List<DormentryBoxNum> findBoxNumByDormentryIds(List<Integer> dormentryIds){
        return slaveBoxMapper.findBoxNumByDormentryIds(dormentryIds);
    }

}
