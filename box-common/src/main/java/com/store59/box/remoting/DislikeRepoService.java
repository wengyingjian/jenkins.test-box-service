/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.store59.box.remoting;

import com.store59.box.model.DislikeRepo;
import com.store59.kylin.common.model.Result;

import java.util.List;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/10/7
 * @since 1.0
 */
public interface DislikeRepoService {

    Result<Boolean> addDislikeRepo(DislikeRepo dislikeRepo);

    Result<List<DislikeRepo>> findDislikeRepoListByUid(long uid);

    Result<Boolean> removeDislikeRepo(Long uid,Integer rid);

    Result<Boolean> removeDislikeRepo(Long uid,List<Integer> rids);
}
