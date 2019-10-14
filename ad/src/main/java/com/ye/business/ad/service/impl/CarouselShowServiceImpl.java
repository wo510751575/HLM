package com.ye.business.ad.service.impl;

import java.util.Date;
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
import com.ye.business.ad.constant.ErrorCodeCarouselShow;
import com.ye.business.ad.dao.ICarouselShowDao;
import com.ye.business.ad.domain.CarouselShow;
import com.ye.business.ad.dto.CarouselDto;
import com.ye.business.ad.dto.CarouselShowDto;
import com.ye.business.ad.dto.FindCarouselShowPage;
import com.ye.business.ad.service.ICarouselShowService;

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
public class CarouselShowServiceImpl implements ICarouselShowService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CarouselShowServiceImpl.class);

	@Resource
	private ICarouselShowDao carouselShowDao;

	@Override
	public String addCarouselShow(CarouselShowDto carouselShowDto) throws TsfaServiceException {
		logger.debug("addCarouselShow(AddCarouselShow addCarouselShow={}) - start", carouselShowDto);

		AssertUtils.notNull(carouselShowDto);
		try {
			CarouselShow carouselShow = new CarouselShow();
			// add数据录入
			carouselShow.setCode(GUID.getPreUUID());
			carouselShow.setMerchantNo(carouselShowDto.getMerchantNo());
			carouselShow.setCarouselCode(carouselShowDto.getCarouselCode());
			carouselShow.setCreateId(carouselShowDto.getCreateId());
			carouselShow.setCretaeName(carouselShowDto.getCretaeName());
			carouselShow.setCreateDate(carouselShowDto.getCreateDate());
			carouselShow.setRemark(carouselShowDto.getRemark());
			carouselShow.setRemark2(carouselShowDto.getRemark2());
			carouselShow.setRemark3(carouselShowDto.getRemark3());
			carouselShow.setRemark4(carouselShowDto.getRemark4());
			carouselShow.setUpdateTime(carouselShowDto.getUpdateTime());
			carouselShowDao.insertSelective(carouselShow);
			logger.debug("addCarouselShow(CarouselShowDto) - end - return");

			return carouselShow.getCode();

		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增广告显示记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarouselShow.CAROUSEL_SHOW_ADD_ERROR, "新增广告显示记录信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询广告显示记录信息
	 *
	 * @param findCarouselShowPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<CarouselShowDto> findCarouselShows(FindCarouselShowPage findCarouselShowPage) throws TsfaServiceException {
		AssertUtils.notNull(findCarouselShowPage);
		List<CarouselShowDto> returnList = null;
		try {
			returnList = carouselShowDao.findCarouselShows(findCarouselShowPage);
		} catch (Exception e) {
			logger.error("查找广告显示记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarouselShow.CAROUSEL_SHOW_NOT_EXIST_ERROR, "广告显示记录信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateCarouselShow(CarouselShowDto carouselShowDto) throws TsfaServiceException {
		logger.debug("updateCarouselShow(CarouselShowDto carouselShowDto={}) - start", carouselShowDto);

		AssertUtils.notNull(carouselShowDto);
		AssertUtils.notNullAndEmpty(carouselShowDto.getCode(), "Code不能为空");
		try {
			CarouselShow carouselShow = new CarouselShow();
			// update数据录入
			carouselShow.setCode(carouselShowDto.getCode());
			carouselShow.setMerchantNo(carouselShowDto.getMerchantNo());
			carouselShow.setCarouselCode(carouselShowDto.getCarouselCode());
			carouselShow.setCreateId(carouselShowDto.getCreateId());
			carouselShow.setCretaeName(carouselShowDto.getCretaeName());
			carouselShow.setCreateDate(carouselShowDto.getCreateDate());
			carouselShow.setRemark(carouselShowDto.getRemark());
			carouselShow.setRemark2(carouselShowDto.getRemark2());
			carouselShow.setRemark3(carouselShowDto.getRemark3());
			carouselShow.setRemark4(carouselShowDto.getRemark4());
			carouselShow.setUpdateTime(carouselShowDto.getUpdateTime());
			AssertUtils.notUpdateMoreThanOne(carouselShowDao.updateByPrimaryKeySelective(carouselShow));
			logger.debug("updateCarouselShow(CarouselShowDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("广告显示记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarouselShow.CAROUSEL_SHOW_UPDATE_ERROR, "广告显示记录信息更新信息错误！", e);
		}
	}

	@Override
	public CarouselShowDto findCarouselShow(CarouselShowDto carouselShowDto) throws TsfaServiceException {
		logger.debug("findCarouselShow(FindCarouselShow findCarouselShow={}) - start", carouselShowDto);

		AssertUtils.notNull(carouselShowDto);
		AssertUtils.notAllNull(carouselShowDto.getCode(), "Code不能为空");
		try {
			CarouselShow carouselShow = carouselShowDao.selectByPrimaryKey(carouselShowDto.getCode());
			if (carouselShow == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.CAROUSEL_SHOW_NOT_EXIST_ERROR,"广告显示记录信息不存在");
			}
			CarouselShowDto findCarouselShowReturn = new CarouselShowDto();
			// find数据录入
			findCarouselShowReturn.setCode(carouselShow.getCode());
			findCarouselShowReturn.setMerchantNo(carouselShow.getMerchantNo());
			findCarouselShowReturn.setCarouselCode(carouselShow.getCarouselCode());
			findCarouselShowReturn.setCreateId(carouselShow.getCreateId());
			findCarouselShowReturn.setCretaeName(carouselShow.getCretaeName());
			findCarouselShowReturn.setCreateDate(carouselShow.getCreateDate());
			findCarouselShowReturn.setRemark(carouselShow.getRemark());
			findCarouselShowReturn.setRemark2(carouselShow.getRemark2());
			findCarouselShowReturn.setRemark3(carouselShow.getRemark3());
			findCarouselShowReturn.setRemark4(carouselShow.getRemark4());
			findCarouselShowReturn.setUpdateTime(carouselShow.getUpdateTime());

			logger.debug("findCarouselShow(CarouselShowDto) - end - return value={}", findCarouselShowReturn);
			return findCarouselShowReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找广告显示记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarouselShow.CAROUSEL_SHOW_FIND_ERROR, "查找广告显示记录信息信息错误！", e);
		}

	}

	@Override
	public Page<CarouselShowDto> findCarouselShowPage(FindCarouselShowPage findCarouselShowPage) throws TsfaServiceException {
		logger.debug("findCarouselShowPage(FindCarouselShowPage findCarouselShowPage={}) - start", findCarouselShowPage);

		AssertUtils.notNull(findCarouselShowPage);
		List<CarouselShowDto> returnList = null;
		int count = 0;
		try {
			returnList = carouselShowDao.findCarouselShowPage(findCarouselShowPage);
			count = carouselShowDao.findCarouselShowPageCount(findCarouselShowPage);
		} catch (Exception e) {
			logger.error("广告显示记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeCarouselShow.CAROUSEL_SHOW_FIND_PAGE_ERROR, "广告显示记录信息不存在错误.！", e);
		}
		Page<CarouselShowDto> returnPage = new Page<CarouselShowDto>(returnList, count, findCarouselShowPage);

		logger.debug("findCarouselShowPage(FindCarouselShowPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void saveCarouselShowForTrack(List<String> carouselCodeList, String updateId, Date date, String ip) {
		logger.debug("saveCarouselShowForTrack");

		AssertUtils.notNull(carouselCodeList);
		AssertUtils.notNullAndEmpty(updateId);
		AssertUtils.notNull(date);

		carouselCodeList.forEach(code -> {

			CarouselShowDto temp = new CarouselShowDto();
			temp.setCarouselCode(code);
			temp.setCreateDate(date);
			temp.setUpdateTime(date);
			temp.setCreateId(updateId);
			temp.setRemark(ip);

			addCarouselShow(temp);
		});
	}

	@Override
	public Map<String, Integer> findCarouselShowPageCountForGroupCarouselCode(List<String> carouselCodeList) {
		FindCarouselShowPage findCarouselShowPage = new FindCarouselShowPage();
		CarouselShowDto param = new CarouselShowDto();
		param.setCarouselCodeList(carouselCodeList);
		findCarouselShowPage.setParam(param);

		List<Map<String, Object>> data = carouselShowDao.findCarouselShowPageCountForGroupCarouselCode(findCarouselShowPage);

		Map<String, Integer> rs = data.stream().collect(Collectors.toMap(map -> map.get("carouselCode").toString(), map -> Integer.parseInt(map.get("num").toString())));
		return rs;
	}

	@Override
	public List<CarouselShowDto> findGroupTotalByExample(CarouselDto record) throws TsfaServiceException {
		return carouselShowDao.findGroupTotalByExample(record);
	}
}
