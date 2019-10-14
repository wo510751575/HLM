package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.terminallogfiles.AddTerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.DelTerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesPage;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesPageReturn;
import com.lj.business.member.dto.terminallogfiles.UpdateTerminalLogFiles;
import com.lj.business.member.service.ITerminalLogFilesService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭俊霖
 * 
 * 
 * CreateDate: 2017-11-01
 */
public class TerminalLogFilesServiceImplTest extends SpringTestCase{

	@Resource
	ITerminalLogFilesService terminalLogFilesService;



	/**
	 * 
	 *
	 * 方法说明：添加终端日志文件信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void addTerminalLogFiles() throws TsfaServiceException{
		AddTerminalLogFiles addTerminalLogFiles = new AddTerminalLogFiles();
		//add数据录入
		addTerminalLogFiles.setCode("Code");
		addTerminalLogFiles.setImei("866036038849425");
		addTerminalLogFiles.setTerminalCode("TerminalCode");
		addTerminalLogFiles.setLogBeginTime(new Date());
		addTerminalLogFiles.setLogFileName("LogFileName11");
		addTerminalLogFiles.setLogAddr("LogAddr");
		addTerminalLogFiles.setUploadTime(new Date());
		addTerminalLogFiles.setMerchantNo("fcecbfa097944565a58134d170f474af");
		addTerminalLogFiles.setMerchantName("MerchantName");
//		addTerminalLogFiles.setShopNo("ShopNo");
//		addTerminalLogFiles.setShopName("ShopName");
		addTerminalLogFiles.setCreateDate(new Date());
		
		Assert.assertNotNull(terminalLogFilesService.addTerminalLogFiles(addTerminalLogFiles ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改终端日志文件信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void updateTerminalLogFiles() throws TsfaServiceException{
		UpdateTerminalLogFiles updateTerminalLogFiles = new UpdateTerminalLogFiles();
		//update数据录入
		updateTerminalLogFiles.setCode("Code");
		updateTerminalLogFiles.setImei("Imei");
		updateTerminalLogFiles.setTerminalCode("TerminalCode");
		updateTerminalLogFiles.setLogBeginTime(new Date());
		updateTerminalLogFiles.setLogFileName("LogFileName");
		updateTerminalLogFiles.setLogAddr("LogAddr");
		updateTerminalLogFiles.setUploadTime(new Date());
		updateTerminalLogFiles.setMerchantNo("MerchantNo");
		updateTerminalLogFiles.setMerchantName("MerchantName");
//		updateTerminalLogFiles.setShopNo("ShopNo");
//		updateTerminalLogFiles.setShopName("ShopName");
		updateTerminalLogFiles.setCreateDate(new Date());

		terminalLogFilesService.updateTerminalLogFiles(updateTerminalLogFiles );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找终端日志文件信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void findTerminalLogFiles() throws TsfaServiceException{
		FindTerminalLogFiles findTerminalLogFiles = new FindTerminalLogFiles();
		findTerminalLogFiles.setCode("code");
		terminalLogFilesService.findTerminalLogFiles(findTerminalLogFiles);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找终端日志文件信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void findTerminalLogFilesPage() throws TsfaServiceException{
		FindTerminalLogFilesPage findTerminalLogFilesPage = new FindTerminalLogFilesPage();
		Page<FindTerminalLogFilesPageReturn> page = terminalLogFilesService.findTerminalLogFilesPage(findTerminalLogFilesPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除终端日志文件信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void delTerminalLogFiles() throws TsfaServiceException{
		DelTerminalLogFiles delTerminalLogFiles = new DelTerminalLogFiles();
//		delTerminalLogFiles.setCode("code");
		Assert.assertNotNull(terminalLogFilesService.delTerminalLogFiles(delTerminalLogFiles));
		
	}
	
	
}
