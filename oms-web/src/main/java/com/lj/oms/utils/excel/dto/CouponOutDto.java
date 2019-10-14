package com.lj.oms.utils.excel.dto;

import java.io.Serializable;
import java.util.Date;

import com.lj.oms.utils.excel.annotation.ExcelField;

// TODO: Auto-generated Javadoc
/**
 * 类说明： 优惠券数据导出
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 * 
 * CreateDate: 2017年9月16日
 */
public class CouponOutDto implements Serializable {

	 /** Generate cron. */
	private static final long serialVersionUID = -1735948274834873419L;
	
	/** 优惠券编号. */
	@ExcelField(title="优惠券编号", align=2, sort=1)
	private String couponNo;
	
	/** 优惠券名称. */
	@ExcelField(title="优惠券名称", align=2, sort=2)
	private String couponName;
	
	/** 优惠券名称. */
	@ExcelField(title="优惠券面额", align=2, sort=3)
	private String couponNotes;
	
	/** 优惠券名称. */
	@ExcelField(title="优惠类型", align=2, sort=4)
	private String couponType;
	
	/** 终端名称. */
//	@ExcelField(title="终端名称", align=2, sort=5)
	
	
	/** 优惠券使用状态. */
	@ExcelField(title="优惠券使用状态", align=2, sort=6)
	private String couponStatus;
	
	/** 使用时间. */
	@ExcelField(title="使用时间", align=2, sort=7)
	private Date useDate;
	
	/** 创建时间. */
	@ExcelField(title="创建时间", align=2, sort=8)
	private Date createDate;
	
	/** 创建时间. */
	@ExcelField(title="开始时间", align=2, sort=8)
	private Date beginDate;
	
	/** 创建时间. */
	@ExcelField(title="结束时间", align=2, sort=8)
	private Date endDate;

	
	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponNotes() {
		return couponNotes;
	}

	public void setCouponNotes(String couponNotes) {
		this.couponNotes = couponNotes;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	


}
