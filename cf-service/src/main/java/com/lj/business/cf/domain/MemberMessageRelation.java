package com.lj.business.cf.domain;

import java.util.Date;

public class MemberMessageRelation {
    /**
     * CODE .
     */
    private String code;

    /**
     * 消息编号 .
     */
    private String msgNo;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 用户编号 .
     */
    private String memberNo;

    /**
     * 用户姓名 .
     */
    private String memberName;

    /**
     * 用户微信号 .
     */
    private String memberWxNo;

    /**
     * 推送时间 .
     */
    private Date pushDate;

    /**
     * 推送成功时间 .
     */
    private Date successDate;

    /**
     * 消息状态             有效：VALID             无效：INVALID .
     */
    private String msgStatus;

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
     * 消息编号 .
     *
     */
    public String getMsgNo() {
        return msgNo;
    }

    /**
     * 消息编号 .
     *
     */
    public void setMsgNo(String msgNo) {
        this.msgNo = msgNo == null ? null : msgNo.trim();
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
     * 导购编号 .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号 .
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
     * 用户编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 用户编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 用户姓名 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 用户姓名 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 用户微信号 .
     *
     */
    public String getMemberWxNo() {
        return memberWxNo;
    }

    /**
     * 用户微信号 .
     *
     */
    public void setMemberWxNo(String memberWxNo) {
        this.memberWxNo = memberWxNo == null ? null : memberWxNo.trim();
    }

    /**
     * 推送时间 .
     *
     */
    public Date getPushDate() {
        return pushDate;
    }

    /**
     * 推送时间 .
     *
     */
    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    /**
     * 推送成功时间 .
     *
     */
    public Date getSuccessDate() {
        return successDate;
    }

    /**
     * 推送成功时间 .
     *
     */
    public void setSuccessDate(Date successDate) {
        this.successDate = successDate;
    }

    /**
     * 消息状态             有效：VALID             无效：INVALID .
     *
     */
    public String getMsgStatus() {
        return msgStatus;
    }

    /**
     * 消息状态             有效：VALID             无效：INVALID .
     *
     */
    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus == null ? null : msgStatus.trim();
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
        builder.append("MemberMessageRelation [code=").append(code);
        builder.append(",msgNo=").append(msgNo); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",memberNameGm=").append(memberNameGm); 
        builder.append(",memberNo=").append(memberNo); 
        builder.append(",memberName=").append(memberName); 
        builder.append(",memberWxNo=").append(memberWxNo); 
        builder.append(",pushDate=").append(pushDate); 
        builder.append(",successDate=").append(successDate); 
        builder.append(",msgStatus=").append(msgStatus); 
        builder.append(",updateId=").append(updateId); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}