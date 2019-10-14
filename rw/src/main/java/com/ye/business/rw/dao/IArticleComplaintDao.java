package com.ye.business.rw.dao;

import java.util.List;

import com.ye.business.rw.domain.ArticleComplaint;
import com.ye.business.rw.dto.ArticleComplaintDto;
import com.ye.business.rw.dto.FindArticleComplaintPage;

public interface IArticleComplaintDao {
	int deleteByPrimaryKey(String code);

	int insert(ArticleComplaint record);

	int insertSelective(ArticleComplaint record);

	ArticleComplaint selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(ArticleComplaint record);

	int updateByPrimaryKey(ArticleComplaint record);

	List<ArticleComplaintDto> findArticleComplaints(FindArticleComplaintPage findArticleComplaintPage);

	List<ArticleComplaintDto> findArticleComplaintPage(FindArticleComplaintPage findArticleComplaintPage);

	int findArticleComplaintPageCount(FindArticleComplaintPage findArticleComplaintPage);
}