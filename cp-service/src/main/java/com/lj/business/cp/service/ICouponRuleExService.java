package com.lj.business.cp.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cp.domain.CouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.AddCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.AddCouponRuleExReturn;
import com.lj.business.cp.dto.couponRuleEx.DelCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.DelCouponRuleExReturn;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleExPage;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleExPageReturn;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleExReturn;
import com.lj.business.cp.dto.couponRuleEx.UpdateCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.UpdateCouponRuleExReturn;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface ICouponRuleExService {
	
	/**
	 * 
	 *
	 * 方法说明：添加优惠券规则业务扩展表信息
	 *
	 * @param addCouponRuleEx
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddCouponRuleExReturn addCouponRuleEx(AddCouponRuleEx addCouponRuleEx) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找优惠券规则业务扩展表信息
	 *
	 * @param findCouponRuleEx
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindCouponRuleExReturn findCouponRuleEx(FindCouponRuleEx findCouponRuleEx) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：根据规则编号查询信息
	 *
	 * @param findCouponRuleEx
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月26日
	 *
	 */
	public  FindCouponRuleExReturn findCouponRuleExByRuleCode(FindCouponRuleEx findCouponRuleEx);
	
	
	/**
	 * 
	 *
	 * 方法说明：删除优惠券规则业务扩展表信息
	 *
	 * @param delCouponRuleEx
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public DelCouponRuleExReturn delCouponRuleEx(DelCouponRuleEx delCouponRuleEx) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改优惠券规则业务扩展表信息
	 *
	 * @param updateCouponRuleEx
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateCouponRuleExReturn updateCouponRuleEx(UpdateCouponRuleEx updateCouponRuleEx)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找优惠券规则业务扩展表信息
	 *
	 * @param findCouponRuleExPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public Page<FindCouponRuleExPageReturn> findCouponRuleExPage(FindCouponRuleExPage findCouponRuleExPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：更新规则剩余数量
	 *
	 * @param couponRuleEx
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2018年1月26日
	 *
	 */
	public  int  updateCouponUseNum(UpdateCouponRuleEx updateCouponRuleEx) throws TsfaServiceException ;
	
	/**
	 * 
	 *
	 * 方法说明：根据规编号更新消费量
	 *
	 * @param couponRuleEx
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2018年1月29日
	 *
	 */
	public int updateCouponCv(UpdateCouponRuleEx updateCouponRuleEx)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：更新浏览量
	 *
	 * @param couponRuleEx
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2018年1月29日
	 *
	 */
	public int updateCouponRuleExData(UpdateCouponRuleEx couponRuleEx)throws TsfaServiceException;
	

}
