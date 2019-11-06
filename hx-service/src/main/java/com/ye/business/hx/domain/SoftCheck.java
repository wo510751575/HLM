package com.ye.business.hx.domain;

import java.util.Date;

public class SoftCheck {
    /**
     *  .
     */
    private String code;

    /**
     * 上颌骨：正常，稍前突，前突，稍后缩，后缩 .
     */
    private String maxilla;

    /**
     * 下颌骨：正常，稍前突，前突，稍后缩，后缩 .
     */
    private String mandible;

    /**
     * 下颌角：偏大，偏小，相对正常 .
     */
    private String mandibularAngle;

    /**
     * 舌体：偏大，偏小，相对正常 .
     */
    private String tongueBody;

    /**
     * 上唇：正常，过短，功能过度，功能不足 .
     */
    private String upperLip;

    /**
     *  .
     */
    private String lowerLip;

    /**
     * 舌系带 .
     */
    private String lingualFrenulum;

    /**
     * 唇系带 .
     */
    private String labialFrenulum;

    /**
     * 扁桃体 .
     */
    private String tonsil;

    /**
     * 腺样体 .
     */
    private String adenoid;

    /**
     * 腭盖 .
     */
    private String palatalLid;

    /**
     * 疼痛左：有，无 .
     */
    private String painLeft;

    /**
     * 疼痛右：有，无 .
     */
    private String painRight;

    /**
     * 弹响左：有，无 .
     */
    private String bangLeft;

    /**
     * 弹响右：有，无 .
     */
    private String bangRight;

    /**
     * 张口：正常，右偏，左偏 .
     */
    private String openMouth;

    /**
     * 闭口：正常，右偏，左偏 .
     */
    private String closeMouth;

    /**
     * 开口度 .
     */
    private String aperture;

    /**
     * 呼吸道:扁桃体肥大，腺样体肥大，慢性咽喉炎，鼻甲肥大，鼻中隔弯曲，鼻肿瘤，鼻息肉 .
     */
    private String respiratoryTract;

    /**
     * 程度:Ⅰ，Ⅱ，Ⅲ .
     */
    private String degree;

    /**
     *  .
     */
    private String other;

    /**
     * 患者编号 .
     */
    private String patientNo;

    /**
     * 创建日期 .
     */
    private Date createDate;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     *  .
     */
    private String remark;

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
     * 上颌骨：正常，稍前突，前突，稍后缩，后缩 .
     *
     */
    public String getMaxilla() {
        return maxilla;
    }

    /**
     * 上颌骨：正常，稍前突，前突，稍后缩，后缩 .
     *
     */
    public void setMaxilla(String maxilla) {
        this.maxilla = maxilla == null ? null : maxilla.trim();
    }

    /**
     * 下颌骨：正常，稍前突，前突，稍后缩，后缩 .
     *
     */
    public String getMandible() {
        return mandible;
    }

    /**
     * 下颌骨：正常，稍前突，前突，稍后缩，后缩 .
     *
     */
    public void setMandible(String mandible) {
        this.mandible = mandible == null ? null : mandible.trim();
    }

    /**
     * 下颌角：偏大，偏小，相对正常 .
     *
     */
    public String getMandibularAngle() {
        return mandibularAngle;
    }

    /**
     * 下颌角：偏大，偏小，相对正常 .
     *
     */
    public void setMandibularAngle(String mandibularAngle) {
        this.mandibularAngle = mandibularAngle == null ? null : mandibularAngle.trim();
    }

    /**
     * 舌体：偏大，偏小，相对正常 .
     *
     */
    public String getTongueBody() {
        return tongueBody;
    }

    /**
     * 舌体：偏大，偏小，相对正常 .
     *
     */
    public void setTongueBody(String tongueBody) {
        this.tongueBody = tongueBody == null ? null : tongueBody.trim();
    }

    /**
     * 上唇：正常，过短，功能过度，功能不足 .
     *
     */
    public String getUpperLip() {
        return upperLip;
    }

    /**
     * 上唇：正常，过短，功能过度，功能不足 .
     *
     */
    public void setUpperLip(String upperLip) {
        this.upperLip = upperLip == null ? null : upperLip.trim();
    }

    /**
     *  .
     *
     */
    public String getLowerLip() {
        return lowerLip;
    }

    /**
     *  .
     *
     */
    public void setLowerLip(String lowerLip) {
        this.lowerLip = lowerLip == null ? null : lowerLip.trim();
    }

    /**
     * 舌系带 .
     *
     */
    public String getLingualFrenulum() {
        return lingualFrenulum;
    }

    /**
     * 舌系带 .
     *
     */
    public void setLingualFrenulum(String lingualFrenulum) {
        this.lingualFrenulum = lingualFrenulum == null ? null : lingualFrenulum.trim();
    }

    /**
     * 唇系带 .
     *
     */
    public String getLabialFrenulum() {
        return labialFrenulum;
    }

    /**
     * 唇系带 .
     *
     */
    public void setLabialFrenulum(String labialFrenulum) {
        this.labialFrenulum = labialFrenulum == null ? null : labialFrenulum.trim();
    }

    /**
     * 扁桃体 .
     *
     */
    public String getTonsil() {
        return tonsil;
    }

