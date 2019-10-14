package com.lj.business.api.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.RandomUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.domain.msg.SmsCodeSenderRequest;
import com.lj.business.api.domain.msg.SmsCodeVerifyRequest;
import com.lj.business.common.CommonConstant;
//import com.lj.business.iem.constant.Constant;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.IDistributeCache;
//import com.lj.messagecenter.dto.sms.SmsSenderRequest;
import com.lj.messagecenter.email.common.MsgConstants;
import com.lj.messagecenter.email.domain.TemplateSmsMessage;
import com.lj.messagecenter.email.service.ISmsSenderService;
//import com.lj.messagecenter.emus.SendTarget;
import com.lj.messagecenter.service.ISmsInfoService;

/**
 * 
 * 
 * 类说明：短信验证码服务接口
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
@Service
public class SmsCodeService {
	
	private static Logger logger = LoggerFactory.getLogger(SmsCodeService.class);	
	
	@Resource 
	private IDistributeCache distributeCache;
	
	@Resource 
	private ISmsSenderService smsSenderService;
	
	@Resource
	private  LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	@Resource 
    private ISmsInfoService smsInfoService;
	

	/**
	 * 
	 *
	 * 方法说明：发送短信校验码
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param request
	 */
	public void send(SmsCodeSenderRequest request) {
		logger.debug("send(SmsCodeSenderRequest request={}) - start", request); 

		AssertUtils.state(request.getExpireSeconds() > 59);	//	必须超过1分钟过期时间
		String key = CommonConstant.VALIDATION_CODE_KEY_PREFIX +request.getMobile();	// 分布式缓存存放smsCode
		logger.debug("key:" + key);
		logger.info("执行到第一步");
		if(StringUtils.isNotEmpty(distributeCache.get(key))){
			logger.error("频繁发送短信验证码");
			throw new TsfaServiceException(ErrorCode.SMS_CODE_EXIST, "亲，短信费用好贵哦，请不要频繁发送！");
		}
		// 随机生成四位验证码
		logger.info("执行到第二步");
		Random rnd = new Random(System.currentTimeMillis());
		String validateCode = StringUtils.padLeft(String.valueOf(Math.abs( rnd.nextInt()%10000) ),4,'0');
		distributeCache.set(key, validateCode,request.getExpireSeconds());	// 插入缓存区
		logger.info("执行到第三步");
		TemplateSmsMessage tsm = new TemplateSmsMessage();	// 发送短信验证码
		tsm.setTelphoneNo(request.getMobile());
		tsm.setTemplateId("sms_validation_code");
		Map<String,String> contentMap = new HashMap<String,String>();
		contentMap.put("validationCode", validateCode);
		contentMap.put("senderName", request.getSenderName());
		
		String templateCode =  localCacheSystemParams.getSystemParam(SystemAliasName.msg.toString(), MsgConstants.ALIDAYU, MsgConstants.TEMPLATE_CODE);
		contentMap.put("templateCode", templateCode);
		String templateParam = "{\"validationCode\":\""+ validateCode +"\"}";
		contentMap.put("templateParam", templateParam );
		
		tsm.setContentMap(contentMap);
		smsSenderService.sendTemplateSms(tsm);
		logger.info("执行到第四步");
		logger.debug("send(SmsCodeSenderRequest) - end"); 
	}
	
	/**
	 * 
	 *
	 * 方法说明：发送验证码使用
	 *
	 * @param request
	 *
	 * @author 许新龙 CreateDate: 2018年7月7日
	 *
	 */
