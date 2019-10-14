/**
 * 
 */
package com.lj.oms.entity.dto.hx;

import java.io.Serializable;

/**
 * 
 * 类说明：焕新店员。
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年3月11日
 */
public class HxGuidDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 商户编号 .
     */
    private String merchantNo;

	/**
	 * 店员编号 .
	 */
	private String memberNo;

	/**
	 * * 店员姓名 .
	 */
	private String memberName;

	/** 所属门店 */
	private String shopNo;

	/** 所属角色 */
	private String roleEnname;

	 

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getRoleEnname() {
		return roleEnname;
	}

	public void setRoleEnname(String roleEnname) {
		this.roleEnname = roleEnname;
	}

}
