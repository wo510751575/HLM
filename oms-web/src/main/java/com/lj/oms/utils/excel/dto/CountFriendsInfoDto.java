package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

public class CountFriendsInfoDto implements Serializable {

	private static final long serialVersionUID = 4800454545051696114L;
	

	@ExcelField(title="微信号", align=2, sort=1)
	private String noWxShop;
	

	@ExcelField(title="门诊名称", align=2, sort=2)
	private String shopName;
	
	@ExcelField(title="描述", align=2, sort=3)
	private String content;
	
	@ExcelField(title="发送日期", align=2, sort=4)
	private String createDate;
	
	@ExcelField(title="评论数", align=3, sort=5)
	private String commentCount;
	
	@ExcelField(title="点赞数", align=3, sort=6)
	private String likeCount;

	public String getNoWxShop() {
		return noWxShop;
	}

	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
