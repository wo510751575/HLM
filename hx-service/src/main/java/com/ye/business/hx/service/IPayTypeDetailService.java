package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PayTypeDetailDto;
import com.ye.business.hx.dto.FindPayTypeDetailPage;


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
public interface IPayTypeDetailService {
	
	/**
	 * 
	 *
	 * 方法说明：添加支付方式详细信息
	 *
	 * @param payTypeDetailDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addPayTypeDetail(PayTypeDetailDto payTypeDetailDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找支付方式详细信息
	 *
	 * @param findPayTypeDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public PayTypeDetailDto findPayTypeDetail(PayTypeDetailDto payTypeDetailDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询支付方式详细信息
	 *
	 * @param findPayTypeDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<PayTypeDetailDto>  findPayTypeDetails(FindPayTypeDetailPage findPayTypeDetailPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改支付方式详细信息
	 *
	 * @param updatePayTypeDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updatePayTypeDetail(PayTypeDetailDto payTypeDetailDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询支付方式详细信息
	 *
	 * @param findPayTypeDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<PayTypeDetailDto> findPayTypeDetailPage(FindPayTypeDetailPage findPayTypeDetailPage) throws TsfaServiceException;
	

}
