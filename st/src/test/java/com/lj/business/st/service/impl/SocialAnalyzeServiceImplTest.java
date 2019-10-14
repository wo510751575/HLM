package com.lj.business.st.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyze;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeReturn;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeTotal;
import com.lj.business.st.service.ISocialAnalyzeService;

/**
 * 
 * 
 * 类说明：测试类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年8月3日
 */
public class SocialAnalyzeServiceImplTest  extends SpringTestCase{
 
	@Resource
	private ISocialAnalyzeService socialAnalyzeService;
	
	@Test
	public void findSocialAnalyzeMax(){
		FindSocialAnalyze findSocialAnalyze=new FindSocialAnalyze();
		findSocialAnalyze.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		findSocialAnalyze.setMemberNoGm("efbc3bb7be534d24b9cb6d72ac21f666");
	    FindSocialAnalyzeReturn list=socialAnalyzeService.findSocialAnalyzeMax(findSocialAnalyze);
	    System.out.println(list.toString());
	}
	
	@Test
	public void findSocialAnalyzeApp() throws Exception {
		FindSocialAnalyzeTotal findSocialAnalyzeTotal = new FindSocialAnalyzeTotal();
		socialAnalyzeService.findSocialAnalyzeApp(findSocialAnalyzeTotal);
	}
	 
} 
