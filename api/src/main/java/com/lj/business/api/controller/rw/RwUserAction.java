package com.lj.business.api.controller.rw;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ape.common.utils.Collections3;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.common.MemberConstants;
import com.lj.business.member.domain.GuidMember;
import com.lj.business.member.dto.AddGuidMember;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.GuidMemberRwDto;
import com.lj.business.member.dto.PersonMemberLogin;
import com.lj.business.member.dto.PersonMemberLoginReturn;
import com.lj.business.member.dto.UpdateGuidMember;
import com.lj.business.member.dto.UpdatePwdDto;
import com.lj.business.member.emus.MemberStatus;
import com.lj.business.member.emus.MemberType;
import com.lj.business.member.service.IGuidMemberRwService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.IMemberLoginService;
import com.lj.business.supcon.dto.token.Token;
import com.lj.business.supcon.service.ITokenService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.IDistributeCache;
import com.lj.kms.dto.EncryptRequest;
import com.lj.kms.dto.EncryptResponse;
import com.lj.kms.service.IEncryptor;
import com.lj.messagecenter.email.domain.TemplateSmsMessage;
import com.lj.messagecenter.email.service.ISmsSenderService;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.ISystemService;
import com.lj.oms.service.sys.SystemService;
import com.ye.business.ad.dto.RwUserBeansDto;
import com.ye.business.ad.service.IRwUserBeansService;
import com.ye.business.rw.constant.RwConstant;
import com.ye.business.rw.constant.SmsCodeConstant;
import com.ye.business.rw.dto.FindRwUserPage;
import com.ye.business.rw.dto.RwUserDto;
import com.ye.business.rw.service.IRwUserService;

/**
 * 
 * *类说明：热文用户
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年5月31日
 */
@RestController
@RequestMapping(value = "/rwUser/")
public class RwUserAction extends Action {

	@Autowired
	private IRwUserService rwUserService;

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Resource
	private ITokenService tokenService;

	// 发送短信
	@Autowired
	private ISmsSenderService smsSenderService;

	@Autowired
	private IRwUserBeansService rwUserBeansService;

	@Resource
	private IMemberLoginService memberLoginService;
	@Autowired
	private IGuidMemberRwService guidMemberRwService;

	@Autowired
	private ISystemService systemService;

	@Autowired
	private IGuidMemberService guidMemberService;

	@Resource
	private IDistributeCache distributeCache;

	@Resource
	public IManagerMemberService managerMemberService;

	/** The i encryptor. */
	@Resource
	private IEncryptor iEncryptor;

	/**
	 * APP登录，使用oms同一账户体系 member#导购表和oms#用户表创建用户时已同步
	 * 
	 * @param personMemberLogin
	 * @param appKey
	 * @return
	 */
	@RequestMapping(value = "rwLogin.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PersonMemberLoginReturn rwLogin(PersonMemberLogin personMemberLogin, String appKey) {
		logger.debug("rwLogin(PersonMemberLogin personMemberLogin={}) - start", personMemberLogin);

		personMemberLogin.setHasRwVersion(true);

		PersonMemberLoginReturn loginReturn = memberLoginService.personMemberLoginAPP(personMemberLogin);

		loginReturn.setIosOpen(localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.IOS_OPEN_SHOW, RwConstant.IOS_OPEN_SHOW));

