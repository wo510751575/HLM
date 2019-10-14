package com.lj.business.cf.dao;

import java.util.List;

import com.lj.business.cf.domain.MessagePush;
import com.lj.business.cf.dto.messagePush.FindMessagePushPage;
import com.lj.business.cf.dto.messagePush.FindMessagePushPageReturn;

public interface IMessagePushDao {
    int deleteByPrimaryKey(String code);

    int insert(MessagePush record);

    int insertSelective(MessagePush record);

    MessagePush selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MessagePush record);

    int updateByPrimaryKey(MessagePush record);

	List<FindMessagePushPageReturn> findMessagePushPage(
			FindMessagePushPage findMessagePushPage);

	int findMessagePushPageCount(FindMessagePushPage findMessagePushPage);
	
	List<FindMessagePushPageReturn> findMessagePushByGm(
			FindMessagePushPage findMessagePushPage);

	List<FindMessagePushPageReturn> findMessagePushByPm(
			FindMessagePushPage findMessagePushPage);
}