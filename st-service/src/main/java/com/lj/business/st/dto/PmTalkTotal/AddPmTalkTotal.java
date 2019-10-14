package com.lj.business.st.dto.PmTalkTotal;

import java.io.Serializable;
import java.util.Date;

public class AddPmTalkTotal implements Serializable { 

     /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = -5117653684463054925L;

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
     * 统计日期 .
     */
    private Date startDate;

    /**
     * 统计结束时间 .
     */
    private Date endDate;

    /**
     * 咨询数量 .
     */
    private Integer numTalk;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getNumTalk() {
		return numTalk;
	}

	public void setNumTalk(Integer numTalk) {
		this.numTalk = numTalk;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddPmTalkTotal [code=").append(code)
				.append(", merchantNo=").append(merchantNo)
				.append(", areaCode=").append(areaCode).append(", areaName=")
				.append(areaName).append(", shopNo=").append(shopNo)
				.append(", shopName=").append(shopName).append(", memberNoGm=")
				.append(memberNoGm).append(", memberNameGm=")
				.append(memberNameGm).append(", startDate=").append(startDate)
				.append(", endDate=").append(endDate).append(", numTalk=")
				.append(numTalk).append(", daySt=").append(daySt)
				.append(", dimensionSt=").append(dimensionSt)
				.append(", createDate=").append(createDate).append("]");
		return builder.toString();
	}
}
