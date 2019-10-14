package com.lj.business.member.domain;

import java.util.Date;

public class AddFriends implements java.io.Serializable{
	private static final long serialVersionUID = 2725323004842686686L;
    /**
     * CODE .
     */
    private String code;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 导购微信号 .
     */
    private String noWxGm;
    
    /**
     * 导购主动添加标识：1是、0否
     */
    private Integer gmAddFlag;

    /**
     * 客户微信二维码 .
     */
    private String wxQrCode;

    /**
     * 客户微信号 .
     */
    private String noWx;

    /**
     * 客户微信别名 .
     */
    private String alias;

    /**
     * 头像地址 .
     */
    private String headAddress;

    /**
     * 微信昵称 .
     */
    private String nickName;

    /**
     * 昵称备注 .
     */
    private String nickRemark;

    /**
     * 手机号 .
     */
    private String mobile;

    /**
     * 性别:             男：MALE             女：FEMALE             未知：UNKNOWN .
     */
    private String sex;

    /**
     * 经度 .
     */
    private String longitude;

    /**
     * 纬度 .
     */
    private String latitude;

    /**
     * 验证信息 .
     */
    private String validation;

    /**
     * 添加好友状态：Y通过、N等待验证、F失败、E过期 .
     */
    private String addStatus;
    
    /**
     * 微信OPENID
     */
    private String wxOpenId;
    
    /**
     * QQ号
     */
    private String noQQ;
    
    /**
     * 标签名称
     */
    private String labelName;
    
    /**
     * 客户来源
     */
    private String memberSrc;

    /**
     * 请求添加时间 .
     */
    private Date requestTime;

    /**
     * 客户通过时间 .
     */
    private Date acceptTime;

    /**
     * 创建客户时间 .
     */
    private Date memberTime;

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
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4微信同步、5手机号搜索、6微信号搜索、7QQ号搜索 WX_ADD_TYPE
     */
    private Integer wxAddType;
    /**
     * 终端名称
     */
    private String shopName;
    
    public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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
     * 客户编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 客户编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 客户名称 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 客户名称 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 导购编号 .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号 .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
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
     * 导购微信号 .
     *
     */
    public String getNoWxGm() {
        return noWxGm;
    }

    /**
     * 导购微信号 .
     *
     */
    public void setNoWxGm(String noWxGm) {
        this.noWxGm = noWxGm == null ? null : noWxGm.trim();
    }

    /**
     * 客户微信二维码 .
     *
     */
    public String getWxQrCode() {
        return wxQrCode;
    }

    /**
     * 客户微信二维码 .
     *
     */
    public void setWxQrCode(String wxQrCode) {
        this.wxQrCode = wxQrCode == null ? null : wxQrCode.trim();
    }

    /**
     * 客户微信号 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 客户微信号 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
    }

    /**
     * 客户微信别名 .
     *
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 客户微信别名 .
     *
     */
    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    /**
     * 头像地址 .
     *
     */
    public String getHeadAddress() {
        return headAddress;
    }

