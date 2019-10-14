package com.ye.business.rw.dao;

import java.util.List;

import com.ye.business.rw.domain.Article;
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.FindArticlePage;

public interface IArticleDao {
	int deleteByPrimaryKey(String code);

	int insert(Article record);

	int insertSelective(Article record);

	Article selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(Article record);

	int updateByPrimaryKeyWithBLOBs(Article record);

	int updateByPrimaryKey(Article record);

	/**
	 * 未返回 articleHtml 字段
	 * 
	 * @param findArticlePage
	 * @return
	 */
	List<ArticleDto> findArticles(FindArticlePage findArticlePage);

	/**
	 * 未返回 articleHtml 字段
	 * 
	 * @param findArticlePage
	 * @return
	 */
	List<ArticleDto> findArticlePage(FindArticlePage findArticlePage);

	int findArticlePageCount(FindArticlePage findArticlePage);
	
	int updateArticleForReadNum(String code);

	List<ArticleDto> findArticleShareReadPage(FindArticlePage findArticlePage);

	int findArticleShareReadPageCount(FindArticlePage findArticlePage);
}