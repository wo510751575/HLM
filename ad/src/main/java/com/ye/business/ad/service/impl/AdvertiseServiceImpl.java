package com.ye.business.ad.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.domain.JobCenter;
import com.lj.cc.service.IJobMgrService;
import com.ye.business.ad.constant.ErrorCodeAdvertise;
import com.ye.business.ad.dao.IAdvertiseDao;
import com.ye.business.ad.domain.Advertise;
import com.ye.business.ad.dto.AdvertiseDto;
import com.ye.business.ad.dto.AdvertiseTaskDto;
import com.ye.business.ad.dto.AdvertiseViewDto;
import com.ye.business.ad.dto.BillDto;
import com.ye.business.ad.dto.FindAdvertisePage;
import com.ye.business.ad.dto.RwUserBeansDto;
import com.ye.business.ad.enums.AdvStatus;
import com.ye.business.ad.enums.RwState;
import com.ye.business.ad.kit.OrderNoUtil;
import com.ye.business.ad.service.IAdvertiseService;
import com.ye.business.ad.service.IAdvertiseShowService;
import com.ye.business.ad.service.IAdvertiseViewService;
import com.ye.business.ad.service.IBillService;
import com.ye.business.ad.service.IRwUserBeansService;

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
public class AdvertiseServiceImpl implements IAdvertiseService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AdvertiseServiceImpl.class);

	@Resource
	private IAdvertiseDao advertiseDao;

	@Autowired
	private IAdvertiseShowService advertiseShowService;

	@Autowired
	private IAdvertiseViewService advertiseViewService;

	@Autowired
	private IBillService billService;

	@Autowired
	private IRwUserBeansService rwUserBeansService;

	@Resource
	private IJobMgrService jobMgrService;

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Override
	public String addAdvertise(AdvertiseDto advertiseDto) throws TsfaServiceException {
		logger.debug("addAdvertise(AddAdvertise addAdvertise={}) - start", advertiseDto);

		AssertUtils.notNull(advertiseDto);
		try {
			Advertise advertise = new Advertise();
			// add数据录入
			advertise.setCode(GUID.getPreUUID());
			advertise.setMemberNoGuid(advertiseDto.getMemberNoGuid());
			advertise.setMemberNameGuid(advertiseDto.getMemberNameGuid());
			advertise.setShopNo(advertiseDto.getShopNo());
			advertise.setShopName(advertiseDto.getShopName());
			advertise.setMerchantNo(advertiseDto.getMerchantNo());
			advertise.setMerchantName(advertiseDto.getMerchantName());
			advertise.setMemberNo(advertiseDto.getMemberNo());
			advertise.setState(advertiseDto.getState());
			advertise.setType(advertiseDto.getType());
			advertise.setLink(advertiseDto.getLink());
			advertise.setAdvLink(advertiseDto.getAdvLink());
			advertise.setAdvTypeCode(advertiseDto.getAdvTypeCode());
			advertise.setAdvType(advertiseDto.getAdvType());
			advertise.setSource(advertiseDto.getSource());
			advertise.setNumOrder(advertiseDto.getNumOrder());
			advertise.setRemark(advertiseDto.getRemark());
			advertise.setCreateId(advertiseDto.getCreateId());
			advertise.setCreateDate(advertiseDto.getCreateDate());
			advertise.setUpdateId(advertiseDto.getUpdateId());
			advertise.setUpdateDate(advertiseDto.getUpdateDate());
			advertise.setPriceSum(advertiseDto.getPriceSum());
			advertise.setPriceClick(advertiseDto.getPriceClick());
			advertise.setPriceView(advertiseDto.getPriceView());
			advertise.setAdvState(advertiseDto.getAdvState());
			advertise.setReleaseDate(advertiseDto.getReleaseDate());
			advertise.setUpDate(advertiseDto.getUpDate());
			advertise.setDownDate(advertiseDto.getDownDate());
			advertise.setAdvStatus(advertiseDto.getAdvStatus());

			// 未下架，验证当前用户金额，与扣除
			if (!AdvStatus.down.toString().equals(advertiseDto.getAdvStatus()) && RwState.pay.toString().equals(advertiseDto.getAdvState())) {
				advertiseDto.setCode(advertise.getCode());
				changeAdvertiseForBeansAdd(advertiseDto);
			}

			// 任务调度上架，下架
			saveParamJob(advertise.getCode(), advertise.getUpDate(), AdvertiseTaskDto.STATUS_UP);
			saveParamJob(advertise.getCode(), advertise.getDownDate(), AdvertiseTaskDto.STATUS_DOWN);

			advertiseDao.insertSelective(advertise);
			logger.debug("addAdvertise(AdvertiseDto) - end - return");

			return advertise.getCode();

		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增广告信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertise.ADVERTISE_ADD_ERROR, "新增广告信息错误！", e);
		}
	}

	/**
	 * 
	 * *方法说明：用户积分扣费，转移到广告中
	 *
	 * @param advertiseDto
	 * @author sjiying
	 * @CreateDate 2019年7月18日
	 */
	private void changeAdvertiseForBeansAdd(AdvertiseDto advertiseDto) {
		// 获取用户金额信息
		RwUserBeansDto beans = rwUserBeansService.findRwUserBeans(advertiseDto.getMemberNoGuid());
		AssertUtils.notNull(beans, "积分不足");
		AssertUtils.notNull(beans.getBeansNormal(), "积分不足");

		boolean priceBool = advertiseDto.getPriceSum().intValue() <= beans.getBeansNormal().intValue();
		AssertUtils.isTrue(priceBool, "用户积分不足：总积分为：" + beans.getBeansNormal().intValue());

		int price = advertiseDto.getPriceSum().intValue();
		if (price == 0) {
			return; // 不需要插入账单
		}
		
		// 扣除用户积分
		RwUserBeansDto updataBeans = new RwUserBeansDto();
		updataBeans.setCode(beans.getCode());
		updataBeans.setChangeBeans(-price);
		updataBeans.setUpdateId(advertiseDto.getUpdateId());
		updataBeans.setUpdateDate(advertiseDto.getUpdateDate());
		
		updataBeans.setChangeFreeze(price);

		rwUserBeansService.updateIncreaseAmountByPrimaryKey(updataBeans);

		
		
		// 增加流水支出
		BillDto expenditure = new BillDto();
		expenditure.setAdvertiseCode(advertiseDto.getCode());
//				expenditure.setArticleCode(param.getArticleCode());

		expenditure.setTradeType("5");
		expenditure.setTradeNo(OrderNoUtil.getOrderNo());

		expenditure.setMemberNo(advertiseDto.getMemberNoGuid());
		expenditure.setMemberName(advertiseDto.getMemberNameGuid());
		expenditure.setMerchantNo(advertiseDto.getMerchantNo());
		expenditure.setMerchantName(advertiseDto.getMerchantName());
		expenditure.setLoginName(beans.getRemark2());

		expenditure.setAmount(-price);
//				expenditure.setRemark(param.getRemark());
		expenditure.setCreateTime(advertiseDto.getUpdateDate());
		expenditure.setCreateId(advertiseDto.getUpdateId());
		billService.addBill(expenditure);
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询广告信息
	 *
	 * @param findAdvertisePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<AdvertiseDto> findAdvertises(FindAdvertisePage findAdvertisePage) throws TsfaServiceException {
		AssertUtils.notNull(findAdvertisePage);
		List<AdvertiseDto> returnList = null;
		try {
			returnList = advertiseDao.findAdvertises(findAdvertisePage);

//			// 插入广告显示信息
//			if (findAdvertisePage.getParam() != null && findAdvertisePage.getParam().getHasTrack() != null && findAdvertisePage.getParam().getHasTrack()) {
//
//				String updateId = findAdvertisePage.getParam().getUpdateId();
//				String ip = findAdvertisePage.getParam().getRemark();
//				String articleCode = findAdvertisePage.getParam().getArticleCode();
//				List<String> advertiseCodeList = returnList.stream().map(AdvertiseDto::getCode).collect(Collectors.toList());
//
//				if (StringUtils.isNotEmpty(updateId)) {
//					advertiseShowService.saveAdvertiseShowForTrack(advertiseCodeList, updateId, new Date(), ip, articleCode);
//				}
//			}

		} catch (Exception e) {
			logger.error("查找广告信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertise.ADVERTISE_NOT_EXIST_ERROR, "广告信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateAdvertise(AdvertiseDto advertiseDto) throws TsfaServiceException {
		logger.debug("updateAdvertise(AdvertiseDto advertiseDto={}) - start", advertiseDto);

		AssertUtils.notNull(advertiseDto);
		AssertUtils.notNullAndEmpty(advertiseDto.getCode(), "Code不能为空");
		try {

			Advertise rsAdRecord = advertiseDao.selectByPrimaryKey(advertiseDto.getCode());

			Advertise advertise = new Advertise();
			// update数据录入
			advertise.setCode(advertiseDto.getCode());
			advertise.setMemberNoGuid(advertiseDto.getMemberNoGuid());
			advertise.setMemberNameGuid(advertiseDto.getMemberNameGuid());
			advertise.setShopNo(advertiseDto.getShopNo());
			advertise.setShopName(advertiseDto.getShopName());
			advertise.setMerchantNo(advertiseDto.getMerchantNo());
			advertise.setMerchantName(advertiseDto.getMerchantName());
			advertise.setMemberNo(advertiseDto.getMemberNo());
			advertise.setState(advertiseDto.getState());
			advertise.setType(advertiseDto.getType());
			advertise.setLink(advertiseDto.getLink());
			advertise.setAdvLink(advertiseDto.getAdvLink());
			advertise.setAdvTypeCode(advertiseDto.getAdvTypeCode());
			advertise.setAdvType(advertiseDto.getAdvType());
			advertise.setSource(advertiseDto.getSource());
			advertise.setNumOrder(advertiseDto.getNumOrder());
			advertise.setRemark(advertiseDto.getRemark());
			advertise.setCreateId(advertiseDto.getCreateId());
			advertise.setCreateDate(advertiseDto.getCreateDate());
			advertise.setUpdateId(advertiseDto.getUpdateId());
			advertise.setUpdateDate(advertiseDto.getUpdateDate());
//			advertise.setPriceSum(advertiseDto.getPriceSum());
			advertise.setPriceClick(advertiseDto.getPriceClick());
			advertise.setPriceView(advertiseDto.getPriceView());
			advertise.setAdvState(advertiseDto.getAdvState());
			advertise.setReleaseDate(advertiseDto.getReleaseDate());
			advertise.setUpDate(advertiseDto.getUpDate());
			advertise.setDownDate(advertiseDto.getDownDate());
			advertise.setAdvStatus(advertiseDto.getAdvStatus());

			// 已下架，返回用户金额
			if (AdvStatus.down.toString().equals(advertiseDto.getAdvStatus()) && RwState.pay.toString().equals(advertiseDto.getAdvState())) {

				changeAdDownMoney(advertiseDto.getUpdateDate(), advertiseDto.getUpdateId(), advertiseDto.getCode());
				// 已下架返回用户金额
				advertise.setPriceSum(0);
			}

			// 未下架，验证当前用户金额，与扣除
			// 更改前必须是下架状态；当前广告为未上架或已上架；必须为付费广告；总费用金额必须大于0
			if (AdvStatus.down.toString().equals(rsAdRecord.getAdvStatus()) && !AdvStatus.down.toString().equals(advertiseDto.getAdvStatus())
					&& RwState.pay.toString().equals(advertiseDto.getAdvState()) && advertiseDto.getPriceSum() != null) {
				changeAdvertiseForBeansAdd(advertiseDto);
				advertise.setPriceSum(advertiseDto.getPriceSum());
			}
			
			// 任务调度上架，下架
			saveParamJob(advertise.getCode(), advertise.getUpDate(), AdvertiseTaskDto.STATUS_UP);
			saveParamJob(advertise.getCode(), advertise.getDownDate(), AdvertiseTaskDto.STATUS_DOWN);

			AssertUtils.notUpdateMoreThanOne(advertiseDao.updateByPrimaryKeySelective(advertise));
			logger.debug("updateAdvertise(AdvertiseDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("广告信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertise.ADVERTISE_UPDATE_ERROR, "广告信息更新信息错误！", e);
		}
	}

	@Override
	public AdvertiseDto findAdvertise(AdvertiseDto advertiseDto) throws TsfaServiceException {
		logger.debug("findAdvertise(FindAdvertise findAdvertise={}) - start", advertiseDto);

		AssertUtils.notNull(advertiseDto);
		AssertUtils.notAllNull(advertiseDto.getCode(), "Code不能为空");
		try {
			Advertise advertise = advertiseDao.selectByPrimaryKey(advertiseDto.getCode());
			if (advertise == null) {
				return null;
				// throw new TsfaServiceException(ErrorCodeAdvertise.ADVERTISE_NOT_EXIST_ERROR,"广告信息不存在");
			}
			AdvertiseDto findAdvertiseReturn = new AdvertiseDto();
			// find数据录入
			findAdvertiseReturn.setCode(advertise.getCode());
			findAdvertiseReturn.setMemberNoGuid(advertise.getMemberNoGuid());
			findAdvertiseReturn.setMemberNameGuid(advertise.getMemberNameGuid());
			findAdvertiseReturn.setShopNo(advertise.getShopNo());
			findAdvertiseReturn.setShopName(advertise.getShopName());
			findAdvertiseReturn.setMerchantNo(advertise.getMerchantNo());
			findAdvertiseReturn.setMerchantName(advertise.getMerchantName());
			findAdvertiseReturn.setMemberNo(advertise.getMemberNo());
			findAdvertiseReturn.setState(advertise.getState());
			findAdvertiseReturn.setType(advertise.getType());
			findAdvertiseReturn.setLink(advertise.getLink());
			findAdvertiseReturn.setAdvLink(advertise.getAdvLink());
			findAdvertiseReturn.setAdvTypeCode(advertise.getAdvTypeCode());
			findAdvertiseReturn.setAdvType(advertise.getAdvType());
			findAdvertiseReturn.setSource(advertise.getSource());
			findAdvertiseReturn.setNumOrder(advertise.getNumOrder());
			findAdvertiseReturn.setRemark(advertise.getRemark());
			findAdvertiseReturn.setCreateId(advertise.getCreateId());
			findAdvertiseReturn.setCreateDate(advertise.getCreateDate());
			findAdvertiseReturn.setUpdateId(advertise.getUpdateId());
			findAdvertiseReturn.setUpdateDate(advertise.getUpdateDate());
			findAdvertiseReturn.setPriceSum(advertise.getPriceSum());
			findAdvertiseReturn.setPriceClick(advertise.getPriceClick());
			findAdvertiseReturn.setPriceView(advertise.getPriceView());
			findAdvertiseReturn.setAdvState(advertise.getAdvState());
			findAdvertiseReturn.setReleaseDate(advertise.getReleaseDate());
			findAdvertiseReturn.setUpDate(advertise.getUpDate());
			findAdvertiseReturn.setDownDate(advertise.getDownDate());
			findAdvertiseReturn.setAdvStatus(advertise.getAdvStatus());

//			if (advertiseDto.getHasTrack() != null && advertiseDto.getHasTrack()) {
//				// 记录查看广告信息
//				AdvertiseViewDto advertiseViewDto = new AdvertiseViewDto();
//				Date now = new Date();
//
//				advertiseViewDto.setCreateDate(now);
//				advertiseViewDto.setAdvertiseCode(advertise.getCode());
//				advertiseViewDto.setUpdateTime(now);
//				advertiseViewDto.setCreateId(advertiseDto.getUpdateId());
//				advertiseViewDto.setRemark(advertiseDto.getRemark());
//				advertiseViewDto.setArticleCode(advertiseDto.getArticleCode());
//				if (StringUtils.isNotEmpty(advertiseDto.getMemberNoGuid())) {
//					advertiseViewService.addAdvertiseView(advertiseViewDto);
//				}
//			}

			if (advertiseDto.isHasTrackShow()) {
				List<String> advertiseCodeList = Arrays.asList(advertise.getCode());
				// 统计展示次数
				Map<String, Integer> numShowMap = this.advertiseShowService.findAdvertiseShowPageCountForGroupAdvertiseCode(advertiseCodeList);
				// 统计查看点击次数
				Map<String, Integer> numViewMap = this.advertiseViewService.findAdvertiseViewPageCountForGroupAdvertiseCode(advertiseCodeList);

				if (numShowMap.containsKey(advertise.getCode())) {
					findAdvertiseReturn.setNumShow(numShowMap.getOrDefault(advertise.getCode(), 0));
				}
				if (numViewMap.containsKey(advertise.getCode())) {
					findAdvertiseReturn.setNumView(numViewMap.getOrDefault(advertise.getCode(), 0));
				}
			}

			logger.debug("findAdvertise(AdvertiseDto) - end - return value={}", findAdvertiseReturn);
			return findAdvertiseReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找广告信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertise.ADVERTISE_FIND_ERROR, "查找广告信息信息错误！", e);
		}

	}

	@Override
	public Page<AdvertiseDto> findAdvertisePage(FindAdvertisePage findAdvertisePage) throws TsfaServiceException {
		logger.debug("findAdvertisePage(FindAdvertisePage findAdvertisePage={}) - start", findAdvertisePage);

		AssertUtils.notNull(findAdvertisePage);
		List<AdvertiseDto> returnList = null;
		int count = 0;
		try {
			returnList = advertiseDao.findAdvertisePage(findAdvertisePage);
			count = advertiseDao.findAdvertisePageCount(findAdvertisePage);
			
			if (findAdvertisePage.getParam() != null && !CollectionUtils.isEmpty(returnList) && findAdvertisePage.getParam().getHasTrack() != null
					&& findAdvertisePage.getParam().getHasTrack()) {

				List<String> advertiseCodeList = returnList.stream().map(AdvertiseDto::getCode).collect(Collectors.toList());
				// 统计展示次数
				Map<String, Integer> numShowMap = this.advertiseShowService.findAdvertiseShowPageCountForGroupAdvertiseCode(advertiseCodeList);
				// 统计查看点击次数
				Map<String, Integer> numViewMap = this.advertiseViewService.findAdvertiseViewPageCountForGroupAdvertiseCode(advertiseCodeList);

				returnList.forEach(action -> {
					if (numShowMap.containsKey(action.getCode())) {
						action.setNumShow(numShowMap.getOrDefault(action.getCode(), 0));
					}
					if (numViewMap.containsKey(action.getCode())) {
						action.setNumView(numViewMap.getOrDefault(action.getCode(), 0));
					}
					action.setCreateDateStr(DateUtils.formatDate(action.getCreateDate(), "yyyy-MM-dd"));
					if (action.getPriceRemain() == null) {
						action.setPriceRemain(0);
					}
				});

			}

		} catch (Exception e) {
			logger.error("广告信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeAdvertise.ADVERTISE_FIND_PAGE_ERROR, "广告信息不存在错误.！", e);
		}
		Page<AdvertiseDto> returnPage = new Page<AdvertiseDto>(returnList, count, findAdvertisePage);

		logger.debug("findAdvertisePage(FindAdvertisePage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(advertiseDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("广告信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertise.ADVERTISE_DELETE_ERROR, "广告信息刪除信息错误！", e);
		}
	}

	@Override
	public List<AdvertiseDto> findOthersRandom(FindAdvertisePage findAdvertisePage) throws TsfaServiceException {
		logger.debug("findOthersRandom(FindAdvertisePage findAdvertisePage={}) - start", findAdvertisePage);
		List<AdvertiseDto> list = new ArrayList<>();
		try {
			list = advertiseDao.findOthersRandom(findAdvertisePage);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("广告信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertise.ADVERTISE_FIND_ERROR, "查找广告信息错误.", e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public List<AdvertiseDto> findRandomRecordList(FindAdvertisePage findAdvertisePage) throws TsfaServiceException {
		logger.debug("findRandomRecordList(FindAdvertisePage findAdvertisePage={}) - start", findAdvertisePage);
		List<AdvertiseDto> rs = Lists.newArrayList();
		try {

			AdvertiseDto findOther = new AdvertiseDto();

			String freeCode = "";

			// 1.如果该商户有自己自助广告随机取一个,否则取其他商户的互助广告
			AdvertiseDto param = findAdvertisePage.getParam();
			if (StringUtils.isNotEmpty(param.getMerchantNo())) {

				FindAdvertisePage findCurrPage = new FindAdvertisePage();
				AdvertiseDto findCurr = new AdvertiseDto();

				findCurr.setMerchantNo(param.getMerchantNo());
				findCurr.setState(RwState.normal.toString());
				findCurr.setAdvState(RwState.free.toString()); // 自助广告
				findCurr.setAdvStatus(AdvStatus.up.toString()); // 已上架广告

				findCurrPage.setParam(findCurr);

				findCurrPage.setSortBy("rand");

				List<AdvertiseDto> data = advertiseDao.findAdvertises(findCurrPage);
				if (!CollectionUtils.isEmpty(data)) {
					rs.add(data.get(0));
					findAdvertisePage.setLimit(findAdvertisePage.getLimit() - 1);// 每页条数减一
					// 自助广告code
					freeCode = data.get(0).getCode();
				}

				// 去除所属自助广告
				findOther.setNotMerchantNo(param.getMerchantNo());
			}

			FindAdvertisePage findOtherPage = new FindAdvertisePage();

			findOther.setState(RwState.normal.toString());
			findOther.setAdvState(RwState.pay.toString()); // 付费广告
			findOther.setAdvStatus(AdvStatus.up.toString()); // 已上架广告
			findOther.setNormalPrice("yes"); // 剩余金额是否足够扣除显示金额

			findOtherPage.setParam(findOther);
			findOtherPage.setSortBy("rand");
			findOtherPage.setLimit(findAdvertisePage.getLimit());
			findOtherPage.setStart(findAdvertisePage.getStart());

			// 2.随机获取其余商户付费广告
			List<AdvertiseDto> data = advertiseDao.findAdvertises(findOtherPage);
			if (!CollectionUtils.isEmpty(data)) {
				rs.addAll(data);
			}

			List<String> moneyCodeList = Lists.newArrayList();

			Date now = new Date();

			// 插入广告显示跟踪记录，并返回新增记录code
			List<String> advertiseCodeList = rs.stream().map(AdvertiseDto::getCode).collect(Collectors.toList());
			if (!CollectionUtils.isEmpty(advertiseCodeList) && StringUtils.isNotEmpty(param.getUpdateId())) {
				moneyCodeList = advertiseShowService.saveAdvertiseShowForTrack(advertiseCodeList, param.getCurrMemberNo(), now, param.getRemark(), param.getArticleCode());
				if (!CollectionUtils.isEmpty(moneyCodeList)) {
					// 去除自助广告code
					moneyCodeList.remove(freeCode);
				}
			}

			// 扣除广告豆，用户新增广告豆，新增流水记录
			changeMoneyRecordList(param, data, moneyCodeList, now);

		} catch (Exception e) {
			logger.error("广告信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertise.ADVERTISE_FIND_ERROR, "查找广告信息错误.", e);
		}
		return rs;
	}

	/**
	 * 
	 * *方法说明：扣除广告豆，用户新增广告豆，新增流水记录 List
	 *
	 * @param param
	 * @param data
	 * @param moneyCodeList
	 * @param now
	 * @author sjiying
	 * @CreateDate 2019年7月12日
	 */
	private void changeMoneyRecordList(AdvertiseDto param, List<AdvertiseDto> data, List<String> moneyCodeList, Date now) {
		// 扣除显示金额
		if (!CollectionUtils.isEmpty(moneyCodeList)) {
			// 扣除当前广告豆，并增加到相应用户账户中去

			List<AdvertiseDto> advRecordList = Lists.newArrayList();
			for (AdvertiseDto action : data) {
				for (String code : moneyCodeList) {
					if (action.getCode().equals(code)) {
						advRecordList.add(action);
					}
				}
			}

			if (!CollectionUtils.isEmpty(advRecordList)) {
				advRecordList.forEach(action -> changeMoneyRecord(param, now, action, "view"));
			}
		}
	}

	/**
	 * 
	 * *方法说明：扣除广告豆，用户新增广告豆，新增流水记录
	 *
	 * @param param
	 * @param now
	 * @param action
	 * @param viewOrClick view - 查看； click - 点击
	 * @author sjiying
	 * @CreateDate 2019年7月12日
	 */
	private void changeMoneyRecord(AdvertiseDto param, Date now, AdvertiseDto action, String viewOrClick) {

		Integer price = action.getPriceView();
		if ("click".equals(viewOrClick)) {
			price = action.getPriceClick();
		}
		
		if (!"pay".equals(action.getAdvState())) {
			return;
		}
		
		if (price == 0) {
			return;
		}

		// 增加流水收入
		BillDto income = new BillDto();
		income.setAdvertiseCode(action.getCode());
		income.setArticleCode(param.getArticleCode());

		income.setTradeType("4");
		income.setTradeNo(OrderNoUtil.getOrderNo());

		income.setMemberNo(param.getCurrMemberNo());
		income.setMemberName(param.getCurrMemberName());
		income.setMerchantNo(param.getCurrMerchantNo());
		income.setMerchantName(param.getCurrMerchantName());
		income.setLoginName(param.getCurrLoginName());

		income.setAmount(price);
		income.setRemark(param.getRemark());
		income.setCreateTime(now);
		income.setCreateId(param.getUpdateId());
		billService.addBill(income);

		// 增加用户豆子
		RwUserBeansDto beans = new RwUserBeansDto();
		beans.setMemberNo(param.getCurrMemberNo());
		beans.setCode(param.getCurrMemberNo());
		beans.setChangeBeans(price);
		beans.setUpdateId(param.getCurrMemberNo());
		beans.setUpdateName(param.getCurrMemberName());
		beans.setUpdateDate(now);
		rwUserBeansService.updateIncreaseAmountByPrimaryKey(beans);

		// 扣除广告豆子
		AdvertiseDto advUpdate = new AdvertiseDto();
		advUpdate.setCode(action.getCode());
		advUpdate.setChangeBeans(price);
		advUpdate.setUpdateId(param.getCurrMemberNo());
		advUpdate.setUpdateDate(now);
		advertiseDao.updateDeductionAmountByPrimaryKey(advUpdate);


		
		RwUserBeansDto beansRecord = rwUserBeansService.findRwUserBeans(param.getCurrMemberNo());
		
		// 增加流水支出
		BillDto expenditure = new BillDto();
		expenditure.setAdvertiseCode(action.getCode());
		expenditure.setArticleCode(param.getArticleCode());

		expenditure.setTradeType("3");
		expenditure.setTradeNo(OrderNoUtil.getOrderNo());

		expenditure.setMemberNo(action.getMemberNoGuid());
		expenditure.setMemberName(action.getMemberNameGuid());
		expenditure.setMerchantNo(action.getMerchantNo());
		expenditure.setMerchantName(action.getMerchantName());
		expenditure.setLoginName(beansRecord.getRemark2());

		expenditure.setAmount(-price);
		expenditure.setRemark(param.getRemark());
		expenditure.setCreateTime(now);
		expenditure.setCreateId(param.getUpdateId());
		billService.addBill(expenditure);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void batchUpdateAdvertiseForUpOrDown(String batchNum) throws TsfaServiceException {
		logger.info("批次{},1.开始记录正常广告信息....", batchNum);
		FindAdvertisePage findParam = new FindAdvertisePage();
		AdvertiseDto param = new AdvertiseDto();

//		param.setState(RwState.normal.toString());
//		param.setAdvStatus(AdvStatus.up.toString()); // 已上架广告
		findParam.setParam(param);

		List<AdvertiseDto> recordList = advertiseDao.findAdvertises(findParam);
		if (CollectionUtils.isEmpty(recordList)) {
			logger.info("批次{},2.无需要上下架广告记录....", batchNum);
			return;
		}

		LocalDateTime sysNow = LocalDateTime.now();

		// 筛选需要下架的广告记录;此处使用并行处理
		List<String> codeList = recordList.parallelStream().filter(pre -> {

			// 下架时间小于当前时间
			if (pre.getDownDate() != null && pre.getDownDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().isBefore(sysNow)) {
				return true;
			}

			// 付费广告豆不足了
			if (pre.getPriceSum().intValue() <= 0 && RwState.pay.toString().equals(pre.getAdvState())) {
				return true;
			}

			return false;
		}).map(AdvertiseDto::getCode).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(codeList)) {
			logger.info("批次{},2.无需要下架广告记录....", batchNum);
			return;
		}
		logger.info("批次{},2.筛选出下架广告信息.... {}", batchNum, codeList);

		Date now = new Date();

		// 广告下架，广告金额返回用户
		codeList.forEach(adCode -> {
			changeAdDownMoney(now, batchNum, adCode);
		});

		AdvertiseDto updateRecord = new AdvertiseDto();
		updateRecord.setAdvStatus(AdvStatus.down.toString());
		updateRecord.setCodeList(codeList);
		updateRecord.setUpDate(now);
		updateRecord.setUpdateId(batchNum);
		updateRecord.setPriceSum(0);

		int count = advertiseDao.updateByPrimaryKeyBatchSelective(updateRecord);

		logger.info("批次{},3.下架广告信息成功{},总计：{}条....", batchNum, codeList, count);

		// 筛选需要下架的广告记录;此处使用并行处理
		List<String> codeUpList = recordList.parallelStream().filter(pre -> {

			// 上架时间小于当前时间
			if (pre.getUpDate() != null && pre.getUpDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().isBefore(sysNow)) {
				return true;
			}

			return false;
		}).map(AdvertiseDto::getCode).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(codeUpList)) {
			logger.info("批次{},4.无需要上架广告记录....", batchNum);
			return;
		}
		AdvertiseDto updateUpRecord = new AdvertiseDto();
		updateUpRecord.setAdvStatus(AdvStatus.up.toString());
		updateUpRecord.setCodeList(codeUpList);
		updateUpRecord.setUpDate(now);
		updateUpRecord.setUpdateId(batchNum);
		int countUp = advertiseDao.updateByPrimaryKeyBatchSelective(updateUpRecord);

		logger.info("批次{},5.上架广告信息成功{},总计：{}条....", batchNum, codeUpList, countUp);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public AdvertiseDto findAdvertiseForView(AdvertiseDto advertiseDto) throws TsfaServiceException {
		logger.debug("findAdvertiseForView(FindAdvertise findAdvertise={}) - start", advertiseDto);

		AssertUtils.notNull(advertiseDto);
		AssertUtils.notAllNull(advertiseDto.getCode(), "Code不能为空");
		try {

			AdvertiseDto findAdvertiseReturn = findAdvertise(advertiseDto);

			// 记录查看广告信息
			AdvertiseViewDto advertiseViewDto = new AdvertiseViewDto();
			Date now = new Date();

			advertiseViewDto.setCreateDate(now);
			advertiseViewDto.setAdvertiseCode(findAdvertiseReturn.getCode());
			advertiseViewDto.setUpdateTime(now);
			advertiseViewDto.setCreateId(advertiseDto.getCurrMemberNo());
			advertiseViewDto.setRemark(advertiseDto.getRemark());
			advertiseViewDto.setArticleCode(advertiseDto.getArticleCode());
			if (StringUtils.isNotEmpty(advertiseDto.getMemberNoGuid())) {
				advertiseViewService.addAdvertiseView(advertiseViewDto);
			}

			// 扣除广告豆，用户新增广告豆，新增流水记录
			changeMoneyRecord(advertiseDto, now, findAdvertiseReturn, "click");

			logger.debug("findAdvertise(AdvertiseDto) - end - return value={}", findAdvertiseReturn);
			return findAdvertiseReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找广告信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertise.ADVERTISE_FIND_ERROR, "查找广告信息信息错误！", e);
		}
	}

	/**
	 * 
	 * *方法说明：广告下架金额返回用户
	 *
	 * @param now 当前时间
	 * @param updateId 当前操作人
	 * @param adCode 广告code
	 * @author sjiying
	 * @CreateDate 2019年7月15日
	 */
	private void changeAdDownMoney(Date now, String updateId, String adCode) {
		Advertise adRecord = advertiseDao.selectByPrimaryKey(adCode);
		AssertUtils.notNull(adRecord, "广告记录不存在");

		// 付费广告
		if (!RwState.pay.toString().equals(adRecord.getAdvState())) {
			return;
		}

		// 剩余金额大于0
		int price = adRecord.getPriceSum().intValue();
		if (price <= 0) {
			return;
		}

		RwUserBeansDto beansRecord = rwUserBeansService.findRwUserBeans(adRecord.getMemberNoGuid());
		// 返回用户积分
		RwUserBeansDto updataBeans = new RwUserBeansDto();
		updataBeans.setCode(adRecord.getMemberNoGuid());
		updataBeans.setChangeBeans(price);
		updataBeans.setUpdateId(updateId);
		updataBeans.setUpdateDate(now);
		
		updataBeans.setChangeFreeze(-price);

		rwUserBeansService.updateIncreaseAmountByPrimaryKey(updataBeans);

		// 增加流水收入
		BillDto income = new BillDto();
		income.setAdvertiseCode(adRecord.getCode());
//				income.setArticleCode(param.getArticleCode());

		income.setTradeType("6");
		income.setTradeNo(OrderNoUtil.getOrderNo());

		income.setMemberNo(adRecord.getMemberNoGuid());
		income.setMemberName(adRecord.getMemberNameGuid());
		income.setMerchantNo(adRecord.getMerchantNo());
		income.setMerchantName(adRecord.getMerchantName());
		income.setLoginName(beansRecord.getRemark2());

		income.setAmount(price);
//				income.setRemark(param.getRemark());
		income.setCreateTime(now);
		income.setCreateId(updateId);
		billService.addBill(income);
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

		String jobEnglishName = String.format("AddAdvertiseJob:%s:%s:%s:%d", code, status, AdvertiseTaskDto.TYPE_ADVERTISE, executeTime.getTime());
		jc.setJobEnglishName(jobEnglishName);

		AdvertiseTaskDto task = new AdvertiseTaskDto();
		task.setCode(code);
		task.setExecuteTime(executeTime);
		task.setStatus(status);
		task.setType(AdvertiseTaskDto.TYPE_ADVERTISE);
		jc.setJobParam(JsonUtils.jsonFromObject(task));

		try {
			jobMgrService.addTempJob(jc);
		} catch (Exception e) {
			logger.debug("重复添加临时任务", e);
		}
		
		logger.debug("AddAdvertiseJob: " + jc);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void updateForTask(AdvertiseTaskDto record) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(record);
		AssertUtils.notNullAndEmpty(record.getCode());
		AssertUtils.notNullAndEmpty(record.getStatus());
		AssertUtils.notNullAndEmpty(record.getExecuteTime());

		Advertise adRecord = advertiseDao.selectByPrimaryKey(record.getCode());
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
			Advertise updateRecord = new Advertise();
			updateRecord.setAdvStatus(AdvStatus.up.toString());
			updateRecord.setCode(record.getCode());
			updateRecord.setUpDate(now);
			updateRecord.setUpdateId(batchNum);

			advertiseDao.updateByPrimaryKeySelective(updateRecord);

			// 此上架不扣除用户积分，增加流水，在编辑保存时，已扣除用户积分
		}

		// 下架
		if (AdvertiseTaskDto.STATUS_DOWN.equals(record.getStatus())) {
			if (!record.getExecuteTime().equals(adRecord.getDownDate())) {
				return; // 下架时间不正确
			}

			Advertise updateRecord = new Advertise();
			updateRecord.setAdvStatus(AdvStatus.down.toString());
			updateRecord.setCode(record.getCode());
			updateRecord.setUpDate(now);
			updateRecord.setUpdateId(batchNum);

			if (RwState.pay.toString().equals(adRecord.getAdvState())) {
				// 下架返回用户金额
				updateRecord.setPriceSum(0);
				changeAdDownMoney(now, batchNum, record.getCode());
			}

			advertiseDao.updateByPrimaryKeySelective(updateRecord);
		}
	}
}
