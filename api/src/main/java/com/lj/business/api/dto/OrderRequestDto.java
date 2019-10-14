//package com.lj.business.api.dto;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
//import com.lj.business.ord.dto.OrderDetailDto;
//import com.lj.business.ord.dto.OrderPromotionDto;
//import com.lj.business.ord.dto.OrderVoucherDto;
//
//public class OrderRequestDto implements Serializable {
//
//	private static final long serialVersionUID = 2524979791859755671L;
//
//	/**
//	 * 订单CODE .
//	 */
//	private String orderCode;
//
//	/**
//	 * 订单号 .
//	 */
//	private String orderNo;
//
//	/**
//	 * 外部订单号 .
//	 */
//	private String outOrderNo;
//
//	/**
//	 * 外部客户号 .
//	 */
//	private String outCustomNo;
//
//	/**
//	 * 微信号
//	 */
//	private String weixinNo;
//
//	/**
//	 * 商户号 .
//	 */
//	private String merchantNo;
//
//	/**
//	 * 门店CODE .
//	 */
//	private String shopNo;
//
//	/**
//	 * 客户名称 .
//	 */
//	private String custName;
//	/**
//     * 头像地址 .
//     */
//    private String headAddress;
//	/**
//	 * 客户手机号 .
//	 */
//	private String custPhone;
//
//	/**
//	 * 订单所属客户ID .
//	 */
//	private String memberNo;
//
//	/**
//	 * 订单类型 .
//	 */
//	private String orderType;
//
//	/**
//	 * 订单来源 1 聚客 2 公众号 3 其他 .
//	 */
//	private String orderSource;
//
//	/**
//	 * 是否参与活动 .
//	 */
//	private String isActivities;
//
//	/**
//	 * 活动优惠金额 .
//	 */
//	private Long activitiesMoney = 0L;
//
//	/**
//	 * 是否使用优惠券 .
//	 */
//	private String isVoucher;
//
//	/**
//	 * 优惠券金额 .
//	 */
//	private Long voucherMoney = 0L;
//
//	/**
//	 * 订单折扣 .
//	 */
//	private BigDecimal discount;
//
//	/**
//	 * 是否全款 .
//	 */
//	private String isAllPay;
//
//	/**
//	 * 订金比例 .
//	 */
//	private Double earnestRate;
//
//	/**
//	 * 订金金额 .
//	 */
//	private Long earnestMoney = 0L;
//
//	/**
//	 * 运费金额 .
//	 */
//	private Long expensesMoney = 0L;
//
//	/**
//	 * 支付金额 .
//	 */
//	private Long payMoney = 0L;
//
//	/**
//	 * 已付金额
//	 */
//	private Long paidMoney = 0L;
//	
//	/**
//	 * 订单金额 .
//	 */
//	private Long orderMoney = 0L;
//
//	/**
//	 * 订单状态 .
//	 */
//	private String status;
//
//	/**
//	 * 退款金额 .
//	 */
//	private Long refundMoney = 0L;
//
//	/**
//	 * 退款标识 .
//	 */
//	private String refundFlag;
//
//	/**
//	 * 建议送货时间 .
//	 */
//	private Date adviseSendDate;
//
//	/**
//	 * 客户预约送货时间 .
//	 */
//	private Date bespeakSendDate;
//
//	/**
//	 * 订单发货时间 .
//	 */
//	private String sendOutDate;
//
//	/**
//	 * 经办人 .
//	 */
//	private String orderSaleCode;
//
//	/**
//	 * 确认人 .
//	 */
//	private String orderConfirmCode;
//
//	/**
//	 * 收货人名称 .
//	 */
//	private String customName;
//
//	/**
//	 * 商品配送方式 .
//	 */
//	private String deliveryMode;
//
//	/**
//	 * 收货人联系方式 .
//	 */
//	private String customPhone;
//
//	/**
//	 * 省CODE .
//	 */
//	private String customProvinceCode;
//
//	private String customProvince;
//
//	/**
//	 * 市CODE .
//	 */
//	private String customCityCode;
//
//	private String customCity;
//
//	/**
//	 * 客户区域ID .
//	 */
//	private String customAreaCode;
//
//	private String customArea;
//
//	/**
//	 * 客户地址 .
//	 */
//	private String orderCustomAddress;
//
//	/**
//	 * 是否开票 .
//	 */
//	private String isInvoice;
//
//	/**
//	 * 发票类型 .
//	 */
//	private String invoiceType;
//
//	/**
//	 * 发票抬头类型 .
//	 */
//	private String invoiceTitleType;
//
//	/**
//	 * 纳税人识别号 .
//	 */
//	private String invoiceNum;
//
//	/**
//	 * 发票抬头 .
//	 */
//	private String invoiceTitle;
//
//	/**
//	 * 发票内容 .
//	 */
//	private String invoiceContext;
//
//	/**
//	 * 发票金额 .
//	 */
//	private Long invoiceMoney = 0L;
//
//	/**
//	 * 电子发票编号 .
//	 */
//	private String eInvoiceNo;
//
//	/**
//	 * 收票人省 .
//	 */
//	private String invoiceProviceCode;
//
//	private String invoiceProvice;
//
//	/**
//	 * 收票人市 .
//	 */
//	private String invoiceCityCode;
//
//	private String invoiceCity;
//	/**
//	 * 区 .
//	 */
//	private String invoiceAreaCode;
//
//	private String invoiceArea;
//
//	/**
//	 * 详细地址 .
//	 */
//	private String invoiceAddress;
//
//	/**
//	 * 收票人电话 .
//	 */
//	private String invoiceTakerPhone;
//
//	/**
//	 * 收票人EMAILL .
//	 */
//	private String invoiceTakerEmail;
//
//	/**
//	 * 收票人 .
//	 */
//	private String invoiceTaker;
//
//	/**
//	 * 更新时间 .
//	 */
//	private Date updateTime;
//
//	/**
//	 * 订单创建人 .
//	 */
//	private String orderCreateCode;
//
//	/**
//	 * 订单创建时间 .
//	 */
//	private Date createTime;
//
//	/**
//	 * 备注 .
//	 */
//	private String remark;
//
//	/**
//	 * 备注 .
//	 */
//	private String remark4;
//
//	/**
//	 * 备注 .
//	 */
//	private String remark3;
//
//	/**
//	 * 备注 .
//	 */
//	private String remark2;
//
//	/**
//	 * 支付信息
//	 */
//
//	private String returnUrl;
//
//	private String payType;
//
//	/**
//	 * 发票单位名称 .
//	 */
//	private String invoicecoName;
//
//	/**
//	 * 发票单位注册地址 .
//	 */
//	private String invoicecoAddr;
//
//	/**
//	 * 发票单位联系方式 .
//	 */
//	private String invoicecoPhone;
//
//	/**
//	 * 发票单位银行 .
//	 */
//	private String invoicecoBank;
//
//	/**
//	 * 发票单位银行账号 .
//	 */
//	private String invoicecoBankaccound;
//
//	/**
//	 * 本次支付金额 如果全款支付则可不传
//	 */
//	private Long paymentMoney;
//	
//	/**
//	 * 下单方式：1商品详情页立即下单、2购物车下单(默认)
//	 */
//	private Integer orderMethod = 2;
//
//	public Long getPaymentMoney() {
//		return paymentMoney;
//	}
//
//	public void setPaymentMoney(Long paymentMoney) {
//		this.paymentMoney = paymentMoney;
//	}
//
//	/**
//	 * 客户订单商品明细
//	 */
//	private List<OrderDetailDto> orderDetails;
//
//	private List<OrderVoucherDto> orderVouchers;
//
//	/**
//	 * 客户促销信息
//	 */
//	private List<OrderPromotionDto> orderPromotions;
//
//	 
//
//	public String getInvoiceCity() {
//		return invoiceCity;
//	}
//
//	public void setInvoiceCity(String invoiceCity) {
//		this.invoiceCity = invoiceCity;
//	}
//
//	public String getInvoiceArea() {
//		return invoiceArea;
//	}
//
//	public void setInvoiceArea(String invoiceArea) {
//		this.invoiceArea = invoiceArea;
//	}
//
//	public String getReturnUrl() {
//		return returnUrl;
//	}
//
//	public void setReturnUrl(String returnUrl) {
//		this.returnUrl = returnUrl;
//	}
//
//	public String getPayType() {
//		return payType;
//	}
//
//	public void setPayType(String payType) {
//		this.payType = payType;
//	}
//
//	public String getOrderCode() {
//		return orderCode;
//	}
//
//	public void setOrderCode(String orderCode) {
//		this.orderCode = orderCode;
//	}
//
//	public String getOrderNo() {
//		return orderNo;
//	}
//
//	public void setOrderNo(String orderNo) {
//		this.orderNo = orderNo;
//	}
//
//	public String getOutOrderNo() {
//		return outOrderNo;
//	}
//
//	public void setOutOrderNo(String outOrderNo) {
//		this.outOrderNo = outOrderNo;
//	}
//
//	public String getOutCustomNo() {
//		return outCustomNo;
//	}
//
//	public void setOutCustomNo(String outCustomNo) {
//		this.outCustomNo = outCustomNo;
//	}
//
//	public String getMerchantNo() {
//		return merchantNo;
//	}
//
//	public void setMerchantNo(String merchantNo) {
//		this.merchantNo = merchantNo;
//	}
//
//	public String getShopNo() {
//		return shopNo;
//	}
//
//	public void setShopNo(String shopNo) {
//		this.shopNo = shopNo;
//	}
//
//	public String getCustPhone() {
//		return custPhone;
//	}
//
//	public void setCustPhone(String custPhone) {
//		this.custPhone = custPhone;
//	}
//
//	public String getMemberNo() {
//		return memberNo;
//	}
//
//	public void setMemberNo(String memberNo) {
//		this.memberNo = memberNo;
//	}
//
//	public String getOrderType() {
//		return orderType;
//	}
//
//	public void setOrderType(String orderType) {
//		this.orderType = orderType;
//	}
//
//	public String getOrderSource() {
//		return orderSource;
//	}
//
//	public void setOrderSource(String orderSource) {
//		this.orderSource = orderSource;
//	}
//
//	public String getIsActivities() {
//		return isActivities;
//	}
//
//	public void setIsActivities(String isActivities) {
//		this.isActivities = isActivities;
//	}
//
//	public Long getActivitiesMoney() {
//		return activitiesMoney;
//	}
//
//	public void setActivitiesMoney(Long activitiesMoney) {
//		this.activitiesMoney = activitiesMoney;
//	}
//
//	public String getIsVoucher() {
//		return isVoucher;
//	}
//
//	public void setIsVoucher(String isVoucher) {
//		this.isVoucher = isVoucher;
//	}
//
//	public Long getVoucherMoney() {
//		return voucherMoney;
//	}
//
//	public void setVoucherMoney(Long voucherMoney) {
//		this.voucherMoney = voucherMoney;
//	}
//
//	public BigDecimal getDiscount() {
//		return discount;
//	}
//
//	public void setDiscount(BigDecimal discount) {
//		this.discount = discount;
//	}
//
//	public String getIsAllPay() {
//		return isAllPay;
//	}
//
//	public void setIsAllPay(String isAllPay) {
//		this.isAllPay = isAllPay;
//	}
//
//	public Double getEarnestRate() {
//		return earnestRate;
//	}
//
//	public void setEarnestRate(Double earnestRate) {
//		this.earnestRate = earnestRate;
//	}
//
//	public Long getEarnestMoney() {
//		return earnestMoney;
//	}
//
//	public void setEarnestMoney(Long earnestMoney) {
//		this.earnestMoney = earnestMoney;
//	}
//
//	public Long getExpensesMoney() {
//		return expensesMoney;
//	}
//
//	public void setExpensesMoney(Long expensesMoney) {
//		this.expensesMoney = expensesMoney;
//	}
//
//	public Long getPayMoney() {
//		return payMoney;
//	}
//
//	public void setPayMoney(Long payMoney) {
//		this.payMoney = payMoney;
//	}
//
//	public Long getOrderMoney() {
//		return orderMoney;
//	}
//
//	public void setOrderMoney(Long orderMoney) {
//		this.orderMoney = orderMoney;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public Long getRefundMoney() {
//		return refundMoney;
//	}
//
//	public void setRefundMoney(Long refundMoney) {
//		this.refundMoney = refundMoney;
//	}
//
//	public String getRefundFlag() {
//		return refundFlag;
//	}
//
//	public void setRefundFlag(String refundFlag) {
//		this.refundFlag = refundFlag;
//	}
//
//	public Date getAdviseSendDate() {
//		return adviseSendDate;
//	}
//
//	public void setAdviseSendDate(Date adviseSendDate) {
//		this.adviseSendDate = adviseSendDate;
//	}
//
//	public Date getBespeakSendDate() {
//		return bespeakSendDate;
//	}
//
//	public void setBespeakSendDate(Date bespeakSendDate) {
//		this.bespeakSendDate = bespeakSendDate;
//	}
//
//	public String getSendOutDate() {
//		return sendOutDate;
//	}
//
//	public void setSendOutDate(String sendOutDate) {
//		this.sendOutDate = sendOutDate;
//	}
//
//	public String getOrderSaleCode() {
//		return orderSaleCode;
//	}
//
//	public void setOrderSaleCode(String orderSaleCode) {
//		this.orderSaleCode = orderSaleCode;
//	}
//
//	public String getOrderConfirmCode() {
//		return orderConfirmCode;
//	}
//
//	public void setOrderConfirmCode(String orderConfirmCode) {
//		this.orderConfirmCode = orderConfirmCode;
//	}
//
//	public String getCustomName() {
//		return customName;
//	}
//
//	public void setCustomName(String customName) {
//		this.customName = customName;
//	}
//
//	public String getDeliveryMode() {
//		return deliveryMode;
//	}
//
//	public void setDeliveryMode(String deliveryMode) {
//		this.deliveryMode = deliveryMode;
//	}
//
//	public String getCustomPhone() {
//		return customPhone;
//	}
//
//	public void setCustomPhone(String customPhone) {
//		this.customPhone = customPhone;
//	}
//
//	public String getCustomProvinceCode() {
//		return customProvinceCode;
//	}
//
//	public void setCustomProvinceCode(String customProvinceCode) {
//		this.customProvinceCode = customProvinceCode;
//	}
//
//	public String getCustomCityCode() {
//		return customCityCode;
//	}
//
//	public void setCustomCityCode(String customCityCode) {
//		this.customCityCode = customCityCode;
//	}
//
//	public String getCustomAreaCode() {
//		return customAreaCode;
//	}
//
//	public void setCustomAreaCode(String customAreaCode) {
//		this.customAreaCode = customAreaCode;
//	}
//
//	public String getOrderCustomAddress() {
//		return orderCustomAddress;
//	}
//
//	public void setOrderCustomAddress(String orderCustomAddress) {
//		this.orderCustomAddress = orderCustomAddress;
//	}
//
//	public String getIsInvoice() {
//		return isInvoice;
//	}
//
//	public void setIsInvoice(String isInvoice) {
//		this.isInvoice = isInvoice;
//	}
//
//	public String getInvoiceType() {
//		return invoiceType;
//	}
//
//	public void setInvoiceType(String invoiceType) {
//		this.invoiceType = invoiceType;
//	}
//
//	public String getInvoiceTitleType() {
//		return invoiceTitleType;
//	}
//
//	public void setInvoiceTitleType(String invoiceTitleType) {
//		this.invoiceTitleType = invoiceTitleType;
//	}
//
//	public String getInvoiceNum() {
//		return invoiceNum;
//	}
//
//	public void setInvoiceNum(String invoiceNum) {
//		this.invoiceNum = invoiceNum;
//	}
//
//	public String getInvoiceTitle() {
//		return invoiceTitle;
//	}
//
//	public void setInvoiceTitle(String invoiceTitle) {
//		this.invoiceTitle = invoiceTitle;
//	}
//
//	public String getInvoiceContext() {
//		return invoiceContext;
//	}
//
//	public void setInvoiceContext(String invoiceContext) {
//		this.invoiceContext = invoiceContext;
//	}
//
//	public Long getInvoiceMoney() {
//		return invoiceMoney;
//	}
//
//	public void setInvoiceMoney(Long invoiceMoney) {
//		this.invoiceMoney = invoiceMoney;
//	}
//
//	public String geteInvoiceNo() {
//		return eInvoiceNo;
//	}
//
//	public void seteInvoiceNo(String eInvoiceNo) {
//		this.eInvoiceNo = eInvoiceNo;
//	}
//
//	 
//
//	public String getInvoiceProviceCode() {
//		return invoiceProviceCode;
//	}
//
//	public void setInvoiceProviceCode(String invoiceProviceCode) {
//		this.invoiceProviceCode = invoiceProviceCode;
//	}
//
//	public String getInvoiceProvice() {
//		return invoiceProvice;
//	}
//
//	public void setInvoiceProvice(String invoiceProvice) {
//		this.invoiceProvice = invoiceProvice;
//	}
//
//	public String getInvoiceCityCode() {
//		return invoiceCityCode;
//	}
//
//	public void setInvoiceCityCode(String invoiceCityCode) {
//		this.invoiceCityCode = invoiceCityCode;
//	}
//
//	public String getInvoiceAreaCode() {
//		return invoiceAreaCode;
//	}
//
//	public void setInvoiceAreaCode(String invoiceAreaCode) {
//		this.invoiceAreaCode = invoiceAreaCode;
//	}
//
//	public String getInvoiceAddress() {
//		return invoiceAddress;
//	}
//
//	public void setInvoiceAddress(String invoiceAddress) {
//		this.invoiceAddress = invoiceAddress;
//	}
//
//	public String getInvoiceTakerPhone() {
//		return invoiceTakerPhone;
//	}
//
//	public void setInvoiceTakerPhone(String invoiceTakerPhone) {
//		this.invoiceTakerPhone = invoiceTakerPhone;
//	}
//
//	public String getInvoiceTakerEmail() {
//		return invoiceTakerEmail;
//	}
//
//	public void setInvoiceTakerEmail(String invoiceTakerEmail) {
//		this.invoiceTakerEmail = invoiceTakerEmail;
//	}
//
//	public String getInvoiceTaker() {
//		return invoiceTaker;
//	}
//
//	public void setInvoiceTaker(String invoiceTaker) {
//		this.invoiceTaker = invoiceTaker;
//	}
//
//	public Date getUpdateTime() {
//		return updateTime;
//	}
//
//	public void setUpdateTime(Date updateTime) {
//		this.updateTime = updateTime;
//	}
//
//	public String getOrderCreateCode() {
//		return orderCreateCode;
//	}
//
//	public void setOrderCreateCode(String orderCreateCode) {
//		this.orderCreateCode = orderCreateCode;
//	}
//
//	public Date getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//
//	public String getRemark() {
//		return remark;
//	}
//
//	public void setRemark(String remark) {
//		this.remark = remark;
//	}
//
//	public String getRemark4() {
//		return remark4;
//	}
//
//	public void setRemark4(String remark4) {
//		this.remark4 = remark4;
//	}
//
//	public String getRemark3() {
//		return remark3;
//	}
//
//	public void setRemark3(String remark3) {
//		this.remark3 = remark3;
//	}
//
//	public String getRemark2() {
//		return remark2;
//	}
//
//	public void setRemark2(String remark2) {
//		this.remark2 = remark2;
//	}
//
//	public List<OrderDetailDto> getOrderDetails() {
//		return orderDetails;
//	}
//
//	public void setOrderDetails(List<OrderDetailDto> orderDetails) {
//		this.orderDetails = orderDetails;
//	}
//
//	public List<OrderVoucherDto> getOrderVouchers() {
//		return orderVouchers;
//	}
//
//	public void setOrderVouchers(List<OrderVoucherDto> orderVouchers) {
//		this.orderVouchers = orderVouchers;
//	}
//
//	public List<OrderPromotionDto> getOrderPromotions() {
//		return orderPromotions;
//	}
//
//	public void setOrderPromotions(List<OrderPromotionDto> orderPromotions) {
//		this.orderPromotions = orderPromotions;
//	}
//
//	public String getWeixinNo() {
//		return weixinNo;
//	}
//
//	public void setWeixinNo(String weixinNo) {
//		this.weixinNo = weixinNo;
//	}
//
//	public String getCustomProvince() {
//		return customProvince;
//	}
//
//	public void setCustomProvince(String customProvince) {
//		this.customProvince = customProvince;
//	}
//
//	public String getCustomCity() {
//		return customCity;
//	}
//
//	public void setCustomCity(String customCity) {
//		this.customCity = customCity;
//	}
//
//	public String getCustomArea() {
//		return customArea;
//	}
//
//	public void setCustomArea(String customArea) {
//		this.customArea = customArea;
//	}
//
//	public String getInvoicecoName() {
//		return invoicecoName;
//	}
//
//	public void setInvoicecoName(String invoicecoName) {
//		this.invoicecoName = invoicecoName;
//	}
//
//	public String getInvoicecoAddr() {
//		return invoicecoAddr;
//	}
//
//	public void setInvoicecoAddr(String invoicecoAddr) {
//		this.invoicecoAddr = invoicecoAddr;
//	}
//
//	public String getInvoicecoPhone() {
//		return invoicecoPhone;
//	}
//
//	public void setInvoicecoPhone(String invoicecoPhone) {
//		this.invoicecoPhone = invoicecoPhone;
//	}
//
//	public String getInvoicecoBank() {
//		return invoicecoBank;
//	}
//
//	public void setInvoicecoBank(String invoicecoBank) {
//		this.invoicecoBank = invoicecoBank;
//	}
//
//	public String getInvoicecoBankaccound() {
//		return invoicecoBankaccound;
//	}
//
//	public void setInvoicecoBankaccound(String invoicecoBankaccound) {
//		this.invoicecoBankaccound = invoicecoBankaccound;
//	}
//	
//	
//
//	public String getCustName() {
//		return custName;
//	}
//
//	public void setCustName(String custName) {
//		this.custName = custName;
//	}
//
//	
//	
//	public Long getPaidMoney() {
//		return paidMoney;
//	}
//
//	public void setPaidMoney(Long paidMoney) {
//		this.paidMoney = paidMoney;
//	}
//
//	public String getHeadAddress() {
//        return headAddress;
//    }
//
//    public void setHeadAddress(String headAddress) {
//        this.headAddress = headAddress;
//    }
//
//	/**
//	 * @return the orderMethod
//	 */
//	public Integer getOrderMethod() {
//		return orderMethod;
//	}
//
//	/**
//	 * @param orderMethod the orderMethod to set
//	 */
//	public void setOrderMethod(Integer orderMethod) {
//		this.orderMethod = orderMethod;
//	}
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("OrderRequestDto [orderCode=");
//		builder.append(orderCode);
//		builder.append(", orderNo=");
//		builder.append(orderNo);
//		builder.append(", outOrderNo=");
//		builder.append(outOrderNo);
//		builder.append(", outCustomNo=");
//		builder.append(outCustomNo);
//		builder.append(", weixinNo=");
//		builder.append(weixinNo);
//		builder.append(", merchantNo=");
//		builder.append(merchantNo);
//		builder.append(", shopNo=");
//		builder.append(shopNo);
//		builder.append(", custName=");
//		builder.append(custName);
//		builder.append(", headAddress=");
//		builder.append(headAddress);
//		builder.append(", custPhone=");
//		builder.append(custPhone);
//		builder.append(", memberNo=");
//		builder.append(memberNo);
//		builder.append(", orderType=");
//		builder.append(orderType);
//		builder.append(", orderSource=");
//		builder.append(orderSource);
//		builder.append(", isActivities=");
//		builder.append(isActivities);
//		builder.append(", activitiesMoney=");
//		builder.append(activitiesMoney);
//		builder.append(", isVoucher=");
//		builder.append(isVoucher);
//		builder.append(", voucherMoney=");
//		builder.append(voucherMoney);
//		builder.append(", discount=");
//		builder.append(discount);
//		builder.append(", isAllPay=");
//		builder.append(isAllPay);
//		builder.append(", earnestRate=");
//		builder.append(earnestRate);
//		builder.append(", earnestMoney=");
//		builder.append(earnestMoney);
//		builder.append(", expensesMoney=");
//		builder.append(expensesMoney);
//		builder.append(", payMoney=");
//		builder.append(payMoney);
//		builder.append(", paidMoney=");
//		builder.append(paidMoney);
//		builder.append(", orderMoney=");
//		builder.append(orderMoney);
//		builder.append(", status=");
//		builder.append(status);
//		builder.append(", refundMoney=");
//		builder.append(refundMoney);
//		builder.append(", refundFlag=");
//		builder.append(refundFlag);
//		builder.append(", adviseSendDate=");
//		builder.append(adviseSendDate);
//		builder.append(", bespeakSendDate=");
//		builder.append(bespeakSendDate);
//		builder.append(", sendOutDate=");
//		builder.append(sendOutDate);
//		builder.append(", orderSaleCode=");
//		builder.append(orderSaleCode);
//		builder.append(", orderConfirmCode=");
//		builder.append(orderConfirmCode);
//		builder.append(", customName=");
//		builder.append(customName);
//		builder.append(", deliveryMode=");
//		builder.append(deliveryMode);
//		builder.append(", customPhone=");
//		builder.append(customPhone);
//		builder.append(", customProvinceCode=");
//		builder.append(customProvinceCode);
//		builder.append(", customProvince=");
//		builder.append(customProvince);
//		builder.append(", customCityCode=");
//		builder.append(customCityCode);
//		builder.append(", customCity=");
//		builder.append(customCity);
//		builder.append(", customAreaCode=");
//		builder.append(customAreaCode);
//		builder.append(", customArea=");
//		builder.append(customArea);
//		builder.append(", orderCustomAddress=");
//		builder.append(orderCustomAddress);
//		builder.append(", isInvoice=");
//		builder.append(isInvoice);
//		builder.append(", invoiceType=");
//		builder.append(invoiceType);
//		builder.append(", invoiceTitleType=");
//		builder.append(invoiceTitleType);
//		builder.append(", invoiceNum=");
//		builder.append(invoiceNum);
//		builder.append(", invoiceTitle=");
//		builder.append(invoiceTitle);
//		builder.append(", invoiceContext=");
//		builder.append(invoiceContext);
//		builder.append(", invoiceMoney=");
//		builder.append(invoiceMoney);
//		builder.append(", eInvoiceNo=");
//		builder.append(eInvoiceNo);
//		builder.append(", invoiceProviceCode=");
//		builder.append(invoiceProviceCode);
//		builder.append(", invoiceProvice=");
//		builder.append(invoiceProvice);
//		builder.append(", invoiceCityCode=");
//		builder.append(invoiceCityCode);
//		builder.append(", invoiceCity=");
//		builder.append(invoiceCity);
//		builder.append(", invoiceAreaCode=");
//		builder.append(invoiceAreaCode);
//		builder.append(", invoiceArea=");
//		builder.append(invoiceArea);
//		builder.append(", invoiceAddress=");
//		builder.append(invoiceAddress);
//		builder.append(", invoiceTakerPhone=");
//		builder.append(invoiceTakerPhone);
//		builder.append(", invoiceTakerEmail=");
//		builder.append(invoiceTakerEmail);
//		builder.append(", invoiceTaker=");
//		builder.append(invoiceTaker);
//		builder.append(", updateTime=");
//		builder.append(updateTime);
//		builder.append(", orderCreateCode=");
//		builder.append(orderCreateCode);
//		builder.append(", createTime=");
//		builder.append(createTime);
//		builder.append(", remark=");
//		builder.append(remark);
//		builder.append(", remark4=");
//		builder.append(remark4);
//		builder.append(", remark3=");
//		builder.append(remark3);
//		builder.append(", remark2=");
//		builder.append(remark2);
//		builder.append(", returnUrl=");
//		builder.append(returnUrl);
//		builder.append(", payType=");
//		builder.append(payType);
//		builder.append(", invoicecoName=");
//		builder.append(invoicecoName);
//		builder.append(", invoicecoAddr=");
//		builder.append(invoicecoAddr);
//		builder.append(", invoicecoPhone=");
//		builder.append(invoicecoPhone);
//		builder.append(", invoicecoBank=");
//		builder.append(invoicecoBank);
//		builder.append(", invoicecoBankaccound=");
//		builder.append(invoicecoBankaccound);
//		builder.append(", paymentMoney=");
//		builder.append(paymentMoney);
//		builder.append(", orderMethod=");
//		builder.append(orderMethod);
//		builder.append(", orderDetails=");
//		builder.append(orderDetails);
//		builder.append(", orderVouchers=");
//		builder.append(orderVouchers);
//		builder.append(", orderPromotions=");
//		builder.append(orderPromotions);
//		builder.append("]");
//		return builder.toString();
//	}
//
//}
