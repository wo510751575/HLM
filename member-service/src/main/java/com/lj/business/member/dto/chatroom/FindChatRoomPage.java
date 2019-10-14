package com.lj.business.member.dto.chatroom;

import com.lj.base.core.pagination.PageParamEntity;

public class FindChatRoomPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6682941449124548245L; 
	/**
     * 导购编号 .
     */
    private String memberNoGm;
    
    private String pmCode;


	/**
     * 终端编号 .
     */
    
    /**
     * 商户编号 .
     */
    private String merchantNo;
    /**
     * 中控微信号 .
     */
    private String noWxZk;
    /**
     * 群名 .
     */
    private String chatRoomName;
    /**
     * 群主用户名 .
     */
    private String roomOwner;
    /**
     * 状态：0初始化、1有效、2无效（删除） .
     */
    private Integer status;
    /**
     * 是否认领
     * true = 已认领 /memberNoGm is not null
     * false = 未认领 /memberNoGm is null
     */
    private Boolean isClaimed;
    
    public String getPmCode() {
		return pmCode;
	}

	public void setPmCode(String pmCode) {
		this.pmCode = pmCode;
	}
    public Boolean isClaimed() {
		return isClaimed;
	}

	public void setClaimed(boolean isClaimed) {
		this.isClaimed = isClaimed;
	}

	public void setNoWxZk(String noWxZk) {
		this.noWxZk = noWxZk;
	}

	public String getChatRoomName() {
		return chatRoomName;
	}

	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}

	public String getRoomOwner() {
		return roomOwner;
	}

	public void setRoomOwner(String roomOwner) {
		this.roomOwner = roomOwner;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		return "FindChatRoomPage [memberNoGm=" + memberNoGm + ", pmCode=" + pmCode + ", merchantNo=" + merchantNo
				+ ", noWxZk=" + noWxZk + ", chatRoomName=" + chatRoomName + ", roomOwner=" + roomOwner + ", status="
				+ status + ", isClaimed=" + isClaimed + "]";
	}

}
