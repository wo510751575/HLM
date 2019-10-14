package com.lj.business.member.domain;

import java.io.Serializable;

public class ChatRoomPm  implements Serializable{
	
	
	private static final long serialVersionUID = -1988390221850354743L;
    /**
     * CODE .
     */
    private String code;

    /**
     * 群组CODE
     */
    private String pmCode;
    
    /**
     * 导购微信
     */
    private String noWxGm;
    


	/**
     * 群组名称
     */
    private String pmName;
    
    private String memberNoGm;
    
    private String memberNameGm;

    public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPmCode() {
		return pmCode;
	}

	public void setPmCode(String pmCode) {
		this.pmCode = pmCode;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
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
}
