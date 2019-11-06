package com.ye.business.hx.domain;

import java.util.Date;

public class DentitionCheck {
    /**
     *  .
     */
    private String code;

    /**
     * 正位像CODE .
     */
    private String front;

    /**
     * 正位像URL .
     */
    private String frontUrl;

    /**
     * 上牙列code .
     */
    private String up;

    /**
     * 上牙列URL .
     */
    private String upUrl;

    /**
     * 下牙列CODE .
     */
    private String down;

    /**
     * 下牙列URL .
     */
    private String downUrl;

    /**
     * 左侧咬合CODE .
     */
    private String left;

    /**
     * 左侧咬合URL .
     */
    private String leftUrl;

    /**
     * 右侧咬合CODE .
     */
    private String right;

    /**
     * 右侧咬合URL .
     */
    private String rightUrl;

    /**
     * 前牙覆牙合覆盖CODE .
     */
    private String frontTeeth;

    /**
     * 前牙覆牙合覆盖URL .
     */
    private String frontTeethUrl;

    /**
     *  .
     */
    private String otherCode;

    /**
     *  .
     */
    private String otherUrl;

    /**
     * 上牙列 .
     */
    private String upTeeth;

    /**
     * 下牙列 .
     */
    private String downTeeth;

    /**
     * 多生牙 .
     */
    private String moreTeeth;

    /**
     * 缺失 .
     */
    private String loss;

    /**
     * 个别牙错位 .
     */
    private String dislocation;

    /**
     * 前牙比 .
     */
    private String anteriorRatio;

    /**
     * 全牙比 .
     */
    private String overallRatio;

    /**
     * 左侧磨牙：中性，中性偏近中，中性偏远中，近中尖对尖，远中尖对尖，完全近中，完全远中 .
     */
    private String leftMolar;

    /**
     * 右侧磨牙：中性，中性偏近中，中性偏远中，近中尖对尖，远中尖对尖，完全近中，完全远中 .
     */
    private String rightMolar;

    /**
     * 左侧尖牙：中性，近中，远中 .
     */
    private String leftFangs;

    /**
     * 右侧尖牙：中性，近中，远中 .
     */
    private String rightFangs;

    /**
     * 前牙覆盖：Ⅰ，Ⅱ，Ⅲ，正常 .
     */
    private String anteriorTeeth;

    /**
     * 牙弓形态：狭窄，宽大，正常 .
     */
    private String archForm;

    /**
     * 后牙：正锁合，反锁合，正常 .
     */
    private String posteriorTeeth;

    /**
     * 尖牙：狭窄，正常 .
     */
    private String canineTeeth;

    /**
     * 前牙覆颌 .
     */
    private String maxillaryOverbite;

    /**
     * 上颌：左偏，右偏，正常 .
     */
    private String maxillary;

    /**
     * 下颌 .
     */
    private String mandible;

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
     * 正位像CODE .
     *
     */
    public String getFront() {
        return front;
    }

    /**
     * 正位像CODE .
     *
     */
    public void setFront(String front) {
        this.front = front == null ? null : front.trim();
    }

    /**
     * 正位像URL .
     *
     */
    public String getFrontUrl() {
        return frontUrl;
    }

