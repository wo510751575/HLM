package com.lj.business.member.service.impl;

import java.util.ArrayList;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IShopTerminalDao;
import com.lj.business.member.dao.ITerminalImStatusDao;
import com.lj.business.member.dao.ITerminalLoginLogDao;
import com.lj.business.member.domain.ShopTerminal;
import com.lj.business.member.domain.TerminalImStatus;
import com.lj.business.member.domain.TerminalLoginLog;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.terminalimstatus.AddTerminalImStatus;
import com.lj.business.member.dto.terminalimstatus.AddTerminalImStatusReturn;
import com.lj.business.member.dto.terminalimstatus.FindTerminalBatteryLevelPage;
import com.lj.business.member.dto.terminalimstatus.FindTerminalBatteryLevelPageReturn;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatus;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusPage;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusPageReturn;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusReturn;
import com.lj.business.member.dto.terminalimstatus.TerminalImLoginRequest;
import com.lj.business.member.dto.terminalimstatus.TerminalImLoginResponse;
import com.lj.business.member.dto.terminalimstatus.TerminalImLogoutRequest;
import com.lj.business.member.dto.terminalimstatus.TerminalImLogoutResponse;
import com.lj.business.member.dto.terminalimstatus.UpdateTerminalImStatus;
import com.lj.business.member.dto.terminalimstatus.UpdateTerminalImStatusReturn;
import com.lj.business.member.emus.TerminalType;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.ITerminalImStatusService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.utils.BeanUtils;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;

import edu.emory.mathcs.backport.java.util.Collections;

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
public class TerminalImStatusServiceImpl implements ITerminalImStatusService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(TerminalImStatusServiceImpl.class);
	
	@Resource
	private ITerminalImStatusDao terminalImStatusDao;
	
	@Resource
	private ITerminalLoginLogDao terminalLoginLogDao;
	
	@Resource
	private IShopTerminalDao shopTerminalDao;
	
	@Resource
	private IGuidMemberService guidMemberService;
	
	@Resource
	private IManagerMemberService managerMemberService;
	@Autowired
	private IGmAssistantShopService gmAssistantShopService;
	
	@Autowired 
	ICommonService commonService;
	// 配置消息
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
//    @Autowired 
//	private ISystemInfoService systemInfo;
    
