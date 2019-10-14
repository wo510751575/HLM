package com.lj.business.member.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.service.person.AddServicePerson;
import com.lj.business.member.dto.service.person.DelServicePerson;
import com.lj.business.member.dto.service.person.FindServicePerson;
import com.lj.business.member.dto.service.person.FindServicePersonApp;
import com.lj.business.member.dto.service.person.FindServicePersonAppReturn;
import com.lj.business.member.dto.service.person.FindServicePersonPage;
import com.lj.business.member.dto.service.person.FindServicePersonPageReturn;
import com.lj.business.member.dto.service.person.FindServicePersonReturn;
import com.lj.business.member.dto.service.person.UpdateServicePerson;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

/**
 * 
 * 
 * 类说明：服务人员表
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年9月20日
 */
public interface IServicePersonService {
	
	/**
	 * 
	 *
	 * 方法说明：添加服务人员表信息
	 *
	 * @param addServicePerson
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public void addServicePerson(AddServicePerson addServicePerson) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找服务人员表信息
	 *
	 * @param findServicePerson
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public FindServicePersonReturn findServicePerson(FindServicePerson findServicePerson) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：删除服务人员表信息
	 *
	 * @param delServicePerson
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public void delServicePerson(DelServicePerson delServicePerson) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改服务人员表信息
	 *
	 * @param updateServicePerson
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public void updateServicePerson(UpdateServicePerson updateServicePerson) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询服务人员表信息
	 *
	 * @param findServicePersonPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年09月21日
	 *
	 */
	public Page<FindServicePersonPageReturn> findServicePersonPage(FindServicePersonPage findServicePersonPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：APP查询所有服务人员列表
	 *
	 * @param findServicePersonApp
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月21日
	 *
	 */
	public List<FindServicePersonAppReturn> findServicePersonApp(FindServicePersonApp findServicePersonApp) throws TsfaServiceException;
	
	/**
	 * 方法说明：查询服务人员列表.
	 *
	 * @param findServicePerson
	 * @return the list< find servicePerson return>
	 * @author 彭俊霖 CreateDate: 2017年9月21日
	 */
	public List<FindServicePersonReturn> findServicePersons(FindServicePerson findServicePerson) throws TsfaServiceException;
}

