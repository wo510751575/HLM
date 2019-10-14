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
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IWordsTypeDao;
import com.lj.business.cm.domain.WordsType;
import com.lj.business.cm.dto.wordsInfo.FindWordsAppReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoApp;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoWeb;
import com.lj.business.cm.dto.wordsInfo.UpdateWordsInfo;
import com.lj.business.cm.dto.wordsType.AddWordsType;
import com.lj.business.cm.dto.wordsType.AddWordsTypeReturn;
import com.lj.business.cm.dto.wordsType.DelWordsType;
import com.lj.business.cm.dto.wordsType.DelWordsTypeReturn;
import com.lj.business.cm.dto.wordsType.FindWordsType;
import com.lj.business.cm.dto.wordsType.FindWordsTypePage;
import com.lj.business.cm.dto.wordsType.FindWordsTypePageReturn;
import com.lj.business.cm.dto.wordsType.FindWordsTypeReturn;
import com.lj.business.cm.dto.wordsType.FindWordsTypeSelectReturn;
import com.lj.business.cm.dto.wordsType.UpdateWordsType;
import com.lj.business.cm.dto.wordsType.UpdateWordsTypeReturn;
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
public class WordsTypeServiceImpl implements IWordsTypeService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WordsTypeServiceImpl.class);

	@Resource
	private IWordsTypeDao wordsTypeDao;
	@Resource
	private IWordsInfoService wordsInfoService;
	
	@Override
	public AddWordsTypeReturn addWordsType(
			AddWordsType addWordsType) throws TsfaServiceException {
		logger.debug("addWordsType(AddWordsType addWordsType={}) - start", addWordsType); 

		AssertUtils.notNull(addWordsType);
		try {
			WordsType wordsType = new WordsType();
			//add数据录入
			wordsType.setCode(GUID.generateByUUID());
			wordsType.setMerchantNo(addWordsType.getMerchantNo());
			wordsType.setMemberNoGm(addWordsType.getMemberNoGm());
			wordsType.setTypeName(addWordsType.getTypeName());
			wordsType.setSeq(addWordsType.getSeq());
			wordsType.setCreateId(addWordsType.getCreateId());
			wordsType.setCreateDate(new Date());
			wordsType.setRemark(addWordsType.getRemark());
			wordsType.setRemark2(addWordsType.getRemark2());
			wordsType.setRemark3(addWordsType.getRemark3());
			wordsType.setRemark4(addWordsType.getRemark4());
			wordsTypeDao.insertSelective(wordsType);
			AddWordsTypeReturn addWordsTypeReturn = new AddWordsTypeReturn();
			addWordsTypeReturn.setCode(wordsType.getCode());
			logger.debug("addWordsType(AddWordsType) - end - return value={}", addWordsTypeReturn); 
			return addWordsTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增话术类型信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_ADD_ERROR,"新增话术类型信息错误！",e);
		}
	}
	
	
	@Override
	public DelWordsTypeReturn delWordsType(
			DelWordsType delWordsType) throws TsfaServiceException {
		logger.debug("delWordsType(DelWordsType delWordsType={}) - start", delWordsType); 

		AssertUtils.notNull(delWordsType);
		AssertUtils.notNullAndEmpty(delWordsType.getCode(),"CODE不能为空！");
		try {
			wordsTypeDao.deleteByPrimaryKey(delWordsType.getCode());
			DelWordsTypeReturn delWordsTypeReturn  = new DelWordsTypeReturn();
			
			//删除话术类型,需同步删除属于该话术类型的所有话术信息
			wordsInfoService.deleteWordsInfoByTypeCode(delWordsType.getCode());

			logger.debug("delWordsType(DelWordsType) - end - return value={}", delWordsTypeReturn); 
			return delWordsTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除话术类型信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_DEL_ERROR,"删除话术类型信息错误！",e);

		}
	}

	@Override
	public UpdateWordsTypeReturn updateWordsType(
			UpdateWordsType updateWordsType, FindWordsTypeReturn findWordsTypeReturn)
			throws TsfaServiceException {
		logger.debug("updateWordsType(UpdateWordsType updateWordsType={}) - start", updateWordsType); 

		AssertUtils.notNull(updateWordsType);
		AssertUtils.notNull(findWordsTypeReturn);
		AssertUtils.notNullAndEmpty(updateWordsType.getCode(),"CODE不能为空");
		try {
			WordsType wordsType = new WordsType();
			//update数据录入
			wordsType.setCode(updateWordsType.getCode());
			wordsType.setMerchantNo(updateWordsType.getMerchantNo());
			wordsType.setMemberNoGm(updateWordsType.getMemberNoGm());
			wordsType.setTypeName(updateWordsType.getTypeName());
			wordsType.setSeq(updateWordsType.getSeq());
			wordsType.setCreateId(updateWordsType.getCreateId());
			wordsType.setCreateDate(updateWordsType.getCreateDate());
			wordsType.setRemark(updateWordsType.getRemark());
			wordsType.setRemark2(updateWordsType.getRemark2());
			wordsType.setRemark3(updateWordsType.getRemark3());
			wordsType.setRemark4(updateWordsType.getRemark4());
			AssertUtils.notUpdateMoreThanOne(wordsTypeDao.updateByPrimaryKeySelective(wordsType));
			UpdateWordsTypeReturn updateWordsTypeReturn = new UpdateWordsTypeReturn();
			
			//如果本次话术类型表类型名称有变动,则同步更新话术信息表类型名称
			if(StringUtils.isNotEmpty(updateWordsType.getTypeName()) && !updateWordsType.getTypeName().equals(findWordsTypeReturn.getTypeName())){
				UpdateWordsInfo updateWordsInfo=new UpdateWordsInfo();
				updateWordsInfo.setTypeCode(updateWordsType.getCode());
				updateWordsInfo.setTypeName(updateWordsType.getTypeName());
				wordsInfoService.updateTypeName(updateWordsInfo);
			}

			logger.debug("updateWordsType(UpdateWordsType) - end - return value={}", updateWordsTypeReturn); 
			return updateWordsTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("话术类型信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_UPDATE_ERROR,"话术类型信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindWordsTypeReturn findWordsType(
			FindWordsType findWordsType) throws TsfaServiceException {
		logger.debug("findWordsType(FindWordsType findWordsType={}) - start", findWordsType); 

		AssertUtils.notNull(findWordsType);
		AssertUtils.notNullAndEmpty(findWordsType.getCode(),"CODE不能为空");
		try {
			WordsType wordsType = wordsTypeDao.selectByPrimaryKey(findWordsType.getCode());
			if(wordsType == null){
				throw new TsfaServiceException(ErrorCode.WORDS_TYPE_NOT_EXIST_ERROR,"话术类型信息不存在");
			}
			FindWordsTypeReturn findWordsTypeReturn = new FindWordsTypeReturn();
			//find数据录入
			findWordsTypeReturn.setCode(wordsType.getCode());
			findWordsTypeReturn.setMerchantNo(wordsType.getMerchantNo());
			findWordsTypeReturn.setTypeName(wordsType.getTypeName());
			findWordsTypeReturn.setSeq(wordsType.getSeq());
			findWordsTypeReturn.setCreateId(wordsType.getCreateId());
			findWordsTypeReturn.setCreateDate(wordsType.getCreateDate());
			findWordsTypeReturn.setRemark(wordsType.getRemark());
			findWordsTypeReturn.setRemark2(wordsType.getRemark2());
			findWordsTypeReturn.setRemark3(wordsType.getRemark3());
			findWordsTypeReturn.setRemark4(wordsType.getRemark4());
			
			logger.debug("findWordsType(FindWordsType) - end - return value={}", findWordsTypeReturn); 
			return findWordsTypeReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找话术类型信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_FIND_ERROR,"查找话术类型信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindWordsTypePageReturn> findWordsTypePage(
			FindWordsTypePage findWordsTypePage)
			throws TsfaServiceException {
		logger.debug("findWordsTypePage(FindWordsTypePage findWordsTypePage={}) - start", findWordsTypePage); 

		AssertUtils.notNull(findWordsTypePage);
		List<FindWordsTypePageReturn> returnList = null;
		int count = 0;
		try {
			returnList = wordsTypeDao.findWordsTypePage(findWordsTypePage);
			count = wordsTypeDao.findWordsTypePageCount(findWordsTypePage);
		}  catch (Exception e) {
			logger.error("话术类型信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_FIND_PAGE_ERROR,"话术类型信息不存在错误.！",e);
		}
		Page<FindWordsTypePageReturn> returnPage = new Page<FindWordsTypePageReturn>(returnList, count, findWordsTypePage);

		logger.debug("findWordsTypePage(FindWordsTypePage) - end - return value={}", returnPage); 
		return  returnPage;
	}

	@Override
	public List<FindWordsAppReturn> findWords(FindWordsInfoApp findWordsInfoApp)
			throws TsfaServiceException {
		logger.debug("findWords(FindWordsInfoApp findWordsInfoApp={}) - start", findWordsInfoApp); 

		AssertUtils.notNull(findWordsInfoApp);
		AssertUtils.notNullAndEmpty(findWordsInfoApp.getMerchantNo(),"商户编号不能为空");
		List<FindWordsAppReturn> returnList = null;
		try {
			returnList = wordsTypeDao.findWords(findWordsInfoApp);
		}  catch (Exception e) {
			logger.error("话术管理信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_FIND_PAGE_ERROR,"话术管理信息不存在错误.！",e);
		}

		logger.debug("findWords(findWordsInfoApp) - end - return value={}", returnList); 
		return  returnList;
	}


	@Override
	public List<FindWordsTypeSelectReturn> findWordsTypes(
			FindWordsInfoWeb findWordsInfoWeb) {
		logger.debug("findWordsTypes(FindWordsInfoWeb findWordsInfoWeb={}) - start", findWordsInfoWeb); 

		AssertUtils.notNull(findWordsInfoWeb);
		//AssertUtils.notNullAndEmpty(findWordsInfoWeb.getMerchantNo(),"商户编号不能为空");
		List<FindWordsTypeSelectReturn> returnList = null;
		try {
			returnList = wordsTypeDao.findWordsTypes(findWordsInfoWeb);
		}  catch (Exception e) {
			logger.error("话术管理信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_FIND_PAGE_ERROR,"话术管理信息不存在错误.！",e);
		}
		logger.debug("findWordsTypes(findWordsInfoWeb) - end - return value={}", returnList); 
		return  returnList;
	}

	@Override
	public int hasTypeName(String memberNoGm,String typeName) {
		AssertUtils.notNullAndEmpty(memberNoGm,"导购编号不能为空");
		AssertUtils.notNullAndEmpty(typeName,"类型名称不能为空");
		return wordsTypeDao.hasTypeName(memberNoGm,typeName);
	}
	
	@Override
	public int hasSeq(String memberNoGm,Integer seq) {
		AssertUtils.notNullAndEmpty(memberNoGm,"导购编号不能为空");
		AssertUtils.notNullAndEmpty(seq,"排序不能为空");
		return wordsTypeDao.hasSeq(memberNoGm,seq);
	}


	@Override
	public int incrementTypeCountByPrimaryKey(String code, Integer increment) {
		logger.debug("incrementTypeCountByPrimaryKey(increment={}) - start", increment);
		if(increment==null || increment<=0){
			increment=1;
		}
		return wordsTypeDao.incrementTypeCountByPrimaryKey(code,increment);
	}


	@Override
	public List<FindWordsAppReturn> findPersonWords(FindWordsTypePage findWordsTypePage) {
		return wordsTypeDao.findPersonWords(findWordsTypePage);
	}


	
}
