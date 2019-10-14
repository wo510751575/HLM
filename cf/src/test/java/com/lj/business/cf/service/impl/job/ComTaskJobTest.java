package com.lj.business.cf.service.impl.job;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.cc.clientintf.IJob;

public class ComTaskJobTest extends SpringTestCase{

	@Resource
	private IJob comTaskJobServiceImpl;
	
	@Test
	public void jobTest() throws Exception {
		comTaskJobServiceImpl.runJob();
	}
	
}
