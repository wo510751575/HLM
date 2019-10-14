package com.lj.business.st.domain;

import java.math.BigDecimal;
import java.util.Date;

public class PmLabelTotal {
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
     * 商户名称 .
     */
    private String labelId;

    /**
     * 商户名称 .
     */
    private String labelName;

    /**
     * 回应数量 .
     */
    private Integer pmNum;

    /**
     * 客户所占比例 .
     */
    private BigDecimal ratioPm;

    /**
     * 统计维度             商户：MERCHANT             区域：AREA             门店：SHOP             导购：GUID .
     */
    private String dimensionSt;

    /**
     * 创建时间 .
     */
    private Date createDate;

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
     * 商户名称 .
     *
     */
    public String getLabelId() {
        return labelId;
    }

    /**
     * 商户名称 .
     *
     */
    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    /**
     * 商户名称 .
     *
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * 商户名称 .
     *
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }

    /**
     * 回应数量 .
     *
     */
    public Integer getPmNum() {
        return pmNum;
    }

    /**
     * 回应数量 .
     *
     */
    public void setPmNum(Integer pmNum) {
        this.pmNum = pmNum;
    }

    /**
     * 客户所占比例 .
     *
     */
    public BigDecimal getRatioPm() {
        return ratioPm;
    }

    /**
     * 客户所占比例 .
     *
     */
    public void setRatioPm(BigDecimal ratioPm) {
        this.ratioPm = ratioPm;
    }

    /**
     * 统计维度             商户：MERCHANT             区域：AREA             门店：SHOP             导购：GUID .
     *
     */
    public String getDimensionSt() {
        return dimensionSt;
    }

    /**
     * 统计维度             商户：MERCHANT             区域：AREA             门店：SHOP             导购：GUID .
     *
     */
    public void setDimensionSt(String dimensionSt) {
        this.dimensionSt = dimensionSt == null ? null : dimensionSt.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PmLabelTotal [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",memberNameGm=").append(memberNameGm); 
        builder.append(",labelId=").append(labelId); 
        builder.append(",labelName=").append(labelName); 
        builder.append(",pmNum=").append(pmNum); 
        builder.append(",ratioPm=").append(ratioPm); 
        builder.append(",dimensionSt=").append(dimensionSt); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}