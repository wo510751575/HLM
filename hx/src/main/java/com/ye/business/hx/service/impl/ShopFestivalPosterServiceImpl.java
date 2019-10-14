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
import com.ye.business.hx.dao.IShopFestivalPosterDao;
import com.ye.business.hx.domain.ShopFestivalPoster;
import com.ye.business.hx.dto.FindShopFestivalPosterPage;
import com.ye.business.hx.dto.ShopFestivalPosterDto;
import com.ye.business.hx.service.IShopFestivalPosterService;

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
 *         CreateDate: 2019.02.19
 */
@Service
public class ShopFestivalPosterServiceImpl implements IShopFestivalPosterService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ShopFestivalPosterServiceImpl.class);

	@Resource
	private IShopFestivalPosterDao shopFestivalPosterDao;

	@Override
	public ShopFestivalPosterDto addShopFestivalPoster(ShopFestivalPosterDto shopFestivalPosterDto)
			throws TsfaServiceException {
		logger.debug("addShopFestivalPoster(AddShopFestivalPoster addShopFestivalPoster={}) - start",
				shopFestivalPosterDto);

		AssertUtils.notNull(shopFestivalPosterDto);
		try {
			//同一张源图 同一个门诊 同一个终端 不重复  
			ShopFestivalPoster record=new ShopFestivalPoster();
			record.setMerchantNo(shopFestivalPosterDto.getMerchantNo());
			record.setFpCode(shopFestivalPosterDto.getFpCode());
			record.setTemplateImg(shopFestivalPosterDto.getTemplateImg());
			shopFestivalPosterDao.deleteByShopFestivalPoster(record);
			
			//2.新增
			ShopFestivalPoster shopFestivalPoster = new ShopFestivalPoster();
			// add数据录入
			shopFestivalPoster.setCode(GUID.getPreUUID());
			shopFestivalPoster.setFpCode(shopFestivalPosterDto.getFpCode());
			shopFestivalPoster.setShopNo(shopFestivalPosterDto.getShopNo());
			shopFestivalPoster.setShopName(shopFestivalPosterDto.getShopName());
			shopFestivalPoster.setMerchantNo(shopFestivalPosterDto.getMerchantNo());
			shopFestivalPoster.setMerchantName(shopFestivalPosterDto.getMerchantName());
			shopFestivalPoster.setShopWx(shopFestivalPosterDto.getShopWx());
			shopFestivalPoster.setTypeName(shopFestivalPosterDto.getTypeName());
			shopFestivalPoster.setTemplateImg(shopFestivalPosterDto.getTemplateImg());
			shopFestivalPoster.setQcordImg(shopFestivalPosterDto.getQcordImg());
			shopFestivalPoster.setUpdateId(shopFestivalPosterDto.getUpdateId());
			shopFestivalPoster.setUpdateDate(new Date());
			shopFestivalPoster.setCreateId(shopFestivalPosterDto.getCreateId());
			shopFestivalPoster.setCreateDate(new Date());
			shopFestivalPoster.setRemark(shopFestivalPosterDto.getRemark());
			shopFestivalPoster.setRemark1(shopFestivalPosterDto.getRemark1());
			shopFestivalPoster.setRemark2(shopFestivalPosterDto.getRemark2());
			shopFestivalPoster.setRemark3(shopFestivalPosterDto.getRemark3());
			shopFestivalPoster.setRemark4(shopFestivalPosterDto.getRemark4());
			shopFestivalPosterDao.insertSelective(shopFestivalPoster);
			logger.debug("addShopFestivalPoster(ShopFestivalPosterDto) - end - return");

			ShopFestivalPosterDto rtDto = new ShopFestivalPosterDto();
			rtDto.setCode(shopFestivalPoster.getCode());
			return rtDto;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增门诊节日问候海报信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_FESTIVAL_POSTER_ADD_ERROR, "新增门诊节日问候海报信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询门诊节日问候海报信息
	 *
	 * @param findShopFestivalPosterPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<ShopFestivalPosterDto> findShopFestivalPosters(FindShopFestivalPosterPage findShopFestivalPosterPage)
			throws TsfaServiceException {
		AssertUtils.notNull(findShopFestivalPosterPage);
		List<ShopFestivalPosterDto> returnList = null;
		try {
			returnList = shopFestivalPosterDao.findShopFestivalPosters(findShopFestivalPosterPage);
		} catch (Exception e) {
			logger.error("查找门诊节日问候海报信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_FESTIVAL_POSTER_NOT_EXIST_ERROR, "门诊节日问候海报信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateShopFestivalPoster(ShopFestivalPosterDto shopFestivalPosterDto) throws TsfaServiceException {
		logger.debug("updateShopFestivalPoster(ShopFestivalPosterDto shopFestivalPosterDto={}) - start", //$NON-NLS-1$
				shopFestivalPosterDto);

		AssertUtils.notNull(shopFestivalPosterDto);
		AssertUtils.notNullAndEmpty(shopFestivalPosterDto.getCode(), "Code不能为空");
		try {
			ShopFestivalPoster shopFestivalPoster = new ShopFestivalPoster();
			// update数据录入
			shopFestivalPoster.setCode(shopFestivalPosterDto.getCode());
			shopFestivalPoster.setFpCode(shopFestivalPosterDto.getFpCode());
			shopFestivalPoster.setShopNo(shopFestivalPosterDto.getShopNo());
			shopFestivalPoster.setShopName(shopFestivalPosterDto.getShopName());
			shopFestivalPoster.setMerchantNo(shopFestivalPosterDto.getMerchantNo());
			shopFestivalPoster.setMerchantName(shopFestivalPosterDto.getMerchantName());
			shopFestivalPoster.setShopWx(shopFestivalPosterDto.getShopWx());
			shopFestivalPoster.setTypeName(shopFestivalPosterDto.getTypeName());
			shopFestivalPoster.setTemplateImg(shopFestivalPosterDto.getTemplateImg());
			shopFestivalPoster.setQcordImg(shopFestivalPosterDto.getQcordImg());
			shopFestivalPoster.setUpdateId(shopFestivalPosterDto.getUpdateId());
			shopFestivalPoster.setUpdateDate(new Date());
//			shopFestivalPoster.setCreateId(shopFestivalPosterDto.getCreateId());
//			shopFestivalPoster.setCreateDate(shopFestivalPosterDto.getCreateDate());
			shopFestivalPoster.setRemark(shopFestivalPosterDto.getRemark());
			shopFestivalPoster.setRemark1(shopFestivalPosterDto.getRemark1());
			shopFestivalPoster.setRemark2(shopFestivalPosterDto.getRemark2());
			shopFestivalPoster.setRemark3(shopFestivalPosterDto.getRemark3());
			shopFestivalPoster.setRemark4(shopFestivalPosterDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(shopFestivalPosterDao.updateByPrimaryKeySelective(shopFestivalPoster));
			logger.debug("updateShopFestivalPoster(ShopFestivalPosterDto) - end - return"); //$NON-NLS-1$
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("门诊节日问候海报信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_FESTIVAL_POSTER_UPDATE_ERROR, "门诊节日问候海报信息更新信息错误！", e);
		}
	}

	@Override
	public ShopFestivalPosterDto findShopFestivalPoster(ShopFestivalPosterDto shopFestivalPosterDto)
			throws TsfaServiceException {
		logger.debug("findShopFestivalPoster(FindShopFestivalPoster findShopFestivalPoster={}) - start", //$NON-NLS-1$
				shopFestivalPosterDto);

		AssertUtils.notNull(shopFestivalPosterDto);
		AssertUtils.notAllNull(shopFestivalPosterDto.getCode(), "Code不能为空");
		try {
			ShopFestivalPoster shopFestivalPoster = shopFestivalPosterDao
					.selectByPrimaryKey(shopFestivalPosterDto.getCode());
			if (shopFestivalPoster == null) {
				return null;
				// throw new
				// TsfaServiceException(ErrorCode.SHOP_FESTIVAL_POSTER_NOT_EXIST_ERROR,"门诊节日问候海报信息不存在");
			}
			ShopFestivalPosterDto findShopFestivalPosterReturn = new ShopFestivalPosterDto();
			// find数据录入
			findShopFestivalPosterReturn.setCode(shopFestivalPoster.getCode());
			findShopFestivalPosterReturn.setFpCode(shopFestivalPoster.getFpCode());
			findShopFestivalPosterReturn.setShopNo(shopFestivalPoster.getShopNo());
			findShopFestivalPosterReturn.setShopName(shopFestivalPoster.getShopName());
			findShopFestivalPosterReturn.setMerchantNo(shopFestivalPoster.getMerchantNo());
			findShopFestivalPosterReturn.setMerchantName(shopFestivalPoster.getMerchantName());
			findShopFestivalPosterReturn.setShopWx(shopFestivalPoster.getShopWx());
			findShopFestivalPosterReturn.setTypeName(shopFestivalPoster.getTypeName());
			findShopFestivalPosterReturn.setTemplateImg(shopFestivalPoster.getTemplateImg());
			findShopFestivalPosterReturn.setQcordImg(shopFestivalPoster.getQcordImg());
			findShopFestivalPosterReturn.setUpdateId(shopFestivalPoster.getUpdateId());
			findShopFestivalPosterReturn.setUpdateDate(shopFestivalPoster.getUpdateDate());
			findShopFestivalPosterReturn.setCreateId(shopFestivalPoster.getCreateId());
			findShopFestivalPosterReturn.setCreateDate(shopFestivalPoster.getCreateDate());
			findShopFestivalPosterReturn.setRemark(shopFestivalPoster.getRemark());
			findShopFestivalPosterReturn.setRemark1(shopFestivalPoster.getRemark1());
			findShopFestivalPosterReturn.setRemark2(shopFestivalPoster.getRemark2());
			findShopFestivalPosterReturn.setRemark3(shopFestivalPoster.getRemark3());
			findShopFestivalPosterReturn.setRemark4(shopFestivalPoster.getRemark4());

			logger.debug("findShopFestivalPoster(ShopFestivalPosterDto) - end - return value={}", //$NON-NLS-1$
					findShopFestivalPosterReturn);
			return findShopFestivalPosterReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找门诊节日问候海报信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_FESTIVAL_POSTER_FIND_ERROR, "查找门诊节日问候海报信息信息错误！", e);
		}

	}

	@Override
	public Page<ShopFestivalPosterDto> findShopFestivalPosterPage(FindShopFestivalPosterPage findShopFestivalPosterPage)
			throws TsfaServiceException {
		logger.debug("findShopFestivalPosterPage(FindShopFestivalPosterPage findShopFestivalPosterPage={}) - start", //$NON-NLS-1$
				findShopFestivalPosterPage);

		AssertUtils.notNull(findShopFestivalPosterPage);
		List<ShopFestivalPosterDto> returnList = null;
		int count = 0;
		try {
			returnList = shopFestivalPosterDao.findShopFestivalPosterPage(findShopFestivalPosterPage);
			count = shopFestivalPosterDao.findShopFestivalPosterPageCount(findShopFestivalPosterPage);
		} catch (Exception e) {
			logger.error("门诊节日问候海报信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.SHOP_FESTIVAL_POSTER_FIND_PAGE_ERROR, "门诊节日问候海报信息不存在错误.！", e);
		}
		Page<ShopFestivalPosterDto> returnPage = new Page<ShopFestivalPosterDto>(returnList, count,
				findShopFestivalPosterPage);

		logger.debug("findShopFestivalPosterPage(FindShopFestivalPosterPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return returnPage;
	}


	/**
	 * 
	 *
	 * 方法说明：不分页查询门诊节日问候海报信息
	 *
	 * @param findShopFestivalPosterPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<ShopFestivalPosterDto> findShopFestivalPostersByTemplateImg(FindShopFestivalPosterPage findShopFestivalPosterPage)
			throws TsfaServiceException {
		AssertUtils.notNull(findShopFestivalPosterPage);
		List<ShopFestivalPosterDto> returnList = null;
		try {
			returnList = shopFestivalPosterDao.findShopFestivalPostersByTemplateImg(findShopFestivalPosterPage);
		} catch (Exception e) {
			logger.error("查找门诊节日问候海报信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_FESTIVAL_POSTER_NOT_EXIST_ERROR, "门诊节日问候海报信息不存在");
		}
		return returnList;
	}
}
