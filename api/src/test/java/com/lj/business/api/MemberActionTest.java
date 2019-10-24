package com.lj.business.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.encryption.MD5;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.api.controller.member.MemberAction;
import com.lj.business.api.dto.UpdateMemberQcordDto;
import com.lj.business.member.dto.UpdateGuidMember;
import com.lj.business.member.emus.CertType;
/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 邹磊
 *   
 * CreateDate: 2017年7月1日
 */
public class MemberActionTest extends SpringTestCase {
	
	@Resource
	private MemberAction memberAction;
	
	

	/**
	 * 
	 *
	 * 方法说明：appTest 测试
	 *
	 *
	 * @author 彭阳 CreateDate: 2017年8月2日
	 *
	 */
	@Test
	public void appTest() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		//businessParamMap.put("mobile", "2017-08-17 00:00:00");
		businessParamMap.put("workDate", "2017-08-17 00:00:00");
		businessParamMap.put("memberNoGm", "234");
		
		ApiHelp.doPost("member/appTest.do", businessParamMap);
	}
	
	/**
	 * 
	 *
	 * 方法说明：
	 *
	 *
	 * @author 彭阳 CreateDate: 2017年8月30日
	 *
	 */
	@Test
	public void h5Login() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("mobile", "18665435141");
		businessParamMap.put("pwd", "123");
		businessParamMap.put("wxCode", "234");
		ApiHelp.doPost("member/h5Login.do", businessParamMap);
	}
	
	/**
	 * 
	 *
	 * 方法说明：客户管理首页：分类信息查询
	 *
	 *
	 * @author 彭阳 CreateDate: 2017年7月12日
	 *
	 */
	@Test
	public void findPmTypeIndex() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("merchantNo", "086c40e17ed44e89a0482366841c63f2");
		businessParamMap.put("memberNoGm", "f06e7f84d37b4d698065ecd0dd02966d");
		businessParamMap.put("shopNo", "ed0ab4713fa04378aadcc7d7e0cbe8aa");
		ApiHelp.doPost("member/findPmTypeIndex.do", businessParamMap);
	}
	
	/**
	 * 
	 *
	 * 方法说明：客户管理首页：分类信息及其明细查询
	 *
	 *
	 * @author 彭阳 CreateDate: 2017年7月12日
	 *
	 */
	@Test
	public void findPmTypeIndexAll() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("merchantNo", "086c40e17ed44e89a0482366841c63f2");
		businessParamMap.put("memberNoGm", "f06e7f84d37b4d698065ecd0dd02966d");
		businessParamMap.put("shopNo", "ed0ab4713fa04378aadcc7d7e0cbe8aa");
		ApiHelp.doPost("member/findPmTypeIndexAll.do", businessParamMap);
	}
	

	@Test
	public void testExistMemberByMobile() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("mobile", "15889399351");
		ApiHelp.doPost("member/existMemberByMobile.do", businessParamMap);
	}
	
	@Test
	public void testPersonRegister() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("mobile", "15889399351");
		businessParamMap.put("smsCode", "1460");
		businessParamMap.put("processCode", "4B4A1we72g3MX327");
		businessParamMap.put("memberName", "彭阳");
		businessParamMap.put("certTypeCode", CertType.I_CARD.name());
		businessParamMap.put("certNo", "432524198502164037");
		businessParamMap.put("pwd", MD5.encryptByMD5("111111"));
		businessParamMap.put("payPwd", MD5.encryptByMD5("111111"));
		businessParamMap.put("merchantNo",ApiHelp.DEVELOPER);
		ApiHelp.doPost("member/personRegister.do", businessParamMap);
	}
	
	@Test
	public void testPersonAccount() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		ApiHelp.doPost("member/personAccount.do", businessParamMap);
	}
	
	@Test
	public void testUpdatePersonMobile() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("mobile", "15889399351");
		businessParamMap.put("mobile", "12346789509");
		businessParamMap.put("payPsw", MD5.encryptByMD5("111111"));
		ApiHelp.doPost("member/updatePersonMobile.do", businessParamMap);
	}
	
	@Test
	public void testUpdateLoginPassword() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("pwd", MD5.encryptByMD5("111111"));
		businessParamMap.put("orgPwd", MD5.encryptByMD5("111111"));
		ApiHelp.doPost("member/updateLoginPassword.do", businessParamMap);
	}
	
	@Test
	public void testResetLoginPassword() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("mobile", "15889399351");
		businessParamMap.put("smsCode", "7651");
		businessParamMap.put("processCode", "01R19dF935MC9Po6");
		businessParamMap.put("password", MD5.encryptByMD5("111111"));
		ApiHelp.doPost("member/resetLoginPassword.do", businessParamMap);
	}
	
	@Test
	public void testUpdatePayPassword() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("pwd", MD5.encryptByMD5("111111"));
		businessParamMap.put("orgPwd", MD5.encryptByMD5("111111"));
		ApiHelp.doPost("member/updatePayPassword.do", businessParamMap);
	}
	
	@Test
	public void testResetPayPassword() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("mobile", "15889399351");
		businessParamMap.put("smsCode", "7651");
		businessParamMap.put("processCode", "01R19dF935MC9Po6");
		businessParamMap.put("password", MD5.encryptByMD5("111111"));
		ApiHelp.doPost("member/resetPayPassword.do", businessParamMap);
	}
	
	@Test
	public void testBindBankCard() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("bankcardNo", "6214850212331638");
		businessParamMap.put("bankCode", "CMB");
		ApiHelp.doPost("member/bindBankCard.do", businessParamMap);
	}
	
	@Test
	public void testHeadImage() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		ApiHelp.doPost("member/headImage.do", businessParamMap);
	}
	
	@Test
	public void testLogout() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		ApiHelp.doPost("logout.do", businessParamMap);
	}
	
	@Test
	public void addMemberLoginInfo() {
		Map<String, Object> addMemberLoginInfoMap = new HashMap<String, Object>();
		addMemberLoginInfoMap.put("currentMemberNo", "1661464762295200856");
		//addMemberLoginInfoMap.put("memberNo", "1661464762295200856");
		addMemberLoginInfoMap.put("phoneNo", "phoneNo");
		addMemberLoginInfoMap.put("phoneType", "phoneType");
		addMemberLoginInfoMap.put("appVersion", "appVersion");
		addMemberLoginInfoMap.put("imei", "imei");
		addMemberLoginInfoMap.put("sysVersion", "sysVersion");
		addMemberLoginInfoMap.put("ipAddress", "ipAddress");
		addMemberLoginInfoMap.put("mac", "mac");
		addMemberLoginInfoMap.put("networkType", "2G");
		addMemberLoginInfoMap.put("areaInfo", "areaInfo");
		addMemberLoginInfoMap.put("longitude", "longitude");
		addMemberLoginInfoMap.put("latitude", "latitude");
		addMemberLoginInfoMap.put("resolution", "resolution");
		addMemberLoginInfoMap.put("size", "size");
		ApiHelp.doPost("member/addMemberLoginInfo.do", addMemberLoginInfoMap);
	}
	
	/**
	 * 
	 *
	 * 方法说明：新增客户
	 *
	 *
	 * @author 冯辉 CreateDate: 2017年7月13日
	 *
	 */
	@Test
	public void addMemberForNonWxMember() {
		Map<String, Object> addMemberForNonWxMemberMap = new HashMap<String, Object>();
		addMemberForNonWxMemberMap.put("merchantNo", "e79d975846ee41ba8c3c55738fda66a9");
		addMemberForNonWxMemberMap.put("memberName", "张八");
		addMemberForNonWxMemberMap.put("memberNoGm", "d7b963349b8f4bcbbed9a36fe41ae626");
		addMemberForNonWxMemberMap.put("memberNameGm", "李四");
		addMemberForNonWxMemberMap.put("shopNo", "LJ_c269478af52646b692fcc48deb10a7ad");
		addMemberForNonWxMemberMap.put("shopName", "南山分店");
		addMemberForNonWxMemberMap.put("house", "南山科技园小区2号");
		addMemberForNonWxMemberMap.put("mobile", "15820235699");
		addMemberForNonWxMemberMap.put("sex", "male");
		addMemberForNonWxMemberMap.put("age", "28");
		addMemberForNonWxMemberMap.put("job", "LJ_04291788e6254ecb9d9ff281200ddaab");
		addMemberForNonWxMemberMap.put("urgentFlag", "Y");
		addMemberForNonWxMemberMap.put("pmTypeCode", "LJ_05d6d65ce6184f4faf1eb3fcfcb71515");
		addMemberForNonWxMemberMap.put("bomName","沙发");
		
		ApiHelp.doPost("member/addMemberForNonWxMember.do", addMemberForNonWxMemberMap);
	}
	
	@Test
	public void updateGuidQcord() throws Exception {
		UpdateMemberQcordDto updateMemberQcordDto = new UpdateMemberQcordDto();
		updateMemberQcordDto.setMemberNo("LJ_98bcf355be91428b9b3bd7374d63bba0");
		updateMemberQcordDto.setQcord("/headImg/660502873233650690.jpg");
		memberAction.updateGuidQcord(updateMemberQcordDto);
	}
	
}
