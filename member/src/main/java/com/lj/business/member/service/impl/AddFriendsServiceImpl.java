package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.IPage;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.core.util.WxOpenIdUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.common.KeyConstant;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.member.aync.AyncSendMessageByNonMemberService;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IAddFriendsDao;
import com.lj.business.member.dao.IFlowQcodeDao;
import com.lj.business.member.dao.IGuidMemberDao;
import com.lj.business.member.dao.IMerchantDao;
import com.lj.business.member.dao.IPersonMemberBaseDao;
import com.lj.business.member.dao.IShopTerminalDao;
import com.lj.business.member.domain.AddFriends;
import com.lj.business.member.domain.GmAssistantShop;
import com.lj.business.member.domain.PersonMemberBase;
import com.lj.business.member.dto.AddPersonMemberByWx;
import com.lj.business.member.dto.AddPersonMemberByWxReturn;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.UpdatePersonMember;
import com.lj.business.member.dto.UpdatePersonMemberBase;
import com.lj.business.member.dto.addfriends.AddAddFriends;
import com.lj.business.member.dto.addfriends.AddAddFriendsReturn;
import com.lj.business.member.dto.addfriends.DistributionMember;
import com.lj.business.member.dto.addfriends.DistributionMemberReturn;
import com.lj.business.member.dto.addfriends.FindAddFriends;
import com.lj.business.member.dto.addfriends.FindAddFriendsAllotPage;
import com.lj.business.member.dto.addfriends.FindAddFriendsPage;
import com.lj.business.member.dto.addfriends.FindAddFriendsPageReturn;
import com.lj.business.member.dto.addfriends.FindAddFriendsReturn;
import com.lj.business.member.dto.addfriends.FindClaimMemberPage;
import com.lj.business.member.dto.addfriends.FindClaimMemberPageReturn;
import com.lj.business.member.dto.addfriends.SyncFriendsDetail;
import com.lj.business.member.dto.addfriends.SyncFriendsFromZk;
import com.lj.business.member.dto.addfriends.UpdateAddFriendStatus;
import com.lj.business.member.dto.addfriends.UpdateAddFriendStatusReturn;
import com.lj.business.member.dto.addfriends.UpdateAddFriends;
import com.lj.business.member.dto.addfriends.UpdateAddFriendsReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.im.FindPersonMemberByChat;
import com.lj.business.member.dto.im.FindPersonMemberByChatReturn;
import com.lj.business.member.dto.im.SyncPersonWxInfoRequest;
import com.lj.business.member.dto.im.SyncPersonWxInfoResponse;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.PmFlowQcode;
import com.lj.business.member.emus.AddFriendsStatus;
import com.lj.business.member.emus.GmAddFlag;
import com.lj.business.member.emus.MemberAddType;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.member.utils.WeiChatUtils;
import com.lj.business.supcon.dto.contacts.MemberClaimMessage;
import com.lj.business.supcon.dto.contacts.NewFriendMessage;
import com.lj.business.supcon.dto.contacts.SyncShopWxFriendsMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IContactsService;
import com.lj.business.weixin.dto.imchat.SendImChatByNonMember;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IWxRedpackDetailInfoService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.distributecache.IDistributeCache;
import com.lj.distributelock.IDistributeLock;
import com.lj.distributelock.RedisLock;

/**
 * 类说明：添加微信好友实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 曾垂瑜
 * 
 * 
 *         CreateDate: 2017-06-14
 */
@Service
public class AddFriendsServiceImpl implements IAddFriendsService {

	private static final String QCORD_URL = "qcordUrl";

	private static final String DIFF_NUM = "diffNum";

	private static final int IM_COUNT = 0;

	private static final String NO_WX_MAP = "noWx";

	private static final String NO_WX_GM_MAP = "noWxGm";

