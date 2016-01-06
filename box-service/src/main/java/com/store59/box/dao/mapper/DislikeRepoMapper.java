package com.store59.box.dao.mapper;

import com.store59.box.model.DislikeRepo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangwangyong on 15/10/9.
 */
public interface DislikeRepoMapper {
    int insertSelective(DislikeRepo dislikeRepo);

    List<DislikeRepo> selectDislikeRepoListByUid(Long uid);

    int countByUid(@Param("uid")Long uid,@Param("rid")Integer rid);

    int deleteDislikeRepo(@Param("uid")Long uid,@Param("rid")Integer rid);

    int deleteDislikeRepoList(@Param("uid")Long uid,@Param("rids")List<Integer> rids);
}
