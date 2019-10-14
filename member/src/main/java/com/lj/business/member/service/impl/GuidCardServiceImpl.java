package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.dto.merchantParams.FindMerchantParamsReturn;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IGuidCardDao;
import com.lj.business.member.domain.GuidCard;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.ManagerMemberDto;
import com.lj.business.member.dto.ManagerMemberReturnDto;
import com.lj.business.member.dto.guidCard.AddGuidCard;
import com.lj.business.member.dto.guidCard.DelGuidCard;
import com.lj.business.member.dto.guidCard.FindGuidCard;
import com.lj.business.member.dto.guidCard.FindGuidCardPage;
import com.lj.business.member.dto.guidCard.FindGuidCardPageReturn;
import com.lj.business.member.dto.guidCard.FindGuidCardReturn;
import com.lj.business.member.dto.guidCard.UpdateGuidCard;
import com.lj.business.member.dto.guidCardCustomer.DelGuidCardCustomer;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomer;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomerReturn;
import com.lj.business.member.dto.guidCardCustomer.GuidCardAddNumDto;
import com.lj.business.member.emus.GuidCardCustType;
import com.lj.business.member.service.IGuidCardCustomerService;
import com.lj.business.member.service.IGuidCardService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.utils.WeiChatUtils;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 梅宏博
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class GuidCardServiceImpl implements IGuidCardService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GuidCardServiceImpl.class);
	

	@Resource
	private IGuidCardDao guidCardDao;
	
	@Resource
	private IGuidCardCustomerService guidCardCustomerService;
	
	@Resource
	private IGuidMemberService guidMemberService;
	
	@Resource
	private IManagerMemberService managerMemberService;
	
