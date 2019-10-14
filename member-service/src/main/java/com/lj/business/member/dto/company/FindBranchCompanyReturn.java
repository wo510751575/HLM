package com.lj.business.member.dto.company;

import java.io.Serializable;
import java.util.Date;

public class FindBranchCompanyReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -5570109702167966761L;

    /**
     * CODE .
     */
    private String code;

    /**
     * 分公司编号 .
     */
    private String companyNo;
    
    /**
     * 经销商代码
     */
    private String dealerCode;

    /**
     * 分公司名称 .
     */
    private String companyName;

    /**
     * 状态：NORMAL正常、CANCEL注销
              .
     */
    private String status;

    /**
     * 公司类型：1分公司、2经销商 .
     */
    private Integer companyType;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 注册时间 .
     */
    private Date registerTime;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新时间 .
     */
    private Date updateDate;

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
     * 省编号 .
     */
    private String provinceNo;
    
    /**
     * 省名称 .
     */
    private String provinceName;
    
    /**
     * 市编号 .
     */
    private String cityNo;
    
    /**
     * 市名称 .
     */
    private String cityName;
    
    /**
     * 业务对接人 .
     */
    private String businessPerson;
    
    /**
     * 营业执照.
     */
    private String businessLicense;
    
    /**
     * 法人姓名 .
     */
    private String legalPersonName;
    
    /**
     * 法人身份证号 .
     */
    private String legalPersonId;
    
    /**
     * 经销商负责人姓名 .
     */
    private String dealerResponsiblePerson;
    
    /**
     * 经销商负责人手机号 .
     */
    private String dealerResponsibleMobile;

    public String getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public String getLegalPersonId() {
		return legalPersonId;
	}

	public void setLegalPersonId(String legalPersonId) {
		this.legalPersonId = legalPersonId;
	}

	public String getDealerResponsiblePerson() {
		return dealerResponsiblePerson;
	}

	public void setDealerResponsiblePerson(String dealerResponsiblePerson) {
		this.dealerResponsiblePerson = dealerResponsiblePerson;
	}

	public String getDealerResponsibleMobile() {
		return dealerResponsibleMobile;
	}

	public void setDealerResponsibleMobile(String dealerResponsibleMobile) {
		this.dealerResponsibleMobile = dealerResponsibleMobile;
	}

	public String getBusinessPerson() {
		return businessPerson;
	}

	public void setBusinessPerson(String businessPerson) {
		this.businessPerson = businessPerson;
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
     * 分公司编号 .
     *
     */
    public String getCompanyNo() {
        return companyNo;
    }

    /**
     * 分公司编号 .
     *
     */
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    /**
     * 分公司名称 .
     *
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 分公司名称 .
     *
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 状态：NORMAL正常、CANCEL注销
              .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态：NORMAL正常、CANCEL注销
              .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 公司类型：1分公司、2经销商 .
     *
     */
    public Integer getCompanyType() {
        return companyType;
    }

    /**
     * 公司类型：1分公司、2经销商 .
     *
     */
    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
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
     * 注册时间 .
     *
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 注册时间 .
     *
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
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
     * 更新时间 .
     *
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间 .
     *
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

	public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindBranchCompanyReturn [code=");
		builder.append(code);
		builder.append(", companyNo=");
		builder.append(companyNo);
		builder.append(", dealerCode=");
		builder.append(dealerCode);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", status=");
		builder.append(status);
		builder.append(", companyType=");
		builder.append(companyType);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", registerTime=");
		builder.append(registerTime);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append(", provinceNo=");
		builder.append(provinceNo);
		builder.append(", provinceName=");
		builder.append(provinceName);
		builder.append(", cityNo=");
		builder.append(cityNo);
		builder.append(", cityName=");
		builder.append(cityName);
		builder.append(", businessPerson=");
		builder.append(businessPerson);
		builder.append(", businessLicense=");
		builder.append(businessLicense);
		builder.append(", legalPersonName=");
		builder.append(legalPersonName);
		builder.append(", legalPersonId=");
		builder.append(legalPersonId);
		builder.append(", dealerResponsiblePerson=");
		builder.append(dealerResponsiblePerson);
		builder.append(", dealerResponsibleMobile=");
		builder.append(dealerResponsibleMobile);
		builder.append("]");
		return builder.toString();
	}

}
