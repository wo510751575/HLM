package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IAreaOrderAnalyzeDao;
import com.lj.business.st.domain.AreaOrderAnalyze;
import com.lj.business.st.dto.AreaOrderAnalyze.AddAreaOrderAnalyze;
import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyze;
import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyzeReturn;
import com.lj.business.st.service.IAreaOrderAnalyzeService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class AreaOrderAnalyzeServiceImpl implements IAreaOrderAnalyzeService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AreaOrderAnalyzeServiceImpl.class);
	
	@Resource
	private IAreaOrderAnalyzeDao areaOrderAnalyzeDao;
	
	
	@Override
	public void addAreaOrderAnalyze(
			AddAreaOrderAnalyze addAreaOrderAnalyze) throws TsfaServiceException {
		logger.debug("addAreaOrderAnalyze(AddAreaOrderAnalyze addAreaOrderAnalyze={}) - start", addAreaOrderAnalyze); 

		AssertUtils.notNull(addAreaOrderAnalyze);
		try {
			AreaOrderAnalyze areaOrderAnalyze = new AreaOrderAnalyze();
			//add数据录入
			areaOrderAnalyze.setCode(addAreaOrderAnalyze.getCode());
			areaOrderAnalyze.setMerchantNo(addAreaOrderAnalyze.getMerchantNo());
			areaOrderAnalyze.setAreaCode(addAreaOrderAnalyze.getAreaCode());
			areaOrderAnalyze.setAreaName(addAreaOrderAnalyze.getAreaName());
			areaOrderAnalyze.setStDate(DateUtils.getPreday(new Date()));
			areaOrderAnalyze.setNumOrder(addAreaOrderAnalyze.getNumOrder());
			areaOrderAnalyze.setCreateDate(new Date());
			areaOrderAnalyzeDao.insert(areaOrderAnalyze);
			logger.debug("addAreaOrderAnalyze(AddAreaOrderAnalyze) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增区域订单分析表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.AREA_ORDER_ANALYZE_ADD_ERROR,"新增区域订单分析表信息错误！",e);
		}
	}

	@Override
	public List<FindAreaOrderAnalyzeReturn> findAreaOrderAnalyzeList(FindAreaOrderAnalyze findAreaOrderAnalyze) throws TsfaServiceException {
		logger.debug("findAreaOrderAnalyzeList(findAreaOrderAnalyze={}) - start", findAreaOrderAnalyze);

		AssertUtils.notNull(findAreaOrderAnalyze);
		//AssertUtils.notNull(findAreaOrderAnalyze.getBeginDate(), "开始日期不能为空");
		//AssertUtils.notNull(findAreaOrderAnalyze.getEndDate(), "结束日期不能为空");

		List<FindAreaOrderAnalyzeReturn> result = new ArrayList<>();
		try {
			List<AreaOrderAnalyze> areaOrderAnalyzeList = areaOrderAnalyzeDao.findAreaOrderAnalyzeList(findAreaOrderAnalyze);
			if (!CollectionUtils.isEmpty(areaOrderAnalyzeList)) {
				for (AreaOrderAnalyze each : areaOrderAnalyzeList) {
					FindAreaOrderAnalyzeReturn item = new FindAreaOrderAnalyzeReturn();
					item.setAreaCode(each.getAreaCode());
					item.setAreaName(each.getAreaName() == null ? "未知区域" : each.getAreaName());
					item.setMerchantNo(each.getMerchantNo());
					item.setNumOrder(each.getNumOrder());
					result.add(item);
				}
			}
			logger.debug("findAreaOrderAnalyzeList(findAreaOrderAnalyzeList) - end - return");
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查询区域订单分析表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.AREA_ORDER_ANALYZE_FIND_ERROR,"查询区域订单分析表信息错误！",e);
		}
		return result;
	}

	@Override
	public List<FindAreaOrderAnalyzeReturn> findAreaCodeMaxNum(FindAreaOrderAnalyze findAreaOrderAnalyze) {
		logger.debug("findAreaCodeMaxNum() - start");
		List<FindAreaOrderAnalyzeReturn> findAreaOrderAnalyzeReturn=null;
		try {
			findAreaOrderAnalyzeReturn= areaOrderAnalyzeDao.findAreaCodeMaxNum(findAreaOrderAnalyze);
		} catch (Exception e) {
			logger.error("查询区域订单分析表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.AREA_ORDER_ANALYZE_FIND_ERROR,"查询区域订单分析表信息错误！",e);
		}
		return findAreaOrderAnalyzeReturn;
	}


}
