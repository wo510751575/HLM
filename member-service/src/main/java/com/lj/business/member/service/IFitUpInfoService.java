package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.fitUpInfo.AddFitUpInfo;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfo;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfoPage;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfoPageReturn;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfoReturn;


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
public interface IFitUpInfoService {
	
	/**
	 * 
	 *
	 * 方法说明：添加装修需求信息表信息
	 *
	 * @param addFitUpInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addFitUpInfo(AddFitUpInfo addFitUpInfo) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找装修需求信息表信息
	 *
	 * @param findFitUpInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindFitUpInfoReturn findFitUpInfo(FindFitUpInfo findFitUpInfo) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除装修需求信息表信息
	 *
	 * @param delFitUpInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	//public void delFitUpInfo(DelFitUpInfo delFitUpInfo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改装修需求信息表信息
	 *
	 * @param updateFitUpInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	//public void updateFitUpInfo(UpdateFitUpInfo updateFitUpInfo)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询装修需求信息表信息
	 *
	 * @param findFitUpInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindFitUpInfoPageReturn> findFitUpInfoPage(FindFitUpInfoPage findFitUpInfoPage) throws TsfaServiceException;
	

}
