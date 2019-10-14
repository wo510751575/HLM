package com.lj.business.weixin.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.supcon.service.IChatService;
import com.lj.business.weixin.constant.ErrorCode;
import com.lj.business.weixin.dao.IImSensitiveWordsDao;
import com.lj.business.weixin.domain.ImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.AddImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.AddImSensitiveWordsReturn;
import com.lj.business.weixin.dto.imSensitiveWords.DelImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.DelImSensitiveWordsReturn;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsPage;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsPageReturn;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsReturn;
import com.lj.business.weixin.dto.imSensitiveWords.UpdateImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.UpdateImSensitiveWordsReturn;
import com.lj.business.weixin.service.IImSensitiveWordsService;
import com.lj.business.weixin.utils.SensitiveWordsUtils;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 罗书明 
 * 
 * 
 * CreateDate: 2017-11-02
 */
@Service
public class ImSensitiveWordsServiceImpl implements IImSensitiveWordsService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ImSensitiveWordsServiceImpl.class);
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Resource
	private IImSensitiveWordsDao imSensitiveWordsDao;
	
	
	@PostConstruct
	public void init() {
		refreshSensitiveWords();
	}
	
	public void refreshSensitiveWords() {
		SensitiveWordsUtils.init(imSensitiveWordsDao.findAllSensitiveWords());
		logger.info("已初始化敏感词词库");
	}
	
	@Override
	public AddImSensitiveWordsReturn addImSensitiveWords(
			AddImSensitiveWords addImSensitiveWords) throws TsfaServiceException {
		logger.debug("addImSensitiveWords(AddImSensitiveWords addImSensitiveWords={}) - start", addImSensitiveWords); 

		AssertUtils.notNull(addImSensitiveWords);
		try {
			ImSensitiveWords imSensitiveWords = new ImSensitiveWords();
			//add数据录入
			imSensitiveWords.setCode(GUID.getPreUUID());
			imSensitiveWords.setWord(addImSensitiveWords.getWord());
			imSensitiveWords.setVersion(System.currentTimeMillis());
			imSensitiveWords.setStatus(addImSensitiveWords.getStatus());
			imSensitiveWords.setShowIndex(addImSensitiveWords.getShowIndex());
			imSensitiveWords.setCreateId(addImSensitiveWords.getCreateId());
			imSensitiveWords.setCreateDate(new Date());
			imSensitiveWords.setRemark(addImSensitiveWords.getRemark());
			imSensitiveWords.setRemark2(addImSensitiveWords.getRemark2());
			imSensitiveWords.setRemark3(addImSensitiveWords.getRemark3());
			imSensitiveWords.setRemark4(addImSensitiveWords.getRemark4());
			imSensitiveWordsDao.insertSelective(imSensitiveWords);
			AddImSensitiveWordsReturn addImSensitiveWordsReturn = new AddImSensitiveWordsReturn();
			
			//刷新敏感词
			refreshSensitiveWords();
			
			logger.debug("addImSensitiveWords(AddImSensitiveWords) - end - return value={}", addImSensitiveWordsReturn); 
			return addImSensitiveWordsReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增IM敏感词表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_SENSITIVE_WORDS_ADD_ERROR,"新增IM敏感词表信息错误！",e);
		}
	}
	
	
	@Override
	public DelImSensitiveWordsReturn delImSensitiveWords(
			DelImSensitiveWords delImSensitiveWords) throws TsfaServiceException {
		logger.debug("delImSensitiveWords(DelImSensitiveWords delImSensitiveWords={}) - start", delImSensitiveWords); 

		AssertUtils.notNull(delImSensitiveWords);
		AssertUtils.notNull(delImSensitiveWords.getCode(),"CODE不能为空！");
		try {
			imSensitiveWordsDao.deleteByPrimaryKey(delImSensitiveWords.getCode());
			DelImSensitiveWordsReturn delImSensitiveWordsReturn  = new DelImSensitiveWordsReturn();
			
			//刷新敏感词
			refreshSensitiveWords();

			logger.debug("delImSensitiveWords(DelImSensitiveWords) - end - return value={}", delImSensitiveWordsReturn); 
			return delImSensitiveWordsReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除IM敏感词表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_SENSITIVE_WORDS_DEL_ERROR,"删除IM敏感词表信息错误！",e);

		}
	}

	@Override
	public UpdateImSensitiveWordsReturn updateImSensitiveWords(
			UpdateImSensitiveWords updateImSensitiveWords)
			throws TsfaServiceException {
		logger.debug("updateImSensitiveWords(UpdateImSensitiveWords updateImSensitiveWords={}) - start", updateImSensitiveWords); 

		AssertUtils.notNull(updateImSensitiveWords);
		AssertUtils.notNullAndEmpty(updateImSensitiveWords.getCode(),"CODE不能为空");
		try {
			ImSensitiveWords imSensitiveWords = new ImSensitiveWords();
			//update数据录入
			imSensitiveWords.setCode(updateImSensitiveWords.getCode());
			imSensitiveWords.setWord(updateImSensitiveWords.getWord());
			imSensitiveWords.setVersion(System.currentTimeMillis());
			imSensitiveWords.setStatus(updateImSensitiveWords.getStatus());
			imSensitiveWords.setShowIndex(updateImSensitiveWords.getShowIndex());
			imSensitiveWords.setCreateId(updateImSensitiveWords.getCreateId());
			imSensitiveWords.setCreateDate(updateImSensitiveWords.getCreateDate());
			imSensitiveWords.setRemark(updateImSensitiveWords.getRemark());
			imSensitiveWords.setRemark2(updateImSensitiveWords.getRemark2());
			imSensitiveWords.setRemark3(updateImSensitiveWords.getRemark3());
			imSensitiveWords.setRemark4(updateImSensitiveWords.getRemark4());
			AssertUtils.notUpdateMoreThanOne(imSensitiveWordsDao.updateByPrimaryKeySelective(imSensitiveWords));
			UpdateImSensitiveWordsReturn updateImSensitiveWordsReturn = new UpdateImSensitiveWordsReturn();

			//刷新敏感词
			refreshSensitiveWords();
			
			logger.debug("updateImSensitiveWords(UpdateImSensitiveWords) - end - return value={}", updateImSensitiveWordsReturn); 
			return updateImSensitiveWordsReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("IM敏感词表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_SENSITIVE_WORDS_UPDATE_ERROR,"IM敏感词表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindImSensitiveWordsReturn findImSensitiveWords(
			FindImSensitiveWords findImSensitiveWords) throws TsfaServiceException {
		logger.debug("findImSensitiveWords(FindImSensitiveWords findImSensitiveWords={}) - start", findImSensitiveWords); 

		AssertUtils.notNull(findImSensitiveWords);
		AssertUtils.notAllNull(findImSensitiveWords.getCode(),"CODE不能为空");
		try {
			ImSensitiveWords imSensitiveWords = imSensitiveWordsDao.selectByPrimaryKey(findImSensitiveWords.getCode());
			if(imSensitiveWords == null){
				throw new TsfaServiceException(ErrorCode.IM_SENSITIVE_WORDS_NOT_EXIST_ERROR,"IM敏感词表信息不存在");
			}
			FindImSensitiveWordsReturn findImSensitiveWordsReturn = new FindImSensitiveWordsReturn();
			//find数据录入
			findImSensitiveWordsReturn.setCode(imSensitiveWords.getCode());
			findImSensitiveWordsReturn.setWord(imSensitiveWords.getWord());
			findImSensitiveWordsReturn.setVersion(imSensitiveWords.getVersion());
			findImSensitiveWordsReturn.setStatus(imSensitiveWords.getStatus());
			findImSensitiveWordsReturn.setShowIndex(imSensitiveWords.getShowIndex());
			findImSensitiveWordsReturn.setCreateId(imSensitiveWords.getCreateId());
			findImSensitiveWordsReturn.setCreateDate(imSensitiveWords.getCreateDate());
			findImSensitiveWordsReturn.setRemark(imSensitiveWords.getRemark());
			findImSensitiveWordsReturn.setRemark2(imSensitiveWords.getRemark2());
			findImSensitiveWordsReturn.setRemark3(imSensitiveWords.getRemark3());
			findImSensitiveWordsReturn.setRemark4(imSensitiveWords.getRemark4());
			
			logger.debug("findImSensitiveWords(FindImSensitiveWords) - end - return value={}", findImSensitiveWordsReturn); 
			return findImSensitiveWordsReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找IM敏感词表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_SENSITIVE_WORDS_FIND_ERROR,"查找IM敏感词表信息信息错误！",e);
		}


	}

	@Override
	public Page<FindImSensitiveWordsPageReturn> findImSensitiveWordsPage(
			FindImSensitiveWordsPage findImSensitiveWordsPage)
			throws TsfaServiceException {
		logger.debug("findImSensitiveWordsPage(FindImSensitiveWordsPage findImSensitiveWordsPage={}) - start", findImSensitiveWordsPage); 

		AssertUtils.notNull(findImSensitiveWordsPage);
		List<FindImSensitiveWordsPageReturn> returnList;
		int count = 0;
		try {
			returnList = imSensitiveWordsDao.findImSensitiveWordsPage(findImSensitiveWordsPage);
			count = imSensitiveWordsDao.findImSensitiveWordsPageCount(findImSensitiveWordsPage);
		}  catch (Exception e) {
			logger.error("IM敏感词表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.IM_SENSITIVE_WORDS_FIND_PAGE_ERROR,"IM敏感词表信息不存在错误.！",e);
		}
		Page<FindImSensitiveWordsPageReturn> returnPage = new Page<FindImSensitiveWordsPageReturn>(returnList, count, findImSensitiveWordsPage);

		logger.debug("findImSensitiveWordsPage(FindImSensitiveWordsPage) - end - return value={}", returnPage); 
		return  returnPage;
	}

	@Override
	public Set<String> findAllSensitiveWords() {
		try {
			return imSensitiveWordsDao.findAllSensitiveWords();
		} catch(Exception e) {
			logger.error("查询敏感词词库失败", e);
			throw new TsfaServiceException(ErrorCode.IM_SENSITIVE_WORDS_FIND_ERROR, "查询敏感词词库失败", e);
		}
	}

	@Override
	public boolean contains(String txt) {
		AssertUtils.notNullAndEmpty(txt,"文字不能为空");
		try {
			return SensitiveWordsUtils.contains(txt);
		} catch(Exception e) {
			logger.error("判断文字是否包含敏感字符失败", e);
			throw new TsfaServiceException(ErrorCode.IM_SENSITIVE_WORDS_FIND_ERROR, "判断文字是否包含敏感字符失败", e);
		}
	} 

}
