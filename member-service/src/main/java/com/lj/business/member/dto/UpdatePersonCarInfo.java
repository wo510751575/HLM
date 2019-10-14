package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdatePersonMember.
 */
public class UpdatePersonCarInfo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2070383305078729740L;

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 会员号 .
	 */
	private String memberNo;

	/**
	 * 会员名称 .
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
	 * 分店编号 .
	 */
	

	/**
	 * 分店名称 .
	 */
	

	/**
	 * 商户编号 .
	 */
	private String merchantNo;

	/**
	 * 商户名称 .
	 */
	private String merchantName;
	
	/**
	 * 车辆信息 .
	 */
	private String carInfo;
	
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
	private String remark4;

	/**
	 * 备注 .
	 */
	private String remark3;

	/**
	 * 备注 .
	 */
	private String remark2;

	/**
	 * 备注 .
	 */
	private String remark;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
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

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdatePersonCarInfo [code=");
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
		builder.append(", carInfo=");
		builder.append(carInfo);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}

}
