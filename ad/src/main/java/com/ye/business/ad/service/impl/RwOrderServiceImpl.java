package com.ye.business.ad.service.impl;

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
import com.ye.business.ad.constant.ErrorCodeRwOrder;
import com.ye.business.ad.dao.IRwOrderDao;
import com.ye.business.ad.domain.RwOrder;
import com.ye.business.ad.dto.FindRwOrderPage;
import com.ye.business.ad.dto.RwOrderDto;
import com.ye.business.ad.service.IRwOrderService;
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
public class RwOrderServiceImpl implements IRwOrderService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(RwOrderServiceImpl.class);
	

	@Resource
	private IRwOrderDao rwOrderDao;
	
	
	@Override
	public String addRwOrder(
			RwOrderDto rwOrderDto) throws TsfaServiceException {
		logger.debug("addRwOrder(AddRwOrder addRwOrder={}) - start", rwOrderDto); 

		AssertUtils.notNull(rwOrderDto);
		try {
			RwOrder rwOrder = new RwOrder();
			//add数据录入
			rwOrder.setLoginName(rwOrderDto.getLoginName());
			rwOrder.setCode(rwOrderDto.getCode());
			rwOrder.setShopNo(rwOrderDto.getShopNo());
			rwOrder.setShopName(rwOrderDto.getShopName());
			rwOrder.setMerchantNo(rwOrderDto.getMerchantNo());
			rwOrder.setMerchantName(rwOrderDto.getMerchantName());
			rwOrder.setOrderNo(rwOrderDto.getOrderNo());
			rwOrder.setOrderType(rwOrderDto.getOrderType());
			rwOrder.setServeCode(rwOrderDto.getServeCode());
			rwOrder.setServeName(rwOrderDto.getServeName());
			rwOrder.setMemberNoGuid(rwOrderDto.getMemberNoGuid());
			rwOrder.setMemberNameGuid(rwOrderDto.getMemberNameGuid());
			rwOrder.setMobile(rwOrderDto.getMobile());
			rwOrder.setSerialNum(rwOrderDto.getSerialNum());
			rwOrder.setPayType(rwOrderDto.getPayType());
			rwOrder.setAmount(rwOrderDto.getAmount());
			rwOrder.setPayTime(rwOrderDto.getPayTime());
			rwOrder.setPayCert(rwOrderDto.getPayCert());
			rwOrder.setStatus(rwOrderDto.getStatus());
			rwOrder.setUpdateId(rwOrderDto.getUpdateId());
			rwOrder.setUpdateDate(rwOrderDto.getUpdateDate());
			rwOrder.setCreateId(rwOrderDto.getCreateId());
			rwOrder.setCreateDate(rwOrderDto.getCreateDate());
			rwOrder.setRemark(rwOrderDto.getRemark());
			rwOrder.setRemark2(rwOrderDto.getRemark2());
			rwOrder.setRemark3(rwOrderDto.getRemark3());
			
			rwOrder.setCode(GUID.getPreUUID());
			rwOrderDao.insertSelective(rwOrder);
			logger.debug("addRwOrder(RwOrderDto) - end - return"); 
			return rwOrder.getCode();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增热文用户订单记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwOrder.RW_ORDER_ADD_ERROR, "新增热文用户订单记录信息错误！", e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询热文用户订单记录信息
	 *
	 * @param findRwOrderPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<RwOrderDto>  findRwOrders(FindRwOrderPage findRwOrderPage)throws TsfaServiceException{
		AssertUtils.notNull(findRwOrderPage);
		List<RwOrderDto> returnList=null;
		try {
			returnList = rwOrderDao.findRwOrders(findRwOrderPage);
		} catch (Exception e) {
			logger.error("查找热文用户订单记录信息信息错误！",  e);
			throw new TsfaServiceException(ErrorCodeRwOrder.RW_ORDER_NOT_EXIST_ERROR, "热文用户订单记录信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateRwOrder(
			RwOrderDto rwOrderDto)
			throws TsfaServiceException {
		logger.debug("updateRwOrder(RwOrderDto rwOrderDto={}) - start", rwOrderDto); 

		AssertUtils.notNull(rwOrderDto);
		AssertUtils.notNullAndEmpty(rwOrderDto.getCode(),"Code不能为空");
		try {
			RwOrder rwOrder = new RwOrder();
			//update数据录入
			rwOrder.setCode(rwOrderDto.getCode());
			rwOrder.setShopNo(rwOrderDto.getShopNo());
			rwOrder.setShopName(rwOrderDto.getShopName());
			rwOrder.setMerchantNo(rwOrderDto.getMerchantNo());
			rwOrder.setMerchantName(rwOrderDto.getMerchantName());
			rwOrder.setOrderNo(rwOrderDto.getOrderNo());
			rwOrder.setOrderType(rwOrderDto.getOrderType());
			rwOrder.setServeCode(rwOrderDto.getServeCode());
			rwOrder.setServeName(rwOrderDto.getServeName());
			rwOrder.setMemberNoGuid(rwOrderDto.getMemberNoGuid());
			rwOrder.setMemberNameGuid(rwOrderDto.getMemberNameGuid());
			rwOrder.setMobile(rwOrderDto.getMobile());
			rwOrder.setSerialNum(rwOrderDto.getSerialNum());
			rwOrder.setPayType(rwOrderDto.getPayType());
			rwOrder.setAmount(rwOrderDto.getAmount());
			rwOrder.setPayTime(rwOrderDto.getPayTime());
			rwOrder.setPayCert(rwOrderDto.getPayCert());
			rwOrder.setStatus(rwOrderDto.getStatus());
			rwOrder.setUpdateId(rwOrderDto.getUpdateId());
			rwOrder.setUpdateDate(rwOrderDto.getUpdateDate());
			rwOrder.setCreateId(rwOrderDto.getCreateId());
			rwOrder.setCreateDate(rwOrderDto.getCreateDate());
			rwOrder.setRemark(rwOrderDto.getRemark());
			rwOrder.setRemark2(rwOrderDto.getRemark2());
			rwOrder.setRemark3(rwOrderDto.getRemark3());
			AssertUtils.notUpdateMoreThanOne(rwOrderDao.updateByPrimaryKeySelective(rwOrder));
			logger.debug("updateRwOrder(RwOrderDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("热文用户订单记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwOrder.RW_ORDER_UPDATE_ERROR, "热文用户订单记录信息更新信息错误！", e);
		}
	}

	

	@Override
	public RwOrderDto findRwOrder(
			RwOrderDto rwOrderDto) throws TsfaServiceException {
		logger.debug("findRwOrder(FindRwOrder findRwOrder={}) - start", rwOrderDto); 

		AssertUtils.notNull(rwOrderDto);
		AssertUtils.notAllNull(rwOrderDto.getCode(),"Code不能为空");
		try {
			RwOrder rwOrder = rwOrderDao.selectByPrimaryKey(rwOrderDto.getCode());
			if(rwOrder == null){
				return null;
				//throw new TsfaServiceException(ErrorCodeRwOrder.RW_ORDER_NOT_EXIST_ERROR,"热文用户订单记录信息不存在");
			}
			RwOrderDto findRwOrderReturn = new RwOrderDto();
			//find数据录入
			findRwOrderReturn.setCode(rwOrder.getCode());
			findRwOrderReturn.setShopNo(rwOrder.getShopNo());
			findRwOrderReturn.setShopName(rwOrder.getShopName());
			findRwOrderReturn.setMerchantNo(rwOrder.getMerchantNo());
			findRwOrderReturn.setMerchantName(rwOrder.getMerchantName());
			findRwOrderReturn.setOrderNo(rwOrder.getOrderNo());
			findRwOrderReturn.setOrderType(rwOrder.getOrderType());
			findRwOrderReturn.setServeCode(rwOrder.getServeCode());
			findRwOrderReturn.setServeName(rwOrder.getServeName());
			findRwOrderReturn.setMemberNoGuid(rwOrder.getMemberNoGuid());
			findRwOrderReturn.setMemberNameGuid(rwOrder.getMemberNameGuid());
			findRwOrderReturn.setMobile(rwOrder.getMobile());
			findRwOrderReturn.setSerialNum(rwOrder.getSerialNum());
			findRwOrderReturn.setPayType(rwOrder.getPayType());
			findRwOrderReturn.setAmount(rwOrder.getAmount());
			findRwOrderReturn.setPayTime(rwOrder.getPayTime());
			findRwOrderReturn.setPayCert(rwOrder.getPayCert());
			findRwOrderReturn.setStatus(rwOrder.getStatus());
			findRwOrderReturn.setUpdateId(rwOrder.getUpdateId());
			findRwOrderReturn.setUpdateDate(rwOrder.getUpdateDate());
			findRwOrderReturn.setCreateId(rwOrder.getCreateId());
			findRwOrderReturn.setCreateDate(rwOrder.getCreateDate());
			findRwOrderReturn.setRemark(rwOrder.getRemark());
			findRwOrderReturn.setRemark2(rwOrder.getRemark2());
			findRwOrderReturn.setRemark3(rwOrder.getRemark3());
			
			logger.debug("findRwOrder(RwOrderDto) - end - return value={}", findRwOrderReturn); 
			return findRwOrderReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找热文用户订单记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwOrder.RW_ORDER_FIND_ERROR, "查找热文用户订单记录信息信息错误！", e);
		}


	}

	
	@Override
	public Page<RwOrderDto> findRwOrderPage(
			FindRwOrderPage findRwOrderPage)
			throws TsfaServiceException {
		logger.debug("findRwOrderPage(FindRwOrderPage findRwOrderPage={}) - start", findRwOrderPage); 

		AssertUtils.notNull(findRwOrderPage);
		List<RwOrderDto> returnList=null;
		int count = 0;
		try {
			returnList = rwOrderDao.findRwOrderPage(findRwOrderPage);
			count = rwOrderDao.findRwOrderPageCount(findRwOrderPage);
		}  catch (Exception e) {
			logger.error("热文用户订单记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeRwOrder.RW_ORDER_FIND_PAGE_ERROR, "热文用户订单记录信息不存在错误.！", e);
		}
		Page<RwOrderDto> returnPage = new Page<RwOrderDto>(returnList, count, findRwOrderPage);

		logger.debug("findRwOrderPage(FindRwOrderPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(rwOrderDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("热文用户订单记录信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwOrder.RW_ORDER_UPDATE_ERROR, "热文用户订单记录信息刪除信息错误！", e);
		}
	}


}
