package com.lj.business.member.domain;

import java.util.Date;

public class ForecastName {
    /**
     * CODE .
     */
    private String code;

    /**
     * 客户编码 .
     */
    private String memberCode;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 姓名 .
     */
    private String name;

    /**
     * 手机号 .
     */
    private String mobile;

    /**
     * 性别:             男：male             女：female             未知：unknown .
     */
    private String sex;

    /**
     * 意向旅游地 .
     */
    private String intentAddress;

    /**
     * 报名人数 .
     */
    private Integer personCount;

    /**
     * 产品路线 .
     */
    private String productLine;
    
    /**
     * 出发地 .
     */
    private String startAddress;
    
    /**
     * 费用预算 .
     */
    private Long budget;
    
    /**
     * 出游时长 .
     */
    private String tourtime;
    
    /**
     * 行程标准 .
     */
    private String travelStandard;
    
    /**
     * 曾出游地 .
     */
    private String onceAddress;

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
     * 客户编码 .
     *
     */
    public String getMemberCode() {
        return memberCode;
    }

    /**
     * 客户编码 .
     *
     */
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode == null ? null : memberCode.trim();
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
     * 姓名 .
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名 .
     *
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * 性别:             男：male             女：female             未知：unknown .
     *
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别:             男：male             女：female             未知：unknown .
     *
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 意向旅游地 .
     *
     */
    public String getIntentAddress() {
        return intentAddress;
    }

    /**
     * 意向旅游地 .
     *
     */
    public void setIntentAddress(String intentAddress) {
        this.intentAddress = intentAddress == null ? null : intentAddress.trim();
    }

    /**
     * 报名人数 .
     *
     */
    public Integer getPersonCount() {
        return personCount;
    }

    /**
     * 报名人数 .
     *
     */
    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    /**
     * 产品路线 .
     *
     */
    public String getProductLine() {
        return productLine;
    }

    /**
     * 产品路线 .
     *
     */
    public void setProductLine(String productLine) {
        this.productLine = productLine == null ? null : productLine.trim();
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

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
	}

	public String getTourtime() {
		return tourtime;
	}

	public void setTourtime(String tourtime) {
		this.tourtime = tourtime;
	}

	public String getTravelStandard() {
		return travelStandard;
	}

	public void setTravelStandard(String travelStandard) {
		this.travelStandard = travelStandard;
	}

	public String getOnceAddress() {
		return onceAddress;
	}

	public void setOnceAddress(String onceAddress) {
		this.onceAddress = onceAddress;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ForecastName [code=");
		builder.append(code);
		builder.append(", memberCode=");
		builder.append(memberCode);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", name=");
		builder.append(name);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", intentAddress=");
		builder.append(intentAddress);
		builder.append(", personCount=");
		builder.append(personCount);
		builder.append(", productLine=");
		builder.append(productLine);
		builder.append(", startAddress=");
		builder.append(startAddress);
		builder.append(", budget=");
		builder.append(budget);
		builder.append(", tourtime=");
		builder.append(tourtime);
		builder.append(", travelStandard=");
		builder.append(travelStandard);
		builder.append(", onceAddress=");
		builder.append(onceAddress);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
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

}