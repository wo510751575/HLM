package com.lj.business.cf.dto.clientFollowSummary;

import java.io.Serializable;

/**
 * The Class FindClientFollowReturn.
 */
public class FindImClientFollowReturn implements Serializable { 

    
	 /** Generate cron. */
	private static final long serialVersionUID = -296672307236641553L;
	
	/**
	 * 商品名备注
	 */
	private String  remark2;
	
	/**
	 * 客户编号
	 */
	private String  memberNo;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindImClientFollowReturn [remark2=");
		builder.append(remark2);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append("]");
		return builder.toString();
	}

}
