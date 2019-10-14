package com.lj.business.api.controller.hx;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.UserHessianService;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.constant.HxConstant;
import com.ye.business.hx.dto.FindPatientServicePage;
import com.ye.business.hx.dto.HxPatientDto;
import com.ye.business.hx.dto.PatientServiceDto;
import com.ye.business.hx.emus.AppointmentStatus;
import com.ye.business.hx.emus.PatientType;
import com.ye.business.hx.emus.ReservationType;
import com.ye.business.hx.emus.VistitingStatus;
import com.ye.business.hx.emus.VistitingType;
import com.ye.business.hx.service.IHxPatientService;
import com.ye.business.hx.service.IPatientServiceService;

/**
 * 焕新：预约/挂号/就诊
 * 
 * @author sjiying
 *
 */
@Controller
@RequestMapping(value = "/hx/ps/")
public class HxPatientServiceAction extends Action {

	// 服务预约/挂号/就诊
	@Autowired
	private IPatientServiceService patientServiceService;

	@Resource
	private IGuidMemberService guidMemberService; // 导购服务
	
	@Resource
	private IHxPatientService hxPatientService;
	@Autowired
	private UserHessianService userHessianService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	/**
	 * 查询预约视图列表
	 * 
	 * @param patientServiceDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findPatientReservationView.do", produces = "application/json;charset=UTF-8")
	public Map<String, Object> findPatientReservationView(PatientServiceDto patientServiceDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientServiceDto);
		AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDateStr(), "预约日期不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getMerchantNo(), "商户编号不能为空");
//		AssertUtils.notNullAndEmpty(patientServiceDto.getShopNo(), "门店编号不能为空");
		
		try {
			patientServiceDto.setReservationDate(DateUtils.parseDate(patientServiceDto.getReservationDateStr(), DateUtils.PATTERN_yyyy_MM_dd));
		} catch (ParseException e) {
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADD_ERROR, "预约时间格式不正确");
		}
		
		// 去掉取消状态的
//		patientServiceDto.setVistitingStatusNot(VistitingStatus.CANCEL.toString());

		return this.patientServiceService.findPatientReservationView(patientServiceDto);
	}

	/**
	 * 方法说明：预约列表
	 * 
	 * @param findPatientServicePage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findPatientReservationPage.do", produces = "application/json;charset=UTF-8")
	public Page<PatientServiceDto> findPatientReservationPage(PatientServiceDto param,FindPatientServicePage findPatientServicePage) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(findPatientServicePage);
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号不能为空");
		findPatientServicePage.setParam(param);
//		AssertUtils.notNullAndEmpty(findPatientServicePage.getParam().getShopNo(), "门店编号不能为空");

		findPatientServicePage.setSortBy("dateDesc"); // 排序dateAsc
		
		
		// 去掉取消状态的
//		findPatientServicePage.getParam().setVistitingStatusNot(VistitingStatus.CANCEL.toString());

		return this.patientServiceService.findPatientReservationPage(findPatientServicePage);
	}
	
	/**
	 * 预约详细
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "info.do", produces = "application/json;charset=UTF-8")
	public PatientServiceDto info(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(patientServiceDto);
		AssertUtils.notNullAndEmpty(patientServiceDto.getCode(), "预约code不能为空");
		PatientServiceDto rt = patientServiceService.findPatientServiceByCode(patientServiceDto);
		
		HxPatientDto patientParam = new HxPatientDto();
		patientParam.setCode(rt.getPatientNo());
		HxPatientDto p = hxPatientService.findHxPatient(patientParam);//患者信息查询并设置
		rt.setAge(p.getAge());
		rt.setSex(p.getSex());
		
		return rt;
	}

	/**
	 * 添加患者预约
	 * 
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "addPatientReservation.do", produces = "application/json;charset=UTF-8")
	public String addPatientReservation(PatientServiceDto patientServiceDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientServiceDto);
		// PC端预约默认是客户确认状态 2019.07.05
		if (StringUtils.isEmpty(patientServiceDto.getStatus())) {
			patientServiceDto.setStatus(AppointmentStatus.CONFIRM.name());
		}
//		AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDate(), "预约日期不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getMerchantNo(), "商户编号不能为空");
//		AssertUtils.notNullAndEmpty(patientServiceDto.getShopNo(), "门店编号不能为空");

		AssertUtils.notNullAndEmpty(patientServiceDto.getPatientNo(), "患者编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getPatientName(), "患者名称不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getMobile(), "手机号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getPatientType(), "患者类型不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getMedicalNo(), "病历编号不能为空");
//		AssertUtils.notNullAndEmpty(patientServiceDto.getAdvisoryNo(), "咨询师编号不能为空"); // 咨询师非必填 2019.07.05 modify
//		AssertUtils.notNullAndEmpty(patientServiceDto.getAdvisoryName(), "咨询师名称不能为空");
		
//		AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDoctorNo(), "预约医生编号不能为空");// 医生非必填 2019.07.05 modify
//		AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDoctorName(), "预约医生名称不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getReservationType(), "预约类型不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getVistitingStatus(), "就诊状态不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getVisitingType(), "就诊类型不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getCreateId(), "创建人编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getCreateName(), "创建人名称不能为空");

		// 直接挂号
		if (ReservationType.REGISTERED.toString().equals(patientServiceDto.getReservationType())) {
			AssertUtils.notNullAndEmpty(patientServiceDto.getRegisteredDate(), "挂号时间不能为空");
			AssertUtils.notNullAndEmpty(patientServiceDto.getRegisteredDoctorNo(), "挂号医生编号不能为空");
			AssertUtils.notNullAndEmpty(patientServiceDto.getRegisteredDoctorName(), "挂号医生名称不能为空");
			
			patientServiceDto.setReservationDate(new Date());
		}
		
		if (PatientType.PT.toString().equals(patientServiceDto.getPatientType())) {
			// 普通类型，必须传递预约时间
			AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDate(), "预约时间不能为空");
			AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDateLen(), "预约时长不能为空");
			// 预约日期是否小于今天
			AssertUtils.isTrue(!LocalDate.now().isAfter(DateUtils.toLocalDate(patientServiceDto.getReservationDate())), "预约日期不能小于今天");
		}
		
		AssertUtils.notNullAndEmpty(patientServiceDto.getServiceChooses(), "预约服务不能为空");

		boolean flag = patientServiceDto.getServiceChooses().stream().anyMatch(choose -> {
			return StringUtils.isAnyBlank(choose.getProjectCode(), choose.getProjectName(), choose.getProjectPropertyCode(), choose.getProjectPropertyName());
		});
		AssertUtils.isTrue(!flag, "选择服务信息不能为空");

		if (StringUtils.isEmpty(patientServiceDto.getUpdateId())) {
			patientServiceDto.setUpdateId(patientServiceDto.getCreateId());
			patientServiceDto.setUpdateName(patientServiceDto.getCreateName());
		}

		// 初始化默认数据
		Date now = new Date();
		patientServiceDto.setCreateDate(now);
		patientServiceDto.setUpdateDate(now);

		String rs = this.patientServiceService.addPatientReservationService(patientServiceDto);

		return rs;
	}

	/**
	 * 修改患者预约
	 * 
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "updatePatientReservation.do", produces = "application/json;charset=UTF-8")
	public String updatePatientReservation(PatientServiceDto patientServiceDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientServiceDto);
		AssertUtils.notNullAndEmpty(patientServiceDto.getCode(), "预约编号不能为空");
		
		AssertUtils.notNullAndEmpty(patientServiceDto.getReservationType(), "预约类型不能为空");

		AssertUtils.notNullAndEmpty(patientServiceDto.getAdvisoryNo(), "咨询师编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getAdvisoryName(), "咨询师名称不能为空");
		
		// 修改挂号
		if (ReservationType.REGISTERED.toString().equals(patientServiceDto.getReservationType())) {
			AssertUtils.notNullAndEmpty(patientServiceDto.getRegisteredDate(), "挂号时间不能为空");
			AssertUtils.notNullAndEmpty(patientServiceDto.getRegisteredDoctorNo(), "挂号医生编号不能为空");
			AssertUtils.notNullAndEmpty(patientServiceDto.getRegisteredDoctorName(), "挂号医生名称不能为空");
			
			// 覆盖预约医生
			patientServiceDto.setReservationDoctorNo(patientServiceDto.getRegisteredDoctorNo());
			patientServiceDto.setReservationDoctorName(patientServiceDto.getRegisteredDoctorName());
		} else {
			// 默认为预约
//			AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDate(), "预约日期不能为空");
			AssertUtils.notNullAndEmpty(patientServiceDto.getPatientType(), "患者类型不能为空");
			AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDoctorNo(), "预约医生编号不能为空");
			AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDoctorName(), "预约医生名称不能为空");
			AssertUtils.notNullAndEmpty(patientServiceDto.getVistitingStatus(), "就诊状态不能为空");
			AssertUtils.notNullAndEmpty(patientServiceDto.getVisitingType(), "就诊类型不能为空");
			
			if (PatientType.PT.toString().equals(patientServiceDto.getPatientType())) {
				// 普通类型，必须传递预约时间
				AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDate(), "预约时间不能为空");
				AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDateLen(), "预约时长不能为空");
				// 预约日期是否小于今天
				AssertUtils.isTrue(!LocalDate.now().isAfter(DateUtils.toLocalDate(patientServiceDto.getReservationDate())), "预约日期不能小于今天");
//			} else {
//				// 待确认：待定类型用户预约时间是否需要清空
//				patientServiceDto.setReservationDate(null);
//				patientServiceDto.setReservationDateLen(null);
			}
		}
		
		AssertUtils.notNullAndEmpty(patientServiceDto.getUpdateId(), "修改人编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getUpdateName(), "修改人名称不能为空");

		AssertUtils.notNullAndEmpty(patientServiceDto.getServiceChooses(), "预约服务不能为空");

		boolean flag = patientServiceDto.getServiceChooses().stream().anyMatch(choose -> {
			return StringUtils.isAnyBlank(choose.getProjectCode(), choose.getProjectName(), choose.getProjectPropertyCode(), choose.getProjectPropertyName());
		});
		AssertUtils.isTrue(!flag, "选择服务信息不能为空");

		// 初始化默认数据
		Date now = new Date();
		patientServiceDto.setUpdateDate(now);
		patientServiceDto.setVistitingStatus(VistitingStatus.UNCONFIRMED.toString());

		this.patientServiceService.updatePatientReservationService(patientServiceDto);

		return patientServiceDto.getCode();
	}
	
	/**
	 * 取消患者预约
	 * 
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "cancelPatientService.do", produces = "application/json;charset=UTF-8")
	public String cancelPatientService(PatientServiceDto patientServiceDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientServiceDto);
		AssertUtils.notNullAndEmpty(patientServiceDto.getCode(), "预约编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getReservationType(), "预约类型不能为空"); 

		AssertUtils.notNullAndEmpty(patientServiceDto.getUpdateId(), "修改人编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getUpdateName(), "修改人名称不能为空");

	 
		this.patientServiceService.cancelPatientService(patientServiceDto);

		return patientServiceDto.getCode();
	}
	
	/**
	 * 方法说明：今日工作：咨询师|医生
	 * 
	 * @param findPatientServicePage 
	 * 		operatioinType：操作类型：RoleEnName枚举  医生 SYS_SHOP_DOCTOR 咨询师 SYS_SHOP_ADVISORY 护士 SYS_SHOP_NURSE
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findWorkTodayPage.do", produces = "application/json;charset=UTF-8")
	public Page<PatientServiceDto> findWorkTodayPage(PatientServiceDto param,FindPatientServicePage findPatientServicePage) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(findPatientServicePage);
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号不能为空");
//		AssertUtils.notNullAndEmpty(findPatientServicePage.getParam().getShopNo(), "门店编号不能为空");
		AssertUtils.notNullAndEmpty(param.getReservationDateStr(), "预约日期不能为空");
		AssertUtils.notNullAndEmpty(param.getUpdateId(), "操作人不能为空");
		
		findPatientServicePage.setParam(param);
		
		try {
			DateUtils.parseDate(findPatientServicePage.getParam().getReservationDateStr(), DateUtils.PATTERN_yyyy_MM_dd);
		} catch (ParseException e) {
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADD_ERROR, "预约时间格式不正确");
		}

		findPatientServicePage.setSortBy("dateAsc"); // 排序
		
		// 客户确认状态的 lhy 2019.07.05
//		param.setStatus(AppointmentStatus.CONFIRM.name());
		
		findPatientServicePage.getParam().setVistitingStatusNot(VistitingStatus.CANCEL.toString());
		findPatientServicePage.getParam().setPatientType(PatientType.PT.toString());

		return this.patientServiceService.findPatientReservationPage(findPatientServicePage);
	}
	
	/**
	 * 患者服务操作：咨询师接诊
	 * 
	 * @param param
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "visitingForAdvisory.do", produces = "application/json;charset=UTF-8")
	public String visitingForAdvisory(PatientServiceDto param) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "预约编号不能为空");
		AssertUtils.notNullAndEmpty(param.getUpdateId(), "修改人编号不能为空");
		AssertUtils.notNullAndEmpty(param.getUpdateName(), "修改人名称不能为空");
		
		patientServiceService.updateVisitingForAdvisory(param);
		
		return param.getCode();
	}
	
	/**
	 * 患者服务操作：医生接诊
	 * 
	 * @param param
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "visitingForDoctor.do", produces = "application/json;charset=UTF-8")
	public String visitingForDoctor(PatientServiceDto param) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "预约编号不能为空");
		AssertUtils.notNullAndEmpty(param.getUpdateId(), "修改人编号不能为空");
		AssertUtils.notNullAndEmpty(param.getUpdateName(), "修改人名称不能为空");
		
		patientServiceService.updateVisitingForDoctor(param);
		
		return param.getCode();
	}
	
	/**
	 * 患者服务操作：分诊
	 * 
	 * @param patientServiceDto
	 * 		
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "editByTriage.do", produces = "application/json;charset=UTF-8")
	public String editByTriage(PatientServiceDto patientServiceDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientServiceDto);
		AssertUtils.notNullAndEmpty(patientServiceDto.getCode(), "预约编号不能为空");
		
		AssertUtils.notNullAndEmpty(patientServiceDto.getUpdateId(), "修改人编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getUpdateName(), "修改人名称不能为空");
		
		AssertUtils.notNullAndEmpty(patientServiceDto.getVisitingType(), "就诊类型不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDoctorNo(), "预约医生编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDoctorName(), "预约医生名称不能为空");

		patientServiceService.updateTriageForAdvisory(patientServiceDto);

		return patientServiceDto.getCode();
	}
	
	/**
	 * 患者服务操作：治疗完成
	 * 
	 * @param patientServiceDto
	 * 		
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "editByFinished.do", produces = "application/json;charset=UTF-8")
	public String editByFinished(PatientServiceDto patientServiceDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientServiceDto);
		AssertUtils.notNullAndEmpty(patientServiceDto.getCode(), "预约编号不能为空");
		
		AssertUtils.notNullAndEmpty(patientServiceDto.getUpdateId(), "修改人编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getUpdateName(), "修改人名称不能为空");
		
		patientServiceService.updateFinished(patientServiceDto);

		return patientServiceDto.getCode();
	}
	
	/**
	 * 患者服务操作：转诊
	 * 
	 * @param patientServiceDto
	 * 		
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "editByReferral.do", produces = "application/json;charset=UTF-8")
	public String editByReferral(PatientServiceDto patientServiceDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientServiceDto);
		AssertUtils.notNullAndEmpty(patientServiceDto.getCode(), "预约编号不能为空");
		
		AssertUtils.notNullAndEmpty(patientServiceDto.getUpdateId(), "修改人编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getUpdateName(), "修改人名称不能为空");
		
		AssertUtils.notNullAndEmpty(patientServiceDto.getVisitingType(), "就诊类型不能为空");

		AssertUtils.notNullAndEmpty(patientServiceDto.getRegisteredDoctorNo(), "挂号医生编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getRegisteredDoctorName(), "挂号医生名称不能为空");
		
		patientServiceService.updateReferralForDoctor(patientServiceDto);

		return patientServiceDto.getCode();
	}
	
	/**
	 * 获取最新预约信息
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "infoByUpToDate.do", produces = "application/json;charset=UTF-8")
	public PatientServiceDto infoByUpToDate(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(patientServiceDto);
		AssertUtils.notNullAndEmpty(patientServiceDto.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getPatientNo(), "患者编号不能为空");
		
		FindPatientServicePage findPatientServicePage = new FindPatientServicePage();
		findPatientServicePage.setParam(patientServiceDto);
		findPatientServicePage.setSortBy("dateDesc"); // 排序
		
		PatientServiceDto rs = patientServiceService.getPatientServiceDtoByExample(findPatientServicePage);
		
		// 返回null时，框架不转换，作此处理
		if (rs == null) rs = new PatientServiceDto();
		
		return rs;
	}
	
	/**
	 * 手机端预约
	 * 
	 * @return
	 * @author lhy 2019.07.05
	 */
	@ResponseBody
	@RequestMapping(value = "apply.do", produces = "application/json;charset=UTF-8")
	public String appointmentByPhone(PatientServiceDto patientServiceDto) {
		AssertUtils.notNullAndEmpty(patientServiceDto.getPatientNo(), "患者编号不能为空");
		//患者类型默认：
		patientServiceDto.setPatientType(PatientType.PT.name());
		//就诊类型默认：
		patientServiceDto.setVisitingType(VistitingType.NEWDIAGNOSIS.name());
		//预约类型默认：
		patientServiceDto.setReservationType(ReservationType.RESERVATION.name());
		//就诊状态默认：getVistitingStatus
		patientServiceDto.setVistitingStatus(VistitingStatus.UNCONFIRMED.name());
		//预约时长默认：
		patientServiceDto.setReservationDateLen(15);
		HxPatientDto patientParam = new HxPatientDto();
		patientParam.setCode(patientServiceDto.getPatientNo());
		HxPatientDto p = hxPatientService.findHxPatient(patientParam);//患者信息查询并设置
		patientServiceDto.setPatientName(p.getName());
		patientServiceDto.setMobile(p.getPhone());
		patientServiceDto.setMedicalNo(p.getCaseNo());
		patientServiceDto.setMerchantName(p.getMerchantName());
		//移动端预约都是待确认状态
		patientServiceDto.setStatus(AppointmentStatus.UNCONFIRM.name());
		
		//自助预约时，导购名称。需要查一次
		if (StringUtils.isEmpty(patientServiceDto.getCreateName())
				&& StringUtils.isNotEmpty(patientServiceDto.getCreateId())) {
			User user = userHessianService.findByUserId(patientServiceDto.getCreateId());
			patientServiceDto.setCreateName(user.getName());
		}
// 以下由前端填
//		AssertUtils.notNullAndEmpty(patientServiceDto.getPatientNo(), "患者编号不能为空");
//		AssertUtils.notNullAndEmpty(patientServiceDto.getMerchantNo(), "商户编号不能为空");
//		AssertUtils.notNullAndEmpty(patientServiceDto.getAdvisoryNo(), "咨询师编号不能为空");
//		AssertUtils.notNullAndEmpty(patientServiceDto.getAdvisoryName(), "咨询师名称不能为空");
//		
//		AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDoctorNo(), "预约医生编号不能为空");
//		AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDoctorName(), "预约医生名称不能为空");
//		AssertUtils.notNullAndEmpty(patientServiceDto.getCreateId(), "创建人编号不能为空");
//		AssertUtils.notNullAndEmpty(patientServiceDto.getCreateName(), "创建人名称不能为空");
		//预约服务
//		AssertUtils.notNullAndEmpty(patientServiceDto.getReservationDate(), "预约时间不能为空");
		
		
		return addPatientReservation(patientServiceDto);
	}
	
	
	/**
	 * 客户确认预约 & 重新预约
	 * @param patientServiceDto
	 * @return
	 * @author lhy 2019.07.05
	 */
	@ResponseBody
	@RequestMapping(value = "confirm.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse confirm(PatientServiceDto patientServiceDto) {
		AssertUtils.notNullAndEmpty(patientServiceDto.getCode(), "预约code不能为空");
		AssertUtils.notNullAndEmpty(patientServiceDto.getStatus(), "预约状态不能为空");
		PatientServiceDto hxPatientDto = new PatientServiceDto();
		// 获取修改对象
		PatientServiceDto findPatientServiceReturn = patientServiceService.findPatientService(patientServiceDto);
		AssertUtils.notNull(findPatientServiceReturn);
		
		if (AppointmentStatus.CONFIRM.name().equals(patientServiceDto.getStatus())) {
			//确认预约（非未确认状态不可确认）
			if (!AppointmentStatus.UNCONFIRM.name().equals(findPatientServiceReturn.getStatus())) {
				throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"该预约已确认");
			}
			hxPatientDto.setStatus(AppointmentStatus.CONFIRM.name());
		
			hxPatientDto.setCode(patientServiceDto.getCode());
			patientServiceService.updatePatientService(hxPatientDto);
		} 
//		else if (AppointmentStatus.CANCEL.name().equals(patientServiceDto.getStatus())) {
//			//取消预约 则检测 就诊状态
//			patientServiceService.cancelPatientService(patientServiceDto);
//			
//		} 
		else if (AppointmentStatus.UNCONFIRM.name().equals(patientServiceDto.getStatus())) {
			//重新预约 (非取消状态不可重新预约)
			if (!VistitingStatus.CANCEL.toString().equals(findPatientServiceReturn.getVistitingStatus())) {
				throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"非取消状态不可重新预约");
			}
			hxPatientDto.setStatus(AppointmentStatus.UNCONFIRM.name());
			hxPatientDto.setVistitingStatus(VistitingStatus.UNCONFIRMED.name());
			
			hxPatientDto.setCode(patientServiceDto.getCode());
			patientServiceService.updatePatientService(hxPatientDto);
		}
		
		
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 手机端修改预约
	 * 
	 * @return
	 * @author lhy 2019.07.09
	 */
	@ResponseBody
	@RequestMapping(value = "editApply.do", produces = "application/json;charset=UTF-8")
	public String editApplyByPhone(PatientServiceDto patientServiceDto) {
		patientServiceService.editApplyByPhone(patientServiceDto);
		return patientServiceDto.getCode();
	}
	
	/**
	 * 预约管理H5，确认预约页url。<p>
	 * key和前端一致，多个页面多返回多条数据
	 * @return
	 * @author lhy 2019.07.17
	 */
	@RequestMapping(value = { "/h5url.do" })
	@ResponseBody
	public Map<String, String> findH5url() {
		Map<String,String> ccMap =  localCacheSystemParams.getSystemParamGroup(HxConstant.systemAliasName, HxConstant.GROUP_PATIENT_SERVICE_URL);
		
		return ccMap;
	}
}
