package com.lj.oms.utils.excel.dto;

import java.io.Serializable;
import java.util.Date;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 类说明：移动终端-经销商运营日报数据导出
 * 
 *
 * @author 彭俊霖
 * @CreateDate 2018年6月15日下午2:26:58
 */
public class CompanyOperationDayReportExportDto implements Serializable{

	 /**
     * 
     */
    private static final long serialVersionUID = -5635489666962970464L;
    
	/** 日期 */
	@ExcelField(title="日期", align=2, sort=10, fieldType= Date.class)
    private Date reportDate;

	/**
	 * 经销商代码
	 */
	@ExcelField(title="经销商代码", align=2, sort=11)
	private String dealerCode;

	/**
	 * 经销商名称
	 */
	@ExcelField(title="经销商名称", align=2, sort=12)
	private String companyName;
	
	/**
     * 总客户数
     */
	@ExcelField(title="总客户数", align=3, sort=20)
    private Integer mbrTotalCount;
	
	/**
	 * 今日新增
	 */
	@ExcelField(title="今日新增", align=3, sort=30)
	private Integer mbrTodayCount;
	
	/**
	 * 商品件数
	 */
	@ExcelField(title="商品件数", align=3, sort=40)
	private Integer ordGoodCount;
	
	/**
	 * 订单数
	 */
	@ExcelField(title="订单数", align=3, sort=50)
	private Integer ordOrderCount;
	
	/**
	 * 订单总额
	 */
	@ExcelField(title="订单总额", align=3, sort=60)
	private String ordOrderAmountStr;
	
	/**
	 * 客单价
	 */
	@ExcelField(title="客单价", align=3, sort=70)
	private String ordCustomerPriceStr;
	
	/**
	 * 优惠总额
	 */
	@ExcelField(title="优惠总额", align=3, sort=80)
	private String ordActivitieAmountStr;
	
	/**
	 * 实际支付总额
	 */
	@ExcelField(title="实际支付总额", align=3, sort=90)
	private String ordPayAmountStr;
	
	/**
	 * 商品件数
	 */
	@ExcelField(title="商品件数", align=3, sort=100)
	private Integer succGoodCount;
	
	/**
	 * 订单数
	 */
	@ExcelField(title="订单数", align=3, sort=110)
	private Integer succOrderCount;

	/**
	 * 订单总额
	 */
	@ExcelField(title="订单总额", align=3, sort=120)
	private String succOrderAmountStr;
	
	/**
	 * 客单价
	 */
	@ExcelField(title="客单价", align=3, sort=130)
	private String succCustomerPriceStr;
	
	/**
	 * 优惠总额
	 */
	@ExcelField(title="优惠总额", align=3, sort=140)
	private String succActivitieAmountStr;
	
	/**
	 * 实际支付总额
	 */
	@ExcelField(title="实际支付总额", align=3, sort=150)
	private String succPayAmountStr;

    /**
     * 访问-商城主页访问次数 .
     */
	@ExcelField(title="商城主页访问次数", align=3, sort=160)
    private Integer pvIndexTotalCount;

    /**
     * 访问-商城主页访问人数 .
     */
	@ExcelField(title="商城主页访问人数", align=3, sort=170)
    private Integer pvIndexMemberCount;

    /**
     * 访问-商品详情页访问次数 .
     */
	@ExcelField(title="商品详情页访问次数", align=3, sort=180)
    private Integer pvDetailTotalCount;

    /**
     * 访问-商品详情页访问人数 .
     */
	@ExcelField(title="商品详情页访问人数", align=3, sort=190)
    private Integer pvDetailMemberCount;
	
	/**
	 * 短信-短信数量 .
	 */
	@ExcelField(title="短信数量", align=3, sort=200)
	private Integer smsCount;

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Integer getMbrTotalCount() {
		return mbrTotalCount;
	}

	public void setMbrTotalCount(Integer mbrTotalCount) {
		this.mbrTotalCount = mbrTotalCount;
	}

	public Integer getMbrTodayCount() {
		return mbrTodayCount;
	}

	public void setMbrTodayCount(Integer mbrTodayCount) {
		this.mbrTodayCount = mbrTodayCount;
	}

	public Integer getOrdGoodCount() {
		return ordGoodCount;
	}

	public void setOrdGoodCount(Integer ordGoodCount) {
		this.ordGoodCount = ordGoodCount;
	}

	public Integer getOrdOrderCount() {
		return ordOrderCount;
	}

	public void setOrdOrderCount(Integer ordOrderCount) {
		this.ordOrderCount = ordOrderCount;
	}

	public String getOrdOrderAmountStr() {
		return ordOrderAmountStr;
	}

	public void setOrdOrderAmountStr(String ordOrderAmountStr) {
		this.ordOrderAmountStr = ordOrderAmountStr;
	}

	public String getOrdCustomerPriceStr() {
		return ordCustomerPriceStr;
	}

	public void setOrdCustomerPriceStr(String ordCustomerPriceStr) {
		this.ordCustomerPriceStr = ordCustomerPriceStr;
	}

	public String getOrdActivitieAmountStr() {
		return ordActivitieAmountStr;
	}

	public void setOrdActivitieAmountStr(String ordActivitieAmountStr) {
		this.ordActivitieAmountStr = ordActivitieAmountStr;
	}

