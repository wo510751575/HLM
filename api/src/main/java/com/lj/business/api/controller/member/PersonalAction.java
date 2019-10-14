package com.lj.business.api.controller.member;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.domain.msg.SmsCodeSenderRequest;
import com.lj.business.api.domain.msg.SmsCodeVerifyRequest;
import com.lj.business.api.service.SmsCodeService;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dto.FindAgreementAndMerchantReturnDto;
import com.lj.business.member.dto.FindAgreementMerchant;
import com.lj.business.member.dto.FindAgreementMerchantReturnDto;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindManagerMemberPage;
import com.lj.business.member.dto.FindManagerMemberPageReturn;
import com.lj.business.member.dto.FindManagersDto;
import com.lj.business.member.dto.FindManagersReturnDto;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.GuidAndManager;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.dto.ManagerMemberDto;
import com.lj.business.member.dto.ManagerMemberReturnDto;
import com.lj.business.member.dto.UpdateManagerMemberDto;
import com.lj.business.member.dto.UpdateManagerOrGuidPwdDto;
import com.lj.business.member.dto.UpdatePwdDto;
import com.lj.business.member.service.IAgreementMerchantService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.IMemberLoginService;
import com.lj.business.member.service.IMerchantService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.distributecache.IDistributeCache;
import com.lj.messagecenter.email.service.ISmsSenderService;
import com.lj.messagecenter.msg.dto.AddSuggest;
import com.lj.messagecenter.msg.service.ISuggestService;

/**
 * 
 * 
 * 类说明：个人中心
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 邹磊
 * 
 *         CreateDate: 2017年7月17日
 */
@Controller
@RequestMapping(value = "/personal/")
public class PersonalAction {
	@Resource
	private IAgreementMerchantService agreementMerchantService;
	@Resource
	private IMerchantService merchantService;
	@Resource
	public IGuidMemberService guidMemberService;
	@Resource
	public IManagerMemberService managerMemberService;
	@Resource
	private ISuggestService suggestService;
	@Resource
	private IMemberLoginService memberLoginService;
	@Resource
	private SmsCodeService smsCodeService;
	@Resource
	private IDistributeCache distributeCache;
	
	@Resource
	private  LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	@Resource 
	private ISmsSenderService smsSenderService;
	
	private static Logger logger = LoggerFactory.getLogger(MemberAction.class);

	/**
	 * 
	 *
	 * 方法说明：查找导购/店长(个人信息)
	 *
	 * @param managerMemberDto
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年7月13日
	 *
	 */
	@RequestMapping(value = "findGuidOrManager.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GuidAndManager findGuidOrManager(ManagerMemberDto managerMemberDto) {
		AssertUtils.notNull(managerMemberDto.getMemberNo(), "店长编号不能为空");
		GuidAndManager findGuidOrManager = new GuidAndManager();
		if (managerMemberDto.getMemberNo() != null) {
			ManagerMemberReturnDto findManagerMember = managerMemberService
					.findManager(managerMemberDto);
			findGuidOrManager.setManagermember(findManagerMember);
			return findGuidOrManager;
		} else {
			FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
			findGuidMemberDto.setMemberNo(managerMemberDto.getMemberNo());
			GuidMemberReturnDto findGuidMember = guidMemberService
					.findGuid(findGuidMemberDto);
			findGuidOrManager.setGuidMember(findGuidMember);
			return findGuidOrManager;
		}
	}

