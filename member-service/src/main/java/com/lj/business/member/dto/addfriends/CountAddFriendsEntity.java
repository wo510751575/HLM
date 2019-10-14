package com.lj.business.member.dto.addfriends;

import java.io.Serializable;
import java.util.Date;

public class CountAddFriendsEntity implements Serializable{
	
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 商户编号
	 */
	private String merchantNo;
	/**
	 * 门店名称
	 */
	private String shopName;
	/**
	 * 导购名称
	 */
	private String memberGmName;


	/**
	 * 统计总数
	 */
	private Integer count;
	   /**
           * 统计总数对应的时间
     */
    private String countDate;
    /**
     * 定时任务保存数据的时间
     */
    private String createDate;
    
    /**
     * 统计数据类型：1 按商户类型统计， 2按微信统计
     */
    private String type;
    
    //终端微信号
    private String noWx;
    
    
	public String getNoWx() {
		return noWx;
	}
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getMemberGmName() {
		return memberGmName;
	}
	public void setMemberGmName(String memberGmName) {
		this.memberGmName = memberGmName;
	}
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getCountDate() {
		return countDate;
	}
	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return "CountAddFriendsEntity [id=" + id + ", merchantNo=" + merchantNo + ", count=" + count + ", countDate="
				+ countDate + ", createDate=" + createDate + "]";
	}
    
}
