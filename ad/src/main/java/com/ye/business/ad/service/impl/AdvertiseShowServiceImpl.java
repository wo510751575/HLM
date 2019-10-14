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
import org.springframework.util.CollectionUtils;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.ad.constant.ErrorCodeAdvertiseShow;
import com.ye.business.ad.dao.IAdvertiseShowDao;
import com.ye.business.ad.domain.AdvertiseShow;
import com.ye.business.ad.dto.AdvertiseDto;
import com.ye.business.ad.dto.AdvertiseShowDto;
import com.ye.business.ad.dto.FindAdvertiseShowPage;
import com.ye.business.ad.service.IAdvertiseShowService;

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
public class AdvertiseShowServiceImpl implements IAdvertiseShowService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AdvertiseShowServiceImpl.class);

	@Resource
	private IAdvertiseShowDao advertiseShowDao;

	@Override
	public String addAdvertiseShow(AdvertiseShowDto advertiseShowDto) throws TsfaServiceException {
		logger.debug("addAdvertiseShow(AddAdvertiseShow addAdvertiseShow={}) - start", advertiseShowDto);

		AssertUtils.notNull(advertiseShowDto);
		try {
			AdvertiseShow advertiseShow = new AdvertiseShow();
			// add数据录入
			advertiseShow.setCode(GUID.getPreUUID());
			advertiseShow.setMerchantNo(advertiseShowDto.getMerchantNo());
			advertiseShow.setAdvertiseCode(advertiseShowDto.getAdvertiseCode());
			advertiseShow.setCreateId(advertiseShowDto.getCreateId());
			advertiseShow.setCretaeName(advertiseShowDto.getCretaeName());
			advertiseShow.setCreateDate(advertiseShowDto.getCreateDate());
			advertiseShow.setRemark(advertiseShowDto.getRemark());
			advertiseShow.setRemark2(advertiseShowDto.getRemark2());
			advertiseShow.setRemark3(advertiseShowDto.getRemark3());
			advertiseShow.setRemark4(advertiseShowDto.getRemark4());
			advertiseShow.setUpdateTime(advertiseShowDto.getUpdateTime());
			advertiseShow.setArticleCode(advertiseShowDto.getArticleCode());
			advertiseShowDao.insertSelective(advertiseShow);
			logger.debug("addAdvertiseShow(AdvertiseShowDto) - end - return");

			return advertiseShow.getCode();

		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增广告显示记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseShow.ADVERTISE_SHOW_ADD_ERROR, "新增广告显示记录信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询广告显示记录信息
	 *
	 * @param findAdvertiseShowPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<AdvertiseShowDto> findAdvertiseShows(FindAdvertiseShowPage findAdvertiseShowPage) throws TsfaServiceException {
		AssertUtils.notNull(findAdvertiseShowPage);
		List<AdvertiseShowDto> returnList = null;
		try {
			returnList = advertiseShowDao.findAdvertiseShows(findAdvertiseShowPage);
		} catch (Exception e) {
			logger.error("查找广告显示记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseShow.ADVERTISE_SHOW_NOT_EXIST_ERROR, "广告显示记录信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateAdvertiseShow(AdvertiseShowDto advertiseShowDto) throws TsfaServiceException {
		logger.debug("updateAdvertiseShow(AdvertiseShowDto advertiseShowDto={}) - start", advertiseShowDto);

		AssertUtils.notNull(advertiseShowDto);
		AssertUtils.notNullAndEmpty(advertiseShowDto.getCode(), "Code不能为空");
		try {
			AdvertiseShow advertiseShow = new AdvertiseShow();
			// update数据录入
			advertiseShow.setCode(advertiseShowDto.getCode());
			advertiseShow.setMerchantNo(advertiseShowDto.getMerchantNo());
			advertiseShow.setAdvertiseCode(advertiseShowDto.getAdvertiseCode());
			advertiseShow.setCreateId(advertiseShowDto.getCreateId());
			advertiseShow.setCretaeName(advertiseShowDto.getCretaeName());
			advertiseShow.setCreateDate(advertiseShowDto.getCreateDate());
			advertiseShow.setRemark(advertiseShowDto.getRemark());
			advertiseShow.setRemark2(advertiseShowDto.getRemark2());
			advertiseShow.setRemark3(advertiseShowDto.getRemark3());
			advertiseShow.setRemark4(advertiseShowDto.getRemark4());
			advertiseShow.setUpdateTime(advertiseShowDto.getUpdateTime());
			advertiseShow.setArticleCode(advertiseShowDto.getArticleCode());
			AssertUtils.notUpdateMoreThanOne(advertiseShowDao.updateByPrimaryKeySelective(advertiseShow));
			logger.debug("updateAdvertiseShow(AdvertiseShowDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("广告显示记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseShow.ADVERTISE_SHOW_UPDATE_ERROR, "广告显示记录信息更新信息错误！", e);
		}
	}

	@Override
	public AdvertiseShowDto findAdvertiseShow(AdvertiseShowDto advertiseShowDto) throws TsfaServiceException {
		logger.debug("findAdvertiseShow(FindAdvertiseShow findAdvertiseShow={}) - start", advertiseShowDto);

		AssertUtils.notNull(advertiseShowDto);
		AssertUtils.notAllNull(advertiseShowDto.getCode(), "Code不能为空");
		try {
			AdvertiseShow advertiseShow = advertiseShowDao.selectByPrimaryKey(advertiseShowDto.getCode());
			if (advertiseShow == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.ADVERTISE_SHOW_NOT_EXIST_ERROR,"广告显示记录信息不存在");
			}
			AdvertiseShowDto findAdvertiseShowReturn = new AdvertiseShowDto();
			// find数据录入
			findAdvertiseShowReturn.setCode(advertiseShow.getCode());
			findAdvertiseShowReturn.setMerchantNo(advertiseShow.getMerchantNo());
			findAdvertiseShowReturn.setAdvertiseCode(advertiseShow.getAdvertiseCode());
			findAdvertiseShowReturn.setCreateId(advertiseShow.getCreateId());
			findAdvertiseShowReturn.setCretaeName(advertiseShow.getCretaeName());
			findAdvertiseShowReturn.setCreateDate(advertiseShow.getCreateDate());
			findAdvertiseShowReturn.setRemark(advertiseShow.getRemark());
			findAdvertiseShowReturn.setRemark2(advertiseShow.getRemark2());
			findAdvertiseShowReturn.setRemark3(advertiseShow.getRemark3());
			findAdvertiseShowReturn.setRemark4(advertiseShow.getRemark4());
			findAdvertiseShowReturn.setUpdateTime(advertiseShow.getUpdateTime());
			findAdvertiseShowReturn.setArticleCode(advertiseShow.getArticleCode());

			logger.debug("findAdvertiseShow(AdvertiseShowDto) - end - return value={}", findAdvertiseShowReturn);
			return findAdvertiseShowReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找广告显示记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseShow.ADVERTISE_SHOW_FIND_ERROR, "查找广告显示记录信息信息错误！", e);
		}

	}

	@Override
	public Page<AdvertiseShowDto> findAdvertiseShowPage(FindAdvertiseShowPage findAdvertiseShowPage) throws TsfaServiceException {
		logger.debug("findAdvertiseShowPage(FindAdvertiseShowPage findAdvertiseShowPage={}) - start", findAdvertiseShowPage);

		AssertUtils.notNull(findAdvertiseShowPage);
		List<AdvertiseShowDto> returnList = null;
		int count = 0;
		try {
			returnList = advertiseShowDao.findAdvertiseShowPage(findAdvertiseShowPage);
			count = advertiseShowDao.findAdvertiseShowPageCount(findAdvertiseShowPage);
		} catch (Exception e) {
			logger.error("广告显示记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseShow.ADVERTISE_SHOW_FIND_PAGE_ERROR, "广告显示记录信息不存在错误.！", e);
		}
		Page<AdvertiseShowDto> returnPage = new Page<AdvertiseShowDto>(returnList, count, findAdvertiseShowPage);

		logger.debug("findAdvertiseShowPage(FindAdvertiseShowPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public List<String> saveAdvertiseShowForTrack(List<String> advertiseCodeList, String updateId, Date date, String ip, String articleCode) {
		logger.debug("saveAdvertiseShowForTrack");

		AssertUtils.notNull(advertiseCodeList);
		AssertUtils.notNullAndEmpty(updateId);
		AssertUtils.notNull(date);
		
		// 查询当前记录是否存在，存在则跳过
		AdvertiseShowDto findRecord = new AdvertiseShowDto();
		findRecord.setAdvertiseCodeList(advertiseCodeList);
		findRecord.setCreateDate(date);
		findRecord.setCreateId(updateId);
		findRecord.setRemark(ip);
		findRecord.setArticleCode(articleCode);
		
		// 一天只记录一次
		List<String> adCodeList = advertiseShowDao.findCodeListByExample(findRecord);
		advertiseCodeList.removeAll(adCodeList);

		if (CollectionUtils.isEmpty(advertiseCodeList)) {
			return null;
		}
		
		advertiseCodeList.forEach(code -> {

			AdvertiseShowDto temp = new AdvertiseShowDto();
			temp.setAdvertiseCode(code);
			temp.setCreateDate(date);
			temp.setUpdateTime(date);
			temp.setCreateId(updateId);
			temp.setRemark(ip);
			temp.setArticleCode(articleCode);

			addAdvertiseShow(temp);
		});
		
		return advertiseCodeList;
	}

	@Override
	public Map<String, Integer> findAdvertiseShowPageCountForGroupAdvertiseCode(List<String> advertiseCodeList) {
		FindAdvertiseShowPage findAdvertiseShowPage = new FindAdvertiseShowPage();
		AdvertiseShowDto param = new AdvertiseShowDto();
		param.setAdvertiseCodeList(advertiseCodeList);
		findAdvertiseShowPage.setParam(param);

		List<Map<String, Object>> data = advertiseShowDao.findAdvertiseShowPageCountForGroupAdvertiseCode(findAdvertiseShowPage);

		Map<String, Integer> rs = data.stream().collect(Collectors.toMap(map -> map.get("advertiseCode").toString(), map -> Integer.parseInt(map.get("num").toString())));
		return rs;
	}

	@Override
	public List<AdvertiseShowDto> findGroupTotalByExample(AdvertiseDto record) throws TsfaServiceException {
		return advertiseShowDao.findGroupTotalByExample(record);
	}

	@Override
	public List<AdvertiseShowDto> findAdvertiseShowForGroupList(AdvertiseShowDto record) throws TsfaServiceException {
		FindAdvertiseShowPage findAdvertiseShowPage = new FindAdvertiseShowPage();
		findAdvertiseShowPage.setParam(record);
		return advertiseShowDao.findAdvertiseShowForGroupList(findAdvertiseShowPage);
	}
	@Override
	public List<AdvertiseShowDto> findAdvertiseShowInfoForGroupList(AdvertiseShowDto record) throws TsfaServiceException {
		FindAdvertiseShowPage findAdvertiseShowPage = new FindAdvertiseShowPage();
		findAdvertiseShowPage.setParam(record);
		return advertiseShowDao.findAdvertiseShowInfoForGroupList(findAdvertiseShowPage);
	}
}
