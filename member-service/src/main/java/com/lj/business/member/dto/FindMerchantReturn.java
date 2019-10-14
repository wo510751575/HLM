package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

import com.lj.business.member.emus.Status;

/**
 * The Class FindMerchantReturn.
 */
public class FindMerchantReturn implements Serializable{

	 /** Generate cron. */
	private static final long serialVersionUID = -4027464039824536780L;
	
	   /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 状态 
             NORMAL正常、
             CANCEL注销
              .
     */
    private Status status;
    
    /**
     * 是否电商：
     * 		  NORMAL正常、
             CANCEL注销
              .
     */
    private String eshopStatus;

    /**
     * 电商网站
     */
    private String eshopUrl;
    
    /**
     * 住址 .
     */
    private String address;

    /**
     * 邮箱 .
     */
    private String email;

    /**
     * 所属行业 .
     */
    private String businessType;
    
    /** 产品类型. */
    private String productType;

    /**
     * LOGO图片 .
     */
    private String logoAddr;

    /**
     * 官方网站 .
     */
    private String websiteUrl;

    /**
     * 联系电话 .
     */
    private String telephone;
    
    /**
     * 商户试用开始时间 .
     */
    private Date beginProbationTime;
    
    /**
     * 商户试用结束时间 .
     */
    private Date endProbationTime;
    
    /**
     * 商户试用状态 .
     */
    private String probationStatus;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新人 .
     */
    private String updateId;

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

    public String getEshopUrl() {
		return eshopUrl;
	}

	public void setEshopUrl(String eshopUrl) {
		this.eshopUrl = eshopUrl;
	}

	/**
     * CODE .
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 商户编号 .
     *
     * @return the merchant no
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     * @param merchantNo the new merchant no
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 商户名称 .
     *
     * @return the merchant name
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称 .
     *
     * @param merchantName the new merchant name
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }


    /**
     * Gets the 状态 NORMAL正常、 CANCEL注销 .
     *
     * @return the 状态 NORMAL正常、 CANCEL注销 
     */
    public Status getStatus() {
		return status;
	}

	/**
	 * Sets the 状态 NORMAL正常、 CANCEL注销 .
	 *
	 * @param status the new 状态 NORMAL正常、 CANCEL注销 
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
     * 住址 .
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 住址 .
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 邮箱 .
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱 .
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 所属行业 .
     *
     * @return the business type
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 所属行业 .
     *
     * @param businessType the new business type
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    /**
     * Gets the 产品类型.
     *
     * @return the productType
     */
	public String getProductType() {
		return productType;
	}

	/**
	 * Sets the 产品类型.
	 *
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
     * LOGO图片 .
     *
     * @return the logo addr
     */
    public String getLogoAddr() {
        return logoAddr;
    }

    /**
     * LOGO图片 .
     *
     * @param logoAddr the new logo addr
     */
    public void setLogoAddr(String logoAddr) {
        this.logoAddr = logoAddr == null ? null : logoAddr.trim();
    }

    /**
     * 官方网站 .
     *
     * @return the website url
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * 官方网站 .
     *
     * @param websiteUrl the new website url
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl == null ? null : websiteUrl.trim();
    }

    /**
     * 联系电话 .
     *
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 联系电话 .
     *
     * @param telephone the new telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * Gets the 商户试用开始时间 .
     *
     * @return the 商户试用开始时间 
     */
    public Date getBeginProbationTime() {
		return beginProbationTime;
	}

	/**
	 * Sets the 商户试用开始时间 .
	 *
	 * @param beginProbationTime the new 商户试用开始时间 
	 */
	public void setBeginProbationTime(Date beginProbationTime) {
		this.beginProbationTime = beginProbationTime;
	}

	/**
	 * Gets the 商户试用结束时间 .
	 *
	 * @return the 商户试用结束时间 
	 */
	public Date getEndProbationTime() {
		return endProbationTime;
	}

	/**
	 * Sets the 商户试用结束时间 .
	 *
	 * @param endProbationTime the new 商户试用结束时间 
	 */
	public void setEndProbationTime(Date endProbationTime) {
		this.endProbationTime = endProbationTime;
	}

	/**
	 * Gets the 商户试用状态 .
	 *
	 * @return the 商户试用状态 
	 */
	public String getProbationStatus() {
		return probationStatus;
	}

	/**
	 * Sets the 商户试用状态 .
	 *
	 * @param probationStatus the new 商户试用状态 
	 */
	public void setProbationStatus(String probationStatus) {
		this.probationStatus = probationStatus;
	}

	/**
     * 创建人 .
     *
     * @return the creates the id
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     * @param createId the new creates the id
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间 .
     *
     * @return the creates the date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     * @param createDate the new creates the date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 更新人 .
     *
     * @return the update id
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 更新人 .
     *
     * @param updateId the new update id
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * 更新时间 .
     *
     * @return the update date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间 .
     *
     * @param updateDate the new update date
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 备注 .
     *
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     * @param remark the new remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     * @return the remark 2
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     * @param remark2 the new remark 2
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     * @return the remark 3
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     * @param remark3 the new remark 3
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     * @return the remark 4
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     * @param remark4 the new remark 4
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

	/**
	 * Gets the 是否电商： NORMAL正常、 CANCEL注销 .
	 *
	 * @return the 是否电商： NORMAL正常、 CANCEL注销 
	 */
	public String getEshopStatus() {
		return eshopStatus;
	}

	/**
	 * Sets the 是否电商： NORMAL正常、 CANCEL注销 .
	 *
	 * @param eshopStatus the new 是否电商： NORMAL正常、 CANCEL注销 
	 */
	public void setEshopStatus(String eshopStatus) {
		this.eshopStatus = eshopStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindMerchantReturn [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", status=");
		builder.append(status);
		builder.append(", eshopStatus=");
		builder.append(eshopStatus);
		builder.append(", eshopUrl=");
		builder.append(eshopUrl);
		builder.append(", address=");
		builder.append(address);
		builder.append(", email=");
		builder.append(email);
		builder.append(", businessType=");
		builder.append(businessType);
		builder.append(", productType=");
		builder.append(productType);
		builder.append(", logoAddr=");
		builder.append(logoAddr);
		builder.append(", websiteUrl=");
		builder.append(websiteUrl);
		builder.append(", telephone=");
		builder.append(telephone);
		builder.append(", beginProbationTime=");
		builder.append(beginProbationTime);
		builder.append(", endProbationTime=");
		builder.append(endProbationTime);
		builder.append(", probationStatus=");
		builder.append(probationStatus);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateId=");
		builder.append(updateId);
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
		builder.append("]");
		return builder.toString();
	}
	
}
