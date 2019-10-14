package com.lj.business.st.service.impl;

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

import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IPmLabelTotalDao;
import com.lj.business.st.domain.PmLabelTotal;
import com.lj.business.st.dto.PmLabelTotal.AddPmLabelTotal;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotal;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotalReturn;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotalReturnDto;
import com.lj.business.st.service.IPmLabelTotalService;

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
 *         CreateDate: 2017-06-14
 */
@Service
public class PmLabelTotalServiceImpl implements IPmLabelTotalService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory
			.getLogger(PmLabelTotalServiceImpl.class);

	@Resource
	private IPmLabelTotalDao pmLabelTotalDao;

	@Override
	public void addPmLabelTotal(AddPmLabelTotal addPmLabelTotal)
			throws TsfaServiceException {
		logger.debug(
				"addPmLabelTotal(AddPmLabelTotal addPmLabelTotal={}) - start",
				addPmLabelTotal);

		AssertUtils.notNull(addPmLabelTotal);
		try {
			PmLabelTotal pmLabelTotal = new PmLabelTotal();
			// add数据录入
			pmLabelTotal.setCode(addPmLabelTotal.getCode());
			
			
			pmLabelTotal.setMerchantNo(addPmLabelTotal.getMerchantNo());
			pmLabelTotal.setShopNo(addPmLabelTotal.getShopNo());
			pmLabelTotal.setShopName(addPmLabelTotal.getShopName());
			pmLabelTotal.setMemberNoGm(addPmLabelTotal.getMemberNoGm());
			pmLabelTotal.setMemberNameGm(addPmLabelTotal.getMemberNameGm());
			pmLabelTotal.setLabelId(addPmLabelTotal.getLabelId());
			pmLabelTotal.setLabelName(addPmLabelTotal.getLabelName());
			pmLabelTotal.setPmNum(addPmLabelTotal.getPmNum());
			pmLabelTotal.setRatioPm(addPmLabelTotal.getRatioPm());
			pmLabelTotal.setDimensionSt(addPmLabelTotal.getDimensionSt());
			pmLabelTotal.setCreateDate(addPmLabelTotal.getCreateDate());
			pmLabelTotalDao.insertSelective(pmLabelTotal);
			logger.debug("addPmLabelTotal(AddPmLabelTotal) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增客户标签统计表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_TOTAL_ADD_ERROR,
					"新增客户标签统计表信息错误！", e);
		}
	}

	@Override
	public FindPmLabelTotalReturn findPmLabelTotal(
			FindPmLabelTotal findPmLabelTotal) throws TsfaServiceException {
		logger.debug(
				"findPmLabelTotal(FindPmLabelTotal findPmLabelTotal={}) - start", findPmLabelTotal); //$NON-NLS-1$

		AssertUtils.notNull(findPmLabelTotal);
		AssertUtils.notAllNull(findPmLabelTotal.getMerchantNo(), "商户编号不能为空");
		try {
			PmLabelTotal pmLabelTotal = pmLabelTotalDao
					.selectByPrimaryKey(findPmLabelTotal.getMerchantNo());
			if (pmLabelTotal == null) {
				return null;
				// throw new
				// TsfaServiceException(ErrorCode.PM_LABEL_TOTAL_NOT_EXIST_ERROR,"客户标签统计表信息不存在");
			}
			FindPmLabelTotalReturn findPmLabelTotalReturn = new FindPmLabelTotalReturn();
			// find数据录入
			findPmLabelTotalReturn.setCode(pmLabelTotal.getCode());
			findPmLabelTotalReturn.setMerchantNo(pmLabelTotal.getMerchantNo());
			findPmLabelTotalReturn.setShopNo(pmLabelTotal.getShopNo());
			findPmLabelTotalReturn.setShopName(pmLabelTotal.getShopName());
			findPmLabelTotalReturn.setMemberNoGm(pmLabelTotal.getMemberNoGm());
			findPmLabelTotalReturn.setMemberNameGm(pmLabelTotal
					.getMemberNameGm());
			findPmLabelTotalReturn.setLabelId(pmLabelTotal.getLabelId());
			findPmLabelTotalReturn.setLabelName(pmLabelTotal.getLabelName());
			findPmLabelTotalReturn.setPmNum(pmLabelTotal.getPmNum());
			findPmLabelTotalReturn.setRatioPm(pmLabelTotal.getRatioPm());
			findPmLabelTotalReturn
					.setDimensionSt(pmLabelTotal.getDimensionSt());
			findPmLabelTotalReturn.setCreateDate(pmLabelTotal.getCreateDate());

			logger.debug(
					"findPmLabelTotal(FindPmLabelTotal) - end - return value={}", findPmLabelTotalReturn); //$NON-NLS-1$
			return findPmLabelTotalReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找客户标签统计表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_TOTAL_FIND_ERROR,
					"查找客户标签统计表信息信息错误！", e);
		}

	}

	/**
	 * 客户分类报表
	 */
	@Override
	public List<FindPmLabelTotalReturnDto> findPmLabelTotalList(
			FindPmLabelTotal findPmLabelTotal) throws TsfaServiceException {
		logger.debug("findPmLabelTotalList(findPmLabelTotal={}) - start",
				findPmLabelTotal);
			AssertUtils.notNull(findPmLabelTotal);
			AssertUtils.notNullAndEmpty(findPmLabelTotal.getMerchantNo(),"商户号不能为空");
			AssertUtils.notNullAndEmpty(findPmLabelTotal.getShopNo(),"分店号不能为空");
			try {
			List<FindPmLabelTotalReturnDto> list = pmLabelTotalDao
					.findPmLabelTotalList(findPmLabelTotal);
			return list;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找客户标签统计表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_TOTAL_ADD_ERROR,
					"查找客户标签统计表信息错误！", e);
		}
	}
	/**
	 * 客户分类报表
	 */
	@Override
	public List<FindPmLabelTotalReturnDto> findPmLabelTotalListApp(
			FindPmLabelTotal findPmLabelTotal) throws TsfaServiceException {
		logger.debug("findPmLabelTotalList(findPmLabelTotal={}) - start",
				findPmLabelTotal);
		AssertUtils.notNull(findPmLabelTotal);
		AssertUtils.notNullAndEmpty(findPmLabelTotal.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findPmLabelTotal.getMemberNoGm(),"会员编号不能为空");
		AssertUtils.notNullAndEmpty(findPmLabelTotal.getShopNo(),"分店号不能为空");
		try {
			List<FindPmLabelTotalReturnDto> list = pmLabelTotalDao
					.findPmLabelTotalListApp(findPmLabelTotal);
			return list;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找客户标签统计表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_TOTAL_ADD_ERROR,"查找客户标签统计表信息错误！", e);
		}
	}

	@Override
	public FindPmLabelTotalReturnDto findPmLabelTotalMax(
			FindPmLabelTotal findPmLabelTotal) {
		AssertUtils.notNull(findPmLabelTotal);
		AssertUtils.notNullAndEmpty(findPmLabelTotal.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findPmLabelTotal.getMemberNoGm(),"会员编号不能为空");
		try {
			FindPmLabelTotalReturnDto findPmLabelTotalReturnDto=pmLabelTotalDao.findPmLabelTotalMax(findPmLabelTotal);
			return findPmLabelTotalReturnDto;
		} catch (Exception e) {
			logger.error("查找客户标签统计表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_TOTAL_ADD_ERROR,"查找客户标签统计表信息错误！", e);
		}
		
	}

}
