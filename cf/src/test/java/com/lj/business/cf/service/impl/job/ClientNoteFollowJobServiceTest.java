package com.lj.business.cf.service.impl.job;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.base.mvc.web.test.SpringTestCase;

public class ClientNoteFollowJobServiceTest extends SpringTestCase {

	@Autowired
	private ClientNoteFollowJobServiceImpl clientNoteFollowJobService;
	
	@Test
	public void jobTest() throws Exception {
		clientNoteFollowJobService.runJob();
	}

}
