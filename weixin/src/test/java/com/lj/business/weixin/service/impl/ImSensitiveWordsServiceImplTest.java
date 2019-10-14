package com.lj.business.weixin.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.weixin.dto.imSensitiveWords.AddImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.DelImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsPage;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsPageReturn;
import com.lj.business.weixin.dto.imSensitiveWords.UpdateImSensitiveWords;
import com.lj.business.weixin.service.IImSensitiveWordsService;



public class ImSensitiveWordsServiceImplTest extends SpringTestCase{

	@Resource
	IImSensitiveWordsService imSensitiveWordsService;



	/**
	 * 
	 *
	 * 方法说明：添加IM敏感词表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addImSensitiveWords() throws TsfaServiceException{
		AddImSensitiveWords addImSensitiveWords = new AddImSensitiveWords();
		//add数据录入
		addImSensitiveWords.setWord("Word");
		addImSensitiveWords.setVersion(0l);
		addImSensitiveWords.setStatus(0);
		addImSensitiveWords.setShowIndex(0);
		addImSensitiveWords.setCreateId("CreateId");
		addImSensitiveWords.setRemark("Remark");
		addImSensitiveWords.setRemark2("Remark2");
		addImSensitiveWords.setRemark3("Remark3");
		addImSensitiveWords.setRemark4("Remark4");
		
		imSensitiveWordsService.addImSensitiveWords(addImSensitiveWords );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改IM敏感词表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateImSensitiveWords() throws TsfaServiceException{
		UpdateImSensitiveWords updateImSensitiveWords = new UpdateImSensitiveWords();
		//update数据录入
		updateImSensitiveWords.setCode("LJ_57732b19004847aea26fae9edf4193de");
		updateImSensitiveWords.setWord("Word");
		updateImSensitiveWords.setVersion(0l);
		updateImSensitiveWords.setStatus(0);
		updateImSensitiveWords.setShowIndex(0);
		updateImSensitiveWords.setCreateId("CreateId");
		updateImSensitiveWords.setCreateDate(new Date());
		updateImSensitiveWords.setRemark("Remark");
		updateImSensitiveWords.setRemark2("Remark2");
		updateImSensitiveWords.setRemark3("Remark3");
		updateImSensitiveWords.setRemark4("Remark4");

		imSensitiveWordsService.updateImSensitiveWords(updateImSensitiveWords );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找IM敏感词表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findImSensitiveWords() throws TsfaServiceException{
		FindImSensitiveWords findImSensitiveWords = new FindImSensitiveWords();
		findImSensitiveWords.setCode("LJ_57732b19004847aea26fae9edf4193de");
		imSensitiveWordsService.findImSensitiveWords(findImSensitiveWords);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找IM敏感词表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findImSensitiveWordsPage() throws TsfaServiceException{
		FindImSensitiveWordsPage findImSensitiveWordsPage = new FindImSensitiveWordsPage();
		Page<FindImSensitiveWordsPageReturn> page = imSensitiveWordsService.findImSensitiveWordsPage(findImSensitiveWordsPage);
		System.out.println(page);
		
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除IM敏感词表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void delImSensitiveWords() throws TsfaServiceException{
		DelImSensitiveWords delImSensitiveWords = new DelImSensitiveWords();
		delImSensitiveWords.setCode("LJ_57732b19004847aea26fae9edf4193de");
		Assert.assertNotNull(imSensitiveWordsService.delImSensitiveWords(delImSensitiveWords));
		
	}
	
	@Test
	public void findAllSensitiveWords() {
		System.out.println(imSensitiveWordsService.findAllSensitiveWords());
	}
	
	public static void main(String[] args) {
		File file = new File("D:\\扬恩科技\\work\\中控\\敏感词\\2.txt");
		try {
			FileInputStream input = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String data = null;
			long now = System.currentTimeMillis();
			int index = 0;
			try {
				while ((data = br.readLine()) != null) {
					String string = "insert into weixin.IM_SENSITIVE_WORDS(code, WORD, VERSION, STATUS, SHOW_INDEX, CREATE_DATE) value ('" 
							+ GUID.getPreUUID() + "', '" + data + "', " + (++index + now) + ", 1, " + index + ", now());";
					System.out.println(string);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
