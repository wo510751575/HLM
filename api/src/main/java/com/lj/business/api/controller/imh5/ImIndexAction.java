/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller.imh5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.base.core.pagination.IPage;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.emus.ChatUtilTypeEnum;
import com.lj.business.cm.dto.FindMaterial;
import com.lj.business.cm.dto.FindMaterialCommenPage;
import com.lj.business.cm.dto.MaterialCommenGroup;
import com.lj.business.cm.dto.MaterialGroup;
import com.lj.business.cm.dto.activity.FindActivityPage;
import com.lj.business.cm.dto.activity.FindActivityPageReturn;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPage;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPageReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeApiReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsAppReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoApp;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoAppReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPage;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoWeb;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoWebReturn;
import com.lj.business.cm.dto.wordsType.FindWordsTypePage;
import com.lj.business.cm.dto.wordsType.FindWordsTypeSelectReturn;
import com.lj.business.cm.service.IActivityService;
import com.lj.business.cm.service.ICommonWordsInfoService;
import com.lj.business.cm.service.ICommonWordsTypeService;
import com.lj.business.cm.service.IFriendsLinkMaterialService;
import com.lj.business.cm.service.IMaterialCommenService;
import com.lj.business.cm.service.IMaterialService;
import com.lj.business.cm.service.IVrMaterialCommenService;
import com.lj.business.cm.service.IVrMaterialTypeService;
import com.lj.business.cm.service.IWordsInfoService;
import com.lj.business.cm.service.IWordsTypeService;
import com.lj.business.common.CommonConstant;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.cp.dto.coupon.AddCoupon;
import com.lj.business.cp.dto.couponMemberRelation.AddCouponMemberRelation;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.emus.CouponStatus;
import com.lj.business.cp.emus.RealName;
import com.lj.business.cp.emus.RuleStatus;
import com.lj.business.cp.emus.ToCoupon;
import com.lj.business.cp.service.ICouponMemberRelationService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.domain.GuidMember;
import com.lj.business.member.dto.EditPersonMember;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindImIndexPage;
import com.lj.business.member.dto.FindImIndexPageReturn;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.FindPersonMemberPage;
import com.lj.business.member.dto.FindPersonMemberPageReturn;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.FindPmLabel;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.dto.PersonMemberLoginReturn;
import com.lj.business.member.dto.PmLabelDto;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomMember;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPage;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPageReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.guidCard.FindGuidCard;
import com.lj.business.member.dto.guidCard.FindGuidCardReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.emus.Gender;
import com.lj.business.member.emus.PmTypeTimeFlag;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidCardService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmLabelService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.token.MemberLoginCache;
import com.lj.business.supcon.dto.token.Token;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.ITokenService;
import com.lj.business.weixin.dto.FindImFriendsInfoPage;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.ToFriendsCommentDto;
import com.lj.business.weixin.dto.ToFriendsInfosDto;
import com.lj.business.weixin.dto.ToFriendsLikeDto;
import com.lj.business.weixin.dto.WxRedpackDetailInfoDto;
import com.lj.business.weixin.dto.imchat.AddImChatInfo;
import com.lj.business.weixin.dto.imchat.FindChatInfoPageFromWeb;
import com.lj.business.weixin.dto.imchat.FindChatInfoPageFromWebReturn;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByMember;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByMemberReturn;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByTerminal;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.dto.imchat.UpdateThirdHaveReadFromWeb;
import com.lj.business.weixin.dto.imemoji.FindImEmoji;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiReturn;
import com.lj.business.weixin.dto.imemoji.NewEmojiPackageReturn;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountReturn;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramReturn;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.ChatRoomType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.MessageStatus;
import com.lj.business.weixin.emus.ReadFlag;
import com.lj.business.weixin.emus.RedPackTypeEnum;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IImCommentInfoService;
import com.lj.business.weixin.service.IImEmojiPackageService;
import com.lj.business.weixin.service.IImEmojiService;
import com.lj.business.weixin.service.IImFriendsFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.IImLikeInfoService;
import com.lj.business.weixin.service.IWxPublicAccountService;
import com.lj.business.weixin.service.IWxRedpackDetailInfoService;
import com.lj.business.weixin.service.IWxSmallProgramService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.RedisCache;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.service.AreaHessianService;
import com.lj.oms.utils.FileUtil;
import com.lj.oms.utils.WxPwdEncryptUtils;
import com.lj.oms.utils.audioUtil.AmrToMp3Util;

/**
 * 
 * 类说明：IM首页跳转Action
 * <p>
 * 详细描述：第一个功能菜单默认进入
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2018年1月22日19:54:27
 */
@Controller
@RequestMapping(value="/imh5/index/")
public class ImIndexAction extends Action {
	private static Logger logger = LoggerFactory.getLogger(ImIndexAction.class);
	public static final String USER_MERCHANT_DEFAULT_PRODUCT_TYPE = "INVITE";//商户默认产品类型(邀约型)
	
	@Autowired 
	private RedisCache redisCache; //缓存
	@Resource
	private IGmAssistantShopService gmAssistantShopService;
	@Resource
	private IShopTerminalService shopTerminalService;
	@Resource
	private IGuidCardService guidCardService;
	@Resource
	private IPersonMemberService personMemberService; // 客户信息服务
	@Resource
	private ICouponMemberRelationService couponMemberRelationService;// 优惠券用户关联service
	@Resource
	private IGuidMemberService guidMemberService; // 导购服务
	@Resource
	private IPmTypeService pmTypeService; // 客户分类服务
	@Resource
	private IPmLabelService pmLabelService; // 客户标签服务
	@Resource
	private IPersonMemberBaseService personMemberBaseService; // 客户基础信息服务
	@Resource
	private  IImEmojiPackageService imEmojiPackageService;
	@Resource
	private AreaHessianService areaHessianService; // 地区服务
	@Resource
	private IMaterialCommenService materialcommenService;
	@Resource
	private IMaterialService materialService;
	@Resource
	private ICouponService couponService;
	@Resource
	private IImChatInfoService imChatInfoService;
	@Autowired
	private IActivityService activityService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
	private ICouponRuleService couponRuleService;
	@Resource
	private IMerchantService merchantService;
	@Resource
	private IPersonMemberImService personMemberImService;
	@Resource
	private IImEmojiService imEmojiService;
	@Resource
	private IImFriendsFacade friendsFacade;
	@Resource
	private IVrMaterialCommenService vrMaterialCommenService;
	@Resource
	private IVrMaterialTypeService vrMaterialTypeService;
	@Resource
	private IImFriendsInfoService imFriendsInfoService;
	@Resource
	private IImCommentInfoService imCommentInfoService;
	@Resource
	private IImLikeInfoService imLikeInfoService;
	@Resource
	private IFriendsLinkMaterialService friendsLinkMaterialService;
	@Resource
	private IWordsInfoService wordsInfoService;
	@Resource
	private IWordsTypeService wordsTypeService;
	@Autowired
	private IChatRoomMemberService chatRoomMemberService;
	@Autowired
	private ITokenService tokenService;
	@Autowired
	private IChatRoomService chatRoomService;
	@Resource
	private ICommonWordsTypeService commonWordsTypeService;			//公司话术类型服务
	@Resource
	private ICommonWordsInfoService commonWordsInfoService;			//公司话术服务
	@Autowired
	private IWxPublicAccountService wxPublicAccountService;
	@Autowired
	private IWxSmallProgramService wxSmallProgramService;
	@Autowired
	private IWxRedpackDetailInfoService wxRedpackDetailInfoService;
	@Autowired
	private ICommonService commonService;
	
	/**
	 * 使用token登录
	 * @param token
	 * @return
	 * @throws JsonProcessingException
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "tokenLogin.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<Object, Object> tokenLogin(String token) throws JsonProcessingException, UnsupportedEncodingException {
		AssertUtils.notNullAndEmpty(token, "token不能为空");
		token =URLDecoder.decode(token, "UTF-8");
		MemberLoginCache memberLoginCache = tokenService.parseMember(token, Token.TOKEN_TIMEOUT_SECONDS);
		AssertUtils.notNull(memberLoginCache, "token无效");
		
		String memberNoGm = memberLoginCache.getCode();
		GuidMember gmQuery = new GuidMember();
		gmQuery.setMemberNo(memberNoGm);
		GuidMember guidMember = guidMemberService.findSingleGuidMember(gmQuery);
		PersonMemberLoginReturn personMemberLoginReturn = buildPersonMemberLoginReturn(guidMember);
		personMemberLoginReturn.setToken(token);
		Map<Object, Object> data = new LinkedHashMap<>();
		
		data.putAll(new BeanMap(personMemberLoginReturn));
		
		//如果有下级，则是老板帐号，adminUserId则不为空
		FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
		findGmAssistantShop.setMerchantNo(guidMember.getMerchantNo());
		findGmAssistantShop.setAssistantNo(guidMember.getMemberNo());
		findGmAssistantShop.setSource(false);
		List<FindGmAssistantShopReturn> list= gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
		if(list !=null && list.size()>0) {
			data.put("adminUserId", true);
		}
//		User user =getUserByCache(memberNoGm);
		// 返回管理员用户信息
//		data.put("adminUserId", user.getId());
//		data.put("adminUserName", user.getName());
//		data.put("adminUserPhotoUrl", user.getPhoto());
		
		logger.info("tokenLogin data:{}", data);
		
		return data;
	}
	
	/**
	 * 组装返回数据
	 * @param guidMember
	 * @return
	 */
	private PersonMemberLoginReturn buildPersonMemberLoginReturn(GuidMember guidMember) {
		PersonMemberLoginReturn personMemberLoginReturn;
		personMemberLoginReturn = new PersonMemberLoginReturn();
		personMemberLoginReturn.setCode(guidMember.getCode());

		if(guidMember!=null){
			personMemberLoginReturn.setMemberNoGuid(guidMember.getMemberNo());
			personMemberLoginReturn.setMemberNameGuid(guidMember.getMemberName());
			personMemberLoginReturn.setMobile(guidMember.getMobile());
			personMemberLoginReturn.setEmail(guidMember.getEmail());
			personMemberLoginReturn.setProvinceCode(guidMember.getProvinceCode());
			personMemberLoginReturn.setCityCode(guidMember.getCityCode());
			personMemberLoginReturn.setAreaCode(guidMember.getAreaCode());
			personMemberLoginReturn.setHeadAddress(guidMember.getHeadAddress());
			personMemberLoginReturn.setGender(guidMember.getGender());
			personMemberLoginReturn.setMemberNoMerchant(guidMember.getMerchantNo());
			personMemberLoginReturn.setMemberNameMerchant(guidMember.getMerchantName());
//			personMemberLoginReturn.setMemberNoShop(guidMember.getMemberNo());
//			personMemberLoginReturn.setMemberNameShop(guidMember.getMemberName());
			personMemberLoginReturn.setWorkDate(guidMember.getWorkDate());
			personMemberLoginReturn.setStatus(guidMember.getStatus());
//			personMemberLoginReturn.setShopNo(guidMember.getShopNo());
//			personMemberLoginReturn.setShopName(guidMember.getShopName());
			personMemberLoginReturn.setNoWx(guidMember.getNoWx());
			
			// 获取导购端需要连接的中控服务地址
			personMemberLoginReturn.setNettyAddress(getNettyUrlByZK(guidMember.getNoWx(), null));
		}
		
		String uploadUrl =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(), SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
		personMemberLoginReturn.setUploadUrl(uploadUrl);
		
		return personMemberLoginReturn;
	}
	
