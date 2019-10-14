package com.lj.business.cp.dto.couponRule;

import java.io.Serializable;

public class SendCouponAfterAddWxFriends implements Serializable {
    private static final long serialVersionUID = 344988361681869814L;

    /**
     * 终端微信，非空
     */
    private String noWxShop;
    
    /**
     * 客户微信，非空
     */
    private String noWx;
    
    /**
     * 添加微信好友CODE
     */
    private String addFriendsCode;
    
    /**
     * 分享标题
     */
    private String shareTitle;
    
    /**
     * 分享描述
     */
    private String shareDes;
    
    /**
     * 分享链接
     */
    private String shareUrl;
    
    /**
     * 商户编号
     */
    private String merchantNo;
    /**
     * 终端编号
     */
    

    public String getNoWxShop() {
        return noWxShop;
    }

    public void setNoWxShop(String noWxShop) {
        this.noWxShop = noWxShop;
    }

    public String getNoWx() {
        return noWx;
    }

    public void setNoWx(String noWx) {
        this.noWx = noWx;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDes() {
        return shareDes;
    }

    public void setShareDes(String shareDes) {
        this.shareDes = shareDes;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getAddFriendsCode() {
        return addFriendsCode;
    }

    public void setAddFriendsCode(String addFriendsCode) {
        this.addFriendsCode = addFriendsCode;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SendCouponAfterAddWxFriends [noWxShop=");
        builder.append(noWxShop);
        builder.append(", noWx=");
        builder.append(noWx);
        builder.append(", addFriendsCode=");
        builder.append(addFriendsCode);
        builder.append(", shareTitle=");
        builder.append(shareTitle);
        builder.append(", shareDes=");
        builder.append(shareDes);
        builder.append(", shareUrl=");
        builder.append(shareUrl);
        builder.append(", merchantNo=");
        builder.append(merchantNo);
        builder.append("]");
        return builder.toString();
    }

}
