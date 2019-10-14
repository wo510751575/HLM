package com.lj.business.api.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.lj.base.core.util.StringUtils;
import com.lj.business.api.common.ApiAccessConstants;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.domain.MobileVersion;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.supcon.dto.token.Token;
import com.lj.business.supcon.service.ITokenService;
import com.lj.business.utils.PaymentVersionUtils;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;

/**
 * 
 * 
 * 类说明：公共业务处理
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
@Controller
public class IndexAction extends Action { 

	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Resource
	private ITokenService tokenService;
	
	/**
	 * 
	 *
	 * 方法说明：检查客户端版本
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param clientVersion
	 * @param clientOS
	 * @param appName			APP名称,如美容美发为hc,为空时则默认为聚客
	 * @return
	 */
	@RequestMapping(value="/checkVersion.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public MobileVersion checkVersion(String clientVersion, String clientOS, String appName) {
		localCacheSystemParams.refresh();//刷新缓存，及时响应后台更新版本
		Map<String, String> versionMap = localCacheSystemParams.getSystemParamGroup(SystemParamConstant.GRP_MOBILE_VERSION);
		appName = StringUtils.toString(appName);
		String serverVersion = versionMap.get(appName + clientOS);						// 客户端最新上线版本
		String mobileDownloadUrl = versionMap.get(appName + clientOS + "DownloadUrl");	// APP下载地址
		String usableLimit = versionMap.get(appName + clientOS + "LimitUsable");			// 最低支持版本
		String fileSize = versionMap.get(appName + clientOS + "FileSize");			//更新文件大小
		String updateRemark = versionMap.get(appName + clientOS + "UpdateRemark");	//版本更新说明
		String mobileWxDownloadUrl = versionMap.get(appName + clientOS + "WxDownloadUrl");	//版本更新说明
		// 组装返回结果
		MobileVersion mv = new MobileVersion();
		mv.setServerVersion(serverVersion);
		mv.setUpdateUrl(mobileDownloadUrl);
		mv.setWxUpdateUrl(mobileWxDownloadUrl);
		mv.setFileSize(fileSize);
		mv.setUpdateRemark(updateRemark);
		mv.setNeedUpdate(PaymentVersionUtils.isUpdatedVersion(clientVersion, serverVersion));	// 是否需要更新
		mv.setForceUpdate(PaymentVersionUtils.isUpdatedVersion(clientVersion, usableLimit));	// 是否必须更新，即服务端不支持客户端当前版本

		return mv;
	}

	/**
	 * 
	 *
	 * 方法说明：登出
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param request
	 * @param st
	 * @return
	 */
	@RequestMapping(value="/logout.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse logout(HttpServletRequest request, SessionStatus st) {
		String token = request.getParameter(ApiAccessConstants.TOKEN);
		if(StringUtils.isNotEmpty(token)) {
			tokenService.removeToken(token);	// 删除token缓存
		}
		st.setComplete();	// 清理session
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 *
	 * 方法说明：刷新令牌，应用场景见：{@link Token}中字段refreshToken说明
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param refreshToken
	 * @param appKey
	 * @param appSecret
	 * @param tokenTimeout
	 * @return
	 */
	@RequestMapping(value="/refreshToken.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Token refreshToken(String refreshToken, String appKey, String appSecret, int tokenTimeout) { 
		return tokenService.refreshToken(refreshToken, appKey, tokenTimeout);
	}
	
	/**
	 * 
	 *
	 * 方法说明：不可访问，如：参数错误、签名错误、未登录、超时登录等系统前置检查错误
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/noAuthorized.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse noAuthorized(HttpServletRequest request){
		GeneralResponse generalResponse = GeneralResponse.generateFailureResponse();
		String errorCode = request.getParameter("errorCode");
		if(StringUtils.isEmpty(errorCode)) {
			generalResponse.setErrorCode(ErrorCode.ACCESS_VALID);
			generalResponse.setErrorMessage("非法请求");
		} else {
			generalResponse.setErrorCode(errorCode);
			generalResponse.setErrorMessage(request.getParameter("errorMessage"));
		}
		return generalResponse;
	}
	
}
