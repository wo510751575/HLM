package com.lj.business.api.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateMemberQcordDto.
 */
public class UpdateMemberQcordDto implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1924258505322378358L;
	
	/**
     * CODE .
     */
    private String memberNo;
    
    /**
     * 二维码 .
     */
    private String qcord;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getQcord() {
		return qcord;
	}

	public void setQcord(String qcord) {
		this.qcord = qcord;
	}

	@Override
	public String toString() {
		return "UpdateMemberQcordDto [memberNo=" + memberNo + ", qcord=" + qcord + "]";
	}
}
