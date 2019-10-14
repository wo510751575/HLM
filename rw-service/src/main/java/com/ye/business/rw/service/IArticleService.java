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
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.FindArticlePage;

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
 *         CreateDate: 2019.04.18
 */
public interface IArticleService {

	/**
	 * 
	 *
	 * 方法说明：添加文章信息
	 *
	 * @param articleDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addArticle(ArticleDto articleDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找文章信息
	 *
	 * @param findArticle
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public ArticleDto findArticle(ArticleDto articleDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询文章信息
	 *
	 * @param findArticlePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<ArticleDto> findArticles(FindArticlePage findArticlePage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改文章信息
	 *
	 * @param updateArticle
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateArticle(ArticleDto articleDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询文章信息
	 *
	 * @param findArticlePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<ArticleDto> findArticlePage(FindArticlePage findArticlePage) throws TsfaServiceException;

	/**
	 * 阅读量修改
	 * 
	 * @param code
	 * @throws TsfaServiceException
	 */
	public void updateArticleForReadNum(String code) throws TsfaServiceException;
	
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
	 * 文章阅读分享统计
	 * @param findArticlePage
	 * @return
	 */
	public Page<ArticleDto> findArticleShareReadPage(FindArticlePage findArticlePage) throws TsfaServiceException;

}