	/**
	 * netty 根据中控登录的地址获取导购登录的地址（集群环境下中控和导购必须登录同一台机子）
	 * 
	 */
	public String getNettyUrlByZK(String gmNoWx, String version) {
		 //记录中控登录地址
		return	redisCache.get(CommonConstant.REDISNETTYKEY + gmNoWx);
	}
	
	/**
	 *
	 * 方法说明：查询门店客户列表首页信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @author 李端强 CreateDate: 2017年11月28日
	 */
	@RequestMapping(value = {"list.do"})
	@ResponseBody
	public Map<String, Object> list(Integer pageNo,Integer pageSize,FindShopTidFromWeb findShopTidFromWeb,String merchantNo,String memberNoGm){
		Map<String, Object> retMap = Maps.newHashMap();
		try {
			//客户分组查询
			FindPmTypePageReturn findPmTypePageReturn=new FindPmTypePageReturn();
			findPmTypePageReturn.setStatus("Y");
			findPmTypePageReturn.setMerchantNo(merchantNo);
			List<FindPmTypePageReturn> pmType=pmTypeService.findPmTypePages(findPmTypePageReturn);
			//新增 今日新增/7天内新增/30天内新增 客户类型
			addTimePmType(pmType);
			retMap.put("pmType", pmType);
			//门店信息查询
			FindGmAssistantShop findGmAssistantShop=new FindGmAssistantShop();
			findGmAssistantShop.setAssistantNo(memberNoGm);
			findGmAssistantShop.setFilterNoTerminal(Boolean.TRUE);	// 过滤掉没有添加终端的门店
			List<FindGmAssistantShopReturn> findGmAssistantShopList = gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
			retMap.put("shops", findGmAssistantShopList);
			FindGuidMemberPage findGuidMemberPage=new FindGuidMemberPage();
			findGuidMemberPage.setLimit(500);
			findGuidMemberPage.setMerchantNo(merchantNo);
//			findGuidMemberPage.setShopNos(shopNos);
			Page<FindGuidMemberPageReturn> pageDto = guidMemberService.findGuidMemberPage(findGuidMemberPage);
			List<FindGuidMemberPageReturn> guidMembers = Lists.newArrayList();
			guidMembers.addAll(pageDto.getRows());
			retMap.put("guidMembers", findGmAssistantShopList);
			//导购助手管理的门店列表查询
			findShopTidFromWeb.setMerchantNo(merchantNo);
			findShopTidFromWeb.setAssistantNo(memberNoGm);
			List<FindShopTidFromWebReturn> shopTids = shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
			retMap.put("shopTids", shopTids);
			//查询的参数
			retMap.put("ShopTidFromWeb", findShopTidFromWeb);
		} catch (Exception e) {
			logger.error("查询首页信息错误：" + e);
		}
		return retMap;
	}
	
	
	/**
	 * 新增 今日新增/7天内新增/30天内新增 客户类型
	 * @author 彭俊霖
	 * @param pmType
	 */
	public void addTimePmType(List<FindPmTypePageReturn> pmType) {
		FindPmTypePageReturn todayFlag=new FindPmTypePageReturn();
		todayFlag.setCode(PmTypeTimeFlag.TODAY.toString());
		todayFlag.setTypeName(PmTypeTimeFlag.TODAY.getName());
		FindPmTypePageReturn weekFlag=new FindPmTypePageReturn();
		weekFlag.setCode(PmTypeTimeFlag.WEEK.toString());
		weekFlag.setTypeName(PmTypeTimeFlag.WEEK.getName());
		FindPmTypePageReturn monthFlag=new FindPmTypePageReturn();
		monthFlag.setCode(PmTypeTimeFlag.MONTH.toString());
		monthFlag.setTypeName(PmTypeTimeFlag.MONTH.getName());
		pmType.add(0,todayFlag);
		pmType.add(1,weekFlag);
		pmType.add(2,monthFlag);
	}
	
	/**
	 * 
	 * 方法说明：客户聊天/接入客户
	 * @return
	 * @author 李端强 CreateDate: 2017年11月29日
	 *
	 */
	@RequestMapping(value = {"chat.do"})
	@ResponseBody
	public Map<String, Object> chat(String memberNoGm,String memberNo,String merchantNo){
		Map<String, Object> retMap = Maps.newHashMap();
		try {
			String merchatNo = null;
			//导购个人信息findGuidMember
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(memberNoGm);
			FindGuidMemberReturn findGuidMemberReturn =	guidMemberService.findGuidMember(findGuidMember);
			retMap.put("findGuidMemberReturn", findGuidMemberReturn);
			//个人资料
			FindPersonMemberPage findPersonMemberPage = new FindPersonMemberPage(); 
			findPersonMemberPage.setMemberNo(memberNo);
			findPersonMemberPage.setMemberNoGm(memberNoGm);
			findPersonMemberPage.setMerchantNo(merchantNo);


			Page<FindPersonMemberPageReturn> pageDto = personMemberService.queryPersonMemberPage(findPersonMemberPage);
			List<FindPersonMemberPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			
			FindPersonMemberPageReturn memberPageReturn=list.get(0);
//			FindShop findShop = new FindShop();
//			findShop.setShopNo(memberPageReturn.getShopNo());
//			List<FindShopPageReturn> shopType= shopService.findShopType(findShop);
//			retMap.put("shopType", shopType.get(0).getShopType());
			
			//同步更新导购信息
			FindGuidMember guidMember=new FindGuidMember();
			guidMember.setMemberNo(memberPageReturn.getMemberNoGm());
			FindGuidMemberReturn guidMemberReturn= guidMemberService.findGuidMember(guidMember);
			memberPageReturn.setMemberNameGm(guidMemberReturn.getMemberName());
			
			//个人资料		
			retMap.put("personMember", memberPageReturn);
			
			//VR素材类型
			FindVrMaterialType findVrMaterialType = new FindVrMaterialType();
			findVrMaterialType.setMerchantNo(merchantNo);
			List<FindVrMaterialTypeApiReturn> typeApiReturns = vrMaterialTypeService.findVrMaterialTypeReturnList(findVrMaterialType);
			retMap.put("vrMaterialType", typeApiReturns);
			 
		    //性别
			retMap.put("genders",Gender.values());
				
		} catch (Exception e) {
			logger.error("查询客户聊天错误：" + e);
		}
		return retMap;
	} 
	

	/**
	 * 
	 * 方法说明：客户聊天记录/历史消息
	 * @return
	 * @author 李端强  CreateDate: 2018年1月23日21:17:25
	 *
	 */
	@RequestMapping(value = {"historyChat.do"})
	@ResponseBody
	public Map<String, Object> historyChat(FindPersonMemberPage findPersonMemberPage){
		Map<String, Object> retMap = Maps.newHashMap();
		try {
			Page<FindPersonMemberPageReturn> pageDto = personMemberService.queryPersonMemberPage(findPersonMemberPage);
			List<FindPersonMemberPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			FindPersonMemberPageReturn memberPageReturn=list.get(0);
			
			//个人资料		
			retMap.put("personMember", memberPageReturn);
			
			//导购个人信息findGuidMember
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(findPersonMemberPage.getMemberNoGm());
			FindGuidMemberReturn findGuidMemberReturn =	guidMemberService.findGuidMember(findGuidMember);
			retMap.put("findGuidMemberReturn", findGuidMemberReturn);
		} catch (Exception e) {
			logger.error("查询客户聊天记录错误：" + e);
		}
		return retMap;
	}
	
	/**
	 * 
	 * 方法说明：导购聊天记录/历史消息
	 * @param findChatInfoPageFromWeb
	 * @return
	 * @throws Exception
	 * @author 梅宏博  CreateDate: 2017年12月11日
	 *
	 */
	@RequestMapping(value = {"gmChat.do"})
	@ResponseBody
	public IPage<FindChatInfoPageFromWebReturn> gmChat(FindChatInfoPageFromWeb findChatInfoPageFromWeb) throws Exception{
		logger.info("gmChat(FindChatInfoPageFromWeb = {})", findChatInfoPageFromWeb);
		AssertUtils.notNullAndEmpty(findChatInfoPageFromWeb.getMemberNo(), "memberNo不能为空");
		
		IPage<FindChatInfoPageFromWebReturn> chatInfoPageFromWeb = imChatInfoService.findChatInfoPageFromWeb(findChatInfoPageFromWeb);
		
		
		Map<String, FindChatRoomMemberPageReturn> chatRoomMap = null;
		if (findChatInfoPageFromWeb.isChatRoomFlag()) {
			chatRoomMap = findChatRoomMember(findChatInfoPageFromWeb.getCode());
			logger.info("gmChat.chatRoomMap: {}", chatRoomMap);
		}
		Collection<FindChatInfoPageFromWebReturn> rows = chatInfoPageFromWeb.getRows();
		logger.info("gmChat.rows: {}", rows);
		for (FindChatInfoPageFromWebReturn row : rows) {
			
			
			if (row.getChatroomType() == ChatRoomType.ROOM.getCode()) {
				FindChatRoomMemberPageReturn findChatRoomMemberPageReturn = chatRoomMap.get(row.getChatroomNoWx());
				row.setMemberHeadUrl(findChatRoomMemberPageReturn != null ? findChatRoomMemberPageReturn.getHeadUrl() : null);
			} else {
				row.setMemberHeadUrl(handlePhotoUrl(row));
			}

			
			/** 发红包. */
			if(row.getType().equals(ChatInfoType.S_REDPACKET.getCode())) {
			    if(row.getContent().startsWith("{")) {
				  JSONObject jsStr = JSONObject.parseObject(row.getContent()); 
				  row.setType(jsStr.getString("type"));
				}else {
  					row.setType(ChatInfoType.SYSTEM.getCode());
  				}
			}
			/** 转账. */
            if(row.getType().equals(ChatInfoType.TRANSFER.getCode())) {
            	if(row.getContent().startsWith("{")) {
  				  JSONObject jsStr = JSONObject.parseObject(row.getContent()); 
  				  row.setType(jsStr.getString("type"));
  				}else {
  					row.setType(ChatInfoType.SYSTEM.getCode());
  				}
			}

//			if(StringUtils.isNotEmpty(row.getContent())) {
//				row.setContent(StringUtils.converJson(row.getContent()));
//			}
//			if(StringUtils.isNotEmpty(row.getShareTitle())) {
//				row.setShareTitle(StringUtils.converJson(row.getShareTitle()));
//			}
//			if(StringUtils.isNotEmpty(row.getShareDes())) {
//				row.setShareDes(StringUtils.converJson(row.getShareDes()));
//			}
		}
		
		return chatInfoPageFromWeb;
	}
	
