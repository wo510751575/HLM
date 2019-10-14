package com.ye.business.ad.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */



import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.ad.dto.BillDto;
import com.ye.business.ad.dto.FindBillPage;

import java.util.List;
/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
public interface IBillService {
	
	/**
	 * 
	 *
	 * 方法说明：添加交易流水信息
	 *
	 * @param billDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addBill(BillDto billDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找交易流水信息
	 *
	 * @param findBill
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public BillDto findBill(BillDto billDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询交易流水信息
	 *
	 * @param findBillPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<BillDto>  findBills(FindBillPage findBillPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改交易流水信息
	 *
	 * @param updateBill
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateBill(BillDto billDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询交易流水信息
	 *
	 * @param findBillPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<BillDto> findBillPage(FindBillPage findBillPage) throws TsfaServiceException;
	

}
