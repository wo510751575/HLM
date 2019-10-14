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

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.rw.constant.ErrorCodeArticleShare;
import com.ye.business.rw.dao.IArticleShareDao;
import com.ye.business.rw.domain.ArticleShare;
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.ArticleShareDto;
import com.ye.business.rw.dto.FindArticlePage;
import com.ye.business.rw.dto.FindArticleSharePage;
import com.ye.business.rw.service.IArticleService;
import com.ye.business.rw.service.IArticleShareService;

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
public class ArticleShareServiceImpl implements IArticleShareService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ArticleShareServiceImpl.class);

	@Resource
	private IArticleShareDao articleShareDao;
	@Autowired
	private IArticleService articleService;

	@Override
	public String addArticleShare(ArticleShareDto articleShareDto) throws TsfaServiceException {
		logger.debug("addArticleShare(AddArticleShare addArticleShare={}) - start", articleShareDto);

		AssertUtils.notNull(articleShareDto);
		try {
			ArticleShare articleShare = new ArticleShare();
			// add数据录入
			articleShare.setCode(articleShareDto.getCode());
			articleShare.setMemberNoGuid(articleShareDto.getMemberNoGuid());
			articleShare.setMemberNameGuid(articleShareDto.getMemberNameGuid());
			articleShare.setShopNo(articleShareDto.getShopNo());
			articleShare.setShopName(articleShareDto.getShopName());
			articleShare.setMerchantNo(articleShareDto.getMerchantNo());
			articleShare.setMerchantName(articleShareDto.getMerchantName());
			articleShare.setArticleCode(articleShareDto.getArticleCode());
			articleShare.setMemberNoGuidView(articleShareDto.getMemberNoGuidView());
			articleShare.setMemberNameGuidView(articleShareDto.getMemberNameGuidView());
			articleShare.setShopNoView(articleShareDto.getShopNoView());
			articleShare.setShopNameView(articleShareDto.getShopNameView());
			articleShare.setMerchantNoView(articleShareDto.getMerchantNoView());
			articleShare.setMerchantNameView(articleShareDto.getMerchantNameView());
			articleShare.setCreateId(articleShareDto.getCreateId());
			articleShare.setCretaeName(articleShareDto.getCretaeName());
			articleShare.setCreateDate(articleShareDto.getCreateDate());
			articleShare.setRemark(articleShareDto.getRemark());
			articleShare.setRemark2(articleShareDto.getRemark2());
			articleShare.setRemark3(articleShareDto.getRemark3());
			articleShare.setRemark4(articleShareDto.getRemark4());
			articleShare.setUpdateTime(articleShareDto.getUpdateTime());
			articleShare.setMainImage(articleShareDto.getMainImage());
			articleShare.setTitle(articleShareDto.getTitle());

			articleShare.setCode(GUID.getPreUUID());
			articleShareDao.insertSelective(articleShare);
			logger.debug("addArticleShare(ArticleShareDto) - end - return");
			return articleShare.getCode();
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增文章分享记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleShare.ARTICLE_SHARE_ADD_ERROR, "新增文章分享记录信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询文章分享记录信息
	 *
	 * @param findArticleSharePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<ArticleShareDto> findArticleShares(FindArticleSharePage findArticleSharePage) throws TsfaServiceException {
		AssertUtils.notNull(findArticleSharePage);
		List<ArticleShareDto> returnList = null;
		try {
			returnList = articleShareDao.findArticleShares(findArticleSharePage);
		} catch (Exception e) {
			logger.error("查找文章分享记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleShare.ARTICLE_SHARE_NOT_EXIST_ERROR, "文章分享记录信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateArticleShare(ArticleShareDto articleShareDto) throws TsfaServiceException {
		logger.debug("updateArticleShare(ArticleShareDto articleShareDto={}) - start", articleShareDto);

		AssertUtils.notNull(articleShareDto);
		AssertUtils.notNullAndEmpty(articleShareDto.getCode(), "Code不能为空");
		try {
			ArticleShare articleShare = new ArticleShare();
			// update数据录入
			articleShare.setCode(articleShareDto.getCode());
			articleShare.setMemberNoGuid(articleShareDto.getMemberNoGuid());
			articleShare.setMemberNameGuid(articleShareDto.getMemberNameGuid());
			articleShare.setShopNo(articleShareDto.getShopNo());
			articleShare.setShopName(articleShareDto.getShopName());
			articleShare.setMerchantNo(articleShareDto.getMerchantNo());
			articleShare.setMerchantName(articleShareDto.getMerchantName());
			articleShare.setArticleCode(articleShareDto.getArticleCode());
			articleShare.setMemberNoGuidView(articleShareDto.getMemberNoGuidView());
			articleShare.setMemberNameGuidView(articleShareDto.getMemberNameGuidView());
			articleShare.setShopNoView(articleShareDto.getShopNoView());
			articleShare.setShopNameView(articleShareDto.getShopNameView());
			articleShare.setMerchantNoView(articleShareDto.getMerchantNoView());
			articleShare.setMerchantNameView(articleShareDto.getMerchantNameView());
			articleShare.setCreateId(articleShareDto.getCreateId());
			articleShare.setCretaeName(articleShareDto.getCretaeName());
			articleShare.setCreateDate(articleShareDto.getCreateDate());
			articleShare.setRemark(articleShareDto.getRemark());
			articleShare.setRemark2(articleShareDto.getRemark2());
			articleShare.setRemark3(articleShareDto.getRemark3());
			articleShare.setRemark4(articleShareDto.getRemark4());
			articleShare.setUpdateTime(articleShareDto.getUpdateTime());
			articleShare.setMainImage(articleShareDto.getMainImage());
			articleShare.setTitle(articleShareDto.getTitle());
			AssertUtils.notUpdateMoreThanOne(articleShareDao.updateByPrimaryKeySelective(articleShare));
			logger.debug("updateArticleShare(ArticleShareDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("文章分享记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleShare.ARTICLE_SHARE_UPDATE_ERROR, "文章分享记录信息更新信息错误！", e);
		}
	}

	@Override
	public ArticleShareDto findArticleShare(ArticleShareDto articleShareDto) throws TsfaServiceException {
		logger.debug("findArticleShare(FindArticleShare findArticleShare={}) - start", articleShareDto);

		AssertUtils.notNull(articleShareDto);
		AssertUtils.notAllNull(articleShareDto.getCode(), "Code不能为空");
		try {
			ArticleShare articleShare = articleShareDao.selectByPrimaryKey(articleShareDto.getCode());
			if (articleShare == null) {
				return null;
				// throw new TsfaServiceException(ErrorCodeArticleShare.ARTICLE_SHARE_NOT_EXIST_ERROR,"文章分享记录信息不存在");
			}
			ArticleShareDto findArticleShareReturn = new ArticleShareDto();
			// find数据录入
			findArticleShareReturn.setCode(articleShare.getCode());
			findArticleShareReturn.setMemberNoGuid(articleShare.getMemberNoGuid());
			findArticleShareReturn.setMemberNameGuid(articleShare.getMemberNameGuid());
			findArticleShareReturn.setShopNo(articleShare.getShopNo());
			findArticleShareReturn.setShopName(articleShare.getShopName());
			findArticleShareReturn.setMerchantNo(articleShare.getMerchantNo());
			findArticleShareReturn.setMerchantName(articleShare.getMerchantName());
			findArticleShareReturn.setArticleCode(articleShare.getArticleCode());
			findArticleShareReturn.setMemberNoGuidView(articleShare.getMemberNoGuidView());
			findArticleShareReturn.setMemberNameGuidView(articleShare.getMemberNameGuidView());
			findArticleShareReturn.setShopNoView(articleShare.getShopNoView());
			findArticleShareReturn.setShopNameView(articleShare.getShopNameView());
			findArticleShareReturn.setMerchantNoView(articleShare.getMerchantNoView());
			findArticleShareReturn.setMerchantNameView(articleShare.getMerchantNameView());
			findArticleShareReturn.setCreateId(articleShare.getCreateId());
			findArticleShareReturn.setCretaeName(articleShare.getCretaeName());
			findArticleShareReturn.setCreateDate(articleShare.getCreateDate());
			findArticleShareReturn.setRemark(articleShare.getRemark());
			findArticleShareReturn.setRemark2(articleShare.getRemark2());
			findArticleShareReturn.setRemark3(articleShare.getRemark3());
			findArticleShareReturn.setRemark4(articleShare.getRemark4());
			findArticleShareReturn.setUpdateTime(articleShare.getUpdateTime());
			findArticleShareReturn.setMainImage(articleShare.getMainImage());
			findArticleShareReturn.setTitle(articleShare.getTitle());

			logger.debug("findArticleShare(ArticleShareDto) - end - return value={}", findArticleShareReturn);
			return findArticleShareReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找文章分享记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleShare.ARTICLE_SHARE_FIND_ERROR, "查找文章分享记录信息信息错误！", e);
		}

	}

	@Override
	public Page<ArticleShareDto> findArticleSharePage(FindArticleSharePage findArticleSharePage) throws TsfaServiceException {
		logger.debug("findArticleSharePage(FindArticleSharePage findArticleSharePage={}) - start", findArticleSharePage);

		AssertUtils.notNull(findArticleSharePage);
		List<ArticleShareDto> returnList = null;
		int count = 0;
		try {
			returnList = articleShareDao.findArticleSharePage(findArticleSharePage);
			count = articleShareDao.findArticleSharePageCount(findArticleSharePage);
		} catch (Exception e) {
			logger.error("文章分享记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeArticleShare.ARTICLE_SHARE_FIND_PAGE_ERROR, "文章分享记录信息不存在错误.！", e);
		}
		Page<ArticleShareDto> returnPage = new Page<ArticleShareDto>(returnList, count, findArticleSharePage);

		logger.debug("findArticleSharePage(FindArticleSharePage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(articleShareDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("文章分享记录信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleShare.ARTICLE_SHARE_UPDATE_ERROR, "文章分享记录信息刪除信息错误！", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ArticleShareDto> findArticleSharePageForGroup(FindArticleSharePage findArticleSharePage) throws TsfaServiceException {
		logger.debug("findArticleSharePageForGroup(FindArticleSharePage findArticleSharePage={}) - start", findArticleSharePage);

		AssertUtils.notNull(findArticleSharePage);
		List<ArticleShareDto> returnList = null;
		long count = 0;
		try {

			List<List<?>> data = articleShareDao.findArticleSharePageForGroup(findArticleSharePage);
			if (CollectionUtils.isNotEmpty(data)) {
				returnList = (List<ArticleShareDto>) data.get(0);
				count = ((List<Long>) data.get(1)).get(0);
			}

			// 查询出相关联的文章信息
			if (CollectionUtils.isNotEmpty(returnList)) {
				List<String> articleCodeList = returnList.stream().map(ArticleShareDto::getArticleCode).collect(Collectors.toList());

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
			throw new TsfaServiceException(ErrorCodeArticleShare.ARTICLE_SHARE_FIND_PAGE_ERROR, "文章分享记录信息不存在错误.！", e);
		}
		Page<ArticleShareDto> returnPage = new Page<ArticleShareDto>(returnList, count, findArticleSharePage);

		logger.debug("findArticleSharePageForGroup(FindArticleSharePage) - end - return value={}", returnPage);
		return returnPage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ArticleShareDto> findArticleSharePageForView(FindArticleSharePage findArticleSharePage) throws TsfaServiceException {
		logger.debug("findArticleSharePageForView(FindArticleSharePage findArticleSharePage={}) - start", findArticleSharePage);

		AssertUtils.notNull(findArticleSharePage);
		List<ArticleShareDto> returnList = null;
		long count = 0;
		try {

			List<List<?>> data = articleShareDao.findArticleSharePageForView(findArticleSharePage);
			if (CollectionUtils.isNotEmpty(data)) {
				returnList = (List<ArticleShareDto>) data.get(0);
				count = ((List<Long>) data.get(1)).get(0);
			}

		} catch (Exception e) {
			logger.error("文章分享记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeArticleShare.ARTICLE_SHARE_FIND_PAGE_ERROR, "文章分享记录信息不存在错误.！", e);
		}
		Page<ArticleShareDto> returnPage = new Page<ArticleShareDto>(returnList, count, findArticleSharePage);

		logger.debug("findArticleSharePageForView(FindArticleSharePage) - end - return value={}", returnPage);
		return returnPage;
	}
	
	@Override
	public List<ArticleShareDto> findArticleShareForGroupList(ArticleShareDto record) throws TsfaServiceException {
		FindArticleSharePage findArticleSharePage = new FindArticleSharePage();
		findArticleSharePage.setParam(record);
		return articleShareDao.findArticleShareForGroupList(findArticleSharePage);
	}
	
	@SuppressWarnings("unchecked")
	public Page<ArticleShareDto> findArticleShareInfoForGroupList(FindArticleSharePage findArticleSharePage) throws TsfaServiceException {
//		logger.debug("findArticleShareInfoForGroupList(FindArticleSharePage findArticleSharePage={}) - start", findArticleSharePage);

		AssertUtils.notNull(findArticleSharePage);
		List<ArticleShareDto> returnList = null;
		long count = 0;
		try {
			List<List<?>> data = articleShareDao.findArticleShareInfoForGroupList(findArticleSharePage);
			if (CollectionUtils.isNotEmpty(data)) {
				returnList = (List<ArticleShareDto>) data.get(0);
				count = ((List<Long>) data.get(1)).get(0);
			}

		} catch (Exception e) {
			logger.error("文章分享记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeArticleShare.ARTICLE_SHARE_FIND_PAGE_ERROR, "文章分享记录信息不存在错误.！", e);
		}
		Page<ArticleShareDto> returnPage = new Page<ArticleShareDto>(returnList, count, findArticleSharePage);

		logger.debug("findArticleShareInfoForGroupList(FindArticleSharePage) - end - return value={}", returnPage);
		return returnPage;
	}
}
