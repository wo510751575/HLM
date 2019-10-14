package com.lj.business.member.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.service.person.FindServicePersonApp;
import com.lj.business.member.service.IServicePersonService;

public class ServicePersonServiceImplTest extends SpringTestCase {

	@Autowired
	private IServicePersonService servicePersonService;
	
	@Test
	public void testFindServicePersonApp() {
		FindServicePersonApp findServicePersonApp = new FindServicePersonApp();
//		findServicePersonApp.setShopNo("LJ_59ed041e8d354b389f01bf8ad5321ed7");
		logger.info(servicePersonService.findServicePersonApp(findServicePersonApp).toString());
	}
	
}
