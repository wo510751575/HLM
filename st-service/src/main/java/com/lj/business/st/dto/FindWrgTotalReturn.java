package com.lj.business.st.dto;

import java.io.Serializable;

/**
 * The Class FindWrgTotalReturn.
 */
public class FindWrgTotalReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 769046976288110418L;
	
	
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
     * 新增客户数量排名_昨日 .
     */
    private Integer numPmAddRankYtd;

    /**
     * 销售额排名_昨日 .
     */
    private Integer numSaleRankYtd;

    /**
     * 成单数量排名_昨日 .
     */
    private Integer numOrderRankYtd;

    /**
     * 图文被阅读数量排名_昨日 .
     */
    private Integer numReadRankYtd;

	/**
	 * @return the numPmOrder
	 */
	public Long getNumPmOrder() {
		return numPmOrder;
	}

	/**
	 * @param numPmOrder the numPmOrder to set
	 */
	public void setNumPmOrder(Long numPmOrder) {
		this.numPmOrder = numPmOrder;
	}

	/**
	 * Gets the 导购编号  .
	 *
	 * @return the 导购编号  
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号  .
	 *
	 * @param memberNoGm the new 导购编号  
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the 导购姓名 .
	 *
	 * @return the 导购姓名 
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * Sets the 导购姓名 .
	 *
	 * @param memberNameGm the new 导购姓名 
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	/**
	 * Gets the 头像地址 .
	 *
	 * @return the 头像地址 
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * Sets the 头像地址 .
	 *
	 * @param headAddress the new 头像地址 
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
	 * Gets the 工作完成度 .
	 *
	 * @return the 工作完成度 
	 */
	public Double getRatioWork() {
		return ratioWork;
	}

	/**
	 * Sets the 工作完成度 .
	 *
	 * @param ratioWork the new 工作完成度 
	 */
	public void setRatioWork(Double ratioWork) {
		this.ratioWork = ratioWork;
	}

	/**
	 * Gets the 击败导购数量-全公司 .
	 *
	 * @return the 击败导购数量-全公司 
	 */
	public Integer getNumBeatGm() {
		return numBeatGm;
	}

	/**
	 * Sets the 击败导购数量-全公司 .
	 *
	 * @param numBeatGm the new 击败导购数量-全公司 
	 */
	public void setNumBeatGm(Integer numBeatGm) {
		this.numBeatGm = numBeatGm;
	}

	/**
	 * Gets the 客户数量 .
	 *
	 * @return the 客户数量 
	 */
	public Long getNumPm() {
		return numPm;
	}

	/**
	 * Sets the 客户数量 .
	 *
	 * @param numPm the new 客户数量 
	 */
	public void setNumPm(Long numPm) {
		this.numPm = numPm;
	}

	/**
	 * Gets the 新增客户数量 .
	 *
	 * @return the 新增客户数量 
	 */
	public Long getNumPmAdd() {
		return numPmAdd;
	}

	/**
	 * Sets the 新增客户数量 .
	 *
	 * @param numPmAdd the new 新增客户数量 
	 */
	public void setNumPmAdd(Long numPmAdd) {
		this.numPmAdd = numPmAdd;
	}

	/**
	 * Gets the 意向客户数量 .
	 *
	 * @return the 意向客户数量 
	 */
	public Long getNumPmIntention() {
		return numPmIntention;
	}

	/**
	 * Sets the 意向客户数量 .
	 *
	 * @param numPmIntention the new 意向客户数量 
	 */
	public void setNumPmIntention(Long numPmIntention) {
		this.numPmIntention = numPmIntention;
	}

	/**
	 * Gets the 暂停跟进客户数量 .
	 *
	 * @return the 暂停跟进客户数量 
	 */
	public Long getNumPmAbandon() {
		return numPmAbandon;
	}

	/**
	 * Sets the 暂停跟进客户数量 .
	 *
	 * @param numPmAbandon the new 暂停跟进客户数量 
	 */
	public void setNumPmAbandon(Long numPmAbandon) {
		this.numPmAbandon = numPmAbandon;
	}

	/**
	 * Gets the 销售额 .
	 *
	 * @return the 销售额 
	 */
	public Long getNumSale() {
		return numSale;
	}

	/**
	 * Sets the 销售额 .
	 *
	 * @param numSale the new 销售额 
	 */
	public void setNumSale(Long numSale) {
		this.numSale = numSale;
	}

	/**
	 * Gets the 成单数量 .
	 *
	 * @return the 成单数量 
	 */
	public Long getNumOrder() {
		return numOrder;
	}

	/**
	 * Sets the 成单数量 .
	 *
	 * @param numOrder the new 成单数量 
	 */
	public void setNumOrder(Long numOrder) {
		this.numOrder = numOrder;
	}

	/**
	 * Gets the 图文被阅读数量 .
	 *
	 * @return the 图文被阅读数量 
	 */
	public Long getNumRead() {
		return numRead;
	}

	/**
	 * Sets the 图文被阅读数量 .
	 *
	 * @param numRead the new 图文被阅读数量 
	 */
	public void setNumRead(Long numRead) {
		this.numRead = numRead;
	}

	/**
	 * Gets the 社交数量 .
	 *
	 * @return the 社交数量 
	 */
	public Long getNumSocial() {
		return numSocial;
	}

	/**
	 * Sets the 社交数量 .
	 *
	 * @param numSocial the new 社交数量 
	 */
	public void setNumSocial(Long numSocial) {
		this.numSocial = numSocial;
	}

	/**
	 * Gets the 工作完成度排名 .
	 *
	 * @return the 工作完成度排名 
	 */
	public Integer getRatioWorkRank() {
		return ratioWorkRank;
	}

	/**
	 * Sets the 工作完成度排名 .
	 *
	 * @param ratioWorkRank the new 工作完成度排名 
	 */
	public void setRatioWorkRank(Integer ratioWorkRank) {
		this.ratioWorkRank = ratioWorkRank;
	}

	/**
	 * Gets the 客户数量排名 .
	 *
	 * @return the 客户数量排名 
	 */
	public Integer getNumPmRank() {
		return numPmRank;
	}

	/**
	 * Sets the 客户数量排名 .
	 *
	 * @param numPmRank the new 客户数量排名 
	 */
	public void setNumPmRank(Integer numPmRank) {
		this.numPmRank = numPmRank;
	}

	/**
	 * Gets the 新增客户数量排名 .
	 *
	 * @return the 新增客户数量排名 
	 */
	public Integer getNumPmAddRank() {
		return numPmAddRank;
	}

	/**
	 * Sets the 新增客户数量排名 .
	 *
	 * @param numPmAddRank the new 新增客户数量排名 
	 */
	public void setNumPmAddRank(Integer numPmAddRank) {
		this.numPmAddRank = numPmAddRank;
	}

	/**
	 * Gets the 销售额排名 .
	 *
	 * @return the 销售额排名 
	 */
	public Integer getNumSaleRank() {
		return numSaleRank;
	}

	/**
	 * Sets the 销售额排名 .
	 *
	 * @param numSaleRank the new 销售额排名 
	 */
	public void setNumSaleRank(Integer numSaleRank) {
		this.numSaleRank = numSaleRank;
	}

	/**
	 * Gets the 成单数量排名 .
	 *
	 * @return the 成单数量排名 
	 */
	public Integer getNumOrderRank() {
		return numOrderRank;
	}

	/**
	 * Sets the 成单数量排名 .
	 *
	 * @param numOrderRank the new 成单数量排名 
	 */
	public void setNumOrderRank(Integer numOrderRank) {
		this.numOrderRank = numOrderRank;
	}

	/**
	 * Gets the 图文被阅读数量排名 .
	 *
	 * @return the 图文被阅读数量排名 
	 */
	public Integer getNumReadRank() {
		return numReadRank;
	}

	/**
	 * Sets the 图文被阅读数量排名 .
	 *
	 * @param numReadRank the new 图文被阅读数量排名 
	 */
	public void setNumReadRank(Integer numReadRank) {
		this.numReadRank = numReadRank;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWrgTotalReturn [shopName=").append(shopName)
				.append(", memberNoGm=").append(memberNoGm)
				.append(", memberNameGm=").append(memberNameGm)
				.append(", headAddress=").append(headAddress)
				.append(", ratioWork=").append(ratioWork)
				.append(", numBeatGm=").append(numBeatGm).append(", numPm=")
				.append(numPm).append(", numPmAdd=").append(numPmAdd)
				.append(", numPmIntention=").append(numPmIntention)
				.append(", numPmAbandon=").append(numPmAbandon)
				.append(", numSale=").append(numSale).append(", numOrder=")
				.append(numOrder).append(", numRead=").append(numRead)
				.append(", numSocial=").append(numSocial)
				.append(", ratioWorkRank=").append(ratioWorkRank)
				.append(", numPmRank=").append(numPmRank)
				.append(", numPmAddRank=").append(numPmAddRank)
				.append(", numSaleRank=").append(numSaleRank)
				.append(", numOrderRank=").append(numOrderRank)
				.append(", numReadRank=").append(numReadRank)
				.append(", ratioWorkRankYtd=").append(ratioWorkRankYtd)
				.append(", numPmRankYtd=").append(numPmRankYtd)
				.append(", numPmAddRankYtd=").append(numPmAddRankYtd)
				.append(", numSaleRankYtd=").append(numSaleRankYtd)
				.append(", numOrderRankYtd=").append(numOrderRankYtd)
				.append(", numReadRankYtd=").append(numReadRankYtd).append("]");
		return builder.toString();
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
	 * Gets the 工作完成度排名_昨日 .
	 *
	 * @return the 工作完成度排名_昨日 
	 */
	public Integer getRatioWorkRankYtd() {
		return ratioWorkRankYtd;
	}

	/**
	 * Sets the 工作完成度排名_昨日 .
	 *
	 * @param ratioWorkRankYtd the new 工作完成度排名_昨日 
	 */
	public void setRatioWorkRankYtd(Integer ratioWorkRankYtd) {
		this.ratioWorkRankYtd = ratioWorkRankYtd;
	}

	/**
	 * Gets the 客户数量排名_昨日 .
	 *
	 * @return the 客户数量排名_昨日 
	 */
	public Integer getNumPmRankYtd() {
		return numPmRankYtd;
	}

	/**
	 * Sets the 客户数量排名_昨日 .
	 *
	 * @param numPmRankYtd the new 客户数量排名_昨日 
	 */
	public void setNumPmRankYtd(Integer numPmRankYtd) {
		this.numPmRankYtd = numPmRankYtd;
	}

	/**
	 * Gets the 新增客户数量排名 .
	 *
	 * @return the 新增客户数量排名 
	 */
	public Integer getNumPmAddRankYtd() {
		return numPmAddRankYtd;
	}

	/**
	 * Sets the 新增客户数量排名 .
	 *
	 * @param numPmAddRankYtd the new 新增客户数量排名 
	 */
	public void setNumPmAddRankYtd(Integer numPmAddRankYtd) {
		this.numPmAddRankYtd = numPmAddRankYtd;
	}

	/**
	 * Gets the 销售额排名 .
	 *
	 * @return the 销售额排名 
	 */
	public Integer getNumSaleRankYtd() {
		return numSaleRankYtd;
	}

	/**
	 * Sets the 销售额排名 .
	 *
	 * @param numSaleRankYtd the new 销售额排名 
	 */
	public void setNumSaleRankYtd(Integer numSaleRankYtd) {
		this.numSaleRankYtd = numSaleRankYtd;
	}

	/**
	 * Gets the 成单数量排名 .
	 *
	 * @return the 成单数量排名 
	 */
	public Integer getNumOrderRankYtd() {
		return numOrderRankYtd;
	}

	/**
	 * Sets the 成单数量排名 .
	 *
	 * @param numOrderRankYtd the new 成单数量排名 
	 */
	public void setNumOrderRankYtd(Integer numOrderRankYtd) {
		this.numOrderRankYtd = numOrderRankYtd;
	}

	/**
	 * Gets the 图文被阅读数量排名 .
	 *
	 * @return the 图文被阅读数量排名 
	 */
	public Integer getNumReadRankYtd() {
		return numReadRankYtd;
	}

	/**
	 * Sets the 图文被阅读数量排名 .
	 *
	 * @param numReadRankYtd the new 图文被阅读数量排名 
	 */
	public void setNumReadRankYtd(Integer numReadRankYtd) {
		this.numReadRankYtd = numReadRankYtd;
	}


}
