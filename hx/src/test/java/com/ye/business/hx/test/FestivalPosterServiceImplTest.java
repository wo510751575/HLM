package com.ye.business.hx.test;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;

import java.util.Date;
import java.util.List;

import com.ye.business.hx.dto.FestivalPosterDto;
import com.ye.business.hx.dto.FindFestivalPosterPage;
import com.ye.business.hx.service.IFestivalPosterService;

/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
public class FestivalPosterServiceImplTest extends SpringTestCase{

	@Resource
	IFestivalPosterService festivalPosterService;



	/**
	 * 
	 *
	 * 方法说明：添加节日问候海报模板信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addFestivalPoster() throws TsfaServiceException{
		FestivalPosterDto festivalPosterDto = new FestivalPosterDto();
		//add数据录入
		festivalPosterDto.setCode("Code");
		festivalPosterDto.setTypeName("TypeName");
		festivalPosterDto.setImgs("Imgs");
		festivalPosterDto.setUpdateId("UpdateId");
		festivalPosterDto.setUpdateDate(new Date());
		festivalPosterDto.setCreateId("CreateId");
		festivalPosterDto.setCreateDate(new Date());
		festivalPosterDto.setRemark("Remark");
		festivalPosterDto.setRemark1("Remark1");
		festivalPosterDto.setRemark2("Remark2");
		festivalPosterDto.setRemark3("Remark3");
		festivalPosterDto.setRemark4("Remark4");
		
		festivalPosterService.addFestivalPoster(festivalPosterDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改节日问候海报模板信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateFestivalPoster() throws TsfaServiceException{
		FestivalPosterDto festivalPosterDto = new FestivalPosterDto();
		//update数据录入
		festivalPosterDto.setCode("Code");
		festivalPosterDto.setTypeName("TypeName");
		festivalPosterDto.setImgs("Imgs");
		festivalPosterDto.setUpdateId("UpdateId");
		festivalPosterDto.setUpdateDate(new Date());
		festivalPosterDto.setCreateId("CreateId");
		festivalPosterDto.setCreateDate(new Date());
		festivalPosterDto.setRemark("Remark");
		festivalPosterDto.setRemark1("Remark1");
		festivalPosterDto.setRemark2("Remark2");
		festivalPosterDto.setRemark3("Remark3");
		festivalPosterDto.setRemark4("Remark4");

		festivalPosterService.updateFestivalPoster(festivalPosterDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找节日问候海报模板信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findFestivalPoster() throws TsfaServiceException{
		FestivalPosterDto findFestivalPoster = new FestivalPosterDto();
		findFestivalPoster.setCode("111");
		festivalPosterService.findFestivalPoster(findFestivalPoster);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找节日问候海报模板信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findFestivalPosterPage() throws TsfaServiceException{
		FindFestivalPosterPage findFestivalPosterPage = new FindFestivalPosterPage();
		Page<FestivalPosterDto> page = festivalPosterService.findFestivalPosterPage(findFestivalPosterPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找节日问候海报模板信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findFestivalPosters() throws TsfaServiceException{
		FindFestivalPosterPage findFestivalPosterPage = new FindFestivalPosterPage();
		List<FestivalPosterDto> page = festivalPosterService.findFestivalPosters(findFestivalPosterPage);
		Assert.assertNotNull(page);
		
	}
	
}