    /**
     * 扁桃体 .
     *
     */
    public void setTonsil(String tonsil) {
        this.tonsil = tonsil == null ? null : tonsil.trim();
    }

    /**
     * 腺样体 .
     *
     */
    public String getAdenoid() {
        return adenoid;
    }

    /**
     * 腺样体 .
     *
     */
    public void setAdenoid(String adenoid) {
        this.adenoid = adenoid == null ? null : adenoid.trim();
    }

    /**
     * 腭盖 .
     *
     */
    public String getPalatalLid() {
        return palatalLid;
    }

    /**
     * 腭盖 .
     *
     */
    public void setPalatalLid(String palatalLid) {
        this.palatalLid = palatalLid == null ? null : palatalLid.trim();
    }

    /**
     * 疼痛左：有，无 .
     *
     */
    public String getPainLeft() {
        return painLeft;
    }

    /**
     * 疼痛左：有，无 .
     *
     */
    public void setPainLeft(String painLeft) {
        this.painLeft = painLeft == null ? null : painLeft.trim();
    }

    /**
     * 疼痛右：有，无 .
     *
     */
    public String getPainRight() {
        return painRight;
    }

    /**
     * 疼痛右：有，无 .
     *
     */
    public void setPainRight(String painRight) {
        this.painRight = painRight == null ? null : painRight.trim();
    }

    /**
     * 弹响左：有，无 .
     *
     */
    public String getBangLeft() {
        return bangLeft;
    }

    /**
     * 弹响左：有，无 .
     *
     */
    public void setBangLeft(String bangLeft) {
        this.bangLeft = bangLeft == null ? null : bangLeft.trim();
    }

    /**
     * 弹响右：有，无 .
     *
     */
    public String getBangRight() {
        return bangRight;
    }

    /**
     * 弹响右：有，无 .
     *
     */
    public void setBangRight(String bangRight) {
        this.bangRight = bangRight == null ? null : bangRight.trim();
    }

    /**
     * 张口：正常，右偏，左偏 .
     *
     */
    public String getOpenMouth() {
        return openMouth;
    }

    /**
     * 张口：正常，右偏，左偏 .
     *
     */
    public void setOpenMouth(String openMouth) {
        this.openMouth = openMouth == null ? null : openMouth.trim();
    }

    /**
     * 闭口：正常，右偏，左偏 .
     *
     */
    public String getCloseMouth() {
        return closeMouth;
    }

    /**
     * 闭口：正常，右偏，左偏 .
     *
     */
    public void setCloseMouth(String closeMouth) {
        this.closeMouth = closeMouth == null ? null : closeMouth.trim();
    }

    /**
     * 开口度 .
     *
     */
    public String getAperture() {
        return aperture;
    }

    /**
     * 开口度 .
     *
     */
    public void setAperture(String aperture) {
        this.aperture = aperture == null ? null : aperture.trim();
    }

    /**
     * 呼吸道:扁桃体肥大，腺样体肥大，慢性咽喉炎，鼻甲肥大，鼻中隔弯曲，鼻肿瘤，鼻息肉 .
     *
     */
    public String getRespiratoryTract() {
        return respiratoryTract;
    }

    /**
     * 呼吸道:扁桃体肥大，腺样体肥大，慢性咽喉炎，鼻甲肥大，鼻中隔弯曲，鼻肿瘤，鼻息肉 .
     *
     */
    public void setRespiratoryTract(String respiratoryTract) {
        this.respiratoryTract = respiratoryTract == null ? null : respiratoryTract.trim();
    }

    /**
     * 程度:Ⅰ，Ⅱ，Ⅲ .
     *
     */
    public String getDegree() {
        return degree;
    }

    /**
     * 程度:Ⅰ，Ⅱ，Ⅲ .
     *
     */
    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    /**
     *  .
     *
     */
    public String getOther() {
        return other;
    }

    /**
     *  .
     *
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
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
        builder.append("SoftCheck [code=").append(code);
        builder.append(",maxilla=").append(maxilla); 
        builder.append(",mandible=").append(mandible); 
        builder.append(",mandibularAngle=").append(mandibularAngle); 
        builder.append(",tongueBody=").append(tongueBody); 
        builder.append(",upperLip=").append(upperLip); 
        builder.append(",lowerLip=").append(lowerLip); 
        builder.append(",lingualFrenulum=").append(lingualFrenulum); 
        builder.append(",labialFrenulum=").append(labialFrenulum); 
        builder.append(",tonsil=").append(tonsil); 
        builder.append(",adenoid=").append(adenoid); 
        builder.append(",palatalLid=").append(palatalLid); 
        builder.append(",painLeft=").append(painLeft); 
        builder.append(",painRight=").append(painRight); 
        builder.append(",bangLeft=").append(bangLeft); 
        builder.append(",bangRight=").append(bangRight); 
        builder.append(",openMouth=").append(openMouth); 
        builder.append(",closeMouth=").append(closeMouth); 
        builder.append(",aperture=").append(aperture); 
        builder.append(",respiratoryTract=").append(respiratoryTract); 
        builder.append(",degree=").append(degree); 
        builder.append(",other=").append(other); 
        builder.append(",patientNo=").append(patientNo); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",createId=").append(createId); 
        builder.append(",remark=").append(remark); 
        builder.append("]");
        return builder.toString();
    }
}