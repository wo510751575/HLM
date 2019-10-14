package com.lj.oms.sys;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ape.common.config.Global;
import com.ape.common.servlet.ValidateCodeServlet;
import com.ape.common.utils.CacheUtils;
import com.ape.common.utils.IdGen;
import com.ape.common.utils.StringUtils;
import com.google.common.collect.Maps;
import com.lj.base.exception.TsfaServiceException;
import com.lj.cc.service.ISystemInfoService;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.User;
import com.lj.oms.security.FormAuthenticationFilter;
import com.lj.oms.security.SystemAuthorizingRealm.Principal;
import com.lj.oms.security.UsernamePasswordToken;
import com.lj.oms.service.impl.UserCacheService;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.service.sys.SystemService;
import com.lj.oms.utils.UserUtils;

/**
 * 登录Controller
 */
@Controller
public class LoginController extends BaseController{
	
//	@Autowired
//	private SessionRedisDao sessionDAO;
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private  com.lj.distributecache.RedisCache redisCache;
	@Autowired
	private UserCacheService userCacheService;
	/** * 系统信息服务. */
	@Autowired
	private ISystemInfoService systemInfo;
	
	private static final String LOGIN_PAGE="modules/sys/sysLogin";
	private static final String REDIRECT_INDEX="redirect:" +Global.getAdminPath() ;
	private static final int MAX_LOGIN_FAIL_NUM=3;
	public static final String USER_CACHE = "userCache";
	public static final String CACHE_MENU_LIST = "menuList";
	/**
	 * 管理登录
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		if (logger.isDebugEnabled()){
			//logger.debug("login, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		// 如果已经登录，则跳转到管理首页
		if(principal != null){
			return REDIRECT_INDEX;
		}
		return LOGIN_PAGE;
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
	public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		// 如果已经登录，则跳转到管理首页
		if(principal != null){
			return REDIRECT_INDEX;
		}

		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
		String exception = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		
		if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")){
			message = "用户或密码错误, 请重试.";
		}

		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
		
		if (logger.isDebugEnabled()){
			//logger.debug("login fail, active session size: {}, message: {}, exception: {}", 
				//	sessionDAO.getActiveSessions(false).size(), message, exception);
		}
		
		// 非授权异常，登录失败，验证码加1。
		if (!UnauthorizedException.class.getName().equals(exception)){
			model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
		}
		
		// 验证失败清空验证码
		request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());
		
		return LOGIN_PAGE;
	}
	
	/**
	 * 更改主题
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "${adminPath}/changeLayout")
	public String changeLayout(HttpServletRequest request, HttpServletResponse response, Model model,String layout) {
		User user=UserUtils.getUser();
		user.setLayout(layout);
		systemService.updateUserInfo(user);
		return REDIRECT_INDEX;
	}
	
	/**
	 * 更改语言
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "${adminPath}/changeLang")
	public String changeLang(HttpServletRequest request, HttpServletResponse response, Model model,String id) {

		return REDIRECT_INDEX;
	}

	/**
	 * 登录成功，进入管理首页
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		List<Office> list=officeService.findIdList();
		
		//清除角色菜单缓存
		CacheUtils.remove(USER_CACHE, CACHE_MENU_LIST + UserUtils.getUser().getId());

		// 登录成功后，验证码计算器清零
		isValidateCodeLogin(principal.getLoginName(), false, true);
		if (logger.isDebugEnabled()){
		//	logger.debug("show index, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		model.addAttribute("list", list);
		model.addAttribute("user_photo", UserUtils.getUser().getPhoto());
		model.addAttribute("layout", UserUtils.getUser().getLayout());
		Office office= officeService.get(UserUtils.getUser().getCompany());
		if(office!=null && StringUtils.isNotBlank(office.getLogo())){
			model.addAttribute("logo", office.getLogo());
		}
		
		if(UserUtils.getUser().getCompany()!=null){
			Map<String,Object> map=new HashMap<>();
			map.put("merchantNo", UserUtils.getUser().getCompany().getId());
//			map.put("shopNos", UserUtils.getUser().getShopNos());
			model.addAttribute("user",map);
		}
       
		return "modules/sys/sysIndex";
	}
	
	/**
	 * 是否是验证码登录
	 * @param useruame 用户名
	 * @param isFail 计数加1
	 * @param clean 计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
		Map<String, Integer> loginFailMap = (Map<String, Integer>)CacheUtils.get("loginFailMap");
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean){
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= MAX_LOGIN_FAIL_NUM;
	}
	
	/**
	 * 方法说明：token免密登录.
	 *
	 * @param token ：token.
	 * @param model
	 * @return
	 *
	 * @author lhy CreateDate: 2018年1月29日
	 *
	 */
	@RequestMapping(value = "${adminPath}/token/login")
	public String tokenLogin(String token,String url, Model model){
		logger.info("tokenLogin(token={}) - start", token);
		// 1.根据token找到登录用户
		User user = userCacheService.getUserByToken(token);
		if (null == user) {
			logger.error("tokenLogin(token={}) ,token not exist.", token);
			throw new TsfaServiceException("unkonw token", "token无效");
		}
		// 2.登录
		UsernamePasswordToken t = new UsernamePasswordToken(UsernamePasswordToken.LOGIN_TYPE_TOKEN);
		t.setUsername(user.getLoginName());
		t.setPassword(user.getId().toCharArray());
		SecurityUtils.getSubject().login(t);
		logger.info("tokenLogin(token={}) - end");
		
		String rtUrl = REDIRECT_INDEX;
		if(StringUtils.isEmpty(url)) {
			rtUrl= REDIRECT_INDEX;
		}else {
			try {
				url = URLDecoder.decode(url,  "UTF-8");
				if(!url.startsWith("http")) {
					url = Global.getAdminPath() + url;//oms的相对路径则拼接
				} 
			} catch (UnsupportedEncodingException e) {
				logger.error("拼接字符串失败", e);
			}
			rtUrl= "redirect:" + url;
		}
		return rtUrl;
	}
	 
}
