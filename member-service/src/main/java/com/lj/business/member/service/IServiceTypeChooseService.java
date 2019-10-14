package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.service.typechoose.AddServiceTypeChoose;
import com.lj.business.member.dto.service.typechoose.AddServiceTypeChooseReturn;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoose;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoosePage;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoosePageReturn;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChooseReturn;
import com.lj.business.member.dto.service.typechoose.UpdateServiceTypeChoose;
import com.lj.business.member.dto.service.typechoose.UpdateServiceTypeChooseReturn;


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
public interface IServiceTypeChooseService {
	
	/**
	 * 
	 *
	 * 方法说明：添加服务类型选择表信息
	 *
	 * @param addServiceTypeChoose
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddServiceTypeChooseReturn addServiceTypeChoose(AddServiceTypeChoose addServiceTypeChoose) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找服务类型选择表信息
	 *
	 * @param findServiceTypeChoose
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindServiceTypeChooseReturn findServiceTypeChoose(FindServiceTypeChoose findServiceTypeChoose) throws TsfaServiceException;
	
	

	/**
	 * 
	 *
	 * 方法说明：修改服务类型选择表信息
	 *
	 * @param updateServiceTypeChoose
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateServiceTypeChooseReturn updateServiceTypeChoose(UpdateServiceTypeChoose updateServiceTypeChoose)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找服务类型选择表信息
	 *
	 * @param findServiceTypeChoosePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public Page<FindServiceTypeChoosePageReturn> findServiceTypeChoosePage(FindServiceTypeChoosePage findServiceTypeChoosePage) throws TsfaServiceException;
	

}
