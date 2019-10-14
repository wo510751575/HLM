package com.lj.oms.utils;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.common.KeyConstant;
import com.lj.distributecache.RedisCache;

/**
 * 
 * 类说明：判断号码规则
 * <p>
 * 详细描述：
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2018年1月11日
 */
public class AddQrCodeUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(AddQrCodeUtils.class);
	
	/**
	 * 方法说明：判断是否为手机号,手机号规则可能出现更新,后期动态调整
	 * @param phone
	 * @return
	 * @author 李端强 CreateDate: 2018年1月11日
	 */
	public static boolean isMobile(String phone) {
		boolean ret =  false;
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,1-9])|(18[1,1-9])|(18[3,1-9])|"
				+ "(18[2,1-9])|(18[4,1-9])|(18[6,1-9]))\\d{8}$";
		if(phone.length() != 11){
			return ret;
		}else {
			Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if(isMatch){
            	ret = true;
            } else {
            	ret = false;
            }
		}
		return ret;
	}
	
	/**
	 * 方法说明：判断该商户调用对应的方法和key是否被限制,distributeCache 商户编号=时间戳+"_"+单日累积次数
	 * @param merchentNo 商户编号
	 * @param merchantWxNo 商户微信号
	 * @param redisCache
	 * @param merchantParamsService
	 * @param keyPre 配置项前缀
	 * @return true被限制,false可调用
	 * @author 李端强 CreateDate: 2018年1月9日
	 *
	 */
	public static synchronized boolean limited(String merchantNo,String merchantWxNo, RedisCache redisCache,IMerchantParamsService merchantParamsService,String key) {
		logger.debug("limited(String merchentNo={},merchantWxNo={}, String keyPre={}) - start", merchantNo, merchantWxNo, key);
		boolean ret = false;
		try {
			FindMerchantParams findMerchantParams = new FindMerchantParams();
			String groupName = "add_friend";//组名
			findMerchantParams.setGroupName(groupName);//组名
			findMerchantParams.setMerchantNo(merchantNo);
			Map<String, String> openApiMap = merchantParamsService.findMerchantParamsByGroup(findMerchantParams);//获取cm商户系统参数
			float times = 3;//一天总次数限制,默认3次
			String paramValue = openApiMap.get(key);
			if(!StringUtils.isEmpty(paramValue)) {
				times = Float.valueOf(paramValue);
			}
			if(times==0) {
				logger.debug("limited(String, RedisCache, IMerchantParamsService, String) - end - return value={}", ret);
				return ret;//被限制
			}
			String historyVisit = redisCache.hget(KeyConstant.ADD_FRIENDS_DAY_LIMIT_BYSHOPWX_CACHE_GROUP_NAME, merchantWxNo);//取缓存
			if(historyVisit==null) {
				redisCache.hset(KeyConstant.ADD_FRIENDS_DAY_LIMIT_BYSHOPWX_CACHE_GROUP_NAME, merchantWxNo,System.currentTimeMillis()+"_"+1);//更新缓存
			}else {
				String[] hisArr = historyVisit.split("_");
				long currentTime = System.currentTimeMillis();//当前时间
				long hisTime = Long.parseLong(hisArr[0]);//上次时间
				int  hisCount = Integer.parseInt(hisArr[1]);//当天已累积次数
				//当天
				long currentDate = DateUtils.parseDate(DateUtils.formatDate(new Date(currentTime),"yyyy-MM-dd"), "yyyy-MM-dd").getTime();
				long hisDate = DateUtils.parseDate(DateUtils.formatDate(new Date(hisTime),"yyyy-MM-dd"), "yyyy-MM-dd").getTime();
				if(currentDate == hisDate) {//同一天
					if((hisCount+1)>times) {//同一天总访问次数判断
						logger.debug("limited(String, IDistributeCache, IMerchantParamsService, String) - end - return value={}", true); 
						return true;
					}
				}else {//跨天,重置统计次数
					hisCount = 0;
				}
				redisCache.hdel(KeyConstant.ADD_FRIENDS_DAY_LIMIT_BYSHOPWX_CACHE_GROUP_NAME, merchantWxNo);
				redisCache.hset(KeyConstant.ADD_FRIENDS_DAY_LIMIT_BYSHOPWX_CACHE_GROUP_NAME, merchantWxNo,System.currentTimeMillis()+"_"+(hisCount+1));//更新缓存
			}
		} catch (Exception e) {
			logger.error("limited(String, IDistributeCache, IMerchantParamsService, String)", e);
			return true;
		}
		logger.debug("limited(String, IDistributeCache, IMerchantParamsService, String) - end - return value={}", ret); 
		return ret;
	}
	
	/**
	 *
	 * 方法说明：获取商户的配置参数值
	 * @param merchantNo
	 * @param merchantParamsService
	 * @param groupName
	 * @param key
	 * @return
	 * @author 李端强 CreateDate: 2018年1月31日
	 *
	 */
	public static String getMerchantParamsValue(String merchantNo,IMerchantParamsService merchantParamsService,String groupName,String key) {
		FindMerchantParams findMerchantParams = new FindMerchantParams();
		findMerchantParams.setGroupName(groupName);//组名
		findMerchantParams.setMerchantNo(merchantNo);//商户编号
		Map<String, String> openApiMap = merchantParamsService.findMerchantParamsByGroup(findMerchantParams);//获取cm商户系统参数
		String paramValue = openApiMap.get(key);
		String value = "";
		if(!StringUtils.isEmpty(paramValue)) {
			value = paramValue;
		}
		return value;//返回商户参数
	}
	
}
