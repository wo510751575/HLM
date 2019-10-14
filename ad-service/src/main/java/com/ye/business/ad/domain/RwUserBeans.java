package com.ye.business.ad.domain;

import java.util.Date;

public class RwUserBeans {
    /**
     * CODE .
     */
    private String code;

    /**
     * 导购编号 .
     */
    private String memberNo;

    /**
     * 导购姓名 .
     */
    private String memberName;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 总充值豆子：价格转换：1:100(元/豆子) .
     */
    private Integer beansSum;

    /**
     * 已使用豆子 .
     */
    private Integer beansUse;

    /**
     * 冻结豆子 .
     */
    private Integer beansFreeze;

    /**
     * 剩余正常豆子：结算规则：总充值-已使用-冻结=剩余 .
     */
    private Integer beansNormal;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建人 .
     */
    private String createName;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新人 .
     */
    private String updateId;

    /**
     * 更新人 .
     */
    private String updateName;

    /**
     * 更新时间 .
     */
    private Date updateDate;

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
     * 导购编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 导购编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 分店编号 .
     *
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * 分店编号 .
     *
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    /**
     * 分店名称 .
     *
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 分店名称 .
     *
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
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
     * 总充值豆子：价格转换：1:100(元/豆子) .
     *
     */
    public Integer getBeansSum() {
        return beansSum;
    }

    /**
     * 总充值豆子：价格转换：1:100(元/豆子) .
     *
     */
    public void setBeansSum(Integer beansSum) {
        this.beansSum = beansSum;
    }

    /**
     * 已使用豆子 .
     *
     */
    public Integer getBeansUse() {
        return beansUse;
    }

    /**
     * 已使用豆子 .
     *
     */
    public void setBeansUse(Integer beansUse) {
        this.beansUse = beansUse;
    }

    /**
     * 冻结豆子 .
     *
     */
    public Integer getBeansFreeze() {
        return beansFreeze;
    }

    /**
     * 冻结豆子 .
     *
     */
    public void setBeansFreeze(Integer beansFreeze) {
        this.beansFreeze = beansFreeze;
    }

    /**
     * 剩余正常豆子：结算规则：总充值-已使用-冻结=剩余 .
     *
     */
    public Integer getBeansNormal() {
        return beansNormal;
    }

    /**
     * 剩余正常豆子：结算规则：总充值-已使用-冻结=剩余 .
     *
     */
    public void setBeansNormal(Integer beansNormal) {
        this.beansNormal = beansNormal;
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
     * 创建人 .
     *
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
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
     * 更新人 .
     *
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * 更新人 .
     *
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RwUserBeans [code=").append(code);
        builder.append(",memberNo=").append(memberNo); 
        builder.append(",memberName=").append(memberName); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",beansSum=").append(beansSum); 
        builder.append(",beansUse=").append(beansUse); 
        builder.append(",beansFreeze=").append(beansFreeze); 
        builder.append(",beansNormal=").append(beansNormal); 
        builder.append(",createId=").append(createId); 
        builder.append(",createName=").append(createName); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",updateId=").append(updateId); 
        builder.append(",updateName=").append(updateName); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}