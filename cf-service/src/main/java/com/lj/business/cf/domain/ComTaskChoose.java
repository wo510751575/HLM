package com.lj.business.cf.domain;

import java.util.Date;

public class ComTaskChoose {
    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 导购编号  .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 项目CODE .
     */
    private String codeList;

    /**
     * 项目名称 .
     */
    private String nameList;

    /**
     * 状态              启用：Y             停用：N              .
     */
    private String status;

    /**
     * 沟通任务类型 完善资料：IMPROVE  其他：OTHER .
     */
    private String comTaskType;

    /**
     * 跟进频率值 .
     */
    private String freValue;

    /**
     * 排序 .
     */
    private Integer seq;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark4;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark2;

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
     * 导购编号  .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号  .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 项目CODE .
     *
     */
    public String getCodeList() {
        return codeList;
    }

    /**
     * 项目CODE .
     *
     */
    public void setCodeList(String codeList) {
        this.codeList = codeList == null ? null : codeList.trim();
    }

    /**
     * 项目名称 .
     *
     */
    public String getNameList() {
        return nameList;
    }

    /**
     * 项目名称 .
     *
     */
    public void setNameList(String nameList) {
        this.nameList = nameList == null ? null : nameList.trim();
    }

    /**
     * 状态              启用：Y             停用：N              .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态              启用：Y             停用：N              .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 沟通任务类型 完善资料：IMPROVE  其他：OTHER .
     *
     */
    public String getComTaskType() {
        return comTaskType;
    }

    /**
     * 沟通任务类型 完善资料：IMPROVE  其他：OTHER .
     *
     */
    public void setComTaskType(String comTaskType) {
        this.comTaskType = comTaskType == null ? null : comTaskType.trim();
    }

    /**
     * 跟进频率值 .
     *
     */
    public String getFreValue() {
        return freValue;
    }

    /**
     * 跟进频率值 .
     *
     */
    public void setFreValue(String freValue) {
        this.freValue = freValue == null ? null : freValue.trim();
    }

    /**
     * 排序 .
     *
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 排序 .
     *
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ComTaskChoose [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",memberNameGm=").append(memberNameGm); 
        builder.append(",codeList=").append(codeList); 
        builder.append(",nameList=").append(nameList); 
        builder.append(",status=").append(status); 
        builder.append(",comTaskType=").append(comTaskType); 
        builder.append(",freValue=").append(freValue); 
        builder.append(",seq=").append(seq); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark4=").append(remark4); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark2=").append(remark2); 
        builder.append("]");
        return builder.toString();
    }
}