package com.lj.business.cp.dto.awardCondition;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FindAwardConditionPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2618714449917114905L; 
	 /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 分店编号 .
     */
    

    /**
     * 分店名称 .
     */
    

    /**
     * 维度编号 .
     */
    private String veidooNo;

    /**
     * 维度上限 .
     */
    private BigDecimal veidooUp;

    /**
     * 维度下限 .
     */
    private BigDecimal veidooDown;

    /**
     * 活动开始时间 .
     */
    private Date beginDate;

    /**
     * 活动结束时间 .
     */
    private Date endDate;

    /**
     * 活动描述 .
     */
    private String activityDesc;

    /**
     * 规则CODE .
     */
    private String ruleCode;

    /**
     * 更新人 .
     */
    private String updateId;

    /**
     * 更新时间 .
     */
    private Date updateDate;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

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
     * 商户名称 .
     *
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称 .
     *
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * 维度编号 .
     *
     */
    public String getVeidooNo() {
        return veidooNo;
    }

    /**
     * 维度编号 .
     *
     */
    public void setVeidooNo(String veidooNo) {
        this.veidooNo = veidooNo == null ? null : veidooNo.trim();
    }

    /**
     * 维度上限 .
     *
     */
    public BigDecimal getVeidooUp() {
        return veidooUp;
    }

    /**
     * 维度上限 .
     *
     */
    public void setVeidooUp(BigDecimal veidooUp) {
        this.veidooUp = veidooUp;
    }

    /**
     * 维度下限 .
     *
     */
    public BigDecimal getVeidooDown() {
        return veidooDown;
    }

    /**
     * 维度下限 .
     *
     */
    public void setVeidooDown(BigDecimal veidooDown) {
        this.veidooDown = veidooDown;
    }

    /**
     * 活动开始时间 .
     *
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 活动开始时间 .
     *
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 活动结束时间 .
     *
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 活动结束时间 .
     *
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 活动描述 .
     *
     */
    public String getActivityDesc() {
        return activityDesc;
    }

    /**
     * 活动描述 .
     *
     */
    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc == null ? null : activityDesc.trim();
    }

    /**
     * 规则CODE .
     *
     */
    public String getRuleCode() {
        return ruleCode;
    }

    /**
     * 规则CODE .
     *
     */
    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AwardCondition [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",veidooNo=").append(veidooNo); 
        builder.append(",veidooUp=").append(veidooUp); 
        builder.append(",veidooDown=").append(veidooDown); 
        builder.append(",beginDate=").append(beginDate); 
        builder.append(",endDate=").append(endDate); 
        builder.append(",activityDesc=").append(activityDesc); 
        builder.append(",ruleCode=").append(ruleCode); 
        builder.append(",updateId=").append(updateId); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }

}
