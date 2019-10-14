package com.ye.business.rw.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.rw.dto.ArticleViewDto;
import com.ye.business.rw.dto.FindArticleViewPage;

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
public interface IArticleViewService {

	/**
	 * 
	 *
	 * 方法说明：添加文章分享记录信息
	 *
	 * @param articleViewDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addArticleView(ArticleViewDto articleViewDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：添加文章分享记录信息
	 *
	 * @param articleViewDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addArticleViewByOnly(ArticleViewDto articleViewDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找文章分享记录信息
	 *
	 * @param findArticleView
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public ArticleViewDto findArticleView(ArticleViewDto articleViewDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询文章分享记录信息
	 *
	 * @param findArticleViewPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<ArticleViewDto> findArticleViews(FindArticleViewPage findArticleViewPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改文章分享记录信息
	 *
	 * @param updateArticleView
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateArticleView(ArticleViewDto articleViewDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询文章分享记录信息
	 *
	 * @param findArticleViewPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<ArticleViewDto> findArticleViewPage(FindArticleViewPage findArticleViewPage) throws TsfaServiceException;

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

	/**
	 * 
	 *
	 * 方法说明：分页查询文章分享记录信息
	 *
	 * @param findArticleViewPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<ArticleViewDto> findArticleViewPageForGroup(FindArticleViewPage findArticleViewPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询文章分享记录信息
	 *
	 * @param findArticleViewPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<ArticleViewDto> findArticleViewPageForView(FindArticleViewPage findArticleViewPage) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：统计分享记录
	 *
	 * @param findArticleViewPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	public List<ArticleViewDto> findArticleViewForGroupList(ArticleViewDto record) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：统计分享文章记录
	 *
	 * @param findArticleViewPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	public Page<ArticleViewDto> findArticleViewInfoForGroupList(FindArticleViewPage findArticleViewPage) throws TsfaServiceException;
	
}
