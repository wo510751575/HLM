package com.lj.business.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.member.domain.ChatRoomMember;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPage;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPageReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberReturn;
import com.lj.business.member.dto.chatroom.UpdateChatRoomMember;

public interface IChatRoomMemberDao {
    int deleteByPrimaryKey(String code);
    
    int deleteByCond(ChatRoomMember record);
    
    int insertSelective(ChatRoomMember record);

    ChatRoomMember selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ChatRoomMember record);
    
    int synChatRoomOwnerHeadUrl(ChatRoomMember record);
    /**
	 * 同步群成员信息
	 * @param updateChatRoomMember
	 * @return
	 * @throws TsfaServiceException
	 */
    int synChatRoomMember(ChatRoomMember record);
    int updateByRoomCode(ChatRoomMember record);
    
    /**
     * 修改群成员信息根据条件
     * @param record
     * @return
     */
    int updateByCond(ChatRoomMember record);

    List<ChatRoomMember> selectByRoomCode(String roomCode);
    
    /**
	 * 
	 *
	 * 方法说明：查找群成员表信息
	 *
	 * @param findChatRoomMemberPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public int findChatRoomMemberPageCount(FindChatRoomMemberPage findChatRoomMemberPage);
	
	/**
	 * 
	 *
	 * 方法说明：查找群成员表信息
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public List<FindChatRoomMemberPageReturn> findChatRoomMemberPage(FindChatRoomMemberPage findChatRoomMemberPage);
	/**
	 * 
	 *
	 * 方法说明：查询群聊下指定微信的群成员信息
	 *
	 * @param roomCode
	 * @param userName
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月28日
	 *
	 */
	public FindChatRoomMemberReturn findChatRoomMemberByNoWx(@Param("roomCode") String roomCode, @Param("userName") String userName);
	
	/**
	 * 获取群成员昵称以‘,’分隔，排除群主
	 * @param roomCode
	 * @param noWxGm
	 * @return
	 */
	String getNickNameByRoomCode(@Param("roomCode") String roomCode, @Param("noWxGm") String noWxGm);
	
	/**
	 * 取消认领
	 * @param record
	 * @return
	 */
	int cancelClaimed(ChatRoomMember record);

	/**
	 *@Desc 软删除
	 *@param updateChatRoomMember
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月6日下午4:57:45
	 */
	void delete(UpdateChatRoomMember updateChatRoomMember);
}