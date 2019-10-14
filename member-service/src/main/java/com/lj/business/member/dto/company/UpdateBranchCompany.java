package com.lj.business.member.dto.company;

import java.io.Serializable;

import com.lj.business.member.emus.CompanyType;

public class UpdateBranchCompany implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -2080029629942972563L;
    /**
     * CODE，code和companyNo不能同时为空.
     */
    private String code;

    /**
     * 分公司编号，code和companyNo不能同时为空 .
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
     */
    private String status;

    /**
     * 公司类型：1分公司、2经销商 .
     */
    private CompanyType companyType;

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
    private String provinceCode;
    
    /**
     * 省名称 .
     */
    private String provinceName;
    
    /**
     * 市编号 .
     */
    private String cityCode;
    
    /**
     * 市名称 .
     */
    private String cityName;
    
    /**
     * 经销商负责人姓名 .
     */
    private String dealerResponsiblePerson;
    
    /**
     * 经销商负责人手机号 .
     */
    private String dealerResponsibleMobile;
    
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
     * 法人身份证正面.
     */
    private String legalPersonCardFront;

    /**
     * 法人身份证反面 .
     */
    private String legalPersonCardReverse;
    
    /**
     * 业务对接人 .
     */
    private String businessPerson;
    
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

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
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
    public CompanyType getCompanyType() {
        return companyType;
    }

    /**
     * 公司类型：1分公司、2经销商 .
     *
     */
    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
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

    public String getLegalPersonCardFront() {
        return legalPersonCardFront;
    }

    public void setLegalPersonCardFront(String legalPersonCardFront) {
        this.legalPersonCardFront = legalPersonCardFront;
    }

    public String getLegalPersonCardReverse() {
        return legalPersonCardReverse;
    }

    public void setLegalPersonCardReverse(String legalPersonCardReverse) {
        this.legalPersonCardReverse = legalPersonCardReverse;
    }

    public String getBusinessPerson() {
        return businessPerson;
    }

    public void setBusinessPerson(String businessPerson) {
        this.businessPerson = businessPerson;
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
		builder.append("UpdateBranchCompany [code=");
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
		builder.append(", joinAreaCode=");
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append(", provinceCode=");
		builder.append(provinceCode);
		builder.append(", provinceName=");
		builder.append(provinceName);
		builder.append(", cityCode=");
		builder.append(cityCode);
		builder.append(", cityName=");
		builder.append(cityName);
		builder.append(", dealerResponsiblePerson=");
		builder.append(dealerResponsiblePerson);
		builder.append(", dealerResponsibleMobile=");
		builder.append(dealerResponsibleMobile);
		builder.append(", businessLicense=");
		builder.append(businessLicense);
		builder.append(", legalPersonName=");
		builder.append(legalPersonName);
		builder.append(", legalPersonId=");
		builder.append(legalPersonId);
		builder.append(", legalPersonCardFront=");
		builder.append(legalPersonCardFront);
		builder.append(", legalPersonCardReverse=");
		builder.append(legalPersonCardReverse);
		builder.append(", businessPerson=");
		builder.append(businessPerson);
		builder.append("]");
		return builder.toString();
	}

}
