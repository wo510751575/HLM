package com.lj.business.member.dto;
import java.io.Serializable;
import java.util.Date;

import com.lj.base.core.util.StringUtils;


public class FindImIndexPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8597808431511259706L;
	
	/**
	 * code，微信群时为群code
	 */
	private String code;
	
	/**
	 * 商户编号
	 */
	private String merchantNo;
	
	/**
	 * 客户编号，微信群时为群code
	 */
	private String memberNo;
	
	/**
	 * 客户名称
	 */
	private String memberName;
	
	/**
	 * 客户头像，微信群时为群头像
	 */
	private String headAddress;
	
	/**
	 * 导购编号
	 */
	private String memberNoGm;
	
	/**
	 * 导购姓名
	 */
	private String memberNameGm;
	
	
	/**
	 * 客户微信昵称，微信群时为群昵称roomNickName
	 */
	private String nickNameWx;
	
	/**
	 * 客户新增方式
	 */
	private String addType;
	
	/**
	 * 分组编号
	 */
	private String pmTypeCode;
	
	/**
	 * 分组名称
	 */
	private String pmTypeName;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 未读聊天记录数
	 */
	private int unreadCount;
	
	/**
	 * 手机号
	 */
	private String mobile;
	/**
     * 微信号，微信群时为群名chatRoomName .
     */
    private String noWx;
    
    /**
     * 微信号别名 .
     */
    private String noWxAlias;
    
    /**
     * 导购微信号 .
     */
    private String noWxGm;
    
    /**
     * 是否为微信群记录
     */
    private boolean chatRoomFlag;
    
    /**
     * 设置置顶
     */
    private String setUpUser;

    /**
     * 免打扰，0：未开启，1：开启
     * 默认未开启
     */
    private Integer noDisturb;
    
	public Integer getNoDisturb() {
		return noDisturb;
	}

	public void setNoDisturb(Integer noDisturb) {
		this.noDisturb = noDisturb;
	}

	public String getSetUpUser() {
		return setUpUser;
	}

	public void setSetUpUser(String setUpUser) {
		this.setUpUser = setUpUser;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMerchantNo() {
		return StringUtils.toString(merchantNo);
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMemberNo() {
		return StringUtils.toString(memberNo);
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return StringUtils.toString(memberName);
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getHeadAddress() {
		return StringUtils.toString(headAddress);
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	public String getMemberNoGm() {
		return StringUtils.toString(memberNoGm);
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMemberNameGm() {
		return StringUtils.toString(memberNameGm);
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	public String getNickNameWx() {
		return StringUtils.toString(nickNameWx);
	}

	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}

	public String getAddType() {
		return StringUtils.toString(addType);
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public String getPmTypeCode() {
		return StringUtils.toString(pmTypeCode);
	}

	public void setPmTypeCode(String pmTypeCode) {
		this.pmTypeCode = pmTypeCode;
	}

	public String getPmTypeName() {
		return StringUtils.toString(pmTypeName);
	}

	public void setPmTypeName(String pmTypeName) {
		this.pmTypeName = pmTypeName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the unreadCount
	 */
	public int getUnreadCount() {
		return unreadCount;
	}

	/**
	 * @param unreadCount the unreadCount to set
	 */
	public void setUnreadCount(int unreadCount) {
		this.unreadCount = unreadCount;
	}

    public String getNoWx() {
        return noWx;
    }

    public void setNoWx(String noWx) {
        this.noWx = noWx;
    }

    public String getNoWxAlias() {
        return noWxAlias;
    }

    public void setNoWxAlias(String noWxAlias) {
        this.noWxAlias = noWxAlias;
    }
    
    public String getNoWxGm() {
        return noWxGm;
    }

    public void setNoWxGm(String noWxGm) {
        this.noWxGm = noWxGm;
    }

	/**
	 * @return the chatRoomFlag
	 */
	public boolean isChatRoomFlag() {
		return chatRoomFlag;
	}

	/**
	 * @param chatRoomFlag the chatRoomFlag to set
	 */
	public void setChatRoomFlag(boolean chatRoomFlag) {
		this.chatRoomFlag = chatRoomFlag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindImIndexPageReturn [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", addType=");
		builder.append(addType);
		builder.append(", pmTypeCode=");
		builder.append(pmTypeCode);
		builder.append(", pmTypeName=");
		builder.append(pmTypeName);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", unreadCount=");
		builder.append(unreadCount);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", noWxAlias=");
		builder.append(noWxAlias);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", chatRoomFlag=");
		builder.append(chatRoomFlag);
		builder.append("]");
		return builder.toString();
	}

}