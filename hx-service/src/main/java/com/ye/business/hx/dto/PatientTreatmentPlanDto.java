package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PatientTreatmentPlanDto implements Serializable { 

    /**
     *  .
     */
    private String code;
    
    /**
     *  .
     */
    private String name;

    /**
     * 治疗步骤,逗号分割 .
     */
    private String step;

    /**
     * 注意事项 .
     */
    private String attention;

    /**
     * 其他说明 .
     */
    private String explain;

    /**
     * 客户编号 .
     */
    private String patientNo;

    /**
     * 排序 .
     */
    private Integer sort;

    /**
     * 创建日期 .
     */
    private Date createDate;

    /**
     * 知情同意书表 .
     */
    private String informedConsent;
    
    /**
     * 知情同意书表名称 .
     */
    private String informedName;
    
    private String list;
    
    /**
     * 治疗步骤 .
     */
   private List<OrthodonticsPlanDto> orthodonticsPlanDtoList;
   
   

	/**  
	 * @Title:  getInformedName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getInformedName() {
		return informedName;
	}

	/**  
	 * @Title:  setInformedName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setInformedName(String informedName) {
		this.informedName = informedName;
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
	 * @Title:  getAttention <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getAttention() {
		return attention;
	}

	/**  
	 * @Title:  setAttention <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setAttention(String attention) {
		this.attention = attention;
	}

	/**  
	 * @Title:  getExplain <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getExplain() {
		return explain;
	}

	/**  
	 * @Title:  setExplain <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setExplain(String explain) {
		this.explain = explain;
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
	 * @Title:  getInformedConsent <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getInformedConsent() {
		return informedConsent;
	}

	/**  
	 * @Title:  setInformedConsent <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setInformedConsent(String informedConsent) {
		this.informedConsent = informedConsent;
	}

	/**  
	 * @Title:  getList <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getList() {
		return list;
	}

	/**  
	 * @Title:  setList <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setList(String list) {
		this.list = list;
	}

	/**  
	 * @Title:  getOrthodonticsPlanDtoList <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<OrthodonticsPlanDto> <BR>  
	 */
	public List<OrthodonticsPlanDto> getOrthodonticsPlanDtoList() {
		return orthodonticsPlanDtoList;
	}

	/**  
	 * @Title:  setOrthodonticsPlanDtoList <BR>  
	 * @Description: please write your description <BR>  
	 * @return: List<OrthodonticsPlanDto> <BR>  
	 */
	public void setOrthodonticsPlanDtoList(List<OrthodonticsPlanDto> orthodonticsPlanDtoList) {
		this.orthodonticsPlanDtoList = orthodonticsPlanDtoList;
	}
   
   

   
}
