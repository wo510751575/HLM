/**
 * 
 */
package com.lj.business.api.controller.hx;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.BillDetailDto;
import com.ye.business.hx.dto.BillDto;
import com.ye.business.hx.dto.BillOperateDto;
import com.ye.business.hx.dto.BillPaymentDto;
import com.ye.business.hx.dto.BillRefundDetailDto;
import com.ye.business.hx.dto.BillRefundDto;
import com.ye.business.hx.dto.FindBillDetailPage;
import com.ye.business.hx.dto.FindBillOperatePage;
import com.ye.business.hx.dto.FindBillPage;
import com.ye.business.hx.dto.FindBillPaymentPage;
import com.ye.business.hx.dto.FindBillRefundDetailPage;
import com.ye.business.hx.dto.FindPayDetailPage;
import com.ye.business.hx.dto.PayDetailDto;
import com.ye.business.hx.emus.BillOperateStatus;
import com.ye.business.hx.emus.BillOperateType;
import com.ye.business.hx.emus.CommonStatus;
import com.ye.business.hx.emus.PayStatus;
import com.ye.business.hx.service.IBillDetailService;
import com.ye.business.hx.service.IBillOperateService;
import com.ye.business.hx.service.IBillPaymentService;
import com.ye.business.hx.service.IBillRefundDetailService;
import com.ye.business.hx.service.IBillService;
import com.ye.business.hx.service.IPayDetailService;

/**
 * 类说明：患者账单。
 * <p>
 *   
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 *   
 * CreateDate:  2019年4月16日
 */
@Controller
@RequestMapping(value = "/hx/bill/")
public class HlmBillAction extends Action {
	
	@Autowired
	private IBillService billService;
	
	@Autowired
	private IBillDetailService billDetailService;
	
	@Autowired
	private IBillOperateService billOperateService;
	
	@Autowired
	private IBillPaymentService billPaymentService;
	
	@Autowired
	private IBillRefundDetailService billRefundDetailService;
	
	@Autowired
	private IPayDetailService payDetailService;
	
