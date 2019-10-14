package com.lj.business.cp.dto.memberInviteRecord;

import java.io.Serializable;
import java.util.Date;

public class FindMemberInviteRecordReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7711493209284883390L;

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 用户手机号 .
	 */
	private String memberMobile;

	/**
	 * 用户编号 .
	 */
	private String memberNo;

	/**
	 * 用户姓名 .
	 */
	private String memberName;
	
	/**
	 * 微信名称
	 */
	private String nickName;

	/**
	 * 微信好友code
	 */
	private String addFriendsCode;
	
	/**
	 * 被邀请人手机号 .
	 */
	private String invitedMobile;

	/**
	 * 被邀请时间 .
	 */
	private Date invitedDate;

	/**
	 * 被邀请人姓名 .
	 */
	private String memberNameInvited;

	/**
	 * 被邀请人微信号 .
	 */
	private String memberWxInvited;

	/**
	 * 被邀请人微信头像 .
	 */
	private String memberHeadWxInvited;

	/**
	 * 更新人 .
	 */
	private String updateId;

	/**
	 * 更新时间 .
	 */
	private Date updateDate;

	/**
	 * 创建人 .
	 */
	private String createId;

	/**
	 * 创建时间 .
	 */
	private Date createDate;

	
	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAddFriendsCode() {
		return addFriendsCode;
	}

	public void setAddFriendsCode(String addFriendsCode) {
		this.addFriendsCode = addFriendsCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getInvitedMobile() {
		return invitedMobile;
	}

	public void setInvitedMobile(String invitedMobile) {
		this.invitedMobile = invitedMobile;
	}

	public Date getInvitedDate() {
		return invitedDate;
	}

	public void setInvitedDate(Date invitedDate) {
		this.invitedDate = invitedDate;
	}

	public String getMemberNameInvited() {
		return memberNameInvited;
	}

	public void setMemberNameInvited(String memberNameInvited) {
		this.memberNameInvited = memberNameInvited;
	}

	public String getMemberWxInvited() {
		return memberWxInvited;
	}

	public void setMemberWxInvited(String memberWxInvited) {
		this.memberWxInvited = memberWxInvited;
	}

	public String getMemberHeadWxInvited() {
		return memberHeadWxInvited;
	}

	public void setMemberHeadWxInvited(String memberHeadWxInvited) {
		this.memberHeadWxInvited = memberHeadWxInvited;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindMemberInviteRecordReturn [code=");
		builder.append(code);
		builder.append(", memberMobile=");
		builder.append(memberMobile);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", addFriendsCode=");
		builder.append(addFriendsCode);
		builder.append(", invitedMobile=");
		builder.append(invitedMobile);
		builder.append(", invitedDate=");
		builder.append(invitedDate);
		builder.append(", memberNameInvited=");
		builder.append(memberNameInvited);
		builder.append(", memberWxInvited=");
		builder.append(memberWxInvited);
		builder.append(", memberHeadWxInvited=");
		builder.append(memberHeadWxInvited);
		builder.append(", updateId=");
		builder.append(updateId);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}


}
