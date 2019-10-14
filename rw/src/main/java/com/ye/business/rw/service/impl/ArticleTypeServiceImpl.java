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
import com.ye.business.rw.constant.ErrorCodeArticleType;
import com.ye.business.rw.dao.IArticleTypeDao;
import com.ye.business.rw.domain.ArticleType;
import com.ye.business.rw.dto.ArticleTypeDto;
import com.ye.business.rw.dto.FindArticleTypePage;
import com.ye.business.rw.service.IArticleTypeService;

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
public class ArticleTypeServiceImpl implements IArticleTypeService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ArticleTypeServiceImpl.class);

	@Resource
	private IArticleTypeDao articleTypeDao;

	@Override
	public String addArticleType(ArticleTypeDto articleTypeDto) throws TsfaServiceException {
		logger.debug("addArticleType(AddArticleType addArticleType={}) - start", articleTypeDto);

		AssertUtils.notNull(articleTypeDto);
		try {
			ArticleType articleType = new ArticleType();
			// add数据录入
			articleType.setCode(GUID.getPreUUID());
			articleType.setMerchantNo(articleTypeDto.getMerchantNo());
			articleType.setTypeName(articleTypeDto.getTypeName());
			articleType.setStatus(articleTypeDto.getStatus());
			articleType.setSeq(articleTypeDto.getSeq());
			articleType.setCreateId(articleTypeDto.getCreateId());
			articleType.setCreateDate(articleTypeDto.getCreateDate());
			articleType.setRemark(articleTypeDto.getRemark());
			articleType.setRemark2(articleTypeDto.getRemark2());
			articleType.setRemark3(articleTypeDto.getRemark3());
			articleType.setRemark4(articleTypeDto.getRemark4());
			articleType.setUpdateTime(articleTypeDto.getUpdateTime());
			articleTypeDao.insertSelective(articleType);
			logger.debug("addArticleType(ArticleTypeDto) - end - return");

			return articleType.getCode();

		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增文章类型信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleType.ARTICLE_TYPE_ADD_ERROR, "新增文章类型信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询文章类型信息
	 *
	 * @param findArticleTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<ArticleTypeDto> findArticleTypes(FindArticleTypePage findArticleTypePage) throws TsfaServiceException {
		AssertUtils.notNull(findArticleTypePage);
		List<ArticleTypeDto> returnList = null;
		try {
			returnList = articleTypeDao.findArticleTypes(findArticleTypePage);
		} catch (Exception e) {
			logger.error("查找文章类型信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleType.ARTICLE_TYPE_NOT_EXIST_ERROR, "文章类型信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateArticleType(ArticleTypeDto articleTypeDto) throws TsfaServiceException {
		logger.debug("updateArticleType(ArticleTypeDto articleTypeDto={}) - start", articleTypeDto);

		AssertUtils.notNull(articleTypeDto);
		AssertUtils.notNullAndEmpty(articleTypeDto.getCode(), "Code不能为空");
		try {
			ArticleType articleType = new ArticleType();
			// update数据录入
			articleType.setCode(articleTypeDto.getCode());
			articleType.setMerchantNo(articleTypeDto.getMerchantNo());
			articleType.setTypeName(articleTypeDto.getTypeName());
			articleType.setStatus(articleTypeDto.getStatus());
			articleType.setSeq(articleTypeDto.getSeq());
			articleType.setCreateId(articleTypeDto.getCreateId());
			articleType.setCreateDate(articleTypeDto.getCreateDate());
			articleType.setRemark(articleTypeDto.getRemark());
			articleType.setRemark2(articleTypeDto.getRemark2());
			articleType.setRemark3(articleTypeDto.getRemark3());
			articleType.setRemark4(articleTypeDto.getRemark4());
			articleType.setUpdateTime(articleTypeDto.getUpdateTime());
			AssertUtils.notUpdateMoreThanOne(articleTypeDao.updateByPrimaryKeySelective(articleType));
			logger.debug("updateArticleType(ArticleTypeDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("文章类型信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleType.ARTICLE_TYPE_UPDATE_ERROR, "文章类型信息更新信息错误！", e);
		}
	}

	@Override
	public ArticleTypeDto findArticleType(ArticleTypeDto articleTypeDto) throws TsfaServiceException {
		logger.debug("findArticleType(FindArticleType findArticleType={}) - start", articleTypeDto);

		AssertUtils.notNull(articleTypeDto);
		AssertUtils.notAllNull(articleTypeDto.getCode(), "Code不能为空");
		try {
			ArticleType articleType = articleTypeDao.selectByPrimaryKey(articleTypeDto.getCode());
			if (articleType == null) {
				return null;
				// throw new TsfaServiceException(ErrorCodeArticleType.ARTICLE_TYPE_NOT_EXIST_ERROR,"文章类型信息不存在");
			}
			ArticleTypeDto findArticleTypeReturn = new ArticleTypeDto();
			// find数据录入
			findArticleTypeReturn.setCode(articleType.getCode());
			findArticleTypeReturn.setMerchantNo(articleType.getMerchantNo());
			findArticleTypeReturn.setTypeName(articleType.getTypeName());
			findArticleTypeReturn.setStatus(articleType.getStatus());
			findArticleTypeReturn.setSeq(articleType.getSeq());
			findArticleTypeReturn.setCreateId(articleType.getCreateId());
			findArticleTypeReturn.setCreateDate(articleType.getCreateDate());
			findArticleTypeReturn.setRemark(articleType.getRemark());
			findArticleTypeReturn.setRemark2(articleType.getRemark2());
			findArticleTypeReturn.setRemark3(articleType.getRemark3());
			findArticleTypeReturn.setRemark4(articleType.getRemark4());
			findArticleTypeReturn.setUpdateTime(articleType.getUpdateTime());

			logger.debug("findArticleType(ArticleTypeDto) - end - return value={}", findArticleTypeReturn);
			return findArticleTypeReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找文章类型信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleType.ARTICLE_TYPE_FIND_ERROR, "查找文章类型信息信息错误！", e);
		}

	}

	@Override
	public Page<ArticleTypeDto> findArticleTypePage(FindArticleTypePage findArticleTypePage) throws TsfaServiceException {
		logger.debug("findArticleTypePage(FindArticleTypePage findArticleTypePage={}) - start", findArticleTypePage);

		AssertUtils.notNull(findArticleTypePage);
		List<ArticleTypeDto> returnList = null;
		int count = 0;
		try {
			returnList = articleTypeDao.findArticleTypePage(findArticleTypePage);
			count = articleTypeDao.findArticleTypePageCount(findArticleTypePage);
		} catch (Exception e) {
			logger.error("文章类型信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeArticleType.ARTICLE_TYPE_FIND_PAGE_ERROR, "文章类型信息不存在错误.！", e);
		}
		Page<ArticleTypeDto> returnPage = new Page<ArticleTypeDto>(returnList, count, findArticleTypePage);

		logger.debug("findArticleTypePage(FindArticleTypePage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(articleTypeDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("文章信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeArticleType.ARTICLE_TYPE_UPDATE_ERROR, "文章类型信息刪除信息错误！", e);
		}
	}

}