	/**
	 * 新增账单。
	 * <br/>仅保存billOperate.optType=SAVE。
	 * <br/>保存并收费billOperate.optType=PAY。
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/add.do" })
	@ResponseBody
	public BillDto billAdd(BillDto param,String billOperateJson,String detailsJson ) {
		
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(param.getMerchantName(),"商户名称不能为空！");
		
		AssertUtils.notNullAndEmpty(param.getPatientNo(), "患者编号不能为空");
		AssertUtils.notNullAndEmpty(param.getPatientName(), "患者名称不能为空");
		AssertUtils.notNullAndEmpty(param.getMedicalNo(), "病历编号不能为空");
		
		AssertUtils.notNullAndEmpty(param.getBillType(),"账单类型不能为空！");
		AssertUtils.notNullAndEmpty(detailsJson, "收费明细项不能为空");
		AssertUtils.notNullAndEmpty(billOperateJson,"账单交易不能为空！");
		
		List<BillDetailDto> details =JSONArray.parseArray(detailsJson,BillDetailDto.class);
		BillOperateDto billOperate =(BillOperateDto) JsonUtils.objectFromJson(billOperateJson, BillOperateDto.class);
		param.setBillOperate(billOperate);
		param.setDetails(details);
		
		AssertUtils.notNullAndEmpty(param.getDetails(), "收费明细项不能为空");
		AssertUtils.notNullAndEmpty(param.getBillOperate(),"账单交易不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillOperate().getOptType(),"操作类型不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillOperate().getPayAmount(),"收费金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillOperate().getOriginalAmount(),"原价金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillOperate().getReallyAmount(),"应付金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillOperate().getDiscountNum(),"折扣不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillOperate().getDebtAmount(),"欠款金额不能为空！");
		
		Date clinicTime = null;
		if(StringUtils.isNotEmpty(param.getClinicTimeStr())) {
			clinicTime=DateUtils.parseDate(param.getBillOperate().getPayTimeStr(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss,null);
			if(clinicTime==null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "就诊时间格式异常！");
			}
			param.setClinicTime(clinicTime);
		}
		
		Date payTime = null;
		if(StringUtils.isNotEmpty(param.getBillOperate().getPayTimeStr())) {
			payTime=DateUtils.parseDate(param.getBillOperate().getPayTimeStr(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss,null);
			if(payTime==null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "支付时间格式异常！");
			}
			param.getBillOperate().setPayTime(payTime);
		}
		
		
		BillDto rtData = billService.addBill(param);
		return rtData;
	}
	
	/**
	 * 修改账单，待收费-编辑全部。
	 * <br/>仅保存billOperate.optType=SAVE。
	 * <br/>保存并收费billOperate.optType=PAY。
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/editAll.do" })
	@ResponseBody
	public GeneralResponse billEditAll(BillDto param,String billOperateJson,String detailsJson ) {
		AssertUtils.notNullAndEmpty(param.getCode(),"账单编号不能为空！");
		AssertUtils.notNull(param.getUpdateId(),"修改人编号不能为空");
		
		AssertUtils.notNullAndEmpty(detailsJson, "收费明细项不能为空");
		AssertUtils.notNullAndEmpty(billOperateJson,"账单交易不能为空！");
		
		List<BillDetailDto> details =JSONArray.parseArray(detailsJson,BillDetailDto.class);
		BillOperateDto billOperate =(BillOperateDto) JsonUtils.objectFromJson(billOperateJson, BillOperateDto.class);
		param.setBillOperate(billOperate);
		param.setDetails(details);
		
		AssertUtils.notNull(param.getBillOperate().getCode(),"交易编号不能为空");
		AssertUtils.notNullAndEmpty(param.getBillOperate().getOptType(),"操作类型不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillOperate().getPayAmount(),"收费金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillOperate().getOriginalAmount(),"原价金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillOperate().getReallyAmount(),"应付金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillOperate().getDiscountNum(),"折扣不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillOperate().getDebtAmount(),"欠款金额不能为空！");
		
		Date clinicTime = null;
		if(StringUtils.isNotEmpty(param.getClinicTimeStr())) {
			clinicTime=DateUtils.parseDate(param.getBillOperate().getPayTimeStr(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss,null);
			if(clinicTime==null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "就诊时间格式异常！");
			}
			param.setClinicTime(clinicTime);
		}
		
		Date payTime = null;
		if(StringUtils.isNotEmpty(param.getBillOperate().getPayTimeStr())) {
			payTime=DateUtils.parseDate(param.getBillOperate().getPayTimeStr(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss,null);
			if(payTime==null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "支付时间格式异常！");
			}
			param.getBillOperate().setPayTime(payTime);
		}
		billService.updateBillInfo(param);
		return GeneralResponse.generateSuccessResponse(param.getCode());
	}
	
	
	/**
	 * 已收费-只能编辑 收费项目的绩效相关人员。
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/editMember.do" })
	@ResponseBody
	public GeneralResponse billEditMember(BillDto param,String detailsJson) {
		AssertUtils.notNullAndEmpty(param.getCode(),"账单编号不能为空！");
		AssertUtils.notNullAndEmpty(detailsJson, "收费明细项不能为空");
		AssertUtils.notNull(param.getUpdateId(),"修改人编号不能为空");
		
		List<BillDetailDto> details =JSONArray.parseArray(detailsJson,BillDetailDto.class);
		param.setDetails(details);
		AssertUtils.notNullAndEmpty(param.getDetails(), "收费明细项不能为空");
		
		billDetailService.updateBillDetailMember(param);
		return GeneralResponse.generateSuccessResponse(param.getCode());
	}
	
	/**
	 * 已支付账单列表
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/haspayList.do" })
	@ResponseBody
	public Page<BillDto> payBillListPage(BillDto param,FindBillPage findPage) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(param.getPatientNo(),"患者编号不能为空！");
		
		param.setNotPayStatus(PayStatus.UNPAY.name());
		
		findPage.setParam(param);
		Page<BillDto> data= billService.findBillPage(findPage);
		return data;
	}
	
	/**
	 * 待处理账单列表 
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/untreatedList.do" })
	@ResponseBody
	public Page<BillOperateDto> unpayBillListPage(BillOperateDto param,FindBillOperatePage findPage) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(param.getPatientNo(),"患者编号不能为空！");
		param.setStatus(BillOperateStatus.INIT.name());
		
		findPage.setParam(param);
		Page<BillOperateDto> data= billOperateService.findUntreatedBillOperatePage(findPage);
		return data;
	}
	
	/**
	 * 账单详情.
	 * <br/>用于（待收费/已收费/退款待审核）时查看详情
	 * @param param (code：账单交易code, billCode:账单code)
	 * <br/>已收费账单则仅入参 billCode:账单编号
	 * <br/>待处理账单则需入参 code:待处理交易code,
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/detail.do" })
	@ResponseBody
	public BillDto billInfo(BillOperateDto param) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillCode(),"账单编号不能为空！");
		
		//1.账单
		BillDto findBill=new BillDto();
		findBill.setCode(param.getBillCode());
		BillDto bill = billService.findBill(findBill);
		//2.账单详细
		FindBillDetailPage findBillDetailPage=new FindBillDetailPage();
		BillDetailDto findDetail=new BillDetailDto();
		findDetail.setBillCode(param.getBillCode());
		findBillDetailPage.setParam(findDetail);
		List<BillDetailDto> detailDtos=billDetailService.findBillDetails(findBillDetailPage);
		bill.setDetails(detailDtos);
		//3.账单交易信息
		if(StringUtils.isNotEmpty(param.getCode())) {
			//3.1账单交易
			BillOperateDto billOperateDto = billOperateService.findBillOperate(param);
			bill.setBillOperate(billOperateDto);
			//map用于组合项目实收或退款信息计算逻辑
			Map<String, BillDetailDto> map=new HashMap<>();
			for (Iterator<BillDetailDto> iterator = detailDtos.iterator(); iterator.hasNext();) {
				BillDetailDto ele =  iterator.next();
				map.put(ele.getProjectCode(), ele);
			}
			//3.2如果是退款 ，退款项目要组合出去
			if(BillOperateType.REFUND.name().equals(billOperateDto.getOperateType())){
				//2退款项目
				FindBillRefundDetailPage findBillRefundDetailPage=new FindBillRefundDetailPage();
				BillRefundDetailDto billRefundDetailDto=new BillRefundDetailDto();
				billRefundDetailDto.setOperateCode(param.getCode());
				findBillRefundDetailPage.setParam(billRefundDetailDto);
				List<BillRefundDetailDto> rtDetailDtos=billRefundDetailService.findBillRefundDetails(findBillRefundDetailPage);
				//3项目退款金额和数量设置到当前账单项目中
				for (Iterator<BillRefundDetailDto> iterator = rtDetailDtos.iterator(); iterator.hasNext();) {
					BillRefundDetailDto ele =  iterator.next();
					BillDetailDto rtInfo = map.get(ele.getProjectCode());
					if(rtInfo!=null) {
						rtInfo.setCurrentRtNum(ele.getItemNum());
						rtInfo.setCurrentRtAmount(ele.getReturnAmount());
						billOperateDto.setRefundCode(ele.getRefundCode());//退款code要给前端用于编辑
					}
				}
			}else if(BillOperateType.DEBT.name().equals(billOperateDto.getOperateType())||BillOperateType.PAY.name().equals(billOperateDto.getOperateType())) {
				//3.2.收欠款 或 收费  找出当前交易的各项目实收信息
				FindPayDetailPage findBillRefundDetailPage = new FindPayDetailPage();
				PayDetailDto billRefundDetailDto = new PayDetailDto();
				billRefundDetailDto.setOperateCode(billOperateDto.getCode());
				findBillRefundDetailPage.setParam(billRefundDetailDto);
				List<PayDetailDto> rtDetailDtos = payDetailService.findPayDetails(findBillRefundDetailPage);
				for (Iterator<PayDetailDto> iterator = rtDetailDtos.iterator(); iterator.hasNext();) {
					PayDetailDto ele =  iterator.next();
					BillDetailDto rtInfo=map.get(ele.getProjectCode());
					if(rtInfo!=null) {
						rtInfo.setCurrentDebtAmount(ele.getDebtAmount()); 
						rtInfo.setCurrentPayAmount(ele.getPayAmount());
						rtInfo.setCurrentReallyAmount(ele.getReallyAmount()); 
					}
				}
			}
		}
		
		return bill;
	}
	
	/**
	 * 患者支付记录 列表。
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/payment/list.do" })
	@ResponseBody
	public Page<BillPaymentDto> paymentListPage(BillPaymentDto param,FindBillPaymentPage findPage){
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(param.getPatientNo(),"患者编号不能为空！");
		findPage.setParam(param);
		Page<BillPaymentDto> data= billPaymentService.findBillPaymentPage(findPage);
		return data;
	}
	
	/**
	 * 支付记录详情：包含产生该记录的交易详情。
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/payment/detail.do" })
	@ResponseBody
	public Map<Object, Object> paymentDetail(BillPaymentDto param){
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(param.getCode(),"支付编号不能为空！");
		//1.支付记录详情
		BillPaymentDto payment= billPaymentService.findBillPayment(param);
		
		//2.账单交易信息
		BillOperateDto paramOpt =new BillOperateDto();
		FindBillOperatePage findPage =new FindBillOperatePage();
		paramOpt.setCode(payment.getOperateCode());
		
		findPage.setParam(paramOpt);
		Page<BillOperateDto> opts= billOperateService.findUntreatedBillOperatePage(findPage);
		BillOperateDto operate=null;
		List<BillOperateDto> list =new ArrayList<BillOperateDto>();
		list.addAll(opts.getRows());
		if(list.size()>0) {
			operate=list.get(0);
		}
		Map<Object, Object> data = new LinkedHashMap<>();
		data.put("payment", payment);
		data.put("billOperate", operate);
		return data;
	}
	
	
	/**
	 * 收欠款
	 * <br/>仅保存billOperate.optType=SAVE。
	 * <br/>保存并收费billOperate.optType=DEBT。
	 * @return
	 */
	@RequestMapping(value = { "/debtRepay.do" })
	@ResponseBody
	public BillOperateDto debtRepay(BillOperateDto param,String detailsJson ) {
		AssertUtils.notNullAndEmpty(param.getBillCode(),"账单code不能为空！");
		AssertUtils.notNullAndEmpty(param.getPayAmount(),"收费金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getOriginalAmount(),"原价金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getReallyAmount(),"应付金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getDiscountNum(),"折扣不能为空！");
		AssertUtils.notNullAndEmpty(param.getDebtAmount(),"欠款金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getOptType(),"操作类型不能为空！");
		AssertUtils.notNullAndEmpty(detailsJson, "项目实收不能为空"); //2019年7月9日 前端未开发注释掉
		List<PayDetailDto> details =JSONArray.parseArray(detailsJson,PayDetailDto.class);
		param.setPayDetails(details);
		AssertUtils.notNullAndEmpty(param.getPayDetails(), "项目实收不能为空");//2019年7月9日 前端未开发注释掉
		
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(),"申请人不能为空！");
		AssertUtils.notNullAndEmpty(param.getMemberNameGuid(),"申请人名称不能为空！");
		
		Date payTime = null;
		if(StringUtils.isNotEmpty(param.getPayTimeStr())) {
			payTime=DateUtils.parseDate(param.getPayTimeStr(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss,null);
			if(payTime==null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "支付时间格式异常！");
			}
			param.setPayTime(payTime);
		}else {
			param.setPayTime(new Date());//前端不传入则修改为当前
		}
		BillOperateDto data = null;
		
		data = billService.debtRepay(param);
		return data;
	}
	
	/**
	 * 修改欠款交易。
	 * <br/>仅保存billOperate.optType=SAVE。
	 * <br/>保存并收费billOperate.optType=DEBT。
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/debtRepay/edit.do" })
	@ResponseBody
	public BillOperateDto debtRepayEdit(BillOperateDto param,String detailsJson ) {
		AssertUtils.notNullAndEmpty(param.getCode(),"交易code不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillCode(),"账单code不能为空！");
		AssertUtils.notNullAndEmpty(param.getPayAmount(),"收费金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getOriginalAmount(),"原价金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getReallyAmount(),"应付金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getDiscountNum(),"折扣不能为空！");
		AssertUtils.notNullAndEmpty(param.getDebtAmount(),"欠款金额不能为空！");
		AssertUtils.notNullAndEmpty(param.getOptType(),"操作类型不能为空！");
		AssertUtils.notNullAndEmpty(detailsJson, "项目实收不能为空"); //2019年7月9日 前端未开发注释掉
		List<PayDetailDto> details =JSONArray.parseArray(detailsJson,PayDetailDto.class);
		param.setPayDetails(details);	
		AssertUtils.notNullAndEmpty(param.getPayDetails(), "项目实收不能为空"); //2019年7月9日 前端未开发注释掉
		
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(),"申请人不能为空！");
		AssertUtils.notNullAndEmpty(param.getMemberNameGuid(),"申请人名称不能为空！");
		
		
		Date payTime = null;
		if(StringUtils.isNotEmpty(param.getPayTimeStr())) {
			payTime=DateUtils.parseDate(param.getPayTimeStr(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss,null);
			if(payTime==null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "支付时间格式异常！");
			}
			param.setPayTime(payTime);
		}else {
			param.setPayTime(new Date());//前端不传入则修改为当前
		}
		BillOperateDto data = null;
		
		data = billService.debtRepayEdit(param);
		return data;
	}
	
	/**
	 * 查询欠款交易详情。
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/debtRepay/detail.do" })
	@ResponseBody
	public BillOperateDto debtRepayDetail(BillOperateDto param) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(param.getCode(),"交易编号不能为空！");
		
		BillOperateDto data = billOperateService.findBillOperate(param);
		return data;
	}
	
	/**
	 * 退款申请。
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/refund/apply.do" })
	@ResponseBody
	public BillRefundDto billRefundApply(BillRefundDto param,String detailsJson) {
		AssertUtils.notNullAndEmpty(param.getBillCode(),"账单编号不能为空！");
		AssertUtils.notNullAndEmpty(detailsJson, "退款明细项不能为空");
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(),"申请人不能为空！");
		AssertUtils.notNullAndEmpty(param.getMemberNameGuid(),"申请人名称不能为空！");
		AssertUtils.notNullAndEmpty(param.getRtAmount(),"退款额度不能为空！");
		AssertUtils.notNullAndEmpty(param.getRefundType(),"退款类型不能为空！");
//		AssertUtils.notNullAndEmpty(param.getRefundTimeStr(),"退费时间不能为空！");
		
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(),"申请人不能为空！");
		AssertUtils.notNullAndEmpty(param.getMemberNameGuid(),"申请人名称不能为空！");
		
		List<BillRefundDetailDto> details =JSONArray.parseArray(detailsJson,BillRefundDetailDto.class);
		param.setDetails(details);
		AssertUtils.notNullAndEmpty(param.getDetails(), "退款明细项不能为空");
		
		Date payTime = null;
		if(StringUtils.isNotEmpty(param.getRefundTimeStr())) {
			payTime=DateUtils.parseDate(param.getRefundTimeStr(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss,null);
			if(payTime==null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "退费时间格式异常！");
			}
			param.setRefundTime(payTime);
		} 
		
		BillRefundDto rtBillRefundDto = billService.billRefundApply(param);
		return rtBillRefundDto;
	}
	
	/**
	 * 修改退款申请。
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/refund/applyEdit.do" })
	@ResponseBody
	public BillRefundDto billRefundApplyEdit(BillRefundDto param,String detailsJson) {
		AssertUtils.notNullAndEmpty(param.getCode(),"退款编号不能为空！");
		AssertUtils.notNullAndEmpty(param.getBillCode(),"账单编号不能为空！");
		
		AssertUtils.notNullAndEmpty(detailsJson, "退款明细项不能为空");
//		AssertUtils.notNull(param.getUpdateId(),"修改人编号不能为空");
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(),"申请人不能为空！");
		AssertUtils.notNullAndEmpty(param.getMemberNameGuid(),"申请人名称不能为空！");
		AssertUtils.notNullAndEmpty(param.getRtAmount(),"退款额度不能为空！");
		AssertUtils.notNullAndEmpty(param.getRefundType(),"退款类型不能为空！");
//		AssertUtils.notNullAndEmpty(param.getRefundTimeStr(),"退费时间不能为空！");
		
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(),"申请人不能为空！");
		AssertUtils.notNullAndEmpty(param.getMemberNameGuid(),"申请人名称不能为空！");
		
		List<BillRefundDetailDto> details =JSONArray.parseArray(detailsJson,BillRefundDetailDto.class);
		param.setDetails(details);
		AssertUtils.notNullAndEmpty(param.getDetails(), "退款明细项不能为空");
		
		Date payTime = null;
		if(StringUtils.isNotEmpty(param.getRefundTimeStr())) {
			payTime=DateUtils.parseDate(param.getRefundTimeStr(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss,null);
			if(payTime==null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "退费时间格式异常！");
			}
			param.setRefundTime(payTime);
		} 
		
		BillRefundDto rtBillRefundDto = billService.billRefundApplyEdit(param);
		return rtBillRefundDto;
	}
	
	/**
	 * 退款。
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/refund.do" })
	@ResponseBody
	public BillRefundDto billRefund(BillRefundDto param) {
		AssertUtils.notNullAndEmpty(param.getCode(),"退款编号不能为空！");
		AssertUtils.notNullAndEmpty(param.getOptType(),"操作类型不能为空！");
		BillRefundDto rt = billService.billRefund(param);
		return rt;
	}
	
	/**
	 * 退款审核列表.(仅仅退款才审核）
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/checkList.do" })
	@ResponseBody
	public Page<BillOperateDto> billCheckListPage(BillOperateDto param,FindBillOperatePage findPage) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		param.setNotStatus(BillOperateStatus.CANCEL.name());//有效的
//		param.setStatus(BillOperateStatus.INIT.name());
//		param.setCheckStatus(CheckStatus.UNCHECK.name());
		param.setOperateType(BillOperateType.REFUND.name());//退款订单
		findPage.setParam(param);
		
		if(StringUtils.isNotEmpty(param.getStartDateStr())) {
			Date startDate=DateUtils.parseDate(param.getStartDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
			if (startDate == null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "开始日期格式异常！");
			}
			param.setStartDate(startDate);
		}
		if(StringUtils.isNotEmpty(param.getEndDateStr())) {
			Date endDate=DateUtils.parseDate(param.getEndDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
			if (endDate == null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "结束日期格式异常！");
			}
			param.setEndDate(endDate);
		}
	
		Page<BillOperateDto> data= billOperateService.findBillOperatePage(findPage);
		return data;
	}
	
	/**
	 * 退款申请审核.
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/check.do" })
	@ResponseBody
	public  GeneralResponse billCheck(BillOperateDto param) {
		AssertUtils.notNullAndEmpty(param.getCode(),"账单交易编号不能为空！");
		AssertUtils.notNullAndEmpty(param.getCheckStatus(),"审核状态不能为空！");
		AssertUtils.notNullAndEmpty(param.getCheckerNoGuid(),"审核人编号不能为空！");
		AssertUtils.notNullAndEmpty(param.getCheckerNameGuid(),"审核人名称不能为空！");
		
		billService.billRefundCheck(param);
		return GeneralResponse.generateSuccessResponse(param.getCode());
	}
	
	/**
	 * 待处理账单作废。
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/untreatedCancel.do" })
	@ResponseBody
	public GeneralResponse billOperateCancel(BillOperateDto param) {
		AssertUtils.notNullAndEmpty(param.getCode(), "账单交易编号不能为空！");

		billService.billOperateCancel(param);
		return GeneralResponse.generateSuccessResponse(param.getCode());
	}
	 
	/**
	 * 账单作废。
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/cancel.do" })
	@ResponseBody
	public GeneralResponse billCancel(BillDto param,BillOperateDto opt) {
		AssertUtils.notNullAndEmpty(param.getCode(), "账单交易编号不能为空！");
		AssertUtils.notNullAndEmpty(param.getRemark(), "备注不能为空！");
		AssertUtils.notNullAndEmpty(opt.getMemberNameGuid(), "操作人名称不能为空！");
		AssertUtils.notNullAndEmpty(opt.getMemberNoGuid(), "操作人编号不能为空！");
		
		//操作人+操作时间+备注内容
		param.setRemark(opt.getMemberNameGuid()+" "+(DateUtils.formatDate(new Date(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss))+" "+param.getRemark());
		billService.billCancel(param);
		return GeneralResponse.generateSuccessResponse(param.getCode());
	}
	
	
	/**
	 * 支付记录作废
	 * @param param
	 * @param opt
	 * @return
	 */
	@RequestMapping(value = { "/payment/cancel.do" })
	@ResponseBody
	public GeneralResponse paymentCancel(BillPaymentDto param,BillOperateDto opt) {
		//支付记录作废（需求：退款的支付记录不支持作废？有BUG，比如支付了一次后，进行退款，再去把刚开始的支付记录作废，岂不是对不上账，都没收款哪里来的退款）
		AssertUtils.notNullAndEmpty(param.getCode(), "支付记录编号不能为空！");
		AssertUtils.notNullAndEmpty(param.getRemark(), "备注不能为空！");
		AssertUtils.notNullAndEmpty(opt.getMemberNameGuid(), "操作人名称不能为空！");
		AssertUtils.notNullAndEmpty(opt.getMemberNoGuid(), "操作人编号不能为空！");
		
		//操作人+操作时间+备注内容
		param.setRemark(opt.getMemberNameGuid()+" "+(DateUtils.formatDate(new Date(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss))+" "+param.getRemark());
		billService.paymentCancel(param);
		return GeneralResponse.generateSuccessResponse(param.getCode());
	}
	
	/**
	 * 按人统计实收总额，退费总额，欠费总额.
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/payment/sum.do" })
	@ResponseBody
	public GeneralResponse billSum(BillDto param) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(param.getPatientNo(),"患者编号不能为空！");
		param.setStatus(CommonStatus.NORMAL.name());
		BillDto rtBillDto = billService.billSum(param);
		return GeneralResponse.generateSuccessResponse(rtBillDto);
	}
	
	
}
