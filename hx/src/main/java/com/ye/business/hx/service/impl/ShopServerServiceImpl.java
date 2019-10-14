package com.ye.business.hx.service.impl;

import java.util.Date;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IShopServerDao;
import com.ye.business.hx.domain.ShopServer;
import com.ye.business.hx.dto.FindServerDetailPage;
import com.ye.business.hx.dto.FindShopServerPage;
import com.ye.business.hx.dto.ServerDetailDto;
import com.ye.business.hx.dto.ServerInfoDto;
import com.ye.business.hx.dto.ShopServerDetailDto;
import com.ye.business.hx.dto.ShopServerDto;
import com.ye.business.hx.service.IServerDetailService;
import com.ye.business.hx.service.IServerInfoService;
import com.ye.business.hx.service.IShopServerDetailService;
import com.ye.business.hx.service.IShopServerService;
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
public class ShopServerServiceImpl implements IShopServerService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ShopServerServiceImpl.class);
	

	@Resource
	private IShopServerDao shopServerDao;
	
	/** 商户服务明细 */
	@Autowired
	private IShopServerDetailService shopServerDetailService;

	/** 服务 */
	@Autowired
	private IServerInfoService serverInfoService;
	
	/** 服务明细 */
	@Autowired
	private IServerDetailService serverDetailService;
	
	@Override
	public void addShopServer(
			ShopServerDto shopServerDto) throws TsfaServiceException {
		logger.debug("addShopServer(AddShopServer addShopServer={}) - start", shopServerDto); 

		AssertUtils.notNull(shopServerDto);
		try {
			ShopServer shopServer = new ShopServer();
			//add数据录入
			shopServer.setCode(GUID.getPreUUID());
			shopServer.setShopNo(shopServerDto.getShopNo());
			shopServer.setShopName(shopServerDto.getShopName());
			shopServer.setMerchantNo(shopServerDto.getMerchantNo());
			shopServer.setMerchantName(shopServerDto.getMerchantName());
			shopServer.setServerCode(shopServerDto.getServerCode());
			shopServer.setServerName(shopServerDto.getServerName());
			shopServer.setPrice(shopServerDto.getPrice());
			shopServer.setOriginalPrice(shopServerDto.getOriginalPrice());
			shopServer.setOrderNo(shopServerDto.getOrderNo());
			shopServer.setUpdateId(shopServerDto.getUpdateId());
			shopServer.setUpdateDate(new Date());
			shopServer.setCreateId(shopServerDto.getCreateId());
			shopServer.setCreateDate(new Date());
			shopServerDao.insertSelective(shopServer);
			logger.debug("addShopServer(ShopServerDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增门诊服务信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SERVER_ADD_ERROR,"新增门诊服务信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询门诊服务信息
	 *
	 * @param findShopServerPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<ShopServerDto>  findShopServers(FindShopServerPage findShopServerPage)throws TsfaServiceException{
		AssertUtils.notNull(findShopServerPage);
		List<ShopServerDto> returnList=null;
		try {
			returnList = shopServerDao.findShopServers(findShopServerPage);
		} catch (Exception e) {
			logger.error("查找门诊服务信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_SERVER_NOT_EXIST_ERROR,"门诊服务信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateShopServer(
			ShopServerDto shopServerDto)
			throws TsfaServiceException {
		logger.debug("updateShopServer(ShopServerDto shopServerDto={}) - start", shopServerDto); //$NON-NLS-1$

		AssertUtils.notNull(shopServerDto);
		AssertUtils.notNullAndEmpty(shopServerDto.getCode(),"Code不能为空");
		try {
			ShopServer shopServer = new ShopServer();
			//update数据录入
			shopServer.setCode(shopServerDto.getCode());
			shopServer.setShopNo(shopServerDto.getShopNo());
			shopServer.setShopName(shopServerDto.getShopName());
			shopServer.setMerchantNo(shopServerDto.getMerchantNo());
			shopServer.setMerchantName(shopServerDto.getMerchantName());
			shopServer.setServerCode(shopServerDto.getServerCode());
			shopServer.setServerName(shopServerDto.getServerName());
			shopServer.setPrice(shopServerDto.getPrice());
			shopServer.setOriginalPrice(shopServerDto.getOriginalPrice());
			shopServer.setOrderNo(shopServerDto.getOrderNo());
			shopServer.setUpdateId(shopServerDto.getUpdateId());
			shopServer.setUpdateDate(new Date());
			shopServer.setCreateId(shopServerDto.getCreateId());
//			shopServer.setCreateDate(shopServerDto.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(shopServerDao.updateByPrimaryKeySelective(shopServer));
			logger.debug("updateShopServer(ShopServerDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("门诊服务信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SERVER_UPDATE_ERROR,"门诊服务信息更新信息错误！",e);
		}
	}

	

	@Override
	public ShopServerDto findShopServer(
			ShopServerDto shopServerDto) throws TsfaServiceException {
		logger.debug("findShopServer(FindShopServer findShopServer={}) - start", shopServerDto); //$NON-NLS-1$

		AssertUtils.notNull(shopServerDto);
		AssertUtils.notAllNull(shopServerDto.getCode(),"Code不能为空");
		try {
			ShopServer shopServer = shopServerDao.selectByPrimaryKey(shopServerDto.getCode());
			if(shopServer == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SHOP_SERVER_NOT_EXIST_ERROR,"门诊服务信息不存在");
			}
			ShopServerDto findShopServerReturn = new ShopServerDto();
			//find数据录入
			findShopServerReturn.setCode(shopServer.getCode());
			findShopServerReturn.setShopNo(shopServer.getShopNo());
			findShopServerReturn.setShopName(shopServer.getShopName());
			findShopServerReturn.setMerchantNo(shopServer.getMerchantNo());
			findShopServerReturn.setMerchantName(shopServer.getMerchantName());
			findShopServerReturn.setServerCode(shopServer.getServerCode());
			findShopServerReturn.setServerName(shopServer.getServerName());
			findShopServerReturn.setPrice(shopServer.getPrice());
			findShopServerReturn.setOriginalPrice(shopServer.getOriginalPrice());
			findShopServerReturn.setOrderNo(shopServer.getOrderNo());
			findShopServerReturn.setUpdateId(shopServer.getUpdateId());
			findShopServerReturn.setUpdateDate(shopServer.getUpdateDate());
			findShopServerReturn.setCreateId(shopServer.getCreateId());
			findShopServerReturn.setCreateDate(shopServer.getCreateDate());
			
			logger.debug("findShopServer(ShopServerDto) - end - return value={}", findShopServerReturn); //$NON-NLS-1$
			return findShopServerReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找门诊服务信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SERVER_FIND_ERROR,"查找门诊服务信息信息错误！",e);
		}


	}

	
	@Override
	public Page<ShopServerDto> findShopServerPage(
			FindShopServerPage findShopServerPage)
			throws TsfaServiceException {
		logger.debug("findShopServerPage(FindShopServerPage findShopServerPage={}) - start", findShopServerPage); //$NON-NLS-1$

		AssertUtils.notNull(findShopServerPage);
		List<ShopServerDto> returnList=null;
		int count = 0;
		try {
			returnList = shopServerDao.findShopServerPage(findShopServerPage);
			count = shopServerDao.findShopServerPageCount(findShopServerPage);
		}  catch (Exception e) {
			logger.error("门诊服务信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SERVER_FIND_PAGE_ERROR,"门诊服务信息不存在错误.！",e);
		}
		Page<ShopServerDto> returnPage = new Page<ShopServerDto>(returnList, count, findShopServerPage);

		logger.debug("findShopServerPage(FindShopServerPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 

	@Override
	public String addShopServerRecord(ShopServerDto shopServerDto) throws TsfaServiceException {
		logger.debug("addShopServerRecord(AddShopServer addShopServer={}) - start", shopServerDto); 

		AssertUtils.notNull(shopServerDto);
		try {
			
			// 获取服务信息
			ServerInfoDto serverInfoDto = new ServerInfoDto();
			serverInfoDto.setCode(shopServerDto.getServerCode());
			ServerInfoDto findServerInfoReturn = serverInfoService.findServerInfo(serverInfoDto);
			AssertUtils.notNull(findServerInfoReturn);
			
			// 获取服务明细
			FindServerDetailPage findServerDetailPage = new FindServerDetailPage();
			ServerDetailDto serverDetail = new ServerDetailDto();
			serverDetail.setServerCode(serverInfoDto.getCode());
			findServerDetailPage.setParam(serverDetail);
			List<ServerDetailDto> findServerDetails = serverDetailService.findServerDetails(findServerDetailPage);
			
			ShopServer shopServer = new ShopServer();
			//add数据录入
			shopServer.setCode(GUID.getPreUUID());
			shopServer.setShopNo(shopServerDto.getShopNo());
			shopServer.setShopName(shopServerDto.getShopName());
			shopServer.setMerchantNo(shopServerDto.getMerchantNo());
			shopServer.setMerchantName(shopServerDto.getMerchantName());
			shopServer.setServerCode(findServerInfoReturn.getCode());
			shopServer.setServerName(findServerInfoReturn.getServerName());
			shopServer.setPrice(findServerInfoReturn.getPrice());
			shopServer.setOriginalPrice(findServerInfoReturn.getOriginalPrice());
			shopServer.setOrderNo(shopServerDto.getOrderNo());
			shopServer.setUpdateId(shopServerDto.getUpdateId());
			shopServer.setUpdateDate(shopServerDto.getUpdateDate());
			shopServer.setCreateId(shopServerDto.getCreateId());
			shopServer.setCreateDate(shopServerDto.getCreateDate());
			shopServerDao.insertSelective(shopServer);
			
			// 添加服务明细
			if (!findServerDetails.isEmpty()) {
				
				// 封装开通服务明细并添加
				findServerDetails.forEach(action -> {
					ShopServerDetailDto sd = new ShopServerDetailDto();
					
					sd.setShopServerCode(shopServer.getCode());
					sd.setServerCode(findServerInfoReturn.getCode());
					sd.setServerName(findServerInfoReturn.getServerName());
					sd.setUserType(action.getUserType());
					sd.setPrice(findServerInfoReturn.getPrice());
					sd.setServerNum(action.getServerNum());
					sd.setUseNum(0);
					sd.setUnuseNum(action.getServerNum());
					sd.setFreezeNum(0);
					sd.setUpdateId(shopServerDto.getUpdateId());
					sd.setUpdateDate(shopServerDto.getUpdateDate());
					sd.setCreateId(shopServerDto.getCreateId());
					sd.setCreateDate(shopServerDto.getCreateDate());
					sd.setIsShop(action.getIsShop());
					sd.setServerDesc(action.getServerDesc());
					
					shopServerDetailService.addShopServerDetail(sd);
				});
			}
			
			logger.debug("addShopServerRecord(ShopServerDto) - end - return"); 
			
			return shopServer.getServerName();
			
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增门诊服务信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SERVER_ADD_ERROR,"新增门诊服务信息错误！",e);
		}
	}
}
