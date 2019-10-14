package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

public class FindUnchatMemberPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4201563983362891747L;

	/**
	 * 客户编号 .
	 */
	private String memberNo;

	/**
	 * 客户名称 .
	 */
	private String memberName;
	
	/**
	 * 导购编号
	 */
	private String memberNoGm;

	/**
	 * 分店编号 .
	 */
	

	/**
	 * 分店名称 .
	 */
	

	/**
	 * 创建时间 .
	 */
	private Date createDate;

    /**
     * 手机号 .
     */
    private String mobile;

    /**
     * 会员来源 ：微博营销 等 .
     */
    private String memberSrc;

    /**
     * 昵称_微信 .
     */
    private String nickNameWx;

    /**
     * 头像地址 .
     */
    private String headAddress;

    /** 微信号. */
    private String noWx;
    
    /** 微信号别名. */
    private String noWxAlias;
    
    /**
     * 聊天记录数
     */
    private int chatCount;

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
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

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
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the memberSrc
	 */
	public String getMemberSrc() {
		return memberSrc;
	}

	/**
	 * @param memberSrc the memberSrc to set
	 */
	public void setMemberSrc(String memberSrc) {
		this.memberSrc = memberSrc;
	}

	/**
	 * @return the nickNameWx
	 */
	public String getNickNameWx() {
		return nickNameWx;
	}

	/**
	 * @param nickNameWx the nickNameWx to set
	 */
	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}

	/**
	 * @return the headAddress
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * @param headAddress the headAddress to set
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
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
	 * @return the noWxAlias
	 */
	public String getNoWxAlias() {
		return noWxAlias;
	}

	/**
	 * @param noWxAlias the noWxAlias to set
	 */
	public void setNoWxAlias(String noWxAlias) {
		this.noWxAlias = noWxAlias;
	}

	/**
	 * @return the chatCount
	 */
	public int getChatCount() {
		return chatCount;
	}

	/**
	 * @param chatCount the chatCount to set
	 */
	public void setChatCount(int chatCount) {
		this.chatCount = chatCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindUnchatMemberPageReturn [memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", memberSrc=");
		builder.append(memberSrc);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", noWxAlias=");
		builder.append(noWxAlias);
		builder.append(", chatCount=");
		builder.append(chatCount);
		builder.append("]");
		return builder.toString();
	}

}
