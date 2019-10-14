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
import com.ye.business.rw.constant.ErrorCodeArticleComplaint;
import com.ye.business.rw.dao.IArticleComplaintDao;
import com.ye.business.rw.domain.ArticleComplaint;
import com.ye.business.rw.dto.ArticleComplaintDto;
import com.ye.business.rw.dto.FindArticleComplaintPage;
import com.ye.business.rw.service.IArticleComplaintService;

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
public class ArticleComplaintServiceImpl implements IArticleComplaintService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ArticleComplaintServiceImpl.class);

	@Resource
	private IArticleComplaintDao articleComplaintDao;

	@Override
	public String addArticleComplaint(ArticleComplaintDto articleComplaintDto) throws TsfaServiceException {
		logger.debug("addArticleComplaint(AddArticleComplaint addArticleComplaint={}) - start", articleComplaintDto);

		AssertUtils.notNull(articleComplaintDto);
		try {
			ArticleComplaint articleComplaint = new ArticleComplaint();
			// add数据录入
			articleComplaint.setCode(articleComplaintDto.getCode());
			articleComplaint.setArticleCode(articleComplaintDto.getArticleCode());
			articleComplaint.setTitle(articleComplaintDto.getTitle());
			articleComplaint.setComplaintContent(articleComplaintDto.getComplaintContent());
			articleComplaint.setComplaintEvidence(articleComplaintDto.getComplaintEvidence());
			articleComplaint.setComplaintUsername(articleComplaintDto.getComplaintUsername());
			articleComplaint.setComplaintMobile(articleComplaintDto.getComplaintMobile());
			articleComplaint.setAuditorCode(articleComplaintDto.getAuditorCode());
			articleComplaint.setAuditorName(articleComplaintDto.getAuditorName());
			articleComplaint.setAuditorDate(articleComplaintDto.getAuditorDate());
			articleComplaint.setAuditorStatus(articleComplaintDto.getAuditorStatus());
			articleComplaint.setAuditorResult(articleComplaintDto.getAuditorResult());
			articleComplaint.setAuditorContent(articleComplaintDto.getAuditorContent());
			articleComplaint.setRemark(articleComplaintDto.getRemark());
			articleComplaint.setCreateId(articleComplaintDto.getCreateId());
			articleComplaint.setCreateDate(articleComplaintDto.getCreateDate());
			articleComplaint.setUpdateId(articleComplaintDto.getUpdateId());

			articleComplaint.setCode(GUID.getPreUUID());
			articleComplaintDao.insertSelective(articleComplaint);
			logger.debug("addArticleComplaint(ArticleComplaintDto) - end - return");
			return articleComplaint.getCode();
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增文章投诉记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleComplaint.ARTICLE_COMPLAINT_ADD_ERROR, "新增文章投诉记录信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询文章投诉记录信息
	 *
	 * @param findArticleComplaintPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<ArticleComplaintDto> findArticleComplaints(FindArticleComplaintPage findArticleComplaintPage) throws TsfaServiceException {
		AssertUtils.notNull(findArticleComplaintPage);
		List<ArticleComplaintDto> returnList = null;
		try {
			returnList = articleComplaintDao.findArticleComplaints(findArticleComplaintPage);
		} catch (Exception e) {
			logger.error("查找文章投诉记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleComplaint.ARTICLE_COMPLAINT_NOT_EXIST_ERROR, "文章投诉记录信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateArticleComplaint(ArticleComplaintDto articleComplaintDto) throws TsfaServiceException {
		logger.debug("updateArticleComplaint(ArticleComplaintDto articleComplaintDto={}) - start", articleComplaintDto);

		AssertUtils.notNull(articleComplaintDto);
		AssertUtils.notNullAndEmpty(articleComplaintDto.getCode(), "Code不能为空");
		try {
			ArticleComplaint articleComplaint = new ArticleComplaint();
			// update数据录入
			articleComplaint.setCode(articleComplaintDto.getCode());
			articleComplaint.setArticleCode(articleComplaintDto.getArticleCode());
			articleComplaint.setTitle(articleComplaintDto.getTitle());
			articleComplaint.setComplaintContent(articleComplaintDto.getComplaintContent());
			articleComplaint.setComplaintEvidence(articleComplaintDto.getComplaintEvidence());
			articleComplaint.setComplaintUsername(articleComplaintDto.getComplaintUsername());
			articleComplaint.setComplaintMobile(articleComplaintDto.getComplaintMobile());
			articleComplaint.setAuditorCode(articleComplaintDto.getAuditorCode());
			articleComplaint.setAuditorName(articleComplaintDto.getAuditorName());
			articleComplaint.setAuditorDate(articleComplaintDto.getAuditorDate());
			articleComplaint.setAuditorStatus(articleComplaintDto.getAuditorStatus());
			articleComplaint.setAuditorResult(articleComplaintDto.getAuditorResult());
			articleComplaint.setAuditorContent(articleComplaintDto.getAuditorContent());
			articleComplaint.setRemark(articleComplaintDto.getRemark());
			articleComplaint.setCreateId(articleComplaintDto.getCreateId());
			articleComplaint.setCreateDate(articleComplaintDto.getCreateDate());
			articleComplaint.setUpdateId(articleComplaintDto.getUpdateId());
			AssertUtils.notUpdateMoreThanOne(articleComplaintDao.updateByPrimaryKeySelective(articleComplaint));
			logger.debug("updateArticleComplaint(ArticleComplaintDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("文章投诉记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleComplaint.ARTICLE_COMPLAINT_UPDATE_ERROR, "文章投诉记录信息更新信息错误！", e);
		}
	}

	@Override
	public ArticleComplaintDto findArticleComplaint(ArticleComplaintDto articleComplaintDto) throws TsfaServiceException {
		logger.debug("findArticleComplaint(FindArticleComplaint findArticleComplaint={}) - start", articleComplaintDto);

		AssertUtils.notNull(articleComplaintDto);
		AssertUtils.notAllNull(articleComplaintDto.getCode(), "Code不能为空");
		try {
			ArticleComplaint articleComplaint = articleComplaintDao.selectByPrimaryKey(articleComplaintDto.getCode());
			if (articleComplaint == null) {
				return null;
				// throw new TsfaServiceException(ErrorCodeArticleComplaint.ARTICLE_COMPLAINT_NOT_EXIST_ERROR,"文章投诉记录信息不存在");
			}
			ArticleComplaintDto findArticleComplaintReturn = new ArticleComplaintDto();
			// find数据录入
			findArticleComplaintReturn.setCode(articleComplaint.getCode());
			findArticleComplaintReturn.setArticleCode(articleComplaint.getArticleCode());
			findArticleComplaintReturn.setTitle(articleComplaint.getTitle());
			findArticleComplaintReturn.setComplaintContent(articleComplaint.getComplaintContent());
			findArticleComplaintReturn.setComplaintEvidence(articleComplaint.getComplaintEvidence());
			findArticleComplaintReturn.setComplaintUsername(articleComplaint.getComplaintUsername());
			findArticleComplaintReturn.setComplaintMobile(articleComplaint.getComplaintMobile());
			findArticleComplaintReturn.setAuditorCode(articleComplaint.getAuditorCode());
			findArticleComplaintReturn.setAuditorName(articleComplaint.getAuditorName());
			findArticleComplaintReturn.setAuditorDate(articleComplaint.getAuditorDate());
			findArticleComplaintReturn.setAuditorStatus(articleComplaint.getAuditorStatus());
			findArticleComplaintReturn.setAuditorResult(articleComplaint.getAuditorResult());
			findArticleComplaintReturn.setAuditorContent(articleComplaint.getAuditorContent());
			findArticleComplaintReturn.setRemark(articleComplaint.getRemark());
			findArticleComplaintReturn.setCreateId(articleComplaint.getCreateId());
			findArticleComplaintReturn.setCreateDate(articleComplaint.getCreateDate());
			findArticleComplaintReturn.setUpdateId(articleComplaint.getUpdateId());

			logger.debug("findArticleComplaint(ArticleComplaintDto) - end - return value={}", findArticleComplaintReturn);
			return findArticleComplaintReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找文章投诉记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleComplaint.ARTICLE_COMPLAINT_FIND_ERROR, "查找文章投诉记录信息信息错误！", e);
		}

	}

	@Override
	public Page<ArticleComplaintDto> findArticleComplaintPage(FindArticleComplaintPage findArticleComplaintPage) throws TsfaServiceException {
		logger.debug("findArticleComplaintPage(FindArticleComplaintPage findArticleComplaintPage={}) - start", findArticleComplaintPage);

		AssertUtils.notNull(findArticleComplaintPage);
		List<ArticleComplaintDto> returnList = null;
		int count = 0;
		try {
			returnList = articleComplaintDao.findArticleComplaintPage(findArticleComplaintPage);
			count = articleComplaintDao.findArticleComplaintPageCount(findArticleComplaintPage);
		} catch (Exception e) {
			logger.error("文章投诉记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeArticleComplaint.ARTICLE_COMPLAINT_FIND_PAGE_ERROR, "文章投诉记录信息不存在错误.！", e);
		}
		Page<ArticleComplaintDto> returnPage = new Page<ArticleComplaintDto>(returnList, count, findArticleComplaintPage);

		logger.debug("findArticleComplaintPage(FindArticleComplaintPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(articleComplaintDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("文章投诉记录信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleComplaint.ARTICLE_COMPLAINT_UPDATE_ERROR, "文章投诉记录信息刪除信息错误！", e);
		}
	}

}
