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

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.constant.HxConstant;
import com.ye.business.hx.dao.IShopConfigDao;
import com.ye.business.hx.domain.ShopConfig;
import com.ye.business.hx.dto.FindShopConfigPage;
import com.ye.business.hx.dto.ShopConfigDto;
import com.ye.business.hx.service.IShopConfigService;
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
public class ShopConfigServiceImpl implements IShopConfigService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ShopConfigServiceImpl.class);
	

	@Resource
	private IShopConfigDao shopConfigDao;
	
	
	@Override
	public ShopConfigDto addShopConfig(
			ShopConfigDto shopConfigDto) throws TsfaServiceException {
		logger.debug("addShopConfig(AddShopConfig addShopConfig={}) - start", shopConfigDto); 

		AssertUtils.notNull(shopConfigDto);
		try {
			ShopConfig shopConfig = new ShopConfig();
			//add数据录入
			shopConfig.setCode(GUID.getPreUUID());
			shopConfig.setShopNo(shopConfigDto.getShopNo());
			shopConfig.setShopName(shopConfigDto.getShopName());
			shopConfig.setMerchantNo(shopConfigDto.getMerchantNo());
			shopConfig.setMerchantName(shopConfigDto.getMerchantName());
			shopConfig.setLableName(shopConfigDto.getLableName());
			if(StringUtils.isEmpty(shopConfigDto.getLableNo())) {
				shopConfig.setLableNo(shopConfig.getCode());
			}else {
				shopConfig.setLableNo(shopConfigDto.getLableNo());
			}
			shopConfig.setParentCode(shopConfigDto.getParentCode());
			shopConfig.setUpdateId(shopConfigDto.getUpdateId());
			shopConfig.setUpdateDate(new Date());
			shopConfig.setCreateId(shopConfigDto.getCreateId());
			shopConfig.setCreateDate(new Date());
			if (null == shopConfigDto.getIndexNo()) {
				ShopConfig config = new ShopConfig();
				config.setParentCode(shopConfig.getParentCode());
				config.setMerchantNo(shopConfig.getMerchantNo());
				
				Integer nextIndexNo = shopConfigDao.findNextShopConfigIndexNo(config);
				if (null == nextIndexNo) {
					nextIndexNo = 1;
				}
				shopConfig.setIndexNo(nextIndexNo);
			}else {
				shopConfig.setIndexNo(shopConfigDto.getIndexNo());
			}
			shopConfig.setRemark(shopConfigDto.getRemark());
			shopConfig.setEnname(shopConfigDto.getEnname());
			shopConfigDao.insertSelective(shopConfig);
			
			logger.debug("addShopConfig(ShopConfigDto) - end - return"); 
			ShopConfigDto rtConfigDto=new ShopConfigDto();
			rtConfigDto.setCode(shopConfig.getCode());
			rtConfigDto.setIndexNo(shopConfig.getIndexNo());
			return rtConfigDto;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增门店配置信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_CONFIG_ADD_ERROR,"新增门店配置信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询门店配置信息
	 *
	 * @param findShopConfigPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<ShopConfigDto>  findShopConfigs(FindShopConfigPage findShopConfigPage)throws TsfaServiceException{
		AssertUtils.notNull(findShopConfigPage);
		List<ShopConfigDto> returnList=null;
		try {
			returnList = shopConfigDao.findShopConfigs(findShopConfigPage);
		} catch (Exception e) {
			logger.error("查找门店配置信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_CONFIG_NOT_EXIST_ERROR,"门店配置信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateShopConfig(
			ShopConfigDto shopConfigDto)
			throws TsfaServiceException {
		logger.debug("updateShopConfig(ShopConfigDto shopConfigDto={}) - start", shopConfigDto); //$NON-NLS-1$

		AssertUtils.notNull(shopConfigDto);
		AssertUtils.notNullAndEmpty(shopConfigDto.getCode(),"Code不能为空");
		try {
			ShopConfig shopConfig = new ShopConfig();
			//update数据录入
			shopConfig.setCode(shopConfigDto.getCode());
			shopConfig.setShopNo(shopConfigDto.getShopNo());
			shopConfig.setShopName(shopConfigDto.getShopName());
			shopConfig.setMerchantNo(shopConfigDto.getMerchantNo());
			shopConfig.setMerchantName(shopConfigDto.getMerchantName());
			shopConfig.setLableName(shopConfigDto.getLableName());
			shopConfig.setLableNo(shopConfigDto.getLableNo());
			shopConfig.setParentCode(shopConfigDto.getParentCode());
			shopConfig.setUpdateId(shopConfigDto.getUpdateId());
			shopConfig.setUpdateDate(new Date());
//			shopConfig.setCreateId(shopConfigDto.getCreateId());
//			shopConfig.setCreateDate(shopConfigDto.getCreateDate());
			shopConfig.setIndexNo(shopConfigDto.getIndexNo());
			shopConfig.setRemark(shopConfigDto.getRemark());
			shopConfig.setEnname(shopConfigDto.getEnname());
			AssertUtils.notUpdateMoreThanOne(shopConfigDao.updateByPrimaryKeySelective(shopConfig));
			logger.debug("updateShopConfig(ShopConfigDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("门店配置信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_CONFIG_UPDATE_ERROR,"门店配置信息更新信息错误！",e);
		}
	}

	

	@Override
	public ShopConfigDto findShopConfig(
			ShopConfigDto shopConfigDto) throws TsfaServiceException {
		logger.debug("findShopConfig(FindShopConfig findShopConfig={}) - start", shopConfigDto); //$NON-NLS-1$

		AssertUtils.notNull(shopConfigDto);
		AssertUtils.notAllNull(shopConfigDto.getCode(),"Code不能为空");
		try {
			ShopConfig shopConfig = shopConfigDao.selectByPrimaryKey(shopConfigDto.getCode());
			if(shopConfig == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SHOP_CONFIG_NOT_EXIST_ERROR,"门店配置信息不存在");
			}
			ShopConfigDto findShopConfigReturn = new ShopConfigDto();
			//find数据录入
			findShopConfigReturn.setCode(shopConfig.getCode());
			findShopConfigReturn.setShopNo(shopConfig.getShopNo());
			findShopConfigReturn.setShopName(shopConfig.getShopName());
			findShopConfigReturn.setMerchantNo(shopConfig.getMerchantNo());
			findShopConfigReturn.setMerchantName(shopConfig.getMerchantName());
			findShopConfigReturn.setLableName(shopConfig.getLableName());
			findShopConfigReturn.setLableNo(shopConfig.getLableNo());
			findShopConfigReturn.setParentCode(shopConfig.getParentCode());
			findShopConfigReturn.setUpdateId(shopConfig.getUpdateId());
			findShopConfigReturn.setUpdateDate(shopConfig.getUpdateDate());
			findShopConfigReturn.setCreateId(shopConfig.getCreateId());
			findShopConfigReturn.setCreateDate(shopConfig.getCreateDate());
			findShopConfigReturn.setIndexNo(shopConfig.getIndexNo());
			findShopConfigReturn.setRemark(shopConfig.getRemark());
			findShopConfigReturn.setEnname(shopConfig.getEnname());
			logger.debug("findShopConfig(ShopConfigDto) - end - return value={}", findShopConfigReturn); //$NON-NLS-1$
			return findShopConfigReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找门店配置信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_CONFIG_FIND_ERROR,"查找门店配置信息信息错误！",e);
		}


	}

	
	@Override
	public Page<ShopConfigDto> findShopConfigPage(
			FindShopConfigPage findShopConfigPage)
			throws TsfaServiceException {
		logger.debug("findShopConfigPage(FindShopConfigPage findShopConfigPage={}) - start", findShopConfigPage); //$NON-NLS-1$

		AssertUtils.notNull(findShopConfigPage);
		List<ShopConfigDto> returnList=null;
		int count = 0;
		try {
			returnList = shopConfigDao.findShopConfigPage(findShopConfigPage);
			count = shopConfigDao.findShopConfigPageCount(findShopConfigPage);
		}  catch (Exception e) {
			logger.error("门店配置信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SHOP_CONFIG_FIND_PAGE_ERROR,"门店配置信息不存在错误.！",e);
		}
		Page<ShopConfigDto> returnPage = new Page<ShopConfigDto>(returnList, count, findShopConfigPage);

		logger.debug("findShopConfigPage(FindShopConfigPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public ShopConfigDto getTopShopCofig(String toplableNo) throws TsfaServiceException {
		// 根据类别查父级CODE
		ShopConfigDto param = new ShopConfigDto();
		param.setMerchantNo(HxConstant.OFFICE_ROOT_ID);
		param.setLableNo(toplableNo);

		ShopConfigDto data = null;
		FindShopConfigPage findShopConfigPage = new FindShopConfigPage();
		findShopConfigPage.setParam(param);
		try {
			List<ShopConfigDto> parentDtos = findShopConfigs(findShopConfigPage);
			if (parentDtos != null && parentDtos.size() > 0) {
				data= parentDtos.get(0);
			}
		} catch (Exception e) {
			logger.error("门店配置信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.SHOP_CONFIG_FIND_ERROR, "门店配置信息不存在错误.！", e);
		}
		return data;
	}


	@Override
	public void updateShopConfigIndexNo(ShopConfigDto shopConfigDto) throws TsfaServiceException {
		logger.debug("updateShopConfig(ShopConfigDto shopConfigDto={}) - start", shopConfigDto); //$NON-NLS-1$

		AssertUtils.notNull(shopConfigDto);
		AssertUtils.notNullAndEmpty(shopConfigDto.getChilds(),"数据不能为空");
		try {
			
			List<ShopConfigDto> datas = shopConfigDto.getChilds();
			for (Iterator<ShopConfigDto> iterator = datas.iterator(); iterator.hasNext();) {
				ShopConfigDto updateData = iterator.next();
				AssertUtils.notNullAndEmpty(updateData.getCode(),"code不能为空");
				AssertUtils.notNullAndEmpty(updateData.getIndexNo(),"排序值不能为空");
				
				ShopConfig shopConfig = new ShopConfig();
				shopConfig.setCode(updateData.getCode());
//				shopConfig.setMerchantNo(shopConfigDto.getMerchantNo());
				shopConfig.setIndexNo(updateData.getIndexNo());
				AssertUtils.notUpdateMoreThanOne(shopConfigDao.updateByPrimaryKeySelective(shopConfig));
			}
			logger.debug("updateShopConfigIndexNo(ShopConfigDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("门店配置信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_CONFIG_UPDATE_ERROR,"门店配置信息更新信息错误！",e);
		}
	
	}


	@Override
	public Page<ShopConfigDto> findSecondShopConfigPage(
			FindShopConfigPage findShopConfigPage)
			throws TsfaServiceException {
		logger.debug("findSecondShopConfigPage(FindShopConfigPage findShopConfigPage={}) - start", findShopConfigPage); //$NON-NLS-1$

		AssertUtils.notNull(findShopConfigPage);
		List<ShopConfigDto> returnList=null;
		int count = 0;
		try {
			returnList = shopConfigDao.findSecondShopConfigPage(findShopConfigPage);
			count = shopConfigDao.findSecondShopConfigPageCount(findShopConfigPage);
		}  catch (Exception e) {
			logger.error("门店配置信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SHOP_CONFIG_FIND_PAGE_ERROR,"门店配置信息不存在错误.！",e);
		}
		Page<ShopConfigDto> returnPage = new Page<ShopConfigDto>(returnList, count, findShopConfigPage);

		logger.debug("findSecondShopConfigPage(FindShopConfigPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public int deleteShopConfig(ShopConfigDto shopConfigDto) throws TsfaServiceException {
		logger.debug("deleteShopConfig(ShopConfigDto shopConfigDto={}) - start", shopConfigDto); //$NON-NLS-1$
		AssertUtils.notNull(shopConfigDto);
		AssertUtils.notNullAndEmpty(shopConfigDto.getCode(),"Code不能为空");
		int count = 0;
		try {
			count = shopConfigDao.deleteShopConfig(shopConfigDto);
		}  catch (Exception e) {
			logger.error("门店配置信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SHOP_CONFIG_UPDATE_ERROR,"门店配置信息删除错误.！",e);
		}

		logger.debug("deleteShopConfig(ShopConfigDto) - end - return value={}", count); //$NON-NLS-1$
		return  count;
	}
	
	
}
