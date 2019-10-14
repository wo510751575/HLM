package com.ye.business.rw.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.rw.dto.ArticleComplaintDto;
import com.ye.business.rw.dto.FindArticleComplaintPage;


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
public interface IArticleComplaintService {
	
	/**
	 * 
	 *
	 * 方法说明：添加文章投诉记录信息
	 *
	 * @param articleComplaintDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addArticleComplaint(ArticleComplaintDto articleComplaintDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找文章投诉记录信息
	 *
	 * @param findArticleComplaint
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public ArticleComplaintDto findArticleComplaint(ArticleComplaintDto articleComplaintDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询文章投诉记录信息
	 *
	 * @param findArticleComplaintPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<ArticleComplaintDto>  findArticleComplaints(FindArticleComplaintPage findArticleComplaintPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改文章投诉记录信息
	 *
	 * @param updateArticleComplaint
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateArticleComplaint(ArticleComplaintDto articleComplaintDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询文章投诉记录信息
	 *
	 * @param findArticleComplaintPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<ArticleComplaintDto> findArticleComplaintPage(FindArticleComplaintPage findArticleComplaintPage) throws TsfaServiceException;
	
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