		// 填充客服信息
		loginReturn.setServicePhone(localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.GROUP_YE_RWUSER, RwConstant.servicePhone));
		loginReturn.setServiceWeChatNo(localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.GROUP_YE_RWUSER, RwConstant.serviceWeChatNo));
		loginReturn.setServiceWeChatQRCode(localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.GROUP_YE_RWUSER, RwConstant.serviceWeChatQRCode));

		// 根据用户信息查询用户豆子
		RwUserBeansDto beans = rwUserBeansService.findRwUserBeans(loginReturn.getMemberNoGuid());
		if (beans != null) {
			loginReturn.setBeansNormal(beans.getBeansNormal());
			loginReturn.setBeansFreeze(beans.getBeansFreeze());
		}

		// 获取客户扩展信息
		GuidMemberRwDto findGuidMemberRwDto = new GuidMemberRwDto();
		findGuidMemberRwDto.setCode(loginReturn.getMemberNoGuid());
		GuidMemberRwDto findGuidMemberRwDtoReturn = guidMemberRwService.findGuidMemberRw(findGuidMemberRwDto);
		if (findGuidMemberRwDtoReturn != null) {
			loginReturn.setUserLevel(findGuidMemberRwDtoReturn.getUserLevel());
			loginReturn.setOpenLevelDate(findGuidMemberRwDtoReturn.getOpenLevelDate());
			loginReturn.setEndLevelDate(findGuidMemberRwDtoReturn.getEndLevelDate());
			loginReturn.setBirthDate(findGuidMemberRwDtoReturn.getBirthDate());
			loginReturn.setUserLike(findGuidMemberRwDtoReturn.getUserLike());
		} else {
			loginReturn.setUserLevel("1");
		}

		// 生成令牌
		if (StringUtils.isNotEmpty(appKey)) {
			Token token = tokenService.generateToken(loginReturn.getMemberNoGuid(), appKey, Token.TOKEN_TIMEOUT_SECONDS);
			loginReturn.setToken(token.getAccessToken());
//			
//			String loginRecordJson = JSON.toJSONString(loginReturn);
//			distributeCache.set(token.getAccessToken(), loginRecordJson, Token.TOKEN_TIMEOUT_SECONDS);
		}

		logger.debug("rwLogin(PersonMemberLoginReturn={}) - end", loginReturn);
		return loginReturn;
	}

	/**
	 * APP登录，使用oms同一账户体系 member#导购表和oms#用户表创建用户时已同步
	 * 
	 * @param personMemberLogin
	 * @param appKey
	 * @return
	 */
	@RequestMapping(value = "tokenLogin.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse tokenLogin(String token, String appKey) {
		logger.debug("tokenLogin(String token={}) - start", token);

		String userid = getLoginUserByToken(token);

		if (User.isAdmin(userid)) {
			return GeneralResponse.generateSuccessResponse("isAdmin");
		}

		GuidMember guidMember = new GuidMember();
		guidMember.setMemberNo(userid);

		GuidMember gm = guidMemberService.findSingleGuidMember(guidMember);

		User user = systemService.getUser(userid);

		PersonMemberLoginReturn loginReturn = buildPersonMemberLoginReturn(gm);

		loginReturn.setOrgName(user.getOffice().getName());
		loginReturn.setCompanyAddress(user.getOffice().getAddress());

		if (!Collections3.isEmpty(user.getRoleList())) {
			loginReturn.setRoleName(user.getRoleList().get(0).getName());
		}

		loginReturn.setIosOpen(localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.IOS_OPEN_SHOW, RwConstant.IOS_OPEN_SHOW));

		// 填充客服信息
		loginReturn.setServicePhone(localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.GROUP_YE_RWUSER, RwConstant.servicePhone));
		loginReturn.setServiceWeChatNo(localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.GROUP_YE_RWUSER, RwConstant.serviceWeChatNo));
		loginReturn.setServiceWeChatQRCode(localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.GROUP_YE_RWUSER, RwConstant.serviceWeChatQRCode));

		// 根据用户信息查询用户豆子
		RwUserBeansDto beans = rwUserBeansService.findRwUserBeans(loginReturn.getMemberNoGuid());
		if (beans != null) {
			loginReturn.setBeansNormal(beans.getBeansNormal());
			loginReturn.setBeansFreeze(beans.getBeansFreeze());
		}

		// 获取客户扩展信息
		GuidMemberRwDto findGuidMemberRwDto = new GuidMemberRwDto();
		findGuidMemberRwDto.setCode(loginReturn.getMemberNoGuid());
		GuidMemberRwDto findGuidMemberRwDtoReturn = guidMemberRwService.findGuidMemberRw(findGuidMemberRwDto);
		if (findGuidMemberRwDtoReturn != null) {
			loginReturn.setUserLevel(findGuidMemberRwDtoReturn.getUserLevel());
			loginReturn.setOpenLevelDate(findGuidMemberRwDtoReturn.getOpenLevelDate());
			loginReturn.setEndLevelDate(findGuidMemberRwDtoReturn.getEndLevelDate());
			loginReturn.setBirthDate(findGuidMemberRwDtoReturn.getBirthDate());
			loginReturn.setUserLike(findGuidMemberRwDtoReturn.getUserLike());
		} else {
			loginReturn.setUserLevel("1");
		}

		loginReturn.setToken(token);

		logger.debug("rwLogin(PersonMemberLoginReturn={}) - end", loginReturn);
		return GeneralResponse.generateSuccessResponse(loginReturn);
	}

	/**
	 * 组装返回数据
	 * 
	 * @param guidMember
	 * @return
	 */
	private PersonMemberLoginReturn buildPersonMemberLoginReturn(GuidMember guidMember) {

		PersonMemberLoginReturn personMemberLoginReturn = new PersonMemberLoginReturn();

		// 登录返回信息
		personMemberLoginReturn.setCode(guidMember.getCode());
		personMemberLoginReturn.setMemberNoGuid(guidMember.getMemberNo());
		personMemberLoginReturn.setMemberNameGuid(guidMember.getMemberName());
		personMemberLoginReturn.setMemberNoMerchant(guidMember.getMerchantNo());
		personMemberLoginReturn.setMemberNameMerchant(guidMember.getMerchantName());
		personMemberLoginReturn.setMobile(guidMember.getMobile());
		personMemberLoginReturn.setEmail(guidMember.getEmail());
		personMemberLoginReturn.setNickName(guidMember.getNickName());
		personMemberLoginReturn.setAddress(guidMember.getAddress());
		personMemberLoginReturn.setAge(guidMember.getAge());
		personMemberLoginReturn.setNoWx(guidMember.getNoWx());
		personMemberLoginReturn.setProvinceCode(guidMember.getProvinceCode());
		personMemberLoginReturn.setCityCode(guidMember.getCityCode());
		personMemberLoginReturn.setAreaCode(guidMember.getAreaCode());
		personMemberLoginReturn.setAreaName(guidMember.getAreaName());
		personMemberLoginReturn.setHeadAddress(guidMember.getHeadAddress());
		personMemberLoginReturn.setGender(guidMember.getGender());
		personMemberLoginReturn.setMemberType(MemberType.GUID.toString());
		personMemberLoginReturn.setWorkDate(guidMember.getWorkDate());
		personMemberLoginReturn.setStatus(guidMember.getStatus());
		personMemberLoginReturn.setRemark3(guidMember.getRemark3());

		String uploadUrl = localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(), SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
		personMemberLoginReturn.setUploadUrl(uploadUrl);

		return personMemberLoginReturn;
	}

	/**
	 * 
	 * *方法说明：注册
	 *
	 * @param rwUser
	 * @param appKey
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月31日
	 */
	@RequestMapping(value = "rwRegistered.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse rwRegistered(String loginName, String verification, String pwd, String appKey) {
		logger.debug("rwRegistered(RwUserDto loginName={}, verification = {}, pwd = {}) - start", loginName, verification, pwd);

		// 注意oms密码与member密码需要保持一致，在前端传递密码需要明文传递.

		// 验证登录名是否有效
		AssertUtils.notNullAndEmpty(loginName, "登录帐号不能为空！");
		AssertUtils.notNullAndEmpty(verification, "验证码不能为空！");
		AssertUtils.notNullAndEmpty(pwd, "密码不能为空！");

		if (systemService.getUserByLoginName(loginName) != null) {
			GeneralResponse.generateFailureResponse(com.lj.business.member.constant.ErrorCode.PERSON_ERROR_NOT_EXIST, "个人会员注册错误：会员已存在！");
		}

		// 验证短信验证码
		verify(loginName, verification, "1");

//		pwd = SystemService.entryptPassword(pwd);

		Date now = new Date();

		// 创建用户
		User user = new User();

		user.setRoleList(Lists.newArrayList());
		user.setLayout(User._DARKBLUE);
		user.setUserType(User.USERTYPE_4);
		user.setLoginFlag("1");

		user.setLoginName(loginName);
		user.setNo(loginName);
		user.setName(loginName);
		// oms密码
		user.setPassword(SystemService.entryptPassword(pwd));
		user.setMobile(loginName);
		user.setCreateDate(now);
		user.setUpdateDate(now);

		// 写入默认公司，
		String companyId = localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.DEFAULT_COMPANY, RwConstant.COMPANY_ID);
		String officeId = localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.DEFAULT_COMPANY, RwConstant.OFFICE_ID);
		String roleIds = localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.DEFAULT_COMPANY, RwConstant.ROLE_IDS);

		user.setCompany(new Office(companyId));
		user.setOffice(new Office(officeId));
		user.setRoleIdList(Arrays.asList(roleIds.split(",")));

		user.setHasGetApi(true);