	public String getOrdPayAmountStr() {
		return ordPayAmountStr;
	}

	public void setOrdPayAmountStr(String ordPayAmountStr) {
		this.ordPayAmountStr = ordPayAmountStr;
	}

	public Integer getSuccGoodCount() {
		return succGoodCount;
	}

	public void setSuccGoodCount(Integer succGoodCount) {
		this.succGoodCount = succGoodCount;
	}

	public Integer getSuccOrderCount() {
		return succOrderCount;
	}

	public void setSuccOrderCount(Integer succOrderCount) {
		this.succOrderCount = succOrderCount;
	}

	public String getSuccOrderAmountStr() {
		return succOrderAmountStr;
	}

	public void setSuccOrderAmountStr(String succOrderAmountStr) {
		this.succOrderAmountStr = succOrderAmountStr;
	}

	public String getSuccCustomerPriceStr() {
		return succCustomerPriceStr;
	}

	public void setSuccCustomerPriceStr(String succCustomerPriceStr) {
		this.succCustomerPriceStr = succCustomerPriceStr;
	}

	public String getSuccActivitieAmountStr() {
		return succActivitieAmountStr;
	}

	public void setSuccActivitieAmountStr(String succActivitieAmountStr) {
		this.succActivitieAmountStr = succActivitieAmountStr;
	}

	public String getSuccPayAmountStr() {
		return succPayAmountStr;
	}

	public void setSuccPayAmountStr(String succPayAmountStr) {
		this.succPayAmountStr = succPayAmountStr;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the pvIndexTotalCount
	 */
	public Integer getPvIndexTotalCount() {
		return pvIndexTotalCount;
	}

	/**
	 * @param pvIndexTotalCount the pvIndexTotalCount to set
	 */
	public void setPvIndexTotalCount(Integer pvIndexTotalCount) {
		this.pvIndexTotalCount = pvIndexTotalCount;
	}

	/**
	 * @return the pvIndexMemberCount
	 */
	public Integer getPvIndexMemberCount() {
		return pvIndexMemberCount;
	}

	/**
	 * @param pvIndexMemberCount the pvIndexMemberCount to set
	 */
	public void setPvIndexMemberCount(Integer pvIndexMemberCount) {
		this.pvIndexMemberCount = pvIndexMemberCount;
	}

	/**
	 * @return the pvDetailTotalCount
	 */
	public Integer getPvDetailTotalCount() {
		return pvDetailTotalCount;
	}

	/**
	 * @param pvDetailTotalCount the pvDetailTotalCount to set
	 */
	public void setPvDetailTotalCount(Integer pvDetailTotalCount) {
		this.pvDetailTotalCount = pvDetailTotalCount;
	}

	/**
	 * @return the pvDetailMemberCount
	 */
	public Integer getPvDetailMemberCount() {
		return pvDetailMemberCount;
	}

	/**
	 * @param pvDetailMemberCount the pvDetailMemberCount to set
	 */
	public void setPvDetailMemberCount(Integer pvDetailMemberCount) {
		this.pvDetailMemberCount = pvDetailMemberCount;
	}

	/**
	 * @return the smsCount
	 */
	public Integer getSmsCount() {
		return smsCount;
	}

	/**
	 * @param smsCount the smsCount to set
	 */
	public void setSmsCount(Integer smsCount) {
		this.smsCount = smsCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyOperationDayReportExportDto [reportDate=");
		builder.append(reportDate);
		builder.append(", dealerCode=");
		builder.append(dealerCode);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", mbrTotalCount=");
		builder.append(mbrTotalCount);
		builder.append(", mbrTodayCount=");
		builder.append(mbrTodayCount);
		builder.append(", ordGoodCount=");
		builder.append(ordGoodCount);
		builder.append(", ordOrderCount=");
		builder.append(ordOrderCount);
		builder.append(", ordOrderAmountStr=");
		builder.append(ordOrderAmountStr);
		builder.append(", ordCustomerPriceStr=");
		builder.append(ordCustomerPriceStr);
		builder.append(", ordActivitieAmountStr=");
		builder.append(ordActivitieAmountStr);
		builder.append(", ordPayAmountStr=");
		builder.append(ordPayAmountStr);
		builder.append(", succGoodCount=");
		builder.append(succGoodCount);
		builder.append(", succOrderCount=");
		builder.append(succOrderCount);
		builder.append(", succOrderAmountStr=");
		builder.append(succOrderAmountStr);
		builder.append(", succCustomerPriceStr=");
		builder.append(succCustomerPriceStr);
		builder.append(", succActivitieAmountStr=");
		builder.append(succActivitieAmountStr);
		builder.append(", succPayAmountStr=");
		builder.append(succPayAmountStr);
		builder.append(", pvIndexTotalCount=");
		builder.append(pvIndexTotalCount);
		builder.append(", pvIndexMemberCount=");
		builder.append(pvIndexMemberCount);
		builder.append(", pvDetailTotalCount=");
		builder.append(pvDetailTotalCount);
		builder.append(", pvDetailMemberCount=");
		builder.append(pvDetailMemberCount);
		builder.append(", smsCount=");
		builder.append(smsCount);
		builder.append("]");
		return builder.toString();
	}

}
