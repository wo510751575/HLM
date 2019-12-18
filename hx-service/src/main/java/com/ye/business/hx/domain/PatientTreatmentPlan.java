package com.ye.business.hx.domain;

import java.util.Date;

public class PatientTreatmentPlan {
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
     *  .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     *  .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 治疗步骤,逗号分割 .
     *
     */
    public String getStep() {
        return step;
    }

    /**
     * 治疗步骤,逗号分割 .
     *
     */
    public void setStep(String step) {
        this.step = step == null ? null : step.trim();
    }

    /**
     * 注意事项 .
     *
     */
    public String getAttention() {
        return attention;
    }

    /**
     * 注意事项 .
     *
     */
    public void setAttention(String attention) {
        this.attention = attention == null ? null : attention.trim();
    }

    /**
     * 其他说明 .
     *
     */
    public String getExplain() {
        return explain;
    }

    /**
     * 其他说明 .
     *
     */
    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }

    /**
     * 客户编号 .
     *
     */
    public String getPatientNo() {
        return patientNo;
    }

    /**
     * 客户编号 .
     *
     */
    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo == null ? null : patientNo.trim();
    }

    /**
     * 排序 .
     *
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序 .
     *
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 创建日期 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建日期 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 知情同意书表 .
     *
     */
    public String getInformedConsent() {
        return informedConsent;
    }

    /**
     * 知情同意书表 .
     *
     */
    public void setInformedConsent(String informedConsent) {
        this.informedConsent = informedConsent == null ? null : informedConsent.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PatientTreatmentPlan [code=").append(code);
        builder.append(",step=").append(step); 
        builder.append(",attention=").append(attention); 
        builder.append(",explain=").append(explain); 
        builder.append(",patientNo=").append(patientNo); 
        builder.append(",sort=").append(sort); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",informedConsent=").append(informedConsent); 
        builder.append("]");
        return builder.toString();
    }
}