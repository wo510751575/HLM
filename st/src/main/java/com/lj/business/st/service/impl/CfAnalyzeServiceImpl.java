package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.ICfAnalyzeChooseDao;
import com.lj.business.st.dao.ICfAnalyzeDao;
import com.lj.business.st.domain.CfAnalyze;
import com.lj.business.st.dto.CfAnalyze.AddCfAnalyze;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyze;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyzeReturn;
import com.lj.business.st.emus.CfAnalyzeType;
import com.lj.business.st.emus.DimensionSt;
import com.lj.business.st.service.ICfAnalyzeService;

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
public class CfAnalyzeServiceImpl implements ICfAnalyzeService { 


	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CfAnalyzeServiceImpl.class);


	/** The cf analyze dao. */
	@Resource
	private ICfAnalyzeDao cfAnalyzeDao;
	
	@Resource
	private ICfAnalyzeChooseDao cfAnalyzeChooseDao;


	/* (non-Javadoc)
	 * @see com.lj.business.st.service.ICfAnalyzeService#addCfAnalyze(com.lj.business.st.dto.CfAnalyze.AddCfAnalyze)
	 */
	@Override
	public void addCfAnalyze(
			AddCfAnalyze addCfAnalyze) throws TsfaServiceException {
		logger.debug("addCfAnalyze(AddCfAnalyze addCfAnalyze={}) - start", addCfAnalyze); 

		AssertUtils.notNull(addCfAnalyze);
		try {
			CfAnalyze cfAnalyze = new CfAnalyze();
			//add数据录入
			cfAnalyze.setCode(GUID.generateCode());
			cfAnalyze.setMerchantNo(addCfAnalyze.getMerchantNo());
			cfAnalyze.setAreaCode(addCfAnalyze.getAreaCode());
			cfAnalyze.setAreaName(addCfAnalyze.getAreaName());
			cfAnalyze.setShopNo(addCfAnalyze.getShopNo());
			cfAnalyze.setShopName(addCfAnalyze.getShopName());
			cfAnalyze.setMemberNoGm(addCfAnalyze.getMemberNoGm());
			cfAnalyze.setMemberNameGm(addCfAnalyze.getMemberNameGm());
			cfAnalyze.setBriefClientAnalyze(addCfAnalyze.getBriefClientAnalyze());
			cfAnalyze.setBriefClientAction(addCfAnalyze.getBriefClientAction());
			cfAnalyze.setBriefSocial(addCfAnalyze.getBriefSocial());
			cfAnalyze.setBriefMaterial(addCfAnalyze.getBriefMaterial());
			cfAnalyze.setBriefClientType(addCfAnalyze.getBriefClientType());
			cfAnalyze.setDimensionSt(addCfAnalyze.getDimensionSt());
			cfAnalyze.setCreateDate(new Date());
			cfAnalyze.setDaySt(addCfAnalyze.getDaySt());
			cfAnalyzeDao.insertSelective(cfAnalyze);
			logger.debug("addCfAnalyze(AddCfAnalyze) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增跟进分析摘要表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CF_ANALYZE_ADD_ERROR,"新增跟进分析摘要表信息错误！",e);
		}
	}




	/* (non-Javadoc)
	 * @see com.lj.business.st.service.ICfAnalyzeService#findCfAnalyze(com.lj.business.st.dto.CfAnalyze.FindCfAnalyze)
	 */
	@Override
	public List<FindCfAnalyzeReturn> findCfAnalyze(
			FindCfAnalyze findCfAnalyze) throws TsfaServiceException {
		logger.debug("findCfAnalyze(FindCfAnalyze findCfAnalyze={}) - start", findCfAnalyze); //$NON-NLS-1$

		AssertUtils.notNull(findCfAnalyze);
		AssertUtils.notAllNull(findCfAnalyze.getMerchantNo(),"商户编号不能为空");
		AssertUtils.notAllNull(findCfAnalyze.getMemberNoGm(),"导购编号不能为空");
		try {
			Date daySt = findCfAnalyze.getDaySt() ;
			if(daySt == null){
				logger.debug("统计日期为空,默认取昨天");
				daySt = DateUtils.getPreday(org.apache.commons.lang.time.DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH) );
			}
			List<FindCfAnalyzeReturn> list = cfAnalyzeChooseDao.findCfAnalyze(findCfAnalyze);
			CfAnalyze cfAnalyzeQuery = new CfAnalyze();
			cfAnalyzeQuery.setMerchantNo(findCfAnalyze.getMerchantNo());
			cfAnalyzeQuery.setMemberNoGm(findCfAnalyze.getMemberNoGm());
			cfAnalyzeQuery.setDaySt(daySt);
			cfAnalyzeQuery.setDimensionSt(DimensionSt.GUID.toString());
			CfAnalyze cfAnalyzeResult = cfAnalyzeDao.selectByParamKey(cfAnalyzeQuery);
			if(cfAnalyzeResult != null){
				for (FindCfAnalyzeReturn findCfAnalyzeReturn : list) {
					if(CfAnalyzeType.CLIENT_ACTION.toString().equals(findCfAnalyzeReturn.getTypeList())){
						findCfAnalyzeReturn.setBrief(cfAnalyzeResult.getBriefClientAction());
					}else if(CfAnalyzeType.CLIENT_ANALYZE.toString().equals(findCfAnalyzeReturn.getTypeList())){
						findCfAnalyzeReturn.setBrief(cfAnalyzeResult.getBriefClientAnalyze());
					}else if(CfAnalyzeType.CLIENT_TYPE.toString().equals(findCfAnalyzeReturn.getTypeList())){
						findCfAnalyzeReturn.setBrief(cfAnalyzeResult.getBriefClientType());
					}else if(CfAnalyzeType.MATERIAL.toString().equals(findCfAnalyzeReturn.getTypeList())){
						findCfAnalyzeReturn.setBrief(cfAnalyzeResult.getBriefMaterial());
					}else if(CfAnalyzeType.SOCIAL.toString().equals(findCfAnalyzeReturn.getTypeList())){
						findCfAnalyzeReturn.setBrief(cfAnalyzeResult.getBriefSocial());
					}else{
						logger.error("没有找到对应的跟进分析摘要");
					}
				}
			}

			logger.debug("findCfAnalyze(FindCfAnalyze) - end - return value={}", list); //$NON-NLS-1$
			return list;

		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找跟进分析摘要表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CF_ANALYZE_FIND_ERROR,"查找跟进分析摘要表信息信息错误！",e);
		}


	}
}