	private Map<String, FindChatRoomMemberPageReturn> findChatRoomMember(String code) 
	{
		AssertUtils.notNullAndEmpty(code, "code不能为空");
		FindChatRoomMemberPage findChatRoomMemberPage = new FindChatRoomMemberPage();
		findChatRoomMemberPage.setRoomCode(code);
		List<FindChatRoomMemberPageReturn> findChatRoomMemberList = chatRoomMemberService.findChatRoomMemberList(findChatRoomMemberPage);
		Map<String, FindChatRoomMemberPageReturn> map = new HashMap<>();
		for (FindChatRoomMemberPageReturn findChatRoomMemberPageReturn : findChatRoomMemberList) {
			map.put(findChatRoomMemberPageReturn.getUserName(), findChatRoomMemberPageReturn);
		}
		return map;
	}

    private String handlePhotoUrl(FindChatInfoPageFromWebReturn imChatInfo) {
        logger.info("handlePhotoUrl.imChatInfo: {}", imChatInfo);
        String photoUrl = null;
        // 导购发送
        if (imChatInfo.getSenderFlag() == SenderFlag.GM.getCode()) {
        	FindShopTerminalReturn findShopTerminalReturn= shopTerminalService.findShopTerminalByWx(imChatInfo.getNoWxGm());
        	if(findShopTerminalReturn!=null) {
        		photoUrl = findShopTerminalReturn.getHeadAddress();
        	}
        	
        // 客户发送
        } else if (imChatInfo.getSenderFlag() == SenderFlag.ZK.getCode()) {
        	FindPersonMemberReturn findPersonMemberReturn= personMemberService.findPersonMemberByNoWxAndShopWx(imChatInfo.getNoWx(), imChatInfo.getNoWxGm());
        	if(findPersonMemberReturn != null) {
        		photoUrl = findPersonMemberReturn.getHeadAddress();
        	}
        }

        return photoUrl;
    }

	/**
	 * 方法说明：IM聊天表情/优惠券/活动/个人名片/素材/VR素材
	 * @return
	 * @author 罗书明 CreateDate: 2017年12月4日
	 *
	 */
	@RequestMapping(value="expressionPackage.do")
	@ResponseBody
	public Object expressionPackage(String packageCode,String type,String merchantNo,String memberNoGm,String memberNo,
									FindVrMaterialCommenPage findVrMaterialCommenPage,@RequestParam(name="userId", required = false) String userId){

		if (ChatUtilTypeEnum.IM_EMO_JI.toString().equals(type)) {
			//表情包及表情
			FindImEmojiPackage findImEmojiPackage = new FindImEmojiPackage();
			List<NewEmojiPackageReturn> imEmojiList=imEmojiPackageService.findImWebEmojiPackage(findImEmojiPackage);
			return imEmojiList;
		}
		//时光长廊
		/*if(getProductType(user.getId()).equals(ProductType.NOINVITE.toString())){
			FindClientActionAppPage findClientActionAppPage= new FindClientActionAppPage();
			findClientActionAppPage.setMemberNo(memberNo);
			findClientActionAppPage.setMemberNoGm(memberNoGm);
			clientConsumeService.findClientActionList(findClientActionAppPage);
		}*/
		
		if (ChatUtilTypeEnum.COUPON.toString().equals(type)) {
			//必填 商户编号  门店编号
			List<FindCouponRuleReturn> findCouponRuleList = null;
			try {
				FindCouponRule findCouponRule = new FindCouponRule();
				findCouponRule.setMerchantNo(merchantNo);
//				findCouponRule.setShopNo(findGuidMemberReturn.getShopNo());
				findCouponRule.setToCoupon(ToCoupon.NONE.toString());
				findCouponRule.setRealName(RealName.NO.toString());
				findCouponRule.setRuleStatus(RuleStatus.YES.toString());
				// 客户端分享地址
				String cp_share_url = localCacheSystemParams.getSystemParam("cp", "share_url", "cp_share_url");
				// 客户端展示的地址
				String cp_client_url = localCacheSystemParams.getSystemParam("cp", "client_url", "cp_client_url");
				// 商户图片静态地址
				String uploadUrl = localCacheSystemParams.getSystemParam("ms", "upload", "uploadUrl");
				findCouponRuleList = couponRuleService.findCouponRuleListWeb(findCouponRule);//TODO 接口更换了，请自己根据需求改动,已变更,2018年2月27日14:21:40
				
				for (FindCouponRuleReturn findCouponRuleReturn : findCouponRuleList) {
					findCouponRuleReturn.setShareUrl(cp_share_url + findCouponRuleReturn.getCode());
					findCouponRuleReturn.setClientUrl(cp_client_url + findCouponRuleReturn.getCode());
					FindMerchantDto findMerchantDto = new FindMerchantDto();
					findMerchantDto.setMerchantNo(findCouponRuleReturn.getMerchantNo());
					FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
					if (findMerchantReturnDto != null) {
						findCouponRuleReturn.setMerchantLogoUrl(uploadUrl + findMerchantReturnDto.getLogoAddr());
					} else {
						findCouponRuleReturn.setMerchantLogoUrl(localCacheSystemParams.getSystemParam("api", "logo", "couponLogoUrl"));
					}
				}
				Collections.sort(findCouponRuleList, new Comparator<FindCouponRuleReturn>() {
					@Override
					public int compare(FindCouponRuleReturn o1, FindCouponRuleReturn o2) { // 数组倒序
						return (int) (o2.getCouponNotes() - o1.getCouponNotes());
					}
				});
			} catch (Exception e) {
				logger.error("查询IM聊天优惠券信息错误：" + e);
			}
			
			return findCouponRuleList;
		} 
		if(ChatUtilTypeEnum.ACTIVITY.toString().equals(type)){
			 //活动
			  FindActivityPage findActivityPage = new FindActivityPage();
			  findActivityPage.setMerchantNo(merchantNo);
			  findActivityPage.setLimit(100);
			  Page<FindActivityPageReturn> pageDto = activityService.findActivityPage(findActivityPage);
			  List<FindActivityPageReturn> list=Lists.newArrayList();
			  list.addAll(pageDto.getRows());
			  for(FindActivityPageReturn activityPageReturn : list){
				  if(StringUtils.isEmpty(activityPageReturn.getLinkUrl())){
					  activityPageReturn.setLinkUrl("?code="+activityPageReturn.getCode());
				  }
			  }
			  return list;
		}
		   
		if (ChatUtilTypeEnum.GUID_CARD.toString().equals(type)) {
			//个人名片(导购)
			FindGuidCard findGuidCard=new FindGuidCard();
			findGuidCard.setMemberNoGm(memberNoGm);
			FindGuidCardReturn guidCardPage = guidCardService.findGuidCardByGm(findGuidCard);
			//个人名片访问地址
			String gmCardUrl=localCacheSystemParams.getSystemParam("api", "common", "gmCardUrl");
			//导购头像地址   
			String guidDHUrl=localCacheSystemParams.getSystemParam("api", "common", "guidDHUrl");
			guidCardPage.setGmCardUrl(gmCardUrl+guidCardPage.getMemberNoGm());
			guidCardPage.setGuidDHUrl(guidDHUrl);
			return guidCardPage;
		}
		
		if (ChatUtilTypeEnum.MATERIAL.toString().equals(type)) {
			
			//查询导购信息
			FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
			findGuidMemberDto.setMemberNo(memberNoGm);
			GuidMemberReturnDto guid = guidMemberService.findGuid(findGuidMemberDto);
			
			Map<String, List<?>> map = new HashMap<>();
			//素材中心
			FindMaterialCommenPage findMaterialCommenPage = new FindMaterialCommenPage();
			findMaterialCommenPage.setMerchantNo(guid.getMerchantNo());
			List<MaterialCommenGroup> materialCommenGroup =  materialcommenService.findMaterialCommenByMerchantNo(findMaterialCommenPage);
			map.put("materialCommenGroup", materialCommenGroup);
			
			//导购素材
			FindMaterial findMaterial=new FindMaterial();
			findMaterial.setMemberNoGm(memberNoGm);
			List<MaterialGroup> materialGroup =  materialService.findMaterialByGm(findMaterial);
			map.put("materialGroup", materialGroup);
			return map;
		}
		
		
		if(ChatUtilTypeEnum.VR_MATERIAL.toString().equals(type)){
			findVrMaterialCommenPage.setMerchantNo(merchantNo);
			List<String> typeList=Lists.newArrayList();
			if(StringUtils.isNotEmpty(findVrMaterialCommenPage.getCodes())){
				String codes = findVrMaterialCommenPage.getCodes();
				if(codes.startsWith(",")) {
					codes = codes.substring(1);
				}
				if(codes.endsWith(",")) {
					codes = codes.substring(0, codes.length() - 1);
				}
				
				if(StringUtils.isNotEmpty(codes)) {
					String[] str =codes.split(",");
					for(String string:str){
						typeList.add(string);
					}
				}
			}
			findVrMaterialCommenPage.setTypeCodes(typeList);
			Page<FindVrMaterialCommenPageReturn> page = vrMaterialCommenService.findVrMaterialCommenPage(findVrMaterialCommenPage);
			List<FindVrMaterialCommenPageReturn> list =Lists.newArrayList();
			list.addAll(page.getRows());
			return list;
		}
		return null;
	}

	/**
	 * 
	 * 方法说明：导购聊天
	 * @return Map<String, Object>
	 * @author 李端强 CreateDate: 2018年1月23日
	 *
	 */
	@RequestMapping(value = {"guideChat.do"})
	@ResponseBody
	public Map<String, Object> guideChat(FindPersonMember findPersonMember,String memberNoGm,String merchantNo,String noWxGm){
		Map<String, Object> retMap = Maps.newHashMap();
		try {
			FindPersonMemberPageReturn personMember = (FindPersonMemberPageReturn) findPersonMember(findPersonMember,noWxGm);
			
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(personMember.getMemberNoGm());
			FindGuidMemberReturn guidMember = guidMemberService.findGuidMember(findGuidMember);
			
			FindShopTidFromWeb findShopTidFromWeb = new FindShopTidFromWeb();
			findShopTidFromWeb.setMerchantNo(merchantNo);
			findShopTidFromWeb.setAssistantNo(memberNoGm);
			List<FindShopTidFromWebReturn> shopTids = shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
			
			FindShopTidFromWebReturn shopTid = null;
			for (FindShopTidFromWebReturn findShopTidFromWebReturn : shopTids) {
				if (guidMember.getNoWx().equals(findShopTidFromWebReturn.getNoWx())) {
					shopTid = findShopTidFromWebReturn;
					break;
				}
			}
			
			retMap.put("personMember", personMember);
			retMap.put("guidMember", guidMember);
			retMap.put("shopTid", shopTid);
			
				//VR素材类型
			 FindVrMaterialType findVrMaterialType = new FindVrMaterialType();
			 findVrMaterialType.setMerchantNo(findPersonMember.getMerchantNo());
			 List<FindVrMaterialTypeApiReturn> typeApiReturns = vrMaterialTypeService.findVrMaterialTypeReturnList(findVrMaterialType);
			 retMap.put("vrMaterialType", typeApiReturns);
		} catch (Exception e) {
			logger.error("查询导购聊天错误：" + e);
		}
		return retMap;
	} 
	
