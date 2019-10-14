package com.lj.business.weixin.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.smallprogram.AddWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.AddWxSmallProgramReturn;
import com.lj.business.weixin.dto.smallprogram.DelWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.DelWxSmallProgramReturn;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramPage;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramPageReturn;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramReturn;
import com.lj.business.weixin.dto.smallprogram.UpdateWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.UpdateWxSmallProgramReturn;


/**
 * 
 * 
 * 类说明：微信小程序接口类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年03月22日
 */
public interface IWxSmallProgramService {
	
	/**
	 * 
	 *
	 * 方法说明：添加微信小程序信息
	 *
	 * @param addWxSmallProgram
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public AddWxSmallProgramReturn addWxSmallProgram(AddWxSmallProgram addWxSmallProgram) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找微信小程序信息
	 *
	 * @param findWxSmallProgram
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindWxSmallProgramReturn findWxSmallProgram(FindWxSmallProgram findWxSmallProgram) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除微信小程序信息
	 *
	 * @param delWxSmallProgram
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public DelWxSmallProgramReturn delWxSmallProgram(DelWxSmallProgram delWxSmallProgram) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改微信小程序信息
	 *
	 * @param updateWxSmallProgram
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public UpdateWxSmallProgramReturn updateWxSmallProgram(UpdateWxSmallProgram updateWxSmallProgram)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找微信小程序信息
	 *
	 * @param findWxSmallProgramPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public Page<FindWxSmallProgramPageReturn> findWxSmallProgramPage(FindWxSmallProgramPage findWxSmallProgramPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据appid和终端微信查找微信小程序信息
	 *
	 * @param findWxSmallProgram
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindWxSmallProgramReturn findByAppidAndNoWxZk(FindWxSmallProgram findWxSmallProgram) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param updateWxSmallProgram
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午7:06:26
	 */
	public void delete(UpdateWxSmallProgram updateWxSmallProgram)throws TsfaServiceException;

}
