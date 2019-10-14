package com.lj.business.member.dto.pushconfig;

import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindWxPushConfigPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1211911217035253996L; 

    /**
     * 微信号 .
     */
    private String noWx;

    /**
     * 状态：USE有效、STOP失效 .
     */
    private String status;

    /**
     * 推送类型：GREET问候语、IMAGE图片、MGR_CARD店长名片、SHARE分享、PA公众号、SP小程序 .
     */
    private String type;

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
     * 商户编号 .
     */
    private String merchantNo;

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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWxPushConfigPage [noWx=");
		builder.append(noWx);
		builder.append(", status=");
		builder.append(status);
		builder.append(", type=");
		builder.append(type);
		builder.append(", pushTime=");
		builder.append(pushTime);
		builder.append(", orgType=");
		builder.append(orgType);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}

}
