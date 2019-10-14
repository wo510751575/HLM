package com.lj.business.supcon.dto.friends;

import java.io.Serializable;

public class AddRedPackMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5957177644825349829L;



	/**
	 * 发红包记录CODE
	 */
	private String rpCode;



	/**
	 * 红包类型:1、普通红包（默认）
	 */
	private String type;

	/**
	 * 终端微信
	 */
	private String noWxShop;

	/**
	 * 客户微信
	 */
	private String noWx;

	/**
	 * 红包金额
	 */
	private Long amt;

	/**
	 * 红包描述
	 */
	private String des;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * msgId
	 * @return
	 */
	private String msgId;
	
	
	public String getMsgId() {
		return msgId;
	}


	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getRpCode() {
		return rpCode;
	}


	public void setRpCode(String rpCode) {
		this.rpCode = rpCode;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getNoWxShop() {
		return noWxShop;
	}


	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}


	public String getNoWx() {
		return noWx;
	}


	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}


	public Long getAmt() {
		return amt;
	}


	public void setAmt(Long amt) {
		this.amt = amt;
	}


	public String getDes() {
		return des;
	}


	public void setDes(String des) {
		this.des = des;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RedPackMessage [rpCode=");
		builder.append(rpCode);
		builder.append(", type=");
		builder.append(type);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", amt=");
		builder.append(amt);
		builder.append(", des=");
		builder.append(des);
		builder.append("]");
		return builder.toString();
	}
}
