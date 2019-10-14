package com.lj.business.st.dto.WorkRatioShop;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class AddWorkRatioShop.
 */
public class AddWorkRatioShop implements Serializable { 

     /** Generate cron. */
	private static final long serialVersionUID = -412387193646736178L;
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
     * 分店LOGO图片 .
     */
    private String logoAddr;
    
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
     * 放弃客户数量 .
     */
    private Long numPmAbandon;

    /**
     * 意向客户数量 .
     */
    private Long numPmIntention;

    /**
     * 销售额 .
     */
    private Long numSale;

    /**
     *  .
     */
    private Double orderRate;

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
     * 成单数量排名_昨日 .
     */
    private Integer numOrderRankYtd;

    /**
     * 新增客户数量排名_昨日 .
     */
    private Integer numPmAddRankYtd;

    /**
     * 客户数量排名_昨日 .
     */
    private Integer numPmRankYtd;

    /**
     * 跟进客户数量排名_昨日 .
     */
    private Integer numPmCfRankYtd;

    /**
     * 维护客户数量排名_昨日 .
     */
    private Integer numPmKeepRankYtd;

    /**
     * 放弃客户数量排名_昨日 .
     */
    private Integer numPmAbandonRankYtd;

    /**
     * 销售额排名_昨日 .
     */
    private Integer numSaleRankYtd;

    /**
     * 统计日期 .
     */
    private Date daySt;

    /**
     *  .
     */
    private Date openDate;

    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP .
     */
    private String dimensionSt;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * CODE .
     *
     * @return the cODE 
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     * @param code the new cODE 
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 商户编号 .
     *
     * @return the 商户编号 
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     * @param merchantNo the new 商户编号 
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 分店编号 .
     *
     * @return the 分店编号 
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * 分店编号 .
     *
     * @param shopNo the new 分店编号 
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    /**
     * 分店名称 .
     *
     * @return the 分店名称 
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 分店名称 .
     *
     * @param shopName the new 分店名称 
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * 店长编号 .
     *
     * @return the 店长编号 
     */
    public String getMemberNoSp() {
        return memberNoSp;
    }

    /**
     * 店长编号 .
     *
     * @param memberNoSp the new 店长编号 
     */
    public void setMemberNoSp(String memberNoSp) {
        this.memberNoSp = memberNoSp == null ? null : memberNoSp.trim();
    }

    /**
     * 店长姓名 .
     *
     * @return the 店长姓名 
     */
    public String getMemberNameSp() {
        return memberNameSp;
    }

    /**
     * 店长姓名 .
     *
     * @param memberNameSp the new 店长姓名 
     */
    public void setMemberNameSp(String memberNameSp) {
        this.memberNameSp = memberNameSp == null ? null : memberNameSp.trim();
    }

    /**
     * 区域CODE .
     *
     * @return the 区域CODE 
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 区域CODE .
     *
     * @param areaCode the new 区域CODE 
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 省CODE .
     *
     * @return the 省CODE 
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 省CODE .
     *
     * @param provinceCode the new 省CODE 
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * 市CODE .
     *
     * @return the 市CODE 
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 市CODE .
     *
     * @param cityCode the new 市CODE 
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * 市区CODE .
     *
     * @return the 市区CODE 
     */
    public String getCityAreaCode() {
        return cityAreaCode;
    }

    /**
     * 市区CODE .
     *
     * @param cityAreaCode the new 市区CODE 
     */
    public void setCityAreaCode(String cityAreaCode) {
        this.cityAreaCode = cityAreaCode == null ? null : cityAreaCode.trim();
    }

    /**
     * 成单数量 .
     *
     * @return the 成单数量 
     */
    public Long getNumOrder() {
        return numOrder;
    }

    /**
     * 成单数量 .
     *
     * @param numOrder the new 成单数量 
     */
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
	
    /**
     * 新增客户数量 .
     *
     * @return the 新增客户数量 
     */
    public Long getNumPmAdd() {
        return numPmAdd;
    }

