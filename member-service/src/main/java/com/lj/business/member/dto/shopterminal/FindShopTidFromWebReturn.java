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
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2017年12月1日
 */
public class FindShopTidFromWebReturn implements Serializable {

	/**
	 * 
	 */ 
	private static final long serialVersionUID = -1514890323355005182L;

	/**
	 * 终端CODE
	 */
	private String tidCode;
	
	/**
	 * 终端编号，资产编号 .
	 */
	private String terminalCode;


    /**
     * 终端所在省CODE .
     */
    private String provinceCode;

	/**
	 * 终端所在市CODE .
	 */
	private String cityCode;

	/**
	 * 绑定微信号 .
	 */
	private String noWx;

	/**
	 * 绑定微信昵称 .
	 */
	private String wxNickname;

	/**
	 * 绑定微信头像地址 .
	 */
	private String headAddress;

    /**
     * 绑定微信二维码地址 .
     */
    private String qcord;

    /**
     * 绑定微信性别:
             男：MALE
             女：FEMALE
             未知：UNKNOWN .
     */
    private String sex;
    
    /**
     * 微信账户余额(分) .
     */
    private Long wxBalance;

	/**
	 * 手机串号 .
	 */
	private String imei;

    /**
     * 是否在线：0离线、1在线 .
     */
    private Integer onlineFlag;
    private String alias;
    /**
     * 来源：下属微信
     */
    private String source;
    private String shopName;
    
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * @return the tidCode
	 */
	public String getTidCode() {
		return tidCode;
	}

	/**
	 * @param tidCode the tidCode to set
	 */
	public void setTidCode(String tidCode) {
		this.tidCode = tidCode;
	}

	/**
	 * @return the terminalCode
	 */
	public String getTerminalCode() {
		return terminalCode;
	}

	/**
	 * @param terminalCode the terminalCode to set
	 */
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}


	/**
	 * @return the provinceCode
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * @param provinceCode the provinceCode to set
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
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
	 * @return the wxNickname
	 */
	public String getWxNickname() {
		return wxNickname;
	}

	/**
	 * @param wxNickname the wxNickname to set
	 */
	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	/**
	 * @return the headAddress
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * @param headAddress the headAddress to set
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
	 * @return the qcord
	 */
	public String getQcord() {
		return qcord;
	}

	/**
	 * @param qcord the qcord to set
	 */
	public void setQcord(String qcord) {
		this.qcord = qcord;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the wxBalance
	 */
	public Long getWxBalance() {
		return wxBalance;
	}

	/**
	 * @param wxBalance the wxBalance to set
	 */
	public void setWxBalance(Long wxBalance) {
		this.wxBalance = wxBalance;
	}

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return the onlineFlag
	 */
	public Integer getOnlineFlag() {
		return onlineFlag;
	}

	/**
	 * @param onlineFlag the onlineFlag to set
	 */
	public void setOnlineFlag(Integer onlineFlag) {
		this.onlineFlag = onlineFlag;
	}

	@Override
	public String toString() {
		return "FindShopTidFromWebReturn [tidCode=" + tidCode + ", terminalCode=" + terminalCode + ", provinceCode="
				+ provinceCode + ", cityCode=" + cityCode + ", noWx=" + noWx + ", wxNickname=" + wxNickname
				+ ", headAddress=" + headAddress + ", qcord=" + qcord + ", sex=" + sex + ", wxBalance=" + wxBalance
				+ ", imei=" + imei + ", onlineFlag=" + onlineFlag + ", alias=" + alias + ", source=" + source
				+ ", shopName=" + shopName + "]";
	}

}
