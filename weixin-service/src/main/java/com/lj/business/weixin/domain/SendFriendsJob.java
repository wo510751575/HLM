package com.lj.business.weixin.domain;

import java.util.Date;

public class SendFriendsJob {
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
     * 素材CODE .
     */
    private String materialCode;
    
    /**
     * 素材分类 .
     */
    private String materialCategory;
    
    /**
     * 朋友圈素材类型 .
     */
    private String friendsMaterialType;

    /**
     * 内容 .
     */
    private String content;

    /**
     * 图片地址 .
     */
    private String imgAddr;

    /**
     * 链接地址 .
     */
    private String linkUrl;

    /**
     * 是否自动评论 .
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
     * 执行时间 .
     */
    private Date executeTime;
    
    /**
     * 真正执行时间 .
     */
    private Date realExecuteTime;



	/**
     * 延迟时间(分钟) .
     */
    private Integer delayTimes;

    /**
     * 任务状态 .
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
     * 创建人 .
     */
    private String createName;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注2 .
     */
    private String remark2;

    /**
     * 备注3 .
     */
    private String remark3;

    /**
     * 备注4 .
     */
    private String remark4;
    
    /**
     * 朋友圈类型
     */
    private String type; 
    /**
     * 多图片地址
     */
    private String imgAddrs;
    /**
     * 音乐或视频地址
     */
    private String resourcePath;
    
    /**
     * 用戶的级别
     */
    private String createUserLevel;
    /**
     * 商户发朋友圈Code
     */
    private String merchantJobCode;
    
    public String getMerchantJobCode() {
		return merchantJobCode;
	}

	public void setMerchantJobCode(String merchantJobCode) {
		this.merchantJobCode = merchantJobCode;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getRealExecuteTime() {
		return realExecuteTime;
	}

	public void setRealExecuteTime(Date realExecuteTime) {
		this.realExecuteTime = realExecuteTime;
	}
	
    public String getCreateUserLevel() {
		return createUserLevel;
	}

	public void setCreateUserLevel(String createUserLevel) {
		this.createUserLevel = createUserLevel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImgAddrs() {
		return imgAddrs;
	}

	public void setImgAddrs(String imgAddrs) {
		this.imgAddrs = imgAddrs;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode == null ? null : materialCode.trim();
    }

    public String getMaterialCategory() {
        return materialCategory;
    }

    public void setMaterialCategory(String materialCategory) {
        this.materialCategory = materialCategory == null ? null : materialCategory.trim();
    }

    public String getFriendsMaterialType() {
        return friendsMaterialType;
    }

    public void setFriendsMaterialType(String friendsMaterialType) {
        this.friendsMaterialType = friendsMaterialType == null ? null : friendsMaterialType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr == null ? null : imgAddr.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Integer getAutoComment() {
        return autoComment;
    }

    public void setAutoComment(Integer autoComment) {
        this.autoComment = autoComment;
    }

    public String getAutoContent() {
        return autoContent;
    }

    public void setAutoContent(String autoContent) {
        this.autoContent = autoContent == null ? null : autoContent.trim();
    }

    public String getNoWxs() {
        return noWxs;
    }

    public void setNoWxs(String noWxs) {
        this.noWxs = noWxs == null ? null : noWxs.trim();
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getDelayTimes() {
        return delayTimes;
    }

    public void setDelayTimes(Integer delayTimes) {
        this.delayTimes = delayTimes;
    }

    public Integer getJobState() {
        return jobState;
    }

    public void setJobState(Integer jobState) {
        this.jobState = jobState;
    }

    public String getSentNoWxs() {
        return sentNoWxs;
    }

    public void setSentNoWxs(String sentNoWxs) {
        this.sentNoWxs = sentNoWxs == null ? null : sentNoWxs.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
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

	@Override
	public String toString() {
		return "SendFriendsJob [code=" + code + ", merchantNo=" + merchantNo + ", merchantName=" + merchantName
				+ ", materialCode=" + materialCode + ", materialCategory=" + materialCategory + ", friendsMaterialType="
				+ friendsMaterialType + ", content=" + content + ", imgAddr=" + imgAddr + ", linkUrl=" + linkUrl
				+ ", autoComment=" + autoComment + ", autoContent=" + autoContent + ", noWxs=" + noWxs
				+ ", executeTime=" + executeTime + ", realExecuteTime=" + realExecuteTime + ", delayTimes=" + delayTimes
				+ ", jobState=" + jobState + ", sentNoWxs=" + sentNoWxs + ", createId=" + createId + ", createName="
				+ createName + ", createDate=" + createDate + ", remark=" + remark + ", remark2=" + remark2
				+ ", remark3=" + remark3 + ", remark4=" + remark4 + ", type=" + type + ", imgAddrs=" + imgAddrs
				+ ", resourcePath=" + resourcePath + ", createUserLevel=" + createUserLevel + ", merchantJobCode="
				+ merchantJobCode + "]";
	}

}