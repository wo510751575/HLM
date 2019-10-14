package com.lj.business.member.dto;

import java.io.Serializable;

public class FindShopPromotionReturn implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -65829788459109206L;
    
    /**
     * CODE .
     */
    private String code;
    
    /**
     * 分店编号 .
     */
    
    
    /**
     * 商户编号 .
     */
    private String memberNoMerchant;

    /**
     * 分店名称 .
     */
    
    
    /** 终端代码. */
    private String shopNoMerchant;
    
    /** 终端状态 OPENED：已经开业、SUSPEND：暂停营业。INDECORATION：尚在装修. */
    private String status;
    
    /** 销售渠道. */
    private String mecShopChannel;
    
    /**
     * 区域名称 .
     */
    private String areaName;
    
    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark2;
    
    /** 客户数量. */
    private Integer personNum;
    
    /** 成单金额. */
    private Long orderAmount;

    

    public String getShopNoMerchant() {
        return shopNoMerchant;
    }

    public void setShopNoMerchant(String shopNoMerchant) {
        this.shopNoMerchant = shopNoMerchant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMecShopChannel() {
        return mecShopChannel;
    }

    public void setMecShopChannel(String mecShopChannel) {
        this.mecShopChannel = mecShopChannel;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMemberNoMerchant() {
        return memberNoMerchant;
    }

    public void setMemberNoMerchant(String memberNoMerchant) {
        this.memberNoMerchant = memberNoMerchant;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindShopPromotionReturn [code=");
        builder.append(code);
        builder.append(", memberNoMerchant=");
        builder.append(memberNoMerchant);
        builder.append(", shopNoMerchant=");
        builder.append(shopNoMerchant);
        builder.append(", status=");
        builder.append(status);
        builder.append(", mecShopChannel=");
        builder.append(mecShopChannel);
        builder.append(", areaName=");
        builder.append(areaName);
        builder.append(", remark3=");
        builder.append(remark3);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", remark2=");
        builder.append(remark2);
        builder.append(", personNum=");
        builder.append(personNum);
        builder.append(", orderAmount=");
        builder.append(orderAmount);
        builder.append("]");
        return builder.toString();
    }

}
