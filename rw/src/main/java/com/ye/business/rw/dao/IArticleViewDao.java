package com.ye.business.rw.dao;

import java.util.List;

import com.ye.business.rw.domain.ArticleView;
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.ArticleViewDto;
import com.ye.business.rw.dto.FindArticleViewPage;

public interface IArticleViewDao {
	int deleteByPrimaryKey(String code);

	int insert(ArticleView record);

	int insertSelective(ArticleView record);

	ArticleView selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(ArticleView record);

	int updateByPrimaryKey(ArticleView record);

	List<ArticleViewDto> findArticleViews(FindArticleViewPage findArticleViewPage);

	List<ArticleViewDto> findArticleViewPage(FindArticleViewPage findArticleViewPage);

	int findArticleViewPageCount(FindArticleViewPage findArticleViewPage);

	/**
	 * 
	 * *方法说明：分页分组统计分享记录
	 *
	 * @param findArticleViewPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年6月5日
	 */
	List<List<?>> findArticleViewPageForGroup(FindArticleViewPage findArticleViewPage);

	List<List<?>> findArticleViewPageForView(FindArticleViewPage findArticleViewPage);

	int findReadCount(ArticleDto articleDto);

	int findViewCount(ArticleDto articleDto);

	/**
	 * 
	 * *方法说明：统计分享记录
	 *
	 * @param findArticleViewPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	List<ArticleViewDto> findArticleViewForGroupList(FindArticleViewPage findArticleViewPage);

	/**
	 * 
	 * *方法说明：统计分享文章记录
	 *
	 * @param findArticleViewPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	List<List<?>> findArticleViewInfoForGroupList(FindArticleViewPage findArticleViewPage);
}