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
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IShopServerDetailDao;
import com.ye.business.hx.domain.ShopServerDetail;
import com.ye.business.hx.dto.FindShopServerDetailPage;
import com.ye.business.hx.dto.ShopServerDetailDto;
import com.ye.business.hx.service.IShopServerDetailService;
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
public class ShopServerDetailServiceImpl implements IShopServerDetailService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ShopServerDetailServiceImpl.class);
	

	@Resource
	private IShopServerDetailDao shopServerDetailDao;
	
	
	@Override
	public void addShopServerDetail(
			ShopServerDetailDto shopServerDetailDto) throws TsfaServiceException {
		logger.debug("addShopServerDetail(AddShopServerDetail addShopServerDetail={}) - start", shopServerDetailDto); 

		AssertUtils.notNull(shopServerDetailDto);
		try {
			ShopServerDetail shopServerDetail = new ShopServerDetail();
			//add数据录入
			shopServerDetail.setCode(GUID.getPreUUID());
			shopServerDetail.setShopServerCode(shopServerDetailDto.getShopServerCode());
			shopServerDetail.setServerCode(shopServerDetailDto.getServerCode());
			shopServerDetail.setServerName(shopServerDetailDto.getServerName());
			shopServerDetail.setUserType(shopServerDetailDto.getUserType());
			shopServerDetail.setPrice(shopServerDetailDto.getPrice());
			shopServerDetail.setServerNum(shopServerDetailDto.getServerNum());
			shopServerDetail.setUseNum(shopServerDetailDto.getUseNum());
			shopServerDetail.setUnuseNum(shopServerDetailDto.getUnuseNum());
			shopServerDetail.setFreezeNum(shopServerDetailDto.getFreezeNum());
			shopServerDetail.setUpdateId(shopServerDetailDto.getUpdateId());
			shopServerDetail.setUpdateDate(new Date());
			shopServerDetail.setCreateId(shopServerDetailDto.getCreateId());
			shopServerDetail.setCreateDate(new Date());
			shopServerDetail.setIsShop(shopServerDetailDto.getIsShop());
			shopServerDetail.setServerDesc(shopServerDetailDto.getServerDesc());
			shopServerDetailDao.insertSelective(shopServerDetail);
			logger.debug("addShopServerDetail(ShopServerDetailDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增门诊服务项信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SERVER_DETAIL_ADD_ERROR,"新增门诊服务项信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询门诊服务项信息
	 *
	 * @param findShopServerDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<ShopServerDetailDto>  findShopServerDetails(FindShopServerDetailPage findShopServerDetailPage)throws TsfaServiceException{
		AssertUtils.notNull(findShopServerDetailPage);
		List<ShopServerDetailDto> returnList=null;
		try {
			returnList = shopServerDetailDao.findShopServerDetails(findShopServerDetailPage);
		} catch (Exception e) {
			logger.error("查找门诊服务项信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_SERVER_DETAIL_NOT_EXIST_ERROR,"门诊服务项信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateShopServerDetail(
			ShopServerDetailDto shopServerDetailDto)
			throws TsfaServiceException {
		logger.debug("updateShopServerDetail(ShopServerDetailDto shopServerDetailDto={}) - start", shopServerDetailDto); //$NON-NLS-1$

		AssertUtils.notNull(shopServerDetailDto);
		AssertUtils.notNullAndEmpty(shopServerDetailDto.getCode(),"Code不能为空");
		try {
			ShopServerDetail shopServerDetail = new ShopServerDetail();
			//update数据录入
			shopServerDetail.setCode(shopServerDetailDto.getCode());
			shopServerDetail.setShopServerCode(shopServerDetailDto.getShopServerCode());
			shopServerDetail.setServerCode(shopServerDetailDto.getServerCode());
			shopServerDetail.setServerName(shopServerDetailDto.getServerName());
			shopServerDetail.setUserType(shopServerDetailDto.getUserType());
			shopServerDetail.setPrice(shopServerDetailDto.getPrice());
			shopServerDetail.setServerNum(shopServerDetailDto.getServerNum());
			shopServerDetail.setUseNum(shopServerDetailDto.getUseNum());
			shopServerDetail.setUnuseNum(shopServerDetailDto.getUnuseNum());
			shopServerDetail.setFreezeNum(shopServerDetailDto.getFreezeNum());
			shopServerDetail.setUpdateId(shopServerDetailDto.getUpdateId());
			shopServerDetail.setUpdateDate(new Date());
			shopServerDetail.setCreateId(shopServerDetailDto.getCreateId());
			shopServerDetail.setCreateDate(shopServerDetailDto.getCreateDate());
			shopServerDetail.setIsShop(shopServerDetailDto.getIsShop());
			shopServerDetail.setServerDesc(shopServerDetailDto.getServerDesc());
			AssertUtils.notUpdateMoreThanOne(shopServerDetailDao.updateByPrimaryKeySelective(shopServerDetail));
			logger.debug("updateShopServerDetail(ShopServerDetailDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("门诊服务项信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SERVER_DETAIL_UPDATE_ERROR,"门诊服务项信息更新信息错误！",e);
		}
	}

	

	@Override
	public ShopServerDetailDto findShopServerDetail(
			ShopServerDetailDto shopServerDetailDto) throws TsfaServiceException {
		logger.debug("findShopServerDetail(FindShopServerDetail findShopServerDetail={}) - start", shopServerDetailDto); //$NON-NLS-1$

		AssertUtils.notNull(shopServerDetailDto);
		AssertUtils.notAllNull(shopServerDetailDto.getCode(),"Code不能为空");
		try {
			ShopServerDetail shopServerDetail = shopServerDetailDao.selectByPrimaryKey(shopServerDetailDto.getCode());
			if(shopServerDetail == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SHOP_SERVER_DETAIL_NOT_EXIST_ERROR,"门诊服务项信息不存在");
			}
			ShopServerDetailDto findShopServerDetailReturn = new ShopServerDetailDto();
			//find数据录入
			findShopServerDetailReturn.setCode(shopServerDetail.getCode());
			findShopServerDetailReturn.setShopServerCode(shopServerDetail.getShopServerCode());
			findShopServerDetailReturn.setServerCode(shopServerDetail.getServerCode());
			findShopServerDetailReturn.setServerName(shopServerDetail.getServerName());
			findShopServerDetailReturn.setUserType(shopServerDetail.getUserType());
			findShopServerDetailReturn.setPrice(shopServerDetail.getPrice());
			findShopServerDetailReturn.setServerNum(shopServerDetail.getServerNum());
			findShopServerDetailReturn.setUseNum(shopServerDetail.getUseNum());
			findShopServerDetailReturn.setUnuseNum(shopServerDetail.getUnuseNum());
			findShopServerDetailReturn.setFreezeNum(shopServerDetail.getFreezeNum());
			findShopServerDetailReturn.setUpdateId(shopServerDetail.getUpdateId());
			findShopServerDetailReturn.setUpdateDate(shopServerDetail.getUpdateDate());
			findShopServerDetailReturn.setCreateId(shopServerDetail.getCreateId());
			findShopServerDetailReturn.setCreateDate(shopServerDetail.getCreateDate());
			findShopServerDetailReturn.setIsShop(shopServerDetail.getIsShop());
			findShopServerDetailReturn.setServerDesc(shopServerDetail.getServerDesc());
			logger.debug("findShopServerDetail(ShopServerDetailDto) - end - return value={}", findShopServerDetailReturn); //$NON-NLS-1$
			return findShopServerDetailReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找门诊服务项信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SERVER_DETAIL_FIND_ERROR,"查找门诊服务项信息信息错误！",e);
		}


	}

	
	@Override
	public Page<ShopServerDetailDto> findShopServerDetailPage(
			FindShopServerDetailPage findShopServerDetailPage)
			throws TsfaServiceException {
		logger.debug("findShopServerDetailPage(FindShopServerDetailPage findShopServerDetailPage={}) - start", findShopServerDetailPage); //$NON-NLS-1$

		AssertUtils.notNull(findShopServerDetailPage);
		List<ShopServerDetailDto> returnList=null;
		int count = 0;
		try {
			returnList = shopServerDetailDao.findShopServerDetailPage(findShopServerDetailPage);
			count = shopServerDetailDao.findShopServerDetailPageCount(findShopServerDetailPage);
		}  catch (Exception e) {
			logger.error("门诊服务项信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SERVER_DETAIL_FIND_PAGE_ERROR,"门诊服务项信息不存在错误.！",e);
		}
		Page<ShopServerDetailDto> returnPage = new Page<ShopServerDetailDto>(returnList, count, findShopServerDetailPage);

		logger.debug("findShopServerDetailPage(FindShopServerDetailPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 


}
