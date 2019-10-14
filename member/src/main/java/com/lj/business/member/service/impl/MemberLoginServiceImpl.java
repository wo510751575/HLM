package com.lj.business.member.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.common.RiskControlParam;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.common.MemberConstants;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IGmAssistantShopDao;
import com.lj.business.member.dao.IGuidMemberDao;
import com.lj.business.member.dao.ILoginCheckDao;
import com.lj.business.member.dao.IManagerMemberDao;
import com.lj.business.member.dao.IShopTerminalDao;
import com.lj.business.member.domain.GmAssistantShop;
import com.lj.business.member.domain.GuidMember;
import com.lj.business.member.domain.LoginCheck;
import com.lj.business.member.domain.ManagerMember;
import com.lj.business.member.domain.ShopTerminal;
import com.lj.business.member.dto.AddMemberLoginInfo;
import com.lj.business.member.dto.PersonMemberLogin;
import com.lj.business.member.dto.PersonMemberLoginReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.emus.LockStatus;
import com.lj.business.member.emus.MemberStatus;
import com.lj.business.member.emus.MemberType;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.IMemberLoginInfoService;
import com.lj.business.member.service.IMemberLoginService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.RedisCache;
import com.lj.kms.dto.DecryptRequest;
import com.lj.kms.dto.DecryptResponse;
import com.lj.kms.service.IEncryptor;
import com.lj.oms.entity.sys.Role;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.RoleHessianService;
import com.lj.oms.service.UserHessianService;


/**
 * 类说明：会员登录
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author LeoPeng
 * 
 * 
 * CreateDate: 2017-6-4
 */
