package com.ye.business.rw.service.impl;

/**
 * Copyright &copy; 2018-2021  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.rw.constant.ErrorCodeArticleView;
import com.ye.business.rw.dao.IArticleViewDao;
import com.ye.business.rw.domain.ArticleView;
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.ArticleViewDto;
import com.ye.business.rw.dto.FindArticlePage;
import com.ye.business.rw.dto.FindArticleViewPage;
import com.ye.business.rw.service.IArticleService;
import com.ye.business.rw.service.IArticleViewService;

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
public class ArticleViewServiceImpl implements IArticleViewService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ArticleViewServiceImpl.class);

	@Resource
	private IArticleViewDao articleViewDao;
	@Autowired
	private IArticleService articleService;

	@Override
	public String addArticleView(ArticleViewDto articleViewDto) throws TsfaServiceException {
		logger.debug("addArticleView(AddArticleView addArticleView={}) - start", articleViewDto);

		AssertUtils.notNull(articleViewDto);
		try {
			ArticleView articleView = new ArticleView();
			// add数据录入
			articleView.setCode(articleViewDto.getCode());
			articleView.setMemberNoGuid(articleViewDto.getMemberNoGuid());
			articleView.setMemberNameGuid(articleViewDto.getMemberNameGuid());
			articleView.setShopNo(articleViewDto.getShopNo());
			articleView.setShopName(articleViewDto.getShopName());
			articleView.setMerchantNo(articleViewDto.getMerchantNo());
			articleView.setMerchantName(articleViewDto.getMerchantName());
			articleView.setArticleCode(articleViewDto.getArticleCode());
			articleView.setMemberNoGuidView(articleViewDto.getMemberNoGuidView());
			articleView.setMemberNameGuidView(articleViewDto.getMemberNameGuidView());
			articleView.setShopNoView(articleViewDto.getShopNoView());
			articleView.setShopNameView(articleViewDto.getShopNameView());
			articleView.setMerchantNoView(articleViewDto.getMerchantNoView());
			articleView.setMerchantNameView(articleViewDto.getMerchantNameView());
			articleView.setCreateId(articleViewDto.getCreateId());
			articleView.setCretaeName(articleViewDto.getCretaeName());
			articleView.setCreateDate(articleViewDto.getCreateDate());
			articleView.setRemark(articleViewDto.getRemark());
			articleView.setRemark2(articleViewDto.getRemark2());
			articleView.setRemark3(articleViewDto.getRemark3());
			articleView.setRemark4(articleViewDto.getRemark4());
			articleView.setUpdateTime(articleViewDto.getUpdateTime());
			articleView.setMainImage(articleViewDto.getMainImage());
			articleView.setTitle(articleViewDto.getTitle());

			articleView.setCode(GUID.getPreUUID());
			articleViewDao.insertSelective(articleView);
			logger.debug("addArticleView(ArticleViewDto) - end - return");
			return articleView.getCode();
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增文章分享记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleView.ARTICLE_VIEW_ADD_ERROR, "新增文章分享记录信息错误！", e);
		}
	}

	@Override
	public String addArticleViewByOnly(ArticleViewDto articleViewDto) throws TsfaServiceException {

		ArticleViewDto param = new ArticleViewDto();
		param.setArticleCode(articleViewDto.getArticleCode());
		param.setMemberNoGuid(articleViewDto.getMemberNoGuid());
		param.setMerchantNo(articleViewDto.getMerchantNo());
		param.setRemark(articleViewDto.getRemark());

		FindArticleViewPage findArticleViewPage = new FindArticleViewPage();
		findArticleViewPage.setParam(param);

		int count = articleViewDao.findArticleViewPageCount(findArticleViewPage);
		if (count > 0) {
			return null;
		}
		
		return addArticleView(articleViewDto);
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询文章分享记录信息
	 *
	 * @param findArticleViewPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<ArticleViewDto> findArticleViews(FindArticleViewPage findArticleViewPage) throws TsfaServiceException {
		AssertUtils.notNull(findArticleViewPage);
		List<ArticleViewDto> returnList = null;
		try {
			returnList = articleViewDao.findArticleViews(findArticleViewPage);
		} catch (Exception e) {
			logger.error("查找文章分享记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleView.ARTICLE_VIEW_NOT_EXIST_ERROR, "文章分享记录信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateArticleView(ArticleViewDto articleViewDto) throws TsfaServiceException {
		logger.debug("updateArticleView(ArticleViewDto articleViewDto={}) - start", articleViewDto);

		AssertUtils.notNull(articleViewDto);
		AssertUtils.notNullAndEmpty(articleViewDto.getCode(), "Code不能为空");
		try {
			ArticleView articleView = new ArticleView();
			// update数据录入
			articleView.setCode(articleViewDto.getCode());
			articleView.setMemberNoGuid(articleViewDto.getMemberNoGuid());
			articleView.setMemberNameGuid(articleViewDto.getMemberNameGuid());
			articleView.setShopNo(articleViewDto.getShopNo());
			articleView.setShopName(articleViewDto.getShopName());
			articleView.setMerchantNo(articleViewDto.getMerchantNo());
			articleView.setMerchantName(articleViewDto.getMerchantName());
			articleView.setArticleCode(articleViewDto.getArticleCode());
			articleView.setMemberNoGuidView(articleViewDto.getMemberNoGuidView());
			articleView.setMemberNameGuidView(articleViewDto.getMemberNameGuidView());
			articleView.setShopNoView(articleViewDto.getShopNoView());
			articleView.setShopNameView(articleViewDto.getShopNameView());
			articleView.setMerchantNoView(articleViewDto.getMerchantNoView());
			articleView.setMerchantNameView(articleViewDto.getMerchantNameView());
			articleView.setCreateId(articleViewDto.getCreateId());
			articleView.setCretaeName(articleViewDto.getCretaeName());
			articleView.setCreateDate(articleViewDto.getCreateDate());
			articleView.setRemark(articleViewDto.getRemark());
			articleView.setRemark2(articleViewDto.getRemark2());
			articleView.setRemark3(articleViewDto.getRemark3());
			articleView.setRemark4(articleViewDto.getRemark4());
			articleView.setUpdateTime(articleViewDto.getUpdateTime());
			articleView.setUpdateTime(articleViewDto.getUpdateTime());
			articleView.setMainImage(articleViewDto.getMainImage());
			AssertUtils.notUpdateMoreThanOne(articleViewDao.updateByPrimaryKeySelective(articleView));
			logger.debug("updateArticleView(ArticleViewDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("文章分享记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleView.ARTICLE_VIEW_UPDATE_ERROR, "文章分享记录信息更新信息错误！", e);
		}
	}

	@Override
	public ArticleViewDto findArticleView(ArticleViewDto articleViewDto) throws TsfaServiceException {
		logger.debug("findArticleView(FindArticleView findArticleView={}) - start", articleViewDto);

		AssertUtils.notNull(articleViewDto);
		AssertUtils.notAllNull(articleViewDto.getCode(), "Code不能为空");
		try {
			ArticleView articleView = articleViewDao.selectByPrimaryKey(articleViewDto.getCode());
			if (articleView == null) {
				return null;
				// throw new TsfaServiceException(ErrorCodeArticleView.ARTICLE_VIEW_NOT_EXIST_ERROR,"文章分享记录信息不存在");
			}
			ArticleViewDto findArticleViewReturn = new ArticleViewDto();
			// find数据录入
			findArticleViewReturn.setCode(articleView.getCode());
			findArticleViewReturn.setMemberNoGuid(articleView.getMemberNoGuid());
			findArticleViewReturn.setMemberNameGuid(articleView.getMemberNameGuid());
			findArticleViewReturn.setShopNo(articleView.getShopNo());
			findArticleViewReturn.setShopName(articleView.getShopName());
			findArticleViewReturn.setMerchantNo(articleView.getMerchantNo());
			findArticleViewReturn.setMerchantName(articleView.getMerchantName());
			findArticleViewReturn.setArticleCode(articleView.getArticleCode());
			findArticleViewReturn.setMemberNoGuidView(articleView.getMemberNoGuidView());
			findArticleViewReturn.setMemberNameGuidView(articleView.getMemberNameGuidView());
			findArticleViewReturn.setShopNoView(articleView.getShopNoView());
			findArticleViewReturn.setShopNameView(articleView.getShopNameView());
			findArticleViewReturn.setMerchantNoView(articleView.getMerchantNoView());
			findArticleViewReturn.setMerchantNameView(articleView.getMerchantNameView());
			findArticleViewReturn.setCreateId(articleView.getCreateId());
			findArticleViewReturn.setCretaeName(articleView.getCretaeName());
			findArticleViewReturn.setCreateDate(articleView.getCreateDate());
			findArticleViewReturn.setRemark(articleView.getRemark());
			findArticleViewReturn.setRemark2(articleView.getRemark2());
			findArticleViewReturn.setRemark3(articleView.getRemark3());
			findArticleViewReturn.setRemark4(articleView.getRemark4());
			findArticleViewReturn.setUpdateTime(articleView.getUpdateTime());
			findArticleViewReturn.setMainImage(articleView.getMainImage());
			findArticleViewReturn.setTitle(articleView.getTitle());

			logger.debug("findArticleView(ArticleViewDto) - end - return value={}", findArticleViewReturn);
			return findArticleViewReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找文章分享记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleView.ARTICLE_VIEW_FIND_ERROR, "查找文章分享记录信息信息错误！", e);
		}

	}

	@Override
	public Page<ArticleViewDto> findArticleViewPage(FindArticleViewPage findArticleViewPage) throws TsfaServiceException {
		logger.debug("findArticleViewPage(FindArticleViewPage findArticleViewPage={}) - start", findArticleViewPage);

		AssertUtils.notNull(findArticleViewPage);
		List<ArticleViewDto> returnList = null;
		int count = 0;
		try {
			returnList = articleViewDao.findArticleViewPage(findArticleViewPage);
			count = articleViewDao.findArticleViewPageCount(findArticleViewPage);
		} catch (Exception e) {
			logger.error("文章分享记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeArticleView.ARTICLE_VIEW_FIND_PAGE_ERROR, "文章分享记录信息不存在错误.！", e);
		}
		Page<ArticleViewDto> returnPage = new Page<ArticleViewDto>(returnList, count, findArticleViewPage);

		logger.debug("findArticleViewPage(FindArticleViewPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(articleViewDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("文章分享记录信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleView.ARTICLE_VIEW_UPDATE_ERROR, "文章分享记录信息刪除信息错误！", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ArticleViewDto> findArticleViewPageForGroup(FindArticleViewPage findArticleViewPage) throws TsfaServiceException {
		logger.debug("findArticleViewPageForGroup(FindArticleViewPage findArticleViewPage={}) - start", findArticleViewPage);

		AssertUtils.notNull(findArticleViewPage);
		List<ArticleViewDto> returnList = null;
		long count = 0;
		try {

			List<List<?>> data = articleViewDao.findArticleViewPageForGroup(findArticleViewPage);
			if (CollectionUtils.isNotEmpty(data)) {
				returnList = (List<ArticleViewDto>) data.get(0);
				count = ((List<Long>) data.get(1)).get(0);
			}

			// 查询出相关联的文章信息
			if (CollectionUtils.isNotEmpty(returnList)) {
				List<String> articleCodeList = returnList.stream().map(ArticleViewDto::getArticleCode).collect(Collectors.toList());

				FindArticlePage findArticlePage = new FindArticlePage();
				ArticleDto param = new ArticleDto();
				param.setCodeList(articleCodeList);
				findArticlePage.setParam(param);
				List<ArticleDto> rs = articleService.findArticles(findArticlePage);
				Map<String, ArticleDto> rsMap = rs.stream().collect(Collectors.toMap(ArticleDto::getCode, ArticleDto -> ArticleDto));
				returnList.forEach(action -> {
					if (rsMap.containsKey(action.getArticleCode())) {
						action.setArticle(rsMap.get(action.getArticleCode()));
					}
				});
			}

		} catch (Exception e) {
			logger.error("文章分享记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeArticleView.ARTICLE_VIEW_FIND_PAGE_ERROR, "文章分享记录信息不存在错误.！", e);
		}
		Page<ArticleViewDto> returnPage = new Page<ArticleViewDto>(returnList, count, findArticleViewPage);

		logger.debug("findArticleViewPageForGroup(FindArticleViewPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ArticleViewDto> findArticleViewPageForView(FindArticleViewPage findArticleViewPage) throws TsfaServiceException {
		logger.debug("findArticleViewPageForView(FindArticleViewPage findArticleViewPage={}) - start", findArticleViewPage);

		AssertUtils.notNull(findArticleViewPage);
		List<ArticleViewDto> returnList = null;
		long count = 0;
		try {

			List<List<?>> data = articleViewDao.findArticleViewPageForView(findArticleViewPage);
			if (CollectionUtils.isNotEmpty(data)) {
				returnList = (List<ArticleViewDto>) data.get(0);
				count = ((List<Long>) data.get(1)).get(0);
			}

		} catch (Exception e) {
			logger.error("文章分享记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeArticleView.ARTICLE_VIEW_FIND_PAGE_ERROR, "文章分享记录信息不存在错误.！", e);
		}
		Page<ArticleViewDto> returnPage = new Page<ArticleViewDto>(returnList, count, findArticleViewPage);

		logger.debug("findArticleViewPageForView(FindArticleViewPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public List<ArticleViewDto> findArticleViewForGroupList(ArticleViewDto record) throws TsfaServiceException {
		FindArticleViewPage findArticleViewPage = new FindArticleViewPage();
		findArticleViewPage.setParam(record);
		return articleViewDao.findArticleViewForGroupList(findArticleViewPage);
	}

	@SuppressWarnings("unchecked")
	public Page<ArticleViewDto> findArticleViewInfoForGroupList(FindArticleViewPage findArticleViewPage) throws TsfaServiceException {
//		logger.debug("findArticleViewInfoForGroupList(FindArticleViewPage findArticleViewPage={}) - start", findArticleViewPage);

		AssertUtils.notNull(findArticleViewPage);
		List<ArticleViewDto> returnList = Lists.newArrayList();
		long count = 0;
		try {
			List<List<?>> data = articleViewDao.findArticleViewInfoForGroupList(findArticleViewPage);
			if (CollectionUtils.isNotEmpty(data)) {
				returnList = (List<ArticleViewDto>) data.get(0);
				count = ((List<Long>) data.get(1)).get(0);
			}

		} catch (Exception e) {
			logger.error("文章分享记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeArticleView.ARTICLE_VIEW_FIND_PAGE_ERROR, "文章分享记录信息不存在错误.！", e);
		}
		Page<ArticleViewDto> returnPage = new Page<ArticleViewDto>(returnList, count, findArticleViewPage);

		logger.debug("findArticleViewInfoForGroupList(FindArticleViewPage) - end - return value={}", returnPage);
		return returnPage;
	}
}
