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
import com.lj.business.st.dao.ISocialAnalyzeDao;
import com.lj.business.st.domain.SocialAnalyze;
import com.lj.business.st.dto.SocialAnalyze.AddSocialAnalyze;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyze;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeReturn;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeTotal;
import com.lj.business.st.service.ISocialAnalyzeService;

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
public class SocialAnalyzeServiceImpl implements ISocialAnalyzeService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(SocialAnalyzeServiceImpl.class);
	

	@Resource
	private ISocialAnalyzeDao socialAnalyzeDao;
	
	
	@Override
	public void addSocialAnalyze(
			AddSocialAnalyze addSocialAnalyze) throws TsfaServiceException {
		logger.debug("addSocialAnalyze(AddSocialAnalyze addSocialAnalyze={}) - start", addSocialAnalyze); 

		AssertUtils.notNull(addSocialAnalyze);
		try {
			SocialAnalyze socialAnalyze = new SocialAnalyze();
			//add数据录入
			socialAnalyze.setCode(addSocialAnalyze.getCode());
			socialAnalyze.setMerchantNo(addSocialAnalyze.getMerchantNo());
			socialAnalyze.setAreaCode(addSocialAnalyze.getAreaCode());
			socialAnalyze.setAreaName(addSocialAnalyze.getAreaName());
			socialAnalyze.setShopNo(addSocialAnalyze.getShopNo());
			socialAnalyze.setShopName(addSocialAnalyze.getShopName());
			socialAnalyze.setMemberNoGm(addSocialAnalyze.getMemberNoGm());
			socialAnalyze.setMemberNameGm(addSocialAnalyze.getMemberNameGm());
			socialAnalyze.setMemberNo(addSocialAnalyze.getMemberNo());
			socialAnalyze.setMemberName(addSocialAnalyze.getMemberName());
			socialAnalyze.setStDate(addSocialAnalyze.getStDate());
			socialAnalyze.setDimensionSt(addSocialAnalyze.getDimensionSt());
			socialAnalyze.setNumSocial(addSocialAnalyze.getNumSocial());
			socialAnalyze.setCreateDate(addSocialAnalyze.getCreateDate());
			socialAnalyzeDao.insert(socialAnalyze);
			logger.debug("addSocialAnalyze(AddSocialAnalyze) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增社交分析表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SOCIAL_ANALYZE_ADD_ERROR,"新增社交分析表信息错误！",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lj.business.st.service.ISocialAnalyzeService#findSocialAnalyze(com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeTotal)
	 */
	@Override
	public List<FindSocialAnalyzeReturn> findSocialAnalyze(FindSocialAnalyzeTotal findSocialAnalyzeTotal) throws TsfaServiceException {
		logger.debug("findSocialAnalyze(FindSocialAnalyzeTotal findSocialAnalyzeTotal={}) - start", findSocialAnalyzeTotal); //$NON-NLS-1$

		AssertUtils.notNull(findSocialAnalyzeTotal);
		AssertUtils.notNullAndEmpty(findSocialAnalyzeTotal.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findSocialAnalyzeTotal.getBeginDate(),"统计开始时间不能为空");
		AssertUtils.notNullAndEmpty(findSocialAnalyzeTotal.getEndDate(),"统计结束时间不能为空");
		try {
			List<FindSocialAnalyzeReturn> list = socialAnalyzeDao.findSocialAnalyze(findSocialAnalyzeTotal);

			logger.debug("findSocialAnalyze() - end - return value={}", list); //$NON-NLS-1$
			return list;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找社交分析表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SOCIAL_ANALYZE_FIND_ERROR,"查找社交分析表信息错误！",e);
		}

	}
	@Override
	public List<FindSocialAnalyzeReturn> findSocialAnalyzeApp(FindSocialAnalyzeTotal findSocialAnalyzeTotal) throws TsfaServiceException {
		
		AssertUtils.notNull(findSocialAnalyzeTotal);
		AssertUtils.notNullAndEmpty(findSocialAnalyzeTotal.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findSocialAnalyzeTotal.getBeginDate(),"统计开始时间不能为空");
		AssertUtils.notNullAndEmpty(findSocialAnalyzeTotal.getEndDate(),"统计结束时间不能为空");
		try {
			List<FindSocialAnalyzeReturn> list = socialAnalyzeDao.findSocialAnalyzeApp(findSocialAnalyzeTotal);
			return list;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找社交分析表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SOCIAL_ANALYZE_FIND_ERROR,"查找社交分析表信息信息错误！",e);
		}
		
	}

	@Override
	public FindSocialAnalyzeReturn findSocialAnalyzeMax(FindSocialAnalyze findSocialAnalyze) {
		AssertUtils.notNull(findSocialAnalyze);
		AssertUtils.notNullAndEmpty(findSocialAnalyze.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findSocialAnalyze.getMemberNoGm(),"会员编号不能为空");
		try {
			 FindSocialAnalyzeReturn findSocialAnalyzeReturn = socialAnalyzeDao.findSocialAnalyzeMax(findSocialAnalyze);
			 return findSocialAnalyzeReturn;
		} catch (Exception e) {
			logger.error("查找社交分析表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SOCIAL_ANALYZE_FIND_ERROR,"查找社交分析表信息信息错误！",e);
		}
	}


}
