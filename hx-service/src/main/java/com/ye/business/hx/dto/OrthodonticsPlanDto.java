package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;

public class OrthodonticsPlanDto implements Serializable { 

    /**
     * CODE .
     */
    private String code;

    /**
     * 类型:1-主诉;2-问题;3-矫治目标;4-治疗步骤 .
     */
    private Integer type;
    
    /**
     * 排序.
     */
    private Integer sort;

    /**
     * 创建时间 .
     */
    private Date createDate;
    
    /**
     * 创建时间 .
     */
    private Date updateDate;

    /**
     *  .
     */
    private String remark;

    /**
     *  .
     */
    private String remark2;

    /**
     *  .
     */
    private String remark3;

    /**
     *  .
     */
    private String remark4;

    /**
     * 内容 .
     */
    private String name;
    
    /**
     * 客户编号 .
     */
    private String patientNo;
    
    /**
     * 上移/下移.
     */
    private boolean move;
    
    /**
     * 上移下移code.
     */
    private String nextCode;
    
    /**
     * 上移下移排序.
     */
    private Integer nextSort;

	/**  
	 * @Title:  getCode <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getCode() {
		return code;
	}

	/**  
	 * @Title:  setCode <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**  
	 * @Title:  getType <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public Integer getType() {
		return type;
	}

	/**  
	 * @Title:  setType <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**  
	 * @Title:  getSort <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public Integer getSort() {
		return sort;
	}

	/**  
	 * @Title:  setSort <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**  
	 * @Title:  getCreateDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**  
	 * @Title:  setCreateDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**  
	 * @Title:  getUpdateDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**  
	 * @Title:  setUpdateDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**  
	 * @Title:  getRemark <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getRemark() {
		return remark;
	}

	/**  
	 * @Title:  setRemark <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**  
	 * @Title:  getRemark2 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getRemark2() {
		return remark2;
	}

	/**  
	 * @Title:  setRemark2 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	/**  
	 * @Title:  getRemark3 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getRemark3() {
		return remark3;
	}

	/**  
	 * @Title:  setRemark3 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	/**  
	 * @Title:  getRemark4 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getRemark4() {
		return remark4;
	}

	/**  
	 * @Title:  setRemark4 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	/**  
	 * @Title:  getName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getName() {
		return name;
	}

	/**  
	 * @Title:  setName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * @Title:  getPatientNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getPatientNo() {
		return patientNo;
	}

	/**  
	 * @Title:  setPatientNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}


	/**  
	 * @Title:  isMove <BR>  
	 * @Description: please write your description <BR>  
	 * @return: boolean <BR>  
	 */
	public boolean isMove() {
		return move;
	}

	/**  
	 * @Title:  setMove <BR>  
	 * @Description: please write your description <BR>  
	 * @return: boolean <BR>  
	 */
	public void setMove(boolean move) {
		this.move = move;
	}

	/**  
	 * @Title:  getNextCode <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getNextCode() {
		return nextCode;
	}

	/**  
	 * @Title:  setNextCode <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setNextCode(String nextCode) {
		this.nextCode = nextCode;
	}

	/**  
	 * @Title:  getNextSort <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public Integer getNextSort() {
		return nextSort;
	}

	/**  
	 * @Title:  setNextSort <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public void setNextSort(Integer nextSort) {
		this.nextSort = nextSort;
	}
    
    
}
