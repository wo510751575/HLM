package com.ye.business.rw.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.rw.dto.FindMyCollectionPage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.rw.dto.MyCollectionDto;

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
public interface IMyCollectionService {

	/**
	 * 
	 *
	 * 方法说明：添加我的收藏信息
	 *
	 * @param myCollectionDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addMyCollection(MyCollectionDto myCollectionDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找我的收藏信息
	 *
	 * @param findMyCollection
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public MyCollectionDto findMyCollection(MyCollectionDto myCollectionDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询我的收藏信息
	 *
	 * @param findMyCollectionPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<MyCollectionDto> findMyCollections(FindMyCollectionPage findMyCollectionPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改我的收藏信息
	 *
	 * @param updateMyCollection
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateMyCollection(MyCollectionDto myCollectionDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询我的收藏信息
	 *
	 * @param findMyCollectionPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<MyCollectionDto> findMyCollectionPage(FindMyCollectionPage findMyCollectionPage) throws TsfaServiceException;

	/**
	 * 阅读量修改
	 * 
	 * @param code
	 * @throws TsfaServiceException
	 */
	public void updateMyCollectionForReadNum(String code) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：主键删除
	 *
	 * @param code
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	public void removeByPrimaryKey(String code) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：条件删除
	 *
	 * @param record
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	public void removeByExample(MyCollectionDto record) throws TsfaServiceException;
}
