package com.lj.business.common;

/**
 * 
 * 
 * 类说明：常量类
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
public class Constants {
	
	/**
	 * 公众号TOKEN
	 */
	public static final String TOKEN = "timessharing_weixin";
	/**
	 * 消息加解密密钥
	 */
	public static final String EncodingAESKey = "PmbAGTAJkTZt0eNutodLIqa37cmeQPQOkwfWjBC5csI";
	
	/**
	 * 第三方用户唯一凭证
	 */
	public static String appid = "wx8c32f84fdfc031ee";
	
	/**
	 * 第三方用户唯一凭证密钥
	 */
	public static String appsecret = "4358b17e9fa47be9f733cf6296a7dfa1";
	/**
	 * 商户ID
	 */
	public static String mch_id="1518250861";
	/**
	 * 获取openId
	 */
	public static String oauth_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	/**
	 * 获取access-token
	 */
	public static String access_token="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
	/**
	 * 获取ticket
	 */
	public static String ticket=" https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESSTOKEN&type=jsapi";

	/**
	 * 小程序获取openId
	 */
	public static String xcx_oauth_url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	/**
	 * 发送公众号红包
	 */
	public static String sendredpack_url="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	/**
	 * 证书类型
	 */
	public static String keyStore_type="PKCS12";
	
	public static final String WEIXIN_APPID="weixin.appid";
	public static final String WEIXIN_MCHID="weixin.mchId";
	public static final String WEIXIN_APPSECRET="weixin.appSecret";
	public static final String WEIXIN_APIKEY="weixin.apiKey";
	public static final String WEIXIN_SSLFILE="weixin.ssLfile";
}
