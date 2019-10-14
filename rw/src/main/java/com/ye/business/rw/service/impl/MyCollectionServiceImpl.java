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
import com.ye.business.rw.constant.ErrorCodeMyCollection;
import com.ye.business.rw.dao.IMyCollectionDao;
import com.ye.business.rw.domain.MyCollection;
import com.ye.business.rw.dto.FindMyCollectionPage;
import com.ye.business.rw.dto.MyCollectionDto;
import com.ye.business.rw.service.IMyCollectionService;

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
public class MyCollectionServiceImpl implements IMyCollectionService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MyCollectionServiceImpl.class);

	@Resource
	private IMyCollectionDao myCollectionDao;

	@Override
	public String addMyCollection(MyCollectionDto myCollectionDto) throws TsfaServiceException {
		logger.debug("addMyCollection(AddMyCollection addMyCollection={}) - start", myCollectionDto);

		AssertUtils.notNull(myCollectionDto);
		try {
			MyCollection myCollection = new MyCollection();
			// add数据录入
			myCollection.setCode(GUID.getPreUUID());
			myCollection.setMemberNoGuid(myCollectionDto.getMemberNoGuid());
			myCollection.setMemberNameGuid(myCollectionDto.getMemberNameGuid());
			myCollection.setShopNo(myCollectionDto.getShopNo());
			myCollection.setShopName(myCollectionDto.getShopName());
			myCollection.setMerchantNo(myCollectionDto.getMerchantNo());
			myCollection.setMerchantName(myCollectionDto.getMerchantName());
			myCollection.setType(myCollectionDto.getType());
			myCollection.setTypeCode(myCollectionDto.getTypeCode());
			myCollection.setMainImage(myCollectionDto.getMainImage());
			myCollection.setTitle(myCollectionDto.getTitle());
			myCollection.setSource(myCollectionDto.getSource());
			myCollection.setRemark(myCollectionDto.getRemark());
			myCollection.setReadNum(myCollectionDto.getReadNum());
			myCollection.setLikeNum(myCollectionDto.getLikeNum());
			myCollection.setCreateId(myCollectionDto.getCreateId());
			myCollection.setCreateDate(myCollectionDto.getCreateDate());
			myCollection.setMemberNo(myCollectionDto.getMemberNo());
			myCollection.setRwState(myCollectionDto.getRwState());
			myCollection.setWebUrl(myCollectionDto.getWebUrl());
			myCollection.setArticleHtml(myCollectionDto.getArticleHtml());
			myCollectionDao.insertSelective(myCollection);
			logger.debug("addMyCollection(MyCollectionDto) - end - return");
			return myCollection.getCode();
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增我的收藏信息错误！", e);
			throw new TsfaServiceException(ErrorCodeMyCollection.MYCOLLECTION_ADD_ERROR, "新增我的收藏信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询我的收藏信息
	 *
	 * @param findMyCollectionPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<MyCollectionDto> findMyCollections(FindMyCollectionPage findMyCollectionPage) throws TsfaServiceException {
		AssertUtils.notNull(findMyCollectionPage);
		List<MyCollectionDto> returnList = null;
		try {
			returnList = myCollectionDao.findMyCollections(findMyCollectionPage);
		} catch (Exception e) {
			logger.error("查找我的收藏信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeMyCollection.MYCOLLECTION_NOT_EXIST_ERROR, "我的收藏信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateMyCollection(MyCollectionDto myCollectionDto) throws TsfaServiceException {
		logger.debug("updateMyCollection(MyCollectionDto myCollectionDto={}) - start", myCollectionDto);

		AssertUtils.notNull(myCollectionDto);
		AssertUtils.notNullAndEmpty(myCollectionDto.getCode(), "Code不能为空");
		try {
			MyCollection myCollection = new MyCollection();
			// update数据录入
			myCollection.setCode(myCollectionDto.getCode());
			myCollection.setMemberNoGuid(myCollectionDto.getMemberNoGuid());
			myCollection.setMemberNameGuid(myCollectionDto.getMemberNameGuid());
			myCollection.setShopNo(myCollectionDto.getShopNo());
			myCollection.setShopName(myCollectionDto.getShopName());
			myCollection.setMerchantNo(myCollectionDto.getMerchantNo());
			myCollection.setMerchantName(myCollectionDto.getMerchantName());
			myCollection.setType(myCollectionDto.getType());
			myCollection.setTypeCode(myCollectionDto.getTypeCode());
			myCollection.setMainImage(myCollectionDto.getMainImage());
			myCollection.setTitle(myCollectionDto.getTitle());
			myCollection.setSource(myCollectionDto.getSource());
			myCollection.setRemark(myCollectionDto.getRemark());
			myCollection.setReadNum(myCollectionDto.getReadNum());
			myCollection.setLikeNum(myCollectionDto.getLikeNum());
			myCollection.setCreateId(myCollectionDto.getCreateId());
			myCollection.setCreateDate(myCollectionDto.getCreateDate());
			myCollection.setMemberNo(myCollectionDto.getMemberNo());
			myCollection.setRwState(myCollectionDto.getRwState());
			myCollection.setWebUrl(myCollectionDto.getWebUrl());
			myCollection.setArticleHtml(myCollectionDto.getArticleHtml());
			AssertUtils.notUpdateMoreThanOne(myCollectionDao.updateByPrimaryKeySelective(myCollection));
			logger.debug("updateMyCollection(MyCollectionDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("我的收藏信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeMyCollection.MYCOLLECTION_UPDATE_ERROR, "我的收藏信息更新信息错误！", e);
		}
	}

	@Override
	public MyCollectionDto findMyCollection(MyCollectionDto myCollectionDto) throws TsfaServiceException {
		logger.debug("findMyCollection(FindMyCollection findMyCollection={}) - start", myCollectionDto);

		AssertUtils.notNull(myCollectionDto);
		AssertUtils.notAllNull(myCollectionDto.getCode(), "Code不能为空");
		try {
			MyCollection myCollection = myCollectionDao.selectByPrimaryKey(myCollectionDto.getCode());
			if (myCollection == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.MYCOLLECTION_NOT_EXIST_ERROR,"我的收藏信息不存在");
			}
			MyCollectionDto findMyCollectionReturn = new MyCollectionDto();
			// find数据录入
			findMyCollectionReturn.setCode(myCollection.getCode());
			findMyCollectionReturn.setMemberNoGuid(myCollection.getMemberNoGuid());
			findMyCollectionReturn.setMemberNameGuid(myCollection.getMemberNameGuid());
			findMyCollectionReturn.setShopNo(myCollection.getShopNo());
			findMyCollectionReturn.setShopName(myCollection.getShopName());
			findMyCollectionReturn.setMerchantNo(myCollection.getMerchantNo());
			findMyCollectionReturn.setMerchantName(myCollection.getMerchantName());
			findMyCollectionReturn.setType(myCollection.getType());
			findMyCollectionReturn.setTypeCode(myCollection.getTypeCode());
			findMyCollectionReturn.setMainImage(myCollection.getMainImage());
			findMyCollectionReturn.setTitle(myCollection.getTitle());
			findMyCollectionReturn.setSource(myCollection.getSource());
			findMyCollectionReturn.setRemark(myCollection.getRemark());
			findMyCollectionReturn.setReadNum(myCollection.getReadNum());
			findMyCollectionReturn.setLikeNum(myCollection.getLikeNum());
			findMyCollectionReturn.setCreateId(myCollection.getCreateId());
			findMyCollectionReturn.setCreateDate(myCollection.getCreateDate());
			findMyCollectionReturn.setMemberNo(myCollection.getMemberNo());
			findMyCollectionReturn.setRwState(myCollection.getRwState());
			findMyCollectionReturn.setWebUrl(myCollection.getWebUrl());
			findMyCollectionReturn.setArticleHtml(myCollection.getArticleHtml());

			logger.debug("findMyCollection(MyCollectionDto) - end - return value={}", findMyCollectionReturn);
			return findMyCollectionReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找我的收藏信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeMyCollection.MYCOLLECTION_FIND_ERROR, "查找我的收藏信息信息错误！", e);
		}

	}

	@Override
	public Page<MyCollectionDto> findMyCollectionPage(FindMyCollectionPage findMyCollectionPage) throws TsfaServiceException {
		logger.debug("findMyCollectionPage(FindMyCollectionPage findMyCollectionPage={}) - start", findMyCollectionPage);

		AssertUtils.notNull(findMyCollectionPage);
		List<MyCollectionDto> returnList = null;
		int count = 0;
		try {
			returnList = myCollectionDao.findMyCollectionPage(findMyCollectionPage);
			count = myCollectionDao.findMyCollectionPageCount(findMyCollectionPage);
		} catch (Exception e) {
			logger.error("我的收藏信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeMyCollection.MYCOLLECTION_FIND_PAGE_ERROR, "我的收藏信息不存在错误.！", e);
		}
		Page<MyCollectionDto> returnPage = new Page<MyCollectionDto>(returnList, count, findMyCollectionPage);

		logger.debug("findMyCollectionPage(FindMyCollectionPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void updateMyCollectionForReadNum(String code) throws TsfaServiceException {

		logger.debug("updateMyCollectionForReadNum(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(myCollectionDao.updateMyCollectionForReadNum(code));
			logger.debug("updateMyCollectionForReadNum(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("我的收藏信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeMyCollection.MYCOLLECTION_UPDATE_ERROR, "我的收藏信息更新阅读量信息错误！", e);
		}
	}

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(myCollectionDao.deleteByPrimaryKey(code));
			logger.debug("updateMyCollectionForReadNum(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("我的收藏信息删除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeMyCollection.MYCOLLECTION_NOT_EXIST_ERROR, "我的收藏信息删除信息错误！", e);
		}
	}

	@Override
	public void removeByExample(MyCollectionDto record) throws TsfaServiceException {
		logger.debug("removeByExample(MyCollectionDto record={}) - start", record);

		AssertUtils.notNullAndEmpty(record);
		try {

			FindMyCollectionPage findMyCollectionPage = new FindMyCollectionPage();
			findMyCollectionPage.setParam(record);

			myCollectionDao.deleteByExample(findMyCollectionPage);
			
			logger.debug("removeByExample(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("我的收藏信息删除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeMyCollection.MYCOLLECTION_NOT_EXIST_ERROR, "我的收藏信息删除信息错误！", e);
		}
	}

}
