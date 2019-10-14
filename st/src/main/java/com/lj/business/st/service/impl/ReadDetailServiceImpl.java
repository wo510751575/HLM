package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IReadDetailDao;
import com.lj.business.st.dao.IReadTotalDao;
import com.lj.business.st.domain.ReadDetail;
import com.lj.business.st.domain.ReadTotal;
import com.lj.business.st.dto.readDetail.AddReadDetail;
import com.lj.business.st.dto.readDetail.AddReadDetailReturn;
import com.lj.business.st.service.IReadDetailService;

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
public class ReadDetailServiceImpl implements IReadDetailService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ReadDetailServiceImpl.class);
	

	@Resource
	private IReadDetailDao readDetailDao;
	
	@Resource
	private IReadTotalDao readTotalDao;
	
	
	@Override
	public AddReadDetailReturn addReadDetail(
			AddReadDetail addReadDetail) throws TsfaServiceException {
		logger.debug("addReadDetail(AddReadDetail addReadDetail={}) - start", addReadDetail); 
		AssertUtils.notNull(addReadDetail);
		AssertUtils.notNullAndEmpty(addReadDetail.getVisitIdentify(),"访客标识不能为空");
		AssertUtils.notNullAndEmpty(addReadDetail.getUrlAddress(),"uRL地址不能为空");
		try {
			AddReadDetailReturn addReadDetailReturn = new AddReadDetailReturn();
			ReadDetail readDetail = new ReadDetail();
			//add数据录入
			readDetail.setCode(GUID.generateCode());
			readDetail.setName(addReadDetail.getName());
			readDetail.setUrlAddress(addReadDetail.getUrlAddress());
			readDetail.setVisitIdentify(addReadDetail.getVisitIdentify());
			readDetail.setIpAddress(addReadDetail.getIpAddress());
			readDetail.setMac(addReadDetail.getMac());
			readDetail.setNetType(addReadDetail.getNetType());
			readDetail.setEquipment(addReadDetail.getEquipment());
			readDetail.setAreaInfo(addReadDetail.getAreaInfo());
			readDetail.setLoginArea(addReadDetail.getLoginArea());
			readDetailDao.insert(readDetail);
			
			//统计总量处理
			ReadTotal readTotalQuery = new ReadTotal();
			readTotalQuery.setUrlAddress(addReadDetail.getUrlAddress());
			ReadTotal readTotal = readTotalDao.selectByParamKey(readTotalQuery);
			if(readTotal == null){
				ReadTotal record = new ReadTotal();
				record.setCode(GUID.generateCode());
				record.setName(addReadDetail.getName());
				record.setUrlAddress(addReadDetail.getUrlAddress());
				record.setVisitNum(1L);
				readTotalDao.insertSelective(record );
				addReadDetailReturn.setVisitNum(1L);
				addReadDetailReturn.setVisitNumPerson(1L);
			}else{
				readTotalDao.updateVisitNum(readTotal);
				if("Y".equals(addReadDetail.getFristVisit())){
					logger.debug("第一次访问");
					readTotalDao.updateVisitNumPerson(readTotal);
				}else{
					logger.debug("不是第一次访问");
				}
				addReadDetailReturn.setVisitNum(readTotal.getVisitNum());
				addReadDetailReturn.setVisitNumPerson(readTotal.getVisitNumPerson());
			}
			logger.debug("addReadDetail(AddReadDetail) - end - return value={}", addReadDetailReturn); //$NON-NLS-1$
			return addReadDetailReturn;
			
			
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增阅读明细表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.READ_DETAIL_ADD_ERROR,"新增阅读明细表信息错误！",e);
		}
	}
	


}
