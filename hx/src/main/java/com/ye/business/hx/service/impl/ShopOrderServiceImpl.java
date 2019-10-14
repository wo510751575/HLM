package com.ye.business.hx.service.impl;

import java.util.Date;
import java.util.Iterator;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IServerDetailDao;
import com.ye.business.hx.dao.IServerInfoDao;
import com.ye.business.hx.dao.IShopOrderDao;
import com.ye.business.hx.dao.IShopServerDao;
import com.ye.business.hx.dao.IShopServerDetailDao;
import com.ye.business.hx.domain.ServerInfo;
import com.ye.business.hx.domain.ShopOrder;
import com.ye.business.hx.domain.ShopServer;
import com.ye.business.hx.domain.ShopServerDetail;
import com.ye.business.hx.dto.FindServerDetailPage;
import com.ye.business.hx.dto.FindShopOrderPage;
import com.ye.business.hx.dto.FindShopServerPage;
import com.ye.business.hx.dto.ServerDetailDto;
import com.ye.business.hx.dto.ShopOrderDto;
import com.ye.business.hx.dto.ShopServerDto;
import com.ye.business.hx.emus.OrderStatus;
import com.ye.business.hx.service.IShopOrderService;
import com.ye.business.hx.util.OrderNoGenUtil;
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
public class ShopOrderServiceImpl implements IShopOrderService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ShopOrderServiceImpl.class);
	

	@Resource
	private IShopOrderDao shopOrderDao;
	@Resource
	private IShopServerDao shopServerDao;
	@Resource
	private IShopServerDetailDao shopServerDetailDao;
	@Resource
	private IServerInfoDao serverInfoDao;
	@Resource
	private IServerDetailDao serverDetailDao;
	
	@Override
	public ShopOrderDto addShopOrder(
			ShopOrderDto shopOrderDto) throws TsfaServiceException {
		logger.debug("addShopOrder(AddShopOrder addShopOrder={}) - start", shopOrderDto); 

		AssertUtils.notNull(shopOrderDto);
		try {
			ShopOrder shopOrder = new ShopOrder();
			//add数据录入
			shopOrder.setCode(GUID.getPreUUID());
			shopOrder.setShopNo(shopOrderDto.getShopNo());
			shopOrder.setShopName(shopOrderDto.getShopName());
			shopOrder.setMerchantNo(shopOrderDto.getMerchantNo());
			shopOrder.setMerchantName(shopOrderDto.getMerchantName());
			shopOrder.setOrderNo(OrderNoGenUtil.getOrderNo());
			shopOrder.setOrderType(shopOrderDto.getOrderType());
			shopOrder.setServeCode(shopOrderDto.getServeCode());
			shopOrder.setServeName(shopOrderDto.getServeName());
			shopOrder.setMemberNoGuid(shopOrderDto.getMemberNoGuid());
			shopOrder.setMemberNameGuid(shopOrderDto.getMemberNameGuid());
			shopOrder.setMobile(shopOrderDto.getMobile());
			shopOrder.setSerialNum(shopOrderDto.getSerialNum());
			shopOrder.setPayType(shopOrderDto.getPayType());
			shopOrder.setAmount(shopOrderDto.getAmount());
			shopOrder.setPayTime(shopOrderDto.getPayTime());
			shopOrder.setPayCert(shopOrderDto.getPayCert());
			shopOrder.setStatus(shopOrderDto.getStatus());
			shopOrder.setUpdateId(shopOrderDto.getUpdateId());
			shopOrder.setUpdateDate(new Date());
			shopOrder.setCreateId(shopOrderDto.getCreateId());
			shopOrder.setCreateDate(new Date());
			shopOrder.setRemark(shopOrderDto.getRemark());
			shopOrder.setRemark2(shopOrderDto.getRemark2());
			shopOrder.setRemark3(shopOrderDto.getRemark3());
			shopOrderDao.insertSelective(shopOrder);
			
			
			logger.debug("addShopOrder(ShopOrderDto) - end - return"); 
			ShopOrderDto rt=new ShopOrderDto();
			rt.setCode(shopOrder.getCode());
			return rt;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增门店订单信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_ORDER_ADD_ERROR,"新增门店订单信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询门店订单信息
	 *
	 * @param findShopOrderPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<ShopOrderDto>  findShopOrders(FindShopOrderPage findShopOrderPage)throws TsfaServiceException{
		AssertUtils.notNull(findShopOrderPage);
		List<ShopOrderDto> returnList=null;
		try {
			returnList = shopOrderDao.findShopOrders(findShopOrderPage);
		} catch (Exception e) {
			logger.error("查找门店订单信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_ORDER_NOT_EXIST_ERROR,"门店订单信息不存在");
		}
		return returnList;
	}
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void updateShopOrder(ShopOrderDto shopOrderDto)
			throws TsfaServiceException {
		logger.debug("updateShopOrder(ShopOrderDto shopOrderDto={}) - start", shopOrderDto); //$NON-NLS-1$

		AssertUtils.notNull(shopOrderDto);
		AssertUtils.notNullAndEmpty(shopOrderDto.getCode(),"Code不能为空");
		try {
			ShopOrder shopOrder = new ShopOrder();
			//update数据录入
			shopOrder.setCode(shopOrderDto.getCode());
			shopOrder.setShopNo(shopOrderDto.getShopNo());
			shopOrder.setShopName(shopOrderDto.getShopName());
			shopOrder.setMerchantNo(shopOrderDto.getMerchantNo());
			shopOrder.setMerchantName(shopOrderDto.getMerchantName());
			shopOrder.setOrderNo(shopOrderDto.getOrderNo());
			shopOrder.setOrderType(shopOrderDto.getOrderType());
			shopOrder.setServeCode(shopOrderDto.getServeCode());
			shopOrder.setServeName(shopOrderDto.getServeName());
			shopOrder.setMemberNoGuid(shopOrderDto.getMemberNoGuid());
			shopOrder.setMemberNameGuid(shopOrderDto.getMemberNameGuid());
			shopOrder.setMobile(shopOrderDto.getMobile());
			shopOrder.setSerialNum(shopOrderDto.getSerialNum());
			shopOrder.setPayType(shopOrderDto.getPayType());
			shopOrder.setAmount(shopOrderDto.getAmount());
			shopOrder.setPayTime(shopOrderDto.getPayTime());
			shopOrder.setPayCert(shopOrderDto.getPayCert());
			shopOrder.setStatus(shopOrderDto.getStatus());
			shopOrder.setUpdateId(shopOrderDto.getUpdateId());
			shopOrder.setUpdateDate(new Date());
//			shopOrder.setCreateId(shopOrderDto.getCreateId());
//			shopOrder.setCreateDate(shopOrderDto.getCreateDate());
			shopOrder.setRemark(shopOrderDto.getRemark());
			shopOrder.setRemark2(shopOrderDto.getRemark2());
			shopOrder.setRemark3(shopOrderDto.getRemark3());
			//1.审核则检测是否已经审核并给门店配置 各种服务
			if(!OrderStatus.WAIT.toString().equals(shopOrder.getStatus())) {
				openShopServer(shopOrderDto);
			}
			//2.然后修改 订单的数据
			AssertUtils.notUpdateMoreThanOne(shopOrderDao.updateByPrimaryKeySelective(shopOrder));
			logger.debug("updateShopOrder(ShopOrderDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("门店订单信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_ORDER_UPDATE_ERROR,"门店订单信息更新信息错误！",e);
		}
	}

	/***
	 * 服务订单审核通过开通服务。
	 * @param shopOrderDto
	 */
	private void openShopServer(ShopOrderDto shopOrderDto) {
		ShopOrder findOrder = shopOrderDao.selectByPrimaryKey(shopOrderDto.getCode());
		//订单已经审核通过，则不可再审核
		if(OrderStatus.PASS.toString().equals(findOrder.getStatus())) {
			throw new TsfaServiceException(ErrorCode.SHOP_ORDER_UPDATE_ERROR,"门店订单信已审核通过！");
		}
		//待审核和审核不通过才可继续审核通过
		if(OrderStatus.PASS.toString().equals(shopOrderDto.getStatus())) {
			//1.检测该订单是否已经给门店配置了该服务
			FindShopServerPage findShopServerPage=new FindShopServerPage();
			ShopServerDto param=new ShopServerDto();
			param.setOrderNo(findOrder.getOrderNo());
			findShopServerPage.setParam(param);
			
			int count=shopServerDao.findShopServerPageCount(findShopServerPage);
			if (count > 0) {
				throw new TsfaServiceException(ErrorCode.SHOP_ORDER_UPDATE_ERROR,"门店订单信已审核！");
			}
			//2.给门诊开通服务 ，找出系统服务
			ServerInfo serverInfo= serverInfoDao.selectByPrimaryKey(findOrder.getServeCode());
			// 2.1 找出系统服务项
			FindServerDetailPage findServerDetailPage = new FindServerDetailPage();
			ServerDetailDto dParam = new ServerDetailDto();
			dParam.setServerCode(serverInfo.getCode());
			findServerDetailPage.setParam(dParam);
			List<ServerDetailDto> serverDetailDtos = serverDetailDao.findServerDetails(findServerDetailPage);
			
			//3.1 开通服务
			ShopServer shopServer = new ShopServer();
			//add数据录入
			shopServer.setCode(GUID.getPreUUID());
			shopServer.setShopNo(findOrder.getShopNo());
			shopServer.setShopName(findOrder.getShopName());
			shopServer.setMerchantNo(findOrder.getMerchantNo());
			shopServer.setMerchantName(findOrder.getMerchantName());
			shopServer.setServerCode(findOrder.getServeCode());
			shopServer.setServerName(serverInfo.getServerName());
			shopServer.setPrice(findOrder.getAmount());
			shopServer.setOriginalPrice(serverInfo.getOriginalPrice());
			shopServer.setOrderNo(findOrder.getOrderNo());
			shopServer.setUpdateId(shopOrderDto.getUpdateId());
			shopServer.setUpdateDate(new Date());
			shopServer.setCreateId(shopOrderDto.getCreateId());
			shopServer.setCreateDate(new Date());
			shopServerDao.insertSelective(shopServer);
			//3.1 开通服务项
			for (Iterator<ServerDetailDto> iterator = serverDetailDtos.iterator(); iterator.hasNext();) {
				ServerDetailDto serverDetail = iterator.next();
				
				ShopServerDetail shopServerDetail = new ShopServerDetail();
				//add数据录入
				shopServerDetail.setCode(GUID.getPreUUID());
				shopServerDetail.setShopServerCode(shopServer.getCode());
				shopServerDetail.setServerCode(shopServer.getServerCode());
				shopServerDetail.setServerName(shopServer.getServerName());
				shopServerDetail.setUserType(serverDetail.getUserType());
				shopServerDetail.setPrice(serverDetail.getPrice());
				shopServerDetail.setServerNum(serverDetail.getServerNum());
				shopServerDetail.setUseNum(0);
				shopServerDetail.setUnuseNum(serverDetail.getServerNum());
				shopServerDetail.setFreezeNum(0);
				shopServerDetail.setUpdateId(shopOrderDto.getUpdateId());
				shopServerDetail.setUpdateDate(new Date());
				shopServerDetail.setCreateId(shopOrderDto.getUpdateId());
				shopServerDetail.setCreateDate(new Date());
				shopServerDetail.setIsShop(serverDetail.getIsShop());
				shopServerDetail.setServerDesc(serverDetail.getServerDesc());
				shopServerDetailDao.insertSelective(shopServerDetail);
			}
		}
	}
	

	@Override
	public ShopOrderDto findShopOrder(
			ShopOrderDto shopOrderDto) throws TsfaServiceException {
		logger.debug("findShopOrder(FindShopOrder findShopOrder={}) - start", shopOrderDto); //$NON-NLS-1$

		AssertUtils.notNull(shopOrderDto);
		AssertUtils.notAllNull(shopOrderDto.getCode(),"Code不能为空");
		try {
			ShopOrder shopOrder = shopOrderDao.selectByPrimaryKey(shopOrderDto.getCode());
			if(shopOrder == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SHOP_ORDER_NOT_EXIST_ERROR,"门店订单信息不存在");
			}
			ShopOrderDto findShopOrderReturn = new ShopOrderDto();
			//find数据录入
			findShopOrderReturn.setCode(shopOrder.getCode());
			findShopOrderReturn.setShopNo(shopOrder.getShopNo());
			findShopOrderReturn.setShopName(shopOrder.getShopName());
			findShopOrderReturn.setMerchantNo(shopOrder.getMerchantNo());
			findShopOrderReturn.setMerchantName(shopOrder.getMerchantName());
			findShopOrderReturn.setOrderNo(shopOrder.getOrderNo());
			findShopOrderReturn.setOrderType(shopOrder.getOrderType());
			findShopOrderReturn.setServeCode(shopOrder.getServeCode());
			findShopOrderReturn.setServeName(shopOrder.getServeName());
			findShopOrderReturn.setMemberNoGuid(shopOrder.getMemberNoGuid());
			findShopOrderReturn.setMemberNameGuid(shopOrder.getMemberNameGuid());
			findShopOrderReturn.setMobile(shopOrder.getMobile());
			findShopOrderReturn.setSerialNum(shopOrder.getSerialNum());
			findShopOrderReturn.setPayType(shopOrder.getPayType());
			findShopOrderReturn.setAmount(shopOrder.getAmount());
			findShopOrderReturn.setPayTime(shopOrder.getPayTime());
			findShopOrderReturn.setPayCert(shopOrder.getPayCert());
			findShopOrderReturn.setStatus(shopOrder.getStatus());
			findShopOrderReturn.setUpdateId(shopOrder.getUpdateId());
			findShopOrderReturn.setUpdateDate(shopOrder.getUpdateDate());
			findShopOrderReturn.setCreateId(shopOrder.getCreateId());
			findShopOrderReturn.setCreateDate(shopOrder.getCreateDate());
			findShopOrderReturn.setRemark(shopOrder.getRemark());
			findShopOrderReturn.setRemark2(shopOrder.getRemark2());
			findShopOrderReturn.setRemark3(shopOrder.getRemark3());
			
			logger.debug("findShopOrder(ShopOrderDto) - end - return value={}", findShopOrderReturn); //$NON-NLS-1$
			return findShopOrderReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找门店订单信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_ORDER_FIND_ERROR,"查找门店订单信息信息错误！",e);
		}


	}

	
	@Override
	public Page<ShopOrderDto> findShopOrderPage(
			FindShopOrderPage findShopOrderPage)
			throws TsfaServiceException {
		logger.debug("findShopOrderPage(FindShopOrderPage findShopOrderPage={}) - start", findShopOrderPage); //$NON-NLS-1$

		AssertUtils.notNull(findShopOrderPage);
		List<ShopOrderDto> returnList=null;
		int count = 0;
		try {
			returnList = shopOrderDao.findShopOrderPage(findShopOrderPage);
			count = shopOrderDao.findShopOrderPageCount(findShopOrderPage);
		}  catch (Exception e) {
			logger.error("门店订单信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SHOP_ORDER_FIND_PAGE_ERROR,"门店订单信息不存在错误.！",e);
		}
		Page<ShopOrderDto> returnPage = new Page<ShopOrderDto>(returnList, count, findShopOrderPage);

		logger.debug("findShopOrderPage(FindShopOrderPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 


}
