package com.ye.business.hx.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IBillDao;
import com.ye.business.hx.dao.IBillDetailDao;
import com.ye.business.hx.dao.IBillOperateDao;
import com.ye.business.hx.dao.IBillPaymentDao;
import com.ye.business.hx.dao.IBillRefundDao;
import com.ye.business.hx.dao.IBillRefundDetailDao;
import com.ye.business.hx.dao.IBillSnapshotDao;
import com.ye.business.hx.dao.IPatientServiceDao;
import com.ye.business.hx.dao.IPayDetailDao;
import com.ye.business.hx.dao.IPayTypeDetailDao;
import com.ye.business.hx.domain.Bill;
import com.ye.business.hx.domain.BillDetail;
import com.ye.business.hx.domain.BillOperate;
import com.ye.business.hx.domain.BillPayment;
import com.ye.business.hx.domain.BillRefund;
import com.ye.business.hx.domain.BillRefundDetail;
import com.ye.business.hx.domain.BillSnapshot;
import com.ye.business.hx.domain.PatientService;
import com.ye.business.hx.domain.PayDetail;
import com.ye.business.hx.domain.PayTypeDetail;
import com.ye.business.hx.dto.BillDetailDto;
import com.ye.business.hx.dto.BillDto;
import com.ye.business.hx.dto.BillOperateDto;
import com.ye.business.hx.dto.BillPaymentDto;
import com.ye.business.hx.dto.BillRefundDetailDto;
import com.ye.business.hx.dto.BillRefundDto;
import com.ye.business.hx.dto.FindBillDetailPage;
import com.ye.business.hx.dto.FindBillOperatePage;
import com.ye.business.hx.dto.FindBillPage;
import com.ye.business.hx.dto.FindBillRefundDetailPage;
import com.ye.business.hx.dto.FindPayDetailPage;
import com.ye.business.hx.dto.PayDetailDto;
import com.ye.business.hx.dto.PayTypeDetailDto;
import com.ye.business.hx.emus.BillOperateProcess;
import com.ye.business.hx.emus.BillOperateStatus;
import com.ye.business.hx.emus.BillOperateType;
import com.ye.business.hx.emus.BillRtStatus;
import com.ye.business.hx.emus.CheckStatus;
import com.ye.business.hx.emus.CommonStatus;
import com.ye.business.hx.emus.OptType;
import com.ye.business.hx.emus.PayMode;
import com.ye.business.hx.emus.PayStatus;
import com.ye.business.hx.emus.RefundStatus;
import com.ye.business.hx.service.IBillService;
import com.ye.business.hx.util.GenerateNo;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
@Service
public class BillServiceImpl implements IBillService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);
	

	@Resource
	private IBillDao billDao;
	@Resource
	private IBillDetailDao billDetailDao;
	@Resource
	private IBillOperateDao billOperateDao;
	@Resource
	private IBillPaymentDao billPaymentDao;
	@Resource
	private IBillSnapshotDao billSnapshotDao;
	@Resource
	private IBillRefundDao billRefundDao;
	@Resource
	private IBillRefundDetailDao billRefundDetailDao;
	@Resource
	private IPatientServiceDao patientServiceDao;
	@Resource
	private IPayDetailDao payDetailDao;
	@Resource
	private IPayTypeDetailDao payTypeDetailDao;
	
	@Override
	public BillDto addBill(
			BillDto billDto) throws TsfaServiceException {
		logger.debug("addBill(AddBill addBill={}) - start", billDto); 

		AssertUtils.notNull(billDto);
		try {
			Bill bill = new Bill();
			//add数据录入
			bill.setCode(GUID.getPreUUID());
			bill.setBillNo(GenerateNo.getBillNo());
			bill.setPatientNo(billDto.getPatientNo());
			bill.setPatientName(billDto.getPatientName());
			bill.setMedicalNo(billDto.getMedicalNo());
			bill.setShopNo(billDto.getShopNo());
			bill.setShopName(billDto.getShopName());
			bill.setMerchantNo(billDto.getMerchantNo());
			bill.setMerchantName(billDto.getMerchantName());
			bill.setBillType(billDto.getBillType());
			
//			bill.setOriginalAmount(billDto.getOriginalAmount());//原价款
//			bill.setReallyAmount(billDto.getReallyAmount());//应付款
//			bill.setDiscountNum(billDto.getDiscountNum());//折扣
//			bill.setPayAmount(0L);//已付款
//			bill.setDebtAmount(billDto.getReallyAmount());//欠款 ，初始 欠款=应付款
			bill.setOriginalAmount(billDto.getBillOperate().getOriginalAmount());//原价款
			bill.setReallyAmount(billDto.getBillOperate().getReallyAmount());//应付款
			bill.setDiscountNum(billDto.getBillOperate().getDiscountNum());//折扣
			bill.setPayAmount(0L);//已付款
			bill.setDebtAmount(billDto.getBillOperate().getReallyAmount());//欠款 ，初始 欠款=应付款
			bill.setRtAmount(0L);//已退款
			
			bill.setPayStatus(PayStatus.UNPAY.name());//初始 待收費状态
			bill.setRtStatus(BillRtStatus.NO.name());//初始 没有退费
			bill.setStatus(CommonStatus.NORMAL.name());//初始 正常
			bill.setClinicTime(billDto.getClinicTime());//就诊时间
//			bill.setPayTime(billDto.getPayTime());
//			bill.setUpdateId(billDto.getUpdateId());
//			bill.setUpdateDate(new Date());
			
//			bill.setCreateId(billDto.getCreateId());
//			bill.setCreateName(billDto.getCreateName());
			bill.setCreateId(billDto.getBillOperate().getMemberNoGuid());
			bill.setCreateName(billDto.getBillOperate().getMemberNameGuid());
			
			bill.setCreateDate(new Date());
			bill.setRemark(billDto.getBillOperate().getRemark());//备注
			bill.setRemark1(billDto.getRemark1());
			bill.setRemark2(billDto.getRemark2());
			bill.setRemark3(billDto.getRemark3());
			bill.setRemark4(billDto.getPatientServiceCode());
			//1.先添加账单信息
			billDao.insertSelective(bill);
			
			BillDto rtBillDto=new BillDto();
			rtBillDto.setCode(bill.getCode());
			rtBillDto.setBillType(bill.getBillType());
			//2.添加账单详细信息
			addBillDetail(bill, billDto.getDetails());
			//3.添加账单处理信息（待收费信息）
			BillOperate billOperate = addPayBillOperate(bill, billDto.getBillOperate());
			//3.2添加账单项目实收（待收费项目信息）
			addFirstPayDetail(bill,billOperate.getCode(), billDto.getDetails());//2019年7月9日 前端未开发注释掉
			//4.直接收费则添加支付记录，计算账单金额信息
			if(OptType.PAY.name().equals(billDto.getBillOperate().getOptType())) {
				payment(bill,billOperate,null);
			}
			//5.从今日工作 预约挂号内容过来的，则给挂号单记录是哪个账单
			if(StringUtils.isNotEmpty(billDto.getPatientServiceCode())) {
				PatientService record=new PatientService();
				record.setRemark4(bill.getCode());
				record.setCode(billDto.getPatientServiceCode());
				patientServiceDao.updateByPrimaryKeySelective(record);
			}
			logger.debug("addBill(BillDto) - end - return"); 
			return rtBillDto;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_ADD_ERROR,"新增账单信息错误！",e);
		}
	}
	
	private void payment(Bill bill,BillOperate billOperate,String refundCode) {
		//1.检测，待处理状态的交易才可操作
		if(!BillOperateStatus.INIT.name().equals(billOperate.getStatus())) {
			logger.error("账单交易操作错误！非未处理状态。BillOperate:{}",billOperate);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_UPDATE_ERROR,"该账单已处理！");
		}
		//2.1处理账单前加快照
		addBillSnapshot(bill, billOperate);
		//2.2 处理
		if(BillOperateType.PAY.name().equals(billOperate.getOperateType())) {
			//2.1 支付
			//2.1修改账单交易状态
			BillOperate updateBillOperate=new BillOperate();
			updateBillOperate.setCode(billOperate.getCode());
			updateBillOperate.setStatus(BillOperateStatus.FINISH.name());
			updateBillOperate.setProcess(BillOperateProcess.FINISH.name());
			billOperateDao.updateByPrimaryKeySelective(updateBillOperate);
			//2.2填加支付记录
			addBillPayment(bill, billOperate);
			//2.3修改账单款项信息
			Bill updatebill=new Bill();
			updatebill.setCode(billOperate.getBillCode());
			updatebill.setDebtAmount(billOperate.getDebtAmount());//欠款
			updatebill.setPayAmount(billOperate.getPayAmount());// 实付款
			updatebill.setPayTime(billOperate.getPayTime());//首次支付时间
			updatebill.setRemark2(billOperate.getRecieverName());//首次收费人
			updatebill.setRemark3(billOperate.getPayTypeName());//首次收费方式
			
			if (billOperate.getPayAmount().equals(billOperate.getReallyAmount())) {
				updatebill.setPayStatus(PayStatus.FINISH.name());// 付清
			}else {
				updatebill.setPayStatus(PayStatus.ARREARAGE.name());// 未结清
			}
			billDao.updateByPrimaryKeySelective(updatebill);
			
			//3.清算账单项目款项信息
			paymentProjectDetail(bill, billOperate);
			
		} else if (BillOperateType.DEBT.name().equals(billOperate.getOperateType())) {
			//2.2 收欠款
			
			//2.1修改账单交易状态
			BillOperate updateBillOperate=new BillOperate();
			updateBillOperate.setCode(billOperate.getCode());
			updateBillOperate.setStatus(BillOperateStatus.FINISH.name());
			updateBillOperate.setProcess(BillOperateProcess.FINISH.name());
			billOperateDao.updateByPrimaryKeySelective(updateBillOperate);
			//2.2填加支付记录
			addBillPayment(bill, billOperate);
			
			//2.3修改账单款项信息
			Bill updatebill=new Bill();
			updatebill.setCode(billOperate.getBillCode());
			updatebill.setDebtAmount(billOperate.getDebtAmount());//欠款
			// 实付款=账单已付+该交易实付款额度
			updatebill.setPayAmount(bill.getPayAmount() + billOperate.getPayAmount());
			// 应付款=账单已付+该交易应付款额度
			updatebill.setReallyAmount(bill.getPayAmount() + billOperate.getReallyAmount());//应付款
			// 折扣=应付款/原价款 ，且按四舍五入格式保留4位小数，存到DB存贮万分比数值，比如0.66666折，四舍五入后即0.6667折，存的数据为6667
			BigDecimal discNum = new BigDecimal(updatebill.getReallyAmount())
					.divide(new BigDecimal(bill.getOriginalAmount()),4,RoundingMode.HALF_UP).multiply(new BigDecimal(10000));
			updatebill.setDiscountNum(discNum.intValue());
			
			if (billOperate.getPayAmount().equals(billOperate.getReallyAmount())) {
				updatebill.setPayStatus(PayStatus.FINISH.name());// 付清
			}else {
				updatebill.setPayStatus(PayStatus.ARREARAGE.name());// 未结清
			}
			billDao.updateByPrimaryKeySelective(updatebill);
			
			//3.清算账单项目款项信息
			paymentProjectDetail(bill, billOperate);
			
		} else if (BillOperateType.REFUND.name().equals(billOperate.getOperateType())) {
			//2.3 退款
			paymentRefund(bill, billOperate, refundCode);
		}
	}
	
	/**
	 * 账单项目款项信息结算
	 * @param bill
	 * @param billOperate
	 */
	private void paymentProjectDetail(Bill bill,BillOperate billOperate) {
		//1.1本次交易的项目 收费信息
		FindPayDetailPage findBillRefundDetailPage = new FindPayDetailPage();
		PayDetailDto billRefundDetailDto = new PayDetailDto();
		billRefundDetailDto.setOperateCode(billOperate.getCode());
		findBillRefundDetailPage.setParam(billRefundDetailDto);
		List<PayDetailDto> rtDetailDtos = payDetailDao.findPayDetails(findBillRefundDetailPage);
		//1.2账单项目的收费信息
		FindBillDetailPage findBillDetailPage=new FindBillDetailPage();
		BillDetailDto findDetail=new BillDetailDto();
		findDetail.setBillCode(bill.getCode());
		findBillDetailPage.setParam(findDetail);
		List<BillDetailDto> detailDtos= billDetailDao.findBillDetails(findBillDetailPage);
		//1.3key=ProjectCode
		Map<String, BillDetailDto> map=new HashMap<>();
		for (Iterator<BillDetailDto> iterator = detailDtos.iterator(); iterator.hasNext();) {
			BillDetailDto ele =  iterator.next();
			map.put(ele.getProjectCode(), ele);
		}
		
		//2.账单项目金額结算 & 交易项目原各金额回写用于支付作废使用
		PayDetail record = new PayDetail();
		BillDetail billDetail=new BillDetail();
		for (Iterator<PayDetailDto> iterator = rtDetailDtos.iterator(); iterator.hasNext();) {
			PayDetailDto ele =  iterator.next();
			BillDetailDto rtInfo=map.get(ele.getProjectCode());
			if(rtInfo!=null) {
				record.setCode(ele.getCode());
				record.setOriginalPayAmount(rtInfo.getPayAmount());
				record.setOriginalReallyAmount(rtInfo.getReallyAmount());
				record.setOriginalDebtAmount(rtInfo.getDebtAmount());
				payDetailDao.updateByPrimaryKeySelective(record);
				
				billDetail.setCode(rtInfo.getCode());
				billDetail.setDebtAmount(ele.getDebtAmount());//欠款
				// 实付款=账单已付+该交易实付款额度
				billDetail.setPayAmount(rtInfo.getPayAmount() + ele.getPayAmount());
				// 应付款=账单已付+该交易应付款额度
				billDetail.setReallyAmount(rtInfo.getPayAmount() + ele.getReallyAmount());//应付款
				billDetailDao.updateByPrimaryKeySelective(billDetail);
			}
		}
	
	}
	
	/**
	 * 退款
	 * @param bill
	 * @param billOperate
	 * @param refundCode
	 */
	private void paymentRefund(Bill bill,BillOperate billOperate,String refundCode) {
		//2.3 退款
		if(!CheckStatus.PASS.name().equals(billOperate.getCheckStatus())) {
			logger.error("账单交易操作错误！不是审核通过状态。BillOperate:{}",billOperate);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_UPDATE_ERROR,"该账单不是审核通过状态！");
		}
		//2.1修改账单交易状态
		BillOperate updateBillOperate=new BillOperate();
		updateBillOperate.setCode(billOperate.getCode());
		updateBillOperate.setStatus(BillOperateStatus.FINISH.name());
		updateBillOperate.setProcess(BillOperateProcess.FINISH.name());
		billOperateDao.updateByPrimaryKeySelective(updateBillOperate);
		//2.2填加支付记录
		addBillPayment(bill, billOperate);
		//2.3修改账单款项信息 （退款时账单仅累加退款总额，账单已付及欠款信息不变）
		Bill updatebill=new Bill();
		updatebill.setCode(billOperate.getBillCode());
		//欠款=账单已欠+该交易退款额度
