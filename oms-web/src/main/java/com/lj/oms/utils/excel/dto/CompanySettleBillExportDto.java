package com.lj.oms.utils.excel.dto;

import java.io.Serializable;
import java.util.Date;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 类说明：移动终端-账单数据导出
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2018年05月07日
 */
public class CompanySettleBillExportDto implements Serializable{

	 /**
     * 
     */
    private static final long serialVersionUID = -5635489666962970464L;
    
	/** 经销商. */
	@ExcelField(title="经销商", align=0, sort=10)
    private String companyName;
	
	/** 支付渠道. */
	@ExcelField(title="支付渠道", align=0, sort=15)
	private String payChannel;
	
	/**
     * 交易笔数 .
     */
	@ExcelField(title="订单数", align=0, sort=20)
    private Integer tradeCount;
	 
	/**
     * 交易金额
     */
	@ExcelField(title="支付总额", align=3, sort=30)
    private String tradeAmountStr;
	
	/**
	 * 交易金额
	 */
	@ExcelField(title="收款手续费", align=3, sort=31)
	private String channelFeeStr;
	
	/**
	 * 交易金额
	 */
	@ExcelField(title="出款手续费", align=3, sort=32)
	private String payoutFeeStr;
	
	/**
	 * 交易金额
	 */
	@ExcelField(title="应收总额", align=3, sort=33)
	private String settleAmountStr;

    /**
     * 账单开始时间
     */
//    @ExcelField(title="账单开始时间", align=2, sort=40, fieldType= Date.class)
//    private Date billBeginTime;

    /**
     * 账单结束时间
     */
    @ExcelField(title="账单日期", align=2, sort=50, fieldType= Date.class)
    private Date billEndTime;
    
	/**
	 * 出款状态 .
	 */
	@ExcelField(title="出款状态", align=0, sort=90)
	private String fundsStatusStr;
	
	/**
     * 银行卡号
     */
    @ExcelField(title="银行卡号", align=0, sort=100)
    private String bankcardNo;
    
    /**
     * 银行名称
     */
    @ExcelField(title="银行名称", align=0, sort=110)
    private String bankName;
    
    /**
     * 账单生成时间
     */
//    @ExcelField(title="账单生成时间", align=2, sort=120, fieldType= Date.class)
//    private Date billCreateTime;
    
    /**
     * 出款时间
     */
    @ExcelField(title="出款时间", align=2, sort=130)
    private String payoutTimeStr;
    
    /**
     * 出款时间
     */
    @ExcelField(title="外部交易编号", align=0, sort=140)
    private String outTradeNo;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the payChannel
	 */
	public String getPayChannel() {
		return payChannel;
	}

	/**
	 * @param payChannel the payChannel to set
	 */
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public Integer getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(Integer tradeCount) {
		this.tradeCount = tradeCount;
	}

	public String getTradeAmountStr() {
		return tradeAmountStr;
	}

	public void setTradeAmountStr(String tradeAmountStr) {
		this.tradeAmountStr = tradeAmountStr;
	}

	public Date getBillEndTime() {
		return billEndTime;
	}

	public void setBillEndTime(Date billEndTime) {
		this.billEndTime = billEndTime;
	}

	public String getFundsStatusStr() {
		return fundsStatusStr;
	}

	public void setFundsStatusStr(String fundsStatusStr) {
		this.fundsStatusStr = fundsStatusStr;
	}

	public String getBankcardNo() {
		return bankcardNo;
	}

	public void setBankcardNo(String bankcardNo) {
		this.bankcardNo = bankcardNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getPayoutTimeStr() {
		return payoutTimeStr;
	}

	public void setPayoutTimeStr(String payoutTimeStr) {
		this.payoutTimeStr = payoutTimeStr;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getChannelFeeStr() {
		return channelFeeStr;
	}

	public void setChannelFeeStr(String channelFeeStr) {
		this.channelFeeStr = channelFeeStr;
	}

	public String getPayoutFeeStr() {
		return payoutFeeStr;
	}

	public void setPayoutFeeStr(String payoutFeeStr) {
		this.payoutFeeStr = payoutFeeStr;
	}

	public String getSettleAmountStr() {
		return settleAmountStr;
	}

	public void setSettleAmountStr(String settleAmountStr) {
		this.settleAmountStr = settleAmountStr;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanySettleBillExportDto [companyName=");
		builder.append(companyName);
		builder.append(", payChannel=");
		builder.append(payChannel);
		builder.append(", tradeCount=");
		builder.append(tradeCount);
		builder.append(", tradeAmountStr=");
		builder.append(tradeAmountStr);
		builder.append(", channelFeeStr=");
		builder.append(channelFeeStr);
		builder.append(", payoutFeeStr=");
		builder.append(payoutFeeStr);
		builder.append(", settleAmountStr=");
		builder.append(settleAmountStr);
		builder.append(", billEndTime=");
		builder.append(billEndTime);
		builder.append(", fundsStatusStr=");
		builder.append(fundsStatusStr);
		builder.append(", bankcardNo=");
		builder.append(bankcardNo);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", payoutTimeStr=");
		builder.append(payoutTimeStr);
		builder.append(", outTradeNo=");
		builder.append(outTradeNo);
		builder.append("]");
		return builder.toString();
	}

}
