package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.io.IOException;
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
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IGuidCardCustomerDao;
import com.lj.business.member.domain.GuidCardCustomer;
import com.lj.business.member.dto.guidCardCustomer.AddGuidCardCustomer;
import com.lj.business.member.dto.guidCardCustomer.DelGuidCardCustomer;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomer;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomerPage;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomerPageReturn;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomerReturn;
import com.lj.business.member.dto.guidCardCustomer.GuidCardAddNumDto;
import com.lj.business.member.dto.guidCardCustomer.UpdateGuidCardCustomer;
import com.lj.business.member.service.IGuidCardCustomerService;
import com.lj.business.member.utils.WeiChatUtils;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 梅宏博
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class GuidCardCustomerServiceImpl implements IGuidCardCustomerService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GuidCardCustomerServiceImpl.class);
	

	@Resource
	private IGuidCardCustomerDao guidCardCustomerDao;
	
	
	@Override
	public void addGuidCardCustomer(
			AddGuidCardCustomer addGuidCardCustomer) throws TsfaServiceException {
		logger.debug("addGuidCardCustomer(AddGuidCardCustomer addGuidCardCustomer={}) - start", addGuidCardCustomer); 

		AssertUtils.notNull(addGuidCardCustomer);
		try {
			GuidCardCustomer guidCardCustomer = new GuidCardCustomer();
			//add数据录入
			guidCardCustomer.setCode(GUID.getPreUUID());
			guidCardCustomer.setGuidCardCode(addGuidCardCustomer.getGuidCardCode());
			guidCardCustomer.setOpenId(addGuidCardCustomer.getOpenId());
			guidCardCustomer.setType(addGuidCardCustomer.getType());
			guidCardCustomer.setCreateDate(new Date());
			guidCardCustomerDao.insert(guidCardCustomer);
			logger.debug("addGuidCardCustomer(AddGuidCardCustomer) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增导购名片与客户关联表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_CUSTOMER_ADD_ERROR,"新增导购名片与客户关联表信息错误！",e);
		}
	}
	
	
	@Override
	public void delGuidCardCustomer(
			DelGuidCardCustomer delGuidCardCustomer) throws TsfaServiceException {
		logger.debug("delGuidCardCustomer(DelGuidCardCustomer delGuidCardCustomer={}) - start", delGuidCardCustomer); 

		AssertUtils.notNull(delGuidCardCustomer);
		AssertUtils.notNull(delGuidCardCustomer.getCode(),"Code不能为空！");
		try {
			guidCardCustomerDao.deleteByPrimaryKey(delGuidCardCustomer.getCode());
			logger.debug("delGuidCardCustomer(DelGuidCardCustomer) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除导购名片与客户关联表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_CUSTOMER_DEL_ERROR,"删除导购名片与客户关联表信息错误！",e);

		}
	}

	@Override
	public void updateGuidCardCustomer(
			UpdateGuidCardCustomer updateGuidCardCustomer)
			throws TsfaServiceException {
		logger.debug("updateGuidCardCustomer(UpdateGuidCardCustomer updateGuidCardCustomer={}) - start", updateGuidCardCustomer); 

		AssertUtils.notNull(updateGuidCardCustomer);
		AssertUtils.notNullAndEmpty(updateGuidCardCustomer.getCode(),"Code不能为空");
		try {
			GuidCardCustomer guidCardCustomer = new GuidCardCustomer();
			//update数据录入
			guidCardCustomer.setCode(updateGuidCardCustomer.getCode());
			guidCardCustomer.setGuidCardCode(updateGuidCardCustomer.getGuidCardCode());
			guidCardCustomer.setOpenId(updateGuidCardCustomer.getOpenId());
			guidCardCustomer.setType(updateGuidCardCustomer.getType());
			guidCardCustomer.setCreateDate(updateGuidCardCustomer.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(guidCardCustomerDao.updateByPrimaryKeySelective(guidCardCustomer));
			logger.debug("updateGuidCardCustomer(UpdateGuidCardCustomer) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("导购名片与客户关联表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_CUSTOMER_UPDATE_ERROR,"导购名片与客户关联表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindGuidCardCustomerReturn findGuidCardCustomer(
			FindGuidCardCustomer findGuidCardCustomer) throws TsfaServiceException {
		logger.debug("findGuidCardCustomer(FindGuidCardCustomer findGuidCardCustomer={}) - start", findGuidCardCustomer); 

		AssertUtils.notNull(findGuidCardCustomer);
		AssertUtils.notAllNull(findGuidCardCustomer.getCode(),"Code不能为空");
		try {
			GuidCardCustomer guidCardCustomer = guidCardCustomerDao.selectByPrimaryKey(findGuidCardCustomer.getCode());
			if(guidCardCustomer == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.GUID_CARD_CUSTOMER_NOT_EXIST_ERROR,"导购名片与客户关联表信息不存在");
			}
			FindGuidCardCustomerReturn findGuidCardCustomerReturn = new FindGuidCardCustomerReturn();
			//find数据录入
			findGuidCardCustomerReturn.setCode(guidCardCustomer.getCode());
			findGuidCardCustomerReturn.setGuidCardCode(guidCardCustomer.getGuidCardCode());
			findGuidCardCustomerReturn.setOpenId(guidCardCustomer.getOpenId());
			findGuidCardCustomerReturn.setType(guidCardCustomer.getType());
			findGuidCardCustomerReturn.setCreateDate(guidCardCustomer.getCreateDate());
			
			logger.debug("findGuidCardCustomer(FindGuidCardCustomer) - end - return value={}", findGuidCardCustomerReturn); 
			return findGuidCardCustomerReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购名片与客户关联表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_CUSTOMER_FIND_ERROR,"查找导购名片与客户关联表信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindGuidCardCustomerPageReturn> findGuidCardCustomerPage(
			FindGuidCardCustomerPage findGuidCardCustomerPage)
			throws TsfaServiceException {
		logger.debug("findGuidCardCustomerPage(FindGuidCardCustomerPage findGuidCardCustomerPage={}) - start", findGuidCardCustomerPage); 

		AssertUtils.notNull(findGuidCardCustomerPage);
		List<FindGuidCardCustomerPageReturn> returnList;
		int count = 0;
		try {
			returnList = guidCardCustomerDao.findGuidCardCustomerPage(findGuidCardCustomerPage);
			count = guidCardCustomerDao.findGuidCardCustomerPageCount(findGuidCardCustomerPage);
		}  catch (Exception e) {
			logger.error("导购名片与客户关联表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_CUSTOMER_FIND_PAGE_ERROR,"导购名片与客户关联表信息不存在错误.！",e);
		}
		Page<FindGuidCardCustomerPageReturn> returnPage = new Page<FindGuidCardCustomerPageReturn>(returnList, count, findGuidCardCustomerPage);

		logger.debug("findGuidCardCustomerPage(FindGuidCardCustomerPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public int addGuidCardCustomerByOpenId(GuidCardAddNumDto guidCardAddNumDto) {
		logger.debug("addGuidCardCustomerByOpenId(GuidCardAddNumDto guidCardAddNumDto={}) - start", guidCardAddNumDto); 
		FindGuidCardCustomer findGuidCardCustomer = new FindGuidCardCustomer();
		findGuidCardCustomer.setGuidCardCode(guidCardAddNumDto.getGuidCardCode());
		String openId;
		try {
			openId = WeiChatUtils.openIdDecrypt(guidCardAddNumDto.getSignature());
		} catch (IOException e) {
			logger.error("查询openId错误",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_FIND_OPENID_ERROR,"查找导购名片表信息信息错误！",e);
		}
		findGuidCardCustomer.setOpenId(openId);
		findGuidCardCustomer.setType(guidCardAddNumDto.getType());
		List<FindGuidCardCustomerReturn> guidCardCustomer = findGuidCardCustomerSelect(findGuidCardCustomer);
		if (guidCardCustomer.size() > 0) {
			return 0;
		}
		AddGuidCardCustomer addGuidCardCustomer = new AddGuidCardCustomer();
		addGuidCardCustomer.setGuidCardCode(guidCardAddNumDto.getGuidCardCode());
		addGuidCardCustomer.setOpenId(openId);
		addGuidCardCustomer.setType(guidCardAddNumDto.getType());
		addGuidCardCustomer(addGuidCardCustomer);
		return 1;
	}

	@Override
	public List<FindGuidCardCustomerReturn> findGuidCardCustomerSelect(
			FindGuidCardCustomer findGuidCardCustomer) {
		logger.debug("findGuidCardCustomerSelect(FindGuidCardCustomer findGuidCardCustomer={}) - start", findGuidCardCustomer); 
		return guidCardCustomerDao.findGuidCardCustomerSelect(findGuidCardCustomer);
	}


	@Override
	public int delGuidCardCustomerSelect(
			DelGuidCardCustomer delGuidCardCustomer) {
		logger.debug("delGuidCardCustomerSelect(DelGuidCardCustomer delGuidCardCustomer={}) - start", delGuidCardCustomer); 
		return guidCardCustomerDao.delGuidCardCustomerSelect(delGuidCardCustomer);
	} 


}
