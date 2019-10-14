package com.lj.business.st.dto.CfAnalyze;

import java.io.Serializable;

/**
 * The Class FindCfAnalyzeReturn.
 */
public class FindCfAnalyzeReturn implements Serializable { 

     /** Generate cron. */
	private static final long serialVersionUID = -710665982736089986L;

	
    /**
     * 项目类型
              .
     */
    private String typeList;

    /**
     * 图片地址 .
     */
    private String imgAddr;
    
    /**
     * 项目名称 .
     */
    private String nameList;
    
    /**
     * 摘要 .
     */
    private String brief;

	/**
	 * Gets the 项目类型 .
	 *
	 * @return the 项目类型 
	 */
	public String getTypeList() {
		return typeList;
	}

	/**
	 * Sets the 项目类型 .
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
	 * Gets the 摘要 .
	 *
	 * @return the 摘要 
	 */
	public String getBrief() {
		return brief;
	}

	/**
	 * Sets the 摘要 .
	 *
	 * @param brief the new 摘要 
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCfAnalyzeReturn [typeList=").append(typeList)
				.append(", imgAddr=").append(imgAddr).append(", nameList=")
				.append(nameList).append(", brief=").append(brief).append("]");
		return builder.toString();
	}
    
    
}