//		user.setIsNewRecord(true);

		// 保存用户信息
		String userid = systemService.saveUser(user);

		String status = MemberStatus.NORMAL.toString();

		// 同步保存导购记录中
		AddGuidMember addGuidMember = new AddGuidMember();
		addGuidMember.setMemberNo(userid);
		addGuidMember.setMemberName(user.getName());
		addGuidMember.setMerchantNo(user.getCompany().getId());
		addGuidMember.setMerchantName(user.getCompany().getName());
		addGuidMember.setLoginName(user.getLoginName());
		addGuidMember.setStatus(status);
		addGuidMember.setJobNum(user.getNo());
		addGuidMember.setMobile(user.getMobile());
//		addGuidMember.setPwd(MD5.encryptByMD5(pwd));
		addGuidMember.setPwd(pwd);
		addGuidMember.setHeadAddress(user.getPhoto());
		addGuidMember.setCreateId(user.getName());

		guidMemberService.addGuidMember(addGuidMember);

		// 同步热文信息
		GuidMemberRwDto guidMemberRw = new GuidMemberRwDto();

		guidMemberRw.setCode(userid);
		guidMemberRw.setUpdateTime(now);
		guidMemberRw.setMemberName(user.getName());
		guidMemberRw.setUserLevel("1");

		guidMemberRw.setCreateTime(now);
		guidMemberRwService.addGuidMemberRw(guidMemberRw);

