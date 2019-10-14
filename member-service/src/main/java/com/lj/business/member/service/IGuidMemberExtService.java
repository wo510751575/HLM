package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.member.dto.GuidMemberExtDto;
import com.lj.business.member.dto.FindGuidMemberExtPage;


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
 * @author 林进权
 * 
 * 
 * CreateDate: 2017-08-22
 */
public interface IGuidMemberExtService {
	
	/**
	 * 
	 *
	 * 方法说明：添加导购开放平台扩展信息
	 *
	 * @param guidMemberExtDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017-08-22
	 *
	 */
	public void addGuidMemberExt(GuidMemberExtDto guidMemberExtDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购开放平台扩展信息
	 *
	 * @param findGuidMemberExt
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017-08-22
	 *
	 */
	public GuidMemberExtDto findGuidMemberExt(GuidMemberExtDto guidMemberExtDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询导购开放平台扩展信息
	 *
	 * @param findGuidMemberExtPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017-08-22
	 *
	 */
	public List<GuidMemberExtDto>  findGuidMemberExts(FindGuidMemberExtPage findGuidMemberExtPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购开放平台扩展信息
	 *
	 * @param updateGuidMemberExt
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017-08-22
	 *
	 */
	public void updateGuidMemberExt(GuidMemberExtDto guidMemberExtDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购开放平台扩展信息
	 *
	 * @param findGuidMemberExtPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017-08-22
	 *
	 */
	public Page<GuidMemberExtDto> findGuidMemberExtPage(FindGuidMemberExtPage findGuidMemberExtPage) throws TsfaServiceException;

	/**
	 * 通过jobNum查询
	 * 方法说明：
	 *
	 * @param @param guidMemberExtDto
	 * @param @return    设定文件 
	 * @return GuidMemberExtDto    返回类型 
	 * @throws Exception
	 *
	 * @author 林进权
	 *         CreateDate: 2018年1月30日
	 */
	public GuidMemberExtDto findGuidMemberExtByMobile(GuidMemberExtDto guidMemberExtDto);
	

}
