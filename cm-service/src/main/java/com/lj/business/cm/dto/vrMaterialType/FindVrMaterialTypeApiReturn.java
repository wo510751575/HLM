package com.lj.business.cm.dto.vrMaterialType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryReturn;

public class FindVrMaterialTypeApiReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -2311273238113970582L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 导购编号  .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 类型名称 .
     */
    private String typeName;

    /**
     * 类型总数量 .
     */
    private Integer typeCount;
    
    /**
     * 分类集合
     */
    private List<FindVrMaterialTypeCategoryReturn> list ;


    public List<FindVrMaterialTypeCategoryReturn> getList() {
		return list;
	}

	public void setList(List<FindVrMaterialTypeCategoryReturn> list) {
		this.list = list;
	}

	/**
     * 客户关注度 .
     */
    private Double customerAttentionRate;

    /**
     * 素材维度
             商户：MERCHANT
             终端：SHOP .
     */
    private String materialDimension;

    /**
     * 显示序号 .
     */
    private Integer showIndex;


  
    

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypeCount() {
		return typeCount;
	}

	public void setTypeCount(Integer typeCount) {
		this.typeCount = typeCount;
	}

	public Double getCustomerAttentionRate() {
		return customerAttentionRate;
	}

	public void setCustomerAttentionRate(Double customerAttentionRate) {
		this.customerAttentionRate = customerAttentionRate;
	}

	public String getMaterialDimension() {
		return materialDimension;
	}

	public void setMaterialDimension(String materialDimension) {
		this.materialDimension = materialDimension;
	}

	public Integer getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindVrMaterialTypeApiReturn  [code=").append(code)
		.append(", merchantNo=").append(merchantNo)
		.append(", memberNoGm=").append(memberNoGm)
		.append(", memberNameGm=").append(memberNameGm)
		.append(", typeName=").append(typeName)
		.append(", typeCount=").append(typeCount)
		.append(", list=").append(list)
		.append(", customerAttentionRate=").append(customerAttentionRate)
		.append(", materialDimension=").append(materialDimension)
		.append(", showIndex=").append(showIndex).append("]");
	return builder.toString();

	}
}
