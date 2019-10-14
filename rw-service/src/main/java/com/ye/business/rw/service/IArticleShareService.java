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
import com.ye.business.rw.dto.ArticleShareDto;
import com.ye.business.rw.dto.FindArticleSharePage;

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
public interface IArticleShareService {

	/**
	 * 
	 *
	 * 方法说明：添加文章分享记录信息
	 *
	 * @param articleShareDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addArticleShare(ArticleShareDto articleShareDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找文章分享记录信息
	 *
	 * @param findArticleShare
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public ArticleShareDto findArticleShare(ArticleShareDto articleShareDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询文章分享记录信息
	 *
	 * @param findArticleSharePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<ArticleShareDto> findArticleShares(FindArticleSharePage findArticleSharePage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改文章分享记录信息
	 *
	 * @param updateArticleShare
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateArticleShare(ArticleShareDto articleShareDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询文章分享记录信息
	 *
	 * @param findArticleSharePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<ArticleShareDto> findArticleSharePage(FindArticleSharePage findArticleSharePage) throws TsfaServiceException;

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
	 * @param findArticleSharePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<ArticleShareDto> findArticleSharePageForGroup(FindArticleSharePage findArticleSharePage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询文章分享记录信息
	 *
	 * @param findArticleSharePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<ArticleShareDto> findArticleSharePageForView(FindArticleSharePage findArticleSharePage) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：统计分享记录
	 *
	 * @param findArticleSharePage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	public List<ArticleShareDto> findArticleShareForGroupList(ArticleShareDto record) throws TsfaServiceException;
	/**
	 * 
	 * *方法说明：统计分享文章记录
	 *
	 * @param findArticleSharePage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	public Page<ArticleShareDto> findArticleShareInfoForGroupList(FindArticleSharePage findArticleSharePage) throws TsfaServiceException;
}
