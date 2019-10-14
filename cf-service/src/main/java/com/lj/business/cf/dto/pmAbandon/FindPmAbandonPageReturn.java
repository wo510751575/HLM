package com.lj.business.cf.dto.pmAbandon;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindPmAbandonPageReturn.
 */
public class FindPmAbandonPageReturn implements Serializable {
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1897500649083272555L;
	
	/**
     * CODE .
     */
    private String code;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;
    
    /**
     * 
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 审批时间 .
     */
    private Date checkDate;
    
    /**
     * 放弃原因 .
     */
    private String resean;
    
    /** 跟踪总表编号. */
    private String cfNo;

    
    /** 产品code. */
    private String bomCode;
    
    /** 产品名称. */
    private String bomName;
    /**
     * 客户头像地址
     */
    private String headAddress;
    
    /**
     * 客户微信昵称
     */
    private String nickNameWx;
    /**
     * 回访时间
     */
    private Date followDate;
    /**
     * 审批人
     */
    private String checker;
    
    /**
     * 审批状态
     */
    private String checkResult;
    
    /**
     * 创建时间
     */
    private Date createDate;
    
    
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Date getFollowDate() {
		return followDate;
	}

	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}

	public String getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	public String getNickNameWx() {
		return nickNameWx;
	}

	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}

	/**
	 * Gets the cODE .
	 * @return the cODE 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the cODE .
	 *
	 * @param code the new cODE 
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * Gets the 导购姓名 .
	 *
	 * @return the 导购姓名 
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * Sets the 导购姓名 .
	 *
	 * @param memberNameGm the new 导购姓名 
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	/**
	 * Gets the 客户编号 .
	 *
	 * @return the 客户编号 
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the 客户编号 .
	 *
	 * @param memberNo the new 客户编号 
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * Gets the 客户名称 .
	 *
	 * @return the 客户名称 
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * Sets the 客户名称 .
	 *
	 * @param memberName the new 客户名称 
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * Gets the 审批时间 .
	 *
	 * @return the 审批时间 
	 */
	public Date getCheckDate() {
		return checkDate;
	}

	/**
	 * Sets the 审批时间 .
	 *
	 * @param checkDate the new 审批时间 
	 */
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	/**
	 * Gets the 放弃原因 .
	 *
	 * @return the 放弃原因 
	 */
	public String getResean() {
		return resean;
	}

	/**
	 * Sets the 放弃原因 .
	 *
	 * @param resean the new 放弃原因 
	 */
	public void setResean(String resean) {
		this.resean = resean;
	}

	/**
	 * Gets the 跟踪总表编号.
	 *
	 * @return the 跟踪总表编号
	 */
	public String getCfNo() {
		return cfNo;
	}

	/**
	 * Sets the 跟踪总表编号.
	 *
	 * @param cfNo the new 跟踪总表编号
	 */
	public void setCfNo(String cfNo) {
		this.cfNo = cfNo;
	}

	/**
	 * Gets the 产品code.
	 *
	 * @return the 产品code
	 */
	public String getBomCode() {
		return bomCode;
	}

	/**
	 * Sets the 产品code.
	 *
	 * @param bomCode the new 产品code
	 */
	public void setBomCode(String bomCode) {
		this.bomCode = bomCode;
	}

	/**
	 * Gets the 产品名称.
	 *
	 * @return the 产品名称
	 */
	public String getBomName() {
		return bomName;
	}

	/**
	 * Sets the 产品名称.
	 *
	 * @param bomName the new 产品名称
	 */
	public void setBomName(String bomName) {
		this.bomName = bomName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPmAbandonPageReturn [code=");
		builder.append(code);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", checkDate=");
		builder.append(checkDate);
		builder.append(", resean=");
		builder.append(resean);
		builder.append(", cfNo=");
		builder.append(cfNo);
		builder.append(", bomCode=");
		builder.append(bomCode);
		builder.append(", bomName=");
		builder.append(bomName);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", followDate=");
		builder.append(followDate);
		builder.append(", checker=");
		builder.append(checker);
		builder.append(", checkResult=");
		builder.append(checkResult);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}

}
