package com.lj.business.st.dto.WorkRatioShop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FindWorkRatioShopReturn implements Serializable { 

     /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = 7272878980798073144L;
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
     * 店长编号 .
     */
    private String memberNoSp;

    /**
     * 店长姓名 .
     */
    private String memberNameSp;

    /**
     * 区域CODE .
     */
    private String areaCode;

    /**
     * 省CODE .
     */
    private String provinceCode;

    /**
     * 市CODE .
     */
    private String cityCode;

    /**
     * 市区CODE .
     */
    private String cityAreaCode;

    /**
     * 成单数量 .
     */
    private Long numOrder;
    
    /**
     * 成单客户数量 .
     */
    private Long numPmOrder;

    /**
     * 新增客户数量 .
     */
    private Long numPmAdd;

    /**
     * 客户数量 .
     */
    private Long numPm;

    /**
     * 跟进客户数量 .
     */
    private Long numPmCf;

    /**
     * 维护客户数量 .
     */
    private Long numPmKeep;
  /**
   * 意向客户数
   */
    private Long numPmIntention;


	/**
     * 放弃客户数量 .
     */
    private Long numPmAbandon;

    /**
     * 销售额 .
     */
    private Long numSale;

    /**
     *  .
     */
    private BigDecimal orderRate;

    /**
     * 成单数量排名 .
     */
    private Integer numOrderRank;

    /**
     * 新增客户数量排名 .
     */
    private Integer numPmAddRank;

    /**
     * 客户数量排名 .
     */
    private Integer numPmRank;

    /**
     * 跟进客户数量排名 .
     */
    private Integer numPmCfRank;

    /**
     * 维护客户数量排名 .
     */
    private Integer numPmKeepRank;

    /**
     * 放弃客户数量排名 .
     */
    private Integer numPmAbandonRank;

    /**
     * 销售额排名 .
     */
    private Integer numSaleRank;

    /**
     * 统计日期 .
     */
    private Date daySt;

    /**
     *  .
     */
    private Date openDate;

    /**
     * 创建时间 .
     */
    private Date createDate;

    public Long getNumPmIntention() {
	return numPmIntention;
}

public void setNumPmIntention(Long numPmIntention) {
	this.numPmIntention = numPmIntention;
}
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

	public String getMemberNoSp() {
		return memberNoSp;
	}

	public void setMemberNoSp(String memberNoSp) {
		this.memberNoSp = memberNoSp;
	}

	public String getMemberNameSp() {
		return memberNameSp;
	}

	public void setMemberNameSp(String memberNameSp) {
		this.memberNameSp = memberNameSp;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityAreaCode() {
		return cityAreaCode;
	}

	public void setCityAreaCode(String cityAreaCode) {
		this.cityAreaCode = cityAreaCode;
	}

	public Long getNumOrder() {
		return numOrder;
	}

	public void setNumOrder(Long numOrder) {
		this.numOrder = numOrder;
	}

    /**
     * 成单客户数量 .
     */
	public Long getNumPmOrder() {
		return numPmOrder;
	}

	/**
     * 成单客户数量 .
     */
	public void setNumPmOrder(Long numPmOrder) {
		this.numPmOrder = numPmOrder;
	}

	public Long getNumPmAdd() {
		return numPmAdd;
	}

	public void setNumPmAdd(Long numPmAdd) {
		this.numPmAdd = numPmAdd;
	}

	public Long getNumPm() {
		return numPm;
	}

	public void setNumPm(Long numPm) {
		this.numPm = numPm;
	}

	public Long getNumPmCf() {
		return numPmCf;
	}

	public void setNumPmCf(Long numPmCf) {
		this.numPmCf = numPmCf;
	}

	public Long getNumPmKeep() {
		return numPmKeep;
	}

	public void setNumPmKeep(Long numPmKeep) {
		this.numPmKeep = numPmKeep;
	}

	public Long getNumPmAbandon() {
		return numPmAbandon;
	}

	public void setNumPmAbandon(Long numPmAbandon) {
		this.numPmAbandon = numPmAbandon;
	}

	public Long getNumSale() {
		return numSale;
	}

	public void setNumSale(Long numSale) {
		this.numSale = numSale;
	}

	public BigDecimal getOrderRate() {
		return orderRate;
	}

	public void setOrderRate(BigDecimal orderRate) {
		this.orderRate = orderRate;
	}

	public Integer getNumOrderRank() {
		return numOrderRank;
	}

	public void setNumOrderRank(Integer numOrderRank) {
		this.numOrderRank = numOrderRank;
	}

	public Integer getNumPmAddRank() {
		return numPmAddRank;
	}

	public void setNumPmAddRank(Integer numPmAddRank) {
		this.numPmAddRank = numPmAddRank;
	}

	public Integer getNumPmRank() {
		return numPmRank;
	}

	public void setNumPmRank(Integer numPmRank) {
		this.numPmRank = numPmRank;
	}

	public Integer getNumPmCfRank() {
		return numPmCfRank;
	}

	public void setNumPmCfRank(Integer numPmCfRank) {
		this.numPmCfRank = numPmCfRank;
	}

	public Integer getNumPmKeepRank() {
		return numPmKeepRank;
	}

	public void setNumPmKeepRank(Integer numPmKeepRank) {
		this.numPmKeepRank = numPmKeepRank;
	}

	public Integer getNumPmAbandonRank() {
		return numPmAbandonRank;
	}

	public void setNumPmAbandonRank(Integer numPmAbandonRank) {
		this.numPmAbandonRank = numPmAbandonRank;
	}

	public Integer getNumSaleRank() {
		return numSaleRank;
	}

	public void setNumSaleRank(Integer numSaleRank) {
		this.numSaleRank = numSaleRank;
	}

	public Date getDaySt() {
		return daySt;
	}

	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWorkRatioShopReturn [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", shopNo=");
		builder.append(shopNo);
		builder.append(", shopName=");
		builder.append(shopName);
		builder.append(", memberNoSp=");
		builder.append(memberNoSp);
		builder.append(", memberNameSp=");
		builder.append(memberNameSp);
		builder.append(", areaCode=");
		builder.append(areaCode);
		builder.append(", provinceCode=");
		builder.append(provinceCode);
		builder.append(", cityCode=");
		builder.append(cityCode);
		builder.append(", cityAreaCode=");
		builder.append(cityAreaCode);
		builder.append(", numOrder=");
		builder.append(numOrder);
		builder.append(", numPmOrder=");
		builder.append(numPmOrder);
		builder.append(", numPmAdd=");
		builder.append(numPmAdd);
		builder.append(", numPm=");
		builder.append(numPm);
		builder.append(", numPmCf=");
		builder.append(numPmCf);
		builder.append(", numPmKeep=");
		builder.append(numPmKeep);
		builder.append(", numPmIntention=");
		builder.append(numPmIntention);
		builder.append(", numPmAbandon=");
		builder.append(numPmAbandon);
		builder.append(", numSale=");
		builder.append(numSale);
		builder.append(", orderRate=");
		builder.append(orderRate);
		builder.append(", numOrderRank=");
		builder.append(numOrderRank);
		builder.append(", numPmAddRank=");
		builder.append(numPmAddRank);
		builder.append(", numPmRank=");
		builder.append(numPmRank);
		builder.append(", numPmCfRank=");
		builder.append(numPmCfRank);
		builder.append(", numPmKeepRank=");
		builder.append(numPmKeepRank);
		builder.append(", numPmAbandonRank=");
		builder.append(numPmAbandonRank);
		builder.append(", numSaleRank=");
		builder.append(numSaleRank);
		builder.append(", daySt=");
		builder.append(daySt);
		builder.append(", openDate=");
		builder.append(openDate);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}
}
