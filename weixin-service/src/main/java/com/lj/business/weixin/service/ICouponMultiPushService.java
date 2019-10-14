package com.lj.business.weixin.service;

import com.lj.base.exception.TsfaException;
import com.lj.business.weixin.dto.couponmultipush.AddCouponMultiPush;
import com.lj.business.weixin.dto.couponmultipush.AddCouponMultiPushReturn;

/**
 * 
 * 
 * 类说明：群发优惠券业务接口
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年1月25日
 */
public interface ICouponMultiPushService {
    
    /**
     * 
     *
     * 方法说明：群发优惠券，保存或者立即执行任务
     *
     * @param addCouponMultiPush
     * @return
     *
     * @author 许新龙 CreateDate: 2018年1月25日
     *
     */
    AddCouponMultiPushReturn addCouponMultiPush(AddCouponMultiPush addCouponMultiPush) throws TsfaException;
    
}
