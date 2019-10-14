package com.lj.business.cm.dto;

import java.io.Serializable;

public class GeneratorMaterialTotalReturn implements Serializable { 

     /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = 803118652314368922L;

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
     * 导购编号  .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 回应数量 .
     */
    private Integer respondNum;

    /**
     * 类型ID .
     */
    private String materialTypeCode;

    /**
     * 类型名称 .
     */
    private String materialTypeName;

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

	public Integer getRespondNum() {
		return respondNum;
	}

	public void setRespondNum(Integer respondNum) {
		this.respondNum = respondNum;
	}

	public String getMaterialTypeCode() {
		return materialTypeCode;
	}

	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeneratorMaterialTotalReturn [merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", respondNum=");
		builder.append(respondNum);
		builder.append(", materialTypeCode=");
		builder.append(materialTypeCode);
		builder.append(", materialTypeName=");
		builder.append(materialTypeName);
		builder.append("]");
		return builder.toString();
	}

}
