package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;

public class ToothCheckDto implements Serializable { 

    /**
     * CODE .
     */
    private String code;

    /**
     * 牙位 .
     */
    private String dentalPosition;

    /**
     * 症状 .
     */
    private String symptom;

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
     * 创建时间 .
     */
    private Date createDate;
    /**
     * 创建时间 .
     */
    private Date startDate;
    /**
     * 创建时间 .
     */
    private Date endDate;
    
    /**
     * 创建时间-开始 .
     */
    private Date createDateStart;
    
    /**
     * 创建时间-结束 .
     */
    private Date createDateEnd;

    /**
     * 患者编号 .
     */
    private String patientNo;
    
    
    private PatientSymptomDto patientSymptom;
    
    

    /**  
	 * @Title:  getStartDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**  
	 * @Title:  setStartDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**  
	 * @Title:  getEndDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**  
	 * @Title:  setEndDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**  
	 * @Title:  getPatientSymptom <BR>  
	 * @Description: please write your description <BR>  
	 * @return: PatientSymptomDto <BR>  
	 */
	public PatientSymptomDto getPatientSymptom() {
		return patientSymptom;
	}

	/**  
	 * @Title:  setPatientSymptom <BR>  
	 * @Description: please write your description <BR>  
	 * @return: PatientSymptomDto <BR>  
	 */
	public void setPatientSymptom(PatientSymptomDto patientSymptom) {
		this.patientSymptom = patientSymptom;
	}

	/**  
	 * @Title:  getCreateDateStart <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public Date getCreateDateStart() {
		return createDateStart;
	}

	/**  
	 * @Title:  setCreateDateStart <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	/**  
	 * @Title:  getCreateDateEnd <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	/**  
	 * @Title:  setCreateDateEnd <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	/**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 牙位 .
     *
     */
    public String getDentalPosition() {
        return dentalPosition;
    }

    /**
     * 牙位 .
     *
     */
    public void setDentalPosition(String dentalPosition) {
        this.dentalPosition = dentalPosition == null ? null : dentalPosition.trim();
    }

    /**
     * 症状 .
     *
     */
    public String getSymptom() {
        return symptom;
    }

    /**
     * 症状 .
     *
     */
    public void setSymptom(String symptom) {
        this.symptom = symptom == null ? null : symptom.trim();
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
     * 患者编号 .
     *
     */
    public String getPatientNo() {
        return patientNo;
    }

    /**
     * 患者编号 .
     *
     */
    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo == null ? null : patientNo.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ToothCheck [code=").append(code);
        builder.append(",dentalPosition=").append(dentalPosition); 
        builder.append(",symptom=").append(symptom); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",patientNo=").append(patientNo); 
        builder.append("]");
        return builder.toString();
    }
}
