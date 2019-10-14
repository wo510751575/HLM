package com.lj.business.cm.dto.vrMaterialCommen;

import java.util.Arrays;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindVrMaterialCommenPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2945489099208247557L; 
	
	/**
	 * code
	 */
	private String code;
	/**
	 * 终端类型 (必填)
	 */
	private String shopType;
	/**
	 * 素材维度 
	 */
	private String dimensionSt;
	
	/**
	 * 商户编号(必填)
	 */
	private String merchantNo;
	
	/**
	 * 类型code
	 */
	private String materialTypeCode;
	/**
	 * 类型分类code
	 */
	private String typeCategoryCode;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 分类code集合
	 */
	private List<String> typeCodes;
	
	/**
	 *codes WEBIM使用参数
	 *
	 */
	private String codes;

	/**
	 * 显示渠道
	 */
	private String showChannel;
	
	/** 终端类型，即销售渠道. */
    private String[] shopTypes;
	
	public String[] getShopTypes() {
		return shopTypes;
	}

	public void setShopTypes(String[] shopTypes) {
		this.shopTypes = shopTypes;
	}

	public String getShowChannel() {
		return showChannel;
	}

	public void setShowChannel(String showChannel) {
		this.showChannel = showChannel;
	}


	public String getCodes() {
		return codes;
	}


	public void setCodes(String codes) {
		this.codes = codes;
	}


	public int getTypeCodesSize() {
		return this.typeCodes == null ? 0 : this.typeCodes.size();
	}
	
	

	public List<String> getTypeCodes() {
		return typeCodes;
	}


	public void setTypeCodes(List<String> typeCodes) {
		this.typeCodes = typeCodes;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getShopType() {
		return shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	public String getDimensionSt() {
		return dimensionSt;
	}
	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getMaterialTypeCode() {
		return materialTypeCode;
	}
	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}
	public String getTypeCategoryCode() {
		return typeCategoryCode;
	}
	public void setTypeCategoryCode(String typeCategoryCode) {
		this.typeCategoryCode = typeCategoryCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindVrMaterialCommenPage [code=");
		builder.append(code);
		builder.append(", shopType=");
		builder.append(shopType);
		builder.append(", dimensionSt=");
		builder.append(dimensionSt);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", materialTypeCode=");
		builder.append(materialTypeCode);
		builder.append(", typeCategoryCode=");
		builder.append(typeCategoryCode);
		builder.append(", title=");
		builder.append(title);
		builder.append(", typeCodes=");
		builder.append(typeCodes);
		builder.append(", codes=");
		builder.append(codes);
		builder.append(", showChannel=");
		builder.append(showChannel);
		builder.append(", shopTypes=");
		builder.append(Arrays.toString(shopTypes));
		builder.append("]");
		return builder.toString();
	}

}
