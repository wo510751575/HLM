package com.lj.business.weixin.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImFriendsInfoTempDto implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1034077707198413950L;
	/**
	 * 头像
	 */
	private String headImg;
	
	
	
	public String getHeadImg() {
		return headImg == null ? "" : headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

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
     * 分店编号 .
     */
    

    /**
     * 分店名称 .
     */
    

    /**
     * 终端微信 .
     */
    private String noWxShop;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 微信号 .
     */
    private String noWx;

    /**
     * 微信昵称 .
     */
    private String nickName;



    /**
     * 资源类型 .
     */
    private String sourcetype;

    /**
     * 类型1图文 2纯文字 3分享 4歌曲分享 15视频
              .
     */
    private String type;

    /**
     *  .
     */
    private String shareurl;

    /**
     *  .
     */
    private String sharetitle;

    /**
     * 图片地址（多个时以逗号分隔） .
     */
    private String imgAddr;

    /**
     * 操作人标识：1自己、2客户 .
     */
    private Integer optFlag;

    /**
     * 发送时间 .
     */
    private Date sendTime;

    /**
     * 消息状态：1 发送中、2发送成功、3发送失败 .
     */
    private Integer status;



    /**
     * 发送朋友圈任务CODE .
     */
    private String jobCode;

    /**
     * 自动评论内容 .
     */
    private String autoContent;

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
     * 内容 .
     */
    private String content;
    
    /**
     * 评论列表
     */
    private List<ImCommentInfoDto> comments;
    
    /**
     * 点赞列表
     */
    private List<ImLikeInfoDto> likes;
    

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
        this.code = code;
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
        this.merchantNo = merchantNo;
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
        this.merchantName = merchantName;
    }


    /**
     * 终端微信 .
     *
     */
    public String getNoWxShop() {
        return noWxShop;
    }

    /**
     * 终端微信 .
     *
     */
    public void setNoWxShop(String noWxShop) {
        this.noWxShop = noWxShop;
    }

    /**
     * 客户编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 客户编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * 客户名称 .
     *
     */
    public String getMemberName() {
        return memberName==null ? "" : memberName;
    }

    /**
     * 客户名称 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * 微信号 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 微信号 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx;
    }

    /**
     * 微信昵称 .
     *
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 微信昵称 .
     *
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

   
    /**
     * 资源类型 .
     *
     */
    public String getSourcetype() {
        return sourcetype;
    }

    /**
     * 资源类型 .
     *
     */
    public void setSourcetype(String sourcetype) {
        this.sourcetype = sourcetype;
    }

    /**
     * 类型1图文 2纯文字 3分享 4歌曲分享 15视频
              .
     *
     */
    public String getType() {
        return type;
    }

    /**
     * 类型1图文 2纯文字 3分享 4歌曲分享 15视频
              .
     *
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *  .
     *
     */
    public String getShareurl() {
        return shareurl;
    }

    /**
     *  .
     *
     */
    public void setShareurl(String shareurl) {
        this.shareurl = shareurl;
    }

    /**
     *  .
     *
     */
    public String getSharetitle() {
        return sharetitle;
    }

    /**
     *  .
     *
     */
    public void setSharetitle(String sharetitle) {
        this.sharetitle = sharetitle;
    }

    /**
     * 图片地址（多个时以逗号分隔） .
     *
     */
    public String getImgAddr() {
        return imgAddr==null ? "" : imgAddr;
    }

    /**
     * 图片地址（多个时以逗号分隔） .
     *
     */
    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    /**
     * 操作人标识：1自己、2客户 .
     *
     */
    public Integer getOptFlag() {
        return optFlag;
    }

    /**
     * 操作人标识：1自己、2客户 .
     *
     */
    public void setOptFlag(Integer optFlag) {
        this.optFlag = optFlag;
    }

    /**
     * 发送时间 .
     *
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 发送时间 .
     *
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 消息状态：1 发送中、2发送成功、3发送失败 .
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 消息状态：1 发送中、2发送成功、3发送失败 .
     *
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    
    /**
     * 发送朋友圈任务CODE .
     *
     */
    public String getJobCode() {
        return jobCode;
    }

    /**
     * 发送朋友圈任务CODE .
     *
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
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
        this.autoContent = autoContent;
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
        this.remark = remark;
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
        this.remark2 = remark2;
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
        this.remark3 = remark3;
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
        this.remark4 = remark4;
    }

    /**
     * 内容 .
     *
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 内容 .
     *
     */
    public void setContent(String content) {
        this.content = content;
    }
    

    public List<ImCommentInfoDto> getComments() {
		return comments==null ? new ArrayList<ImCommentInfoDto>() : comments;
	}

	public void setComments(List<ImCommentInfoDto> comments) {
		this.comments = comments;
	}

	public List<ImLikeInfoDto> getLikes() {
		return likes==null ? new ArrayList<ImLikeInfoDto>() : likes;
	}

	public void setLikes(List<ImLikeInfoDto> likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImFriendsInfoTempDto [headImg=");
		builder.append(headImg);
		builder.append(", code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", sourcetype=");
		builder.append(sourcetype);
		builder.append(", type=");
		builder.append(type);
		builder.append(", shareurl=");
		builder.append(shareurl);
		builder.append(", sharetitle=");
		builder.append(sharetitle);
		builder.append(", imgAddr=");
		builder.append(imgAddr);
		builder.append(", optFlag=");
		builder.append(optFlag);
		builder.append(", sendTime=");
		builder.append(sendTime);
		builder.append(", status=");
		builder.append(status);
		builder.append(", jobCode=");
		builder.append(jobCode);
		builder.append(", autoContent=");
		builder.append(autoContent);
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
		builder.append(", content=");
		builder.append(content);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", likes=");
		builder.append(likes);
		builder.append("]");
		return builder.toString();
	}
}
