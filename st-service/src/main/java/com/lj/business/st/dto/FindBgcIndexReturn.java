package com.lj.business.st.dto;

import java.io.Serializable;

/**
 * The Class FindBgcIndexReturn.
 */
public class FindBgcIndexReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 871716326052010593L;
	

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
     * 图片地址 .
     */
    private String imgAddr;


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
	 * Gets the 项目类型.
	 *
	 * @return the 项目类型
	 */
	public String getTypeList() {
		return typeList;
	}


	/**
	 * Sets the 项目类型.
	 *
	 * @param typeList the new 项目类型
	 */
	public void setTypeList(String typeList) {
		this.typeList = typeList;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindBgcIndexReturn [codeList=").append(codeList)
				.append(", nameList=").append(nameList).append(", typeList=")
				.append(typeList).append(", imgAddr=").append(imgAddr)
				.append("]");
		return builder.toString();
	}

	
	

}
