package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

public class AddFriendsTaskCountDto implements Serializable {

	private String totalMobile;
	private String totalAlreadyExcMobile;
	private String successMobile;
	private String trans;
	private String wxGmCount;

	public String getTotalMobile() {
		return totalMobile;
	}

	public void setTotalMobile(String totalMobile) {
		this.totalMobile = totalMobile;
	}

	public String getTotalAlreadyExcMobile() {
		return totalAlreadyExcMobile;
	}

	public void setTotalAlreadyExcMobile(String totalAlreadyExcMobile) {
		this.totalAlreadyExcMobile = totalAlreadyExcMobile;
	}

	public String getSuccessMobile() {
		return successMobile;
	}

	public void setSuccessMobile(String successMobile) {
		this.successMobile = successMobile;
	}

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	public String getWxGmCount() {
		return wxGmCount;
	}

	public void setWxGmCount(String wxGmCount) {
		this.wxGmCount = wxGmCount;
	}

	@Override
	public String toString() {
		return "AddFriendsTaskCountDto [totalMobile=" + totalMobile + ", totalAlreadyExcMobile=" + totalAlreadyExcMobile
				+ ", successMobile=" + successMobile + ", trans=" + trans + ", wxGmCount=" + wxGmCount + "]";
	}

}
