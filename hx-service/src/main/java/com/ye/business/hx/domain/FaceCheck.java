package com.ye.business.hx.domain;

import java.util.Date;

public class FaceCheck {
    /**
     *  .
     */
    private String code;

    /**
     * 正面照url .
     */
    private String positive;

    /**
     * 正面照code .
     */
    private String positiveCode;

    /**
     * 正面微笑照url .
     */
    private String positiveSmile;

    /**
     * 正面微笑照code .
     */
    private String positiveSmileCode;

    /**
     * 侧位像url .
     */
    private String side;

    /**
     * 侧位像code .
     */
    private String sideCode;

    /**
     * 右侧照url .
     */
    private String right;

    /**
     * 右侧照code .
     */
    private String rightCode;

    /**
     * 左侧照url .
     */
    private String letf;

    /**
     * 左侧照code .
     */
    private String letfCode;

    /**
     * 其他照片url .
     */
    private String other;

    /**
     * 其他照片code .
     */
    private String otherCode;

    /**
     * 正面型：长面，短面，均面 .
     */
    private String frontalType;

    /**
     * 颏餍窝：明显，正常，无 .
     */
    private String chinSocket;

    /**
     * 唇形：松弛，紧张，正常 .
     */
    private String lipShape;

    /**
     * 对称性 .
     */
    private String symmetry;

    /**
     * 微笑唇齿拉 .
     */
    private String smilePulling;

    /**
     * 放松唇齿拉 .
     */
    private String relaxPulling;

    /**
     * 面型：凹，凸，直 .
     */
    private String faceShape;

    /**
     * 鼻唇角：偏大，偏小，正常 .
     */
    private String nasolabialAngle;

    /**
     * 颏唇沟：正常，明显，无 .
     */
    private String labialGroove;

    /**
     * 颏位：偏左，偏右，正常 .
     */
    private String chinPosition;

    /**
     * 其他 .
     */
    private String others;

    /**
     * 患者编号 .
     */
    private String patientNo;

    /**
     * 创建日期 .
     */
    private Date createDate;

    /**
     *  .
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
     * 正面照url .
     *
     */
    public String getPositive() {
        return positive;
    }

    /**
     * 正面照url .
     *
     */
    public void setPositive(String positive) {
        this.positive = positive == null ? null : positive.trim();
    }

    /**
     * 正面照code .
     *
     */
    public String getPositiveCode() {
        return positiveCode;
    }

    /**
     * 正面照code .
     *
     */
    public void setPositiveCode(String positiveCode) {
        this.positiveCode = positiveCode == null ? null : positiveCode.trim();
    }

    /**
     * 正面微笑照url .
     *
     */
    public String getPositiveSmile() {
        return positiveSmile;
    }

    /**
     * 正面微笑照url .
     *
     */
    public void setPositiveSmile(String positiveSmile) {
        this.positiveSmile = positiveSmile == null ? null : positiveSmile.trim();
    }

    /**
     * 正面微笑照code .
     *
     */
    public String getPositiveSmileCode() {
        return positiveSmileCode;
    }

    /**
     * 正面微笑照code .
     *
     */
    public void setPositiveSmileCode(String positiveSmileCode) {
        this.positiveSmileCode = positiveSmileCode == null ? null : positiveSmileCode.trim();
    }

    /**
     * 侧位像url .
     *
     */
    public String getSide() {
        return side;
    }

    /**
     * 侧位像url .
     *
     */
    public void setSide(String side) {
        this.side = side == null ? null : side.trim();
    }

    /**
     * 侧位像code .
     *
     */
    public String getSideCode() {
        return sideCode;
    }

    /**
     * 侧位像code .
     *
     */
    public void setSideCode(String sideCode) {
        this.sideCode = sideCode == null ? null : sideCode.trim();
    }

    /**
     * 右侧照url .
     *
     */
    public String getRight() {
        return right;
    }

    /**
     * 右侧照url .
     *
     */
    public void setRight(String right) {
        this.right = right == null ? null : right.trim();
    }

    /**
     * 右侧照code .
     *
     */
    public String getRightCode() {
        return rightCode;
    }

    /**
     * 右侧照code .
     *
     */
    public void setRightCode(String rightCode) {
        this.rightCode = rightCode == null ? null : rightCode.trim();
    }

    /**
     * 左侧照url .
     *
     */
    public String getLetf() {
        return letf;
    }

    /**
     * 左侧照url .
     *
     */
    public void setLetf(String letf) {
        this.letf = letf == null ? null : letf.trim();
    }

    /**
     * 左侧照code .
     *
     */
    public String getLetfCode() {
        return letfCode;
    }

    /**
     * 左侧照code .
     *
     */
    public void setLetfCode(String letfCode) {
        this.letfCode = letfCode == null ? null : letfCode.trim();
    }

    /**
     * 其他照片url .
     *
     */
    public String getOther() {
        return other;
    }

    /**
     * 其他照片url .
     *
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    /**
     * 其他照片code .
     *
     */
    public String getOtherCode() {
        return otherCode;
    }