//	@Deprecated
//	public void sendMH(SmsCodeSenderRequest request) {
//        logger.debug("send(SmsCodeSenderRequest request={}) - start", request); 
//
//        AssertUtils.state(request.getExpireSeconds() > 59); //  必须超过1分钟过期时间
//        String key = CommonConstant.VALIDATION_CODE_KEY_PREFIX_MH +request.getMobile();    // 分布式缓存存放smsCode
//        logger.debug("key:" + key);
//        logger.info("执行到第一步");
//        if(StringUtils.isNotEmpty(distributeCache.get(key))){
//            logger.error("频繁发送短信验证码");
//            throw new TsfaServiceException(ErrorCode.SMS_CODE_EXIST, "亲，短信费用好贵哦，请不要频繁发送！");
//        }
//        // 随机生成四位验证码
//        logger.info("执行到第二步");
//        Random rnd = new Random(System.currentTimeMillis());
//        String validateCode = StringUtils.padLeft(String.valueOf(Math.abs( rnd.nextInt()%10000) ),4,'0');
//        distributeCache.set(key, validateCode,request.getExpireSeconds());  // 插入缓存区
//        logger.info("执行到第三步");
//        
//        SmsSenderRequest req = new SmsSenderRequest();
//        req.setMobile(request.getMobile());
//        
//        String content = localCacheSystemParams.getSystemParam(SystemAliasName.iem.toString(), Constant.SMS_GROUP,  Constant.LOGIN);
//        content = content.replaceAll("\\$\\{validateCode\\}", validateCode);
//        content = content.replaceAll("\\$\\{expireMins\\}", String.valueOf(request.getExpireSeconds()/60));
//        req.setContent(content);
//        req.setSendTarget(SendTarget.MEMBER.getStatus());
//        smsInfoService.sendSms(req);
//        
//        logger.info("执行到第四步");
//        logger.debug("send(SmsCodeSenderRequest) - end"); 
//    }
	
	/**
	 * 
	 *
	 * 方法说明：校验短信验证码
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param request
	 * @return
	 */
	public String verify(SmsCodeVerifyRequest request) {
		AssertUtils.notNullAndEmpty(request.getSmsCode());
		String key = CommonConstant.VALIDATION_CODE_KEY_PREFIX + request.getMobile();
		String value = distributeCache.get(key);
		if(request.getSmsCode().equals(value))	{
			String processCode = "";
			if(request.isProcessFlag()) {
				// 验证码正确，再返回一个随机校验码，用于校验有可能绕过短信校验码的非法请求(暂时只用于注册和找回登录密码两个未登录的功能)
				processCode = RandomUtils.getRandom(16);
				// 将随机校验码存入缓存，并设置过期时间
				distributeCache.set(CommonConstant.PROCESS_CODE_KEY_PREFIX + request.getMobile() + request.getSmsCode(), processCode, request.getProcessExpired());
			}
			distributeCache.del(key);	// 验证通过，删除key，以便可以接受后续的验证码
			return processCode;
		} else {
			logger.error("短信验证码错误");
			throw new TsfaServiceException(ErrorCode.SMS_CODE_ERROR, "对不起，您输入的验证码有误");
		}
	}
	
	public String verifyMH(SmsCodeVerifyRequest request) {
        AssertUtils.notNullAndEmpty(request.getSmsCode());
        String key = CommonConstant.VALIDATION_CODE_KEY_PREFIX_MH + request.getMobile();
        String value = distributeCache.get(key);
        if(request.getSmsCode().equals(value))  {
            String processCode = "";
            if(request.isProcessFlag()) {
                // 验证码正确，再返回一个随机校验码，用于校验有可能绕过短信校验码的非法请求(暂时只用于注册和找回登录密码两个未登录的功能)
                processCode = RandomUtils.getRandom(16);
                // 将随机校验码存入缓存，并设置过期时间
                distributeCache.set(CommonConstant.PROCESS_CODE_KEY_PREFIX_MH + request.getMobile() + request.getSmsCode(), processCode, request.getProcessExpired());
            }
            distributeCache.del(key);   // 验证通过，删除key，以便可以接受后续的验证码
            return processCode;
        } else {
            logger.error("短信验证码错误");
            throw new TsfaServiceException(ErrorCode.SMS_CODE_ERROR, "对不起，您输入的验证码有误");
        }
    }
	
	/***
	 * 
	 *
	 * 方法说明：判断验证码是否匹配
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param mobile
	 * @param smsCode
	 */
	public void verify(String mobile, String smsCode) {
		String key = CommonConstant.VALIDATION_CODE_KEY_PREFIX + mobile;
		String value = distributeCache.get(key);
		if(smsCode.equals(value)) {
			distributeCache.del(key);	// 验证通过，删除key，以便可以接受后续的验证码
		} else {
			logger.error("短信验证码错误");
			throw new TsfaServiceException(ErrorCode.SMS_CODE_ERROR, "对不起，您输入的验证码有误");
		}
	}
	
}
