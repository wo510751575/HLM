package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PayDetailDto;
import com.ye.business.hx.dto.FindPayDetailPage;


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
public interface IPayDetailService {
	
	/**
	 * 
	 *
	 * 方法说明：添加收费项目详细信息
	 *
	 * @param payDetailDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addPayDetail(PayDetailDto payDetailDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找收费项目详细信息
	 *
	 * @param findPayDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public PayDetailDto findPayDetail(PayDetailDto payDetailDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询收费项目详细信息
	 *
	 * @param findPayDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<PayDetailDto>  findPayDetails(FindPayDetailPage findPayDetailPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改收费项目详细信息
	 *
	 * @param updatePayDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updatePayDetail(PayDetailDto payDetailDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询收费项目详细信息
	 *
	 * @param findPayDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<PayDetailDto> findPayDetailPage(FindPayDetailPage findPayDetailPage) throws TsfaServiceException;
	

}
