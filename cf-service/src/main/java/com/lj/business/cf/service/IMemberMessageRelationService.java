package com.lj.business.cf.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.business.cf.dto.memberMessageRelation.AddMemberMessageRelation;
import com.lj.business.cf.dto.memberMessageRelation.DelMemberMessageRelation;
import com.lj.business.cf.dto.memberMessageRelation.FindMemberMessageRelation;
import com.lj.business.cf.dto.memberMessageRelation.FindMemberMessageRelationPage;
import com.lj.business.cf.dto.memberMessageRelation.FindMemberMessageRelationPageReturn;
import com.lj.business.cf.dto.memberMessageRelation.FindMemberMessageRelationReturn;
import com.lj.business.cf.dto.memberMessageRelation.UpdateMemberMessageRelation;
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
public interface IMemberMessageRelationService {
	
	/**
	 * 
	 *
	 * 方法说明：添加用户消息关联表信息
	 *
	 * @param addMemberMessageRelation
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addMemberMessageRelation(AddMemberMessageRelation addMemberMessageRelation) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找用户消息关联表信息
	 *
	 * @param findMemberMessageRelation
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindMemberMessageRelationReturn findMemberMessageRelation(FindMemberMessageRelation findMemberMessageRelation) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除用户消息关联表信息
	 *
	 * @param delMemberMessageRelation
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delMemberMessageRelation(DelMemberMessageRelation delMemberMessageRelation) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改用户消息关联表信息
	 *
	 * @param updateMemberMessageRelation
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateMemberMessageRelation(UpdateMemberMessageRelation updateMemberMessageRelation)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询用户消息关联表信息
	 *
	 * @param findMemberMessageRelationPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindMemberMessageRelationPageReturn> findMemberMessageRelationPage(FindMemberMessageRelationPage findMemberMessageRelationPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据商户和时间查询用户关系消息表
	 *
	 * @param findMemberMessageRelation
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年09月15日
	 *
	 */
	public List<FindMemberMessageRelationReturn> findMemberMessageRelationByMerDay(
			FindMemberMessageRelation findMemberMessageRelation);
	

}
