/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.contacts;

import com.lj.business.supcon.netty.message.BaseRequest;

/**
 * 
 * 类说明：查询微信基本信息
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月26日
 */
public class FindWxInfoRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5518252441412702448L;

	/**
	 * 导购编号，非空
	 */
	private String memberNoGm;
	/**
	 * 导购微信号
	 */
	private String noWxGm;
	
	/**
	 * 客户微信二维码内容，非空
	 */
	private String wxQrCode;
	/**
	 * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子、5接口搜索手机号添加、6接口搜索微信号添加、7接口搜索QQ号添加
	 */
	private int wxAddType = 1;

	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	/**
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * @return the wxQrCode
	 */
	public String getWxQrCode() {
		return wxQrCode;
	}

	/**
	 * @param wxQrCode the wxQrCode to set
	 */
	public void setWxQrCode(String wxQrCode) {
		this.wxQrCode = wxQrCode;
	}
	/**
	 * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子、5接口搜索手机号添加、6接口搜索微信号添加、7接口搜索QQ号添加
	 */
	public int getWxAddType() {
		return wxAddType;
	}
	/**
	 * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子、5接口搜索手机号添加、6接口搜索微信号添加、7接口搜索QQ号添加
	 */
	public void setWxAddType(int wxAddType) {
		this.wxAddType = wxAddType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWxInfoRequest [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", wxQrCode=");
		builder.append(wxQrCode);
		builder.append(", wxAddType=");
		builder.append(wxAddType);
		builder.append("]");
		return builder.toString();
	}

}
