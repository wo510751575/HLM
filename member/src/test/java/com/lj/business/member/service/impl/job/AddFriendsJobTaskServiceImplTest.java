package com.lj.business.member.service.impl.job;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.cc.clientintf.IJob;

public class AddFriendsJobTaskServiceImplTest extends SpringTestCase{
	
	@Resource
	private IJob addFriendsJobTaskServiceImpl;
	@Test
	public void jobTest() throws Exception {
		addFriendsJobTaskServiceImpl.runJob();
	}
}
