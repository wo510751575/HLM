/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.member.dao.IPmChatBehaviorDao;
import com.lj.business.member.dto.UpdatePmChatBehavior;
import com.lj.business.member.service.IPmChatBehaviorService;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月29日
 */
@Service
public class PmChatBehaviorServiceImpl implements IPmChatBehaviorService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PmChatBehaviorServiceImpl.class);
	
	@Resource
	private IPmChatBehaviorDao pmChatBehaviorDao;

	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmChatBehaviorService#updateBehavior(com.lj.business.member.dto.UpdatePmChatBehavior)
	 */
	@Override
	public void updateBehavior(UpdatePmChatBehavior updatePmChatBehavior) {
		logger.debug("updateBehavior(UpdatePmChatBehavior updatePmChatBehavior={})", updatePmChatBehavior);

		AssertUtils.notNull(updatePmChatBehavior);
		AssertUtils.notNullAndEmpty(updatePmChatBehavior.getMemberNo(), "客户编号不能为空");
		AssertUtils.notNullAndEmpty(updatePmChatBehavior.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(updatePmChatBehavior.getNoWxGm(), "终端微信号不能为空");
		AssertUtils.notAllNull(updatePmChatBehavior.getChatTime(), updatePmChatBehavior.getThirdUnreadFlag(), "聊天时间和未读标识不能同时为空");
		
		try {
			pmChatBehaviorDao.insertOrUpdate(updatePmChatBehavior);
		} catch(Exception e) {
			logger.error("更新客户最新聊天动态失败", e);
		}
	}

	
	@Override
	public void delete(UpdatePmChatBehavior updatePmChatBehavior) {
		pmChatBehaviorDao.delete(updatePmChatBehavior);
		
	}

}
