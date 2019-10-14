package com.lj.business.cp.dto.couponRuleEx;

import java.io.Serializable;
import java.util.Date;

public class FindCouponRuleExReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -1320235315600071644L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 规则编号 .
     */
    private String ruleCode;

    /**
     * 限领次数
              .
     */
    private Integer useNum;

    /**
     * 剩余数
             由客户领用之后回写 .
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	public Integer getSurplusNum() {
		return surplusNum;
	}

	public void setSurplusNum(Integer surplusNum) {
		this.surplusNum = surplusNum;
	}

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getUv() {
		return uv;
	}

	public void setUv(Integer uv) {
		this.uv = uv;
	}

	public Integer getCv() {
		return cv;
	}

	public void setCv(Integer cv) {
		this.cv = cv;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCouponRuleExReturn [code=");
		builder.append(code);
		builder.append(", ruleCode=");
		builder.append(ruleCode);
		builder.append(", useNum=");
		builder.append(useNum);
		builder.append(", surplusNum=");
		builder.append(surplusNum);
		builder.append(", pv=");
		builder.append(pv);
		builder.append(", uv=");
		builder.append(uv);
		builder.append(", cv=");
		builder.append(cv);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}
    
    
}
