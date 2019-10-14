package com.lj.business.weixin.domain;

import java.util.Date;

public class ImLikeInfo {
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
	 * 微信号 .
	 */
	private String username;

	/**
	 * 昵称 .
	 */
	private String nickname;

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

	private String memberNoGm;

	private String memberNoGmName;
	
	
	  /**
     * app已读标识
     */
    private String appReadFlag;

    /**
     * web已读标识
     */
    private String webReadFlag;

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
	 * 微信号 .
	 *
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 微信号 .
	 *
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 昵称 .
	 *
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 昵称 .
	 *
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	
	

	public String getAppReadFlag() {
		return appReadFlag;
	}

	public void setAppReadFlag(String appReadFlag) {
		this.appReadFlag = appReadFlag;
	}

	public String getWebReadFlag() {
		return webReadFlag;
	}

	public void setWebReadFlag(String webReadFlag) {
		this.webReadFlag = webReadFlag;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImLikeInfo [code=");
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
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNoGmName=");
		builder.append(memberNoGmName);
		builder.append(", appReadFlag=");
		builder.append(appReadFlag);
		builder.append(", webReadFlag=");
		builder.append(webReadFlag);
		builder.append("]");
		return builder.toString();
	}
}