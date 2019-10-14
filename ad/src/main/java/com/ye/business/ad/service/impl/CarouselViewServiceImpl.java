package com.ye.business.ad.service.impl;

/**
 * Copyright &copy; 2018-2021  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.ad.constant.ErrorCodeCarouselView;
import com.ye.business.ad.dao.ICarouselViewDao;
import com.ye.business.ad.domain.CarouselView;
import com.ye.business.ad.dto.CarouselDto;
import com.ye.business.ad.dto.CarouselViewDto;
import com.ye.business.ad.dto.FindCarouselViewPage;
import com.ye.business.ad.service.ICarouselViewService;

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
public class CarouselViewServiceImpl implements ICarouselViewService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CarouselViewServiceImpl.class);

	@Resource
	private ICarouselViewDao carouselViewDao;

	@Override
	public String addCarouselView(CarouselViewDto carouselViewDto) throws TsfaServiceException {
		logger.debug("addCarouselView(AddCarouselView addCarouselView={}) - start", carouselViewDto);

		AssertUtils.notNull(carouselViewDto);
		try {
			CarouselView carouselView = new CarouselView();
			// add数据录入
			carouselView.setCode(GUID.getPreUUID());
			carouselView.setMerchantNo(carouselViewDto.getMerchantNo());
			carouselView.setCarouselCode(carouselViewDto.getCarouselCode());
			carouselView.setCreateId(carouselViewDto.getCreateId());
			carouselView.setCretaeName(carouselViewDto.getCretaeName());
			carouselView.setCreateDate(carouselViewDto.getCreateDate());
			carouselView.setRemark(carouselViewDto.getRemark());
			carouselView.setRemark2(carouselViewDto.getRemark2());
			carouselView.setRemark3(carouselViewDto.getRemark3());
			carouselView.setRemark4(carouselViewDto.getRemark4());
			carouselView.setUpdateTime(carouselViewDto.getUpdateTime());
			carouselViewDao.insertSelective(carouselView);
			logger.debug("addCarouselView(CarouselViewDto) - end - return");

			return carouselView.getCode();

		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增广告查看记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarouselView.CAROUSEL_VIEW_ADD_ERROR, "新增广告查看记录信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询广告查看记录信息
	 *
	 * @param findCarouselViewPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<CarouselViewDto> findCarouselViews(FindCarouselViewPage findCarouselViewPage) throws TsfaServiceException {
		AssertUtils.notNull(findCarouselViewPage);
		List<CarouselViewDto> returnList = null;
		try {
			returnList = carouselViewDao.findCarouselViews(findCarouselViewPage);
		} catch (Exception e) {
			logger.error("查找广告查看记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarouselView.CAROUSEL_VIEW_NOT_EXIST_ERROR, "广告查看记录信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateCarouselView(CarouselViewDto carouselViewDto) throws TsfaServiceException {
		logger.debug("updateCarouselView(CarouselViewDto carouselViewDto={}) - start", carouselViewDto);

		AssertUtils.notNull(carouselViewDto);
		AssertUtils.notNullAndEmpty(carouselViewDto.getCode(), "Code不能为空");
		try {
			CarouselView carouselView = new CarouselView();
			// update数据录入
			carouselView.setCode(carouselViewDto.getCode());
			carouselView.setMerchantNo(carouselViewDto.getMerchantNo());
			carouselView.setCarouselCode(carouselViewDto.getCarouselCode());
			carouselView.setCreateId(carouselViewDto.getCreateId());
			carouselView.setCretaeName(carouselViewDto.getCretaeName());
			carouselView.setCreateDate(carouselViewDto.getCreateDate());
			carouselView.setRemark(carouselViewDto.getRemark());
			carouselView.setRemark2(carouselViewDto.getRemark2());
			carouselView.setRemark3(carouselViewDto.getRemark3());
			carouselView.setRemark4(carouselViewDto.getRemark4());
			carouselView.setUpdateTime(carouselViewDto.getUpdateTime());
			AssertUtils.notUpdateMoreThanOne(carouselViewDao.updateByPrimaryKeySelective(carouselView));
			logger.debug("updateCarouselView(CarouselViewDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("广告查看记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarouselView.CAROUSEL_VIEW_UPDATE_ERROR, "广告查看记录信息更新信息错误！", e);
		}
	}

	@Override
	public CarouselViewDto findCarouselView(CarouselViewDto carouselViewDto) throws TsfaServiceException {
		logger.debug("findCarouselView(FindCarouselView findCarouselView={}) - start", carouselViewDto);

		AssertUtils.notNull(carouselViewDto);
		AssertUtils.notAllNull(carouselViewDto.getCode(), "Code不能为空");
		try {
			CarouselView carouselView = carouselViewDao.selectByPrimaryKey(carouselViewDto.getCode());
			if (carouselView == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.CAROUSEL_VIEW_NOT_EXIST_ERROR,"广告查看记录信息不存在");
			}
			CarouselViewDto findCarouselViewReturn = new CarouselViewDto();
			// find数据录入
			findCarouselViewReturn.setCode(carouselView.getCode());
			findCarouselViewReturn.setMerchantNo(carouselView.getMerchantNo());
			findCarouselViewReturn.setCarouselCode(carouselView.getCarouselCode());
			findCarouselViewReturn.setCreateId(carouselView.getCreateId());
			findCarouselViewReturn.setCretaeName(carouselView.getCretaeName());
			findCarouselViewReturn.setCreateDate(carouselView.getCreateDate());
			findCarouselViewReturn.setRemark(carouselView.getRemark());
			findCarouselViewReturn.setRemark2(carouselView.getRemark2());
			findCarouselViewReturn.setRemark3(carouselView.getRemark3());
			findCarouselViewReturn.setRemark4(carouselView.getRemark4());
			findCarouselViewReturn.setUpdateTime(carouselView.getUpdateTime());

			logger.debug("findCarouselView(CarouselViewDto) - end - return value={}", findCarouselViewReturn);
			return findCarouselViewReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找广告查看记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarouselView.CAROUSEL_VIEW_FIND_ERROR, "查找广告查看记录信息信息错误！", e);
		}

	}

	@Override
	public Page<CarouselViewDto> findCarouselViewPage(FindCarouselViewPage findCarouselViewPage) throws TsfaServiceException {
		logger.debug("findCarouselViewPage(FindCarouselViewPage findCarouselViewPage={}) - start", findCarouselViewPage);

		AssertUtils.notNull(findCarouselViewPage);
		List<CarouselViewDto> returnList = null;
		int count = 0;
		try {
			returnList = carouselViewDao.findCarouselViewPage(findCarouselViewPage);
			count = carouselViewDao.findCarouselViewPageCount(findCarouselViewPage);
		} catch (Exception e) {
			logger.error("广告查看记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeCarouselView.CAROUSEL_VIEW_FIND_PAGE_ERROR, "广告查看记录信息不存在错误.！", e);
		}
		Page<CarouselViewDto> returnPage = new Page<CarouselViewDto>(returnList, count, findCarouselViewPage);

		logger.debug("findCarouselViewPage(FindCarouselViewPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public Map<String, Integer> findCarouselViewPageCountForGroupCarouselCode(List<String> carouselCodeList) {
		FindCarouselViewPage findCarouselViewPage = new FindCarouselViewPage();
		CarouselViewDto param = new CarouselViewDto();
		param.setCarouselCodeList(carouselCodeList);
		findCarouselViewPage.setParam(param);

		List<Map<String, Object>> data = carouselViewDao.findCarouselViewPageCountForGroupCarouselCode(findCarouselViewPage);

		Map<String, Integer> rs = data.stream().collect(Collectors.toMap(map -> map.get("carouselCode").toString(), map -> Integer.parseInt(map.get("num").toString())));
		return rs;
	}

	@Override
	public List<CarouselViewDto> findGroupTotalByExample(CarouselDto record) throws TsfaServiceException {
		return carouselViewDao.findGroupTotalByExample(record);
	}

}
