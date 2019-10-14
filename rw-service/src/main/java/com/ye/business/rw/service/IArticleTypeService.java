package com.ye.business.rw.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.rw.dto.ArticleTypeDto;
import com.ye.business.rw.dto.FindArticleTypePage;
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
public interface IArticleTypeService {
	
	/**
	 * 
	 *
	 * 方法说明：添加文章类型信息
	 *
	 * @param advertiseTypeDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addArticleType(ArticleTypeDto advertiseTypeDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找文章类型信息
	 *
	 * @param findArticleType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public ArticleTypeDto findArticleType(ArticleTypeDto advertiseTypeDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询文章类型信息
	 *
	 * @param findArticleTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<ArticleTypeDto>  findArticleTypes(FindArticleTypePage findArticleTypePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改文章类型信息
	 *
	 * @param updateArticleType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateArticleType(ArticleTypeDto advertiseTypeDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询文章类型信息
	 *
	 * @param findArticleTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<ArticleTypeDto> findArticleTypePage(FindArticleTypePage findArticleTypePage) throws TsfaServiceException;
	
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
