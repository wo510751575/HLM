package com.lj.business.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.bean.BeanMapConverter;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.api.utils.SignUtil;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.oms.entity.dto.kuaitui.KuaiTuiDto;

/**
 * 快推对接Api
 * @author wo510
 *
 */
public class KuaiTuiActionTest extends SpringTestCase{
	
	@Autowired
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	/**
	 * 方法说明1：测试提供外部接口,根据手机号查询客户的微信号
	 * @author 李端强 CreateDate: 2017年12月29日
	 */
	@Test
	public void acctAuthorize() {
		KuaiTuiDto k= new KuaiTuiDto();
		k.setMerchantName("名片个人试用版2");
		k.setMerchantNo("test1");
		k.setMobile("18670275121");
		String timestamp = "1542103248653";
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = BeanMapConverter.bean2map(k);
		
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		System.out.println("sign===="+sign);
		
		String paramContent = JsonUtils.jsonFromObject(k);
		System.out.println(paramContent);
//		ApiHelp.post("kuaitui/acctAuthorize.do",paramContent,sign,timestamp);
	};
	
	
	
	
	@Test
	public void getMerchant() {
		
		Map<String, String> map = new HashMap<>();
		map.put("id", "24754050f3824fb8b7c3a9772559365a");
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		System.out.println(sign);
		
		
		ApiHelp.post("kuaitui/getMerchant.do",paramContent,sign,timestamp+"");
	};
	
	@Test
	public void editMerchantStatus() {
		
		Map<String, String> map = new HashMap<>();
		map.put("id", "24754050f3824fb8b7c3a9772559365a");
		map.put("status", "CANCEL");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/editMerchantStatus.do",paramContent,sign,timestamp);
	};
	
	
	@Test
	public void getAreas() {
		
		Map<String, String> map = new HashMap<>();
		map.put("id", "24754050f3824fb8b7c3a9772559365a");
		map.put("status", "CANCEL");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/getAreas.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void getCityAreas() {
		
		Map<String, String> map = new HashMap<>();
		map.put("id", "24754050f3824fb8b7c3a9772559365a");
		map.put("status", "CANCEL");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/getCityAreas.do",paramContent,sign,timestamp);
	};
	
	
	@Test
	public void addShop() {
		
		Map<String, String> map = new HashMap<>();
		map.put("merchantNo", "24754050f3824fb8b7c3a9772559365a");
		map.put("merchantName", "快推_merchantNo");
		map.put("shopNoMerchant", "shopNoMerchant");
		map.put("shopName", "shopName");
		map.put("shopType", "shopType");
		map.put("status", "OPENED");
		map.put("telephone", "telephone");
		map.put("bizScope", "bizScope");
		map.put("areaCode", "1");
		map.put("cityAreaCode", "440305");
		map.put("addrInfo", "addrInfo");
		map.put("openDate", "2018-11-12");
		map.put("longitude", "123");
		map.put("latitude", "123");
		map.put("logoAddr", "456");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/addShop.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void getShop() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "LJ_d6cab0d35159457c95b175a4fbd3222c");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/getShop.do",paramContent,sign,timestamp);
	};
	
	
	@Test
	public void editShop() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "LJ_d6cab0d35159457c95b175a4fbd3222c");
		map.put("shopName", "shopName2");
		map.put("shopNoMerchant", "shopName2");
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/editShop.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void addGuid() {
		
		Map<String, String> map = new HashMap<>();
		map.put("merchantNo", "1f4f1003451244879bf7302ad86c2461");
		map.put("merchantName", "名片个人试用版");
		map.put("shopNo", "LJ_170bed979c914aa6a38dca4afc9380d1");
		map.put("memberName", "盼盼");
		map.put("mobile", "18670275125");
		map.put("age", "0");
		map.put("gender", "FEMALE");
		map.put("workDate", "2018-11-14");
		map.put("areaCode", "4");
		map.put("status", "NORMAL");
		map.put("pwd", "1f41855b0ed130e63513833778bf541b");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		System.out.println(paramContent);
		String timestamp = "1542248088389";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		System.out.println(sign);
//		ApiHelp.post("kuaitui/addGuid.do",paramContent,sign,timestamp);
	};
	
	
	@Test
	public void editGuid() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "LJ_e1e8234359f94bde88df1415df32a9a8");
//		map.put("memberName", "test2");
//		map.put("mobile", "18670275126");
//		map.put("age", "28");
//		map.put("gender", "FEMALE");
//		map.put("noWx", "test1");
//		map.put("workDate", "2018-11-12");
//		map.put("areaCode", "1");
		map.put("status", "NORMAL");
