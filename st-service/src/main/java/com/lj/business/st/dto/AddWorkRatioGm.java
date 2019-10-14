package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

public class AddWorkRatioGm implements Serializable { 


    private static final long serialVersionUID = -1703398676249322203L;

    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 区域CODE .
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
     * 头像地址 .
     */
    private String headAddress;

    /**
     * 工作完成度 .
     */
    private Double ratioWork;

    /**
     * 击败导购数量-全公司 .
     */
    private Integer numBeatGm;

    /**
     * 客户数量 .
     */
    private Long numPm;

    /**
     * 新增客户数量 .
     */
    private Long numPmAdd;

    /**
     * 意向客户数量 .
     */
    private Long numPmIntention;

    /**
     * 暂停跟进客户数量 .
     */
    private Long numPmAbandon;

    /**
     * 销售额 .
     */
    private Long numSale;

    /**
     * 成单数量 .
     */
    private Long numOrder;

    /**
     * 成单客户数量 .
     */
    private Long numPmOrder;

    /**
     * 图文被阅读数量 .
     */
    private Long numRead;

    /**
     * 社交数量 .
     */
    private Long numSocial;

    /**
     * 跟进客户数量 .
     */
    private Long numPmCf;

    /**
     * 产生跟进的客户数量 .
     */
    private Long numPmCfDay;
    
    /**
     * 跟进客户次数 .
     */
    private Long numCfDay;

    /**
     * 维护客户数量 .
     */
    private Long numPmKeep;

    /**
     * 工作完成度排名 .
     */
    private Integer ratioWorkRank;

    /**
     * 客户数量排名 .
     */
    private Integer numPmRank;

    /**
     * 新增客户数量排名 .
     */
    private Integer numPmAddRank;

    /**
     * 销售额排名 .
     */
    private Integer numSaleRank;

    /**
     * 成单数量排名 .
     */
    private Integer numOrderRank;

    /**
     * 图文被阅读数量排名 .
     */
    private Integer numReadRank;

    /**
     * 工作完成度排名_昨日 .
     */
    private Integer ratioWorkRankYtd;

    /**
     * 客户数量排名_昨日 .
     */
    private Integer numPmRankYtd;

    /**
     * 新增客户数量排名 .
     */
    private Integer numPmAddRankYtd;

    /**
     * 销售额排名 .
     */
    private Integer numSaleRankYtd;

    /**
     * 成单数量排名 .
     */
    private Integer numOrderRankYtd;

    /**
     * 图文被阅读数量排名 .
     */
    private Integer numReadRankYtd;

    /**
     * 统计日期 .
     */
    private Date daySt;

    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP
             导购：GUID .
     */
    private String dimensionSt;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 到店体验数 .
     */
    private Long numVisit;

    /**
     * 到店体验数排名 .
     */
    private Long numVisitRank;

    /**
     * 到店体验数昨日排名 .
     */
    private Long numVisitRankYtd;

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

	public Double getRatioWork() {
		return ratioWork;
	}

	public void setRatioWork(Double ratioWork) {
		this.ratioWork = ratioWork;
	}

	public Integer getNumBeatGm() {
		return numBeatGm;
	}

	public void setNumBeatGm(Integer numBeatGm) {
		this.numBeatGm = numBeatGm;
	}

	public Long getNumPm() {
		return numPm;
	}

	public void setNumPm(Long numPm) {
		this.numPm = numPm;
	}

	public Long getNumPmAdd() {
		return numPmAdd;
	}

	public void setNumPmAdd(Long numPmAdd) {
		this.numPmAdd = numPmAdd;
	}

	public Long getNumPmIntention() {
		return numPmIntention;
	}

