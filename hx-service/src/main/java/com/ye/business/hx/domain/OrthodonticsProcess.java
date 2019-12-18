package com.ye.business.hx.domain;

import java.util.Date;

public class OrthodonticsProcess {
    /**
     *  .
     */
    private String code;

    /**
     * 类型:1-处置;2-检查;3-医嘱 .
     */
    private Integer type;

    /**
     * 牙位 .
     */
    private String toothPosition;

    /**
     * 说明 .
     */
    private String explain;

    /**
     * 患者编号 .
     */
    private String patientNo;

    /**
     * 经治医生 .
     */
    private String attendingDoctor;

    /**
     * 护士 .
     */
    private String nurse;

    /**
     * 创建日期 .
     */
    private Date createDate;
    
    
    /**
     *更新日期 .
     */
    private Date updateDate;

    /**
     *  .
     */
    private String remark;
    

    /**
     *  关联正畸过程记录表.
     */
    private String recordCode;
    
    
    

    /**  
	 * @Title:  getRecordCode <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getRecordCode() {
		return recordCode;
	}

	/**  
	 * @Title:  setRecordCode <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
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
     * 类型:1-处置;2-检查;3-医嘱 .
     *
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型:1-处置;2-检查;3-医嘱 .
     *
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 牙位 .
     *
     */
    public String getToothPosition() {
        return toothPosition;
    }

    /**
     * 牙位 .
     *
     */
    public void setToothPosition(String toothPosition) {
        this.toothPosition = toothPosition == null ? null : toothPosition.trim();
    }

    /**
     * 说明 .
     *
     */
    public String getExplain() {
        return explain;
    }

    /**
     * 说明 .
     *
     */
    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
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
     * 经治医生 .
     *
     */
    public String getAttendingDoctor() {
        return attendingDoctor;
    }

    /**
     * 经治医生 .
     *
     */
    public void setAttendingDoctor(String attendingDoctor) {
        this.attendingDoctor = attendingDoctor == null ? null : attendingDoctor.trim();
    }

    /**
     * 护士 .
     *
     */
    public String getNurse() {
        return nurse;
    }

    /**
     * 护士 .
     *
     */
    public void setNurse(String nurse) {
        this.nurse = nurse == null ? null : nurse.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OrthodonticsProcess [code=").append(code);
        builder.append(",type=").append(type); 
        builder.append(",toothPosition=").append(toothPosition); 
        builder.append(",explain=").append(explain); 
        builder.append(",patientNo=").append(patientNo); 
        builder.append(",attendingDoctor=").append(attendingDoctor); 
        builder.append(",nurse=").append(nurse); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append("]");
        return builder.toString();
    }
}