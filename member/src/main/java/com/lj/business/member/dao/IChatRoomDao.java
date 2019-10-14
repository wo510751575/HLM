package com.lj.business.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.member.domain.ChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomPage;
import com.lj.business.member.dto.chatroom.FindChatRoomPageReturn;
import com.lj.business.member.dto.chatroom.UpdateChatRoom;


public interface IChatRoomDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(ChatRoom record);

    ChatRoom selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ChatRoom record);

    ChatRoom selectBySelective(FindChatRoom chatRoom);
    
    int deleteByRoomName(@Param("chatRoomName") String chatRoomName,@Param("noWxGm") String noWxGm);
    
    int updateSetUpUser(ChatRoom record);
    
    int updateCancleSetUpUser(ChatRoom record);
    
    List<FindChatRoomPageReturn> findChatRoomPage(FindChatRoomPage findChatRoomPage);

	int findChatRoomPageCount(FindChatRoomPage findChatRoomPage);

	List<FindChatRoomPageReturn> findChatRooms(FindChatRoomPage findChatRoomPage);
	

	/**
	 * 取消认领
	 * @param record
	 * @return
	 */
	int cancelClaimed(ChatRoom record);

	String selectSetUp(String roomCode);


	/**
	 *@Desc 软删除
	 *@param updateChatRoom
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月6日下午4:40:11
	 */
	void delete(UpdateChatRoom updateChatRoom);
}