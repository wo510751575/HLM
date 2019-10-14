package com.lj.oms.utils.excel.dto;

import java.io.Serializable;
import java.util.Date;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 类说明：移动终端-商城运营日报数据导出
 * 
 *
 * @author 彭俊霖
 * @CreateDate 2018年6月15日下午2:26:58
 */
public class VoucherExportDto implements Serializable{

	 /**
     * 
     */
    private static final long serialVersionUID = -5635489666962970464L;
    
	/** 券号 */
	@ExcelField(title="券号", align=0, sort=10)
    private String voucherNo;
	
	/** 优惠券名称 */
	@ExcelField(title="优惠券名称", align=0, sort=20)
	private String voucherName;
	
	/** 优惠券金额 */
	@ExcelField(title="优惠券金额", align=3, sort=30)
	private String voucherAmountStr;
	
	/** 使用门槛 */
	@ExcelField(title="使用门槛", align=3, sort=40)
	private String thresholdAmountStr;
	
	/** 有效日期 */
	@ExcelField(title="有效日期", align=0, sort=50)
	private String dateStr;
	
	/** 状态 */
	@ExcelField(title="状态", align=0, sort=55)
	private String remark;
	
	/** 领取时间*/
	@ExcelField(title="领取时间", align=0, sort=60,fieldType=Date.class)
	private Date receiveTime;

	/** 抵扣金额 */
	@ExcelField(title="抵扣金额", align=3, sort=70)
	private String deductionAmountStr;
	
	/** 客户名称 */
	@ExcelField(title="客户名称", align=0, sort=80)
	private String custName;
	
	/** 手机号 */
	@ExcelField(title="手机号", align=0, sort=90)
	private String receiveMobile;
	
	/** 所属经销商 */
	@ExcelField(title="所属经销商", align=0, sort=110)
	private String companyName;

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getVoucherName() {
		return voucherName;
	}

	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}

	public String getVoucherAmountStr() {
		return voucherAmountStr;
	}

	public void setVoucherAmountStr(String voucherAmountStr) {
		this.voucherAmountStr = voucherAmountStr;
	}

	public String getThresholdAmountStr() {
		return thresholdAmountStr;
	}

	public void setThresholdAmountStr(String thresholdAmountStr) {
		this.thresholdAmountStr = thresholdAmountStr;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getDeductionAmountStr() {
		return deductionAmountStr;
	}

	public void setDeductionAmountStr(String deductionAmountStr) {
		this.deductionAmountStr = deductionAmountStr;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getReceiveMobile() {
		return receiveMobile;
	}

	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VoucherExportDto [voucherNo=");
		builder.append(voucherNo);
		builder.append(", voucherName=");
		builder.append(voucherName);
		builder.append(", voucherAmountStr=");
		builder.append(voucherAmountStr);
		builder.append(", thresholdAmountStr=");
		builder.append(thresholdAmountStr);
		builder.append(", dateStr=");
		builder.append(dateStr);
		builder.append(", receiveTime=");
		builder.append(receiveTime);
		builder.append(", deductionAmountStr=");
		builder.append(deductionAmountStr);
		builder.append(", custName=");
		builder.append(custName);
		builder.append(", receiveMobile=");
		builder.append(receiveMobile);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append("]");
		return builder.toString();
	}
	
}
