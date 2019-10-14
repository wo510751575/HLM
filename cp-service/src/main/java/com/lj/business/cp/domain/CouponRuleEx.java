package com.lj.business.cp.domain;

import java.util.Date;

public class CouponRuleEx {
    /**
     * CODE .
     */
    private String code;

    /**
     * 规则编号 .
     */
    private String ruleCode;

    /**
     * 限领次数              .
     */
    private Integer useNum;

    /**
     * 剩余数             由客户领用之后回写 .
     */
    private Integer surplusNum;

    /**
     * 浏览量 .
     */
    private Integer pv;

    /**
     * 点击量 .
     */
    private Integer uv;

    /**
     * 消费量 .
     */
    private Integer cv;

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
     * 规则编号 .
     *
     */
    public String getRuleCode() {
        return ruleCode;
    }

    /**
     * 规则编号 .
     *
     */
    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
    }

    /**
     * 限领次数              .
     *
     */
    public Integer getUseNum() {
        return useNum;
    }

    /**
     * 限领次数              .
     *
     */
    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    /**
     * 剩余数             由客户领用之后回写 .
     *
     */
    public Integer getSurplusNum() {
        return surplusNum;
    }

    /**
     * 剩余数             由客户领用之后回写 .
     *
     */
    public void setSurplusNum(Integer surplusNum) {
        this.surplusNum = surplusNum;
    }

    /**
     * 浏览量 .
     *
     */
    public Integer getPv() {
        return pv;
    }

    /**
     * 浏览量 .
     *
     */
    public void setPv(Integer pv) {
        this.pv = pv;
    }

    /**
     * 点击量 .
     *
     */
    public Integer getUv() {
        return uv;
    }

    /**
     * 点击量 .
     *
     */
    public void setUv(Integer uv) {
        this.uv = uv;
    }

    /**
     * 消费量 .
     *
     */
    public Integer getCv() {
        return cv;
    }

    /**
     * 消费量 .
     *
     */
    public void setCv(Integer cv) {
        this.cv = cv;
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
        builder.append("CouponRuleEx [code=").append(code);
        builder.append(",ruleCode=").append(ruleCode); 
        builder.append(",useNum=").append(useNum); 
        builder.append(",surplusNum=").append(surplusNum); 
        builder.append(",pv=").append(pv); 
        builder.append(",uv=").append(uv); 
        builder.append(",cv=").append(cv); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}