package com.lj.business.ai.dto;

import java.io.Serializable;

public class MerchantAutoReplyDto implements Serializable{
	private static final long serialVersionUID = -228390713054344743L;
      private String code;
      private String merchantNo;
      private String memberGmNo;
      private String memberGmName; //导购名称
	  private String type;         //托管类型：1 按商户托管 2按导购托管',
      private String status;    //开启状态：0不开启  1 开启',
      
      public String getMemberGmName() {
		return memberGmName;
	}
	public void setMemberGmName(String memberGmName) {
		this.memberGmName = memberGmName;
	}
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
	public String getMemberGmNo() {
		return memberGmNo;
	}
	public void setMemberGmNo(String memberGmNo) {
		this.memberGmNo = memberGmNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
      
      
}