	/**
	 * 
	 *
	 * 方法说明：更新导购/店长信息(个人中心)
	 *
	 * @param updateManagerMemberDto
	 * @param updateGuidMemberDto
	 *
	 * @author 邹磊 CreateDate: 2017年7月13日
	 *
	 */
	@RequestMapping(value = "updateGuidOrManager.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	 public GeneralResponse updateGuidOrManage(UpdateManagerMemberDto updateManagerMemberDto) {
		managerMemberService.updateManager(updateManagerMemberDto);
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 *
	 * 方法说明：查找导购/店长(通讯录)
	 *
	 * @param findManagerMemberPage
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年7月13日
	 *
	 */
	@RequestMapping(value = "findGuidsAndManagers.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<FindManagersReturnDto> findGuidsAndManagers(FindManagersDto findManagersDto) {
			List<FindManagersReturnDto> findManagers = managerMemberService.findManagers(findManagersDto);
			return findManagers;
	}

	/**
	 * 
	 *
	 * 方法说明：添加反馈信息(个人中心-反馈)
	 *
	 * @param addSuggest
	 *
	 * @author 邹磊 CreateDate: 2017年7月10日
	 *
	 */
	@RequestMapping(value = "addSuggest.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse addSuggest(AddSuggest addSuggest) {
		suggestService.addSuggest(addSuggest);
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 *
	 * 方法说明：查找商户信息(关于我们)
	 *
	 * @param findMerchantPage
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年7月7日
	 *
	 */
	@RequestMapping(value = "selectMerchant.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public FindAgreementAndMerchantReturnDto selectMerchant(
			String merchantNo, String appName) {
		FindAgreementAndMerchantReturnDto findAgreementAndMerchantReturnDto = new FindAgreementAndMerchantReturnDto();
		
		FindMerchantDto findMerchantDto = new FindMerchantDto();
		findMerchantDto.setMerchantNo(merchantNo);
		FindMerchantReturnDto selectMerchant = merchantService.selectMerchant(findMerchantDto);
		selectMerchant.setLogoAddr("/app/logo/" + StringUtils.toString(appName) + "logo.png");
		findAgreementAndMerchantReturnDto.setFindMerchantReturnDto(selectMerchant);
		
		FindAgreementMerchant findAgreementMerchant = new FindAgreementMerchant(); 
		findAgreementMerchant.setMerchantNo(merchantNo);
		FindAgreementMerchantReturnDto agreementMerchant = agreementMerchantService.findAgreementMerchant(findAgreementMerchant);
		
		findAgreementAndMerchantReturnDto.setFindAgreementMerchantReturnDto(agreementMerchant);
		return findAgreementAndMerchantReturnDto;
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改密码
	 *
	 * @param updateManagerOrGuidPwdDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 邹磊 CreateDate: 2017年7月26日
	 *@deprecated
	 */
	@RequestMapping(value="updateManagerOrGuidForPwd.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse updateManagerOrGuidForPwd(UpdateManagerOrGuidPwdDto updateManagerOrGuidPwdDto) throws TsfaServiceException {
		AssertUtils.notNull(updateManagerOrGuidPwdDto);
		managerMemberService.updateManagerForPwd(updateManagerOrGuidPwdDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改密码
	 *
	 * @param updatePwdDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年8月9日
	 *
	 */
	@RequestMapping(value="updatePwd.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse updatePwd(UpdatePwdDto updatePwdDto) throws TsfaServiceException {
		logger.debug("updatePwd(UpdatePwdDto updatePwdDto=" + updatePwdDto + ") - start");  //$NON-NLS-2$
		
		String mobile =updatePwdDto.getMobile();
		//String merchantNo =updatePwdDto.getMerchantNo();
		AssertUtils.notNullAndEmpty(mobile,"手机号不能为空");
		//AssertUtils.notNullAndEmpty(merchantNo,"商户编号不能为空");
		AssertUtils.notNullAndEmpty(updatePwdDto.getValidateCode(),"验证码不能为空");
		try{
			SmsCodeVerifyRequest request = new SmsCodeVerifyRequest();
			request.setMobile(mobile);
			request.setSmsCode(updatePwdDto.getValidateCode());
			request.setProcessFlag(false);	//不返回处理码
			
			if(smsCodeService.verify(request).equals("")){
				logger.debug("验证通过");
				//获取导购信息
				FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
				findGuidMemberPage.setMobile(mobile);
				List<FindGuidMemberPageReturn> guidMemberList = guidMemberService.findGuidMembers(findGuidMemberPage);
				
				//获取管理人员信息
				FindManagerMemberPage findManagerMemberPage = new FindManagerMemberPage();
				findManagerMemberPage.setMobile(mobile);
				List<FindManagerMemberPageReturn> managerMemberList = managerMemberService.findManagerMembers(findManagerMemberPage);
				
				if(guidMemberList.size()<=0 && managerMemberList.size()<=0){
					logger.info("用户不存在！");
					throw new TsfaServiceException(ErrorCode.PERSON_ERROR_NOT_EXIST,"用户不存在！"); 
				}
				if(guidMemberList.size()>0){
					FindGuidMemberPageReturn guidMember = guidMemberList.get(0);
					updatePwdDto.setCode(guidMember.getCode());
					guidMemberService.updatePwd(updatePwdDto);
				}
				
				if(managerMemberList.size()>0){
					FindManagerMemberPageReturn managerMember=managerMemberList.get(0);
					updatePwdDto.setCode(managerMember.getCode());
					managerMemberService.updatePwd(updatePwdDto);
				}
				
				//发送提醒短信 //XXX LEOPENG 模板需要申请，暂时无法申请下来，注释先
				/*TemplateSmsMessage tsm = new TemplateSmsMessage();	// 发送提醒短信
				tsm.setTelphoneNo(request.getMobile());
				tsm.setTemplateId("sms_update_psw");
				Map<String,String> contentMap = new HashMap<String,String>();
				String templateCode =  localCacheSystemParams.getSystemParam(SystemAliasName.msg.toString(), MsgConstants.ALIDAYU, MsgConstants.TEMPLATE_CODE_PSW);
				contentMap.put("templateCode", templateCode);
				String account = request.getMobile();
				String time = DateUtils.formatDate(new Date(), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss);
				String templateParam = "{\"account\":\""+ account +"\",\"time\":\""+ time +"\"}";
				contentMap.put("templateParam", templateParam );
				//兼容性设置
				contentMap.put("account", account );
				contentMap.put("time", time );
				contentMap.put("senderName", "扬恩科技");
				
				tsm.setContentMap(contentMap);
				smsSenderService.sendTemplateSms(tsm);*/
				
				
			}
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("修改登录密码错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_LOGIN_ERROR,"修改登录密码错误！",e);
		}
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取短信验证码
	 * @param updatePwdDto
	 * @return
	 * @throws TsfaServiceException
	 *	
	 * @author 段志鹏 CreateDate: 2017年8月9日
	 *
	 */
	@RequestMapping(value="getValideCode.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse getValideCode(String mobile) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(mobile,"手机号不能为空");
		SmsCodeSenderRequest request = new SmsCodeSenderRequest();
		request.setMobile(mobile);
		request.setExpireSeconds(180);	//180秒有效			TODO
		request.setSenderName("扬恩科技");
		try{
			
			//获取导购信息
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setMobile(mobile);
			List<FindGuidMemberPageReturn> guidMember = guidMemberService.findGuidMembers(findGuidMemberPage);
			//获取管理人员信息
			FindManagerMemberPage findManagerMemberPage = new FindManagerMemberPage();
			findManagerMemberPage.setMobile(mobile);
			List<FindManagerMemberPageReturn> managerMember = managerMemberService.findManagerMembers(findManagerMemberPage);
			if(guidMember.size()<=0 && managerMember.size()<=0){
				logger.info("用户不存在！");
				throw new TsfaServiceException(ErrorCode.PERSON_ERROR_NOT_EXIST,"用户不存在！"); 
			}
			/*发送短信*/
			smsCodeService.send(request);
			 logger.info("短信发送完毕！");
			//String key = CommonConstant.VALIDATION_CODE_KEY_PREFIX +request.getMobile();	// 分布式缓存存放smsCode
			//return distributeCache.get(key);
			return GeneralResponse.generateSuccessResponse();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("获取短信验证码错误！",e);
			throw new TsfaServiceException(com.lj.business.api.common.ErrorCode.SMS_CODE_ERROR,"获取短信验证码错误！",e);
		}
	}
}
