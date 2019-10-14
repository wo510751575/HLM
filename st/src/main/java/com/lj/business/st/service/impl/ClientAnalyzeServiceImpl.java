package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IClientAnalyzeDao;
import com.lj.business.st.domain.ClientAnalyze;
import com.lj.business.st.domain.ClientInterestRpt;
import com.lj.business.st.domain.ClientLineRpt;
import com.lj.business.st.dto.AddClientAnalyze;
import com.lj.business.st.dto.AddClientAnalyzeReturn;
import com.lj.business.st.dto.FindClientAnalyze;
import com.lj.business.st.dto.FindClientAnalyzeAndOthers;
import com.lj.business.st.dto.FindClientAnalyzeAndOthersReturn;
import com.lj.business.st.dto.FindClientAnalyzeReturn;
import com.lj.business.st.service.IClientAnalyzeService;
import com.lj.business.st.service.IClientInterestRptService;
import com.lj.business.st.service.IClientLineRptService;

/**
 * 
 * 
 * 类说明：客户画像实现
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月31日
 */
@Service
public class ClientAnalyzeServiceImpl implements IClientAnalyzeService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ClientAnalyzeServiceImpl.class);
	

	@Resource
	private IClientAnalyzeDao shopClientAnalyzeDao;

	@Resource
	private IClientLineRptService clientLineRptService;

	@Resource
	private IClientInterestRptService clientInterestRptService;

	@Resource
	private IPersonMemberService personMemberService;

	
	public AddClientAnalyzeReturn addClientAnalyze(
			AddClientAnalyze addClientAnalyze) throws TsfaServiceException {
		logger.debug("addClientAnalyze(AddClientAnalyze addClientAnalyze={}) - start", addClientAnalyze); 

		AssertUtils.notNull(addClientAnalyze);
		try {
			ClientAnalyze shopClientAnalyze = new ClientAnalyze();
			//add数据录入
			shopClientAnalyze.setCode(GUID.getPreUUID());
			shopClientAnalyze.setMerchantNo(addClientAnalyze.getMerchantNo());
			shopClientAnalyze.setAreaCode(addClientAnalyze.getAreaCode());
			shopClientAnalyze.setAreaName(addClientAnalyze.getAreaName());
			shopClientAnalyze.setShopNo(addClientAnalyze.getShopNo());
			shopClientAnalyze.setShopName(addClientAnalyze.getShopName());
			shopClientAnalyze.setStDate(addClientAnalyze.getStDate());
			shopClientAnalyze.setRatioMale(addClientAnalyze.getRatioMale());
			shopClientAnalyze.setRatioFemale(addClientAnalyze.getRatioFemale());
			shopClientAnalyze.setNumPm(addClientAnalyze.getNumPm());
			shopClientAnalyze.setRatioAgeTen(addClientAnalyze.getRatioAgeTen());
			shopClientAnalyze.setNumAgeTen(addClientAnalyze.getNumAgeTen());
			shopClientAnalyze.setRatioAgeTwenty(addClientAnalyze.getRatioAgeTwenty());
			shopClientAnalyze.setNumAgeTwenty(addClientAnalyze.getNumAgeTwenty());
			shopClientAnalyze.setRatioAgeThirty(addClientAnalyze.getRatioAgeThirty());
			shopClientAnalyze.setNumAgeThirty(addClientAnalyze.getNumAgeThirty());
			shopClientAnalyze.setRatioAgeForty(addClientAnalyze.getRatioAgeForty());
			shopClientAnalyze.setNumAgeForty(addClientAnalyze.getNumAgeForty());
			shopClientAnalyze.setRatioAgeFifty(addClientAnalyze.getRatioAgeFifty());
			shopClientAnalyze.setNumAgeFifty(addClientAnalyze.getNumAgeFifty());
			shopClientAnalyze.setCreateDate(addClientAnalyze.getCreateDate());
			shopClientAnalyze.setDimensionSt(addClientAnalyze.getDimensionSt());
            shopClientAnalyze.setNumMale(addClientAnalyze.getNumMale());
            shopClientAnalyze.setNumFemale(addClientAnalyze.getNumFemale());
			shopClientAnalyzeDao.insertSelective(shopClientAnalyze);
			AddClientAnalyzeReturn addClientAnalyzeReturn = new AddClientAnalyzeReturn();
			
			logger.debug("addClientAnalyze(AddClientAnalyze) - end - return value={}", addClientAnalyzeReturn); 
			return addClientAnalyzeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增客户画像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_ADD_ERROR,"新增客户画像信息错误！",e);
		}
	}


	@Override
	public AddClientAnalyzeReturn addClientAnalyze(ClientAnalyze clientAnalyze) throws TsfaServiceException {
		logger.debug("addClientAnalyze(ClientAnalyze clientAnalyze={}) - start", clientAnalyze); 
		try {
			//add数据录入
			clientAnalyze.setCode(GUID.getPreUUID());
			shopClientAnalyzeDao.insertSelective(clientAnalyze);
			AddClientAnalyzeReturn addClientAnalyzeReturn = new AddClientAnalyzeReturn();
			
			logger.debug("addClientAnalyze(ClientAnalyze) - end - return value={}", addClientAnalyzeReturn); 
			return addClientAnalyzeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增客户画像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_ADD_ERROR,"新增客户画像信息错误！",e);
		}
	}


	@Override
	public List<Map<String, Object>> findClientAnalyzeList(
			Map<String, Object> map) throws TsfaServiceException {
		logger.debug("findClientAnalyzeList(Map<String, Object> map={}) - start", map); 
		AssertUtils.notNull(map);
		List<Map<String,Object>> list= null;
		try {
			list=shopClientAnalyzeDao.findClientAnalyzeList(map);
		} catch (Exception e) {
			logger.error("查询客户画像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_FIND_ERROR,"查询客户画像信息错误！",e);
		}
		logger.debug("findClientAnalyzeList(map - end - return"); 
		return list;
	}


	@Override
	public List<FindClientAnalyzeReturn> findClientAnalyze(
			FindClientAnalyze findClientAnalyze) {
		logger.debug("findClientAnalyze(FindClientAnalyzeObject findClientAnalyze={}) - start", findClientAnalyze); 
		List<FindClientAnalyzeReturn> list=null;
		try {			
			list=shopClientAnalyzeDao.findClientAnalyze(findClientAnalyze);
			
		} catch (Exception e) {
			logger.error("查询客户画像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_FIND_ERROR,"查询客户画像信息错误！",e);
		}
		logger.debug("findClientAnalyze(findClientAnalyze - end - return"); 
		return list;
	}

	@Override
	public FindClientAnalyzeAndOthersReturn findClientAnalyzeAndOthersReturn(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers) throws TsfaServiceException {
		AssertUtils.notNull(findClientAnalyzeAndOthers);

		try {
			FindClientAnalyzeAndOthersReturn result = new FindClientAnalyzeAndOthersReturn();

			// 描述
			Map<String, Object> descMap = new HashMap<>();

			// 查询客户画像
			ClientAnalyze shopClientAnalyzeBase = shopClientAnalyzeDao.findClientAnalyzeBase(findClientAnalyzeAndOthers);
			List<Map<String, Object>> ageList = new ArrayList<>();
			// 性别
			Map<String, Object> sexMap = new HashMap<>();
			
			if(shopClientAnalyzeBase != null){
				sexMap.put("male", mulHundredToString(shopClientAnalyzeBase.getRatioMale()));
				sexMap.put("female", mulHundredToString(shopClientAnalyzeBase.getRatioFemale()));
				if (shopClientAnalyzeBase.getRatioMale() > shopClientAnalyzeBase.getRatioFemale()) {
					descMap.put("sexName", "男性");
				}
				else if (shopClientAnalyzeBase.getRatioMale() < shopClientAnalyzeBase.getRatioFemale()){
					descMap.put("sexName", "女性");
				} else {
					descMap.put("sexName", "");
				}
				
				descMap.put("areaName", shopClientAnalyzeBase.getAreaName());
	
				// 年龄结构
				ageList.add(getAgeRatio("10-19", shopClientAnalyzeBase.getRatioAgeTen(), shopClientAnalyzeBase.getNumAgeTen()));
				ageList.add(getAgeRatio("20-29", shopClientAnalyzeBase.getRatioAgeTwenty(), shopClientAnalyzeBase.getNumAgeTwenty()));
				ageList.add(getAgeRatio("30-39", shopClientAnalyzeBase.getRatioAgeThirty(), shopClientAnalyzeBase.getNumAgeThirty()));
				ageList.add(getAgeRatio("40-49", shopClientAnalyzeBase.getRatioAgeForty(), shopClientAnalyzeBase.getNumAgeForty()));
				ageList.add(getAgeRatio("50-59", shopClientAnalyzeBase.getRatioAgeFifty(), shopClientAnalyzeBase.getNumAgeFifty()));
		    }
			
			String ageName = "";
			Integer ageNumber = 0;
			for (Map<String, Object> each : ageList) {
				Integer tempAgeNumber = (Integer) each.get("number");
				if (tempAgeNumber > ageNumber) {
					ageNumber = tempAgeNumber;
					ageName = (String) each.get("ageName");
				}
			}
			descMap.put("ageName", ageName);

			// 客户职业
			BigDecimal ratioLine = null;
			List<Map<String, Object>> occupationList = new ArrayList<>();
			List<ClientLineRpt> clientLineRpts = clientLineRptService.selectClientLineRptList(findClientAnalyzeAndOthers);
			if (!CollectionUtils.isEmpty(clientLineRpts)) {
				for (ClientLineRpt each : clientLineRpts) {
					Map<String, Object> item = new HashMap<>();
					item.put("name", each.getLineName() == null ? "" : each.getLineName());
					item.put("code", each.getCodeLine() == null ? "" : each.getCodeLine());
					item.put("ratio", mulHundredToString(each.getRatioLine().doubleValue()));
					occupationList.add(item);

					if (ratioLine == null || each.getRatioLine().doubleValue() > ratioLine.doubleValue()) {
						ratioLine = each.getRatioLine();
						descMap.put("occupationName", each.getLineName());
					}
				}
			}

			// 客户兴趣
			List<Map<String, Object>> interestList = new ArrayList<>();
			List<ClientInterestRpt> clientInterestRpts = clientInterestRptService.selectClientInterestRptList(findClientAnalyzeAndOthers);
			if (!CollectionUtils.isEmpty(clientInterestRpts)) {
				for (ClientInterestRpt each : clientInterestRpts) {
					Map<String, Object> item = new HashMap<>();
					item.put("name", each.getInterestName());
					item.put("code", each.getCodeInterest());
					item.put("ratio", mulHundredToString(each.getRatioLine().doubleValue()));
					interestList.add(item);
				}
			}

			result.setDescMap(descMap);
			result.setSexMap(sexMap);
			result.setAgeList(ageList);
			result.setOccupationList(occupationList);
			result.setInterestList(interestList);
			return result;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查询客户画像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_FIND_ERROR,"查询客户画像信息错误！",e);
		}

	}

	@Override
	public ClientAnalyze findByShopNo(String shopNo) {
		try {
			FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
			findClientAnalyzeAndOthers.setDimensionSt("SHOP");
			findClientAnalyzeAndOthers.setShopNo(shopNo);
			return shopClientAnalyzeDao.findClientAnalyzeBase(findClientAnalyzeAndOthers);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查询客户画像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_FIND_ERROR,"查询客户画像信息错误！",e);
		}
	}

	@Override
	public ClientAnalyze findByMerchantNo(String merchantNo) {
		try {
			FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
			findClientAnalyzeAndOthers.setDimensionSt("MERCHANT");
			findClientAnalyzeAndOthers.setMerchantNo(merchantNo);
			return shopClientAnalyzeDao.findClientAnalyzeBase(findClientAnalyzeAndOthers);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查询客户画像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_FIND_ERROR,"查询客户画像信息错误！",e);
		}
	}

	@Override
	public ClientAnalyze findByAreaCode(String areaCode) {
		try {
			FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
			findClientAnalyzeAndOthers.setDimensionSt("AREA");
			findClientAnalyzeAndOthers.setAreaCode(areaCode);
			return shopClientAnalyzeDao.findClientAnalyzeBase(findClientAnalyzeAndOthers);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查询客户画像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_FIND_ERROR,"查询客户画像信息错误！",e);
		}
	}

	@Override
	public int updateClientAnalyze(ClientAnalyze shopClientAnalyze) {
		try {
			return shopClientAnalyzeDao.updateClientAnalyze(shopClientAnalyze);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("更新客户画像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_ADD_ERROR,"更新客户画像信息错误！",e);
		}
	}

	

	@Override
	public List<ClientAnalyze> selectAllShopData() {
		try {
			return shopClientAnalyzeDao.selectAllShopData();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("获取所有商户维度的数据时异常！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_FIND_ERROR,"获取所有商户维度的数据时异常！",e);
		}
	}


	@Override
	public List<ClientAnalyze> selectMerchantTotalByArea() {
		try {
			return shopClientAnalyzeDao.selectMerchantTotalByArea();
		}  catch (Exception e) {
			logger.error("根据商户区域维度数据统计商户数据异常！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_FIND_ERROR,"根据商户区域维度数据统计商户数据时异常！",e);
		}
	}


	@Override
	public List<ClientAnalyze> selectAreaTotalByShop() {
		try {
			return shopClientAnalyzeDao.selectAreaTotalByShop();
		}  catch (Exception e) {
			logger.error("根据分店维度数据统计商户区域数据异常！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_FIND_ERROR,"根据分店维度数据统计商户区域数据时异常！",e);
		}
	}

	/*private Double getRatio(Integer totalNum, Integer num) {
		return getRatio(totalNum.longValue(), num.longValue());
	}

	private Double getRatio(Long totalNum, Long num) {
		if (totalNum == 0 || num == 0) {
			return 0D;
		}
		double result = ArithUtil.div(num, totalNum);
		BigDecimal bd = new BigDecimal(result);
		bd = bd.setScale(4, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}*/

	private Map<String, Object> getAgeRatio(String ageName, Double ratio, Integer number) {
		Map<String, Object> result = new HashMap<>();
		result.put("ageName", ageName);
		result.put("ratio", mulHundredToString(ratio));
		result.put("number", number);
		return result;
	}

	


	private String mulHundredToString(Double num) {
		BigDecimal bd = new BigDecimal(num * 100);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.toString();
	}


	@Override
	public ClientAnalyze findClientAnalyzeBase(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers) {
		try {
			return shopClientAnalyzeDao.findClientAnalyzeBase(findClientAnalyzeAndOthers);
		}  catch (Exception e) {
			logger.error("查询客户画像数据异常！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_FIND_ERROR,"查询客户画像数据异常时异常！",e);
		}
	}
}
