package com.lj.business.st.domain;

import java.io.Serializable;
import java.util.Date;

public class PmTypeTotal implements Serializable {

    private static final long serialVersionUID = 2107098670174957703L;

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
     * 区域CODE .
     */
    private String areaCode;
    
     /**区域名称*/
   private String areaName;
    /**
     * 省CODE .
     */
    private String provinceCode;

    /**
     * 市CODE .
     */
    private String cityCode;

    /**
     * 市区CODE .
     */
    private String cityAreaCode;

    /**
     * 客户分类CODE .
     */
    private String pmTypeCode;
    
    /**客户分类类型*/
   private  String pmTypeType;
   
    
    /**
     * 客户分类名称 .
     */
    private String typeName;

    /**
     * 客户数量 .
     */
    private Integer numPm;
    
    /**
     * 新增客户数
     */
   private Integer numAdd;

    /**
     * 客户占比 .
     */
    private Double ratioPm;

    /**
     * 统计日期 .
     */
    private Date daySt;

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
    
    

	public Integer getNumAdd() {
		return numAdd;
	}

	public void setNumAdd(Integer numAdd) {
		this.numAdd = numAdd;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPmTypeType() {
		return pmTypeType;
	}

	public void setPmTypeType(String pmTypeType) {
		this.pmTypeType = pmTypeType;
	}

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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityAreaCode() {
		return cityAreaCode;
	}

	public void setCityAreaCode(String cityAreaCode) {
		this.cityAreaCode = cityAreaCode;
	}

	public String getPmTypeCode() {
		return pmTypeCode;
	}

	public void setPmTypeCode(String pmTypeCode) {
		this.pmTypeCode = pmTypeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getNumPm() {
		return numPm;
	}

	public void setNumPm(Integer numPm) {
		this.numPm = numPm;
	}

	public Double getRatioPm() {
		return ratioPm;
	}

	public void setRatioPm(Double ratioPm) {
		this.ratioPm = ratioPm;
	}

	public Date getDaySt() {
		return daySt;
	}

	public void setDaySt(Date daySt) {
		this.daySt = daySt;
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
		return "PmTypeTotal [code=" + code + ", merchantNo=" + merchantNo
				+ ", shopNo=" + shopNo + ", shopName=" + shopName
				+ ", memberNoGm=" + memberNoGm + ", memberNameGm="
				+ memberNameGm + ", areaCode=" + areaCode + ", areaName="
				+ areaName + ", provinceCode=" + provinceCode + ", cityCode="
				+ cityCode + ", cityAreaCode=" + cityAreaCode + ", pmTypeCode="
				+ pmTypeCode + ", pmTypeType=" + pmTypeType + ", typeName="
				+ typeName + ", numPm=" + numPm + ", numAdd=" + numAdd
				+ ", ratioPm=" + ratioPm + ", daySt=" + daySt
				+ ", dimensionSt=" + dimensionSt + ", createDate=" + createDate
				+ "]";
	}
    
}