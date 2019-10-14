package com.lj.business.weixin.domain;

import java.util.Date;

public class ImChatInfoTemp {
    /**
     * CODE .
     */
    private String code;

    /**
     * 终端微信号 .
     */
    private String noWxShop;

    /**
     * 客户微信 .
     */
    private String noWx;

    /**
     * 分店编号 .
     */
    

    /**
     * 分店名称 .
     */
    

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 消息类型：1文本、3图片、34语音、43视频、42名片、47图片表情、48定位 49分享、50视频聊天、10000系统信息 .
     */
    private String type;

    /**
     * 聊天时间，发送端发送时间 .
     */
    private Date chatTime;

    /**
     * 资源路径：语音、图片、视频 .
     */
    private String resourcesPath;

    /**
     * 分享标题 .
     */
    private String shareTitle;

    /**
     * 分享描述 .
     */
    private String shareDes;

    /**
     * 分享链接 .
     */
    private String shareUrl;

    /**
     * 手机串号 .
     */
    private String imei;

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
     * 终端微信号 .
     *
     */
    public String getNoWxShop() {
        return noWxShop;
    }

    /**
     * 终端微信号 .
     *
     */
    public void setNoWxShop(String noWxShop) {
        this.noWxShop = noWxShop == null ? null : noWxShop.trim();
    }

    /**
     * 客户微信 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 客户微信 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
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
     * 消息类型：1文本、3图片、34语音、43视频、42名片、47图片表情、48定位 49分享、50视频聊天、10000系统信息 .
     *
     */
    public String getType() {
        return type;
    }

    /**
     * 消息类型：1文本、3图片、34语音、43视频、42名片、47图片表情、48定位 49分享、50视频聊天、10000系统信息 .
     *
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 聊天时间，发送端发送时间 .
     *
     */
    public Date getChatTime() {
        return chatTime;
    }

    /**
     * 聊天时间，发送端发送时间 .
     *
     */
    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }

    /**
     * 资源路径：语音、图片、视频 .
     *
     */
    public String getResourcesPath() {
        return resourcesPath;
    }

    /**
     * 资源路径：语音、图片、视频 .
     *
     */
    public void setResourcesPath(String resourcesPath) {
        this.resourcesPath = resourcesPath == null ? null : resourcesPath.trim();
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
     * 分享描述 .
     *
     */
    public String getShareDes() {
        return shareDes;
    }

    /**
     * 分享描述 .
     *
     */
    public void setShareDes(String shareDes) {
        this.shareDes = shareDes == null ? null : shareDes.trim();
    }

    /**
     * 分享链接 .
     *
     */
    public String getShareUrl() {
        return shareUrl;
    }

    /**
     * 分享链接 .
     *
     */
    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl == null ? null : shareUrl.trim();
    }

    /**
     * 手机串号 .
     *
     */
    public String getImei() {
        return imei;
    }

    /**
     * 手机串号 .
     *
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ImChatInfoTemp [code=").append(code);
        builder.append(",noWxShop=").append(noWxShop); 
        builder.append(",noWx=").append(noWx); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",type=").append(type); 
        builder.append(",chatTime=").append(chatTime); 
        builder.append(",resourcesPath=").append(resourcesPath); 
        builder.append(",shareTitle=").append(shareTitle); 
        builder.append(",shareDes=").append(shareDes); 
        builder.append(",shareUrl=").append(shareUrl); 
        builder.append(",imei=").append(imei); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append(",content=").append(content); 
        builder.append("]");
        return builder.toString();
    }
}