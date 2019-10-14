package com.lj.business.weixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dao.IImChatInfoSensitiveDao;
import com.lj.business.weixin.dto.FindImChatInfoSensitivePage;
import com.lj.business.weixin.dto.FindImChatInfoSensitivePageReturn;
import com.lj.business.weixin.service.IImChatInfoSensitiveService;

/**
 * 
 * 
 * 类说明：IM聊天敏感词记录实现
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2018年1月11日
 */

@Service
public class ImChatInfoSensitiveServiceImpl implements IImChatInfoSensitiveService{
	
	private static final Logger logger = LoggerFactory.getLogger(ImChatInfoSensitiveServiceImpl.class);
	
	@Resource
	private IImChatInfoSensitiveDao imChatInfoSensitiveDao;			

	@Override
	public Page<FindImChatInfoSensitivePageReturn> findImChatInfoSensitivePage(
			FindImChatInfoSensitivePage findImChatInfoSensitivePage) throws TsfaServiceException {
		logger.debug("FindImChatInfoSensitivePage",findImChatInfoSensitivePage);
		List<FindImChatInfoSensitivePageReturn> list = null;
		int count ;
		try {
        	count = imChatInfoSensitiveDao.findImChatInfoSensitiveCount(findImChatInfoSensitivePage);
			if(count > 0){
			list = imChatInfoSensitiveDao.findImChatInfoSensitivePage(findImChatInfoSensitivePage);
			}
		} catch (Exception e) {
			logger.error("查询IM聊天记录敏感词信息错误",e);
		 throw new TsfaServiceException("FindImChatInfoSensitivePage", "查询IM聊天记录敏感词信息错误", e);
		}
		Page<FindImChatInfoSensitivePageReturn> returnPage = new Page<FindImChatInfoSensitivePageReturn>(list, count, findImChatInfoSensitivePage);
		logger.debug("FindImChatInfoSensitivePageReturn",returnPage);
		return returnPage;
	}

}
