package com.lj.business.member.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddFriendsTaskDetail {
    /**
     *  .
     */
    private String code;

    /**
     * 关联addfriends_task的code .
     */
    private String taskCode;

    /**
     * 电话号码 .
     */
    private String phone;

    /**
     * 用户名称 .
     */
    private String username;

    /**
     * 发送请求话术 .
     */
    private String sendMessage;

    /**
     * 导入时间 .
     */
    private Date createDate;

    /**
     * 执行时间 .
     */
    private Date executeDate;

    /**
     * 发起好友请求时间 .
     */
    private Date addfriendsDate;

    /**
     * 好友回应时间 .
     */
    private Date callbackDate;

    /**
     * 用户微信 .
     */
    private String noWx;

    /**
     *  .
     */
    private String noWxGm;

    /**
     * 1 已启动 2 已暂停 3 执行中 4 已完成 .
     */
    private String status;

    /**
     * 详情说明 .
     */
    private String detail;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;
    /**
     * 编号集合
     */
    private List<String> codes=new ArrayList<String>();
    
    public List<String> getCodes() {
		return codes;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
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
     * 关联addfriends_task的code .
     *
     */
    public String getTaskCode() {
        return taskCode;
    }

    /**
     * 关联addfriends_task的code .
     *
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode == null ? null : taskCode.trim();
    }

    /**
     * 电话号码 .
     *
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话号码 .
     *
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 用户名称 .
     *
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名称 .
     *
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 发送请求话术 .
     *
     */
    public String getSendMessage() {
        return sendMessage;
    }

    /**
     * 发送请求话术 .
     *
     */
    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage == null ? null : sendMessage.trim();
    }

    /**
     * 导入时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 导入时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 执行时间 .
     *
     */
    public Date getExecuteDate() {
        return executeDate;
    }

    /**
     * 执行时间 .
     *
     */
    public void setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
    }

    /**
     * 发起好友请求时间 .
     *
     */
    public Date getAddfriendsDate() {
        return addfriendsDate;
    }

    /**
     * 发起好友请求时间 .
     *
     */
    public void setAddfriendsDate(Date addfriendsDate) {
        this.addfriendsDate = addfriendsDate;
    }

    /**
     * 好友回应时间 .
     *
     */
    public Date getCallbackDate() {
        return callbackDate;
    }

    /**
     * 好友回应时间 .
     *
     */
    public void setCallbackDate(Date callbackDate) {
        this.callbackDate = callbackDate;
    }

    /**
     * 用户微信 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 用户微信 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
    }

    /**
     *  .
     *
     */
    public String getNoWxGm() {
        return noWxGm;
    }

    /**
     *  .
     *
     */
    public void setNoWxGm(String noWxGm) {
        this.noWxGm = noWxGm == null ? null : noWxGm.trim();
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
     * 详情说明 .
     *
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 详情说明 .
     *
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
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
        builder.append("AddFriendsTaskDetail [code=").append(code);
        builder.append(",taskCode=").append(taskCode); 
        builder.append(",phone=").append(phone); 
        builder.append(",username=").append(username); 
        builder.append(",sendMessage=").append(sendMessage); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",executeDate=").append(executeDate); 
        builder.append(",addfriendsDate=").append(addfriendsDate); 
        builder.append(",callbackDate=").append(callbackDate); 
        builder.append(",noWx=").append(noWx); 
        builder.append(",noWxGm=").append(noWxGm); 
        builder.append(",status=").append(status); 
        builder.append(",detail=").append(detail); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append("]");
        return builder.toString();
    }
}