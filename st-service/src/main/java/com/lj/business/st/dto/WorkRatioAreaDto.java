package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

public class WorkRatioAreaDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4144643991129040608L;
	
	 /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String memberNoMerchant;

    /**
     * 区域CODE .
     */
    private String areaCode;

    /**
     * 区域名称 .
     */
    private String areaName;

    /**
     * 省CODE .
     */
    private String provinceCode;

    /**
     * 省名称 .
     */
    private String provinceName;

    /**
     * 门店数量 .
     */
    private Integer numShop;

    /**
     * 门店占比 .
     */
    private Double ratioShop;

    /**
     * 客户数量 .
     */
    private Long numPm;

    /**
     * 客户占比 .
     */
    private Double ratioPm;

    /**
     * 统计日期 .
     */
    private Date stDate;

    /**
     * 统计维度
             区域：AREA
             省：PROVINCE .
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

	public String getMemberNoMerchant() {
		return memberNoMerchant;
	}

	public void setMemberNoMerchant(String memberNoMerchant) {
		this.memberNoMerchant = memberNoMerchant;
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

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getNumShop() {
		return numShop;
	}

	public void setNumShop(Integer numShop) {
		this.numShop = numShop;
	}

	public Double getRatioShop() {
		return ratioShop;
	}

	public void setRatioShop(Double ratioShop) {
		this.ratioShop = ratioShop;
	}

	public Long getNumPm() {
		return numPm;
	}

	public void setNumPm(Long numPm) {
		this.numPm = numPm;
	}

	public Double getRatioPm() {
		return ratioPm;
	}

	public void setRatioPm(Double ratioPm) {
		this.ratioPm = ratioPm;
	}

	public Date getStDate() {
		return stDate;
	}

	public void setStDate(Date stDate) {
		this.stDate = stDate;
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
		builder.append("WorkRatioAreaDto [code=").append(code)
				.append(", memberNoMerchant=").append(memberNoMerchant)
				.append(", areaCode=").append(areaCode).append(", areaName=")
				.append(areaName).append(", provinceCode=")
				.append(provinceCode).append(", provinceName=")
				.append(provinceName).append(", numShop=").append(numShop)
				.append(", ratioShop=").append(ratioShop).append(", numPm=")
				.append(numPm).append(", ratioPm=").append(ratioPm)
				.append(", stDate=").append(stDate).append(", dimensionSt=")
				.append(dimensionSt).append(", createDate=").append(createDate)
				.append("]");
		return builder.toString();
	}
    
}
