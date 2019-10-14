package com.lj.business.st.dto.ClientInterestRpt;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AddClientInterestRpt implements Serializable { 

     /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = 1L;
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
     * 兴趣CODE .
     */
    private String codeInterest;

    /**
     * 兴趣名称 .
     */
    private String interestName;

    /**
     * 所占比例 .
     */
    private BigDecimal ratioLine;

    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP
             导购：GUID .
     */
    private String dimensionSt;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 兴趣数
     */
    private Integer numInterest;

    /**
     * 区域
     */
    private String areaCode;

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
     * 兴趣CODE .
     *
     */
    public String getCodeInterest() {
        return codeInterest;
    }

    /**
     * 兴趣CODE .
     *
     */
    public void setCodeInterest(String codeInterest) {
        this.codeInterest = codeInterest == null ? null : codeInterest.trim();
    }

    /**
     * 兴趣名称 .
     *
     */
    public String getInterestName() {
        return interestName;
    }

    /**
     * 兴趣名称 .
     *
     */
    public void setInterestName(String interestName) {
        this.interestName = interestName == null ? null : interestName.trim();
    }

    /**
     * 所占比例 .
     *
     */
    public BigDecimal getRatioLine() {
        return ratioLine;
    }

    /**
     * 所占比例 .
     *
     */
    public void setRatioLine(BigDecimal ratioLine) {
        this.ratioLine = ratioLine;
    }

    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP
             导购：GUID .
     *
     */
    public String getDimensionSt() {
        return dimensionSt;
    }

    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP
             导购：GUID .
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

    public Integer getNumInterest() {
        return numInterest;
    }

    public void setNumInterest(Integer numInterest) {
        this.numInterest = numInterest;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClientInterestRpt [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",memberNameGm=").append(memberNameGm); 
        builder.append(",codeInterest=").append(codeInterest); 
        builder.append(",interestName=").append(interestName); 
        builder.append(",ratioLine=").append(ratioLine); 
        builder.append(",dimensionSt=").append(dimensionSt); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
    
}
