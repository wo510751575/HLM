package com.ye.business.ad.domain;

import java.util.Date;

public class RwUserBeansChange {
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
    private Integer oldBeansSum;

    /**
     * 已使用豆子 .
     */
    private Integer oldBeansUse;

    /**
     * 冻结豆子 .
     */
    private Integer oldBeansFreeze;

    /**
     * 剩余正常豆子：结算规则：总充值-已使用-冻结=剩余 .
     */
    private Integer oldBeansNormal;

    /**
     * 总充值豆子：价格转换：1:100(元/豆子) .
     */
    private Integer nowBeansSum;

    /**
     * 已使用豆子 .
     */
    private Integer nowBeansUse;

    /**
     * 冻结豆子 .
     */
    private Integer nowBeansFreeze;

    /**
     * 剩余正常豆子：结算规则：总充值-已使用-冻结=剩余 .
     */
    private Integer nowBeansNormal;

    /**
     * 使用数量 .
     */
    private Integer beansNum;

    /**
     * recharge-充值；ad-广告扣费；adre-广告回退； .
     */
    private String type;

    /**
     * 状态：normal-正常；freeze-冻结； .
     */
    private String status;

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
    public Integer getOldBeansSum() {
        return oldBeansSum;
    }

    /**
     * 总充值豆子：价格转换：1:100(元/豆子) .
     *
     */
    public void setOldBeansSum(Integer oldBeansSum) {
        this.oldBeansSum = oldBeansSum;
    }

    /**
     * 已使用豆子 .
     *
     */
    public Integer getOldBeansUse() {
        return oldBeansUse;
    }

    /**
     * 已使用豆子 .
     *
     */
    public void setOldBeansUse(Integer oldBeansUse) {
        this.oldBeansUse = oldBeansUse;
    }

    /**
     * 冻结豆子 .
     *
     */
    public Integer getOldBeansFreeze() {
        return oldBeansFreeze;
    }

    /**
     * 冻结豆子 .
     *
     */
    public void setOldBeansFreeze(Integer oldBeansFreeze) {
        this.oldBeansFreeze = oldBeansFreeze;
    }

    /**
     * 剩余正常豆子：结算规则：总充值-已使用-冻结=剩余 .
     *
     */
    public Integer getOldBeansNormal() {
        return oldBeansNormal;
    }

    /**
     * 剩余正常豆子：结算规则：总充值-已使用-冻结=剩余 .
     *
     */
    public void setOldBeansNormal(Integer oldBeansNormal) {
        this.oldBeansNormal = oldBeansNormal;
    }

    /**
     * 总充值豆子：价格转换：1:100(元/豆子) .
     *
     */
    public Integer getNowBeansSum() {
        return nowBeansSum;
    }

    /**
     * 总充值豆子：价格转换：1:100(元/豆子) .
     *
     */
    public void setNowBeansSum(Integer nowBeansSum) {
        this.nowBeansSum = nowBeansSum;
    }

    /**
     * 已使用豆子 .
     *
     */
    public Integer getNowBeansUse() {
        return nowBeansUse;
    }

    /**
     * 已使用豆子 .
     *
     */
    public void setNowBeansUse(Integer nowBeansUse) {
        this.nowBeansUse = nowBeansUse;
    }

    /**
     * 冻结豆子 .
     *
     */
    public Integer getNowBeansFreeze() {
        return nowBeansFreeze;
    }

    /**
     * 冻结豆子 .
     *
     */
    public void setNowBeansFreeze(Integer nowBeansFreeze) {
        this.nowBeansFreeze = nowBeansFreeze;
    }

    /**
     * 剩余正常豆子：结算规则：总充值-已使用-冻结=剩余 .
     *
     */
    public Integer getNowBeansNormal() {
        return nowBeansNormal;
    }

    /**
     * 剩余正常豆子：结算规则：总充值-已使用-冻结=剩余 .
     *
     */
    public void setNowBeansNormal(Integer nowBeansNormal) {
        this.nowBeansNormal = nowBeansNormal;
    }

    /**
     * 使用数量 .
     *
     */
    public Integer getBeansNum() {
        return beansNum;
    }

    /**
     * 使用数量 .
     *
     */
    public void setBeansNum(Integer beansNum) {
        this.beansNum = beansNum;
    }

    /**
     * recharge-充值；ad-广告扣费；adre-广告回退； .
     *
     */
    public String getType() {
        return type;
    }

    /**
     * recharge-充值；ad-广告扣费；adre-广告回退； .
     *
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 状态：normal-正常；freeze-冻结； .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态：normal-正常；freeze-冻结； .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        builder.append("RwUserBeansChange [code=").append(code);
        builder.append(",memberNo=").append(memberNo); 
        builder.append(",memberName=").append(memberName); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",oldBeansSum=").append(oldBeansSum); 
        builder.append(",oldBeansUse=").append(oldBeansUse); 
        builder.append(",oldBeansFreeze=").append(oldBeansFreeze); 
        builder.append(",oldBeansNormal=").append(oldBeansNormal); 
        builder.append(",nowBeansSum=").append(nowBeansSum); 
        builder.append(",nowBeansUse=").append(nowBeansUse); 
        builder.append(",nowBeansFreeze=").append(nowBeansFreeze); 
        builder.append(",nowBeansNormal=").append(nowBeansNormal); 
        builder.append(",beansNum=").append(beansNum); 
        builder.append(",type=").append(type); 
        builder.append(",status=").append(status); 
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