package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AddFriendsTaskDto implements Serializable { 

    /**
     *  .
     */
    private String code;

    /**
     * 任务名称 .
     */
    private String name;

    /**
     * 用于添加好友的微信号,多个用,号分割 .
     */
    private String noWxArrays;

    /**
     * 发送的加好友话术 .
     */
    private String sendMessage;

    /**
     * 共提供多少个电话号码 .
     */
    private Integer totalPhonenum;

    /**
     * 完成个数 .
     */
    private Integer completeNum;

    /**
     *  .
     */
    private Integer successNum;

    /**
     * 1 已启动 2 已暂停 3 执行中 4 已完成 .
     */
    private String status;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新时间 .
     */
    private Date updateDate;

    /**
     *  .
     */
    private Date startDate;

    /**
     *  .
     */
    private Date endDate;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;
    /**
     * 转化率
     */
    private String trans;
    /**
     * 详情列表
     */
    private List<AddFriendsTaskDetailDto> detailList;
    
    public List<AddFriendsTaskDetailDto> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<AddFriendsTaskDetailDto> detailList) {
		this.detailList = detailList;
	}

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	/**
     *  .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     *  .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 任务名称 .
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 任务名称 .
     *
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 用于添加好友的微信号,多个用,号分割 .
     *
     */
    public String getNoWxArrays() {
        return noWxArrays;
    }

    /**
     * 用于添加好友的微信号,多个用,号分割 .
     *
     */
    public void setNoWxArrays(String noWxArrays) {
        this.noWxArrays = noWxArrays == null ? null : noWxArrays.trim();
    }

    /**
     * 发送的加好友话术 .
     *
     */
    public String getSendMessage() {
        return sendMessage;
    }

    /**
     * 发送的加好友话术 .
     *
     */
    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage == null ? null : sendMessage.trim();
    }

    /**
     * 共提供多少个电话号码 .
     *
     */
    public Integer getTotalPhonenum() {
        return totalPhonenum;
    }

    /**
     * 共提供多少个电话号码 .
     *
     */
    public void setTotalPhonenum(Integer totalPhonenum) {
        this.totalPhonenum = totalPhonenum;
    }

    /**
     * 完成个数 .
     *
     */
    public Integer getCompleteNum() {
        return completeNum;
    }

    /**
     * 完成个数 .
     *
     */
    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    /**
     *  .
     *
     */
    public Integer getSuccessNum() {
        return successNum;
    }

    /**
     *  .
     *
     */
    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    /**
     * 1 已启动 2 已暂停 3 执行中 4 已完成 .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 1 已启动 2 已暂停 3 执行中 4 已完成 .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     *  .
     *
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     *  .
     *
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     *  .
     *
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     *  .
     *
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AddFriendsTask [code=").append(code);
        builder.append(",name=").append(name); 
        builder.append(",noWxArrays=").append(noWxArrays); 
        builder.append(",sendMessage=").append(sendMessage); 
        builder.append(",totalPhonenum=").append(totalPhonenum); 
        builder.append(",completeNum=").append(completeNum); 
        builder.append(",successNum=").append(successNum); 
        builder.append(",status=").append(status); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",startDate=").append(startDate); 
        builder.append(",endDate=").append(endDate); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append("]");
        return builder.toString();
    }
}