//		rwUserService.personRwUserRegistered(null);

		RwUserBeansDto upBeans = new RwUserBeansDto();
		upBeans.setCode(userid);
		upBeans.setMemberName(user.getName());
		upBeans.setMerchantNo(user.getOffice().getId());
		upBeans.setMerchantName(user.getOffice().getName());
		upBeans.setBeansSum(0);
		upBeans.setBeansNormal(0);
		upBeans.setBeansFreeze(0);
		upBeans.setBeansUse(0);
		upBeans.setCreateId(userid);
		upBeans.setCreateName(user.getName());
		upBeans.setUpdateId(userid);
		upBeans.setUpdateName(user.getName());
		upBeans.setCreateDate(now);
		upBeans.setUpdateDate(now);
		rwUserBeansService.addRwUserBeans(upBeans);

		logger.debug("rwRegistered(RwUserDto={}) - end");
		return GeneralResponse.generateSuccessResponse("OK");
	}

	/**
	 * 
	 * *方法说明：发送短信验证码
	 *
	 * @param loginName 手机号
	 * @param type 类型：1-注册；2-忘记密码；
	 * @param appKey
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月31日
	 */
	@RequestMapping(value = "rwSendSms.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String rwSendSms(String loginName, String type, String appKey) {
		logger.debug("rwSendSms(String loginName={}) - start", loginName);

		AssertUtils.notNullAndEmpty(loginName, "登录帐号不能为空！");
		AssertUtils.notNullAndEmpty(type, "类型不能为空！");

		String templateId = "";
		String redisKey = "";

		FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
		findGuidMemberPage.setMobile(loginName);
		List<FindGuidMemberPageReturn> guidMember = guidMemberService.findGuidMembers(findGuidMemberPage);

		if ("1".equals(type)) {

			if (guidMember != null && !guidMember.isEmpty()) {
				logger.info("用户已存在！");
				throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.PERSON_ERROR_NOT_EXIST, "用户已存在！");
			}

			templateId = localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), SmsCodeConstant.RW_USER_SMS, SmsCodeConstant.RW_REGISTERED_SMS_CODE);
			redisKey = SmsCodeConstant.RW_REGISTERED_SMS_CODE + loginName;
		} else if ("2".equals(type)) {

			// 忘记密码需要验证用户是否存在
			if (guidMember == null || guidMember.isEmpty()) {
				logger.info("用户不存在！");
				throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.PERSON_ERROR_NOT_EXIST, "用户不存在！");
			}

			templateId = localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), SmsCodeConstant.RW_USER_SMS, SmsCodeConstant.RW_FORGET_SMS_CODE);
			redisKey = SmsCodeConstant.RW_FORGET_SMS_CODE + loginName;
		}

		if (StringUtils.isNotEmpty(distributeCache.get(redisKey))) {
			logger.error("频繁发送短信验证码");
			throw new TsfaServiceException(ErrorCode.SMS_CODE_EXIST, "亲，短信费用好贵哦，请不要频繁发送！");
		}

		Random rnd = new Random(System.currentTimeMillis());
		String validateCode = StringUtils.padLeft(String.valueOf(Math.abs(rnd.nextInt() % 10000)), 4, '0');

		int expireSeconds = 1800;

		distributeCache.set(redisKey, validateCode, expireSeconds); // 插入缓存区

		TemplateSmsMessage sms = new TemplateSmsMessage();

