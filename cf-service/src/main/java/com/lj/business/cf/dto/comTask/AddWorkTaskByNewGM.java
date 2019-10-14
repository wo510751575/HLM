package com.lj.business.cf.dto.comTask;

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：新增导购初始化工作任务统计表入参
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月18日
 */
public class AddWorkTaskByNewGM implements Serializable {

	private static final long serialVersionUID = 3340828748810386219L;

	/**
     * 商户编号,非空 .
     */
    private String merchantNo;

    /**
     * 分店编号,非空 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 导购编号,非空 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddWorkTaskByNewGM [merchantNo=");
		builder.append(merchantNo);
		builder.append(", shopNo=");
		builder.append(shopNo);
		builder.append(", shopName=");
		builder.append(shopName);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append("]");
		return builder.toString();
	}
	
}
