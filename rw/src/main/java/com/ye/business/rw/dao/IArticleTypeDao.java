package com.ye.business.rw.dao;

import java.util.List;

import com.ye.business.rw.domain.ArticleType;
import com.ye.business.rw.dto.ArticleTypeDto;
import com.ye.business.rw.dto.FindArticleTypePage;

public interface IArticleTypeDao {
	int deleteByPrimaryKey(String code);

	int insert(ArticleType record);

	int insertSelective(ArticleType record);

	ArticleType selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(ArticleType record);

	int updateByPrimaryKey(ArticleType record);

	List<ArticleTypeDto> findArticleTypes(FindArticleTypePage findArticleTypePage);

	List<ArticleTypeDto> findArticleTypePage(FindArticleTypePage findArticleTypePage);

	int findArticleTypePageCount(FindArticleTypePage findArticleTypePage);
}