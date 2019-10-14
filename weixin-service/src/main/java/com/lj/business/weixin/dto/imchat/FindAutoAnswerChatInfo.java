package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;

public class FindAutoAnswerChatInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 54770060518688204L; 

    /**
     * 导购编号，为空时表示群聊 .
     */
    private String memberNoGm;
    /**
     * 客户编号 .
     */
    private String memberNo;
    
    /**
     * 导购微信号
     * @return
     */
    private String noWxGm;
	
	public String getNoWxGm() {
		return noWxGm;
	}
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}
	public String getMemberNoGm() {
		return memberNoGm;
	}
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindAutoAnswerChatInfo [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append("]");
		return builder.toString();
	}

}
