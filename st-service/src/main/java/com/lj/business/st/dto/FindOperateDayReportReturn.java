package com.lj.business.st.dto;

import com.lj.base.core.util.EnumUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.business.st.emus.UnitType;

import java.io.Serializable;

// TODO: Auto-generated Javadoc

/**
 * The Class FindWorkDayGmIndexReturn.
 */
public class FindOperateDayReportReturn implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9211057806546811341L;
	
	/** 图片地址. */
	private String imgAddr;
	
	/** 类型code. */
	private String codeList;
	
	/** 类型名称. */
	private String codeName;
	
	/** 数量. */
	private Long num;
	
	/** 单位. */
	private String unitType;
	
	/** 单位中文. */
	private String unitTypeName;
	
	/** 项目类型. */
	private String typeList;

	private String nameList;

	private Integer seq;

	private String unitList;

	/**
	 * Gets the img addr.
	 *
	 * @return the img addr
	 */
	public String getImgAddr() {
		return imgAddr;
	}

	/**
	 * Sets the img addr.
	 *
	 * @param imgAddr the new img addr
	 */
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	/**
	 * Gets the code list.
	 *
	 * @return the code list
	 */
	public String getCodeList() {
		return codeList;
	}

	/**
	 * Sets the code list.
	 *
	 * @param codeList the new code list
	 */
	public void setCodeList(String codeList) {
		this.codeList = codeList;
	}

	/**
	 * Gets the code name.
	 *
	 * @return the code name
	 */
	public String getCodeName() {
		return codeName;
	}

	/**
	 * Sets the code name.
	 *
	 * @param codeName the new code name
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	/**
	 * Gets the num.
	 *
	 * @return the num
	 */
	public Long getNum() {
		return num;
	}

	/**
	 * Sets the num.
	 *
	 * @param num the new num
	 */
	public void setNum(Long num) {
		this.num = num;
	}

	/**
	 * Gets the unit type.
	 *
	 * @return the unit type
	 */
	public String getUnitType() {
		return unitType;
	}

	/**
	 * Sets the unit type.
	 *
	 * @param unitType the new unit type
	 */
	public void setUnitType(String unitType) {
		this.unitType = unitType;
		if(!StringUtils.isEmpty(this.unitType)){
			this.setUnitTypeName(EnumUtils.getValue(UnitType.class, this.unitType));
		}
	}

	/**
	 * Gets the unit type name.
	 *
	 * @return the unit type name
	 */
	public String getUnitTypeName() {
		return unitTypeName;
	}

	/**
	 * Sets the unit type name.
	 *
	 * @param unitTypeName the new unit type name
	 */
	public void setUnitTypeName(String unitTypeName) {
		this.unitTypeName = unitTypeName;
	}
	
	/**
	 * Gets the type list.
	 *
	 * @return the type list
	 */
	public String getTypeList() {
		return typeList;
	}

	/**
	 * Sets the type list.
	 *
	 * @param typeList the new type list
	 */
	public void setTypeList(String typeList) {
		this.typeList = typeList;
	}

	public String getNameList() {
		return nameList;
	}

	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getUnitList() {
		return unitList;
	}

	public void setUnitList(String unitList) {
		this.unitList = unitList;
	}

	/* (non-Javadoc)
                 * @see java.lang.Object#toString()
                 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWorkDayGmIndexReturn [imgAddr=").append(imgAddr)
				.append(", codeList=").append(codeList).append(", codeName=")
				.append(codeName).append(", num=").append(num)
				.append(", unitType=").append(unitType)
				.append(", unitTypeName=").append(unitTypeName)
				.append(", typeList=").append(typeList).append("]");
		return builder.toString();
	}

}