	/**
	 * 
	 * 方法说明：门店微信（终端）详情查询/门店微信明细查询
	 * @return
	 * @author 彭俊霖 CreateDate: 2017年12月04日
	 */
	@RequestMapping(value = {"shopTerminalInfo.do"})
	@ResponseBody
	public FindShopTerminalReturn shopTerminalInfo(FindShopTerminal findShopTerminal){
		return shopTerminalService.findShopTerminal(findShopTerminal);
	}
	
	/**
	 * 
	 * 方法说明：未读客户数
	 * @param findUnreadCountByTerminal
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月5日
	 *
	 */
	@RequestMapping(value = {"unreadPersonCount.do"})
	@ResponseBody
	public List<Map<String, Object>> unreadPersonCount(String noWxList,String merchantNo,String memberNoGm){
		try {
			if (StringUtils.isNotEmpty(noWxList)) {
				String[] noWxs = noWxList.split(",");
				List<String> list = new ArrayList<>();
				for (String string : noWxs) {
					list.add(string);
				}
				FindUnreadCountByTerminal findUnreadCountByTerminal = new FindUnreadCountByTerminal();
				findUnreadCountByTerminal.setNoWxList(list);
				findUnreadCountByTerminal.setMerchantNo(merchantNo);
				return imChatInfoService.findUnreadPersonCountByWx(findUnreadCountByTerminal);
			}
		} catch (Exception e) {
			logger.error("查询未读客户数错误：" + e);
		}
		return new ArrayList<Map<String, Object>>();
	}
	
	/**
	 * 导购未读数
	 * @param noWxList
	 * @param merchantNo
	 * @param memberNoGm
	 * @return
	 */
	@RequestMapping(value = {"unreadCountByGm.do"})
	@ResponseBody
	public List<Map<String, Object>> unreadCountByGm(String[] memberNoGms,String merchantNo,String noWxGm){
		try {
			if (memberNoGms.length>0) {
				List<String> list = new ArrayList<>(Arrays.asList(memberNoGms));
				
				FindUnreadCountByTerminal findUnreadCountByTerminal = new FindUnreadCountByTerminal();
				findUnreadCountByTerminal.setNoWxGm(noWxGm);
				findUnreadCountByTerminal.setMerchantNo(merchantNo);
				findUnreadCountByTerminal.setMemberNoGms(list);
				return imChatInfoService.findUnreadPersonCountByGm(findUnreadCountByTerminal);
			}
		} catch (Exception e) {
			logger.error("查询未读客户数错误：" + e);
		}
		return new ArrayList<Map<String, Object>>();
	}
	
	/**
	 * 
	 * 方法说明：客户未读聊天记录数统计（导购助手）
	 * @param shopNo			门店编号
	 * @param noWxShop			门店微信
	 * @param memberNoList		客户列表
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月5日
	 *
	 */
	@RequestMapping(value = {"unreadCountByMember.do"})
	@ResponseBody
	public List<FindUnreadCountByMemberReturn> unreadCountByMember(String memberNoGm, String noWxShop, String memberNoList){
		List<FindUnreadCountByMemberReturn> returnList = new ArrayList<FindUnreadCountByMemberReturn>();
		try {
			if (StringUtils.isNotEmpty(memberNoList)) {
				String[] memberNos = memberNoList.split(",");
				List<String> list = new ArrayList<>();
				for (String string : memberNos) {
					list.add(string);
				}
				FindUnreadCountByMember findUnreadCountByMember = new FindUnreadCountByMember();
//				findUnreadCountByMember.setShopNo(shopNo);
				findUnreadCountByMember.setNoWxShop(noWxShop);
				findUnreadCountByMember.setMemberNoGm(memberNoGm);
				findUnreadCountByMember.setMemberNoList(list);
				returnList= imChatInfoService.findUnreadCountByMemberFromWeb(findUnreadCountByMember);
			}
		} catch (Exception e) {
			logger.error("查询未读聊天记录错误：" + e);
		}
		return returnList;
	}

