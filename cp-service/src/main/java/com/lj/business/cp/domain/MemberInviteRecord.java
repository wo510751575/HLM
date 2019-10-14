package com.lj.business.cp.domain;

import java.util.Date;

public class MemberInviteRecord {
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
     * 微信好友code
     */
    private String addFriendsCode;
    
    /**
     * 微信名称
     */
    private String nickName;

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
     * 规则CODE .
     */
    private String ruleCode;

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
    
    

    public String getAddFriendsCode() {
		return addFriendsCode;
	}

	public void setAddFriendsCode(String addFriendsCode) {
		this.addFriendsCode = addFriendsCode;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 用户手机号 .
     *
     */
    public String getMemberMobile() {
        return memberMobile;
    }

    /**
     * 用户手机号 .
     *
     */
    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile == null ? null : memberMobile.trim();
    }

    /**
     * 用户编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 用户编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 用户姓名 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 用户姓名 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 被邀请人手机号 .
     *
     */
    public String getInvitedMobile() {
        return invitedMobile;
    }

    /**
     * 被邀请人手机号 .
     *
     */
    public void setInvitedMobile(String invitedMobile) {
        this.invitedMobile = invitedMobile == null ? null : invitedMobile.trim();
    }

    /**
     * 被邀请时间 .
     *
     */
    public Date getInvitedDate() {
        return invitedDate;
    }

    /**
     * 被邀请时间 .
     *
     */
    public void setInvitedDate(Date invitedDate) {
        this.invitedDate = invitedDate;
    }

    /**
     * 被邀请人姓名 .
     *
     */
    public String getMemberNameInvited() {
        return memberNameInvited;
    }

    /**
     * 被邀请人姓名 .
     *
     */
    public void setMemberNameInvited(String memberNameInvited) {
        this.memberNameInvited = memberNameInvited == null ? null : memberNameInvited.trim();
    }

    /**
     * 被邀请人微信号 .
     *
     */
    public String getMemberWxInvited() {
        return memberWxInvited;
    }

    /**
     * 被邀请人微信号 .
     *
     */
    public void setMemberWxInvited(String memberWxInvited) {
        this.memberWxInvited = memberWxInvited == null ? null : memberWxInvited.trim();
    }

    /**
     * 被邀请人微信头像 .
     *
     */
    public String getMemberHeadWxInvited() {
        return memberHeadWxInvited;
    }

    /**
     * 被邀请人微信头像 .
     *
     */
    public void setMemberHeadWxInvited(String memberHeadWxInvited) {
        this.memberHeadWxInvited = memberHeadWxInvited == null ? null : memberHeadWxInvited.trim();
    }

    /**
     * 规则CODE .
     *
     */
    public String getRuleCode() {
        return ruleCode;
    }

    /**
     * 规则CODE .
     *
     */
    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
    }

    /**
     * 更新人 .
     *
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 更新人 .
     *
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * 更新时间 .
     *
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间 .
     *
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberInviteRecord [code=");
		builder.append(code);
		builder.append(", memberMobile=");
		builder.append(memberMobile);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", addFriendsCode=");
		builder.append(addFriendsCode);
		builder.append(", nickName=");
		builder.append(nickName);
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
		builder.append(", ruleCode=");
		builder.append(ruleCode);
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