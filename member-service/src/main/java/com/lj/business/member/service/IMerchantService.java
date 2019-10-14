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
import com.lj.business.member.dto.AddMerchant;
import com.lj.business.member.dto.AddMerchantReturn;
import com.lj.business.member.dto.DelMerchant;
import com.lj.business.member.dto.DelMerchantReturn;
import com.lj.business.member.dto.FindMerchant;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantPage;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.dto.FindMerchantReturn;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.UpdateMerchant;
import com.lj.business.member.dto.UpdateMerchantReturn;


/**
 * 类说明：接口类
 * 商户
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface IMerchantService {
	
	/**
	 * 方法说明：添加商户表信息.
	 *
	 * @param addMerchant the add merchant
	 * @return the adds the merchant return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public AddMerchantReturn addMerchant(AddMerchant addMerchant) throws TsfaServiceException;
	
	/**
	 * 方法说明：通过code或merchantNo查找商户表信息.
	 *
	 * @param findMerchantDto the find merchant dto
	 * @return the find merchant return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public FindMerchantReturnDto selectMerchant(FindMerchantDto findMerchantDto) throws TsfaServiceException;
	
	/**
	 * 方法说明：查找商户表信息.
	 *
	 * @param findMerchant the find merchant
	 * @return the find merchant return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public FindMerchantReturn findMerchant(FindMerchant findMerchant) throws TsfaServiceException;
	
	
	/**
	 * 方法说明：删除商户表信息.
	 *
	 * @param delMerchant the del merchant
	 * @return the del merchant return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public DelMerchantReturn delMerchant(DelMerchant delMerchant) throws TsfaServiceException;

	/**
	 * 方法说明：修改商户表信息.
	 *
	 * @param updateMerchant the update merchant
	 * @return the update merchant return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public UpdateMerchantReturn updateMerchant(UpdateMerchant updateMerchant)throws TsfaServiceException;

	/**
	 * 方法说明：查找商户表信息.
	 *
	 * @param findMerchantPage the find merchant page
	 * @return the page< find merchant page return>
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public Page<FindMerchantPageReturn> findMerchantPage(FindMerchantPage findMerchantPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找商户列表
	 *
	 * @param findMerchantPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年8月2日
	 *
	 */
	public List<FindMerchantPageReturn> findMerchants(FindMerchantPage findMerchantPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询所有商户信息
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月25日
	 *
	 */
	public List<FindMerchantPageReturn> findAllMerchant() throws TsfaServiceException; 
	/**
	 * 
	 *
	 * 方法说明：根据商户编号修改商户信息
	 *
	 * @param updateMerchant
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年11月15日
	 *
	 */
	public UpdateMerchantReturn updateByMerchantNo(UpdateMerchant updateMerchant)throws TsfaServiceException;

	
}