//	@Resource
//	private IShopService shopService;
	
	@Resource
	private IMerchantParamsService merchantParamsService;
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	private final String MERCHANT_SLOGAN = "merchant_slogan";
	
	private static String HEAD_ADDRESS_PRE;
	
	@PostConstruct
	private void init(){
		HEAD_ADDRESS_PRE = localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),
				SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
	}
	
	
	@Override
	public void addGuidCard(
			AddGuidCard addGuidCard) throws TsfaServiceException {
		logger.debug("addGuidCard(AddGuidCard addGuidCard={}) - start", addGuidCard); 

		AssertUtils.notNull(addGuidCard);
		try {
			GuidCard guidCard = new GuidCard();
			//add数据录入
			guidCard.setCode(addGuidCard.getCode());
			guidCard.setMemberNoGm(addGuidCard.getMemberNoGm());
			guidCard.setMemberNameGm(addGuidCard.getMemberNameGm());
			guidCard.setMerchantNo(addGuidCard.getMerchantNo());
			guidCard.setMerchantName(addGuidCard.getMerchantName());
			guidCard.setPosition(addGuidCard.getPosition());
			guidCard.setAddrInfo(addGuidCard.getAddrInfo());
			guidCard.setStatus(addGuidCard.getStatus());
			guidCard.setMobile(addGuidCard.getMobile());
			guidCard.setAge(addGuidCard.getAge());
			guidCard.setGender(addGuidCard.getGender());
			guidCard.setHeadAddress(addGuidCard.getHeadAddress());
			guidCard.setQcord(addGuidCard.getQcord());
			guidCard.setPageView(0);
			guidCard.setNumPraise(0);
			guidCard.setNumCollection(0);
			guidCard.setCreateId(addGuidCard.getMemberNoGm());
			guidCard.setCreateDate(new Date());
			guidCardDao.insertSelective(guidCard);
			logger.debug("addGuidCard(AddGuidCard) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增导购名片表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_ADD_ERROR,"新增导购名片表信息错误！",e);
		}
	}
	
	
	@Override
	public void delGuidCard(
			DelGuidCard delGuidCard) throws TsfaServiceException {
		logger.debug("delGuidCard(DelGuidCard delGuidCard={}) - start", delGuidCard); 

		AssertUtils.notNull(delGuidCard);
		AssertUtils.notNull(delGuidCard.getCode(),"Code不能为空！");
		try {
			guidCardDao.deleteByPrimaryKey(delGuidCard.getCode());
			logger.debug("delGuidCard(DelGuidCard) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除导购名片表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_DEL_ERROR,"删除导购名片表信息错误！",e);

		}
	}

	@Override
	public void updateGuidCard(
			UpdateGuidCard updateGuidCard)
			throws TsfaServiceException {
		logger.debug("updateGuidCard(UpdateGuidCard updateGuidCard={}) - start", updateGuidCard); 

		AssertUtils.notNull(updateGuidCard);
		AssertUtils.notNullAndEmpty(updateGuidCard.getCode(),"Code不能为空");
		try {
			GuidCard guidCard = new GuidCard();
			//update数据录入
			guidCard.setCode(updateGuidCard.getCode());
			guidCard.setMemberNoGm(updateGuidCard.getMemberNoGm());
			guidCard.setMemberNameGm(updateGuidCard.getMemberNameGm());
//			guidCard.setShopNo(updateGuidCard.getShopNo());
//			guidCard.setShopName(updateGuidCard.getShopName());
			guidCard.setMerchantNo(updateGuidCard.getMerchantNo());
			guidCard.setMerchantName(updateGuidCard.getMerchantName());
			guidCard.setPosition(updateGuidCard.getPosition());
			guidCard.setAddrInfo(updateGuidCard.getAddrInfo());
			guidCard.setStatus(updateGuidCard.getStatus());
			guidCard.setMobile(updateGuidCard.getMobile());
			guidCard.setAge(updateGuidCard.getAge());
			guidCard.setGender(updateGuidCard.getGender());
			guidCard.setHeadAddress(updateGuidCard.getHeadAddress());
			guidCard.setQcord(updateGuidCard.getQcord());
			guidCard.setPageView(updateGuidCard.getPageView());
			guidCard.setNumPraise(updateGuidCard.getNumPraise());
			guidCard.setNumCollection(updateGuidCard.getNumCollection());
			guidCard.setCreateId(updateGuidCard.getCreateId());
			guidCard.setCreateDate(updateGuidCard.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(guidCardDao.updateByPrimaryKeySelective(guidCard));
			logger.debug("updateGuidCard(UpdateGuidCard) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("导购名片表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_UPDATE_ERROR,"导购名片表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindGuidCardReturn findGuidCard(
			FindGuidCard findGuidCard) throws TsfaServiceException {
		logger.debug("findGuidCard(FindGuidCard findGuidCard={}) - start", findGuidCard); 

		AssertUtils.notNull(findGuidCard);
		AssertUtils.notAllNull(findGuidCard.getCode(),"Code不能为空");
		try {
			GuidCard guidCard = guidCardDao.selectByPrimaryKey(findGuidCard.getCode());
			if(guidCard == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.GUID_CARD_NOT_EXIST_ERROR,"导购名片表信息不存在");
			}
			FindGuidCardReturn findGuidCardReturn = new FindGuidCardReturn();
			//find数据录入
			findGuidCardReturn.setCode(guidCard.getCode());
			findGuidCardReturn.setMemberNoGm(guidCard.getMemberNoGm());
			findGuidCardReturn.setMemberNameGm(guidCard.getMemberNameGm());
//			findGuidCardReturn.setShopNo(guidCard.getShopNo());
//			findGuidCardReturn.setShopName(guidCard.getShopName());
			findGuidCardReturn.setMerchantNo(guidCard.getMerchantNo());
			findGuidCardReturn.setMerchantName(guidCard.getMerchantName());
			findGuidCardReturn.setPosition(guidCard.getPosition());
			findGuidCardReturn.setAddrInfo(guidCard.getAddrInfo());
			findGuidCardReturn.setStatus(guidCard.getStatus());
			findGuidCardReturn.setMobile(guidCard.getMobile());
			findGuidCardReturn.setAge(guidCard.getAge());
			findGuidCardReturn.setGender(guidCard.getGender());
			findGuidCardReturn.setHeadAddress(guidCard.getHeadAddress());
			findGuidCardReturn.setQcord(guidCard.getQcord());
			findGuidCardReturn.setPageView(guidCard.getPageView());
			findGuidCardReturn.setNumPraise(guidCard.getNumPraise());
			findGuidCardReturn.setNumCollection(guidCard.getNumCollection());
			findGuidCardReturn.setCreateId(guidCard.getCreateId());
			findGuidCardReturn.setCreateDate(guidCard.getCreateDate());
			
			logger.debug("findGuidCard(FindGuidCard) - end - return value={}", findGuidCardReturn); 
			return findGuidCardReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购名片表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_FIND_ERROR,"查找导购名片表信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindGuidCardPageReturn> findGuidCardPage(
			FindGuidCardPage findGuidCardPage)
			throws TsfaServiceException {
		logger.debug("findGuidCardPage(FindGuidCardPage findGuidCardPage={}) - start", findGuidCardPage); 

		AssertUtils.notNull(findGuidCardPage);
		List<FindGuidCardPageReturn> returnList;
		int count = 0;
		try {
			returnList = guidCardDao.findGuidCardPage(findGuidCardPage);
			count = guidCardDao.findGuidCardPageCount(findGuidCardPage);
		}  catch (Exception e) {
			logger.error("导购名片表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_FIND_PAGE_ERROR,"导购名片表信息不存在错误.！",e);
		}
		Page<FindGuidCardPageReturn> returnPage = new Page<FindGuidCardPageReturn>(returnList, count, findGuidCardPage);

		logger.debug("findGuidCardPage(FindGuidCardPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public FindGuidCardReturn findGuidCardByGm(FindGuidCard findGuidCard) {
		logger.debug("findGuidCardByGm(FindGuidCard findGuidCard={}) - start", findGuidCard); 
		GuidCard guidCard = guidCardDao.findGuidCardByGm(findGuidCard);
		FindGuidCardReturn findGuidCardReturn = new FindGuidCardReturn();
		
		FindGuidMember findGuidMember = new FindGuidMember();
		findGuidMember.setMemberNo(findGuidCard.getMemberNoGm());
		FindGuidMemberReturn guidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
		
		//查询口号
		FindMerchantParams findMerchantParams = new FindMerchantParams();
		findMerchantParams.setMerchantNo(guidMemberReturn.getMerchantNo());
		findMerchantParams.setGroupName(MERCHANT_SLOGAN);
		FindMerchantParamsReturn merchantParams = merchantParamsService.findMerchantParamsSelect(findMerchantParams);
		
		if (merchantParams != null)
			findGuidCardReturn.setSlogan(merchantParams.getSysParamValue());
		

		if (guidCard == null) {
			AddGuidCard addGuidCard = new AddGuidCard();
			addGuidCard.setCode(GUID.getPreUUID());
			addGuidCard.setMemberNoGm(guidMemberReturn.getMemberNo());
			addGuidCard.setMemberNameGm(guidMemberReturn.getMemberName());
			addGuidCard.setMerchantNo(guidMemberReturn.getMerchantNo());
			addGuidCard.setMerchantName(guidMemberReturn.getMerchantName());
//			addGuidCard.setPosition(managerMember == null ? "GUID" : managerMember.getMemberType());
//			addGuidCard.setAddrInfo(shopReturn.getAddrInfo());
			addGuidCard.setStatus(guidMemberReturn.getStatus());
			addGuidCard.setMobile(guidMemberReturn.getMobile());
			addGuidCard.setAge(guidMemberReturn.getAge());
			addGuidCard.setGender(guidMemberReturn.getGender());
			if (StringUtils.isNoneBlank(guidMemberReturn.getHeadAddress())) {
				addGuidCard.setHeadAddress(guidMemberReturn.getHeadAddress().startsWith("http") ?
						guidMemberReturn.getHeadAddress() : HEAD_ADDRESS_PRE + guidMemberReturn.getHeadAddress());
			}
			
			addGuidCard.setQcord(guidMemberReturn.getQcord());
			
			addGuidCard(addGuidCard);
			
			findGuidCardReturn.setCode(addGuidCard.getCode());
			findGuidCardReturn.setMemberNoGm(addGuidCard.getMemberNoGm());
			findGuidCardReturn.setMemberNameGm(addGuidCard.getMemberNameGm());
			findGuidCardReturn.setMerchantNo(addGuidCard.getMerchantNo());
			findGuidCardReturn.setMerchantName(addGuidCard.getMerchantName());
//			findGuidCardReturn.setPosition(managerMember == null ? "GUID" : managerMember.getMemberType());
			findGuidCardReturn.setAddrInfo(addGuidCard.getAddrInfo());
			findGuidCardReturn.setStatus(addGuidCard.getStatus());
			findGuidCardReturn.setMobile(addGuidCard.getMobile());
			findGuidCardReturn.setAge(addGuidCard.getAge());
			findGuidCardReturn.setGender(addGuidCard.getGender());
			findGuidCardReturn.setHeadAddress(addGuidCard.getHeadAddress());
			findGuidCardReturn.setQcord(addGuidCard.getQcord());
			findGuidCardReturn.setPageView(0);
			findGuidCardReturn.setNumPraise(0);
			findGuidCardReturn.setNumCollection(0);
			findGuidCardReturn.setIsSave("0");
			findGuidCardReturn.setIsPraise("0");
		} else {
			findGuidCardReturn.setCode(guidCard.getCode());
			findGuidCardReturn.setMemberNoGm(guidCard.getMemberNoGm());
			findGuidCardReturn.setMemberNameGm(guidCard.getMemberNameGm());
			findGuidCardReturn.setMerchantNo(guidCard.getMerchantNo());
			findGuidCardReturn.setMerchantName(guidCard.getMerchantName());
//			findGuidCardReturn.setPosition(managerMember == null ? "GUID" : managerMember.getMemberType());
			findGuidCardReturn.setAddrInfo(guidCard.getAddrInfo());
			findGuidCardReturn.setStatus(guidCard.getStatus());
			findGuidCardReturn.setMobile(guidCard.getMobile());
			findGuidCardReturn.setAge(guidCard.getAge());
			findGuidCardReturn.setGender(guidCard.getGender());
			findGuidCardReturn.setHeadAddress(guidCard.getHeadAddress());
			findGuidCardReturn.setQcord(guidCard.getQcord());
			findGuidCardReturn.setPageView(guidCard.getPageView());
			findGuidCardReturn.setNumPraise(guidCard.getNumPraise());
			findGuidCardReturn.setNumCollection(guidCard.getNumCollection());
			findGuidCardReturn.setIsSave("0");
			findGuidCardReturn.setIsPraise("0");
			if (findGuidCard.getSignature() != null) {
				FindGuidCardCustomer findGuidCardCustomer = new FindGuidCardCustomer();
				String openId = getOpenId(findGuidCard.getSignature());
				findGuidCardCustomer.setOpenId(openId);
				findGuidCardCustomer.setGuidCardCode(guidCard.getCode());
				List<FindGuidCardCustomerReturn> cardCustomer = guidCardCustomerService.findGuidCardCustomerSelect(findGuidCardCustomer);
				for (FindGuidCardCustomerReturn findGuidCardCustomerReturn : cardCustomer) {
					if (GuidCardCustType.COLLECTION.toString().equals(findGuidCardCustomerReturn.getType()))
						findGuidCardReturn.setIsSave("1");
					if (GuidCardCustType.PRAISE.toString().equals(findGuidCardCustomerReturn.getType()))
						findGuidCardReturn.setIsPraise("1");
				}
			}
			
		}
		logger.debug("findGuidCardByGm(FindGuidCard) - end - return value={}", findGuidCardReturn); 
		return findGuidCardReturn;
	}


	@Override
	public boolean addGuidCardNum(GuidCardAddNumDto guidCardAddNumDto) {
		logger.debug("addGuidCardNum(GuidCardAddNumDto guidCardAddNumDto={}) - start", guidCardAddNumDto); 
		int flag = guidCardCustomerService.addGuidCardCustomerByOpenId(guidCardAddNumDto);
		if (flag > 0) {
			switch (guidCardAddNumDto.getType()) {
			case "PAGE_VIEW":
				guidCardAddNumDto.setType("PAGE_VIEW");
				break;
			case "PRAISE":
				guidCardAddNumDto.setType("NUM_PRAISE");
				break;
			case "COLLECTION":
				guidCardAddNumDto.setType("NUM_COLLECTION");
				break;
			default:
				throw new TsfaServiceException(ErrorCode.GUID_CARD_TYPE_ERROR,"导购名片客户关联表类型错误.！");
			}
			int num = addGuidCardTypeNum(guidCardAddNumDto);
			if (num > 0) {
				return true;
			}
		}
		return false;
	}


	private int addGuidCardTypeNum(GuidCardAddNumDto guidCardAddNumDto) {
		return guidCardDao.addGuidCardTypeNum(guidCardAddNumDto);
	}


	@Override
	public List<FindGuidCardReturn> findGuidCards(
			GuidCardAddNumDto guidCardAddNumDto) {
		logger.debug("findGuidCards(GuidCardAddNumDto guidCardAddNumDto={}) - start", guidCardAddNumDto); 
		FindGuidCardCustomer findGuidCardCustomer = new FindGuidCardCustomer();
		String openId = getOpenId(guidCardAddNumDto.getSignature());
		findGuidCardCustomer.setOpenId(openId);
		findGuidCardCustomer.setType(guidCardAddNumDto.getType());
		List<FindGuidCardCustomerReturn> guidCardCustomers = guidCardCustomerService.findGuidCardCustomerSelect(findGuidCardCustomer);
		List<FindGuidCardReturn> findGuidCardReturns = new ArrayList<>();
		if (guidCardCustomers.size() > 0) {
			List<String> guidCardCodes = new ArrayList<>();
			for (FindGuidCardCustomerReturn findGuidCardCustomerReturn : guidCardCustomers) {
				guidCardCodes.add(findGuidCardCustomerReturn.getGuidCardCode());
			}
			findGuidCardReturns =  guidCardDao.findGuidCardByCode(guidCardCodes);
			return findGuidCardReturns;
		}
		return findGuidCardReturns;
	}
	
	@Override
	public void delGuidCardSave(GuidCardAddNumDto guidCardAddNumDto) {
		logger.debug("delGuidCardSave(GuidCardAddNumDto guidCardAddNumDto={}) - start", guidCardAddNumDto); 
		DelGuidCardCustomer delGuidCardCustomer = new DelGuidCardCustomer();
		delGuidCardCustomer.setGuidCardCode(guidCardAddNumDto.getGuidCardCode());
		delGuidCardCustomer.setOpenId(getOpenId(guidCardAddNumDto.getSignature()));
		delGuidCardCustomer.setType(GuidCardCustType.COLLECTION.toString());
		if (guidCardCustomerService.delGuidCardCustomerSelect(delGuidCardCustomer) > 0) {
			guidCardAddNumDto.setType("NUM_COLLECTION");
			guidCardDao.redGuidCardTypeNum(guidCardAddNumDto);
		}
	}


	@Override
	public String getSignature(String jsCode) {
		try {
			return WeiChatUtils.openIdEncrypt(WeiChatUtils.getOpenId(jsCode));
		} catch (Exception e) {
			logger.error("查询openId错误",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_FIND_OPENID_ERROR,"查找导购名片表信息信息错误！",e);
		}
	}
	
	@Override
	public void updateGuidCardByShop(UpdateGuidCard updateGuidCard) {
		logger.debug("updateGuidCardByShop(UpdateGuidCard updateGuidCard={}) - start", updateGuidCard); 
		guidCardDao.updateGuidCardByShop(updateGuidCard);
	}


	private String getOpenId(String signature){
		try {
			return WeiChatUtils.openIdDecrypt(signature);
		} catch (IOException e) {
			logger.error("查询openId错误",e);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_FIND_OPENID_ERROR,"查找导购名片表信息信息错误！",e);
		}
	}



}