//		updatebill.setDebtAmount(bill.getDebtAmount() + billOperate.getPayAmount());
		//已付=账单已付-该交易退款额度
//		updatebill.setPayAmount(bill.getPayAmount() - billOperate.getPayAmount());
		// 退款成功则未结清
//		updatebill.setPayStatus(PayStatus.ARREARAGE.name());// 未结清
		// 退款=账单已退+该交易退款额度
		updatebill.setRtAmount(bill.getRtAmount() + billOperate.getPayAmount());
		updatebill.setRtStatus(BillRtStatus.RT.name());
		billDao.updateByPrimaryKeySelective(updatebill);
		
		//2.4修改账单项目退款信息
		//1退款项目
		BillRefund updateRefund=new BillRefund();
		updateRefund.setCode(refundCode);
		updateRefund.setRefundStatus(RefundStatus.FINISH.name());
		billRefundDao.updateByPrimaryKeySelective(updateRefund);
		
		FindBillRefundDetailPage findBillRefundDetailPage = new FindBillRefundDetailPage();
		BillRefundDetailDto billRefundDetailDto = new BillRefundDetailDto();
		billRefundDetailDto.setRefundCode(refundCode);
		findBillRefundDetailPage.setParam(billRefundDetailDto);
		List<BillRefundDetailDto> rtDetailDtos=billRefundDetailDao.findBillRefundDetails(findBillRefundDetailPage);
		Map<String, BillRefundDetailDto> map=new HashMap<>();
		for (Iterator<BillRefundDetailDto> iterator = rtDetailDtos.iterator(); iterator.hasNext();) {
			BillRefundDetailDto ele =  iterator.next();
			map.put(ele.getProjectCode(), ele);
		}
		
		FindBillDetailPage findBillDetailPage=new FindBillDetailPage();
		BillDetailDto findDetail=new BillDetailDto();
		findDetail.setBillCode(bill.getCode());
		findBillDetailPage.setParam(findDetail);
		List<BillDetailDto> detailDtos= billDetailDao.findBillDetails(findBillDetailPage);
		//2项目退款金额和数量 累加 到当前账单项目中
		BillDetail record = new BillDetail();
		for (Iterator<BillDetailDto> iterator = detailDtos.iterator(); iterator.hasNext();) {
			BillDetailDto ele =  iterator.next();
			BillRefundDetailDto rtInfo=map.get(ele.getProjectCode());
			if(rtInfo!=null) {
				record.setCode(ele.getCode());
				if(rtInfo.getItemNum()!=null) {
					//退款数=已退款数+该次的退款数
					record.setRtNum(ele.getRtNum() + rtInfo.getItemNum());
				}
				if(rtInfo.getReturnAmount()!=null) {
				//退款金额=已退款金额+该次的退款金额
					record.setRtAmount(ele.getRtAmount() + rtInfo.getReturnAmount());
				}
				billDetailDao.updateByPrimaryKeySelective(record);
			}
		}
	
	}
	
	private void addBillSnapshot(Bill bill,BillOperate billOperate) throws TsfaServiceException {
		logger.debug("addBillSnapshot(Bill bill={}) - start", bill); 
		try {
			BillSnapshot billSnapshot = new BillSnapshot();
			//add数据录入
			billSnapshot.setCode(GUID.getPreUUID());
			billSnapshot.setBillCode(bill.getCode());
			billSnapshot.setOperateCode(billOperate.getCode());
			billSnapshot.setPatientNo(bill.getPatientNo());
			billSnapshot.setPatientName(bill.getPatientName());
			billSnapshot.setMedicalNo(bill.getMedicalNo());
			billSnapshot.setShopNo(bill.getShopNo());
			billSnapshot.setShopName(bill.getShopName());
			billSnapshot.setMerchantNo(bill.getMerchantNo());
			billSnapshot.setMerchantName(bill.getMerchantName());
			billSnapshot.setOriginalAmount(bill.getOriginalAmount());
			billSnapshot.setReallyAmount(bill.getReallyAmount());
			billSnapshot.setDiscountNum(bill.getDiscountNum());
			billSnapshot.setPayAmount(bill.getPayAmount());
			billSnapshot.setDebtAmount(bill.getDebtAmount());
			billSnapshot.setRtAmount(bill.getRtAmount());
			billSnapshot.setPayStatus(bill.getPayStatus());
			billSnapshot.setRtStatus(bill.getRtStatus());
			billSnapshot.setStatus(bill.getStatus());
			billSnapshot.setClinicTime(bill.getClinicTime());
			billSnapshot.setUpdateId(bill.getUpdateId());
			billSnapshot.setUpdateDate(new Date());
			billSnapshot.setCreateId(bill.getCreateId());
			billSnapshot.setCreateName(bill.getCreateName());
			billSnapshot.setCreateDate(new Date());
			billSnapshot.setRemark(bill.getRemark());
			billSnapshot.setRemark1(bill.getRemark1());
			billSnapshot.setRemark2(bill.getRemark2());
			billSnapshot.setRemark3(bill.getRemark3());
			billSnapshot.setRemark4(bill.getRemark4());
			billSnapshot.setSnapshotTime(new Date());
			billSnapshotDao.insertSelective(billSnapshot);
			logger.debug("addBillSnapshot(Bill) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单快照信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_SNAPSHOT_ADD_ERROR,"新增账单快照信息错误！",e);
		}
	}
	
	private void addBillPayment(Bill bill,BillOperate billOperate) throws TsfaServiceException {
		logger.debug("addBillPayment(BillOperate billOperate={}) - start", billOperate); 

		AssertUtils.notNull(billOperate);
		//只有交易的金额大于0才记录
//		if(billOperate.getPayAmount()<= 0) {
//			return ;
//		}
		try {
			BillPayment billPayment = new BillPayment();
			//add数据录入
			billPayment.setCode(GUID.getPreUUID());
			billPayment.setPayNo(GenerateNo.getPayNo());
			billPayment.setPatientNo(bill.getPatientNo());
			billPayment.setPatientName(bill.getPatientName());
			billPayment.setMedicalNo(bill.getMedicalNo());
			billPayment.setBillCode(billOperate.getBillCode());
			billPayment.setOperateCode(billOperate.getCode());
			billPayment.setShopNo(bill.getShopNo());
			billPayment.setShopName(bill.getShopName());
			billPayment.setMerchantNo(bill.getMerchantNo());
			billPayment.setMerchantName(bill.getMerchantName());
			billPayment.setPayType(billOperate.getPayType());
			billPayment.setPayTypeName(billOperate.getPayTypeName());
			billPayment.setPayRemark(billOperate.getPayRemark());
			billPayment.setPayAmount(billOperate.getPayAmount());//实收/实退
			billPayment.setPayTime(billOperate.getPayTime());
			billPayment.setRecieverNo(billOperate.getRecieverNo());
			billPayment.setRecieverName(billOperate.getRecieverName());
			billPayment.setBizType(billOperate.getOperateType());
			billPayment.setStatus(CommonStatus.NORMAL.name());
			 
			if(BillOperateType.REFUND.name().equals(billOperate.getOperateType())) {
				billPayment.setPayMode(PayMode.SUB.name());
				//如果是退款，则记录账单此刻的应收款和实收款 
				billPayment.setReceivableAmt(bill.getReallyAmount());
				//账单实收(可退)=已收-已退
				billPayment.setBillPayAmount(bill.getPayAmount()-bill.getRtAmount());
			}else {
				billPayment.setPayMode(PayMode.ADD.name());
				billPayment.setReceivableAmt(billOperate.getReallyAmount());//应收款
				billPayment.setDebtAmt(billOperate.getDebtAmount());//欠收款
			}
//			billPayment.setUpdateId(billPaymentDto.getUpdateId());
//			billPayment.setUpdateDate(new Date());
			billPayment.setCreateId(billOperate.getCreateId());
			billPayment.setCreateDate(new Date());
			billPayment.setRemark(billOperate.getRemark());
			billPayment.setRemark1(billOperate.getRemark1());
			billPayment.setRemark2(billOperate.getRemark2());
			billPayment.setRemark3(billOperate.getRemark3());
			billPayment.setRemark4(billOperate.getRemark4());
			billPaymentDao.insertSelective(billPayment);
			
			//2.支付方式对应的收款(收款才有1对N，才记录)
			addPayTypeDetail(billOperate);
			
			logger.debug("addBillPayment(BillPaymentDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单支付信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_PAYMENT_ADD_ERROR,"新增账单支付信息信息错误！",e);
		}
	}
	
	/**
	 * 新增支付方式款项
	 * @param billOperate
	 */
	private void addPayTypeDetail(BillOperate billOperate) {
		if(StringUtils.isEmpty(billOperate.getPayRemark())) {
			addPayTypeDetailByRefund(billOperate);
		}else{
			addPayTypeDetailByPayRemark(billOperate);
		}
	}
	
	/** 支付方式对应的收款 <br/>
	 *  退款只有一个支付方式，billOperate.payRemark 为空
	 **/
	private void addPayTypeDetailByRefund(BillOperate billOperate) {
		PayTypeDetail billDetail = new PayTypeDetail();
		// add数据录入
		billDetail.setCode(GUID.getPreUUID());
		billDetail.setBillCode(billOperate.getBillCode());
		billDetail.setOperateCode(billOperate.getCode());

		billDetail.setPayType(billOperate.getPayType());
		billDetail.setPayTypeName(billOperate.getPayTypeName());
		billDetail.setPayAmount(billOperate.getPayAmount());
		billDetail.setIndexNo(0);
		payTypeDetailDao.insertSelective(billDetail);
	}
	
	/** 支付方式对应的收款 <br/>
	 *  收费允许多个收费方式
	 **/
	private void addPayTypeDetailByPayRemark(BillOperate billOperate) {
		AssertUtils.notNull(billOperate.getPayRemark(), "支付详细为空");
		//key=支付方式code ,value=支付方式名称 
		Map<String ,String> payCode2Names=new HashMap<>();
		String[] payCodes=billOperate.getPayType().split(",");
		String[] payNames=billOperate.getPayTypeName().split(",");
		for (int i = 0; i < payNames.length; i++) {
			payCode2Names.put(payCodes[i], payNames[i]);
		}
		
		List<PayTypeDetailDto> details = JSONArray.parseArray(billOperate.getPayRemark(), PayTypeDetailDto.class);
		int i = 0;
		for (Iterator<PayTypeDetailDto> iterator = details.iterator(); iterator.hasNext();) {
			PayTypeDetailDto typeDetailDto = (PayTypeDetailDto) iterator.next();
			
			PayTypeDetail billDetail = new PayTypeDetail();
			//add数据录入
			billDetail.setCode(GUID.getPreUUID());
			billDetail.setBillCode(billOperate.getBillCode());
			billDetail.setOperateCode(billOperate.getCode());
	 
			billDetail.setPayType(typeDetailDto.getPayType());
			billDetail.setPayTypeName(payCode2Names.get(typeDetailDto.getPayType()));//获取支付名称
			billDetail.setPayAmount(typeDetailDto.getYesTotalSum().multiply(new BigDecimal(100)).longValue());
			billDetail.setIndexNo(i++);
			payTypeDetailDao.insertSelective(billDetail);
		}
	}
	
	private BillOperate addPayBillOperate(Bill rtBillDto,
			BillOperateDto billOperateDto) throws TsfaServiceException {
		logger.debug("addBillOperate(AddBillOperate addBillOperate={}) - start", billOperateDto); 

		AssertUtils.notNull(billOperateDto);
		try {
			BillOperate billOperate = new BillOperate();
			//add数据录入
			billOperate.setCode(GUID.getPreUUID());
			billOperate.setBillCode(rtBillDto.getCode());
			billOperate.setOperateType(BillOperateType.PAY.name());
			//仅保存则是待支付状态，支付则是已支付状态
			billOperate.setStatus(BillOperateStatus.INIT.name());
			billOperate.setProcess(BillOperateProcess.UNPAY.name());
			//收费不需要审核，故初始状态是 审核通过
			billOperate.setCheckStatus(CheckStatus.PASS.name());
			
			billOperate.setMemberNoGuid(billOperateDto.getMemberNoGuid());
			billOperate.setMemberNameGuid(billOperateDto.getMemberNameGuid());
//			billOperate.setCheckerNoGuid(billOperateDto.getCheckerNoGuid());
//			billOperate.setCheckerNameGuid(billOperateDto.getCheckerNameGuid());
			billOperate.setApplyTime(new Date());//申请时间是当前
//			billOperate.setCheckTime(billOperateDto.getCheckTime());
			billOperate.setPayType(billOperateDto.getPayType());
			billOperate.setPayTypeName(billOperateDto.getPayTypeName());
			billOperate.setPayRemark(billOperateDto.getPayRemark());
			
			billOperate.setPayTime(billOperateDto.getPayTime());
			//收款人 前端填入
			billOperate.setRecieverNo(billOperateDto.getRecieverNo());
			billOperate.setRecieverName(billOperateDto.getRecieverName());
			//款项前端计算并填入，因为前端可调整价格和折扣，故不根据项目后台计算
			billOperate.setOriginalAmount(billOperateDto.getOriginalAmount());//原价款
			billOperate.setReallyAmount(billOperateDto.getReallyAmount());//应付款
			billOperate.setDiscountNum(billOperateDto.getDiscountNum());//折扣
			billOperate.setDebtAmount(billOperateDto.getDebtAmount());//欠款
			billOperate.setPayAmount(billOperateDto.getPayAmount());//实付款
			
//			billOperate.setUpdateId(billOperateDto.getUpdateId());
//			billOperate.setUpdateDate(new Date());
			billOperate.setCreateId(billOperateDto.getMemberNoGuid());
			billOperate.setCreateDate(new Date());
			billOperate.setRemark(billOperateDto.getRemark());
			billOperate.setRemark1(billOperateDto.getRemark1());
			billOperate.setRemark2(billOperateDto.getRemark2());
			billOperate.setRemark3(billOperateDto.getRemark3());
			billOperate.setRemark4(billOperateDto.getRemark4());
			billOperate.setShopNo(rtBillDto.getShopNo());
			billOperate.setShopName(rtBillDto.getShopName());
			billOperate.setMerchantNo(rtBillDto.getMerchantNo());
			billOperate.setMerchantName(rtBillDto.getMerchantName());
			billOperateDao.insertSelective(billOperate);
			logger.debug("addBillOperate(BillOperateDto) - end - return"); 
			return billOperate;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单交易操作错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_ADD_ERROR,"新增账单交易操作错误！",e);
		}
	}
	
	/**
	 * 
	 * @param rtBillDto
	 * @param billDetailDtos
	 * @return 所有医生的名称
	 * @throws TsfaServiceException
	 */
	private void addBillDetail(Bill rtBillDto,List<BillDetailDto> billDetailDtos) throws TsfaServiceException {
		logger.debug("addBillDetail(AddBillDetail addBillDetail={}) - start", billDetailDtos); 
		try {
			StringBuffer doctorNames = new StringBuffer();
			for (Iterator<BillDetailDto> iterator = billDetailDtos.iterator(); iterator.hasNext();) {
				BillDetailDto billDetailDto = (BillDetailDto) iterator.next();
				BillDetail billDetail = new BillDetail();
				//add数据录入
				billDetail.setCode(GUID.getPreUUID());
				billDetail.setBillCode(rtBillDto.getCode());
				billDetail.setShopNo(rtBillDto.getShopNo());
				billDetail.setShopName(rtBillDto.getShopName());
				billDetail.setMerchantNo(rtBillDto.getMerchantNo());
				billDetail.setMerchantName(rtBillDto.getMerchantName());
				
				billDetail.setProjectCode(billDetailDto.getProjectCode());
				billDetail.setProjectName(billDetailDto.getProjectName());
				billDetail.setProjectUnit(billDetailDto.getProjectUnit());
				billDetail.setUnitPrice(billDetailDto.getUnitPrice());
				billDetail.setItemDisUnitPrice(billDetailDto.getItemDisUnitPrice());
				billDetail.setItemNum(billDetailDto.getItemNum());
				billDetail.setOriginalAmount(billDetailDto.getOriginalAmount());
				billDetail.setItemDiscountAmount(billDetailDto.getItemDiscountAmount());
				billDetail.setDiscountItem(billDetailDto.getDiscountItem());
				billDetail.setRtNum(0);//退款数量
				billDetail.setRtAmount(0L);//退款金额
				billDetail.setDiscountOrderStatus(billDetailDto.getDiscountOrderStatus());
				billDetail.setAdvisoryNo(billDetailDto.getAdvisoryNo());
				billDetail.setAdvisoryName(billDetailDto.getAdvisoryName());
				billDetail.setDoctorNo(billDetailDto.getDoctorNo());
				billDetail.setDoctorName(billDetailDto.getDoctorName());
				billDetail.setAssistantNo(billDetailDto.getAssistantNo());
				billDetail.setAssistantName(billDetailDto.getAssistantName());
				billDetail.setUpdateId(rtBillDto.getUpdateId());
				billDetail.setUpdateDate(new Date());
				billDetail.setCreateId(rtBillDto.getCreateId());
				billDetail.setCreateDate(new Date());
				billDetail.setRemark(billDetailDto.getRemark());
				billDetail.setRemark1(billDetailDto.getRemark1());
				billDetail.setRemark2(billDetailDto.getRemark2());
				billDetail.setRemark3(billDetailDto.getRemark3());
				billDetail.setRemark4(billDetailDto.getRemark4());
				
				billDetail.setPayAmount(0L);//首次支付，付款=0
				billDetail.setReallyAmount(billDetailDto.getReallyAmount());//应收 前端计算录入
				billDetail.setDebtAmount(billDetailDto.getReallyAmount());//欠款 ，初始 欠款=应付款
				
				billDetailDao.insertSelective(billDetail);
				
				if(StringUtils.isNotEmpty(billDetail.getDoctorName())) {
					String key=billDetail.getDoctorName()+",";
					if(doctorNames.indexOf(key)==-1) {
						doctorNames.append(key);
					}
				}
			}
	
			logger.debug("addBillDetail(BillDetailDto) - end - return"); 
			//2.修改 账单的医生 
			String dString=doctorNames.toString();
			if(StringUtils.isNotEmpty(dString)) {
				Bill updateBill = new Bill();
				updateBill.setCode(rtBillDto.getCode());
				updateBill.setRemark1(doctorNames.toString().substring(0, dString.length()-1));
				billDao.updateByPrimaryKeySelective(updateBill);
			}
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单详情信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_DETAIL_ADD_ERROR,"新增账单详情信息错误！",e);
		}
	}
	
	/** 新增账单 项目应收信息
	 */
	private void addFirstPayDetail(Bill rtBillDto,String billOperateCode,List<BillDetailDto> billDetailDtos) throws TsfaServiceException {
		logger.debug("addPayDetail(AddBillDetail addBillDetail={}) - start", billDetailDtos); 
		try {
			int i=0;
			for (Iterator<BillDetailDto> iterator = billDetailDtos.iterator(); iterator.hasNext();) {
				BillDetailDto billDetailDto = (BillDetailDto) iterator.next();
				if (billDetailDto.getPayAmount() == null) {
					throw new TsfaServiceException(ErrorCode.BILL_DETAIL_ADD_ERROR,"项目实收不能为空！");
				}
				if (billDetailDto.getReallyAmount() == null) {
					throw new TsfaServiceException(ErrorCode.BILL_DETAIL_ADD_ERROR,"项目应收不能为空！");
				}
				if (billDetailDto.getDebtAmount() == null) {
					throw new TsfaServiceException(ErrorCode.BILL_DETAIL_ADD_ERROR,"项目欠款不能为空！");
				}
				PayDetail billDetail = new PayDetail();
				//add数据录入
				billDetail.setCode(GUID.getPreUUID());
				billDetail.setBillCode(rtBillDto.getCode());
				billDetail.setOperateCode(billOperateCode);
				billDetail.setProjectCode(billDetailDto.getProjectCode());
				billDetail.setProjectName(billDetailDto.getProjectName());
		 
				billDetail.setPayAmount(billDetailDto.getPayAmount());
				billDetail.setReallyAmount(billDetailDto.getReallyAmount());
				billDetail.setDebtAmount(billDetailDto.getDebtAmount());
				billDetail.setIndexNo(i++);
				
				billDetail.setOriginalPayAmount(0L);//首次支付，付款=0
				billDetail.setOriginalReallyAmount(billDetailDto.getReallyAmount());//应收 前端计算录入
				billDetail.setOriginalDebtAmount(billDetailDto.getReallyAmount());//欠款 ，初始 欠款=应付款
				
				payDetailDao.insertSelective(billDetail);
			}
			logger.debug("addPayDetail(BillDetailDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单详情信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_DETAIL_ADD_ERROR,"新增账单详情信息错误！",e);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询账单信息
	 *
	 * @param findBillPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<BillDto>  findBills(FindBillPage findBillPage)throws TsfaServiceException{
		AssertUtils.notNull(findBillPage);
		List<BillDto> returnList=null;
		try {
			returnList = billDao.findBills(findBillPage);
		} catch (Exception e) {
			logger.error("查找账单信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.BILL_NOT_EXIST_ERROR,"账单信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateBill(
			BillDto billDto)
			throws TsfaServiceException {
		logger.debug("updateBill(BillDto billDto={}) - start", billDto); //$NON-NLS-1$

		AssertUtils.notNull(billDto);
		AssertUtils.notNullAndEmpty(billDto.getCode(),"Code不能为空");
		try {
			Bill bill = new Bill();
			//update数据录入
			bill.setCode(billDto.getCode());
			bill.setPatientNo(billDto.getPatientNo());
			bill.setPatientName(billDto.getPatientName());
			bill.setMedicalNo(billDto.getMedicalNo());
			bill.setShopNo(billDto.getShopNo());
			bill.setShopName(billDto.getShopName());
			bill.setMerchantNo(billDto.getMerchantNo());
			bill.setMerchantName(billDto.getMerchantName());
			bill.setBillType(billDto.getBillType());
			bill.setOriginalAmount(billDto.getOriginalAmount());
			bill.setReallyAmount(billDto.getReallyAmount());
			bill.setDiscountNum(billDto.getDiscountNum());
			bill.setPayAmount(billDto.getPayAmount());
			bill.setDebtAmount(billDto.getDebtAmount());
			bill.setRtAmount(billDto.getRtAmount());
			bill.setPayStatus(billDto.getPayStatus());
			bill.setRtStatus(billDto.getRtStatus());
			bill.setStatus(billDto.getStatus());
			bill.setClinicTime(billDto.getClinicTime());
			bill.setPayTime(billDto.getPayTime());
			bill.setUpdateId(billDto.getUpdateId());
			bill.setUpdateDate(new Date());
//			bill.setCreateId(billDto.getCreateId());
//			bill.setCreateName(billDto.getCreateName());
//			bill.setCreateDate(billDto.getCreateDate());
			bill.setRemark(billDto.getRemark());
			bill.setRemark1(billDto.getRemark1());
			bill.setRemark2(billDto.getRemark2());
			bill.setRemark3(billDto.getRemark3());
			bill.setRemark4(billDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(billDao.updateByPrimaryKeySelective(bill));
			logger.debug("updateBill(BillDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("账单信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_UPDATE_ERROR,"账单信息更新信息错误！",e);
		}
	}

	

	@Override
	public BillDto findBill(
			BillDto billDto) throws TsfaServiceException {
		logger.debug("findBill(FindBill findBill={}) - start", billDto); //$NON-NLS-1$

		AssertUtils.notNull(billDto);
		AssertUtils.notAllNull(billDto.getCode(),"Code不能为空");
		try {
			Bill bill = billDao.selectByPrimaryKey(billDto.getCode());
			if(bill == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.BILL_NOT_EXIST_ERROR,"账单信息不存在");
			}
			BillDto findBillReturn = new BillDto();
			//find数据录入
			findBillReturn.setCode(bill.getCode());
			findBillReturn.setBillNo(bill.getBillNo());
			findBillReturn.setPatientNo(bill.getPatientNo());
			findBillReturn.setPatientName(bill.getPatientName());
			findBillReturn.setMedicalNo(bill.getMedicalNo());
			findBillReturn.setShopNo(bill.getShopNo());
			findBillReturn.setShopName(bill.getShopName());
			findBillReturn.setMerchantNo(bill.getMerchantNo());
			findBillReturn.setMerchantName(bill.getMerchantName());
			findBillReturn.setBillType(bill.getBillType());
			findBillReturn.setOriginalAmount(bill.getOriginalAmount());
			findBillReturn.setReallyAmount(bill.getReallyAmount());
			findBillReturn.setDiscountNum(bill.getDiscountNum());
			findBillReturn.setPayAmount(bill.getPayAmount());
			findBillReturn.setDebtAmount(bill.getDebtAmount());
			findBillReturn.setRtAmount(bill.getRtAmount());
			findBillReturn.setPayStatus(bill.getPayStatus());
			findBillReturn.setRtStatus(bill.getRtStatus());
			findBillReturn.setStatus(bill.getStatus());
			findBillReturn.setClinicTime(bill.getClinicTime());
			findBillReturn.setPayTime(bill.getPayTime());
			findBillReturn.setUpdateId(bill.getUpdateId());
			findBillReturn.setUpdateDate(bill.getUpdateDate());
			findBillReturn.setCreateId(bill.getCreateId());
			findBillReturn.setCreateName(bill.getCreateName());
			findBillReturn.setCreateDate(bill.getCreateDate());
			findBillReturn.setRemark(bill.getRemark());
			findBillReturn.setRemark1(bill.getRemark1());
			findBillReturn.setRemark2(bill.getRemark2());
			findBillReturn.setRemark3(bill.getRemark3());
			findBillReturn.setRemark4(bill.getRemark4());
			
			logger.debug("findBill(BillDto) - end - return value={}", findBillReturn); //$NON-NLS-1$
			return findBillReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找账单信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_FIND_ERROR,"查找账单信息信息错误！",e);
		}


	}

	
	@Override
	public Page<BillDto> findBillPage(
			FindBillPage findBillPage)
			throws TsfaServiceException {
		logger.debug("findBillPage(FindBillPage findBillPage={}) - start", findBillPage); //$NON-NLS-1$

		AssertUtils.notNull(findBillPage);
		List<BillDto> returnList=null;
		int count = 0;
		try {
			returnList = billDao.findBillPage(findBillPage);
			count = billDao.findBillPageCount(findBillPage);
		}  catch (Exception e) {
			logger.error("账单信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.BILL_FIND_PAGE_ERROR,"账单信息不存在错误.！",e);
		}
		Page<BillDto> returnPage = new Page<BillDto>(returnList, count, findBillPage);

		logger.debug("findBillPage(FindBillPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}

	@Override
	public void updateBillInfo(BillDto billDto) throws TsfaServiceException {
		//1.检测：仅当账单=待支付才可编辑全部
		Bill findBill = billDao.selectByPrimaryKey(billDto.getCode());
		
		if(!CommonStatus.NORMAL.name().equals(findBill.getStatus())) {
			logger.error("账单修改错误！账单已作废，不可编辑。BillDto:{}",billDto);
			throw new TsfaServiceException(ErrorCode.BILL_UPDATE_ERROR,"该账单已作废！");
		}
		
		if(!PayStatus.UNPAY.name().equals(findBill.getPayStatus())) {
			logger.error("账单修改错误！账单已支付，不可编辑。BillDto:{}",billDto);
			throw new TsfaServiceException(ErrorCode.BILL_UPDATE_ERROR,"该账单已支付不可编辑！");
		}
		//2.修改
		Bill bill = new Bill();
		//update数据录入
		bill.setCode(billDto.getCode());
//		bill.setPatientNo(billDto.getPatientNo());
//		bill.setPatientName(billDto.getPatientName());
//		bill.setMedicalNo(billDto.getMedicalNo());
//		bill.setShopNo(billDto.getShopNo());
//		bill.setShopName(billDto.getShopName());
//		bill.setMerchantNo(billDto.getMerchantNo());
//		bill.setMerchantName(billDto.getMerchantName());
//		bill.setBillType(billDto.getBillType());
//		bill.setOriginalAmount(billDto.getOriginalAmount());
//		bill.setReallyAmount(billDto.getReallyAmount());
//		bill.setDiscountNum(billDto.getDiscountNum());
//		bill.setPayAmount(billDto.getPayAmount());
//		bill.setDebtAmount(billDto.getDebtAmount());
//		bill.setRtAmount(billDto.getRtAmount());
		bill.setOriginalAmount(billDto.getBillOperate().getOriginalAmount());//原价款
		bill.setReallyAmount(billDto.getBillOperate().getReallyAmount());//应付款
		bill.setDiscountNum(billDto.getBillOperate().getDiscountNum());//折扣
//		bill.setPayAmount(0L);//已付款
		bill.setDebtAmount(billDto.getBillOperate().getReallyAmount());//欠款 ，初始 欠款=应付款
//		bill.setRtAmount(0L);//已退款
//		bill.setPayStatus(billDto.getPayStatus());
//		bill.setRtStatus(billDto.getRtStatus());
//		bill.setStatus(billDto.getStatus());
		bill.setClinicTime(billDto.getClinicTime());
//		bill.setPayTime(billDto.getPayTime());
		bill.setUpdateId(billDto.getUpdateId());
		bill.setUpdateDate(new Date());
//		bill.setCreateId(billDto.getCreateId());
//		bill.setCreateName(billDto.getCreateName());
//		bill.setCreateDate(billDto.getCreateDate());
		bill.setRemark(billDto.getBillOperate().getRemark());
		bill.setRemark1(billDto.getRemark1());
		bill.setRemark2(billDto.getRemark2());
		bill.setRemark3(billDto.getRemark3());
		bill.setRemark4(billDto.getRemark4());
		//1.修改账单信息
		billDao.updateByPrimaryKeySelective(bill);
		//2.修改账单详细信息，入参bill=findBill 需要使用原账单的一些商户和患者信息
		findBill.setUpdateId(bill.getUpdateId());
		updateBillDetail(findBill, billDto.getDetails());
		//3.修改账单处理信息（待收费信息）
		billDto.getBillOperate().setUpdateId(bill.getUpdateId());
		updatePayBillOperate(billDto.getBillOperate());
		//3.2添加账单项目实收（待收费项目信息）
		updateFirstPayDetail(findBill,billDto.getBillOperate().getCode(),billDto.getDetails());//2019年7月9日 前端未开发注释掉
		//4.直接收费则添加支付记录，计算账单金额信息
		if(OptType.PAY.name().equals(billDto.getBillOperate().getOptType())) {
			BillOperate findbillOperate = billOperateDao.selectByPrimaryKey(billDto.getBillOperate().getCode());
			//入参bill=findBill，待支付的账单，需要使用最新的账单的信息进行快照,findbillOperate需要使用最新的
			findBill = billDao.selectByPrimaryKey(billDto.getCode());
			payment(findBill,findbillOperate,null);
		}
	} 

	private void updateBillDetail(Bill rtBillDto,List<BillDetailDto> billDetailDtos) throws TsfaServiceException {
		logger.debug("updateBillDetail(BillDetailDto addBillDetail={}) - start", billDetailDtos); 
		//1.先删该账单的所有详细
		BillDetail record=new BillDetail();
		record.setBillCode(rtBillDto.getCode());
		billDetailDao.deleteBillDetail(record);
		//2.再增
		addBillDetail(rtBillDto, billDetailDtos);	
	}
	/** 待处理支付 项目应收*/
	private void updateFirstPayDetail(Bill rtBillDto,String billOperateCode,List<BillDetailDto> billDetailDtos) throws TsfaServiceException {
		logger.debug("updateBillDetail(BillDetailDto addBillDetail={}) - start", billDetailDtos); 
		//1.先删该账单的所有详细
		PayDetail record=new PayDetail();
		record.setOperateCode(billOperateCode);
		payDetailDao.deleteDetail(record);
		//2.再增
		addFirstPayDetail(rtBillDto, billOperateCode, billDetailDtos);	
	}
	
	private BillOperate updatePayBillOperate(BillOperateDto billOperateDto) throws TsfaServiceException {
		logger.debug("updatePayBillOperate(BillOperateDto ={}) - start", billOperateDto); 

		AssertUtils.notNull(billOperateDto);
		AssertUtils.notNull(billOperateDto.getCode(),"交易编号不能为空");
		try {
			BillOperate billOperate = new BillOperate();
			//add数据录入
			billOperate.setCode(billOperateDto.getCode());
//			billOperate.setOperateType(BillOperateType.PAY.name());
			//仅保存则是待支付状态，支付则是已支付状态
//			billOperate.setStatus(BillOperateStatus.INIT.name());
//			billOperate.setProcess(BillOperateProcess.UNPAY.name());
			//收费不需要审核，故初始状态是 审核通过
//			billOperate.setCheckStatus(CheckStatus.PASS.name());
			
//			billOperate.setMemberNoGuid(billOperateDto.getMemberNoGuid());
//			billOperate.setMemberNameGuid(billOperateDto.getMemberNameGuid());
//			billOperate.setCheckerNoGuid(billOperateDto.getCheckerNoGuid());
//			billOperate.setCheckerNameGuid(billOperateDto.getCheckerNameGuid());
//			billOperate.setApplyTime(new Date());//申请时间是当前
//			billOperate.setCheckTime(billOperateDto.getCheckTime());
			billOperate.setPayType(billOperateDto.getPayType());
			billOperate.setPayTypeName(billOperateDto.getPayTypeName());
			billOperate.setPayRemark(billOperateDto.getPayRemark());
			
			billOperate.setPayTime(billOperateDto.getPayTime());
			//收款人 前端填入
			billOperate.setRecieverNo(billOperateDto.getRecieverNo());
			billOperate.setRecieverName(billOperateDto.getRecieverName());
			//款项前端计算并填入，因为前端可调整价格和折扣，故不根据项目后台计算
			billOperate.setOriginalAmount(billOperateDto.getOriginalAmount());//原价款
			billOperate.setReallyAmount(billOperateDto.getReallyAmount());//应付款
			billOperate.setDiscountNum(billOperateDto.getDiscountNum());//折扣
			billOperate.setDebtAmount(billOperateDto.getDebtAmount());//欠款
			billOperate.setPayAmount(billOperateDto.getPayAmount());//实付款
			
			billOperate.setUpdateId(billOperateDto.getUpdateId());
			billOperate.setUpdateDate(new Date());
//			billOperate.setCreateId(billOperateDto.getMemberNoGuid());
//			billOperate.setCreateDate(new Date());
			billOperate.setRemark(billOperateDto.getRemark());
			billOperate.setRemark1(billOperateDto.getRemark1());
			billOperate.setRemark2(billOperateDto.getRemark2());
			billOperate.setRemark3(billOperateDto.getRemark3());
			billOperate.setRemark4(billOperateDto.getRemark4());
			billOperateDao.updateByPrimaryKeySelective(billOperate);
			logger.debug("addBillOperate(BillOperateDto) - end - return"); 
			return billOperate;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单交易操作错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_ADD_ERROR,"新增账单交易操作错误！",e);
		}
	}

	@Override
	public BillOperateDto debtRepay(BillOperateDto billOperateDto) throws TsfaServiceException {

		logger.debug("addBillOperate(AddBillOperate addBillOperate={}) - start", billOperateDto); 
		AssertUtils.notNull(billOperateDto);
		AssertUtils.notNull(billOperateDto.getBillCode(),"账单code不能为空");
		//0.先检测 ,是否有待处理账单
		checkBillHasTrade(billOperateDto.getBillCode());
		
		try {
			Bill findBill = billDao.selectByPrimaryKey(billOperateDto.getBillCode());
			if(!CommonStatus.NORMAL.name().equals(findBill.getStatus())) {
				logger.error("账单收欠费错误！账单已作废，不可操作。BillDto:{}",findBill);
				throw new TsfaServiceException(ErrorCode.BILL_UPDATE_ERROR,"该账单已作废！");
			}
			BillOperate billOperate = new BillOperate();
			//add数据录入
			billOperate.setCode(GUID.getPreUUID());
			billOperate.setBillCode(billOperateDto.getBillCode());
			billOperate.setOperateType(BillOperateType.DEBT.name());
			//仅保存则是待支付状态，支付则是已支付状态
			billOperate.setStatus(BillOperateStatus.INIT.name());
			billOperate.setProcess(BillOperateProcess.UNPAY.name());
			//收费不需要审核，故初始状态是 审核通过
			billOperate.setCheckStatus(CheckStatus.PASS.name());
			
			billOperate.setMemberNoGuid(billOperateDto.getMemberNoGuid());
			billOperate.setMemberNameGuid(billOperateDto.getMemberNameGuid());
//			billOperate.setCheckerNoGuid(billOperateDto.getCheckerNoGuid());
//			billOperate.setCheckerNameGuid(billOperateDto.getCheckerNameGuid());
			billOperate.setApplyTime(new Date());//申请时间是当前
//			billOperate.setCheckTime(billOperateDto.getCheckTime());
			billOperate.setPayType(billOperateDto.getPayType());
			billOperate.setPayTypeName(billOperateDto.getPayTypeName());
			billOperate.setPayRemark(billOperateDto.getPayRemark());
			
			billOperate.setPayTime(billOperateDto.getPayTime());
			//收款人 前端填入
			billOperate.setRecieverNo(billOperateDto.getRecieverNo());
			billOperate.setRecieverName(billOperateDto.getRecieverName());
			//款项前端计算并填入，因为前端可调整价格和折扣，故不根据项目后台计算
			billOperate.setOriginalAmount(billOperateDto.getOriginalAmount());//原价款
			billOperate.setReallyAmount(billOperateDto.getReallyAmount());//应付款
			billOperate.setDiscountNum(billOperateDto.getDiscountNum());//折扣
			billOperate.setDebtAmount(billOperateDto.getDebtAmount());//欠款
			billOperate.setPayAmount(billOperateDto.getPayAmount());//实付款
			
//			billOperate.setUpdateId(billOperateDto.getUpdateId());
//			billOperate.setUpdateDate(new Date());
			billOperate.setCreateId(billOperateDto.getMemberNoGuid());
			billOperate.setCreateDate(new Date());
			billOperate.setRemark(billOperateDto.getRemark());
			billOperate.setRemark1(billOperateDto.getRemark1());
			billOperate.setRemark2(billOperateDto.getRemark2());
			billOperate.setRemark3(billOperateDto.getRemark3());
			billOperate.setRemark4(billOperateDto.getRemark4());
			
			billOperate.setShopNo(findBill.getShopNo());
			billOperate.setShopName(findBill.getShopName());
			billOperate.setMerchantNo(findBill.getMerchantNo());
			billOperate.setMerchantName(findBill.getMerchantName());
			//1.新增 账单交易信息
			billOperateDao.insertSelective(billOperate);
			logger.debug("addBillOperate(BillOperateDto) - end - return"); 
			//1.1  收费项目 2019年7月9日 前端未开发注释掉
			addDebtPayDetail(billOperateDto.getBillCode(), billOperate.getCode(), billOperateDto.getPayDetails());
			//2.直接收费则添加支付记录，计算账单金额信息
			if(OptType.DEBT.name().equals(billOperateDto.getOptType())) {
				payment(findBill,billOperate,null);
			}
			BillOperateDto rtBillOperateDto=new BillOperateDto();
			rtBillOperateDto.setCode(billOperate.getCode());
			rtBillOperateDto.setBillCode(billOperate.getBillCode());
			return rtBillOperateDto;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单交易操作错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_ADD_ERROR,"新增账单交易操作错误！",e);
		}
	
	}
	
	/**   项目应收信息
	 */
	private void addDebtPayDetail(String billCode,String billOperateCode,List<PayDetailDto> billDetailDtos) throws TsfaServiceException {
		logger.debug("addPayDetail(AddBillDetail addBillDetail={}) - start", billDetailDtos); 
		if (null == billDetailDtos) {
			return ;
		}
		try {
			int i=0;
			for (Iterator<PayDetailDto> iterator = billDetailDtos.iterator(); iterator.hasNext();) {
				PayDetailDto billDetailDto = (PayDetailDto) iterator.next();
				if (billDetailDto.getPayAmount() == null) {
					throw new TsfaServiceException(ErrorCode.BILL_DETAIL_ADD_ERROR,"项目实收不能为空！");
				}
				if (billDetailDto.getReallyAmount() == null) {
					throw new TsfaServiceException(ErrorCode.BILL_DETAIL_ADD_ERROR,"项目应收不能为空！");
				}
				if (billDetailDto.getDebtAmount() == null) {
					throw new TsfaServiceException(ErrorCode.BILL_DETAIL_ADD_ERROR,"项目欠款不能为空！");
				}
				PayDetail billDetail = new PayDetail();
				//add数据录入
				billDetail.setCode(GUID.getPreUUID());
				billDetail.setBillCode(billCode);
				billDetail.setOperateCode(billOperateCode);
				billDetail.setProjectCode(billDetailDto.getProjectCode());
				billDetail.setProjectName(billDetailDto.getProjectName());
		 
				billDetail.setPayAmount(billDetailDto.getPayAmount());
				billDetail.setReallyAmount(billDetailDto.getReallyAmount());
				billDetail.setDebtAmount(billDetailDto.getDebtAmount());
				billDetail.setIndexNo(i++);
				payDetailDao.insertSelective(billDetail);
			}
			logger.debug("addPayDetail(BillDetailDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单详情信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_DETAIL_ADD_ERROR,"新增账单详情信息错误！",e);
		}
	}
	
	/** 待处理支付 项目应收*/
	private void updateDebtPayDetail(String billCode,String billOperateCode,List<PayDetailDto> billDetailDtos) throws TsfaServiceException {
		logger.debug("updateBillDetail(BillDetailDto addBillDetail={}) - start", billDetailDtos); 
		//1.先删该账单的所有详细
		PayDetail record=new PayDetail();
		record.setOperateCode(billOperateCode);
		payDetailDao.deleteDetail(record);
		//2.再增
		addDebtPayDetail(billCode, billOperateCode, billDetailDtos);	
	}
	
	
	private void checkBillHasTrade(String billCode) {
		AssertUtils.notNull(billCode,"账单code不能为空");
		FindBillOperatePage findBillOperatePage = new FindBillOperatePage();
		BillOperateDto param = new BillOperateDto();
		param.setStatus(BillOperateStatus.INIT.name());//待处理
		param.setBillCode(billCode);
		findBillOperatePage.setParam(param);
		int c = billOperateDao.findBillOperatePageCount(findBillOperatePage);
		if(c>0) {
			logger.error("该账单有待处理的交易！billCode{}",billCode);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_ADD_ERROR,"该账单有待处理的交易！");
		}
	}

	@Override
	public BillOperateDto debtRepayEdit(BillOperateDto billOperateDto) throws TsfaServiceException {
		AssertUtils.notNull(billOperateDto);
		AssertUtils.notNull(billOperateDto.getCode(),"交易code不能为空");
		AssertUtils.notNull(billOperateDto.getBillCode(),"账单code不能为空");//可修改账单故入参该值
		//0.1.检测，待处理状态的交易才可操作
		BillOperate findbillOperate = billOperateDao.selectByPrimaryKey(billOperateDto.getCode());
		if(!BillOperateType.DEBT.name().equals(findbillOperate.getOperateType())) {
			logger.error("账单交易操作错误！非收欠款账单。BillOperate:{}",findbillOperate);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_UPDATE_ERROR,"该账单不是收欠款！");
		}
		if(!BillOperateStatus.INIT.name().equals(findbillOperate.getStatus())) {
			logger.error("账单交易操作错误！非未处理状态。BillOperate:{}",findbillOperate);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_UPDATE_ERROR,"该账单已处理！");
		}
		//0.2.检测,修改了收款的账单 ,检查该账单是否有待处理账单（旧的则不需要检测，在新增时检测过）
		if(!billOperateDto.getBillCode().equals(findbillOperate.getBillCode())) {
			checkBillHasTrade(billOperateDto.getBillCode());
		}
		
		try {
			BillOperate billOperate = new BillOperate();
			//add数据录入
			billOperate.setCode(billOperateDto.getCode());
			billOperate.setBillCode(billOperateDto.getBillCode());
//			billOperate.setOperateType(BillOperateType.DEBT.name());
//			//仅保存则是待支付状态，支付则是已支付状态
//			billOperate.setStatus(BillOperateStatus.INIT.name());
//			billOperate.setProcess(BillOperateProcess.UNPAY.name());
//			//收费不需要审核，故初始状态是 审核通过
//			billOperate.setCheckStatus(CheckStatus.PASS.name());
			
			billOperate.setMemberNoGuid(billOperateDto.getMemberNoGuid());
			billOperate.setMemberNameGuid(billOperateDto.getMemberNameGuid());
//			billOperate.setCheckerNoGuid(billOperateDto.getCheckerNoGuid());
//			billOperate.setCheckerNameGuid(billOperateDto.getCheckerNameGuid());
//			billOperate.setApplyTime(new Date());//申请时间是当前
//			billOperate.setCheckTime(billOperateDto.getCheckTime());
			billOperate.setPayType(billOperateDto.getPayType());
			billOperate.setPayTypeName(billOperateDto.getPayTypeName());
			billOperate.setPayRemark(billOperateDto.getPayRemark());
			
			billOperate.setPayTime(billOperateDto.getPayTime());
			//收款人 前端填入
			billOperate.setRecieverNo(billOperateDto.getRecieverNo());
			billOperate.setRecieverName(billOperateDto.getRecieverName());
			//款项前端计算并填入，因为前端可调整价格和折扣，故不根据项目后台计算
			billOperate.setOriginalAmount(billOperateDto.getOriginalAmount());//原价款
			billOperate.setReallyAmount(billOperateDto.getReallyAmount());//应付款
			billOperate.setDiscountNum(billOperateDto.getDiscountNum());//折扣
			billOperate.setDebtAmount(billOperateDto.getDebtAmount());//欠款
			billOperate.setPayAmount(billOperateDto.getPayAmount());//实付款
			
			billOperate.setUpdateId(billOperateDto.getMemberNoGuid());
			billOperate.setUpdateDate(new Date());
//			billOperate.setCreateId(billOperateDto.getMemberNoGuid());
//			billOperate.setCreateDate(new Date());
			billOperate.setRemark(billOperateDto.getRemark());
			billOperate.setRemark1(billOperateDto.getRemark1());
			billOperate.setRemark2(billOperateDto.getRemark2());
			billOperate.setRemark3(billOperateDto.getRemark3());
			billOperate.setRemark4(billOperateDto.getRemark4());
			//1.新增 账单交易信息
			billOperateDao.updateByPrimaryKeySelective(billOperate);
			//1.2  收费项目 2019年7月9日 前端未开发注释掉
			updateDebtPayDetail(billOperateDto.getBillCode(), billOperate.getCode(), billOperateDto.getPayDetails());
			
			logger.debug("addBillOperate(BillOperateDto) - end - return"); 
			
			//2.直接收费则添加支付记录，计算账单金额信息
			if(OptType.DEBT.name().equals(billOperateDto.getOptType())) {
				Bill findBill = billDao.selectByPrimaryKey(billOperateDto.getBillCode());
				findbillOperate = billOperateDao.selectByPrimaryKey(billOperateDto.getCode());
				//入参bill=findBill，待支付的账单，需要使用最新的账单的信息进行快照,findbillOperate需要使用最新的
				payment(findBill,findbillOperate,null);
			}
			BillOperateDto rtBillOperateDto=new BillOperateDto();
			rtBillOperateDto.setCode(billOperate.getCode());
			rtBillOperateDto.setBillCode(billOperate.getBillCode());
			return rtBillOperateDto;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单交易操作错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_ADD_ERROR,"新增账单交易操作错误！",e);
		}
	}

	@Override
	public BillRefundDto billRefundApply(BillRefundDto refundDto) throws TsfaServiceException {
		//1.检测该账单是否有交易
		checkBillHasTrade(refundDto.getBillCode());
		//2.检测退款额度是否超实收
		Bill findBill = billDao.selectByPrimaryKey(refundDto.getBillCode());
		if(!CommonStatus.NORMAL.name().equals(findBill.getStatus())) {
			logger.error("账单退费错误！账单已作废，不可退费。BillDto:{}",findBill);
			throw new TsfaServiceException(ErrorCode.BILL_UPDATE_ERROR,"该账单已作废！");
		}
		if((findBill.getRtAmount()+refundDto.getRtAmount())>findBill.getPayAmount()) {
			logger.error("该账单退款额度超过可退款额度！。BillRefundDto:{}，Bill{}",refundDto,findBill);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_UPDATE_ERROR,"该账单退款额度超过可退款额度！");
		}
		//3.0 预设值
		refundDto.setPatientNo(findBill.getPatientNo());
		refundDto.setPatientName(findBill.getPatientName());
		refundDto.setMedicalNo(findBill.getMedicalNo());
		refundDto.setShopNo(findBill.getShopNo());
		refundDto.setShopName(findBill.getShopName());
		refundDto.setMerchantNo(findBill.getMerchantNo());
		refundDto.setMerchantName(findBill.getMerchantName());
		//账单应收
		refundDto.setReallyAmount(findBill.getReallyAmount());
		//账单可退=已收-已退
		refundDto.setBillPayAmount(findBill.getPayAmount()-findBill.getRtAmount());
		//3.新增退款申请（直接进入待审核状态）
		BillOperate billOperate = addRefundBillOperate(refundDto);
		//4.新增退款账单
		refundDto.setOperateCode(billOperate.getCode());//预设交易编号
		BillRefund billRefund = addBillRefund(refundDto);
		//4.1新增退款项目
		addBillRefundDetail(billRefund, refundDto.getDetails());
		
		BillRefundDto rtBillRefundDto=new BillRefundDto();
		rtBillRefundDto.setCode(billRefund.getCode());
		rtBillRefundDto.setBillCode(billRefund.getBillCode());
		return rtBillRefundDto;
	}
	
	private BillRefund addBillRefund(BillRefundDto billRefundDto) throws TsfaServiceException {
		logger.debug("addBillRefund(AddBillRefund addBillRefund={}) - start", billRefundDto); 

		AssertUtils.notNull(billRefundDto);
		try {
			BillRefund billRefund = new BillRefund();
			//add数据录入
			billRefund.setCode(GUID.getPreUUID());
			billRefund.setBillCode(billRefundDto.getBillCode());
			billRefund.setOperateCode(billRefundDto.getOperateCode());
			billRefund.setShopNo(billRefundDto.getShopNo());
			billRefund.setShopName(billRefundDto.getShopName());
			billRefund.setMerchantNo(billRefundDto.getMerchantNo());
			billRefund.setMerchantName(billRefundDto.getMerchantName());
			billRefund.setPatientNo(billRefundDto.getPatientNo());
			billRefund.setPatientName(billRefundDto.getPatientName());
			billRefund.setMedicalNo(billRefundDto.getMedicalNo());
			billRefund.setPayType(billRefundDto.getPayType());
			billRefund.setPayTypeName(billRefundDto.getPayTypeName());
			billRefund.setRtAmount(billRefundDto.getRtAmount());
			billRefund.setRefundGdNo(billRefundDto.getRefundGdNo());
			billRefund.setRefundGdName(billRefundDto.getRefundGdName());
			billRefund.setRefundTime(billRefundDto.getRefundTime());
			billRefund.setRefundStatus(RefundStatus.UNCHECK.name());//待审核  初始
			billRefund.setStatus(CommonStatus.NORMAL.name());
			billRefund.setRefundType(billRefundDto.getRefundType());
//			billRefund.setUpdateId(billRefundDto.getUpdateId());
//			billRefund.setUpdateDate(new Date());
			billRefund.setCreateId(billRefundDto.getMemberNoGuid());
			billRefund.setCreateDate(new Date());
			billRefund.setRemark(billRefundDto.getRemark());
			billRefund.setRemark1(billRefundDto.getRemark1());
			billRefund.setRemark2(billRefundDto.getRemark2());
			billRefund.setRemark3(billRefundDto.getRemark3());
			billRefund.setRemark4(billRefundDto.getRemark4());
			billRefund.setMemberNoGuid(billRefundDto.getMemberNoGuid());
			billRefund.setMemberNameGuid(billRefundDto.getMemberNameGuid());
			
			billRefundDao.insertSelective(billRefund);
			logger.debug("addBillRefund(BillRefundDto) - end - return"); 
			return billRefund;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单退款信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_ADD_ERROR,"新增账单退款信息信息错误！",e);
		}
	}
		
	
	private void addBillRefundDetail(BillRefund billRefund, List<BillRefundDetailDto> details)
			throws TsfaServiceException {
		logger.debug("addBillRefundDetail(BillRefundDetailDto details={}) - start", details); 
		try {
			for (Iterator<BillRefundDetailDto> iterator = details.iterator(); iterator.hasNext();) {
				BillRefundDetailDto billRefundDetailDto = iterator.next();
				
				AssertUtils.notNull(billRefundDetailDto.getProjectCode(),"退款项目编号不能为空");
				AssertUtils.notNull(billRefundDetailDto.getProjectName(),"退款项目名称不能为空");
//				AssertUtils.notNull(billRefundDetailDto.getItemNum(),"退款项目数量不能为空");
				AssertUtils.notNull(billRefundDetailDto.getReturnAmount(),"退款项目金额不能为空");
				
				BillRefundDetail billRefundDetail = new BillRefundDetail();
				//add数据录入
				billRefundDetail.setCode(GUID.getPreUUID());
				billRefundDetail.setBillCode(billRefund.getBillCode());
				billRefundDetail.setRefundCode(billRefund.getCode());
				billRefundDetail.setOperateCode(billRefund.getOperateCode());
				billRefundDetail.setShopNo(billRefund.getShopNo());
				billRefundDetail.setShopName(billRefund.getShopName());
				billRefundDetail.setMerchantNo(billRefund.getMerchantNo());
				billRefundDetail.setMerchantName(billRefund.getMerchantName());
				billRefundDetail.setProjectCode(billRefundDetailDto.getProjectCode());
				billRefundDetail.setProjectName(billRefundDetailDto.getProjectName());
				billRefundDetail.setItemNum(billRefundDetailDto.getReturnNum());
				billRefundDetail.setReturnAmount(billRefundDetailDto.getReturnAmount());
//				billRefundDetail.setUpdateId(billRefundDetailDto.getUpdateId());
//				billRefundDetail.setUpdateDate(new Date());
				billRefundDetail.setCreateId(billRefund.getCreateId());
				billRefundDetail.setCreateDate(new Date());
				billRefundDetail.setRemark(billRefundDetailDto.getRemark());
				billRefundDetail.setRemark1(billRefundDetailDto.getRemark1());
				billRefundDetail.setRemark2(billRefundDetailDto.getRemark2());
				billRefundDetail.setRemark3(billRefundDetailDto.getRemark3());
				billRefundDetail.setRemark4(billRefundDetailDto.getRemark4());
				billRefundDetailDao.insertSelective(billRefundDetail);
			}
			logger.debug("addBillRefundDetail(BillRefund) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单详情信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_DETAIL_ADD_ERROR,"新增账单详情信息错误！",e);
		}
	}
	
	
	private BillOperate addRefundBillOperate(BillRefundDto billRefundDto) throws TsfaServiceException {
		logger.debug("addRefundBillOperate(BillRefund billRefund={}) - start", billRefundDto); 

		AssertUtils.notNull(billRefundDto);
		try {
			BillOperate billOperate = new BillOperate();
			//add数据录入
			billOperate.setCode(GUID.getPreUUID());
			billOperate.setBillCode(billRefundDto.getBillCode());
			billOperate.setOperateType(BillOperateType.REFUND.name());
			billOperate.setStatus(BillOperateStatus.INIT.name());
			billOperate.setProcess(BillOperateProcess.UNCHECK.name());//待审核
			billOperate.setCheckStatus(CheckStatus.UNCHECK.name());//待审核
			
			billOperate.setMemberNoGuid(billRefundDto.getMemberNoGuid());
			billOperate.setMemberNameGuid(billRefundDto.getMemberNameGuid());
//			billOperate.setCheckerNoGuid(billOperateDto.getCheckerNoGuid());
//			billOperate.setCheckerNameGuid(billOperateDto.getCheckerNameGuid());
			billOperate.setApplyTime(new Date());//申请时间是当前
//			billOperate.setCheckTime(billOperateDto.getCheckTime());
			billOperate.setPayType(billRefundDto.getPayType());
			billOperate.setPayTypeName(billRefundDto.getPayTypeName());
			billOperate.setPayRemark(billRefundDto.getPayRemark());
			
			billOperate.setPayTime(billRefundDto.getRefundTime());
			//收款人 前端填入
			billOperate.setRecieverNo(billRefundDto.getRefundGdNo());
			billOperate.setRecieverName(billRefundDto.getRefundGdName());
			//款项前端计算并填入，因为前端可调整价格和折扣，故不根据项目后台计算
//			billOperate.setOriginalAmount(billRefund.getOriginalAmount());//原价款
			billOperate.setReallyAmount(billRefundDto.getReallyAmount());//账单应付款
//			billOperate.setDiscountNum(billOperateDto.getDiscountNum());//折扣
//			billOperate.setDebtAmount(billOperateDto.getDebtAmount());//欠款
			billOperate.setPayAmount(billRefundDto.getRtAmount());//实付款（实退款）
			billOperate.setBillPayAmount(billRefundDto.getBillPayAmount());//账单可退款
//			billOperate.setUpdateId(billOperateDto.getUpdateId());
//			billOperate.setUpdateDate(new Date());
			billOperate.setCreateId(billRefundDto.getMemberNoGuid());
			billOperate.setCreateDate(new Date());
			billOperate.setRemark(billRefundDto.getRemark());
			billOperate.setRemark1(billRefundDto.getRemark1());
			billOperate.setRemark2(billRefundDto.getRemark2());
			billOperate.setRemark3(billRefundDto.getRemark3());
			billOperate.setRemark4(billRefundDto.getRemark4());
			billOperate.setRefundType(billRefundDto.getRefundType());
			billOperate.setShopNo(billRefundDto.getShopNo());
			billOperate.setShopName(billRefundDto.getShopName());
			billOperate.setMerchantNo(billRefundDto.getMerchantNo());
			billOperate.setMerchantName(billRefundDto.getMerchantName());
			billOperateDao.insertSelective(billOperate);
			logger.debug("addBillOperate(BillOperateDto) - end - return"); 
			return billOperate;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单交易操作错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_ADD_ERROR,"新增账单交易操作错误！",e);
		}
	}

	@Override
	public BillRefundDto billRefundApplyEdit(BillRefundDto refundDto) throws TsfaServiceException {
		AssertUtils.notNull(refundDto);
		AssertUtils.notNull(refundDto.getCode(),"退款账单code不能为空");
		
		//0.1.检测，待处理未审核状态的交易才可修改
		BillRefund billRefund = billRefundDao.selectByPrimaryKey(refundDto.getCode());
		BillOperate findbillOperate = billOperateDao.selectByPrimaryKey(billRefund.getOperateCode());
		if(!BillOperateType.REFUND.name().equals(findbillOperate.getOperateType())) {
			logger.error("账单交易操作错误！非退款账单。BillOperate:{}",findbillOperate);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_UPDATE_ERROR,"该账单不是退款账单！");
		}
		if(!BillOperateStatus.INIT.name().equals(findbillOperate.getStatus())) {
			logger.error("账单交易操作错误！非未处理状态。BillOperate:{}",findbillOperate);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_UPDATE_ERROR,"该账单已处理！");
		}
		if(!BillOperateProcess.UNCHECK.name().equals(findbillOperate.getProcess())) {
			logger.error("账单交易操作错误！非未审核状态。Operate:{}",findbillOperate);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_UPDATE_ERROR,"该账单已审核！");
		}
		//2.检测退款额度是否超实收
		Bill findBill = billDao.selectByPrimaryKey(refundDto.getBillCode());
		if(!CommonStatus.NORMAL.name().equals(findBill.getStatus())) {
			logger.error("账单退费错误！账单已作废，不可退费。BillDto:{}",findBill);
			throw new TsfaServiceException(ErrorCode.BILL_UPDATE_ERROR,"该账单已作废！");
		}
		if((findBill.getRtAmount()+refundDto.getRtAmount())>findBill.getPayAmount()) {
			logger.error("该账单退款额度超过可退款额度！。BillRefundDto:{}，Bill{}",refundDto,findBill);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_UPDATE_ERROR,"退款额度超过账单可退款额度！");
		}
		//3.修改退款申请（直接进入待审核状态）
		refundDto.setOperateCode(billRefund.getOperateCode());
		updateRefundBillOperate(refundDto,new Date());//重新申请，申请时间为当前
		//4.修改退款账单
		updateBillRefund(refundDto);
		//4.1修改退款项目
		updateBillRefundDetail(billRefund, refundDto.getDetails());
		 
		BillRefundDto rtBillRefundDto=new BillRefundDto();
		rtBillRefundDto.setCode(billRefund.getCode());
		rtBillRefundDto.setBillCode(billRefund.getBillCode());
		return rtBillRefundDto;
		
	}
	
	private void updateBillRefundDetail(BillRefund billRefund, List<BillRefundDetailDto> details) throws TsfaServiceException {
		logger.debug("updateBillDetail(BillDetailDto addBillDetail={}) - start", details); 
		//1.先删该退款账单的所有详细
		BillRefundDetail record=new BillRefundDetail();
//		record.setBillCode(billRefund.getBillCode());
		record.setRefundCode(billRefund.getCode());
		billRefundDetailDao.deleteBillRefundDetail(record);
		//2.再增
		addBillRefundDetail(billRefund, details);	
	}
	
	private BillOperate updateRefundBillOperate(BillRefundDto billRefundDto,Date applyTime) throws TsfaServiceException {
		logger.debug("updateRefundBillOperate(BillRefundDto ={}) - start", billRefundDto); 

		AssertUtils.notNull(billRefundDto);
		AssertUtils.notNull(billRefundDto.getOperateCode(),"交易编号不能为空");
		try {
			BillOperate billOperate = new BillOperate();
			billOperate.setCode(billRefundDto.getOperateCode());
//			billOperate.setOperateType(BillOperateType.REFUND.name());
//			billOperate.setStatus(BillOperateStatus.INIT.name());
//			billOperate.setProcess(BillOperateProcess.UNCHECK.name());//待审核
//			billOperate.setCheckStatus(CheckStatus.UNCHECK.name());//待审核
			
//			billOperate.setMemberNoGuid(billOperateDto.getMemberNoGuid());
//			billOperate.setMemberNameGuid(billOperateDto.getMemberNameGuid());
//			billOperate.setCheckerNoGuid(billOperateDto.getCheckerNoGuid());
//			billOperate.setCheckerNameGuid(billOperateDto.getCheckerNameGuid());
			billOperate.setApplyTime(applyTime);//重新申请，则时间是当前
//			billOperate.setCheckTime(billOperateDto.getCheckTime());
			billOperate.setPayType(billRefundDto.getPayType());
			billOperate.setPayTypeName(billRefundDto.getPayTypeName());
			billOperate.setPayRemark(billRefundDto.getPayRemark());
			
			billOperate.setPayTime(billRefundDto.getRefundTime());
			//退款人 前端填入
			billOperate.setRecieverNo(billRefundDto.getRefundGdNo());
			billOperate.setRecieverName(billRefundDto.getRefundGdName());
			//款项前端计算并填入，因为前端可调整价格和折扣，故不根据项目后台计算
//			billOperate.setOriginalAmount(billOperateDto.getOriginalAmount());//原价款
//			billOperate.setReallyAmount(billOperateDto.getReallyAmount());//应付款
//			billOperate.setDiscountNum(billOperateDto.getDiscountNum());//折扣
//			billOperate.setDebtAmount(billOperateDto.getDebtAmount());//欠款
			billOperate.setPayAmount(billRefundDto.getRtAmount());//实付款
			
			billOperate.setUpdateId(billRefundDto.getMemberNoGuid());
			billOperate.setUpdateDate(new Date());
//			billOperate.setCreateId(billOperateDto.getMemberNoGuid());
//			billOperate.setCreateDate(new Date());
			billOperate.setRemark(billRefundDto.getRemark());
			billOperate.setRemark1(billRefundDto.getRemark1());
			billOperate.setRemark2(billRefundDto.getRemark2());
			billOperate.setRemark3(billRefundDto.getRemark3());
			billOperate.setRemark4(billRefundDto.getRemark4());
			billOperate.setRefundType(billRefundDto.getRefundType());
			billOperateDao.updateByPrimaryKeySelective(billOperate);
			logger.debug("addBillOperate(BillOperateDto) - end - return"); 
			return billOperate;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单交易操作错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_ADD_ERROR,"新增账单交易操作错误！",e);
		}
	}

	private BillRefund updateBillRefund(BillRefundDto billRefundDto) throws TsfaServiceException {
		logger.debug("updateBillRefund(AddBillRefund addBillRefund={}) - start", billRefundDto); 

		AssertUtils.notNull(billRefundDto);
		try {
			BillRefund billRefund = new BillRefund();
			billRefund.setCode(billRefundDto.getCode());
//			billRefund.setBillCode(billRefundDto.getBillCode());
//			billRefund.setOperateCode(billRefundDto.getOperateCode());
//			billRefund.setShopNo(billRefundDto.getShopNo());
//			billRefund.setShopName(billRefundDto.getShopName());
//			billRefund.setMerchantNo(billRefundDto.getMerchantNo());
//			billRefund.setMerchantName(billRefundDto.getMerchantName());
//			billRefund.setPatientNo(billRefundDto.getPatientNo());
//			billRefund.setPatientName(billRefundDto.getPatientName());
//			billRefund.setMedicalNo(billRefundDto.getMedicalNo());
			billRefund.setPayType(billRefundDto.getPayType());
			billRefund.setPayTypeName(billRefundDto.getPayTypeName());
			billRefund.setRtAmount(billRefundDto.getRtAmount());
			billRefund.setRefundGdNo(billRefundDto.getRefundGdNo());
			billRefund.setRefundGdName(billRefundDto.getRefundGdName());
			billRefund.setRefundTime(billRefundDto.getRefundTime());
//			billRefund.setRefundStatus(RefundStatus.UNCHECK.name());//待审核  初始
//			billRefund.setStatus(CommonStatus.NORMAL.name());
			billRefund.setRefundType(billRefundDto.getRefundType());
			billRefund.setUpdateId(billRefundDto.getMemberNoGuid());
			billRefund.setUpdateDate(new Date());
//			billRefund.setCreateId(billRefundDto.getMemberNoGuid());
//			billRefund.setCreateDate(new Date());
			billRefund.setRemark(billRefundDto.getRemark());
			billRefund.setRemark1(billRefundDto.getRemark1());
			billRefund.setRemark2(billRefundDto.getRemark2());
			billRefund.setRemark3(billRefundDto.getRemark3());
			billRefund.setRemark4(billRefundDto.getRemark4());
//			billRefund.setMemberNoGuid(billRefundDto.getMemberNoGuid());
//			billRefund.setMemberNameGuid(billRefundDto.getMemberNameGuid());
			billRefundDao.updateByPrimaryKeySelective(billRefund);
			logger.debug("updateBillRefund(BillRefundDto) - end - return"); 
			return billRefund;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单退款信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_ADD_ERROR,"新增账单退款信息信息错误！",e);
		}
	}

	@Override
	public BillRefundDto billRefund(BillRefundDto refundDto) throws TsfaServiceException {
		AssertUtils.notNull(refundDto.getCode(),"退款账单code不能为空");
		//1.检测是否已退款
		//1.1.检测，待处理已审核通过未退款状态的交易才可修改
		BillRefund billRefund = billRefundDao.selectByPrimaryKey(refundDto.getCode());
		BillOperate findbillOperate = billOperateDao.selectByPrimaryKey(billRefund.getOperateCode());
		if(!BillOperateStatus.INIT.name().equals(findbillOperate.getStatus())) {
			logger.error("账单交易操作错误！非未处理状态。BillOperate:{}",findbillOperate);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_UPDATE_ERROR,"该账单已处理！");
		}
		if(!BillOperateProcess.UNREFUND.name().equals(findbillOperate.getProcess())) {
			logger.error("账单交易操作错误！非待退款状态。Operate:{}",findbillOperate);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_UPDATE_ERROR,"该账单不是待退费状态！");
		}
		//2.1 预设 哪些信息 可修改避免前端注入修改了别的信息
		BillRefundDto updateRufundDto = new BillRefundDto();
		updateRufundDto.setCode(refundDto.getCode());
		updateRufundDto.setOperateCode(findbillOperate.getCode());
		updateRufundDto.setPayType(refundDto.getPayType());//支付方式
		updateRufundDto.setPayTypeName(refundDto.getPayTypeName());
		updateRufundDto.setRefundGdNo(refundDto.getRefundGdNo());//退款人
		updateRufundDto.setRefundGdName(refundDto.getRefundGdName());
		updateRufundDto.setRefundTime(refundDto.getRefundTime());
		updateRufundDto.setMemberNoGuid(refundDto.getMemberNoGuid());
		updateRufundDto.setRemark(refundDto.getRemark());//备注
		//2.修改退款申请
		updateRefundBillOperate(updateRufundDto,null);
		//3.修改退款账单(退款项目不可修改)
		updateBillRefund(updateRufundDto);
		//4.退款（仅保存则不退款）
		if(OptType.REFUND.name().equals(refundDto.getOptType())) {
			Bill findBill = billDao.selectByPrimaryKey(findbillOperate.getBillCode());
			findbillOperate = billOperateDao.selectByPrimaryKey(findbillOperate.getCode());
			//入参bill=findBill，待支付的账单，需要使用最新的账单的信息进行快照,findbillOperate需要使用最新的
			payment(findBill,findbillOperate,refundDto.getCode());
		}
		BillRefundDto rtBillRefundDto=new BillRefundDto();
		rtBillRefundDto.setCode(billRefund.getCode());
		rtBillRefundDto.setBillCode(billRefund.getBillCode());
		return rtBillRefundDto;
	}

	@Override
	public BillDto billCancel(BillDto billDto) throws TsfaServiceException {
		//0.检测该账单是否有交易,有未完成的交易則不可作废
		checkBillHasTrade(billDto.getCode());
		//1.账单作废
		Bill updateDto = new Bill();
		updateDto.setCode(billDto.getCode());
		updateDto.setStatus(CommonStatus.CANCEL.name());
		updateDto.setRemark(billDto.getRemark());
//		Bill findBill = billDao.selectByPrimaryKey(billDto.getCode());
//		updateDto.setReallyAmount(findBill.getOriginalAmount());//实付款
//		updateDto.setPayAmount(0L);//已付款
//		updateDto.setDebtAmount(findBill.getReallyAmount());//欠款
//		updateDto.setRtAmount(0L);//已退款
//		updateDto.setDiscountNum(10000);//折扣
//		updateDto.setRtStatus(BillRtStatus.NO.name());//初始 没有退费
//		updateDto.setPayStatus(PayStatus.ARREARAGE.name());// 未结清
		billDao.updateByPrimaryKeySelective(updateDto);
		
		//2.其下支付信息也作废
		BillPayment billPayment=new BillPayment();
		billPayment.setBillCode(billDto.getCode());
		billPayment.setStatus(CommonStatus.CANCEL.name());
		billPayment.setRemark(billDto.getRemark());
		billPaymentDao.updateBillPayment(billPayment);
		
		return billDto ;
	}

	@Override
	public void paymentCancel(BillPaymentDto paymentDto) throws TsfaServiceException {
		BillPayment findBillPayment = billPaymentDao.selectByPrimaryKey(paymentDto.getCode());
		if (findBillPayment == null) {
			throw new TsfaServiceException(ErrorCode.BILL_PAYMENT_UPDATE_ERROR, "未找到该支付记录数据。");
		}
		if(!CommonStatus.NORMAL.name().equals(findBillPayment.getStatus())) {
			throw new TsfaServiceException(ErrorCode.BILL_PAYMENT_UPDATE_ERROR, "该支付记录非正常状态。");
		}
		//0.1检测该账单是否有交易,有未完成的交易則不可作废
		//(为什么要支付记录作废也要检测，避免对不上账，比如 支付记录已完成支付300元，正进行300的退款，作废已支付的300，退款成功则成负数了倒贴了)
		checkBillHasTrade(findBillPayment.getBillCode());
		
		//0.1检测是否账单最后一笔支付记录，非最后一笔不可作废
		BillPaymentDto param= new BillPaymentDto();
		param.setBillCode(findBillPayment.getBillCode());
		param.setStatus(CommonStatus.NORMAL.name());
	 
		BillPaymentDto lastPayment = billPaymentDao.getLastNormalPayment(param);
		if (lastPayment != null) {
			if (!paymentDto.getCode().equals(lastPayment.getCode())) {
				logger.error("该支付记录不是最后一笔！billPayment.Code{}", paymentDto.getCode());
				throw new TsfaServiceException(ErrorCode.BILL_PAYMENT_UPDATE_ERROR, "请先选择最新支付记录进行作废");
			}
		}else {
			throw new TsfaServiceException(ErrorCode.BILL_PAYMENT_UPDATE_ERROR, "该支付记录不是最后一笔有效的支付记录！");
		}
		//1.支付记录作废
		BillPayment updatePayment=new BillPayment();
		updatePayment.setCode(paymentDto.getCode());
		updatePayment.setRemark(paymentDto.getRemark());
		updatePayment.setStatus(CommonStatus.CANCEL.name());
		billPaymentDao.updateByPrimaryKeySelective(updatePayment);
		//2.回到原快照c
		BillSnapshot billSnapshot = billSnapshotDao.selectByOperateCode(findBillPayment.getOperateCode());
		Bill updateBill = new Bill();
		updateBill.setCode(billSnapshot.getBillCode());
		updateBill.setOriginalAmount(billSnapshot.getOriginalAmount());//原价
		updateBill.setReallyAmount(billSnapshot.getReallyAmount());//应收
		updateBill.setDiscountNum(billSnapshot.getDiscountNum());//折扣
		updateBill.setPayAmount(billSnapshot.getPayAmount());//实付
		updateBill.setDebtAmount(billSnapshot.getDebtAmount());//欠款
		updateBill.setRtAmount(billSnapshot.getRtAmount());//退款
		updateBill.setRtStatus(billSnapshot.getRtStatus());//退款状态
		//快照是待收费状态，则修改为欠账状态
		if(PayStatus.UNPAY.name().equals(billSnapshot.getPayStatus())) {
			updateBill.setPayStatus(PayStatus.ARREARAGE.name());
		}else {
			updateBill.setPayStatus(billSnapshot.getPayStatus());
		}
		billDao.updateByPrimaryKeySelective(updateBill);
		
		//3.如果是退款则需要把 账单项目的已退数和已退金额 还原 
		if(BillOperateType.REFUND.name().equals(findBillPayment.getBizType())) {
			//3.1修改账单项目退款信息
			//找出本次退款的项目信息
			FindBillRefundDetailPage findBillRefundDetailPage = new FindBillRefundDetailPage();
			BillRefundDetailDto billRefundDetailDto = new BillRefundDetailDto();
			billRefundDetailDto.setOperateCode(param.getCode());//
			findBillRefundDetailPage.setParam(billRefundDetailDto);
			List<BillRefundDetailDto> rtDetailDtos=billRefundDetailDao.findBillRefundDetails(findBillRefundDetailPage);
			Map<String, BillRefundDetailDto> map=new HashMap<>();
			String refundCode = null;
			for (Iterator<BillRefundDetailDto> iterator = rtDetailDtos.iterator(); iterator.hasNext();) {
				BillRefundDetailDto ele =  iterator.next();
				map.put(ele.getProjectCode(), ele);
				refundCode=ele.getRefundCode();
			}
			//找出账单的收费项目信息
			FindBillDetailPage findBillDetailPage=new FindBillDetailPage();
			BillDetailDto findDetail=new BillDetailDto();
			findDetail.setBillCode(findBillPayment.getBillCode());
			findBillDetailPage.setParam(findDetail);
			List<BillDetailDto> detailDtos= billDetailDao.findBillDetails(findBillDetailPage);
			//项目退款金额和数量 剔除当前账单项目中
			BillDetail record = new BillDetail();
			for (Iterator<BillDetailDto> iterator = detailDtos.iterator(); iterator.hasNext();) {
				BillDetailDto ele =  iterator.next();
				BillRefundDetailDto rtInfo=map.get(ele.getProjectCode());
				if(rtInfo!=null) {
					record.setCode(ele.getCode());
					if(rtInfo.getItemNum()!=null) {
						//退款数=已退款数-该次的退款数
						record.setRtNum(ele.getRtNum() - rtInfo.getItemNum());
					}
					if(rtInfo.getReturnAmount()!=null) {
					//退款金额=已退款金额-该次的退款金额
						record.setRtAmount(ele.getRtAmount() - rtInfo.getReturnAmount());
					}
					billDetailDao.updateByPrimaryKeySelective(record);
				}
			}
			//3.2退款账单 作废
			BillRefund updateRefund=new BillRefund();
			updateRefund.setCode(refundCode);
			updateRefund.setStatus(CommonStatus.CANCEL.name());
			billRefundDao.updateByPrimaryKeySelective(updateRefund);
		
		}else if(BillOperateType.DEBT.name().equals(findBillPayment.getBizType())||BillOperateType.PAY.name().equals(findBillPayment.getBizType())) {
			//1.收欠款 或 收费 作废则 将项目收费 回到上一次
			projectDetailCancel(findBillPayment.getBillCode(), findBillPayment.getOperateCode());
		}
	}

	private void projectDetailCancel(String billCode,String operateCode) {
		//1.1本次交易的项目 收费信息
		FindPayDetailPage findBillRefundDetailPage = new FindPayDetailPage();
		PayDetailDto billRefundDetailDto = new PayDetailDto();
		billRefundDetailDto.setOperateCode(operateCode);
		findBillRefundDetailPage.setParam(billRefundDetailDto);
		List<PayDetailDto> rtDetailDtos = payDetailDao.findPayDetails(findBillRefundDetailPage);
		if(rtDetailDtos==null || rtDetailDtos.size()==0) {
			return ;
		}
		//1.2账单项目的收费信息
		FindBillDetailPage findBillDetailPage=new FindBillDetailPage();
		BillDetailDto findDetail=new BillDetailDto();
		findDetail.setBillCode(billCode);
		findBillDetailPage.setParam(findDetail);
		List<BillDetailDto> detailDtos= billDetailDao.findBillDetails(findBillDetailPage);
		//1.3key=ProjectCode
		Map<String, BillDetailDto> map=new HashMap<>();
		for (Iterator<BillDetailDto> iterator = detailDtos.iterator(); iterator.hasNext();) {
			BillDetailDto ele =  iterator.next();
			map.put(ele.getProjectCode(), ele);
		}
		
		//2.账单项目各金额复原
		BillDetail billDetail=new BillDetail();
		for (Iterator<PayDetailDto> iterator = rtDetailDtos.iterator(); iterator.hasNext();) {
			PayDetailDto ele =  iterator.next();
			BillDetailDto rtInfo=map.get(ele.getProjectCode());
			if(rtInfo!=null) {
				billDetail.setCode(rtInfo.getCode());
				billDetail.setDebtAmount(ele.getOriginalDebtAmount()); 
				billDetail.setPayAmount(ele.getOriginalPayAmount());
				billDetail.setReallyAmount(ele.getOriginalReallyAmount()); 
				billDetailDao.updateByPrimaryKeySelective(billDetail);
			}
		}
	
	}
	
	@Override
	public BillDto billSum(BillDto billDto) throws TsfaServiceException {
		AssertUtils.notNull(billDto);
		try {
			BillDto bill = billDao.billSum(billDto);
			 
			logger.debug("billSum(BillDto) - end - return value={}", bill); //$NON-NLS-1$
			return bill;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找账单信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_FIND_ERROR,"统计患者账单信息信息错误！",e);
		}

	}

	@Override
	public void billRefundCheck(BillOperateDto billOperateDto) throws TsfaServiceException {
		BillOperate findbillOperate = billOperateDao.selectByPrimaryKey(billOperateDto.getCode());
		if(!CheckStatus.UNCHECK.name().equals(findbillOperate.getCheckStatus())) {
			logger.error("账单交易操作错误！该交易已审核。Operate:{}",billOperateDto);
			throw new TsfaServiceException(com.ye.business.hx.constant.ErrorCode.BILL_OPERATE_UPDATE_ERROR,"该交易已审核！");
		}
		BillOperate updateDto=new BillOperate();
		updateDto.setCode(billOperateDto.getCode());
		updateDto.setRemark(billOperateDto.getRemark());
		updateDto.setCheckStatus(billOperateDto.getCheckStatus());
		updateDto.setCheckerNoGuid(billOperateDto.getCheckerNoGuid());
		updateDto.setCheckerNameGuid(billOperateDto.getCheckerNameGuid());
		updateDto.setCheckTime(new Date());
		if(CheckStatus.PASS.name().equals(billOperateDto.getCheckStatus())) {
			updateDto.setProcess(BillOperateProcess.UNREFUND.name());
		}else {
			updateDto.setProcess(BillOperateProcess.UNPASS.name());//审核不通过依旧为待处理账单，让申请人可删。
		}
		billOperateDao.updateByPrimaryKeySelective(updateDto);
		
		//2 若是退款则修改退款账单状态
		if(BillOperateType.REFUND.name().equals(findbillOperate.getOperateType())) {
			BillRefund billRefund=new BillRefund();
			billRefund.setOperateCode(findbillOperate.getCode());
			if(CheckStatus.PASS.name().equals(billOperateDto.getCheckStatus())) {
				billRefund.setRefundStatus(RefundStatus.UNPAY.name());
			}else {
				billRefund.setRefundStatus(RefundStatus.CHKREFUS.name());
			}
			billRefund.setRemark(billOperateDto.getRemark());
			
			billRefundDao.updateByOperateCodeSelective(billRefund);
		}
	}

	@Override
	public void billOperateCancel(BillOperateDto billOperateDto) throws TsfaServiceException {
		BillOperate findbillOperate = billOperateDao.selectByPrimaryKey(billOperateDto.getCode());
		if(!BillOperateStatus.INIT.name().equals(findbillOperate.getStatus())) {
			logger.error("账单交易操作错误！该交易已处理。Operate:{}",billOperateDto);
			throw new TsfaServiceException(com.ye.business.hx.constant.ErrorCode.BILL_OPERATE_UPDATE_ERROR,"该交易已处理！");
		}
		//1.交易作废
		BillOperate updateDto = new BillOperate();
		updateDto.setCode(billOperateDto.getCode());
		updateDto.setStatus(BillOperateStatus.CANCEL.name());
		updateDto.setRemark(billOperateDto.getRemark());
		billOperateDao.updateByPrimaryKeySelective(updateDto);
		
		//2.业务作废
		if(BillOperateType.REFUND.name().equals(findbillOperate.getOperateType())) {
			BillRefund billRefund=new BillRefund();
			billRefund.setOperateCode(findbillOperate.getCode());
			billRefund.setStatus(CommonStatus.CANCEL.name()); 
			billRefund.setRemark(billOperateDto.getRemark());
			
			billRefundDao.updateByOperateCodeSelective(billRefund);
		}else if(BillOperateType.PAY.name().equals(findbillOperate.getOperateType())) {
			Bill bill=new Bill();
			bill.setCode(findbillOperate.getBillCode());
			bill.setRemark(billOperateDto.getRemark());
			bill.setStatus(CommonStatus.CANCEL.name()); 
			
			billDao.updateByPrimaryKeySelective(bill);
		}
	}

	@Override
	public BillDto billSumBySearch(FindBillPage findBillPage) throws TsfaServiceException {
		AssertUtils.notNull(findBillPage);
		try {
			BillDto bill = billDao.billSumBySearch(findBillPage);
			 
			logger.debug("billSumBySearch(findBillPage) - end - return value={}", bill); //$NON-NLS-1$
			return bill;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("统计患者账单信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_FIND_ERROR,"统计患者账单信息信息错误！",e);
		}
	}
	
	
		
}
