package com.lj.business.api.controller.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lj.base.core.pagination.IPage;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.api.common.Constans;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.dto.FindMemberRecordDto;
import com.lj.business.api.dto.FindPmTypeIndexAllReturn;
import com.lj.business.api.dto.UpdateMemberQcordDto;
import com.lj.business.api.dto.tmall.TmallBonusH5Dto;
import com.lj.business.api.utils.WeixinUtil;
import com.lj.business.api.utils.WxMD5Util;
import com.lj.business.common.CommonConstant;
import com.lj.business.common.Constants;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.domain.GmAssistantShop;
import com.lj.business.member.domain.MemLine;
import com.lj.business.member.domain.PersonMemberBase;
import com.lj.business.member.dto.ChangePmLabel;
import com.lj.business.member.dto.ChangePmType;
import com.lj.business.member.dto.ChangePmTypeApp;
import com.lj.business.member.dto.ChangePmTypeUngroup;
import com.lj.business.member.dto.EditPersonMember;
import com.lj.business.member.dto.FindGmLabelPage;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindGuidmemberActionInfoListDto;
import com.lj.business.member.dto.FindIntegralDayDto;
import com.lj.business.member.dto.FindIntegralDayDtoReturn;
import com.lj.business.member.dto.FindMemberInfoReturn;
import com.lj.business.member.dto.FindMemberRecord;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.FindPmInfoAll;
import com.lj.business.member.dto.FindPmInfoAllReturn;
import com.lj.business.member.dto.FindPmSeachPage;
import com.lj.business.member.dto.FindPmSeachPageReturn;
import com.lj.business.member.dto.FindPmTypeIndex;
import com.lj.business.member.dto.FindPmTypeIndexPage;
import com.lj.business.member.dto.FindPmTypeIndexPageReturn;
import com.lj.business.member.dto.FindPmTypeIndexReturn;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.FindTmallOrderPage;
import com.lj.business.member.dto.GmLabelDto;
import com.lj.business.member.dto.MecMemberNoReturn;
import com.lj.business.member.dto.PersonMemberExtDto;
import com.lj.business.member.dto.PersonMemberLogin;
import com.lj.business.member.dto.PersonMemberLoginReturn;
import com.lj.business.member.dto.PmLabelDto;
import com.lj.business.member.dto.TmallOrderDto;
import com.lj.business.member.dto.UpdateGuidMember;
import com.lj.business.member.dto.UpdateGuidMemberReturn;
import com.lj.business.member.dto.UpdatePersonMember;
import com.lj.business.member.dto.UpdatePersonMemberBase;
import com.lj.business.member.dto.UpdatePersonMemberReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopPage;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopPageReturn;
import com.lj.business.member.dto.guidCard.FindGuidCard;
import com.lj.business.member.dto.guidCard.FindGuidCardReturn;
import com.lj.business.member.dto.guidCardCustomer.GuidCardAddNumDto;
import com.lj.business.member.dto.guidMemberIntegral.GuidMemberIntegralDto;
import com.lj.business.member.dto.guidMemberIntegralDay.FindGuidMemberIntegralDay;
import com.lj.business.member.dto.guidMemberIntegralDay.FindGuidMemberIntegralDayReturn;
import com.lj.business.member.dto.guidmemberActionInfo.FindGuidmemberActionInfoReturn;
import com.lj.business.member.dto.im.FindImFriendsPage;
import com.lj.business.member.dto.shopterminal.AddShopTerminal;
import com.lj.business.member.dto.shopterminal.AddShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.UpdateWorkKey;
import com.lj.business.member.emus.IntegralType;
import com.lj.business.member.emus.ShopTerminalStatus;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGmLabelService;
import com.lj.business.member.service.IGuidCardService;
import com.lj.business.member.service.IGuidMemberIntegralDayService;
import com.lj.business.member.service.IGuidMemberIntegralService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IGuidmemberActionInfoService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.IMemLineService;
import com.lj.business.member.service.IMemberLoginService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberExtService;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmLabelService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.member.service.ITmallOrderService;
import com.lj.business.supcon.dto.token.Token;
import com.lj.business.supcon.service.ITokenService;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountReturn;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IWxPublicAccountService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.GroupName;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.RedisCache;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.ISystemService;
import com.lj.oms.service.OfficeHessianService;
import com.ye.business.rw.constant.RwConstant;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 
 * 
 * 类说明：会员相关信息处理action 修改人 修改日期 内容
 * -------------------------------------------------------- rain 2017-07-03
 * 添加客户管理首页接口queryMbr,urgentMbr,urgentOpMbr,
 * unGroupMbr,favorMbr,unfavorMbr,orderMbr,abandonMbr， repeatMbr rain 2017-07-04
 * 添加客户管理跟进接口cfHistory,addCFRecord，addCFOrder，abandonMember rain 2017-07-05
 * 添加客户管理跟进接口inqueryComTaskList
 * ，addClientKeep,clientKeepHistory，abandonHistory，orderHistory
 * 
 * --------------------------------------------------------
 * <p>
 * 详细描述：
 * 
 * 
 * @author 彭阳
 * 
 * 
 *         CreateDate: 2017年7月3日
 */
@Controller
@RequestMapping(value = "/member/")
public class MemberAction extends Action {

	private static Logger logger = LoggerFactory.getLogger(MemberAction.class);

	@Resource
	public IMemberLoginService memberLoginService;

	@Resource
	public IPersonMemberService personMemberService;

	@Resource
	private IGmAssistantShopService gmAssistantShopService;

	@Resource
	public IPmTypeService pmTypeService;
	@Resource
	public IMemLineService memLineService;
	@Resource
	public IGuidMemberService guidMemberService;

	@Resource
	public IManagerMemberService managerMemberService;

	@Resource
	public IGuidMemberIntegralService guidMemberIntegralService;

	@Resource
	private IGuidmemberActionInfoService guidmemberActionInfoService;

	@Resource
	private IGuidMemberIntegralDayService guidMemberIntegralDayService;
	@Resource
	private IGuidCardService guidCardService;
	@Resource
	private ITokenService tokenService;

	@Resource
	private ISystemService systemService;

	@Autowired
	private IPmLabelService pmLabelService;
	@Resource
	private IShopTerminalService shopTerminalService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Autowired
	private RedisCache redisCache; // 记录并区分添加方式
	@Autowired
	private IPersonMemberImService personMemberImService;
	@Autowired
	private IPersonMemberExtService personMemberExtService;
	@Resource
	public IPersonMemberBaseService personMemberBaseService;
	@Autowired
	private IImChatInfoService imChatInfoService;
	@Autowired
	private OfficeHessianService officeHessianService;
	@Autowired
	private IWxPublicAccountService wxPublicAccountService;
	@Autowired
	private ITmallOrderService tmallOrderService;
	// netty地址key
	public final String REDISNETTYKEY = "REDIS-nettyAddress-";
	@Autowired
	private IGmLabelService gmLabelService;

	/**
	 * 
	 *
	 * 方法说明：API测试方法
	 *
	 * @param mobile
	 * @return
	 * 
	 * @author 彭阳 CreateDate: 2017年6月22日
	 *
	 */
	@RequestMapping(value = "appTest.do")
	@ResponseBody
	public Map<String, String> appTest(FindIntegralDayDto mobile) {
		logger.debug("appTest(String mobile={}) - start", mobile);

		/*
		 * PersonMemberLoginReturn personMemberLoginReturn = new
		 * PersonMemberLoginReturn();
		 * personMemberLoginReturn.setMemberNameGuid("memberName1111111111");
		 * personMemberLoginReturn.setCreateDate(new Date());
		 */
		Map<String, String> map = new HashMap<String, String>();
		map.put("key1111", "value1111");
		map.put("key2222", "value2222");
		map.put("key3333", "value3333");

		return map;
	}

	/**
	 * 
	 *
	 * 方法说明：工作完成度
	 *
	 * @param findSalesGmDayFirstIndex
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年8月17日
	 *
	 */
	@RequestMapping(value = "findIntegralDay.do")
	@ResponseBody
	public FindIntegralDayDtoReturn findIntegralDay(FindIntegralDayDto findIntegralDayDto) {
		logger.debug("findIntegralDay(FindIntegralDayDto findIntegralDayDto={}) - start", findIntegralDayDto);

		FindGuidMemberIntegralDay findGuidMemberIntegralDay = new FindGuidMemberIntegralDay();
		findGuidMemberIntegralDay.setMemberNo(findIntegralDayDto.getMemberNoGm());
		findGuidMemberIntegralDay.setDaySt(findIntegralDayDto.getWorkDate());
		List<FindGuidMemberIntegralDayReturn> list = guidMemberIntegralDayService
				.findByGMDayList(findGuidMemberIntegralDay);
		Double total = 0.0;
		if (list != null && list.size() > 0) {
			for (FindGuidMemberIntegralDayReturn findGuidMemberIntegralDayReturn : list) {
				total = total + findGuidMemberIntegralDayReturn.getIntegralScore();
			}
		}
		FindIntegralDayDtoReturn findIntegralDayDtoReturn = new FindIntegralDayDtoReturn();
		findIntegralDayDtoReturn.setTotal(total);

		logger.debug("findIntegralDay() - end - return value={}", findIntegralDayDtoReturn);
		return findIntegralDayDtoReturn;
	}

	/**
	 * APP登录，使用oms同一账户体系 member#导购表和oms#用户表创建用户时已同步
	 * 
	 * @param personMemberLogin
	 * @param appKey
	 * @return
	 */
	@RequestMapping(value = "appLogin.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PersonMemberLoginReturn appLogin(PersonMemberLogin personMemberLogin, String appKey) {
		logger.debug("appLogin(PersonMemberLogin personMemberLogin={}) - start", personMemberLogin);

		PersonMemberLoginReturn loginReturn = memberLoginService.personMemberLoginAPP(personMemberLogin);
		Map<String, String> nettyMap = localCacheSystemParams.getSystemParamGroup(SystemAliasName.ms.toString(),
				"nettyAddress");
		String nettyPort = nettyMap.get("port");

		loginReturn.setNettyAddress(getNettyUrlByZK(loginReturn.getNoWx(), personMemberLogin.getversion()));
		loginReturn.setNettyPort(nettyPort);
		// 生成令牌
		if (StringUtils.isNotEmpty(appKey)) {
			Token token = tokenService.generateToken(loginReturn.getMemberNoGuid(), appKey,
					Token.TOKEN_TIMEOUT_SECONDS);
			loginReturn.setToken(token.getAccessToken());
		}
		
		// 是否开启ios 热文服务
		loginReturn.setIosOpen(localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.IOS_OPEN_SHOW, RwConstant.IOS_OPEN_SHOW));