    /**
     * 新增客户数量 .
     *
     * @param numPmAdd the new 新增客户数量 
     */
    public void setNumPmAdd(Long numPmAdd) {
        this.numPmAdd = numPmAdd;
    }

    /**
     * 客户数量 .
     *
     * @return the 客户数量 
     */
    public Long getNumPm() {
        return numPm;
    }

    /**
     * 客户数量 .
     *
     * @param numPm the new 客户数量 
     */
    public void setNumPm(Long numPm) {
        this.numPm = numPm;
    }

    /**
     * 跟进客户数量 .
     *
     * @return the 跟进客户数量 
     */
    public Long getNumPmCf() {
        return numPmCf;
    }

    /**
     * 跟进客户数量 .
     *
     * @param numPmCf the new 跟进客户数量 
     */
    public void setNumPmCf(Long numPmCf) {
        this.numPmCf = numPmCf;
    }

    /**
     * 维护客户数量 .
     *
     * @return the 维护客户数量 
     */
    public Long getNumPmKeep() {
        return numPmKeep;
    }

    /**
     * 维护客户数量 .
     *
     * @param numPmKeep the new 维护客户数量 
     */
    public void setNumPmKeep(Long numPmKeep) {
        this.numPmKeep = numPmKeep;
    }

    /**
     * 放弃客户数量 .
     *
     * @return the 放弃客户数量 
     */
    public Long getNumPmAbandon() {
        return numPmAbandon;
    }

    /**
     * 放弃客户数量 .
     *
     * @param numPmAbandon the new 放弃客户数量 
     */
    public void setNumPmAbandon(Long numPmAbandon) {
        this.numPmAbandon = numPmAbandon;
    }

    /**
     * 意向客户数量 .
     *
     * @return the 意向客户数量 
     */
    public Long getNumPmIntention() {
        return numPmIntention;
    }

    /**
     * 意向客户数量 .
     *
     * @param numPmIntention the new 意向客户数量 
     */
    public void setNumPmIntention(Long numPmIntention) {
        this.numPmIntention = numPmIntention;
    }

    /**
     * 销售额 .
     *
     * @return the 销售额 
     */
    public Long getNumSale() {
        return numSale;
    }

    /**
     * 销售额 .
     *
     * @param numSale the new 销售额 
     */
    public void setNumSale(Long numSale) {
        this.numSale = numSale;
    }

    /**
     * .
     *
     * @return the order rate
     */
    public Double getOrderRate() {
        return orderRate;
    }

    /**
     * .
     *
     * @param orderRate the order rate
     */
    public void setOrderRate(Double orderRate) {
        this.orderRate = orderRate;
    }

    /**
     * 成单数量排名 .
     *
     * @return the 成单数量排名 
     */
    public Integer getNumOrderRank() {
        return numOrderRank;
    }

    /**
     * 成单数量排名 .
     *
     * @param numOrderRank the new 成单数量排名 
     */
    public void setNumOrderRank(Integer numOrderRank) {
        this.numOrderRank = numOrderRank;
    }

    /**
     * 新增客户数量排名 .
     *
     * @return the 新增客户数量排名 
     */
    public Integer getNumPmAddRank() {
        return numPmAddRank;
    }

    /**
     * 新增客户数量排名 .
     *
     * @param numPmAddRank the new 新增客户数量排名 
     */
    public void setNumPmAddRank(Integer numPmAddRank) {
        this.numPmAddRank = numPmAddRank;
    }

    /**
     * 客户数量排名 .
     *
     * @return the 客户数量排名 
     */
    public Integer getNumPmRank() {
        return numPmRank;
    }

    /**
     * 客户数量排名 .
     *
     * @param numPmRank the new 客户数量排名 
     */
    public void setNumPmRank(Integer numPmRank) {
        this.numPmRank = numPmRank;
    }

    /**
     * 跟进客户数量排名 .
     *
     * @return the 跟进客户数量排名 
     */
    public Integer getNumPmCfRank() {
        return numPmCfRank;
    }

