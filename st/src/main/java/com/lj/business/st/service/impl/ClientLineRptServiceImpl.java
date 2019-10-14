package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IClientLineRptDao;
import com.lj.business.st.domain.ClientLineRpt;
import com.lj.business.st.dto.FindClientAnalyzeAndOthers;
import com.lj.business.st.dto.ClientLineRpt.AddClientLineRpt;
import com.lj.business.st.dto.ClientLineRpt.FindClientLineRpt;
import com.lj.business.st.dto.ClientLineRpt.FindClientLineRptReturn;
import com.lj.business.st.service.IClientLineRptService;

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
public class ClientLineRptServiceImpl implements IClientLineRptService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ClientLineRptServiceImpl.class);
	

	@Resource
	private IClientLineRptDao clientLineRptDao;
	
	
	@Override
	public void addClientLineRpt(
			AddClientLineRpt addClientLineRpt) throws TsfaServiceException {
		logger.debug("addClientLineRpt(AddClientLineRpt addClientLineRpt={}) - start", addClientLineRpt); 

		AssertUtils.notNull(addClientLineRpt);
		try {
			ClientLineRpt clientLineRpt = new ClientLineRpt();
			//add数据录入
			clientLineRpt.setCode(GUID.getPreUUID());
			clientLineRpt.setMerchantNo(addClientLineRpt.getMerchantNo());
			clientLineRpt.setShopNo(addClientLineRpt.getShopNo());
			clientLineRpt.setShopName(addClientLineRpt.getShopName());
			clientLineRpt.setMemberNoGm(addClientLineRpt.getMemberNoGm());
			clientLineRpt.setMemberNameGm(addClientLineRpt.getMemberNameGm());
			clientLineRpt.setCodeLine(addClientLineRpt.getCodeLine());
			clientLineRpt.setLineName(addClientLineRpt.getLineName());
			clientLineRpt.setRatioLine(addClientLineRpt.getRatioLine());
			clientLineRpt.setDimensionSt(addClientLineRpt.getDimensionSt());
			clientLineRpt.setCreateDate(addClientLineRpt.getCreateDate());
			clientLineRpt.setAreaCode(addClientLineRpt.getAreaCode());
			clientLineRpt.setNumLine(addClientLineRpt.getNumLine());
			clientLineRptDao.insert(clientLineRpt);
			logger.debug("addClientLineRpt(AddClientLineRpt) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增客户职业统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_LINE_RPT_ADD_ERROR,"新增客户职业统计表信息错误！",e);
		}
	}
	
	
	@Override
	public FindClientLineRptReturn findClientLineRpt(
			FindClientLineRpt findClientLineRpt) throws TsfaServiceException {
		logger.debug("findClientLineRpt(FindClientLineRpt findClientLineRpt={}) - start", findClientLineRpt); //$NON-NLS-1$

		AssertUtils.notNull(findClientLineRpt);
		AssertUtils.notAllNull(findClientLineRpt.getCode(),"Code不能为空");
		try {
			ClientLineRpt clientLineRpt = clientLineRptDao.selectByPrimaryKey(findClientLineRpt.getCode());
			if(clientLineRpt == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.CLIENT_LINE_RPT_NOT_EXIST_ERROR,"客户职业统计表信息不存在");
			}
			FindClientLineRptReturn findClientLineRptReturn = new FindClientLineRptReturn();
			//find数据录入
			findClientLineRptReturn.setCode(clientLineRpt.getCode());
			findClientLineRptReturn.setMerchantNo(clientLineRpt.getMerchantNo());
			findClientLineRptReturn.setShopNo(clientLineRpt.getShopNo());
			findClientLineRptReturn.setShopName(clientLineRpt.getShopName());
			findClientLineRptReturn.setMemberNoGm(clientLineRpt.getMemberNoGm());
			findClientLineRptReturn.setMemberNameGm(clientLineRpt.getMemberNameGm());
			findClientLineRptReturn.setCodeLine(clientLineRpt.getCodeLine());
			findClientLineRptReturn.setLineName(clientLineRpt.getLineName());
			findClientLineRptReturn.setRatioLine(clientLineRpt.getRatioLine());
			findClientLineRptReturn.setDimensionSt(clientLineRpt.getDimensionSt());
			findClientLineRptReturn.setCreateDate(clientLineRpt.getCreateDate());
			
			logger.debug("findClientLineRpt(FindClientLineRpt) - end - return value={}", findClientLineRptReturn); //$NON-NLS-1$
			return findClientLineRptReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找客户职业统计表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_LINE_RPT_FIND_ERROR,"查找客户职业统计表信息信息错误！",e);
		}


	}

	@Override
	public List<ClientLineRpt> selectClientLineRptList(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers) throws TsfaServiceException {
		AssertUtils.notNull(findClientAnalyzeAndOthers);

		try {
			return clientLineRptDao.selectClientLineRptList(findClientAnalyzeAndOthers);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找客户职业统计表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_LINE_RPT_FIND_ERROR,"查找客户职业统计表信息信息错误！",e);
		}
	}

	@Override
	public int updateClientLineRpt(ClientLineRpt clientLineRpt) throws TsfaServiceException {
		AssertUtils.notNull(clientLineRpt);

		try {
			return clientLineRptDao.updateByPrimaryKeySelective(clientLineRpt);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("更新客户职业统计表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_LINE_RPT_FIND_ERROR,"更新客户职业统计表信息信息错误！",e);
		}
	}

	@Override
	public List<ClientLineRpt> selectAllShopData(Map<String,String> map) {
		try {
			return clientLineRptDao.selectAllShopData(map);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("获取所有商户维度的数据时异常！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_LINE_RPT_FIND_ERROR,"获取所有商户维度的数据时异常！",e);
		}
	}


	@Override
	public List<ClientLineRpt> selectMerchantTotalByArea() {
		try {
			return clientLineRptDao.selectMerchantTotalByArea();
		}  catch (Exception e) {
			logger.error("根据商户区域维度数据统计商户数据异常！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_LINE_RPT_FIND_ERROR,"根据商户区域维度数据统计商户数据时异常！",e);
		}
	}


	@Override
	public List<ClientLineRpt> selectAreaTotalByShop() {
		try {
			return clientLineRptDao.selectAreaTotalByShop();
		}  catch (Exception e) {
			logger.error("根据分店维度数据统计商户区域数据异常！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_LINE_RPT_FIND_ERROR,"根据分店维度数据统计商户区域数据时异常！",e);
		}
	}


}
