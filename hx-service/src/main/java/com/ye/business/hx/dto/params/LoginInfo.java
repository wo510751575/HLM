package com.ye.business.hx.dto.params;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * 登录信息
 * 
 * @author bobo
 *
 */
public class LoginInfo extends PageParamEntity {

	private String memberNoGuid;
	private String memberNameGuid;
	private String shopNo;
	private String shopName;
	private String memberNoMerchant;
	private String memberNameMerchant;

	public String getMemberNoGuid() {
		return memberNoGuid;
	}

	public void setMemberNoGuid(String memberNoGuid) {
		this.memberNoGuid = memberNoGuid;
	}

	public String getMemberNameGuid() {
		return memberNameGuid;
	}

	public void setMemberNameGuid(String memberNameGuid) {
		this.memberNameGuid = memberNameGuid;
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

	public String getMemberNoMerchant() {
		return memberNoMerchant;
	}

	public void setMemberNoMerchant(String memberNoMerchant) {
		this.memberNoMerchant = memberNoMerchant;
	}

	public String getMemberNameMerchant() {
		return memberNameMerchant;
	}

	public void setMemberNameMerchant(String memberNameMerchant) {
		this.memberNameMerchant = memberNameMerchant;
	}
}
