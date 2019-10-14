package com.lj.business.cm.dto.guidIntroduceMaterial;

import java.io.Serializable;
import java.util.Date;

public class UpdateGuidIntroduceMaterial implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -733265226002856959L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    

    /**
     * 分店名称 .
     */
    

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;
    
    /**
     * 模板号 .
     */
    private String templateNo;
    
    /**
     * 图像地址 .
     */
    private String headAddress;

    /**
     * 姓名 .
     */
    private String name;

    /**
     * 职位 .
     */
    private String position;

    /**
     * 服务星级 .
     */
    private Double serveLevel;

    /**
     * 专业水平 .
     */
    private Double professionalLevel;

    /**
     * 手机号 .
     */
    private String mobile;

    /**
     * 公司名称 .
     */
    private String companyName;

    /**
     * 公司地址 .
     */
    private String companyAddress;

    /**
     * 口号 .
     */
    private String slogan;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注2 .
     */
    private String remark2;

    /**
     * 备注3 .
     */
    private String remark3;

    /**
     * 备注4 .
     */
    private String remark4;
    
    /**
     * 创建时间 .
     */
    private Date createDate;
    
    /**
     * 产品名称 .
     */
    private String bomName;
    
    /**
     * 产品图片地址 .
     */
    private String bomAddress;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
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

	public String getTemplateNo() {
		return templateNo;
	}

	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
	}

	public String getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Double getServeLevel() {
		return serveLevel;
	}

	public void setServeLevel(Double serveLevel) {
		this.serveLevel = serveLevel;
	}

	public Double getProfessionalLevel() {
		return professionalLevel;
	}

	public void setProfessionalLevel(Double professionalLevel) {
		this.professionalLevel = professionalLevel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getBomName() {
		return bomName;
	}

	public void setBomName(String bomName) {
		this.bomName = bomName;
	}

	public String getBomAddress() {
		return bomAddress;
	}

	public void setBomAddress(String bomAddress) {
		this.bomAddress = bomAddress;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateGuidIntroduceMaterial [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", templateNo=");
		builder.append(templateNo);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", name=");
		builder.append(name);
		builder.append(", position=");
		builder.append(position);
		builder.append(", serveLevel=");
		builder.append(serveLevel);
		builder.append(", professionalLevel=");
		builder.append(professionalLevel);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", companyAddress=");
		builder.append(companyAddress);
		builder.append(", slogan=");
		builder.append(slogan);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", bomName=");
		builder.append(bomName);
		builder.append(", bomAddress=");
		builder.append(bomAddress);
		builder.append("]");
		return builder.toString();
	}

}
