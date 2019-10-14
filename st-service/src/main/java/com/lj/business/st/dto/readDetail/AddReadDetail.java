package com.lj.business.st.dto.readDetail;

import java.io.Serializable;

/**
 * The Class AddReadDetail.
 */
public class AddReadDetail implements Serializable { 

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5143328482927207852L;

    /**
     * 文章名称 .
     */
    private String name;

    /**
     * URL地址（必填） .
     */
    private String urlAddress;

    /**
     * 访客标识（必填） .
     */
    private String visitIdentify;

    /**
     * 访问人IP .
     */
    private String ipAddress;

    /**
     * MAC地址 .
     */
    private String mac;

    /**
     * 网络信息 .
     */
    private String netType;

    /**
     * PC
             ANDROID PAD
             ANDROID PHONE
             IPAD
             IPHONE
             PHONE
             PAD .
     */
    private String equipment;

    /**
     * 区域信息（经纬度） .
     */
    private String areaInfo;

    /**
     * 登录地区 .
     */
    private String loginArea;
    
    /**
     * 第一次访问
     *  是：Y
     *  否：N .
     */
    private String fristVisit;

	/**
	 * Gets the 文章名称 .
	 *
	 * @return the 文章名称 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the 文章名称 .
	 *
	 * @param name the new 文章名称 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the uRL地址 .
	 *
	 * @return the uRL地址 
	 */
	public String getUrlAddress() {
		return urlAddress;
	}

	/**
	 * Sets the uRL地址 .
	 *
	 * @param urlAddress the new uRL地址 
	 */
	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
	}

	/**
	 * Gets the 访客标识 .
	 *
	 * @return the 访客标识 
	 */
	public String getVisitIdentify() {
		return visitIdentify;
	}

	/**
	 * Sets the 访客标识 .
	 *
	 * @param visitIdentify the new 访客标识 
	 */
	public void setVisitIdentify(String visitIdentify) {
		this.visitIdentify = visitIdentify;
	}

	/**
	 * Gets the 访问人IP .
	 *
	 * @return the 访问人IP 
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * Sets the 访问人IP .
	 *
	 * @param ipAddress the new 访问人IP 
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Gets the mAC地址 .
	 *
	 * @return the mAC地址 
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * Sets the mAC地址 .
	 *
	 * @param mac the new mAC地址 
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * Gets the 网络信息 .
	 *
	 * @return the 网络信息 
	 */
	public String getNetType() {
		return netType;
	}

	/**
	 * Sets the 网络信息 .
	 *
	 * @param netType the new 网络信息 
	 */
	public void setNetType(String netType) {
		this.netType = netType;
	}

	/**
	 * Gets the pC ANDROID PAD ANDROID PHONE IPAD IPHONE PHONE PAD .
	 *
	 * @return the pC ANDROID PAD ANDROID PHONE IPAD IPHONE PHONE PAD 
	 */
	public String getEquipment() {
		return equipment;
	}

	/**
	 * Sets the pC ANDROID PAD ANDROID PHONE IPAD IPHONE PHONE PAD .
	 *
	 * @param equipment the new pC ANDROID PAD ANDROID PHONE IPAD IPHONE PHONE PAD 
	 */
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	/**
	 * Gets the 区域信息（经纬度） .
	 *
	 * @return the 区域信息（经纬度） 
	 */
	public String getAreaInfo() {
		return areaInfo;
	}

	/**
	 * Sets the 区域信息（经纬度） .
	 *
	 * @param areaInfo the new 区域信息（经纬度） 
	 */
	public void setAreaInfo(String areaInfo) {
		this.areaInfo = areaInfo;
	}

	/**
	 * Gets the 登录地区 .
	 *
	 * @return the 登录地区 
	 */
	public String getLoginArea() {
		return loginArea;
	}

	/**
	 * Sets the 登录地区 .
	 *
	 * @param loginArea the new 登录地区 
	 */
	public void setLoginArea(String loginArea) {
		this.loginArea = loginArea;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddReadDetail [name=").append(name)
				.append(", urlAddress=").append(urlAddress)
				.append(", visitIdentify=").append(visitIdentify)
				.append(", ipAddress=").append(ipAddress).append(", mac=")
				.append(mac).append(", netType=").append(netType)
				.append(", equipment=").append(equipment).append(", areaInfo=")
				.append(areaInfo).append(", loginArea=").append(loginArea)
				.append(", fristVisit=").append(fristVisit).append("]");
		return builder.toString();
	}

	public String getFristVisit() {
		return fristVisit;
	}

	public void setFristVisit(String fristVisit) {
		this.fristVisit = fristVisit;
	}

}
