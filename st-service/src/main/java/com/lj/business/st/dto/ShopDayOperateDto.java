package com.lj.business.st.dto;

import java.io.Serializable;

/**
 * The Class ShopDayOperateDto.
 */
public class ShopDayOperateDto implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7157686048007210706L;
	
	/**
     * 商户编号 .
     */
    private String merchantNo;
    
    /**
     * 分店编号 .
     */
    private String shopNo;
    
    /**
     * 分店名称 .
     */
    private String shopName;
	
	/**
	 * 客户总数 .
	 */
	private int numPm;
	
	/**
     * 意向客户数量 .
     */
    private int numPmIntention;
    
    /**
     * 非意向客户数量 .
     */
    private int numPmNoIntention;
    
    /**
	 * 成单客户总数 .
	 */
	private int numOrderPm;
	
	/**
     * 暂停跟进客户数量 .
     */
    private int numPmAbandon;
    
    /**
     * 未分组客户数量 .
     */
    private int numPmUngroup;
    
    /**
     * 成单率 .
     */
    private int orderRatio;
    
    /**
     * 销售额 .
     */
    private double sale;
    
    /**
     * 跟进客户数量 .
     */
    private long numPmCf;
    
    /**
     * 最高跟进客户次数 .
     */
    private int maxPmCfNum;
    
    /**
     * 业务任务产生量 .
     */
    private int numComTaskProduce;
    
    /**
     * 业务任务完成量 .
     */
    private int numComTaskComplete;
    
    /**
     * 社交任务产生量 .
     */
    private int numSocialTaskProduce;
    
    /**
     * 社交任务完成量 .
     */
    private int numSocialTaskComplete;
    
    /**
     * 邀约任务完成量 .
     */
    private int numInviteTaskComplete;
    
    /**
     * 应邀客户量 .
     */
    private int isInvitePmNum;
    
    /**
     * 到店客户量 .
     */
    private int clientExpPmNum;
    
    /**
     * 微信聊天次数 .
     */
    private int numWeChat;
    
    /**
     * 通话次数 .
     */
    private int numCall;
    
    /**
     * 通话时长 .
     */
    private long callTime ;

	/**
	 * Gets the 商户编号 .
	 *
	 * @return the 商户编号 
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号 .
	 *
	 * @param merchantNo the new 商户编号 
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the 分店编号 .
	 *
	 * @return the 分店编号 
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * Sets the 分店编号 .
	 *
	 * @param shopNo the new 分店编号 
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/**
	 * Gets the 分店名称 .
	 *
	 * @return the 分店名称 
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * Sets the 分店名称 .
	 *
	 * @param shopName the new 分店名称 
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * Gets the 客户总数 .
	 *
	 * @return the 客户总数 
	 */
	public int getNumPm() {
		return numPm;
	}

	/**
	 * Sets the 客户总数 .
	 *
	 * @param numPm the new 客户总数 
	 */
	public void setNumPm(int numPm) {
		this.numPm = numPm;
	}

	/**
	 * Gets the 意向客户数量 .
	 *
	 * @return the 意向客户数量 
	 */
	public int getNumPmIntention() {
		return numPmIntention;
	}

	/**
	 * Sets the 意向客户数量 .
	 *
	 * @param numPmIntention the new 意向客户数量 
	 */
	public void setNumPmIntention(int numPmIntention) {
		this.numPmIntention = numPmIntention;
	}

	/**
	 * Gets the 非意向客户数量 .
	 *
	 * @return the 非意向客户数量 
	 */
	public int getNumPmNoIntention() {
		return numPmNoIntention;
	}

	/**
	 * Sets the 非意向客户数量 .
	 *
	 * @param numPmNoIntention the new 非意向客户数量 
	 */
	public void setNumPmNoIntention(int numPmNoIntention) {
		this.numPmNoIntention = numPmNoIntention;
	}

	/**
	 * Gets the 成单客户总数 .
	 *
	 * @return the 成单客户总数 
	 */
	public int getNumOrderPm() {
		return numOrderPm;
	}

	/**
	 * Sets the 成单客户总数 .
	 *
	 * @param numOrderPm the new 成单客户总数 
	 */
	public void setNumOrderPm(int numOrderPm) {
		this.numOrderPm = numOrderPm;
	}

	/**
	 * Gets the 暂停跟进客户数量 .
	 *
	 * @return the 暂停跟进客户数量 
	 */
	public int getNumPmAbandon() {
		return numPmAbandon;
	}

	/**
	 * Sets the 暂停跟进客户数量 .
	 *
	 * @param numPmAbandon the new 暂停跟进客户数量 
	 */
	public void setNumPmAbandon(int numPmAbandon) {
		this.numPmAbandon = numPmAbandon;
	}

	/**
	 * Gets the 未分组客户数量 .
	 *
	 * @return the 未分组客户数量 
	 */
	public int getNumPmUngroup() {
		return numPmUngroup;
	}

	/**
	 * Sets the 未分组客户数量 .
	 *
	 * @param numPmUngroup the new 未分组客户数量 
	 */
	public void setNumPmUngroup(int numPmUngroup) {
		this.numPmUngroup = numPmUngroup;
	}

	/**
	 * Gets the 成单率 .
	 *
	 * @return the 成单率 
	 */
	public int getOrderRatio() {
		return orderRatio;
	}

	/**
	 * Sets the 成单率 .
	 *
	 * @param orderRatio the new 成单率 
	 */
	public void setOrderRatio(int orderRatio) {
		this.orderRatio = orderRatio;
	}

	/**
	 * Gets the 销售额 .
	 *
	 * @return the 销售额 
	 */
	public double getSale() {
		return sale;
	}

	/**
	 * Sets the 销售额 .
	 *
	 * @param sale the new 销售额 
	 */
	public void setSale(double sale) {
		this.sale = sale;
	}

	/**
	 * Gets the 跟进客户数量 .
	 *
	 * @return the 跟进客户数量 
	 */
	public long getNumPmCf() {
		return numPmCf;
	}

	/**
	 * Sets the 跟进客户数量 .
	 *
	 * @param numPmCf the new 跟进客户数量 
	 */
	public void setNumPmCf(long numPmCf) {
		this.numPmCf = numPmCf;
	}

	/**
	 * Gets the 最高跟进客户次数 .
	 *
	 * @return the 最高跟进客户次数 
	 */
	public int getMaxPmCfNum() {
		return maxPmCfNum;
	}

	/**
	 * Sets the 最高跟进客户次数 .
	 *
	 * @param maxPmCfNum the new 最高跟进客户次数 
	 */
	public void setMaxPmCfNum(int maxPmCfNum) {
		this.maxPmCfNum = maxPmCfNum;
	}

	/**
	 * Gets the 业务任务产生量 .
	 *
	 * @return the 业务任务产生量 
	 */
	public int getNumComTaskProduce() {
		return numComTaskProduce;
	}

	/**
	 * Sets the 业务任务产生量 .
	 *
	 * @param numComTaskProduce the new 业务任务产生量 
	 */
	public void setNumComTaskProduce(int numComTaskProduce) {
		this.numComTaskProduce = numComTaskProduce;
	}

	/**
	 * Gets the 业务任务完成量 .
	 *
	 * @return the 业务任务完成量 
	 */
	public int getNumComTaskComplete() {
		return numComTaskComplete;
	}

	/**
	 * Sets the 业务任务完成量 .
	 *
	 * @param numComTaskComplete the new 业务任务完成量 
	 */
	public void setNumComTaskComplete(int numComTaskComplete) {
		this.numComTaskComplete = numComTaskComplete;
	}

	/**
	 * Gets the 社交任务产生量 .
	 *
	 * @return the 社交任务产生量 
	 */
	public int getNumSocialTaskProduce() {
		return numSocialTaskProduce;
	}

	/**
	 * Sets the 社交任务产生量 .
	 *
	 * @param numSocialTaskProduce the new 社交任务产生量 
	 */
	public void setNumSocialTaskProduce(int numSocialTaskProduce) {
		this.numSocialTaskProduce = numSocialTaskProduce;
	}

	/**
	 * Gets the 社交任务完成量 .
	 *
	 * @return the 社交任务完成量 
	 */
	public int getNumSocialTaskComplete() {
		return numSocialTaskComplete;
	}

	/**
	 * Sets the 社交任务完成量 .
	 *
	 * @param numSocialTaskComplete the new 社交任务完成量 
	 */
	public void setNumSocialTaskComplete(int numSocialTaskComplete) {
		this.numSocialTaskComplete = numSocialTaskComplete;
	}

	/**
	 * Gets the 邀约任务完成量 .
	 *
	 * @return the 邀约任务完成量 
	 */
	public int getNumInviteTaskComplete() {
		return numInviteTaskComplete;
	}

	/**
	 * Sets the 邀约任务完成量 .
	 *
	 * @param numInviteTaskComplete the new 邀约任务完成量 
	 */
	public void setNumInviteTaskComplete(int numInviteTaskComplete) {
		this.numInviteTaskComplete = numInviteTaskComplete;
	}

	/**
	 * Gets the 应邀客户量 .
	 *
	 * @return the 应邀客户量 
	 */
	public int getIsInvitePmNum() {
		return isInvitePmNum;
	}

	/**
	 * Sets the 应邀客户量 .
	 *
	 * @param isInvitePmNum the new 应邀客户量 
	 */
	public void setIsInvitePmNum(int isInvitePmNum) {
		this.isInvitePmNum = isInvitePmNum;
	}

	/**
	 * Gets the 到店客户量 .
	 *
	 * @return the 到店客户量 
	 */
	public int getClientExpPmNum() {
		return clientExpPmNum;
	}

	/**
	 * Sets the 到店客户量 .
	 *
	 * @param clientExpPmNum the new 到店客户量 
	 */
	public void setClientExpPmNum(int clientExpPmNum) {
		this.clientExpPmNum = clientExpPmNum;
	}

	/**
	 * Gets the 微信聊天次数 .
	 *
	 * @return the 微信聊天次数 
	 */
	public int getNumWeChat() {
		return numWeChat;
	}

	/**
	 * Sets the 微信聊天次数 .
	 *
	 * @param numWeChat the new 微信聊天次数 
	 */
	public void setNumWeChat(int numWeChat) {
		this.numWeChat = numWeChat;
	}

	/**
	 * Gets the 通话次数 .
	 *
	 * @return the 通话次数 
	 */
	public int getNumCall() {
		return numCall;
	}

	/**
	 * Sets the 通话次数 .
	 *
	 * @param numCall the new 通话次数 
	 */
	public void setNumCall(int numCall) {
		this.numCall = numCall;
	}

	/**
	 * Gets the 通话时长 .
	 *
	 * @return the 通话时长 
	 */
	public long getCallTime() {
		return callTime;
	}

	/**
	 * Sets the 通话时长 .
	 *
	 * @param callTime the new 通话时长 
	 */
	public void setCallTime(long callTime) {
		this.callTime = callTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShopDayOperateDto [merchantNo=" + merchantNo + ", shopNo="
				+ shopNo + ", shopName=" + shopName + ", numPm=" + numPm
				+ ", numPmIntention=" + numPmIntention + ", numPmNoIntention="
				+ numPmNoIntention + ", numOrderPm=" + numOrderPm
				+ ", numPmAbandon=" + numPmAbandon + ", numPmUngroup="
				+ numPmUngroup + ", orderRatio=" + orderRatio + ", sale="
				+ sale + ", numPmCf=" + numPmCf + ", maxPmCfNum=" + maxPmCfNum
				+ ", numComTaskProduce=" + numComTaskProduce
				+ ", numComTaskComplete=" + numComTaskComplete
				+ ", numSocialTaskProduce=" + numSocialTaskProduce
				+ ", numSocialTaskComplete=" + numSocialTaskComplete
				+ ", numInviteTaskComplete=" + numInviteTaskComplete
				+ ", isInvitePmNum=" + isInvitePmNum + ", clientExpPmNum="
				+ clientExpPmNum + ", numWeChat=" + numWeChat + ", numCall="
				+ numCall + ", callTime=" + callTime + "]";
	}

}
