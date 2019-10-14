/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.shopterminal;

import java.io.Serializable;

/**
 * 
 * 类说明：更新签到令牌
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月26日
 */
public class UpdateWorkKey implements Serializable {

	private static final long serialVersionUID = -4407660712163105205L;

	/**
	 * 手机串号
	 */
	private String code;
	
	/**
	 * 工作密钥
	 */
	private String workKey;
	/**
     * 绑定微信昵称 .
     */
    private String wxNickname;
    /**
     * 中控微信相关信息
     */
    private String alias;
    private String headurl;
    private String nickname;
    /**
     * 绑定微信头像地址 .
     */
    private String headAddress;
    /**
     * 绑定微信二维码地址 .
     */
    private String qcord;
    
	public String getQcord() {
		return qcord;
	}

	public void setQcord(String qcord) {
		this.qcord = qcord;
	}

	public String getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	public String getWxNickname() {
		return wxNickname;
	}

	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the workKey
	 */
	public String getWorkKey() {
		return workKey;
	}

	/**
	 * @param workKey the workKey to set
	 */
	public void setWorkKey(String workKey) {
		this.workKey = workKey;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateWorkKey [code=");
		builder.append(code);
		builder.append(", workKey=");
		builder.append(workKey);
		builder.append(", wxNickname=");
		builder.append(wxNickname);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", headurl=");
		builder.append(headurl);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append("]");
		return builder.toString();
	}

}
