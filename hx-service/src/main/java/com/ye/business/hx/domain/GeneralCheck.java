package com.ye.business.hx.domain;

import java.util.Date;

public class GeneralCheck {
    /**
     *  .
     */
    private String code;

    /**
     * 主诉 .
     */
    private String chiefComplaint;

    /**
     * 不良习惯:咬上唇，咬下唇，覆盖下唇，吮指，咬手指，咬物，口呼吸 .
     */
    private String badHabits;

    /**
     * 口腔卫生：好，中，差 .
     */
    private String oralHygiene;

    /**
     * 颈椎：不详，S1，S2，S3，S4，S5，S6 .
     */
    private String cervicalVertebra;

    /**
     * 手腕骨：F期，FG期，G期，H期，I期，不详 .
     */
    private String wristBone;

    /**
     * 月经：有，无，不详 .
     */
    private String menstruation;

    /**
     * 初潮 .
     */
    private String menarche;

    /**
     * 矫正史：有，无 .
     */
    private String correctiveHistory;

    /**
     * 经治医生 .
     */
    private String attendingDoctor;

    /**
     * 拔牙情况 .
     */
    private String toothExtraction;

    /**
     * 乳牙龋齿：有，无，不详 .
     */
    private String dentalCaries;

    /**
     * 乳牙早失：有，无，不详 .
     */
    private String earlyLoss;

    /**
     * 乳牙错颌：无，前牙反颌，下颌后缩，下颌后斜，不详 .
     */
    private String malocclusion;

    /**
     * 替牙龋齿：有，无，不详 .
     */
    private String dentalCariesReplace;

    /**
     * 替牙错颌：无，前牙反颌，下颌后缩，下颌后斜，不详 .
     */
    private String earlyLossReplace;

    /**
     * 替牙错颌：无，前牙反颌，下颌后缩，下颌后斜，不详 .
     */
    private String malocclusionReplace;

    /**
     * 唇裂：无、单侧完全裂，单侧不完全裂，单侧混合裂，双侧完全裂，双侧不完全裂，双侧混合裂 .
     */
    private String cleftLip;

    /**
     * 腭裂：无、软腭裂，不完全裂，单侧完全腭裂，双侧完全腭裂 .
     */
    private String cleftPalate;

    /**
     * 其他情况 .
     */
    private String others;

    /**
     * 患者编号 .
     */
    private String patientNo;

    /**
     *  .
     */
    private String remark;

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
     * 主诉 .
     *
     */
    public String getChiefComplaint() {
        return chiefComplaint;
    }

