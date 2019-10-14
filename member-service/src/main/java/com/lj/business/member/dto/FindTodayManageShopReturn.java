package com.lj.business.member.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindTodayManageShopReturn.
 */
public class FindTodayManageShopReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4645530190001563843L;

	/** The head address. */
	private String headAddress; // 头像地址.
	
	/** The member name gm. */
	private String memberNameGm; // 导购姓名.
	
	/** The member no gm. */
	private String memberNoGm; // 导购编号.
	
	/** The merchant no. */
	private String merchantNo; // 商户编号.
	
	/** The ratio work. */
	private double ratioWork; // 工作完成度.
	
	/** The shop name. */
	 // 分店名称.
	
	/** The shop no. */
	 // 分店编号.

	/**
	 * Gets the head address.
	 *
	 * @return the head address
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * Sets the head address.
	 *
	 * @param headAddress the new head address
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
	 * Gets the member name gm.
	 *
	 * @return the member name gm
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * Sets the member name gm.
	 *
	 * @param memberNameGm the new member name gm
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	/**
	 * Gets the member no gm.
	 *
	 * @return the member no gm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the member no gm.
	 *
	 * @param memberNoGm the new member no gm
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the merchant no.
	 *
	 * @return the merchant no
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the merchant no.
	 *
	 * @param merchantNo the new merchant no
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the ratio work.
	 *
	 * @return the ratio work
	 */
	public double getRatioWork() {
		return ratioWork;
	}

	/**
	 * Sets the ratio work.
	 *
	 * @param ratioWork the new ratio work
	 */
	public void setRatioWork(double ratioWork) {
		this.ratioWork = ratioWork;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindTodayManageShopReturn [headAddress=");
		builder.append(headAddress);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", ratioWork=");
		builder.append(ratioWork);
		builder.append("]");
		return builder.toString();
	}

}
