package com.lj.business.member.dto.forecastName;

import java.io.Serializable;

public class FindForecastName implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -523461936820597788L; 

	private String code;
	
	/**
     * 客户编号 .
     */
    private String memberNo;
    
    /**
     * 导购编号 .
     */
    private String memberNoGm;

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

    public String getMemberNoGm() {
        return memberNoGm;
    }

    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindForecastName [code=");
        builder.append(code);
        builder.append(", memberNo=");
        builder.append(memberNo);
        builder.append(", memberNoGm=");
        builder.append(memberNoGm);
        builder.append("]");
        return builder.toString();
    }

}
