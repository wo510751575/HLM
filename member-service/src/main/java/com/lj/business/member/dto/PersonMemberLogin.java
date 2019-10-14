package com.lj.business.member.dto;

import java.io.Serializable;


/**
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author LeoPeng
 * 
 * 
 * CreateDate: 2017-6-4
 */
public class PersonMemberLogin implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2167078588183922561L;
	
	 /**
     * 手机号（必填） .
     */
    private String mobile;
    

    /**
     * 登录密码（必填） .
     */
    private String pwd;
    
    /**
     * 微信公众号OPENID .
     */
    private String openIdGzhWx;
    
    /**
     *  .
     */
    private String ipAddress;
    
    /**
     * 微信号（必填）
     */
    private String noWx;

	/**
     *  mac 地址
     */
    private String mac;

    /**
     *  .
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
     *  .
     */
    private String loginArea;
    
   /**
    * 登录上传中控相关微信信息
    * 昵称，头像，别名
    */
    private String zkInfo;
    
    /**
     * 取消手机号后采用后台账号统一登录
     */
    private String loginName;
    
    //中控扫描二维码添加
    private String alias;
    private String nickname;
    private String merchantNo;
    private String headurl;
    private String shopNo;
    private String wxId;//微信ID 
  //app版本号
    private String version;
  //中控版本号
    private String versionCode;
    /**
     * 是否热文版:默认 false;
     */
    private boolean hasRwVersion;
    
    public String getWxId() {
		return wxId;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


    //是否检测导购助手，true则代表必须有导购助手，否则不检测
    private boolean checkGmAssistant=true;
    
    
    
    public boolean isCheckGmAssistant() {
		return checkGmAssistant;
	}

	public void setCheckGmAssistant(boolean checkGmAssistant) {
		this.checkGmAssistant = checkGmAssistant;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
    
    public String getversion() {
		return version;
	}

	public void setversion(String version) {
		this.version = version;
	}
	
	
	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getZkInfo() {
		return zkInfo;
	}
	
	public void setZkInfo(String zkInfo) {
		this.zkInfo = zkInfo;
	}

	/**
	 * Gets the ip address.
	 *
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * Sets the ip address.
	 *
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Gets the mac.
	 *
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * Sets the mac.
	 *
	 * @param mac the mac to set
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * Gets the net type.
	 *
	 * @return the netType
	 */
	public String getNetType() {
		return netType;
	}

	/**
	 * Sets the net type.
	 *
	 * @param netType the netType to set
	 */
	public void setNetType(String netType) {
		this.netType = netType;
	}

	/**
	 * Gets the pC ANDROID PAD ANDROID PHONE IPAD IPHONE PHONE PAD .
	 *
	 * @return the equipment
	 */
	public String getEquipment() {
		return equipment;
	}

	/**
	 * Sets the pC ANDROID PAD ANDROID PHONE IPAD IPHONE PHONE PAD .
	 *
	 * @param equipment the equipment to set
	 */
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	/**
	 * Gets the 区域信息（经纬度） .
	 *
	 * @return the areaInfo
	 */
	public String getAreaInfo() {
		return areaInfo;
	}

	/**
	 * Sets the 区域信息（经纬度） .
	 *
	 * @param areaInfo the areaInfo to set
	 */
	public void setAreaInfo(String areaInfo) {
		this.areaInfo = areaInfo;
	}

	/**
	 * Gets the login area.
	 *
	 * @return the loginArea
	 */
	public String getLoginArea() {
		return loginArea;
	}

	/**
	 * Sets the login area.
	 *
	 * @param loginArea the loginArea to set
	 */
	public void setLoginArea(String loginArea) {
		this.loginArea = loginArea;
	}

	/**
	 * Gets the 手机号 .
	 *
	 * @return the 手机号 
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the 手机号 .
	 *
	 * @param mobile the new 手机号 
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Gets the 登录密码 .
	 *
	 * @return the 登录密码 
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * Sets the 登录密码 .
	 *
	 * @param pwd the new 登录密码 
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

	/**
	 * Gets the 微信公众号OPENID .
	 *
	 * @return the 微信公众号OPENID 
	 */
	public String getOpenIdGzhWx() {
		return openIdGzhWx;
	}

	/**
	 * Sets the 微信公众号OPENID .
	 *
	 * @param openIdGzhWx the new 微信公众号OPENID 
	 */
	public void setOpenIdGzhWx(String openIdGzhWx) {
		this.openIdGzhWx = openIdGzhWx;
	}

    public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	@Override
	public String toString() {
		return "PersonMemberLogin [mobile=" + mobile + ", pwd=" + pwd + ", openIdGzhWx=" + openIdGzhWx + ", ipAddress="
				+ ipAddress + ", noWx=" + noWx + ", mac=" + mac + ", netType=" + netType + ", equipment=" + equipment
				+ ", areaInfo=" + areaInfo + ", loginArea=" + loginArea + ", zkInfo=" + zkInfo + ", loginName="
				+ loginName + ", alias=" + alias + ", nickname=" + nickname + ", merchantNo=" + merchantNo
				+ ", headurl=" + headurl + ", shopNo=" + shopNo + ", wxId=" + wxId + ", version=" + version
				+ ", versionCode=" + versionCode + "]";
	}

	public boolean isHasRwVersion() {
		return hasRwVersion;
	}

	public void setHasRwVersion(boolean hasRwVersion) {
		this.hasRwVersion = hasRwVersion;
	}

	
}
