package com.lj.business.member.dto.addfriends;

import java.io.Serializable;

public class AddAddFriends implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -5608231016172508967L;

    /**
     * 导购编号，非空 .
     */
    private String memberNoGm;
    
    /**
     * 客户名称
     */
    private String memberName;

	/**
	 * 导购微信号，非空
	 */
	private String noWxGm;
	
	/**
	 * 导购主动添加标识：1是、0否
	 */
	private Integer gmAddFlag;

    /**
     * 客户微信二维码，非空 .
     */
    private String wxQrCode;
	
	/**
	 * 客户微信，非空
	 */
	private String noWx;
	
	/**
	 * 客户微信别名
	 */
	private String alias;
	
	/**
	 * 微信昵称
	 */
	private String nickNameWx;
	
	/**
	 * 昵称备注
	 */
	private String nickRemark;
	
	/**
	 * 微信头像地址
	 */
	private String headAddress;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 手机号
	 */
	private String mobile; 
	
	/**
     * 验证信息 .
     */
    private String validation;
    
    /**
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子  5接口搜索手机号添加 WX_ADD_TYPE
     */
    private Integer wxAddType;
    /**
     * 微信OPENID
     */
    private String wxOpenId;
    /**
     * QQ号
     */
    private String noQq;
    /**
     * 标签名称,逗号分隔,最多4个
     */
    private String labelName;
    /**
     * 客户来源
     */
    private String memberSrc;
    /**
     * 客户来源
     */
    private String remark4;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the nickNameWx
	 */
	public String getNickNameWx() {
		return nickNameWx;
	}

	/**
	 * @param nickNameWx the nickNameWx to set
	 */
	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
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
	 * @return the noWxGm
	 */
	public String getNoWxGm() {
		return noWxGm;
	}

	/**
	 * @param noWxGm the noWxGm to set
	 */
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
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
	 * @return the nickRemark
	 */
	public String getNickRemark() {
		return nickRemark;
	}

	/**
	 * @param nickRemark the nickRemark to set
	 */
	public void setNickRemark(String nickRemark) {
		this.nickRemark = nickRemark;
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
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the validation
	 */
	public String getValidation() {
		return validation;
	}

	/**
	 * @param validation the validation to set
	 */
	public void setValidation(String validation) {
		this.validation = validation;
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

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getNoQq() {
		return noQq;
	}

	public void setNoQq(String noQq) {
		this.noQq = noQq;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getMemberSrc() {
		return memberSrc;
	}

	public void setMemberSrc(String memberSrc) {
		this.memberSrc = memberSrc;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddAddFriends [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", wxQrCode=");
		builder.append(wxQrCode);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", nickRemark=");
		builder.append(nickRemark);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", validation=");
		builder.append(validation);
		builder.append(", wxAddType=");
		builder.append(wxAddType);
		builder.append(", wxOpenId=");
		builder.append(wxOpenId);
		builder.append(", noQq=");
		builder.append(noQq);
		builder.append(", labelName=");
		builder.append(labelName);
		builder.append(", memberSrc=");
		builder.append(memberSrc);
		builder.append(", gmAddFlag=");
		builder.append(gmAddFlag);
		builder.append("]");
		return builder.toString();
	}

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

}
