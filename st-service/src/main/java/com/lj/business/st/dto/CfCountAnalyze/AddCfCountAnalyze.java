package com.lj.business.st.dto.CfCountAnalyze;

import java.io.Serializable;
import java.util.Date;

public class AddCfCountAnalyze implements Serializable { 

     /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = -4147475313808100412L;

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
     * 统计日期 .
     */
    private Date stDate;

    /**
     * 总跟踪次数 .
     */
    private Long numCf;

    /**
     * 1-5次跟踪占比 .
     */
    private Double ratioCfFive;

    /**
     * 1-5次跟踪数量 .
     */
    private Integer numCfFive;

    /**
     * 6-10次跟踪占比 .
     */
    private Double ratioCfTen;

    /**
     * 6-10次跟踪数量 .
     */
    private Integer numCfTen;

    /**
     * 10-15次跟踪占比 .
     */
    private Double ratioCfFifteen;

    /**
     * 10-15次跟踪数量 .
     */
    private Integer numCfFifteen;

    /**
     * 16次以上跟踪占比 .
     */
    private Double ratioCfSixteen;

    /**
     * 16次跟踪数量 .
     */
    private Integer numCfSixteen;

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

	public Date getStDate() {
		return stDate;
	}

	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}

	public Long getNumCf() {
		return numCf;
	}

	public void setNumCf(Long numCf) {
		this.numCf = numCf;
	}

	public Double getRatioCfFive() {
		return ratioCfFive;
	}

	public void setRatioCfFive(Double ratioCfFive) {
		this.ratioCfFive = ratioCfFive;
	}

	public Integer getNumCfFive() {
		return numCfFive;
	}

	public void setNumCfFive(Integer numCfFive) {
		this.numCfFive = numCfFive;
	}

	public Double getRatioCfTen() {
		return ratioCfTen;
	}

	public void setRatioCfTen(Double ratioCfTen) {
		this.ratioCfTen = ratioCfTen;
	}

	public Integer getNumCfTen() {
		return numCfTen;
	}

	public void setNumCfTen(Integer numCfTen) {
		this.numCfTen = numCfTen;
	}

	public Double getRatioCfFifteen() {
		return ratioCfFifteen;
	}

	public void setRatioCfFifteen(Double ratioCfFifteen) {
		this.ratioCfFifteen = ratioCfFifteen;
	}

	public Integer getNumCfFifteen() {
		return numCfFifteen;
	}

	public void setNumCfFifteen(Integer numCfFifteen) {
		this.numCfFifteen = numCfFifteen;
	}

	public Double getRatioCfSixteen() {
		return ratioCfSixteen;
	}

	public void setRatioCfSixteen(Double ratioCfSixteen) {
		this.ratioCfSixteen = ratioCfSixteen;
	}

	public Integer getNumCfSixteen() {
		return numCfSixteen;
	}

	public void setNumCfSixteen(Integer numCfSixteen) {
		this.numCfSixteen = numCfSixteen;
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
		builder.append("AddCfCountAnalyze [code=").append(code)
				.append(", merchantNo=").append(merchantNo)
				.append(", areaCode=").append(areaCode).append(", areaName=")
				.append(areaName).append(", shopNo=").append(shopNo)
				.append(", shopName=").append(shopName).append(", stDate=")
				.append(stDate).append(", numCf=").append(numCf)
				.append(", ratioCfFive=").append(ratioCfFive)
				.append(", numCfFive=").append(numCfFive)
				.append(", ratioCfTen=").append(ratioCfTen)
				.append(", numCfTen=").append(numCfTen)
				.append(", ratioCfFifteen=").append(ratioCfFifteen)
				.append(", numCfFifteen=").append(numCfFifteen)
				.append(", ratioCfSixteen=").append(ratioCfSixteen)
				.append(", numCfSixteen=").append(numCfSixteen)
				.append(", createDate=").append(createDate).append("]");
		return builder.toString();
	}
}
