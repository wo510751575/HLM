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
import com.lj.business.weixin.dto.imemoji.AddImEmoji;
import com.lj.business.weixin.dto.imemoji.AddImEmojiReturn;
import com.lj.business.weixin.dto.imemoji.DelImEmoji;
import com.lj.business.weixin.dto.imemoji.DelImEmojiReturn;
import com.lj.business.weixin.dto.imemoji.FindImEmoji;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPageReturn;
import com.lj.business.weixin.dto.imemoji.FindImEmojiReturn;
import com.lj.business.weixin.dto.imemoji.UpdateImEmoji;
import com.lj.business.weixin.dto.imemoji.UpdateImEmojiReturn;


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
public interface IImEmojiService {
	
	/**
	 * 
	 *
	 * 方法说明：添加IM表情信息
	 *
	 * @param addImEmoji
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public AddImEmojiReturn addImEmoji(AddImEmoji addImEmoji) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找IM表情信息
	 *
	 * @param findImEmoji
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public FindImEmojiReturn findImEmoji(FindImEmoji findImEmoji) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除IM表情信息
	 *
	 * @param delImEmoji
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public DelImEmojiReturn delImEmoji(DelImEmoji delImEmoji) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改IM表情信息
	 *
	 * @param updateImEmoji
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public UpdateImEmojiReturn updateImEmoji(UpdateImEmoji updateImEmoji)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找IM表情信息
	 *
	 * @param findImEmojiPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public Page<FindImEmojiPageReturn> findImEmojiPage(FindImEmojiPage findImEmojiPage) throws TsfaServiceException;

	/**
	 *
	 * 方法说明：查询该表情包表情所有显示序号
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月20日
	 * @param string 
	 *
	 */
	public List<Integer> findAllShowIndex(String packageCode);
	

}
