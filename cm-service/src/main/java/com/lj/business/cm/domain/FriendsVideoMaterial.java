package com.lj.business.cm.domain;

import java.util.Date;

public class FriendsVideoMaterial {
    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 标题 .
     */
    private String title;

    /**
     * 分享标题 .
     */
    private String shareTitle;

    /**
     * 内容 .
     */
    private String content;

    /**
     * 素材类型 .
     */
    private String materialType;

    /**
     * 封面图 .
     */
    private String imageUrl;

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
    private String commentContent;

    /**
     * 删除标识：0未删除、1已删除 .
     */
    private Integer deleteFlag;

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
     * 标题 .
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题 .
     *
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 分享标题 .
     *
     */
    public String getShareTitle() {
        return shareTitle;
    }

    /**
     * 分享标题 .
     *
     */
    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle == null ? null : shareTitle.trim();
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
     * 素材类型 .
     *
     */
    public String getMaterialType() {
        return materialType;
    }

    /**
     * 素材类型 .
     *
     */
    public void setMaterialType(String materialType) {
        this.materialType = materialType == null ? null : materialType.trim();
    }

    /**
     * 封面图 .
     *
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 封面图 .
     *
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
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
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 自动评论内容 .
     *
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    /**
     * 删除标识：0未删除、1已删除 .
     *
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 删除标识：0未删除、1已删除 .
     *
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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
        builder.append("FriendsVideoMaterial [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",title=").append(title); 
        builder.append(",shareTitle=").append(shareTitle); 
        builder.append(",content=").append(content); 
        builder.append(",materialType=").append(materialType); 
        builder.append(",imageUrl=").append(imageUrl); 
        builder.append(",linkUrl=").append(linkUrl); 
        builder.append(",autoComment=").append(autoComment); 
        builder.append(",commentContent=").append(commentContent); 
        builder.append(",deleteFlag=").append(deleteFlag); 
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