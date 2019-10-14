package com.lj.business.st.dto.salesGmDayFirst;

import java.io.Serializable;
import java.util.Date;

public class AddSalesGmDayFirst implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -803540145823627255L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 区域编号 .
     */
    private String areaCode;

    /**
     * 区域名称 .
     */
    private String areaName;

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
     * 销售额 .
     */
    private Long numSale;

    /**
     * 销售目标 .
     */
    private Long numSaleTarget;

    /**
     * 统计日期 .
     */
    private Date daySt;

    /**
     * 修改时间 .
     */
    private Date updateDate;

    /**
     * 创建时间 .
     */
    private Date createDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	public String getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	public Long getNumSale() {
		return numSale;
	}

	public void setNumSale(Long numSale) {
		this.numSale = numSale;
	}

	public Long getNumSaleTarget() {
		return numSaleTarget;
	}

	public void setNumSaleTarget(Long numSaleTarget) {
		this.numSaleTarget = numSaleTarget;
	}

	public Date getDaySt() {
		return daySt;
	}

	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddSalesGmDayFirst [code=").append(code)
				.append(", merchantNo=").append(merchantNo)
				.append(", areaCode=").append(areaCode).append(", areaName=")
				.append(areaName).append(", shopNo=").append(shopNo)
				.append(", shopName=").append(shopName).append(", memberNoGm=")
				.append(memberNoGm).append(", memberNameGm=")
				.append(memberNameGm).append(", headAddress=")
				.append(headAddress).append(", numSale=").append(numSale)
				.append(", numSaleTarget=").append(numSaleTarget)
				.append(", daySt=").append(daySt).append(", updateDate=")
				.append(updateDate).append(", createDate=").append(createDate)
				.append("]");
		return builder.toString();
	}
    
}
