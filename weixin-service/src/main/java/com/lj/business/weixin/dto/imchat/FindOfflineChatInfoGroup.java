package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * 类说明：导购手机客户端返回按客户分组的离线聊天记录明细
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月30日
 */
public class FindOfflineChatInfoGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3251484530086329493L;
	
	/**
	 * 客户编号
	 */
	private String memberNo;
	
	/**
	 * 客户微信号
	 */
	private String noWx;
	
	/**
	 * 客户微信别名
	 */
	private String alias;
	
	/**
	 * 离线记录数
	 */
	private int count;
	
	/**
	 * 记录明细
	 */
	private List<FindOfflineChatInfoDetail> chatList;

	 /**
	 * 群头像
	 */
	private String headUrl;
	
	/**
	 * 群昵称
	 */
	private String roomNickName;
	
	/**
	 * 群聊类型：1单聊、2群聊
	 */
	private String chatroomType;
	
	/**
     * 免打扰，0：未开启，1：开启
     * 默认未开启
     */
    private Integer noDisturb=0;
    
    public Integer getNoDisturb() {
		return noDisturb;
	}

	public void setNoDisturb(Integer noDisturb) {
		this.noDisturb = noDisturb;
	}
	public String getChatroomType() {
		return chatroomType;
	}

	public void setChatroomType(String chatroomType) {
		this.chatroomType = chatroomType;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getRoomNickName() {
		return roomNickName;
	}

	public void setRoomNickName(String roomNickName) {
		this.roomNickName = roomNickName;
	}


	/**
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the chatList
	 */
	public List<FindOfflineChatInfoDetail> getChatList() {
		return chatList;
	}

	/**
	 * @param chatList the chatList to set
	 */
	public void setChatList(List<FindOfflineChatInfoDetail> chatList) {
		this.chatList = chatList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindOfflineChatInfoGroup [memberNo=");
		builder.append(memberNo);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", count=");
		builder.append(count);
		builder.append(", chatList=");
		builder.append(chatList);
		builder.append(", headUrl=");
		builder.append(headUrl);
		builder.append(", roomNickName=");
		builder.append(roomNickName);
		builder.append(", chatroomType=");
		builder.append(chatroomType);
		builder.append("]");
		return builder.toString();
	}

}
