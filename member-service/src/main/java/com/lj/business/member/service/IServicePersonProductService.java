package com.lj.business.member.service;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.service.personproduct.AddServicePersonProduct;
import com.lj.business.member.dto.service.personproduct.DelServicePersonProduct;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProduct;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductPage;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductPageReturn;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductReturn;
import com.lj.business.member.dto.service.personproduct.UpdateServicePersonProduct;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

/**
 * 
 * 
 * 类说明：服务人员作品表
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年9月21日
 */
public interface IServicePersonProductService {
	/**
	 * 
	 *
	 * 方法说明：添加服务人员作品表信息
	 *
	 * @param addServicePerson
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public void addServicePersonProduct(AddServicePersonProduct addServicePersonProduct) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找服务人员作品表信息
	 *
	 * @param findServicePerson
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public FindServicePersonProductReturn findServicePersonProduct(FindServicePersonProduct findServicePersonProduct) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：删除服务人员作品表信息
	 *
	 * @param delServicePerson
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public void delServicePersonProduct(DelServicePersonProduct delServicePersonProduct) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改服务人员作品表信息
	 *
	 * @param updateServicePerson
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public void updateServicePersonProduct(UpdateServicePersonProduct updateServicePersonProduct) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询服务人员作品表信息
	 *
	 * @param findServicePersonPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public Page<FindServicePersonProductPageReturn> findServicePersonProductPage(FindServicePersonProductPage findServicePersonProductPage) throws TsfaServiceException;
}
