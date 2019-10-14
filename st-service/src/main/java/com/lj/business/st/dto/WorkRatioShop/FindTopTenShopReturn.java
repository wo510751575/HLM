package com.lj.business.st.dto.WorkRatioShop;

import java.io.Serializable;

/**
 * The Class FindTopTenShopReturn.
 */
public class FindTopTenShopReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1061299611179122021L;

	
	/**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 店长编号 .
     */
    private String memberNoSp;

    /**
     * 店长姓名 .
     */
    private String memberNameSp;

    /**
     * 店长头像地址 .
     */
    private String headAddress;

	/**
	 * Gets the 分店编号 .
	 *
	 * @return the 分店编号 
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * Sets the 分店编号 .
	 *
	 * @param shopNo the new 分店编号 
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/**
	 * Gets the 分店名称 .
	 *
	 * @return the 分店名称 
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * Sets the 分店名称 .
	 *
	 * @param shopName the new 分店名称 
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * Gets the 店长编号 .
	 *
	 * @return the 店长编号 
	 */
	public String getMemberNoSp() {
		return memberNoSp;
	}

	/**
	 * Sets the 店长编号 .
	 *
	 * @param memberNoSp the new 店长编号 
	 */
	public void setMemberNoSp(String memberNoSp) {
		this.memberNoSp = memberNoSp;
	}

	/**
	 * Gets the 店长姓名 .
	 *
	 * @return the 店长姓名 
	 */
	public String getMemberNameSp() {
		return memberNameSp;
	}

	/**
	 * Sets the 店长姓名 .
	 *
	 * @param memberNameSp the new 店长姓名 
	 */
	public void setMemberNameSp(String memberNameSp) {
		this.memberNameSp = memberNameSp;
	}

	/**
	 * Gets the 店长头像地址 .
	 *
	 * @return the 店长头像地址 
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * Sets the 店长头像地址 .
	 *
	 * @param headAddress the new 店长头像地址 
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindTopTenShopReturn [shopNo=").append(shopNo)
				.append(", shopName=").append(shopName).append(", memberNoSp=")
				.append(memberNoSp).append(", memberNameSp=")
				.append(memberNameSp).append(", headAddress=")
				.append(headAddress).append("]");
		return builder.toString();
	}
    
    
	
}
