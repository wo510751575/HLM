package com.lj.business.weixin.dto;

import java.io.Serializable;
import java.util.Date;

public class ImCommentInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6121869471372030274L;

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 朋友圈信息CODE .
	 */
	private String friendsCode;

	/**
	 * 朋友圈ID .
	 */
	private String friendsId;

	/**
	 * 商户编号 .
	 */
	private String merchantNo;

	/**
	 * 商户名称 .
	 */
	private String merchantName;

	/**
	 * 终端微信 .
	 */
	private String noWxShop;

	/**
	 * 操作人标识：1自己、2客户 .
	 */
	private Integer optFlag;

	/**
	 * 客户编号 .
	 */
	private String memberNo;

	/**
	 * 客户名称 .
	 */
	private String memberName;

	/**
	 * 评论人微信号 .
	 */
	private String username;

	/**
	 * 评论昵称 .
	 */
	private String nickname;

	/**
	 * 回复人微信号 .
	 */
	private String tousername;

	/**
	 * 回复人昵称 .
	 */
	private String tonickname;

	/**
	 * 消息状态：1 发送中、2发送成功、3发送失败 .
	 */
	private Integer status;

	/**
	 * 创建时间 .
	 */
	private Date createDate;

	/**
	 * 备注 .
	 */
	private String remark;

	/**
	 * 备注 .
	 */
	private String remark2;

	/**
	 * 备注 .
	 */
	private String remark3;

	/**
	 * 备注 .
	 */
	private String remark4;

	/**
	 * 内容 .
	 */
	private String content;

	/**
	 * 手机串号
	 */
	private String imei;

	private String memberNoGm;

	private String memberNoGmName;
	
	private String commentTime;
	
	private String commentSerId;

	/**
	 * app已读标识
	 */
	private String appReadFlag;

	/**
	 * web已读标识
	 */
	private String webReadFlag;

	

	public String getCommentSerId() {
		return commentSerId;
	}

	public void setCommentSerId(String commentSerId) {
		this.commentSerId = commentSerId;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMemberNoGmName() {
		return memberNoGmName;
	}

	public void setMemberNoGmName(String memberNoGmName) {
		this.memberNoGmName = memberNoGmName;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
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
		this.code = code;
	}

	/**
	 * 朋友圈信息CODE .
	 *
	 */
	public String getFriendsCode() {
		return friendsCode;
	}

	/**
	 * 朋友圈信息CODE .
	 *
	 */
	public void setFriendsCode(String friendsCode) {
		this.friendsCode = friendsCode;
	}

	/**
	 * 朋友圈ID .
	 *
	 */
	public String getFriendsId() {
		return friendsId;
	}

	/**
	 * 朋友圈ID .
	 *
	 */
	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}

	/**
	 * 商户编号 .
	 *
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * 商户编号 .
	 *
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * 商户名称 .
	 *
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * 商户名称 .
	 *
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * 终端微信 .
	 *
	 */
	public String getNoWxShop() {
		return noWxShop;
	}

	/**
	 * 终端微信 .
	 *
	 */
	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}

	/**
	 * 操作人标识：1自己、2客户 .
	 *
	 */
	public Integer getOptFlag() {
		return optFlag;
	}

	/**
	 * 操作人标识：1自己、2客户 .
	 *
	 */
	public void setOptFlag(Integer optFlag) {
		this.optFlag = optFlag;
	}

	/**
	 * 客户编号 .
	 *
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * 客户编号 .
	 *
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * 客户名称 .
	 *
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * 客户名称 .
	 *
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * 评论人微信号 .
	 *
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 评论人微信号 .
	 *
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 评论昵称 .
	 *
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 评论昵称 .
	 *
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 回复人微信号 .
	 *
	 */
	public String getTousername() {
		return tousername;
	}

	/**
	 * 回复人微信号 .
	 *
	 */
	public void setTousername(String tousername) {
		this.tousername = tousername;
	}

	/**
	 * 回复人昵称 .
	 *
	 */
	public String getTonickname() {
		return tonickname;
	}

	/**
	 * 回复人昵称 .
	 *
	 */
	public void setTonickname(String tonickname) {
		this.tonickname = tonickname;
	}

	/**
	 * 消息状态：1 发送中、2发送成功、3发送失败 .
	 *
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 消息状态：1 发送中、2发送成功、3发送失败 .
	 *
	 */
	public void setStatus(Integer status) {
		this.status = status;
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

	/**
	 * 备注 .
	 *
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注 .
	 *
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 备注 .
	 *
	 */
	public String getRemark2() {
		return remark2;
	}

	/**
	 * 备注 .
	 *
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	/**
	 * 备注 .
	 *
	 */
	public String getRemark3() {
		return remark3;
	}

	/**
	 * 备注 .
	 *
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	/**
	 * 备注 .
	 *
	 */
	public String getRemark4() {
		return remark4;
	}

	/**
	 * 备注 .
	 *
	 */
	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	/**
	 * 内容 .
	 *
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 内容 .
	 *
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	/**
	 * @return the appReadFlag
	 */
	public String getAppReadFlag() {
		return appReadFlag;
	}

	/**
	 * @param appReadFlag the appReadFlag to set
	 */
	public void setAppReadFlag(String appReadFlag) {
		this.appReadFlag = appReadFlag;
	}

	/**
	 * @return the webReadFlag
	 */
	public String getWebReadFlag() {
		return webReadFlag;
	}

	/**
	 * @param webReadFlag the webReadFlag to set
	 */
	public void setWebReadFlag(String webReadFlag) {
		this.webReadFlag = webReadFlag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImCommentInfoDto [code=");
		builder.append(code);
		builder.append(", friendsCode=");
		builder.append(friendsCode);
		builder.append(", friendsId=");
		builder.append(friendsId);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append(", optFlag=");
		builder.append(optFlag);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", username=");
		builder.append(username);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", tousername=");
		builder.append(tousername);
		builder.append(", tonickname=");
		builder.append(tonickname);
		builder.append(", status=");
		builder.append(status);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append(", content=");
		builder.append(content);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNoGmName=");
		builder.append(memberNoGmName);
		builder.append(", commentTime=");
		builder.append(commentTime);
		builder.append(", commentSerId=");
		builder.append(commentSerId);
		builder.append(", appReadFlag=");
		builder.append(appReadFlag);
		builder.append(", webReadFlag=");
		builder.append(webReadFlag);
		builder.append("]");
		return builder.toString();
	}
}