	private static final String MERCHANT_NO_MAP = "merchantNo";

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AddFriendsServiceImpl.class);

	@Resource
	private IAddFriendsDao addFriendsDao;

	@Resource
	private IGuidMemberService guidMemberService;

	@Resource
	private IShopTerminalDao shopTerminalDao;

	/** The iguidMember dao. */
	@Resource
	private IGuidMemberDao iguidMemberDao;

	@Resource
	private IMerchantService merchantService;

	@Resource
	private IFlowQcodeDao flowQcodeDao;

	@Resource
	private IPersonMemberService personMemberService;

	@Resource
	private IPersonMemberBaseDao personMemberBaseDao;

	@Resource
	private IPersonMemberBaseService personMemberBaseService;

	@Resource
	private IPersonMemberImService personMemberImService;

	@Resource
	private IDistributeLock redisLock;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Resource
	private IShopTerminalService shopTerminalService;

	@Resource
	private ICouponRuleService couponRuleService;

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Autowired
	private IMerchantParamsService merchantParamsService;

	@Resource
	IWxRedpackDetailInfoService wxRedpackDetailInfoService;

	@Resource
	private IImChatInfoService imChatInfoService;

	@Resource
	private AyncSendMessageByNonMemberService ayncSendMessageByNonMemberService;

	@Resource
	private IDistributeCache redis;
	@Autowired
	ICommonService commonService;
	@Autowired
	IMerchantDao merchantDao;
	@Resource
	private IGmAssistantShopService gmAssistantShopService;

	/**
	 * 
	 *
	 * 方法说明：添加添加微信好友信息 待完善逻辑：XXX 1、中控手机微信好友上限控制 2、频繁添加好友控制
	 *
	 * @param addAddFriends
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月25日
	 *
	 */
	@Override
	public AddAddFriendsReturn addAddFriends(AddAddFriends addAddFriends) throws TsfaServiceException {
		logger.debug("addAddFriends(AddAddFriends addAddFriends={}) - start", addAddFriends);

		AssertUtils.notNull(addAddFriends);
		AssertUtils.notNullAndEmpty(addAddFriends.getNoWxGm(), "导购微信号不能为空");
		AssertUtils.notNullAndEmpty(addAddFriends.getWxQrCode(), "客户微信二维码不能为空");
		AssertUtils.notNullAndEmpty(addAddFriends.getNoWx(), "客户微信不能为空");
		AssertUtils.notNullAndEmpty(addAddFriends.getWxAddType(), "客户新增方式不能为空");
		AssertUtils.notAllNull(addAddFriends.getMemberNoGm(), addAddFriends.getNoWxGm(), "导购编号和导购微信号不能同时空");
		try {
			// 查询导购信息
			FindGuidMember findGuidMember = new FindGuidMember();
			if (StringUtils.isNotEmpty(addAddFriends.getMemberNoGm())) {
				findGuidMember.setMemberNo(addAddFriends.getMemberNoGm());// 导购编号
			}

			FindShopTerminalReturn shopTerminalReturn = shopTerminalService
					.findShopTerminalNormal(addAddFriends.getNoWxGm());
			FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);

			// 验证终端是否可以添加客户微信为好友
			PersonMemberBase personMemberBase = this.validateMember(addAddFriends.getMemberNoGm(),
					addAddFriends.getNoWxGm(), addAddFriends.getNoWx(), addAddFriends.getAlias());

			// 根据手机号查询客户基础表信息
			if (StringUtils.isNotEmpty(addAddFriends.getMobile())) {
				FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
				findPersonMemberBase.setMobile(addAddFriends.getMobile());
				personMemberBase = personMemberBaseDao.findByMobile(findPersonMemberBase);
				// 存在客户基础记录
				if (personMemberBase != null) {
					FindPersonMember findPersonMember = new FindPersonMember();
					findPersonMember.setMemberNo(personMemberBase.getMemberNo());
					findPersonMember.setShopWx(addAddFriends.getNoWxGm());
					FindPersonMemberReturn findPersonMemberReturn = personMemberService
							.findPersonMember(findPersonMember);
					if (findPersonMemberReturn != null) {
						String remarkString = "同终端下，手机号已存在[" + personMemberBase.getMemberNo() + "]的客户";
						logger.error(remarkString);
						throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_MOBILE_EXIST, remarkString);
					}
				}
			}

			// 查询导购是否已存在请求添加该客户微信的记录
			AddFriends findAddFriends = new AddFriends();
			findAddFriends.setNoWxGm(addAddFriends.getNoWxGm());
			findAddFriends.setNoWx(addAddFriends.getNoWx());
			findAddFriends.setMobile(addAddFriends.getMobile());// 手机
			findAddFriends.setNoQQ(addAddFriends.getNoQq());// QQ
			AddFriends addFriends = addFriendsDao.selectBySelectiveAndSync(findAddFriends); // 未分配或导购扫码未通过客户，包括同店同微信的其他导购扫码添加记录和终端终端未分配客户

			if (addFriends == null) { // 没有添加记录则新增
				addFriends = new AddFriends();
				insertAddFriends(addAddFriends, shopTerminalReturn, findGuidMemberReturn, addFriends);
			} else { // 有添加记录，则更新
				updateExistAddFriends(addAddFriends, findGuidMemberReturn, addFriends);
			}

			AddAddFriendsReturn addAddFriendsReturn = new AddAddFriendsReturn();
			addAddFriendsReturn.setCode(addFriends.getCode());
			logger.debug("addAddFriends(AddAddFriends) - end - return value={}", addAddFriendsReturn);
			return addAddFriendsReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("新增添加微信好友信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_ADD_ERROR, "新增添加微信好友信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：更新已存在的AddFriends信息
	 *
	 * @param addAddFriends
	 * @param findGuidMemberReturn
	 * @param addFriends
	 *
	 * @author 许新龙 CreateDate: 2018年4月30日
	 *
	 */
	private void updateExistAddFriends(AddAddFriends addAddFriends, FindGuidMemberReturn findGuidMemberReturn,
			AddFriends addFriends) {
		// 已是终端微信的好友（未分配到导购），不能再添加
		if (AddFriendsStatus.Y.name().equals(addFriends.getAddStatus())) {
			logger.error("已是终端微信的好友（未分配到导购），不能重复添加此客户：{}", addAddFriends);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_NOT_CLAIMED_ERROR, "已是终端微信的好友（未分配到导购），不能重复添加此客户");
		}

		logger.error("同终端同绑定微信的导购已请求添加该客户添加微信好友：{}", addFriends);
		AddFriends updateAddFriends = new AddFriends();
		updateAddFriends.setCode(addFriends.getCode());
		if (null != findGuidMemberReturn) {// 导购编号存在则指定,商户搜索添加不指定导购编号,2018年1月16日21:22:41,liDuanqiang
			updateAddFriends.setMemberNoGm(findGuidMemberReturn.getMemberNo());
			updateAddFriends.setMemberNameGm(findGuidMemberReturn.getMemberName());
		}
		if (StringUtils.isEmpty(addFriends.getNoWx())) {// 已存在的好友记录中没有微信号,则更新微信号
			updateAddFriends.setNoWx(addAddFriends.getNoWx());
			updateAddFriends.setWxOpenId(WxOpenIdUtils.generateWxOpenId(addAddFriends.getNoWx()));
		}
		updateAddFriends.setAlias(addAddFriends.getAlias());
		updateAddFriends.setNoQQ(addAddFriends.getNoQq());
		updateAddFriends.setWxAddType(addAddFriends.getWxAddType());
		updateAddFriends.setWxQrCode(addAddFriends.getWxQrCode());
		updateAddFriends.setNickName(addAddFriends.getNickNameWx());

		updateAddFriends.setHeadAddress(addAddFriends.getHeadAddress());
		updateAddFriends.setSex(addAddFriends.getSex());
		updateAddFriends.setRequestTime(new Date());
		updateAddFriends.setMobile(addAddFriends.getMobile());
		updateAddFriends.setValidation(addAddFriends.getValidation());
		updateAddFriends.setNickRemark(addAddFriends.getNickRemark());
		updateAddFriends.setLabelName(addAddFriends.getLabelName());
		addFriendsDao.updateByPrimaryKeySelective(updateAddFriends);// 值非空,则修改
	}

	/**
	 * 
	 *
	 * 方法说明：插入AddFriends信息
	 *
	 * @param addAddFriends
	 * @param shopTerminalReturn
	 * @param findGuidMemberReturn
	 * @param addFriends
	 *
	 * @author 许新龙 CreateDate: 2018年4月30日
	 *
	 */
	private void insertAddFriends(AddAddFriends addAddFriends, FindShopTerminalReturn shopTerminalReturn,
			FindGuidMemberReturn findGuidMemberReturn, AddFriends addFriends) {
		// add数据录入
		addFriends.setCode(GUID.generateCode());

		if (findGuidMemberReturn != null) {// 导购编号存在则指定,商户搜索添加不指定导购编号
			addFriends.setMemberNoGm(findGuidMemberReturn.getMemberNo());
			addFriends.setMemberNameGm(findGuidMemberReturn.getMemberName());
		}
		addFriends.setMerchantNo(shopTerminalReturn.getMerchantNo());
		addFriends.setMerchantName(shopTerminalReturn.getMerchantName());
		addFriends.setNoWxGm(addAddFriends.getNoWxGm());
		addFriends.setWxQrCode(addAddFriends.getWxQrCode());
		addFriends.setNoWx(addAddFriends.getNoWx());
		addFriends.setAlias(addAddFriends.getAlias());
		addFriends.setNickName(addAddFriends.getNickNameWx());
		addFriends.setHeadAddress(addAddFriends.getHeadAddress());
		addFriends.setSex(addAddFriends.getSex());
		addFriends.setAddStatus(AddFriendsStatus.N.name()); // 等待验证
		addFriends.setRequestTime(new Date());
		addFriends.setAcceptTime(addFriends.getRequestTime());
		addFriends.setCreateId(addAddFriends.getMemberNoGm());
		addFriends.setCreateDate(addFriends.getRequestTime());
		addFriends.setWxAddType(addAddFriends.getWxAddType());// 微信好友的添加方式
		addFriends.setWxOpenId(addAddFriends.getWxOpenId());
		if (StringUtils.isEmpty(addFriends.getWxOpenId())) {
			addFriends.setWxOpenId(WxOpenIdUtils.generateWxOpenId(addFriends.getNoWx()));// 强制生成wxOpenId
		}
		addFriends.setNoQQ(addAddFriends.getNoQq());
		addFriends.setMobile(addAddFriends.getMobile());
		addFriends.setValidation(addAddFriends.getValidation());
		addFriends.setNickRemark(addAddFriends.getNickRemark());
		addFriends.setLabelName(addAddFriends.getLabelName());
		addFriends.setMemberSrc(addAddFriends.getMemberSrc());
		addFriends.setRemark4(addAddFriends.getRemark4());
		if (StringUtils.isNotEmpty(addAddFriends.getMemberNoGm())) {// 有指定导购编号,则为导购主动添加,后续反查认领列表
			addFriends.setGmAddFlag(GmAddFlag.INITIATIVE.getCode());// 导购主动添加标识
		} else {
			addFriends.setGmAddFlag(GmAddFlag.PASSIVE.getCode());
		}
		addFriendsDao.insertSelective(addFriends);
	}

	@Override
	public AddAddFriendsReturn addAddWxFriends(AddAddFriends addAddFriends) throws TsfaServiceException {
		logger.debug("addAddWxFriends(AddAddFriends addAddFriends={}) - start", addAddFriends);

		AssertUtils.notNullAndEmpty(addAddFriends.getNoWxGm(), "导购微信号不能为空");

		// 查询终端微信
		FindShopTerminalReturn shopTerminalReturn = shopTerminalService
				.findShopTerminalNormal(addAddFriends.getNoWxGm());
		if (shopTerminalReturn == null) {
			logger.error("终端微信不存在或者无效：{}", addAddFriends.getNoWxGm());
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_NORMAL_ERROR, "无效的终端微信");
		}

		AddFriends addFriends = null;
		try {
			addFriends = new AddFriends();
			// add数据录入
			addFriends.setCode(GUID.generateCode());
			addFriends.setMemberName(addAddFriends.getMemberName());
//            addFriends.setMemberNoGm(findGuidMemberReturn.getMemberNo());
//            addFriends.setMemberNameGm(findGuidMemberReturn.getMemberName());
//            addFriends.setShopNo(shopTerminalReturn.getShopNo());
//            addFriends.setShopName(shopTerminalReturn.getShopName());
			addFriends.setMerchantNo(shopTerminalReturn.getMerchantNo());
			addFriends.setMerchantName(shopTerminalReturn.getMerchantName());
			addFriends.setNoWxGm(addAddFriends.getNoWxGm());

			addFriends.setWxQrCode(addAddFriends.getWxQrCode());
			addFriends.setNoWx(addAddFriends.getNoWx());

			addFriends.setHeadAddress(addAddFriends.getHeadAddress());
			addFriends.setSex(addAddFriends.getSex());
			addFriends.setMobile(addAddFriends.getMobile());
			if (StringUtils.isEmpty(addFriends.getRemark4())) {
				addFriends.setAddStatus(AddFriendsStatus.N.name()); // 等待验证
			} else {
				addFriends.setAddStatus(AddFriendsStatus.F.name()); // 添加失败
				addFriends.setRemark4(addAddFriends.getRemark4());
			}
			addFriends.setRequestTime(new Date());
			addFriends.setAcceptTime(addFriends.getRequestTime());
			addFriends.setCreateId(addAddFriends.getMemberNoGm());
			addFriends.setCreateDate(addFriends.getRequestTime());
			addFriends.setWxAddType(addAddFriends.getWxAddType());// 微信好友的添加方式
			addFriends.setWxOpenId(addAddFriends.getWxOpenId());
			addFriends.setNoQQ(addAddFriends.getNoQq());
			addFriends.setLabelName(addAddFriends.getLabelName());
			addFriends.setMemberSrc(addAddFriends.getMemberSrc());
			if (!StringUtils.isEmpty(addAddFriends.getMemberNoGm())) {// 有指定导购编号,则为导购主动添加,后续反查认领列表
				addFriends.setGmAddFlag(GmAddFlag.INITIATIVE.getCode());// 导购主动添加标识
			} else {
				addFriends.setGmAddFlag(GmAddFlag.PASSIVE.getCode());
			}
			logger.debug("addFriend(AddAddFriends) - end - return value={}", addFriends);
			addFriendsDao.insertSelective(addFriends);

			AddAddFriendsReturn addAddFriendsReturn = new AddAddFriendsReturn();
			addAddFriendsReturn.setCode(addFriends.getCode());

			logger.debug("addAddWxFriends(AddAddFriends) - end - return value={}", addAddFriendsReturn);
			return addAddFriendsReturn;
		} catch (TsfaServiceException e) {
			logger.error("新增添加微信好友信息错误！", e.getExceptionInfo());
			throw e;
		} catch (Exception e) {
			logger.error("新增添加微信好友信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_ADD_ERROR, "新增添加微信好友信息错误！", e);
		}
	}

	@Override
	public UpdateAddFriendsReturn updateAddFriends(UpdateAddFriends updateAddFriends) throws TsfaServiceException {
		logger.debug("updateAddFriends(UpdateAddFriends updateAddFriends={}) - start", updateAddFriends);

		AssertUtils.notNull(updateAddFriends);
		AssertUtils.notNullAndEmpty(updateAddFriends.getCode(), "Code不能为空");
		try {
			AddFriends addFriends = new AddFriends();
			// update数据录入
			addFriends.setCode(updateAddFriends.getCode());
			addFriends.setMemberNo(updateAddFriends.getMemberNo());
			addFriends.setMemberName(updateAddFriends.getMemberName());
			addFriends.setMemberNoGm(updateAddFriends.getMemberNoGm());
			addFriends.setMemberNameGm(updateAddFriends.getMemberNameGm());
			addFriends.setMerchantNo(updateAddFriends.getMerchantNo());
			addFriends.setMerchantName(updateAddFriends.getMerchantName());
			addFriends.setNoWxGm(updateAddFriends.getNoWxGm());
			addFriends.setWxQrCode(updateAddFriends.getWxQrCode());
			addFriends.setNoWx(updateAddFriends.getNoWx());
			addFriends.setAlias(updateAddFriends.getAlias());
			addFriends.setNickRemark(updateAddFriends.getNickRemark());
			addFriends.setMobile(updateAddFriends.getMobile());
			addFriends.setSex(updateAddFriends.getSex());
			addFriends.setValidation(updateAddFriends.getValidation());
			addFriends.setAddStatus(updateAddFriends.getAddStatus());
			addFriends.setAcceptTime(updateAddFriends.getAcceptTime());
			addFriends.setCreateId(updateAddFriends.getCreateId());
			addFriends.setCreateDate(updateAddFriends.getCreateDate());
			addFriends.setRemark(updateAddFriends.getRemark());
			addFriends.setRemark2(updateAddFriends.getRemark2());
			addFriends.setRemark3(updateAddFriends.getRemark3());
			addFriends.setRemark4(updateAddFriends.getRemark4());
			addFriends.setWxAddType(updateAddFriends.getWxAddType());
			AssertUtils.notUpdateMoreThanOne(addFriendsDao.updateByPrimaryKeySelective(addFriends));
			UpdateAddFriendsReturn updateAddFriendsReturn = new UpdateAddFriendsReturn();

			logger.debug("updateAddFriends(UpdateAddFriends) - end - return value={}", updateAddFriendsReturn);
			return updateAddFriendsReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("添加微信好友信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_UPDATE_ERROR, "添加微信好友信息更新信息错误！", e);
		}
	}

	@Override
	public FindAddFriendsReturn findAddFriends(FindAddFriends findAddFriends) throws TsfaServiceException {
		logger.debug("findAddFriends(FindAddFriends findAddFriends={}) - start", findAddFriends);

		AssertUtils.notNull(findAddFriends);
		AssertUtils.notNullAndEmpty(findAddFriends.getCode(), "Code不能为空");
		try {
			AddFriends addFriends = addFriendsDao.selectByPrimaryKey(findAddFriends.getCode());
			if (addFriends == null) {
				throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_NOT_EXIST_ERROR, "添加微信好友信息不存在");
			}
			FindAddFriendsReturn findAddFriendsReturn = new FindAddFriendsReturn();
			// find数据录入
			findAddFriendsReturn.setCode(addFriends.getCode());
			findAddFriendsReturn.setMemberNo(addFriends.getMemberNo());
			findAddFriendsReturn.setMemberName(addFriends.getMemberName());
			findAddFriendsReturn.setMemberNoGm(addFriends.getMemberNoGm());
			findAddFriendsReturn.setMemberNameGm(addFriends.getMemberNameGm());
			findAddFriendsReturn.setMerchantNo(addFriends.getMerchantNo());
			findAddFriendsReturn.setMerchantName(addFriends.getMerchantName());
			findAddFriendsReturn.setNoWxGm(addFriends.getNoWxGm());
			findAddFriendsReturn.setNickName(addFriends.getNickName());
			findAddFriendsReturn.setWxQrCode(addFriends.getWxQrCode());
			findAddFriendsReturn.setNoWx(addFriends.getNoWx());
			findAddFriendsReturn.setAlias(addFriends.getAlias());
			findAddFriendsReturn.setNickRemark(addFriends.getNickRemark());
			findAddFriendsReturn.setMobile(addFriends.getMobile());
			findAddFriendsReturn.setSex(addFriends.getSex());
			findAddFriendsReturn.setValidation(addFriends.getValidation());
			findAddFriendsReturn.setAddStatus(addFriends.getAddStatus());
			findAddFriendsReturn.setRequestTime(addFriends.getRequestTime());
			findAddFriendsReturn.setAcceptTime(addFriends.getAcceptTime());
			findAddFriendsReturn.setCreateId(addFriends.getCreateId());
			findAddFriendsReturn.setCreateDate(addFriends.getCreateDate());
			findAddFriendsReturn.setRemark(addFriends.getRemark());
			findAddFriendsReturn.setRemark2(addFriends.getRemark2());
			findAddFriendsReturn.setRemark3(addFriends.getRemark3());
			findAddFriendsReturn.setRemark4(addFriends.getRemark4());
			findAddFriendsReturn.setNoQQ(addFriends.getNoQQ());
			findAddFriendsReturn.setWxAddType(addFriends.getWxAddType());
			findAddFriendsReturn.setWxOpenId(addFriends.getWxOpenId());

			logger.debug("findAddFriends(FindAddFriends) - end - return value={}", findAddFriendsReturn);
			return findAddFriendsReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找添加微信好友信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_FIND_ERROR, "查找添加微信好友信息信息错误！", e);
		}

	}

	@Override
	public Page<FindAddFriendsPageReturn> findAddWxFriends(FindAddFriendsPage findAddFriendsPage)
			throws TsfaServiceException {
		logger.debug("findAddFriendsPage(FindAddFriendsPage findAddFriendsPage={}) - start", findAddFriendsPage);

		AssertUtils.notNull(findAddFriendsPage);
		List<FindAddFriendsPageReturn> returnList;
		int count = 0;
		try {
			returnList = addFriendsDao.findAddFriendsPage(findAddFriendsPage);
			count = addFriendsDao.findAddFriendsPageCount(findAddFriendsPage);
		} catch (Exception e) {
			logger.error("添加微信好友信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_FIND_PAGE_ERROR, "添加微信好友信息不存在错误.！", e);
		}
		Page<FindAddFriendsPageReturn> returnPage = new Page<FindAddFriendsPageReturn>(returnList, count,
				findAddFriendsPage);

		logger.debug("findAddFriendsPage(FindAddFriendsPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public Page<FindAddFriendsPageReturn> findAddFriendsPage(FindAddFriendsPage findAddFriendsPage)
			throws TsfaServiceException {
		logger.debug("findAddFriendsPage(FindAddFriendsPage findAddFriendsPage={}) - start", findAddFriendsPage);

		AssertUtils.notNull(findAddFriendsPage);
		List<FindAddFriendsPageReturn> returnList = null;
		int count = 0;
		try {
			count = addFriendsDao.findAddFriendsPageCount(findAddFriendsPage);
			if (count > 0) {
				returnList = addFriendsDao.findAddFriendsPage(findAddFriendsPage);
			}
		} catch (Exception e) {
			logger.error("添加微信好友信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_FIND_PAGE_ERROR, "添加微信好友信息不存在错误.！", e);
		}
		Page<FindAddFriendsPageReturn> returnPage = new Page<FindAddFriendsPageReturn>(returnList, count,
				findAddFriendsPage);

		logger.debug("findAddFriendsPage(FindAddFriendsPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public UpdateAddFriendStatusReturn updateAddFriendsStatus(UpdateAddFriendStatus updateAddFriendStatus)
			throws TsfaServiceException {
		logger.debug("updateAddFriendsStatus(UpdateAddFriendStatus updateAddFriendStatus={}) - start",
				updateAddFriendStatus);

		AssertUtils.notNull(updateAddFriendStatus);
		if (StringUtils.isEmpty(updateAddFriendStatus.getMemberNoGm())) { // 有导购编号就按导购编号查询
			AssertUtils.notNullAndEmpty(updateAddFriendStatus.getNoWxGm(), "导购微信号不能为空");
		}
		AssertUtils.notNullAndEmpty(updateAddFriendStatus.getNoWx(), "客户微信号不能为空");

		UpdateAddFriendStatusReturn updateAddFriendStatusReturn = null;
		try {
			AddFriends findAddFriends = new AddFriends();
			if (StringUtils.isNotEmpty(updateAddFriendStatus.getCode())) {
				if (!updateAddFriendStatus.getCode().startsWith(KeyConstant.OMS_PMB_LIST_SEARCH_ADD_PREFIX)) {
					findAddFriends.setCode(updateAddFriendStatus.getCode());
				} else { // 已是导购客户，添加为微信好友
					// 1、更新pmb微信基本信息{@link IPersonMemberImService#syncPersonWxInfo()}
					String pmbCodeAndGM = updateAddFriendStatus.getCode()
							.replaceAll(KeyConstant.OMS_PMB_LIST_SEARCH_ADD_PREFIX, "");// PMB主键+","+导购编号
					String[] pmbCodeAndGMArr = pmbCodeAndGM.split(",");
					String pmbCode = null;
					String gmCode = null;
					if (pmbCodeAndGMArr.length > 1) {
						pmbCode = pmbCodeAndGMArr[0];
						gmCode = pmbCodeAndGMArr[1];
					} else {
						pmbCode = pmbCodeAndGMArr[0];// 兼容只有客户编号的情形
						logger.warn("OMS客户列表中点击添加好友,缺失导购编号!!!");
					}
					PersonMemberBase pmb = personMemberBaseDao.selectByPrimaryKey(pmbCode);// 查询pmb基本信息
					FindPersonMember findPersonMember = new FindPersonMember();
					findPersonMember.setMemberNo(pmb.getMemberNo());// 客户编号
					findPersonMember.setMemberNoGm(gmCode);// 导购编号
					FindPersonMemberReturn pm = personMemberService.findPersonMemberByMemberNoAndGM(findPersonMember);// pmb用户客户编号查询关系
					SyncPersonWxInfoRequest syncPersonWxInfoRequest = new SyncPersonWxInfoRequest();
					syncPersonWxInfoRequest.setAlias(updateAddFriendStatus.getAlias());
					syncPersonWxInfoRequest.setHeadAddress(updateAddFriendStatus.getHeadAddress());
					syncPersonWxInfoRequest.setMemberNo(pmb.getMemberNo());
					syncPersonWxInfoRequest.setMemberNoGm(pm.getMemberNoGm());
					syncPersonWxInfoRequest.setNickNameWx(updateAddFriendStatus.getNickNameWx());
					syncPersonWxInfoRequest.setNoWx(updateAddFriendStatus.getNoWx());
					syncPersonWxInfoRequest.setNoWxGm(updateAddFriendStatus.getNoWxGm());
					syncPersonWxInfoRequest.setSex(updateAddFriendStatus.getSex());
					syncPersonWxInfoRequest.setMobile(updateAddFriendStatus.getMobile());
					SyncPersonWxInfoResponse syncRes = personMemberImService.syncPersonWxInfo(syncPersonWxInfoRequest);
					// 2、修改pm表wx_friends标识
					UpdatePersonMember updatePersonMember = new UpdatePersonMember();
					BeanUtils.copyProperties(pm, updatePersonMember);
					updatePersonMember.setWxFriends(1);// 已成为好友
					personMemberService.updatePersonMember(updatePersonMember);
					updateAddFriendStatusReturn = new UpdateAddFriendStatusReturn();
					updateAddFriendStatusReturn.setMemberNoGm(pm.getMemberNoGm());
					updateAddFriendStatusReturn.setNoWxGm(updateAddFriendStatus.getNoWxGm());
					updateAddFriendStatusReturn.setMemberNo(pmb.getMemberNo());
					updateAddFriendStatusReturn.setMemberName(pm.getMemberName());
					updateAddFriendStatusReturn.setMobile(pmb.getMobile());
					updateAddFriendStatusReturn.setNickNameRemarkLocal("");// 本地备注
					updateAddFriendStatusReturn.setVersion(syncRes.getVersion());// 版本号
					/**
					 * 客户分类相关信息 DZP 2018-12-14
					 */
					updateAddFriendStatusReturn.setCodePm(pm.getCode());
					updateAddFriendStatusReturn.setPmTypeCode(pm.getPmTypeCode());
					updateAddFriendStatusReturn.setPmTypeName(pm.getPmTypeName());
					return updateAddFriendStatusReturn;
				}
			} else { // 主键不存在,则按具体条件修改
				findAddFriends.setNoWxGm(updateAddFriendStatus.getNoWxGm());
				findAddFriends.setNoWx(updateAddFriendStatus.getNoWx());
			}
			AddFriends addFriends = addFriendsDao.selectBySelectiveAndSync(findAddFriends); // 未分配或导购扫码未通过客户
			/**
			 * 查询终端
			 */
			FindShopTerminalReturn findShopTerminalReturn = shopTerminalService
					.findShopTerminalByWx(updateAddFriendStatus.getNoWxGm());
			AssertUtils.notNull(findShopTerminalReturn, "终端不存在或已注销！");
			String code = null;

			if (addFriends != null) { // 不为空
				AddFriends updateAddFriends = new AddFriends();
				updateAddFriends.setCode(addFriends.getCode());
				updateAddFriends.setAcceptTime(new Date());
				code = addFriends.getCode();

				// 验证通过
				if (updateAddFriendStatus.isStatus()) {
					// 主动加的情况下，搜索和加好友返回的微信号可能不一样，所以每次都更新微信号
					updateAddFriends.setNoWx(updateAddFriendStatus.getNoWx());
					if (StringUtils.isNotEmpty(updateAddFriendStatus.getAlias())) { // 微信别名,默认取请求参数
						updateAddFriends.setAlias(updateAddFriendStatus.getAlias());
					} else {
						updateAddFriends.setAlias(addFriends.getAlias());
					}
					if (StringUtils.isNotEmpty(updateAddFriendStatus.getNickNameWx())) { // 微信昵称,默认取请求参数
						updateAddFriends.setNickName(updateAddFriendStatus.getNickNameWx());
					} else {
						updateAddFriends.setNickName(addFriends.getNickName());
					}

					// IM 或者 app搜索添加，可能会先打备注
					if (StringUtils.isEmpty(addFriends.getNickRemark())
							&& StringUtils.isNotEmpty(updateAddFriendStatus.getNickNameRemarkWx())) { // 微信昵称备注,默认取请求参数
						updateAddFriends.setNickRemark(updateAddFriendStatus.getNickNameRemarkWx());
					} else {
						updateAddFriends.setNickRemark(addFriends.getNickRemark());
					}
					if (StringUtils.isNotEmpty(updateAddFriendStatus.getHeadAddress())) { // 微信头像地址,默认取请求参数
						updateAddFriends.setHeadAddress(updateAddFriendStatus.getHeadAddress());
					} else {
						updateAddFriends.setHeadAddress(addFriends.getHeadAddress());
					}
					if (StringUtils.isNotEmpty(updateAddFriendStatus.getSex())) { // 性别,默认取请求参数
						updateAddFriends.setSex(updateAddFriendStatus.getSex());
					} else {
						updateAddFriends.setSex(addFriends.getSex());
					}
					if (StringUtils.isNotEmpty(updateAddFriendStatus.getLongitude())) { // 经度,默认取请求参数
						updateAddFriends.setLongitude(updateAddFriendStatus.getLongitude());
					} else {
						updateAddFriends.setLongitude(addFriends.getLongitude());
					}
					if (StringUtils.isNotEmpty(updateAddFriendStatus.getLatitude())) { // 纬度,默认取请求参数
						updateAddFriends.setLatitude(updateAddFriendStatus.getLatitude());
					} else {
						updateAddFriends.setLatitude(addFriends.getLatitude());
					}
					if (StringUtils.isNotEmpty(updateAddFriendStatus.getLabelName())) { // 纬度,默认取请求参数
						updateAddFriends.setLabelName(updateAddFriendStatus.getLabelName());
					} else {
						updateAddFriends.setLabelName(addFriends.getLabelName());
					}

					if (StringUtils.isNotEmpty(updateAddFriendStatus.getMobile())) {
						if (StringUtils.isEmpty(addFriends.getMobile())
								|| !updateAddFriendStatus.getMobile().equals(addFriends.getMobile())) {
							updateAddFriends.setMobile(updateAddFriendStatus.getMobile());
						}
					}

					updateAddFriends.setNoWxGm(updateAddFriendStatus.getNoWxGm());
					updateAddFriends.setAddStatus(AddFriendsStatus.Y.name());
					updateAddFriends.setWxAddType(addFriends.getWxAddType());// 添加方式,商户运营主动搜索添加,liduanqiang

					/**
					 * 1、导购编号不为空,则添加方式为导购扫客户,需新增客户信息; 2、导购编号为空，则更新客户信息和状态,再通过OMS分配功能指定所属导购
					 */
					if (StringUtils.isNotEmpty(addFriends.getMemberNoGm())) {
						logger.info("导购扫客户,新增客户信息");
						updateAddFriends.setMerchantNo(addFriends.getMerchantNo());
						updateAddFriends.setMemberNoGm(addFriends.getMemberNoGm());
						updateAddFriends.setNoWxGm(updateAddFriendStatus.getNoWxGm());
						updateAddFriends.setMobile(addFriends.getMobile());
						updateAddFriends.setWxAddType(addFriends.getWxAddType());
						updateAddFriends.setWxQrCode(addFriends.getWxQrCode());
						updateAddFriends.setAcceptTime(addFriends.getAcceptTime());
						updateAddFriendStatusReturn = this.addMember(updateAddFriends);
						updateAddFriends.setMemberNo(updateAddFriendStatusReturn.getMemberNo());
						updateAddFriends.setMemberName(updateAddFriendStatusReturn.getMemberName());
						updateAddFriends.setMemberNoGm(addFriends.getMemberNoGm());
						updateAddFriends.setMemberTime(new Date(updateAddFriendStatusReturn.getVersion()));
					} else {
						logger.info("客户扫导购,修改客户微信基本信息");
					}
				} else { // 拒绝
					updateAddFriends.setAddStatus(AddFriendsStatus.F.name());
				}
				updateAddFriends.setMemberNoGm(updateAddFriendStatus.getMemberNoGm());
				// 更新添加微信好友记录
				addFriendsDao.updateByPrimaryKeySelective(updateAddFriends);
				logger.info("已更新添加客户微信好友状态：{}", addFriends.getCode());

				// 新增好友发送相关消息，如问候、红包等
				if (!AddFriendsStatus.Y.name().equals(addFriends.getAddStatus()) && updateAddFriendStatus.isStatus()) {
					this.sendMessageByNewFriends(addFriendsDao.selectByPrimaryKey(addFriends.getCode()));
				}
			} else if (updateAddFriendStatus.isStatus()) { // 为空：客户扫码添加导购,创建添加好友记录,再通过OMS分配功能指定所属导购

				// 获取锁
				String claimLockName = null;
				String claimLockValue = null;
				try {

					// 验证该门店是否可以添加该好友
					this.validateMember(updateAddFriendStatus.getMemberNoGm(), updateAddFriendStatus.getNoWxGm(),
							updateAddFriendStatus.getNoWx(), updateAddFriendStatus.getAlias());
					// 新增记录
					addFriends = new AddFriends();
					code = GUID.generateCode();
					addFriends.setCode(code);
					// addFriends.setShopNo(findShopTerminalReturn.getShopNo());
					addFriends.setShopName(findShopTerminalReturn.getShopName());
					addFriends.setMerchantNo(findShopTerminalReturn.getMerchantNo());
					addFriends.setMerchantName(findShopTerminalReturn.getMerchantName());
					addFriends.setNoWxGm(updateAddFriendStatus.getNoWxGm());
					addFriends.setGmAddFlag(GmAddFlag.PASSIVE.getCode()); // 非导购主动添加
					addFriends.setWxAddType(MemberAddType.MEMBER_SCAN.getCode()); // 客户扫导购
					addFriends.setWxQrCode(null); // 客户扫导购,获取不到客户微信二维码
					addFriends.setNoWx(updateAddFriendStatus.getNoWx());
					addFriends.setAlias(updateAddFriendStatus.getAlias());
					addFriends.setHeadAddress(updateAddFriendStatus.getHeadAddress());
					addFriends.setNickName(updateAddFriendStatus.getNickNameWx());
					addFriends.setNickRemark(updateAddFriendStatus.getNickNameRemarkWx());
					addFriends.setMobile(updateAddFriendStatus.getMobile());
					addFriends.setSex(updateAddFriendStatus.getSex());
					addFriends.setLongitude(updateAddFriendStatus.getLongitude());
					addFriends.setLatitude(updateAddFriendStatus.getLatitude());
					addFriends.setAddStatus(AddFriendsStatus.Y.name()); // 验证通过
					addFriends.setRequestTime(new Date());
					addFriends.setAcceptTime(new Date());
					addFriends.setCreateDate(new Date());
					addFriends.setValidation(updateAddFriendStatus.getValidation());
					claimLockName = "ADDFRIENDS-KEY-" + addFriends.getNoWxGm() + ":" + addFriends.getNoWx();

					claimLockValue = redisLock.getLockNoWait(claimLockName, 3);

					addFriends.setLabelName(updateAddFriendStatus.getLabelName());
					addFriends.setMemberNoGm(updateAddFriendStatus.getMemberNoGm());
					addFriendsDao.insertSelective(addFriends);
				} catch (TsfaServiceException e) {
					throw e;
				} catch (Exception e) {
					logger.error("为新增客户addFriends错误！", e);
					throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_UPDATE_ERROR, "为新增客户addFriends错误！", e);
				} finally { // 不管成功与否，保证锁能释放
					// 释放锁
					if (StringUtils.isNotEmpty(claimLockName)) {
						redisLock.releaseLock(claimLockName, claimLockValue);
					}
				}
				logger.info("已新增添加客户微信好友记录：{}", addFriends.getCode());

				// 新增好友发送相关消息，如问候、红包等
				this.sendMessageByNewFriends(addFriends);
			}

			// **************判断是否存在自动认领************** start
			String lockName = RedisLock.LOCK_NAME_PREFIX + updateAddFriendStatus.getNoWxGm();
			String value = redis.get(lockName);
			if (value != null && !value.equals("")) {
				String[] memberNoGm = value.split(",");
				DistributionMember distributionMember = new DistributionMember();
				distributionMember.setMemberNoGm(memberNoGm[1]);
				distributionMember.setCode(code);
				distributionMember(distributionMember);
				return updateAddFriendStatusReturn;
			}
			// **************判断是否存在自动认领************** end

			// 二合一版本，只有一个导购的情况，自动认领，默认分配给商户下的一个导购 start
			if (StringUtils.isNotEmpty(findShopTerminalReturn.getMerchantNo())) {
				FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
				findGmAssistantShop.setMerchantNo(findShopTerminalReturn.getMerchantNo());
				findGmAssistantShop.setNoWx(findShopTerminalReturn.getNoWx());
				findGmAssistantShop.setSource(true);// 下级
				List<FindGmAssistantShopReturn> list = gmAssistantShopService
						.findGmAssistantShopList(findGmAssistantShop);
				logger.info("二合一版本-----自动认领------list={}", list);
				if (null != list && list.size() == 1) {
					FindGmAssistantShopReturn guidInfoShop = list.get(0);
					DistributionMember distributionMember = new DistributionMember();
					distributionMember.setMemberNoGm(guidInfoShop.getAssistantNo());
					distributionMember.setCode(code);
					distributionMember(distributionMember);
					return updateAddFriendStatusReturn;
				}
			}
			// 二合一版本，只有一个导购的情况，自动认领，默认分配给商户下的一个导购 end

			// 查询指定微信未分配的客户
			/*
			 * FIXBUG 591 AddFriends repeatFriendsQuery = new AddFriends();
			 * repeatFriendsQuery.setNoWx(updateAddFriendStatus.getNoWx());
			 * repeatFriendsQuery.setAlias(updateAddFriendStatus.getAlias());
			 * List<AddFriends> friendsList =
			 * addFriendsDao.selectByRepeatAndClaim(repeatFriendsQuery); if(friendsList !=
			 * null && !friendsList.isEmpty()) { for(AddFriends repeatFriends : friendsList)
			 * { if(!code.equals(repeatFriends.getCode())) { logger.warn("删除重复未分配客户记录: {}",
			 * repeatFriends); addFriendsDao.deleteByPrimaryKey(repeatFriends.getCode()); }
			 * } }
			 */
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("添加微信好友信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_UPDATE_ERROR, "添加微信好友信息更新信息错误！", e);
		}

		logger.debug("updateAddFriendsStatus(UpdateAddFriendStatus) - end - return value={}",
				updateAddFriendStatusReturn);
		return updateAddFriendStatusReturn;
	}

	/**
	 * 
	 *
	 * 方法说明：新增好友发送相关消息，如问候、红包等
	 *
	 * @param addFriends
	 *
	 * @author 曾垂瑜 CreateDate: 2018年3月20日
	 *
	 */
	private void sendMessageByNewFriends(AddFriends addFriends) {
		FindMerchantParams findMerchantParams = new FindMerchantParams();
		String groupName = "push_switch";// 组名
		findMerchantParams.setGroupName(groupName);
		findMerchantParams.setMerchantNo(addFriends.getMerchantNo());
		Map<String, String> paramsMap = merchantParamsService.findMerchantParamsByGroup(findMerchantParams);

		if (MapUtils.isNotEmpty(paramsMap)) {
			// 客户认领前发送个人问候(默认为认领后发送)
			if (paramsMap.get("greetings") != null && "ON".equals(paramsMap.get("greetings"))
					&& "beforeClaim".equals(paramsMap.get("greetingsTime"))) {
				this.sendGreetings(addFriends, paramsMap.get("greetingsInfo"));
			}

			// 红包
			if (paramsMap.get("redPack") != null && "ON".equals(paramsMap.get("redPack"))) {
				logger.info("添加好友根据商户配置发红包：中控{}发给微信{}红包", addFriends.getNoWxGm(), addFriends.getNoWx());
				wxRedpackDetailInfoService.sendRedPackAfterAddWxFriends(paramsMap, addFriends.getNoWx(),
						addFriends.getNoWxGm(), addFriends.getMerchantNo(), "", addFriends.getNickName());
			}
		}

//		FindMerchantDto findMerchant = new FindMerchantDto();
//		findMerchant.setMerchantNo(addFriends.getMerchantNo());
//		FindMerchantReturnDto merchant = merchantService.selectMerchant(findMerchant);
//		if(com.lj.business.mec.emus.Status.use.toString().equals(merchant.getEshopStatus())) {	// 移动终端商户，则发送终端商城访问地址
//			this.sendMec(addFriends);
		// 异步推送终端配置信息
//			ayncSendMessageByNonMemberService.sendMessage(addFriends);
//		}
	}

	/**
	 * 
	 *
	 * 方法说明：发送个人问候
	 *
	 * @param addFriends
	 * @param greetingsInfo 商户自定义问候话术，目前只支持配置一条
	 *
	 * @author 曾垂瑜 CreateDate: 2018年3月20日
	 *
	 */
	public void sendGreetings(AddFriends addFriends, String greetingsInfo) {
		try {
			// 如果商户没有自定义问候话术，则从基础配置信息里随机获取一条
			if (StringUtils.isEmpty(greetingsInfo)) {
				Map<String, String> personalGreetingsMap = localCacheSystemParams.getSystemParamGroup("ms",
						"personalGreetings");
				String greetingKey = "greeting" + ((int) (personalGreetingsMap.size() * Math.random()) + 1);
				greetingsInfo = personalGreetingsMap.get(greetingKey);
			}
			// 获取随机数量和内容的问候表情
			Object[] arr = new Object[WeiChatUtils.getOccur(greetingsInfo, "{")];
			Map<String, String> greetingEmojiMap = localCacheSystemParams.getSystemParamGroup("ms", "greetingEmoji");
			for (int i = 0; i < arr.length; i++) {
				arr[i] = "";
				for (int j = 0; j < (int) (3 * Math.random()) + 1; j++) {// 暂定一个坑随机填1~3个表情
					String emojiKey = "emoji" + ((int) (greetingEmojiMap.size() * Math.random()) + 1);
					arr[i] += greetingEmojiMap.get(emojiKey);
				}
			}
//			logger.info("替换表情:" + arr);
			// 整合话术及表情,生成问候语
			greetingsInfo = MessageFormat.format(greetingsInfo, arr);

			// 发送消息
			SendImChatByNonMember chatByNonMember = new SendImChatByNonMember();
			chatByNonMember.setNoWxShop(addFriends.getNoWxGm());
			chatByNonMember.setNoWx(addFriends.getNoWx());
			chatByNonMember.setAlias(addFriends.getAlias());
			chatByNonMember.setType(ChatInfoType.TEXT.getCode());
			chatByNonMember.setContent(greetingsInfo);// 个人问候
			chatByNonMember.setMsgSource(MessageSource.SA.getCode());
			chatByNonMember.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
			logger.info("发送个人问候：{}", chatByNonMember);
			imChatInfoService.sendChatByNonMember(chatByNonMember);
		} catch (Exception e) {
			logger.error("发送个人问候失败", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：通知终端下所有导购认领客户
	 *
	 * @param addFriends
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月16日
	 *
	 */
	private void memberClaim(AddFriends addFriends) {
		try {
//			FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
//			findGuidMemberDto.setMerchantNo(addFriends.getMerchantNo());
//			findGuidMemberDto.setShopNo(addFriends.getShopNo());
//			findGuidMemberDto.setNoWx(addFriends.getNoWxGm());

//			List<GuidMemberReturnDto> guidList = guidMemberService.findGuidMemberList(findGuidMemberDto);

			FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
			findGmAssistantShop.setMerchantNo(addFriends.getMerchantNo());
			findGmAssistantShop.setNoWx(addFriends.getNoWxGm());
			List<GmAssistantShop> guidList = gmAssistantShopService.findGmAssistantListByWx(findGmAssistantShop);

			if (guidList != null && !guidList.isEmpty()) {
				// 通知终端下所有导购认领客户
				List<String> memberNoGmList = new ArrayList<String>();
				for (GmAssistantShop guid : guidList) {
					// 只有绑定了相同微信号的导购才能认领
					if (guid.getNoWx().equals(addFriends.getNoWxGm())) {
						memberNoGmList.add(guid.getMemberNo());
					}
				}

				// 通知
				MemberClaimMessage memberClaimMessage = new MemberClaimMessage();
				memberClaimMessage.setMemberNoGmList(memberNoGmList);
				memberClaimMessage.setCode(addFriends.getCode());
				memberClaimMessage.setNoWx(addFriends.getNoWx());
				memberClaimMessage.setAlias(addFriends.getAlias());
				memberClaimMessage.setNickNameWx(addFriends.getNickName());
				memberClaimMessage.setNickNameRemarkWx(addFriends.getNickRemark());
				memberClaimMessage.setHeadAddress(addFriends.getHeadAddress());
				memberClaimMessage.setSex(addFriends.getSex());
				memberClaimMessage.setCreateTime(addFriends.getAcceptTime().getTime());

				for (String memberNoGm : memberClaimMessage.getMemberNoGmList()) {
					IContactsService basic = commonService.getHessianContactsService(memberNoGm);
					basic.sendMemberClaimNotifyMessage(memberClaimMessage, memberNoGm);
				}
				logger.info("已通知绑定同一微信的所有导购认领客户：{}", memberClaimMessage);
			}
		} catch (Exception e) {
			logger.error("知终端下所有导购认领客户失败", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：创建客户信息
	 *
	 * @param addFriends
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月13日
	 *
	 */
	private UpdateAddFriendStatusReturn addMember(AddFriends addFriends) {
		logger.info("UpdateAddFriendStatusReturn addMember( AddFriends addFriends={}", addFriends);
		// 创建客户信息
		AddPersonMemberByWx addPersonMemberByWx = new AddPersonMemberByWx();
		addPersonMemberByWx.setMemberNoGuid(addFriends.getMemberNoGm());
		addPersonMemberByWx.setNoWxGm(addFriends.getNoWxGm());
		addPersonMemberByWx.setNoWx(addFriends.getNoWx());
		addPersonMemberByWx.setAlias(addFriends.getAlias());

		String mn = "";// 优先显示微信备注（微信备注=客户资料备注），没有备注显示微信昵称
		if (StringUtils.isNotEmpty(addFriends.getNickRemark())) {
			mn = addFriends.getNickRemark();
		} else if (StringUtils.isNotEmpty(addFriends.getNickName())) {
			mn = addFriends.getNickName();
		} else {
			mn = addFriends.getAlias();
		}
		addPersonMemberByWx.setMemberName(mn);
		addPersonMemberByWx.setNickNameWx(addFriends.getNickName());
		addPersonMemberByWx.setHeadAddress(addFriends.getHeadAddress());
		addPersonMemberByWx.setSex(addFriends.getSex());
		addPersonMemberByWx.setLongitude(addFriends.getLongitude());
		addPersonMemberByWx.setLatitude(addFriends.getLatitude());
		addPersonMemberByWx.setScanAddress(addFriends.getWxQrCode());
		addPersonMemberByWx.setAddType(addFriends.getWxAddType());
		addPersonMemberByWx.setWxOpenId(addFriends.getWxOpenId());
		addPersonMemberByWx.setNoQQ(addFriends.getNoQQ());
		addPersonMemberByWx.setMemberSrc(addFriends.getMemberSrc());
		addPersonMemberByWx.setGmAddFlag(addFriends.getGmAddFlag());
		addPersonMemberByWx.setAcceptTime(addFriends.getAcceptTime());
		addPersonMemberByWx.setMobile(addFriends.getMobile());
		addPersonMemberByWx.setNickNameRemarkWx(addFriends.getNickRemark());
		addPersonMemberByWx.setLabelName(addFriends.getLabelName());
		addPersonMemberByWx.setMobile(addFriends.getMobile());
		// 创建客户
		AddPersonMemberByWxReturn personMember = personMemberService.addPersonMemberByWx(addPersonMemberByWx);
		logger.info("已新增客户: {}", personMember);

		UpdateAddFriendStatusReturn updateAddFriendStatusReturn = new UpdateAddFriendStatusReturn();
		updateAddFriendStatusReturn.setNoWxGm(addFriends.getNoWxGm());
		updateAddFriendStatusReturn.setMemberNo(personMember.getMemberNo());
		updateAddFriendStatusReturn.setMemberName(personMember.getMemberName());
		updateAddFriendStatusReturn.setMemberNoGm(addFriends.getMemberNoGm());
		updateAddFriendStatusReturn.setMobile(personMember.getMobile());
		updateAddFriendStatusReturn.setNickNameRemarkLocal(personMember.getNickNameRemarkLocal());
		updateAddFriendStatusReturn.setVersion(personMember.getVersion());
		/**
		 * 客户分类相关信息 DZP 2018-12-14
		 */
		updateAddFriendStatusReturn.setCodePm(personMember.getCodePm());
		updateAddFriendStatusReturn.setPmTypeCode(personMember.getPmTypeCode());
		updateAddFriendStatusReturn.setPmTypeName(personMember.getPmTypeName());
		return updateAddFriendStatusReturn;
	}

	@Override
	public DistributionMemberReturn distributionMember(DistributionMember distributionMember) {
		logger.debug("distributionMember(DistributionMember distributionMember={}) - start", distributionMember); //$NON-NLS-1$

		AssertUtils.notNull(distributionMember);
		AssertUtils.notNullAndEmpty(distributionMember.getCode(), "Code不能为空");
		AssertUtils.notNullAndEmpty(distributionMember.getMemberNoGm(), "导购编号不能为空");

		AddFriends addFriends = addFriendsDao.selectByPrimaryKey(distributionMember.getCode());
		if (addFriends == null) {
			logger.error("没有找到客户添加记录： {}", distributionMember.getCode());
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_NOT_EXIST_ERROR, "没有找到客户添加记录");
		}
		logger.debug("找到客户添加记录： {}", addFriends);
		if (!AddFriendsStatus.Y.name().equals(addFriends.getAddStatus())) {
			logger.error("客户未验证通过添加微信好友请求");
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_UPDATE_ERROR,
					"客户" + addFriends.getNickName() + "未验证通过添加微信好友请求");
		}
		if (StringUtils.isNotEmpty(addFriends.getMemberNoGm()) && StringUtils.isNotEmpty(addFriends.getMemberNo())) {
			logger.error("已为客户分配所属导购");
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_HAND_DISTRIBUTION_ERROR,
					"已为客户" + addFriends.getNickName() + "分配所属导购");
		}

		// 查询导购信息
//		FindGuidMember findGuidMember = new FindGuidMember();
//		findGuidMember.setMemberNo(distributionMember.getMemberNoGm());
//		FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
		FindGmAssistantShopReturn findGmAssistantShopReturn = gmAssistantShopService
				.findGmByWxAndNo(addFriends.getNoWxGm(), distributionMember.getMemberNoGm());
		if (null == findGmAssistantShopReturn) {
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_DISTRIBUTION_ERROR, "导购不存在");
		}

//		if(!addFriends.getShopNo().equals(findGuidMemberReturn.getShopNo())) {
//			logger.error("只能将客户分配给同一门店的导购");
//			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_DISTRIBUTION_ERROR, "只能将客户"+addFriends.getNickName()+"分配给同一门店的导购");
//		}
//		if(!addFriends.getNoWxGm().equals(findGmAssistantShopReturn.getNoWx())) {
//			String remark = "只能将客户"+addFriends.getNickName()+"分配给绑定了微信" + addFriends.getNoWxGm() + "的导购";
//			logger.error(remark);
//			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_DISTRIBUTION_ERROR, remark);
//		}

		// 根据导购绑定的微信号，检查客户微信是否已添加同终端其他导购为微信好友
		FindPersonMemberByChat findPersonMemberByChat = new FindPersonMemberByChat();
		findPersonMemberByChat.setNoWxGm(addFriends.getNoWxGm());
		findPersonMemberByChat.setNoWx(addFriends.getNoWx());
		FindPersonMemberByChatReturn findPersonMemberReturn = personMemberImService
				.findPersonMemberByChat(findPersonMemberByChat);
		if (findPersonMemberReturn != null) {
			if (distributionMember.getMemberNoGm().equals(findPersonMemberReturn.getMemberNoGm())) {
				logger.error("导购已认领该客户");
				throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_REPEATEDLY_ERROR,
						"导购已认领该客户" + addFriends.getNickName());
			} else {
				String remark = "终端其他导购[" + findPersonMemberReturn.getMemberNameGm() + "]已认领该客户"
						+ addFriends.getNickName();
				logger.error(remark);
				throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_REPEATEDLY_OTHER_ERROR, remark);
			}
		}

		// 获取认领锁
		String claimLockName = this.getClaimKey(addFriends.getCode());
		String claimLockValue = null;
		try {
			claimLockValue = redisLock.getLockNoWait(claimLockName);
		} catch (Exception e) {
			logger.error("已有导购正在认领该客户:" + addFriends.getNickName());
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_HAND_DISTRIBUTION_ERROR,
					"已有导购正在认领该客户:" + addFriends.getNickName());
		}

		UpdateAddFriendStatusReturn updateAddFriendStatusReturn = null;
		final String code = addFriends.getCode();
		try {
			/**
			 * 新增客户 ，创建客户信息 邀约，非邀约逻辑，包含认领后的通知
			 */
			addFriends.setMemberNoGm(distributionMember.getMemberNoGm());
			updateAddFriendStatusReturn = this.addMember(addFriends);
		} catch (final TsfaServiceException e) {
			// 另起线程，事务外更新为失败
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					AddFriends updateAddFriends = new AddFriends();
					updateAddFriends.setCode(code);
					updateAddFriends.setAddStatus(AddFriendsStatus.F.name()); // 失败
					updateAddFriends
							.setRemark4(e.getExceptionInfo().length() > 50 ? e.getExceptionInfo().substring(0, 50)
									: e.getExceptionInfo());
					addFriendsDao.updateByPrimaryKeySelective(updateAddFriends);
				}
			});
			throw e;
		}

		DistributionMemberReturn distributionMemberReturn = null;
		try {
			// 修改添加记录所属导购和客户信息
			AddFriends updateAddFriends = new AddFriends();
			updateAddFriends.setCode(addFriends.getCode());
			updateAddFriends.setMemberNoGm(findGmAssistantShopReturn.getAssistantNo());
			updateAddFriends.setMemberNameGm(findGmAssistantShopReturn.getAssistantName());
			updateAddFriends.setMemberNo(updateAddFriendStatusReturn.getMemberNo());
			updateAddFriends.setMemberName(updateAddFriendStatusReturn.getMemberName());
			updateAddFriends.setMemberTime(new Date(updateAddFriendStatusReturn.getVersion()));
			addFriendsDao.updateByPrimaryKeySelective(updateAddFriends);
			logger.info("已更新添加记录所属导购和客户信息");

			// 通知导购客户端新增客户
			final NewFriendMessage newFriendMessage = new NewFriendMessage();
			newFriendMessage.setCode(addFriends.getCode());
			newFriendMessage.setMemberNoGm(updateAddFriends.getMemberNoGm());
			newFriendMessage.setMemberNameGm(updateAddFriends.getMemberNameGm());
			newFriendMessage.setNoWxGm(addFriends.getNoWxGm());
			newFriendMessage.setNoWx(addFriends.getNoWx());
			newFriendMessage.setAlias(addFriends.getAlias());
			newFriendMessage.setNickNameWx(addFriends.getNickName());
			newFriendMessage.setNickNameRemarkWx(addFriends.getNickRemark());
			newFriendMessage.setNickNameRemarkLocal(updateAddFriendStatusReturn.getNickNameRemarkLocal());
			newFriendMessage.setHeadAddress(addFriends.getHeadAddress());
			newFriendMessage.setSex(addFriends.getSex());
			newFriendMessage.setLongitude(addFriends.getLongitude());
			newFriendMessage.setMemberNo(updateAddFriendStatusReturn.getMemberNo());
			newFriendMessage.setMemberName(updateAddFriendStatusReturn.getMemberName());
			newFriendMessage.setMobile(addFriends.getMobile());
			newFriendMessage.setVersion(updateAddFriendStatusReturn.getVersion());
			newFriendMessage.setAcceptTime(addFriends.getAcceptTime());
			newFriendMessage.setClaimTime(updateAddFriends.getMemberTime());
			/**
			 * 客户分类相关信息 DZP 2018-12-14
			 */
			newFriendMessage.setCodePm(updateAddFriendStatusReturn.getCodePm());
			newFriendMessage.setPmTypeCode(updateAddFriendStatusReturn.getPmTypeCode());
			newFriendMessage.setPmTypeName(updateAddFriendStatusReturn.getPmTypeName());
			taskExecutor.execute(new Runnable() { // 通过线程池通知,应改为异步消息 TODO
				@Override
				public void run() {
					try {

						IContactsService basic = commonService
								.getHessianContactsService(newFriendMessage.getMemberNoGm());
						basic.sendNewFriendMessage(newFriendMessage);
						logger.info("已通知导购客户端新好友消息：{}", newFriendMessage);
					} catch (Exception e) {
						logger.error("通知导购客户端新好友消息失败", e);
					}
				}
			});

			distributionMemberReturn = new DistributionMemberReturn();
			distributionMemberReturn.setMemberNo(updateAddFriendStatusReturn.getMemberNo());
			distributionMemberReturn.setMemberNameGm(updateAddFriendStatusReturn.getMemberName());
			distributionMemberReturn.setMemberNoGm(findGmAssistantShopReturn.getAssistantNo());
			distributionMemberReturn.setMemberNameGm(findGmAssistantShopReturn.getAssistantName());
			distributionMemberReturn.setClaimTime(updateAddFriends.getMemberTime().getTime());
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("为新增客户分配所属导购错误！", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_UPDATE_ERROR, "为新增客户分配所属导购错误！", e);
		} finally { // 不管成功与否，保证锁能释放
			// 释放锁
			redisLock.releaseLock(claimLockName, claimLockValue);
		}

		logger.debug("distributionMember(DistributionMember) - end - return value={}", distributionMemberReturn);
		return distributionMemberReturn;
	}

	@Override
	public Page<FindAddFriendsPageReturn> findAddFriendsAllotPage(FindAddFriendsAllotPage findAddFriendsAllotPage)
			throws TsfaServiceException {
		logger.debug("findAddFriendsAllotPage(FindAddFriendsAllotPage findAddFriendsAllotPage={}) - start",
				findAddFriendsAllotPage);

		AssertUtils.notNull(findAddFriendsAllotPage);
		List<FindAddFriendsPageReturn> returnList;
		int count = 0;
		try {
			returnList = addFriendsDao.findAddFriendsAllotPage(findAddFriendsAllotPage);
			count = addFriendsDao.findAddFriendsAllotPageCount(findAddFriendsAllotPage);
		} catch (Exception e) {
			logger.error("添加微信好友信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_FIND_PAGE_ERROR, "添加微信好友信息不存在错误.！", e);
		}
		Page<FindAddFriendsPageReturn> returnPage = new Page<FindAddFriendsPageReturn>(returnList, count,
				findAddFriendsAllotPage);

		logger.debug("findAddFriendsAllotPage(FindAddFriendsAllotPage) - end - return value={}", returnPage);
		return returnPage;
	}

	/**
	 * 
	 *
	 * 方法说明：验证终端是否可以添加客户微信为好友
	 * 
	 * @param shopNo     终端编号
	 * @param memberNoGm 导购编号
	 * @param noWxGm     导购微信号
	 * @param noWx       客户微信号
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月14日
	 *
	 */
	private PersonMemberBase validateMember(String memberNoGm, String noWxGm, String noWx, String alias) {
		// 根据客户微信号和别名查询客户基础表信息
		PersonMemberBase personMemberBase = personMemberBaseDao.findMemberBaseByNoWxOrAlias(noWx, alias);
//		// 存在该微信号的客户基础数据
		if (personMemberBase != null) {
			AddFriends record = new AddFriends();
			record.setNoWxGm(noWxGm);
			record.setNoWx(noWx);
			AddFriends addFriends = addFriendsDao.selectBySelective(record);

			if (addFriends != null) {
				if (StringUtils.isNotEmpty(addFriends.getMemberNoGm())
						&& addFriends.getMemberNoGm().equals(memberNoGm)) {
					String remarkString = "导购已添加该客户";
					logger.error(memberNoGm + remarkString);
					throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_REPEATEDLY_ERROR, remarkString);
				} else {
					String remarkString = "该终端已添加该客户";
					logger.error(remarkString);
					throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_REPEATEDLY_OTHER_ERROR, remarkString);
				}
			}
		}

		return personMemberBase;
	}

	private String getClaimKey(String code) {
		return KeyConstant.MEMBER_CLAIM_CACHE_PREFIX + code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IAddFriendsService#syncFriendsRequest(java.
	 * lang.String)
	 */
	@Override
	public void syncFriendsRequest(String code) {
		logger.debug("syncFriendsRequest(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "code不能为空");

		// 查询终端终端
		FindShopTerminal findShopTerminal = new FindShopTerminal();
		findShopTerminal.setCode(code);
		FindShopTerminalReturn shopTerminal = shopTerminalService.findShopTerminal(findShopTerminal);

		// 获取同步锁（前缀 + 商户号 + 微信号），检查终端微信是否正在同步通讯录
		final String lockName = KeyConstant.MEMBER_SYNC_CACHE_PREFIX + shopTerminal.getMerchantNo()
				+ shopTerminal.getNoWx();
		String lockValue = null;

		try {
			lockValue = redisLock.getLockNoWait(lockName);
		} catch (Exception e) {
			logger.error("终端微信{}正在同步通讯录", shopTerminal.getNoWx());
			throw new TsfaServiceException(ErrorCode.FRIENDS_SYNCING_ERROR, "终端微信正在同步通讯录");
		}
		logger.info("请求同步-得到同步锁：{} - {}", lockName, lockValue);

		// 向中控系统发送同步通讯录请求
		try {
			SyncShopWxFriendsMessage syncShopWxFriendsMessage = new SyncShopWxFriendsMessage();
			syncShopWxFriendsMessage.setNoWxShop(shopTerminal.getNoWx());

			IContactsService basic = commonService.getHessianContactsService(syncShopWxFriendsMessage.getNoWxShop());
			basic.sendSyncShopWxFriendsMessage(syncShopWxFriendsMessage);
		} catch (TsfaServiceException e) {
			logger.error("向中控系统发送同步通讯录请求失败", e);
			throw e;
		} catch (Exception e) {
			logger.error("向中控系统发送同步通讯录请求失败", e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_SYNCING_ERROR, "向中控系统发送同步通讯录请求失败");
		} finally { // 不管发送请求成功与否，保证锁能释放
			// 释放锁
			logger.info("请求同步-释放同步锁：{} - {}", lockName, lockValue);
			redisLock.releaseLock(lockName, lockValue);
		}

		logger.debug("syncFriendsRequest(String) - end");
	}

	@Override
	public void syncFriendsList(final String noWxGm, final SyncFriendsFromZk syncFriendsFromZk) {
		logger.debug("syncFriendsList(SyncFriendsFromZk syncFriendsFromZk={}) - start", syncFriendsFromZk);

		AssertUtils.notNull(syncFriendsFromZk);
		AssertUtils.notEmpty(syncFriendsFromZk.getFriendsList(), "客户列表不能为空");

		// 获取同步锁（前缀 + 微信号），同时检查终端微信是否正在同步通讯录
		final String noWxShop = noWxGm;
		final String lockName = KeyConstant.MEMBER_SYNC_CACHE_PREFIX + noWxShop;
		String lockValue = null;
		try {
			lockValue = redisLock.getLockNoWait(lockName);
		} catch (Exception e) {
			logger.error("终端微信{}正在同步通讯录", noWxShop);
			throw new TsfaServiceException(ErrorCode.FRIENDS_SYNCING_ERROR, "终端微信正在同步通讯录");
		}
		final String lockValueFinal = lockValue;
		logger.info("同步处理-得到同步锁：{} - {}", lockName, lockValue);

		// 异步执行
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				logger.info("异步同步终端微信{}通讯录 --- 开始", noWxShop);
				long begin = System.currentTimeMillis();
				int total = syncFriendsFromZk.getFriendsList().length; // 合计好友数
				List<String> failureList = new ArrayList<String>(); // 同步失败列表，格式：微信号-别名-微信昵称
				for (SyncFriendsDetail detail : syncFriendsFromZk.getFriendsList()) {
					logger.debug("正在同步终端微信好友：{}", detail);
					try {
						syncFriend(noWxGm, detail);
					} catch (TsfaServiceException e) {
						logger.error("同步通讯录(中控)错误：", e);
						failureList.add(detail.getNoWx() + "-" + detail.getAlias() + "-" + detail.getNickNameWx());
					} catch (Exception e) {
						logger.error("同步通讯录(中控)错误：", e);
						failureList.add(detail.getNoWx() + "-" + detail.getAlias() + "-" + detail.getNickNameWx());
					}
				}
				// 释放锁
				logger.info("同步处理-释放同步锁：{} - {}", lockName, lockValueFinal);
				redisLock.releaseLock(lockName, lockValueFinal);

				int failureCount = failureList.size();
				logger.info("微信({})待同步微信好友共 {} 个，成功 {} 个，失败 {} 个", noWxShop, total, (total - failureCount),
						failureCount);
				if (failureCount > 0) {
					logger.info("同步失败微信好友信息：{}", failureList);
				}
				long end = System.currentTimeMillis();
				logger.info("此次同步通讯录共花费(毫秒)：" + (end - begin));
				logger.info("异步同步终端微信{}通讯录 --- 结束", noWxShop);
			}
		});

		logger.debug("syncFriendsList(SyncFriendsFromZk) - end");
	}

	/**
	 * 
	 *
	 * 方法说明：同步终端微信好友 1、终端已存在该客户，则更新客户基本信息 2、不存在则检查待认领列表，待认领列表有则更新，无则新增
	 *
	 * @param syncFriendsDetail
	 * @param shop
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月12日
	 *
	 */
	private void syncFriend(String noWxGm, SyncFriendsDetail syncFriendsDetail) {
		logger.debug("syncFriend(SyncFriendsDetail syncFriendsDetail={}) - start", syncFriendsDetail);

		AssertUtils.notNull(syncFriendsDetail);
		AssertUtils.notNullAndEmpty(noWxGm, "导购微信号不能为空");
		AssertUtils.notNullAndEmpty(syncFriendsDetail.getNoWx(), "客户微信号不能为空");

		try {
			/**
			 * 获取终端
			 */
			FindShopTerminalReturn findShopTerminalReturn = shopTerminalService.findShopTerminalByWx(noWxGm);
			if (findShopTerminalReturn == null) {
				logger.error("终端微信不存在或者无效：{}", noWxGm);
				throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_NORMAL_ERROR, "无效的终端微信");
			}

			// 检查添加好友列表
			AddFriends findAddFriends = new AddFriends();
			findAddFriends.setNoWxGm(noWxGm);
			findAddFriends.setNoWx(syncFriendsDetail.getNoWx());
			findAddFriends.setMerchantNo(findShopTerminalReturn.getMerchantNo());
			AddFriends addFriends = addFriendsDao.selectBySelective(findAddFriends);
			String code = null;
			if (addFriends != null) { // 不为空，则更新
				boolean hasChanged = Boolean.FALSE; // 与数据库数据相比有更新
				AddFriends updateAddFriends = new AddFriends();
				if (StringUtils.isNotEmpty(syncFriendsDetail.getAlias())
						&& !syncFriendsDetail.getAlias().equals(addFriends.getAlias())) { // 微信别名
					updateAddFriends.setAlias(syncFriendsDetail.getAlias());
					hasChanged = Boolean.TRUE;
				}
				// 昵称和标签统一需求，只同步第一次，后续不同步
				if (StringUtils.isNotEmpty(syncFriendsDetail.getNickNameWx())
						&& StringUtils.isEmpty(addFriends.getNickName())) { // 微信昵称
					updateAddFriends.setNickName(syncFriendsDetail.getNickNameWx());
					hasChanged = Boolean.TRUE;
				}
				if (StringUtils.isNotEmpty(syncFriendsDetail.getNickNameRemarkWx())
						&& !syncFriendsDetail.getNickNameRemarkWx().equals(addFriends.getNickRemark())) { // 微信昵称备注
					updateAddFriends.setNickRemark(syncFriendsDetail.getNickNameRemarkWx());
					hasChanged = Boolean.TRUE;
				}
				if (StringUtils.isNotEmpty(syncFriendsDetail.getHeadAddress())
						&& !syncFriendsDetail.getHeadAddress().equals(addFriends.getHeadAddress())) { // 微信头像地址
					updateAddFriends.setHeadAddress(syncFriendsDetail.getHeadAddress());
					hasChanged = Boolean.TRUE;
				}
				if (StringUtils.isNotEmpty(syncFriendsDetail.getSex())
						&& !syncFriendsDetail.getSex().equals(addFriends.getSex())) { // 性别
					updateAddFriends.setSex(syncFriendsDetail.getSex());
					hasChanged = Boolean.TRUE;
				}
				if (!AddFriendsStatus.Y.name().equals(addFriends.getAddStatus())) {
					updateAddFriends.setNoWxGm(noWxGm);
					updateAddFriends.setWxAddType(4); // 微信自动导入
					updateAddFriends.setAddStatus(AddFriendsStatus.Y.name());
					updateAddFriends.setAcceptTime(new Date());
					hasChanged = Boolean.TRUE;
				}

				if (StringUtils.isNotEmpty(syncFriendsDetail.getLabelName())
						&& StringUtils.isEmpty(addFriends.getLabelName())) { // 标签
					updateAddFriends.setLabelName(syncFriendsDetail.getLabelName());
					hasChanged = Boolean.TRUE;
				}

				// 手机号码,也和标签备注同一需求，只同步第一次
				if (StringUtils.isNotEmpty(syncFriendsDetail.getMobile())
						&& StringUtils.isEmpty(addFriends.getMobile())) {
					updateAddFriends.setMobile(syncFriendsDetail.getMobile());
					hasChanged = Boolean.TRUE;
				}

				if (hasChanged) { // 有更新
					updateAddFriends.setCode(addFriends.getCode());
					addFriendsDao.updateByPrimaryKeySelective(updateAddFriends); // 更新添加微信好友记录
					logger.info("已同步更新添加客户微信好友状态：{}", addFriends.getCode());
					// 同步personMemberBase中客户昵称和头像
					try {

						final String merchantNo = addFriends.getMerchantNo();
						final String memberNo = addFriends.getMemberNo();
						final String shopWx = addFriends.getNoWxGm();
						final String memberNoGm = addFriends.getMemberNoGm();
						final String memberNameGm = addFriends.getMemberNameGm();
						taskExecutor.execute(new Runnable() {
							@Override
							public void run() {
								UpdatePersonMemberBase updatePersonMemberBase = new UpdatePersonMemberBase();
								updatePersonMemberBase.setNoWx(syncFriendsDetail.getNoWx());
								boolean upFlag = false;
								if (StringUtils.isNotEmpty(updateAddFriends.getAlias())) {
									updatePersonMemberBase.setNoWxAlias(updateAddFriends.getAlias());
									upFlag = true;
								}
								if (StringUtils.isNotEmpty(updateAddFriends.getNickName())) {
									updatePersonMemberBase.setNickNameWx(updateAddFriends.getNickName());
									upFlag = true;
								}
								if (StringUtils.isNotEmpty(updateAddFriends.getHeadAddress())) {
									updatePersonMemberBase.setHeadAddress(updateAddFriends.getHeadAddress());
									upFlag = true;
								}
								if (StringUtils.isNotEmpty(updateAddFriends.getMobile())) {
									updatePersonMemberBase.setMobile(updateAddFriends.getMobile());
									upFlag = true;
								}
								if (upFlag) {
									personMemberBaseDao.updatePersonMemberWxInfoByNoWx(updatePersonMemberBase);
								}

								// 更新标签
								if (StringUtils.isNotEmpty(updateAddFriends.getLabelName())) {
									personMemberService.addLabels(updateAddFriends.getLabelName(), merchantNo, memberNo,
											shopWx, memberNoGm, memberNameGm);
								}
							}
						});
					} catch (Exception e) {
						logger.error("同步personMemberBase数据错误 e={}", e);
					}
				}
				code = addFriends.getCode();
			} else { // 新增记录

				addFriends = new AddFriends();
				code = GUID.generateCode();
				addFriends.setCode(code);
//				addFriends.setShopNo(shop.getShopNo());
				addFriends.setShopName(findShopTerminalReturn.getShopName());
				addFriends.setMerchantNo(findShopTerminalReturn.getMerchantNo());
				addFriends.setMerchantName(findShopTerminalReturn.getMerchantName());
				addFriends.setNoWxGm(noWxGm);
				addFriends.setGmAddFlag(GmAddFlag.PASSIVE.getCode()); // 非导购主动添加
				addFriends.setWxAddType(4); // 微信自动导入
				addFriends.setNoWx(syncFriendsDetail.getNoWx());
				addFriends.setAlias(syncFriendsDetail.getAlias());
				addFriends.setHeadAddress(syncFriendsDetail.getHeadAddress());
				addFriends.setNickName(syncFriendsDetail.getNickNameWx());
				addFriends.setNickRemark(syncFriendsDetail.getNickNameRemarkWx());
				addFriends.setSex(syncFriendsDetail.getSex());
				addFriends.setAddStatus(AddFriendsStatus.Y.name()); // 验证通过
				addFriends.setRequestTime(new Date());
				addFriends.setAcceptTime(new Date());
				addFriends.setCreateDate(new Date());
				addFriends.setMemberTime(new Date());
				addFriends.setLabelName(syncFriendsDetail.getLabelName());
				addFriends.setMobile(syncFriendsDetail.getMobile());
				addFriendsDao.insertSelective(addFriends);
				logger.info("已同步新增添加客户微信好友记录：{}", addFriends.getCode());

				// 发送消息给同门店绑定同一微信的所有导购，通知导购认领该导购(此处应使用异步消息 XXX)
				this.memberClaim(addFriends);
			}

			// 查询指定微信未分配的客户
			AddFriends repeatFriendsQuery = new AddFriends();
			repeatFriendsQuery.setNoWx(syncFriendsDetail.getNoWx());
			repeatFriendsQuery.setAlias(syncFriendsDetail.getAlias());
			List<AddFriends> friendsList = addFriendsDao.selectByRepeatAndClaim(repeatFriendsQuery);
			if (friendsList != null && !friendsList.isEmpty()) {
				for (AddFriends repeatFriends : friendsList) {
					if (!code.equals(repeatFriends.getCode())) {
						logger.warn("删除重复未分配客户记录: {}", repeatFriends);
						addFriendsDao.deleteByPrimaryKey(repeatFriends.getCode());
					}
				}
			}

			// 二合一版本，只有一个导购的情况，自动认领，默认分配给商户下的一个导购 start
			if (StringUtils.isNotEmpty(findShopTerminalReturn.getMerchantNo())) {
				try {
					FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
					findGmAssistantShop.setMerchantNo(findShopTerminalReturn.getMerchantNo());
					findGmAssistantShop.setNoWx(findShopTerminalReturn.getNoWx());
					findGmAssistantShop.setSource(true);// 下级
					List<FindGmAssistantShopReturn> list = gmAssistantShopService
							.findGmAssistantShopList(findGmAssistantShop);
					logger.info("二合一版本-----自动认领------list={}", list);
					if (null != list && list.size() == 1) {
						FindGmAssistantShopReturn guidInfoShop = list.get(0);
						DistributionMember distributionMember = new DistributionMember();
						distributionMember.setMemberNoGm(guidInfoShop.getAssistantNo());
						distributionMember.setCode(code);
						distributionMember(distributionMember);
					}
				} catch (TsfaServiceException e) {
					e.printStackTrace();
					logger.error("自动认领失败 e={}", e.getExceptionInfo());
				} catch (Exception e) {
					logger.error("自动认领失败 e={}", e);
				}
			}
			// 二合一版本，只有一个导购的情况，自动认领，默认分配给商户下的一个导购 end
		} catch (TsfaServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_UPDATE_ERROR, "同步通讯录(中控)错误！", e);
		}

		logger.debug("syncFriend(SyncFriendsDetail) - end");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IAddFriendsService#findClaimMemberPage(com.lj.
	 * business.member.dto.addfriends.FindClaimMemberPage)
	 */
	@Override
	public IPage<FindClaimMemberPageReturn> findClaimMemberPage(FindClaimMemberPage findClaimMemberPage,
			boolean memberNoGmFlag, String noWxGm) {
		logger.debug("findClaimMemberPage(FindClaimMemberPage findClaimMemberPage={}) - start", findClaimMemberPage);

		AssertUtils.notNull(findClaimMemberPage);
		AssertUtils.notNullAndEmpty(findClaimMemberPage.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(noWxGm, "终端微信不能为空");

		// 查询导购信息
//		FindGuidMember findGuidMember = new FindGuidMember();
//		findGuidMember.setMemberNo(findClaimMemberPage.getMemberNoGm());
//		FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
		FindGmAssistantShopReturn findGuidMemberReturn = gmAssistantShopService.findGmByWxAndNo(noWxGm,
				findClaimMemberPage.getMemberNoGm());
		List<FindClaimMemberPageReturn> returnList = null;
		int count = 0;
		if (findGuidMemberReturn != null && StringUtils.isNotEmpty(findGuidMemberReturn.getNoWx())) {
			try {
				// 查询所有已认领或待认领，不关联导购
				if (memberNoGmFlag == false) {
					findClaimMemberPage.setMemberNoGm(null);
				}
				count = addFriendsDao.findClaimMemberPageCount(findClaimMemberPage, findGuidMemberReturn.getNoWx(),
						findGuidMemberReturn.getAssistantNo());
				if (count > 0) {
					returnList = addFriendsDao.findClaimMemberPageList(findClaimMemberPage,
							findGuidMemberReturn.getNoWx(), findGuidMemberReturn.getAssistantNo());
				}
			} catch (Exception e) {
				logger.error("添加微信好友信息不存在错误", e);
				throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_FIND_PAGE_ERROR, "添加微信好友信息不存在错误.！", e);
			}
		}
		Page<FindClaimMemberPageReturn> returnPage = new Page<FindClaimMemberPageReturn>(returnList, count,
				findClaimMemberPage);

		logger.debug("findClaimMemberPage(FindClaimMemberPage) - end - return value={}", returnPage);
		return returnPage;
	}

	/**
	 * 查询导购认领的用户
	 * 
	 * @param memberNoGm
	 * @return
	 */
	public List<AddFriends> findClaimMemberList(String memberNoGm) throws TsfaServiceException {
		return addFriendsDao.findClaimMemberList(memberNoGm);
	}

	/**
	 * 取消客户绑定
	 */
	public void updateCancleAddFriendsData(String gmNo, String wxNo, String noWxGm) throws TsfaServiceException {
		logger.debug("updateCancleAddFriendsData(String gmNo={}, String wxNo={},String noWxGm={}) - start", gmNo, wxNo,
				noWxGm);
		AssertUtils.notAllNullAndEmpty(gmNo, "导购编号不能为空");
		AssertUtils.notAllNullAndEmpty(wxNo, "客户微信不能为空");
		AssertUtils.notAllNullAndEmpty(noWxGm, "终端微信不能为空");
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("gmNo", gmNo);
			map.put("wxNo", wxNo);
			map.put("noWxGm", noWxGm);
			addFriendsDao.updateCancleAddFriendsData(map);
		} catch (Exception e) {
			logger.error("取消客户绑定错误", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_CANCLE_ERROR, "取消客户绑定错误.！", e);
		}
		logger.debug("updateCancleAddFriendsData(updateCancleAddFriendsData updateCancleAddFriendsData={}) - end",
				gmNo + "|" + wxNo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IAddFriendsService#findNotClaimedFriends(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public FindAddFriendsReturn findNotClaimedFriends(String noWxGm, String noWx) {
		logger.debug("findNotClaimedFriends(noWxGm={}, noWx={}) - start", noWxGm, noWx);

		AssertUtils.notNullAndEmpty(noWxGm, "导购微信号不能为空");
		AssertUtils.notNullAndEmpty(noWx, "客户微信号不能为空");

		AddFriends findAddFriends = new AddFriends();
		findAddFriends.setNoWxGm(noWxGm);
		findAddFriends.setNoWx(noWx);
		AddFriends addFriends = addFriendsDao.selectBySelectiveAndSync(findAddFriends); // 未分配或导购扫码未通过客户
		FindAddFriendsReturn findAddFriendsReturn = null;

		if (addFriends != null && StringUtils.isEmpty(addFriends.getWxQrCode())
				&& AddFriendsStatus.Y.name().equals(addFriends.getAddStatus())) {
			findAddFriendsReturn = new FindAddFriendsReturn();
			// find数据录入
			findAddFriendsReturn.setCode(addFriends.getCode());
			findAddFriendsReturn.setMemberNo(addFriends.getMemberNo());
			findAddFriendsReturn.setMemberName(addFriends.getMemberName());
			findAddFriendsReturn.setMemberNoGm(addFriends.getMemberNoGm());
			findAddFriendsReturn.setMemberNameGm(addFriends.getMemberNameGm());
			findAddFriendsReturn.setMerchantNo(addFriends.getMerchantNo());
			findAddFriendsReturn.setMerchantName(addFriends.getMerchantName());
			findAddFriendsReturn.setNoWxGm(addFriends.getNoWxGm());
			findAddFriendsReturn.setWxQrCode(addFriends.getWxQrCode());
			findAddFriendsReturn.setNoWx(addFriends.getNoWx());
			findAddFriendsReturn.setNickRemark(addFriends.getNickRemark());
			findAddFriendsReturn.setMobile(addFriends.getMobile());
			findAddFriendsReturn.setSex(addFriends.getSex());
			findAddFriendsReturn.setValidation(addFriends.getValidation());
			findAddFriendsReturn.setAddStatus(addFriends.getAddStatus());
			findAddFriendsReturn.setRequestTime(addFriends.getRequestTime());
			findAddFriendsReturn.setAcceptTime(addFriends.getAcceptTime());
			findAddFriendsReturn.setCreateId(addFriends.getCreateId());
			findAddFriendsReturn.setCreateDate(addFriends.getCreateDate());
			findAddFriendsReturn.setRemark(addFriends.getRemark());
			findAddFriendsReturn.setRemark2(addFriends.getRemark2());
			findAddFriendsReturn.setRemark3(addFriends.getRemark3());
			findAddFriendsReturn.setRemark4(addFriends.getRemark4());
		}

		logger.debug("findNotClaimedFriends() - end - return value={}", findAddFriendsReturn);
		return findAddFriendsReturn;
	}

	@Override
	public int findAddWxFriendsCount(FindAddFriendsPage findAddFriendsPage) throws TsfaServiceException {
		AssertUtils.notNull(findAddFriendsPage);
		int count = 0;
		try {
			count = addFriendsDao.findAddFriendsPageCount(findAddFriendsPage);
		} catch (Exception e) {
			logger.error("添加微信好友数量查询错误", e);
		}
		return count;
	}

	@Override
	public void sendQcordByMec(Map<String, Object> paramMap) throws TsfaServiceException {
		logger.debug("findAddFriendsByMec(Map<String, Object> paramMap={})) - start", paramMap);
		AssertUtils.notNull(paramMap);
		AssertUtils.notNullAndEmpty(paramMap.get(MERCHANT_NO_MAP), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(paramMap.get(DIFF_NUM), "分钟差值不能为空");
		AssertUtils.notNullAndEmpty(paramMap.get(QCORD_URL), "二维码地址不能为空");
		List<FindAddFriendsPageReturn> returnList = null;
		try {
			returnList = addFriendsDao.findAddFriendsByMec(paramMap);
			logger.info("五分钟未应答的客户：{}", returnList.toString());
			if (null != returnList && returnList.size() > 0) {
				// 二维码地址
				final String qcordUrl = paramMap.get(QCORD_URL).toString();

				/**
				 * 获取客户与导购聊天信息 1.im_chat_info中至少两条记录，添加好友消息会有一条
				 */
				for (final FindAddFriendsPageReturn findAddFriendsPageReturn : returnList) {
					// 异步执行
					taskExecutor.execute(new Runnable() {
						@Override
						public void run() {
							logger.info("电商异步推送二维码 memberNo--- 开始", findAddFriendsPageReturn.getMemberNo());
							long begin = System.currentTimeMillis();

							// 查询聊天信息
							Map<String, Object> imMap = new HashMap<String, Object>();
							imMap.put(MERCHANT_NO_MAP, findAddFriendsPageReturn.getMerchantNo());
							imMap.put(NO_WX_GM_MAP, findAddFriendsPageReturn.getNoWxGm());
							imMap.put(NO_WX_MAP, findAddFriendsPageReturn.getNoWx());
							int count = imChatInfoService.findImChatInfoByMecCount(imMap);
							logger.info("导购客户聊天数：{}", count);
							if (count <= IM_COUNT) {
								/**
								 * 发送微信二维码
								 */
								if (StringUtils.isNotEmpty(qcordUrl)) {
//									SendImChatInfo sendImChatInfo = new SendImChatInfo();
//									sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
//									sendImChatInfo.setMemberNoGm(findAddFriendsPageReturn.getMemberNoGm());
//									sendImChatInfo.setMemberNo(findAddFriendsPageReturn.getMemberNo());
//									sendImChatInfo.setType(ChatInfoType.IMG.getCode());	// 图片
//									sendImChatInfo.setResources(qcordUrl);
//									sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
//									logger.info("推送二维码：{}", sendImChatInfo);
//									imChatInfoService.sendChat(sendImChatInfo);

									SendImChatByNonMember chatByNonMember = new SendImChatByNonMember();
									chatByNonMember.setNoWx(findAddFriendsPageReturn.getNoWx());
									chatByNonMember.setNoWxShop(findAddFriendsPageReturn.getNoWxGm());
									chatByNonMember.setType(ChatInfoType.IMG.getCode()); // 图片
									chatByNonMember.setResources(qcordUrl);
									chatByNonMember.setMsgSource(MessageSource.SA.getCode());
									chatByNonMember.setAlias(findAddFriendsPageReturn.getNickName());
									chatByNonMember.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
									imChatInfoService.sendChatByNonMember(chatByNonMember);
									logger.info("向未认领客户发送IM聊天记录,推送二维码：{}", chatByNonMember);
								}
							}
							long end = System.currentTimeMillis();
							logger.info("此次推送二维码共花费(毫秒)：" + (end - begin));
							logger.info("电商异步推送二维码 memberNo--- 結束", findAddFriendsPageReturn.getMemberNo());
						}
					});
				}
			}

		} catch (Exception e) {
			logger.error("添加微信好友信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_FIND_PAGE_ERROR, "添加微信好友信息不存在错误.！", e);
		}
	}

	/**
	 * 查询商户当天和总数没超过限制的微信号()
	 * 
	 * @return 返回二维码地址
	 */
	@Override
	public synchronized String findWxGmByMerchantNo(String merchantNo, String code, int dayLimit, int totalLimit) {
		// 查询门店微信
		List<PmFlowQcode> list = flowQcodeDao.findFlowWxByCode(code);
		String value = redis.get("REDIS-KEY-FLOW-QCODE-" + code);

		List<String> wxqcodelist = new ArrayList<String>();
		String qcodestr = "";
		for (PmFlowQcode st : list) {
			String array[] = st.getFlowWxNo().split(",");

			for (String wx : array) {
				if (wx != null && !wx.equals("")) {
					Integer dayNum = addFriendsDao.findDayWxGmByMerchantNo(merchantNo, wx);
					Integer totalNum = addFriendsDao.findTotalWxGmByMerchantNo(merchantNo, wx);

					if (dayNum < dayLimit && totalNum < totalLimit) {

						FindShopTerminal findShopTerminal = new FindShopTerminal();
						findShopTerminal.setMerchantNo(merchantNo);
						findShopTerminal.setNoWx(wx);
						FindShopTerminalReturn findShopTerminalReturn = shopTerminalDao
								.findShopTerminalByWx(findShopTerminal);
						// 未配置二维码
						if (findShopTerminalReturn.getQcord() == null || findShopTerminalReturn.getQcord().equals("")) {
							continue;
						}

						qcodestr = findShopTerminalReturn.getQcord();

						// 把符合条件的二维码装进一个数组
						wxqcodelist.add(findShopTerminalReturn.getQcord());
					}
				}
			}
		}
		// 单个二维码的时候
		if (wxqcodelist.size() == 1) {
			redis.set("REDIS-KEY-FLOW-QCODE-" + code, "0");
			return qcodestr;
		}
		Integer k = 0;
		// 第一次进入
		if (wxqcodelist.size() > 1 && (value == null || value.equals(""))) {
			redis.set("REDIS-KEY-FLOW-QCODE-" + code, "0");
			qcodestr = wxqcodelist.get(0);
			return qcodestr;
		}
		// 轮流算法
		if (wxqcodelist.size() > 1 && value != null && !value.equals("")) {

			k = Integer.valueOf(value) + 1;
			if (k < wxqcodelist.size()) {
				redis.set("REDIS-KEY-FLOW-QCODE-" + code, k + "");
				qcodestr = wxqcodelist.get(k);
				return qcodestr;
			}
			// 从头再来
			if (k > wxqcodelist.size() || k == wxqcodelist.size()) {
				k = 0;
				redis.set("REDIS-KEY-FLOW-QCODE-" + code, k + "");
				qcodestr = wxqcodelist.get(k);
				return qcodestr;
			}

		}

		return "";

	}

	@Override
	public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> countYesterdayAddFriends() {
		System.out.println("定时任务进来了");
		// 获取所有商户编码
		List<String> listAll = merchantDao.getMerchantNoAll();
		System.out.println("获取：所有商户编码" + listAll);
		// 商户编码是唯一的，不需要去重了！
//		List<String> listNo = new ArrayList<>();
//		for ( int i = 0 ; i< listAll.size();i++) {
//			if (!listNo.contains(listAll.get(i))) {
//				listNo.add(listAll.get(i));
//			}
//		}
		// 根据每个商户编码查询昨天的新增客户总量
		List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> counts = new ArrayList<>();
		for (String merchantNo : listAll) {
			com.lj.business.member.dto.addfriends.CountAddFriendsEntity entity = addFriendsDao
					.getYesterdayCountByMerchantNo(merchantNo);
			if (entity == null || entity.getCount() == 0) {
				entity.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				entity.setCountDate(
						new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Date().getTime() - 86400000L)));// 获取昨天的时间
																												// =
																												// 今天的时间
																												// -
																												// 86400000L
				entity.setMerchantNo(merchantNo);
				entity.setCount(0);
			}
			counts.add(entity);
		}
		System.out.println("统计集合：" + counts);
		return counts;
	}

	/**
	 * 统计某一天的数据
	 */
	public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> countAddFriendsByDate(String date,
			String type) {
		System.out.println("定时任务进来了");
		// 获取所有商户编码
		List<String> listAll = merchantDao.getMerchantNoAll();
		System.out.println("获取：所有商户编码" + listAll);

		if (type.equals("1")) {

			// 根据每个商户编码查询昨天的新增客户总量
			List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> counts = new ArrayList<>();
			for (String merchantNo : listAll) {
				com.lj.business.member.dto.addfriends.CountAddFriendsEntity entity = null;

				entity = addFriendsDao.getCountByMerchantNoWithDate(merchantNo, date);

				if (entity == null || entity.getCount() == null) {
					entity = new com.lj.business.member.dto.addfriends.CountAddFriendsEntity();
					entity.setCount(0);
				}
				entity.setType(type);
				entity.setCountDate(date);
				entity.setMerchantNo(merchantNo);
				entity.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				counts.add(entity);
			}
			return counts;
		}

		if (type.equals("2")) {

			// 根据每个商户编码查询昨天的新增客户总量
			List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> counts = new ArrayList<>();
			for (String merchantNo : listAll) {

				List<FindShopTerminalReturn> list = shopTerminalDao.findShopTerminalByMerchantNo(merchantNo);
				for (FindShopTerminalReturn st : list) {
					com.lj.business.member.dto.addfriends.CountAddFriendsEntity entity = null;

					entity = addFriendsDao.getCountByWxNoWithDate(st.getNoWx(), date);

					if (entity == null || entity.getCount() == null) {
						entity = new com.lj.business.member.dto.addfriends.CountAddFriendsEntity();
						entity.setCount(0);
					}
					entity.setNoWx(st.getNoWx());
					entity.setType(type);
					entity.setCountDate(date);
					entity.setMerchantNo(merchantNo);
					entity.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					counts.add(entity);
				}
			}
			return counts;
		}
		return null;
	}

	/**
	 * 门店新增用户排行榜
	 * 
	 */
	public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> selectShopOrderByaddFriends(
			String merchantNo, String startTime, String endTime, String limit) {
		return addFriendsDao.selectShopOrderByaddFriends(merchantNo, startTime, endTime, Integer.valueOf(limit));
	}

	/**
	 * 导购新增用户排行榜
	 */
	public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> selectGuidOrderByaddFriends(
			String merchantNo, String startTime, String endTime, String limit) {
		return addFriendsDao.selectGuidOrderByaddFriends(merchantNo, startTime, endTime, Integer.valueOf(limit));
	}

	/**
	 * 门店维护排行榜
	 * 
	 * @param merchantNo
	 * @return
	 */
	public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> selectShopOrderByServiceFriends(
			String merchantNo, String startTime, String endTime, String limit) {
		return addFriendsDao.selectShopOrderByServiceFriends(merchantNo, startTime, endTime, Integer.valueOf(limit));
	}

	/**
	 * 导购维护排行榜
	 * 
	 * @param merchantNo
	 * @return
	 */
	public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> selectGuidOrderByServiceFriends(
			String merchantNo, String startTime, String endTime, String limit) {
		return addFriendsDao.selectGuidOrderByServiceFriends(merchantNo, startTime, endTime, Integer.valueOf(limit));
	}

	@Override
	public void delete(UpdateAddFriends updateAddFriends) {
		addFriendsDao.delete(updateAddFriends);

	}

}
