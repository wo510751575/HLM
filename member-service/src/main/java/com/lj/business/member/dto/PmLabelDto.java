package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: PmLabelDto
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 扬恩科技
 * @date: 2019-06-24 17:47
 * 
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved.
 *             注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
public class PmLabelDto implements Serializable {
	private static final long serialVersionUID = 1020585059476361742L;

	/**
	 * 客户标签CODE .
	 */
	private String code;

	/**
	 * 客户标签名称 .
	 */
	private String labelName;

	/**
	 * 标签描述
	 */
	private String remark;

	/**
	 * 最后更新时间
	 */
	private Date updateTime;

	private String merchantNo;

	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * 标签描述
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 标签描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 最后更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 最后更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "PmLabelDto [code=" + code + ", labelName=" + labelName + ", remark=" + remark + ", updateTime="
				+ updateTime + ", merchantNo=" + merchantNo + "]";
	}

}
