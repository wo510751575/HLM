package com.lj.business.st.dto.bestGmChoose;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindBestGmChooseReturn.
 */
public class FindBestGmChooseReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -2509133787521703656L;

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
     * 项目CODE .
     */
    private String codeList;

    /**
     * 项目名称 .
     */
    private String nameList;

    /**
     * 项目类型
             
              .
     */
    private String typeList;

    /**
     * 排序 .
     */
    private Integer seq;

    /**
     * 图片地址 .
     */
    private String imgAddr;

    /**
     * 创建时间 .
     */
    private Date createDate;

	/**
	 * Gets the cODE .
	 *
	 * @return the cODE 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the cODE .
	 *
	 * @param code the new cODE 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the 商户编号 .
	 *
	 * @return the 商户编号 
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号 .
	 *
	 * @param merchantNo the new 商户编号 
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the 分店编号 .
	 *
	 * @return the 分店编号 
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * Sets the 分店编号 .
	 *
	 * @param shopNo the new 分店编号 
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/**
	 * Gets the 分店名称 .
	 *
	 * @return the 分店名称 
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * Sets the 分店名称 .
	 *
	 * @param shopName the new 分店名称 
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * Gets the 导购编号  .
	 *
	 * @return the 导购编号  
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号  .
	 *
	 * @param memberNoGm the new 导购编号  
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the 导购姓名 .
	 *
	 * @return the 导购姓名 
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * Sets the 导购姓名 .
	 *
	 * @param memberNameGm the new 导购姓名 
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	/**
	 * Gets the 项目CODE .
	 *
	 * @return the 项目CODE 
	 */
	public String getCodeList() {
		return codeList;
	}

	/**
	 * Sets the 项目CODE .
	 *
	 * @param codeList the new 项目CODE 
	 */
	public void setCodeList(String codeList) {
		this.codeList = codeList;
	}

	/**
	 * Gets the 项目名称 .
	 *
	 * @return the 项目名称 
	 */
	public String getNameList() {
		return nameList;
	}

	/**
	 * Sets the 项目名称 .
	 *
	 * @param nameList the new 项目名称 
	 */
	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	/**
	 * Gets the 项目类型  .
	 *
	 * @return the 项目类型  
	 */
	public String getTypeList() {
		return typeList;
	}

	/**
	 * Sets the 项目类型  .
	 *
	 * @param typeList the new 项目类型  
	 */
	public void setTypeList(String typeList) {
		this.typeList = typeList;
	}

	/**
	 * Gets the 排序 .
	 *
	 * @return the 排序 
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * Sets the 排序 .
	 *
	 * @param seq the new 排序 
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/**
	 * Gets the 图片地址 .
	 *
	 * @return the 图片地址 
	 */
	public String getImgAddr() {
		return imgAddr;
	}

	/**
	 * Sets the 图片地址 .
	 *
	 * @param imgAddr the new 图片地址 
	 */
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	/**
	 * Gets the 创建时间 .
	 *
	 * @return the 创建时间 
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the 创建时间 .
	 *
	 * @param createDate the new 创建时间 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindBestGmChooseReturn [code=").append(code)
				.append(", merchantNo=").append(merchantNo).append(", shopNo=")
				.append(shopNo).append(", shopName=").append(shopName)
				.append(", memberNoGm=").append(memberNoGm)
				.append(", memberNameGm=").append(memberNameGm)
				.append(", codeList=").append(codeList).append(", nameList=")
				.append(nameList).append(", typeList=").append(typeList)
				.append(", seq=").append(seq).append(", imgAddr=")
				.append(imgAddr).append("]");
		return builder.toString();
	}
}
