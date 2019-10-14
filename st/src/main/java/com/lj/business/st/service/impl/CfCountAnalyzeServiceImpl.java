package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import com.lj.base.core.util.ArithUtil;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.ICfCountAnalyzeDao;
import com.lj.business.st.domain.CfCountAnalyze;
import com.lj.business.st.dto.CfCountAnalyze.AddCfCountAnalyze;
import com.lj.business.st.dto.CfCountAnalyze.FindCfCountAnalyze;
import com.lj.business.st.dto.CfCountAnalyze.FindCfCountAnalyzeReturn;
import com.lj.business.st.service.ICfCountAnalyzeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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
public class CfCountAnalyzeServiceImpl implements ICfCountAnalyzeService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CfCountAnalyzeServiceImpl.class);
	

	@Resource
	private ICfCountAnalyzeDao cfCountAnalyzeDao;
	
	
	@Override
	public void addCfCountAnalyze(
			AddCfCountAnalyze addCfCountAnalyze) throws TsfaServiceException {
		logger.debug("addCfCountAnalyze(AddCfCountAnalyze addCfCountAnalyze={}) - start", addCfCountAnalyze); 

		AssertUtils.notNull(addCfCountAnalyze);
		try {
			CfCountAnalyze cfCountAnalyze = new CfCountAnalyze();
			//add数据录入
			cfCountAnalyze.setCode(GUID.getPreUUID());
			cfCountAnalyze.setMerchantNo(addCfCountAnalyze.getMerchantNo());
			cfCountAnalyze.setAreaCode(addCfCountAnalyze.getAreaCode());
			cfCountAnalyze.setAreaName(addCfCountAnalyze.getAreaName());
			cfCountAnalyze.setShopNo(addCfCountAnalyze.getShopNo());
			cfCountAnalyze.setShopName(addCfCountAnalyze.getShopName());
			cfCountAnalyze.setStDate(addCfCountAnalyze.getStDate());
			cfCountAnalyze.setNumCf(addCfCountAnalyze.getNumCf());
			cfCountAnalyze.setRatioCfFive(addCfCountAnalyze.getRatioCfFive());
			cfCountAnalyze.setNumCfFive(addCfCountAnalyze.getNumCfFive());
			cfCountAnalyze.setRatioCfTen(addCfCountAnalyze.getRatioCfTen());
			cfCountAnalyze.setNumCfTen(addCfCountAnalyze.getNumCfTen());
			cfCountAnalyze.setRatioCfFifteen(addCfCountAnalyze.getRatioCfFifteen());
			cfCountAnalyze.setNumCfFifteen(addCfCountAnalyze.getNumCfFifteen());
			cfCountAnalyze.setRatioCfSixteen(addCfCountAnalyze.getRatioCfSixteen());
			cfCountAnalyze.setNumCfSixteen(addCfCountAnalyze.getNumCfSixteen());
			cfCountAnalyze.setCreateDate(addCfCountAnalyze.getCreateDate());
			cfCountAnalyzeDao.insert(cfCountAnalyze);
			logger.debug("addCfCountAnalyze(AddCfCountAnalyze) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增跟进次数分析表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CF_COUNT_ANALYZE_ADD_ERROR,"新增跟进次数分析表信息错误！",e);
		}
	}

	@Override
	public List<FindCfCountAnalyzeReturn> findCfCountAnalyzeList(FindCfCountAnalyze findCfCountAnalyze) throws TsfaServiceException {
		logger.debug("findPmTypeTotalList(findCfCountAnalyze={}) - start", findCfCountAnalyze);

		AssertUtils.notNull(findCfCountAnalyze);
		//AssertUtils.notNull(findCfCountAnalyze.getBeginDate(), "开始日期不能为空");
		//AssertUtils.notNull(findCfCountAnalyze.getEndDate(), "结束日期不能为空");

		List<FindCfCountAnalyzeReturn> result = new ArrayList<>();
		try {
			List<CfCountAnalyze> cfCountAnalyzeList = cfCountAnalyzeDao.findCfCountAnalyzeList(findCfCountAnalyze);
			if (!CollectionUtils.isEmpty(cfCountAnalyzeList)) {

				// 获取所有的数量
				Long totalNum = 0L;
				for (CfCountAnalyze each : cfCountAnalyzeList) {
					if (each == null) {
						continue;
					}
					totalNum += each.getNumCf();
				}

				for (CfCountAnalyze each : cfCountAnalyzeList) {
					if (each == null) {
						continue;
					}
					FindCfCountAnalyzeReturn item = new FindCfCountAnalyzeReturn();
					item.setNumCf(each.getNumCf());
					item.setNumCfFifteen(each.getNumCfFifteen());
					item.setNumCfFive(each.getNumCfFive());
					item.setNumCfSixteen(each.getNumCfSixteen());
					item.setNumCfTen(each.getNumCfTen());
					item.setRatioCfFifteen(getRatio(totalNum, each.getNumCfFifteen()));
					item.setRatioCfFive(getRatio(totalNum, each.getNumCfFive()));
					item.setRatioCfSixteen(getRatio(totalNum, each.getNumCfSixteen()));
					item.setRatioCfTen(getRatio(totalNum, each.getNumCfTen()));
					result.add(item);
				}
			}
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查询跟进次数分析表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CF_COUNT_ANALYZE_FIND_ERROR,"查询跟进次数分析表信息错误！",e);
		}
		return result;
	}

	private String getRatio(Long totalNum, Integer num) {
		if (totalNum == 0) {
			return "0";
		}
		Double div = ArithUtil.div(num, totalNum);
		BigDecimal bd = new BigDecimal(div * 100);
		bd = bd.setScale(4, RoundingMode.HALF_UP);
		return bd.toString();
	}
	
	/**
	 *  public static double mul(double value1,double value2){
         BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
         BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
         return b1.multiply(b2).doubleValue();
    }
	 */

}