    /**
     * 跟进客户数量排名 .
     *
     * @param numPmCfRank the new 跟进客户数量排名 
     */
    public void setNumPmCfRank(Integer numPmCfRank) {
        this.numPmCfRank = numPmCfRank;
    }

    /**
     * 维护客户数量排名 .
     *
     * @return the 维护客户数量排名 
     */
    public Integer getNumPmKeepRank() {
        return numPmKeepRank;
    }

    /**
     * 维护客户数量排名 .
     *
     * @param numPmKeepRank the new 维护客户数量排名 
     */
    public void setNumPmKeepRank(Integer numPmKeepRank) {
        this.numPmKeepRank = numPmKeepRank;
    }

    /**
     * 放弃客户数量排名 .
     *
     * @return the 放弃客户数量排名 
     */
    public Integer getNumPmAbandonRank() {
        return numPmAbandonRank;
    }

    /**
     * 放弃客户数量排名 .
     *
     * @param numPmAbandonRank the new 放弃客户数量排名 
     */
    public void setNumPmAbandonRank(Integer numPmAbandonRank) {
        this.numPmAbandonRank = numPmAbandonRank;
    }

    /**
     * 销售额排名 .
     *
     * @return the 销售额排名 
     */
    public Integer getNumSaleRank() {
        return numSaleRank;
    }

    /**
     * 销售额排名 .
     *
     * @param numSaleRank the new 销售额排名 
     */
    public void setNumSaleRank(Integer numSaleRank) {
        this.numSaleRank = numSaleRank;
    }

    /**
     * 成单数量排名_昨日 .
     *
     * @return the 成单数量排名_昨日 
     */
    public Integer getNumOrderRankYtd() {
        return numOrderRankYtd;
    }

    /**
     * 成单数量排名_昨日 .
     *
     * @param numOrderRankYtd the new 成单数量排名_昨日 
     */
    public void setNumOrderRankYtd(Integer numOrderRankYtd) {
        this.numOrderRankYtd = numOrderRankYtd;
    }

    /**
     * 新增客户数量排名_昨日 .
     *
     * @return the 新增客户数量排名_昨日 
     */
    public Integer getNumPmAddRankYtd() {
        return numPmAddRankYtd;
    }

    /**
     * 新增客户数量排名_昨日 .
     *
     * @param numPmAddRankYtd the new 新增客户数量排名_昨日 
     */
    public void setNumPmAddRankYtd(Integer numPmAddRankYtd) {
        this.numPmAddRankYtd = numPmAddRankYtd;
    }

    /**
     * 客户数量排名_昨日 .
     *
     * @return the 客户数量排名_昨日 
     */
    public Integer getNumPmRankYtd() {
        return numPmRankYtd;
    }

    /**
     * 客户数量排名_昨日 .
     *
     * @param numPmRankYtd the new 客户数量排名_昨日 
     */
    public void setNumPmRankYtd(Integer numPmRankYtd) {
        this.numPmRankYtd = numPmRankYtd;
    }

    /**
     * 跟进客户数量排名_昨日 .
     *
     * @return the 跟进客户数量排名_昨日 
     */
    public Integer getNumPmCfRankYtd() {
        return numPmCfRankYtd;
    }

    /**
     * 跟进客户数量排名_昨日 .
     *
     * @param numPmCfRankYtd the new 跟进客户数量排名_昨日 
     */
    public void setNumPmCfRankYtd(Integer numPmCfRankYtd) {
        this.numPmCfRankYtd = numPmCfRankYtd;
    }

    /**
     * 维护客户数量排名_昨日 .
     *
     * @return the 维护客户数量排名_昨日 
     */
    public Integer getNumPmKeepRankYtd() {
        return numPmKeepRankYtd;
    }

    /**
     * 维护客户数量排名_昨日 .
     *
     * @param numPmKeepRankYtd the new 维护客户数量排名_昨日 
     */
    public void setNumPmKeepRankYtd(Integer numPmKeepRankYtd) {
        this.numPmKeepRankYtd = numPmKeepRankYtd;
    }

