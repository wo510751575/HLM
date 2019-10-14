package com.lj.business.weixin.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.publicaccount.AddWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.AddWxPublicAccountReturn;
import com.lj.business.weixin.dto.publicaccount.DelWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.DelWxPublicAccountReturn;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPage;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPageReturn;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountReturn;
import com.lj.business.weixin.dto.publicaccount.UpdateWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.UpdateWxPublicAccountReturn;


/**
 * 
 * 
 * 类说明：微信公众号接口类
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
public interface IWxPublicAccountService {
	
	/**
	 * 
	 *
	 * 方法说明：添加微信公众号信息
	 *
	 * @param addWxPublicAccount
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public AddWxPublicAccountReturn addWxPublicAccount(AddWxPublicAccount addWxPublicAccount) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找微信公众号信息
	 *
	 * @param findWxPublicAccount
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindWxPublicAccountReturn findWxPublicAccount(FindWxPublicAccount findWxPublicAccount) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：删除微信公众号信息
	 *
	 * @param delWxPublicAccount
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public DelWxPublicAccountReturn delWxPublicAccount(DelWxPublicAccount delWxPublicAccount) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改微信公众号信息
	 *
	 * @param updateWxPublicAccount
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public UpdateWxPublicAccountReturn updateWxPublicAccount(UpdateWxPublicAccount updateWxPublicAccount)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找微信公众号信息
	 *
	 * @param findWxPublicAccountPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public Page<FindWxPublicAccountPageReturn> findWxPublicAccountPage(FindWxPublicAccountPage findWxPublicAccountPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：根据公众号和终端微信查找微信公众号信息
	 *
	 * @param findWxPublicAccount
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindWxPublicAccountReturn findByUsernameAndNoWxZk(FindWxPublicAccount findWxPublicAccount) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param updateWxPublicAccount
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午6:42:29
	 */
	public void delete(UpdateWxPublicAccount updateWxPublicAccount);

}
