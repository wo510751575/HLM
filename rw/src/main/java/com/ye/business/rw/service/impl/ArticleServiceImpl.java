package com.ye.business.rw.service.impl;

/**
 * Copyright &copy; 2018-2021  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.rw.constant.ErrorCodeArticle;
import com.ye.business.rw.dao.IArticleDao;
import com.ye.business.rw.dao.IArticleShareDao;
import com.ye.business.rw.domain.Article;
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.FindArticlePage;
import com.ye.business.rw.service.IArticleService;
/**
 * 类说明：实现类
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
@Service
public class ArticleServiceImpl implements IArticleService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	

	@Resource
	private IArticleDao articleDao;
	@Resource
	private IArticleShareDao articleShareDao;
	
	
	@Override
	public String addArticle(ArticleDto articleDto) throws TsfaServiceException {
		logger.debug("addArticle(AddArticle addArticle={}) - start", articleDto); 

		AssertUtils.notNull(articleDto);
		try {
			Article article = new Article();
			//add数据录入
			article.setCode(GUID.getPreUUID());
			article.setMemberNoGuid(articleDto.getMemberNoGuid());
			article.setMemberNameGuid(articleDto.getMemberNameGuid());
			article.setShopNo(articleDto.getShopNo());
			article.setShopName(articleDto.getShopName());
			article.setMerchantNo(articleDto.getMerchantNo());
			article.setMerchantName(articleDto.getMerchantName());
			article.setType(articleDto.getType());
			article.setTypeCode(articleDto.getTypeCode());
			article.setMainImage(articleDto.getMainImage());
			article.setTitle(articleDto.getTitle());
			article.setSource(articleDto.getSource());
			article.setRemark(articleDto.getRemark());
			article.setReadNum(articleDto.getReadNum());
			article.setLikeNum(articleDto.getLikeNum());
			article.setCreateId(articleDto.getCreateId());
			article.setCreateDate(articleDto.getCreateDate());
			article.setMemberNo(articleDto.getMemberNo());
			article.setRwState(articleDto.getRwState());
			article.setWebUrl(articleDto.getWebUrl());
			article.setArticleHtml(articleDto.getArticleHtml());
			articleDao.insertSelective(article);
			logger.debug("addArticle(ArticleDto) - end - return"); 
			return article.getCode();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增文章信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticle.ARTICLE_ADD_ERROR, "新增文章信息错误！", e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询文章信息
	 *
	 * @param findArticlePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<ArticleDto>  findArticles(FindArticlePage findArticlePage)throws TsfaServiceException{
		AssertUtils.notNull(findArticlePage);
		List<ArticleDto> returnList=null;
		try {
			returnList = articleDao.findArticles(findArticlePage);
		} catch (Exception e) {
			logger.error("查找文章信息信息错误！",  e);
			throw new TsfaServiceException(ErrorCodeArticle.ARTICLE_NOT_EXIST_ERROR, "文章信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateArticle(ArticleDto articleDto) throws TsfaServiceException {
		logger.debug("updateArticle(ArticleDto articleDto={}) - start", articleDto); 

		AssertUtils.notNull(articleDto);
		AssertUtils.notNullAndEmpty(articleDto.getCode(),"Code不能为空");
		try {
			Article article = new Article();
			//update数据录入
			article.setCode(articleDto.getCode());
			article.setMemberNoGuid(articleDto.getMemberNoGuid());
			article.setMemberNameGuid(articleDto.getMemberNameGuid());
			article.setShopNo(articleDto.getShopNo());
			article.setShopName(articleDto.getShopName());
			article.setMerchantNo(articleDto.getMerchantNo());
			article.setMerchantName(articleDto.getMerchantName());
			article.setType(articleDto.getType());
			article.setTypeCode(articleDto.getTypeCode());
			article.setMainImage(articleDto.getMainImage());
			article.setTitle(articleDto.getTitle());
			article.setSource(articleDto.getSource());
			article.setRemark(articleDto.getRemark());
			article.setReadNum(articleDto.getReadNum());
			article.setLikeNum(articleDto.getLikeNum());
			article.setCreateId(articleDto.getCreateId());
			article.setCreateDate(articleDto.getCreateDate());
			article.setMemberNo(articleDto.getMemberNo());
			article.setRwState(articleDto.getRwState());
			article.setWebUrl(articleDto.getWebUrl());
			article.setArticleHtml(articleDto.getArticleHtml());
			AssertUtils.notUpdateMoreThanOne(articleDao.updateByPrimaryKeySelective(article));
			logger.debug("updateArticle(ArticleDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("文章信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticle.ARTICLE_UPDATE_ERROR, "文章信息更新信息错误！", e);
		}
	}

	

	@Override
	public ArticleDto findArticle(ArticleDto articleDto) throws TsfaServiceException {
		logger.debug("findArticle(FindArticle findArticle={}) - start", articleDto); 

		AssertUtils.notNull(articleDto);
		AssertUtils.notAllNull(articleDto.getCode(),"Code不能为空");
		try {
			Article article = articleDao.selectByPrimaryKey(articleDto.getCode());
			if(article == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.ARTICLE_NOT_EXIST_ERROR,"文章信息不存在");
			}
			ArticleDto findArticleReturn = new ArticleDto();
			//find数据录入
			findArticleReturn.setCode(article.getCode());
			findArticleReturn.setMemberNoGuid(article.getMemberNoGuid());
			findArticleReturn.setMemberNameGuid(article.getMemberNameGuid());
			findArticleReturn.setShopNo(article.getShopNo());
			findArticleReturn.setShopName(article.getShopName());
			findArticleReturn.setMerchantNo(article.getMerchantNo());
			findArticleReturn.setMerchantName(article.getMerchantName());
			findArticleReturn.setType(article.getType());
			findArticleReturn.setTypeCode(article.getTypeCode());
			findArticleReturn.setMainImage(article.getMainImage());
			findArticleReturn.setTitle(article.getTitle());
			findArticleReturn.setSource(article.getSource());
			findArticleReturn.setRemark(article.getRemark());
			findArticleReturn.setReadNum(article.getReadNum());
			findArticleReturn.setLikeNum(article.getLikeNum());
			findArticleReturn.setCreateId(article.getCreateId());
			findArticleReturn.setCreateDate(article.getCreateDate());
			findArticleReturn.setMemberNo(article.getMemberNo());
			findArticleReturn.setRwState(article.getRwState());
			findArticleReturn.setWebUrl(article.getWebUrl());
			findArticleReturn.setArticleHtml(article.getArticleHtml());
			
			logger.debug("findArticle(ArticleDto) - end - return value={}", findArticleReturn); 
			return findArticleReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找文章信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticle.ARTICLE_FIND_ERROR, "查找文章信息信息错误！", e);
		}


	}

	
	@Override
	public Page<ArticleDto> findArticlePage(FindArticlePage findArticlePage) throws TsfaServiceException {
		logger.debug("findArticlePage(FindArticlePage findArticlePage={}) - start", findArticlePage); 

		AssertUtils.notNull(findArticlePage);
		List<ArticleDto> returnList=null;
		int count = 0;
		try {
			returnList = articleDao.findArticlePage(findArticlePage);
			count = articleDao.findArticlePageCount(findArticlePage);
		}  catch (Exception e) {
			logger.error("文章信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeArticle.ARTICLE_FIND_PAGE_ERROR, "文章信息不存在错误.！", e);
		}
		Page<ArticleDto> returnPage = new Page<ArticleDto>(returnList, count, findArticlePage);

		logger.debug("findArticlePage(FindArticlePage) - end - return value={}", returnPage); 
		return  returnPage;
	} 

	@Override
	public void updateArticleForReadNum(String code) throws TsfaServiceException {

		logger.debug("updateArticleForReadNum(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {
			
			AssertUtils.notUpdateMoreThanOne(articleDao.updateArticleForReadNum(code));
			logger.debug("updateArticleForReadNum(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("文章信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticle.ARTICLE_UPDATE_ERROR, "文章信息更新阅读量信息错误！", e);
		}
	}
	
	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {
			
			AssertUtils.notUpdateMoreThanOne(articleDao.deleteByPrimaryKey(code));
			logger.debug("updateArticleForReadNum(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("文章信息删除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticle.ARTICLE_NOT_EXIST_ERROR, "文章信息删除信息错误！", e);
		}
	}


	
	public Page<ArticleDto> findArticleShareReadPage(FindArticlePage findArticlePage) throws TsfaServiceException {
		logger.debug("findArticleShareReadPage(FindArticlePage findArticlePage={}) - start",findArticlePage);
		AssertUtils.notNull(findArticlePage);
		List<ArticleDto> returnList = null;
		int count = 0;
		try {
			returnList = articleDao.findArticleShareReadPage(findArticlePage);
			for (ArticleDto articleDto : returnList) {
				articleDto.setMemberNoGuid(findArticlePage.getParam().getMemberNoGuid());
				int read = articleShareDao.findReadCount(articleDto);
				int share = articleShareDao.findShareCount(articleDto);
				articleDto.setReadNum(read);
				articleDto.setLikeNum(share);
			}
			count = articleDao.findArticleShareReadPageCount(findArticlePage);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查询文章分享阅读信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticle.ARTICLE_NOT_EXIST_ERROR, "文章信息删除信息错误！", e);
		}
		Page<ArticleDto> returnPage = new Page<ArticleDto>(returnList, count, findArticlePage);
		return returnPage;
	}
}