//		注册：【天天热文分享】注册会员，您的验证码：${code}，切勿告知他人，在30分钟内有效。
//		忘记密码：【天天热文分享】会员密码找回，您的验证码：${code}，切勿告知他人，在30分钟内有效。

		sms.setTelphoneNo(loginName);
		sms.setTemplateId(templateId);

		Map<String, String> contentMap = new HashMap<>();
		contentMap.put("code", validateCode);
		sms.setContentMap(contentMap);

		try {
			smsSenderService.sendTemplateSms(sms);
		} catch (Exception e) {
			logger.error("获取短信验证码错误！", e);
			throw new TsfaServiceException(com.lj.business.api.common.ErrorCode.SMS_CODE_ERROR, "获取短信验证码错误！", e);
		}

		// 写入缓存：默认过期时间：30分钟
		logger.info("验证码：{}, {}, {};", loginName, type, validateCode);

		String rs = "OK";

		logger.debug("rwRegistered(RwUserDto={}) - end", rs);
		return rs;
	}

	/**
	 * 
	 * *方法说明：短信验证码校验
	 *
	 * @param loginName
	 * @param vcode
	 * @param type
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年6月25日
	 */
	private boolean verify(String loginName, String vcode, String type) {

		String redisKey = "";
		if ("1".equals(type)) {
			redisKey = SmsCodeConstant.RW_REGISTERED_SMS_CODE + loginName;
		} else if ("2".equals(type)) {
			redisKey = SmsCodeConstant.RW_FORGET_SMS_CODE + loginName;
		}

		String value = distributeCache.get(redisKey);
		if (vcode.equals(value)) {
			distributeCache.del(redisKey); // 验证通过，删除key，以便可以接受后续的验证码
			return true;
		} else {
			logger.error("短信验证码错误");
			throw new TsfaServiceException(ErrorCode.SMS_CODE_ERROR, "对不起，您输入的验证码有误");
		}
	}

	/**
	 * 
	 * *方法说明：修改密码
	 *
	 * @param rwUser
	 * @param appKey
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年6月2日
	 */
	@RequestMapping(value = "rwEditPwd.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse rwEditPwd(RwUserDto rwUser, String appKey, String token) {
		logger.debug("rwLogin(RwUserDto rwUser={}) - start", rwUser);

		AssertUtils.notNull(rwUser);

		String pwd = rwUser.getPwd();
		String loginName = rwUser.getLoginName();
		String verification = rwUser.getVerification();
		String nowPwd = rwUser.getNowPwd();

		AssertUtils.notNullAndEmpty(loginName, "登录帐号不能为空！");
		AssertUtils.notNullAndEmpty(nowPwd, "新密码不能为空！");

		// 获取导购信息
		FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
		findGuidMemberPage.setMobile(loginName);
		List<FindGuidMemberPageReturn> guidMemberList = guidMemberService.findGuidMembers(findGuidMemberPage);

		if (Collections3.isEmpty(guidMemberList)) {
			logger.info("用户不存在！");
			throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.PERSON_ERROR_NOT_EXIST, "用户不存在！");
		}

		if (StringUtils.isNotEmpty(pwd)) {
			AssertUtils.notNullAndEmpty(pwd, "密码不能为空！");

			// 校验旧密码

			// 加密机加密
			EncryptRequest encryptRequest = new EncryptRequest();
			encryptRequest.setAppCode(MemberConstants.ENCRYPT_APPCODE);
			encryptRequest.setOriginalText(nowPwd);

			EncryptResponse encryptResponse = iEncryptor.encrypt(encryptRequest);
			String enpwd = encryptResponse.getCipherText();

			if (!enpwd.equals(guidMemberList.get(0).getPwd())) {
				logger.info("用户密码！");
				throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.PERSON_LOGIN_ERROR_PSW, "用户密码！");
			}

		} else {
			AssertUtils.notNullAndEmpty(verification, "短信验证码不能为空！");
			// 校验短信验证码
			verify(loginName, verification, "2");
		}

		FindGuidMemberPageReturn guidMember = guidMemberList.get(0);

		UpdatePwdDto updatePwdDto = new UpdatePwdDto();
		updatePwdDto.setCode(guidMember.getCode());
		updatePwdDto.setPwd(nowPwd);

		guidMemberService.updatePwd(updatePwdDto);

		logger.debug("rwLogin(RwUserDto={}) - end");
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 * *方法说明：app 修改用户信息
	 *
	 * @param updateGuidMember
	 * @param guidMemberRw
	 * @param appKey
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年6月25日
	 */
	@RequestMapping(value = "rwEdit.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse rwEdit(UpdateGuidMember updateGuidMember, GuidMemberRwDto guidMemberRw, String appKey, String token) {
		logger.debug("rwLogin(UpdateGuidMember updateGuidMember={}) - start", updateGuidMember);

//		AssertUtils.notNullAndEmpty(updateGuidMember.getMemberNo(), "用户编号不能为空");

		String userid = getLoginUserByToken(token);

		updateGuidMember.setMemberNo(userid);

		UpdateGuidMember updateParam = new UpdateGuidMember();
		updateParam.setCode(updateGuidMember.getCode());
		updateParam.setAge(updateGuidMember.getAge());
		updateParam.setAddress(updateGuidMember.getAddress());
		updateParam.setNickName(updateGuidMember.getNickName());
		updateParam.setUpdateDate(new Date());
		updateParam.setGender(updateGuidMember.getGender());
		updateParam.setHeadAddress(updateGuidMember.getHeadAddress());
		updateParam.setAreaCode(updateGuidMember.getAreaCode());
		updateParam.setAreaName(updateGuidMember.getAreaName());
		updateParam.setCityAreaCode(updateGuidMember.getCityAreaCode());
		updateParam.setCityCode(updateGuidMember.getCityCode());
		updateParam.setProvinceCode(updateGuidMember.getProvinceCode());
		updateParam.setNoWx(updateGuidMember.getNoWx());
		updateParam.setRemark(updateGuidMember.getRemark());
		updateParam.setRemark3(updateGuidMember.getRemark3());

		guidMemberService.updateGuidMember(updateParam);

		// 更新扩展热文用户信息
		if (guidMemberRw != null) {

			guidMemberRw.setCode(updateGuidMember.getMemberNo());
			if (guidMemberRwService.findGuidMemberRw(guidMemberRw) == null) {
				guidMemberRw.setUserLevel("1"); // 默认不允许修改，后台操作
				guidMemberRwService.addGuidMemberRw(guidMemberRw);
			} else {
				guidMemberRw.setUserLevel(null); // 默认不允许修改，后台操作
				guidMemberRwService.updateGuidMemberRw(guidMemberRw);
			}
		}

		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 分页查询用户
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param findRwUserPage
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "userList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse userList(FindRwUserPage findRwUserPage, RwUserDto param) {

		findRwUserPage.setParam(param);

		Page<RwUserDto> page = rwUserService.findRwUserPage(findRwUserPage);

		return GeneralResponse.generateSuccessResponse(page);

	}

	/**
	 * 查询商户下所有用户
	 * 
	 * @param merchantNo
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "memberInfo.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse memberInfo(GuidMemberRwDto guidMemberRwDto) throws TsfaServiceException {

		GuidMemberRwDto guidMemberRw = guidMemberRwService.findGuidMemberRw(guidMemberRwDto);

		return GeneralResponse.generateSuccessResponse(guidMemberRw);

	}

	/**
	 * 
	 * *方法说明：获取用户总Beans
	 *
	 * @param code
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年7月15日
	 */
	@RequestMapping(value = "beansNum.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse beansNum(String code) throws TsfaServiceException {

		RwUserBeansDto rs = rwUserBeansService.findRwUserBeans(code);

		if (rs != null && rs.getBeansNormal().intValue() > 0) {
			return GeneralResponse.generateSuccessResponse(rs.getBeansNormal());
		}

		return GeneralResponse.generateSuccessResponse(0);
	}

}
