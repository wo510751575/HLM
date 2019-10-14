package com.lj.business.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.member.domain.BatchSendMessage;

public interface IBatchSendMessageDao {
	
	/**
	 * 
	 *
	 * 方法说明：查询群发列表总数
	 *
	 * @param noWxGm
	 * @return
	 *
	 * @author zlh CreateDate: 2017年11月23日
	 *
	 */
	public int findBatchSendMessagePageCount(BatchSendMessage batchSendMessage);
	
	
	
	/**
	 * 
	 *
	 * 方法说明：查询群发列表
	 *
	 * @param noWxGm
	 * @return
	 *
	 * @author zlh CreateDate: 2017年11月23日
	 *
	 */
	public List<BatchSendMessage> findBatchSendMessagePageList(BatchSendMessage batchSendMessage);
	
	/**
	 * 方法说明 新增发送任务
	 * @param batchSendMessage
	 */
	public void addBatchSendMessage(BatchSendMessage batchSendMessage);
	

}
