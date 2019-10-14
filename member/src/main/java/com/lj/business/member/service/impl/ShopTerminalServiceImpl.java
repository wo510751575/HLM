package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.common.ZkVersionConstants;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IFlowQcodeDao;
import com.lj.business.member.dao.IGmAssistantShopDao;
import com.lj.business.member.dao.IGuidMemberDao;
import com.lj.business.member.dao.IPersonMemberImDao;
import com.lj.business.member.dao.IShopTerminalDao;
import com.lj.business.member.domain.ShopTerminal;
import com.lj.business.member.dto.couponmultipush.FindShopTerminalByWxList;
import com.lj.business.member.dto.shopterminal.AddShopTerminal;
import com.lj.business.member.dto.shopterminal.AddShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalPage;
import com.lj.business.member.dto.shopterminal.FindShopTerminalPageReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.dto.shopterminal.PmFlowQcode;
import com.lj.business.member.dto.shopterminal.TerminalSign;
import com.lj.business.member.dto.shopterminal.TerminalSignReturn;
import com.lj.business.member.dto.shopterminal.UpdateShopTerminal;
import com.lj.business.member.dto.shopterminal.UpdateShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.UpdateTerminalWxPwd;
import com.lj.business.member.dto.shopterminal.UpdateWorkKey;
import com.lj.business.member.dto.terminalimstatus.AddTerminalImStatus;
import com.lj.business.member.emus.TerminalType;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.member.service.ITerminalImStatusService;
import com.lj.business.member.service.impl.job.JobLogFeedBackService;
import com.lj.business.supcon.dto.common.VersionInfoMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.cc.clientintf.IJob;
import com.lj.cc.enums.GroupName;
import com.lj.oms.utils.WxPwdEncryptUtils;

/**
 * 
 * 
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2017年11月1日
 */
@Service
public class ShopTerminalServiceImpl implements IShopTerminalService, IJob {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ShopTerminalServiceImpl.class);
	
	public static final String SHOP_TERMINAL_VERSION_CHECK_JOB = "shopTerminalVersionCheckJob";

	@Resource
	private IShopTerminalDao shopTerminalDao;
	@Resource
	private  ITerminalImStatusService terminalImStatusService;
//	@Resource
//	private  IShopService shopService;
	@Resource
	private  IGmAssistantShopDao gmAssistantShopDao;
	
	@Resource
	private IPersonMemberImDao personMemberImDao;
	
	@Resource
	private IFlowQcodeDao flowQcodeDao;
	
	@Resource
	private IGuidMemberDao guidMemberDao;
	
	@Resource
	private IMerchantParamsService merchantParamsService;

	
	@Resource
	private JobLogFeedBackService jobLogFeedBackService;
	
	@Autowired 
	ICommonService commonService;
	
    /** * 系统信息服务. */
//    @Autowired 
//	private ISystemInfoService systemInfo;
    
