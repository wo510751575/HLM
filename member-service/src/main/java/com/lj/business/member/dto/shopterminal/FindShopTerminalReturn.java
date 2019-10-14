package com.lj.business.member.dto.shopterminal;

import java.io.Serializable;
import java.util.Date;

public class FindShopTerminalReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 612006974157416952L;

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
     * 终端名称
     */
    private String shopName;
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
     * 绑定微信性别 .
     */
    private String sex;

    /**
     * 绑定微信版本号 .
     */
    private Integer wxVersionCode;

    /**
     * 绑定微信版本名称 .
     */
    private String wxVersionName;

    /**
     * 微信账户余额(分) .
     */
    private Long wxBalance;

    /**
     * 微信支付密码 .
     */
    private String wxPwd;

    /**
     * 签到时间 .
     */
    private Date signTime;

    /**
     * 工作密钥 .
     */
    private String workKey;

    /**
     * 手机串号 .
     */
    private String imei;

    /**
     * 状态：0未激活、1正常、2注销 .
     */
    private Integer status;

    /**
     * 客户端版本名称 .
     */
    private String versionName;

    /**
     * 客户端版本号 .
     */
    private Integer versionCode;
    
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
     * 终端编码 .
     *
     */
    public String getTerminalCode() {
        return terminalCode;
    }

    /**
     * 终端编码 .
     *
     */
    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode == null ? null : terminalCode.trim();
    }

    /**
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 商户名称 .
     *
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称 .
     *
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * 绑定微信号 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 绑定微信号 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
    }

    /**
     * 绑定微信名(微信username) .
     *
     */
    public String getUsernameWx() {
        return usernameWx;
    }

    /**
     * 绑定微信名(微信username) .
     *
     */
    public void setUsernameWx(String usernameWx) {
        this.usernameWx = usernameWx == null ? null : usernameWx.trim();
    }

    /**
     * 绑定微信昵称 .
     *
     */
    public String getWxNickname() {
        return wxNickname;
    }

    /**
     * 绑定微信昵称 .
     *
     */
    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname == null ? null : wxNickname.trim();
    }

    /**
     * 绑定微信头像地址 .
     *
     */
    public String getHeadAddress() {
        return headAddress;
    }

    /**
     * 绑定微信头像地址 .
     *
     */
    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress == null ? null : headAddress.trim();
    }

    /**
     * 绑定微信二维码地址 .
     *
     */
    public String getQcord() {
        return qcord;
    }

    /**
     * 绑定微信二维码地址 .
     *
     */
    public void setQcord(String qcord) {
        this.qcord = qcord == null ? null : qcord.trim();
    }

    /**
     * 绑定微信性别 .
     *
     */
    public String getSex() {
        return sex;
    }

    /**
     * 绑定微信性别 .
     *
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 绑定微信版本号 .
     *
     */
    public Integer getWxVersionCode() {
        return wxVersionCode;
    }

    /**
     * 绑定微信版本号 .
     *
     */
    public void setWxVersionCode(Integer wxVersionCode) {
        this.wxVersionCode = wxVersionCode;
    }

    /**
     * 绑定微信版本名称 .
     *
     */
    public String getWxVersionName() {
        return wxVersionName;
    }

    /**
     * 绑定微信版本名称 .
     *
     */
    public void setWxVersionName(String wxVersionName) {
        this.wxVersionName = wxVersionName == null ? null : wxVersionName.trim();
    }

    /**
     * 微信账户余额(分) .
     *
     */
    public Long getWxBalance() {
        return wxBalance;
    }

    /**
     * 微信账户余额(分) .
     *
     */
    public void setWxBalance(Long wxBalance) {
        this.wxBalance = wxBalance;
    }

    /**
     * 微信支付密码 .
     *
     */
    public String getWxPwd() {
        return wxPwd;
    }

    /**
     * 微信支付密码 .
     *
     */
    public void setWxPwd(String wxPwd) {
        this.wxPwd = wxPwd == null ? null : wxPwd.trim();
    }

    /**
     * 签到时间 .
     *
     */
    public Date getSignTime() {
        return signTime;
    }

    /**
     * 签到时间 .
     *
     */
    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    /**
     * 工作密钥 .
     *
     */
    public String getWorkKey() {
        return workKey;
    }

    /**
     * 工作密钥 .
     *
     */
    public void setWorkKey(String workKey) {
        this.workKey = workKey == null ? null : workKey.trim();
    }

    /**
     * 手机串号 .
     *
     */
    public String getImei() {
        return imei;
    }

    /**
     * 手机串号 .
     *
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    /**
     * 状态：0未激活、1正常、2注销 .
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：0未激活、1正常、2注销 .
     *
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 客户端版本名称 .
     *
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * 客户端版本名称 .
     *
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    /**
     * 客户端版本号 .
     *
     */
    public Integer getVersionCode() {
        return versionCode;
    }

    /**
     * 客户端版本号 .
     *
     */
    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
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

    /**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
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
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Override
	public String toString() {
		return "FindShopTerminalReturn [code=" + code + ", terminalCode=" + terminalCode + ", merchantNo=" + merchantNo
				+ ", merchantName=" + merchantName + ", shopName=" + shopName + ", noWx=" + noWx + ", usernameWx="
				+ usernameWx + ", wxNickname=" + wxNickname + ", headAddress=" + headAddress + ", qcord=" + qcord
				+ ", sex=" + sex + ", wxVersionCode=" + wxVersionCode + ", wxVersionName=" + wxVersionName
				+ ", wxBalance=" + wxBalance + ", wxPwd=" + wxPwd + ", signTime=" + signTime + ", workKey=" + workKey
				+ ", imei=" + imei + ", status=" + status + ", versionName=" + versionName + ", versionCode="
				+ versionCode + ", uploadFriendsFlag=" + uploadFriendsFlag + ", createId=" + createId + ", createDate="
				+ createDate + ", remark=" + remark + ", remark2=" + remark2 + ", remark3=" + remark3 + ", remark4="
				+ remark4 + ", alias=" + alias + ", headurl=" + headurl + ", nickname=" + nickname + "]";
	}
}
