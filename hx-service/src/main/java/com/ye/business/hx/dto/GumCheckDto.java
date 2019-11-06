package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;

public class GumCheckDto implements Serializable { 

    /**
     *  .
     */
    private String code;

    /**
     * 患者编号 .
     */
    private String patientNo;

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
     * 创建人 .
     */
    private String createId;

    /**
     * 坐标 .
     */
    private String position;

    /**
     * 菌斑-近:1-是;0-否 .
     */
    private Integer plaqueS;

    /**
     * 菌斑-中:1-是;0-否 .
     */
    private Integer plaqueM;

    /**
     * 菌斑-远:1-是;0-否 .
     */
    private Integer plaqueL;

    /**
     * 探测出血-近:1-是;0-否 .
     */
    private Integer bleedingS;

    /**
     * 探测出血-中:1-是;0-否 .
     */
    private Integer bleedingM;

    /**
     * 探测出血-远:1-是;0-否 .
     */
    private Integer bleedingL;

    /**
     * 探诊深度-近 .
     */
    private Integer pdS;

    /**
     * 探诊深度-中 .
     */
    private Integer pdM;

    /**
     * 探诊深度-远 .
     */
    private Integer pdL;

    /**
     * 龈缘-近 .
     */
    private Integer gmS;

    /**
     * 龈缘-中 .
     */
    private Integer gmM;

    /**
     * 龈缘-远 .
     */
    private Integer gmL;

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
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 坐标 .
     *
     */
    public String getPosition() {
        return position;
    }

    /**
     * 坐标 .
     *
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * 菌斑-近:1-是;0-否 .
     *
     */
    public Integer getPlaqueS() {
        return plaqueS;
    }

    /**
     * 菌斑-近:1-是;0-否 .
     *
     */
    public void setPlaqueS(Integer plaqueS) {
        this.plaqueS = plaqueS;
    }

    /**
     * 菌斑-中:1-是;0-否 .
     *
     */
    public Integer getPlaqueM() {
        return plaqueM;
    }

    /**
     * 菌斑-中:1-是;0-否 .
     *
     */
    public void setPlaqueM(Integer plaqueM) {
        this.plaqueM = plaqueM;
    }

    /**
     * 菌斑-远:1-是;0-否 .
     *
     */
    public Integer getPlaqueL() {
        return plaqueL;
    }

    /**
     * 菌斑-远:1-是;0-否 .
     *
     */
    public void setPlaqueL(Integer plaqueL) {
        this.plaqueL = plaqueL;
    }

    /**
     * 探测出血-近:1-是;0-否 .
     *
     */
    public Integer getBleedingS() {
        return bleedingS;
    }

    /**
     * 探测出血-近:1-是;0-否 .
     *
     */
    public void setBleedingS(Integer bleedingS) {
        this.bleedingS = bleedingS;
    }

    /**
     * 探测出血-中:1-是;0-否 .
     *
     */
    public Integer getBleedingM() {
        return bleedingM;
    }

    /**
     * 探测出血-中:1-是;0-否 .
     *
     */
    public void setBleedingM(Integer bleedingM) {
        this.bleedingM = bleedingM;
    }

    /**
     * 探测出血-远:1-是;0-否 .
     *
     */
    public Integer getBleedingL() {
        return bleedingL;
    }

    /**
     * 探测出血-远:1-是;0-否 .
     *
     */
    public void setBleedingL(Integer bleedingL) {
        this.bleedingL = bleedingL;
    }

    /**
     * 探诊深度-近 .
     *
     */
    public Integer getPdS() {
        return pdS;
    }

    /**
     * 探诊深度-近 .
     *
     */
    public void setPdS(Integer pdS) {
        this.pdS = pdS;
    }

    /**
     * 探诊深度-中 .
     *
     */
    public Integer getPdM() {
        return pdM;
    }

    /**
     * 探诊深度-中 .
     *
     */
    public void setPdM(Integer pdM) {
        this.pdM = pdM;
    }

    /**
     * 探诊深度-远 .
     *
     */
    public Integer getPdL() {
        return pdL;
    }

    /**
     * 探诊深度-远 .
     *
     */
    public void setPdL(Integer pdL) {
        this.pdL = pdL;
    }

    /**
     * 龈缘-近 .
     *
     */
    public Integer getGmS() {
        return gmS;
    }

    /**
     * 龈缘-近 .
     *
     */
    public void setGmS(Integer gmS) {
        this.gmS = gmS;
    }

    /**
     * 龈缘-中 .
     *
     */
    public Integer getGmM() {
        return gmM;
    }

    /**
     * 龈缘-中 .
     *
     */
    public void setGmM(Integer gmM) {
        this.gmM = gmM;
    }

    /**
     * 龈缘-远 .
     *
     */
    public Integer getGmL() {
        return gmL;
    }

    /**
     * 龈缘-远 .
     *
     */
    public void setGmL(Integer gmL) {
        this.gmL = gmL;
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
        builder.append("GumCheck [code=").append(code);
        builder.append(",patientNo=").append(patientNo); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",createId=").append(createId); 
        builder.append(",position=").append(position); 
        builder.append(",plaqueS=").append(plaqueS); 
        builder.append(",plaqueM=").append(plaqueM); 
        builder.append(",plaqueL=").append(plaqueL); 
        builder.append(",bleedingS=").append(bleedingS); 
        builder.append(",bleedingM=").append(bleedingM); 
        builder.append(",bleedingL=").append(bleedingL); 
        builder.append(",pdS=").append(pdS); 
        builder.append(",pdM=").append(pdM); 
        builder.append(",pdL=").append(pdL); 
        builder.append(",gmS=").append(gmS); 
        builder.append(",gmM=").append(gmM); 
        builder.append(",gmL=").append(gmL); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}
