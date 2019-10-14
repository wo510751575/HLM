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
import com.ye.business.ad.constant.ErrorCodeAdvertiseView;
import com.ye.business.ad.dao.IAdvertiseViewDao;
import com.ye.business.ad.domain.AdvertiseView;
import com.ye.business.ad.dto.AdvertiseDto;
import com.ye.business.ad.dto.AdvertiseViewDto;
import com.ye.business.ad.dto.FindAdvertiseViewPage;
import com.ye.business.ad.service.IAdvertiseViewService;

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
public class AdvertiseViewServiceImpl implements IAdvertiseViewService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AdvertiseViewServiceImpl.class);

	@Resource
	private IAdvertiseViewDao advertiseViewDao;

	@Override
	public String addAdvertiseView(AdvertiseViewDto advertiseViewDto) throws TsfaServiceException {
		logger.debug("addAdvertiseView(AddAdvertiseView addAdvertiseView={}) - start", advertiseViewDto);

		AssertUtils.notNull(advertiseViewDto);
		try {
			
			// 插入时，验证当天如果存在则不插入数据
			
			FindAdvertiseViewPage findAdvertiseViewPage = new FindAdvertiseViewPage();
			findAdvertiseViewPage.setParam(advertiseViewDto);
			
			int count = advertiseViewDao.findAdvertiseViewPageCount(findAdvertiseViewPage);
			if (count > 0) {
				return "";
			}
			
			
			AdvertiseView advertiseView = new AdvertiseView();
			// add数据录入
			advertiseView.setCode(GUID.getPreUUID());
			advertiseView.setMerchantNo(advertiseViewDto.getMerchantNo());
			advertiseView.setAdvertiseCode(advertiseViewDto.getAdvertiseCode());
			advertiseView.setCreateId(advertiseViewDto.getCreateId());
			advertiseView.setCretaeName(advertiseViewDto.getCretaeName());
			advertiseView.setCreateDate(advertiseViewDto.getCreateDate());
			advertiseView.setRemark(advertiseViewDto.getRemark());
			advertiseView.setRemark2(advertiseViewDto.getRemark2());
			advertiseView.setRemark3(advertiseViewDto.getRemark3());
			advertiseView.setRemark4(advertiseViewDto.getRemark4());
			advertiseView.setUpdateTime(advertiseViewDto.getUpdateTime());
			advertiseView.setArticleCode(advertiseViewDto.getArticleCode());
			advertiseViewDao.insertSelective(advertiseView);
			logger.debug("addAdvertiseView(AdvertiseViewDto) - end - return");

			return advertiseView.getCode();

		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增广告查看记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseView.ADVERTISE_VIEW_ADD_ERROR, "新增广告查看记录信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询广告查看记录信息
	 *
	 * @param findAdvertiseViewPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<AdvertiseViewDto> findAdvertiseViews(FindAdvertiseViewPage findAdvertiseViewPage) throws TsfaServiceException {
		AssertUtils.notNull(findAdvertiseViewPage);
		List<AdvertiseViewDto> returnList = null;
		try {
			returnList = advertiseViewDao.findAdvertiseViews(findAdvertiseViewPage);
		} catch (Exception e) {
			logger.error("查找广告查看记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseView.ADVERTISE_VIEW_NOT_EXIST_ERROR, "广告查看记录信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateAdvertiseView(AdvertiseViewDto advertiseViewDto) throws TsfaServiceException {
		logger.debug("updateAdvertiseView(AdvertiseViewDto advertiseViewDto={}) - start", advertiseViewDto);

		AssertUtils.notNull(advertiseViewDto);
		AssertUtils.notNullAndEmpty(advertiseViewDto.getCode(), "Code不能为空");
		try {
			AdvertiseView advertiseView = new AdvertiseView();
			// update数据录入
			advertiseView.setCode(advertiseViewDto.getCode());
			advertiseView.setMerchantNo(advertiseViewDto.getMerchantNo());
			advertiseView.setAdvertiseCode(advertiseViewDto.getAdvertiseCode());
			advertiseView.setCreateId(advertiseViewDto.getCreateId());
			advertiseView.setCretaeName(advertiseViewDto.getCretaeName());
			advertiseView.setCreateDate(advertiseViewDto.getCreateDate());
			advertiseView.setRemark(advertiseViewDto.getRemark());
			advertiseView.setRemark2(advertiseViewDto.getRemark2());
			advertiseView.setRemark3(advertiseViewDto.getRemark3());
			advertiseView.setRemark4(advertiseViewDto.getRemark4());
			advertiseView.setUpdateTime(advertiseViewDto.getUpdateTime());
			advertiseView.setArticleCode(advertiseViewDto.getArticleCode());
			AssertUtils.notUpdateMoreThanOne(advertiseViewDao.updateByPrimaryKeySelective(advertiseView));
			logger.debug("updateAdvertiseView(AdvertiseViewDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("广告查看记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseView.ADVERTISE_VIEW_UPDATE_ERROR, "广告查看记录信息更新信息错误！", e);
		}
	}

	@Override
	public AdvertiseViewDto findAdvertiseView(AdvertiseViewDto advertiseViewDto) throws TsfaServiceException {
		logger.debug("findAdvertiseView(FindAdvertiseView findAdvertiseView={}) - start", advertiseViewDto);

		AssertUtils.notNull(advertiseViewDto);
		AssertUtils.notAllNull(advertiseViewDto.getCode(), "Code不能为空");
		try {
			AdvertiseView advertiseView = advertiseViewDao.selectByPrimaryKey(advertiseViewDto.getCode());
			if (advertiseView == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.ADVERTISE_VIEW_NOT_EXIST_ERROR,"广告查看记录信息不存在");
			}
			AdvertiseViewDto findAdvertiseViewReturn = new AdvertiseViewDto();
			// find数据录入
			findAdvertiseViewReturn.setCode(advertiseView.getCode());
			findAdvertiseViewReturn.setMerchantNo(advertiseView.getMerchantNo());
			findAdvertiseViewReturn.setAdvertiseCode(advertiseView.getAdvertiseCode());
			findAdvertiseViewReturn.setCreateId(advertiseView.getCreateId());
			findAdvertiseViewReturn.setCretaeName(advertiseView.getCretaeName());
			findAdvertiseViewReturn.setCreateDate(advertiseView.getCreateDate());
			findAdvertiseViewReturn.setRemark(advertiseView.getRemark());
			findAdvertiseViewReturn.setRemark2(advertiseView.getRemark2());
			findAdvertiseViewReturn.setRemark3(advertiseView.getRemark3());
			findAdvertiseViewReturn.setRemark4(advertiseView.getRemark4());
			findAdvertiseViewReturn.setUpdateTime(advertiseView.getUpdateTime());
			findAdvertiseViewReturn.setArticleCode(advertiseView.getArticleCode());

			logger.debug("findAdvertiseView(AdvertiseViewDto) - end - return value={}", findAdvertiseViewReturn);
			return findAdvertiseViewReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找广告查看记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseView.ADVERTISE_VIEW_FIND_ERROR, "查找广告查看记录信息信息错误！", e);
		}

	}

	@Override
	public Page<AdvertiseViewDto> findAdvertiseViewPage(FindAdvertiseViewPage findAdvertiseViewPage) throws TsfaServiceException {
		logger.debug("findAdvertiseViewPage(FindAdvertiseViewPage findAdvertiseViewPage={}) - start", findAdvertiseViewPage);

		AssertUtils.notNull(findAdvertiseViewPage);
		List<AdvertiseViewDto> returnList = null;
		int count = 0;
		try {
			returnList = advertiseViewDao.findAdvertiseViewPage(findAdvertiseViewPage);
			count = advertiseViewDao.findAdvertiseViewPageCount(findAdvertiseViewPage);
		} catch (Exception e) {
			logger.error("广告查看记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeAdvertiseView.ADVERTISE_VIEW_FIND_PAGE_ERROR, "广告查看记录信息不存在错误.！", e);
		}
		Page<AdvertiseViewDto> returnPage = new Page<AdvertiseViewDto>(returnList, count, findAdvertiseViewPage);

		logger.debug("findAdvertiseViewPage(FindAdvertiseViewPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public Map<String, Integer> findAdvertiseViewPageCountForGroupAdvertiseCode(List<String> advertiseCodeList) {
		FindAdvertiseViewPage findAdvertiseViewPage = new FindAdvertiseViewPage();
		AdvertiseViewDto param = new AdvertiseViewDto();
		param.setAdvertiseCodeList(advertiseCodeList);
		findAdvertiseViewPage.setParam(param);

		List<Map<String, Object>> data = advertiseViewDao.findAdvertiseViewPageCountForGroupAdvertiseCode(findAdvertiseViewPage);

		Map<String, Integer> rs = data.stream().collect(Collectors.toMap(map -> map.get("advertiseCode").toString(), map -> Integer.parseInt(map.get("num").toString())));
		return rs;
	}

	@Override
	public int selectAdvertiseViewCount(AdvertiseDto advertiseDto) {

		return advertiseViewDao.selectAdvertiseViewCount(advertiseDto);
	}

	@Override
	public int getCountView(AdvertiseViewDto param) {

		return advertiseViewDao.getCountView(param);
	}

	@Override
	public List<AdvertiseViewDto> findGroupTotalByExample(AdvertiseDto record) throws TsfaServiceException {
		return advertiseViewDao.findGroupTotalByExample(record);
	}
}
