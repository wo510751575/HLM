package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FindStListReturn.
 */
public class FindStListReturn implements Serializable { 

     /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = -1965119934532241546L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 项目名称 .
     */
    private String nameList;

    /**
     * 项目描述 .
     */
    private String desList;

    /**
     * 状态 
             启用：Y
             停用：N
              .
     */
    private String status;

    /**
     * 项目类型
             
              .
     */
    private String typeList;

    /**
     * 项目单位
             个：GE
             元：YUAN .
     */
    private String unitList;

    /**
     * 表类型
             日工作简报项目表：WORK_BR_DAY_LIST
              .
     */
    private String tableList;

    /**
     * 图片地址 .
     */
    private String imgAddr;

    /**
     * 创建时间 .
     */
    private Date createDate;

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
	 * @param code the code
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @param nameList the name list
	 */
	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	/**
	 * Gets the des list.
	 *
	 * @return the des list
	 */
	public String getDesList() {
		return desList;
	}

	/**
	 * Sets the des list.
	 *
	 * @param desList the des list
	 */
	public void setDesList(String desList) {
		this.desList = desList;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the status
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @param typeList the type list
	 */
	public void setTypeList(String typeList) {
		this.typeList = typeList;
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
	 * @param unitList the unit list
	 */
	public void setUnitList(String unitList) {
		this.unitList = unitList;
	}

	/**
	 * Gets the table list.
	 *
	 * @return the table list
	 */
	public String getTableList() {
		return tableList;
	}

	/**
	 * Sets the table list.
	 *
	 * @param tableList the table list
	 */
	public void setTableList(String tableList) {
		this.tableList = tableList;
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
	 * @param imgAddr the img addr
	 */
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	/**
	 * Gets the create date.
	 *
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the create date.
	 *
	 * @param createDate the creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FindStListReturn [code=" + code + ", nameList=" + nameList
				+ ", desList=" + desList + ", status=" + status + ", typeList="
				+ typeList + ", unitList=" + unitList + ", tableList="
				+ tableList + ", imgAddr=" + imgAddr + ", createDate="
				+ createDate + "]";
	}
    
    
}
