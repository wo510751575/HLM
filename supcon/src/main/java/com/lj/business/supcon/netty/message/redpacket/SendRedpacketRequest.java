/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.redpacket;

import com.lj.business.supcon.netty.message.BaseDto;

/**
 * 
 * 类说明：发红包
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月25日
 */
public class SendRedpacketRequest extends BaseDto {

	private static final long serialVersionUID = -2431677849173857681L;

	/**
	 * 发红包记录CODE
	 */
	private String rpCode;
	
	/**
	 * 红包类型:1、普通红包（默认）2转账
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
	 * 客户昵称
	 */
	private String nickName;
	


	/**
	 * 红包金额
	 */
	private Long amt;
	
	/**
	 * 红包描述
	 */
	private String des;
	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the rpCode
	 */
	public String getRpCode() {
		return rpCode;
	}

	/**
	 * @param rpCode the rpCode to set
	 */
	public void setRpCode(String rpCode) {
		this.rpCode = rpCode;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the noWxShop
	 */
	public String getNoWxShop() {
		return noWxShop;
	}

	/**
	 * @param noWxShop the noWxShop to set
	 */
	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
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
	 * @return the amt
	 */
	public Long getAmt() {
		return amt;
	}

	/**
	 * @param amt the amt to set
	 */
	public void setAmt(Long amt) {
		this.amt = amt;
	}

	/**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}

	/**
	 * @param des the des to set
	 */
	public void setDes(String des) {
		this.des = des;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SendRedpacketRequest [rpCode=");
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
