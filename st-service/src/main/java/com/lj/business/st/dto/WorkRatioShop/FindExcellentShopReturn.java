package com.lj.business.st.dto.WorkRatioShop;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FindExcellentShopReturn.
 */
public class FindExcellentShopReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6625471107913770996L;
	

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
     * 分店LOGO图片.
     */
    private String logoAddr;
    
    /** 销售额. */
    private Long numSale;
    
    /** 统计时间. */
    private Date daySt;
    
    
	/**
	 * Gets the num sale.
	 *
	 * @return the num sale
	 */
	public Long getNumSale() {
		return numSale;
	}

	/**
	 * Sets the num sale.
	 *
	 * @param numSale the num sale
	 */
	public void setNumSale(Long numSale) {
		this.numSale = numSale;
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
	 * @param daySt the day st
	 */
	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}

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
	 * Gets the 分店LOGO图片.
	 *
	 * @return the 分店LOGO图片
	 */
	public String getLogoAddr() {
		return logoAddr;
	}

	/**
	 * Sets the 分店LOGO图片.
	 *
	 * @param logoAddr the new 分店LOGO图片
	 */
	public void setLogoAddr(String logoAddr) {
		this.logoAddr = logoAddr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FindExcellentShopReturn [shopNo=" + shopNo + ", shopName="
				+ shopName + ", memberNoSp=" + memberNoSp + ", memberNameSp="
				+ memberNameSp + ", logoAddr=" + logoAddr + ", numSale="
				+ numSale + ", daySt=" + daySt + "]";
	}
    

}
