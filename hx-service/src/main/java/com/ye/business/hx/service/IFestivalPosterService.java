package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.FestivalPosterDto;
import com.ye.business.hx.dto.FindFestivalPosterPage;


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
/**
 * 类说明：接口类
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
public interface IFestivalPosterService {
	
	/**
	 * 
	 *
	 * 方法说明：添加节日问候海报模板信息
	 *
	 * @param festivalPosterDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addFestivalPoster(FestivalPosterDto festivalPosterDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找节日问候海报模板信息
	 *
	 * @param findFestivalPoster
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public FestivalPosterDto findFestivalPoster(FestivalPosterDto festivalPosterDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询节日问候海报模板信息
	 *
	 * @param findFestivalPosterPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<FestivalPosterDto>  findFestivalPosters(FindFestivalPosterPage findFestivalPosterPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改节日问候海报模板信息
	 *
	 * @param updateFestivalPoster
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateFestivalPoster(FestivalPosterDto festivalPosterDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询节日问候海报模板信息
	 *
	 * @param findFestivalPosterPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<FestivalPosterDto> findFestivalPosterPage(FindFestivalPosterPage findFestivalPosterPage) throws TsfaServiceException;
	

	/**
	 * 
	 *
	 * 方法说明：修改节日问候海报模板信息
	 *
	 * @param updateFestivalPoster
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void deleteFestivalPoster(FestivalPosterDto festivalPosterDto)throws TsfaServiceException;

}
