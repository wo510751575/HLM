package com.lj.business.api.controller.hx;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.servlet.ValidateCodeServlet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.PersonMemberLogin;
import com.lj.business.member.dto.PersonMemberLoginReturn;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMemberLoginService;
import com.lj.business.supcon.dto.token.MemberLoginCache;
import com.lj.business.supcon.dto.token.Token;
import com.lj.business.supcon.service.ITokenService;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByTerminal;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.entity.sys.Menu;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.Role;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.AreaHessianService;
import com.lj.oms.service.OfficeHessianService;
import com.lj.oms.service.RoleHessianService;
import com.lj.oms.service.UserHessianService;
import com.ye.business.hx.constant.HxConstant;
import com.ye.business.hx.dto.FindShopConfigPage;
import com.ye.business.hx.dto.ShopConfigDto;
import com.ye.business.hx.service.IGuidScheduleLogService;
import com.ye.business.hx.service.IShopConfigService;

/**
 * 焕新公用接口。
 * 
 * @author 刘红艳
 *
 */
@Controller
@RequestMapping(value = "/hx/")
public class HxIndexAction extends Action {

	 
	@Resource
	private ITokenService tokenService;
//	@Autowired//	private RedisCache redisCache; // 缓存

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
	private IGuidMemberService guidMemberService; // 导购服务
	@Autowired
	private UserHessianService userHessianService;
	@Resource
	private AreaHessianService areaHessianService; // 地区服务
	@Resource
	private IGuidScheduleLogService guidScheduleLogService;
	@Autowired
	private IShopConfigService shopConfigService;
 
	@Resource
	private OfficeHessianService officeHessianService; // 商户服务
	
	@Autowired
	private RoleHessianService roleHessianService;
	
	@Autowired
	private IGmAssistantShopService gmAssistantShopService;
	
	@Resource
	public IImChatInfoService imChatInfoService;
	
	@Resource
	public IMemberLoginService memberLoginService;
	
	/**
	 * 好乐美门诊登录。
	 * @param personMemberLogin 用户名及密码非空
	 * @param captcha 验证码 非空
	 * @return
	 */
	@RequestMapping(value = "/login.do")
	@ResponseBody
	public Map<Object, Object> loginByUsername(HttpServletRequest request,PersonMemberLogin personMemberLogin,String captcha){
		logger.debug("loginByUsername(PersonMemberLogin personMemberLogin={}) - start", personMemberLogin);
		
		String code = (String)request.getSession().getAttribute(ValidateCodeServlet.VALIDATE_CODE);
		if (captcha == null || !captcha.toUpperCase().equals(code)){
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR,"验证码错误！");
		}
		personMemberLogin.setCheckGmAssistant(false);//非必須有导购助手
		PersonMemberLoginReturn loginReturn = memberLoginService.personMemberLoginAPP(personMemberLogin);
		// 生成令牌
		Token token = tokenService.generateToken(loginReturn.getMemberNoGuid(), "HLM_H5",
				Token.TOKEN_TIMEOUT_SECONDS);
		loginReturn.setToken(token.getAccessToken());
		
		//2.设置门诊信息
		setHLMLoginInfo(loginReturn, loginReturn.getMemberNoMerchant());
		
		logger.debug("loginByUsername(PersonMemberLoginReturn={}) - end", loginReturn); 
		Map<Object, Object> data = new LinkedHashMap<>();
		data.putAll(new BeanMap(loginReturn));
		//OMS退出登录
		String omsTokenLoginUrl=localCacheSystemParams.getSystemParam("api-os", "oms.token", "loginUrl");
		String omsLogoutUrl=omsTokenLoginUrl.replace("/token/login", "/logout");
		data.put("omsLogoutUrl", omsLogoutUrl);
		