    /**
     * 放弃客户数量排名_昨日 .
     *
     * @return the 放弃客户数量排名_昨日 
     */
    public Integer getNumPmAbandonRankYtd() {
        return numPmAbandonRankYtd;
    }

    /**
     * 放弃客户数量排名_昨日 .
     *
     * @param numPmAbandonRankYtd the new 放弃客户数量排名_昨日 
     */
    public void setNumPmAbandonRankYtd(Integer numPmAbandonRankYtd) {
        this.numPmAbandonRankYtd = numPmAbandonRankYtd;
    }

    /**
     * 销售额排名_昨日 .
     *
     * @return the 销售额排名_昨日 
     */
    public Integer getNumSaleRankYtd() {
        return numSaleRankYtd;
    }

    /**
     * 销售额排名_昨日 .
     *
     * @param numSaleRankYtd the new 销售额排名_昨日 
     */
    public void setNumSaleRankYtd(Integer numSaleRankYtd) {
        this.numSaleRankYtd = numSaleRankYtd;
    }

    /**
     * 统计日期 .
     *
     * @return the 统计日期 
     */
    public Date getDaySt() {
        return daySt;
    }

    /**
     * 统计日期 .
     *
     * @param daySt the new 统计日期 
     */
    public void setDaySt(Date daySt) {
        this.daySt = daySt;
    }

    /**
     * .
     *
     * @return the open date
     */
    public Date getOpenDate() {
        return openDate;
    }

    /**
     * .
     *
     * @param openDate the open date
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**
     * 统计维度
     * 商户：MERCHANT
     * 区域：AREA
     * 门店：SHOP .
     *
     * @return the 统计维度 商户：MERCHANT 区域：AREA 门店：SHOP 
     */
    public String getDimensionSt() {
        return dimensionSt;
    }

    /**
     * 统计维度
     * 商户：MERCHANT
     * 区域：AREA
     * 门店：SHOP .
     *
     * @param dimensionSt the new 统计维度 商户：MERCHANT 区域：AREA 门店：SHOP 
     */
    public void setDimensionSt(String dimensionSt) {
        this.dimensionSt = dimensionSt == null ? null : dimensionSt.trim();
    }

    /**
     * 创建时间 .
     *
     * @return the 创建时间 
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     * @param createDate the new 创建时间 
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

	/**
	 * Gets the 分店LOGO图片 .
	 *
	 * @return the 分店LOGO图片 
	 */
	public String getLogoAddr() {
		return logoAddr;
	}

	/**
	 * Sets the 分店LOGO图片 .
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
		StringBuilder builder = new StringBuilder();
		builder.append("AddWorkRatioShop [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", shopNo=");
		builder.append(shopNo);
		builder.append(", shopName=");
		builder.append(shopName);
		builder.append(", logoAddr=");
		builder.append(logoAddr);
		builder.append(", memberNoSp=");
		builder.append(memberNoSp);
		builder.append(", memberNameSp=");
		builder.append(memberNameSp);
		builder.append(", headAddress=");
		builder.append(headAddress);
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
		builder.append(", numPmAbandon=");
		builder.append(numPmAbandon);
		builder.append(", numPmIntention=");
		builder.append(numPmIntention);
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
		builder.append(", numOrderRankYtd=");
		builder.append(numOrderRankYtd);
		builder.append(", numPmAddRankYtd=");
		builder.append(numPmAddRankYtd);
		builder.append(", numPmRankYtd=");
		builder.append(numPmRankYtd);
		builder.append(", numPmCfRankYtd=");
		builder.append(numPmCfRankYtd);
		builder.append(", numPmKeepRankYtd=");
		builder.append(numPmKeepRankYtd);
		builder.append(", numPmAbandonRankYtd=");
		builder.append(numPmAbandonRankYtd);
		builder.append(", numSaleRankYtd=");
		builder.append(numSaleRankYtd);
		builder.append(", daySt=");
		builder.append(daySt);
		builder.append(", openDate=");
		builder.append(openDate);
		builder.append(", dimensionSt=");
		builder.append(dimensionSt);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}
}