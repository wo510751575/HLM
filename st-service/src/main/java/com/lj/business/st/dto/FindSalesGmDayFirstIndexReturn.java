package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FindSalesGmDayFirstIndexReturn.
 */
public class FindSalesGmDayFirstIndexReturn implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3688077759214330477L;
	
	/**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;
	
	 /**
     * 导购编号  .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 导购头像 .
     */
    private String headAddress;

    /**
     * 统计日期 .
     */
    private Date daySt;
    
	/**
	 * Gets the shop no.
	 *
	 * @return the shop no
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * Sets the shop no.
	 *
	 * @param shopNo the new shop no
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/**
	 * Gets the shop name.
	 *
	 * @return the shop name
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * Sets the shop name.
	 *
	 * @param shopName the new shop name
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
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
	 * Gets the day st.
	 *
	 * @return the day st
	 */
	public Date getDaySt() {
		return daySt;
	}

	/**
	 * Sets the day st.
	 *
	 * @param daySt the new day st
	 */
	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindSalesGmDayFirstIndexReturn [shopNo=")
				.append(shopNo).append(", shopName=").append(shopName)
				.append(", memberNoGm=").append(memberNoGm)
				.append(", memberNameGm=").append(memberNameGm)
				.append(", headAddress=").append(headAddress)
				.append(", daySt=").append(daySt).append("]");
		return builder.toString();
	}

}
