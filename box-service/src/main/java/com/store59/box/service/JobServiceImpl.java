/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.store59.box.service;

import com.store59.box.canstants.BoxStatus;
import com.store59.box.model.Box;
import com.store59.box.model.query.BoxQuery;
import com.store59.box.remoting.BoxService;
import com.store59.box.remoting.JobService;
import com.store59.box.utils.DateUtils;
import com.store59.box.utils.RestClient;
import com.store59.coupon.enums.CouponScope;
import com.store59.coupon.enums.CouponType;
import com.store59.coupon.model.CouponAdd;
import com.store59.coupon.remoting.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:zhangwangy@59store.com">阿勇</a>
 * @version 1.0 15/12/28
 * @since 1.0
 */
@Service("jobService")
public class JobServiceImpl implements JobService{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoxService    boxService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private RestClient    restClient;

    @Override
    public void sendCouponForNewBox() {
        //查询昨天新增的盒子
        BoxQuery boxQuery = new BoxQuery();
        boxQuery.setBoxStatus(BoxStatus.APPROVED);
        boxQuery.setOpenTimeBigin(DateUtils.getStartTime() - 24 * 60 * 60);
        boxQuery.setOpenTimeEnd(DateUtils.getEndTime() - 24 * 60 * 60);
        List<Box> boxes = boxService.findBoxList(boxQuery, false).getData();
        logger.info("新开通盒子送优惠券 start_time:"+boxQuery.getOpenTimeBigin()+"  end_time:"+boxQuery.getOpenTimeEnd());
        //为新增盒子用户发放优惠券
        if (!CollectionUtils.isEmpty(boxes)) {
            for (Box box : boxes) {
                sendCoupon(box.getUid());
                logger.info("新开通盒子送优惠券用户:"+box.getUid());
                //发送短信通知用户
                restClient.sendMessageWithPhone(box.getPhone(), "恭喜！你已成功开通零食盒，10元新手礼券已放入你的账户，请用app登录后查看使用。");
            }
        }
    }

    private void sendCoupon(Long uid) {
        List<CouponAdd> couponAdds = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CouponAdd couponAdd = new CouponAdd();
            couponAdd.setUid(uid);
            couponAdd.setType(CouponType.USER_MONEY_COUPON.getType());
            couponAdd.setThreshold(0d);
            couponAdd.setText("零食盒新人礼券");
            couponAdd.setActiveDate(System.currentTimeMillis() / 1000 + i * 7 * 24 * 60 * 60);
            couponAdd.setExpireDate(System.currentTimeMillis() / 1000 + (i + 1) * 7 * 24 * 60 * 60);
            couponAdd.setDiscount(2d);
            couponAdd.setScope(CouponScope.BOX.getScope());
            couponAdds.add(couponAdd);
        }
        for (int i=0;i<2;i++){
            CouponAdd couponAdd = new CouponAdd();
            couponAdd.setUid(uid);
            couponAdd.setType(CouponType.USER_MONEY_COUPON.getType());
            couponAdd.setThreshold(20d);
            couponAdd.setText("零食盒新人礼券");
            couponAdd.setActiveDate(System.currentTimeMillis() / 1000 );
            couponAdd.setExpireDate(System.currentTimeMillis() / 1000 + 2 * 7 * 24 * 60 * 60);
            couponAdd.setDiscount(2d);
            couponAdd.setScope(CouponScope.YEMAO.getScope());
            couponAdds.add(couponAdd);
        }
        couponService.addCouponList(couponAdds);
    }
}