    /**
     * 主诉 .
     *
     */
    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint == null ? null : chiefComplaint.trim();
    }

    /**
     * 不良习惯:咬上唇，咬下唇，覆盖下唇，吮指，咬手指，咬物，口呼吸 .
     *
     */
    public String getBadHabits() {
        return badHabits;
    }

    /**
     * 不良习惯:咬上唇，咬下唇，覆盖下唇，吮指，咬手指，咬物，口呼吸 .
     *
     */
    public void setBadHabits(String badHabits) {
        this.badHabits = badHabits == null ? null : badHabits.trim();
    }

    /**
     * 口腔卫生：好，中，差 .
     *
     */
    public String getOralHygiene() {
        return oralHygiene;
    }

    /**
     * 口腔卫生：好，中，差 .
     *
     */
    public void setOralHygiene(String oralHygiene) {
        this.oralHygiene = oralHygiene == null ? null : oralHygiene.trim();
    }

    /**
     * 颈椎：不详，S1，S2，S3，S4，S5，S6 .
     *
     */
    public String getCervicalVertebra() {
        return cervicalVertebra;
    }

    /**
     * 颈椎：不详，S1，S2，S3，S4，S5，S6 .
     *
     */
    public void setCervicalVertebra(String cervicalVertebra) {
        this.cervicalVertebra = cervicalVertebra == null ? null : cervicalVertebra.trim();
    }

    /**
     * 手腕骨：F期，FG期，G期，H期，I期，不详 .
     *
     */
    public String getWristBone() {
        return wristBone;
    }

    /**
     * 手腕骨：F期，FG期，G期，H期，I期，不详 .
     *
     */
    public void setWristBone(String wristBone) {
        this.wristBone = wristBone == null ? null : wristBone.trim();
    }

    /**
     * 月经：有，无，不详 .
     *
     */
    public String getMenstruation() {
        return menstruation;
    }

    /**
     * 月经：有，无，不详 .
     *
     */
    public void setMenstruation(String menstruation) {
        this.menstruation = menstruation == null ? null : menstruation.trim();
    }

    /**
     * 初潮 .
     *
     */
    public String getMenarche() {
        return menarche;
    }

    /**
     * 初潮 .
     *
     */
    public void setMenarche(String menarche) {
        this.menarche = menarche == null ? null : menarche.trim();
    }

    /**
     * 矫正史：有，无 .
     *
     */
    public String getCorrectiveHistory() {
        return correctiveHistory;
    }

    /**
     * 矫正史：有，无 .
     *
     */
    public void setCorrectiveHistory(String correctiveHistory) {
        this.correctiveHistory = correctiveHistory == null ? null : correctiveHistory.trim();
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
     * 拔牙情况 .
     *
     */
    public String getToothExtraction() {
        return toothExtraction;
    }

    /**
     * 拔牙情况 .
     *
     */
    public void setToothExtraction(String toothExtraction) {
        this.toothExtraction = toothExtraction == null ? null : toothExtraction.trim();
    }

    /**
     * 乳牙龋齿：有，无，不详 .
     *
     */
    public String getDentalCaries() {
        return dentalCaries;
    }

    /**
     * 乳牙龋齿：有，无，不详 .
     *
     */
    public void setDentalCaries(String dentalCaries) {
        this.dentalCaries = dentalCaries == null ? null : dentalCaries.trim();
    }

    /**
     * 乳牙早失：有，无，不详 .
     *
     */
    public String getEarlyLoss() {
        return earlyLoss;
    }

    /**
     * 乳牙早失：有，无，不详 .
     *
     */
    public void setEarlyLoss(String earlyLoss) {
        this.earlyLoss = earlyLoss == null ? null : earlyLoss.trim();
    }

    /**
     * 乳牙错颌：无，前牙反颌，下颌后缩，下颌后斜，不详 .
     *
     */
    public String getMalocclusion() {
        return malocclusion;
    }

    /**
     * 乳牙错颌：无，前牙反颌，下颌后缩，下颌后斜，不详 .
     *
     */
    public void setMalocclusion(String malocclusion) {
        this.malocclusion = malocclusion == null ? null : malocclusion.trim();
    }

    /**
     * 替牙龋齿：有，无，不详 .
     *
     */
    public String getDentalCariesReplace() {
        return dentalCariesReplace;
    }

    /**
     * 替牙龋齿：有，无，不详 .
     *
     */
    public void setDentalCariesReplace(String dentalCariesReplace) {
        this.dentalCariesReplace = dentalCariesReplace == null ? null : dentalCariesReplace.trim();
    }

    /**
     * 替牙错颌：无，前牙反颌，下颌后缩，下颌后斜，不详 .
     *
     */
    public String getEarlyLossReplace() {
        return earlyLossReplace;
    }

    /**
     * 替牙错颌：无，前牙反颌，下颌后缩，下颌后斜，不详 .
     *
     */
    public void setEarlyLossReplace(String earlyLossReplace) {
        this.earlyLossReplace = earlyLossReplace == null ? null : earlyLossReplace.trim();
    }

    /**
     * 替牙错颌：无，前牙反颌，下颌后缩，下颌后斜，不详 .
     *
     */
    public String getMalocclusionReplace() {
        return malocclusionReplace;
    }

    /**
     * 替牙错颌：无，前牙反颌，下颌后缩，下颌后斜，不详 .
     *
     */
    public void setMalocclusionReplace(String malocclusionReplace) {
        this.malocclusionReplace = malocclusionReplace == null ? null : malocclusionReplace.trim();
    }

    /**
     * 唇裂：无、单侧完全裂，单侧不完全裂，单侧混合裂，双侧完全裂，双侧不完全裂，双侧混合裂 .
     *
     */
    public String getCleftLip() {
        return cleftLip;
    }

    /**
     * 唇裂：无、单侧完全裂，单侧不完全裂，单侧混合裂，双侧完全裂，双侧不完全裂，双侧混合裂 .
     *
     */
    public void setCleftLip(String cleftLip) {
        this.cleftLip = cleftLip == null ? null : cleftLip.trim();
    }

    /**
     * 腭裂：无、软腭裂，不完全裂，单侧完全腭裂，双侧完全腭裂 .
     *
     */
    public String getCleftPalate() {
        return cleftPalate;
    }

    /**
     * 腭裂：无、软腭裂，不完全裂，单侧完全腭裂，双侧完全腭裂 .
     *
     */
    public void setCleftPalate(String cleftPalate) {
        this.cleftPalate = cleftPalate == null ? null : cleftPalate.trim();
    }

    /**
     * 其他情况 .
     *
     */
    public String getOthers() {
        return others;
    }

    /**
     * 其他情况 .
     *
     */
    public void setOthers(String others) {
        this.others = others == null ? null : others.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GeneralCheck [code=").append(code);
        builder.append(",chiefComplaint=").append(chiefComplaint); 
        builder.append(",badHabits=").append(badHabits); 
        builder.append(",oralHygiene=").append(oralHygiene); 
        builder.append(",cervicalVertebra=").append(cervicalVertebra); 
        builder.append(",wristBone=").append(wristBone); 
        builder.append(",menstruation=").append(menstruation); 
        builder.append(",menarche=").append(menarche); 
        builder.append(",correctiveHistory=").append(correctiveHistory); 
        builder.append(",attendingDoctor=").append(attendingDoctor); 
        builder.append(",toothExtraction=").append(toothExtraction); 
        builder.append(",dentalCaries=").append(dentalCaries); 
        builder.append(",earlyLoss=").append(earlyLoss); 
        builder.append(",malocclusion=").append(malocclusion); 
        builder.append(",dentalCariesReplace=").append(dentalCariesReplace); 
        builder.append(",earlyLossReplace=").append(earlyLossReplace); 
        builder.append(",malocclusionReplace=").append(malocclusionReplace); 
        builder.append(",cleftLip=").append(cleftLip); 
        builder.append(",cleftPalate=").append(cleftPalate); 
        builder.append(",others=").append(others); 
        builder.append(",patientNo=").append(patientNo); 
        builder.append(",remark=").append(remark); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",createId=").append(createId); 
        builder.append("]");
        return builder.toString();
    }
}