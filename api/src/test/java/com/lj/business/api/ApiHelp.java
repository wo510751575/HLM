package com.lj.business.api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.encryption.MD5;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.web.httpclient.HttpClientUtils;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.dto.PersonMemberLoginReturn;
import com.lj.business.supcon.dto.token.Token;
import com.lj.distributecache.IDistributeCache;

public class ApiHelp extends SpringTestCase {

	public static final String URL = "http://localhost/api/";
//	public static final String URL = "http://192.168.6.62:8080/api/";
	public static final Long DEVELOPER = 661456283157610676L;
	public static final String SGIN_PARAM = "013cXuH9vf584W0x";
	
	@Resource 
	private IDistributeCache distributeCache;
	
	@Test
	public void mainTest() {
		distributeCache.set("test_py","123");
		System.out.println(distributeCache.get("test_py"));
//		Token token = login();
//		System.out.println(distributeCache.get("APIRTOKEN" + token.getRefreshToken()));
//		System.out.println(distributeCache.get("APIATOKEN" + token.getAccessToken()));
//		System.out.println(distributeCache.get("APIRTOKEN825170b262904d9d895c6d618dea9a49"));
//		String value = distributeCache.get("APIATOKEN" + "RYgmFT3LJ4wuDeI2YGxeXypRY/TztFbSoEzNrDm9ojlEz2A82L14YL38Z00jQ1Lp");
//		System.out.println(value);
//		System.out.println((Long.valueOf(value.substring(15)) - Long.valueOf(value.substring(0,  15)))/60000);
//		System.out.println(distributeCache.get("APIMTOKEN" ));
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("mobile","18612311111");
		businessParamMap.put("pwd","3111111");
//		businessParamMap.put("imei","99000987068346");
		
//		businessParamMap.put("merchantNo","e79d975846ee41ba8c3c55738fda66a9");
//		businessParamMap.put("memberNo","efbc3bb7be534d24b9cb6d72ac21f666");
		
		//businessParamMap.put("pwd", MD5.encryptByMD5("123456"));
//		businessParamMap.put("limit", 0);
//		doPost("member/checkLoginPwd.do", businessParamMap);
//		doPost("area/findAllList.do", businessParamMap);
		doPost("member/appLogin.do", businessParamMap);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(URLDecoder.decode(Base64.decode("ç¼–ç æ ¼å¼æμ‹èˉ•"), "UTF-8"));
//		System.out.println(URLDecoder.decode("%E7%BC%96%E7%A0%81%E6%A0%BC%E5%BC%8F%E6%B5%8B%E8%AF%95", "UTF-8"));
//		String data = "UoylgDAI+kvYn31ImhsgcEXjuggBgbtJ+FkxYMpj5CARPRjjQPH3w1iQsSEvVw0/bUASol4m4tZcEPJz6JpFHhpSMs/qJi0DTWEkksTavmvLebj68YqQgdD2mddXc8XoX11otBm84cesSgoEyYs0auH2i13aN50v5sUIcoc9RQ9Gh8qP6Tg8FKJLuw09JNNeqfGnnpaEgIB9aSLhmjeHrA==";
//		String key = "1yxe5q9wvlb6p7dr";
//		System.out.println(AES.decrypt(data, key));
//		String en = "bf83163aabcd28427bf6fbd9d4ad3f60";
		System.out.println(MD5.encryptByMD5("", "1461851480702" + "fQ6mf09T02EK3Zws"));
}
	

