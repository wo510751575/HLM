package com.lj.business.cf.dto.pmAbandon;

import java.io.Serializable;
import java.util.Date;


/**
 * The Class FindPmAbandonPage.
 */
public class FindPmAbandonList implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7185975303163800919L;

    
    /** 商户编号. */
    private String merchantNo;
    
    /**
     * 导购编号 .
     */
    private String memberNoGm;
    
    /** 客户编号. */
    private String memberNo;


    /**
     * 审批结果
             等待审批：WAIT
             同意：Y
             拒绝：N
              .
     */
    private String checkResult;
    
    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;
    
    private String memberName;
    
    private String memberNameGm;
    
    


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getMemberNameGm() {
		return memberNameGm;
	}


	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	/**
	 * Gets the 商户编号.
	 *
	 * @return the 商户编号
	 */
	public String getMerchantNo() {
		return merchantNo;
	}


	/**
	 * Sets the 商户编号.
	 *
	 * @param merchantNo the new 商户编号
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}


	/**
	 * Gets the 导购编号 .
	 *
	 * @return the 导购编号 
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}


	/**
	 * Sets the 导购编号 .
	 *
	 * @param memberNoGm the new 导购编号 
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}


	/**
	 * Gets the 客户编号.
	 *
	 * @return the 客户编号
	 */
	public String getMemberNo() {
		return memberNo;
	}


	/**
	 * Sets the 客户编号.
	 *
	 * @param memberNo the new 客户编号
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}


	/**
	 * Gets the 审批结果 等待审批：WAIT 同意：Y 拒绝：N .
	 *
	 * @return the 审批结果 等待审批：WAIT 同意：Y 拒绝：N 
	 */
	public String getCheckResult() {
		return checkResult;
	}


	/**
	 * Sets the 审批结果 等待审批：WAIT 同意：Y 拒绝：N .
	 *
	 * @param checkResult the new 审批结果 等待审批：WAIT 同意：Y 拒绝：N 
	 */
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPmAbandonPage [merchantNo=").append(merchantNo)
				.append(", memberNoGm=").append(memberNoGm)
				.append(", memberNo=").append(memberNo)
				.append(", checkResult=").append(checkResult).append("]");
		return builder.toString();
	}
    


}


