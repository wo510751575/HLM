package com.lj.business.member.domain;

import java.util.Date;

public class GmLabel {
    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 客户标签名称 .
     */
    private String labelName;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

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
     * 最后更新时间 .
     */
    private Date updateTime;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

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
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 客户标签名称 .
     *
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * 客户标签名称 .
     *
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
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
     * 最后更新时间 .
     *
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 最后更新时间 .
     *
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 导购编号 .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号 .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GmLabel [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",labelName=").append(labelName); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append(",updateTime=").append(updateTime); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",memberNameGm=").append(memberNameGm); 
        builder.append("]");
        return builder.toString();
    }
}