@Service
public class MemberLoginServiceImpl implements IMemberLoginService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MemberLoginServiceImpl.class);

	/** 导购. */
	@Resource
	private IGuidMemberDao guidMemberDao;

	/** The login check dao. */
	@Resource
	private ILoginCheckDao loginCheckDao;

	/** The manager member dao. */
	@Resource
	private IManagerMemberDao managerMemberDao;

	/** The member login info service. */
	@Resource
	private IMemberLoginInfoService memberLoginInfoService;

	/** The local cache system params. */
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	/** The encryptor service. */
	@Resource
	private IEncryptor encryptorService;
	@Resource
	private IManagerMemberService managerMemberService;
	@Resource
	private IGuidMemberService guidMemberService;
	
	@Resource
	private IMerchantService merchantService;
	@Autowired
	private UserHessianService userHessianService;
	@Autowired
	private RoleHessianService roleHessianService;
	
	@Resource
	private IGmAssistantShopDao gmAssistantShopDao;
	
	@Resource
	private IShopTerminalDao shopTerminalDao;
	
	@Resource
	private RedisCache redisCache;
	@Resource
	private IEncryptor iEncryptor;
	@Resource
	private ICommonService commonService;
	
	/**
	 * APP登录（优先店长身份，校验手机串号）.
	 *
	 * @param personMemberLogin the person member login
	 * @return the person member login return
	 * @throws TsfaServiceException the tsfa service exception
	 */
	@Override
	public PersonMemberLoginReturn personMemberLoginAPP(PersonMemberLogin personMemberLogin) throws TsfaServiceException {
		logger.debug("personMemberLogin(PersonMemberLogin personMemberLogin=" + personMemberLogin + ") - start");  //$NON-NLS-2$
		AssertUtils.notNull(personMemberLogin);
		String pwd = personMemberLogin.getPwd();
		String loginName = personMemberLogin.getLoginName();
		
		AssertUtils.notNullAndEmpty(loginName,"登录帐号不能为空！");
		AssertUtils.notNullAndEmpty(pwd,"密码不能为空！");

		
		PersonMemberLoginReturn personMemberLoginReturn = null;

		try{
			
			personMemberLoginReturn = guidLogin(personMemberLogin, loginName, pwd);
			/**
			 * 获取用户
			 */
			User user =userHessianService.findByUserId(personMemberLoginReturn.getMemberNoGuid());
			personMemberLoginReturn.setOrgName(user.getCompany().getName());
			personMemberLoginReturn.setCompanyAddress(user.getCompany().getAddress());
			
			/**
			 * 获取所属机构和角色
			 */
			List<Role> roles =roleHessianService.findByUserId(personMemberLoginReturn.getMemberNoGuid());
			if(roles!=null && roles.size()>0) {
				personMemberLoginReturn.setRoleName(roles.get(0).getName());
			}
			
			//如果有下级，则是老板帐号，adminUserId则不为空
			FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
			findGmAssistantShop.setMerchantNo(personMemberLoginReturn.getMemberNoMerchant());
			findGmAssistantShop.setAssistantNo(personMemberLoginReturn.getMemberNoGuid());
			findGmAssistantShop.setSource(false);
			List<FindGmAssistantShopReturn> list= gmAssistantShopDao.findGmAssistantShopList(findGmAssistantShop);
			if(list !=null && list.size()>0) {
				personMemberLoginReturn.setMemberType(MemberType.BOSS.toString());
			}
			
			/**
			 *  如果登录时mac不为空（ios客户端登录时该字段为极光推送注册的registerId），
			 *  则将mac与对应的导购编号缓存起来（保存同一mac下只保存最后登录的导购编号），用于客户端离线时推送通知
			 *  见{@link com.lj.business.supcon.netty.service.SendChatInfoService#pushChatInfo()}
			 *  update by   zengchuiyu 
			 *  update date 2018-05-02
			 *  bug id      6652
			 */
			if(StringUtils.isNotEmpty(personMemberLogin.getMac())) {
				redisCache.hset(MemberConstants.MEMBER_LOGIN_SMS_ID_KEY, personMemberLogin.getMac(), personMemberLoginReturn.getMemberNoGuid());
			}
			
			String uploadUrl =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
			personMemberLoginReturn.setUploadUrl(uploadUrl);
			
			String wxUpdateUrl =  localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(),SystemParamConstant.GRP_MOBILE_VERSION, SystemParamConstant.ANDROID_WX_DOWNLOAD_URL);
			personMemberLoginReturn.setWxUpdateUrl(wxUpdateUrl);
			
			
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("个人会员登录错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR,"个人会员登录错误！",e);
		}
		
		logger.debug("personMemberLoginAPP(PersonMemberLogin) - end - return value={}", personMemberLoginReturn); 
		return personMemberLoginReturn;
	}
	
	
	
	/**
	 * .
	 *
	 * @param personMemberLogin the person member login
	 * @return the person member login return
	 * @throws TsfaServiceException the tsfa service exception
	 */
	@Override
	public PersonMemberLoginReturn changeAccountLoginAPP(PersonMemberLogin personMemberLogin) throws TsfaServiceException {
		logger.debug("personMemberLogin(PersonMemberLogin personMemberLogin=" + personMemberLogin + ") - start");  //$NON-NLS-2$
		AssertUtils.notNull(personMemberLogin);
		
		String loginName = personMemberLogin.getLoginName();
		
		AssertUtils.notNullAndEmpty(loginName,"登录号不能为空！");
		AssertUtils.notNullAndEmpty(personMemberLogin.getNoWx(),"微信号不能为空！");
		
		PersonMemberLoginReturn personMemberLoginReturn = null;

		try{
			/**
			 * 获取终端状态，离线直接返回提示
			 */
			if(!commonService.getZkTerminalStatus(personMemberLogin.getNoWx())){
				throw new TsfaServiceException("", "中控已离线！");
			}
			
			personMemberLoginReturn = changeGuidLogin(personMemberLogin, loginName);
			/**
			 * 获取用户
			 */
			User user =userHessianService.findByUserId(personMemberLoginReturn.getMemberNoGuid());
			personMemberLoginReturn.setOrgName(user.getOffice().getName());
			personMemberLoginReturn.setCompanyAddress(user.getOffice().getAddress());
			
			/**
			 * 获取所属机构和角色
			 */
			List<Role> roles =roleHessianService.findByUserId(personMemberLoginReturn.getMemberNoGuid());
			personMemberLoginReturn.setRoleName(roles.get(0).getName());
			
			//如果有下级，则是老板帐号，adminUserId则不为空
			FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
			findGmAssistantShop.setMerchantNo(personMemberLoginReturn.getMemberNoMerchant());
			findGmAssistantShop.setAssistantNo(personMemberLoginReturn.getMemberNoGuid());
			findGmAssistantShop.setSource(false);
			List<FindGmAssistantShopReturn> list= gmAssistantShopDao.findGmAssistantShopList(findGmAssistantShop);
			if(list !=null && list.size()>0) {
				personMemberLoginReturn.setMemberType(MemberType.BOSS.toString());
			}
			
			/**
			 *  如果登录时mac不为空（ios客户端登录时该字段为极光推送注册的registerId），
			 *  则将mac与对应的导购编号缓存起来（保存同一mac下只保存最后登录的导购编号），用于客户端离线时推送通知
			 *  见{@link com.lj.business.supcon.netty.service.SendChatInfoService#pushChatInfo()}
			 *  update by   zengchuiyu 
			 *  update date 2018-05-02
			 *  bug id      6652
			 */
			if(StringUtils.isNotEmpty(personMemberLogin.getMac())) {
				redisCache.hset(MemberConstants.MEMBER_LOGIN_SMS_ID_KEY, personMemberLogin.getMac(), personMemberLoginReturn.getMemberNoGuid());
			}
			
			String uploadUrl =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
			personMemberLoginReturn.setUploadUrl(uploadUrl);
			
			String wxUpdateUrl =  localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(),SystemParamConstant.GRP_MOBILE_VERSION, SystemParamConstant.ANDROID_WX_DOWNLOAD_URL);
			personMemberLoginReturn.setWxUpdateUrl(wxUpdateUrl);
			
			
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("个人会员登录错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR,"个人会员登录错误！",e);
		}
		
		logger.debug("personMemberLoginAPP(PersonMemberLogin) - end - return value={}", personMemberLoginReturn); 
		return personMemberLoginReturn;
	}


	/**
	 * 
	 *
	 * 方法说明：管理人员登录
	 *
	 * @param personMemberLogin
	 * @param pwd
	 * @param imei
	 * @param managerMember
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月20日
	 *
	 */
	/*@Deprecated
	private PersonMemberLoginReturn manageLogin(
			PersonMemberLogin personMemberLogin, String pwd, String imei,
			ManagerMember managerMember, String noWx) {
		PersonMemberLoginReturn personMemberLoginReturn;
		if(MemberStatus.FREEZE.toString().equals(managerMember.getStatus())){
			logger.error("个人会员登录错误：会员被冻结！");
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_FREEZE,"个人会员登录错误：会员被冻结！");
		}
		if(MemberStatus.CANCEL.toString().equals(managerMember.getStatus())){
			logger.error("个人会员登录错误：会员被注销！");
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_FREEZE,"个人会员登录错误：会员被注销！");
		}
		if(MemberStatus.LEAVE.toString().equals(managerMember.getStatus())){
			logger.error("个人会员登录错误：会员已离职！");
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_FREEZE,"个人会员登录错误：会员已离职！");
		}
		
		//商户试用期判断
		FindMerchantDto findMerchantDto = new FindMerchantDto();
		findMerchantDto.setMerchantNo(managerMember.getMemberNoMerchant());
		FindMerchantReturnDto merchant = merchantService.selectMerchant(findMerchantDto);
		if (merchant != null && MerchantProbationStatusEnum.END.toString().equals(merchant.getProbationStatus())) {
			logger.error("个人会员登录错误：商户试用期结束！");
			throw new TsfaServiceException(ErrorCode.MERCHANT_PROBATION_ERROR_END,"个人会员登录错误：商户试用期结束！");
		}
		
		//暂时注释，不判断IMEI
		if(StringUtils.isEmpty(imei) || !managerMember.getImei().equals(imei)){
			logger.error("个人会员登录错误：不是本人手机！");
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_MOBILE,"个人会员登录错误：不是本人手机！");
		}

		if(StringUtils.isNotEmpty(noWx)) {
			if(!managerMember.getNoWx().equals(noWx)) {
				logger.error("个人会员登录错误：微信号不存在！");
				throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_CANCEL,"个人会员登录错误：微信号不存在！");
			}
		}
		
		//登录密码锁定时间（小时）
		String loginPasswordLockTimeStr = localCacheSystemParams.getSystemParam(RiskControlParam.GroupName,RiskControlParam.LoginPasswordLockTime);
		LoginCheck findLoginCheck = new LoginCheck();
		findLoginCheck.setMemberNo(managerMember.getMemberNo());

		LoginCheck loginCheck = loginCheckDao.findLoginCheck(findLoginCheck);
		//登录密码连续输入错误次数
		if(loginCheck.getCycleLoginFailTimes() == null) loginCheck.setCycleLoginFailTimes(0);
		Integer cycleLoginFailTimes = loginCheck.getCycleLoginFailTimes();
		Date lastLoginErrorDate = loginCheck.getLastLoginErrorDate();
		Date now = new Date();

		//会员是自动锁定
		if(LockStatus.AUTOLOCK.toString().equals(loginCheck.getLockStatus())){
			if(lastLoginErrorDate != null){
				double loginPasswordLockTime = Double.valueOf(loginPasswordLockTimeStr);
				logger.debug("登录冻结时间，loginPasswordLockTime:" + loginPasswordLockTime);
				double loginFreezeDisTime = ((double)(System.currentTimeMillis() - lastLoginErrorDate.getTime()))/(1000*60*60);
				logger.debug("登录冻结时间差，loginFreezeDisTime:" + loginFreezeDisTime +"小时");
				if(loginFreezeDisTime < loginPasswordLockTime){
					//如果没有超过冻结时间
					logger.error("个人会员登录错误：登录失败次数过多，会员被登录锁定！");
					throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_AUTOLOCK,"登录失败次数过多，会员被登录锁定！");
				}else{
					//如果超过冻结时间，重新计算错误次数，并解除登录冻结
					logger.debug("超过冻结时间，重新计算错误次数，并解除登录冻结");
					cycleLoginFailTimes = 0;
				}
			}
		}

		//密码检测 前台会做一次MD5
		DecryptRequest decrpytDTO = new DecryptRequest();
		decrpytDTO.setEncryptorId(managerMember.getEncryptionCode());
		decrpytDTO.setInputText(pwd);
		DecryptResponse decryptResponse = encryptorService.decrypt(decrpytDTO);

		if(!decryptResponse.getOutputText().equals(managerMember.getPwd())){
			//登录 错误次数是否隔天
			if(lastLoginErrorDate != null){//隔天
				if(!DateUtils.isSameDay(now, lastLoginErrorDate)){
					logger.debug("登录错误次数隔天，重新计算错误次数");
					cycleLoginFailTimes = 0;
				}
			}
			//登录密码连续输入错误次数限制
			Short loginPasswordErrorTimes = Short.valueOf(localCacheSystemParams.getSystemParam(RiskControlParam.GroupName,RiskControlParam.LoginPasswordErrorTimes));
			logger.debug("登录密码连续输入错误次数限制，loginPasswordErrorTimes:" + loginPasswordErrorTimes);
			cycleLoginFailTimes++;
			//登录密码连续输入错误超过限制
			if(loginPasswordErrorTimes <= cycleLoginFailTimes){
				//冻结会员状态
				ManagerMember personMemberUpdate = new ManagerMember();
				personMemberUpdate.setCode(managerMember.getCode());
				personMemberUpdate.setStatus(LockStatus.AUTOLOCK.toString());
				managerMemberDao.updateByPrimaryKeySelective(personMemberUpdate);

				//更新错误次数
				loginCheck.setCycleLoginFailTimes(cycleLoginFailTimes);
				loginCheck.setLastLoginErrorDate(now);
				loginCheckDao.updateByPrimaryKeySelective(loginCheck);

				logger.error("个人会员登录错误：登录密码错误，错误次数已超过限制，账户冻结！");
				throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_PSW_FREEZE,"警告：您"+ cycleLoginFailTimes +"次登录密码输入错误，"+ loginPasswordLockTimeStr+"小时内拒绝再次登录！");
			}else{
				ManagerMember personMemberUpdate = new ManagerMember();
				personMemberUpdate.setCode(managerMember.getCode());
				personMemberUpdate.setStatus(LockStatus.NORMAL.toString());//解除登录冻结
				managerMemberDao.updateByPrimaryKeySelective(personMemberUpdate);
				//更新错误次数
				loginCheck.setCycleLoginFailTimes(cycleLoginFailTimes);
				loginCheck.setLastLoginErrorDate(now);
				loginCheckDao.updateByPrimaryKeySelective(loginCheck);
			}
			logger.error("个人会员登录错误：登录密码错误！");
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_PSW,"个人会员登录错误：登录密码错误，您还有"+ (loginPasswordErrorTimes - cycleLoginFailTimes) + "次机会！");
		}

		//登录成功
		logger.debug("登录成功，更新最后一次登录时间及会员锁定状态,");
		cycleLoginFailTimes = 0;
		ManagerMember managerMemberUpdateTemp = new ManagerMember();
		managerMemberUpdateTemp.setCode(managerMember.getCode());
		managerMemberUpdateTemp.setStatus(LockStatus.NORMAL.toString());//解除登录冻结
		managerMemberDao.updateByPrimaryKeySelective(managerMemberUpdateTemp);
		//更新错误次数
		loginCheck.setCycleLoginFailTimes(cycleLoginFailTimes);
		loginCheck.setLastLoginErrorDate(now);
		loginCheckDao.updateByPrimaryKeySelective(loginCheck);

		//获取导购信息
		personMemberLoginReturn = new PersonMemberLoginReturn();
		GuidMember recordGuidMember = new GuidMember();
		recordGuidMember.setMobile(personMemberLogin.getMobile());
		GuidMember guidMember = guidMemberDao.findGuidMember(recordGuidMember);
		if(guidMember == null){
			logger.error("店长没有导购信息！！");
			throw new TsfaServiceException(ErrorCode.MANAGER_NOT_HAVE_GM_ERROR,"店长没有导购信息错误!");
		}else{
			personMemberLoginReturn.setMemberNoGuid(guidMember.getMemberNo());//导购编号
			personMemberLoginReturn.setMemberNameGuid(guidMember.getMemberName());//导购姓名
			personMemberLoginReturn.setQcord(guidMember.getQcord());//导购二维码
		}

		//登录返回信息
		personMemberLoginReturn.setCode(managerMember.getCode());
		personMemberLoginReturn.setMemberNoMerchant(managerMember.getMemberNoMerchant());
		//同步商户名称  
		personMemberLoginReturn.setMemberNameMerchant(merchant.getMerchantName());
		personMemberLoginReturn.setMemberNoShop(managerMember.getMemberNo());
		personMemberLoginReturn.setMemberNameShop(managerMember.getMemberName());
		personMemberLoginReturn.setMemberType(managerMember.getMemberType());
//		personMemberLoginReturn.setShopNo(managerMember.getMemberNoShop());
		
		//公司地址
//		FindShop findShop = new FindShop();
//		if(guidMember != null)
//			findShop.setShopNo(guidMember.getShopNo());
//		FindShopReturn shopReturn = shopService.findShopByShopNo(findShop);
		
		
//		personMemberLoginReturn.setCompanyAddress(shopReturn.getAddrInfo());
//		personMemberLoginReturn.setShopType(shopReturn.getShopType());
//		personMemberLoginReturn.setShopName(shopReturn.getShopName());

		personMemberLoginReturn.setMobile(managerMember.getMobile());
		personMemberLoginReturn.setEmail(managerMember.getEmail());
		personMemberLoginReturn.setProvinceCode(managerMember.getProvinceWx());
		personMemberLoginReturn.setCityCode(managerMember.getCityWx());
		personMemberLoginReturn.setAreaCode(managerMember.getAreaCode());
		personMemberLoginReturn.setAreaName(managerMember.getAreaName());
		personMemberLoginReturn.setHeadAddress(managerMember.getHeadAddress());
		personMemberLoginReturn.setGender(managerMember.getSex());
		personMemberLoginReturn.setWorkDate(managerMember.getWorkDate());
		personMemberLoginReturn.setStatus(managerMember.getStatus());
		personMemberLoginReturn.setNoWx(guidMember.getNoWx());

		//新增登录记录
		AddMemberLoginInfo addMemberLoginInfo = new AddMemberLoginInfo();
		addMemberLoginInfo.setMemberType(managerMember.getMemberType());
		addMemberLoginInfo.setMemberNo(managerMember.getMemberNo());
		addMemberLoginInfo.setIpAddress(personMemberLogin.getIpAddress());
		addMemberLoginInfo.setMac(personMemberLogin.getMac());
		addMemberLoginInfo.setNetType(personMemberLogin.getNetType());
		addMemberLoginInfo.setEquipment(personMemberLogin.getEquipment());
		addMemberLoginInfo.setAreaInfo(personMemberLogin.getAreaInfo());
		addMemberLoginInfo.setLoginArea(personMemberLogin.getLoginArea());
		addMemberLoginInfo(addMemberLoginInfo);
		return personMemberLoginReturn;
	}*/


	/**
	 * 
	 *
	 * 方法说明：导购登录
	 *
	 * @param personMemberLogin
	 * @param mobile
	 * @param pwd
	 * @param imei
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月20日
	 *
	 */
	private PersonMemberLoginReturn guidLogin(PersonMemberLogin personMemberLogin, String loginName, String pwd) {
		PersonMemberLoginReturn personMemberLoginReturn;

		//获取导购信息
		GuidMember recordGuidMember = new GuidMember();
		recordGuidMember.setLoginName(loginName);
		GuidMember guidMember = guidMemberDao.findGuidMember(recordGuidMember);

		if(guidMember == null){
			logger.info("个人会员登录错误：会员不存在！");
			throw new TsfaServiceException(ErrorCode.PERSON_ERROR_NOT_EXIST,"个人会员登录错误：会员不存在！");
		}

		if(MemberStatus.CANCEL.toString().equals(guidMember.getStatus())){
			logger.error("个人会员登录错误：会员被注销！");
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_FREEZE,"个人会员登录错误：会员被注销！");
		}


		//登录密码锁定时间（小时）
		String loginPasswordLockTimeStr = localCacheSystemParams.getSystemParam(RiskControlParam.GroupName,RiskControlParam.LoginPasswordLockTime);
		LoginCheck findLoginCheck = new LoginCheck();
		findLoginCheck.setMemberNo(guidMember.getMemberNo());

		LoginCheck loginCheck = loginCheckDao.findLoginCheck(findLoginCheck);
		//登录密码连续输入错误次数
		if(loginCheck.getCycleLoginFailTimes() == null)
			loginCheck.setCycleLoginFailTimes(0);
		Integer cycleLoginFailTimes = loginCheck.getCycleLoginFailTimes();
		Date lastLoginErrorDate = loginCheck.getLastLoginErrorDate();
		Date now = new Date();
		
		if(LockStatus.AUTOLOCK.toString().equals(loginCheck.getLockStatus())){
			if(lastLoginErrorDate != null){
				double loginPasswordLockTime = Double.valueOf(loginPasswordLockTimeStr);
				logger.debug("登录冻结时间，loginPasswordLockTime:" + loginPasswordLockTime);
				double loginFreezeDisTime = ((double)(System.currentTimeMillis() - lastLoginErrorDate.getTime()))/(1000*60*60);
				logger.debug("登录冻结时间差，loginFreezeDisTime:" + loginFreezeDisTime +"小时");
				if(loginFreezeDisTime < loginPasswordLockTime){
					//如果没有超过冻结时间
					logger.error("个人会员登录错误：登录失败次数过多，会员被登录锁定！");
					throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_AUTOLOCK,"登录失败次数过多，会员被登录锁定！");
				}else{
					//如果超过冻结时间，重新计算错误次数，并解除登录冻结
					logger.debug("超过冻结时间，重新计算错误次数，并解除登录冻结");
					cycleLoginFailTimes = 0;
				}
			}
		}

		//密码检测 前台会做一次MD5
		DecryptRequest decrpytDTO = new DecryptRequest();
		decrpytDTO.setEncryptorId(guidMember.getEncryptionCode());
		decrpytDTO.setInputText(pwd);
		DecryptResponse decryptResponse = encryptorService.decrypt(decrpytDTO);

		if(!decryptResponse.getOutputText().equals(guidMember.getPwd())){
			//登录 错误次数是否隔天
			if(lastLoginErrorDate != null){//隔天
				if(!DateUtils.isSameDay(now, lastLoginErrorDate)){
					logger.debug("登录错误次数隔天，重新计算错误次数");
					cycleLoginFailTimes = 0;
				}
			}
			//登录密码连续输入错误次数限制
			Short loginPasswordErrorTimes = Short.valueOf(localCacheSystemParams.getSystemParam(RiskControlParam.GroupName,RiskControlParam.LoginPasswordErrorTimes));
			logger.debug("登录密码连续输入错误次数限制，loginPasswordErrorTimes:" + loginPasswordErrorTimes);
			cycleLoginFailTimes++;
			//登录密码连续输入错误超过限制
			if(loginPasswordErrorTimes <= cycleLoginFailTimes){
				//冻结导购状态
				GuidMember personMemberUpdate = new GuidMember();
				personMemberUpdate.setCode(guidMember.getCode());
				personMemberUpdate.setStatus(LockStatus.AUTOLOCK.toString());
				guidMemberDao.updateByPrimaryKeySelective(personMemberUpdate);

				//更新错误次数
				loginCheck.setCycleLoginFailTimes(cycleLoginFailTimes);
				loginCheck.setLastLoginErrorDate(now);
				loginCheckDao.updateByPrimaryKeySelective(loginCheck);

				logger.error("个人会员登录错误：登录密码错误，错误次数已超过限制，账户冻结！");
				throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_PSW_FREEZE,"警告：您"+ cycleLoginFailTimes +"次登录密码输入错误，"+ loginPasswordLockTimeStr+"小时内拒绝再次登录！");
			}else{
				GuidMember personMemberUpdate = new GuidMember();
				personMemberUpdate.setCode(guidMember.getCode());
				personMemberUpdate.setStatus(LockStatus.NORMAL.toString());//解除登录冻结
				guidMemberDao.updateByPrimaryKeySelective(personMemberUpdate);
				//更新错误次数
				loginCheck.setCycleLoginFailTimes(cycleLoginFailTimes);
				loginCheck.setLastLoginErrorDate(now);
				loginCheckDao.updateByPrimaryKeySelective(loginCheck);
			}
			logger.error("个人会员登录错误：登录密码错误！");
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_PSW,"个人会员登录错误：登录密码错误，您还有"+ (loginPasswordErrorTimes - cycleLoginFailTimes) + "次机会！");
		}

		//登录成功
		logger.debug("登录成功，更新最后一次登录时间及会员锁定状态,");
		cycleLoginFailTimes = 0;
		GuidMember personMemberUpdate = new GuidMember();
		personMemberUpdate.setCode(guidMember.getCode());
		personMemberUpdate.setStatus(LockStatus.NORMAL.toString());//解除登录冻结
		guidMemberDao.updateByPrimaryKeySelective(personMemberUpdate);
		
		//更新最后登录成功时间
		loginCheck.setCycleLoginFailTimes(cycleLoginFailTimes);
		loginCheck.setLastLoginDate(now);
		loginCheckDao.updateByPrimaryKeySelective(loginCheck);
		
		personMemberLoginReturn = new PersonMemberLoginReturn();
		if(personMemberLogin.isCheckGmAssistant()) {//当APP登录才检测导购助手
			FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
			findGmAssistantShop.setLoginName(guidMember.getLoginName());
	//		findGmAssistantShop.setSource(true);	//排除下属微信 DZP 2019-04-22 四月份需求
			List<FindGmAssistantShopReturn> list =gmAssistantShopDao.findGmAssistantShopList(findGmAssistantShop);
			// 此版本允许无导购 2019-07-12
	//		if(null == list || list.size()==0) {
	//			// 热文版默认无导购助手
	//			if (!personMemberLogin.isHasRwVersion()) {
	//				throw new TsfaServiceException(ErrorCode.GM_ASSIS_NOT_EXIXS_ERROR, "没有绑定导购助手");
	//			}
	//		}
			
	
	        if(list != null && list.size() > 0) {
	        	FindGmAssistantShopReturn as =  list.get(0);
	        	personMemberLoginReturn.setNoWx(as.getNoWx());
	        	
	        	ShopTerminal shopTerminal = shopTerminalDao.selectByWx(as.getNoWx());
	        	personMemberLoginReturn.setQcord(shopTerminal.getQcord());
	        	personMemberLoginReturn.setNickName(shopTerminal.getNickname());
	        	personMemberLoginReturn.setAlias(shopTerminal.getAlias());
	        	personMemberLoginReturn.setWxHeadUrl(shopTerminal.getHeadurl());
	        	personMemberLoginReturn.setShopName(shopTerminal.getShopName());
	        	/**
	        	 * 更新当前切换的微信最后登录成功时间
	        	 */
	        	GmAssistantShop updateRecord = new GmAssistantShop();
	        	updateRecord.setNoWx(as.getNoWx());
	        	updateRecord.setAssistantNo(guidMember.getMemberNo());
	        	gmAssistantShopDao.updateLastDate(updateRecord);
			}
		}
		
		
		//登录返回信息
		personMemberLoginReturn.setCode(guidMember.getCode());
		personMemberLoginReturn.setMemberNoGuid(guidMember.getMemberNo());
		personMemberLoginReturn.setMemberNameGuid(guidMember.getMemberName());
		personMemberLoginReturn.setMemberNoMerchant(guidMember.getMerchantNo());
		personMemberLoginReturn.setMemberNameMerchant(guidMember.getMerchantName());
		personMemberLoginReturn.setMobile(guidMember.getMobile());
		personMemberLoginReturn.setEmail(guidMember.getEmail());
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
		
		//新增登录记录
		AddMemberLoginInfo addMemberLoginInfo = new AddMemberLoginInfo();				
		addMemberLoginInfo.setMemberType(MemberType.GUID.toString());
		addMemberLoginInfo.setMemberNo(guidMember.getMemberNo());
		addMemberLoginInfo.setIpAddress(personMemberLogin.getIpAddress());
		addMemberLoginInfo.setMac(personMemberLogin.getMac());
		addMemberLoginInfo.setNetType(personMemberLogin.getNetType());
		addMemberLoginInfo.setEquipment(personMemberLogin.getEquipment());
		addMemberLoginInfo.setAreaInfo(personMemberLogin.getAreaInfo());
		addMemberLoginInfo.setLoginArea(personMemberLogin.getLoginArea());
		addMemberLoginInfo(addMemberLoginInfo);
		return personMemberLoginReturn;
	}

	
	
	/**
	 * 
	 *
	 * 方法说明：切换导购账号登录
	 *
	 * @param personMemberLogin
	 * @param mobile
	 * @param pwd
	 * @param imei
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月20日
	 *
	 */
	private PersonMemberLoginReturn changeGuidLogin(PersonMemberLogin personMemberLogin, String loginName) {
		PersonMemberLoginReturn personMemberLoginReturn;

		//获取导购信息
		GuidMember recordGuidMember = new GuidMember();
		recordGuidMember.setLoginName(loginName);
		GuidMember guidMember = guidMemberDao.findGuidMember(recordGuidMember);

		if(guidMember == null){
			logger.info("个人会员登录错误：会员不存在！");
			throw new TsfaServiceException(ErrorCode.PERSON_ERROR_NOT_EXIST,"个人会员登录错误：会员不存在！");
		}

		if(MemberStatus.CANCEL.toString().equals(guidMember.getStatus())){
			logger.error("个人会员登录错误：会员被注销！");
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_FREEZE,"个人会员登录错误：会员被注销！");
		}

		//登录成功
		logger.debug("登录成功，更新最后一次登录时间及会员锁定状态,");
		GuidMember personMemberUpdate = new GuidMember();
		personMemberUpdate.setCode(guidMember.getCode());
		personMemberUpdate.setStatus(LockStatus.NORMAL.toString());//解除登录冻结
		guidMemberDao.updateByPrimaryKeySelective(personMemberUpdate);
	
		personMemberLoginReturn = new PersonMemberLoginReturn();

        personMemberLoginReturn.setNoWx(personMemberLogin.getNoWx());
        
        /**
    	 * 更新当前切换的微信最后登录成功时间
    	 */
    	GmAssistantShop updateRecord = new GmAssistantShop();
    	updateRecord.setNoWx(personMemberLogin.getNoWx());
    	updateRecord.setAssistantNo(guidMember.getMemberNo());
    	gmAssistantShopDao.updateLastDate(updateRecord);
        	
        ShopTerminal shopTerminal = shopTerminalDao.selectByWx(personMemberLogin.getNoWx());
        personMemberLoginReturn.setQcord(shopTerminal.getQcord());
        personMemberLoginReturn.setNickName(shopTerminal.getNickname());
    	personMemberLoginReturn.setAlias(shopTerminal.getAlias());
    	personMemberLoginReturn.setWxHeadUrl(shopTerminal.getHeadurl());
    	personMemberLoginReturn.setShopName(shopTerminal.getShopName());
    	
		//登录返回信息
		personMemberLoginReturn.setCode(guidMember.getCode());
		personMemberLoginReturn.setMemberNoGuid(guidMember.getMemberNo());
		personMemberLoginReturn.setMemberNameGuid(guidMember.getMemberName());
		personMemberLoginReturn.setMemberNoMerchant(guidMember.getMerchantNo());
		personMemberLoginReturn.setMemberNameMerchant(guidMember.getMerchantName());
		personMemberLoginReturn.setMobile(guidMember.getMobile());
		personMemberLoginReturn.setEmail(guidMember.getEmail());
		personMemberLoginReturn.setProvinceCode(guidMember.getProvinceCode());
		personMemberLoginReturn.setCityCode(guidMember.getCityCode());
		personMemberLoginReturn.setAreaCode(guidMember.getAreaCode());
		personMemberLoginReturn.setAreaName(guidMember.getAreaName());
		
		personMemberLoginReturn.setGender(guidMember.getGender());
		personMemberLoginReturn.setMemberType(MemberType.GUID.toString());
		personMemberLoginReturn.setWorkDate(guidMember.getWorkDate());
		personMemberLoginReturn.setHeadAddress(guidMember.getHeadAddress());
		personMemberLoginReturn.setStatus(guidMember.getStatus());

		//新增登录记录
		AddMemberLoginInfo addMemberLoginInfo = new AddMemberLoginInfo();				
		addMemberLoginInfo.setMemberType(MemberType.GUID.toString());
		addMemberLoginInfo.setMemberNo(guidMember.getMemberNo());
		addMemberLoginInfo.setIpAddress(personMemberLogin.getIpAddress());
		addMemberLoginInfo.setMac(personMemberLogin.getMac());
		addMemberLoginInfo.setNetType(personMemberLogin.getNetType());
		addMemberLoginInfo.setEquipment(personMemberLogin.getEquipment());
		addMemberLoginInfo.setAreaInfo(personMemberLogin.getAreaInfo());
		addMemberLoginInfo.setLoginArea(personMemberLogin.getLoginArea());
		addMemberLoginInfo(addMemberLoginInfo);
		return personMemberLoginReturn;
	}

	/**
	 * H5登录（BOOS，区域经理，店长）
	 * 不校验手机串号（前端拿不到）.
	 *	如果既有openIdGzhWx ，也有手机号，视为第一次登录
	 * 	如果只有openIdGzhWx ，没有手机号，视为微信自动登录
	 *
	 * @param personMemberLogin the person member login
	 * @return the person member login return
	 * @throws TsfaServiceException the tsfa service exception
	 */
	@Override
	public PersonMemberLoginReturn personMemberLoginH5(
			PersonMemberLogin personMemberLogin) throws TsfaServiceException {
		logger.debug("personMemberLoginH5(PersonMemberLogin personMemberLogin={}) - start", personMemberLogin); 

		AssertUtils.notNull(personMemberLogin);
		//AssertUtils.notAllNullAndEmpty(personMemberLogin.getMobile(), personMemberLogin.getOpenIdGzhWx(),"手机号、公众号OPENID不能全部为空！");
		PersonMemberLoginReturn personMemberLoginReturn = null;
		String mobile =personMemberLogin.getMobile();
		String pwd = personMemberLogin.getPwd();
		String openIdGzhWx = personMemberLogin.getOpenIdGzhWx();
		try{
			if(StringUtils.isEmpty(personMemberLogin.getMobile()) && StringUtils.isEmpty(personMemberLogin.getOpenIdGzhWx()) ){
				logger.error("手机号、公众号OPENID全部为空，直接返回NULL，提示前端需要账号密码登录");
				throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_PSW_LOGIN,"个人会员登录错误：需要密码登录");
			}
			ManagerMember managerMember = null;

			//如果只有openIdGzhWx ，没有手机号，视为微信自动登录
			if(!StringUtils.isEmpty(openIdGzhWx) && StringUtils.isEmpty(mobile)){
				ManagerMember record = new ManagerMember();
				record.setOpenIdGzhWx(openIdGzhWx);
				//获取管理人员信息
				managerMember = managerMemberDao.findManagerMember(record);
				if(managerMember == null)//如果openIdGzhWx对应的数据不存在，返回NULL，需要用户重新登录
					throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_PSW_LOGIN,"个人会员登录错误：需要密码登录");
				
				if(MemberStatus.FREEZE.toString().equals(managerMember.getStatus())){
					logger.error("个人会员登录错误：会员被冻结！");
					throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_FREEZE,"个人会员登录错误：会员被冻结！");
				}
			}
			
			//如果既有openIdGzhWx(有可能无法获取) ，也有手机号，视为第一次登录
			if(!StringUtils.isEmpty(mobile)){
				ManagerMember record = new ManagerMember();
				record.setMobile(mobile);
				//获取管理人员信息
				managerMember = managerMemberDao.findManagerMember(record);
				
				if(managerMember == null){
					logger.info("个人会员登录错误：会员不存在！");
					throw new TsfaServiceException(ErrorCode.PERSON_ERROR_NOT_EXIST,"个人会员登录错误：会员不存在！");
				}
				if(MemberStatus.FREEZE.toString().equals(managerMember.getStatus())){
					logger.error("个人会员登录错误：会员被冻结！");
					throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_FREEZE,"个人会员登录错误：会员被冻结！");
				}

				//登录密码锁定时间（小时）
				String loginPasswordLockTimeStr = localCacheSystemParams.getSystemParam(RiskControlParam.GroupName,RiskControlParam.LoginPasswordLockTime);
				LoginCheck findLoginCheck = new LoginCheck();
				findLoginCheck.setMemberNo(managerMember.getMemberNo());

				LoginCheck loginCheck = loginCheckDao.findLoginCheck(findLoginCheck);
				//登录密码连续输入错误次数
				if(loginCheck.getCycleLoginFailTimes() == null) loginCheck.setCycleLoginFailTimes(0);
				Integer cycleLoginFailTimes = loginCheck.getCycleLoginFailTimes();
				Date lastLoginErrorDate = loginCheck.getLastLoginErrorDate();
				Date now = new Date();

				//会员是自动锁定
				if(LockStatus.AUTOLOCK.toString().equals(loginCheck.getLockStatus())){
					if(lastLoginErrorDate != null){
						double loginPasswordLockTime = Double.valueOf(loginPasswordLockTimeStr);
						logger.debug("登录冻结时间，loginPasswordLockTime:" + loginPasswordLockTime);
						double loginFreezeDisTime = ((double)(System.currentTimeMillis() - lastLoginErrorDate.getTime()))/(1000*60*60);
						logger.debug("登录冻结时间差，loginFreezeDisTime:" + loginFreezeDisTime +"小时");
						if(loginFreezeDisTime < loginPasswordLockTime){
							//如果没有超过冻结时间
							logger.error("个人会员登录错误：登录失败次数过多，会员被登录锁定！");
							throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_AUTOLOCK,"登录失败次数过多，会员被登录锁定！");
						}else{
							//如果超过冻结时间，重新计算错误次数，并解除登录冻结
							logger.debug("超过冻结时间，重新计算错误次数，并解除登录冻结");
							cycleLoginFailTimes = 0;
						}
					}
				}

				//密码检测 前台会做一次MD5
				DecryptRequest decrpytDTO = new DecryptRequest();
				decrpytDTO.setEncryptorId(managerMember.getEncryptionCode());
				decrpytDTO.setInputText(pwd);
				DecryptResponse decryptResponse = encryptorService.decrypt(decrpytDTO);

				if(!decryptResponse.getOutputText().equals(managerMember.getPwd())){
					//登录 错误次数是否隔天
					if(lastLoginErrorDate != null){//隔天
						if(!DateUtils.isSameDay(now, lastLoginErrorDate)){
							logger.debug("登录错误次数隔天，重新计算错误次数");
							cycleLoginFailTimes = 0;
						}
					}
					//登录密码连续输入错误次数限制
					Short loginPasswordErrorTimes = Short.valueOf(localCacheSystemParams.getSystemParam(RiskControlParam.GroupName,RiskControlParam.LoginPasswordErrorTimes));
					logger.debug("登录密码连续输入错误次数限制，loginPasswordErrorTimes:" + loginPasswordErrorTimes);
					cycleLoginFailTimes++;
					//登录密码连续输入错误超过限制
					if(loginPasswordErrorTimes <= cycleLoginFailTimes){
						//冻结会员状态
						ManagerMember personMemberUpdate = new ManagerMember();
						personMemberUpdate.setCode(managerMember.getCode());
						personMemberUpdate.setStatus(LockStatus.AUTOLOCK.toString());
						managerMemberDao.updateByPrimaryKeySelective(personMemberUpdate);

						//更新错误次数
						loginCheck.setCycleLoginFailTimes(cycleLoginFailTimes);
						loginCheck.setLastLoginErrorDate(now);
						loginCheckDao.updateByPrimaryKeySelective(loginCheck);

						logger.error("个人会员登录错误：登录密码错误，错误次数已超过限制，账户冻结！");
						throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_PSW_FREEZE,"警告：您"+ cycleLoginFailTimes +"次登录密码输入错误，"+ loginPasswordLockTimeStr+"小时内拒绝再次登录！");
					}else{
						ManagerMember personMemberUpdate = new ManagerMember();
						personMemberUpdate.setCode(managerMember.getCode());
						personMemberUpdate.setStatus(LockStatus.NORMAL.toString());//解除登录冻结
						managerMemberDao.updateByPrimaryKeySelective(personMemberUpdate);
						//更新错误次数
						loginCheck.setCycleLoginFailTimes(cycleLoginFailTimes);
						loginCheck.setLastLoginErrorDate(now);
						loginCheckDao.updateByPrimaryKeySelective(loginCheck);
					}
					logger.error("个人会员登录错误：登录密码错误！");
					throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_PSW,"个人会员登录错误：登录密码错误，您还有"+ (loginPasswordErrorTimes - cycleLoginFailTimes) + "次机会！");
				}

				//登录成功
				logger.debug("登录成功，更新最后一次登录时间及会员锁定状态,");
				cycleLoginFailTimes = 0;
				ManagerMember managerMemberUpdateTemp = new ManagerMember();
				managerMemberUpdateTemp.setCode(managerMember.getCode());
				managerMemberUpdateTemp.setStatus(LockStatus.NORMAL.toString());//解除登录冻结
				if(!StringUtils.isEmpty(openIdGzhWx))
					managerMemberUpdateTemp.setOpenIdGzhWx(openIdGzhWx);//更新公众号
				managerMemberDao.updateByPrimaryKeySelective(managerMemberUpdateTemp);
				//更新错误次数
				loginCheck.setCycleLoginFailTimes(cycleLoginFailTimes);
				loginCheck.setLastLoginErrorDate(now);
				loginCheckDao.updateByPrimaryKeySelective(loginCheck);
			}
			

			//登录返回信息
			personMemberLoginReturn = new PersonMemberLoginReturn();
			personMemberLoginReturn.setCode(managerMember.getCode());

			//获取导购信息
			GuidMember recordGuidMember = new GuidMember();
			recordGuidMember.setMobile(mobile);
			GuidMember guidMember = guidMemberDao.findGuidMember(recordGuidMember);
			if(guidMember!=null){
				personMemberLoginReturn.setMemberNoGuid(guidMember.getMemberNo());
				personMemberLoginReturn.setMemberNameGuid(guidMember.getMemberName());
			}
			
			personMemberLoginReturn.setMobile(managerMember.getMobile());
			personMemberLoginReturn.setEmail(managerMember.getEmail());
			personMemberLoginReturn.setProvinceCode(managerMember.getProvinceCode());
			personMemberLoginReturn.setCityCode(managerMember.getCityCode());
			personMemberLoginReturn.setAreaCode(managerMember.getAreaCode());
			personMemberLoginReturn.setHeadAddress(managerMember.getHeadAddress());
			personMemberLoginReturn.setGender(managerMember.getSex());
			personMemberLoginReturn.setMemberNoMerchant(managerMember.getMemberNoMerchant());
			personMemberLoginReturn.setMemberNameMerchant(managerMember.getMemberNameMerchant());
			personMemberLoginReturn.setMemberType(managerMember.getMemberType());
			personMemberLoginReturn.setWorkDate(managerMember.getWorkDate());
			personMemberLoginReturn.setStatus(managerMember.getStatus());
			String uploadUrl =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
			personMemberLoginReturn.setUploadUrl(uploadUrl);
			//新增登录记录
			AddMemberLoginInfo addMemberLoginInfo = new AddMemberLoginInfo();
			addMemberLoginInfo.setMemberType(managerMember.getMemberType());
			addMemberLoginInfo.setMemberNo(managerMember.getMemberNo());
			addMemberLoginInfo.setIpAddress(personMemberLogin.getIpAddress());
			addMemberLoginInfo.setMac(personMemberLogin.getMac());
			addMemberLoginInfo.setNetType(personMemberLogin.getNetType());
			addMemberLoginInfo.setEquipment(personMemberLogin.getEquipment());
			addMemberLoginInfo.setAreaInfo(personMemberLogin.getAreaInfo());
			addMemberLoginInfo.setLoginArea(personMemberLogin.getLoginArea());
			addMemberLoginInfo(addMemberLoginInfo);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("个人会员登录错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR,"个人会员登录错误！",e);

		}

		logger.debug("personMemberLogin(PersonMemberLogin) - end"); 
		return personMemberLoginReturn;
	}


	/**
	 * 方法说明：添加用户登录记录.
	 *
	 * @param addMemberLoginInfo the add member login info
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 段志鹏 CreateDate: 2017年6月20日
	 */
	private void addMemberLoginInfo(AddMemberLoginInfo addMemberLoginInfo) throws TsfaServiceException {
		logger.debug("addMemberLoginInfo(AddMemberLoginInfo addMemberLoginInfo={}) - start", addMemberLoginInfo); 

		try{
			memberLoginInfoService.addMemberLoginInfo(addMemberLoginInfo);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("添加会员登录信息错误",e);
			throw new TsfaServiceException(ErrorCode.PERSON_ADD_LOGIN_INFO_ERROR,"添加会员登录信息错误",e);
		}
	}


	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IMemberLoginService#updatePwd(com.lj.business.member.dto.UpdatePwdDto)
	 */
	/*@Override
	public void updatePwd(UpdatePwdDto updatePwdDto)
			throws TsfaServiceException {
		logger.debug("updatePwd(UpdatePwdDto updatePwdDto=" + updatePwdDto + ") - start");  //$NON-NLS-2$

		String mobile =updatePwdDto.getMobile();
		String merchantNo =updatePwdDto.getMerchantNo();
		AssertUtils.notNullAndEmpty(mobile,"手机号不能为空");
		AssertUtils.notNullAndEmpty(merchantNo,"商户编号不能为空");
		try{
			String validateCode = "123456";		
			if(!updatePwdDto.getValidateCode().equals(validateCode)){
				logger.error("验证码错误！");
				throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR_PSW,"验证码错误");
			}

			//获取导购信息
			GuidMember recordGuidMember = new GuidMember();
			recordGuidMember.setMobile(mobile);
			recordGuidMember.setMerchantNo(merchantNo);
			GuidMember guidMember = guidMemberDao.findGuidMember(recordGuidMember);

			//获取管理人员信息
			ManagerMember record = new ManagerMember();
			record.setMobile(mobile);
			record.setMemberNoMerchant(merchantNo);
			ManagerMember managerMember = managerMemberDao.findManagerMember(record);

			if(guidMember==null && managerMember==null){
				logger.info("用户不存在！");
				throw new TsfaServiceException(ErrorCode.PERSON_ERROR_NOT_EXIST,"用户不存在！"); 
			}

			//XXX LEOPENG 待完善，管理员、导购优先级问题
			if(guidMember!=null){
				//密码检测 前台会做一次MD5
				updatePwdDto.setCode(guidMember.getCode());
				guidMemberService.updatePwd(updatePwdDto);
			}

			if(managerMember!=null){
				updatePwdDto.setCode(managerMember.getCode());
				managerMemberService.updatePwd(updatePwdDto);
			}
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("修改登录密码错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR,"修改登录密码错误！",e);
		}
	}*/


	@Override
	public String getValideCode(String mobile) throws TsfaServiceException {
		logger.debug("getValideCode(String mobile=" + mobile + ") - start");  //$NON-NLS-2$
		AssertUtils.notNullAndEmpty(mobile,"手机号不能为空");
		try{
			String validateCode = "123456";		//TODO 获取短信验证码

			//获取导购信息
			GuidMember recordGuidMember = new GuidMember();
			recordGuidMember.setMobile(mobile);
			GuidMember guidMember = guidMemberDao.findGuidMember(recordGuidMember);

			//获取管理人员信息
			ManagerMember record = new ManagerMember();
			record.setMobile(mobile);
			ManagerMember managerMember = managerMemberDao.findManagerMember(record);

			if(guidMember==null && managerMember==null){
				logger.info("用户不存在！");
				throw new TsfaServiceException(ErrorCode.PERSON_ERROR_NOT_EXIST,"用户不存在！"); 
			}
			return validateCode;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("修改登录密码错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR,"修改登录密码错误！",e);
		}
	}



}
