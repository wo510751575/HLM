package com.lj.business.api.controller;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.lj.base.core.encryption.MD5;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.common.ApiAccessConstants;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.spring.ApiHttpServletRequest;
import com.lj.business.api.spring.ApiMultipartHttpServletRequest;
import com.lj.business.supcon.dto.token.MemberLoginCache;
import com.lj.business.supcon.dto.token.Token;
import com.lj.business.supcon.service.ITokenService;
import com.lj.distributecache.RedisCacheConfigFromCC;

/**
 * 
 * 
 * 类说明：过滤器，未登录不允许访问未经授权的URL
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
@Component
public class SecurityCheckFilter extends OncePerRequestFilter {

	private static Logger logger = LoggerFactory.getLogger(SecurityCheckFilter.class);

	// 允许匿名访问的请求 // WIFI在内的所有请求都可匿名访问
	public static final String [] ALLOW_ANONYMOUS_LIST = {"/noAuthorized.do", "/login.do", "/logout.do", "/refreshToken.do", "/sms/", "/checkVersion.do",
		"/member/personRegister.do", "/member/resetLoginPassword.do", "/member/existMemberByMobile.do", "/member/headImage.do",
		"appTest.do","appLogin.do","h5Login.do","zkLogin.do", "/personal/getValideCode.do", "/personal/updatePwd.do", "/im/chat/", "/recruit/","/h5/","/api/area",
        "/iem/member/register.do", "/iem/member/getValideCode.do", "/iem/member/login.do", "/iem/member/gzhLogin.do", "/iem/member/iemRegisterQuery.do", "iem/goods/",
        "/iem/advert/list.do", "/iem/wx/", "/iem/evaluation/goodsEvaluations.do", "/iem/shoppingcar/goodsCount.do","/h5/selectZKQcode.do","/h5/selectJudePhone.do",
        "/h5/toSelectJudePhonePage.do","/hx/login.do"
        , "/rw/article/list.do", "/rw/article/info.do", "/rw/articleType/list.do"
        , "/ad/advertise/list.do", "/ad/advertise/view.do", "/ad/advertiseType/list.do", "/ad/carousel/list.do"
        , "/rwUser/rwLogin.do", "/rwUser/rwRegistered.do", "/rwUser/rwSendSms.do", "/rwUser/rwEditPwd.do"
        ,"/hx/ps/apply.do","/hx/ps/confirm.do","/hx/ps/info.do","/hx/guid/list.do","/hx/configList.do"
	};

	public static final String[] NO_SECURITY_CHECKFILTER = {"/member/findGuidCard.do","/member/guidCardAddNumType.do","/member/findGuidCards.do","findImToken.do",
		"delGuidCardSave.do","getSignature.do", "/im/chat/uploadFileFromWeb.do", "/weixin/toWxAuth.do","/product/hotKeyword.do"
		, "/read/addReadDetail.do","/recruit/questionss.do","/member/forecastName/","/mecMember/iem","/member/iem","/fitUpInfo/", "/dealerapply/", "/mecupload/"
		,"/uploadWorkflowFiles.do","/member/h5SupplyMemberInfo","/h5/selectZKQcode.do","/h5/selectJudePhone.do","/h5/toSelectJudePhonePage.do","/firend/addFriendsTaskFailNotify.do"
		,"/im/index","/member/h5Login.do","/upload/uploadHeadImage.do","/servlet/validateCodeServlet"
		};

	// 不需要遵循业务框架提交参数格式的请求
	public static final String [] SPECIAL_LIST = {"/weixin/","/wx/","/mec/amy/", "/im/chat/uploadFilesFromApp.do", "/im/chat/uploadFileNew.do", "/iem/order/callbackByXml/", "/iem/weixin/","/h5/selectZKQcode.do",
			"/h5/selectJudePhone.do","/h5/toSelectJudePhonePage.do","/firend/addFriendsTaskFailNotify.do"};


	@Resource 
	private RedisCacheConfigFromCC redisCache;
	@Autowired
	private ITokenService tokenService;
	@Resource
	private CommonsMultipartResolver multipartResolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { 
		
		/*logger.info(System.getProperty("user.dir") );
		logger.info( this.getClass().getClassLoader().getResource("")+"");
		logger.info( this.getClass().getClassLoader().getResource("/")+"");
		logger.info(this.getClass().getClassLoader().getResource("META-INF/plexus/")+"");
		Enumeration<URL> e  = this.getClass().getClassLoader().getResources("META-INF/plexus/");
		while(e.hasMoreElements())
		{
				URL url = e.nextElement();
				logger.info("url --->",url);
		}*/
		String requestUrl = request.getRequestURI();
		// 只过滤.do和.jsp路径的请求
		if(!requestUrl.endsWith(".do") && !requestUrl.endsWith(".jsp")) {
			filterChain.doFilter(request, response);
			return;
		}
		// 判断不需要遵循业务框架提交参数格式的请求
		for(String allowedUrl : SPECIAL_LIST) {
			if(requestUrl.contains(allowedUrl)) {
				logger.info("不需要遵循业务框架提交参数格式的请求--------->{}",requestUrl);
				filterChain.doFilter(request, response);
				return;
			}
		}

		// 上传文件加普通表单
		if(multipartResolver.isMultipart(request)) {
			this.doMultipart(request, response, filterChain);
		} else {	// 普通表单请求
			this.doForm(request, response, filterChain);
		}
	}

	public void doMultipart(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		ApiMultipartHttpServletRequest apiRequest = new ApiMultipartHttpServletRequest((DefaultMultipartHttpServletRequest) multipartResolver.resolveMultipart(request));

		String noWxGm = apiRequest.getParameter(ApiAccessConstants.NO_WX_GM);					// 终端微信
		String memberNoGm = apiRequest.getParameter(ApiAccessConstants.MEMBER_NO_GM);					// 导购号
		String timestamp = apiRequest.getParameter(ApiAccessConstants.TIMESTAMP);		// 时间戳
		String appKey = apiRequest.getParameter(ApiAccessConstants.APP_KEY);			// 接入应用KEY
		String token = apiRequest.getParameter(ApiAccessConstants.TOKEN);									// token令牌
		String paramJson = StringUtils.toString(apiRequest.getParameter(ApiAccessConstants.PARAM_JSON));	// 业务参数JSON字符串
		String signature = apiRequest.getParameter(ApiAccessConstants.SIGNATURE);							// 签名
		String requestUrl = request.getRequestURI();		

		// 请求路径
		Map<String, String[]> params = null;
		try {
			params = this.authorized(requestUrl, appKey, timestamp, token, noWxGm,memberNoGm, paramJson, signature, request);
		} catch(TsfaServiceException e) {
			logger.error(e.getExceptionInfo(), e);
			// 错误返回，不再调用其他Filter
			request.getRequestDispatcher("/noAuthorized.do").forward(new ApiMultipartHttpServletRequest(request, e), response);
			return;
		} catch(Throwable t) {
			logger.error(t.getMessage(), t);
			// 错误返回，不再调用其他Filter
			request.getRequestDispatcher("/noAuthorized.do").forward(new ApiMultipartHttpServletRequest(request, t), response);
			return;
		}
		apiRequest.setParameters(params);

		// 转发给其他Filter
		filterChain.doFilter(apiRequest, response);
	}

	public void doForm(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		// 将paramJson业务参数JSON字符串转换为map
		ApiHttpServletRequest apiRequest = new ApiHttpServletRequest(request);

		String noWxGm = apiRequest.getParameter(ApiAccessConstants.NO_WX_GM);
		String memberNoGm = apiRequest.getParameter(ApiAccessConstants.MEMBER_NO_GM);
		String appKey = apiRequest.getParameter(ApiAccessConstants.APP_KEY);								// 接入应用KEY
		String timestamp = apiRequest.getParameter(ApiAccessConstants.TIMESTAMP);							// 时间戳
		String token = apiRequest.getParameter(ApiAccessConstants.TOKEN);									// token令牌
		String paramJson = StringUtils.toString(apiRequest.getParameter(ApiAccessConstants.PARAM_JSON));	// 业务参数JSON字符串
		String signature = apiRequest.getParameter(ApiAccessConstants.SIGNATURE);							// 签名

		String requestUrl = request.getRequestURI();														// 请求路径
		Map<String, String[]> params = null;
		try {
			params = this.authorized(requestUrl, appKey, timestamp, token, noWxGm,memberNoGm, paramJson, signature, request);
		} catch(TsfaServiceException e) {
			logger.error(e.getExceptionInfo(), e);
			// 错误返回，不再调用其他Filter
			request.getRequestDispatcher("/noAuthorized.do").forward(new ApiHttpServletRequest(request, e), response);
			return;
		} catch(Throwable t) {
			logger.error(t.getMessage(), t);
			// 错误返回，不再调用其他Filter
			request.getRequestDispatcher("/noAuthorized.do").forward(new ApiHttpServletRequest(request, t), response);
			return;
		}
		apiRequest.setParameters(params);

		// 转发给其他Filter
		filterChain.doFilter(apiRequest, response);
	}

	/**
	 * 
	 *
	 * 方法说明：业务前置检查
	 * 
	 * appKey    :   应用KEY
	 * timestamp  :   时间戳
	 * noWxGm    :   终端微信
	 * paramJson  :	  业务参数明文，JSON格式字符串
	 * signature   :   对paramJson进行签名（MD5Salt/SHA1/SHA256，服务端设置盐值，客户端保存）
	 * token  : 可选，登陆后由服务器返回
	 * 前置检查步骤：
	 * 	1、检查时间戳是否有效；
	 * 	2、通过token和appKey检查访问超时，为兼容以往版本（没有token），只有当appKey不为空时才检查token；
	 * 	3、校验签名。
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param requestUrl
	 * @param appKey
	 * @param timestamp
	 * @param token
	 * @param imei
	 * @param paramJson
	 * @param signature
	 * @param request
	 * @return	解析后附加参数
	 */
	public Map<String, String[]> authorized(String requestUrl, String appKey, String timestamp, String token, String noWxGm,String memberNoGm, String paramJson, String signature, HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append("HttpRequest： requestUrl=").append(requestUrl);
		builder.append(", appKey=").append(appKey);
		builder.append(", timestamp=").append(timestamp);
		builder.append(", token=").append(token);
		builder.append(", noWxGm=").append(noWxGm);
		builder.append(", memberNoGm=").append(memberNoGm);
		builder.append(", paramJson=").append(paramJson);
		builder.append(", signature=").append(signature);
		logger.info(builder.toString());

		for (String noSecurityUrl : NO_SECURITY_CHECKFILTER) {
			if (requestUrl.contains(noSecurityUrl)) {
				return new TreeMap<String, String[]>();
			}
		}
		// 1、检查时间戳是否有效
		if(StringUtils.isEmpty(timestamp)) {
			throw new TsfaServiceException(ErrorCode.TIMESTAMP_IS_EMPTY, "客户端时间戳为空");
		}
		// 客户端时间戳比服务端相比超过10分钟，视为错误的时间戳 //XXX LEOPENG 测试暂时修改
		/*if(Math.abs(Long.valueOf(timestamp) - System.currentTimeMillis()) > 600000) {
			throw new TsfaServiceException(ErrorCode.TIMESTAMP_ERROR, "客户端时间戳错误");
		}*/

		// 4、校验token
		boolean allowedBool = Boolean.FALSE;	// 当前路径可匿名访问，即不需要校验token就可访问
		// 可匿名访问的路径
		for(String allowedUrl : ALLOW_ANONYMOUS_LIST){
			if(requestUrl.contains(allowedUrl))	// 访问路径在范围内
				allowedBool = Boolean.TRUE;
		}
		// 当前路径必须登录才可访问，校验token
		MemberLoginCache memberCache = null;	// 当前登录会员信息
		if(!allowedBool && StringUtils.isNotEmpty(appKey)) {	// 为兼容旧版本APP，只当appKey不为空时才校验token
			if(StringUtils.isEmpty(token)) {	// 非匿名访问路径须提交token
				throw new TsfaServiceException(ErrorCode.TOKEN_IS_EMPTY, "token为空");
			}
			memberCache = tokenService.parseMember(token, Token.TOKEN_TIMEOUT_SECONDS);
		}

		// 5、校验签名
		if(StringUtils.isNotEmpty(signature)) {
			String encryptParamJson = null;	// 业务参数加密后字符串
			try	{ 
				encryptParamJson = MD5.encryptByMD5Twice(paramJson, timestamp + "013cXuH9vf584W0x");
			} catch(TsfaServiceException e) {
				throw e;
			} catch(Exception e) {
				throw new TsfaServiceException(ErrorCode.SIGNATURE_ERROR, "验签加密异常：", e);
			}
			// 验签
			if(!signature.equals(encryptParamJson)) {
				throw new TsfaServiceException(ErrorCode.SIGNATURE_ERROR, "签名错误");
			}
		} else {
			throw new TsfaServiceException(ErrorCode.SIGNATURE_ERROR, "签名错误，签名字符串signature不能为空");
		}

		// 6、解析后参数
		Map<String, String[]> params = new TreeMap<String, String[]>();
		if(memberCache != null && memberCache.getCode() != null) {	// 设置当前导购编号
			params.put(Action.CURRENT_MEMBER_NO_GM, new String[]{memberCache.getCode().toString()});
		}

		return params;
	} 
}