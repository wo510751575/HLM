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
import com.ye.business.ad.constant.ErrorCodeAdvertiseType;
import com.ye.business.ad.dao.IAdvertiseTypeDao;
import com.ye.business.ad.domain.AdvertiseType;
import com.ye.business.ad.dto.AdvertiseTypeDto;
import com.ye.business.ad.dto.FindAdvertiseTypePage;
import com.ye.business.ad.service.IAdvertiseTypeService;

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
public class AdvertiseTypeServiceImpl implements IAdvertiseTypeService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AdvertiseTypeServiceImpl.class);

	@Resource
	private IAdvertiseTypeDao advertiseTypeDao;

	@Override
	public String addAdvertiseType(AdvertiseTypeDto advertiseTypeDto) throws TsfaServiceException {
		logger.debug("addAdvertiseType(AddAdvertiseType addAdvertiseType={}) - start", advertiseTypeDto);

		AssertUtils.notNull(advertiseTypeDto);
		try {
			AdvertiseType advertiseType = new AdvertiseType();
			// add数据录入
			advertiseType.setCode(GUID.getPreUUID());
			advertiseType.setMerchantNo(advertiseTypeDto.getMerchantNo());
			advertiseType.setTypeName(advertiseTypeDto.getTypeName());
			advertiseType.setStatus(advertiseTypeDto.getStatus());
			advertiseType.setSeq(advertiseTypeDto.getSeq());
			advertiseType.setCreateId(advertiseTypeDto.getCreateId());
			advertiseType.setCreateDate(advertiseTypeDto.getCreateDate());
			advertiseType.setRemark(advertiseTypeDto.getRemark());
			advertiseType.setRemark2(advertiseTypeDto.getRemark2());
			advertiseType.setRemark3(advertiseTypeDto.getRemark3());
			advertiseType.setRemark4(advertiseTypeDto.getRemark4());
			advertiseType.setUpdateTime(advertiseTypeDto.getUpdateTime());
			advertiseTypeDao.insertSelective(advertiseType);
			logger.debug("addAdvertiseType(AdvertiseTypeDto) - end - return");

			return advertiseType.getCode();

		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增广告类型信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseType.ADVERTISE_TYPE_ADD_ERROR, "新增广告类型信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询广告类型信息
	 *
	 * @param findAdvertiseTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<AdvertiseTypeDto> findAdvertiseTypes(FindAdvertiseTypePage findAdvertiseTypePage) throws TsfaServiceException {
		AssertUtils.notNull(findAdvertiseTypePage);
		List<AdvertiseTypeDto> returnList = null;
		try {
			returnList = advertiseTypeDao.findAdvertiseTypes(findAdvertiseTypePage);
		} catch (Exception e) {
			logger.error("查找广告类型信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseType.ADVERTISE_TYPE_NOT_EXIST_ERROR, "广告类型信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateAdvertiseType(AdvertiseTypeDto advertiseTypeDto) throws TsfaServiceException {
		logger.debug("updateAdvertiseType(AdvertiseTypeDto advertiseTypeDto={}) - start", advertiseTypeDto);

		AssertUtils.notNull(advertiseTypeDto);
		AssertUtils.notNullAndEmpty(advertiseTypeDto.getCode(), "Code不能为空");
		try {
			AdvertiseType advertiseType = new AdvertiseType();
			// update数据录入
			advertiseType.setCode(advertiseTypeDto.getCode());
			advertiseType.setMerchantNo(advertiseTypeDto.getMerchantNo());
			advertiseType.setTypeName(advertiseTypeDto.getTypeName());
			advertiseType.setStatus(advertiseTypeDto.getStatus());
			advertiseType.setSeq(advertiseTypeDto.getSeq());
			advertiseType.setCreateId(advertiseTypeDto.getCreateId());
			advertiseType.setCreateDate(advertiseTypeDto.getCreateDate());
			advertiseType.setRemark(advertiseTypeDto.getRemark());
			advertiseType.setRemark2(advertiseTypeDto.getRemark2());
			advertiseType.setRemark3(advertiseTypeDto.getRemark3());
			advertiseType.setRemark4(advertiseTypeDto.getRemark4());
			advertiseType.setUpdateTime(advertiseTypeDto.getUpdateTime());
			AssertUtils.notUpdateMoreThanOne(advertiseTypeDao.updateByPrimaryKeySelective(advertiseType));
			logger.debug("updateAdvertiseType(AdvertiseTypeDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("广告类型信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseType.ADVERTISE_TYPE_UPDATE_ERROR, "广告类型信息更新信息错误！", e);
		}
	}

	@Override
	public AdvertiseTypeDto findAdvertiseType(AdvertiseTypeDto advertiseTypeDto) throws TsfaServiceException {
		logger.debug("findAdvertiseType(FindAdvertiseType findAdvertiseType={}) - start", advertiseTypeDto);

		AssertUtils.notNull(advertiseTypeDto);
		AssertUtils.notAllNull(advertiseTypeDto.getCode(), "Code不能为空");
		try {
			AdvertiseType advertiseType = advertiseTypeDao.selectByPrimaryKey(advertiseTypeDto.getCode());
			if (advertiseType == null) {
				return null;
				// throw new TsfaServiceException(ErrorCodeAdvertiseType.ADVERTISE_TYPE_NOT_EXIST_ERROR,"广告类型信息不存在");
			}
			AdvertiseTypeDto findAdvertiseTypeReturn = new AdvertiseTypeDto();
			// find数据录入
			findAdvertiseTypeReturn.setCode(advertiseType.getCode());
			findAdvertiseTypeReturn.setMerchantNo(advertiseType.getMerchantNo());
			findAdvertiseTypeReturn.setTypeName(advertiseType.getTypeName());
			findAdvertiseTypeReturn.setStatus(advertiseType.getStatus());
			findAdvertiseTypeReturn.setSeq(advertiseType.getSeq());
			findAdvertiseTypeReturn.setCreateId(advertiseType.getCreateId());
			findAdvertiseTypeReturn.setCreateDate(advertiseType.getCreateDate());
			findAdvertiseTypeReturn.setRemark(advertiseType.getRemark());
			findAdvertiseTypeReturn.setRemark2(advertiseType.getRemark2());
			findAdvertiseTypeReturn.setRemark3(advertiseType.getRemark3());
			findAdvertiseTypeReturn.setRemark4(advertiseType.getRemark4());
			findAdvertiseTypeReturn.setUpdateTime(advertiseType.getUpdateTime());

			logger.debug("findAdvertiseType(AdvertiseTypeDto) - end - return value={}", findAdvertiseTypeReturn);
			return findAdvertiseTypeReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找广告类型信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseType.ADVERTISE_TYPE_FIND_ERROR, "查找广告类型信息信息错误！", e);
		}

	}

	@Override
	public Page<AdvertiseTypeDto> findAdvertiseTypePage(FindAdvertiseTypePage findAdvertiseTypePage) throws TsfaServiceException {
		logger.debug("findAdvertiseTypePage(FindAdvertiseTypePage findAdvertiseTypePage={}) - start", findAdvertiseTypePage);

		AssertUtils.notNull(findAdvertiseTypePage);
		List<AdvertiseTypeDto> returnList = null;
		int count = 0;
		try {
			returnList = advertiseTypeDao.findAdvertiseTypePage(findAdvertiseTypePage);
			count = advertiseTypeDao.findAdvertiseTypePageCount(findAdvertiseTypePage);
		} catch (Exception e) {
			logger.error("广告类型信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseType.ADVERTISE_TYPE_FIND_PAGE_ERROR, "广告类型信息不存在错误.！", e);
		}
		Page<AdvertiseTypeDto> returnPage = new Page<AdvertiseTypeDto>(returnList, count, findAdvertiseTypePage);

		logger.debug("findAdvertiseTypePage(FindAdvertiseTypePage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(advertiseTypeDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("广告信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseType.ADVERTISE_TYPE_UPDATE_ERROR, "广告类型信息刪除信息错误！", e);
		}
	}

}