    /**
     * 其他照片code .
     *
     */
    public void setOtherCode(String otherCode) {
        this.otherCode = otherCode == null ? null : otherCode.trim();
    }

    /**
     * 正面型：长面，短面，均面 .
     *
     */
    public String getFrontalType() {
        return frontalType;
    }

    /**
     * 正面型：长面，短面，均面 .
     *
     */
    public void setFrontalType(String frontalType) {
        this.frontalType = frontalType == null ? null : frontalType.trim();
    }

    /**
     * 颏餍窝：明显，正常，无 .
     *
     */
    public String getChinSocket() {
        return chinSocket;
    }

    /**
     * 颏餍窝：明显，正常，无 .
     *
     */
    public void setChinSocket(String chinSocket) {
        this.chinSocket = chinSocket == null ? null : chinSocket.trim();
    }

    /**
     * 唇形：松弛，紧张，正常 .
     *
     */
    public String getLipShape() {
        return lipShape;
    }

    /**
     * 唇形：松弛，紧张，正常 .
     *
     */
    public void setLipShape(String lipShape) {
        this.lipShape = lipShape == null ? null : lipShape.trim();
    }

    /**
     * 对称性 .
     *
     */
    public String getSymmetry() {
        return symmetry;
    }

    /**
     * 对称性 .
     *
     */
    public void setSymmetry(String symmetry) {
        this.symmetry = symmetry == null ? null : symmetry.trim();
    }

    /**
     * 微笑唇齿拉 .
     *
     */
    public String getSmilePulling() {
        return smilePulling;
    }

    /**
     * 微笑唇齿拉 .
     *
     */
    public void setSmilePulling(String smilePulling) {
        this.smilePulling = smilePulling == null ? null : smilePulling.trim();
    }

    /**
     * 放松唇齿拉 .
     *
     */
    public String getRelaxPulling() {
        return relaxPulling;
    }

    /**
     * 放松唇齿拉 .
     *
     */
    public void setRelaxPulling(String relaxPulling) {
        this.relaxPulling = relaxPulling == null ? null : relaxPulling.trim();
    }

    /**
     * 面型：凹，凸，直 .
     *
     */
    public String getFaceShape() {
        return faceShape;
    }

    /**
     * 面型：凹，凸，直 .
     *
     */
    public void setFaceShape(String faceShape) {
        this.faceShape = faceShape == null ? null : faceShape.trim();
    }

    /**
     * 鼻唇角：偏大，偏小，正常 .
     *
     */
    public String getNasolabialAngle() {
        return nasolabialAngle;
    }

    /**
     * 鼻唇角：偏大，偏小，正常 .
     *
     */
    public void setNasolabialAngle(String nasolabialAngle) {
        this.nasolabialAngle = nasolabialAngle == null ? null : nasolabialAngle.trim();
    }

    /**
     * 颏唇沟：正常，明显，无 .
     *
     */
    public String getLabialGroove() {
        return labialGroove;
    }

    /**
     * 颏唇沟：正常，明显，无 .
     *
     */
    public void setLabialGroove(String labialGroove) {
        this.labialGroove = labialGroove == null ? null : labialGroove.trim();
    }

    /**
     * 颏位：偏左，偏右，正常 .
     *
     */
    public String getChinPosition() {
        return chinPosition;
    }

    /**
     * 颏位：偏左，偏右，正常 .
     *
     */
    public void setChinPosition(String chinPosition) {
        this.chinPosition = chinPosition == null ? null : chinPosition.trim();
    }

    /**
     * 其他 .
     *
     */
    public String getOthers() {
        return others;
    }

    /**
     * 其他 .
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
    public String getCreateId() {
        return createId;
    }

    /**
     *  .
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
        builder.append("FaceCheck [code=").append(code);
        builder.append(",positive=").append(positive); 
        builder.append(",positiveCode=").append(positiveCode); 
        builder.append(",positiveSmile=").append(positiveSmile); 
        builder.append(",positiveSmileCode=").append(positiveSmileCode); 
        builder.append(",side=").append(side); 
        builder.append(",sideCode=").append(sideCode); 
        builder.append(",right=").append(right); 
        builder.append(",rightCode=").append(rightCode); 
        builder.append(",letf=").append(letf); 
        builder.append(",letfCode=").append(letfCode); 
        builder.append(",other=").append(other); 
        builder.append(",otherCode=").append(otherCode); 
        builder.append(",frontalType=").append(frontalType); 
        builder.append(",chinSocket=").append(chinSocket); 
        builder.append(",lipShape=").append(lipShape); 
        builder.append(",symmetry=").append(symmetry); 
        builder.append(",smilePulling=").append(smilePulling); 
        builder.append(",relaxPulling=").append(relaxPulling); 
        builder.append(",faceShape=").append(faceShape); 
        builder.append(",nasolabialAngle=").append(nasolabialAngle); 
        builder.append(",labialGroove=").append(labialGroove); 
        builder.append(",chinPosition=").append(chinPosition); 
        builder.append(",others=").append(others); 
        builder.append(",patientNo=").append(patientNo); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",createId=").append(createId); 
        builder.append(",remark=").append(remark); 
        builder.append("]");
        return builder.toString();
    }
}