//    @Autowired 
//	private RedisCache redisCache;
    

	@Override
	public AddTerminalImStatusReturn addTerminalImStatus(AddTerminalImStatus addTerminalImStatus) throws TsfaServiceException {
		logger.debug("addTerminalImStatus(AddTerminalImStatus addTerminalImStatus={}) - start", addTerminalImStatus);

		AssertUtils.notNull(addTerminalImStatus);
		try {
			TerminalImStatus terminalImStatus = new TerminalImStatus();
			// add数据录入
			terminalImStatus.setCode(GUID.generateByUUID());
			terminalImStatus.setMerchantNo(addTerminalImStatus.getMerchantNo());
			terminalImStatus.setMerchantName(addTerminalImStatus.getMerchantName());
//			terminalImStatus.setShopNo(addTerminalImStatus.getShopNo());
//			terminalImStatus.setShopName(addTerminalImStatus.getShopName());
			terminalImStatus.setTerminalType(addTerminalImStatus.getTerminalType());
			terminalImStatus.setTerminalCode(addTerminalImStatus.getTerminalCode());
			terminalImStatus.setOnlineFlag(addTerminalImStatus.getOnlineFlag());
			terminalImStatus.setMemberNoGm(addTerminalImStatus.getMemberNoGm());
			terminalImStatus.setMemberName(addTerminalImStatus.getMemberName());
			terminalImStatus.setNoWx(addTerminalImStatus.getNoWx());
			terminalImStatus.setWxNickname(addTerminalImStatus.getWxNickname());
			terminalImStatus.setImei(addTerminalImStatus.getImei());
			terminalImStatus.setLoginTime(addTerminalImStatus.getLoginTime());
			terminalImStatus.setBatteryLevel(addTerminalImStatus.getBatteryLevel());
			terminalImStatus.setCreateId(addTerminalImStatus.getCreateId());
			terminalImStatus.setCreateDate(new Date());
			terminalImStatus.setRemark(addTerminalImStatus.getRemark());
			terminalImStatus.setRemark2(addTerminalImStatus.getRemark2());
			terminalImStatus.setRemark3(addTerminalImStatus.getRemark3());
			terminalImStatus.setRemark4(addTerminalImStatus.getRemark4());
			terminalImStatusDao.insertSelective(terminalImStatus);
			AddTerminalImStatusReturn addTerminalImStatusReturn = new AddTerminalImStatusReturn();

			logger.debug("addTerminalImStatus(AddTerminalImStatus) - end - return value={}", addTerminalImStatusReturn);
			return addTerminalImStatusReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增终端IM状态信息错误！", e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_IM_STATUS_ADD_ERROR, "新增终端IM状态信息错误！", e);
		}
	}

	@Override
	public UpdateTerminalImStatusReturn updateTerminalImStatus(UpdateTerminalImStatus updateTerminalImStatus) throws TsfaServiceException {
		logger.debug("updateTerminalImStatus(UpdateTerminalImStatus updateTerminalImStatus={}) - start", updateTerminalImStatus); 

		UpdateTerminalImStatusReturn updateTerminalImStatusReturn = this.update(updateTerminalImStatus);

		logger.debug("updateTerminalImStatus(UpdateTerminalImStatus) - end - return value={}", updateTerminalImStatusReturn); 
		return updateTerminalImStatusReturn;
	}
	
	public UpdateTerminalImStatusReturn update(UpdateTerminalImStatus updateTerminalImStatus) throws TsfaServiceException {
		AssertUtils.notNull(updateTerminalImStatus);
		AssertUtils.notNullAndEmpty(updateTerminalImStatus.getCode(), "code不能为空");
		try {
			TerminalImStatus terminalImStatus = new TerminalImStatus();
			// update数据录入
			terminalImStatus.setCode(updateTerminalImStatus.getCode());
			terminalImStatus.setMerchantNo(updateTerminalImStatus.getMerchantNo());
			terminalImStatus.setMerchantName(updateTerminalImStatus.getMerchantName());
//			terminalImStatus.setShopNo(updateTerminalImStatus.getShopNo());
//			terminalImStatus.setShopName(updateTerminalImStatus.getShopName());
			terminalImStatus.setTerminalType(updateTerminalImStatus.getTerminalType());
			terminalImStatus.setTerminalCode(updateTerminalImStatus.getTerminalCode());
			terminalImStatus.setOnlineFlag(updateTerminalImStatus.getOnlineFlag());
			terminalImStatus.setMemberNoGm(updateTerminalImStatus.getMemberNoGm());
			terminalImStatus.setMemberName(updateTerminalImStatus.getMemberName());
			terminalImStatus.setNoWx(updateTerminalImStatus.getNoWx());
			terminalImStatus.setWxNickname(updateTerminalImStatus.getWxNickname());
			terminalImStatus.setImei(updateTerminalImStatus.getImei());
			terminalImStatus.setLoginTime(updateTerminalImStatus.getLoginTime());
			terminalImStatus.setBatteryLevel(updateTerminalImStatus.getBatteryLevel());
			terminalImStatus.setCreateId(updateTerminalImStatus.getCreateId());
			terminalImStatus.setCreateDate(updateTerminalImStatus.getCreateDate());
			terminalImStatus.setRemark(updateTerminalImStatus.getRemark());
			terminalImStatus.setRemark2(updateTerminalImStatus.getRemark2());
			terminalImStatus.setRemark3(updateTerminalImStatus.getRemark3());
			terminalImStatus.setRemark4(updateTerminalImStatus.getRemark4());
			AssertUtils.notUpdateMoreThanOne(terminalImStatusDao.updateByPrimaryKeySelective(terminalImStatus));
			UpdateTerminalImStatusReturn updateTerminalImStatusReturn = new UpdateTerminalImStatusReturn();
			return updateTerminalImStatusReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("终端IM状态信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_IM_STATUS_UPDATE_ERROR, "终端IM状态信息更新信息错误！", e);
		}
	}

	@Override
	public FindTerminalImStatusReturn findTerminalImStatus(FindTerminalImStatus findTerminalImStatus) throws TsfaServiceException {
		logger.debug("findTerminalImStatus(FindTerminalImStatus findTerminalImStatus={}) - start", findTerminalImStatus); 

		AssertUtils.notNull(findTerminalImStatus);
		AssertUtils.notAllNull(findTerminalImStatus.getCode(), "code不能为空");
		try {
			TerminalImStatus terminalImStatus = terminalImStatusDao.selectByPrimaryKey(findTerminalImStatus.getCode());
			if (terminalImStatus == null) {
				throw new TsfaServiceException(ErrorCode.TERMINAL_IM_STATUS_NOT_EXIST_ERROR, "终端IM状态信息不存在");
			}
			FindTerminalImStatusReturn findTerminalImStatusReturn = new FindTerminalImStatusReturn();
			// find数据录入
			findTerminalImStatusReturn.setCode(terminalImStatus.getCode());
			findTerminalImStatusReturn.setMerchantNo(terminalImStatus.getMerchantNo());
			findTerminalImStatusReturn.setMerchantName(terminalImStatus.getMerchantName());
//			findTerminalImStatusReturn.setShopNo(terminalImStatus.getShopNo());
//			findTerminalImStatusReturn.setShopName(terminalImStatus.getShopName());
			findTerminalImStatusReturn.setTerminalType(terminalImStatus.getTerminalType());
			findTerminalImStatusReturn.setTerminalCode(terminalImStatus.getTerminalCode());
			findTerminalImStatusReturn.setOnlineFlag(terminalImStatus.getOnlineFlag());
			findTerminalImStatusReturn.setMemberNoGm(terminalImStatus.getMemberNoGm());
			findTerminalImStatusReturn.setMemberName(terminalImStatus.getMemberName());
			findTerminalImStatusReturn.setNoWx(terminalImStatus.getNoWx());
			findTerminalImStatusReturn.setWxNickname(terminalImStatus.getWxNickname());
			findTerminalImStatusReturn.setImei(terminalImStatus.getImei());
			findTerminalImStatusReturn.setLoginTime(terminalImStatus.getLoginTime());
			findTerminalImStatusReturn.setBatteryLevel(terminalImStatus.getBatteryLevel());
			findTerminalImStatusReturn.setCreateId(terminalImStatus.getCreateId());
			findTerminalImStatusReturn.setCreateDate(terminalImStatus.getCreateDate());
			findTerminalImStatusReturn.setRemark(terminalImStatus.getRemark());
			findTerminalImStatusReturn.setRemark2(terminalImStatus.getRemark2());
			findTerminalImStatusReturn.setRemark3(terminalImStatus.getRemark3());
			findTerminalImStatusReturn.setRemark4(terminalImStatus.getRemark4());

			logger.debug("findTerminalImStatus(FindTerminalImStatus) - end - return value={}", findTerminalImStatusReturn); 
			return findTerminalImStatusReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找终端IM状态信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_IM_STATUS_FIND_ERROR, "查找终端IM状态信息信息错误！", e);
		}

	}

	@Override
	public Page<FindTerminalImStatusPageReturn> findTerminalImStatusPage(FindTerminalImStatusPage findTerminalImStatusPage) throws TsfaServiceException {
		logger.debug("findTerminalImStatusPage(FindTerminalImStatusPage findTerminalImStatusPage={}) - start", findTerminalImStatusPage); 

		AssertUtils.notNull(findTerminalImStatusPage);
		List<FindTerminalImStatusPageReturn> returnList;
		int count = 0;
		try {
			returnList = terminalImStatusDao.findTerminalImStatusPage(findTerminalImStatusPage);
			count = terminalImStatusDao.findTerminalImStatusPageCount(findTerminalImStatusPage);
		} catch (Exception e) {
			logger.error("终端IM状态信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_IM_STATUS_FIND_PAGE_ERROR, "终端IM状态信息不存在错误.！", e);
		}
		Page<FindTerminalImStatusPageReturn> returnPage = new Page<FindTerminalImStatusPageReturn>(returnList, count, findTerminalImStatusPage);

		logger.debug("findTerminalImStatusPage(FindTerminalImStatusPage) - end - return value={}", returnPage); 
		return returnPage;
	}

	/* (non-Javadoc)
	 * @see com.lj.business.member.service.ITerminalImStatusService#login(com.lj.business.member.dto.terminalimstatus.TerminalImLoginRequest)
	 */
	@Override
	public TerminalImLoginResponse login(TerminalImLoginRequest request) {
		logger.debug("login(TerminalImLoginRequest request={}) - start", request); 

		AssertUtils.notNull(request);
		AssertUtils.notNullAndEmpty(request.getType(), "登录类型不能为空");
		if(TerminalType.ZK.name().equals(request.getType())) {
			AssertUtils.notNullAndEmpty(request.getTerminalCode(), "终端号不能为空");
		}else {
			AssertUtils.notNullAndEmpty(request.getMemberNoGm(), "导购编号不能为空");
			AssertUtils.notNullAndEmpty(request.getNoWx(), "终端微信不能为空");
		}
		
		TerminalImLoginResponse response = null;
		try {
			TerminalImStatus query = new TerminalImStatus();
			
			String merchantNo;			//商户号
			String merchantName;		//商户名称
			String wxNickname = null;			//终端微信昵称
			String noWx = null;	//终端微信
			ShopTerminal shopTerminal = null;
			String noWxListStr="";	//导购绑定的微信集合
			FindGuidMemberReturn findGuidMemberReturn =null;
			FindShopTerminalReturn findShopTerminalReturn =null;
			if(TerminalType.ZK.name().equals(request.getType())) {
				shopTerminal = shopTerminalDao.selectByPrimaryKey(request.getTerminalCode());
				findShopTerminalReturn = new FindShopTerminalReturn();
				if(shopTerminal == null) {
					throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_EXIST_ERROR, "非法终端：终端不存在");
				}
				if(shopTerminal.getStatus() != 1) {	// 正常状态
					throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_NORMAL_ERROR, "终端状态非法");
				}
				if(StringUtils.isEmpty(shopTerminal.getNoWx())) {	// 必须绑定微信号
					throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_BIND_WX, "终端没有绑定微信");
				}
				BeanUtils.copyPropertiesNull(findShopTerminalReturn, shopTerminal, true);
				noWx = shopTerminal.getNoWx();
				
				//查询条件
				merchantNo = shopTerminal.getMerchantNo();
				merchantName = shopTerminal.getMerchantName();
				wxNickname = shopTerminal.getNickname();
				
				// 更新终端版本号及绑定微信版本
				ShopTerminal updateTerminal = new ShopTerminal();
				if(request.getVersionCode() != null && (shopTerminal.getVersionCode() == null || request.getVersionCode() != shopTerminal.getVersionCode())) {
					updateTerminal.setVersionCode(request.getVersionCode());
					updateTerminal.setVersionName(request.getVersionName());
				}
				if(request.getWxVersionCode() !=null && (shopTerminal.getWxVersionCode()==null || request.getWxVersionCode() != shopTerminal.getWxVersionCode())){
					updateTerminal.setWxVersionCode(request.getWxVersionCode());
					updateTerminal.setWxVersionName(request.getWxVersionName());
				}
				if(StringUtils.isNotEmpty(request.getUsernameWx()) && !request.getUsernameWx().equals(shopTerminal.getUsernameWx())) {
					updateTerminal.setUsernameWx(request.getUsernameWx());
				}
				if(updateTerminal.getVersionCode()!=null||updateTerminal.getWxVersionCode()!=null || StringUtils.isNotEmpty(updateTerminal.getUsernameWx())){
					updateTerminal.setCode(shopTerminal.getCode());
					shopTerminalDao.updateByPrimaryKeySelective(updateTerminal);
					if(updateTerminal.getVersionCode()!=null){
						logger.info("已更新终端[{} - {}]版本号为：versionCode = {}, versionName = {}", shopTerminal.getCode(), shopTerminal.getImei(), request.getVersionCode(), request.getVersionName());
					}
					if(updateTerminal.getWxVersionCode()!=null){
						logger.info("已更新终端[{} - {}]绑定微信版本为：wxVersionCode = {}, wxVersionName = {}", shopTerminal.getCode(), shopTerminal.getImei(), request.getWxVersionCode(),request.getWxVersionName());
					}
				}
				query.setTerminalCode(shopTerminal.getCode());
			} else {
				// 查询导购信息
				FindGuidMember findGuidMember = new FindGuidMember();
				findGuidMember.setMemberNo(request.getMemberNoGm());
				
				findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
				if(findGuidMemberReturn == null) {
					throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR, "导购不存在");
				}
				
				//导购绑定的微信集合
				noWxListStr =gmAssistantShopService.findGroupConcatByAssNo(request.getMemberNoGm());
				// 必须绑定微信号
				if(StringUtils.isEmpty(noWxListStr)) {
					throw new TsfaServiceException(ErrorCode.GM_NOT_BIND_WX, "导购:"+findGuidMember.getMemberName()+"没有绑定微信");
				}
				if(!noWxListStr.contains(request.getNoWx())) {
					throw new TsfaServiceException(ErrorCode.GM_NOT_BIND_WX, "导购没有绑定该微信：" + request.getUsernameWx());
				}
				
				noWx = request.getNoWx();
				//查询条件
				merchantNo = findGuidMemberReturn.getMerchantNo();
				merchantName = findGuidMemberReturn.getMerchantName();
				query.setMemberNoGm(request.getMemberNoGm());
				
			}
			
			// 记录终端状态
			query.setMerchantNo(merchantNo);
			query.setTerminalType(request.getType());
			query.setNoWx(noWx);
			TerminalImStatus terminalImStatus = terminalImStatusDao.selectBySelective(query);
			
			if(terminalImStatus != null) {	// 已有状态记录则更新
				TerminalImStatus updateImStatus = new TerminalImStatus();
				updateImStatus.setCode(terminalImStatus.getCode());
				updateImStatus.setOnlineFlag(1);
				updateImStatus.setTerminalCode(request.getTerminalCode());
				terminalImStatus.setLoginTime(new Date());
				terminalImStatusDao.updateByPrimaryKeySelective(updateImStatus);
			} else {	// 没有状态记录则新增
				TerminalImStatus addImStatus = new TerminalImStatus();
				addImStatus.setCode(GUID.generateCode());
				addImStatus.setCreateId(merchantName);
				addImStatus.setCreateDate(new Date());
				addImStatus.setTerminalType(request.getType());
				addImStatus.setOnlineFlag(1);
				addImStatus.setMerchantNo(merchantNo);
				addImStatus.setMerchantName(merchantName);
				addImStatus.setTerminalCode(request.getTerminalCode());
				if(findGuidMemberReturn != null) {
					addImStatus.setMemberNoGm(findGuidMemberReturn.getMemberNo());
					addImStatus.setMemberName(findGuidMemberReturn.getMemberName());
				}
				addImStatus.setLoginTime(new Date());
				addImStatus.setNoWx(noWx);
				addImStatus.setWxNickname(wxNickname);
				terminalImStatusDao.insertSelective(addImStatus);
			}
			
			// 记录终端日志
			TerminalLoginLog terminalLoginLog = new TerminalLoginLog();
			terminalLoginLog.setCode(GUID.getPreUUID());
			terminalLoginLog.setOptType(0);	// 登录
			terminalLoginLog.setOptTime(new Date());
			terminalLoginLog.setTerminalType(request.getType());
			terminalLoginLog.setTerminalCode(request.getTerminalCode());
			if(findGuidMemberReturn != null) {
				terminalLoginLog.setMemberNoGm(findGuidMemberReturn.getMemberNo());
				terminalLoginLog.setMemberName(findGuidMemberReturn.getMemberName());
				terminalLoginLog.setMerchantNo(findGuidMemberReturn.getMerchantNo());
				terminalLoginLog.setMerchantName(findGuidMemberReturn.getMerchantName());
			}else if(shopTerminal != null){
				terminalLoginLog.setMerchantNo(shopTerminal.getMerchantNo());
				terminalLoginLog.setMerchantName(shopTerminal.getMerchantName());
			}
			
			terminalLoginLog.setNoWx(noWx);
			terminalLoginLogDao.insertSelective(terminalLoginLog);
			
			response = new TerminalImLoginResponse();
			response.setFindGuidMemberReturn(findGuidMemberReturn);
			response.setFindShopTerminalReturn(findShopTerminalReturn);
			response.addNoWx(noWx);	// XXX 微信列表
		} catch(TsfaServiceException e) {
			logger.error(e.getExceptionInfo(), e);
			throw e;
		} catch(Exception e) {
			logger.error("终端登录失败", e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_LOGIN_ERROR, "终端登录失败", e);
		}
		
		logger.debug("login(TerminalImLoginRequest) - end - return value={}", response); 
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.lj.business.member.service.ITerminalImStatusService#logout(com.lj.business.member.dto.terminalimstatus.TerminalImLogoutRequest)
	 */
	@Override
	public TerminalImLogoutResponse logout(TerminalImLogoutRequest request) {
		logger.debug("logout(TerminalImLogoutRequest request={}) - start", request); 

		AssertUtils.notNull(request);
		AssertUtils.notNullAndEmpty(request.getType(), "登录类型不能为空");
		if(TerminalType.ZK.name().equals(request.getType())) {
			AssertUtils.notNullAndEmpty(request.getTerminalCode(), "终端号不能为空");
		}else {
			AssertUtils.notNullAndEmpty(request.getMemberNoGm(), "导购编号不能为空");
		}
		
		TerminalImLogoutResponse response = null;
		try {
			
			TerminalLoginLog terminalLoginLog = new TerminalLoginLog();
			
			TerminalImStatus query = new TerminalImStatus();
			if(TerminalType.ZK.name().equals(request.getType())) {
				query.setTerminalCode(request.getTerminalCode());
			} else {
				query.setMemberNoGm(request.getMemberNoGm());
			}
			query.setTerminalType(request.getType());
			TerminalImStatus terminalImStatus = terminalImStatusDao.selectBySelective(query);
			if(terminalImStatus != null) {	// 已有状态记录则更新
				TerminalImStatus updateImStatus = new TerminalImStatus();
				updateImStatus.setCode(terminalImStatus.getCode());
				updateImStatus.setOnlineFlag(0);
				terminalImStatusDao.updateByPrimaryKeySelective(updateImStatus);
			} else {
				logger.info("终端未登录，没有找到记录");
//					return null;
			}
			
			
			// 记录终端日志
			terminalLoginLog.setCode(GUID.getPreUUID());
			terminalLoginLog.setOptType(1);	// 登出
			terminalLoginLog.setOptTime(new Date());
			terminalLoginLog.setTerminalType(request.getType());
			if(TerminalType.ZK.name().equals(request.getType())) {
				
			} else {
				// 查询导购信息
				FindGuidMember findGuidMember = new FindGuidMember();
				findGuidMember.setMemberNo(request.getMemberNoGm());
				FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
				terminalLoginLog.setMemberNoGm(findGuidMemberReturn.getMemberNo());
				terminalLoginLog.setMemberName(findGuidMemberReturn.getMemberName());
			}
			terminalLoginLog.setTerminalCode(request.getTerminalCode());
			if(null != terminalImStatus) {
				terminalLoginLog.setNoWx(terminalImStatus.getNoWx());
				terminalLoginLog.setMerchantNo(terminalImStatus.getMerchantNo());
				terminalLoginLog.setMerchantName(terminalImStatus.getMerchantName());
			}
			
			terminalLoginLogDao.insertSelective(terminalLoginLog);
			
			//中控登出,发送异常预警
			if(TerminalType.ZK.name().equals(request.getType())) {
				// 查询店长信息
//				List<ManagerMemberReturnDto> findManagerMemberByShop = managerMemberService.findManagerMemberByShop(findGuidMemberReturn.getShopNo());
//				if(findManagerMemberByShop!=null&&findManagerMemberByShop.size()>0){//(有店长时才发送预警 )
//					ManagerMemberReturnDto manager=findManagerMemberByShop.get(0);//获取店长信息
//					//查询店长对应导购
//					FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
//					findGuidMemberPage.setMobile(manager.getMobile());
//					findGuidMemberPage.setShopNo(manager.getMemberNoShop());
//					List<FindGuidMemberPageReturn> guidList = guidMemberService.findGuidMembers(findGuidMemberPage);
//					if(guidList == null || guidList.isEmpty()) {
//						logger.error("店长没有对应的导购信息: {}", manager.getMemberNo());
//						return response;
//					}
//					FindGuidMemberPageReturn guid = guidList.get(0);
//					
//					//给中控手机对应店长推送手机离线预警
//					PushNotifyMessage pushNotifyMessage = new PushNotifyMessage();
//					pushNotifyMessage.setType("10");	// 10-中控机异常预警
//					pushNotifyMessage.setMemberNoGm(guid.getMemberNo()); // 店长对应的导购编号
//					//从cc配置中获取推送信息
//					String message = localCacheSystemParams.getSystemParam("ms", "TidNotify", "loginStatusNotify");
//					message = message.replaceAll("\\$\\{mobileName\\}", terminalImStatus.getTerminalCode());
//					message = message.replaceAll("\\$\\{time\\}", DateUtils.formatDate(new Date(), "HH:mm"));
//					pushNotifyMessage.setTitle(message);
//					pushNotifyMessage.setOfflinePush(Boolean.TRUE);
//					
//					IContactsService basic =  commonService.getHessianContactsService(pushNotifyMessage.getMemberNoGm());
//					basic.sendPushNotifyMessage(pushNotifyMessage);
//					logger.info("已向店长{}发送离线预警信息", manager.getMemberName());
//				}else{
//					logger.info("未查询到店长");
//				}
			}
			response = new TerminalImLogoutResponse();
		} catch(TsfaServiceException e) {
			logger.error(e.getExceptionInfo(), e);
			throw e;
		} catch(Exception e) {
			logger.error("终端登出失败", e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_LOGOUT_ERROR, "终端登出失败", e);
		}
		
		logger.debug("logout(TerminalImLogoutRequest) - end - return value={}", response); 
		return response;
	}

	@Override
	public UpdateTerminalImStatusReturn updateTerminalImStatusByType(UpdateTerminalImStatus updateTerminalImStatus) throws TsfaServiceException {
		logger.debug("updateTerminalImStatusByType(UpdateTerminalImStatus updateTerminalImStatus={}) - start", updateTerminalImStatus); 

		AssertUtils.notNull(updateTerminalImStatus);
		AssertUtils.notNullAndEmpty(updateTerminalImStatus.getTerminalType(), "登录类型不能为空");
		if(TerminalType.ZK.name().equals(updateTerminalImStatus.getTerminalType())) {
//			AssertUtils.notNullAndEmpty(updateTerminalImStatus.getImei(), "手机串号不能为空");
			AssertUtils.notNullAndEmpty(updateTerminalImStatus.getTerminalCode(), "终端号不能为空");
			AssertUtils.notNullAndEmpty(updateTerminalImStatus.getBatteryLevel(), "手机电量不能为空");
		}else {
			AssertUtils.notNullAndEmpty(updateTerminalImStatus.getMemberNoGm(), "导购编号不能为空");
		}
		
		UpdateTerminalImStatusReturn response = null;
		try {
			TerminalImStatus query = new TerminalImStatus();
			if(TerminalType.ZK.name().equals(updateTerminalImStatus.getTerminalType())) {
				query.setTerminalCode(updateTerminalImStatus.getTerminalCode());
			} else {
				query.setMemberNoGm(updateTerminalImStatus.getMemberNoGm());
			}
			query.setTerminalType(updateTerminalImStatus.getTerminalType());
			TerminalImStatus terminalImStatus = terminalImStatusDao.selectBySelective(query);
			if(terminalImStatus != null) {
//				Integer batteryLevel=terminalImStatus.getBatteryLevel();//存储上次通知电量
				updateTerminalImStatus.setCode(terminalImStatus.getCode());
				response = this.update(updateTerminalImStatus);//更新
//				if(StringUtils.isNotEmpty(terminalImStatus.getShopNo())){
//					// 查询店长信息(有店长时才发送预警 )
//					List<ManagerMemberReturnDto> findManagerMemberByShop = managerMemberService.findManagerMemberByShop(terminalImStatus.getShopNo());
//					if(findManagerMemberByShop!=null&&findManagerMemberByShop.size()>0){
//						ManagerMemberReturnDto manager=findManagerMemberByShop.get(0);//获取店长信息
//						if(updateTerminalImStatus.getBatteryLevel() != null) {
//							//获取配置中所有IM中控终端电量预警值
//							Map<String, String> systemParamGroup = localCacheSystemParams.getSystemParamGroup("ms", "TidBatteryWarning");
//							//从大到小排序
//							sortByValue(systemParamGroup);
//							for (String level : systemParamGroup.values()) {
//								Integer intLevel=Integer.valueOf(level);
//								// 终端上次通知电量在预警范围之外，而本次通知电量达到预警值，则预警通知店长
//								if(updateTerminalImStatus.getBatteryLevel() <= intLevel && 
//									(batteryLevel == null || batteryLevel==0||batteryLevel > intLevel)) {
//									FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
//									findGuidMemberPage.setMobile(manager.getMobile());
//									findGuidMemberPage.setShopNo(manager.getMemberNoShop());
//									List<FindGuidMemberPageReturn> guidList = guidMemberService.findGuidMembers(findGuidMemberPage);
//									if(guidList == null || guidList.isEmpty()) {
//										logger.error("店长没有对应的导购信息: {}", manager.getMemberNo());
//										return response;
//									}
//									FindGuidMemberPageReturn guid = guidList.get(0);
//									
//									PushNotifyMessage pushNotifyMessage = new PushNotifyMessage();
//									pushNotifyMessage.setType("10");	// 10-中控机异常预警
//									pushNotifyMessage.setMemberNoGm(guid.getMemberNo()); // 店长对应的导购编号
//									//从cc配置中获取推送信息
//									String message = localCacheSystemParams.getSystemParam("ms", "TidNotify", "batteryNotify");
//									message = message.replaceAll("\\$\\{batteryLevel\\}", updateTerminalImStatus.getBatteryLevel().toString());
//									message = message.replaceAll("\\$\\{mobileName\\}", terminalImStatus.getTerminalCode());
//									message = message.replaceAll("\\$\\{time\\}", DateUtils.formatDate(new Date(), "HH:mm"));
//									pushNotifyMessage.setTitle(message);
//									pushNotifyMessage.setOfflinePush(Boolean.TRUE);
//									
//									IContactsService basic =  commonService.getHessianContactsService(pushNotifyMessage.getMemberNoGm());
//									basic.sendPushNotifyMessage(pushNotifyMessage);
//									logger.info("已向店长{}发送低电量预警信息", manager.getMemberName());
//									break;
//								}else {
//									logger.info("未满足通知条件");
//								}
//							}
//						}
//					}else{
//						logger.info("未查询到店长");
//					}
//				}else{
//					logger.error("终端分店编号为空");
//					throw new TsfaServiceException(ErrorCode.TERMINAL_IM_STATUS_UPDATE_ERROR, "修改终端IM状态信息失败.！");
//				}
			}
		} catch(TsfaServiceException e) {
			logger.error(e.getExceptionInfo(), e);
			throw e;
		} catch(Exception e) {
			logger.error("修改终端IM状态信息失败", e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_IM_STATUS_UPDATE_ERROR, "修改终端IM状态信息失败", e);
		}
		
		logger.debug("updateTerminalImStatusByType(UpdateTerminalImStatus) - end - return value={}", response); 
		return response;
	}
	
	/**
	 * 
	 * 方法说明：Map按Value排序 sortByValue (从大到小)
	 * 
	 * @param	Map<K, V> map
	 * @author 彭俊霖 CreateDate: 2017年11月13日
	 *
	 */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) throws TsfaServiceException{
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o2, Map.Entry<K, V> o1) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

	@Override
	public Page<FindTerminalBatteryLevelPageReturn> findTerminalBatteryLevelPage(FindTerminalBatteryLevelPage findTerminalBatteryLevelPage) throws TsfaServiceException {
		logger.debug("findTerminalBatteryLevelPage(FindTerminalBatteryLevelPage findTerminalBatteryLevelPage={}) - start", findTerminalBatteryLevelPage); 

		AssertUtils.notNull(findTerminalBatteryLevelPage);
		List<FindTerminalBatteryLevelPageReturn> returnList = new ArrayList<FindTerminalBatteryLevelPageReturn>();
		int count = 0;
		try {
			count = terminalImStatusDao.findTerminalBatteryLevelPageCount(findTerminalBatteryLevelPage);
			if(count >0) {
				returnList = terminalImStatusDao.findTerminalBatteryLevelPage(findTerminalBatteryLevelPage);
				for (FindTerminalBatteryLevelPageReturn findTerminalBatteryLevelPageReturn : returnList) {
					String account ="";
					String terminalType = findTerminalBatteryLevelPageReturn.getTerminalType();
					if(TerminalType.ZK.toString().equals(terminalType)) {
						account = findTerminalBatteryLevelPageReturn.getNoWx();
					}else {
						account = findTerminalBatteryLevelPageReturn.getMemberNoGm();
					}
					findTerminalBatteryLevelPageReturn.setOnlineFlag(commonService.getClientStatus(account)?1:0);
				}
			}
		} catch (Exception e) {
			logger.error("终端手机电量信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_IM_STATUS_FIND_PAGE_ERROR, "终端手机电量信息不存在错误！", e);
		}
		Page<FindTerminalBatteryLevelPageReturn> returnPage = new Page<FindTerminalBatteryLevelPageReturn>(returnList, count, findTerminalBatteryLevelPage);

		logger.debug("findTerminalBatteryLevelPage(FindTerminalBatteryLevelPage) - end - return value={}", returnPage); 
		return returnPage;
	}

	
	@Override
	public void delete(UpdateTerminalImStatus updateTerminalImStatus) {
		terminalImStatusDao.delete(updateTerminalImStatus);
		
	}


	
	@Override
	public void deleteLoginLog(String merchantNo, String noWx) {
		terminalLoginLogDao.delete(merchantNo,noWx);
		
	}
	


}
