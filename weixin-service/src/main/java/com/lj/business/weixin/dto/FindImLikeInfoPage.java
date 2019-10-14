package com.lj.business.weixin.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindImLikeInfoPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6773366257745626241L; 

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
     * 分店编号 .
     */
    
    
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
     * 微信号 .
     */
    private String username;
    
    /**
     * 消息状态：1 发送中、2发送成功、3发送失败 .
     */
    private Integer status;
    
    private String memberNoGm;
    
    

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
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

	public String getFriendsId() {
		return friendsId;
	}

	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
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

	public Integer getOptFlag() {
		return optFlag;
	}

	public void setOptFlag(Integer optFlag) {
		this.optFlag = optFlag;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindImLikeInfoPage [code=");
		builder.append(code);
		builder.append(", friendsCode=");
		builder.append(friendsCode);
		builder.append(", friendsId=");
		builder.append(friendsId);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append(", optFlag=");
		builder.append(optFlag);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", username=");
		builder.append(username);
		builder.append(", status=");
		builder.append(status);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append("]");
		return builder.toString();
	}
}