    /**
     * 头像地址 .
     *
     */
    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress == null ? null : headAddress.trim();
    }

    /**
     * 微信昵称 .
     *
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 微信昵称 .
     *
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 昵称备注 .
     *
     */
    public String getNickRemark() {
        return nickRemark;
    }

    /**
     * 昵称备注 .
     *
     */
    public void setNickRemark(String nickRemark) {
        this.nickRemark = nickRemark == null ? null : nickRemark.trim();
    }

    /**
     * 手机号 .
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号 .
     *
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 性别:             男：MALE             女：FEMALE             未知：UNKNOWN .
     *
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别:             男：MALE             女：FEMALE             未知：UNKNOWN .
     *
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 经度 .
     *
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 经度 .
     *
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * 纬度 .
     *
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 纬度 .
     *
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * 验证信息 .
     *
     */
    public String getValidation() {
        return validation;
    }

    /**
     * 验证信息 .
     *
     */
    public void setValidation(String validation) {
        this.validation = validation == null ? null : validation.trim();
    }

    /**
     * 添加好友状态：Y通过、N等待验证、F失败、E过期 .
     *
     */
    public String getAddStatus() {
        return addStatus;
    }

    /**
     * 添加好友状态：Y通过、N等待验证、F失败、E过期 .
     *
     */
    public void setAddStatus(String addStatus) {
        this.addStatus = addStatus == null ? null : addStatus.trim();
    }

    /**
	 * @return the wxOpenId
	 */
	public String getWxOpenId() {
		return wxOpenId;
	}

	/**
	 * @param wxOpenId the wxOpenId to set
	 */
	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	/**
	 * @return the noQQ
	 */
	public String getNoQQ() {
		return noQQ;
	}

	/**
	 * @param noQQ the noQQ to set
	 */
	public void setNoQQ(String noQQ) {
		this.noQQ = noQQ;
	}

	/**
	 * @return the labelName
	 */
	public String getLabelName() {
		return labelName;
	}

	/**
	 * @param labelName the labelName to set
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * @return the memberSrc
	 */
	public String getMemberSrc() {
		return memberSrc;
	}

	/**
	 * @param memberSrc the memberSrc to set
	 */
	public void setMemberSrc(String memberSrc) {
		this.memberSrc = memberSrc;
	}

	/**
     * 请求添加时间 .
     *
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * 请求添加时间 .
     *
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * 客户通过时间 .
     *
     */
    public Date getAcceptTime() {
        return acceptTime;
    }

    /**
     * 客户通过时间 .
     *
     */
    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     * 创建客户时间 .
     *
     */
    public Date getMemberTime() {
        return memberTime;
    }

    /**
     * 创建客户时间 .
     *
     */
    public void setMemberTime(Date memberTime) {
        this.memberTime = memberTime;
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
    
    /**
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子  5接口搜索手机号添加 WX_ADD_TYPE
     */
    public Integer getWxAddType() {
		return wxAddType;
	}
    
    /**
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子  5接口搜索手机号添加 WX_ADD_TYPE
     */
	public void setWxAddType(Integer wxAddType) {
		this.wxAddType = wxAddType;
	}
	
	/**
     * 导购主动添加标识：1是、0否
     */
	public Integer getGmAddFlag() {
		return gmAddFlag;
	}
	
	/**
     * 导购主动添加标识：1是、0否
     */
	public void setGmAddFlag(Integer gmAddFlag) {
		this.gmAddFlag = gmAddFlag;
	}

	@Override
	public String toString() {
		return "AddFriends [code=" + code + ", memberNo=" + memberNo + ", memberName=" + memberName + ", memberNoGm="
				+ memberNoGm + ", memberNameGm=" + memberNameGm + ", merchantNo=" + merchantNo + ", merchantName="
				+ merchantName + ", noWxGm=" + noWxGm + ", gmAddFlag=" + gmAddFlag + ", wxQrCode=" + wxQrCode
				+ ", noWx=" + noWx + ", alias=" + alias + ", headAddress=" + headAddress + ", nickName=" + nickName
				+ ", nickRemark=" + nickRemark + ", mobile=" + mobile + ", sex=" + sex + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", validation=" + validation + ", addStatus=" + addStatus + ", wxOpenId="
				+ wxOpenId + ", noQQ=" + noQQ + ", labelName=" + labelName + ", memberSrc=" + memberSrc
				+ ", requestTime=" + requestTime + ", acceptTime=" + acceptTime + ", memberTime=" + memberTime
				+ ", createId=" + createId + ", createDate=" + createDate + ", remark=" + remark + ", remark2="
				+ remark2 + ", remark3=" + remark3 + ", remark4=" + remark4 + ", wxAddType=" + wxAddType + ", shopName="
				+ shopName + "]";
	}

}