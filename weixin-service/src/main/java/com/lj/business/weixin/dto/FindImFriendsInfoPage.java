package com.lj.business.weixin.dto;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

public class FindImFriendsInfoPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6289589431504291978L;
	
	 /**
     * 朋友圈ID .
     */
    private String friendsId;

    /**
     * 商户编号 .
     */
    private String merchantNo;
    
    /**
     * 分店编号 .
     */
    
    
    /**
     * 微信号 .
     */
    private String noWx;
    
    /**
     * 客户编号 .
     */
    private String memberNo;
    
    /**
     * 发送时间 .
     */
    private Date sendTime;

    /**
     * 朋友圈编号
     */
	private String firendsCode;

	
	private String noWxShop;
	
	
	/**
	 * 朋友圈编号
	 */
	private String commentCode;
	
	
	
    /**
     * 操作人标识：1自己、2客户 .
     */
    private Integer optFlag;
    
    /**
     * 消息状态
     */
    private String status;
    
    private String memberNoGm;


    private String appReadFlag;

    private String webReadFlag;
    
    /**
     * 是否查询待回复标识：1是、2否 .
     */
    private Integer toReplyFlag;
    
private String content;
    


	/*
     * 发送朋友圈任务CODE
     */
    private String jobCode;
    
    //用于筛选日期
    private Date startTime;
 
    private Date endTime;
    
    public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getAppReadFlag() {
		return appReadFlag;
	}

	public void setAppReadFlag(String appReadFlag) {
		this.appReadFlag = appReadFlag;
	}

	public String getWebReadFlag() {
		return webReadFlag;
	}

	public void setWebReadFlag(String webReadFlag) {
		this.webReadFlag = webReadFlag;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public Integer getOptFlag() {
		return optFlag;
	}

	public void setOptFlag(Integer optFlag) {
		this.optFlag = optFlag;
	}

	public String getFriendsId() {
		return friendsId;
	}

	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}


	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getFirendsCode() {
		return firendsCode;
	}

	public void setFirendsCode(String firendsCode) {
		this.firendsCode = firendsCode;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}
	
	

	public String getNoWxShop() {
		return noWxShop;
	}

	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getToReplyFlag() {
        return toReplyFlag;
    }

    public void setToReplyFlag(Integer toReplyFlag) {
        this.toReplyFlag = toReplyFlag;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindImFriendsInfoPage [friendsId=");
		builder.append(friendsId);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", sendTime=");
		builder.append(sendTime);
		builder.append(", firendsCode=");
		builder.append(firendsCode);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append(", commentCode=");
		builder.append(commentCode);
		builder.append(", optFlag=");
		builder.append(optFlag);
		builder.append(", status=");
		builder.append(status);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", appReadFlag=");
		builder.append(appReadFlag);
		builder.append(", webReadFlag=");
		builder.append(webReadFlag);
		builder.append(", toReplyFlag=");
		builder.append(toReplyFlag);
		builder.append(", jobCode=");
		builder.append(jobCode);
		builder.append("]");
		return builder.toString();
	}

}
