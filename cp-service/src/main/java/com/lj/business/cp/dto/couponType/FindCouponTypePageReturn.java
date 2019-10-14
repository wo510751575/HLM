package com.lj.business.cp.dto.couponType;

import java.io.Serializable;
import java.util.Date;

public class FindCouponTypePageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1278738961160030010L; 
	 /**
     * CODE .
     */
    private String code;

    /**
     * 优惠券类型
             优惠券：COUPON
             现金券：CASH
             折扣券：DISCOUNT .
     */
    private String couponType;

    /**
     * 优惠券类型说明 .
     */
    private String couponRemark;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 是否启用
             启用：YES
             不启用：NO .
     */
    private String useEnable;

    /**
     * 更新人 .
     */
    private String updateId;

    /**
     * 更新时间 .
     */
    private Date updateDate;

    /**
     * 创建人 .
     */
    private String createId;

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
     * 优惠券类型
             优惠券：COUPON
             现金券：CASH
             折扣券：DISCOUNT .
     *
     */
    public String getCouponType() {
        return couponType;
    }

    /**
     * 优惠券类型
             优惠券：COUPON
             现金券：CASH
             折扣券：DISCOUNT .
     *
     */
    public void setCouponType(String couponType) {
        this.couponType = couponType == null ? null : couponType.trim();
    }

    /**
     * 优惠券类型说明 .
     *
     */
    public String getCouponRemark() {
        return couponRemark;
    }

    /**
     * 优惠券类型说明 .
     *
     */
    public void setCouponRemark(String couponRemark) {
        this.couponRemark = couponRemark == null ? null : couponRemark.trim();
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
     * 是否启用
             启用：YES
             不启用：NO .
     *
     */
    public String getUseEnable() {
        return useEnable;
    }

    /**
     * 是否启用
             启用：YES
             不启用：NO .
     *
     */
    public void setUseEnable(String useEnable) {
        this.useEnable = useEnable == null ? null : useEnable.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CouponType [code=").append(code);
        builder.append(",couponType=").append(couponType); 
        builder.append(",couponRemark=").append(couponRemark); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",useEnable=").append(useEnable); 
        builder.append(",updateId=").append(updateId); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }

}
