package com.ye.business.ad.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.domain.JobCenter;
import com.lj.cc.service.IJobMgrService;
import com.ye.business.ad.constant.ErrorCodeCarousel;
import com.ye.business.ad.dao.ICarouselDao;
import com.ye.business.ad.domain.Carousel;
import com.ye.business.ad.dto.AdvertiseTaskDto;
import com.ye.business.ad.dto.CarouselDto;
import com.ye.business.ad.dto.CarouselViewDto;
import com.ye.business.ad.dto.FindCarouselPage;
import com.ye.business.ad.enums.AdvStatus;
import com.ye.business.ad.service.ICarouselService;
import com.ye.business.ad.service.ICarouselShowService;
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
public class CarouselServiceImpl implements ICarouselService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CarouselServiceImpl.class);

	@Resource
	private ICarouselDao carouselDao;

	@Autowired
	private ICarouselShowService carouselShowService;

	@Autowired
	private ICarouselViewService carouselViewService;

	@Resource
	private IJobMgrService jobMgrService;

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Override
	public String addCarousel(CarouselDto carouselDto) throws TsfaServiceException {
		logger.debug("addCarousel(AddCarousel addCarousel={}) - start", carouselDto);

		AssertUtils.notNull(carouselDto);
		try {
			Carousel carousel = new Carousel();
			// add数据录入
			carousel.setCode(GUID.getPreUUID());
			carousel.setMemberNoGuid(carouselDto.getMemberNoGuid());
			carousel.setMemberNameGuid(carouselDto.getMemberNameGuid());
			carousel.setShopNo(carouselDto.getShopNo());
			carousel.setShopName(carouselDto.getShopName());
			carousel.setMerchantNo(carouselDto.getMerchantNo());
			carousel.setMerchantName(carouselDto.getMerchantName());
			carousel.setMemberNo(carouselDto.getMemberNo());
			carousel.setState(carouselDto.getState());
			carousel.setLink(carouselDto.getLink());
			carousel.setAdvLink(carouselDto.getAdvLink());
			carousel.setAdvTypeCode(carouselDto.getAdvTypeCode());
			carousel.setAdvType(carouselDto.getAdvType());
			carousel.setSource(carouselDto.getSource());
			carousel.setNumOrder(carouselDto.getNumOrder());
			carousel.setRemark(carouselDto.getRemark());
			carousel.setCreateId(carouselDto.getCreateId());
			carousel.setCreateDate(carouselDto.getCreateDate());
			carousel.setUpdateId(carouselDto.getUpdateId());
			carousel.setUpdateDate(carouselDto.getUpdateDate());
			carousel.setPriceSum(carouselDto.getPriceSum());
			carousel.setPriceClick(carouselDto.getPriceClick());
			carousel.setPriceView(carouselDto.getPriceView());
			carousel.setReleaseDate(carouselDto.getReleaseDate());
			carousel.setUpDate(carouselDto.getUpDate());
			carousel.setDownDate(carouselDto.getDownDate());
			carousel.setAdvStatus(carouselDto.getAdvStatus());
			carouselDao.insertSelective(carousel);

			// 任务调度上架，下架
			saveParamJob(carousel.getCode(), carousel.getUpDate(), AdvertiseTaskDto.STATUS_UP);
			saveParamJob(carousel.getCode(), carousel.getDownDate(), AdvertiseTaskDto.STATUS_DOWN);

			logger.debug("addCarousel(CarouselDto) - end - return");
			return carousel.getCode();
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增轮播广告记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarousel.CAROUSEL_ADD_ERROR, "新增轮播广告记录信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询轮播广告记录信息
	 *
	 * @param findCarouselPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<CarouselDto> findCarousels(FindCarouselPage findCarouselPage) throws TsfaServiceException {
		AssertUtils.notNull(findCarouselPage);
		List<CarouselDto> returnList = null;
		try {
			returnList = carouselDao.findCarousels(findCarouselPage);

			// 插入广告显示信息
			if (findCarouselPage.getParam() != null && findCarouselPage.getParam().getHasTrack() != null && findCarouselPage.getParam().getHasTrack()) {

				String updateId = findCarouselPage.getParam().getUpdateId();
				String ip = findCarouselPage.getParam().getRemark();
				List<String> carouselCodeList = returnList.stream().map(CarouselDto::getCode).collect(Collectors.toList());

				if (StringUtils.isNotEmpty(updateId)) {
					carouselShowService.saveCarouselShowForTrack(carouselCodeList, updateId, new Date(), ip);
				}
			}

		} catch (Exception e) {
			logger.error("查找轮播广告记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarousel.CAROUSEL_NOT_EXIST_ERROR, "轮播广告记录信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateCarousel(CarouselDto carouselDto) throws TsfaServiceException {
		logger.debug("updateCarousel(CarouselDto carouselDto={}) - start", carouselDto);

		AssertUtils.notNull(carouselDto);
		AssertUtils.notNullAndEmpty(carouselDto.getCode(), "Code不能为空");
		try {
			Carousel carousel = new Carousel();
			// update数据录入
			carousel.setCode(carouselDto.getCode());
			carousel.setMemberNoGuid(carouselDto.getMemberNoGuid());
			carousel.setMemberNameGuid(carouselDto.getMemberNameGuid());
			carousel.setShopNo(carouselDto.getShopNo());
			carousel.setShopName(carouselDto.getShopName());
			carousel.setMerchantNo(carouselDto.getMerchantNo());
			carousel.setMerchantName(carouselDto.getMerchantName());
			carousel.setMemberNo(carouselDto.getMemberNo());
			carousel.setState(carouselDto.getState());
			carousel.setLink(carouselDto.getLink());
			carousel.setAdvLink(carouselDto.getAdvLink());
			carousel.setAdvTypeCode(carouselDto.getAdvTypeCode());
			carousel.setAdvType(carouselDto.getAdvType());
			carousel.setSource(carouselDto.getSource());
			carousel.setNumOrder(carouselDto.getNumOrder());
			carousel.setRemark(carouselDto.getRemark());
			carousel.setCreateId(carouselDto.getCreateId());
			carousel.setCreateDate(carouselDto.getCreateDate());
			carousel.setUpdateId(carouselDto.getUpdateId());
			carousel.setUpdateDate(carouselDto.getUpdateDate());
			carousel.setPriceSum(carouselDto.getPriceSum());
			carousel.setPriceClick(carouselDto.getPriceClick());
			carousel.setPriceView(carouselDto.getPriceView());
			carousel.setReleaseDate(carouselDto.getReleaseDate());
			carousel.setUpDate(carouselDto.getUpDate());
			carousel.setDownDate(carouselDto.getDownDate());
			carousel.setAdvStatus(carouselDto.getAdvStatus());
			AssertUtils.notUpdateMoreThanOne(carouselDao.updateByPrimaryKeySelective(carousel));

			// 任务调度上架，下架
			saveParamJob(carousel.getCode(), carousel.getUpDate(), AdvertiseTaskDto.STATUS_UP);
			saveParamJob(carousel.getCode(), carousel.getDownDate(), AdvertiseTaskDto.STATUS_DOWN);

			logger.debug("updateCarousel(CarouselDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("轮播广告记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarousel.CAROUSEL_UPDATE_ERROR, "轮播广告记录信息更新信息错误！", e);
		}
	}

	@Override
	public CarouselDto findCarousel(CarouselDto carouselDto) throws TsfaServiceException {
		logger.debug("findCarousel(FindCarousel findCarousel={}) - start", carouselDto);

		AssertUtils.notNull(carouselDto);
		AssertUtils.notAllNull(carouselDto.getCode(), "Code不能为空");
		try {
			Carousel carousel = carouselDao.selectByPrimaryKey(carouselDto.getCode());
			if (carousel == null) {
				return null;
				// throw new TsfaServiceException(ErrorCodeCarousel.CAROUSEL_NOT_EXIST_ERROR,"轮播广告记录信息不存在");
			}
			CarouselDto findCarouselReturn = new CarouselDto();
			// find数据录入
			findCarouselReturn.setCode(carousel.getCode());
			findCarouselReturn.setMemberNoGuid(carousel.getMemberNoGuid());
			findCarouselReturn.setMemberNameGuid(carousel.getMemberNameGuid());
			findCarouselReturn.setShopNo(carousel.getShopNo());
			findCarouselReturn.setShopName(carousel.getShopName());
			findCarouselReturn.setMerchantNo(carousel.getMerchantNo());
			findCarouselReturn.setMerchantName(carousel.getMerchantName());
			findCarouselReturn.setMemberNo(carousel.getMemberNo());
			findCarouselReturn.setState(carousel.getState());
			findCarouselReturn.setLink(carousel.getLink());
			findCarouselReturn.setAdvLink(carousel.getAdvLink());
			findCarouselReturn.setAdvTypeCode(carousel.getAdvTypeCode());
			findCarouselReturn.setAdvType(carousel.getAdvType());
			findCarouselReturn.setSource(carousel.getSource());
			findCarouselReturn.setNumOrder(carousel.getNumOrder());
			findCarouselReturn.setRemark(carousel.getRemark());
			findCarouselReturn.setCreateId(carousel.getCreateId());
			findCarouselReturn.setCreateDate(carousel.getCreateDate());
			findCarouselReturn.setUpdateId(carousel.getUpdateId());
			findCarouselReturn.setUpdateDate(carousel.getUpdateDate());
			findCarouselReturn.setPriceSum(carousel.getPriceSum());
			findCarouselReturn.setPriceClick(carousel.getPriceClick());
			findCarouselReturn.setPriceView(carousel.getPriceView());
			findCarouselReturn.setReleaseDate(carousel.getReleaseDate());
			findCarouselReturn.setUpDate(carousel.getUpDate());
			findCarouselReturn.setDownDate(carousel.getDownDate());
			findCarouselReturn.setAdvStatus(carousel.getAdvStatus());

			if (carouselDto.getHasTrack() != null && carouselDto.getHasTrack()) {
				// 记录查看广告信息
				CarouselViewDto carouselViewDto = new CarouselViewDto();
				Date now = new Date();

				carouselViewDto.setCreateDate(now);
				carouselViewDto.setCarouselCode(carousel.getCode());
				carouselViewDto.setUpdateTime(now);
				carouselViewDto.setCreateId(carouselDto.getUpdateId());
				carouselViewDto.setRemark(carouselDto.getRemark());

				carouselViewService.addCarouselView(carouselViewDto);
			}

			logger.debug("findCarousel(CarouselDto) - end - return value={}", findCarouselReturn);
			return findCarouselReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找轮播广告记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarousel.CAROUSEL_FIND_ERROR, "查找轮播广告记录信息信息错误！", e);
		}

	}

	@Override
	public Page<CarouselDto> findCarouselPage(FindCarouselPage findCarouselPage) throws TsfaServiceException {
		logger.debug("findCarouselPage(FindCarouselPage findCarouselPage={}) - start", findCarouselPage);

		AssertUtils.notNull(findCarouselPage);
		List<CarouselDto> returnList = null;
		int count = 0;
		try {
			returnList = carouselDao.findCarouselPage(findCarouselPage);
			count = carouselDao.findCarouselPageCount(findCarouselPage);

			if (findCarouselPage.getParam() != null && returnList != null && !returnList.isEmpty() && findCarouselPage.getParam().getHasTrack() != null
					&& findCarouselPage.getParam().getHasTrack()) {

				List<String> carouselCodeList = returnList.stream().map(CarouselDto::getCode).collect(Collectors.toList());
				// 统计展示次数
				Map<String, Integer> numShowMap = this.carouselShowService.findCarouselShowPageCountForGroupCarouselCode(carouselCodeList);
				// 统计查看点击次数
				Map<String, Integer> numViewMap = this.carouselViewService.findCarouselViewPageCountForGroupCarouselCode(carouselCodeList);

				returnList.forEach(action -> {
					if (numShowMap.containsKey(action.getCode())) {
						action.setNumShow(numShowMap.getOrDefault(action.getCode(), 0));
					}
					if (numViewMap.containsKey(action.getCode())) {
						action.setNumView(numViewMap.getOrDefault(action.getCode(), 0));
					}
				});

			}

		} catch (Exception e) {
			logger.error("轮播广告记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeCarousel.CAROUSEL_FIND_PAGE_ERROR, "轮播广告记录信息不存在错误.！", e);
		}
		Page<CarouselDto> returnPage = new Page<CarouselDto>(returnList, count, findCarouselPage);

		logger.debug("findCarouselPage(FindCarouselPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(carouselDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("轮播广告记录信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeCarousel.CAROUSEL_UPDATE_ERROR, "轮播广告记录信息刪除信息错误！", e);
		}
	}

	@Override
	public void batchUpdateCarouselForUpOrDown(String batchNum) throws TsfaServiceException {
		logger.info("批次{},1.开始记录正常轮播广告信息....", batchNum);

		FindCarouselPage findCarouselPage = new FindCarouselPage();

		CarouselDto param = new CarouselDto();

//		param.setState(RwState.normal.toString());
//		param.setAdvStatus(AdvStatus.up.toString()); // 已上架广告
		findCarouselPage.setParam(param);

		List<CarouselDto> recordList = carouselDao.findCarousels(findCarouselPage);

		if (CollectionUtils.isEmpty(recordList)) {
			logger.info("批次{},2.无需要上下架轮播广告记录....", batchNum);
			return;
		}

		LocalDateTime sysNow = LocalDateTime.now();

		// 筛选需要下架的广告记录;此处使用并行处理
		List<String> codeList = recordList.parallelStream().filter(pre -> {

			// 下架时间小于当前时间
			if (pre.getDownDate() != null && pre.getDownDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().isBefore(sysNow)) {
				return true;
			}

			return false;
		}).map(CarouselDto::getCode).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(codeList)) {
			logger.info("批次{},2.无需要下架轮播广告记录....", batchNum);
			return;
		}
		logger.info("批次{},2.筛选出下架轮播广告信息.... {}", batchNum, codeList);

		Date now = new Date();

		CarouselDto updateRecord = new CarouselDto();
		updateRecord.setAdvStatus(AdvStatus.down.toString());
		updateRecord.setCodeList(codeList);
		updateRecord.setUpDate(now);
		updateRecord.setUpdateId(batchNum);

		int count = carouselDao.updateByPrimaryKeyBatchSelective(updateRecord);

		logger.info("批次{},3.下架轮播广告信息成功{},总计：{}条....", batchNum, codeList, count);

		// 筛选需要下架的广告记录;此处使用并行处理
		List<String> codeUpList = recordList.parallelStream().filter(pre -> {

			// 下架时间小于当前时间
			if (pre.getUpDate() != null && pre.getUpDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().isBefore(sysNow)) {
				return true;
			}

			return false;
		}).map(CarouselDto::getCode).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(codeList)) {
			logger.info("批次{},4.无需要上架轮播广告记录....", batchNum);
			return;
		}
		logger.info("批次{},4.筛选出上架轮播广告信息.... {}", batchNum, codeUpList);

		CarouselDto updateUpRecord = new CarouselDto();
		updateUpRecord.setAdvStatus(AdvStatus.up.toString());
		updateUpRecord.setCodeList(codeUpList);
		updateUpRecord.setUpDate(now);
		updateUpRecord.setUpdateId(batchNum);

		int countUp = carouselDao.updateByPrimaryKeyBatchSelective(updateUpRecord);

		logger.info("批次{},5.上架轮播广告信息成功{},总计：{}条....", batchNum, codeUpList, countUp);
	}

	/**
	 * 
	 * *方法说明：新增临时任务调度
	 *
	 * @param code
	 * @param executeTime
	 * @param status
	 * @author sjiying
	 * @CreateDate 2019年7月18日
	 */
	private void saveParamJob(String code, Date executeTime, String status) {

		// 为空，不执行
		if (StringUtils.isEmpty(code) || StringUtils.isEmpty(status) || executeTime == null) {
			return;
		}
		// 执行时间小于当前时间，不执行
		if (executeTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().isBefore(LocalDateTime.now())) {
			return;
		}

		JobCenter jc = new JobCenter();
		jc.setIsEnable("1");
		// 构造corn表达式
		StringBuilder jobCalender = new StringBuilder("");
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(executeTime);// 设置日历时间

		jobCalender.append(calendar.get(Calendar.SECOND)).append(" ").append(calendar.get(Calendar.MINUTE)).append(" ").append(calendar.get(Calendar.HOUR_OF_DAY)).append(" ")
				.append(calendar.get(Calendar.DATE)).append(" ").append(calendar.get(Calendar.MONTH) + 1).append(" ?").append(" ").append(calendar.get(Calendar.YEAR));
		jc.setJobCalender(jobCalender.toString());// 0 47 11 25 12 ? 2017

		//
		String callbackUrl = localCacheSystemParams.getSystemParam("ad", "advertiseJob", "advertiseJobTaskCallbackUrl");
		jc.setJobIntf(callbackUrl);
		jc.setJobName("添加广告上下架任务回调地址");
		jc.setSystemAliasName("ad");

		String jobEnglishName = String.format("AddAdvertiseJob:%s:%s:%s:%d", code, status, AdvertiseTaskDto.TYPE_CAROUSEL, executeTime.getTime());
		jc.setJobEnglishName(jobEnglishName);

		AdvertiseTaskDto task = new AdvertiseTaskDto();
		task.setCode(code);
		task.setExecuteTime(executeTime);
		task.setStatus(status);
		task.setType(AdvertiseTaskDto.TYPE_CAROUSEL);
		jc.setJobParam(JsonUtils.jsonFromObject(task));

		jobMgrService.addTempJob(jc);
		logger.debug("AddAdvertiseJob: " + jc);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void updateForTask(AdvertiseTaskDto record) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(record);
		AssertUtils.notNullAndEmpty(record.getCode());
		AssertUtils.notNullAndEmpty(record.getStatus());
		AssertUtils.notNullAndEmpty(record.getExecuteTime());

		Carousel adRecord = carouselDao.selectByPrimaryKey(record.getCode());
		AssertUtils.notNullAndEmpty(adRecord);

		String batchNum = "" + System.currentTimeMillis();
		Date now = new Date();

		// 上架
		if (AdvertiseTaskDto.STATUS_UP.equals(record.getStatus())) {
			if (!record.getExecuteTime().equals(adRecord.getUpDate())) {
				return; // 上架时间不正确
			}
			if (AdvStatus.up.toString().equals(adRecord.getAdvStatus())) {
				return; // 已上架
			}
			Carousel updateRecord = new Carousel();
			updateRecord.setAdvStatus(AdvStatus.up.toString());
			updateRecord.setCode(record.getCode());
			updateRecord.setUpDate(now);
			updateRecord.setUpdateId(batchNum);

			carouselDao.updateByPrimaryKeySelective(updateRecord);
		}

		// 下架
		if (AdvertiseTaskDto.STATUS_DOWN.equals(record.getStatus())) {
			if (!record.getExecuteTime().equals(adRecord.getDownDate())) {
				return; // 下架时间不正确
			}

			Carousel updateRecord = new Carousel();
			updateRecord.setAdvStatus(AdvStatus.down.toString());
			updateRecord.setCode(record.getCode());
			updateRecord.setUpDate(now);
			updateRecord.setUpdateId(batchNum);

			carouselDao.updateByPrimaryKeySelective(updateRecord);
		}
	}
}
