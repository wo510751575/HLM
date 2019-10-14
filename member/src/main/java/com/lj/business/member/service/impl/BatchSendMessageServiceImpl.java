package com.lj.business.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.IPage;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.business.member.dao.IBatchSendMessageDao;
import com.lj.business.member.domain.BatchSendMessage;
import com.lj.business.member.service.IBatchSendMessageService;

@Service
public class BatchSendMessageServiceImpl implements IBatchSendMessageService{

	
	private static final Logger logger = LoggerFactory.getLogger(BatchSendMessageServiceImpl.class);
	
	@Resource
	private IBatchSendMessageDao batchSendMessageDao;
	
	@Override
	public IPage<BatchSendMessage> findBathchSendMessagePage(BatchSendMessage batchSendMessage) {
		logger.debug("findBatchSendMessage(FindBatchSendMessage findBatchSendMessage={}) - start", batchSendMessage); //$NON-NLS-1$
		
		AssertUtils.notNull(batchSendMessage);
		List<BatchSendMessage> returnList = null;
		int count = 0;
			try {
				count = batchSendMessageDao.findBatchSendMessagePageCount(batchSendMessage);
				if(count > 0) {
					returnList = batchSendMessageDao.findBatchSendMessagePageList(batchSendMessage);
				}
			} catch (Exception e) {
				logger.error("群发列表查询错误", e);
			}
		
		Page<BatchSendMessage> returnPage = new Page<BatchSendMessage>(returnList, count, batchSendMessage);

		logger.debug("findBatchSendMessage(findBatchSendMessage) - end - return value={}", returnPage); 
		return returnPage;
	}

	@Override
	public void addBatchSendMessage(BatchSendMessage message) {
		batchSendMessageDao.addBatchSendMessage(message);
		
	}
}