		logger.debug("appLogin(PersonMemberLoginReturn={}) - end", loginReturn);
		return loginReturn;
	}

	/**
	 * 
	 *
	 * 方法说明：APP切换账号登录
	 *
	 * @param personMemberLogin
	 * @param appKey
	 * @param httpServletRequest
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月3日
	 *
	 */
	@RequestMapping(value = "appChangeAccountLogin.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PersonMemberLoginReturn appChangeAccountLogin(PersonMemberLogin personMemberLogin, String token,
			String appKey) {
		logger.debug("appLogin(PersonMemberLogin personMemberLogin={}) - start", personMemberLogin);
		PersonMemberLoginReturn loginReturn = memberLoginService.changeAccountLoginAPP(personMemberLogin);

		loginReturn.setNettyAddress(getNettyUrlByZK(loginReturn.getNoWx(), personMemberLogin.getversion()));
		Map<String, String> nettyMap = localCacheSystemParams.getSystemParamGroup(SystemAliasName.ms.toString(),
				"nettyAddress");
		String nettyPort = nettyMap.get("port");
		loginReturn.setNettyPort(nettyPort);
		// 生成令牌
		if (StringUtils.isEmpty(token)) {
			Token token2 = tokenService.generateToken(loginReturn.getMemberNoGuid(), appKey,
					Token.TOKEN_TIMEOUT_SECONDS);
			loginReturn.setToken(token2.getAccessToken());
		} else {
			loginReturn.setToken(token);
		}
		logger.debug("appLogin(PersonMemberLoginReturn={}) - end", loginReturn);
		return loginReturn;
	}

	/**
	 * 导购助手可切换的微信列表
	 * 
	 * @param model
	 * @param merchantNo
	 * @param loginName
	 * @param findGmAssistantShopPage
	 * @return
	 */
	@RequestMapping(value = "findAccountPage.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public IPage<FindGmAssistantShopPageReturn> findAccountPage(String merchantNo, String loginName,
			FindGmAssistantShopPage findGmAssistantShopPage) {
		AssertUtils.notNullAndEmpty(merchantNo, "商户号不能为空");
		AssertUtils.notNullAndEmpty(loginName, "登录名不能为空");
		findGmAssistantShopPage.setMerchantNo(merchantNo);
		findGmAssistantShopPage.setLoginName(loginName);
//		findGmAssistantShopPage.setSource(true);	BOSS可以登陆下属微信 DZP 2019-04-22 4月份需求
		Page<FindGmAssistantShopPageReturn> pages = gmAssistantShopService
				.findGmAssistantWithTerminalPage(findGmAssistantShopPage);
		return pages;
	}

	/**
	 * 
	 *
	 * 方法说明：中控客户端登录
	 *
	 * @param personMemberLogin
	 * @param appKey
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月5日
	 * @deprecated 弃用 使用扫码登录，不再使用 导购的账号手动登录 2019.03.01
	 */
	@RequestMapping(value = "zkLoginOld.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PersonMemberLoginReturn zkLoginOld(PersonMemberLogin personMemberLogin, String appKey) {
		logger.debug("zkLogin(PersonMemberLogin personMemberLogin={}) - start", personMemberLogin);
		PersonMemberLoginReturn loginReturn = memberLoginService.personMemberLoginAPP(personMemberLogin);

		String nettyAddressArrayStr = localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),
				"nettyAddress", "url");

		loginReturn.setNettyAddress(
				getNettyUrl(nettyAddressArrayStr, personMemberLogin.getNoWx(), personMemberLogin.getVersionCode()));

		FindShopTerminalReturn shopTerminal = null;

		shopTerminal = shopTerminalService.findShopTerminalByWx(personMemberLogin.getNoWx());

		// 验证终端信息
		if (shopTerminal == null) {
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_EXIST_ERROR, "非法终端：终端不存在");
		}
		if (shopTerminal.getStatus() != 1) { // 正常状态
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_NORMAL_ERROR, "终端状态非法");
		}
		if (StringUtils.isEmpty(shopTerminal.getNoWx())) { // 必须绑定微信号
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_BIND_WX, "终端没有绑定微信");
		}
		if (!loginReturn.getNoWx().equals(shopTerminal.getNoWx())) { // 导购绑定微信必须与终端绑定微信一致
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_SAME_WX, "导购绑定微信必须与终端绑定微信一致");
		}

		// 生成令牌
		if (!StringUtils.isEmpty(appKey)) {
			Token token = tokenService.generateToken(loginReturn.getMemberNoGuid(), appKey,
					Token.TOKEN_TIMEOUT_SECONDS);
			loginReturn.setToken(token.getAccessToken());
			logger.info("已生成访问令牌");
		}

		// 更新终端工作密钥
		UpdateWorkKey updateWorkKey = new UpdateWorkKey();
		updateWorkKey.setCode(shopTerminal.getCode());
		updateWorkKey.setWorkKey(loginReturn.getToken()); // 登录成功后，访问令牌即终端的工作密钥
		shopTerminalService.updateWorkKey(updateWorkKey);
		logger.info("已更新终端工作密钥");

		/**
		 * 更新中控微信相关数据 zkInfo 异步
		 * 
		 */
//		synUpdateZkInfo(shopTerminal,personMemberLogin.getZkInfo());

		logger.debug("zkLogin(PersonMemberLoginReturn={}) - end", loginReturn);
		return loginReturn;
	}

	/**
	 * 
	 *
	 * 方法说明：中控客户端扫码登录 1、中控存在则返回token和中控信息 2、中控不存在的时候，添加新的终端并返回
	 * 
	 * @param personMemberLogin
	 * @param appKey
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月5日
	 *
	 */
	@RequestMapping(value = "zkLogin.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> zkLogin(String noWx, String alias, String headurl, String nickname, String appKey,
			String merchantNo, String qcord) {
		logger.debug("zkLogin(String noWx={}, String appKey={},String merchantNo={}) - start", noWx, appKey,
				merchantNo);

		Map<String, String> returnMap = new HashMap<>();

		Office office = officeHessianService.findOfficeByMerchantNo(merchantNo);
		if (null == office) {
			throw new TsfaServiceException(ErrorCode.MERCHANT_NOT_EXIST_ERROR, "商户不存在");
		}
		// 兼容线上已有数据，已别名优先
		noWx = StringUtils.isNotEmpty(alias) ? alias : noWx;
		FindShopTerminalReturn shopTerminal = shopTerminalService.findShopTerminalByWx(noWx);
		// 验证终端信息
		if (shopTerminal == null) {
			logger.info("终端不存在：添加终端信息");
			shopTerminal = addShopTerminal(noWx, alias, nickname, merchantNo, office, headurl);
		} else {
			if (shopTerminal.getStatus() != 1) { // 正常状态
				throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_NORMAL_ERROR, "终端状态非法");
			}
			if (StringUtils.isEmpty(shopTerminal.getNoWx())) { // 必须绑定微信号
				throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_BIND_WX, "终端没有绑定微信");
			}
		}

		/**
		 * 返回信息
		 */
		returnMap.put("memberNoMerchant", shopTerminal.getMerchantNo());
		returnMap.put("memberNameMerchant", office.getName());
		returnMap.put("terminalNo", shopTerminal.getTerminalCode());
		returnMap.put("code", shopTerminal.getCode());

		Map<String, String> nettyMap = localCacheSystemParams.getSystemParamGroup(SystemAliasName.ms.toString(),
				"nettyAddress");
		String nettyAddressArrayStr = nettyMap.get("url");
		String nettyPort = nettyMap.get("port");

		returnMap.put("nettyAddress", getNettyUrl(nettyAddressArrayStr, noWx, ""));
		returnMap.put("nettyPort", nettyPort);

		String ipAddress = localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), "common", "commonUrl");
		returnMap.put("ipAddress", ipAddress);
		String uploadUrl = localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),
				SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
		returnMap.put("uploadUrl", uploadUrl);
		String weixinAppid = localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(),
				GroupName.mec_weixin.toString(), Constants.WEIXIN_APPID);
		returnMap.put("weixinAppid", weixinAppid);
