package com.lj.business.member.dto.pushconfig;

import java.io.Serializable;
import java.util.Date;

public class UpdateWxPushConfig implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -3002066832487999038L;
    /**
     * CODE .
     */
    private String code;

    /**
     * 微信号 .
     */
    private String noWx;

    /**
     * 状态：USE有效、STOP失效 .
     */
    private String status;

    /**
     * 排序 .
     */
    private Integer sort;

    /**
     * 推送类型：GREET问候语、IMAGE图片、MGR_CARD店长名片、SHARE分享、PA公众号、SP小程序 .
     */
    private String type;

    /**
     * 内容 .
     */
    private String content;

    /**
     * 分享CODE .
     */
    private String shareCode;

    /**
     * 分享标题 .
     */
    private String shareTitle;

    /**
     * 分享描述 .
     */
    private String shareDes;

    /**
     * 分享图标 .
     */
    private String shareIcon;

    /**
     * 分享链接 .
     */
    private String shareUrl;

    /**
     * 推送时机 .
     */
    private String pushTime;

    /**
     * 机构类型：MERCHANT商户、SHOP终端 .
     */
    private String orgType;

    /**
     * 终端编号 .
     */
    

    /**
     * 终端名称 .
     */
    

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新时间 .
     */
    private Date updateDate;

    /**
     * 备注 .
     */
    private String remark;

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
        this.noWx = noWx == null ? null : noWx.trim();
    }

    /**
     * 状态：USE有效、STOP失效 .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态：USE有效、STOP失效 .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 排序 .
     *
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序 .
     *
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 推送类型：GREET问候语、IMAGE图片、MGR_CARD店长名片、SHARE分享、PA公众号、SP小程序 .
     *
     */
    public String getType() {
        return type;
    }

    /**
     * 推送类型：GREET问候语、IMAGE图片、MGR_CARD店长名片、SHARE分享、PA公众号、SP小程序 .
     *
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
     * 分享CODE .
     *
     */
    public String getShareCode() {
        return shareCode;
    }

    /**
     * 分享CODE .
     *
     */
    public void setShareCode(String shareCode) {
        this.shareCode = shareCode == null ? null : shareCode.trim();
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
     * 分享图标 .
     *
     */
    public String getShareIcon() {
        return shareIcon;
    }

    /**
     * 分享图标 .
     *
     */
    public void setShareIcon(String shareIcon) {
        this.shareIcon = shareIcon == null ? null : shareIcon.trim();
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
     * 推送时机 .
     *
     */
    public String getPushTime() {
        return pushTime;
    }

    /**
     * 推送时机 .
     *
     */
    public void setPushTime(String pushTime) {
        this.pushTime = pushTime == null ? null : pushTime.trim();
    }

    /**
     * 机构类型：MERCHANT商户、SHOP终端 .
     *
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * 机构类型：MERCHANT商户、SHOP终端 .
     *
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UpdateWxPushConfig [code=").append(code);
        builder.append(",noWx=").append(noWx); 
        builder.append(",status=").append(status); 
        builder.append(",sort=").append(sort); 
        builder.append(",type=").append(type); 
        builder.append(",content=").append(content); 
        builder.append(",shareCode=").append(shareCode); 
        builder.append(",shareTitle=").append(shareTitle); 
        builder.append(",shareDes=").append(shareDes); 
        builder.append(",shareIcon=").append(shareIcon); 
        builder.append(",shareUrl=").append(shareUrl); 
        builder.append(",pushTime=").append(pushTime); 
        builder.append(",orgType=").append(orgType); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",remark=").append(remark); 
        builder.append("]");
        return builder.toString();
    }
}
