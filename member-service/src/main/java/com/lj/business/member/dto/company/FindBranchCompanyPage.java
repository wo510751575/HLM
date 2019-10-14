package com.lj.business.member.dto.company;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindBranchCompanyPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2378430794863529660L; 
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
     * 业务对接人 .
     */
    private String businessPerson;

    /**
     * 注册时间 - 从.
     */
    private Date registerTimeBegin;
    
    /**
     * 注册时间 - 到 .
     */
    private Date registerTimeEnd;
    
    /**
	 * 地区id
	 */
	private String areaId;
	/**
	 * 地区名
	 */
	private String areaName;
	/**
	 * 所在省
	 */
	private String province;
	/**
	 * 所在市
	 */
	private String city;

    public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
     * 注册时间 - 从 .
     *
     */
    public Date getRegisterTimeBegin() {
        return registerTimeBegin;
    }

    /**
     * 注册时间 - 从 .
     *
     */
    public void setRegisterTimeBegin(Date registerTimeBegin) {
        this.registerTimeBegin = registerTimeBegin;
    }
    
    /**
     * 注册时间 - 到.
     *
     */
    public Date getRegisterTimeEnd() {
    	return registerTimeEnd;
    }
    
    /**
     * 注册时间 - 到 .
     *
     */
    public void setRegisterTimeEnd(Date registerTimeEnd) {
    	this.registerTimeEnd = registerTimeEnd;
    }

	public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindBranchCompanyPage [code=");
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
		builder.append(", businessPerson=");
		builder.append(businessPerson);
		builder.append(", registerTimeBegin=");
		builder.append(registerTimeBegin);
		builder.append(", registerTimeEnd=");
		builder.append(registerTimeEnd);
		builder.append(", areaId=");
		builder.append(areaId);
		builder.append(", areaName=");
		builder.append(areaName);
		builder.append(", province=");
		builder.append(province);
		builder.append(", city=");
		builder.append(city);
		builder.append("]");
		return builder.toString();
	}

}
