package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.member.dto.GuidMemberRwDto;
import com.lj.business.member.dto.FindGuidMemberRwPage;


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author sjiying
 * 
 * 
 * CreateDate: 2019.04.18
 */
public interface IGuidMemberRwService {
	
	/**
	 * 
	 *
	 * 方法说明：添加导购扩展热文用户记录信息
	 *
	 * @param guidMemberRwDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addGuidMemberRw(GuidMemberRwDto guidMemberRwDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购扩展热文用户记录信息
	 *
	 * @param findGuidMemberRw
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public GuidMemberRwDto findGuidMemberRw(GuidMemberRwDto guidMemberRwDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询导购扩展热文用户记录信息
	 *
	 * @param findGuidMemberRwPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<GuidMemberRwDto>  findGuidMemberRws(FindGuidMemberRwPage findGuidMemberRwPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购扩展热文用户记录信息
	 *
	 * @param updateGuidMemberRw
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateGuidMemberRw(GuidMemberRwDto guidMemberRwDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购扩展热文用户记录信息
	 *
	 * @param findGuidMemberRwPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<GuidMemberRwDto> findGuidMemberRwPage(FindGuidMemberRwPage findGuidMemberRwPage) throws TsfaServiceException;
	
	/**
	 * 
	 * *方法说明：刪除
	 *
	 * @param code
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年5月8日
	 */
	public void removeByPrimaryKey(String code) throws TsfaServiceException;

}