	/**
	 * 
	 * 方法说明：发送聊天记录
	 * @param sendImChatInfo
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月5日
	 *
	 */
	@RequestMapping(value = {"sendChatMessage.do"})
	@ResponseBody
	public GeneralResponse sendChatMessage(MultipartFile uploadFile,
			SendImChatInfo sendImChatInfo, String couponRuleCode, String code, String flag,String merchantNo,String amount,String noWx,String pwd){
		AssertUtils.notNullAndEmpty(merchantNo,"商户编号不能为空");
		try {
			String memberNos = sendImChatInfo.getMemberNo();
			String[] memberNoArr = memberNos.split(",");
			String memberNoGms = sendImChatInfo.getMemberNoGm();
			String[] memberNoGmArr = memberNoGms.split(",");
			//*******循环开始
			for(int i=0;i<memberNoArr.length;i++){
				sendImChatInfo.setMemberNo(memberNoArr[i]);
				sendImChatInfo.setMemberNoGm(memberNoGmArr[i]);
			sendImChatInfo.setMsgSource(MessageSource.SA.getCode()); // 消息来源为导购助手
			if(sendImChatInfo.getChatroomType()==null 
					||StringUtils.isEmpty(sendImChatInfo.getMemberNo())
					||StringUtils.isEmpty(sendImChatInfo.getType())
					||StringUtils.isEmpty(merchantNo)
					||StringUtils.isEmpty(sendImChatInfo.getMemberNoGm())) {
				return GeneralResponse.generateFailureResponse("", "发送失败：参数错误！");
			}
			sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode()); // 导购发送

			//查询客户基础信息
			FindPersonMemberReturn personMemberBase =null;
			if(ChatRoomType.PERSONAL.getCode().equals(sendImChatInfo.getChatroomType())) {
				FindPersonMember findPersonMember = new FindPersonMember();
				findPersonMember.setMemberNo(sendImChatInfo.getMemberNo());
				findPersonMember.setMemberNoGm(sendImChatInfo.getMemberNoGm());
				findPersonMember.setShopWx(sendImChatInfo.getNoWxGm());
				personMemberBase = personMemberService.findPersonMemberByMemberNoAndGM(findPersonMember);
				if(personMemberBase == null) {
					return GeneralResponse.generateFailureResponse("", "该客户你未认领！");
				}
			}else {
				FindChatRoom findChatRoom = new FindChatRoom();
				findChatRoom.setCode(sendImChatInfo.getMemberNo());
				FindChatRoomReturn chatRoomReturn= chatRoomService.findChatRoom(findChatRoom);
				if(StringUtils.isEmpty(chatRoomReturn.getMemberNoGm()) ||!chatRoomReturn.getMemberNoGm().equals(sendImChatInfo.getMemberNoGm())) {
					return GeneralResponse.generateFailureResponse("", "该群你未认领！");
				}
			}
			
			// FixBUG: 有 - 的UUID，会导致中控客户端处理失败
			if (!StringUtils.isEmpty(sendImChatInfo.getMsgId())) {
                sendImChatInfo.setMsgId(sendImChatInfo.getMsgId().replaceAll("-",""));
            }
			
		    if (!StringUtils.isEmpty(sendImChatInfo.getShareUrl())) {
                sendImChatInfo.setShareUrl(StringEscapeUtils.unescapeHtml4(sendImChatInfo.getShareUrl()).toString());
            }
		    
			logger.info("sendChatMessage sendImChatInfo：{}", sendImChatInfo);
			
			switch (sendImChatInfo.getType()) {
			case "1":	// 文本
				sendImChatInfo.setContent(StringEscapeUtils.unescapeHtml4(sendImChatInfo.getContent()).toString());
				break;
			case "42":	// 发送名片，包括个人名片和公众号
				/**
				 * content字段以json格式保存以下参数：
				 * 1、发送个人名片时(前端组装参数)：
				 * 	发送人微信号（noWx）：username
				 * 	发送人微信别名：alias
				 * 	发送人客户编号：memberNo
				 * 	发送人所属导购：memberNoGm
				 * 	名片标识：certflag=0
				 * 2、发送公众号时(后台组装参数)：
				 *  公众号xml参数: wxParam
				 * 	公众号微信名：username
				 * 	公众号微信别名：alias
				 *  名片标识：certflag !=0
				 * resources：个人名片时为微信头像、公众号时为logo
				 * shareTitle：个人名片时为客户名称（优先）或微信昵称、公众号时为公众号名字
				 * shareDes：个人名片时先不设置、公众号时为公众号
				 */
				AssertUtils.notNullAndEmpty(flag,"名片类型不能为空");
				AssertUtils.notNullAndEmpty(code,"名片编号不能为空");
				if("0".equals(flag)) {	// 发送个人名片
					FindPersonMember findPersonMember = new FindPersonMember();
					findPersonMember.setCode(code);
					FindPersonMemberReturn findPersonMemberReturn = personMemberService.findPersonMember(findPersonMember);
					FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
					findPersonMemberBase.setMemberNo(findPersonMemberReturn.getMemberNo());
					FindPersonMemberBaseReturn personMemberBaseCard = personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
					sendImChatInfo.setResources(personMemberBaseCard.getHeadAddress());
					sendImChatInfo.setShareTitle(personMemberBaseCard.getNickNameWx());
					Map<String, String> contentMap = new HashMap<>();
					contentMap.put("certflag", "0");
					contentMap.put("username", personMemberBaseCard.getNoWx());
					contentMap.put("alias", personMemberBaseCard.getNoWxAlias());
					contentMap.put("memberNo", findPersonMemberReturn.getMemberNo());
					contentMap.put("memberNoGm", findPersonMemberReturn.getMemberNoGm());
					sendImChatInfo.setContent(JsonUtils.jsonFromObject(contentMap));
					logger.info("发送微信个人名片：{}", sendImChatInfo);
				} else{	// 发公众号名片
					FindWxPublicAccount findWxPublicAccount = new FindWxPublicAccount();
					findWxPublicAccount.setCode(code);
					FindWxPublicAccountReturn findWxPublicAccountReturn = wxPublicAccountService.findWxPublicAccount(findWxPublicAccount);
					sendImChatInfo.setResources(findWxPublicAccountReturn.getPaLogo());
					sendImChatInfo.setShareTitle(findWxPublicAccountReturn.getPaName());
					sendImChatInfo.setShareDes(findWxPublicAccountReturn.getPaDesc());
					Map<String, String> contentMap = new HashMap<>();
					contentMap.put("certflag", findWxPublicAccountReturn.getPaCertflag());
					contentMap.put("username", findWxPublicAccountReturn.getPaUsername());
					contentMap.put("alias", findWxPublicAccountReturn.getPaAlias());
					contentMap.put(com.lj.business.supcon.common.Constants.WX_PARAM_KEY, findWxPublicAccountReturn.getWxParam());
					sendImChatInfo.setContent(JsonUtils.jsonFromObject(contentMap));
					logger.info("发送公众号名片：{}", sendImChatInfo);
				}
				break;
			case "47":	// 图片表情
				Map<String, String> groupMap = localCacheSystemParams.getSystemParamGroup("api", "common");
				FindImEmoji findImEmoji = new FindImEmoji();
				findImEmoji.setCode(sendImChatInfo.getContent());
				FindImEmojiReturn findImEmojiReturn = imEmojiService.findImEmoji(findImEmoji);
				Map<String, String> map = new HashMap<String, String>();
				map.put("code", findImEmojiReturn.getCode());
				map.put("name", findImEmojiReturn.getEmojiName());
				String commonUrl = groupMap.get("commonUrl");
				commonUrl = commonUrl.endsWith("/") ? commonUrl.substring(0, commonUrl.length() -1) : commonUrl;
				map.put("url", commonUrl + findImEmojiReturn.getEmojiUrl());
				sendImChatInfo.setContent(JsonUtils.jsonFromObject(map));
				break;
			case "49":	// 文章  文本消息内容不转义
				String shareImage = localCacheSystemParams.getSystemParam("ms","image", "shareImage");
				logger.info("分享图片链接：" + shareImage);
				sendImChatInfo.setResources(shareImage);
				sendImChatInfo.setContent(StringEscapeUtils.unescapeHtml4(sendImChatInfo.getContent()).toString());
				break;
			case "490":	// 素材
				if(StringUtils.isEmpty(sendImChatInfo.getResources())) {
					// 查询导购信息
					FindGuidMember findGuidMember = new FindGuidMember();
					findGuidMember.setMemberNo(sendImChatInfo.getMemberNoGm());
					FindGuidMemberReturn guidMember = guidMemberService.findGuidMember(findGuidMember);
					
					// XXX 分享icon地址没有写入到resources字段
					groupMap = localCacheSystemParams.getSystemParamGroup("api", "common");
					if(StringUtils.isNotEmpty(guidMember.getHeadAddress())) {
						commonUrl = groupMap.get("commonUrl");
						commonUrl = commonUrl.endsWith("/") ? commonUrl.substring(0, commonUrl.length() -1) : commonUrl;
						sendImChatInfo.setResources(commonUrl + guidMember.getHeadAddress());
					} else {	// 没有设置头像，取默认头像
						sendImChatInfo.setResources(groupMap.get("guidDHUrl"));
					}
				}
				break;
			case "493":	// 导购名片
				// 查询导购信息
				FindGuidMember findGuidMember = new FindGuidMember();
				findGuidMember.setMemberNo(sendImChatInfo.getMemberNoGm());
				FindGuidMemberReturn guidMember = guidMemberService.findGuidMember(findGuidMember);
				
				groupMap = localCacheSystemParams.getSystemParamGroup("api", "common");
				if(StringUtils.isNotEmpty(guidMember.getHeadAddress())) {
					commonUrl = groupMap.get("commonUrl");
					commonUrl = commonUrl.endsWith("/") ? commonUrl.substring(0, commonUrl.length() -1) : commonUrl;
					sendImChatInfo.setResources(commonUrl + guidMember.getHeadAddress());
				} else {	// 没有设置头像，取默认头像
					sendImChatInfo.setResources(groupMap.get("guidDHUrl"));
				}
				sendImChatInfo.setShareTitle(guidMember.getMemberName());
				sendImChatInfo.setShareDes("电话：" + guidMember.getMobile()/* + " \n" + guidMember.getShopName()*/);
				sendImChatInfo.setShareUrl(groupMap.get("gmCardUrl") + guidMember.getMemberNo());
				logger.info("发送导购个人名片：{}", sendImChatInfo);
				break;
			case "491":	// 优惠券
				// 查询导购信息
				AssertUtils.notNullAndEmpty(couponRuleCode,"优惠券规则编号不能为空");
				findGuidMember = new FindGuidMember();
				findGuidMember.setMemberNo(sendImChatInfo.getMemberNoGm());
				guidMember = guidMemberService.findGuidMember(findGuidMember);
				
				FindCouponRule findCouponRule = new FindCouponRule();
				findCouponRule.setCode(couponRuleCode);
				
				FindCouponRuleReturn findCouponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
				
				AddCoupon addCoupon = new AddCoupon();
				addCoupon.setMerchantNo(guidMember.getMerchantNo());
				addCoupon.setMerchantName(guidMember.getMerchantName());
//				addCoupon.setShopNo(guidMember.getShopNo());
//				addCoupon.setShopName(guidMember.getShopName());
				addCoupon.setRuleNo(findCouponRuleReturn.getCode());
				addCoupon.setCouponNo(GUID.generateCode());
				addCoupon.setCouponStatus(CouponStatus.USED.toString());
				addCoupon.setUseDate(new Date());
				addCoupon.setCreateDate(new Date());
				couponService.addCoupon(addCoupon); // 新增优惠券
				
				AddCouponMemberRelation addCouponMemberRelation = new AddCouponMemberRelation();
				addCouponMemberRelation.setMemberNoGm(guidMember.getMemberNo());
				addCouponMemberRelation.setMemberNameGm(guidMember.getMemberName());
				addCouponMemberRelation.setMemberNo(personMemberBase.getMemberNo());
				addCouponMemberRelation.setMemberName(personMemberBase.getMemberName());
				addCouponMemberRelation.setCouponNo(addCoupon.getCouponNo());
				addCouponMemberRelation.setCouponStatus(addCoupon.getCouponStatus());
				addCouponMemberRelation.setUseDate(new Date());
				addCouponMemberRelation.setCreateDate(new Date());
				couponMemberRelationService.addCouponMemberRelation(addCouponMemberRelation); // 新增优惠券用户绑定关系
				
				String cp_result_url = localCacheSystemParams.getSystemParam("cp", "result_url", "cp_result_url");
				// 优惠券领取url
				String resultUrl = String.format(cp_result_url, guidMember.getMemberNo(), personMemberBase.getMemberNo(), addCoupon.getCouponNo());
				
				FindMerchantDto findMerchantDto = new FindMerchantDto();
				findMerchantDto.setMerchantNo(findCouponRuleReturn.getMerchantNo());
				FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
				
				sendImChatInfo.setType("491");	// 分享优惠券
				if (findMerchantReturnDto != null && StringUtils.isNotEmpty(findMerchantReturnDto.getLogoAddr())) {
					String uploadUrl = localCacheSystemParams.getSystemParam("ms", "upload", "uploadUrl");	//商户图片静态地址
					sendImChatInfo.setResources(uploadUrl + findMerchantReturnDto.getLogoAddr()); // 商户图标
				} else {
					String couponUrl = localCacheSystemParams.getSystemParam("api", "logo", "couponLogoUrl");
					sendImChatInfo.setResources(couponUrl);
				}
				sendImChatInfo.setShareTitle(findCouponRuleReturn.getCouponName());
				sendImChatInfo.setShareDes(/*"使用范围:" + findCouponRuleReturn.getShopName() + */"说明:" + findCouponRuleReturn.getCouponRemark());
				sendImChatInfo.setShareUrl(resultUrl);
				logger.info("发送优惠券：{}", sendImChatInfo);

				break;
			case "494":	// 小程序
				// 查询小程序信息
				AssertUtils.notNullAndEmpty(code,"小程序编号不能为空");
				FindWxSmallProgram findWxSmallProgram = new FindWxSmallProgram();
				findWxSmallProgram.setCode(code);
				FindWxSmallProgramReturn findWxSmallProgramReturn = wxSmallProgramService.findWxSmallProgram(findWxSmallProgram);
				sendImChatInfo.setResources(findWxSmallProgramReturn.getSpLogo());
				sendImChatInfo.setShareTitle(findWxSmallProgramReturn.getSpName());
				sendImChatInfo.setShareDes(findWxSmallProgramReturn.getSpDesc());
				sendImChatInfo.setShareUrl(findWxSmallProgramReturn.getSpUrl());
				Map<String, String> contentMap = new HashMap<>();
				contentMap.put(com.lj.business.supcon.common.Constants.WX_PARAM_KEY, findWxSmallProgramReturn.getWxParam());
				sendImChatInfo.setContent(JsonUtils.jsonFromObject(contentMap));
				logger.info("发送小程序：{}", sendImChatInfo);
				break;
			case "48":
				logger.info("---- > type :48  content :{}",StringUtils.converJson(sendImChatInfo.getContent()));
				sendImChatInfo.setContent(StringUtils.converJson(sendImChatInfo.getContent()));
				break;
			case "436207665"://发红包
				logger.info("---- > type :436207665");
				AssertUtils.notNullAndEmpty(pwd,"支付密码不能为空！");
				AssertUtils.notNullAndEmpty(merchantNo,"商户号不能为空！");
				if(ChatRoomType.PERSONAL.getCode().equals(sendImChatInfo.getChatroomType())) {
					FindShopTerminalReturn findShopTerminalReturn= shopTerminalService.findShopTerminalByWx(sendImChatInfo.getNoWxGm());
					AssertUtils.notNull(findShopTerminalReturn,"终端不存在!");
					AssertUtils.notNullAndEmpty(findShopTerminalReturn.getWxPwd(),"终端未维护支付密码!");
					String oldPwd = WxPwdEncryptUtils.encrypt(pwd, findShopTerminalReturn.getWorkKey());
					if(!oldPwd.equals(findShopTerminalReturn.getWxPwd())) {
						return GeneralResponse.generateFailureResponse("", "支付密码错误！");
					}
					if(!commonService.getClientStatus(sendImChatInfo.getNoWxGm())) {
						return GeneralResponse.generateFailureResponse("", "中控已离线！");
					}
					WxRedpackDetailInfoDto wxRedpackDetailInfoDto = new WxRedpackDetailInfoDto();
					wxRedpackDetailInfoDto.setNoWx(noWx);
					wxRedpackDetailInfoDto.setNoWxShop(sendImChatInfo.getNoWxGm());
					//元转分
					BigDecimal amountB = new BigDecimal(amount).multiply(new BigDecimal(100));
					if(amountB.compareTo(BigDecimal.ZERO)<=0) {
						return GeneralResponse.generateFailureResponse("", "金额必须大于0！");
					}
					wxRedpackDetailInfoDto.setAmount(amountB.longValue());
					if(sendImChatInfo.getContent() == null || sendImChatInfo.getContent().equals("")) {
						wxRedpackDetailInfoDto.setContent("恭喜发财，大吉大利");
					}else {
					    wxRedpackDetailInfoDto.setContent(sendImChatInfo.getContent());
					}
					wxRedpackDetailInfoDto.setMemberNo(personMemberBase.getMemberNo());
					wxRedpackDetailInfoDto.setMemberNoGm(personMemberBase.getMemberNoGm());
					wxRedpackDetailInfoDto.setMemberName(personMemberBase.getMemberName());
					wxRedpackDetailInfoDto.setMemberNameGm(personMemberBase.getMemberNameGm());
					wxRedpackDetailInfoDto.setMerchantNo(merchantNo);
					wxRedpackDetailInfoDto.setMsgId(GUID.generateByUUID());
					wxRedpackDetailInfoDto.setType(Integer.valueOf(RedPackTypeEnum.NORMAL.getType()));//发送转账
					String messageType = "1";//messageType:1 为红包
					String source = "2";//发送源：0 或null为系统， 1 APP,  2.WEB ，
					wxRedpackDetailInfoService.sendWxRedpackByIm(wxRedpackDetailInfoDto, messageType, source); 
					return GeneralResponse.generateSuccessResponse("红包发送中");
				}else {
					return GeneralResponse.generateFailureResponse("", "群聊红包暂不支持！");
				}
			case "419430449"://发转账
				logger.info("---- > type :419430449");
				AssertUtils.notNullAndEmpty(pwd,"支付密码不能为空！");
				AssertUtils.notNullAndEmpty(merchantNo,"商户号不能为空！");
				if(ChatRoomType.PERSONAL.getCode().equals(sendImChatInfo.getChatroomType())) {
					FindShopTerminalReturn findShopTerminalReturn= shopTerminalService.findShopTerminalByWx(sendImChatInfo.getNoWxGm());
					AssertUtils.notNull(findShopTerminalReturn,"终端不存在!");
					AssertUtils.notNullAndEmpty(findShopTerminalReturn.getWxPwd(),"终端未维护支付密码!");
					String oldPwd = WxPwdEncryptUtils.encrypt(pwd, findShopTerminalReturn.getWorkKey());
					if(!oldPwd.equals(findShopTerminalReturn.getWxPwd())) {
						return GeneralResponse.generateFailureResponse("", "支付密码错误！");
					}
					if(!commonService.getClientStatus(sendImChatInfo.getNoWxGm())) {
						return GeneralResponse.generateFailureResponse("", "中控已离线！");
					}
					WxRedpackDetailInfoDto wxRedpackDetailInfoDto = new WxRedpackDetailInfoDto();
					wxRedpackDetailInfoDto.setNoWx(noWx);
					wxRedpackDetailInfoDto.setNoWxShop(sendImChatInfo.getNoWxGm());
					//元转分
					BigDecimal amountB = new BigDecimal(amount).multiply(new BigDecimal(100));
					if(amountB.compareTo(BigDecimal.ZERO)<=0) {
						return GeneralResponse.generateFailureResponse("", "金额必须大于0！");
					}
					wxRedpackDetailInfoDto.setAmount(amountB.longValue());
					if(sendImChatInfo.getContent() == null || sendImChatInfo.getContent().equals("")) {
						wxRedpackDetailInfoDto.setContent("恭喜发财，大吉大利");
					}else {
					    wxRedpackDetailInfoDto.setContent(sendImChatInfo.getContent());
					}
					wxRedpackDetailInfoDto.setMemberNo(personMemberBase.getMemberNo());
					wxRedpackDetailInfoDto.setMemberNoGm(personMemberBase.getMemberNoGm());
					wxRedpackDetailInfoDto.setMemberName(personMemberBase.getMemberName());
					wxRedpackDetailInfoDto.setMemberNameGm(personMemberBase.getMemberNameGm());
					wxRedpackDetailInfoDto.setMerchantNo(merchantNo);
					wxRedpackDetailInfoDto.setType(Integer.valueOf(RedPackTypeEnum.TRANS.getType()));//发送转账
					wxRedpackDetailInfoDto.setMsgId(GUID.generateByUUID());
					String messageType = "2";//messageType:2 为转账
					String source = "2";//发送源：0 或null为系统， 1 APP,  2.WEB ，
					wxRedpackDetailInfoService.sendWxRedpackByIm(wxRedpackDetailInfoDto, messageType, source); 
		
					return GeneralResponse.generateSuccessResponse("转账发送中");
				}else {
					return GeneralResponse.generateFailureResponse("", "群聊转账暂不支持！");
				}	
		
			default:	// 其他：语音等
				if (StringUtils.isEmpty(sendImChatInfo.getResources())) {
					sendImChatInfo.setResources(uploadFile(uploadFile, merchantNo));
				}
				break;
			}
			imChatInfoService.sendChat(sendImChatInfo);
			} //*******循环结束
			return GeneralResponse.generateSuccessResponse("发送成功");
		} catch(TsfaServiceException e){
			return GeneralResponse.generateFailureResponse(e);
		}catch (Exception e) {
			return GeneralResponse.generateFailureResponse(e);
		}
	}
	
	
    public AddImChatInfo buidAddImChatInfoForRedPack(SendImChatInfo sendImChatInfo ) {
    	AddImChatInfo addImChatInfo = new AddImChatInfo();
		addImChatInfo.setCode(GUID.generateCode());
		addImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
		addImChatInfo.setNoWxGm(sendImChatInfo.getNoWxGm());
		addImChatInfo.setMemberNo(sendImChatInfo.getMemberNo());
		addImChatInfo.setType(sendImChatInfo.getType());	
		addImChatInfo.setChatroomType(sendImChatInfo.getChatroomType());
		addImChatInfo.setContent(sendImChatInfo.getContent());
		addImChatInfo.setMsgSource(MessageSource.SA.getCode());
		addImChatInfo.setStatus(MessageStatus.SUCCESS.getCode());
		addImChatInfo.setThirdReadFlag(ReadFlag.NO.getCode());
		return addImChatInfo;
    }
	/**
	 *
	 * 方法说明：上传文件到服务器
	 * @param uploadFile
	 * @param merchantNo
	 * @return
	 * @throws IOException
	 * @author 李端强 CreateDate: 2018年1月23日
	 *
	 */
	private String uploadFile(MultipartFile uploadFile, String merchantNo) throws IOException {
		if (uploadFile == null || uploadFile.isEmpty()) {
			throw new TsfaServiceException(ErrorCode.HEAD_IMAGE_IS_EMPTY, "上传文件为空");
		}
		
		// 判断文件格式
		String fileName = uploadFile.getOriginalFilename();
		String fileType = FileUtil.getFileType(fileName);
		if(StringUtils.isEmpty(fileType)) {
			logger.error("不支持的文件格式: {}", fileName);
			fileType = "file";	// 默认为file格式
		}

		Map<String, String> map = localCacheSystemParams.getSystemParamGroup(SystemAliasName.weixin.name(), "imfile");
		String uploadPath = map.get("uploadPath"); 
		String filePath = merchantNo + "/" + fileType.toLowerCase() + "/";
		String imageFolder = uploadPath+filePath;
		// 保存文件
		String fileInputName= FileUtil.saveFile(imageFolder, uploadFile);
		String url = map.get("uploadUrl") + filePath + fileInputName;
		return url;
		
	}
	
	/**
	 * 
	 * 方法说明：更新第三方已读
	 * @param updateThirdHaveReadFromWeb
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月5日
	 *
	 */
	@RequestMapping(value = {"updateThirdHaveRead.do"})
	@ResponseBody
	public Boolean updateThirdHaveRead(UpdateThirdHaveReadFromWeb updateThirdHaveReadFromWeb){
		try {
			imChatInfoService.updateThirdHaveReadFromWeb(updateThirdHaveReadFromWeb);
			return true;
		} catch (Exception e) {
			logger.error("更新第三方已读错误：" + e);
		}
		return false;
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询客户列表
	 * @param findPersonMemberPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月4日
	 *
	 */
	@RequestMapping(value={"personMemberList.do"})
	@ResponseBody
	public Map<String,Object> personMemberList(FindImIndexPage findImIndex,Integer pageNo, Integer pageSize){
		logger.info(" findImIndex: {}",  findImIndex);
		//返回参数
		Map<String,Object> resultMap=new HashMap<String, Object>();
		AssertUtils.notNullAndEmpty(findImIndex.getMemberNoGm(), "导购编号不能为空");
		GuidMember gmQuery = new GuidMember();
		gmQuery.setMemberNo(findImIndex.getMemberNoGm());
		GuidMember guidMember = guidMemberService.findSingleGuidMember(gmQuery);
		AssertUtils.notNull(guidMember, "导购不存在");
		String merchantNo = guidMember.getMerchantNo();

		//默认查询第一页
		pageNo = pageNo == null ? 1 : pageNo;
		
		if (pageNo != null && pageNo > 0 && pageSize != null && pageSize > 0) {
			findImIndex.setStart((pageNo - 1) * pageSize);
			findImIndex.setLimit(pageSize);
		}
		
		//查询客户分组
//		FindPmTypePageReturn findPmTypePageReturn=new FindPmTypePageReturn();
//		findPmTypePageReturn.setStatus("Y");
//		findPmTypePageReturn.setMerchantNo(merchantNo);
//		List<FindPmTypePageReturn> pmType=pmTypeService.findPmTypePages(findPmTypePageReturn);
//		
//		Map<String, String> pmTypeMap = new HashMap<>();
//		for (FindPmTypePageReturn pmTypeReturn : pmType) {
//			pmTypeMap.put(pmTypeReturn.getCode(), pmTypeReturn.getTypeName());
//		}
		
		Long count = personMemberService.findImIndexListCount(findImIndex);
		if(count==0) {
			resultMap.put("page", new Page<FindImIndexPageReturn>(Lists.newArrayList(),0,0,0));
			return resultMap;
		}
		List<FindImIndexPageReturn> list = personMemberService.findImIndexList(findImIndex);
//		findImIndex.setTypeCode(PmTypeTimeFlag.TODAY.toString());//查询今日数据
//		Long todayCount = personMemberService.findImIndexListCount(findImIndex);
		
		for (FindImIndexPageReturn findImIndexPageReturn : list) {
//			findImIndexPageReturn.setPmTypeName(pmTypeMap.get(findImIndexPageReturn.getPmTypeCode()));
			List<String> memberNoList = new ArrayList<>();
			memberNoList.add(findImIndexPageReturn.getMemberNo());
			//查询每个客户的未读消息数
			FindUnreadCountByMember findUnreadCountByMember = new FindUnreadCountByMember();
//			findUnreadCountByMember.setShopNo(findImIndexPageReturn.getShopNo());
			findUnreadCountByMember.setNoWxShop(findImIndexPageReturn.getNoWxGm());
			findUnreadCountByMember.setMemberNoGm(findImIndexPageReturn.getMemberNoGm());
			findUnreadCountByMember.setMemberNoList(memberNoList);
			List<FindUnreadCountByMemberReturn> unreadCountByMember = imChatInfoService.findUnreadCountByMemberFromWeb(findUnreadCountByMember);
			if (null != unreadCountByMember && !unreadCountByMember.isEmpty()){
				findImIndexPageReturn.setUnreadCount(unreadCountByMember.get(0).getUnreadCount());
			}
		}
		
//		FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
//		findGuidMemberPage.setNoWx(findImIndex.getNoWx());
//		findGuidMemberPage.setLimit(1000);
//		List<FindGuidMemberPageReturn> guidMembers = guidMemberService.findGuidMembers(findGuidMemberPage);
		
		Page<FindImIndexPageReturn> page = new Page<FindImIndexPageReturn>(list,count
				,findImIndex.getStart(),findImIndex.getLimit());
		
		resultMap.put("page", page);
//		resultMap.put("guidMembers", guidMembers);
//		resultMap.put("todayCount", todayCount);
		return resultMap;
	}
	
	/**
	 *
	 * 方法说明：查询客户信息
	 * @param findPersonMember
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月6日
	 *
	 */
	@RequestMapping(value={"findPersonMember.do"})
	@ResponseBody
	public Object findPersonMember(FindPersonMember findPersonMember,String noWxGm){
			AssertUtils.notNullAndEmpty(noWxGm, "终端微信不能为空");
			AssertUtils.notNullAndEmpty(findPersonMember.getCode(), "编号不能为空");
			if (!findPersonMember.isChatRoomFlag()) {
				Map<String, String> map = new HashMap<>();
				map.put("code", findPersonMember.getCode());
				map.put("pmTypeCode", findPersonMember.getPmTypeCode());
				FindPersonMemberPageReturn personMemberPageReturn = personMemberService.getByCond(map);
				AssertUtils.notNull(personMemberPageReturn,"客户不存在！");
				//查询导购
				FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
				findGuidMemberDto.setMemberNo(personMemberPageReturn.getMemberNoGm());
				GuidMemberReturnDto findGuid = guidMemberService.findGuid(findGuidMemberDto);
				personMemberPageReturn.setMobileGm(findGuid.getMobile());
				personMemberPageReturn.setMemberNameGm(findGuid.getMemberName());
				
				//设置地区
				String province = personMemberPageReturn.getProvinceCode() == null ? "" : areaHessianService.getAreaNameByCode(personMemberPageReturn.getProvinceCode());
				String city = personMemberPageReturn.getCityCode() == null ? "" : areaHessianService.getAreaNameByCode(personMemberPageReturn.getCityCode());
				personMemberPageReturn.setAddress(province + city); 
				//查询客户标签	1350					
				FindPmLabel findPmLabel = new FindPmLabel();		
				findPmLabel.setShopWx(noWxGm);		
				findPmLabel.setMemberNo(personMemberPageReturn.getMemberNo());		
				findPmLabel.setMerchantNo(personMemberPageReturn.getMerchantNo());		
				List<PmLabelDto> labels = pmLabelService.findPmLabelByMemberNoAndMerchantNo(findPmLabel);		
				personMemberPageReturn.setLables(labels);
				return personMemberPageReturn;
				
			} else {
				// 群信息
				FindChatRoomMember findChatRoomMember = new FindChatRoomMember();
				findChatRoomMember.setCode(findPersonMember.getCode());
				return chatRoomMemberService.findChatRoomMember(findChatRoomMember);
			}
	}
	
	/**
	 *
	 * 方法说明：获取导购电话
	 * @param findGuidMemberDto
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月11日
	 *
	 */
	@RequestMapping(value={"getGmMobile.do"})
	@ResponseBody
	public String getGmMobile(FindGuidMemberDto findGuidMemberDto){
		GuidMemberReturnDto guid = guidMemberService.findGuid(findGuidMemberDto);
		return guid.getMobile();
	}
	
	/**
	 * 
	 * 方法说明：获取导购个人微信号
	 * @param findGuidMemberDto
	 * @return noWxPersonal
	 * @author 彭俊霖  CreateDate: 2017年12月16日
	 *
	 */
//	@RequestMapping(value={"getGmNoWxPersonal.do"})
//	@ResponseBody
//	public Map<String,String> getGmNoWxPersonal(FindGuidMemberDto findGuidMemberDto){
//		GuidMemberReturnDto guid = guidMemberService.findGuid(findGuidMemberDto);
//		Map<String,String> resultMap=new HashMap<String, String>();
//		if(StringUtils.isEmpty(guid.getNoWxPersonal())){
//			resultMap.put("state", "false");
//			resultMap.put("msg", "导购个人微信号为空");
//			return resultMap;
//		}
//		// 获取添加了导购私人微信对应的客户信息
//		 FindPersonMemberByShopAndNoWx find = new FindPersonMemberByShopAndNoWx();
////		 find.setShopNo(guid.getShopNo());
//		 find.setNoWx(guid.getNoWxPersonal());
//		 FindPersonMemberByShopAndNoWxReturn personMemberByGm=personMemberImService.findPersonMemberByShopAndNoWx(find);
//		 if(personMemberByGm==null){
//			 resultMap.put("state", "false");
//			 resultMap.put("msg", "导购所属门店没有添加导购个人微信为好友");
//		 }else{
//			 resultMap.put("state", "true");
//			 resultMap.put("msg", guid.getNoWxPersonal());
//		 }
//		 return resultMap;
//	}
	
	/**
	 *
	 * 方法说明：转换音频格式pcm===>>wav
	 * @param resource
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月11日
	 * @throws IOException 
	 *
	 */
	@RequestMapping(value={"audioConvert.do"})
	@ResponseBody
	public GeneralResponse audioConvert(String resource) throws IOException{
		try {
			AssertUtils.notNullAndEmpty(resource,"资源路径不能为空！");
			//IM文件存储路径前缀
		    String UPLOAD_IM_PATH =  localCacheSystemParams.getSystemParam(SystemAliasName.weixin.toString(),SystemParamConstant.IM_UPLOAD_GROUP, SystemParamConstant.UPLOAD_PATH);
            String UPLOAD_IM_URL =  localCacheSystemParams.getSystemParam(SystemAliasName.weixin.toString(),SystemParamConstant.IM_UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);

			//源文件
		    resource = resource.replace(UPLOAD_IM_URL, "/");
		    File sourcefile = new File(UPLOAD_IM_PATH + resource);
		    
		    //如果源文件不存在
			if (!sourcefile.exists()) {
				logger.error("源文件不存在：" + UPLOAD_IM_PATH + resource);
				return GeneralResponse.generateFailureResponse("0", "源文件不存在：" + UPLOAD_IM_PATH + resource);
			}
		
			 String mp3 = AmrToMp3Util.encoder(sourcefile);		
			 String parentPath = resource.substring(0, resource.lastIndexOf("/"));
			 String returnPath = UPLOAD_IM_URL+parentPath+"/"+mp3;
			return GeneralResponse.generateSuccessResponse(returnPath);

		} catch (Exception e) {
			logger.error("文件地址错误：" + resource, e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}
	
	/** 
	* 复制单个文件 
	* @param oldPath String 原文件路径 如：c:/fqf.txt 
	* @param newPath String 复制后路径 如：f:/fqf.txt 
	* @return boolean 
	*/ 
	public void copyFile(String oldPath, String newPath) { 
			try { 
					int bytesum = 0; 
					int byteread = 0; 
					File oldfile = new File(oldPath); 
					if (oldfile.exists()) { //文件存在时 
						InputStream inStream = new FileInputStream(oldPath); //读入原文件 
						FileOutputStream fs = new FileOutputStream(newPath); 
						byte[] buffer = new byte[1444]; 
						int length; 
						while ( (byteread = inStream.read(buffer)) != -1) { 
							bytesum += byteread; //字节数 文件大小 
							System.out.println(bytesum); 
							fs.write(buffer, 0, byteread); 
						} 
						inStream.close(); 
					} 
			}catch (Exception e) { 
				System.out.println("复制单个文件操作出错"); 
				e.printStackTrace(); 
			} 
	} 
	
	/**
	 *
	 * 方法说明：Date转换为不带时分秒的Date
	 * @param date 带时分秒的Date
	 * @return 不带时分秒的Date
	 * @throws Exception
	 * @author 李端强 CreateDate: 2018年1月23日
	 *
	 */
	public static Date getDayBeginTime(Date date) throws Exception{
		String formatDate = DateUtils.formatDate(date, "yyyy-MM-dd");
		return DateUtils.parseDate(formatDate, "yyyy-MM-dd");
	}
	
	/**
	 * 
	 * 方法说明：更新客户信息
	 * @param memberNoGm	导购编号
	 * @param memberNo		客户编号
	 * @param memberName	客户名称(微信本地备注 刘萱瑶12-14 11：00确定)
	 * @return
	 * @author 曾垂瑜 CreateDate: 2017年12月12日
	 *
	 */
	@RequestMapping(value={"updatePersonMember.do"})
	@ResponseBody
	public GeneralResponse updatePersonMember(String memberNoGm, String memberNo, String memberName,String noWxGm){
		EditPersonMember editPersonMember = new EditPersonMember();
		editPersonMember.setMemberNo(memberNo);
		editPersonMember.setNoWxGm(noWxGm);
		editPersonMember.setMemberNoGm(memberNoGm);
		try {
			//HTML特殊字符转义
			String content= StringEscapeUtils.unescapeHtml4(memberName).toString();
			editPersonMember.setMemberName(content);
			personMemberService.updatePersonMemberName(editPersonMember);
		} catch(TsfaServiceException e) {
			logger.error("更新客户信息失败", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch(Exception e) {
			logger.error("更新客户信息失败", e);
			return GeneralResponse.generateFailureResponse(e);
		}
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 * 方法说明：获取推送消息
	 * @param findMessagePushPage
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月19日
	 *
	 */
	/*@RequestMapping(value={"getMessagePush.do"})
	@ResponseBody
	public Map<String, Object> getMessagePush(FindMessagePushPage findMessagePushPage, int pageNo){
		try {
			findMessagePushPage.setMsgStatus("INVALID");
			findMessagePushPage.setLimit(20);
			findMessagePushPage.setStart((pageNo - 1) * 20);
			findMessagePushPage.setIsPage(true);
			findMessagePushPage.setSortDir(PageSortType.desc);
			List<FindMessagePushPageReturn> messagePush = messagePushService.findMessagePushByGm(findMessagePushPage);
			
			Map<String,Object> map = new HashMap<>();
			map.put("pageNo", pageNo);
			map.put("rows", messagePush);
			return map;
		} catch(Exception e){
			logger.error("获取推送消息失败", e);
			return null;
		}
	}*/
	
	/**
	 * 方法说明：朋友圈点赞
	 * @param toLikeDto
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月22日
	 */
	@RequestMapping(value={"toImLike.do"})
	@ResponseBody
	public GeneralResponse toImLike(ToFriendsLikeDto toLikeDto){
		try {
			friendsFacade.toImLike(toLikeDto);
		}catch (TsfaServiceException e) {
			logger.error("点赞朋友圈错误", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("点赞朋友圈错误", e);
			return GeneralResponse.generateFailureResponse();
		}
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 * 方法说明：朋友圈评论
	 * @param toFriendsCommentDto
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月22日
	 *
	 */
	@RequestMapping(value={"toComment.do"})
	@ResponseBody
	public GeneralResponse toComment(ToFriendsCommentDto toFriendsCommentDto){
		try {
			friendsFacade.toComment(toFriendsCommentDto);
		}catch (TsfaServiceException e) {
			logger.error("点赞朋友圈错误", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("评论朋友圈错误", e);
			return GeneralResponse.generateFailureResponse();
		}
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 * 方法说明：发送朋友圈
	 * @param toFriendsInfosDto
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月22日
	 *
	 */
	@RequestMapping(value={"toFriendsInfo.do"})
	@ResponseBody
	public GeneralResponse toFriendsInfo(ToFriendsInfosDto toFriendsInfosDto){
		try {
			friendsFacade.toFriendsInfo(toFriendsInfosDto);
		}catch (TsfaServiceException e) {
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("错误", e);
			return GeneralResponse.generateFailureResponse();
		}
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取朋友圈信息
	 * @param findImFriendsInfoPage
	 * @param pageNo
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月22日
	 *
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value={"getfriendData.do"})
	@ResponseBody
	public Map<String, Object> getfriendData(FindImFriendsInfoPage findImFriendsInfoPage, Integer pageNo, Integer pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			//获取朋友圈信息
			findImFriendsInfoPage.setStart(pageNo == null ? 1 : (pageNo - 1) * 10);

			if(findImFriendsInfoPage==null) {
				findImFriendsInfoPage = new FindImFriendsInfoPage();
			}  
			if(pageSize!=null){
				findImFriendsInfoPage.setLimit(pageSize);//尺寸
			}
			if(pageNo!=null){
				findImFriendsInfoPage.setStart((pageNo-1)*findImFriendsInfoPage.getLimit());//起始
			}
			findImFriendsInfoPage.setStatus("2");//只查询发送成功的
			findImFriendsInfoPage.setMemberNoGm(null);//同一微信终端都可以看
			Page<ImFriendsInfoDto> infos = imFriendsInfoService.findImFriendsInfoWeb(findImFriendsInfoPage);
			logger.info(" find data :{}" ,infos);
			ArrayList<ImFriendsInfoDto> basePageReturns = Lists.newArrayList();
			basePageReturns.addAll(infos.getRows());
			Page<ImFriendsInfoDto> pages = new Page<ImFriendsInfoDto>(basePageReturns,infos.getTotal()
					,findImFriendsInfoPage.getStart(),findImFriendsInfoPage.getLimit());
			map.put("success", true);
			map.put("friendForumPage", pages);//成功返回
			
			//map.put("likeInfo", likeInfoMap);
			map.put("pageNo", pageNo);
		} catch (Exception e) {
			map.put("success", false);
			map.put("friendForumPage", "获取导购绑定的朋友圈信息异常.");
			logger.error("获取朋友圈信息错误", e);
		}
		logger.info("map ---- >",map);
		return map;
	}
	
	/**
	 * 
	 * 方法说明：获取导购助手微信信息
	 * @param findShopTidFromWeb
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月23日
	 *
	 */
	@RequestMapping(value={"getShopWx.do"})
	@ResponseBody
	public List<FindShopTidFromWebReturn> getShopWx(FindShopTidFromWeb findShopTidFromWeb,String memberNoGm){
		//导购助手管理的门店列表查询
		findShopTidFromWeb.setAssistantNo(memberNoGm);
		return shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
	}
	
	/**
	 * 
	 * 方法说明：话术选择
	 * @author 彭俊霖
	 * CreateDate: 2017-01-09
	 * @param findWordsInfoApp
	 * @return
	 */
	/*@RequestMapping(value="wordsSelect.do")
	@ResponseBody
	public List<FindWordsInfoAppReturn> wordsSelect(FindWordsInfoApp findWordsInfoApp)  {
		List<FindWordsInfoAppReturn> wordsSelect=Lists.newArrayList();
		try {
			if(findWordsInfoApp!=null && findWordsInfoApp.getMerchantNo()!=null){
				wordsSelect=wordsInfoService.wordsSelect(findWordsInfoApp);
			}
		} catch (Exception e) {
			logger.error("获取话术信息错误！",e);
		}
		return wordsSelect;
	}*/
	
	/**
	 * 
	 * 方法说明：话术信息-更多
	 * @author 彭俊霖
	 * CreateDate: 2017-01-09
	 * @param findWordsInfoWeb
	 * @return
	 */
	@RequestMapping(value="moreWords.do")
	@ResponseBody
	public FindWordsInfoWebReturn moreWords(FindWordsInfoWeb findWordsInfoWeb)  {
		FindWordsInfoWebReturn moreWords=new FindWordsInfoWebReturn();
		try {
			if(findWordsInfoWeb!=null && findWordsInfoWeb.getMerchantNo()!=null){
				List<FindWordsInfoReturn> infoList = wordsInfoService.moreWords(findWordsInfoWeb);
				List<FindWordsTypeSelectReturn> typeList=wordsTypeService.findWordsTypes(findWordsInfoWeb);
				moreWords.setInfoList(infoList);
				moreWords.setTypeList(typeList);
			}
		} catch (Exception e) {
			logger.error("获取话术信息-更多错误！",e);
		}
		return moreWords;
	}
    
	/**
	 * 
	 * 方法说明：查询下级省市区
	 * @param parentId
	 * @return
	 * @author 曾垂瑜 CreateDate: 2018年1月9日
	 *
	 */
	@ResponseBody
	@RequestMapping(value={"selectAreaByParentId.do"})
	public List<Area> selectAreaByParentId(String parentId) {
		// 为空，则查询中国下所有省
		if(StringUtils.isEmpty(parentId)) {
			parentId = "1";
		}
		return areaHessianService.selectAreaByParentId(parentId);
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：根据cityCode查询城市名称
	 *
	 * @param code
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年3月9日
	 *
	 */
	@RequestMapping(value = {"getByCode.do"})
    @ResponseBody
    public Map<String, Object> getByCode(String code){
        Map<String, Object> retMap = Maps.newHashMapWithExpectedSize(3);
        try {
            retMap.put("success", Boolean.TRUE);
            retMap.put("data", areaHessianService.getAreaNameByCode(code));
        } catch (Exception e) {
            logger.error("getByCode查询错误：" + e);
            retMap.put("success", Boolean.FALSE);
            retMap.put("msg", "查询错误");
        }
        return retMap;
    }
	
	@RequestMapping(value = {"toCancelChatInfo.do"})
    @ResponseBody
    public Map<String, Object> toCancelChatInfo(String code){
		Map<String, Object> retMap = Maps.newHashMapWithExpectedSize(3);
		
		logger.debug("toCancelChatInfo code: {}", code); 
		
		try {
			if(StringUtils.isEmpty(code)){
				retMap.put("success", Boolean.FALSE);
	            retMap.put("msg", "参数错误！");
				return retMap;
			}
			
			imChatInfoService.toCancelChatInfo(code);
			retMap.put("success", Boolean.TRUE);
		} catch (Exception e) {
			logger.error("撤销错误 e={}",e);

			retMap.put("success", Boolean.FALSE);
            retMap.put("msg", "撤销消息失败!");
		}
		
        return retMap;
    }
	
	/**
	 * 请求中控下载文件
	 * @param msgId		消息ID
	 * @param content	文件下载信息
	 * @param findFlag	查询标志，1：导购端请求查询，2：导购助手请求查询
	 * @return ture or false
	 */
    @PostMapping("requestZkUploadChatFile.do")
	@ResponseBody
	public Map<String, Object> requestZkUploadChatFile(String msgId, String content, int findFlag){
		Map<String, Object> retMap = Maps.newHashMapWithExpectedSize(3);
    	try{
			imChatInfoService.requestZkUploadChatFile(msgId, content, findFlag);
			retMap.put("success", Boolean.TRUE);
			return retMap;
		} catch (Exception e){
			logger.error("请求下载文件错误 e={}",e);
			retMap.put("success", Boolean.FALSE);
			retMap.put("msg", "请求下载文件失败!");
		}
		return retMap;
	}

	/**
	 * 请求中控下载视频
	 * @param msgId		消息ID
	 * @param content	视频下载信息
	 * @param findFlag	查询标志，1：导购端请求查询，2：导购助手请求查询
	 * @return ture or false
	 */
    @PostMapping("requestZkUploadChatVideo.do")
	@ResponseBody
	public Map<String, Object> findChatVideo(String msgId, String content, int findFlag){
		Map<String, Object> retMap = Maps.newHashMapWithExpectedSize(3);
		try{
			imChatInfoService.requestZkUploadChatVideo(msgId, content, findFlag);
			retMap.put("success", Boolean.TRUE);
			return retMap;
		} catch (Exception e){
			logger.error("请求下载视频错误 e={}",e);
			retMap.put("success", Boolean.FALSE);
			retMap.put("msg", "请求下载视频失败!");
		}
		return retMap;
	}
    
    /**
	 * 
	 *
	 * 方法说明：话术列表
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoApp
	 * @return
	 */
	@RequestMapping(value="findWords.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<FindWordsAppReturn> findWords(FindWordsTypePage findWordsTypePage)  {
		List<FindWordsAppReturn> list = new ArrayList<>();
		String code = findWordsTypePage.getCode();
		//code equals 1 查公共 ,code equals 2查个人
		if("1".equals(code)){
			FindWordsInfoApp findWordsInfoApp = new FindWordsInfoApp();
			findWordsInfoApp.setMerchantNo(findWordsTypePage.getMerchantNo());
			list = commonWordsTypeService.findWords(findWordsInfoApp);
		}else{
			list = wordsTypeService.findPersonWords(findWordsTypePage);
		}
		return list;
	}
	
	
	
	/**
	 * 
	 *
	 * 方法说明：话术搜索
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoApp
	 * @return
	 */
	@RequestMapping(value="wordsSearch.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<FindWordsInfoAppReturn> wordsSearch(FindWordsInfoPage findWordsInfoPage)  {
		List<FindWordsInfoAppReturn> personWordsList = wordsInfoService.wordsPersonSearch(findWordsInfoPage);
		FindWordsInfoApp findWordsInfoApp = new FindWordsInfoApp();
		findWordsInfoApp.setContent(findWordsInfoPage.getContent());
		findWordsInfoApp.setMerchantNo(findWordsInfoPage.getMerchantNo());
		List<FindWordsInfoAppReturn> commonWordsList = commonWordsInfoService.wordsSearch(findWordsInfoApp);
		commonWordsList.addAll(personWordsList);
		return commonWordsList;
	}
	
	/**
	 * 
	 *
	 * 方法说明：默认话术
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoApp
	 * @return
	 */
	@RequestMapping(value="wordsSelect.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<FindWordsInfoAppReturn> wordsSelect(FindWordsInfoPage findWordsInfoPage)  {
		List<FindWordsInfoAppReturn> list = wordsInfoService.findDefaultWordsH5(findWordsInfoPage);
		return list;
	}
	
	/**
	 * 
	 *
	 * 方法说明：
	 *
	 * @author zlh
	 *   
	 * CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoApp
	 * @return
	 */
	@RequestMapping(value="checkPayPwd.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse checkPwd(String pwd,String noWxGm) {
		try {
			AssertUtils.notNullAndEmpty(pwd,"支付密码不能为空！");
			AssertUtils.notNullAndEmpty(noWxGm,"微信不能为空！");
			FindShopTerminalReturn findShopTerminalReturn= shopTerminalService.findShopTerminalByWx(noWxGm);
			AssertUtils.notNull(findShopTerminalReturn,"终端不存在!");
		//	AssertUtils.notNullAndEmpty(findShopTerminalReturn.getWxPwd(),"终端未维护支付密码!");
			if(findShopTerminalReturn == null || findShopTerminalReturn.getWxPwd() == null
					|| findShopTerminalReturn.getWxPwd().equals("")) {
				return GeneralResponse.generateFailureResponse("", "终端不存在或未维护支付密码!");
			}
			
			String oldPwd = WxPwdEncryptUtils.encrypt(pwd, findShopTerminalReturn.getWorkKey());
			if(!oldPwd.equals(findShopTerminalReturn.getWxPwd())) {
				return GeneralResponse.generateFailureResponse("", "支付密码错误！");
			}
			return GeneralResponse.generateSuccessResponse("", "支付密码正确");
		}catch(Exception e) {
			logger.error("查询支付密码", e);
			return GeneralResponse.generateFailureResponse("", "支付密码错误！");
		}
	}
}
