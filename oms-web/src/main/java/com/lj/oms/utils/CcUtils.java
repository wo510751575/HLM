package com.lj.oms.utils;

import com.ape.common.config.Global;
import com.ape.common.utils.SpringContextHolder;
import com.ape.common.utils.StringUtils;
import com.lj.business.common.SystemParamConstant;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.RedisCache;

/**
 * 
 * 
 * 类说明：CC工具类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年12月21日
 */
public class CcUtils {

	private static LocalCacheSystemParamsFromCC localCacheSystemParams = SpringContextHolder.getBean(LocalCacheSystemParamsFromCC.class);
	private static RedisCache redisCache= SpringContextHolder.getBean(RedisCache.class);
	/**
	 * 
	 *
	 * 方法说明：获取上传路径前缀
	 *
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月8日
	 *
	 */
	public static String getUploadUrl(){
		String uploadUrl =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
		if(StringUtils.isBlank(uploadUrl)){
			return Global.getConfig("uploadUrl");
		}
		return uploadUrl;
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取IM文件访问路径前缀
	 *
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月28日
	 *
	 */
	public static String getImUploadUrl(){
		String imUploadUrl =  localCacheSystemParams.getSystemParam(SystemAliasName.weixin.toString(),SystemParamConstant.IM_UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
		if(StringUtils.isBlank(imUploadUrl)){ 
			return Global.getConfig("imUploadUrl");
		}
		return imUploadUrl;
	}
	
	/**
	 * 获取redis值
	 * @param key
	 * @return
	 */
	public static Object getRedisValue(String key){
		return redisCache.get(key);
	}
	
}
