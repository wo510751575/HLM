package com.lj.business.cm.service.impl;

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
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IWordsInfoDao;
import com.lj.business.cm.domain.WordsInfo;
import com.lj.business.cm.dto.wordsInfo.AddWordsInfo;
import com.lj.business.cm.dto.wordsInfo.AddWordsInfoReturn;
import com.lj.business.cm.dto.wordsInfo.DelWordsInfo;
import com.lj.business.cm.dto.wordsInfo.DelWordsInfoReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfo;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoApp;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoAppReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPage;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPageReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoWeb;
import com.lj.business.cm.dto.wordsInfo.UpdateWordsInfo;
import com.lj.business.cm.dto.wordsInfo.UpdateWordsInfoReturn;
import com.lj.business.cm.service.IWordsInfoService;
import com.lj.business.cm.service.IWordsTypeService;

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
public class WordsInfoServiceImpl implements IWordsInfoService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WordsInfoServiceImpl.class);
	

	@Resource
	private IWordsInfoDao wordsInfoDao;
	@Resource
	private IWordsTypeService wordsTypeService;
	
	
	@Override
	public AddWordsInfoReturn addWordsInfo(
			AddWordsInfo addWordsInfo) throws TsfaServiceException {
		logger.debug("addWordsInfo(AddWordsInfo addWordsInfo={}) - start", addWordsInfo); 

		AssertUtils.notNull(addWordsInfo);
		try {
			WordsInfo wordsInfo = new WordsInfo();
			//add数据录入
			wordsInfo.setCode(GUID.generateByUUID());
			wordsInfo.setContent(addWordsInfo.getContent());
			wordsInfo.setDefaultFlag(addWordsInfo.getDefaultFlag());
			wordsInfo.setMerchantNo(addWordsInfo.getMerchantNo());
			wordsInfo.setMemberNoGm(addWordsInfo.getMemberNoGm());
			wordsInfo.setTypeCode(addWordsInfo.getTypeCode());
			wordsInfo.setTypeName(addWordsInfo.getTypeName());
			wordsInfo.setCreateId(addWordsInfo.getCreateId());
			wordsInfo.setCreateDate(new Date());
			wordsInfo.setUpdateTime(new Date());
			wordsInfo.setRemark(addWordsInfo.getRemark());
			wordsInfo.setRemark2(addWordsInfo.getRemark2());
			wordsInfo.setRemark3(addWordsInfo.getRemark3());
			wordsInfo.setRemark4(addWordsInfo.getRemark4());
			wordsInfoDao.insertSelective(wordsInfo);
			
			//增加类型数量
			wordsTypeService.incrementTypeCountByPrimaryKey(addWordsInfo.getTypeCode(),1);
			AddWordsInfoReturn addWordsInfoReturn = new AddWordsInfoReturn();
			addWordsInfoReturn.setCode(wordsInfo.getCode());
			logger.debug("addWordsInfo(AddWordsInfo) - end - return value={}", addWordsInfoReturn); 
			return addWordsInfoReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增话术信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_ADD_ERROR,"新增话术信息信息错误！",e);
		}
	}
	
	
	@Override
	public DelWordsInfoReturn delWordsInfo(
			DelWordsInfo delWordsInfo) throws TsfaServiceException {
		logger.debug("delWordsInfo(DelWordsInfo delWordsInfo={}) - start", delWordsInfo); 

		AssertUtils.notNull(delWordsInfo);
		AssertUtils.notNullAndEmpty(delWordsInfo.getCode(),"CODE不能为空！");
		try {
			wordsInfoDao.deleteByPrimaryKey(delWordsInfo.getCode());
			DelWordsInfoReturn delWordsInfoReturn  = new DelWordsInfoReturn();

			logger.debug("delWordsInfo(DelWordsInfo) - end - return value={}", delWordsInfoReturn); 
			return delWordsInfoReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除话术信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_DEL_ERROR,"删除话术信息信息错误！",e);

		}
	}

	@Override
	public UpdateWordsInfoReturn updateWordsInfo(
			UpdateWordsInfo updateWordsInfo)
			throws TsfaServiceException {
		logger.debug("updateWordsInfo(UpdateWordsInfo updateWordsInfo={}) - start", updateWordsInfo); 

		AssertUtils.notNull(updateWordsInfo);
		AssertUtils.notNullAndEmpty(updateWordsInfo.getCode(),"CODE不能为空");
		try {
			WordsInfo wordsInfo = new WordsInfo();
			//update数据录入
			wordsInfo.setCode(updateWordsInfo.getCode());
			wordsInfo.setContent(updateWordsInfo.getContent());
			wordsInfo.setDefaultFlag(updateWordsInfo.getDefaultFlag());
			wordsInfo.setMerchantNo(updateWordsInfo.getMerchantNo());
			wordsInfo.setMemberNoGm(updateWordsInfo.getMemberNoGm());
			wordsInfo.setTypeCode(updateWordsInfo.getTypeCode());
			wordsInfo.setTypeName(updateWordsInfo.getTypeName());
			wordsInfo.setCreateId(updateWordsInfo.getCreateId());
			wordsInfo.setCreateDate(updateWordsInfo.getCreateDate());
			wordsInfo.setUpdateTime(new Date());
			wordsInfo.setRemark(updateWordsInfo.getRemark());
			wordsInfo.setRemark2(updateWordsInfo.getRemark2());
			wordsInfo.setRemark3(updateWordsInfo.getRemark3());
			wordsInfo.setRemark4(updateWordsInfo.getRemark4());
			AssertUtils.notUpdateMoreThanOne(wordsInfoDao.updateByPrimaryKeySelective(wordsInfo));
			UpdateWordsInfoReturn updateWordsInfoReturn = new UpdateWordsInfoReturn();

			logger.debug("updateWordsInfo(UpdateWordsInfo) - end - return value={}", updateWordsInfoReturn); 
			return updateWordsInfoReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("话术信息信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_UPDATE_ERROR,"话术信息信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindWordsInfoReturn findWordsInfo(
			FindWordsInfo findWordsInfo) throws TsfaServiceException {
		logger.debug("findWordsInfo(FindWordsInfo findWordsInfo={}) - start", findWordsInfo); 

		AssertUtils.notNull(findWordsInfo);
		AssertUtils.notNullAndEmpty(findWordsInfo.getCode(),"CODE不能为空");
		try {
			WordsInfo wordsInfo = wordsInfoDao.selectByPrimaryKey(findWordsInfo.getCode());
			if(wordsInfo == null){
				throw new TsfaServiceException(ErrorCode.WORDS_INFO_NOT_EXIST_ERROR,"话术信息信息不存在");
			}
			FindWordsInfoReturn findWordsInfoReturn = new FindWordsInfoReturn();
			//find数据录入
			findWordsInfoReturn.setCode(wordsInfo.getCode());
			findWordsInfoReturn.setContent(wordsInfo.getContent());
			findWordsInfoReturn.setDefaultFlag(wordsInfo.getDefaultFlag());
			findWordsInfoReturn.setMerchantNo(wordsInfo.getMerchantNo());
			findWordsInfoReturn.setMemberNoGm(wordsInfo.getMemberNoGm());
			findWordsInfoReturn.setTypeCode(wordsInfo.getTypeCode());
			findWordsInfoReturn.setTypeName(wordsInfo.getTypeName());
			findWordsInfoReturn.setCreateId(wordsInfo.getCreateId());
			findWordsInfoReturn.setCreateDate(wordsInfo.getCreateDate());
			findWordsInfoReturn.setUpdateTime(wordsInfo.getUpdateTime());
			findWordsInfoReturn.setRemark(wordsInfo.getRemark());
			findWordsInfoReturn.setRemark2(wordsInfo.getRemark2());
			findWordsInfoReturn.setRemark3(wordsInfo.getRemark3());
			findWordsInfoReturn.setRemark4(wordsInfo.getRemark4());
			
			logger.debug("findWordsInfo(FindWordsInfo) - end - return value={}", findWordsInfoReturn); 
			return findWordsInfoReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找话术信息信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_FIND_ERROR,"查找话术信息信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindWordsInfoPageReturn> findWordsInfoPage(
			FindWordsInfoPage findWordsInfoPage)
			throws TsfaServiceException {
		logger.debug("findWordsInfoPage(FindWordsInfoPage findWordsInfoPage={}) - start", findWordsInfoPage); 

		AssertUtils.notNull(findWordsInfoPage);
		List<FindWordsInfoPageReturn> returnList = null;
		int count = 0;
		try {
			returnList = wordsInfoDao.findWordsInfoPage(findWordsInfoPage);
			count = wordsInfoDao.findWordsInfoPageCount(findWordsInfoPage);
		}  catch (Exception e) {
			logger.error("话术信息信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_FIND_PAGE_ERROR,"话术信息信息不存在错误.！",e);
		}
		Page<FindWordsInfoPageReturn> returnPage = new Page<FindWordsInfoPageReturn>(returnList, count, findWordsInfoPage);

		logger.debug("findWordsInfoPage(FindWordsInfoPage) - end - return value={}", returnPage); 
		return  returnPage;
	}

	@Override
	public List<FindWordsInfoAppReturn> wordsSelect(FindWordsInfoApp findWordsInfoApp)
			throws TsfaServiceException {
		logger.debug("wordsSelect(FindWordsInfoApp findWordsInfoApp={}) - start", findWordsInfoApp); 

		AssertUtils.notNull(findWordsInfoApp);
		AssertUtils.notNullAndEmpty(findWordsInfoApp.getMerchantNo(),"商户编号不能为空");
		List<FindWordsInfoAppReturn> returnList = null;
		try {
			returnList = wordsInfoDao.wordsSelect(findWordsInfoApp);
		}  catch (Exception e) {
			logger.error("话术选择信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_FIND_PAGE_ERROR,"话术选择信息不存在错误.！",e);
		}

		logger.debug("wordsSelect(FindWordsInfoApp) - end - return value={}", returnList); 
		return  returnList;
	} 

	@Override
	public List<FindWordsInfoAppReturn> wordsSearch(
			FindWordsInfoApp findWordsInfoApp) throws TsfaServiceException {
		logger.debug("wordsSearch(FindWordsInfoApp findWordsInfoApp={}) - start", findWordsInfoApp); 

		AssertUtils.notNull(findWordsInfoApp);
		AssertUtils.notNullAndEmpty(findWordsInfoApp.getMerchantNo(),"商户编号不能为空");
		AssertUtils.notNullAndEmpty(findWordsInfoApp.getContent(),"内容不能为空");
		List<FindWordsInfoAppReturn> returnList = null;
		try {
			returnList = wordsInfoDao.wordsSearch(findWordsInfoApp);
		}  catch (Exception e) {
			logger.error("话术搜索信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_FIND_PAGE_ERROR,"话术搜索信息不存在错误.！",e);
		}

		logger.debug("wordsSearch(findWordsInfoApp) - end - return value={}", returnList); 
		return  returnList;
	}

	@Override
	public List<FindWordsInfoReturn> moreWords(
			FindWordsInfoWeb findWordsInfoWeb) throws TsfaServiceException {
		logger.debug("moreWords(FindWordsInfoWeb findWordsInfoWeb={}) - start", findWordsInfoWeb); 

		AssertUtils.notNull(findWordsInfoWeb);
		AssertUtils.notNullAndEmpty(findWordsInfoWeb.getMerchantNo(),"商户编号不能为空");
		List<FindWordsInfoReturn> returnList = null;
		try {
			returnList = wordsInfoDao.moreWords(findWordsInfoWeb);
		}  catch (Exception e) {
			logger.error("话术WEB信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_FIND_PAGE_ERROR,"话术WEB信息不存在错误.！",e);
		}

		logger.debug("moreWords(findWordsInfoWeb) - end - return value={}", returnList); 
		return  returnList;
	}

	@Override
	public Integer findDefaultCount(String memberNoGm) {
		AssertUtils.notNullAndEmpty(memberNoGm,"导购编号不能为空");
		return wordsInfoDao.findDefaultCount(memberNoGm);
	}

	@Override
	public void updateTypeName(UpdateWordsInfo updateWordsInfo) throws TsfaServiceException {
		AssertUtils.notNull(updateWordsInfo);
		AssertUtils.notNullAndEmpty(updateWordsInfo.getTypeCode(),"类型CODE不能为空");
		AssertUtils.notNullAndEmpty(updateWordsInfo.getTypeName(),"类型名称不能为空");
		wordsInfoDao.updateTypeName(updateWordsInfo);
	}

	@Override
	public void deleteWordsInfoByTypeCode(String typeCode)
			throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(typeCode,"类型CODE不能为空");
		wordsInfoDao.deleteWordsInfoByTypeCode(typeCode);
	}


	@Override
	public Integer checkWords(String code) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(code,"话术CODE不能为空");
		return wordsInfoDao.checkWords(code);
	}


	@Override
	public List<FindWordsInfoAppReturn> wordsPersonSearch(FindWordsInfoPage findWordsInfoPage) {
		return wordsInfoDao.wordsPersonSearch(findWordsInfoPage);
	}


	@Override
	public List<FindWordsInfoAppReturn> wordsPersonSelect(FindWordsInfoPage findWordsInfoPage) {
		logger.debug("wordsPersonSelect(FindWordsInfoPage findWordsInfoPage={}) - start", findWordsInfoPage); 

		AssertUtils.notNull(findWordsInfoPage);
		AssertUtils.notNullAndEmpty(findWordsInfoPage.getMerchantNo(),"商户编号不能为空");
		AssertUtils.notNullAndEmpty(findWordsInfoPage.getMemberNoGm(),"导购编号不能为空");
		List<FindWordsInfoAppReturn> returnList = null;
		try {
			returnList = wordsInfoDao.wordsPersonSelect(findWordsInfoPage);
		}  catch (Exception e) {
			logger.error("话术选择信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_FIND_PAGE_ERROR,"话术选择信息不存在错误.！",e);
		}

		logger.debug("wordsPersonSelect(FindWordsInfoPage) - end - return value={}", returnList); 
		return  returnList;
	}


	@Override
	public List<FindWordsInfoAppReturn> findDefaultWords(FindWordsInfoPage findWordsInfoPage) {
		logger.debug("findDefaultWords(FindWordsInfoPage findWordsInfoPage={}) - start", findWordsInfoPage); 

		AssertUtils.notNull(findWordsInfoPage);
		AssertUtils.notNullAndEmpty(findWordsInfoPage.getMerchantNo(),"商户编号不能为空");
		AssertUtils.notNullAndEmpty(findWordsInfoPage.getMemberNoGm(),"导购编号不能为空");
		List<FindWordsInfoAppReturn> list = null;
		try {
			list = wordsInfoDao.findDefaultWords(findWordsInfoPage);
		}  catch (Exception e) {
			logger.error("话术选择信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_FIND_PAGE_ERROR,"话术选择信息不存在错误.！",e);
		}

		logger.debug("findDefaultWords(FindWordsInfoPage) - end - return value={}", list); 
		return  list;
	}


	@Override
	public List<FindWordsInfoAppReturn> findDefaultWordsH5(FindWordsInfoPage findWordsInfoPage) {
		logger.debug("findDefaultWordsH5(FindWordsInfoPage findWordsInfoPage={}) - start", findWordsInfoPage); 

		AssertUtils.notNull(findWordsInfoPage);
		AssertUtils.notNullAndEmpty(findWordsInfoPage.getMerchantNo(),"商户编号不能为空");
		AssertUtils.notNullAndEmpty(findWordsInfoPage.getMemberNoGm(),"导购编号不能为空");
		List<FindWordsInfoAppReturn> list = null;
		try {
			list = wordsInfoDao.findDefaultWordsH5(findWordsInfoPage);
		}  catch (Exception e) {
			logger.error("话术选择信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_FIND_PAGE_ERROR,"话术选择信息不存在错误.！",e);
		}

		logger.debug("findDefaultWordsH5(FindWordsInfoPage) - end - return value={}", list); 
		return  list;
	} 
	
}
