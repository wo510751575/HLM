package com.ye.business.hx.domain;

import java.util.Date;

public class TreatmentPlan {
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
     * 方案名称 .
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 方案名称 .
     *
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 步骤 .
     *
     */
    public String getStep() {
        return step;
    }

    /**
     * 步骤 .
     *
     */
    public void setStep(String step) {
        this.step = step == null ? null : step.trim();
    }

    /**
     * 是否启用:1-启用;2-禁用 .
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 是否启用:1-启用;2-禁用 .
     *
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     *  .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     *  .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     *  .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     *  .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     *  .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     *  .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     *  .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     *  .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TreatmentPlan [code=").append(code);
        builder.append(",name=").append(name); 
        builder.append(",step=").append(step); 
        builder.append(",status=").append(status); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}