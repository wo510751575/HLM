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
import com.lj.business.st.dao.IMaterialTotalDao;
import com.lj.business.st.domain.MaterialTotal;
import com.lj.business.st.dto.MaterialTotal.AddMaterialTotal;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotal;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotalReturn;
import com.lj.business.st.service.IMaterialTotalService;

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
public class MaterialTotalServiceImpl implements IMaterialTotalService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MaterialTotalServiceImpl.class);
	

	@Resource
	private IMaterialTotalDao materialTotalDao;
	
	
	@Override
	public void addMaterialTotal(
			AddMaterialTotal addMaterialTotal) throws TsfaServiceException {
		logger.debug("addMaterialTotal(AddMaterialTotal addMaterialTotal={}) - start", addMaterialTotal); 

		AssertUtils.notNull(addMaterialTotal);
		try {
			MaterialTotal materialTotal = new MaterialTotal();
			//add数据录入
			materialTotal.setCode(addMaterialTotal.getCode());
			materialTotal.setMerchantNo(addMaterialTotal.getMerchantNo());
			materialTotal.setShopNo(addMaterialTotal.getShopNo());
			materialTotal.setShopName(addMaterialTotal.getShopName());
			materialTotal.setMemberNoGm(addMaterialTotal.getMemberNoGm());
			materialTotal.setMemberNameGm(addMaterialTotal.getMemberNameGm());
			materialTotal.setRespondNum(addMaterialTotal.getRespondNum());
			materialTotal.setRatioRespond(addMaterialTotal.getRatioRespond());
			materialTotal.setDaySt(addMaterialTotal.getDaySt());
			materialTotal.setMaterialTypeCode(addMaterialTotal.getMaterialTypeCode());
			materialTotal.setMaterialTypeName(addMaterialTotal.getMaterialTypeName());
			materialTotal.setDimensionSt(addMaterialTotal.getDimensionSt());
			materialTotal.setCreateDate(addMaterialTotal.getCreateDate());
			materialTotalDao.insert(materialTotal);
			logger.debug("addMaterialTotal(AddMaterialTotal) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增素材中心统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TOTAL_ADD_ERROR,"新增素材中心统计表信息错误！",e);
		}
	}
	
	@Override
	public List<FindMaterialTotalReturn> findMaterialTotal(FindMaterialTotal findMaterialTotal) throws TsfaServiceException {
		AssertUtils.notNull(findMaterialTotal);
		AssertUtils.notNullAndEmpty(findMaterialTotal.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findMaterialTotal.getBeginDate(),"统计开始时间不能为空");
		AssertUtils.notNullAndEmpty(findMaterialTotal.getEndDate(),"统计结束时间不能为空");
		try {
			List<FindMaterialTotalReturn> list = materialTotalDao.findMaterialTotal(findMaterialTotal);
			return list;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找素材中心统计表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TOTAL_FIND_ERROR,"查找素材中心统计表信息信息错误！",e);
		}


	}
	@Override
	public List<FindMaterialTotalReturn> findMaterialTotalApp(FindMaterialTotal findMaterialTotal) throws TsfaServiceException {
		AssertUtils.notNull(findMaterialTotal);
		AssertUtils.notNullAndEmpty(findMaterialTotal.getMerchantNo(),"商户号不能为空");
		try {
			List<FindMaterialTotalReturn> list = materialTotalDao.findMaterialTotalApp(findMaterialTotal);
			return list;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找素材中心统计表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TOTAL_FIND_ERROR,"查找素材中心统计表信息信息错误！",e);
		}
		
		
	}

	@Override
	public List<FindMaterialTotalReturn> findMaterialTotalCount(
			FindMaterialTotal findMaterialTotal) throws TsfaServiceException {
		AssertUtils.notNull(findMaterialTotal);
		AssertUtils.notNullAndEmpty(findMaterialTotal.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findMaterialTotal.getMemberNoGm(),"导购编号不能为空");
		List<FindMaterialTotalReturn> list=null;
		try {
			list=materialTotalDao.findMaterialTotalCount(findMaterialTotal);
		} catch (Exception e) {
			logger.error("查找素材中心统计表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TOTAL_FIND_ERROR,"查找素材中心统计表信息信息错误！",e);
		}
		return list;
	}



}
