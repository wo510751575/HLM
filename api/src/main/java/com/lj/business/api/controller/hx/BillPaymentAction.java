package com.lj.business.api.controller.hx;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.BillDetailDto;
import com.ye.business.hx.dto.BillDto;
import com.ye.business.hx.dto.BillPaymentDto;
import com.ye.business.hx.dto.FindBillDetailPage;
import com.ye.business.hx.dto.FindBillPage;
import com.ye.business.hx.dto.FindBillPaymentPage;
import com.ye.business.hx.dto.FindStDailyPayPage;
import com.ye.business.hx.dto.StDailyPayDto;
import com.ye.business.hx.emus.PayStatus;
import com.ye.business.hx.service.IBillDetailService;
import com.ye.business.hx.service.IBillPaymentService;
import com.ye.business.hx.service.IBillService;
import com.ye.business.hx.service.IStDailyPayService;

/**
 * 类说明：商户账单统计。
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019.06.17
 */
@Controller
@RequestMapping(value = "/hx/bill/mc")
public class BillPaymentAction extends Action {
	@Autowired
	private IBillPaymentService billPaymentService;
	@Autowired
	private IBillService billService;
	@Autowired
	private IBillDetailService billDetailService;
	@Autowired
	private IStDailyPayService stDailyPayService;

