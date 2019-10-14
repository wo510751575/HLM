package com.lj.business.st.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.constant.PublicConstants;
import com.lj.business.st.dto.CfAnalyzeChooseDto;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyze;
import com.lj.business.st.emus.CfAnalyzeType;
import com.lj.business.st.service.ICfAnalyzeChooseService;
import com.lj.business.st.util.TestHelp;

/**
 * 
 * 
 * 类说明：跟进选择测试
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年8月1日
 */
public class CfAnalyzeChooseServiceImplTest extends SpringTestCase{
  
	@Resource
	private ICfAnalyzeChooseService cfAnalyzeChooseService;
	
	@Test
	public void findCfAnalyzeChoose(){
		FindCfAnalyze findCfAnalyze=new FindCfAnalyze();
		cfAnalyzeChooseService.findCfAnalyzeChoose(findCfAnalyze);
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：
	 *
	 *
	 * @author 彭阳 CreateDate: 2017年8月1日
	 *
	 */
	@Test
	public void insertSelective(){
		CfAnalyzeType [] cfAnalyzeTypeAr = CfAnalyzeType.values();
		int count = 1;
		for (CfAnalyzeType cfAnalyzeType : cfAnalyzeTypeAr) {
			CfAnalyzeChooseDto record = new CfAnalyzeChooseDto();
			record.setCodeList("codeList");
			record.setMemberNameGm("memberNameGm");
			record.setMemberNoGm(TestHelp.memberNo_test);
			record.setMerchantNo(TestHelp.merchantNo_test);
			record.setNameList(cfAnalyzeType.getName());
			record.setSeq(count);
			record.setShopName("shopName");
			record.setShopNo(TestHelp.shopNo_test);
			record.setTypeList(cfAnalyzeType.toString());
			record.setImgAddr("/gjfx/"+ "GJFX_"+count+".png");
			count++;
			cfAnalyzeChooseService.insertSelective(record );
		}
		
		
	}
}
