package com.ye.business.rw.test;

import java.util.List;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.FindArticlePage;
import com.ye.business.rw.service.IArticleService;
/**
 * 类说明：测试类
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
public class ArticleServiceImplTest extends SpringTestCase{

	@Resource
	IArticleService articleService;



	/**
	 * 
	 *
	 * 方法说明：添加文章信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	@Test
	public void addArticle() throws TsfaServiceException{
		ArticleDto articleDto = new ArticleDto();
		//add数据录入
		articleDto.setCode("Code");
		articleDto.setMemberNoGuid("MemberNoGuid");
		articleDto.setMemberNameGuid("MemberNameGuid");
		articleDto.setShopNo("ShopNo");
		articleDto.setShopName("ShopName");
		articleDto.setMerchantNo("MerchantNo");
		articleDto.setMerchantName("MerchantName");
		articleDto.setType("Type");
		articleDto.setMainImage("MainImage");
		articleDto.setTitle("Title");
		articleDto.setSource("Source");
		articleDto.setRemark("Remark");
//		articleDto.setReadNum("ReadNum");
//		articleDto.setLikeNum("LikeNum");
		articleDto.setCreateId("CreateId");
//		articleDto.setCreateDate("CreateDate");
		articleDto.setArticleHtml("ArticleHtml");
		
		articleService.addArticle(articleDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改文章信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updateArticle() throws TsfaServiceException{
		ArticleDto articleDto = new ArticleDto();
		//update数据录入
		articleDto.setCode("Code");
		articleDto.setMemberNoGuid("MemberNoGuid");
		articleDto.setMemberNameGuid("MemberNameGuid");
		articleDto.setShopNo("ShopNo");
		articleDto.setShopName("ShopName");
		articleDto.setMerchantNo("MerchantNo");
		articleDto.setMerchantName("MerchantName");
		articleDto.setType("Type");
		articleDto.setMainImage("MainImage");
		articleDto.setTitle("Title");
		articleDto.setSource("Source");
		articleDto.setRemark("Remark");
//		articleDto.setReadNum("ReadNum");
//		articleDto.setLikeNum("LikeNum");
		articleDto.setCreateId("CreateId");
//		articleDto.setCreateDate("CreateDate");
		articleDto.setArticleHtml("ArticleHtml");

		articleService.updateArticle(articleDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找文章信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findArticle() throws TsfaServiceException{
		ArticleDto findArticle = new ArticleDto();
		findArticle.setCode("111");
		articleService.findArticle(findArticle);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找文章信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	@Test
	public void findArticlePage() throws TsfaServiceException {
		FindArticlePage findArticlePage = new FindArticlePage();
		Page<ArticleDto> page = articleService.findArticlePage(findArticlePage);
		Assert.assertNotNull(page);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找文章信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	@Test
	public void findArticles() throws TsfaServiceException {
		FindArticlePage findArticlePage = new FindArticlePage();
		List<ArticleDto> page = articleService.findArticles(findArticlePage);
		Assert.assertNotNull(page);		
	}
	
}
