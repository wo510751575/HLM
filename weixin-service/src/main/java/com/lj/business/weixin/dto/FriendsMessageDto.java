package com.lj.business.weixin.dto;

import java.io.Serializable;
import java.util.Date;

public class FriendsMessageDto    implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6714842447823865794L;
	
	
	private String code;
	/**
	 * 朋友圈CODE
	 */
	private String friendsCode;
	/**
	 * 商户号
	 */
	private String merchantNo;
	
	/**
	 * 终端号
	 */
	
	/**
	 * 终端微信号
	 */
	private String noWxShop;
	/**
	 * 导购编号
	 */
	private String memberNoGm;
	
	/**
	 * 会员编号
	 */
	private String memberNo;
	/**
	 * 消息类型
	 */
	private String type;
	/**
	 * 用户微信号
	 */
	private String username;
	/**
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * 被评论人微信号
	 */
	private String toUserName;
	/**
	 * 被评论人昵称
	 */
	private String toNickName;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	
	private String headImg;
	
	private String friendsType;
	
	private String friendsContent;
	
	private String friendsImg;
	
	private String shareUrl;
	
	private String shareTitle;
	
	
	
	
	private String beginDate;
	
	private String endDate;
	
	private String memberName;

	/**
	 * 操作人标识：1自己、2客户 .
	 */
	private Integer optFlag;
	
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getFriendsType() {
		return friendsType;
	}

	public void setFriendsType(String friendsType) {
		this.friendsType = friendsType;
	}

	public String getFriendsContent() {
		return friendsContent;
	}

	public void setFriendsContent(String friendsContent) {
		this.friendsContent = friendsContent;
	}

	public String getFriendsImg() {
		return friendsImg;
	}

	public void setFriendsImg(String friendsImg) {
		this.friendsImg = friendsImg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFriendsCode() {
		return friendsCode;
	}

	public void setFriendsCode(String friendsCode) {
		this.friendsCode = friendsCode;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}


	public String getNoWxShop() {
		return noWxShop;
	}

	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getToNickName() {
		return toNickName;
	}

	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	/**
	 * @return the optFlag
	 */
	public Integer getOptFlag() {
		return optFlag;
	}

	/**
	 * @param optFlag the optFlag to set
	 */
	public void setOptFlag(Integer optFlag) {
		this.optFlag = optFlag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FriendsMessageDto [code=");
		builder.append(code);
		builder.append(", friendsCode=");
		builder.append(friendsCode);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", type=");
		builder.append(type);
		builder.append(", username=");
		builder.append(username);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", toUserName=");
		builder.append(toUserName);
		builder.append(", toNickName=");
		builder.append(toNickName);
		builder.append(", content=");
		builder.append(content);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", headImg=");
		builder.append(headImg);
		builder.append(", friendsType=");
		builder.append(friendsType);
		builder.append(", friendsContent=");
		builder.append(friendsContent);
		builder.append(", friendsImg=");
		builder.append(friendsImg);
		builder.append(", shareUrl=");
		builder.append(shareUrl);
		builder.append(", shareTitle=");
		builder.append(shareTitle);
		builder.append(", beginDate=");
		builder.append(beginDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", optFlag=");
		builder.append(optFlag);
		builder.append("]");
		return builder.toString();
	}

}
