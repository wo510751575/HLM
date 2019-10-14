package com.lj.business.member.service;

import com.lj.base.core.pagination.IPage;
import com.lj.business.member.domain.BatchSendMessage;
import com.lj.business.member.dto.addfriends.FindClaimMemberPageReturn;

public interface IBatchSendMessageService {

	public void addBatchSendMessage(BatchSendMessage message);

	public IPage<BatchSendMessage> findBathchSendMessagePage(BatchSendMessage message);
	
}