	public void setNumPmIntention(Long numPmIntention) {
		this.numPmIntention = numPmIntention;
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

	public Long getNumRead() {
		return numRead;
	}

	public void setNumRead(Long numRead) {
		this.numRead = numRead;
	}

	public Long getNumSocial() {
		return numSocial;
	}

	public void setNumSocial(Long numSocial) {
		this.numSocial = numSocial;
	}

	public Long getNumPmCf() {
		return numPmCf;
	}

	public void setNumPmCf(Long numPmCf) {
		this.numPmCf = numPmCf;
	}

	public Long getNumPmCfDay() {
		return numPmCfDay;
	}

	public void setNumPmCfDay(Long numPmCfDay) {
		this.numPmCfDay = numPmCfDay;
	}

	public Long getNumCfDay() {
		return numCfDay;
	}

	public void setNumCfDay(Long numCfDay) {
		this.numCfDay = numCfDay;
	}

	public Long getNumPmKeep() {
		return numPmKeep;
	}

	public void setNumPmKeep(Long numPmKeep) {
		this.numPmKeep = numPmKeep;
	}

	public Integer getRatioWorkRank() {
		return ratioWorkRank;
	}

	public void setRatioWorkRank(Integer ratioWorkRank) {
		this.ratioWorkRank = ratioWorkRank;
	}

	public Integer getNumPmRank() {
		return numPmRank;
	}

	public void setNumPmRank(Integer numPmRank) {
		this.numPmRank = numPmRank;
	}

	public Integer getNumPmAddRank() {
		return numPmAddRank;
	}

	public void setNumPmAddRank(Integer numPmAddRank) {
		this.numPmAddRank = numPmAddRank;
	}

	public Integer getNumSaleRank() {
		return numSaleRank;
	}

	public void setNumSaleRank(Integer numSaleRank) {
		this.numSaleRank = numSaleRank;
	}

	public Integer getNumOrderRank() {
		return numOrderRank;
	}

	public void setNumOrderRank(Integer numOrderRank) {
		this.numOrderRank = numOrderRank;
	}

	public Integer getNumReadRank() {
		return numReadRank;
	}

	public void setNumReadRank(Integer numReadRank) {
		this.numReadRank = numReadRank;
	}

	public Integer getRatioWorkRankYtd() {
		return ratioWorkRankYtd;
	}

	public void setRatioWorkRankYtd(Integer ratioWorkRankYtd) {
		this.ratioWorkRankYtd = ratioWorkRankYtd;
	}

	public Integer getNumPmRankYtd() {
		return numPmRankYtd;
	}

	public void setNumPmRankYtd(Integer numPmRankYtd) {
		this.numPmRankYtd = numPmRankYtd;
	}

	public Integer getNumPmAddRankYtd() {
		return numPmAddRankYtd;
	}

	public void setNumPmAddRankYtd(Integer numPmAddRankYtd) {
		this.numPmAddRankYtd = numPmAddRankYtd;
	}

	public Integer getNumSaleRankYtd() {
		return numSaleRankYtd;
	}

	public void setNumSaleRankYtd(Integer numSaleRankYtd) {
		this.numSaleRankYtd = numSaleRankYtd;
	}

	public Integer getNumOrderRankYtd() {
		return numOrderRankYtd;
	}

	public void setNumOrderRankYtd(Integer numOrderRankYtd) {
		this.numOrderRankYtd = numOrderRankYtd;
	}

	public Integer getNumReadRankYtd() {
		return numReadRankYtd;
	}

	public void setNumReadRankYtd(Integer numReadRankYtd) {
		this.numReadRankYtd = numReadRankYtd;
	}

	public Date getDaySt() {
		return daySt;
	}

	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}

	public String getDimensionSt() {
		return dimensionSt;
	}

	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getNumVisit() {
		return numVisit;
	}

	public void setNumVisit(Long numVisit) {
		this.numVisit = numVisit;
	}

	public Long getNumVisitRank() {
		return numVisitRank;
	}

	public void setNumVisitRank(Long numVisitRank) {
		this.numVisitRank = numVisitRank;
	}

	public Long getNumVisitRankYtd() {
		return numVisitRankYtd;
	}

	public void setNumVisitRankYtd(Long numVisitRankYtd) {
		this.numVisitRankYtd = numVisitRankYtd;
	}

	@Override
	public String toString() {
		return "AddWorkRatioGm [code=" + code + ", merchantNo=" + merchantNo
				+ ", areaCode=" + areaCode + ", areaName=" + areaName
				+ ", shopNo=" + shopNo + ", shopName=" + shopName
				+ ", memberNoGm=" + memberNoGm + ", memberNameGm="
				+ memberNameGm + ", headAddress=" + headAddress
				+ ", ratioWork=" + ratioWork + ", numBeatGm=" + numBeatGm
				+ ", numPm=" + numPm + ", numPmAdd=" + numPmAdd
				+ ", numPmIntention=" + numPmIntention + ", numPmAbandon="
				+ numPmAbandon + ", numSale=" + numSale + ", numOrder="
				+ numOrder + ", numPmOrder=" + numPmOrder + ", numRead="
				+ numRead + ", numSocial=" + numSocial + ", numPmCf=" + numPmCf
				+ ", numPmCfDay=" + numPmCfDay + ", numCfDay=" + numCfDay
				+ ", numPmKeep=" + numPmKeep + ", ratioWorkRank="
				+ ratioWorkRank + ", numPmRank=" + numPmRank
				+ ", numPmAddRank=" + numPmAddRank + ", numSaleRank="
				+ numSaleRank + ", numOrderRank=" + numOrderRank
				+ ", numReadRank=" + numReadRank + ", ratioWorkRankYtd="
				+ ratioWorkRankYtd + ", numPmRankYtd=" + numPmRankYtd
				+ ", numPmAddRankYtd=" + numPmAddRankYtd + ", numSaleRankYtd="
				+ numSaleRankYtd + ", numOrderRankYtd=" + numOrderRankYtd
				+ ", numReadRankYtd=" + numReadRankYtd + ", daySt=" + daySt
				+ ", dimensionSt=" + dimensionSt + ", createDate=" + createDate
				+ ", numVisit=" + numVisit + ", numVisitRank=" + numVisitRank
				+ ", numVisitRankYtd=" + numVisitRankYtd + "]";
	}
}
