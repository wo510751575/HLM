package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The Class AddClientExpTotal.
 */
public class AddClientExpTotal implements Serializable{

	 /** Generate cron. */
	private static final long serialVersionUID = 1156874076893290516L;
	
    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

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
     * 体验时间 .
     */
    private Date stDate;

    /**
     * 新增客户量 .
     */
    private Integer numAdd;

    /**
     * 创建时间 .
     */
    private Date createDate;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @param merchantNo the merchant no
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

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
	 * @param shopNo the shop no
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
	 * @param shopName the shop name
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
	 * @param memberNoGm the member no gm
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
	 * @param memberNameGm the member name gm
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	/**
	 * Gets the exp time.
	 *
	 * @return the exp time
	 */
	public Date getStDate() {
		return stDate;
	}

	/**
	 * Sets the exp time.
	 *
	 * @param stDate the exp time
	 */
	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}

	/**
	 * Gets the num add.
	 *
	 * @return the num add
	 */
	public Integer getNumAdd() {
		return numAdd;
	}

	/**
	 * Sets the num add.
	 *
	 * @param numAdd the num add
	 */
	public void setNumAdd(Integer numAdd) {
		this.numAdd = numAdd;
	}

	/**
	 * Gets the create date.
	 *
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the create date.
	 *
	 * @param createDate the creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AddClientExpTotal [code=" + code + ", merchantNo=" + merchantNo
				+ ", shopNo=" + shopNo + ", shopName=" + shopName
				+ ", memberNoGm=" + memberNoGm + ", memberNameGm="
				+ memberNameGm + ", stDate=" + stDate + ", numAdd=" + numAdd
				+ ", createDate=" + createDate + "]";
	}
    
    

}
