package com.lj.business.weixin.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.constant.ErrorCode;
import com.lj.business.weixin.dao.IImEmojiDao;
import com.lj.business.weixin.domain.ImEmoji;
import com.lj.business.weixin.dto.imemoji.AddImEmoji;
import com.lj.business.weixin.dto.imemoji.AddImEmojiReturn;
import com.lj.business.weixin.dto.imemoji.DelImEmoji;
import com.lj.business.weixin.dto.imemoji.DelImEmojiReturn;
import com.lj.business.weixin.dto.imemoji.FindImEmoji;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPageReturn;
import com.lj.business.weixin.dto.imemoji.FindImEmojiReturn;
import com.lj.business.weixin.dto.imemoji.UpdateImEmoji;
import com.lj.business.weixin.dto.imemoji.UpdateImEmojiReturn;
import com.lj.business.weixin.service.IImEmojiService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭俊霖
 * 
 * 
 * CreateDate: 2017-11-01
 */
@Service
public class ImEmojiServiceImpl implements IImEmojiService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ImEmojiServiceImpl.class);
	

	@Resource
	private IImEmojiDao imEmojiDao;
	
	
	@Override
	public AddImEmojiReturn addImEmoji(
			AddImEmoji addImEmoji) throws TsfaServiceException {
		logger.debug("addImEmoji(AddImEmoji addImEmoji={}) - start", addImEmoji); 

		AssertUtils.notNull(addImEmoji);
		try {
			ImEmoji imEmoji = new ImEmoji();
			//add数据录入
			imEmoji.setCode(GUID.generateByUUID());
			imEmoji.setPackageCode(addImEmoji.getPackageCode());
			imEmoji.setEmojiName(addImEmoji.getEmojiName());
			imEmoji.setEmojiUrl(addImEmoji.getEmojiUrl());
			imEmoji.setVersion(addImEmoji.getVersion());
			imEmoji.setStatus(addImEmoji.getStatus());
			imEmoji.setShowIndex(addImEmoji.getShowIndex());
			imEmoji.setCreateId(addImEmoji.getCreateId());
			imEmoji.setCreateDate(new Date());
			imEmoji.setRemark(addImEmoji.getRemark());
			imEmoji.setRemark2(addImEmoji.getRemark2());
			imEmoji.setRemark3(addImEmoji.getRemark3());
			imEmoji.setRemark4(addImEmoji.getRemark4());
			imEmojiDao.insert(imEmoji);
			AddImEmojiReturn addImEmojiReturn = new AddImEmojiReturn();
			
			logger.debug("addImEmoji(AddImEmoji) - end - return value={}", addImEmojiReturn); 
			return addImEmojiReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增IM表情信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_ADD_ERROR,"新增IM表情信息错误！",e);
		}
	}
	
	
	@Override
	public DelImEmojiReturn delImEmoji(
			DelImEmoji delImEmoji) throws TsfaServiceException {
		logger.debug("delImEmoji(DelImEmoji delImEmoji={}) - start", delImEmoji); 

		AssertUtils.notNull(delImEmoji);
		AssertUtils.notNull(delImEmoji.getCode(),"ID不能为空！");
		try {
			imEmojiDao.deleteByPrimaryKey(delImEmoji.getCode());
			DelImEmojiReturn delImEmojiReturn  = new DelImEmojiReturn();

			logger.debug("delImEmoji(DelImEmoji) - end - return value={}", delImEmojiReturn); 
			return delImEmojiReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除IM表情信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_DEL_ERROR,"删除IM表情信息错误！",e);

		}
	}

	@Override
	public UpdateImEmojiReturn updateImEmoji(
			UpdateImEmoji updateImEmoji)
			throws TsfaServiceException {
		logger.debug("updateImEmoji(UpdateImEmoji updateImEmoji={}) - start", updateImEmoji); 

		AssertUtils.notNull(updateImEmoji);
		AssertUtils.notNullAndEmpty(updateImEmoji.getCode(),"ID不能为空");
		try {
			ImEmoji imEmoji = new ImEmoji();
			//update数据录入
			imEmoji.setCode(updateImEmoji.getCode());
			imEmoji.setPackageCode(updateImEmoji.getPackageCode());
			imEmoji.setEmojiName(updateImEmoji.getEmojiName());
			imEmoji.setEmojiUrl(updateImEmoji.getEmojiUrl());
			imEmoji.setVersion(updateImEmoji.getVersion());
			imEmoji.setStatus(updateImEmoji.getStatus());
			imEmoji.setShowIndex(updateImEmoji.getShowIndex());
			imEmoji.setCreateId(updateImEmoji.getCreateId());
			imEmoji.setCreateDate(updateImEmoji.getCreateDate());
			imEmoji.setRemark(updateImEmoji.getRemark());
			imEmoji.setRemark2(updateImEmoji.getRemark2());
			imEmoji.setRemark3(updateImEmoji.getRemark3());
			imEmoji.setRemark4(updateImEmoji.getRemark4());
			AssertUtils.notUpdateMoreThanOne(imEmojiDao.updateByPrimaryKeySelective(imEmoji));
			UpdateImEmojiReturn updateImEmojiReturn = new UpdateImEmojiReturn();

			logger.debug("updateImEmoji(UpdateImEmoji) - end - return value={}", updateImEmojiReturn); 
			return updateImEmojiReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("IM表情信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_UPDATE_ERROR,"IM表情信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindImEmojiReturn findImEmoji(
			FindImEmoji findImEmoji) throws TsfaServiceException {
		logger.debug("findImEmoji(FindImEmoji findImEmoji={}) - start", findImEmoji); 

		AssertUtils.notNull(findImEmoji);
		AssertUtils.notNullAndEmpty(findImEmoji.getCode(),"ID不能为空");
		try {
			ImEmoji imEmoji = imEmojiDao.selectByPrimaryKey(findImEmoji.getCode());
			if(imEmoji == null){
				throw new TsfaServiceException(ErrorCode.IM_EMOJI_NOT_EXIST_ERROR,"IM表情信息不存在");
			}
			FindImEmojiReturn findImEmojiReturn = new FindImEmojiReturn();
			//find数据录入
			findImEmojiReturn.setCode(imEmoji.getCode());
			findImEmojiReturn.setPackageCode(imEmoji.getPackageCode());
			findImEmojiReturn.setEmojiName(imEmoji.getEmojiName());
			findImEmojiReturn.setEmojiUrl(imEmoji.getEmojiUrl());
			findImEmojiReturn.setVersion(imEmoji.getVersion());
			findImEmojiReturn.setStatus(imEmoji.getStatus());
			findImEmojiReturn.setShowIndex(imEmoji.getShowIndex());
			findImEmojiReturn.setCreateId(imEmoji.getCreateId());
			findImEmojiReturn.setCreateDate(imEmoji.getCreateDate());
			findImEmojiReturn.setRemark(imEmoji.getRemark());
			findImEmojiReturn.setRemark2(imEmoji.getRemark2());
			findImEmojiReturn.setRemark3(imEmoji.getRemark3());
			findImEmojiReturn.setRemark4(imEmoji.getRemark4());
			
			logger.debug("findImEmoji(FindImEmoji) - end - return value={}", findImEmojiReturn); 
			return findImEmojiReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找IM表情信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_FIND_ERROR,"查找IM表情信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindImEmojiPageReturn> findImEmojiPage(
			FindImEmojiPage findImEmojiPage)
			throws TsfaServiceException {
		logger.debug("findImEmojiPage(FindImEmojiPage findImEmojiPage={}) - start", findImEmojiPage); 

		AssertUtils.notNull(findImEmojiPage);
		List<FindImEmojiPageReturn> returnList;
		int count = 0;
		try {
			returnList = imEmojiDao.findImEmojiPage(findImEmojiPage);
			count = imEmojiDao.findImEmojiPageCount(findImEmojiPage);
		}  catch (Exception e) {
			logger.error("IM表情信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_FIND_PAGE_ERROR,"IM表情信息不存在错误.！",e);
		}
		Page<FindImEmojiPageReturn> returnPage = new Page<FindImEmojiPageReturn>(returnList, count, findImEmojiPage);

		logger.debug("findImEmojiPage(FindImEmojiPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public List<Integer> findAllShowIndex(String packageCode) {
		return imEmojiDao.findAllShowIndex(packageCode);
	} 


}
