package com.lj.business.member.dto.shopterminal;

import java.io.Serializable;
import java.util.Date;

public class FindShopTerminalPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7855434984112845766L; 

	/**
     * CODE .
     */
    private String code;

    /**
     * 终端编码 .
     */
    private String terminalCode;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 手机名称 .
     */
    private String mobileName;

    /**
     * 绑定微信号 .
     */
    private String noWx;

    /**
     * 绑定微信名(微信username) .
     */
    private String usernameWx;

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
     * 手机串号 .
     */
    private String imei;

    /**
     * 状态：0未激活、1正常、2注销 .
     */
    private Integer status;
    
    /**
     * 客户端版本名称
     */
    private String versionName;
    
    /**
     * 客户端版本号
     */
    private Integer versionCode;

    /**
     * 微信版本名称
     */
    private String wxVersionName;
    
    /**
     * 微信版本号
     */
    private Integer wxVersionCode;
    
    /**
     * 微信支付密码
     */
    private String wxPwd;
    
    /**
     * 是否上传朋友圈信息：0不上传、1上传
     */
    private Integer uploadFriendsFlag;

    /**
     * 创建人 .
     */
    private String createId;

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
    /**
     * 中控微信相关信息
     */
    private String alias;
    private String headurl;
    private String nickname;
    private String shopName;
    
    public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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
	public String getWxVersionName() {
		return wxVersionName;
	}

	public void setWxVersionName(String wxVersionName) {
		this.wxVersionName = wxVersionName;
	}

	public Integer getWxVersionCode() {
		return wxVersionCode;
	}

	public void setWxVersionCode(Integer wxVersionCode) {
		this.wxVersionCode = wxVersionCode;
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
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * @param merchantName the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


	/**
	 * @return the mobileName
	 */
	public String getMobileName() {
		return mobileName;
	}

	/**
	 * @param mobileName the mobileName to set
	 */
	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
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
	 * @return the usernameWx
	 */
	public String getUsernameWx() {
		return usernameWx;
	}

	/**
	 * @param usernameWx the usernameWx to set
	 */
	public void setUsernameWx(String usernameWx) {
		this.usernameWx = usernameWx;
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
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the versionName
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * @param versionName the versionName to set
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * @return the versionCode
	 */
	public Integer getVersionCode() {
		return versionCode;
	}

	/**
	 * @param versionCode the versionCode to set
	 */
	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	/**
	 * @return the createId
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * @param createId the createId to set
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the remark2
	 */
	public String getRemark2() {
		return remark2;
	}

	/**
	 * @param remark2 the remark2 to set
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	/**
	 * @return the remark3
	 */
	public String getRemark3() {
		return remark3;
	}

	/**
	 * @param remark3 the remark3 to set
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	/**
	 * @return the remark4
	 */
	public String getRemark4() {
		return remark4;
	}

	/**
	 * @param remark4 the remark4 to set
	 */
	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getWxPwd() {
		return wxPwd;
	}

	public void setWxPwd(String wxPwd) {
		this.wxPwd = wxPwd;
	}

    /**
     * 是否上传朋友圈信息：0不上传、1上传
     */
	public Integer getUploadFriendsFlag() {
		return uploadFriendsFlag;
	}

	/**
     * 是否上传朋友圈信息：0不上传、1上传
     */
	public void setUploadFriendsFlag(Integer uploadFriendsFlag) {
		this.uploadFriendsFlag = uploadFriendsFlag;
	}

	@Override
	public String toString() {
		return "FindShopTerminalPageReturn [code=" + code + ", terminalCode=" + terminalCode + ", merchantNo="
				+ merchantNo + ", merchantName=" + merchantName + ", mobileName=" + mobileName + ", noWx=" + noWx
				+ ", usernameWx=" + usernameWx + ", wxNickname=" + wxNickname + ", headAddress=" + headAddress
				+ ", qcord=" + qcord + ", sex=" + sex + ", imei=" + imei + ", status=" + status + ", versionName="
				+ versionName + ", versionCode=" + versionCode + ", wxVersionName=" + wxVersionName + ", wxVersionCode="
				+ wxVersionCode + ", wxPwd=" + wxPwd + ", uploadFriendsFlag=" + uploadFriendsFlag + ", createId="
				+ createId + ", createDate=" + createDate + ", remark=" + remark + ", remark2=" + remark2 + ", remark3="
				+ remark3 + ", remark4=" + remark4 + ", alias=" + alias + ", headurl=" + headurl + ", nickname="
				+ nickname + ", shopName=" + shopName + "]";
	}

}
