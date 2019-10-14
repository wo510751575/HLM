package com.lj.business.member.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lj.base.core.util.Base64Utils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.constant.ErrorCode;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;

/**
 * 
 * 
 * 类说明：微信工具类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 梅宏博
 *   
 * CreateDate: 2017年11月7日
 */
@Component
public class WeiChatUtils {
	
	private static final String GET_OPEN_ID_URL = "https://api.weixin.qq.com/sns/jscode2session";
	
	private static final String OPEN_ID_KEY = "oiwefnndhhzdfs";
	
	private static LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WeiChatUtils.class);
	
	@Resource
	public void setLocalCacheSystemParams(
			LocalCacheSystemParamsFromCC _localCacheSystemParams) {
		localCacheSystemParams = _localCacheSystemParams;
	}



	/**
	 * 
	 *
	 * 方法说明：根据jsCode获取openId
	 *
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月7日
	 * @throws Exception 
	 *
	 */
	public static String getOpenId(String jsCode) throws Exception{
		String appId =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.MINI_PROGRAMS, SystemParamConstant.APP_ID);
		String secret =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.MINI_PROGRAMS, SystemParamConstant.SECRET);
		Map<String,String> params = new HashMap<>();
		params.put("appid", appId);
		params.put("secret", secret);
		params.put("js_code", jsCode);
		params.put("grant_type", "authorization_code");
		String response = HttpUtil.get(GET_OPEN_ID_URL, params);
		Map<String, String> mapFromJson = JsonUtils.mapFromJson(response);
		String openId = mapFromJson.get("openid");
		if (StringUtils.isBlank(openId)) {
			logger.error("获取openId错误" + mapFromJson);
			throw new TsfaServiceException(ErrorCode.GUID_CARD_FIND_OPENID_ERROR,"查找导购名片表信息信息错误！");
		}
		return openId;
	}
	
	/**
	 * 
	 *
	 * 方法说明：加密openId
	 *
	 * @param signature
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月8日
	 *
	 */
	public static String openIdEncrypt(String openId){
		return Base64Utils.encode((Base64Utils.encode(openId.getBytes()) + OPEN_ID_KEY).getBytes());
	}
	
	/**
	 * 
	 *
	 * 方法说明：解密openId
	 *
	 * @param signature
	 * @return
	 * @throws IOException
	 *
	 * @author 梅宏博  CreateDate: 2017年11月8日
	 *
	 */
	public static String openIdDecrypt(String signature) throws IOException{
		return new String(Base64Utils.decode(new String(Base64Utils.decode(signature)).replace(OPEN_ID_KEY, "")));
	}
	
	/**
	 * 获取字符串中特定字符的数量
	 * @param src	源字符串
	 * @param find	特定字符(串)
	 * 
	 * @author 彭俊霖 CreateDate: 2017年12月13日
	 * @return
	 */
	public static int getOccur(String src,String find){
		  int o = 0;
		  int index=-1;
		  while((index=src.indexOf(find,index))>-1){
		   ++index;
		   ++o;
		  }
		  return o;
	 }
	
}
