package com.lj.business.st.domain;

import java.io.Serializable;
import java.util.Date;

public class WorkRatioGm implements Serializable {

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

    /**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 区域CODE .
     *
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 区域CODE .
     *
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 区域名称 .
     *
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 区域名称 .
     *
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 分店编号 .
     *
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * 分店编号 .
     *
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    /**
     * 分店名称 .
     *
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 分店名称 .
     *
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * 导购编号  .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号  .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 头像地址 .
     *
     */
    public String getHeadAddress() {
        return headAddress;
    }

    /**
     * 头像地址 .
     *
     */
    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress == null ? null : headAddress.trim();
    }

    /**
     * 工作完成度 .
     *
     */
    public Double getRatioWork() {
        return ratioWork;
    }

    /**
     * 工作完成度 .
     *
     */
    public void setRatioWork(Double ratioWork) {
        this.ratioWork = ratioWork;
    }

    /**
     * 击败导购数量-全公司 .
     *
     */
    public Integer getNumBeatGm() {
        return numBeatGm;
    }

    /**
     * 击败导购数量-全公司 .
     *
     */
    public void setNumBeatGm(Integer numBeatGm) {
        this.numBeatGm = numBeatGm;
    }

    /**
     * 客户数量 .
     *
     */
    public Long getNumPm() {
        return numPm;
    }

    /**
     * 客户数量 .
     *
     */
    public void setNumPm(Long numPm) {
        this.numPm = numPm;
    }

    /**
     * 新增客户数量 .
     *
     */
    public Long getNumPmAdd() {
        return numPmAdd;
    }

    /**
     * 新增客户数量 .
     *
     */
    public void setNumPmAdd(Long numPmAdd) {
        this.numPmAdd = numPmAdd;
    }

    /**
     * 意向客户数量 .
     *
     */
    public Long getNumPmIntention() {
        return numPmIntention;
    }

    /**
     * 意向客户数量 .
     *
     */
    public void setNumPmIntention(Long numPmIntention) {
        this.numPmIntention = numPmIntention;
    }

    /**
     * 暂停跟进客户数量 .
     *
     */
    public Long getNumPmAbandon() {
        return numPmAbandon;
    }

    /**
     * 暂停跟进客户数量 .
     *
     */
    public void setNumPmAbandon(Long numPmAbandon) {
        this.numPmAbandon = numPmAbandon;
    }

    /**
     * 销售额 .
     *
     */
    public Long getNumSale() {
        return numSale;
    }

    /**
     * 销售额 .
     *
     */
    public void setNumSale(Long numSale) {
        this.numSale = numSale;
    }

    /**
     * 成单数量 .
     *
     */
    public Long getNumOrder() {
        return numOrder;
    }

    /**
     * 成单数量 .
     *
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
     * 图文被阅读数量 .
     *
     */
    public Long getNumRead() {
        return numRead;
    }

    /**
     * 图文被阅读数量 .
     *
     */
    public void setNumRead(Long numRead) {
        this.numRead = numRead;
    }

    /**
     * 社交数量 .
     *
     */
    public Long getNumSocial() {
        return numSocial;
    }

    /**
     * 社交数量 .
     *
     */
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

	/**
     * 工作完成度排名 .
     *
     */
    public Integer getRatioWorkRank() {
        return ratioWorkRank;
    }

    /**
     * 工作完成度排名 .
     *
     */
    public void setRatioWorkRank(Integer ratioWorkRank) {
        this.ratioWorkRank = ratioWorkRank;
    }

    /**
     * 客户数量排名 .
     *
     */
    public Integer getNumPmRank() {
        return numPmRank;
    }

    /**
     * 客户数量排名 .
     *
     */
    public void setNumPmRank(Integer numPmRank) {
        this.numPmRank = numPmRank;
    }

    /**
     * 新增客户数量排名 .
     *
     */
    public Integer getNumPmAddRank() {
        return numPmAddRank;
    }

    /**
     * 新增客户数量排名 .
     *
     */
    public void setNumPmAddRank(Integer numPmAddRank) {
        this.numPmAddRank = numPmAddRank;
    }

    /**
     * 销售额排名 .
     *
     */
    public Integer getNumSaleRank() {
        return numSaleRank;
    }

    /**
     * 销售额排名 .
     *
     */
    public void setNumSaleRank(Integer numSaleRank) {
        this.numSaleRank = numSaleRank;
    }

    /**
     * 成单数量排名 .
     *
     */
    public Integer getNumOrderRank() {
        return numOrderRank;
    }

    /**
     * 成单数量排名 .
     *
     */
    public void setNumOrderRank(Integer numOrderRank) {
        this.numOrderRank = numOrderRank;
    }

    /**
     * 图文被阅读数量排名 .
     *
     */
    public Integer getNumReadRank() {
        return numReadRank;
    }

    /**
     * 图文被阅读数量排名 .
     *
     */
    public void setNumReadRank(Integer numReadRank) {
        this.numReadRank = numReadRank;
    }

    /**
     * 工作完成度排名_昨日 .
     *
     */
    public Integer getRatioWorkRankYtd() {
        return ratioWorkRankYtd;
    }

    /**
     * 工作完成度排名_昨日 .
     *
     */
    public void setRatioWorkRankYtd(Integer ratioWorkRankYtd) {
        this.ratioWorkRankYtd = ratioWorkRankYtd;
    }

    /**
     * 客户数量排名_昨日 .
     *
     */
    public Integer getNumPmRankYtd() {
        return numPmRankYtd;
    }

    /**
     * 客户数量排名_昨日 .
     *
     */
    public void setNumPmRankYtd(Integer numPmRankYtd) {
        this.numPmRankYtd = numPmRankYtd;
    }

    /**
     * 新增客户数量排名 .
     *
     */
    public Integer getNumPmAddRankYtd() {
        return numPmAddRankYtd;
    }

    /**
     * 新增客户数量排名 .
     *
     */
    public void setNumPmAddRankYtd(Integer numPmAddRankYtd) {
        this.numPmAddRankYtd = numPmAddRankYtd;
    }

    /**
     * 销售额排名 .
     *
     */
    public Integer getNumSaleRankYtd() {
        return numSaleRankYtd;
    }

    /**
     * 销售额排名 .
     *
     */
    public void setNumSaleRankYtd(Integer numSaleRankYtd) {
        this.numSaleRankYtd = numSaleRankYtd;
    }

    /**
     * 成单数量排名 .
     *
     */
    public Integer getNumOrderRankYtd() {
        return numOrderRankYtd;
    }

    /**
     * 成单数量排名 .
     *
     */
    public void setNumOrderRankYtd(Integer numOrderRankYtd) {
        this.numOrderRankYtd = numOrderRankYtd;
    }

    /**
     * 图文被阅读数量排名 .
     *
     */
    public Integer getNumReadRankYtd() {
        return numReadRankYtd;
    }

    /**
     * 图文被阅读数量排名 .
     *
     */
    public void setNumReadRankYtd(Integer numReadRankYtd) {
        this.numReadRankYtd = numReadRankYtd;
    }

    /**
     * 统计日期 .
     *
     */
    public Date getDaySt() {
        return daySt;
    }

    /**
     * 统计日期 .
     *
     */
    public void setDaySt(Date daySt) {
        this.daySt = daySt;
    }

    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP
             导购：GUID .
     *
     */
    public String getDimensionSt() {
        return dimensionSt;
    }

    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP
             导购：GUID .
     *
     */
    public void setDimensionSt(String dimensionSt) {
        this.dimensionSt = dimensionSt == null ? null : dimensionSt.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
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
		return "WorkRatioGm [code=" + code + ", merchantNo=" + merchantNo
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