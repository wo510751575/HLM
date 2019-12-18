package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;

public class TreatmentPlanDto implements Serializable { 

    /**
     *  .
     */
    private String code;

    /**
     * 方案名称 .
     */
    private String name;

    /**
     * 步骤 .
     */
    private String step;
    /**
   
    /**
     * 是否启用:1-启用;2-禁用 .
     */
    private Integer status;

    /**
     * 创建时间 .
     */
    private Date createDate;

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
     *  .
     */
    private String patientNo;

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
	 * @Title:  getStep <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getStep() {
		return step;
	}

	/**  
	 * @Title:  setStep <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setStep(String step) {
		this.step = step;
	}

	/**  
	 * @Title:  getStatus <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public Integer getStatus() {
		return status;
	}

	/**  
	 * @Title:  setStatus <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
    

}
