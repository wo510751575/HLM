package com.lj.business.cp.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cp.dto.coupon.FindCoupon;
import com.lj.business.cp.dto.couponMemberRelation.AddCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.DelCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationPage;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationPageReturn;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationReturn;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationVoReturn;
import com.lj.business.cp.dto.couponMemberRelation.UpdateCouponMemberRelation;

/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 *         CreateDate: 2017-06-14
 */
public interface ICouponMemberRelationService {

	/**
	 * 
	 *
	 * 方法说明：添加导购行为信息记录表信息
	 *
	 * @param addCouponMemberRelation
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addCouponMemberRelation(AddCouponMemberRelation addCouponMemberRelation) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：删除导购行为信息记录表信息
	 *
	 * @param delCouponMemberRelation
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delCouponMemberRelation(DelCouponMemberRelation delCouponMemberRelation) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购行为信息记录表信息
	 *
	 * @param updateCouponMemberRelation
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateCouponMemberRelation(UpdateCouponMemberRelation updateCouponMemberRelation) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购行为信息记录表信息
	 *
	 * @param findCouponMemberRelationPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindCouponMemberRelationPageReturn> findCouponMemberRelationPage(FindCouponMemberRelationPage findCouponMemberRelationPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据导购编号查询已使用的优惠券
	 *
	 * @param findCouponMemberRelation
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	public List<FindCouponMemberRelationReturn> findCouponMemberRelationList(FindCouponMemberRelation findCouponMemberRelation) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：根据条件获取客户获取的优惠券
	 *
	 * @param findCouponMemberRelation
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰 CreateDate: 2017年9月20日
	 *
	 */
	public FindCouponMemberRelationVoReturn findCouponMemberRelation(FindCouponMemberRelation findCouponMemberRelation) throws TsfaServiceException;
	
	
	 /**
	 *   
	 *
	 * 方法说明：查询客户最后的领取优惠券
	 *
	 * @param findCoupon
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月26日
	 *
	 */
	public List<FindCouponMemberRelationReturn>  findMemberCoupon(FindCoupon findCoupon)throws TsfaServiceException ;
	
	/**
	 * 
	 *
	 * 方法说明：根据优惠券编号更新状态与使用时间
	 *
	 * @param couponMemberRelation
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月29日
	 *
	 */
	public  int updateByCouponStatus(UpdateCouponMemberRelation updateCouponMemberRelation)throws TsfaServiceException ;

	/**
	 * 
	 *
	 * 方法说明：根据memberNo或者addFriendsCode，查询认领客户或者非认领客户领取和使用状态
	 *
	 * @param ruleCode
	 * @param memberNo
	 * @param addFriendsCode
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年1月30日
	 *
	 */
    Map<String, Object> findCouponStatus(FindCoupon findCoupon) throws TsfaServiceException;
}
