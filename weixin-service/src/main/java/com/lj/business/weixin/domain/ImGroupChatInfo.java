package com.lj.business.weixin.domain;

import java.util.Date;

/**
 * 群发设置
 * @author wo510
 *
 */
public class ImGroupChatInfo {
	/**
	 * 编号
	 */
    private String code;

    /**
     * 商户号
     */
    private String merchantNo;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 群发会员编号（,分隔）
     */
    private String memberNos;

    /**
     * 群发会员名称（,分隔）
     */
    private String memberNames;

    /**
     * 群发会员微信（,分隔）
     */
    private String memberNoWxs;


    /**
     * 导购编号
     */
    private String memberNoGm;

    /**
     * 导购名称
     */
    private String memberNameGm;

    /**
     * 导购微信号
     */
    private String noWxGm;

    /**
     * 消息类型：1文本、3图片
     */
    private String type;

    /**
     * 消息状态：1 未发送、2发送成功、3发送失败
     */
    private String status;

    /**
     * 内容
     */
    private String content;
    
    /**
     * 资源路径：语音、图片、视频
     */
    private String resourcesPath;

    /**
     * 群聊类型：1单聊、2群聊
     */
    private Integer chatroomType;

    /**
     * 群聊消息发送人微信
     */
    private String chatroomNoWx;

    /**
     * 聊天助手编号
     */
    private String chatAssistantCode;

    /**
     * 创建时间
     */
    private Date createDate;

    private String remark;

    private String remark2;

    private String remark3;

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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getMemberNos() {
        return memberNos;
    }

    public void setMemberNos(String memberNos) {
        this.memberNos = memberNos == null ? null : memberNos.trim();
    }

    public String getMemberNames() {
        return memberNames;
    }

    public void setMemberNames(String memberNames) {
        this.memberNames = memberNames == null ? null : memberNames.trim();
    }

    public String getMemberNoWxs() {
        return memberNoWxs;
    }

    public void setMemberNoWxs(String memberNoWxs) {
        this.memberNoWxs = memberNoWxs == null ? null : memberNoWxs.trim();
    }

    

    public String getMemberNoGm() {
        return memberNoGm;
    }

    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    public String getMemberNameGm() {
        return memberNameGm;
    }

    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    public String getNoWxGm() {
        return noWxGm;
    }

    public void setNoWxGm(String noWxGm) {
        this.noWxGm = noWxGm == null ? null : noWxGm.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getResourcesPath() {
        return resourcesPath;
    }

    public void setResourcesPath(String resourcesPath) {
        this.resourcesPath = resourcesPath == null ? null : resourcesPath.trim();
    }

    public Integer getChatroomType() {
        return chatroomType;
    }

    public void setChatroomType(Integer chatroomType) {
        this.chatroomType = chatroomType;
    }

    public String getChatroomNoWx() {
        return chatroomNoWx;
    }

    public void setChatroomNoWx(String chatroomNoWx) {
        this.chatroomNoWx = chatroomNoWx == null ? null : chatroomNoWx.trim();
    }

    public String getChatAssistantCode() {
        return chatAssistantCode;
    }

    public void setChatAssistantCode(String chatAssistantCode) {
        this.chatAssistantCode = chatAssistantCode == null ? null : chatAssistantCode.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}