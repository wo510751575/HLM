package com.lj.business.member.dto.forecastName;

import java.io.Serializable;
import java.util.Date;

public class UpdateForecastName implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 3422184322803191809L;

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
     * 性别:
             男：male
             女：female
             未知：unknown .
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIntentAddress() {
		return intentAddress;
	}

	public void setIntentAddress(String intentAddress) {
		this.intentAddress = intentAddress;
	}

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
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
		builder.append("UpdateForecastName [code=");
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
