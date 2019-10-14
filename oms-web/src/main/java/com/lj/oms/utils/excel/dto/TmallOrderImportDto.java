package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 
 * 类说明：天猫订单导入Dto
 * 
 * 
 * <p>
 * 
 * @Company: 扬恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年2月20日
 */
public class TmallOrderImportDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 下单人姓名 */
	@ExcelField(title="下单人姓名", align=2, sort=1)
	private String memberName;

	/** 下单人电话 */
	@ExcelField(title="下单人电话", align=2, sort=2)
	private String mobile;

	/** 下单人旺旺 */
	@ExcelField(title="下单人旺旺", align=2, sort=3)
	private String noWw;

	/** 订单号 */
	@ExcelField(title="订单号", align=2, sort=4)
	private String orderNo;

	/** 商品名称 */
	@ExcelField(title="商品名称", align=2, sort=5)
	private String productName;

	/** 商品链接 */
	@ExcelField(title="商品链接", align=2, sort=6)
	private String productUrl;

	/** 金额 */
	@ExcelField(title="金额（元）", align=2, sort=7)
	private String amountStr;

	/** 下单时间 */
	@ExcelField(title="下单时间(格式：2018-01-01 20:15:16)", align=2, sort=8)
	private String orderDate;

	/** 评论星级 */
	@ExcelField(title="评论星级", align=2, sort=9)
	private String commentLevel;

	/** 评论内容 */
	@ExcelField(title="评论内容", align=2, sort=10)
	private String commentCtx;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNoWw() {
		return noWw;
	}

	public void setNoWw(String noWw) {
		this.noWw = noWw;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getAmountStr() {
		return amountStr;
	}

	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(String commentLevel) {
		this.commentLevel = commentLevel;
	}

	public String getCommentCtx() {
		return commentCtx;
	}

	public void setCommentCtx(String commentCtx) {
		this.commentCtx = commentCtx;
	}

	@Override
	public String toString() {
		return "TmallOrderImportDto [memberName=" + memberName + ", mobile=" + mobile + ", noWw=" + noWw + ", orderNo="
				+ orderNo + ", productName=" + productName + ", productUrl=" + productUrl + ", amountStr=" + amountStr
				+ ", orderDate=" + orderDate + ", commentLevel=" + commentLevel + ", commentCtx=" + commentCtx + "]";
	}
 
}
