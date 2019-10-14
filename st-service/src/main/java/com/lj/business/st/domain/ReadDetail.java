package com.lj.business.st.domain;

import java.util.Date;

public class ReadDetail {
    /**
     * CODE .
     */
    private String code;

    /**
     * 文章名称 .
     */
    private String name;

    /**
     * URL地址 .
     */
    private String urlAddress;

    /**
     * 访客标识 .
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
     * PC             ANDROID PAD             ANDROID PHONE             IPAD             IPHONE             PHONE             PAD .
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
     * 创建时间 .
     */
    private Date createDate;

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
        this.code = code == null ? null : code.trim();
    }

    /**
     * 文章名称 .
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 文章名称 .
     *
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * URL地址 .
     *
     */
    public String getUrlAddress() {
        return urlAddress;
    }

    /**
     * URL地址 .
     *
     */
    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress == null ? null : urlAddress.trim();
    }

    /**
     * 访客标识 .
     *
     */
    public String getVisitIdentify() {
        return visitIdentify;
    }

    /**
     * 访客标识 .
     *
     */
    public void setVisitIdentify(String visitIdentify) {
        this.visitIdentify = visitIdentify == null ? null : visitIdentify.trim();
    }

    /**
     * 访问人IP .
     *
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 访问人IP .
     *
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    /**
     * MAC地址 .
     *
     */
    public String getMac() {
        return mac;
    }

    /**
     * MAC地址 .
     *
     */
    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    /**
     * 网络信息 .
     *
     */
    public String getNetType() {
        return netType;
    }

    /**
     * 网络信息 .
     *
     */
    public void setNetType(String netType) {
        this.netType = netType == null ? null : netType.trim();
    }

    /**
     * PC             ANDROID PAD             ANDROID PHONE             IPAD             IPHONE             PHONE             PAD .
     *
     */
    public String getEquipment() {
        return equipment;
    }

    /**
     * PC             ANDROID PAD             ANDROID PHONE             IPAD             IPHONE             PHONE             PAD .
     *
     */
    public void setEquipment(String equipment) {
        this.equipment = equipment == null ? null : equipment.trim();
    }

    /**
     * 区域信息（经纬度） .
     *
     */
    public String getAreaInfo() {
        return areaInfo;
    }

    /**
     * 区域信息（经纬度） .
     *
     */
    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo == null ? null : areaInfo.trim();
    }

    /**
     * 登录地区 .
     *
     */
    public String getLoginArea() {
        return loginArea;
    }

    /**
     * 登录地区 .
     *
     */
    public void setLoginArea(String loginArea) {
        this.loginArea = loginArea == null ? null : loginArea.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReadDetail [code=").append(code);
        builder.append(",name=").append(name); 
        builder.append(",urlAddress=").append(urlAddress); 
        builder.append(",visitIdentify=").append(visitIdentify); 
        builder.append(",ipAddress=").append(ipAddress); 
        builder.append(",mac=").append(mac); 
        builder.append(",netType=").append(netType); 
        builder.append(",equipment=").append(equipment); 
        builder.append(",areaInfo=").append(areaInfo); 
        builder.append(",loginArea=").append(loginArea); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}