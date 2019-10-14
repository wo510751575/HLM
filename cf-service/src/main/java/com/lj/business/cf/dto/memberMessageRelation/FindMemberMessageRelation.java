package com.lj.business.cf.dto.memberMessageRelation;

import java.io.Serializable;
import java.util.Date;

public class FindMemberMessageRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6319625599202760849L; 

	private String code;
	
	private String merchantNo;
	
	private String msgStatus;
	
	private Date beginDate;
	
	private Date endDate;

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

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "FindMemberMessageRelation [code=" + code + ", merchantNo="
				+ merchantNo + ", beginDate=" + beginDate + ", endDate="
				+ endDate + "]";
	}
	
}
