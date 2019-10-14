package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.ChatRoom;
import com.lj.business.member.domain.ChatRoomPm;

public interface IChatRoomPmDao {

	List<ChatRoom>  findByPmCode(com.lj.business.member.domain.ChatRoom chatroom);

	List<ChatRoomPm> selectRoomGroup(com.lj.business.member.domain.ChatRoomPm chatroompm);
	int insertSelective(ChatRoom record);
}