	/**
	 * 账单收费统计:商户支付记录 列表(收费对账)。
	 * 
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/payment/list.do" })
	@ResponseBody
	public Page<BillPaymentDto> paymentListPage(BillPaymentDto param, FindBillPaymentPage findPage) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户号不能为空！");
		findPage.setParam(param);
		Page<BillPaymentDto> data = billPaymentService.findBillPaymentPageByMerchant(findPage);
		return data;
	}
	
	/**
	 * 按条件统计实收总额，退费总额(收费对账).
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/payment/sum.do" })
	@ResponseBody
	public GeneralResponse paymentSum(BillPaymentDto param, FindBillPaymentPage findPage) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		findPage.setParam(param);
		
		BillDto rtBillDto = billPaymentService.paymentSum(findPage);
		return GeneralResponse.generateSuccessResponse(rtBillDto);
	}

	/**
	 * 账单收费统计:已支付账单项目收费列表
	 * 
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/project/list.do" })
	@ResponseBody
	public Page<BillDetailDto> billProjectListPage(BillDetailDto param, FindBillDetailPage findPage) {
		preBillProjectParam(param, findPage);
		Page<BillDetailDto> data = billDetailService.findBillDetailPageByMerchant(findPage);
		return data;
	}
	
	private void preBillProjectParam(BillDetailDto param, FindBillDetailPage findPage) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户号不能为空！");

		//1.首次缴费时间
		Date payStartDate = null;
		Date payEndDate = null;
		if(StringUtils.isNotEmpty(param.getPayStartDateStr())) {
			payStartDate=DateUtils.parseDate(param.getPayStartDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
			if (payStartDate == null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "缴费开始日期格式异常！");
			}
		}
		if(StringUtils.isNotEmpty(param.getPayEndDateStr())) {
			payEndDate=DateUtils.parseDate(param.getPayEndDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
			if (payEndDate == null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "缴费结束日期格式异常！");
			}
		}
		
		param.setPayStartDate(payStartDate);
		param.setPayEndDate(payEndDate);
	 
		param.setNotPayStatus(PayStatus.UNPAY.name());
		findPage.setParam(param);
	}
	
	/**
	 * 已支付账单项目收费列表：按搜索条件统计实收总额，退费总额，欠费总额.
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/project/sum.do" })
	@ResponseBody
	public GeneralResponse billProjectSum(BillDetailDto param, FindBillDetailPage findPage) {
		preBillProjectParam(param, findPage);
		BillDetailDto rtBillDto = billDetailService.billProjectSum(findPage);
		return GeneralResponse.generateSuccessResponse(rtBillDto);
	}
	
	
	/**
	 * 账单收费统计:账单列表
	 * 
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/list.do" })
	@ResponseBody
	public Page<BillDto> billListPage(BillDto param, FindBillPage findPage) {
		preBillListParam(param, findPage);
		Page<BillDto> data = billService.findBillPage(findPage);
		return data;
	}
	
	private void preBillListParam(BillDto param, FindBillPage findPage) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户号不能为空！");

		//1.首次缴费时间
		Date payStartDate = null;
		Date payEndDate = null;
		if(StringUtils.isNotEmpty(param.getPayStartDateStr())) {
			payStartDate=DateUtils.parseDate(param.getPayStartDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
			if (payStartDate == null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "缴费开始日期格式异常！");
			}
		}
		if(StringUtils.isNotEmpty(param.getPayEndDateStr())) {
			payEndDate=DateUtils.parseDate(param.getPayEndDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
			if (payEndDate == null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "缴费结束日期格式异常！");
			}
		}
		//2.就诊时间
		Date clinicStartDate = null;
		Date clinicEndDate = null;
		if(StringUtils.isNotEmpty(param.getClinicStartDateStr())) {
			clinicStartDate=DateUtils.parseDate(param.getClinicStartDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
			if(clinicStartDate==null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "就诊开始时间格式异常！");
			}
		}
		if(StringUtils.isNotEmpty(param.getClinicEndDateStr())) {
			clinicEndDate = DateUtils.parseDate(param.getClinicEndDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
			if (clinicEndDate == null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "就诊结束时间格式异常！");
			}
		}
		
		param.setPayStartDate(payStartDate);
		param.setPayEndDate(payEndDate);
		param.setClinicStartDate(clinicStartDate);
		param.setClinicEndDate(clinicEndDate);
		param.setNotPayStatus(PayStatus.UNPAY.name());
		findPage.setParam(param);
	}
	/**
	 * 账单列表：按搜索条件统计实收总额，退费总额，欠费总额.
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/sum.do" })
	@ResponseBody
	public GeneralResponse billSum(BillDto param, FindBillPage findPage) {
		preBillListParam(param, findPage);
		BillDto rtBillDto = billService.billSumBySearch(findPage);
		return GeneralResponse.generateSuccessResponse(rtBillDto);
	}
	
	/**
	 * 每日各支付方式收支列表。
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/stDailyPay/list.do" })
	@ResponseBody
	public Page<StDailyPayDto> findStDailyPayPage(StDailyPayDto param, FindStDailyPayPage findPage) {
		prefindStDailyPayPageParam(param, findPage);
		Page<StDailyPayDto> data = stDailyPayService.findStDailyPayPageGroupByStDay(findPage);
		return data;
	}

	private void prefindStDailyPayPageParam(StDailyPayDto param, FindStDailyPayPage findPage) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户号不能为空！");
		AssertUtils.notNullAndEmpty(param.getPayMode(), "支付类型不能为空");
		findPage.setParam(param);
		Date startDate= null;
		Date endDate= null;
		if(StringUtils.isNotEmpty(param.getStartDateStr())) {
			startDate =DateUtils.parseDate(param.getStartDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
			if (startDate == null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "开始日期格式异常！");
			}
		}
		if(StringUtils.isNotEmpty(param.getEndDateStr())) {
			endDate=DateUtils.parseDate(param.getEndDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
			if (endDate == null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "结束日期格式异常！");
			}
		}
		param.setStartDate(startDate);
		param.setEndDate(endDate);
	}
	
	/**
	 * 各支付方式收支合计。
	 * 
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/stDailyPay/sum.do" })
	@ResponseBody
	public List<StDailyPayDto> stDailyPaySum(StDailyPayDto param, FindStDailyPayPage findPage) {
		prefindStDailyPayPageParam(param, findPage);
		List<StDailyPayDto> data = stDailyPayService.stDailyPaySumGroupByPayType(findPage);
		return data;
	}
}
