package com.lj.business.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.mvc.web.util.DateEditor;
import com.lj.base.mvc.web.util.IntegerEditor;
import com.lj.base.mvc.web.util.LongEditor;
import com.lj.business.api.common.ApiAccessConstants;
import com.lj.business.supcon.service.ITokenService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.oms.service.ISystemService;

/**
 * 
 * 
 * 类说明：Action基础类
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 * 
 * CreateDate: 2017年7月1日
 */
public abstract class Action {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	public static final String CURRENT_MEMBER_NO_GM = "currentMemberNoGm";

	public static final String APP_KEY = "appKey";

	public static final String API_TOKENTIMEOUT = "tokenTimeout";
	/** CC配置 敏华电商价格配置 */
	public static final String CC_GROUPNAME_MEC = "mec";
	/** CC配置 敏华电商价格配置门店 */
	public static final String CC_KEY_PRODUCT_PRICE_SHOP_NO = "shop_no_price";
	public static final String CC_SYSTEMALIASNAM_MEC = "mec";

	public static final String PRODUCT_TYPE_NONE = "nothingsale";
	@Autowired
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private ISystemService systemService;

	/**
	 * 获取当前终端微信
	 * 
	 * @param request
	 * @return
	 */
	public String getNoWxGm(HttpServletRequest request) {
		String noWxGm = request.getParameter(ApiAccessConstants.NO_WX_GM);
		return noWxGm;
	}

	/**
	 * 
	 * *方法说明：获取当前登陆用户信息&验证当前token是否过期
	 *
	 * @param token
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年6月28日
	 */
	public String getLoginUserByToken(String token) {
		return tokenService.parseMember(token).getCode();
	}

	/**
	 * 
	 * *方法说明：验证当前用户是否有权限
	 *
	 * @param userid
	 * @param permission
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	public void verifyCurrentPermission(String userid, String permission) {
		AssertUtils.notNullAndEmpty(userid);
		AssertUtils.notNullAndEmpty(permission);

		boolean expression = systemService.verifyMenuPermission(userid, permission);
		AssertUtils.isTrue(expression, "无权限");
	}

	/**
	 * 
	 * *方法说明：验证当前用户是否有权限
	 *
	 * @param token
	 * @param permission
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	public void verifyTokenPermission(String token, String permission) {
		verifyCurrentPermission(getLoginUserByToken(token), permission);
	}

	/**
	 * 
	 *
	 * 方法说明：获取当前登录会员信息
	 *
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 * 
	 * @param request
	 * @return
	 */
//	public FindPersonMemberReturn getCurrentMember(HttpServletRequest request) {
//		Long memberNo = getCurrentMemberNo(request);
//		if (memberNo != null) {
//			return memberQueryService.findPersonMemberByMemeberNo(memberNo);
//		}
//		return null;
//	}

	/**
	 * 
	 *
	 * 方法说明：获取当前登录会员信息、账户信息
	 *
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 * 
	 * @param request
	 * @return
	 */
//	public PersonAccountInfo getCurrentMemberAccount(HttpServletRequest request) {
//		Long memberNo = getCurrentMemberNo(request);
//		if (memberNo != null) {
//			return memberQueryService.findPersonAccountInfoByMemberNo(memberNo);
//		}
//		return null;
//	}

	/**
	 * 
	 *
	 * 方法说明：绑定日期型字符串的处理
	 *
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 * 
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		// binder.registerCustomEditor(ApproveMemberInfo.class, new
		// ApproveMemberInfoEditor());
	}

	/** 获取敏华电商统一商品价格的终端 **/
//	public String getMecProductPriceShopNo() {
//		String shopNo= localCacheSystemParams.getSystemParam(CC_SYSTEMALIASNAM_MEC, CC_GROUPNAME_MEC, CC_KEY_PRODUCT_PRICE_SHOP_NO);
//	}
	/**
	 * 方法说明：获取敏华电商终端商品销售渠道.需求源：宇翠
	 * 
	 * @param shopNo 终端号
	 * @return
	 *
	 * @author lhy CreateDate: 2018年4月25日
	 *
	 */
	public List<String> getMecShopProductTypes(String shopNo) {
		List<String> productTypes = new ArrayList<String>();
		if (StringUtils.isNotEmpty(shopNo)) {
//			FindShop shop = new FindShop();
//			shop.setShopNo(shopNo);
//    		FindShopReturn shopReturn = shopService.findShopByShopNo(shop);
//			if (null == shopReturn) {
//    				throw new TsfaServiceException(ErrorCode.SHOP_NOT_EXIST_ERROR,"终端信息不存在！");
//    		}

//			if (StringUtils.isNotEmpty(shopReturn.getMecShopChannel())) {
//				String[] ptypes = shopReturn.getMecShopChannel().split(",");
//				for (int i = 0; i < ptypes.length; i++) {
//					String s = ptypes[i];
//					if (StringUtils.isNotEmpty(s)) {
//						productTypes.add(s);
//					}
//				}
//			}

			if (productTypes.size() == 0) {
				productTypes.add(PRODUCT_TYPE_NONE);// 终端没有销售任何类型的商品则设置为nothingsale,SQL会无法匹配，不可为空，否则会出逻辑问题
			}
		}
		return productTypes;
	}

	/**
	 * 
	 *
	 * 方法说明：商品分类：场景分类编码
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月3日
	 *
	 */
	public String getScenarioCategory(String productCategory) {
		return localCacheSystemParams.getSystemParam(CC_SYSTEMALIASNAM_MEC, "productCategory", productCategory);
	}

	/**
	 * 获取客户端IP
	 * 
	 * @param request
	 * @return
	 */
	public String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		String[] ipArrays = ip.split(",");
		if (ipArrays != null && ipArrays.length > 1) {
			return ipArrays[0];
		} else {
			return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
		}
	}
}
