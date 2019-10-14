package com.ye.business.rw.dao;

import java.util.List;

import com.ye.business.rw.domain.ArticleShare;
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.ArticleShareDto;
import com.ye.business.rw.dto.FindArticleSharePage;

public interface IArticleShareDao {
	int deleteByPrimaryKey(String code);

	int insert(ArticleShare record);

	int insertSelective(ArticleShare record);

	ArticleShare selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(ArticleShare record);

	int updateByPrimaryKey(ArticleShare record);

	List<ArticleShareDto> findArticleShares(FindArticleSharePage findArticleSharePage);

	List<ArticleShareDto> findArticleSharePage(FindArticleSharePage findArticleSharePage);

	int findArticleSharePageCount(FindArticleSharePage findArticleSharePage);

	/**
	 * 
	 * *方法说明：分页分组统计分享记录
	 *
	 * @param findArticleSharePage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年6月5日
	 */
	List<List<?>> findArticleSharePageForGroup(FindArticleSharePage findArticleSharePage);

	List<List<?>> findArticleSharePageForView(FindArticleSharePage findArticleSharePage);

	int findReadCount(ArticleDto articleDto);

	int findShareCount(ArticleDto articleDto);

	/**
	 * 
	 * *方法说明：统计分享记录
	 *
	 * @param findArticleSharePage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	List<ArticleShareDto> findArticleShareForGroupList(FindArticleSharePage findArticleSharePage);
	/**
	 * 
	 * *方法说明：统计分享文章记录
	 *
	 * @param findArticleSharePage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	List<List<?>> findArticleShareInfoForGroupList(FindArticleSharePage findArticleSharePage);
}