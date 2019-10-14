package com.lj.business.st.dto.PmLabelTotal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AddPmLabelTotal implements Serializable { 

     /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = -9163783161902087415L;

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
     * 导购编号  .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 商户名称 .
     */
    private String labelId;

    /**
     * 商户名称 .
     */
    private String labelName;

    /**
     * 回应数量 .
     */
    private Integer pmNum;

    /**
     * 客户所占比例 .
     */
    private BigDecimal ratioPm;

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

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Integer getPmNum() {
		return pmNum;
	}

	public void setPmNum(Integer pmNum) {
		this.pmNum = pmNum;
	}

	public BigDecimal getRatioPm() {
		return ratioPm;
	}

	public void setRatioPm(BigDecimal ratioPm) {
		this.ratioPm = ratioPm;
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
		builder.append("AddPmLabelTotal [code=").append(code)
				.append(", merchantNo=").append(merchantNo).append(", shopNo=")
				.append(shopNo).append(", shopName=").append(shopName)
				.append(", memberNoGm=").append(memberNoGm)
				.append(", memberNameGm=").append(memberNameGm)
				.append(", labelId=").append(labelId).append(", labelName=")
				.append(labelName).append(", pmNum=").append(pmNum)
				.append(", ratioPm=").append(ratioPm).append(", dimensionSt=")
				.append(dimensionSt).append(", createDate=").append(createDate)
				.append("]");
		return builder.toString();
	}
}