    /**
     * 正位像URL .
     *
     */
    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl == null ? null : frontUrl.trim();
    }

    /**
     * 上牙列code .
     *
     */
    public String getUp() {
        return up;
    }

    /**
     * 上牙列code .
     *
     */
    public void setUp(String up) {
        this.up = up == null ? null : up.trim();
    }

    /**
     * 上牙列URL .
     *
     */
    public String getUpUrl() {
        return upUrl;
    }

    /**
     * 上牙列URL .
     *
     */
    public void setUpUrl(String upUrl) {
        this.upUrl = upUrl == null ? null : upUrl.trim();
    }

    /**
     * 下牙列CODE .
     *
     */
    public String getDown() {
        return down;
    }

    /**
     * 下牙列CODE .
     *
     */
    public void setDown(String down) {
        this.down = down == null ? null : down.trim();
    }

    /**
     * 下牙列URL .
     *
     */
    public String getDownUrl() {
        return downUrl;
    }

    /**
     * 下牙列URL .
     *
     */
    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl == null ? null : downUrl.trim();
    }

    /**
     * 左侧咬合CODE .
     *
     */
    public String getLeft() {
        return left;
    }

    /**
     * 左侧咬合CODE .
     *
     */
    public void setLeft(String left) {
        this.left = left == null ? null : left.trim();
    }

    /**
     * 左侧咬合URL .
     *
     */
    public String getLeftUrl() {
        return leftUrl;
    }

    /**
     * 左侧咬合URL .
     *
     */
    public void setLeftUrl(String leftUrl) {
        this.leftUrl = leftUrl == null ? null : leftUrl.trim();
    }

    /**
     * 右侧咬合CODE .
     *
     */
    public String getRight() {
        return right;
    }

    /**
     * 右侧咬合CODE .
     *
     */
    public void setRight(String right) {
        this.right = right == null ? null : right.trim();
    }

    /**
     * 右侧咬合URL .
     *
     */
    public String getRightUrl() {
        return rightUrl;
    }

    /**
     * 右侧咬合URL .
     *
     */
    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl == null ? null : rightUrl.trim();
    }

    /**
     * 前牙覆牙合覆盖CODE .
     *
     */
    public String getFrontTeeth() {
        return frontTeeth;
    }

    /**
     * 前牙覆牙合覆盖CODE .
     *
     */
    public void setFrontTeeth(String frontTeeth) {
        this.frontTeeth = frontTeeth == null ? null : frontTeeth.trim();
    }

    /**
     * 前牙覆牙合覆盖URL .
     *
     */
    public String getFrontTeethUrl() {
        return frontTeethUrl;
    }

    /**
     * 前牙覆牙合覆盖URL .
     *
     */
    public void setFrontTeethUrl(String frontTeethUrl) {
        this.frontTeethUrl = frontTeethUrl == null ? null : frontTeethUrl.trim();
    }

    /**
     *  .
     *
     */
    public String getOtherCode() {
        return otherCode;
    }

    /**
     *  .
     *
     */
    public void setOtherCode(String otherCode) {
        this.otherCode = otherCode == null ? null : otherCode.trim();
    }

    /**
     *  .
     *
     */
    public String getOtherUrl() {
        return otherUrl;
    }

    /**
     *  .
     *
     */
    public void setOtherUrl(String otherUrl) {
        this.otherUrl = otherUrl == null ? null : otherUrl.trim();
    }

    /**
     * 上牙列 .
     *
     */
    public String getUpTeeth() {
        return upTeeth;
    }

    /**
     * 上牙列 .
     *
     */
    public void setUpTeeth(String upTeeth) {
        this.upTeeth = upTeeth == null ? null : upTeeth.trim();
    }

    /**
     * 下牙列 .
     *
     */
    public String getDownTeeth() {
        return downTeeth;
    }

    /**
     * 下牙列 .
     *
     */
    public void setDownTeeth(String downTeeth) {
        this.downTeeth = downTeeth == null ? null : downTeeth.trim();
    }

    /**
     * 多生牙 .
     *
     */
    public String getMoreTeeth() {
        return moreTeeth;
    }

    /**
     * 多生牙 .
     *
     */
    public void setMoreTeeth(String moreTeeth) {
        this.moreTeeth = moreTeeth == null ? null : moreTeeth.trim();
    }

    /**
     * 缺失 .
     *
     */
    public String getLoss() {
        return loss;
    }

    /**
     * 缺失 .
     *
     */
    public void setLoss(String loss) {
        this.loss = loss == null ? null : loss.trim();
    }

    /**
     * 个别牙错位 .
     *
     */
    public String getDislocation() {
        return dislocation;
    }

    /**
     * 个别牙错位 .
     *
     */
    public void setDislocation(String dislocation) {
        this.dislocation = dislocation == null ? null : dislocation.trim();
    }

    /**
     * 前牙比 .
     *
     */
    public String getAnteriorRatio() {
        return anteriorRatio;
    }

    /**
     * 前牙比 .
     *
     */
    public void setAnteriorRatio(String anteriorRatio) {
        this.anteriorRatio = anteriorRatio == null ? null : anteriorRatio.trim();
    }

    /**
     * 全牙比 .
     *
     */
    public String getOverallRatio() {
        return overallRatio;
    }

    /**
     * 全牙比 .
     *
     */
    public void setOverallRatio(String overallRatio) {
        this.overallRatio = overallRatio == null ? null : overallRatio.trim();
    }

    /**
     * 左侧磨牙：中性，中性偏近中，中性偏远中，近中尖对尖，远中尖对尖，完全近中，完全远中 .
     *
     */
    public String getLeftMolar() {
        return leftMolar;
    }

    /**
     * 左侧磨牙：中性，中性偏近中，中性偏远中，近中尖对尖，远中尖对尖，完全近中，完全远中 .
     *
     */
    public void setLeftMolar(String leftMolar) {
        this.leftMolar = leftMolar == null ? null : leftMolar.trim();
    }

    /**
     * 右侧磨牙：中性，中性偏近中，中性偏远中，近中尖对尖，远中尖对尖，完全近中，完全远中 .
     *
     */
    public String getRightMolar() {
        return rightMolar;
    }

    /**
     * 右侧磨牙：中性，中性偏近中，中性偏远中，近中尖对尖，远中尖对尖，完全近中，完全远中 .
     *
     */
    public void setRightMolar(String rightMolar) {
        this.rightMolar = rightMolar == null ? null : rightMolar.trim();
    }

    /**
     * 左侧尖牙：中性，近中，远中 .
     *
     */
    public String getLeftFangs() {
        return leftFangs;
    }

    /**
     * 左侧尖牙：中性，近中，远中 .
     *
     */
    public void setLeftFangs(String leftFangs) {
        this.leftFangs = leftFangs == null ? null : leftFangs.trim();
    }

    /**
     * 右侧尖牙：中性，近中，远中 .
     *
     */
    public String getRightFangs() {
        return rightFangs;
    }

    /**
     * 右侧尖牙：中性，近中，远中 .
     *
     */
    public void setRightFangs(String rightFangs) {
        this.rightFangs = rightFangs == null ? null : rightFangs.trim();
    }

    /**
     * 前牙覆盖：Ⅰ，Ⅱ，Ⅲ，正常 .
     *
     */
    public String getAnteriorTeeth() {
        return anteriorTeeth;
    }

    /**
     * 前牙覆盖：Ⅰ，Ⅱ，Ⅲ，正常 .
     *
     */
    public void setAnteriorTeeth(String anteriorTeeth) {
        this.anteriorTeeth = anteriorTeeth == null ? null : anteriorTeeth.trim();
    }

    /**
     * 牙弓形态：狭窄，宽大，正常 .
     *
     */
    public String getArchForm() {
        return archForm;
    }

    /**
     * 牙弓形态：狭窄，宽大，正常 .
     *
     */
    public void setArchForm(String archForm) {
        this.archForm = archForm == null ? null : archForm.trim();
    }

    /**
     * 后牙：正锁合，反锁合，正常 .
     *
     */
    public String getPosteriorTeeth() {
        return posteriorTeeth;
    }

    /**
     * 后牙：正锁合，反锁合，正常 .
     *
     */
    public void setPosteriorTeeth(String posteriorTeeth) {
        this.posteriorTeeth = posteriorTeeth == null ? null : posteriorTeeth.trim();
    }

    /**
     * 尖牙：狭窄，正常 .
     *
     */
    public String getCanineTeeth() {
        return canineTeeth;
    }

    /**
     * 尖牙：狭窄，正常 .
     *
     */
    public void setCanineTeeth(String canineTeeth) {
        this.canineTeeth = canineTeeth == null ? null : canineTeeth.trim();
    }

    /**
     * 前牙覆颌 .
     *
     */
    public String getMaxillaryOverbite() {
        return maxillaryOverbite;
    }

    /**
     * 前牙覆颌 .
     *
     */
    public void setMaxillaryOverbite(String maxillaryOverbite) {
        this.maxillaryOverbite = maxillaryOverbite == null ? null : maxillaryOverbite.trim();
    }

    /**
     * 上颌：左偏，右偏，正常 .
     *
     */
    public String getMaxillary() {
        return maxillary;
    }

    /**
     * 上颌：左偏，右偏，正常 .
     *
     */
    public void setMaxillary(String maxillary) {
        this.maxillary = maxillary == null ? null : maxillary.trim();
    }

    /**
     * 下颌 .
     *
     */
    public String getMandible() {
        return mandible;
    }

    /**
     * 下颌 .
     *
     */
    public void setMandible(String mandible) {
        this.mandible = mandible == null ? null : mandible.trim();
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
        builder.append("DentitionCheck [code=").append(code);
        builder.append(",front=").append(front); 
        builder.append(",frontUrl=").append(frontUrl); 
        builder.append(",up=").append(up); 
        builder.append(",upUrl=").append(upUrl); 
        builder.append(",down=").append(down); 
        builder.append(",downUrl=").append(downUrl); 
        builder.append(",left=").append(left); 
        builder.append(",leftUrl=").append(leftUrl); 
        builder.append(",right=").append(right); 
        builder.append(",rightUrl=").append(rightUrl); 
        builder.append(",frontTeeth=").append(frontTeeth); 
        builder.append(",frontTeethUrl=").append(frontTeethUrl); 
        builder.append(",otherCode=").append(otherCode); 
        builder.append(",otherUrl=").append(otherUrl); 
        builder.append(",upTeeth=").append(upTeeth); 
        builder.append(",downTeeth=").append(downTeeth); 
        builder.append(",moreTeeth=").append(moreTeeth); 
        builder.append(",loss=").append(loss); 
        builder.append(",dislocation=").append(dislocation); 
        builder.append(",anteriorRatio=").append(anteriorRatio); 
        builder.append(",overallRatio=").append(overallRatio); 
        builder.append(",leftMolar=").append(leftMolar); 
        builder.append(",rightMolar=").append(rightMolar); 
        builder.append(",leftFangs=").append(leftFangs); 
        builder.append(",rightFangs=").append(rightFangs); 
        builder.append(",anteriorTeeth=").append(anteriorTeeth); 
        builder.append(",archForm=").append(archForm); 
        builder.append(",posteriorTeeth=").append(posteriorTeeth); 
        builder.append(",canineTeeth=").append(canineTeeth); 
        builder.append(",maxillaryOverbite=").append(maxillaryOverbite); 
        builder.append(",maxillary=").append(maxillary); 
        builder.append(",mandible=").append(mandible); 
        builder.append(",other=").append(other); 
        builder.append(",patientNo=").append(patientNo); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",createId=").append(createId); 
        builder.append(",remark=").append(remark); 
        builder.append("]");
        return builder.toString();
    }
}