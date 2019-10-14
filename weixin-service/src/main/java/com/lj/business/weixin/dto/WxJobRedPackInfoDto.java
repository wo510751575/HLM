package com.lj.business.weixin.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WxJobRedPackInfoDto implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -1629326688348043755L;

	/**
     * code .
     */
    private String code;

    /**
     * JOB_CODE .
     */
    private String jobCode;

    /**
     * 商户号 .
     */
    private String merchantNo;

    /**
     * 终端微信号 .
     */
    private String wxNoShop;

    /**
     * 终端号 .
     */
    

    /**
     * 发红包导购号 .
     */
    private String sendRedpackGm;

    /**
     * 红包类型 .
     */
    private String redpackType;

    /**
     * 红包文字 .
     */
    private String redpackContent;

    /**
     * 红包总额 .
     */
    private Long redpackAmount;

    /**
     * 总红包个数 .
     */
    private Integer redpackCount;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新时间 .
     */
    private Date updateDate;

    /**
     * 状态 .
     */
    private String status;

    /**
     * 备注 .
     */
    private String remark;
    
    
    private List<String> redPackList;

    /**
     * code .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * code .
     *
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * JOB_CODE .
     *
     */
    public String getJobCode() {
        return jobCode;
    }

    /**
     * JOB_CODE .
     *
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    /**
     * 商户号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    /**
     * 终端微信号 .
     *
     */
    public String getWxNoShop() {
        return wxNoShop;
    }

    /**
     * 终端微信号 .
     *
     */
    public void setWxNoShop(String wxNoShop) {
        this.wxNoShop = wxNoShop;
    }


    /**
     * 发红包导购号 .
     *
     */
    public String getSendRedpackGm() {
        return sendRedpackGm;
    }

    /**
     * 发红包导购号 .
     *
     */
    public void setSendRedpackGm(String sendRedpackGm) {
        this.sendRedpackGm = sendRedpackGm;
    }

    /**
     * 红包类型 .
     *
     */
    public String getRedpackType() {
        return redpackType;
    }

    /**
     * 红包类型 .
     *
     */
    public void setRedpackType(String redpackType) {
        this.redpackType = redpackType;
    }

    /**
     * 红包文字 .
     *
     */
    public String getRedpackContent() {
        return redpackContent;
    }

    /**
     * 红包文字 .
     *
     */
    public void setRedpackContent(String redpackContent) {
        this.redpackContent = redpackContent;
    }

    /**
     * 红包总额 .
     *
     */
    public Long getRedpackAmount() {
        return redpackAmount;
    }

    /**
     * 红包总额 .
     *
     */
    public void setRedpackAmount(Long redpackAmount) {
        this.redpackAmount = redpackAmount;
    }

    /**
     * 总红包个数 .
     *
     */
    public Integer getRedpackCount() {
        return redpackCount;
    }

    /**
     * 总红包个数 .
     *
     */
    public void setRedpackCount(Integer redpackCount) {
        this.redpackCount = redpackCount;
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
     * 状态 .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态 .
     *
     */
    public void setStatus(String status) {
        this.status = status;
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
        this.remark = remark;
    }

   

	 
	public List<String> getRedPackList() {
		return redPackList;
	}

	public void setRedPackList(List<String> redPackList) {
		this.redPackList = redPackList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxJobRedPackInfoDto [code=");
		builder.append(code);
		builder.append(", jobCode=");
		builder.append(jobCode);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", wxNoShop=");
		builder.append(wxNoShop);
		builder.append(", sendRedpackGm=");
		builder.append(sendRedpackGm);
		builder.append(", redpackType=");
		builder.append(redpackType);
		builder.append(", redpackContent=");
		builder.append(redpackContent);
		builder.append(", redpackAmount=");
		builder.append(redpackAmount);
		builder.append(", redpackCount=");
		builder.append(redpackCount);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", redPackList=");
		builder.append(redPackList);
		builder.append("]");
		return builder.toString();
	}
	
	
    
}