	/**
	 * 
	 *
	 * 方法说明：JSON数据转MAP，测试用
	 *
	 *
	 * @author 彭阳 CreateDate: 2017年7月17日
	 *
	 */
	private static void jsonToMap() {
		String str = "'mobile':'13501556421','memberName':'我shi康师傅-33','pmTypeCode':'LJ_c16fc97446ce4fa883d3d6d3113d62f7','memberSrc':'HOOK','sex':'UNKNOWN','merchantNo':'e79d975846ee41ba8c3c55738fda66a9','memberNoGm':'d7b963349b8f4bcbbed9a36fe41ae626','code':'LJ_80f5bc34a58f4a5eb1914366a4daeed3','codePm':'LJ_c44e286279fd426ea6acf7180a1b78a0','urgencyPm':'N','pmTypePmCode':'LJ_43c295624dee4139ac90968a05a3f09f','memberNo':'51dc3429ed7a4a7793a2cfff96f00c2f','age':'','job':'','bomName':'','bomCode':'','spruceUp':'','houses':'','cityAreaCode':'111','shopName':'南山分店','shopNo':'LJ_c269478af52646b692fcc48deb10a7ad','headAddress':'http://n.sinaimg.cn/tech/transform/20170713/aNcK-fyiavtv5064752.jpg'";
		String strAr[] = str.split(",");
		for (String string : strAr) {
			String stringAr[] = string.split(":");
			System.out.println("businessParamMap.put(\""+ stringAr[0].replaceAll("'", "") +"\",\""+ stringAr[1].replaceAll("'", "") +"\");");
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：提交post请求
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param action
	 * @param businessParamMap
	 */
	public static void doPost(String action, Map<String, Object> businessParamMap) {
		Map<String,String> params = buildBaseParamters();
		String paramJson = JsonUtils.jsonFromObject(businessParamMap);
		System.out.println("paramJson==================="+paramJson);
		params.put("paramJson", paramJson);
		String signature = MD5.encryptByMD5Twice(paramJson, params.get("timestamp") + SGIN_PARAM);
//		System.out.println(signature);
		params.put("signature", signature);
		//params.put("token", "7jrgNUk6E4z9DJZMoRHw2dTFsMv/PoIqYpBDMBbERabExBj05bVVjVJP0o00AYRj");	// 签名
//		params.put("token", login());
		String result = HttpClientUtils.postToWeb(ApiHelp.URL + action, params);
		System.out.println("GeneralResponse = " + result); 
		GeneralResponse response = (GeneralResponse) JsonUtils.objectFromJson(result, GeneralResponse.class);
		System.out.println(response.getReturnObject()); 
		AssertUtils.isTrue(response.isResult());
	}

	@Test
	public void testLogin() {
		login();
	}
	
	@Test
	public void testLogout() {
		String token = login();
		logout(token);
	}
	
	/*@Test 
	public void testRefreshToken() throws InterruptedException {
		String token = login();
//		Thread.currentThread().sleep(600000);
		refreshToken(token.getRefreshToken());
	}*/
	
	/**
	 * 
	 *
	 * 方法说明：登录
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @return
	 */
	public static String login() {
		Map<String,String> params = ApiHelp.buildBaseParamters();
//		PersonMemberLogin login = new PersonMemberLogin();
//		login.setMobile("15889399351");
//		login.setPwd(MD5.encryptByMD5("111111"));
//		String paramJson = JsonUtils.jsonFromObject(login);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("mobile", "18612311111");
//		paramMap.put("pwd", MD5.encryptByMD5("399351"));
		paramMap.put("pwd", MD5.encryptByMD5(paramMap.get("mobile").substring(5)));
		String paramJson = JsonUtils.jsonFromObject(paramMap);
		params.put("paramJson", paramJson);
		String signature = MD5.encryptByMD5Twice(paramJson, params.get("timestamp") + SGIN_PARAM);
		System.out.println(signature);
		params.put("signature", signature);
		params.put("token", "");
		String result = HttpClientUtils.postToWeb(ApiHelp.URL + "member/appLogin.do", params);
		System.out.println("result= " + result); 
		@SuppressWarnings("rawtypes")
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("returnObject", PersonMemberLoginReturn.class);
		GeneralResponse response = (GeneralResponse) JsonUtils.objectFromJson(result, GeneralResponse.class, classMap);
		String token = ((PersonMemberLoginReturn)response.getReturnObject()).getToken();
		System.out.println(token);
		return token;
	}
	
	/**
	 * 
	 *
	 * 方法说明：刷新令牌
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param refreshToken
	 * @return
	 */
	public static Token refreshToken(String refreshToken) {
		Map<String,String> params = ApiHelp.buildBaseParamters();
		params.put("refreshToken", refreshToken);
		String paramJson = JsonUtils.jsonFromObject(params);
		params.put("paramJson", paramJson);
		String signature = MD5.encryptByMD5Twice(paramJson, params.get("timestamp") + SGIN_PARAM);
		params.put("signature", signature);
		params.put("token", "");
		String result = HttpClientUtils.postToWeb(ApiHelp.URL + "refreshToken.do", params);
		System.out.println("result= " + result); 
		@SuppressWarnings("rawtypes")
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("returnObject", Token.class);
		GeneralResponse response = (GeneralResponse) JsonUtils.objectFromJson(result, GeneralResponse.class, classMap);
		Token token = (Token)response.getReturnObject();
		System.out.println(token);
		return token;
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
	 * @param token
	 */
	public static void logout(String token) {
		Map<String,String> params = ApiHelp.buildBaseParamters();
		String paramJson = JsonUtils.jsonFromObject(params);
		params.put("paramJson", paramJson);
		String signature = MD5.encryptByMD5Twice(paramJson, params.get("timestamp") + SGIN_PARAM);
		params.put("signature", signature);
		params.put("token", token);
		String result = HttpClientUtils.postToWeb(ApiHelp.URL + "logout.do", params);
		System.out.println("result= " + result); 
		GeneralResponse response = (GeneralResponse) JsonUtils.objectFromJson(result, GeneralResponse.class);
		System.out.println(response);
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：构建基础请求参数
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @return
	 */
	public static Map<String,String> buildBaseParamters() {
		Map<String,String> params = new HashMap<String,String>();
		params.put("developer", DEVELOPER.toString());
		params.put("appKey", "l8hh473K8gO82ZT6");
		params.put("appSecret", "LO7491JZ0h89gySA14432s909V7H87PX");
		params.put("terminalId", "terminalId");
		String timestamp = String.valueOf(System.currentTimeMillis());
		params.put("timestamp", timestamp);
		return params;
	}
	

	/**
	 * 
	 *
	 * 方法说明：分页消息列表
	 *
	 *
	 * @author 段志鹏 CreateDate: 2017年7月3日
	 *
	 */
	@Test
	public void listMsg() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("msgType","SYSTEM");
//		businessParamMap.put("memberNoShop","333M0001");
		doPost("msg/listMsg.do", businessParamMap);
	}
	
	
	@Test
	public void findPersonMember() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("memberNo","1111111111111111");
		doPost("member/forecastName/findPersonMember.do", businessParamMap);
	}
	
}
