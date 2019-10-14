package com.lj.business.st.dto.PmLabelTotal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FindPmLabelTotal.
 */
public class FindPmLabelTotal implements Serializable { 
     
     /** Generate cron. */
	private static final long serialVersionUID = -5073636895292159139L;


    /**
     * 商户编号 .(必填)
     */
    private String merchantNo;

    /**
     * 分店编号 .(必填)
     */
    private String shopNo;
    
    /** 导购编号(必填). */
    private String memberNoGm;
    
	/**
	 * 统计维度 商户：MERCHANT 区域：AREA 门店：SHOP 导购：GUID .(必填)
	 */
	private String dimensionSt;

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

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getDimensionSt() {
		return dimensionSt;
	}

	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPmLabelTotal [merchantNo=").append(merchantNo)
				.append(", shopNo=").append(shopNo).append(", memberNoGm=")
				.append(memberNoGm).append(", dimensionSt=")
				.append(dimensionSt).append("]");
		return builder.toString();
	}
}
