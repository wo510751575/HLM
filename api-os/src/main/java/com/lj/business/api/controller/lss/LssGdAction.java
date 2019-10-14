package com.lj.business.api.controller.lss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lj.base.core.encryption.MD5;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.httpclient.HttpClientUtils;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.dto.LssAuthorizeDto;
import com.lj.business.member.domain.GuidMember;
import com.lj.business.member.dto.PersonMemberDto;
import com.lj.business.member.dto.PersonMemberLoginReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.supcon.dto.token.Token;
import com.lj.business.supcon.service.ITokenService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;

/**
 * 
 * 
 * 
 * 类说明：乐莎莎工单交互Controller
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月13日
 */
@Controller
@RequestMapping(value = "/lss/gd")
public class LssGdAction {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public static final String lssSystemAliasName = "lss";
	public static final String group_gd = "lssgd";
	public static final String host_gd = "gdHost";
	
	@Resource
	private IPersonMemberService personMemberService ;
	@Autowired
	private ITokenService tokenService;
	@Resource
	private IGuidMemberService guidMemberService; // 导购服务
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	
	/**
	 * 乐莎莎工单 获取 IM token
	 * @return
	 */
	@RequestMapping(value = "/imToken.do")
	@ResponseBody
	public GeneralResponse lssImToken(LssAuthorizeDto lssAuthorizeDto) {
		logger.debug("lssImLogin(LssAuthorizeDto lssAuthorizeDto={}) - start", lssAuthorizeDto.getLoginame());
		AssertUtils.notNullAndEmpty(lssAuthorizeDto.getLoginame(), "登录名不能为空！");
		AssertUtils.notNullAndEmpty(lssAuthorizeDto.getLoginpwd(), "密码不能为空！");
		AssertUtils.notNullAndEmpty(lssAuthorizeDto.getAppKey(), "APPKEY不能为空！");

		PersonMemberLoginReturn loginReturn = null;
		String gdHost = localCacheSystemParams.getSystemParam(lssSystemAliasName, group_gd, host_gd);
		// 1.使用工单校验用户
		Map<String, String> map = new HashMap<String, String>();
		map.put("loginame", lssAuthorizeDto.getLoginame());
		map.put("loginpwd", lssAuthorizeDto.getLoginpwd());
		String result = HttpClientUtils.postToWeb(gdHost + "/ztc/getAdmin", map);
		if (StringUtils.isEmpty(result)) {
			throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "请求工单错误");
		}
		JSONObject obj = JSONObject.parseObject(result);
		if (obj == null || obj.getIntValue("result") != 1) {
			throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "请求工单验证错误");
		}
//			JSONObject admin=obj.getJSONObject("obj");
//			String pwd = MD5.encryptByMD5(admin.getString("loginpwd"));
//			if (!lssAuthorizeDto.getLoginpwd().equals(pwd)) {
//				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "密码不正确");
//			}
		// 2.查找客户是否存在于客户直通车Im
		GuidMember gmQuery = new GuidMember();
		gmQuery.setLoginName(lssAuthorizeDto.getLoginame());
		GuidMember guidMember = guidMemberService.findSingleGuidMember(gmQuery);
		AssertUtils.notNullAndEmpty(guidMember, "导购不存在！");

		Token token = tokenService.generateToken(guidMember.getMemberNo(), lssAuthorizeDto.getAppKey(),
				Token.TOKEN_TIMEOUT_SECONDS);
		loginReturn = new PersonMemberLoginReturn();
		loginReturn.setCode(guidMember.getCode());
		loginReturn.setMemberNameMerchant(guidMember.getMerchantName());
		loginReturn.setMemberNoMerchant(guidMember.getMerchantNo());
		loginReturn.setMemberNoGuid(guidMember.getMemberNo());
		loginReturn.setMemberNameGuid(guidMember.getMemberName());
		loginReturn.setToken(token.getAccessToken());
		logger.debug("lssImLogin(LssAuthorizeDto) - end - return value={}", token);

		return GeneralResponse.generateSuccessResponse(loginReturn);
	}

	/**
	 * 查导购好友信息
	 * @param memberDto
	 * @return
	 */
	@RequestMapping(value = "/pmlist.do")
	@ResponseBody
	public GeneralResponse pmList(PersonMemberDto memberDto) {
		List<PersonMemberDto> data = personMemberService.findPersonMemberByMoblies(memberDto);
		 return GeneralResponse.generateSuccessResponse(data);
	}
	
}
