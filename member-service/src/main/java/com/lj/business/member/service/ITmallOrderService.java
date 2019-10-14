package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.member.dto.TmallOrderDto;
import com.lj.business.member.dto.FindTmallOrderPage;


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
public interface ITmallOrderService {
	
	/**
	 * 
	 *
	 * 方法说明：添加天猫订单信息
	 *
	 * @param tmallOrderDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addTmallOrder(TmallOrderDto tmallOrderDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找天猫订单信息
	 *
	 * @param findTmallOrder
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public TmallOrderDto findTmallOrder(TmallOrderDto tmallOrderDto) throws TsfaServiceException;
	
	
	public TmallOrderDto get(FindTmallOrderPage findTmallOrderPage) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：不分页查询天猫订单信息
	 *
	 * @param findTmallOrderPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<TmallOrderDto>  findTmallOrders(FindTmallOrderPage findTmallOrderPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改天猫订单信息
	 *
	 * @param updateTmallOrder
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateTmallOrder(TmallOrderDto tmallOrderDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询天猫订单信息
	 *
	 * @param findTmallOrderPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<TmallOrderDto> findTmallOrderPage(FindTmallOrderPage findTmallOrderPage) throws TsfaServiceException;
	
	/**
	 * 根据订单号校验发红包
	 * 1.一个客户只可收到一个红包，一个客户指同一旺旺号
	 * 2.对比订单不符合条件：订单金额不满足活动条件！
	 * 3.对比订单不符合条件：验证不成功，请重新发送订单号。
	 * 4.已领过红包的提示语：您已经领过红包，请继续关注我们其他活动！
	 * @param orderNo
	 * @return 配置需发放的金额
	 */
	String validateOrder(String orderNo,String openId) throws TsfaServiceException;
	/**
	 * 重发公众号红包
	 * @param code 红包记录code
	 * @throws TsfaServiceExceptionp
	 */
	void repeatRedPack(String code) throws TsfaServiceException;
}
