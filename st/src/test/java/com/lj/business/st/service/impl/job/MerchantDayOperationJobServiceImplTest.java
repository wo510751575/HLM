package com.lj.business.st.service.impl.job;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.base.exception.TsfaServiceException;
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
 * @author 彭阳
 *   
 * CreateDate: 2017年9月29日
 */
public class MerchantDayOperationJobServiceImplTest extends SpringTestCase{
	
	@Autowired
	private MerchantDayOperationJobServiceImpl merchantDayOperationJobServiceImpl;
	

	
	@Test
	public void runJob() throws TsfaServiceException{ 
		merchantDayOperationJobServiceImpl.runJob();
	}
	
	
}
