package com.lj.business.cm.domain;

import java.util.Date;

public class VrMaterialType {
    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 导购编号  .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 类型名称 .
     */
    private String typeName;

    /**
     * 类型总数量 .
     */
    private Integer typeCount;

    /**
     * 图片地址 .
     */
    private String imgAddr;

    /**
     * 客户关注度 .
     */
    private Double customerAttentionRate;

    /**
     * 素材维度             商户：MERCHANT             终端：SHOP .
     */
    private String materialDimension;

    /**
     * 显示序号 .
     */
    private Integer showIndex;

    /**
     * 创建人 .
     */
    private String createId;

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
     * 类型名称 .
     *
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 类型名称 .
     *
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * 类型总数量 .
     *
     */
    public Integer getTypeCount() {
        return typeCount;
    }

    /**
     * 类型总数量 .
     *
     */
    public void setTypeCount(Integer typeCount) {
        this.typeCount = typeCount;
    }

    /**
     * 图片地址 .
     *
     */
    public String getImgAddr() {
        return imgAddr;
    }

    /**
     * 图片地址 .
     *
     */
    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr == null ? null : imgAddr.trim();
    }

    /**
     * 客户关注度 .
     *
     */
    public Double getCustomerAttentionRate() {
        return customerAttentionRate;
    }

    /**
     * 客户关注度 .
     *
     */
    public void setCustomerAttentionRate(Double customerAttentionRate) {
        this.customerAttentionRate = customerAttentionRate;
    }

    /**
     * 素材维度             商户：MERCHANT             终端：SHOP .
     *
     */
    public String getMaterialDimension() {
        return materialDimension;
    }

    /**
     * 素材维度             商户：MERCHANT             终端：SHOP .
     *
     */
    public void setMaterialDimension(String materialDimension) {
        this.materialDimension = materialDimension == null ? null : materialDimension.trim();
    }

    /**
     * 显示序号 .
     *
     */
    public Integer getShowIndex() {
        return showIndex;
    }

    /**
     * 显示序号 .
     *
     */
    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
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
        builder.append("VrMaterialType [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",memberNameGm=").append(memberNameGm); 
        builder.append(",typeName=").append(typeName); 
        builder.append(",typeCount=").append(typeCount); 
        builder.append(",imgAddr=").append(imgAddr); 
        builder.append(",customerAttentionRate=").append(customerAttentionRate); 
        builder.append(",materialDimension=").append(materialDimension); 
        builder.append(",showIndex=").append(showIndex); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}