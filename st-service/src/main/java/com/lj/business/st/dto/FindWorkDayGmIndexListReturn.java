package com.lj.business.st.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindWorkDayGmIndexListReturn.
 */
public class FindWorkDayGmIndexListReturn implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -680713619547595084L;
	
	 /**
     * 项目CODE .
     */
    private String codeList;

    /**
     * 项目名称 .
     */
    private String nameList;

    /** 项目类型. */
    private String typeList;

    /**
     * 排序 .
     */
    private Integer seq;
    
    /** 图片地址. */
    private String imgAddr;
    
    /** 单位code. */
    private String unitList;
    
    /** 单位名称. */
    private String unitName;

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
	 * Gets the name list.
	 *
	 * @return the name list
	 */
	public String getNameList() {
		return nameList;
	}

	/**
	 * Sets the name list.
	 *
	 * @param nameList the new name list
	 */
	public void setNameList(String nameList) {
		this.nameList = nameList;
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

	/**
	 * Gets the seq.
	 *
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * Sets the seq.
	 *
	 * @param seq the new seq
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

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
	 * Gets the unit list.
	 *
	 * @return the unit list
	 */
	public String getUnitList() {
		return unitList;
	}

	/**
	 * Sets the unit list.
	 *
	 * @param unitList the new unit list
	 */
	public void setUnitList(String unitList) {
		this.unitList = unitList;
	}

	/**
	 * Gets the unit name.
	 *
	 * @return the unit name
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * Sets the unit name.
	 *
	 * @param unitName the new unit name
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWorkDayGmIndexListReturn [codeList=")
				.append(codeList).append(", nameList=").append(nameList)
				.append(", typeList=").append(typeList).append(", seq=")
				.append(seq).append(", imgAddr=").append(imgAddr)
				.append(", unitList=").append(unitList).append(", unitName=")
				.append(unitName).append("]");
		return builder.toString();
	}
    
}
