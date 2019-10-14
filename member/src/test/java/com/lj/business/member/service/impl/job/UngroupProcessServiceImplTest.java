package com.lj.business.member.service.impl.job;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.base.mvc.web.test.SpringTestCase;

/**
 * 
 * 
 * 类说明：未分组任务处理：未分组人员自动分组到意向未到店，每日凌晨跑批
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年10月30日
 */
public class UngroupProcessServiceImplTest extends SpringTestCase {

	@Autowired
	private UngroupProcessServiceImpl ungroupProcessServiceImpl;
	
	@Test
	public void runJob() {
		ungroupProcessServiceImpl.runJob();
	}
}
