package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.service.product.AddServiceProduct;
import com.lj.business.member.dto.service.product.AddServiceProductReturn;
import com.lj.business.member.dto.service.product.FindServiceProduct;
import com.lj.business.member.dto.service.product.FindServiceProductPage;
import com.lj.business.member.dto.service.product.FindServiceProductPageReturn;
import com.lj.business.member.dto.service.product.FindServiceProductReturn;
import com.lj.business.member.dto.service.product.UpdateServiceProduct;
import com.lj.business.member.dto.service.product.UpdateServiceProductReturn;


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
public interface IServiceProductService {
	
	/**
	 * 
	 *
	 * 方法说明：添加服务人员作品表信息
	 *
	 * @param addServiceProduct
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddServiceProductReturn addServiceProduct(AddServiceProduct addServiceProduct) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找服务人员作品表信息
	 *
	 * @param findServiceProduct
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindServiceProductReturn findServiceProduct(FindServiceProduct findServiceProduct) throws TsfaServiceException;
	

	/**
	 * 
	 *
	 * 方法说明：修改服务人员作品表信息
	 *
	 * @param updateServiceProduct
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateServiceProductReturn updateServiceProduct(UpdateServiceProduct updateServiceProduct)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找服务人员作品表信息
	 *
	 * @param findServiceProductPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public Page<FindServiceProductPageReturn> findServiceProductPage(FindServiceProductPage findServiceProductPage) throws TsfaServiceException;
	

}
