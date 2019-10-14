package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * The Class AddGuidMemberReturn.
 */
public class AddGuidMemberReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2512462995149172532L; 
	/**
     * CODE .
     */
    private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "AddGuidMemberReturn [code=" + code + "]";
	}
    
}
