package com.lj.business.cm.friendsLinkMaterial;

import java.io.Serializable;
import java.util.Date;

public class UpdateFriendsLinkMaterial implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 5599203442694712363L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 主题 .
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
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

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getAutoComment() {
		return autoComment;
	}

	public void setAutoComment(Integer autoComment) {
		this.autoComment = autoComment;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateFriendsLinkMaterial [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", title=");
		builder.append(title);
		builder.append(", shareTitle=");
		builder.append(shareTitle);
		builder.append(", content=");
		builder.append(content);
		builder.append(", materialType=");
		builder.append(materialType);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", linkUrl=");
		builder.append(linkUrl);
		builder.append(", autoComment=");
		builder.append(autoComment);
		builder.append(", commentContent=");
		builder.append(commentContent);
		builder.append(", deleteFlag=");
		builder.append(deleteFlag);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append("]");
		return builder.toString();
	}
    
}
