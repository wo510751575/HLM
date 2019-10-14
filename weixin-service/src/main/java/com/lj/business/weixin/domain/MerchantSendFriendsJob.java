package com.lj.business.weixin.domain;

import java.util.Date;

public class MerchantSendFriendsJob {
    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 内容 .
     */
    private String content;

    /**
     * 图片地址（多个时以逗号分隔） .
     */
    private String imgAddr;

    /**
     * 链接地址 .
     */
    private String linkUrl;

    /**
     * 是否自动评论：0否、1是 .
     */
    private Integer autoComment;

    /**
     * 自动评论内容 .
     */
    private String autoContent;

    /**
     * 发送微信 .
     */
    private String noWxs;

    /**
     * 预定执行时间 .
     */
    private Date executeTime;

    /**
     * 实际执行时间 .
     */
    private Date realExecuteTime;

    /**
     * 任务状态：1创建、2执行中、3执行完成 .
     */
    private Integer jobState;

    /**
     * 已发送微信 .
     */
    private String sentNoWxs;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建人用户级别 .
     */
    private String createUserLevel;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 朋友圈类型,1图文，2视频，3文章  .
     */
    private String type;

    /**
     * 分享标题 .
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
     * 创建人 .
     */
    private String createName;

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
     * 内容 .
     *
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容 .
     *
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 图片地址（多个时以逗号分隔） .
     *
     */
    public String getImgAddr() {
        return imgAddr;
    }

    /**
     * 图片地址（多个时以逗号分隔） .
     *
     */
    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr == null ? null : imgAddr.trim();
    }

    /**
     * 链接地址 .
     *
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 链接地址 .
     *
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    /**
     * 是否自动评论：0否、1是 .
     *
     */
    public Integer getAutoComment() {
        return autoComment;
    }

    /**
     * 是否自动评论：0否、1是 .
     *
     */
    public void setAutoComment(Integer autoComment) {
        this.autoComment = autoComment;
    }

    /**
     * 自动评论内容 .
     *
     */
    public String getAutoContent() {
        return autoContent;
    }

    /**
     * 自动评论内容 .
     *
     */
    public void setAutoContent(String autoContent) {
        this.autoContent = autoContent == null ? null : autoContent.trim();
    }

    /**
     * 发送微信 .
     *
     */
    public String getNoWxs() {
        return noWxs;
    }

    /**
     * 发送微信 .
     *
     */
    public void setNoWxs(String noWxs) {
        this.noWxs = noWxs == null ? null : noWxs.trim();
    }

    /**
     * 预定执行时间 .
     *
     */
    public Date getExecuteTime() {
        return executeTime;
    }

    /**
     * 预定执行时间 .
     *
     */
    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    /**
     * 实际执行时间 .
     *
     */
    public Date getRealExecuteTime() {
        return realExecuteTime;
    }

    /**
     * 实际执行时间 .
     *
     */
    public void setRealExecuteTime(Date realExecuteTime) {
        this.realExecuteTime = realExecuteTime;
    }

    /**
     * 任务状态：1创建、2执行中、3执行完成 .
     *
     */
    public Integer getJobState() {
        return jobState;
    }

    /**
     * 任务状态：1创建、2执行中、3执行完成 .
     *
     */
    public void setJobState(Integer jobState) {
        this.jobState = jobState;
    }

    /**
     * 已发送微信 .
     *
     */
    public String getSentNoWxs() {
        return sentNoWxs;
    }

    /**
     * 已发送微信 .
     *
     */
    public void setSentNoWxs(String sentNoWxs) {
        this.sentNoWxs = sentNoWxs == null ? null : sentNoWxs.trim();
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
     * 创建人用户级别 .
     *
     */
    public String getCreateUserLevel() {
        return createUserLevel;
    }

    /**
     * 创建人用户级别 .
     *
     */
    public void setCreateUserLevel(String createUserLevel) {
        this.createUserLevel = createUserLevel == null ? null : createUserLevel.trim();
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
     * 朋友圈类型,1图文，2视频，3文章  .
     *
     */
    public String getType() {
        return type;
    }

    /**
     * 朋友圈类型,1图文，2视频，3文章  .
     *
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 分享标题 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 分享标题 .
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MerchantSendFriendsJob [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",content=").append(content); 
        builder.append(",imgAddr=").append(imgAddr); 
        builder.append(",linkUrl=").append(linkUrl); 
        builder.append(",autoComment=").append(autoComment); 
        builder.append(",autoContent=").append(autoContent); 
        builder.append(",noWxs=").append(noWxs); 
        builder.append(",executeTime=").append(executeTime); 
        builder.append(",realExecuteTime=").append(realExecuteTime); 
        builder.append(",jobState=").append(jobState); 
        builder.append(",sentNoWxs=").append(sentNoWxs); 
        builder.append(",createId=").append(createId); 
        builder.append(",createUserLevel=").append(createUserLevel); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",type=").append(type); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append(",createName=").append(createName); 
        builder.append("]");
        return builder.toString();
    }
}