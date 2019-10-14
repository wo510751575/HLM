package com.lj.business.cp.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.cp.dto.awardCondition.AddAwardCondition;
import com.lj.business.cp.dto.awardCondition.DelAwardCondition;
import com.lj.business.cp.dto.awardCondition.FindAwardCondition;
import com.lj.business.cp.dto.awardCondition.FindAwardConditionPage;
import com.lj.business.cp.dto.awardCondition.FindAwardConditionPageReturn;
import com.lj.business.cp.dto.awardCondition.FindAwardConditionReturn;
import com.lj.business.cp.dto.awardCondition.UpdateAwardCondition;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface IAwardConditionService {
	
	/**
	 * 
	 *
	 * 方法说明：添加导购行为信息记录表信息
	 *
	 * @param addAwardCondition
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addAwardCondition(AddAwardCondition addAwardCondition) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购行为信息记录表信息
	 *
	 * @param findAwardCondition
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindAwardConditionReturn findAwardCondition(FindAwardCondition findAwardCondition) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除导购行为信息记录表信息
	 *
	 * @param delAwardCondition
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delAwardCondition(DelAwardCondition delAwardCondition) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购行为信息记录表信息
	 *
	 * @param updateAwardCondition
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateAwardCondition(UpdateAwardCondition updateAwardCondition)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购行为信息记录表信息
	 *
	 * @param findAwardConditionPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindAwardConditionPageReturn> findAwardConditionPage(FindAwardConditionPage findAwardConditionPage) throws TsfaServiceException;
	

}
