package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.Date;

public class AddImChatInfoReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6409489114035939255L; 
	
    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 消息状态：1 未发送、2发送成功、3发送失败 .
     * 客户端重新发送聊天记录的情况下，服务器数据库返回该记录的发送状态
     */
    private String status = "1";	// 默认为未发送
    
    /**
     * 是否为未找到客户数据(只存储中控端由客户端发给导购的聊天记录)，默认为否false
     * 为true时，聊天记录不能再转发给接收端
     */
    private boolean tempBool = Boolean.FALSE;

	/**
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the tempBool
	 */
	public boolean isTempBool() {
		return tempBool;
	}

	/**
	 * @param tempBool the tempBool to set
	 */
	public void setTempBool(boolean tempBool) {
		this.tempBool = tempBool;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddImChatInfoReturn [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", tempBool=");
		builder.append(tempBool);
		builder.append("]");
		return builder.toString();
	}

}
