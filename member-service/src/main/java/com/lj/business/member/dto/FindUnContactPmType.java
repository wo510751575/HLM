package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：查询未联系顾客分类入参
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2017年12月7日
 */
public class FindUnContactPmType implements Serializable {
    private static final long serialVersionUID = 2588064221010423650L;
    
    /**
     * 商户编号(必填)
     */
    private String merchantNo;
    
    /**
     * 导购编号(必填)
     */
    private String memberNoGm;
    
    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMemberNoGm() {
        return memberNoGm;
    }

    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindUnContactPmType [merchantNo=");
        builder.append(merchantNo);
        builder.append(", memberNoGm=");
        builder.append(memberNoGm);
        builder.append(", startDate=");
        builder.append(startDate);
        builder.append(", endDate=");
        builder.append(endDate);
        builder.append("]");
        return builder.toString();
    }
    
}
