package com.lj.business.member.dto.addfriends;

import java.io.Serializable;
import java.util.Date;

public class UpdateAddFriends implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -953427567220879399L;

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
     * 昵称备注 .
     */
    private String nickRemark;
    
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别:
             男：male
             女：female
             未知：unknown .
     */
    private String sex;

    /**
     * 验证信息 .
     */
    private String validation;

    /**
     * 添加好友状态：Y通过、N等待验证、F失败、E过期 .
     */
    private String addStatus;

    /**
     * 客户通过时间 .
     */
    private Date acceptTime;

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
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子、5接口搜索手机号添加、6接口搜索微信号添加、7接口搜索QQ号添加
     */
    private Integer wxAddType;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
	 * @return the memberNameGm
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * @param memberNameGm the memberNameGm to set
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
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
		this.nickRemark = nickRemark;
	}

	/**
     * 手机号
     */
	public String getMobile() {
		return mobile;
	}

	/**
     * 手机号
     */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
     * 性别:
             男：male
             女：female
             未知：unknown .
     *
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别:
             男：male
             女：female
             未知：unknown .
     *
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
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
	 * @return the addStatus
	 */
	public String getAddStatus() {
		return addStatus;
	}

	/**
	 * @param addStatus the addStatus to set
	 */
	public void setAddStatus(String addStatus) {
		this.addStatus = addStatus;
	}

	/**
	 * @return the acceptTime
	 */
	public Date getAcceptTime() {
		return acceptTime;
	}

	/**
	 * @param acceptTime the acceptTime to set
	 */
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateAddFriends [code=");
		builder.append(code);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", wxQrCode=");
		builder.append(wxQrCode);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", nickRemark=");
		builder.append(nickRemark);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", validation=");
		builder.append(validation);
		builder.append(", addStatus=");
		builder.append(addStatus);
		builder.append(", acceptTime=");
		builder.append(acceptTime);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append("]");
		return builder.toString();
	}

    public Integer getWxAddType() {
        return wxAddType;
    }

    public void setWxAddType(Integer wxAddType) {
        this.wxAddType = wxAddType;
    }

}
