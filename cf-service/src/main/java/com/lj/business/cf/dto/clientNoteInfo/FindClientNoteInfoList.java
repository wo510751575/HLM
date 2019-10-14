package com.lj.business.cf.dto.clientNoteInfo;

import java.io.Serializable;
import java.util.Date;

public class FindClientNoteInfoList implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 4582013494545297259L;

    /**
     * 发送时间 .
     */
    private String sendTime;

    /**
     * 创建时间 .
     */
    private Date createDate;
    /**
     * 导购编号
     */
   private String memberNoGm;
   
   
    public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	private String remark2;


	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "FindClientNoteInfoList [sendTime=" + sendTime + ", createDate="
				+ createDate + ", memberNoGm=" + memberNoGm + ", remark2="
				+ remark2 + "]";
	}

    
    
}
