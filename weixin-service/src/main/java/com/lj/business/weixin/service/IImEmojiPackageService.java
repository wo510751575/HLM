package com.lj.business.weixin.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.imemoji.AddImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.AddImEmojiPackageReturn;
import com.lj.business.weixin.dto.imemoji.DelImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.DelImEmojiPackageReturn;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackagePage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackagePageReturn;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackageReturn;
import com.lj.business.weixin.dto.imemoji.FindNewEmojiPackageReturn;
import com.lj.business.weixin.dto.imemoji.NewEmojiPackageReturn;
import com.lj.business.weixin.dto.imemoji.UpdateImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.UpdateImEmojiPackageReturn;



/**
 * 类说明：接口类
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
public interface IImEmojiPackageService {
	
	/**
	 * 
	 *
	 * 方法说明：添加IM表情包信息
	 *
	 * @param addImEmojiPackage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public AddImEmojiPackageReturn addImEmojiPackage(AddImEmojiPackage addImEmojiPackage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找IM表情包信息
	 *
	 * @param findImEmojiPackage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public FindImEmojiPackageReturn findImEmojiPackage(FindImEmojiPackage findImEmojiPackage) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除IM表情包信息
	 *
	 * @param delImEmojiPackage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public DelImEmojiPackageReturn delImEmojiPackage(DelImEmojiPackage delImEmojiPackage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改IM表情包信息
	 *
	 * @param updateImEmojiPackage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public UpdateImEmojiPackageReturn updateImEmojiPackage(UpdateImEmojiPackage updateImEmojiPackage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找IM表情包信息
	 *
	 * @param findImEmojiPackagePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public Page<FindImEmojiPackagePageReturn> findImEmojiPackagePage(FindImEmojiPackagePage findImEmojiPackagePage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：APP查找表情包信息
	 *
	 * @param findImEmojiPackage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public FindNewEmojiPackageReturn findNewEmojiPackage(FindImEmojiPackage findImEmojiPackage);
	
	/**
	 * 
	 *
	 * 方法说明：查询表情包最大版本号
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月2日
	 *
	 */
	public long findMaxVersion() throws TsfaServiceException;

	/**
	 *
	 * 方法说明：查询表情包所有显示序号
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月20日
	 *
	 */
	public List<Integer> findAllShowIndex();

	/**
	 *
	 * 方法说明：查询IM_WBE表情包信息
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月04日
	 *
	 */
	public List<NewEmojiPackageReturn> findImWebEmojiPackage(FindImEmojiPackage findImEmojiPackage) throws TsfaServiceException;
}
