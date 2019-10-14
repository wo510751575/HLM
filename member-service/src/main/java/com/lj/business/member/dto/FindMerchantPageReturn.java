package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

import com.lj.business.member.emus.Status;

/**
 * The Class FindMerchantPageReturn.
 */
public class FindMerchantPageReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7439813370252596692L; 
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
     * 电商状态 
             NORMAL正常、
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
    
    /**
     * 产品类型
     */
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
     * @return the cODE 
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     * @param code the new cODE 
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 商户编号 .
     *
     * @return the 商户编号 
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     * @param merchantNo the new 商户编号 
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 商户名称 .
     *
     * @return the 商户名称 
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称 .
     *
     * @param merchantName the new 商户名称 
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }


    public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	/**
     * 住址 .
     *
     * @return the 住址 
     */
    public String getAddress() {
        return address;
    }

    /**
     * 住址 .
     *
     * @param address the new 住址 
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 邮箱 .
     *
     * @return the 邮箱 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱 .
     *
     * @param email the new 邮箱 
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 所属行业 .
     *
     * @return the 所属行业 
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 所属行业 .
     *
     * @param businessType the new 所属行业 
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    /**
     * 产品类型
     */
	public String getProductType() {
		return productType;
	}

	/**
     * 产品类型
     */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
     * LOGO图片 .
     *
     * @return the lOGO图片 
     */
    public String getLogoAddr() {
        return logoAddr;
    }

    /**
     * LOGO图片 .
     *
     * @param logoAddr the new lOGO图片 
     */
    public void setLogoAddr(String logoAddr) {
        this.logoAddr = logoAddr == null ? null : logoAddr.trim();
    }

    /**
     * 官方网站 .
     *
     * @return the 官方网站 
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * 官方网站 .
     *
     * @param websiteUrl the new 官方网站 
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl == null ? null : websiteUrl.trim();
    }

    /**
     * 联系电话 .
     *
     * @return the 联系电话 
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 联系电话 .
     *
     * @param telephone the new 联系电话 
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Date getBeginProbationTime() {
		return beginProbationTime;
	}

	public void setBeginProbationTime(Date beginProbationTime) {
		this.beginProbationTime = beginProbationTime;
	}

	public Date getEndProbationTime() {
		return endProbationTime;
	}

	public void setEndProbationTime(Date endProbationTime) {
		this.endProbationTime = endProbationTime;
	}

	public String getProbationStatus() {
		return probationStatus;
	}

	public void setProbationStatus(String probationStatus) {
		this.probationStatus = probationStatus;
	}

	/**
     * 创建人 .
     *
     * @return the 创建人 
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     * @param createId the new 创建人 
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间 .
     *
     * @return the 创建时间 
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     * @param createDate the new 创建时间 
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 更新人 .
     *
     * @return the 更新人 
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 更新人 .
     *
     * @param updateId the new 更新人 
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * 更新时间 .
     *
     * @return the 更新时间 
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间 .
     *
     * @param updateDate the new 更新时间 
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 备注 .
     *
     * @return the 备注 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     * @param remark the new 备注 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     * @return the 备注 
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     * @param remark2 the new 备注 
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     * @return the 备注 
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     * @param remark3 the new 备注 
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     * @return the 备注 
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     * @param remark4 the new 备注 
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

	public String getEshopStatus() {
		return eshopStatus;
	}

	public void setEshopStatus(String eshopStatus) {
		this.eshopStatus = eshopStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindMerchantPageReturn [code=");
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
