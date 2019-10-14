package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.guidCardCustomer.AddGuidCardCustomer;
import com.lj.business.member.dto.guidCardCustomer.DelGuidCardCustomer;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomer;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomerPage;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomerPageReturn;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomerReturn;
import com.lj.business.member.dto.guidCardCustomer.GuidCardAddNumDto;
import com.lj.business.member.dto.guidCardCustomer.UpdateGuidCardCustomer;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 梅宏博
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface IGuidCardCustomerService {
	
	/**
	 * 
	 *
	 * 方法说明：添加导购名片与客户关联表信息
	 *
	 * @param addGuidCardCustomer
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addGuidCardCustomer(AddGuidCardCustomer addGuidCardCustomer) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购名片与客户关联表信息
	 *
	 * @param findGuidCardCustomer
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindGuidCardCustomerReturn findGuidCardCustomer(FindGuidCardCustomer findGuidCardCustomer) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除导购名片与客户关联表信息
	 *
	 * @param delGuidCardCustomer
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delGuidCardCustomer(DelGuidCardCustomer delGuidCardCustomer) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购名片与客户关联表信息
	 *
	 * @param updateGuidCardCustomer
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateGuidCardCustomer(UpdateGuidCardCustomer updateGuidCardCustomer)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购名片与客户关联表信息
	 *
	 * @param findGuidCardCustomerPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindGuidCardCustomerPageReturn> findGuidCardCustomerPage(FindGuidCardCustomerPage findGuidCardCustomerPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：添加名片和客户关联关系
	 *
	 * @param guidCardAddNumDto
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月3日
	 *
	 */
	public int addGuidCardCustomerByOpenId(GuidCardAddNumDto guidCardAddNumDto);
	
	/**
	 * 
	 *
	 * 方法说明：根据条件查找名片明细
	 *
	 * @param findGuidCardCustomer
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月3日
	 *
	 */
	public List<FindGuidCardCustomerReturn> findGuidCardCustomerSelect(FindGuidCardCustomer findGuidCardCustomer);

	/**
	 * 
	 *
	 * 方法说明：删除客户与导购名片的关系
	 *
	 * @param delGuidCardCustomer
	 *
	 * @author 梅宏博  CreateDate: 2017年11月8日
	 *
	 */
	public int delGuidCardCustomerSelect(
			DelGuidCardCustomer delGuidCardCustomer);
}
