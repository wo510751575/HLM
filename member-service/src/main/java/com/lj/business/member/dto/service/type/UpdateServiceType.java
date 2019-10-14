package com.lj.business.member.dto.service.type;

import java.io.Serializable;
import java.util.Date;

public class UpdateServiceType implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -8005221785686463075L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 服务类型 .
     */
    private String serviceType;

    /**
     * 服务名称 .
     */
    private String serviceName;

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

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	@Override
	public String toString() {
		return "UpdateServiceType [code=" + code + ", serviceType="
				+ serviceType + ", serviceName=" + serviceName + ", createId="
				+ createId + ", createDate=" + createDate + ", remark="
				+ remark + ", remark2=" + remark2 + ", remark3=" + remark3
				+ ", remark4=" + remark4 + "]";
	}
    
}
