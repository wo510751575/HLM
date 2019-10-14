package com.ye.business.hx.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IHxPatientDao;
import com.ye.business.hx.dao.IPatientServiceDao;
import com.ye.business.hx.domain.PatientService;
import com.ye.business.hx.dto.FindGuidScheduleLogPage;
import com.ye.business.hx.dto.FindGuidSchedulePage;
import com.ye.business.hx.dto.FindHxPatientPage;
import com.ye.business.hx.dto.FindPatientServiceChoosePage;
import com.ye.business.hx.dto.FindPatientServicePage;
import com.ye.business.hx.dto.FindShopSchedulePage;
import com.ye.business.hx.dto.GuidScheduleDto;
import com.ye.business.hx.dto.GuidScheduleLogDto;
import com.ye.business.hx.dto.HxPatientDto;
import com.ye.business.hx.dto.PatientReservationReturn;
import com.ye.business.hx.dto.PatientServiceChooseDto;
import com.ye.business.hx.dto.PatientServiceDto;
import com.ye.business.hx.dto.ShopScheduleDto;
import com.ye.business.hx.emus.AppointmentStatus;
import com.ye.business.hx.emus.PatientType;
import com.ye.business.hx.emus.ReservationType;
import com.ye.business.hx.emus.ScheduleType;
import com.ye.business.hx.emus.VistitingStatus;
import com.ye.business.hx.service.IGuidScheduleLogService;
import com.ye.business.hx.service.IGuidScheduleService;
import com.ye.business.hx.service.IPatientServiceChooseService;
import com.ye.business.hx.service.IPatientServiceService;
import com.ye.business.hx.service.IShopScheduleService;
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
public class PatientServiceServiceImpl implements IPatientServiceService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientServiceServiceImpl.class);
	
	@Resource
	private IHxPatientDao hxPatientDao;
	
	@Resource
	private IPatientServiceDao patientServiceDao;
	
	// 门诊排班记录
	@Autowired
	private IGuidScheduleService guidScheduleService;
	// 门诊历史排版记录
	@Autowired
	private IGuidScheduleLogService guidScheduleLogService;
	// 门诊班次
	@Autowired
	private IShopScheduleService shopScheduleService;
	// 服务预约
	@Autowired
	private IPatientServiceChooseService patientServiceChooseService;
	
	
	
	
	@Override
	public void addPatientService(
			PatientServiceDto patientServiceDto) throws TsfaServiceException {
		logger.debug("addPatientService(AddPatientService addPatientService={}) - start", patientServiceDto); 

		AssertUtils.notNull(patientServiceDto);
		try {
			PatientService patientService = new PatientService();
			//add数据录入
			patientService.setCode(GUID.getPreUUID());
			patientService.setPatientNo(patientServiceDto.getPatientNo());
			patientService.setPatientName(patientServiceDto.getPatientName());
			patientService.setMobile(patientServiceDto.getMobile());
			patientService.setPatientType(patientServiceDto.getPatientType());
			patientService.setMedicalNo(patientServiceDto.getMedicalNo());
			patientService.setAdvisoryDate(patientServiceDto.getAdvisoryDate());
			patientService.setAdvisoryNo(patientServiceDto.getAdvisoryNo());
			patientService.setAdvisoryName(patientServiceDto.getAdvisoryName());
			patientService.setShopNo(patientServiceDto.getShopNo());
			patientService.setShopName(patientServiceDto.getShopName());
			patientService.setMerchantNo(patientServiceDto.getMerchantNo());
			patientService.setMerchantName(patientServiceDto.getMerchantName());
			patientService.setReservationDate(patientServiceDto.getReservationDate());
			patientService.setReservationDateLen(patientServiceDto.getReservationDateLen());
			patientService.setReservationDoctorNo(patientServiceDto.getReservationDoctorNo());
			patientService.setReservationDoctorName(patientServiceDto.getReservationDoctorName());
			patientService.setReservationType(patientServiceDto.getReservationType());
			patientService.setRegisteredDate(patientServiceDto.getRegisteredDate());
			patientService.setRegisteredDoctorNo(patientServiceDto.getRegisteredDoctorNo());
			patientService.setRegisteredDoctorName(patientServiceDto.getRegisteredDoctorName());
			patientService.setAssistantNo(patientServiceDto.getAssistantNo());
			patientService.setAssistantName(patientServiceDto.getAssistantName());
			patientService.setVistitingStatus(patientServiceDto.getVistitingStatus());
			patientService.setVisitingType(patientServiceDto.getVisitingType());
			patientService.setVisitingDate(patientServiceDto.getVisitingDate());
			patientService.setVisitingAdvisoryDate(patientServiceDto.getVisitingAdvisoryDate());
			patientService.setTriageDate(patientServiceDto.getTriageDate());
			patientService.setReviewReservationDate(patientServiceDto.getReviewReservationDate());
			patientService.setFinishedDate(patientServiceDto.getFinishedDate());
			patientService.setMedicalDate(patientServiceDto.getMedicalDate());
			patientService.setCreateDate(patientServiceDto.getCreateDate());
			patientService.setCreateId(patientServiceDto.getCreateId());
			patientService.setCreateName(patientServiceDto.getCreateName());
			patientService.setRemark(patientServiceDto.getRemark());
			patientService.setRemark2(patientServiceDto.getRemark2());
			patientService.setRemark3(patientServiceDto.getRemark3());
			patientService.setRemark4(patientServiceDto.getRemark4());
			patientService.setUpdateId(patientServiceDto.getUpdateId());
			patientService.setUpdateName(patientServiceDto.getUpdateName());
			patientService.setUpdateDate(patientServiceDto.getUpdateDate());
			patientServiceDao.insert(patientService);
			logger.debug("addPatientService(PatientServiceDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增患者服务（预约/挂号/就诊）信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADD_ERROR,"新增患者服务（预约/挂号/就诊）信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询患者服务（预约/挂号/就诊）信息
	 *
	 * @param findPatientServicePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<PatientServiceDto>  findPatientServices(FindPatientServicePage findPatientServicePage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientServicePage);
		List<PatientServiceDto> returnList=null;
		try {
			returnList = patientServiceDao.findPatientServices(findPatientServicePage);
		} catch (Exception e) {
			logger.error("查找患者服务（预约/挂号/就诊）信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_NOT_EXIST_ERROR,"患者服务（预约/挂号/就诊）信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientService(
			PatientServiceDto patientServiceDto)
			throws TsfaServiceException {
		logger.debug("updatePatientService(PatientServiceDto patientServiceDto={}) - start", patientServiceDto); //$NON-NLS-1$

		AssertUtils.notNull(patientServiceDto);
		AssertUtils.notNullAndEmpty(patientServiceDto.getCode(),"Code不能为空");
		try {
			PatientService patientService = new PatientService();
			//update数据录入
			patientService.setCode(patientServiceDto.getCode());
			patientService.setPatientNo(patientServiceDto.getPatientNo());
			patientService.setPatientName(patientServiceDto.getPatientName());
			patientService.setMobile(patientServiceDto.getMobile());
			patientService.setPatientType(patientServiceDto.getPatientType());
			patientService.setMedicalNo(patientServiceDto.getMedicalNo());
			patientService.setAdvisoryDate(patientServiceDto.getAdvisoryDate());
			patientService.setAdvisoryNo(patientServiceDto.getAdvisoryNo());
			patientService.setAdvisoryName(patientServiceDto.getAdvisoryName());
			patientService.setShopNo(patientServiceDto.getShopNo());
			patientService.setShopName(patientServiceDto.getShopName());
			patientService.setMerchantNo(patientServiceDto.getMerchantNo());
			patientService.setMerchantName(patientServiceDto.getMerchantName());
			patientService.setReservationDate(patientServiceDto.getReservationDate());
			patientService.setReservationDateLen(patientServiceDto.getReservationDateLen());
			patientService.setReservationDoctorNo(patientServiceDto.getReservationDoctorNo());
			patientService.setReservationDoctorName(patientServiceDto.getReservationDoctorName());
			patientService.setReservationType(patientServiceDto.getReservationType());
			patientService.setRegisteredDate(patientServiceDto.getRegisteredDate());
			patientService.setRegisteredDoctorNo(patientServiceDto.getRegisteredDoctorNo());
			patientService.setRegisteredDoctorName(patientServiceDto.getRegisteredDoctorName());
			patientService.setAssistantNo(patientServiceDto.getAssistantNo());
			patientService.setAssistantName(patientServiceDto.getAssistantName());
			patientService.setVistitingStatus(patientServiceDto.getVistitingStatus());
			patientService.setVisitingType(patientServiceDto.getVisitingType());
			patientService.setVisitingDate(patientServiceDto.getVisitingDate());
			patientService.setVisitingAdvisoryDate(patientServiceDto.getVisitingAdvisoryDate());
			patientService.setTriageDate(patientServiceDto.getTriageDate());
			patientService.setReviewReservationDate(patientServiceDto.getReviewReservationDate());
			patientService.setFinishedDate(patientServiceDto.getFinishedDate());
			patientService.setMedicalDate(patientServiceDto.getMedicalDate());
			patientService.setCreateDate(patientServiceDto.getCreateDate());
			patientService.setCreateId(patientServiceDto.getCreateId());
			patientService.setCreateName(patientServiceDto.getCreateName());
			patientService.setRemark(patientServiceDto.getRemark());
			patientService.setRemark2(patientServiceDto.getRemark2());
			patientService.setRemark3(patientServiceDto.getRemark3());
			patientService.setRemark4(patientServiceDto.getRemark4());
			patientService.setUpdateId(patientServiceDto.getUpdateId());
			patientService.setUpdateName(patientServiceDto.getUpdateName());
			patientService.setUpdateDate(patientServiceDto.getUpdateDate());
			patientService.setStatus(patientServiceDto.getStatus());
			AssertUtils.notUpdateMoreThanOne(patientServiceDao.updateByPrimaryKeySelective(patientService));
			logger.debug("updatePatientService(PatientServiceDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("患者服务（预约/挂号/就诊）信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"患者服务（预约/挂号/就诊）信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientServiceDto findPatientService(
			PatientServiceDto patientServiceDto) throws TsfaServiceException {
		logger.debug("findPatientService(FindPatientService findPatientService={}) - start", patientServiceDto); //$NON-NLS-1$

		AssertUtils.notNull(patientServiceDto);
		AssertUtils.notAllNull(patientServiceDto.getCode(),"Code不能为空");
		try {
			PatientService patientService = patientServiceDao.selectByPrimaryKey(patientServiceDto.getCode());
			if(patientService == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_NOT_EXIST_ERROR,"患者服务（预约/挂号/就诊）信息不存在");
			}
			PatientServiceDto findPatientServiceReturn = new PatientServiceDto();
			//find数据录入
			findPatientServiceReturn.setCode(patientService.getCode());
			findPatientServiceReturn.setPatientNo(patientService.getPatientNo());
			findPatientServiceReturn.setPatientName(patientService.getPatientName());
			findPatientServiceReturn.setMobile(patientService.getMobile());
			findPatientServiceReturn.setPatientType(patientService.getPatientType());
			findPatientServiceReturn.setMedicalNo(patientService.getMedicalNo());
			findPatientServiceReturn.setAdvisoryDate(patientService.getAdvisoryDate());
			findPatientServiceReturn.setAdvisoryNo(patientService.getAdvisoryNo());
			findPatientServiceReturn.setAdvisoryName(patientService.getAdvisoryName());
			findPatientServiceReturn.setShopNo(patientService.getShopNo());
			findPatientServiceReturn.setShopName(patientService.getShopName());
			findPatientServiceReturn.setMerchantNo(patientService.getMerchantNo());
			findPatientServiceReturn.setMerchantName(patientService.getMerchantName());
			findPatientServiceReturn.setReservationDate(patientService.getReservationDate());
			findPatientServiceReturn.setReservationDateLen(patientService.getReservationDateLen());
			findPatientServiceReturn.setReservationDoctorNo(patientService.getReservationDoctorNo());
			findPatientServiceReturn.setReservationDoctorName(patientService.getReservationDoctorName());
			findPatientServiceReturn.setReservationType(patientService.getReservationType());
			findPatientServiceReturn.setRegisteredDate(patientServiceDto.getRegisteredDate());
			findPatientServiceReturn.setRegisteredDoctorNo(patientService.getRegisteredDoctorNo());
			findPatientServiceReturn.setRegisteredDoctorName(patientService.getRegisteredDoctorName());
			findPatientServiceReturn.setAssistantNo(patientService.getAssistantNo());
			findPatientServiceReturn.setAssistantName(patientService.getAssistantName());
			findPatientServiceReturn.setVistitingStatus(patientService.getVistitingStatus());
			findPatientServiceReturn.setVisitingType(patientService.getVisitingType());
			findPatientServiceReturn.setVisitingDate(patientService.getVisitingDate());
			findPatientServiceReturn.setVisitingAdvisoryDate(patientService.getVisitingAdvisoryDate());
			findPatientServiceReturn.setTriageDate(patientService.getTriageDate());
			findPatientServiceReturn.setReviewReservationDate(patientService.getReviewReservationDate());
			findPatientServiceReturn.setFinishedDate(patientService.getFinishedDate());
			findPatientServiceReturn.setMedicalDate(patientService.getMedicalDate());
			findPatientServiceReturn.setCreateDate(patientService.getCreateDate());
			findPatientServiceReturn.setCreateId(patientService.getCreateId());
			findPatientServiceReturn.setCreateName(patientService.getCreateName());
			findPatientServiceReturn.setRemark(patientService.getRemark());
			findPatientServiceReturn.setRemark2(patientService.getRemark2());
			findPatientServiceReturn.setRemark3(patientService.getRemark3());
			findPatientServiceReturn.setRemark4(patientService.getRemark4());
			findPatientServiceReturn.setUpdateId(patientService.getUpdateId());
			findPatientServiceReturn.setUpdateName(patientService.getUpdateName());
			findPatientServiceReturn.setUpdateDate(patientService.getUpdateDate());
			findPatientServiceReturn.setStatus(patientService.getStatus());
			logger.debug("findPatientService(PatientServiceDto) - end - return value={}", findPatientServiceReturn); //$NON-NLS-1$
			return findPatientServiceReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找患者服务（预约/挂号/就诊）信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_FIND_ERROR,"查找患者服务（预约/挂号/就诊）信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientServiceDto> findPatientServicePage(
			FindPatientServicePage findPatientServicePage)
			throws TsfaServiceException {
		logger.debug("findPatientServicePage(FindPatientServicePage findPatientServicePage={}) - start", findPatientServicePage); //$NON-NLS-1$

		AssertUtils.notNull(findPatientServicePage);
		List<PatientServiceDto> returnList=null;
		int count = 0;
		try {
			returnList = patientServiceDao.findPatientServicePage(findPatientServicePage);
			count = patientServiceDao.findPatientServicePageCount(findPatientServicePage);
		}  catch (Exception e) {
			logger.error("患者服务（预约/挂号/就诊）信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_FIND_PAGE_ERROR,"患者服务（预约/挂号/就诊）信息不存在错误.！",e);
		}
		Page<PatientServiceDto> returnPage = new Page<PatientServiceDto>(returnList, count, findPatientServicePage);

		logger.debug("findPatientServicePage(FindPatientServicePage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 

	@Override
	public Map<String, Object> findPatientReservationView(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		logger.debug("findPatientReservationView(PatientServiceDto patientServiceDto={}) - start", patientServiceDto); //$NON-NLS-1$

		AssertUtils.notNullAndEmpty(patientServiceDto);
		Map<String, Object> returnMap = new LinkedHashMap<>();
		try {

			// 根据预约日期，转换为对应星期；判断日期是否是当周日期，不是则在班次历史表中查找
			// 获取本周周一日期
			LocalDate monday = DateUtils.toLocalDate(DateUtils.getThisWeekMonday());
			LocalDate reservationday = DateUtils.toLocalDate(patientServiceDto.getReservationDate());
			
			// 根据预约条件获取排班记录
			List<PatientReservationReturn> guidScheduleList = findGuidScheduleList(patientServiceDto, monday, reservationday);
			returnMap.put("guidScheduleList", guidScheduleList);

			// 获取预约记录
			FindPatientServicePage findPatientServicePage = new FindPatientServicePage();
			findPatientServicePage.setParam(patientServiceDto);
			findPatientServicePage.setSortBy("dateAsc"); // 排序
			List<PatientServiceDto> patientServiceDtoList = findPatientServices(findPatientServicePage);
			
			// 查询关联预约项目：二级项目
			if (!patientServiceDtoList.isEmpty() && patientServiceDtoList.size() > 0) {
				List<String> patientReservationCodes = patientServiceDtoList.stream().map(PatientServiceDto::getCode).collect(Collectors.toList());
				FindPatientServiceChoosePage findPatientServiceChoosePage = new FindPatientServiceChoosePage();
				PatientServiceChooseDto patientServiceChooseDto = new PatientServiceChooseDto();
				patientServiceChooseDto.setPatientReservationCodes(patientReservationCodes);
				findPatientServiceChoosePage.setParam(patientServiceChooseDto);
				List<PatientServiceChooseDto> patientServiceChooseDtoList = patientServiceChooseService.findPatientServiceChooses(findPatientServiceChoosePage);
				Map<String, List<PatientServiceChooseDto>> serviceChooseMap = patientServiceChooseDtoList.stream().collect(Collectors.groupingBy(PatientServiceChooseDto::getPatientReservationCode));
				
				// 绑定预约项目
				patientServiceDtoList.forEach(service -> {
					if (serviceChooseMap.containsKey(service.getCode())) {
						service.setServiceChooses(serviceChooseMap.get(service.getCode()));
					}
				});
			}
			// 以预约医生分组
			Map<String, List<PatientServiceDto>> doctorMap = patientServiceDtoList.stream().collect(Collectors.groupingBy(PatientServiceDto::getReservationDoctorNo));
			returnMap.put("doctorMap", doctorMap);

		}  catch (Exception e) {
			logger.error("患者服务（预约/挂号/就诊）信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_FIND_PAGE_ERROR,"患者服务（预约/挂号/就诊）信息不存在错误.！",e);
		}

		logger.debug("findPatientReservationView(PatientServiceDto) - end - return value={}", returnMap); //$NON-NLS-1$
		return  returnMap;
	}


	/**
	 * 根据预约日期查询排班记录
	 * @param patientServiceDto
	 * @param monday
	 * @param reservationday
	 * @return
	 */
	private List<PatientReservationReturn> findGuidScheduleList(PatientServiceDto patientServiceDto, LocalDate monday,
			LocalDate reservationday) {
		List<PatientReservationReturn> returnList;
		if (monday.isAfter(reservationday)) { // 小于本周周一，去历史库中寻找班次记录
			// 从历史排班库中查找排班记录
			FindGuidScheduleLogPage findGuidScheduleLogPage = new FindGuidScheduleLogPage();
			GuidScheduleLogDto guidScheduleLogDto = new GuidScheduleLogDto();
			
			guidScheduleLogDto.setMerchantNo(patientServiceDto.getMerchantNo());
			guidScheduleLogDto.setShopNo(patientServiceDto.getShopNo());
			guidScheduleLogDto.setWorkDateStr(patientServiceDto.getReservationDateStr());
			
			findGuidScheduleLogPage.setParam(guidScheduleLogDto);
			List<GuidScheduleLogDto> guidScheduleLogDtoList = guidScheduleLogService.findGuidScheduleLogs(findGuidScheduleLogPage);
			
			returnList = guidScheduleLogDtoList.stream().map(GuidScheduleLogDto -> buildPatientReservationReturn(GuidScheduleLogDto)).collect(Collectors.toList());
			
		} else {
			// 从当前排班记录中查找
			LocalDate sunday = DateUtils.toLocalDate(DateUtils.getThisWeekSunday());
			
			FindGuidSchedulePage findGuidSchedulePage = new FindGuidSchedulePage();
			GuidScheduleDto guidScheduleDto = new GuidScheduleDto();
			
			// 当前日期大于本周，则取固定排班记录，否则取当周排班记录
			guidScheduleDto.setType(sunday.isBefore(reservationday) ? ScheduleType.FIXED.toString() : ScheduleType.WEEK.toString());
			guidScheduleDto.setMerchantNo(patientServiceDto.getMerchantNo());
			guidScheduleDto.setShopNo(patientServiceDto.getShopNo());
			guidScheduleDto.setDayNum(DateUtils.getWeekDay(patientServiceDto.getReservationDate()));
			
			findGuidSchedulePage.setParam(guidScheduleDto);
			List<GuidScheduleDto> guidScheduleDtoList = guidScheduleService.findGuidSchedules(findGuidSchedulePage);
			// 获取门店班次
			List<String> scheduleCodeList = guidScheduleDtoList.stream().map(GuidScheduleDto::getScheduleCode).collect(Collectors.toList());
			FindShopSchedulePage findShopSchedulePage = new FindShopSchedulePage();
			ShopScheduleDto shopScheduleDto = new ShopScheduleDto();
			shopScheduleDto.setCodes(scheduleCodeList);
			findShopSchedulePage.setParam(shopScheduleDto);
			List<ShopScheduleDto> shopScheduleDtoList = shopScheduleService.findShopSchedules(findShopSchedulePage);
			Map<String, ShopScheduleDto> shopScheduleDtoMap = shopScheduleDtoList.stream().collect(Collectors.toMap(ShopScheduleDto::getCode, ShopScheduleDto -> ShopScheduleDto));
			
			returnList = guidScheduleDtoList.stream().map(GuidScheduleDto -> buildPatientReservationReturn(GuidScheduleDto)).collect(Collectors.toList());
			
			returnList.stream().forEach(patientReservationReturn -> {
				
				if (shopScheduleDtoMap.containsKey(patientReservationReturn.getScheduleCode())) {
					ShopScheduleDto schedule = shopScheduleDtoMap.get(patientReservationReturn.getScheduleCode());
					patientReservationReturn.setScheduleName(schedule.getScheduleName());
					patientReservationReturn.setAptFlag(schedule.getAptFlag());
					patientReservationReturn.setWorkDate(patientServiceDto.getReservationDate());
					patientReservationReturn.setWorkTime(schedule.getWorkTime());
					patientReservationReturn.setOffTime(schedule.getOffTime());
				}
			});
		}
		return returnList;
	}
	
	/**
	 * 组装返回数据：历史排班记录
	 * 
	 * @param guidScheduleLogDto
	 * @return
	 */
	private PatientReservationReturn buildPatientReservationReturn(GuidScheduleLogDto guidScheduleLogDto) {
		PatientReservationReturn patientReservationReturn;
		patientReservationReturn = new PatientReservationReturn();
		patientReservationReturn.setCode(guidScheduleLogDto.getCode());
		 
		patientReservationReturn.setMemberNoGuid(guidScheduleLogDto.getMemberNoGuid());
		patientReservationReturn.setMemberNameGuid(guidScheduleLogDto.getMemberNameGuid());
		patientReservationReturn.setShopNo(guidScheduleLogDto.getShopNo());
		patientReservationReturn.setShopName(guidScheduleLogDto.getShopName());
		patientReservationReturn.setMerchantNo(guidScheduleLogDto.getMerchantNo());
		patientReservationReturn.setMerchantName(guidScheduleLogDto.getMerchantName());
		patientReservationReturn.setWorkDate(guidScheduleLogDto.getWorkDate());
		patientReservationReturn.setDayNum(guidScheduleLogDto.getDayNum());
		patientReservationReturn.setScheduleCode(guidScheduleLogDto.getScheduleCode());
		patientReservationReturn.setScheduleName(guidScheduleLogDto.getScheduleName());
		patientReservationReturn.setWorkTime(guidScheduleLogDto.getWorkTime());
		patientReservationReturn.setOffTime(guidScheduleLogDto.getOffTime());
		patientReservationReturn.setAptFlag(guidScheduleLogDto.getAptFlag());
		
		return patientReservationReturn;
	}
	
	/**
	 * 组装返回数据：当前或未来排版记录
	 * 
	 * @param guidScheduleLogDto
	 * @return
	 */
	private PatientReservationReturn buildPatientReservationReturn(GuidScheduleDto guidScheduleDto) {
		PatientReservationReturn patientReservationReturn;
		patientReservationReturn = new PatientReservationReturn();
		patientReservationReturn.setCode(guidScheduleDto.getCode());
		 
		patientReservationReturn.setMemberNoGuid(guidScheduleDto.getMemberNoGuid());
		patientReservationReturn.setMemberNameGuid(guidScheduleDto.getMemberNameGuid());
		patientReservationReturn.setShopNo(guidScheduleDto.getShopNo());
		patientReservationReturn.setShopName(guidScheduleDto.getShopName());
		patientReservationReturn.setMerchantNo(guidScheduleDto.getMerchantNo());
		patientReservationReturn.setMerchantName(guidScheduleDto.getMerchantName());
		patientReservationReturn.setType(guidScheduleDto.getType());
//		patientReservationReturn.setWorkDate(guidScheduleDto.getWorkDate());
		patientReservationReturn.setDayNum(guidScheduleDto.getDayNum());
		patientReservationReturn.setScheduleCode(guidScheduleDto.getScheduleCode());
//		patientReservationReturn.setScheduleName(guidScheduleDto.getScheduleName());
//		patientReservationReturn.setWorkTime(guidScheduleDto.getWorkTime());
//		patientReservationReturn.setOffTime(guidScheduleDto.getOffTime());
//		patientReservationReturn.setAptFlag(guidScheduleDto.getAptFlag());
		
		return patientReservationReturn;
	}

	
	@Override
	public Page<PatientServiceDto> findPatientReservationPage(FindPatientServicePage findPatientServicePage) throws TsfaServiceException {
		logger.debug("findPatientReservationPage(FindPatientServicePage findPatientServicePage={}) - start", findPatientServicePage); //$NON-NLS-1$

		AssertUtils.notNull(findPatientServicePage);
		Page<PatientServiceDto> returnPage = findPatientServicePage(findPatientServicePage);
		
		
		if (returnPage.getRows() != null && returnPage.getRows().size() > 0) {
			List<String> patientReservationCodes = returnPage.getRows().stream().map(PatientServiceDto::getCode).collect(Collectors.toList());
			List<String> patientCodesList = returnPage.getRows().stream().map(PatientServiceDto::getPatientNo).collect(Collectors.toList());
			
			//1.查出预约项目
			FindPatientServiceChoosePage findPatientServiceChoosePage = new FindPatientServiceChoosePage();
			PatientServiceChooseDto patientServiceChooseDto = new PatientServiceChooseDto();
			patientServiceChooseDto.setPatientReservationCodes(patientReservationCodes);
			findPatientServiceChoosePage.setParam(patientServiceChooseDto);
			List<PatientServiceChooseDto> patientServiceChooseDtoList = patientServiceChooseService.findPatientServiceChooses(findPatientServiceChoosePage);
			Map<String, List<PatientServiceChooseDto>> serviceChooseMap = patientServiceChooseDtoList.stream().collect(Collectors.groupingBy(PatientServiceChooseDto::getPatientReservationCode));
			
			//1.查出患者信息
			FindHxPatientPage paramPage=new FindHxPatientPage();
			HxPatientDto param =new HxPatientDto();
			param.setCodes(patientCodesList);
			paramPage.setParam(param);
			List<HxPatientDto> patientDtos=hxPatientDao.findHxPatients(paramPage);
			Map<String, HxPatientDto> patientMap = patientDtos.stream().collect(Collectors.toMap(HxPatientDto::getCode, (p) -> p)); 
			// 绑定预约项目
			returnPage.getRows().forEach(service -> {
				if (serviceChooseMap.containsKey(service.getCode())) {
					service.setServiceChooses(serviceChooseMap.get(service.getCode()));
				}
				if (patientMap.containsKey(service.getPatientNo())) {
					service.setSex(patientMap.get(service.getPatientNo()).getSex());
				}
			});
		}

		logger.debug("findPatientReservationPage(FindPatientServicePage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 

	@Override
	public String addPatientReservationService(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		logger.debug("addPatientReservationService(addPatientReservationService addPatientService={}) - start", patientServiceDto); 

		AssertUtils.notNull(patientServiceDto);
		try {
			PatientService patientService = new PatientService();
			
			//add数据录入
			patientService.setCode(GUID.getPreUUID());
			patientService.setPatientNo(patientServiceDto.getPatientNo());
			patientService.setPatientName(patientServiceDto.getPatientName());
			patientService.setMobile(patientServiceDto.getMobile());
			patientService.setPatientType(patientServiceDto.getPatientType());
			patientService.setMedicalNo(patientServiceDto.getMedicalNo());
			patientService.setAdvisoryDate(patientServiceDto.getAdvisoryDate());
			patientService.setAdvisoryNo(patientServiceDto.getAdvisoryNo());
			patientService.setAdvisoryName(patientServiceDto.getAdvisoryName());
			patientService.setShopNo(patientServiceDto.getShopNo());
			patientService.setShopName(patientServiceDto.getShopName());
			patientService.setMerchantNo(patientServiceDto.getMerchantNo());
			patientService.setMerchantName(patientServiceDto.getMerchantName());
			patientService.setReservationDate(patientServiceDto.getReservationDate());
			patientService.setReservationDateLen(patientServiceDto.getReservationDateLen());
			patientService.setReservationDoctorNo(patientServiceDto.getReservationDoctorNo());
			patientService.setReservationDoctorName(patientServiceDto.getReservationDoctorName());
			patientService.setReservationType(patientServiceDto.getReservationType());
			patientService.setRegisteredDate(patientServiceDto.getRegisteredDate());
			patientService.setRegisteredDoctorNo(patientServiceDto.getRegisteredDoctorNo());
			patientService.setRegisteredDoctorName(patientServiceDto.getRegisteredDoctorName());
			patientService.setAssistantNo(patientServiceDto.getAssistantNo());
			patientService.setAssistantName(patientServiceDto.getAssistantName());
			patientService.setVistitingStatus(patientServiceDto.getVistitingStatus());
			patientService.setVisitingType(patientServiceDto.getVisitingType());
//			patientService.setVisitingDate(patientServiceDto.getVisitingDate());
//			patientService.setVisitingAdvisoryDate(patientServiceDto.getVisitingAdvisoryDate());
//			patientService.setTriageDate(patientServiceDto.getTriageDate());
//			patientService.setReviewReservationDate(patientServiceDto.getReviewReservationDate());
//			patientService.setFinishedDate(patientServiceDto.getFinishedDate());
//			patientService.setMedicalDate(patientServiceDto.getMedicalDate());
			patientService.setCreateDate(patientServiceDto.getCreateDate());
			patientService.setCreateId(patientServiceDto.getCreateId());
			patientService.setCreateName(patientServiceDto.getCreateName());
			patientService.setRemark(patientServiceDto.getRemark());
			patientService.setRemark2(patientServiceDto.getRemark2());
			patientService.setRemark3(patientServiceDto.getRemark3());
			patientService.setRemark4(patientServiceDto.getRemark4());
			patientService.setUpdateId(patientServiceDto.getUpdateId());
			patientService.setUpdateName(patientServiceDto.getUpdateName());
			patientService.setUpdateDate(patientServiceDto.getUpdateDate());
			patientService.setStatus(patientServiceDto.getStatus());
			patientServiceDao.insert(patientService);
			
			// 添加预约服务项目
			patientServiceDto.getServiceChooses().forEach(choose -> {
				choose.setPatientReservationCode(patientService.getCode());
				choose.setCreateDate(patientService.getCreateDate());
				choose.setCreateId(patientService.getCreateId());
				patientServiceChooseService.addPatientServiceChoose(choose);
			});
			
			//复诊预约
			if (StringUtils.isNotBlank(patientServiceDto.getReviewCode())) {
				PatientService findPatientServiceReturn = patientServiceDao.selectByPrimaryKey(patientServiceDto.getReviewCode());
				if (findPatientServiceReturn != null) {
					
					PatientService updateRecord = new PatientService();
					updateRecord.setCode(findPatientServiceReturn.getCode());
					updateRecord.setReviewReservationDate(patientServiceDto.getUpdateDate());
					updateRecord.setUpdateDate(patientServiceDto.getUpdateDate());
					updateRecord.setUpdateId(patientServiceDto.getUpdateId());
					updateRecord.setUpdateName(patientServiceDto.getUpdateName());
					
					patientServiceDao.updateByPrimaryKeySelective(updateRecord);
				}
			}
			
			logger.debug("addPatientReservationService(PatientServiceDto) - end - return");
			return patientService.getCode(); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增患者服务（预约/挂号/就诊）信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADD_ERROR,"新增患者服务（预约/挂号/就诊）信息错误！",e);
		}
	}
	
	@Override
	public void updatePatientReservationService(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		logger.debug("updatePatientReservationService(PatientServiceDto patientServiceDto={}) - start", patientServiceDto); //$NON-NLS-1$

		AssertUtils.notNull(patientServiceDto);
		AssertUtils.notNullAndEmpty(patientServiceDto.getCode(),"Code不能为空");
		try {
			
			// 获取修改对象
			PatientServiceDto findPatientServiceReturn = findPatientService(patientServiceDto);
			
			AssertUtils.notNull(findPatientServiceReturn);
			// 修改：治疗中、治疗完成状态的不能修改，已取消的修改后状态变成【未确认】
			if (VistitingStatus.TREATMENT.toString().equals(findPatientServiceReturn.getVistitingStatus()) 
					|| VistitingStatus.FINISHED.toString().equals(findPatientServiceReturn.getVistitingStatus())) {
				throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"患者服务（预约）信息更新信息错误：此预约已更改状态，不允许修改");
			}
			
			PatientService patientService = new PatientService();
			//update数据录入
			patientService.setCode(patientServiceDto.getCode());
			patientService.setPatientNo(patientServiceDto.getPatientNo());
			patientService.setPatientName(patientServiceDto.getPatientName());
			patientService.setMobile(patientServiceDto.getMobile());
			patientService.setPatientType(patientServiceDto.getPatientType());
			patientService.setMedicalNo(patientServiceDto.getMedicalNo());
			patientService.setAdvisoryDate(patientServiceDto.getAdvisoryDate());
			patientService.setAdvisoryNo(patientServiceDto.getAdvisoryNo());
			patientService.setAdvisoryName(patientServiceDto.getAdvisoryName());
			patientService.setShopNo(patientServiceDto.getShopNo());
			patientService.setShopName(patientServiceDto.getShopName());
			patientService.setMerchantNo(patientServiceDto.getMerchantNo());
			patientService.setMerchantName(patientServiceDto.getMerchantName());
			patientService.setReservationDate(patientServiceDto.getReservationDate());
			patientService.setReservationDateLen(patientServiceDto.getReservationDateLen());
			patientService.setReservationDoctorNo(patientServiceDto.getReservationDoctorNo());
			patientService.setReservationDoctorName(patientServiceDto.getReservationDoctorName());
			patientService.setReservationType(patientServiceDto.getReservationType());
			patientService.setRegisteredDate(patientServiceDto.getRegisteredDate());
			patientService.setRegisteredDoctorNo(patientServiceDto.getRegisteredDoctorNo());
			patientService.setRegisteredDoctorName(patientServiceDto.getRegisteredDoctorName());
			patientService.setAssistantNo(patientServiceDto.getAssistantNo());
			patientService.setAssistantName(patientServiceDto.getAssistantName());
			patientService.setVistitingStatus(patientServiceDto.getVistitingStatus());
			patientService.setVisitingType(patientServiceDto.getVisitingType());
//			patientService.setVisitingDate(patientServiceDto.getVisitingDate());
//			patientService.setVisitingAdvisoryDate(patientServiceDto.getVisitingAdvisoryDate());
//			patientService.setTriageDate(patientServiceDto.getTriageDate());
//			patientService.setReviewReservationDate(patientServiceDto.getReviewReservationDate());
//			patientService.setFinishedDate(patientServiceDto.getFinishedDate());
//			patientService.setMedicalDate(patientServiceDto.getMedicalDate());
			patientService.setCreateDate(patientServiceDto.getCreateDate());
			patientService.setCreateId(patientServiceDto.getCreateId());
			patientService.setCreateName(patientServiceDto.getCreateName());
			patientService.setRemark(patientServiceDto.getRemark());
			patientService.setRemark2(patientServiceDto.getRemark2());
			patientService.setRemark3(patientServiceDto.getRemark3());
			patientService.setRemark4(patientServiceDto.getRemark4());
			patientService.setUpdateId(patientServiceDto.getUpdateId());
			patientService.setUpdateName(patientServiceDto.getUpdateName());
			patientService.setUpdateDate(patientServiceDto.getUpdateDate());
			
			if (ReservationType.REGISTERED.toString().equals(patientServiceDto.getReservationType())) {
				// 挂号
				// 挂号，判断预约时间是否为空，为空覆盖预约时间
				if (findPatientServiceReturn.getReservationDate() == null) {
					patientService.setReservationDate(patientServiceDto.getRegisteredDate());
					patientService.setReservationDateLen(15);
				} else {
					patientService.setReservationDate(findPatientServiceReturn.getReservationDate());
					patientService.setReservationDateLen(findPatientServiceReturn.getReservationDateLen());
				}
				patientService.setPatientType(PatientType.PT.toString());
				AssertUtils.notUpdateMoreThanOne(patientServiceDao.updateRegisteredByPrimaryKey(patientService));
			} else {
				// 预约
				AssertUtils.notUpdateMoreThanOne(patientServiceDao.updateReservationByPrimaryKey(patientService));
			}
			
			// 删除旧预约服务项目
			patientServiceChooseService.deleteByPatientReservationCode(patientService.getCode());
			
			// 添加预约服务项目
			patientServiceDto.getServiceChooses().forEach(choose -> {
				choose.setPatientReservationCode(patientService.getCode());
				choose.setCreateDate(patientService.getCreateDate());
				choose.setCreateId(patientService.getUpdateId());
				patientServiceChooseService.addPatientServiceChoose(choose);
			});
			
			logger.debug("updatePatientReservationService(PatientServiceDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("患者服务（预约/挂号/就诊）信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"患者服务（预约/挂号/就诊）信息更新信息错误！",e);
		}
	}
	
	@Override
	public void cancelPatientService(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		logger.debug("cancelPatientService(PatientServiceDto patientServiceDto={}) - start", patientServiceDto); //$NON-NLS-1$

		AssertUtils.notNull(patientServiceDto);
		AssertUtils.notNullAndEmpty(patientServiceDto.getCode(),"Code不能为空");
		try {
			
			// 获取修改对象
			PatientServiceDto findPatientServiceReturn = findPatientService(patientServiceDto);
			
			AssertUtils.notNull(findPatientServiceReturn);
			// 取消预约：治疗中、治疗完成状态的预约不能取消，
			if (!VistitingStatus.UNCONFIRMED.toString().equals(findPatientServiceReturn.getVistitingStatus())) {
				throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"患者服务（预约）信息更新信息错误：此预约不允许修改");
			}
			
			PatientService patientService = new PatientService();
			//update数据录入
			patientService.setCode(patientServiceDto.getCode());
			
			patientService.setVistitingStatus(VistitingStatus.CANCEL.toString());
//			patientService.setStatus(AppointmentStatus.CANCEL.name());//2019.07.05
			
			patientService.setUpdateId(patientServiceDto.getUpdateId());
			patientService.setUpdateName(patientServiceDto.getUpdateName());
			patientService.setUpdateDate(new Date());
			
			AssertUtils.notUpdateMoreThanOne(patientServiceDao.updateByPrimaryKeySelective(patientService));
			logger.debug("cancelPatientService(PatientServiceDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("患者服务（预约/挂号/就诊）信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"患者服务（预约/挂号/就诊）信息更新信息错误！",e);
		}
	}

	@Override
	public PatientServiceDto findPatientServiceByCode(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		logger.debug("findPatientServiceByCode(FindPatientService findPatientService={}) - start", patientServiceDto); //$NON-NLS-1$

		AssertUtils.notNull(patientServiceDto);
		AssertUtils.notAllNull(patientServiceDto.getCode(),"Code不能为空");
		
		try {
			PatientServiceDto findPatientServiceReturn = findPatientService(patientServiceDto);
			if(findPatientServiceReturn == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_NOT_EXIST_ERROR,"患者服务（预约/挂号/就诊）信息不存在");
			}

			FindPatientServiceChoosePage findPatientServiceChoosePage = new FindPatientServiceChoosePage();
			PatientServiceChooseDto patientServiceChooseDto = new PatientServiceChooseDto();
			patientServiceChooseDto.setPatientReservationCode(findPatientServiceReturn.getCode());
			findPatientServiceChoosePage.setParam(patientServiceChooseDto);
			List<PatientServiceChooseDto> findPatientServiceChooseReturn = patientServiceChooseService.findPatientServiceChooses(findPatientServiceChoosePage);
			
			// 绑定预约项目
			findPatientServiceReturn.setServiceChooses(findPatientServiceChooseReturn);
			
			logger.debug("findPatientServiceByCode(PatientServiceDto) - end - return value={}", findPatientServiceReturn); //$NON-NLS-1$
			return findPatientServiceReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找患者服务（预约/挂号/就诊）信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_FIND_ERROR,"查找患者服务（预约/挂号/就诊）信息信息错误！",e);
		}


	}
	
	@Override
	public void updateVisitingForAdvisory(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		logger.debug("updateVisitingForAdvisory(PatientServiceDto patientServiceDto={}) - start", patientServiceDto); //$NON-NLS-1$

		try {
			// 获取修改对象
			PatientService patientService = patientServiceDao.selectByPrimaryKey(patientServiceDto.getCode());
			
			AssertUtils.notNull(patientService);
			AssertUtils.isNull(patientService.getVisitingAdvisoryDate(), "接诊失败：该患者已接诊无需重复接诊！");
			AssertUtils.isNull(patientService.getVisitingDate(), "接诊失败：该患者已接诊无需重复接诊！");
			// 此操作人是否属于可修改对象
			AssertUtils.isEqual(patientService.getAdvisoryNo(), patientServiceDto.getUpdateId(), "接诊失败：当前对象无权限进行接诊！");
			// 就诊状态不属于：未确认
			AssertUtils.isEqual(VistitingStatus.UNCONFIRMED.toString(), patientService.getVistitingStatus(), "接诊失败：该患者已接诊无需重复接诊！");
			
			Date now = new Date();
			// 未挂号，默认挂号
			if (ReservationType.RESERVATION.toString().equals(patientService.getReservationType())) {
				patientService.setReservationType(ReservationType.REGISTERED.toString());
				patientService.setRegisteredDate(now);
				// 挂号医生
				patientService.setRegisteredDoctorNo(patientService.getReservationDoctorNo());
				patientService.setRegisteredDoctorName(patientService.getReservationDoctorName());
			}
			
			patientService.setVisitingAdvisoryDate(now); // 写入咨询师接诊时间
			patientService.setVistitingStatus(VistitingStatus.TREATMENT.toString()); // 就诊状态修改为：治疗中
			
			//update数据录入
			patientService.setUpdateId(patientServiceDto.getUpdateId());
			patientService.setUpdateName(patientServiceDto.getUpdateName());
			patientService.setUpdateDate(now);
			
			AssertUtils.notUpdateMoreThanOne(patientServiceDao.updateByPrimaryKey(patientService));
			logger.debug("updateVisitingForAdvisory(PatientServiceDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("咨询师接诊失败！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"咨询师接诊失败！",e);
		}
	}
	
	@Override
	public void updateVisitingForDoctor(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		logger.debug("updateVisitingForDoctor(PatientServiceDto patientServiceDto={}) - start", patientServiceDto); //$NON-NLS-1$
		
		try {
			// 获取修改对象
			PatientService patientService = patientServiceDao.selectByPrimaryKey(patientServiceDto.getCode());
			
			AssertUtils.notNull(patientService);
			AssertUtils.isNull(patientService.getVisitingDate(), "接诊失败：该患者已接诊无需重复接诊！");
			// 此操作人是否属于可修改对象
			AssertUtils.isEqual(patientService.getReservationDoctorNo(), patientServiceDto.getUpdateId(), "接诊失败：当前对象无权限进行接诊！");
			
			// 就诊状态不属于：未确认；治疗中
			boolean flag = !VistitingStatus.UNCONFIRMED.toString().equals(patientService.getVistitingStatus()) 
					&& !VistitingStatus.TREATMENT.toString().equals(patientService.getVistitingStatus());
			AssertUtils.isTrue(!flag, "接诊失败：该患者已接诊无需重复接诊！");
			
			Date now = new Date();
			// 未挂号，默认挂号
			if (ReservationType.RESERVATION.toString().equals(patientService.getReservationType())) {
				patientService.setReservationType(ReservationType.REGISTERED.toString());
				patientService.setRegisteredDate(now);
				// 挂号医生
				patientService.setRegisteredDoctorNo(patientService.getReservationDoctorNo());
				patientService.setRegisteredDoctorName(patientService.getReservationDoctorName());
			}
			
			// 咨询师未接诊：默认接诊
			if (patientService.getVisitingAdvisoryDate() == null) {
				patientService.setVisitingAdvisoryDate(now);
			}
			
			patientService.setVistitingStatus(VistitingStatus.TREATMENT.toString()); // 就诊状态修改为：治疗中
			patientService.setVisitingDate(now); // 医生接诊时间
			
			//update数据录入
			patientService.setUpdateId(patientServiceDto.getUpdateId());
			patientService.setUpdateName(patientServiceDto.getUpdateName());
			patientService.setUpdateDate(now);
			
			AssertUtils.notUpdateMoreThanOne(patientServiceDao.updateByPrimaryKey(patientService));
			logger.debug("updateVisitingForDoctor(PatientServiceDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("医生接诊失败！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"医生接诊失败！",e);
		}
	}
	
	@Override
	public void updateTriageForAdvisory(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		logger.debug("updateTriageForAdvisory(PatientServiceDto patientServiceDto={}) - start", patientServiceDto); //$NON-NLS-1$
		
		try {
			// 获取修改对象
			PatientService patientService = patientServiceDao.selectByPrimaryKey(patientServiceDto.getCode());
			
			AssertUtils.notNull(patientService);
			AssertUtils.isNull(patientService.getVisitingDate(), "分诊失败：该患者医生已接诊！");
			// 此操作人是否属于可修改对象
			AssertUtils.isEqual(patientService.getAdvisoryNo(), patientServiceDto.getUpdateId(), "分诊失败：当前对象无权限进行分诊！");
			
			Date now = new Date();
			
			patientService.setVisitingType(patientServiceDto.getVisitingType()); // 就诊类型
			// 分诊医生
			patientService.setReservationDoctorNo(patientServiceDto.getReservationDoctorNo());
			patientService.setReservationDoctorName(patientServiceDto.getReservationDoctorName());
			patientService.setTriageDate(now); // 分诊时间
			// 助手
			patientService.setAssistantNo(patientServiceDto.getAssistantNo());
			patientService.setAssistantName(patientServiceDto.getAssistantName());
			// 备注
			patientService.setRemark(patientServiceDto.getRemark());
			
			//update数据录入
			patientService.setUpdateId(patientServiceDto.getUpdateId());
			patientService.setUpdateName(patientServiceDto.getUpdateName());
			patientService.setUpdateDate(now);
			
			AssertUtils.notUpdateMoreThanOne(patientServiceDao.updateByPrimaryKey(patientService));
			logger.debug("updateTriageForAdvisory(PatientServiceDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("咨询师分诊失败！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"咨询师分诊失败！",e);
		}
	}
	
	@Override
	public void updateReferralForDoctor(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		logger.debug("updateReferralForDoctor(PatientServiceDto patientServiceDto={}) - start", patientServiceDto); //$NON-NLS-1$
		
		try {
			// 获取修改对象
			PatientService patientService = patientServiceDao.selectByPrimaryKey(patientServiceDto.getCode());
			
			AssertUtils.notNull(patientService);
			// 此操作人是否属于可修改对象
			AssertUtils.isEqual(patientService.getReservationDoctorNo(), patientServiceDto.getUpdateId(), "该预约医生已接诊，不能再转诊！");
			// 治疗完成，不允许转诊
			AssertUtils.isTrue(!VistitingStatus.FINISHED.toString().equals(patientService.getVistitingStatus()), "当前患者已治疗完成！");
			
			Date now = new Date();
			
			patientService.setVisitingType(patientServiceDto.getVisitingType()); // 就诊类型
			// 转诊预约医生
			patientService.setReservationDoctorNo(patientServiceDto.getRegisteredDoctorNo());
			patientService.setReservationDoctorName(patientServiceDto.getRegisteredDoctorName());
			// 预约时间；此预约时间等待产品确定是否可更改
//			patientService.setReservationDate(patientServiceDto.getReservationDate());
			// 挂号医生，如果存在挂号，则修改挂号医生
			if (StringUtils.isNotBlank(patientService.getRegisteredDoctorNo())) {
				patientService.setRegisteredDoctorNo(patientServiceDto.getRegisteredDoctorNo());
				patientService.setRegisteredDoctorName(patientServiceDto.getRegisteredDoctorName());
			}
			// 备注
			patientService.setRemark(patientServiceDto.getRemark());
			
			//update数据录入
			patientService.setUpdateId(patientServiceDto.getUpdateId());
			patientService.setUpdateName(patientServiceDto.getUpdateName());
			patientService.setUpdateDate(now);
			
			AssertUtils.notUpdateMoreThanOne(patientServiceDao.updateByPrimaryKey(patientService));
			logger.debug("updateReferralForDoctor(PatientServiceDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("转诊失败！",e);
			if(e instanceof IllegalArgumentException ) {
				throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR, "转诊失败！" + e.getMessage(), e);
			}else {
				throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"转诊失败！",e);
			}
		}
	}
	
	@Override
	public void updateFinished(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		logger.debug("updateFinished(PatientServiceDto patientServiceDto={}) - start", patientServiceDto); //$NON-NLS-1$
		
		try {
			// 获取修改对象
			PatientService patientService = patientServiceDao.selectByPrimaryKey(patientServiceDto.getCode());
			
			AssertUtils.notNull(patientService);
			AssertUtils.notNull(patientService.getVisitingDate(), "治疗完成失败：当前患者医生还未接诊！");
			// 此操作人是否属于可修改对象
			boolean flag = StringUtils.equals(patientService.getReservationDoctorNo(), patientServiceDto.getUpdateId()) 
					|| StringUtils.equals(patientService.getAdvisoryNo(), patientServiceDto.getUpdateId());
			AssertUtils.isTrue(flag, "治疗完成失败：当前对象无权限进行操作！");
			// 不属于治疗中，全部不允许操作
			AssertUtils.isEqual(patientService.getVistitingStatus(), VistitingStatus.TREATMENT.toString(), "治疗完成失败：当前患者不属于治疗中！");
			
			Date now = new Date();
			
			patientService.setVistitingStatus(VistitingStatus.FINISHED.toString()); // 就诊状态：治疗完成
			patientService.setFinishedDate(now); // 治疗完成时间
			
			//update数据录入
			patientService.setUpdateId(patientServiceDto.getUpdateId());
			patientService.setUpdateName(patientServiceDto.getUpdateName());
			patientService.setUpdateDate(now);
			
			AssertUtils.notUpdateMoreThanOne(patientServiceDao.updateByPrimaryKey(patientService));
			logger.debug("updateFinished(PatientServiceDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("治疗完成失败！",e);
			if(e instanceof IllegalArgumentException ) {
				throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR, "失败 " + e.getMessage(), e);
			}else {
				throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"治疗完成失败！",e);
			}
		}
	}
	
	@Override
	public PatientServiceDto getPatientServiceDtoByExample(FindPatientServicePage findPatientServicePage)
			throws TsfaServiceException {
		AssertUtils.notNull(findPatientServicePage);
		PatientServiceDto rs = null;
		try {
			rs = patientServiceDao.getPatientServiceByExample(findPatientServicePage);
		} catch (Exception e) {
			logger.error("查找患者服务（预约/挂号/就诊）信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_NOT_EXIST_ERROR,"患者服务（预约/挂号/就诊）信息不存在");
		}
		return rs;
	}


	@Override
	public void editApplyByPhone(PatientServiceDto patientServiceDto) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(patientServiceDto);
		AssertUtils.notNullAndEmpty(patientServiceDto.getCode(), "预约编号不能为空");
		if(null!=patientServiceDto.getReservationDate()) {
			// 预约日期是否小于今天
			AssertUtils.isTrue(!LocalDate.now().isAfter(DateUtils.toLocalDate(patientServiceDto.getReservationDate())), "预约日期不能小于今天");
		}
		PatientServiceDto findPatientServiceReturn = this.findPatientService(patientServiceDto);
		
		AssertUtils.notNull(findPatientServiceReturn,"预约信息不存在");
		// 修改：治疗中、治疗完成状态的不能修改，已取消的修改后状态变成【未确认】
		if (VistitingStatus.TREATMENT.toString().equals(findPatientServiceReturn.getVistitingStatus()) 
				|| VistitingStatus.FINISHED.toString().equals(findPatientServiceReturn.getVistitingStatus())) {
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_UPDATE_ERROR,"患者服务（预约）信息更新信息错误：此预约已更改状态，不允许修改");
		}
	
		PatientService patientService = new PatientService();
		//update数据录入
		patientService.setCode(patientServiceDto.getCode());
		patientService.setReservationDate(patientServiceDto.getReservationDate());
		patientService.setReservationDoctorNo(patientServiceDto.getReservationDoctorNo());
		patientService.setReservationDoctorName(patientServiceDto.getReservationDoctorName());
		patientService.setVistitingStatus(VistitingStatus.UNCONFIRMED.name());//修改后变成【未确认】
		patientService.setStatus(AppointmentStatus.UNCONFIRM.name());//修改后变成【未确认】
		patientService.setRemark(patientServiceDto.getRemark()); 
		patientService.setUpdateId(patientServiceDto.getUpdateId());
		patientService.setUpdateName(patientServiceDto.getUpdateName());
		patientService.setUpdateDate(new Date());
		// 1.修改预约信息
		patientServiceDao.updateByPrimaryKeySelective(patientService);
		if (null != patientServiceDto.getServiceChooses()) {
			boolean flag = patientServiceDto.getServiceChooses().stream().anyMatch(choose -> {
				return (StringUtils.isBlank(choose.getProjectCode())||StringUtils.isBlank(choose.getProjectName())||StringUtils.isBlank( choose.getProjectPropertyCode())||StringUtils.isBlank(choose.getProjectPropertyName()));
			});
			AssertUtils.isTrue(!flag, "选择服务信息不完整");
			// 2.1删除旧预约服务项目
			patientServiceChooseService.deleteByPatientReservationCode(patientServiceDto.getCode());
			// 2.2添加预约服务项目
			patientServiceDto.getServiceChooses().forEach(choose -> {
				choose.setPatientReservationCode(patientService.getCode());
				choose.setCreateDate(patientService.getCreateDate());
				choose.setCreateId(patientService.getUpdateId());
				patientServiceChooseService.addPatientServiceChoose(choose);
			});
		}
	}
	
}
