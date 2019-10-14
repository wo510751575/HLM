package com.lj.business.cf.dto.comTask;

import java.io.Serializable;
import java.util.Date;

public class FindLastComTaskReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3578400124723555463L;
	
	/**
	 * 编号
	 */
	private String code;
	
	/**
	 * 导购编号
	 */
	private String memberNo;
	
	/**
	 * 导购编号
	 */
	private String memberNoGm;
	
	/**
	 * 任务备注
	 */
	private String remarkCom;

	/**
	 * 工作日期
	 */
	private Date lastResultDate;

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

	public String getRemarkCom() {
		return remarkCom;
	}

	public void setRemarkCom(String remarkCom) {
		this.remarkCom = remarkCom;
	}

	public Date getLastResultDate() {
		return lastResultDate;
	}

	public void setLastResultDate(Date lastResultDate) {
		this.lastResultDate = lastResultDate;
	}

	@Override
	public String toString() {
		return "FindLastComTaskReturn [code=" + code + ", memberNo=" + memberNo
				+ ", memberNoGm=" + memberNoGm + ", remarkCom=" + remarkCom
				+ ", lastResultDate=" + lastResultDate + "]";
	}

}
