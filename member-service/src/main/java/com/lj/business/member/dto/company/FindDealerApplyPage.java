package com.lj.business.member.dto.company;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年5月1日
 */
public class FindDealerApplyPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8791888234452417587L; 
    /**
     * CODE .
     */
    private String code;

    /**
     * 经销商编号 .
     */
    private String dealerNo;
    
    /**
     * 经销商代码
     */
    private String dealerCode;

    /**
     * 经销商名称 .
     */
    private String dealerName;
    
    /**
     * 经销商负责人姓名
     */
    private String dealerResponsiblePerson;

    /**
     * 经销商负责人手机号
     */
    private String dealerResponsibleMobile;

    /**
     * 申请状态 .
     */
    private Integer applyStatus;

    /**
     * 申请时间 - 从.
     */
    private Date startTime;
    
    /**
     * 申请时间 - 到.
     */
    private Date endTime;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDealerNo() {
        return dealerNo;
    }

    public void setDealerNo(String dealerNo) {
        this.dealerNo = dealerNo;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerResponsiblePerson() {
        return dealerResponsiblePerson;
    }

    public void setDealerResponsiblePerson(String dealerResponsiblePerson) {
        this.dealerResponsiblePerson = dealerResponsiblePerson;
    }

    public String getDealerResponsibleMobile() {
        return dealerResponsibleMobile;
    }

    public void setDealerResponsibleMobile(String dealerResponsibleMobile) {
        this.dealerResponsibleMobile = dealerResponsibleMobile;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindDealerApplyPage [code=");
        builder.append(code);
        builder.append(", dealerNo=");
        builder.append(dealerNo);
        builder.append(", dealerCode=");
        builder.append(dealerCode);
        builder.append(", dealerName=");
        builder.append(dealerName);
        builder.append(", dealerResponsiblePerson=");
        builder.append(dealerResponsiblePerson);
        builder.append(", dealerResponsibleMobile=");
        builder.append(dealerResponsibleMobile);
        builder.append(", applyStatus=");
        builder.append(applyStatus);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", endTime=");
        builder.append(endTime);
        builder.append(", merchantNo=");
        builder.append(merchantNo);
        builder.append("]");
        return builder.toString();
    }
    
}