		return data;
	}
	
	
	/**
	 * 方法说明：oms 免密登录及跳转到指定页面。
	 *
	 * @param url 免密登录后跳转的地址 
	 * @param model
	 * @return 返回拼接好的OMS地址
	 *
	 * @author lhy CreateDate: 2019.04.02
	 *
	 */
	@RequestMapping(value = "/oms/login.do")
	@ResponseBody
	public String omsNoPwdLogin(String memberNoGuid ,String url, Model model){
		AssertUtils.notNullAndEmpty(memberNoGuid, "memberNoGuid不能为空");
		logger.info("omsNoPwdLogin(url={}) - start",url);
		String omsTokenLoginUrl=localCacheSystemParams.getSystemParam("api-os", "oms.token", "loginUrl");
		logger.info("omsTokenLoginUrl:" + omsTokenLoginUrl);
		User user=new User();
		user.setId(memberNoGuid);
		String token=userHessianService.createOmsToken(user);
		String redirectUrl=null;
		if(StringUtils.isEmpty(url)) {
			redirectUrl=omsTokenLoginUrl+"?token=" + token;
		}else {
			try {
				//加码过去
				redirectUrl=omsTokenLoginUrl+"?token=" + token+"&url="+URLEncoder.encode(url, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("拼接字符串失败", e);
				throw new RuntimeException("拼接字符串失败", e);
			};
		}
		return redirectUrl;
		//return new ModelAndView(new RedirectView(redirectUrl));考虑到签名等原因不直接使用get跳转
	}
	
	
	/**
	 * 使用token登录
	 * 
	 * @param token
	 * @return
	 * @throws JsonProcessingException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "tokenLogin.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<Object, Object> tokenLogin(String token) throws JsonProcessingException, UnsupportedEncodingException {
		AssertUtils.notNullAndEmpty(token, "token不能为空");
		token = URLDecoder.decode(token, "UTF-8");
		MemberLoginCache memberLoginCache = tokenService.parseMember(token, Token.TOKEN_TIMEOUT_SECONDS);

		String memberNoGm = memberLoginCache.getCode();
		FindGuidMember gmQuery = new FindGuidMember();
		gmQuery.setMemberNo(memberNoGm);
		FindGuidMemberReturn guidMember = guidMemberService.findGuidMember(gmQuery);
		Map<Object, Object> data = new LinkedHashMap<>();
		// 导购则返回导购相关信息
		if (guidMember != null) {
			PersonMemberLoginReturn personMemberLoginReturn = buildPersonMemberLoginReturn(guidMember);
			personMemberLoginReturn.setToken(token);
			data.putAll(new BeanMap(personMemberLoginReturn));
		}else {
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_NOT_EXIST_ERROR,"非门诊员工！"); 
		}
		// 换新系统非导购不可使用
//		User user = getUserByCache(memberNoGm);
//		// 返回管理员用户信息
//		data.put("admin", user);
//		data.put("company", user.getCompany());
		//OMS退出登录
		String omsTokenLoginUrl=localCacheSystemParams.getSystemParam("api-os", "oms.token", "loginUrl");
		String omsLogoutUrl=omsTokenLoginUrl.replace("/token/login", "/logout");
		data.put("omsLogoutUrl", omsLogoutUrl);
		logger.info("tokenLogin data:{}", data);
		return data;
	}
	

	/**
	 * 方法说明：缓存获取用户登录信息
	 * 
	 * @param userId
	 * @return
	 * @author 李端强 CreateDate: 2018年1月29日
	 *
	
	private User getUserByCache(String userId) {
		AssertUtils.notNullAndEmpty(userId, "登录用户ID不能为空");
		User user = null;
		user = userHessianService.findByUserId(userId);
		return user;
	} */

	/**
	 * 组装返回数据
	 * 
	 * @param guidMember
	 * @return
	 */
	private PersonMemberLoginReturn buildPersonMemberLoginReturn(FindGuidMemberReturn guidMember) {
		PersonMemberLoginReturn personMemberLoginReturn;
		personMemberLoginReturn = new PersonMemberLoginReturn();
		personMemberLoginReturn.setCode(guidMember.getCode());
		 
		personMemberLoginReturn.setMemberNoGuid(guidMember.getMemberNo());
		personMemberLoginReturn.setMemberNameGuid(guidMember.getMemberName());
		personMemberLoginReturn.setMobile(guidMember.getMobile());
		personMemberLoginReturn.setEmail(guidMember.getEmail());
		personMemberLoginReturn.setAreaCode(guidMember.getAreaCode());
		personMemberLoginReturn.setHeadAddress(guidMember.getHeadAddress());
		personMemberLoginReturn.setGender(guidMember.getGender());
		personMemberLoginReturn.setMemberNoMerchant(guidMember.getMerchantNo());
		personMemberLoginReturn.setMemberNameMerchant(guidMember.getMerchantName());
		personMemberLoginReturn.setStatus(guidMember.getStatus());
//		personMemberLoginReturn.setShopName(guidMember.getMerchantName());//门店被砍
//		personMemberLoginReturn.setNoWx(guidMember.getNoWx());焕新H5不需要终端微信，APP&IM才需要，且要从导购助手拿
		// 获取导购端需要连接的中控服务地址
//		personMemberLoginReturn.setNettyAddress(getNettyUrlByZK(guidMember.getNoWx(), null));
		
		String uploadUrl = localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),
				SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
		personMemberLoginReturn.setUploadUrl(uploadUrl);

		//2.设置门诊信息
		setHLMLoginInfo(personMemberLoginReturn, guidMember.getMerchantNo());
		
		return personMemberLoginReturn;
	}

	/**
	 * 设置门诊登录信息
	 * @param personMemberLoginReturn
	 * @param merchantNo
	 */
	private void setHLMLoginInfo(PersonMemberLoginReturn personMemberLoginReturn,String merchantNo) {

		/**找出门诊地址*/
		Office office=officeHessianService.findOfficeByMerchantNo(merchantNo);
		// 查询门诊LOGO
		personMemberLoginReturn.setShopLogoAddr(office.getLogo());
		
		String areaIds=office.getArea().getParentIds()+office.getArea().getId();
	    List<String> list = new ArrayList<String>();
        list = Arrays.asList(areaIds.split(","));
        logger.info("areaIds:{}",areaIds);
		List<Area> areas = areaHessianService.findAreaListByIds(list);
		for (Iterator<Area> iterator = areas.iterator(); iterator.hasNext();) {
			Area area =  iterator.next();
			if(Area.TYPE_PRIVINCE.equals(area.getType())){
				personMemberLoginReturn.setProvinceCode(area.getId());
				personMemberLoginReturn.setProvinceName(area.getName());
			}
			if(Area.TYPE_CITY.equals(area.getType())){
				personMemberLoginReturn.setCityCode(area.getId());
				personMemberLoginReturn.setCityName(area.getName());
			}
			if(Area.TYPE_REGION.equals(area.getType())){
				personMemberLoginReturn.setRegionCode(area.getId());
				personMemberLoginReturn.setRegionName(area.getName());
			}
		}/**找出门诊地址 END */
		
		
		/*** 获取所属角色 */
		List<Role> roles =roleHessianService.findByUserId(personMemberLoginReturn.getMemberNoGuid());
		StringBuffer roleNname = new StringBuffer();
		StringBuffer roleEnnames = new StringBuffer();
		if(roles.size()>0) {
			roleNname.append(roles.get(0).getName());
			roleEnnames.append(roles.get(0).getEnname());
			for (int i = 1; i < roles.size(); i++) {
				roleEnnames.append(",");
				roleNname.append(",");
				roleNname.append(roles.get(i).getName());
				roleEnnames.append(roles.get(i).getEnname());
			}
		} 
		personMemberLoginReturn.setRoleEnnames(roleEnnames.toString());
		personMemberLoginReturn.setRoleName(roleNname.toString());
		/*** 获取所属角色END */
	}
	
	/**
	 * netty 根据中控登录的地址获取导购登录的地址（集群环境下中控和导购必须登录同一台机子）
	 * 
	 
	private String getNettyUrlByZK(String gmNoWx, String version) {
		// 记录中控登录地址
		return redisCache.get(MemberAction.REDISNETTYKEY + gmNoWx);
	}*/
	
	/**
	 * 查询用户的权限
	 * @param memberNoGuid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"getMenu.do"})
	public List<Menu> findMenuList(String memberNoGuid){
		AssertUtils.notNullAndEmpty(memberNoGuid,"员工编号不能为空！");
		return userHessianService.findMenuList(memberNoGuid);
	}
	
	
	/**
	 * 查询门诊的地址
	 * @param merchantNo 商户号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"getMerchantArea.do"})
	public Map<String, String> findMerchantArea(String merchantNo){
		AssertUtils.notNullAndEmpty(merchantNo, "商户号不能为空");
		/**找出门诊地址*/
		Office office=officeHessianService.findOfficeByMerchantNo(merchantNo);
		if(null ==office) {
			throw new TsfaServiceException(ErrorCode.MERCHANT_NOT_EXIST_ERROR, "商户不存在");
		}
		// 查询门诊LOGO
		Map<String, String> data = new HashMap<>();
		String areaIds=office.getArea().getParentIds()+office.getArea().getId();
	    List<String> list = new ArrayList<String>();
        list = Arrays.asList(areaIds.split(","));
        logger.info("areaIds:{}",areaIds);
		List<Area> areas = areaHessianService.findAreaListByIds(list);
		for (Iterator<Area> iterator = areas.iterator(); iterator.hasNext();) {
			Area area =  iterator.next();
			if(Area.TYPE_PRIVINCE.equals(area.getType())){
				data.put("provinceCode", area.getId());
				data.put("provinceName", area.getName());
			}
			if(Area.TYPE_CITY.equals(area.getType())){
				data.put("cityCode", area.getId());
				data.put("cityName", area.getName());
			}
			if(Area.TYPE_REGION.equals(area.getType())){
				data.put("regionCode", area.getId());
				data.put("regionName", area.getName());
			}
		}/**找出门诊地址 END */
		
		return data;
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
	 * 选项列表。
	 * @param configDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"configList.do"})
	public List<ShopConfigDto> findShopConfig(ShopConfigDto configDto){
//		AssertUtils.notNullAndEmpty(configDto.getShopNo(),"门诊号不能为空！");
		AssertUtils.notNullAndEmpty(configDto.getMerchantNo(),"商户号不能为空！");
		if(StringUtils.isEmpty(configDto.getParentCode()) && StringUtils.isEmpty(configDto.getLableNo())) {
			throw new TsfaServiceException(com.lj.business.api.common.ErrorCode.PARAM_ERROR,
					"parentCode或lableNo必须有一个非空！");
		}
		List<ShopConfigDto> datas= null;
		//客户类型
		if(HxConstant.CONFIG_LABLE_NO_USER_TYPE.equals(configDto.getLableNo())) {
			ShopConfigDto  findConfig=new ShopConfigDto();
			//根据类别查父级CODE
			ShopConfigDto param = new ShopConfigDto();
			param.setMerchantNo(Office.ROOT_ID);
			param.setLableNo(HxConstant.CONFIG_LABLE_NO_USER_TYPE);
			
			FindShopConfigPage findShopConfigPage=new FindShopConfigPage();
			findShopConfigPage.setParam(param);
			
			List<ShopConfigDto> parentDtos= shopConfigService.findShopConfigs(findShopConfigPage);
			if (parentDtos != null && parentDtos.size() > 0) {
				findConfig.setParentCode(parentDtos.get(0).getCode());
			
				FindShopConfigPage findPage=new FindShopConfigPage();
				findPage.setParam(findConfig);
				findConfig.setMerchantNo(Office.ROOT_ID);
				findConfig.setShopNo(null);
				datas= shopConfigService.findShopConfigs(findPage);
			}else {
				return new ArrayList<>();// 没找到则返空集合
			} 
		}else {//非客户类型查询
			ShopConfigDto  findConfig=new ShopConfigDto();
			//根据类别查父级CODE
			if(configDto.getLableNo()!=null && StringUtils.isEmpty(configDto.getParentCode())) {
				ShopConfigDto param = new ShopConfigDto();
				param.setMerchantNo(Office.ROOT_ID);
				param.setLableNo(configDto.getLableNo());
				
				FindShopConfigPage findShopConfigPage=new FindShopConfigPage();
				findShopConfigPage.setParam(param);
				
				List<ShopConfigDto> parentDtos= shopConfigService.findShopConfigs(findShopConfigPage);
				if (parentDtos != null && parentDtos.size() > 0) {
					findConfig.setParentCode(parentDtos.get(0).getCode());
				} else {
					return new ArrayList<>();// 没找到则返空集合
				}
			}else {
				findConfig.setParentCode(configDto.getParentCode());
			}
			//1.先从门诊查
			findConfig.setMerchantNo(configDto.getMerchantNo());
			findConfig.setShopNo(configDto.getShopNo());
			
			FindShopConfigPage findPage=new FindShopConfigPage();
			findPage.setParam(findConfig);
			datas= shopConfigService.findShopConfigs(findPage);
			
			//2.没有查到则从ROOT的配置查
			if (datas == null || datas.size() <= 0) {
				findConfig.setMerchantNo(Office.ROOT_ID);
				findConfig.setShopNo(null);
				 datas= shopConfigService.findShopConfigs(findPage);
			}
			//3.查的是预约项目，则把二级同步查出去
			if(StringUtils.isEmpty(configDto.getParentCode()) && HxConstant.CONFIG_LABLE_NO_PATIENT_PROJECT.equals(configDto.getLableNo())) {
				datas.forEach(action -> {
					findConfig.setParentCode(action.getCode());
					List<ShopConfigDto>  childs= shopConfigService.findShopConfigs(findPage);
					action.setChilds(childs);
				});
				
			}
		}
		
		return datas;
	}
	
	
	/**
	 * IM未读总数。
	 * @param merchantNo  
	 * @param memberNoGuid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"im/unreadCount.do"})
	public Integer findWxUnreadPersonCount(String merchantNo,String memberNoGuid ) {
		AssertUtils.notNullAndEmpty(memberNoGuid,"员工编号不能为空！");
		AssertUtils.notNullAndEmpty(merchantNo,"商户编号不能为空！");
		//查询现有导购助手列表
		String noWxList = gmAssistantShopService.findGroupConcatByAssNo(memberNoGuid);
		int rtCount = 0;
		if (StringUtils.isNotEmpty(noWxList)) {
			String[] noWxs = noWxList.split(",");
			List<String> list = new ArrayList<>();
			for (String string : noWxs) {
				list.add(string);
			}
			FindUnreadCountByTerminal findUnreadCountByTerminal = new FindUnreadCountByTerminal();
			findUnreadCountByTerminal.setNoWxList(list);
			findUnreadCountByTerminal.setMerchantNo(merchantNo);
			rtCount= imChatInfoService.findUnreadPersonCount(findUnreadCountByTerminal);
		}
		return rtCount;
	}
}
