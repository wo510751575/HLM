package com.lj.business.member.service;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.member.dto.service.type.AddServiceType;
import com.lj.business.member.dto.service.type.FindServiceType;
import com.lj.business.member.dto.service.type.FindServiceTypeReturn;
import com.lj.business.member.dto.service.type.UpdateServiceType;

/**
 * 
 * 
 * 类说明：服务类型表
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月20日
 */
public interface IServiceTypeService {
	
	/**
	 * 
	 *
	 * 方法说明：添加服务人员表信息
	 *
	 * @param addServiceType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public void addServiceType(AddServiceType addServiceType) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找服务人员表信息
	 *
	 * @param findServiceType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public FindServiceTypeReturn findServiceType(FindServiceType findServiceType) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改服务人员表信息
	 *
	 * @param updateServiceType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public void updateServiceType(UpdateServiceType updateServiceType) throws TsfaServiceException;

	
	/**
	 * 方法说明：查询服务人员列表.
	 *
	 * @param findServiceType
	 * @return the list< find serviceType return>
	 * @author 彭俊霖 CreateDate: 2017年9月21日
	 */
	public List<FindServiceTypeReturn> findServiceTypes(FindServiceType findServiceType) throws TsfaServiceException;
}