//		map.put("pwd", "123456");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/editGuid.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void getGuid() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "LJ_52fdedc9bed14986be9412b9127dfcc9");
//		map.put("mobile", "18670275121");
		String paramContent = JsonUtils.jsonFromObject(map);
		System.out.println(paramContent);
		String timestamp = "1542248088389";
		map.put("timestamp", timestamp);
		
		String sign = getSign(map);
		System.out.println(sign);
//		ApiHelp.post("kuaitui/getGuid.do",paramContent,sign,timestamp);
	};
	
	
	@Test
	public void addActivity() {
		
		Map<String, String> map = new HashMap<>();
		map.put("merchantNo", "merchantNo");
		map.put("merchantName", "merchantName");
		map.put("title", "title");
		map.put("startDate", "2018-11-11");
		map.put("linkUrl", "linkUrl");
		map.put("imgAddr", "imgAddr");
		map.put("showImgAddr", "showImgAddr");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/addActivity.do",paramContent,sign,timestamp);
	};
	
	
	@Test
	public void editActivity() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "ea737f7502bc4d7e885a216f5d2ccd73");
		map.put("title", "title1");
		map.put("startDate", "2018-11-12");
		map.put("linkUrl", "linkUrl1");
		map.put("imgAddr", "imgAddr1");
		map.put("showImgAddr", "showImgAddr1");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/editActivity.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void getActivity() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "ea737f7502bc4d7e885a216f5d2ccd73");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/getActivity.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void findActivitys() {
		
		Map<String, String> map = new HashMap<>();
		map.put("merchantNo", "3461e7ccd72d460e8704dd3cdefc018e");
		map.put("pageNo", "2");
		map.put("pageSize", "10");
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/findActivitys.do",paramContent,sign,timestamp);
	};
	
	
	@Test
	public void delActivity() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "8837c422375e494ab1a21c8f79e58209");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/delActivity.do",paramContent,sign,timestamp);
	};
	
	
	@Test
	public void addWordsType() {
		
		Map<String, String> map = new HashMap<>();
		map.put("merchantNo", "merchantNo");
		map.put("typeName", "typeName");
		map.put("seq", "seq");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/addWordsType.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void editWordsType() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "cd988f23003741e1af1af976b400b3dc");
		map.put("typeName", "typeName1");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/editWordsType.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void getWordsType() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "cd988f23003741e1af1af976b400b3dc");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/getWordsType.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void findWordsTypes() {
		
		Map<String, String> map = new HashMap<>();
		map.put("merchantNo", "f4dd2bae07bf42ea815ce6dec4d1546a");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/findWordsTypes.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void delWordsType() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "cd988f23003741e1af1af976b400b3dc");
		
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/delWordsType.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void addWords() {
		
		Map<String, String> map = new HashMap<>();
		map.put("content", "content");
		map.put("defaultFlag", "1");
		map.put("merchantNo", "merchantNo");
		map.put("typeCode", "typeCode");
		map.put("typeName", "typeName");
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/addWords.do",paramContent,sign,timestamp);
	};

	@Test
	public void editWords() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "304299651e1b4f0b81dcebbc762573e7");
		map.put("defaultFlag", "1");
		map.put("typeCode", "typeCode1");
		map.put("typeName", "typeName1");
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/editWords.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void getWords() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "304299651e1b4f0b81dcebbc762573e7");
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/getWords.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void findWordss() {
		
		Map<String, String> map = new HashMap<>();
		map.put("merchantNo", "b08f245d8f3241d6a9da2d6aff1fb043");
		map.put("pageNo", "2");
		map.put("pageSize", "11");
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/findWordss.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void delWords() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "304299651e1b4f0b81dcebbc762573e7");
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("kuaitui/delWords.do",paramContent,sign,timestamp);
	};
	
	@Test
	public void uploadImage() {
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "304299651e1b4f0b81dcebbc762573e7");
		String paramContent = JsonUtils.jsonFromObject(map);
		
		String timestamp = new Date().getTime()+"";
		map.put("timestamp", timestamp);
		String sign = getSign(map);
		
		ApiHelp.post("/upload/uploadImage.do",paramContent,sign,timestamp);
	};
	
	private String getSign(Map<String, String> map) {
		/**
		 * 密钥
		 */
		String secret=localCacheSystemParams.getSystemParam("api-os", KuaiTuiDto.ROLE_KEY, "secret");
		map.put("secret", secret);
		if(map.containsKey("class")){
			map.remove("class");
		}
		String sign = SignUtil.getSha1Sign(map);
		return sign;
	}
	
}