//		String wxUpdateUrl =  localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(),SystemParamConstant.GRP_MOBILE_VERSION, SystemParamConstant.ANDROID_WX_DOWNLOAD_URL);
//		returnMap.put("wxUpdateUrl", wxUpdateUrl);

		// 生成令牌
		if (StringUtils.isNotEmpty(appKey)) {
			Token token = tokenService.generateToken(shopTerminal.getCode(), appKey, Token.TOKEN_TIMEOUT_SECONDS);
			returnMap.put("token", token.getAccessToken());
			logger.info("已生成访问令牌");
		}

		// 更新终端工作密钥
		UpdateWorkKey updateWorkKey = new UpdateWorkKey();
		updateWorkKey.setCode(shopTerminal.getCode());
		updateWorkKey.setWorkKey(returnMap.get("token")); // 登录成功后，访问令牌即终端的工作密钥
		boolean upFlag = false;
		if (StringUtils.isEmpty(shopTerminal.getAlias()) || !shopTerminal.getAlias().equals(alias)) {
			updateWorkKey.setAlias(alias);
			upFlag = true;
		}
		if (StringUtils.isEmpty(shopTerminal.getWxNickname()) || !shopTerminal.getWxNickname().equals(nickname)) {
			updateWorkKey.setNickname(nickname);
			updateWorkKey.setWxNickname(nickname);
			upFlag = true;
		}
		if (StringUtils.isEmpty(shopTerminal.getHeadAddress()) || !shopTerminal.getHeadAddress().equals(headurl)) {
			updateWorkKey.setHeadurl(headurl);
			updateWorkKey.setHeadAddress(headurl);
			upFlag = true;
		}
		if (StringUtils.isEmpty(shopTerminal.getQcord()) || !shopTerminal.getQcord().equals(qcord)) {
			updateWorkKey.setQcord(qcord);
			upFlag = true;
		}
		if (upFlag) {
			shopTerminalService.updateWorkKey(updateWorkKey);
			logger.info("已更新终端工作密钥");
		}
		logger.debug("zkLogin(={}) - end", returnMap);
		return returnMap;
	}

	/**
	 * 添加终端
	 * 
	 * @param noWx
	 * @param usernameWx
	 * @param wxNickname
	 * @param merchantNo
	 * @param office
	 * @return
	 * @throws TsfaServiceException
	 */
	private FindShopTerminalReturn addShopTerminal(String noWx, String alias, String nickname, String merchantNo,
			Office office, String headurl) throws TsfaServiceException {
		AddShopTerminal addShopTerminal = new AddShopTerminal();
		addShopTerminal.setMerchantNo(merchantNo);
		addShopTerminal.setMerchantName(office.getName());
		addShopTerminal.setNoWx(noWx);
		addShopTerminal.setAlias(alias);
		addShopTerminal.setNickname(nickname);
		addShopTerminal.setHeadurl(headurl);
		addShopTerminal.setCreateId(office.getName());
		addShopTerminal.setStatus(ShopTerminalStatus.NORMAL.getValue());
		addShopTerminal.setUploadFriendsFlag(1);
		addShopTerminal.setWxNickname(nickname);
		AddShopTerminalReturn addShopTerminalReturn = shopTerminalService.addShopTerminal(addShopTerminal);
		FindShopTerminalReturn shopTerminal = new FindShopTerminalReturn();
		shopTerminal.setCode(addShopTerminalReturn.getCode());
		shopTerminal.setTerminalCode(addShopTerminalReturn.getTerminalCode());
		shopTerminal.setMerchantNo(merchantNo);
		shopTerminal.setMerchantName(office.getName());
		shopTerminal.setAlias(alias);
		shopTerminal.setNickname(nickname);
		shopTerminal.setHeadurl(headurl);
		shopTerminal.setNoWx(noWx);
		return shopTerminal;
	}

	/**
	 * netty 负载均衡
	 * 
	 */
	public String getNettyUrl(String nettyAddressArrayStr, String noWx, String versionCode) {

		logger.info("中控版本号：" + versionCode);

		String nettyAddressArray[] = null;

		if (nettyAddressArrayStr != null && !nettyAddressArrayStr.equals("")) {
			nettyAddressArray = nettyAddressArrayStr.split(",");
			String s = redisCache.get("REDIS-nettyAddress");

			// #######为了快推升级兼容某版本，后期可删除########(start)

			if (versionCode == null || versionCode.equals("")) {

				logger.info("367版本：" + nettyAddressArray[0]);
				// 选择第一台机
				redisCache.set("REDIS-nettyAddress", "0");
				// 记录中控登录地址
				redisCache.set(CommonConstant.REDISNETTYKEY + noWx, nettyAddressArray[0]);
				return nettyAddressArray[0];
			}

			if (versionCode.equals("367") || versionCode.equals("366")) {

				logger.info("367版本：" + nettyAddressArray[0]);
				// 选择第一台机
				redisCache.set("REDIS-nettyAddress", "0");
				// 记录中控登录地址
				redisCache.set(CommonConstant.REDISNETTYKEY + noWx, nettyAddressArray[0]);
				return nettyAddressArray[0];
			}
			// #######为了快推升级兼容某版本，后期可删除########(end)

			// ************ 中控不是首次登录 （再次登录默认选择原来的登录机子，防止集群环境下导购端取不到资源） *********************
			String nettyUrl = redisCache.get(CommonConstant.REDISNETTYKEY + noWx);
			if (nettyUrl != null && !nettyUrl.equals("")) {
				return nettyUrl;
			}

			// ************************ 中控首次登录 (使用负载均衡算法)****************************
			// 简单负载均衡选举(不考虑大并发登录)
			if (s == null || nettyAddressArray.length == 1) {
				// 选择第一台机
				redisCache.set("REDIS-nettyAddress", "0");
				// 记录中控登录地址
				redisCache.set(CommonConstant.REDISNETTYKEY + noWx, nettyAddressArray[0]);
				return nettyAddressArray[0];
			}

			if (s != null && !s.equals("") && nettyAddressArray.length > 1) {
				// 选择第一台机
				int num = Integer.valueOf(s) + 1;
				if (num > nettyAddressArray.length) {

					redisCache.set("REDIS-nettyAddress", "0");
					// 记录中控登录地址
					redisCache.set(CommonConstant.REDISNETTYKEY + noWx, nettyAddressArray[0]);
					return nettyAddressArray[0];
				}
				// 选择下一台机
				if (num == nettyAddressArray.length || num < nettyAddressArray.length) {
					redisCache.set("REDIS-nettyAddress", String.valueOf(num));
					// 记录中控登录地址
					redisCache.set(CommonConstant.REDISNETTYKEY + noWx, nettyAddressArray[num]);
					return nettyAddressArray[num];
				}
			}
		}
		return "";
	}

	/**
	 * netty 根据中控登录的地址获取导购登录的地址（集群环境下中控和导购必须登录同一台机子）
	 * 
	 */
	public String getNettyUrlByZK(String gmNoWx, String version) {
		// 记录中控登录地址
		return redisCache.get(CommonConstant.REDISNETTYKEY + gmNoWx);
	}

	/**
	 * 
	 *
	 * 方法说明：H5登录接口（店长和BOOS） 如果既有wxCode ，也有手机号，视为第一次登录 如果只有wxCode ，没有手机号，视为微信自动登录
	 * (快推使用)
	 * 
	 * @param personMemberLogin
	 * @param httpServletRequest
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月3日
	 *
	 */
	@RequestMapping(value = "h5Login.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<Object, Object> h5Login(String mobile, String pwd, String wxCode, String appKey, String version)
			throws JsonProcessingException {
		PersonMemberLogin personMemberLogin = new PersonMemberLogin();
		if (!com.lj.base.core.util.StringUtils.isEmpty(wxCode)) {
			try {
				String openId = WeixinUtil.getOpenId(wxCode);
				personMemberLogin.setOpenIdGzhWx(openId);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		personMemberLogin.setMobile(mobile);
		personMemberLogin.setPwd(WxMD5Util.MD5Encode(pwd, "UTF-8"));
		PersonMemberLoginReturn personMemberLoginReturn = memberLoginService.personMemberLoginH5(personMemberLogin);

		// 获取导购端需要连接的中控服务地址
		personMemberLoginReturn.setNettyAddress(getNettyUrlByZK(personMemberLoginReturn.getNoWx(), version));

		// 生成令牌
		if (!StringUtils.isEmpty(appKey)) {
			Token token = tokenService.generateToken(personMemberLoginReturn.getMemberNoGuid(), appKey,
					Token.TOKEN_TIMEOUT_SECONDS);
			personMemberLoginReturn.setToken(token.getAccessToken());
		}

		// 导购关联的管理员用户信息
		Map<Object, Object> data = new LinkedHashMap<>();

		data.putAll(new BeanMap(personMemberLoginReturn));
//		adminUserLogin(data, personMemberLoginReturn.getMobile());

		logger.info("h5Login data:{}", data);

		return data;
	}

	/**
	 * 
	 *
	 * 方法说明：客户管理首页：分类信息查询
	 *
	 * @param findPmTypeIndex
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月11日
	 *
	 */
	@RequestMapping(value = "findPmTypeIndex.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<FindPmTypeIndexReturn> findPmTypeIndex(FindPmTypeIndex findPmTypeIndex) {

		List<FindPmTypeIndexReturn> list = pmTypeService.findPmTypeIndex(findPmTypeIndex);
		/**
		 * 增加虚拟未分组
		 */
		int count = pmTypeService.findCountByUngroup(findPmTypeIndex);
		FindPmTypeIndexReturn findPmTypeIndexReturn = new FindPmTypeIndexReturn();
		findPmTypeIndexReturn.setNumClient(Long.valueOf(count));
		findPmTypeIndexReturn.setTypeName("未分组");
		list.add(0, findPmTypeIndexReturn);
		return list;
	}

	/**
	 * 
	 *
	 * 方法说明：客户管理首页：分类信息明细查询
	 *
	 * @param findPmTypeIndex
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月11日
	 *
	 */
	@RequestMapping(value = "findPmTypeIndexPage.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindPmTypeIndexPageReturn> findPmTypeIndexPage(FindPmTypeIndexPage findPmTypeIndexPage) {
		// findPmTypeIndexPage.setLimit(ApiConstans.LIMIT);
		return personMemberService.findPmTypeIndexPage(findPmTypeIndexPage);
	}

	/**
	 * 
	 *
	 * 方法说明：客户管理首页：分类信息明细查询(LOHO)
	 *
	 * @param findPmTypeIndexPage
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月9日
	 *
	 */
	@RequestMapping(value = "findPmTypeIndexPageLoho.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindPmTypeIndexPageReturn> findPmTypeIndexPageLoho(FindPmTypeIndexPage findPmTypeIndexPage) {
		// findPmTypeIndexPage.setLimit(ApiConstans.LIMIT);
		return personMemberService.findPmTypeIndexPageLoho(findPmTypeIndexPage);
	}

	/**
	 * 
	 *
	 * 方法说明：客户管理首页：分类信息及其明细查询
	 *
	 * @param findPmTypeIndex
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月11日
	 *
	 */
	@RequestMapping(value = "findPmTypeIndexAll.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<FindPmTypeIndexAllReturn> findPmTypeIndexAll(FindPmTypeIndex findPmTypeIndex) {

		List<FindPmTypeIndexAllReturn> resultList = new ArrayList<FindPmTypeIndexAllReturn>();
		List<FindPmTypeIndexReturn> list = pmTypeService.findPmTypeIndex(findPmTypeIndex);
		for (FindPmTypeIndexReturn findPmTypeIndexReturn : list) {

			FindPmTypeIndexAllReturn findPmTypeIndexAllReturn = new FindPmTypeIndexAllReturn();
			findPmTypeIndexAllReturn.setPmTye(findPmTypeIndexReturn);
			FindPmTypeIndexPage findPmTypeIndexPage = new FindPmTypeIndexPage();
			findPmTypeIndexPage.setLimit(Constans.LIMIT);
			findPmTypeIndexPage.setMemberNoGm(findPmTypeIndex.getMemberNoGm());
			findPmTypeIndexPage.setMerchantNo(findPmTypeIndex.getMerchantNo());
			findPmTypeIndexPage.setPmTypeCode(findPmTypeIndexReturn.getCode());
//			findPmTypeIndexPage.setShopNo(findPmTypeIndex.getShopNo());
			Page<FindPmTypeIndexPageReturn> page = personMemberService.findPmTypeIndexPage(findPmTypeIndexPage);
			findPmTypeIndexAllReturn.setDetail(page.getRows());
			resultList.add(findPmTypeIndexAllReturn);

		}

		return resultList;
	}

	/**
	 * 
	 *
	 * 方法说明：更改客户所属分类_APP
	 *
	 * @param findPmSeachPage
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月11日
	 *
	 */
	@RequestMapping(value = "changePmTypeApp.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse changePmTypeApp(ChangePmTypeApp changePmTypeApp) {
//		pmTypeService.changePmType_app(changePmTypeApp);

		UpdatePersonMember updatePersonMember = new UpdatePersonMember();
		updatePersonMember.setCode(changePmTypeApp.getCode());
		updatePersonMember.setPmTypeCode(changePmTypeApp.getPmTypeCode());
		personMemberService.updatePmType(updatePersonMember);
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 *
	 * 方法说明：更改客户所属分类_APP 未分组移动到其他分组
	 *
	 * @param changePmTypeUngroup
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月27日
	 *
	 */
	@RequestMapping(value = "changePmTypeAppUngroup.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse changePmTypeUngroup(ChangePmTypeUngroup changePmTypeUngroup) {
//		pmTypeService.changePmType_app_ungroup(changePmTypeUngroup);

		FindPersonMember findPersonMember = new FindPersonMember();
		findPersonMember.setMemberNo(changePmTypeUngroup.getMemberNo());
		findPersonMember.setMemberNoGm(changePmTypeUngroup.getMemberNo());
		FindPersonMemberReturn findPersonMemberReturn = personMemberService
				.findPersonMemberByMemberNoAndGM(findPersonMember);
		if (null == findPersonMemberReturn) {
			return GeneralResponse.generateFailureResponse();
		}
		UpdatePersonMember updatePersonMember = new UpdatePersonMember();
		updatePersonMember.setCode(findPersonMemberReturn.getCode());
		updatePersonMember.setPmTypeCode(changePmTypeUngroup.getPmTypeCode());
		personMemberService.updatePmType(updatePersonMember);

		// 添加积分
		if (org.springframework.util.StringUtils.hasText(changePmTypeUngroup.getMemberNoGm())
				&& org.springframework.util.StringUtils.hasText(changePmTypeUngroup.getMerchantNo())) {
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(changePmTypeUngroup.getMemberNoGm());
			FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
			GuidMemberIntegralDto guidMemberIntegralDto = new GuidMemberIntegralDto();
			guidMemberIntegralDto.setMerchantNo(changePmTypeUngroup.getMerchantNo());
			guidMemberIntegralDto.setMemberNo(changePmTypeUngroup.getMemberNoGm());
			if (findGuidMemberReturn != null) {
//				guidMemberIntegralDto.setShopNo(findGuidMemberReturn.getShopNo());
				guidMemberIntegralDto.setAreaCode(findGuidMemberReturn.getAreaCode());
				guidMemberIntegralDto.setAreaName(findGuidMemberReturn.getAreaName());
			}
			guidMemberIntegralDto.setIntegralType(IntegralType.COM_TASK.toString());
			guidMemberIntegralDto.setAmount(1d);
			guidMemberIntegralService.doIntegral(guidMemberIntegralDto);
		}

		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 *
	 * 方法说明：客户管理搜索：分页查询
	 *
	 * @param findPmSeachPage
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月11日
	 *
	 */
	@RequestMapping(value = "findPmSeachPage.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindPmSeachPageReturn> findPmSeachPage(FindPmSeachPage findPmSeachPage) {
		// findPmSeachPage.setLimit(ApiConstans.LIMIT);
		return personMemberService.findPmSeachPage(findPmSeachPage);
	}

	/**
	 * 
	 * 方法说明：我的客户-新增客户，提供客户职业组基本信息查询
	 * 
	 * @param
	 * @return
	 *
	 * @author rain CreateDate: 2017年7月3日
	 *
	 */
	@RequestMapping(value = "inqueryMemberJobInfo.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MemLine> inqueryMemberJobInfo(String merchantNo, HttpServletRequest httpServletRequest) {
		List<MemLine> list = memLineService.inqueryMemberJobInfo(merchantNo);
		return list;
	}

	/**
	 * 
	 * 方法说明：我的客户-新增客户，提供客户分组基本信息查询
	 * 
	 * @param
	 * @return
	 *
	 * @author rain CreateDate: 2017年7月3日
	 *
	 */
	@RequestMapping(value = "inqueryMemberGroupInfo.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<FindPmTypePageReturn> inqueryMemberGroupInfo(String merchantNo, HttpServletRequest httpServletRequest) {
		FindPmTypePageReturn findPmTypePageReturn = new FindPmTypePageReturn();
		findPmTypePageReturn.setMerchantNo(merchantNo);
		findPmTypePageReturn.setStatus(CommonConstant.Y);
		return pmTypeService.findPmTypePages(findPmTypePageReturn);
	}

	/**
	 * 
	 *
	 * 方法说明：查找客户及其基本信息
	 *
	 * @param findPmInfoAll
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月13日
	 *
	 */
	@RequestMapping(value = "findPmInfoAll.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public FindPmInfoAllReturn findPmInfoAll(FindPmInfoAll findPmInfoAll) {
		return personMemberService.findPmInfoAll(findPmInfoAll);
	}

	/**
	 * 我的客户-客户资料-编辑
	 * 
	 * @return
	 * @author rain CreateDate: 2017年7月3日
	 *
	 */
	@RequestMapping(value = "modifyMemberInfo.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse modifyMemberInfo(String noWxGm, String paramJson, HttpServletRequest httpServletRequest) {
		logger.debug(
				"modifyMemberInfo(EditPersonMember editPersonMember={}, HttpServletRequest httpServletRequest={}) - start",
				paramJson, httpServletRequest);

		@SuppressWarnings("rawtypes")
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("labels", PmLabelDto[].class);
		EditPersonMember editPersonMember = (EditPersonMember) JsonUtils.objectFromJson(paramJson,
				EditPersonMember.class, classMap);
//		EditPersonMember editPersonMember = JSON.parseObject(paramJson, EditPersonMember.class);
		// 如果是初次更新手机号码,则设置更新手机号码时间
		PersonMemberBase personMemberBase = personMemberBaseService.checkMobile(editPersonMember);
		if (null == personMemberBase || (StringUtils.isEmpty(personMemberBase.getMobile())
				&& null == personMemberBase.getAddMobileDate())) {
			editPersonMember.setAddMobileDate(new Date());
		}
		editPersonMember.setNoWxGm(noWxGm);
		UpdatePersonMemberReturn memberReturn = personMemberService.editPersonMember(editPersonMember);
		return GeneralResponse.generateSuccessResponse(memberReturn);
	}

	/**
	 * 
	 * 方法说明：我的客户-跟进记录
	 * 
	 * @param AddClientFollow含客户MemberNo ,导购编码memberGMCode
	 * @return
	 *
	 * @author rain CreateDate: 2017年7月4日
	 *
	 */
	/*
	 * @RequestMapping(value = "cfHistory.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public List<FindClientFollowReturn> cfHistory(FindClientFollow
	 * findClientFollow, HttpServletRequest httpServletRequest) { return
	 * clientFollowService.cfHistory(findClientFollow); }
	 */

	/**
	 * 
	 *
	 * 方法说明：跟进或者维护列表
	 *
	 * @param findClientFollowClientKeep
	 * @param httpServletRequest
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月11日
	 *
	 */
	/*
	 * @RequestMapping(value = "cfOrCkHistory.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public FindClientFollowClientKeepReturn
	 * cfOrCkHistory(FindClientFollowClientKeep findClientFollowClientKeep,
	 * HttpServletRequest httpServletRequest) { logger.debug(
	 * "cfOrCkHistory(FindClientFollowClientKeep findClientFollowClientKeep={}, HttpServletRequest httpServletRequest={}) - start"
	 * , findClientFollowClientKeep, httpServletRequest);
	 * 
	 * FindClientFollowClientKeepReturn returnFindClientFollowClientKeepReturn =
	 * clientFollowService .cfOrCkHistory(findClientFollowClientKeep);
	 * 
	 * logger.debug("cfOrCkHistory() - end - return value={}",
	 * returnFindClientFollowClientKeepReturn); return
	 * returnFindClientFollowClientKeepReturn; }
	 */

	/**
	 * 
	 * 方法说明：我的客户-跟进记录-新增
	 * 
	 * @param AddClientFollow含跟踪总表cfNo ,客户MemberNo,导购编码memberGMCode
	 * @return
	 *
	 * @author rain CreateDate: 2017年7月4日
	 *
	 */
	/*
	 * @RequestMapping(value = "addCFRecord.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public AddClientFollowReturn addCFRecord(AddClientFollow
	 * addClientFollow, HttpServletRequest httpServletRequest) {
	 * logger.debug("addCFRecord(AddClientFollow addCFRecord={}) - start",
	 * addClientFollow); CheckPmTypeDto checkPmTypeDto = new CheckPmTypeDto();
	 * checkPmTypeDto.setMemberNo(addClientFollow.getMemberNo());
	 * checkPmTypeDto.setMemberNoGm(addClientFollow.getMemberNoGm());
	 * checkPmTypeDto.setMerchantNo(addClientFollow.getMerchantNo());
	 * CheckPmTypeReturn checkPmTypeReturn =
	 * pmTypeService.checkPmType(checkPmTypeDto);
	 * logger.debug("addCFRecord(checkPmTypeDto checkPmTypeDto={})",
	 * checkPmTypeDto);
	 * logger.debug("addCFRecord(checkPmTypeReturn checkPmTypeReturn={})",
	 * checkPmTypeReturn); if (checkPmTypeReturn != null) { // 客户是否意向(到店)或者意向(非到店)
	 * 指定则产生任务，不指定则不产生任务 if (checkPmTypeReturn.getIntention() != null &&
	 * checkPmTypeReturn.getIntention()) {
	 * addClientFollow.setComTaskType(ComTaskType.COM_TASK.toString());
	 * addClientFollow.setComTaskTypeCf(ComTaskType.COM_TASK.toString()); }
	 * 
	 * // 客户是否非意向 产生沟通任务 if (checkPmTypeReturn.getOther() != null &&
	 * checkPmTypeReturn.getOther()) { FindComTaskList findComTaskList = new
	 * FindComTaskList(); findComTaskList.setComTaskType(ComTaskType.COM_TASK);
	 * findComTaskList.setMerchantNo(addClientFollow.getMerchantNo());
	 * FindComTaskListReturn findComTaskListReturn =
	 * comTaskListService.findComTaskList(findComTaskList); if
	 * (findComTaskListReturn != null) {
	 * addClientFollow.setTaskCode(findComTaskListReturn.getCode()); }
	 * addClientFollow.setComTaskType(ComTaskType.COM_TASK.toString());
	 * addClientFollow.setComTaskTypeCf(ComTaskType.COM_TASK.toString());
	 * 
	 * // 如果没有指定任务时间，获取频率的时间 if (addClientFollow.getNextDate() == null) {
	 * logger.debug("没有指定任务时间，获取频率的时间"); // 获取频率 FindComTaskChoose findComTaskChoose
	 * = new FindComTaskChoose();
	 * findComTaskChoose.setMerchantNo(addClientFollow.getMerchantNo());
	 * findComTaskChoose.setComTaskType(ComTaskType.COM_TASK.toString());
	 * ComTaskChooseReturnDto comTaskChooseReturnDto = comTaskChooseService
	 * .findComTaskChoose(findComTaskChoose); addClientFollow.setNextDate(
	 * DateUtils.addHours(new Date(),
	 * Integer.valueOf(comTaskChooseReturnDto.getFreValue()))); } }
	 * 
	 * // 客户是否未分组 什么都不产生 if (checkPmTypeReturn.getUngroup() != null &&
	 * checkPmTypeReturn.getUngroup()) { addClientFollow.setTaskCode(null);
	 * addClientFollow.setComTaskType(ComTaskType.COM_TASK.toString());
	 * addClientFollow.setComTaskTypeCf(ComTaskType.COM_TASK.toString()); } }
	 * addClientFollow.setRemarkCom(CommonConstant.REPLACE_REMARK_COM + "手工录入跟进记录");
	 * addClientFollow.setLastResultDate(new Date()); AddClientFollowReturn cfr =
	 * clientFollowService.addCFOrder(addClientFollow, "0");
	 * logger.debug("addCFRecord(AddClientFollowReturn addCFRecord={}) - end", cfr);
	 * return cfr; }
	 */

	/**
	 * 
	 * 方法说明：我的客户-跟进记录-成单
	 * 
	 * @param AddClientFollow含跟踪总表cfNo ,客户MemberNo,导购编码memberGMCode,成单金额ORDER_AMOUNT,1代表成单，未成单跟踪录入
	 * @return
	 *
	 * @author rain CreateDate: 2017年7月4日
	 *
	 */
	/*
	 * @RequestMapping(value = "addCFOrder.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public AddClientFollowReturn addCFOrder(AddClientFollow
	 * addClientFollow, HttpServletRequest httpServletRequest) { logger.
	 * debug("addCFOrder(AddClientFollow addClientFollow={}, HttpServletRequest httpServletRequest={}) - start"
	 * , addClientFollow, httpServletRequest);
	 * 
	 * addClientFollow.setRemarkCom(CommonConstant.REPLACE_REMARK_COM + "成单");
	 * addClientFollow.setLastResultDate(new Date()); AddClientFollowReturn acf =
	 * clientFollowService.addCFOrder(addClientFollow, "1");
	 * 
	 * logger.debug("addCFOrder() - end - return value={}", acf); return acf; }
	 */

	/**
	 * 
	 * 方法说明：我的客户-跟进记录-放弃
	 * 
	 * @param AddClientFollow 客户MemberNo,导购编码memberGMCode
	 * @return
	 *
	 * @author rain CreateDate: 2017年7月4日
	 *
	 */
	/*
	 * @RequestMapping(value = "abandonMember.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public AddClientFollowReturn abandonMember(AddClientFollow
	 * addClientFollow, HttpServletRequest httpServletRequest) { logger.debug(
	 * "abandonMember(AddClientFollow addClientFollow={}, HttpServletRequest httpServletRequest={}) - start"
	 * , addClientFollow, httpServletRequest);
	 * 
	 * addClientFollow.setRemarkCom(CommonConstant.REPLACE_REMARK_COM + "申请暂停跟进客户");
	 * addClientFollow.setLastResultDate(new Date()); AddClientFollowReturn acf =
	 * clientFollowService.addCFOrder(addClientFollow, "2");
	 * 
	 * logger.debug("abandonMember() - end - return value={}", acf); return acf; }
	 */

	/**
	 * 
	 *
	 * 方法说明：店长审批-跟进记录-放弃-审批
	 *
	 * @param addClientFollow
	 * @param httpServletRequest
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月11日
	 *
	 */
	/*
	 * @RequestMapping(value = "abandonCheck.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public AbandonCheckReturn abandonCheck(AbandonCheckDto
	 * abandonCheckDto, HttpServletRequest httpServletRequest) { logger.debug(
	 * "abandonCheck(AbandonCheckDto abandonCheckDto={}, HttpServletRequest httpServletRequest={}) - start"
	 * , abandonCheckDto, httpServletRequest);
	 * 
	 * AbandonCheckReturn acr = clientFollowService.abandonCheck(abandonCheckDto);
	 * 
	 * logger.debug("abandonCheck() - end - return value={}", acr); return acr; }
	 */

	/**
	 * 
	 * 方法说明：我的客户-购买记录 TODO
	 * 
	 * @param AddClientFollow含客户MemberNo ,导购编码memberGMCode
	 * @return
	 *
	 * @author rain CreateDate: 2017年7月4日
	 *
	 */
	/*
	 * @RequestMapping(value = "orderHistory.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public List<FindClientFollowReturn>
	 * orderHistory(FindClientFollow findClientFollow, HttpServletRequest
	 * httpServletRequest) { logger.debug(
	 * "orderHistory(FindClientFollow findClientFollow={}, HttpServletRequest httpServletRequest={}) - start"
	 * , findClientFollow, httpServletRequest);
	 * 
	 * List<FindClientFollowReturn> returnList =
	 * clientFollowService.orderHistory(findClientFollow);
	 * logger.debug("orderHistory() - end - return value={}", returnList); return
	 * returnList; }
	 */

	/**
	 * 
	 * 方法说明：我的客户-放弃记录
	 * 
	 * @param AddClientFollow含客户MemberNo ,导购编码memberGMCode
	 * @return
	 *
	 * @author rain CreateDate: 2017年7月4日
	 *
	 */
	/*
	 * @RequestMapping(value = "abandonHistory.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public List<FindPmAbandonReturn> abandonHistory(FindPmAbandon
	 * findPmAbandon, HttpServletRequest httpServletRequest) { logger.
	 * debug("abandonHistory(FindPmAbandon findPmAbandon={}, HttpServletRequest httpServletRequest={}) - start"
	 * , findPmAbandon, httpServletRequest);
	 * 
	 * List<FindPmAbandonReturn> returnList =
	 * pmAbandonService.abandonHistory(findPmAbandon);
	 * logger.debug("abandonHistory() - end - return value={}", returnList); return
	 * returnList; }
	 */

	/**
	 * 
	 * 方法说明：我的客户-维护记录 TODO
	 * 
	 * @param AddClientFollow含客户MemberNo ,导购编码memberGMCode
	 * @return
	 *
	 * @author rain CreateDate: 2017年7月4日
	 *
	 */
	/*
	 * @RequestMapping(value = "clientKeepHistory.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public List<FindClientKeepReturn>
	 * clientKeepHistory(FindClientKeep findClientKeep, HttpServletRequest
	 * httpServletRequest) { logger.debug(
	 * "clientKeepHistory(FindClientKeep findClientKeep={}, HttpServletRequest httpServletRequest={}) - start"
	 * , findClientKeep, httpServletRequest);
	 * 
	 * List<FindClientKeepReturn> returnList =
	 * clientKeepService.clientKeepHistory(findClientKeep);
	 * logger.debug("clientKeepHistory() - end - return value={}", returnList);
	 * return returnList; }
	 */

	/**
	 * 
	 * 方法说明：客户管理-任务类型列表
	 * 
	 * @param
	 * @return
	 * 
	 * @author rain CreateDate: 2017年7月4日
	 *
	 */
	/*
	 * @RequestMapping(value = "inqueryComTaskList.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public List<FindComTaskChoosePageDto> inqueryComTaskList(String
	 * merchantNo, HttpServletRequest httpServletRequest) { logger.
	 * debug("inqueryComTaskList(String merchantNo={}, HttpServletRequest httpServletRequest={}) - start"
	 * , merchantNo, httpServletRequest);
	 * 
	 * FindComTaskChoosePageDto dto = new FindComTaskChoosePageDto();
	 * dto.setMerchantNo(merchantNo); List<FindComTaskChoosePageDto> returnList =
	 * comTaskChooseService.findComTaskChoosesApp(dto);
	 * logger.debug("inqueryComTaskList() - end - return value={}", returnList);
	 * return returnList; }
	 */

	/**
	 * 
	 * 方法说明：客户管理-任务类型列表
	 * 
	 * @param
	 * @return
	 * 
	 * @author rain CreateDate: 2017年8月15日
	 *
	 */
	/*
	 * @RequestMapping(value = "inqueryComTaskListNew.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public List<FindComTaskChoosePageDto> inqueryComTaskListNew(
	 * FindInqueryComTaskListNewDto findInqueryComTaskListNewDto, HttpServletRequest
	 * httpServletRequest) {
	 * 
	 * FindComTaskChoosePageDto dto = new FindComTaskChoosePageDto();
	 * dto.setMerchantNo(findInqueryComTaskListNewDto.getMerchantNo());
	 * dto.setMemberNo(findInqueryComTaskListNewDto.getMemberNo());
	 * dto.setMemberNoGm(findInqueryComTaskListNewDto.getMemberNoGm());
	 * List<FindComTaskChoosePageDto> returnList =
	 * comTaskChooseService.findComTaskChoosesNewApp(dto); return returnList; }
	 */

	/**
	 * 
	 * 方法说明：我的客户-维护记录-新增
	 * 
	 * @param AddClientFollow含维护总表ckNo ,客户MemberNo,导购编码memberGMCode
	 * @return
	 *
	 * @author rain CreateDate: 2017年7月4日
	 *
	 */
	/*
	 * @RequestMapping(value = "addClientKeep.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public AddClientKeepReturn addClientKeep(AddClientKeep
	 * addClientKeep, HttpServletRequest httpServletRequest) {
	 * logger.debug("addClientKeep(AddClientKeepReturn addClientKeep) ) - start",
	 * addClientKeep);
	 * 
	 * AddClientKeepReturn ackr =
	 * clientKeepService.newClientKeepInfo(addClientKeep);
	 * 
	 * logger.debug("addClientKeep(AddClientKeepReturn addClientKeep) ) - end",
	 * ackr); return ackr; }
	 */

	/**
	 * 
	 *
	 * 方法说明：分页查询购买记录
	 *
	 * @param findBuyRecordPage
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月11日
	 *
	 */
	/*
	 * @RequestMapping(value = "findBuyRecordPage.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public Page<FindBuyRecordPageReturn>
	 * findBuyRecordPage(FindBuyRecordPage findBuyRecordPage) { logger.
	 * debug("findBuyRecordPage(FindBuyRecordPage findBuyRecordPage={}) - start",
	 * findBuyRecordPage);
	 * 
	 * // findBuyRecordPage.setLimit(ApiConstans.LIMIT);
	 * Page<FindBuyRecordPageReturn> returnPage =
	 * clientFollowSummaryService.findBuyRecordPage(findBuyRecordPage);
	 * logger.debug("findBuyRecordPage() - end - return value={}", returnPage);
	 * return returnPage; }
	 */

	/**
	 * 
	 *
	 * 方法说明：分页查询查找客户放弃表信息
	 *
	 * @param findPmAbandonPage
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月11日
	 *
	 */
	/*
	 * @RequestMapping(value = "findPmAbandonPage.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public Page<FindPmAbandonPageReturn>
	 * findPmAbandonPage(FindPmAbandonPage findPmAbandonPage) { logger.
	 * debug("findPmAbandonPage(FindPmAbandonPage findPmAbandonPage={}) - start",
	 * findPmAbandonPage);
	 * 
	 * // findPmAbandonPage.setLimit(ApiConstans.LIMIT);
	 * Page<FindPmAbandonPageReturn> returnPage =
	 * pmAbandonService.findPmAbandonPage(findPmAbandonPage);
	 * logger.debug("findPmAbandonPage() - end - return value={}", returnPage);
	 * return returnPage; }
	 */

	/**
	 * 
	 *
	 * 方法说明：查询放弃列表
	 *
	 * @param findPmAbandonList
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月17日
	 *
	 */
	/*
	 * @RequestMapping(value = "findPmAbandonList.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public List<FindPmAbandonListReturn>
	 * findPmAbandonList(FindPmAbandonList findPmAbandonList) { logger.
	 * debug("findPmAbandonList(FindPmAbandonList findPmAbandonList={}) - start",
	 * findPmAbandonList);
	 * 
	 * List<FindPmAbandonListReturn> returnList =
	 * pmAbandonService.findPmAbandonList(findPmAbandonList);
	 * logger.debug("findPmAbandonList() - end - return value={}", returnList);
	 * return returnList; }
	 */

	/**
	 * 
	 *
	 * 方法说明：查询最后时间
	 *
	 * @param findCForCKLastDateDto
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年8月5日
	 *
	 */
	/*
	 * @RequestMapping(value = "findCForCKLastDate.do", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public FindCForCKLastDateDtoReturn
	 * findCForCKLastDate(FindCForCKLastDateDto findCForCKLastDateDto) { logger.
	 * debug("findCForCKLastDate(FindCForCKLastDateDto findCForCKLastDateDto={}) - start"
	 * , findCForCKLastDateDto);
	 * 
	 * FindCForCKLastDateDtoReturn returnFindCForCKLastDateDtoReturn =
	 * clientFollowSummaryService .findCForCKLastDate(findCForCKLastDateDto);
	 * logger.debug("findCForCKLastDate() - end - return value={}",
	 * returnFindCForCKLastDateDtoReturn); return returnFindCForCKLastDateDtoReturn;
	 * }
	 */

	/**
	 * 
	 *
	 * 方法说明：积分_活动专题 发送素材
	 *
	 * @param guidMemberIntegralDto
	 *
	 * @author 彭阳 CreateDate: 2017年8月5日
	 *
	 */
	@RequestMapping(value = "doIntegral.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse doIntegral(GuidMemberIntegralDto guidMemberIntegralDto) {
		logger.debug("doIntegral(GuidMemberIntegralDto guidMemberIntegralDto={}) - start", guidMemberIntegralDto);

		guidMemberIntegralService.doIntegral(guidMemberIntegralDto);
		GeneralResponse returnGeneralResponse = GeneralResponse.generateSuccessResponse();
		logger.debug("doIntegral() - end - return value={}", returnGeneralResponse);
		return returnGeneralResponse;
	}

	/**
	 * 
	 *
	 * 方法说明：我的员工
	 *
	 * @param findShopGmDto
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年10月9日
	 *
	 */
	@RequestMapping(value = "findShopGm.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<GmAssistantShop> findShopGm(String memberNoGm, String noWxGm, String merchantNo) {
		AssertUtils.notAllNullAndEmpty(memberNoGm, "导购编号不能为空");
		AssertUtils.notAllNullAndEmpty(noWxGm, "终端微信不能为空");
		AssertUtils.notAllNullAndEmpty(merchantNo, "商户编号不能为空");
		FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
		findGmAssistantShop.setNoWx(noWxGm);
		findGmAssistantShop.setMerchantNo(merchantNo);
		List<GmAssistantShop> list = gmAssistantShopService.findGmAssistantListByWx(findGmAssistantShop);
		List<GmAssistantShop> returnList = new ArrayList<GmAssistantShop>();
		for (GmAssistantShop item : list) {
			if (!item.getMemberNo().equals(memberNoGm)) {
				returnList.add(item);
				FindImFriendsPage findImFriendsPage = new FindImFriendsPage();
				findImFriendsPage.setMerchantNo(merchantNo);
				findImFriendsPage.setNoWxGm(noWxGm);
				findImFriendsPage.setMemberNoGm(item.getMemberNo());
				findImFriendsPage.setVersion(0L);
				int count = personMemberImService.findImFriendsCount(findImFriendsPage);
				item.setCount(count);
			}
		}
		logger.debug("findShopGm() - end - return value={}", returnList);
		return returnList;
	}

	/**
	 * 
	 *
	 * 方法说明：app首页导购行为统计数据
	 *
	 * @param findGuidmemberActionInfoListDto
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年8月10日
	 *
	 */
	@RequestMapping(value = "findGuidmemberActionInfoList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<FindGuidmemberActionInfoReturn> findGuidmemberActionInfoList(
			FindGuidmemberActionInfoListDto findGuidmemberActionInfoListDto) {
		logger.debug(
				"findGuidmemberActionInfoList(FindGuidmemberActionInfoListDto findGuidmemberActionInfoListDto={}) - start",
				findGuidmemberActionInfoListDto);

		List<FindGuidmemberActionInfoReturn> list = guidmemberActionInfoService
				.findGuidmemberActionInfoList(findGuidmemberActionInfoListDto);
		return list;
	}

	/**
	 * 
	 *
	 * 方法说明：app首页导购行为统计数据
	 *
	 * @param findGuidmemberActionInfoListDto
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年8月10日
	 *
	 */
	@RequestMapping(value = "updateGuidQcord.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public UpdateGuidMemberReturn updateGuidQcord(UpdateMemberQcordDto updateMemberQcordDto) {
		logger.debug("updateGuidQcord(UpdateMemberQcordDto updateMemberQcordDto={}) - start", updateMemberQcordDto);

		AssertUtils.notNull(updateMemberQcordDto);
		AssertUtils.notNull(updateMemberQcordDto.getMemberNo());
		AssertUtils.notNull(updateMemberQcordDto.getQcord());
		// 根据会员号查询导购
		FindGuidMember findGuidMember = new FindGuidMember();
		findGuidMember.setMemberNo(updateMemberQcordDto.getMemberNo());
		FindGuidMemberReturn guidMember = guidMemberService.findGuidMember(findGuidMember);
		// 更新导购二维码
		if (null != guidMember) {
			UpdateGuidMember updateGuidMember = new UpdateGuidMember();
			updateGuidMember.setCode(guidMember.getCode());
			updateGuidMember.setQcord(updateMemberQcordDto.getQcord());
			return guidMemberService.updateGuidMember(updateGuidMember);
		}
		return new UpdateGuidMemberReturn();
	}

	/**
	 * 
	 *
	 * 方法说明：根据导购编号和商户号查询客户信息
	 *
	 * @param findMemberRecordDto
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月5日
	 *
	 */
	@RequestMapping(value = "findMemberRecord.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<FindMemberInfoReturn> findMemberRecord(FindMemberRecordDto findMemberRecordDto) {
		logger.debug("findMemberRecord(FindMemberRecordDto findMemberRecordDto={}) - start", findMemberRecordDto);

		AssertUtils.notNull(findMemberRecordDto);
		AssertUtils.notNull(findMemberRecordDto.getMerchantNo());
		AssertUtils.notNull(findMemberRecordDto.getMemberNoGm());

		FindMemberRecord findMemberRecord = new FindMemberRecord();
		findMemberRecord.setMerchantNo(findMemberRecordDto.getMerchantNo());
		findMemberRecord.setMemberNoGm(findMemberRecordDto.getMemberNoGm());

		return personMemberService.findMemberRecord(findMemberRecord);
	}

	/**
	 * 
	 *
	 * 方法说明：查找导购名片
	 *
	 * @param findGuidCard
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年11月3日
	 *
	 */
	@RequestMapping(value = "findGuidCard.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public FindGuidCardReturn findGuidCard(FindGuidCard findGuidCard) {
		logger.debug("findGuidCard(FindGuidCard findGuidCard={}) - start", findGuidCard);
		return guidCardService.findGuidCardByGm(findGuidCard);
	}

	/**
	 * 
	 *
	 * 方法说明：导购名片浏览，点赞，收藏
	 *
	 * @param findGuidCard
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年11月3日
	 *
	 */
	@RequestMapping(value = "guidCardAddNumType.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse guidCardAddNumType(GuidCardAddNumDto guidCardAddNumDto) {
		logger.debug("findGuidCard(GuidCardAddNumDto guidCardAddNumDto={}) - start", guidCardAddNumDto);
		boolean flag = guidCardService.addGuidCardNum(guidCardAddNumDto);
		return flag ? GeneralResponse.generateSuccessResponse() : GeneralResponse.generateFailureResponse();
	}

	/**
	 * 
	 *
	 * 方法说明：删除客户保存的导购名片
	 *
	 * @param guidCardAddNumDto
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年11月8日
	 *
	 */
	@RequestMapping(value = "delGuidCardSave.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse delGuidCardSave(GuidCardAddNumDto guidCardAddNumDto) {
		logger.debug("delGuidCardSave(GuidCardAddNumDto guidCardAddNumDto={}) - start", guidCardAddNumDto);
		guidCardService.delGuidCardSave(guidCardAddNumDto);
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 *
	 * 方法说明：查找导购名片
	 *
	 * @param findGuidCard
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年11月3日
	 *
	 */
	@RequestMapping(value = "findGuidCards.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<FindGuidCardReturn> findGuidCards(GuidCardAddNumDto guidCardAddNumDto) {
		logger.debug("findGuidCard(GuidCardAddNumDto guidCardAddNumDto={}) - start", guidCardAddNumDto);
		return guidCardService.findGuidCards(guidCardAddNumDto);
	}

	/**
	 * 
	 *
	 * 方法说明：获取openId签名
	 *
	 * @param jsCode
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年11月8日
	 *
	 */
	@RequestMapping(value = "getSignature.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getSignature(String jsCode) {
		logger.debug("getSignature(String jsCode={}) - start", jsCode);
		return guidCardService.getSignature(jsCode);
	}

	/**
	 * H5补充客户信息
	 * 
	 * @param memberNo
	 * @param memberNoGm
	 * @return
	 */
	/*
	 * @RequestMapping(value = "h5SupplyMemberInfo")
	 * 
	 * @ResponseBody public GeneralResponse h5SupplyMemberInfo(PersonMemberExtDto
	 * extDto) {
	 * logger.info("h5SupplyMemberInfo(PersonMemberExtDto extDto={})",extDto); try {
	 * AssertUtils.notNullAndEmpty(extDto.getMemberNo(), "客户编号不能为空!");
	 * AssertUtils.notNullAndEmpty(extDto.getNoWxGm(), "导购编号不能为空!"); int count =
	 * personMemberExtService.findCountByMemberNo(extDto.getMemberNo()); //不存在新增
	 * if(count == 0) { personMemberExtService.addPersonMemberExt(extDto); }else {
	 * personMemberExtService.updatePersonMemberExt(extDto); }
	 * 
	 *//**
		 * 保存手机号到客户基础表
		 *//*
			 * if(StringUtils.isNotEmpty(extDto.getMobile()) ||
			 * StringUtils.isNotEmpty(extDto.getRealName())) { UpdatePersonMemberBase
			 * updatePersonMemberBase = new UpdatePersonMemberBase();
			 * updatePersonMemberBase.setMemberNo(extDto.getMemberNo());
			 * updatePersonMemberBase.setMemberName(extDto.getRealName());
			 * updatePersonMemberBase.setMobile(extDto.getMobile());
			 * personMemberBaseService.updatePersonMemberMobile(updatePersonMemberBase); }
			 * 
			 * 
			 * FindPersonMember findPersonMember = new FindPersonMember();
			 * findPersonMember.setMemberNo(extDto.getMemberNo());
			 * findPersonMember.setMemberNoGm(extDto.getMemberNoGm());
			 * FindPersonMemberReturn personMemberReturn
			 * =personMemberService.findPersonMemberByMGM(findPersonMember);
			 * if(StringUtils.isNotEmpty(extDto.getMemberNoGm())) { //成功推送文字内容 String
			 * groupName = "push_switch";//组名 FindMerchantParams findMerchantParams = new
			 * FindMerchantParams(); findMerchantParams.setGroupName(groupName);
			 * findMerchantParams.setMerchantNo(personMemberReturn.getMerchantNo());
			 * Map<String, String> paramsMap =
			 * merchantParamsService.findMerchantParamsByGroup(findMerchantParams); if
			 * (MapUtils.isNotEmpty(paramsMap) &&
			 * StringUtils.isNotEmpty(paramsMap.get("3.thank"))) {
			 * sendText(extDto.getNoWxGm(), extDto.getMemberNo(), paramsMap.get("3.thank"));
			 * } }
			 * 
			 * return GeneralResponse.generateSuccessResponse(); } catch (Exception e) {
			 * logger.error("H5补充客户信息错误 e={}",e); return
			 * GeneralResponse.generateFailureResponse(e); }
			 * 
			 * }
			 */

	/**
	 * 发送文本消息
	 * 
	 * @param noWxGm
	 * @param memberNo
	 * @param content
	 */
	private void sendText(final String noWxGm, final String memberNo, String content) {
		try {
			// 发送消息
			SendImChatInfo sendImChatInfo = new SendImChatInfo();
			sendImChatInfo.setSenderFlag(1);
			sendImChatInfo.setNoWxGm(noWxGm);
			sendImChatInfo.setMemberNo(memberNo);
			sendImChatInfo.setType(ChatInfoType.TEXT.getCode());
			sendImChatInfo.setContent(content);
			sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
			sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
			logger.info("发送H5链接：{}", sendImChatInfo);
			imChatInfoService.sendChat(sendImChatInfo);

		} catch (Exception e) {
			logger.error("发送H5链接失败", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：批量更改客户所属分组
	 *
	 * @param changePmTypeHcList
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年12月7日
	 *
	 */
	@RequestMapping(value = "changePmTypeAppBatch.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse changePmTypeAppBatch(String codePms, ChangePmType changePmType) {
		logger.debug("changePmTypeAppBatch(String codePms={},ChangePmType changePmType={}) - start", codePms,
				changePmType);
		if (StringUtils.isEmpty(changePmType.getPmTypeCode()) || StringUtils.isEmpty(codePms)
				|| StringUtils.isEmpty(changePmType.getPmTypeName())) {
			return GeneralResponse.generateFailureResponse(com.lj.business.api.common.ErrorCode.PARAM_ERROR, "请求参数错误！");
		}

		String[] codePmArr = codePms.split(",");

		if (null != codePmArr && codePmArr.length > 0) {
			try {
				personMemberService.changePmTypeBatch(codePmArr, changePmType.getPmTypeCode(),
						changePmType.getPmTypeName());
			} catch (Exception e) {
				logger.error("批量更改客户所属分组错误 e={}", e);
				return GeneralResponse.generateFailureResponse();
			}
		}
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * H5补充客户信息根据openId
	 * 
	 * @param memberNo
	 * @param memberNoGm
	 * @return
	 */
	@RequestMapping(value = "h5SupplyMemberInfoByOpenId")
	@ResponseBody
	public GeneralResponse h5SupplyMemberInfoByOpenId(PersonMemberExtDto extDto) {
		logger.info("h5SupplyMemberInfo(PersonMemberExtDto extDto={})", extDto);
		try {
			if (StringUtils.isEmpty(extDto.getOpenId())) {
				return GeneralResponse.generateResponse(false, "0", "openId不能为空!", null);
			}
			MecMemberNoReturn mecMemberNoReturn = personMemberExtService.findPmbByOpenId(extDto.getOpenId());
			// 不存在新增
			if (StringUtils.isNotEmpty(extDto.getMobile())) {
				extDto.setRemark(extDto.getMobile());
			}
			if (null == mecMemberNoReturn) {
				personMemberExtService.addPersonMemberExt(extDto);
			} else {
				extDto.setCode(mecMemberNoReturn.getCode());
				personMemberExtService.updatePersonMemberExt(extDto);
			}
			return GeneralResponse.generateSuccessResponse();
		} catch (Exception e) {
			logger.error("H5补充客户信息错误 e={}", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "changePmLabel.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse changePmLabel(ChangePmLabel changePmLabel, String labelsJson) {
		if (StringUtils.isNotEmpty(labelsJson)) {
			// 将json数据转为对象
			JSONArray labels = JSONArray.fromObject(labelsJson);
			List<PmLabelDto> list = JSONArray.toList(labels, new PmLabelDto(), new JsonConfig());
			changePmLabel.setLabels(list);
		}
		pmLabelService.changePmLabel(changePmLabel);
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 删除客户标签
	 * 
	 * @param memberNos
	 * @param pmLabelCode
	 * @return
	 */
	@RequestMapping(value = "deletePmLabelByMemberNo.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse deletePmLabelByMemberNo(String memberNos, String pmLabelCode, String shopWx) {
		logger.debug("addPmLabelAndMembers() - start");
		AssertUtils.notNullAndEmpty(memberNos, "会员编号不能为空");
		AssertUtils.notNullAndEmpty(pmLabelCode, "标签编号不能为空");

		String[] memberNoFor = memberNos.split(",");
		int count = 0;
		for (String string : memberNoFor) {
			count += pmLabelService.deletePmLabelByMemberNo(string, pmLabelCode, shopWx);
		}
		logger.debug("addPmLabelAndMembers() - end - return value={}", count);
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 *
	 * 方法说明：根据商户号查询标签列表
	 *
	 * @param merchantNo
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2017年12月8日
	 *
	 */
	@RequestMapping(value = "findPmLabelListByMerchantNo.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse findPmLabelListByMerchantNo(String merchantNo, Integer type, String memberNoGm) {
		logger.debug("findPmLabelListByMerchantNo(String merchantNo={}) - start", merchantNo);

		if (null == type || type == 0) {
			List<PmLabelDto> returnList = pmLabelService.findPmLabelByMerchantNo(merchantNo);
			logger.debug("findPmLabelListByMerchantNo(String) - end - return value={}", returnList);
			return GeneralResponse.generateSuccessResponse(returnList);
		} else {
			GmLabelDto gmLabelDto = new GmLabelDto();
			gmLabelDto.setMerchantNo(merchantNo);
			gmLabelDto.setMemberNoGm(memberNoGm);
			List<GmLabelDto> returnList = gmLabelService.findGmLabels(new FindGmLabelPage(gmLabelDto));
			// 应IOS要求，这里单独处理，冯老师他说对象对比效率高
			List<GmLabelDto> list = new ArrayList<GmLabelDto>();
			for (GmLabelDto gmLabelDto2 : returnList) {
				GmLabelDto dto = new GmLabelDto();
				dto.setCode(gmLabelDto2.getCode());
				dto.setLabelName(gmLabelDto2.getLabelName());
				dto.setType(gmLabelDto2.getType());
				list.add(dto);
			}
			logger.debug("findPmLabelListByMerchantNo(String) - end - return value={}", list);
			return GeneralResponse.generateSuccessResponse(list);
		}
	}

	/**
	 * 根据商户号And终端微信获取标签列表（包含统计数和客户基本信息）
	 * 
	 * @param merchantNo
	 * @param shopWx
	 * @return
	 */
	@RequestMapping(value = "findPmLabelAndMembers.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String, String>> findPmLabelAndMembers(String merchantNo, String noWxGm, String memberNoGm,
			Integer type) {
		Map<String, String> parmap = new HashMap<String, String>();
		parmap.put("merchantNo", merchantNo);
		parmap.put("shopWx", noWxGm);
		parmap.put("memberNoGm", memberNoGm);
		parmap.put("type", String.valueOf(null == type ? 0 : type));
		logger.debug("findPmLabelListByMerchantNo(String parmap={}) - start", parmap);
		List<Map<String, String>> returnList = pmLabelService.findPmLabelByMerchantNoAndShopWx(parmap);
		logger.debug("findPmLabelListByMerchantNo(String) - end - return value={}", returnList);
		return returnList;
	}

	/**
	 * 给客户打标签
	 * 
	 * @param merchantNo
	 * @param shopWx
	 * @return
	 */
	@RequestMapping(value = "addPmLabelByMemberNo.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse addPmLabelAndMembers(String memberNos, String pmLabelCode, String pmLabelName, String shopWx,
			Integer type) {
		AssertUtils.notNullAndEmpty(memberNos, "会员编号不能为空");
		AssertUtils.notNullAndEmpty(pmLabelCode, "标签编号不能为空");
		AssertUtils.notNullAndEmpty(pmLabelName, "标签名称不能为空");
		AssertUtils.notNullAndEmpty(shopWx, "终端微信不能为空");
		String[] memberNoFor = memberNos.split(",");
		int count = 0;
		type = type == null ? 0 : type;
		for (String string : memberNoFor) {
			count += pmLabelService.addPmLabelByMemberNo(string, pmLabelCode, pmLabelName, shopWx, type);
		}
		return GeneralResponse.generateSuccessResponse(count);
	}

	@RequestMapping(value = "tmallBonusH5.do")
	@ResponseBody
	public GeneralResponse tmallBonusH5(TmallBonusH5Dto tmallBonusH5Dto, HttpServletRequest request) {
		logger.info("h5SupplyMemberInfo(TmallBonusH5Dto tmallBonusH5Dto={})", tmallBonusH5Dto);
		AssertUtils.notNull(tmallBonusH5Dto);
		AssertUtils.notNullAndEmpty(tmallBonusH5Dto.getNoWxGm(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(tmallBonusH5Dto.getOpenId(), "openId不能为空");
		AssertUtils.notNullAndEmpty(tmallBonusH5Dto.getNoWx(), "客户微信不能为空");
		AssertUtils.notNullAndEmpty(tmallBonusH5Dto.getOrderNo(), "订单号不能为空");
		try {
			String amount = tmallOrderService.validateOrder(tmallBonusH5Dto.getOrderNo(), tmallBonusH5Dto.getOpenId());
			if (StringUtils.isNotEmpty(amount) && Integer.valueOf(amount) > 0) {
			} else {
				logger.info("验证不成功，订单不满足活动要求");
				GeneralResponse.generateFailureResponse("", "验证不成功，订单不满足活动要求！");
			}

			UpdatePersonMemberBase updatePersonMemberBase = new UpdatePersonMemberBase();
			boolean isUpdate = false;
			FindPersonMemberBaseReturn baseReturn = personMemberBaseService
					.findMemberBaseByNoWxOrAlias(tmallBonusH5Dto.getNoWx(), "");
			if (null == baseReturn) {
				GeneralResponse.generateFailureResponse("", "客户不存在!");
			}

			// 回写相关信息
			// 获取订单记录
			FindTmallOrderPage findTmallOrderPage = new FindTmallOrderPage();
			TmallOrderDto paramOrderDto = new TmallOrderDto();
			paramOrderDto.setOrderNo(tmallBonusH5Dto.getOrderNo());
			findTmallOrderPage.setParam(paramOrderDto);
			TmallOrderDto tmallOrderDto = tmallOrderService.get(findTmallOrderPage);
			if (StringUtils.isEmpty(baseReturn.getOpenIdGzhWx())
					|| !baseReturn.getOpenIdGzhWx().equals(tmallBonusH5Dto.getOpenId())) {
				updatePersonMemberBase.setOpenIdGzhWx(tmallBonusH5Dto.getOpenId());
				isUpdate = true;
			}

			if (StringUtils.isEmpty(baseReturn.getNoWw()) || !baseReturn.getNoWw().equals(tmallOrderDto.getNoWw())) {
				updatePersonMemberBase.setNoWw(tmallOrderDto.getNoWw());
				isUpdate = true;
			}

			if (StringUtils.isEmpty(baseReturn.getMobile())
					|| !baseReturn.getMobile().equals(tmallOrderDto.getMobile())) {
				updatePersonMemberBase.setMobile(tmallOrderDto.getMobile());
				isUpdate = true;
			}

			if (StringUtils.isEmpty(baseReturn.getOrderNo())
					|| !baseReturn.getOrderNo().equals(tmallBonusH5Dto.getOrderNo())) {
				updatePersonMemberBase.setOrderNo(tmallBonusH5Dto.getOrderNo());
				isUpdate = true;
			}

			if (isUpdate) {
				updatePersonMemberBase.setCode(baseReturn.getCode());
				logger.info("天猫订单H5链接补充资料 :{}", updatePersonMemberBase);
				personMemberBaseService.updatePersonMemberBase(updatePersonMemberBase);
			}

			// 推送公众号
			FindShopTerminalReturn findShopTerminalReturn = shopTerminalService
					.findShopTerminalByWx(tmallBonusH5Dto.getNoWxGm());
			if (findShopTerminalReturn == null) {
				GeneralResponse.generateFailureResponse("", "终端不存在!");
			}
			String paUserName = localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(),
					GroupName.mec_weixin.toString(), "weixin.mp.userName");
			String content = "验证成功，请关注公众号领取红包！如已关注，请取消后再次关注！";
			sendText(tmallBonusH5Dto.getNoWxGm(), baseReturn.getMemberNo(), content);
			sendPublicAccountByPushConfig(tmallBonusH5Dto.getNoWxGm(), baseReturn.getMemberNo(),
					findShopTerminalReturn.getMerchantNo(), paUserName);
			return GeneralResponse.generateSuccessResponse();
		} catch (Exception e) {
			logger.error("H5补充客户信息错误 e={}", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}

	/**
	 * 推送发红包的公众号消息
	 * 
	 * @param noWxGm
	 * @param memberNo
	 * @param merchantNo
	 * @param paUsername 公众号用户名
	 */
	private void sendPublicAccountByPushConfig(String noWxGm, String memberNo, String merchantNo, String paUsername) {
		try {
			// 获取商户下固定公众号paUsername为
			FindWxPublicAccount findWxPublicAccount = new FindWxPublicAccount();
			findWxPublicAccount.setPaUsername(paUsername);
			findWxPublicAccount.setMerchantNo(merchantNo);
			findWxPublicAccount.setNoWxZk(noWxGm);
			logger.info("获取商户下固定公众号:{}", findWxPublicAccount);
			FindWxPublicAccountReturn findWxPublicAccountReturn = wxPublicAccountService
					.findByUsernameAndNoWxZk(findWxPublicAccount);
			if (findWxPublicAccountReturn != null && CommonConstant.I_YES == findWxPublicAccountReturn.getStatus()) {
				SendImChatInfo sendImChatInfo = new SendImChatInfo();
				sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
				sendImChatInfo.setNoWxGm(noWxGm);
				sendImChatInfo.setMemberNo(memberNo);
				sendImChatInfo.setType(ChatInfoType.CARD.getCode());
				sendImChatInfo.setResources(findWxPublicAccountReturn.getPaLogo());
				sendImChatInfo.setShareTitle(findWxPublicAccountReturn.getPaName());
				sendImChatInfo.setShareDes(findWxPublicAccountReturn.getPaDesc());
				Map<String, String> contentMap = new HashMap<>();
				contentMap.put("certflag", findWxPublicAccountReturn.getPaCertflag());
				contentMap.put("username", findWxPublicAccountReturn.getPaUsername());
				contentMap.put("alias", findWxPublicAccountReturn.getPaAlias());
				contentMap.put(com.lj.business.supcon.common.Constants.WX_PARAM_KEY,
						findWxPublicAccountReturn.getWxParam());
				sendImChatInfo.setContent(JsonUtils.jsonFromObject(contentMap));
				sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
				sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
				logger.info("推送公众号名片：{}", sendImChatInfo);
				imChatInfoService.sendChat(sendImChatInfo);
			}
		} catch (Exception e) {
			logger.error("客户认领后，自定义推送公众号名片失败：", e);
		}

	}

	/**
	 * 修改客户信息
	 * 
	 * @param noWxGm
	 * @param paramJson
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "updateMember.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse updateMember(EditPersonMember editPersonMember) {
		AssertUtils.notAllNullAndEmpty(editPersonMember.getMemberNo(), "客户编号不能为空");
		AssertUtils.notAllNullAndEmpty(editPersonMember.getNoWxGm(), "终端微信不能为空");
		AssertUtils.notAllNullAndEmpty(editPersonMember.getMemberNoGm(), "导购编号不能为空");

		FindPersonMember findPersonMember = new FindPersonMember();
		findPersonMember.setMemberNo(editPersonMember.getMemberNo());
		findPersonMember.setShopWx(editPersonMember.getNoWxGm());
		findPersonMember.setMemberNoGm(editPersonMember.getMemberNoGm());
		FindPersonMemberReturn findPersonMemberReturn = personMemberService
				.findPersonMemberByMemberNoAndGM(findPersonMember);
		AssertUtils.notNull(findPersonMemberReturn, "客户信息不存在");

		if (StringUtils.isNotEmpty(editPersonMember.getRemark())
				&& !editPersonMember.getRemark().equals(findPersonMemberReturn.getRemark())) {
			UpdatePersonMember updatePersonMember = new UpdatePersonMember();
			updatePersonMember.setCode(findPersonMemberReturn.getCode());
			updatePersonMember.setRemark(editPersonMember.getRemark());
			personMemberService.updatePersonMember(updatePersonMember);
		}

		FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
		findPersonMemberBase.setMemberNo(findPersonMemberReturn.getMemberNo());
		FindPersonMemberBaseReturn findPersonMemberBaseReturn = personMemberBaseService
				.findPersonMemberBase(findPersonMemberBase);
		AssertUtils.notNull(findPersonMemberBaseReturn, "客户基础信息不存在");

		// 首次添加手机号码
		PersonMemberBase personMemberBase = personMemberBaseService.checkMobile(editPersonMember);
		if (null == personMemberBase || (StringUtils.isEmpty(personMemberBase.getMobile())
				&& null == personMemberBase.getAddMobileDate())) {
			UpdatePersonMemberBase updatePersonMemberBase = new UpdatePersonMemberBase();
			updatePersonMemberBase.setMemberNo(findPersonMemberBaseReturn.getMemberNo());
			updatePersonMemberBase.setMobile(editPersonMember.getMobile());
			updatePersonMemberBase.setAddMobileDate(new Date());
			personMemberBaseService.updatePersonMemberMobile(updatePersonMemberBase);
		}

		if (StringUtils.isNotEmpty(editPersonMember.getMobile())
				&& !editPersonMember.getMobile().equals(findPersonMemberBaseReturn.getMobile())) {
			UpdatePersonMemberBase updatePersonMemberBase = new UpdatePersonMemberBase();
			updatePersonMemberBase.setMemberNo(findPersonMemberBaseReturn.getMemberNo());
			updatePersonMemberBase.setMobile(editPersonMember.getMobile());
			personMemberBaseService.updatePersonMemberMobile(updatePersonMemberBase);
		}

		return GeneralResponse.generateSuccessResponse();
	}
}
