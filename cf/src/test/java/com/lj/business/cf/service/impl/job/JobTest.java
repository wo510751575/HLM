package com.lj.business.cf.service.impl.job;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.cc.clientintf.IJob;

public class JobTest extends SpringTestCase {

	@Resource
	private IJob wxChatFollowJobService;
	
	@Test
	public void test() throws Exception {
		wxChatFollowJobService.runJob();
	}
	
}
