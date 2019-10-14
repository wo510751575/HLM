package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;

public class PatientMedicalTemplateDto implements Serializable { 

    /**
     * CODE .
     */
    private String code;

    /**
     * 模版名称 .
     */
    private String name;

    /**
     * 主诉及病史：主诉 .
     */
    private String mainRemark;

    /**
     * 主诉及病史：现病史 .
     */
    private String mainCurrentRemark;

    /**
     * 主诉及病史：既往史 .
     */
    private String mainPastRemark;

    /**
     * 口腔检查：口腔检查 .
     */
    private String checkOralRemark;

    /**
     * 口腔检查：辅助检查 .
     */
    private String checkAuxiliaryRemark;

    /**
     * 诊断与治疗计划：诊断 .
     */
    private String planDiagnosisRemark;

    /**
     * 诊断与治疗计划：治疗计划 .
     */
    private String planTreatmentRemark;

    /**
     * 处置与医嘱：处置 .
     */
    private String dmDisposalRemark;

    /**
     * 处置与医嘱：医嘱 .
     */
    private String dmMedicalRemark;

    /**
     * 其他：标签 .
     */
    private String otherLabelRemark;

    /**
     * 其他：备注 .
     */
    private String otherRemark;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建人 .
     */
    private String createName;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark2;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark4;

    /**
     * 更新人 .
     */
    private String updateId;

    /**
     * 更新人 .
     */
    private String updateName;

    /**
     * 更新时间 .
     */
    private Date updateDate;
    /**
     * 模版目录Code
     */
    private String listCode;
    
    public String getListCode() {
		return listCode;
	}

	public void setListCode(String listCode) {
		this.listCode = listCode;
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
     * 模版名称 .
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 模版名称 .
     *
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 主诉及病史：主诉 .
     *
     */
    public String getMainRemark() {
        return mainRemark;
    }

    /**
     * 主诉及病史：主诉 .
     *
     */
    public void setMainRemark(String mainRemark) {
        this.mainRemark = mainRemark == null ? null : mainRemark.trim();
    }

    /**
     * 主诉及病史：现病史 .
     *
     */
    public String getMainCurrentRemark() {
        return mainCurrentRemark;
    }

    /**
     * 主诉及病史：现病史 .
     *
     */
    public void setMainCurrentRemark(String mainCurrentRemark) {
        this.mainCurrentRemark = mainCurrentRemark == null ? null : mainCurrentRemark.trim();
    }

    /**
     * 主诉及病史：既往史 .
     *
     */
    public String getMainPastRemark() {
        return mainPastRemark;
    }

    /**
     * 主诉及病史：既往史 .
     *
     */
    public void setMainPastRemark(String mainPastRemark) {
        this.mainPastRemark = mainPastRemark == null ? null : mainPastRemark.trim();
    }

    /**
     * 口腔检查：口腔检查 .
     *
     */
    public String getCheckOralRemark() {
        return checkOralRemark;
    }

    /**
     * 口腔检查：口腔检查 .
     *
     */
    public void setCheckOralRemark(String checkOralRemark) {
        this.checkOralRemark = checkOralRemark == null ? null : checkOralRemark.trim();
    }

    /**
     * 口腔检查：辅助检查 .
     *
     */
    public String getCheckAuxiliaryRemark() {
        return checkAuxiliaryRemark;
    }

    /**
     * 口腔检查：辅助检查 .
     *
     */
    public void setCheckAuxiliaryRemark(String checkAuxiliaryRemark) {
        this.checkAuxiliaryRemark = checkAuxiliaryRemark == null ? null : checkAuxiliaryRemark.trim();
    }

    /**
     * 诊断与治疗计划：诊断 .
     *
     */
    public String getPlanDiagnosisRemark() {
        return planDiagnosisRemark;
    }

    /**
     * 诊断与治疗计划：诊断 .
     *
     */
    public void setPlanDiagnosisRemark(String planDiagnosisRemark) {
        this.planDiagnosisRemark = planDiagnosisRemark == null ? null : planDiagnosisRemark.trim();
    }

    /**
     * 诊断与治疗计划：治疗计划 .
     *
     */
    public String getPlanTreatmentRemark() {
        return planTreatmentRemark;
    }

    /**
     * 诊断与治疗计划：治疗计划 .
     *
     */
    public void setPlanTreatmentRemark(String planTreatmentRemark) {
        this.planTreatmentRemark = planTreatmentRemark == null ? null : planTreatmentRemark.trim();
    }

    /**
     * 处置与医嘱：处置 .
     *
     */
    public String getDmDisposalRemark() {
        return dmDisposalRemark;
    }

    /**
     * 处置与医嘱：处置 .
     *
     */
    public void setDmDisposalRemark(String dmDisposalRemark) {
        this.dmDisposalRemark = dmDisposalRemark == null ? null : dmDisposalRemark.trim();
    }

    /**
     * 处置与医嘱：医嘱 .
     *
     */
    public String getDmMedicalRemark() {
        return dmMedicalRemark;
    }

    /**
     * 处置与医嘱：医嘱 .
     *
     */
    public void setDmMedicalRemark(String dmMedicalRemark) {
        this.dmMedicalRemark = dmMedicalRemark == null ? null : dmMedicalRemark.trim();
    }

    /**
     * 其他：标签 .
     *
     */
    public String getOtherLabelRemark() {
        return otherLabelRemark;
    }

    /**
     * 其他：标签 .
     *
     */
    public void setOtherLabelRemark(String otherLabelRemark) {
        this.otherLabelRemark = otherLabelRemark == null ? null : otherLabelRemark.trim();
    }

    /**
     * 其他：备注 .
     *
     */
    public String getOtherRemark() {
        return otherRemark;
    }

    /**
     * 其他：备注 .
     *
     */
    public void setOtherRemark(String otherRemark) {
        this.otherRemark = otherRemark == null ? null : otherRemark.trim();
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
     * 创建人 .
     *
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    /**
     * 更新人 .
     *
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 更新人 .
     *
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * 更新人 .
     *
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * 更新人 .
     *
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    /**
     * 更新时间 .
     *
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间 .
     *
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

	@Override
	public String toString() {
		return "PatientMedicalTemplateDto [code=" + code + ", name=" + name + ", mainRemark=" + mainRemark
				+ ", mainCurrentRemark=" + mainCurrentRemark + ", mainPastRemark=" + mainPastRemark
				+ ", checkOralRemark=" + checkOralRemark + ", checkAuxiliaryRemark=" + checkAuxiliaryRemark
				+ ", planDiagnosisRemark=" + planDiagnosisRemark + ", planTreatmentRemark=" + planTreatmentRemark
				+ ", dmDisposalRemark=" + dmDisposalRemark + ", dmMedicalRemark=" + dmMedicalRemark
				+ ", otherLabelRemark=" + otherLabelRemark + ", otherRemark=" + otherRemark + ", createDate="
				+ createDate + ", createId=" + createId + ", createName=" + createName + ", remark=" + remark
				+ ", remark2=" + remark2 + ", remark3=" + remark3 + ", remark4=" + remark4 + ", updateId=" + updateId
				+ ", updateName=" + updateName + ", updateDate=" + updateDate + ", listCode=" + listCode + "]";
	}

}
