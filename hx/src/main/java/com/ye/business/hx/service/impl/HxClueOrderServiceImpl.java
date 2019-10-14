package com.ye.business.hx.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
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
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IHxClueOrderDao;
import com.ye.business.hx.domain.HxClueOrder;
import com.ye.business.hx.dto.FindHxClueOrderPage;
import com.ye.business.hx.dto.HxClueOrderDto;
import com.ye.business.hx.service.IHxClueOrderService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
@Service
public class HxClueOrderServiceImpl implements IHxClueOrderService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(HxClueOrderServiceImpl.class);
	

	@Resource
	private IHxClueOrderDao hxClueOrderDao;
	
	
	@Override
	public void addHxClueOrder(
			HxClueOrderDto hxClueOrderDto) throws TsfaServiceException {
		logger.debug("addHxClueOrder(AddHxClueOrder addHxClueOrder={}) - start", hxClueOrderDto); 

		AssertUtils.notNull(hxClueOrderDto);
		try {
			HxClueOrder hxClueOrder = new HxClueOrder();
			//add数据录入
			hxClueOrder.setCode(GUID.getPreUUID());
			hxClueOrder.setClueCode(hxClueOrderDto.getClueCode());
			hxClueOrder.setMemberNo(hxClueOrderDto.getMemberNo());
			hxClueOrder.setMemberName(hxClueOrderDto.getMemberName());
			hxClueOrder.setShopNo(hxClueOrderDto.getShopNo());
			hxClueOrder.setShopName(hxClueOrderDto.getShopName());
			hxClueOrder.setMerchantNo(hxClueOrderDto.getMerchantNo());
			hxClueOrder.setMerchantName(hxClueOrderDto.getMerchantName());
			hxClueOrder.setShopServerCode(hxClueOrderDto.getShopServerCode());
			hxClueOrder.setServerName(hxClueOrderDto.getServerName());
			hxClueOrder.setServerCode(hxClueOrderDto.getServerCode());
			hxClueOrder.setUserType(hxClueOrderDto.getUserType());
			hxClueOrder.setUserPrice(hxClueOrderDto.getUserPrice());
			hxClueOrder.setStatus(hxClueOrderDto.getStatus());
			hxClueOrder.setCreateId(hxClueOrderDto.getCreateId());
			hxClueOrder.setCreateDate(hxClueOrderDto.getCreateDate());
			hxClueOrder.setUpdateId(hxClueOrderDto.getUpdateId());
			hxClueOrder.setUpdateDate(hxClueOrderDto.getUpdateDate());
			hxClueOrder.setRemark(hxClueOrderDto.getRemark());
			hxClueOrder.setType(hxClueOrderDto.getType());
			hxClueOrderDao.insert(hxClueOrder);
			logger.debug("addHxClueOrder(HxClueOrderDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增线索使用订单信息错误！",e);
			throw new TsfaServiceException(ErrorCode.HX_CLUE_ORDER_ADD_ERROR,"新增线索使用订单信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询线索使用订单信息
	 *
	 * @param findHxClueOrderPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<HxClueOrderDto>  findHxClueOrders(FindHxClueOrderPage findHxClueOrderPage)throws TsfaServiceException{
		AssertUtils.notNull(findHxClueOrderPage);
		List<HxClueOrderDto> returnList=null;
		try {
			returnList = hxClueOrderDao.findHxClueOrders(findHxClueOrderPage);
		} catch (Exception e) {
			logger.error("查找线索使用订单信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.HX_CLUE_ORDER_NOT_EXIST_ERROR,"线索使用订单信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateHxClueOrder(
			HxClueOrderDto hxClueOrderDto)
			throws TsfaServiceException {
		logger.debug("updateHxClueOrder(HxClueOrderDto hxClueOrderDto={}) - start", hxClueOrderDto); //$NON-NLS-1$

		AssertUtils.notNull(hxClueOrderDto);
		AssertUtils.notNullAndEmpty(hxClueOrderDto.getCode(),"Code不能为空");
		try {
			HxClueOrder hxClueOrder = new HxClueOrder();
			//update数据录入
			hxClueOrder.setCode(hxClueOrderDto.getCode());
			hxClueOrder.setClueCode(hxClueOrderDto.getClueCode());
			hxClueOrder.setMemberNo(hxClueOrderDto.getMemberNo());
			hxClueOrder.setMemberName(hxClueOrderDto.getMemberName());
			hxClueOrder.setShopNo(hxClueOrderDto.getShopNo());
			hxClueOrder.setShopName(hxClueOrderDto.getShopName());
			hxClueOrder.setMerchantNo(hxClueOrderDto.getMerchantNo());
			hxClueOrder.setMerchantName(hxClueOrderDto.getMerchantName());
			hxClueOrder.setShopServerCode(hxClueOrderDto.getShopServerCode());
			hxClueOrder.setServerName(hxClueOrderDto.getServerName());
			hxClueOrder.setServerCode(hxClueOrderDto.getServerCode());
			hxClueOrder.setUserType(hxClueOrderDto.getUserType());
			hxClueOrder.setUserPrice(hxClueOrderDto.getUserPrice());
			hxClueOrder.setStatus(hxClueOrderDto.getStatus());
			hxClueOrder.setCreateId(hxClueOrderDto.getCreateId());
			hxClueOrder.setCreateDate(hxClueOrderDto.getCreateDate());
			hxClueOrder.setUpdateId(hxClueOrderDto.getUpdateId());
			hxClueOrder.setUpdateDate(hxClueOrderDto.getUpdateDate());
			hxClueOrder.setRemark(hxClueOrderDto.getRemark());
			hxClueOrder.setType(hxClueOrderDto.getType());
			AssertUtils.notUpdateMoreThanOne(hxClueOrderDao.updateByPrimaryKeySelective(hxClueOrder));
			logger.debug("updateHxClueOrder(HxClueOrderDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("线索使用订单信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.HX_CLUE_ORDER_UPDATE_ERROR,"线索使用订单信息更新信息错误！",e);
		}
	}

	

	@Override
	public HxClueOrderDto findHxClueOrder(
			HxClueOrderDto hxClueOrderDto) throws TsfaServiceException {
		logger.debug("findHxClueOrder(FindHxClueOrder findHxClueOrder={}) - start", hxClueOrderDto); //$NON-NLS-1$

		AssertUtils.notNull(hxClueOrderDto);
		AssertUtils.notAllNull(hxClueOrderDto.getCode(),"Code不能为空");
		try {
			HxClueOrder hxClueOrder = hxClueOrderDao.selectByPrimaryKey(hxClueOrderDto.getCode());
			if(hxClueOrder == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.HX_CLUE_ORDER_NOT_EXIST_ERROR,"线索使用订单信息不存在");
			}
			HxClueOrderDto findHxClueOrderReturn = new HxClueOrderDto();
			//find数据录入
			findHxClueOrderReturn.setCode(hxClueOrder.getCode());
			findHxClueOrderReturn.setClueCode(hxClueOrder.getClueCode());
			findHxClueOrderReturn.setMemberNo(hxClueOrder.getMemberNo());
			findHxClueOrderReturn.setMemberName(hxClueOrder.getMemberName());
			findHxClueOrderReturn.setShopNo(hxClueOrder.getShopNo());
			findHxClueOrderReturn.setShopName(hxClueOrder.getShopName());
			findHxClueOrderReturn.setMerchantNo(hxClueOrder.getMerchantNo());
			findHxClueOrderReturn.setMerchantName(hxClueOrder.getMerchantName());
			findHxClueOrderReturn.setShopServerCode(hxClueOrder.getShopServerCode());
			findHxClueOrderReturn.setServerName(hxClueOrder.getServerName());
			findHxClueOrderReturn.setServerCode(hxClueOrder.getServerCode());
			findHxClueOrderReturn.setUserType(hxClueOrder.getUserType());
			findHxClueOrderReturn.setUserPrice(hxClueOrder.getUserPrice());
			findHxClueOrderReturn.setStatus(hxClueOrder.getStatus());
			findHxClueOrderReturn.setCreateId(hxClueOrder.getCreateId());
			findHxClueOrderReturn.setCreateDate(hxClueOrder.getCreateDate());
			findHxClueOrderReturn.setUpdateId(hxClueOrder.getUpdateId());
			findHxClueOrderReturn.setUpdateDate(hxClueOrder.getUpdateDate());
			findHxClueOrderReturn.setRemark(hxClueOrder.getRemark());
			findHxClueOrderReturn.setType(hxClueOrder.getType());
			logger.debug("findHxClueOrder(HxClueOrderDto) - end - return value={}", findHxClueOrderReturn); //$NON-NLS-1$
			return findHxClueOrderReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找线索使用订单信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.HX_CLUE_ORDER_FIND_ERROR,"查找线索使用订单信息信息错误！",e);
		}


	}

	
	@Override
	public Page<HxClueOrderDto> findHxClueOrderPage(
			FindHxClueOrderPage findHxClueOrderPage)
			throws TsfaServiceException {
		logger.debug("findHxClueOrderPage(FindHxClueOrderPage findHxClueOrderPage={}) - start", findHxClueOrderPage); //$NON-NLS-1$

		AssertUtils.notNull(findHxClueOrderPage);
		List<HxClueOrderDto> returnList=null;
		int count = 0;
		try {
			returnList = hxClueOrderDao.findHxClueOrderPage(findHxClueOrderPage);
			count = hxClueOrderDao.findHxClueOrderPageCount(findHxClueOrderPage);
		}  catch (Exception e) {
			logger.error("线索使用订单信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.HX_CLUE_ORDER_FIND_PAGE_ERROR,"线索使用订单信息不存在错误.！",e);
		}
		Page<HxClueOrderDto> returnPage = new Page<HxClueOrderDto>(returnList, count, findHxClueOrderPage);

		logger.debug("findHxClueOrderPage(FindHxClueOrderPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 


}
