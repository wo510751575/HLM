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
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IClientInterestRptDao;
import com.lj.business.st.domain.ClientInterestRpt;
import com.lj.business.st.dto.FindClientAnalyzeAndOthers;
import com.lj.business.st.dto.ClientInterestRpt.AddClientInterestRpt;
import com.lj.business.st.dto.ClientInterestRpt.FindClientInterestRpt;
import com.lj.business.st.dto.ClientInterestRpt.FindClientInterestRptReturn;
import com.lj.business.st.service.IClientInterestRptService;

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
public class ClientInterestRptServiceImpl implements IClientInterestRptService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ClientInterestRptServiceImpl.class);
	

	@Resource
	private IClientInterestRptDao clientInterestRptDao;
	
	
	@Override
	public void addClientInterestRpt(
			AddClientInterestRpt addClientInterestRpt) throws TsfaServiceException {
		logger.debug("addClientInterestRpt(AddClientInterestRpt addClientInterestRpt={}) - start", addClientInterestRpt); 

		AssertUtils.notNull(addClientInterestRpt);
		try {
			ClientInterestRpt clientInterestRpt = new ClientInterestRpt();
			//add数据录入
			clientInterestRpt.setCode(GUID.getPreUUID());
			clientInterestRpt.setMerchantNo(addClientInterestRpt.getMerchantNo());
			clientInterestRpt.setShopNo(addClientInterestRpt.getShopNo());
			clientInterestRpt.setShopName(addClientInterestRpt.getShopName());
			clientInterestRpt.setMemberNoGm(addClientInterestRpt.getMemberNoGm());
			clientInterestRpt.setMemberNameGm(addClientInterestRpt.getMemberNameGm());
			clientInterestRpt.setCodeInterest(addClientInterestRpt.getCodeInterest());
			clientInterestRpt.setInterestName(addClientInterestRpt.getInterestName());
			clientInterestRpt.setRatioLine(addClientInterestRpt.getRatioLine());
			clientInterestRpt.setDimensionSt(addClientInterestRpt.getDimensionSt());
			clientInterestRpt.setCreateDate(addClientInterestRpt.getCreateDate());
			clientInterestRpt.setNumInterest(addClientInterestRpt.getNumInterest());
			clientInterestRpt.setAreaCode(addClientInterestRpt.getAreaCode());
			clientInterestRptDao.insert(clientInterestRpt);
			logger.debug("addClientInterestRpt(AddClientInterestRpt) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增客户兴趣统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_INTEREST_RPT_ADD_ERROR,"新增客户兴趣统计表信息错误！",e);
		}
	}
	

	@Override
	public FindClientInterestRptReturn findClientInterestRpt(
			FindClientInterestRpt findClientInterestRpt) throws TsfaServiceException {
		logger.debug("findClientInterestRpt(FindClientInterestRpt findClientInterestRpt={}) - start", findClientInterestRpt); //$NON-NLS-1$

		AssertUtils.notNull(findClientInterestRpt);
		AssertUtils.notAllNull(findClientInterestRpt.getCode(),"Code不能为空");
		try {
			ClientInterestRpt clientInterestRpt = clientInterestRptDao.selectByPrimaryKey(findClientInterestRpt.getCode());
			if(clientInterestRpt == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.CLIENT_INTEREST_RPT_NOT_EXIST_ERROR,"客户兴趣统计表信息不存在");
			}
			FindClientInterestRptReturn findClientInterestRptReturn = new FindClientInterestRptReturn();
			//find数据录入
			findClientInterestRptReturn.setCode(clientInterestRpt.getCode());
			findClientInterestRptReturn.setMerchantNo(clientInterestRpt.getMerchantNo());
			findClientInterestRptReturn.setShopNo(clientInterestRpt.getShopNo());
			findClientInterestRptReturn.setShopName(clientInterestRpt.getShopName());
			findClientInterestRptReturn.setMemberNoGm(clientInterestRpt.getMemberNoGm());
			findClientInterestRptReturn.setMemberNameGm(clientInterestRpt.getMemberNameGm());
			findClientInterestRptReturn.setCodeInterest(clientInterestRpt.getCodeInterest());
			findClientInterestRptReturn.setInterestName(clientInterestRpt.getInterestName());
			findClientInterestRptReturn.setRatioLine(clientInterestRpt.getRatioLine());
			findClientInterestRptReturn.setDimensionSt(clientInterestRpt.getDimensionSt());
			findClientInterestRptReturn.setCreateDate(clientInterestRpt.getCreateDate());
			
			logger.debug("findClientInterestRpt(FindClientInterestRpt) - end - return value={}", findClientInterestRptReturn); //$NON-NLS-1$
			return findClientInterestRptReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找客户兴趣统计表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_INTEREST_RPT_FIND_ERROR,"查找客户兴趣统计表信息信息错误！",e);
		}


	}

	@Override
	public List<ClientInterestRpt> selectClientInterestRptList(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers) {
		AssertUtils.notNull(findClientAnalyzeAndOthers);

		try {
			return clientInterestRptDao.selectClientInterestRptList(findClientAnalyzeAndOthers);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找客户兴趣统计表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_INTEREST_RPT_FIND_ERROR,"查找客户兴趣统计表信息信息错误！",e);
		}
	}

	@Override
	public int updateClientInterestRpt(ClientInterestRpt clientInterestRpt) throws TsfaServiceException {
		AssertUtils.notNull(clientInterestRpt);

		try {
			return clientInterestRptDao.updateByPrimaryKeySelective(clientInterestRpt);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("更新客户兴趣统计表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_INTEREST_RPT_FIND_ERROR,"更新客户兴趣统计表信息信息错误！",e);
		}
	}

	@Override
	public List<ClientInterestRpt> selectAllShopData() {
		try {
			return clientInterestRptDao.selectAllShopData();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("获取所有商户维度的数据时异常！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_INTEREST_RPT_FIND_ERROR,"获取所有商户维度的数据时异常！",e);
		}
	}


	@Override
	public List<ClientInterestRpt> selectMerchantTotalByArea() {
		try {
			return clientInterestRptDao.selectMerchantTotalByArea();
		}  catch (Exception e) {
			logger.error("根据商户区域维度数据统计商户数据异常！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_INTEREST_RPT_FIND_ERROR,"根据商户区域维度数据统计商户数据时异常！",e);
		}
	}


	@Override
	public List<ClientInterestRpt> selectAreaTotalByShop() {
		try {
			return clientInterestRptDao.selectAreaTotalByShop();
		}  catch (Exception e) {
			logger.error("根据分店维度数据统计商户区域数据异常！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_INTEREST_RPT_FIND_ERROR,"根据分店维度数据统计商户区域数据时异常！",e);
		}
	}

}
