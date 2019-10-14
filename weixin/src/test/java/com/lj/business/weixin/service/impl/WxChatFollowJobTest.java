package com.lj.business.weixin.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.weixin.service.impl.job.WxChatFollowJobServiceImpl;

public class WxChatFollowJobTest extends SpringTestCase{

	@Resource
	private WxChatFollowJobServiceImpl wxChatFollowJob;
	
	@Test
	public void jobTest() throws Exception {
		wxChatFollowJob.runJob();
	}
}