//    @Autowired 
//	private RedisCache redisCache;
	
	@Override
	public AddShopTerminalReturn addShopTerminal(AddShopTerminal addShopTerminal) throws TsfaServiceException {
		logger.debug("addShopTerminal(AddShopTerminal addShopTerminal={}) - start", addShopTerminal);

		AssertUtils.notNull(addShopTerminal);
		AssertUtils.notNullAndEmpty(addShopTerminal.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(addShopTerminal.getNoWx(), "绑定微信不能为空");
		AssertUtils.notNullAndEmpty(addShopTerminal.getWxNickname(), "微信昵称不能为空");
		try {
			
			FindShopTerminalPage findShopTerminalPage = new FindShopTerminalPage();
			findShopTerminalPage.setMerchantNo(addShopTerminal.getMerchantNo());
			// 终端终端数量
			int count = shopTerminalDao.findShopTerminalPageCount(findShopTerminalPage);
			
			ShopTerminal shopTerminal = new ShopTerminal();
			// add数据录入
			shopTerminal.setCode(GUID.generateByUUID());
			shopTerminal.setTerminalCode(StringUtils.lpad(String.valueOf(++count), "0", 4));
			shopTerminal.setMerchantNo(addShopTerminal.getMerchantNo());
			shopTerminal.setMerchantName(addShopTerminal.getMerchantName());
//			shopTerminal.setShopNo(addShopTerminal.getShopNo());
//			shopTerminal.setShopName(addShopTerminal.getShopName());
			shopTerminal.setNoWx(addShopTerminal.getNoWx());
			shopTerminal.setWxNickname(addShopTerminal.getWxNickname());
			shopTerminal.setHeadAddress(addShopTerminal.getHeadAddress());
			shopTerminal.setQcord(addShopTerminal.getQcord());
			shopTerminal.setSex(addShopTerminal.getSex());
			shopTerminal.setImei(addShopTerminal.getImei());
			shopTerminal.setStatus(addShopTerminal.getStatus());
			shopTerminal.setUploadFriendsFlag(addShopTerminal.getUploadFriendsFlag());
			shopTerminal.setCreateId(addShopTerminal.getCreateId());
			shopTerminal.setCreateDate(new Date());
			shopTerminal.setRemark(addShopTerminal.getRemark());
			shopTerminal.setRemark2(addShopTerminal.getRemark2());
			shopTerminal.setRemark3(addShopTerminal.getRemark3());
			shopTerminal.setRemark4(addShopTerminal.getRemark4());
			shopTerminal.setUsernameWx(addShopTerminal.getUsernameWx());
			shopTerminal.setAlias(addShopTerminal.getAlias());
			shopTerminal.setNickname(addShopTerminal.getNickname());
			shopTerminal.setHeadurl(addShopTerminal.getHeadurl());
			shopTerminalDao.insertSelective(shopTerminal);
			
			//保存终端终端信息save后需add 终端IM状态
			AddTerminalImStatus addTerminalImStatus=new AddTerminalImStatus();
			addTerminalImStatus.setMerchantNo(addShopTerminal.getMerchantNo());
			addTerminalImStatus.setMerchantName(addShopTerminal.getMerchantName());
			addTerminalImStatus.setCreateId(addShopTerminal.getCreateId());
//			addTerminalImStatus.setShopNo(addShopTerminal.getShopNo());
//			addTerminalImStatus.setShopName(addShopTerminal.getShopName());
			addTerminalImStatus.setTerminalType(TerminalType.ZK.name());//ZK中控
			addTerminalImStatus.setTerminalCode(shopTerminal.getCode());
			addTerminalImStatus.setOnlineFlag(0);//0:离线
			addTerminalImStatus.setImei(addShopTerminal.getImei());//手机串号
			addTerminalImStatus.setWxNickname(shopTerminal.getWxNickname());
			addTerminalImStatus.setNoWx(shopTerminal.getNoWx());
			terminalImStatusService.addTerminalImStatus(addTerminalImStatus);

			AddShopTerminalReturn addShopTerminalReturn = new AddShopTerminalReturn();
			addShopTerminalReturn.setCode(shopTerminal.getCode());
			addShopTerminalReturn.setTerminalCode(shopTerminal.getTerminalCode());
			logger.debug("addShopTerminal(AddShopTerminal) - end - return value={}", addShopTerminalReturn);
			return addShopTerminalReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增终端终端信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_ADD_ERROR, "新增终端终端信息错误！", e);
		}
	}

	@Override
	public UpdateShopTerminalReturn updateShopTerminal(UpdateShopTerminal updateShopTerminal) throws TsfaServiceException {
		logger.debug("updateShopTerminal(UpdateShopTerminal updateShopTerminal={}) - start", updateShopTerminal); 

		AssertUtils.notNull(updateShopTerminal);
		AssertUtils.notNullAndEmpty(updateShopTerminal.getCode(), "code不能为空");
		try {
			//如果本次修改二维码有变更,则更新该终端所有导购的二维码
			/* 新版门店已改为从终端上直接获取二维码，不需要更新了。DZP 2019-05-31
			 * ShopTerminal s = shopTerminalDao.selectByPrimaryKey(updateShopTerminal.getCode());
			if(StringUtils.isNotEmpty(updateShopTerminal.getQcord()) && !updateShopTerminal.getQcord().equals(s.getQcord())){
				UpdateGuidMember updateGuidMember=new UpdateGuidMember();
				updateGuidMember.setMerchantNo(updateShopTerminal.getMerchantNo());
				updateGuidMember.setNoWx(updateShopTerminal.getNoWx());
				updateGuidMember.setQcord(updateShopTerminal.getQcord());
				guidMemberDao.updateQcordByNoWx(updateGuidMember);
				logger.debug("updateQcordByNoWx(updateGuidMember) {}", "更新终端导购二维码成功!");
			}*/
			
			ShopTerminal shopTerminal = new ShopTerminal();
			// update数据录入
			shopTerminal.setCode(updateShopTerminal.getCode());
			shopTerminal.setTerminalCode(updateShopTerminal.getTerminalCode());
			shopTerminal.setMerchantNo(updateShopTerminal.getMerchantNo());
			shopTerminal.setMerchantName(updateShopTerminal.getMerchantName());
			shopTerminal.setShopName(updateShopTerminal.getShopName());
			shopTerminal.setNoWx(updateShopTerminal.getNoWx());
			shopTerminal.setWxNickname(updateShopTerminal.getWxNickname());
			shopTerminal.setHeadAddress(updateShopTerminal.getHeadAddress());
			shopTerminal.setQcord(updateShopTerminal.getQcord());
			shopTerminal.setSex(updateShopTerminal.getSex());
			shopTerminal.setImei(updateShopTerminal.getImei());
			shopTerminal.setStatus(updateShopTerminal.getStatus());
			shopTerminal.setUploadFriendsFlag(updateShopTerminal.getUploadFriendsFlag());
			shopTerminal.setCreateId(updateShopTerminal.getCreateId());
			shopTerminal.setCreateDate(updateShopTerminal.getCreateDate());
			shopTerminal.setRemark(updateShopTerminal.getRemark());
			shopTerminal.setRemark2(updateShopTerminal.getRemark2());
			shopTerminal.setRemark3(updateShopTerminal.getRemark3());
			shopTerminal.setRemark4(updateShopTerminal.getRemark4());
			shopTerminal.setAlias(updateShopTerminal.getAlias());
			shopTerminal.setHeadurl(updateShopTerminal.getHeadurl());
			shopTerminal.setNickname(updateShopTerminal.getNickname());
			AssertUtils.notUpdateMoreThanOne(shopTerminalDao.updateByPrimaryKeySelective(shopTerminal));
			UpdateShopTerminalReturn updateShopTerminalReturn = new UpdateShopTerminalReturn();

			logger.debug("updateShopTerminal(UpdateShopTerminal) - end - return value={}", updateShopTerminalReturn); 
			return updateShopTerminalReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("终端终端信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_UPDATE_ERROR, "终端终端信息更新信息错误！", e);
		}
	}

	@Override
	public FindShopTerminalReturn findShopTerminal(FindShopTerminal findShopTerminal) throws TsfaServiceException {
		logger.debug("findShopTerminal(FindShopTerminal findShopTerminal={}) - start", findShopTerminal); 

		AssertUtils.notNull(findShopTerminal);
		AssertUtils.notNullAndEmpty(findShopTerminal.getCode(), "code不能为空");
		try {
			ShopTerminal shopTerminal = shopTerminalDao.selectByPrimaryKey(findShopTerminal.getCode());
			if (shopTerminal == null) {
				throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_EXIST_ERROR, "终端终端信息不存在");
			}
			FindShopTerminalReturn findShopTerminalReturn = new FindShopTerminalReturn();
			// find数据录入
			findShopTerminalReturn.setCode(shopTerminal.getCode());
			findShopTerminalReturn.setTerminalCode(shopTerminal.getTerminalCode());
			findShopTerminalReturn.setMerchantNo(shopTerminal.getMerchantNo());
			findShopTerminalReturn.setMerchantName(shopTerminal.getMerchantName());
			findShopTerminalReturn.setShopName(shopTerminal.getShopName());
			findShopTerminalReturn.setNoWx(shopTerminal.getNoWx());
			findShopTerminalReturn.setUsernameWx(shopTerminal.getUsernameWx());
			findShopTerminalReturn.setWxNickname(shopTerminal.getWxNickname());
			findShopTerminalReturn.setHeadAddress(shopTerminal.getHeadAddress());
			findShopTerminalReturn.setQcord(shopTerminal.getQcord());
			findShopTerminalReturn.setSex(shopTerminal.getSex());
			findShopTerminalReturn.setWxVersionCode(shopTerminal.getWxVersionCode());
			findShopTerminalReturn.setWxVersionName(shopTerminal.getWxVersionName());
			findShopTerminalReturn.setWxBalance(shopTerminal.getWxBalance());
			if(!StringUtils.isEmpty(shopTerminal.getWxPwd())) {
				findShopTerminalReturn.setWxPwd("true");//密码不直接返回,使用true标识
			}
			findShopTerminalReturn.setImei(shopTerminal.getImei());
			findShopTerminalReturn.setStatus(shopTerminal.getStatus());
			findShopTerminalReturn.setVersionCode(shopTerminal.getVersionCode());
			findShopTerminalReturn.setVersionName(shopTerminal.getVersionName());
			findShopTerminalReturn.setUploadFriendsFlag(shopTerminal.getUploadFriendsFlag());
			findShopTerminalReturn.setCreateId(shopTerminal.getCreateId());
			findShopTerminalReturn.setCreateDate(shopTerminal.getCreateDate());
			findShopTerminalReturn.setRemark(shopTerminal.getRemark());
			findShopTerminalReturn.setRemark2(shopTerminal.getRemark2());
			findShopTerminalReturn.setRemark3(shopTerminal.getRemark3());
			findShopTerminalReturn.setRemark4(shopTerminal.getRemark4());
			findShopTerminalReturn.setAlias(shopTerminal.getAlias());
			findShopTerminalReturn.setHeadurl(shopTerminal.getHeadurl());
			findShopTerminalReturn.setNickname(shopTerminal.getNickname());

			logger.debug("findShopTerminal(FindShopTerminal) - end - return value={}", findShopTerminalReturn); 
			return findShopTerminalReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找终端终端信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_ERROR, "查找终端终端信息信息错误！", e);
		}

	}
	
	/*@Override
<<<<<<< .working
	public FindShopTerminalReturn findShopTerminalByImei(String imei) throws TsfaServiceException {
		logger.debug("findShopTerminalByImei(String imei={}) - start", imei); //$NON-NLS-1$
		
		AssertUtils.notNullAndEmpty(imei, "imei不能为空");
		try {
			ShopTerminal shopTerminal = shopTerminalDao.selectByImei(imei);
			FindShopTerminalReturn findShopTerminalReturn = null;
			if (shopTerminal != null) {
				findShopTerminalReturn = new FindShopTerminalReturn();
				findShopTerminalReturn.setCode(shopTerminal.getCode());
				findShopTerminalReturn.setTerminalCode(shopTerminal.getTerminalCode());
				findShopTerminalReturn.setMerchantNo(shopTerminal.getMerchantNo());
				findShopTerminalReturn.setMerchantName(shopTerminal.getMerchantName());
				findShopTerminalReturn.setShopNo(shopTerminal.getShopNo());
				findShopTerminalReturn.setShopName(shopTerminal.getShopName());
				findShopTerminalReturn.setNoWx(shopTerminal.getNoWx());
				findShopTerminalReturn.setUsernameWx(shopTerminal.getUsernameWx());
				findShopTerminalReturn.setWxNickname(shopTerminal.getWxNickname());
				findShopTerminalReturn.setHeadAddress(shopTerminal.getHeadAddress());
				findShopTerminalReturn.setQcord(shopTerminal.getQcord());
				findShopTerminalReturn.setSex(shopTerminal.getSex());
				findShopTerminalReturn.setWxVersionCode(shopTerminal.getWxVersionCode());
				findShopTerminalReturn.setWxVersionName(shopTerminal.getWxVersionName());
				findShopTerminalReturn.setWxBalance(shopTerminal.getWxBalance());
				findShopTerminalReturn.setImei(shopTerminal.getImei());
				findShopTerminalReturn.setStatus(shopTerminal.getStatus());
				findShopTerminalReturn.setVersionCode(shopTerminal.getVersionCode());
				findShopTerminalReturn.setVersionName(shopTerminal.getVersionName());
				findShopTerminalReturn.setUploadFriendsFlag(shopTerminal.getUploadFriendsFlag());
				findShopTerminalReturn.setCreateId(shopTerminal.getCreateId());
				findShopTerminalReturn.setCreateDate(shopTerminal.getCreateDate());
				findShopTerminalReturn.setRemark(shopTerminal.getRemark());
				findShopTerminalReturn.setRemark2(shopTerminal.getRemark2());
				findShopTerminalReturn.setRemark3(shopTerminal.getRemark3());
				findShopTerminalReturn.setRemark4(shopTerminal.getRemark4());
				findShopTerminalReturn.setAlias(shopTerminal.getAlias());
				findShopTerminalReturn.setHeadurl(shopTerminal.getHeadurl());
				findShopTerminalReturn.setNickname(shopTerminal.getNickname());
			}
			
			logger.debug("findShopTerminalByImei(String) - end - return value={}", findShopTerminalReturn); //$NON-NLS-1$
			return findShopTerminalReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找门店终端信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_ERROR, "查找门店终端信息信息错误！", e);
		}
	}*/
	
	
	
	
	@Override
	public FindShopTerminalReturn findShopTerminalByWx(String noWx) throws TsfaServiceException {
		logger.debug("findShopTerminalByWx(String noWx={}) - start", noWx); 
		
		AssertUtils.notNullAndEmpty(noWx, "noWx不能为空");
		try {
			ShopTerminal shopTerminal = shopTerminalDao.selectByWx(noWx);
			FindShopTerminalReturn findShopTerminalReturn = null;
			if (shopTerminal != null) {
				findShopTerminalReturn = new FindShopTerminalReturn();
				findShopTerminalReturn.setCode(shopTerminal.getCode());
				findShopTerminalReturn.setTerminalCode(shopTerminal.getTerminalCode());
				findShopTerminalReturn.setMerchantNo(shopTerminal.getMerchantNo());
				findShopTerminalReturn.setMerchantName(shopTerminal.getMerchantName());
				findShopTerminalReturn.setNoWx(shopTerminal.getNoWx());
				findShopTerminalReturn.setUsernameWx(shopTerminal.getUsernameWx());
				findShopTerminalReturn.setWxNickname(shopTerminal.getWxNickname());
				findShopTerminalReturn.setHeadAddress(shopTerminal.getHeadAddress());
				findShopTerminalReturn.setQcord(shopTerminal.getQcord());
				findShopTerminalReturn.setSex(shopTerminal.getSex());
				findShopTerminalReturn.setWxVersionCode(shopTerminal.getWxVersionCode());
				findShopTerminalReturn.setWxVersionName(shopTerminal.getWxVersionName());
				findShopTerminalReturn.setWxPwd(shopTerminal.getWxPwd());
				findShopTerminalReturn.setWxBalance(shopTerminal.getWxBalance());
				findShopTerminalReturn.setImei(shopTerminal.getImei());
				findShopTerminalReturn.setStatus(shopTerminal.getStatus());
				findShopTerminalReturn.setVersionCode(shopTerminal.getVersionCode());
				findShopTerminalReturn.setVersionName(shopTerminal.getVersionName());
				findShopTerminalReturn.setUploadFriendsFlag(shopTerminal.getUploadFriendsFlag());
				findShopTerminalReturn.setCreateId(shopTerminal.getCreateId());
				findShopTerminalReturn.setCreateDate(shopTerminal.getCreateDate());
				findShopTerminalReturn.setRemark(shopTerminal.getRemark());
				findShopTerminalReturn.setRemark2(shopTerminal.getRemark2());
				findShopTerminalReturn.setRemark3(shopTerminal.getRemark3());
				findShopTerminalReturn.setRemark4(shopTerminal.getRemark4());
				findShopTerminalReturn.setAlias(shopTerminal.getAlias());
				findShopTerminalReturn.setHeadurl(shopTerminal.getHeadurl());
				findShopTerminalReturn.setNickname(shopTerminal.getNickname());
				findShopTerminalReturn.setWorkKey(shopTerminal.getWorkKey());
			}
			
			logger.debug("findShopTerminalByWx(String) - end - return value={}", findShopTerminalReturn); 
			return findShopTerminalReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找终端终端信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_ERROR, "查找终端终端信息信息错误！", e);
		}
	}

	@Override
	public Page<FindShopTerminalPageReturn> findShopTerminalPage(FindShopTerminalPage findShopTerminalPage) throws TsfaServiceException {
		logger.debug("findShopTerminalPage(FindShopTerminalPage findShopTerminalPage={}) - start", findShopTerminalPage); 

		AssertUtils.notNull(findShopTerminalPage);
		List<FindShopTerminalPageReturn> returnList = new ArrayList<>();
		int count = 0;
		try {
			count = shopTerminalDao.findShopTerminalPageCount(findShopTerminalPage);
			if(count>0) {
				returnList = shopTerminalDao.findShopTerminalPage(findShopTerminalPage);
			}
			
		} catch (Exception e) {
			logger.error("终端终端信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_PAGE_ERROR, "终端终端信息不存在错误.！", e);
		}
		Page<FindShopTerminalPageReturn> returnPage = new Page<FindShopTerminalPageReturn>(returnList, count, findShopTerminalPage);

		logger.debug("findShopTerminalPage(FindShopTerminalPage) - end - return value={}", returnPage); 
		return returnPage;
	}

	@Override
	public List<String> findAllNoWx() {
		return shopTerminalDao.findAllNoWx();
	}
	
	@Override
	public List<String> findAllImei() {
		return shopTerminalDao.findAllImei();
	}

	@Override
	public int hasNoWx(String noWx) {
		return shopTerminalDao.hasNoWx(noWx);
	}
	
	@Override
	public int hasImei(String imei) {
		return shopTerminalDao.hasImei(imei);
	}


	@Override
	public List<FindShopTidFromWebReturn> findShopTerminalFromWeb(FindShopTidFromWeb findShopTidFromWeb) {
		logger.debug("findShopTerminalFromWeb(FindShopTidFromWeb findShopTidFromWeb={}) - start", findShopTidFromWeb); 

		AssertUtils.notNull(findShopTidFromWeb); 
		AssertUtils.notNullAndEmpty(findShopTidFromWeb.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(findShopTidFromWeb.getAssistantNo(), "助手编号不能为空");
		
		List<FindShopTidFromWebReturn> returnList = null;
		try {
			List<String> queryNoWxList = null;		// 待查询终端微信列表
			// 1、如果客户分组pmTypeCode不为空，则查询拥有指定分组客户的导购绑定微信列表，并取其为待查询终端微信列表
			if(StringUtils.isNotEmpty(findShopTidFromWeb.getPmTypeCode())) {
				queryNoWxList = personMemberImDao.findNoWxByPmTypeCode(findShopTidFromWeb.getPmTypeCode());
				// 没有包含指定分组的导购绑定微信，则返回空的结果
				if(queryNoWxList == null || queryNoWxList.isEmpty()) {
					returnList = new ArrayList<FindShopTidFromWebReturn>();
					logger.debug("findShopTerminalFromWeb(FindShopTidFromWeb) - end - return value={}", returnList); 
					return returnList;
				}
			}
			
			// 2、搜索关键字searchWords不为空，则分别按社交名称和导购名称查询终端微信并取其合集,即待查询终端微信列表
			if(StringUtils.isNotEmpty(findShopTidFromWeb.getSearchWords())) {
				List<String> noWxByTidList = shopTerminalDao.findNoWxByWxNickname(findShopTidFromWeb.getMerchantNo(), findShopTidFromWeb.getSearchWords());
				List<String> noWxByGmList = guidMemberDao.findNoWxByMemberName(findShopTidFromWeb.getMerchantNo(), findShopTidFromWeb.getSearchWords());
				// 先取两个列表的合集
				noWxByTidList.removeAll(noWxByGmList);
				noWxByGmList.addAll(noWxByTidList);
				// 合集为空，则返回空的结果
				if(noWxByGmList == null || noWxByGmList.isEmpty()) {
					returnList = new ArrayList<FindShopTidFromWebReturn>();
					logger.debug("findShopTerminalFromWeb(FindShopTidFromWeb) - end - return value={}", returnList); 
					return returnList;
				}
				if(queryNoWxList == null) {
					queryNoWxList = noWxByGmList;
				} else {
					queryNoWxList.retainAll(noWxByGmList);
				}
			}
			
			returnList = shopTerminalDao.findShopTerminalFromWeb(findShopTidFromWeb, queryNoWxList);
			
			// 获取终端在线状态
			if(findShopTidFromWeb.isQueryOnlineBool() && returnList != null && !returnList.isEmpty()) {
				for (FindShopTidFromWebReturn tid : returnList) {
					ICommonService basic = commonService.getHessianCommonService(tid.getNoWx());
					tid.setOnlineFlag(basic.getZkTerminalStatus(tid.getNoWx()) ? 1 : 0);
				}
			}
		} catch (Exception e) {
			logger.error("导购助手管理的终端列表查询错误", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_PAGE_ERROR, "导购助手管理的终端列表查询错误！", e);
		}

		logger.debug("findShopTerminalFromWeb(FindShopTidFromWeb) - end - return value={}", returnList); 
		return returnList;
	}
	

	
	@Override
    public List<FindShopTidFromWebReturn> findShopTerminalForecastName(FindShopTidFromWeb findShopTidFromWeb) {
        logger.debug("findShopTerminalForecastName(FindShopTidFromWeb findShopTidFromWeb={}) - start", findShopTidFromWeb); 

        AssertUtils.notNull(findShopTidFromWeb); 
        AssertUtils.notNullAndEmpty(findShopTidFromWeb.getMerchantNo(), "商户编号不能为空");
        AssertUtils.notNullAndEmpty(findShopTidFromWeb.getAssistantNo(), "助手编号不能为空");
        
        List<FindShopTidFromWebReturn> returnList = null;
        try {
            List<String> queryNoWxList = null;      // 待查询终端微信列表
            // 1、如果客户分组pmTypeCode不为空，则查询拥有指定分组客户的导购绑定微信列表，并取其为待查询终端微信列表
            if(StringUtils.isNotEmpty(findShopTidFromWeb.getPmTypeCode())) {
                queryNoWxList = personMemberImDao.findNoWxByPmTypeCode(findShopTidFromWeb.getPmTypeCode());
                // 没有包含指定分组的导购绑定微信，则返回空的结果
                if(queryNoWxList == null || queryNoWxList.isEmpty()) {
                    returnList = new ArrayList<FindShopTidFromWebReturn>();
                    logger.debug("findShopTerminalFromWeb(FindShopTidFromWeb) - end - return value={}", returnList); 
                    return returnList;
                }
            }
            
            // 2、搜索关键字searchWords不为空，则分别按社交名称和导购名称查询终端微信并取其合集,即待查询终端微信列表
            if(StringUtils.isNotEmpty(findShopTidFromWeb.getSearchWords())) {
                List<String> noWxByTidList = shopTerminalDao.findNoWxByWxNickname(findShopTidFromWeb.getMerchantNo(), findShopTidFromWeb.getSearchWords());
                List<String> noWxByGmList = guidMemberDao.findNoWxByMemberName(findShopTidFromWeb.getMerchantNo(), findShopTidFromWeb.getSearchWords());
                // 先取两个列表的合集
                noWxByTidList.removeAll(noWxByGmList);
                noWxByGmList.addAll(noWxByTidList);
                // 合集为空，则返回空的结果
                if(noWxByGmList == null || noWxByGmList.isEmpty()) {
                    returnList = new ArrayList<FindShopTidFromWebReturn>();
                    logger.debug("findShopTerminalFromWeb(FindShopTidFromWeb) - end - return value={}", returnList); 
                    return returnList;
                }
                if(queryNoWxList == null) {
                    queryNoWxList = noWxByGmList;
                } else {
                    queryNoWxList.retainAll(noWxByGmList);
                }
            }
            
            //筛选有预报名的终端微信
            List<String> forecastNameNoWxList = shopTerminalDao.findShopTerminalForecastName(queryNoWxList);
            if (CollectionUtils.isEmpty(forecastNameNoWxList)) {
                returnList = new ArrayList<FindShopTidFromWebReturn>();
                logger.debug("findShopTerminalForecastName-从[{}]筛选出有预报名的终端微信", queryNoWxList);
                logger.debug("findShopTerminalForecastName(FindShopTidFromWeb) - end - return value={}", returnList); 
                return returnList;
            }
            returnList = shopTerminalDao.findShopTerminalFromWeb(findShopTidFromWeb, forecastNameNoWxList);
            
            // 获取终端在线状态
            if(findShopTidFromWeb.isQueryOnlineBool() && returnList != null && !returnList.isEmpty()) {
                List<String> noWxList = new ArrayList<String>();
                Map<String, Boolean> statusMap = new HashMap<String, Boolean>();
                for (FindShopTidFromWebReturn tid : returnList) {
                    noWxList.add(tid.getNoWx());
                    ICommonService basic = commonService.getHessianCommonService(tid.getNoWx());
                    statusMap.put(tid.getNoWx(), basic.getZkTerminalStatus(tid.getNoWx()));
                }
               // Map<String, Boolean> statusMap = commonService.getZkTerminalStatusList(noWxList);
                for (FindShopTidFromWebReturn tid : returnList) {
                    tid.setOnlineFlag(statusMap.get(tid.getNoWx()) != null && statusMap.get(tid.getNoWx()) ? 1 : 0);
                }
            }
        } catch (Exception e) {
            logger.error("预报名-导购助手管理的终端列表查询错误", e);
            throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_PAGE_ERROR, "预报名-导购助手管理的终端列表查询错误！", e);
        }

        logger.debug("findShopTerminalForecastName(FindShopTidFromWeb) - end - return value={}", returnList); 
        return returnList;
    }

	@Override
	public List<FindShopTerminalReturn> findShopTerminalSelect(FindShopTerminal findShopTerminal) throws TsfaServiceException{
		logger.debug("findShopTerminalSelect(FindShopTerminal findShopTerminal={}) - start", findShopTerminal); 
		List<FindShopTerminalReturn> findShopTerminalReturn = shopTerminalDao.findShopTerminalSelect(findShopTerminal);
		logger.debug("findShopTerminalSelect(findShopTerminal) - end - return value={}", findShopTerminalReturn); 
		return findShopTerminalReturn;
	}

	@Override
	public List<FindShopTerminalReturn> findShopTerminalByShopNo(String shopNo) throws TsfaServiceException {
		logger.debug("findShopTerminalByShopNo(String shopNo={}) - start", shopNo); 
		
//		AssertUtils.notNullAndEmpty(shopNo, "终端编号不能为空");
		List<FindShopTerminalReturn> returnList = null;
		try {
			returnList=shopTerminalDao.findShopTerminal();
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找终端终端信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_ERROR, "查找终端终端信息信息错误！", e);
		}
		logger.debug("findShopTerminalByShopNo(String) - end - return value={}", returnList); 
		return returnList;
	}
	
	
	public FindShopTerminalReturn findShopTerminalNormal(String noWx){
		return shopTerminalDao.findShopTerminalNormal(noWx);
	}
	
	@Override
	public List<String> findShopTerminalByWxList(FindShopTerminalByWxList findShopTerminalByWxList){
        return shopTerminalDao.findShopTerminalByWxList(findShopTerminalByWxList);
    }
	
	public int updateShopTerminalShopName(UpdateShopTerminal updateShopTerminal){
		ShopTerminal shopTerminal =new ShopTerminal();
//		shopTerminal.setShopName(updateShopTerminal.getShopName());
//		shopTerminal.setShopNo(updateShopTerminal.getShopNo());
	    return shopTerminalDao.updateShopTerminalShopName(shopTerminal);
	}

	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IShopTerminalService#updateVersion(java.lang.String)
	 */
	@Override
	public boolean checkAndUpdateVersion(String code) {
		logger.debug("checkAndUpdateVersion(String code={}) - start", code); 
		
		// 查询终端终端
		ShopTerminal shopTerminal = shopTerminalDao.selectByPrimaryKey(code);
		if(shopTerminal == null) {
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_EXIST_ERROR, "非法终端：终端不存在");
		}
		if(shopTerminal.getStatus() != 1) {	// 正常状态
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_NORMAL_ERROR, "终端状态非法");
		}
		if(StringUtils.isEmpty(shopTerminal.getNoWx())) {	// 必须绑定微信号
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_BIND_WX, "终端没有绑定微信");
		}
		
		// 查询商户配置的中控客户端版本控制参数
		FindMerchantParams findMerchantParams = new FindMerchantParams();
		findMerchantParams.setMerchantNo(shopTerminal.getMerchantNo());
		findMerchantParams.setGroupName(GroupName.ZKVERSION.name());
		Map<String, String> merchantParamsMap = merchantParamsService.findMerchantParamsByGroup(findMerchantParams);
		if(merchantParamsMap == null || merchantParamsMap.isEmpty()) {
			logger.error("商户({})没有配置中控客户端版本控制参数", shopTerminal.getMerchantName());
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_UPDATE_VERSION_ERROR, "商户没有配置中控客户端版本参数");
		}
		
		/*if(!this.checkAndUpdateVersion(shopTerminal, merchantParamsMap)) {
			logger.error("终端[{}]当前已是最新版本", shopTerminal.getTerminalCode());
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_UPDATE_VERSION_ERROR, "终端当前已是最新版本");
		}*/
		
		boolean flag = this.checkAndUpdateVersion(shopTerminal, merchantParamsMap);
		logger.debug("checkAndUpdateVersion(String) - end - return"); 
		return flag;
	}
	
	@Override
	public boolean validNoWx(FindShopTerminalPage findShopTerminalPage) {
		logger.debug("validNoWx(FindShopTerminalPage findShopTerminalPage={}) - start", findShopTerminalPage); 
		AssertUtils.notNull(findShopTerminalPage); 
		AssertUtils.notNullAndEmpty(findShopTerminalPage.getNoWx(), "微信号不能为空");
		boolean flag = false;
		Integer count=0;
		try {
			findShopTerminalPage.setStatus(1);//正常状态
			count = shopTerminalDao.findShopTerminalPageCount(findShopTerminalPage);
			if(count>0){
				flag=Boolean.TRUE;
			}
		} catch(TsfaServiceException e) {
			logger.error("校验工作微信号是否为终端有效微信号失败", e);
			throw e;
		} catch(Exception e) {
			logger.error("校验工作微信号是否为终端有效微信号失败", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOWX_NOT_VALID_ERROR, "校验工作微信号是否为终端有效微信号失败");
		}
		
		logger.debug("validNoWx(FindShopTerminalPage) - end - return count={}",count); 
		return flag;
	}

	/**
	 * 终端终端中控APP版本更新任务
	 */
	@Override
	public void runJob() {
		logger.info("终端终端中控APP版本更新任务 -- start");
		jobLogFeedBackService.pushLog(SHOP_TERMINAL_VERSION_CHECK_JOB, "终端版本检查任务 - 开始", "begin");
		
		List<ShopTerminal> stList = null;
		try {
			stList = shopTerminalDao.findAllShopTerminalByCheckVersion();	// 按商户编号排序
		} catch(Exception e) {
			logger.error("查询所有待检查版本的有效终端异常", e);
			logger.info("终端终端中控APP版本更新任务 -- end");
			return;
		}
		if(stList == null || stList.isEmpty()) {
			logger.info("没有找到待检查版本的有效终端");
			jobLogFeedBackService.pushLog(SHOP_TERMINAL_VERSION_CHECK_JOB, "终端版本检查任务 - 结束", "end");
			logger.info("终端终端中控APP版本更新任务 -- end");
			return;
		}
		jobLogFeedBackService.pushLog(SHOP_TERMINAL_VERSION_CHECK_JOB, "待检查终端合计：" + stList.size() + "个", "total");
		
		int updateCount = 0;		// 需更新版本终端数
		int notUpdateCount = 0;		// 需更新版本终端数
		int successCount = 0;		// 检查成功终端数
		
		// 循环检查
		Map<String, String> merchantParamsMap = null;	// 上一个终端所属商户的商户参数
		String merchantNo = null;	// 上一个终端所属商户编号
		FindMerchantParams findMerchantParams = new FindMerchantParams();
		findMerchantParams.setGroupName(GroupName.ZKVERSION.name());
		for(ShopTerminal shopTerminal : stList) {
			// 与上一个商户不一样，则重置商户编号与商户参数
			if(!shopTerminal.getMerchantNo().equals(merchantNo)) {
				merchantNo = shopTerminal.getMerchantNo();
				// 查询商户配置的中控客户端版本控制参数
				findMerchantParams.setMerchantNo(merchantNo);
				merchantParamsMap = merchantParamsService.findMerchantParamsByGroup(findMerchantParams);
				if(merchantParamsMap == null || merchantParamsMap.isEmpty()) {
					String remark = "商户没有配置中控版本控制参数：" + shopTerminal.getMerchantNo() + " - " + shopTerminal.getMerchantName();
					logger.warn(remark);
					jobLogFeedBackService.pushLog(SHOP_TERMINAL_VERSION_CHECK_JOB, remark, "warn");
					continue;
				}
			}
			// 重置后的商户参数依然为空，不检查终端版本
			if(merchantParamsMap == null || merchantParamsMap.isEmpty()) {
				continue;
			}
			
			// 检查并更新
			try {
				if(this.checkAndUpdateVersion(shopTerminal, merchantParamsMap)) {	// 有更新
					updateCount++;
				} else {	// 没有更新
					notUpdateCount++;
				}
				successCount++;
			} catch(TsfaServiceException e) {
				jobLogFeedBackService.pushLog(SHOP_TERMINAL_VERSION_CHECK_JOB, "检查终端版本失败：" + shopTerminal.getTerminalCode(), "error");
			}
		}

		String remark = "终端版本需更新" + updateCount + "个, 已是最新版本" + notUpdateCount + "个";
		logger.info(remark);
		jobLogFeedBackService.pushLog(SHOP_TERMINAL_VERSION_CHECK_JOB, remark, "total");
		
		remark = "终端版本检查成功" + successCount + "个, 失败" + (stList.size() - successCount) + "个";
		logger.info(remark);
		jobLogFeedBackService.pushLog(SHOP_TERMINAL_VERSION_CHECK_JOB, remark, "total");
		
		jobLogFeedBackService.pushLog(SHOP_TERMINAL_VERSION_CHECK_JOB, "终端版本检查任务 - 结束", "end");
		logger.info("终端终端中控APP版本更新任务 -- end");
	}
	
	/**
	 * 
	 *
	 * 方法说明：检查并更新中控版本
	 *
	 * @param shopTerminal
	 * @param merchantParamsMap
	 * @return	是否有更新
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月10日
	 *
	 */
	private boolean checkAndUpdateVersion(ShopTerminal shopTerminal, Map<String, String> merchantParamsMap) {
		try {
			int currentVersion = Integer.valueOf(merchantParamsMap.get(ZkVersionConstants.CURRENT_VERSION));	// 最新版本号
			if(shopTerminal.getVersionCode() != null && shopTerminal.getVersionCode() < currentVersion) {
				int limitVersion = Integer.valueOf(merchantParamsMap.get(ZkVersionConstants.LIMIT_VERSION));		// 最低支持版本号
				boolean forceUpdate = shopTerminal.getVersionCode() < limitVersion;		// 小于最低支持版本，需强制更新
				VersionInfoMessage versionInfoMessage = new VersionInfoMessage();
//				versionInfoMessage.setImei(shopTerminal.getImei());
				versionInfoMessage.setNoWxGm(shopTerminal.getNoWx());
				versionInfoMessage.setCurrentVersion(currentVersion);
				versionInfoMessage.setLimitVersion(limitVersion);
				versionInfoMessage.setFileSize(merchantParamsMap.get(ZkVersionConstants.FILE_SIZE));
				versionInfoMessage.setForceUpdate(forceUpdate);
				versionInfoMessage.setDownloadUrl(merchantParamsMap.get(ZkVersionConstants.DOWNLOAD_URL));
				versionInfoMessage.setUpdateDesc(merchantParamsMap.get(ZkVersionConstants.UPDATE_DESC));
				versionInfoMessage.setVersionName(merchantParamsMap.get(ZkVersionConstants.VERSION_NAME));
				
				ICommonService basic = commonService.getHessianCommonService(versionInfoMessage.getNoWxGm());
				basic.sendVersionInfoMessage(versionInfoMessage);
			//	commonService.sendVersionInfoMessage(versionInfoMessage);
				return Boolean.TRUE;	// 有更新
			} else {
				return Boolean.FALSE;	// 没更新
			}
		} catch(TsfaServiceException e) {
			logger.error("更新中控终端版本失败", e);
			throw e;
		} catch(Exception e) {
			logger.error("更新中控终端版本失败", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_UPDATE_VERSION_ERROR, "更新中控终端版本失败");
		}
	}

	@Override
	public List<FindShopTerminalPageReturn> findShopTerminalsByNotBind(FindShopTerminalPage findShopTerminalPage) {
		logger.debug("findShopTerminalsByNotBind(findShopTerminalPage={}) - start", findShopTerminalPage); 

		AssertUtils.notNull(findShopTerminalPage);
		AssertUtils.notNullAndEmpty(findShopTerminalPage.getMerchantNo(), "商户不能为空");
		List<FindShopTerminalPageReturn> returnList;
		try {
			returnList = shopTerminalDao.findShopTerminalsByNotBind(findShopTerminalPage);
		} catch (Exception e) {
			logger.error("终端终端信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_PAGE_ERROR, "终端终端信息不存在错误.！", e);
		}

		logger.debug("findShopTerminalsByNotBind(findShopTerminalPage) - end - return value={}", returnList); 
		return returnList;
	}

	@Override
	public List<FindShopTerminalPageReturn> findShopTerminalsByBind(FindShopTerminalPage findShopTerminalPage) {
		logger.debug("findShopTerminalsByBind(findShopTerminalPage={}) - start", findShopTerminalPage); 

		AssertUtils.notNull(findShopTerminalPage);
		AssertUtils.notNullAndEmpty(findShopTerminalPage.getMerchantNo(), "商户不能为空");
		List<FindShopTerminalPageReturn> returnList;
		try {
			returnList = shopTerminalDao.findShopTerminalsByBind(findShopTerminalPage);
		} catch (Exception e) {
			logger.error("终端终端信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_PAGE_ERROR, "终端终端信息不存在错误.！", e);
		}

		logger.debug("findShopTerminalsByBind(findShopTerminalPage) - end - return value={}", returnList); 
		return returnList;
	}

	@Override
	public void updateWxAccountBalance(UpdateShopTerminal updateShopTerminal) {
		logger.debug("updateWxAccountBalance(UpdateShopTerminal updateShopTerminal={}) - start", updateShopTerminal); 

		AssertUtils.notNull(updateShopTerminal);
		AssertUtils.notAllNullAndEmpty(updateShopTerminal.getCode(), updateShopTerminal.getNoWx(), "code和noWx不能同时为空");
		AssertUtils.notNull(updateShopTerminal.getWxBalance(), "微信账户余额不能为空");
		try {
			shopTerminalDao.updateWxAccountBalance(updateShopTerminal);

			logger.debug("updateWxAccountBalance(UpdateShopTerminal) - end"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("更新终端微信账户余额错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_UPDATE_ERROR, "更新终端微信账户余额错误！", e);
		}
	}

	@Override
	public void updateWorkKey(UpdateWorkKey updateWorkKey) {
		logger.debug("updateSignToken(UpdateWorkKey updateSignToken={}) - start", updateWorkKey); 

		AssertUtils.notNull(updateWorkKey);
		AssertUtils.notNull(updateWorkKey.getCode(), "code不能为空");
		AssertUtils.notNull(updateWorkKey.getWorkKey(), "工作密钥不能为空");
		try {
			ShopTerminal shopTerminal = shopTerminalDao.selectByPrimaryKey(updateWorkKey.getCode());
			ShopTerminal updateShopTerminal = new ShopTerminal();
			updateShopTerminal.setCode(shopTerminal.getCode());
			// 重新加密支付密码
			if(StringUtils.isNotEmpty(shopTerminal.getWxPwd())) {
				// 用原工作密钥解密出支付密码原文
				String oriPwd = WxPwdEncryptUtils.decrypt(shopTerminal.getWxPwd(), shopTerminal.getWorkKey());
				// 再用新工作密钥加密支付密码
				updateShopTerminal.setWxPwd(WxPwdEncryptUtils.encrypt(oriPwd, updateWorkKey.getWorkKey()));
			}
			updateShopTerminal.setWorkKey(updateWorkKey.getWorkKey());
			/**
			 * 更新中控微信信息
			 */
			updateShopTerminal.setHeadurl(updateWorkKey.getHeadurl());
			updateShopTerminal.setNickname(updateWorkKey.getNickname());
			updateShopTerminal.setWxNickname(updateWorkKey.getWxNickname());
			updateShopTerminal.setAlias(updateWorkKey.getAlias());
			updateShopTerminal.setHeadAddress(updateWorkKey.getHeadAddress());
			updateShopTerminal.setQcord(updateWorkKey.getQcord());
			shopTerminalDao.updateByPrimaryKeySelective(updateShopTerminal);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("更新终端工作密钥错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_UPDATE_ERROR, "更新终端工作密钥错误！", e);
		}
		
		logger.debug("updateSignToken(UpdateShopTerminal) - end"); 
	}

	@Override
	public TerminalSignReturn sign(TerminalSign terminalSign) {
		logger.debug("sign(TerminalSign terminalSign={}) - start", terminalSign); 

		AssertUtils.notNull(terminalSign);
		AssertUtils.notNull(terminalSign.getNoWx(), "微信号不能为空");
		
		ShopTerminal shopTerminal = null;
		try {
			shopTerminal = shopTerminalDao.selectByWx(terminalSign.getNoWx());
		} catch (Exception e) {
			logger.error("查询终端错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_ERROR, "查询终端错误！", e);
		}
		if(shopTerminal == null) {
			logger.error("终端不存在:{}", terminalSign.getNoWx());
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_EXIST_ERROR, "终端不存在");
		}
//		if(!terminalSign.getWorkKey().equals(shopTerminal.getWorkKey())) {
//			logger.error("错误的工作密钥: {}", shopTerminal.getWorkKey());
//			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_WORK_KEY_ERROR, "错误的工作密钥");
//		}
		// 没有设置支付密码并已生成过签到令牌（即密钥密文为空）
		if(StringUtils.isEmpty(shopTerminal.getWxPwd())) {
			logger.error("终端微信没有配置微信支付密码");
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_CONFIG_PWD, "终端微信没有配置微信支付密码");
		}
			
		TerminalSignReturn terminalSignReturn = new TerminalSignReturn();
		try {
			// 用原工作密钥解密出支付密码原文
			String oriPwd = WxPwdEncryptUtils.decrypt(shopTerminal.getWxPwd(), shopTerminal.getWorkKey());
			Date now = new Date();
			long timestamp = now.getTime();	// 当时间戳
			// AES加密密钥密文，密钥 = 工作密钥 + 时间戳
			terminalSignReturn.setEncrypt(WxPwdEncryptUtils.encryptBySign(oriPwd, shopTerminal.getWorkKey(), timestamp));
			terminalSignReturn.setTimestamp(timestamp);
			terminalSignReturn.setToken(shopTerminal.getWorkKey());
			
			ShopTerminal updateShopTerminal = new ShopTerminal();
			updateShopTerminal.setCode(shopTerminal.getCode());
			updateShopTerminal.setSignTime(now);
			shopTerminalDao.updateByPrimaryKeySelective(updateShopTerminal);
		} catch (Exception e) {
			logger.error("终端签到失败！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_SIGN_ERROR, "终端签到失败！", e);
		}
		
		logger.debug("sign(TerminalSign) - end - return value = {}", terminalSignReturn); 
		return terminalSignReturn;
	}

	@Override
	public void sendTerminalSignCommand(String code) {
		logger.debug("sendTerminalSignCommand(String code={}) - start", code); 

		AssertUtils.notNull(code, "code不能为空");
		
		ShopTerminal shopTerminal = null;
		try {
			shopTerminal = shopTerminalDao.selectByPrimaryKey(code);
		} catch (Exception e) {
			logger.error("查询终端错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_ERROR, "查询终端错误！", e);
		}
		if(shopTerminal == null) {
			logger.error("终端不存在:{}", code);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_EXIST_ERROR, "终端不存在");
		}
		// 没有设置支付密码
		if(StringUtils.isEmpty(shopTerminal.getWxPwd())) {
			logger.error("终端微信没有配置微信支付密码");
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_CONFIG_PWD, "终端微信没有配置微信支付密码");
		}
			
		try {
			// 更新密码和工作密钥
//			ShopTerminal updateShopTerminal = new ShopTerminal();
//			updateShopTerminal.setCode(shopTerminal.getCode());
//			// 再用新工作密钥加密支付密码
//			updateShopTerminal.setWxPwd(WxPwdEncryptUtils.encrypt(shopTerminal.getWxPwd(), shopTerminal.getWorkKey()));
//			updateShopTerminal.setWorkKey(shopTerminal.getWorkKey());
//			shopTerminalDao.updateByPrimaryKeySelective(updateShopTerminal);
			
			// 下发命令
			ICommonService basic = commonService.getHessianCommonService(shopTerminal.getNoWx());
            basic.sendSignCommandMessage(shopTerminal.getNoWx(), shopTerminal.getWorkKey());
		} catch (TsfaServiceException e) {
			logger.error("下发签到命令失败！", e);
			throw e;
		} catch (Exception e) {
			logger.error("下发签到命令失败！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_SEND_SIGN_ERROR, "下发签到命令失败！", e);
		}
		
		logger.debug("sendTerminalSignCommand(String) - end"); 
	}

	@Override
	public void updateTerminalWxPwd(UpdateTerminalWxPwd updateTerminalWxPwd) {
		logger.debug("updateTerminalWxPwd(UpdateTerminalWxPwd updateTerminalWxPwd={}) - start", updateTerminalWxPwd.getCode()); 

		AssertUtils.notNull(updateTerminalWxPwd.getCode(), "code不能为空");
		AssertUtils.notNull(updateTerminalWxPwd.getPwd(), "密码不能为空");
		
		ShopTerminal shopTerminal = null;
		try {
			shopTerminal = shopTerminalDao.selectByPrimaryKey(updateTerminalWxPwd.getCode());
		} catch (Exception e) {
			logger.error("查询终端错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_ERROR, "查询终端错误！", e);
		}
		if(shopTerminal == null) {
			logger.error("终端不存在:{}", updateTerminalWxPwd.getCode());
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_EXIST_ERROR, "终端不存在");
		}
			
		try {
			// 更新密码和工作密钥
			ShopTerminal updateShopTerminal = new ShopTerminal();
			updateShopTerminal.setCode(shopTerminal.getCode());
			// 再用新工作密钥加密支付密码
			updateShopTerminal.setWxPwd(WxPwdEncryptUtils.encrypt(updateTerminalWxPwd.getPwd(), shopTerminal.getWorkKey()));
			shopTerminalDao.updateByPrimaryKeySelective(updateShopTerminal);
		} catch (TsfaServiceException e) {
			logger.error("更新密码失败！", e);
			throw e;
		} catch (Exception e) {
			logger.error("更新密码失败！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_UPDATE_PWD_ERROR, "更新密码失败！", e);
		}
		
		// 下发命令
		try {
			TerminalSign terminalSign = new TerminalSign();
			terminalSign.setNoWx(shopTerminal.getNoWx());
			TerminalSignReturn terminalSignReturn= this.sign(terminalSign);
			
			ICommonService basic = commonService.getHessianCommonService(shopTerminal.getNoWx());
			basic.sendSignResponse(shopTerminal.getNoWx(), terminalSignReturn.getToken(), terminalSignReturn.getEncrypt(), terminalSignReturn.getTimestamp());
			logger.info("已向终端下发签到结果");
				
		} catch (TsfaServiceException e) {
			logger.error("下发签到命令失败！", e);
		} catch (Exception e) {
			logger.error("下发签到命令失败！", e);
		} 
		
		logger.debug("updateTerminalWxPwd(UpdateTerminalWxPwd) - end"); 
	}

	@Override
	public List<FindShopTerminalReturn> findAllShopTerminalByWeiXin(
			FindShopTerminal findShopTerminal) {
		AssertUtils.notAllNull(findShopTerminal.getRemark4(), "中控机类型不能为空！");
		AssertUtils.notAllNull(findShopTerminal.getMerchantNo(), "商户编号不能为空！");
		List<FindShopTerminalReturn> list = Lists.newArrayList();
		try {
			list = shopTerminalDao.findAllShopTerminalByWeiXin(findShopTerminal);
		} catch (Exception e) {
			logger.error("终端终端信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_PAGE_ERROR, "终端终端信息不存在错误.！", e);
		}
		return list;
	}

	/**
     * 
     *
     * 方法说明：开关终端上传朋友圈功能
     *
     * @param updateShopTerminal
     *
     * @author 曾垂瑜 CreateDate: 2018年8月15日
     *
     */
	@Override
	public void updateUploadFriendsFlag(UpdateShopTerminal updateShopTerminal) {
		logger.debug("updateUploadFriendsFlag(UpdateShopTerminal updateShopTerminal={}) - start", updateShopTerminal); 
		
		AssertUtils.notNull(updateShopTerminal.getCode(), "code不能为空");
		AssertUtils.notNull(updateShopTerminal.getUploadFriendsFlag(), "上传朋友圈开关标识不能为空");
		
		ShopTerminal shopTerminal = null;
		try {
			shopTerminal = shopTerminalDao.selectByPrimaryKey(updateShopTerminal.getCode());
		} catch (Exception e) {
			logger.error("查询终端错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_FIND_ERROR, "查询终端错误！", e);
		}
		if(shopTerminal == null) {
			logger.error("终端不存在:{}", updateShopTerminal.getCode());
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_EXIST_ERROR, "终端不存在");
		}
			
		try {
			ShopTerminal update = new ShopTerminal();
			update.setCode(shopTerminal.getCode());
			update.setUploadFriendsFlag(updateShopTerminal.getUploadFriendsFlag());
			shopTerminalDao.updateByPrimaryKeySelective(update);
		} catch (Exception e) {
			logger.error("开关终端上传朋友圈功能失败！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_UPDATE_ERROR, "开关终端上传朋友圈功能失败！", e);
		}
		
		try {
			ICommonService basic = commonService.getHessianCommonService(shopTerminal.getImei());
			if(basic.getClientStatus(shopTerminal.getImei())) {
				basic.sendUploadFriendsFlagCommand(shopTerminal.getImei(), CommonConstant.I_YES == updateShopTerminal.getUploadFriendsFlag());
				logger.info("已向终端下发开关终端上传朋友圈功能命令");
			}
//			if(commonService.getClientStatus(shopTerminal.getImei())) {
//				commonService.sendUploadFriendsFlagCommand(shopTerminal.getImei(), CommonConstant.I_YES == updateShopTerminal.getUploadFriendsFlag());
//				logger.info("已向终端下发开关终端上传朋友圈功能命令");
//			}
		} catch (TsfaServiceException e) {
			logger.error("下发开关终端上传朋友圈功能命令失败！", e);
		} catch (Exception e) {
			logger.error("下发开关终端上传朋友圈功能命令失败！", e);
		} 
		
		logger.debug("updateUploadFriendsFlag(UpdateShopTerminal) - end"); 
	}
	
	
	
	/**
	 * 根据商户号和导购微信号查询终端信息
	 */
	public FindShopTerminalReturn findShopTerminalByMerchantNoAndNoWxGm(String merchantNo, String noWxGm) throws TsfaServiceException{
		return shopTerminalDao.findShopTerminalByMerchantNoAndNoWxGm(merchantNo, noWxGm);
	}

	/**
	 * 根据商户号查询终端信息
	 */
	public  List<FindShopTerminalReturn> findAllShopTerminalByMerchantNo(String merchantNo){
		return shopTerminalDao.findShopTerminalByMerchantNo(merchantNo);
	}
	
	
	/**
	 * 根据商户查询分流二维码列表
	 */
	 public List<PmFlowQcode> findFlowWxByMerchantNo(String merchantNo){
		 return flowQcodeDao.findFlowWxByMerchantNo(merchantNo);
	 }
	 
	 
	 /**
	* 根据code查询分流二维码列表
	*/
	public List<PmFlowQcode> findFlowWxByCode(String code){
			 return flowQcodeDao.findFlowWxByCode(code);
	}
	 
	 /**
	  * 增加分流二维码组
	  * @param pmFlowQcode
	  */
	 public void addFlowWxByMerchantNo(PmFlowQcode pmFlowQcode) {
		  flowQcodeDao.addFlowWxByMerchantNo(pmFlowQcode);
	 }
	 
	 /**
	  * 更新分流二维码组
	  * @param pmFlowQcode
	  */
	 public void updateFlowWxByMerchantNo(PmFlowQcode pmFlowQcode) {
		 flowQcodeDao.updateFlowWxByMerchantNo(pmFlowQcode);
	 }
	 
	 /**
	 * 
	 * 方法说明： 根据商户编号，统计微信总数
	 * 
	 */
	@Override
	public Integer getShopTerminalCount(ShopTerminal shopTerminalEntity) {
		Integer count = shopTerminalDao.getShopTerminalCount(shopTerminalEntity);
		return count;
	}

	
	@Override
	public void delete(String code) {
		shopTerminalDao.deleteByPrimaryKey(code);
	}
}
