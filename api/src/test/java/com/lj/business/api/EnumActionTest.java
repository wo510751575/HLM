package com.lj.business.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
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
public class EnumActionTest extends SpringTestCase {
	
	@Test
	public void testEnum() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		ApiHelp.doPost("enum/enumtype.do", businessParamMap);
	}

}
