package com.lj.business.member.domain;

import java.util.Date;

public class TmallOrder {
    /** code*/
    private String code;

    /** 商户编号*/
    private String merchantNo;

    /** 下单人姓名*/
    private String memberName;

    /** 下单人手机号*/
    private String mobile;

    /** 下单人旺旺*/
    private String noWw;

    /** 订单号*/
    private String orderNo;

    /** 商品名称*/
    private String productName;

    /** 商品链接*/
    private String productUrl;

    /** 金额*/
    private Long amount;

    /** 下单时间*/
    private String orderDate;

    /** 评论星级*/
    private String commentLevel;

    /** 评论内容*/
    private String commentCtx;

    /** 导入时间*/
    private Date createDate;

    /** 导入人*/
    private String createId;

    /** 备注*/
    private String remark;

    /** 备注2*/
    private String remark2;

    /** 备注3*/
    private String remark3;

    /** 备注4*/
    private String remark4;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getNoWw() {
        return noWw;
    }

    public void setNoWw(String noWw) {
        this.noWw = noWw == null ? null : noWw.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl == null ? null : productUrl.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(String commentLevel) {
        this.commentLevel = commentLevel == null ? null : commentLevel.trim();
    }

    public String getCommentCtx() {
        return commentCtx;
    }

    public void setCommentCtx(String commentCtx) {
        this.commentCtx = commentCtx == null ? null : commentCtx.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }
}