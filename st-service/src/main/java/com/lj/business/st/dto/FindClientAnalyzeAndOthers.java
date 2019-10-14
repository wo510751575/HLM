package com.lj.business.st.dto;

import java.io.Serializable;

public class FindClientAnalyzeAndOthers implements Serializable {

    /**
     * Generate cron.
     *
     * @param
     * @param
     * @throws
     */
    private static final long serialVersionUID = -4122623716699263937L;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP
     */
    private String dimensionSt;

	/**
	 * 区域编码
	 */
	private String areaCode;

	/**
	 * 行业CODE
	 */
	private String codeLine;

	/**
	 * 兴趣CODE
	 */
	private String codeInterest;

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

	public String getDimensionSt() {
		return dimensionSt;
	}

	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCodeLine() {
		return codeLine;
	}

	public void setCodeLine(String codeLine) {
		this.codeLine = codeLine;
	}

	public String getCodeInterest() {
		return codeInterest;
	}

	public void setCodeInterest(String codeInterest) {
		this.codeInterest = codeInterest;
	}
}
