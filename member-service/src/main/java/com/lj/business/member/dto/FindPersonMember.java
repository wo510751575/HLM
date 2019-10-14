package com.lj.business.member.dto;

import java.io.Serializable;

import com.lj.base.core.util.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class FindPersonMember.
 */
public class FindPersonMember implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9076091602064733682L; 
	
	/** The code. */
	private String code;
	
	/** 客户号. */
	private String memberNo;
	
	/** 导购号. */
	private String memberNoGm;
	
	/** 商户号. */
	private String merchantNo;
	
    /** 区域CODE. */
    private String areaCode;
    
    /** 区域名称. */
    private String areaName;
    
    /**终端编号*/
    private String shopWx;
    
    /**客户类型*/
    private String pmTypeType;
    
    /**创建时间*/
    private String createDate;
    
    /**省code*/
    private String provinceCode;
    /**
     * 类型code
     */
    private String pmTypeCode;
    /**
     * 会员编号，多个用英文逗号,隔开
     */
    private String memberNos;
    
    /** 手机号 */
    private String mobile;
    
    /**
     * 是否群聊（默认false）
     */
    private boolean chatRoomFlag = false;
    public boolean isChatRoomFlag() {
		return chatRoomFlag;
    }    
    
    public String getShopWx() {
		return shopWx;
	}
	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}

	public String[] getMemberNoList() {
    	if(StringUtils.isNotEmpty(memberNos)) {
    		return memberNos.split(",");
    	}
    	return null;
    }
    
	public String getMemberNos() {
		return memberNos;
	}



	public void setMemberNos(String memberNos) {
		this.memberNos = memberNos;
	}



	public String getPmTypeCode() {
		return pmTypeCode;
	}

	public void setPmTypeCode(String pmTypeCode) {
		this.pmTypeCode = pmTypeCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * Gets the area code.
	 *
	 * @return the area code
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * Sets the area code.
	 *
	 * @param areaCode the area code
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * Gets the area name.
	 *
	 * @return the area name
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Sets the area name.
	 *
	 * @param areaName the area name
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the 客户号.
	 *
	 * @return the 客户号
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the 客户号.
	 *
	 * @param memberNo the new 客户号
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * Gets the 导购号.
	 *
	 * @return the 导购号
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购号.
	 *
	 * @param memberNoGm the new 导购号
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}
	
	/**
	 * Gets the merchant no.
	 *
	 * @return the merchant no
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the merchant no.
	 *
	 * @param merchantNo the merchant no
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getPmTypeType() {
		return pmTypeType;
	}

	public void setPmTypeType(String pmTypeType) {
		this.pmTypeType = pmTypeType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "FindPersonMember [code=" + code + ", memberNo=" + memberNo + ", memberNoGm=" + memberNoGm
				+ ", merchantNo=" + merchantNo + ", areaCode=" + areaCode + ", areaName=" + areaName + ", shopWx="
				+ shopWx + ", pmTypeType=" + pmTypeType + ", createDate=" + createDate + ", provinceCode="
				+ provinceCode + ", pmTypeCode=" + pmTypeCode + ", memberNos=" + memberNos + ", mobile=" + mobile + "]";
	}

}
