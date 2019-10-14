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
import com.ye.business.ad.constant.ErrorCodeBeansOrder;
import com.ye.business.ad.dao.IBeansOrderDao;
import com.ye.business.ad.domain.BeansOrder;
import com.ye.business.ad.dto.BeansOrderDto;
import com.ye.business.ad.dto.FindBeansOrderPage;
import com.ye.business.ad.service.IBeansOrderService;
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
public class BeansOrderServiceImpl implements IBeansOrderService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(BeansOrderServiceImpl.class);
	

	@Resource
	private IBeansOrderDao beansOrderDao;
	
	
	@Override
	public String addBeansOrder(
			BeansOrderDto beansOrderDto) throws TsfaServiceException {
		logger.debug("addBeansOrder(AddBeansOrder addBeansOrder={}) - start", beansOrderDto); 

		AssertUtils.notNull(beansOrderDto);
		try {
			BeansOrder beansOrder = new BeansOrder();
			//add数据录入
			beansOrder.setCode(beansOrderDto.getCode());
			beansOrder.setShopNo(beansOrderDto.getShopNo());
			beansOrder.setShopName(beansOrderDto.getShopName());
			beansOrder.setMerchantNo(beansOrderDto.getMerchantNo());
			beansOrder.setMerchantName(beansOrderDto.getMerchantName());
			beansOrder.setOrderNo(beansOrderDto.getOrderNo());
			beansOrder.setOrderType(beansOrderDto.getOrderType());
			beansOrder.setServeCode(beansOrderDto.getServeCode());
			beansOrder.setServeName(beansOrderDto.getServeName());
			beansOrder.setMemberNoGuid(beansOrderDto.getMemberNoGuid());
			beansOrder.setMemberNameGuid(beansOrderDto.getMemberNameGuid());
			beansOrder.setMobile(beansOrderDto.getMobile());
			beansOrder.setSerialNum(beansOrderDto.getSerialNum());
			beansOrder.setPayType(beansOrderDto.getPayType());
			beansOrder.setAmount(beansOrderDto.getAmount());
			beansOrder.setCredits(beansOrderDto.getCredits());
			beansOrder.setPayTime(beansOrderDto.getPayTime());
			beansOrder.setPayCert(beansOrderDto.getPayCert());
			beansOrder.setStatus(beansOrderDto.getStatus());
			beansOrder.setUpdateId(beansOrderDto.getUpdateId());
			beansOrder.setUpdateDate(beansOrderDto.getUpdateDate());
			beansOrder.setCreateId(beansOrderDto.getCreateId());
			beansOrder.setCreateDate(beansOrderDto.getCreateDate());
			beansOrder.setRemark(beansOrderDto.getRemark());
			beansOrder.setRemark2(beansOrderDto.getRemark2());
			beansOrder.setRemark3(beansOrderDto.getRemark3());
			
			beansOrder.setCode(GUID.getPreUUID());
			beansOrderDao.insertSelective(beansOrder);
			logger.debug("addBeansOrder(BeansOrderDto) - end - return"); 
			return beansOrder.getCode();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增豆子充值记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeBeansOrder.BEANS_ORDER_ADD_ERROR, "新增豆子充值记录信息错误！", e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询豆子充值记录信息
	 *
	 * @param findBeansOrderPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<BeansOrderDto>  findBeansOrders(FindBeansOrderPage findBeansOrderPage)throws TsfaServiceException{
		AssertUtils.notNull(findBeansOrderPage);
		List<BeansOrderDto> returnList=null;
		try {
			returnList = beansOrderDao.findBeansOrders(findBeansOrderPage);
		} catch (Exception e) {
			logger.error("查找豆子充值记录信息信息错误！",  e);
			throw new TsfaServiceException(ErrorCodeBeansOrder.BEANS_ORDER_NOT_EXIST_ERROR, "豆子充值记录信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateBeansOrder(
			BeansOrderDto beansOrderDto)
			throws TsfaServiceException {
		logger.debug("updateBeansOrder(BeansOrderDto beansOrderDto={}) - start", beansOrderDto); 

		AssertUtils.notNull(beansOrderDto);
		AssertUtils.notNullAndEmpty(beansOrderDto.getCode(),"Code不能为空");
		try {
			BeansOrder beansOrder = new BeansOrder();
			//update数据录入
			beansOrder.setCode(beansOrderDto.getCode());
			beansOrder.setShopNo(beansOrderDto.getShopNo());
			beansOrder.setShopName(beansOrderDto.getShopName());
			beansOrder.setMerchantNo(beansOrderDto.getMerchantNo());
			beansOrder.setMerchantName(beansOrderDto.getMerchantName());
			beansOrder.setOrderNo(beansOrderDto.getOrderNo());
			beansOrder.setOrderType(beansOrderDto.getOrderType());
			beansOrder.setServeCode(beansOrderDto.getServeCode());
			beansOrder.setServeName(beansOrderDto.getServeName());
			beansOrder.setMemberNoGuid(beansOrderDto.getMemberNoGuid());
			beansOrder.setMemberNameGuid(beansOrderDto.getMemberNameGuid());
			beansOrder.setMobile(beansOrderDto.getMobile());
			beansOrder.setSerialNum(beansOrderDto.getSerialNum());
			beansOrder.setPayType(beansOrderDto.getPayType());
			beansOrder.setAmount(beansOrderDto.getAmount());
			beansOrder.setPayTime(beansOrderDto.getPayTime());
			beansOrder.setPayCert(beansOrderDto.getPayCert());
			beansOrder.setStatus(beansOrderDto.getStatus());
			beansOrder.setUpdateId(beansOrderDto.getUpdateId());
			beansOrder.setUpdateDate(beansOrderDto.getUpdateDate());
			beansOrder.setCreateId(beansOrderDto.getCreateId());
			beansOrder.setCreateDate(beansOrderDto.getCreateDate());
			beansOrder.setRemark(beansOrderDto.getRemark());
			beansOrder.setRemark2(beansOrderDto.getRemark2());
			beansOrder.setRemark3(beansOrderDto.getRemark3());
			AssertUtils.notUpdateMoreThanOne(beansOrderDao.updateByPrimaryKeySelective(beansOrder));
			logger.debug("updateBeansOrder(BeansOrderDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("豆子充值记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeBeansOrder.BEANS_ORDER_UPDATE_ERROR, "豆子充值记录信息更新信息错误！", e);
		}
	}

	

	@Override
	public BeansOrderDto findBeansOrder(
			BeansOrderDto beansOrderDto) throws TsfaServiceException {
		logger.debug("findBeansOrder(FindBeansOrder findBeansOrder={}) - start", beansOrderDto); 

		AssertUtils.notNull(beansOrderDto);
		AssertUtils.notAllNull(beansOrderDto.getCode(),"Code不能为空");
		try {
			BeansOrder beansOrder = beansOrderDao.selectByPrimaryKey(beansOrderDto.getCode());
			if(beansOrder == null){
				return null;
				//throw new TsfaServiceException(ErrorCodeBeansOrder.BEANS_ORDER_NOT_EXIST_ERROR,"豆子充值记录信息不存在");
			}
			BeansOrderDto findBeansOrderReturn = new BeansOrderDto();
			//find数据录入
			findBeansOrderReturn.setCode(beansOrder.getCode());
			findBeansOrderReturn.setShopNo(beansOrder.getShopNo());
			findBeansOrderReturn.setShopName(beansOrder.getShopName());
			findBeansOrderReturn.setMerchantNo(beansOrder.getMerchantNo());
			findBeansOrderReturn.setMerchantName(beansOrder.getMerchantName());
			findBeansOrderReturn.setOrderNo(beansOrder.getOrderNo());
			findBeansOrderReturn.setOrderType(beansOrder.getOrderType());
			findBeansOrderReturn.setServeCode(beansOrder.getServeCode());
			findBeansOrderReturn.setServeName(beansOrder.getServeName());
			findBeansOrderReturn.setMemberNoGuid(beansOrder.getMemberNoGuid());
			findBeansOrderReturn.setMemberNameGuid(beansOrder.getMemberNameGuid());
			findBeansOrderReturn.setMobile(beansOrder.getMobile());
			findBeansOrderReturn.setSerialNum(beansOrder.getSerialNum());
			findBeansOrderReturn.setPayType(beansOrder.getPayType());
			findBeansOrderReturn.setAmount(beansOrder.getAmount());
			findBeansOrderReturn.setPayTime(beansOrder.getPayTime());
			findBeansOrderReturn.setPayCert(beansOrder.getPayCert());
			findBeansOrderReturn.setStatus(beansOrder.getStatus());
			findBeansOrderReturn.setUpdateId(beansOrder.getUpdateId());
			findBeansOrderReturn.setUpdateDate(beansOrder.getUpdateDate());
			findBeansOrderReturn.setCreateId(beansOrder.getCreateId());
			findBeansOrderReturn.setCreateDate(beansOrder.getCreateDate());
			findBeansOrderReturn.setRemark(beansOrder.getRemark());
			findBeansOrderReturn.setRemark2(beansOrder.getRemark2());
			findBeansOrderReturn.setRemark3(beansOrder.getRemark3());
			
			logger.debug("findBeansOrder(BeansOrderDto) - end - return value={}", findBeansOrderReturn); 
			return findBeansOrderReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找豆子充值记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeBeansOrder.BEANS_ORDER_FIND_ERROR, "查找豆子充值记录信息信息错误！", e);
		}


	}

	
	@Override
	public Page<BeansOrderDto> findBeansOrderPage(
			FindBeansOrderPage findBeansOrderPage)
			throws TsfaServiceException {
		logger.debug("findBeansOrderPage(FindBeansOrderPage findBeansOrderPage={}) - start", findBeansOrderPage); 

		AssertUtils.notNull(findBeansOrderPage);
		List<BeansOrderDto> returnList=null;
		int count = 0;
		try {
			returnList = beansOrderDao.findBeansOrderPage(findBeansOrderPage);
			count = beansOrderDao.findBeansOrderPageCount(findBeansOrderPage);
		}  catch (Exception e) {
			logger.error("豆子充值记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeBeansOrder.BEANS_ORDER_FIND_PAGE_ERROR, "豆子充值记录信息不存在错误.！", e);
		}
		Page<BeansOrderDto> returnPage = new Page<BeansOrderDto>(returnList, count, findBeansOrderPage);

		logger.debug("findBeansOrderPage(FindBeansOrderPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(beansOrderDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("豆子充值记录信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeBeansOrder.BEANS_ORDER_UPDATE_ERROR, "豆子充值记录信息刪除信息错误！", e);
		}
	}


}
