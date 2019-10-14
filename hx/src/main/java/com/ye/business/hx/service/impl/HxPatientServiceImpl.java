package com.ye.business.hx.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IHxPatientDao;
import com.ye.business.hx.dao.IPatientMedicalDao;
import com.ye.business.hx.dao.IPatientServiceAdvisoryDao;
import com.ye.business.hx.dao.IPatientServiceChooseDao;
import com.ye.business.hx.dao.IPatientServiceDao;
import com.ye.business.hx.domain.HxPatient;
import com.ye.business.hx.domain.PatientMedical;
import com.ye.business.hx.domain.PatientServiceChoose;
import com.ye.business.hx.dto.FindHxPatientPage;
import com.ye.business.hx.dto.HxPatientDto;
import com.ye.business.hx.dto.PatientServiceAdvisoryDto;
import com.ye.business.hx.dto.PatientServiceDto;
import com.ye.business.hx.dto.params.PatientParams;
import com.ye.business.hx.service.IHxPatientService;
import com.ye.business.hx.util.FormatUtil;
import com.ye.business.hx.util.GenerateNo;
import com.ye.business.hx.util.ObjectUtil;

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
 *         CreateDate: 2019.02.19
 */
@Service
public class HxPatientServiceImpl implements IHxPatientService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory
			.getLogger(HxPatientServiceImpl.class);

	@Resource
	private IHxPatientDao hxPatientDao;

	@Resource
	private IPatientServiceAdvisoryDao patientServiceAdvisoryDao;

	@Resource
	private IPatientServiceDao patientServiceDao;

	@Resource
	private IPatientServiceChooseDao patientServiceChooseDao;

	@Resource
	private IPatientMedicalDao patientMedicalDao;

	@Override
	public void addHxPatient(HxPatientDto hxPatientDto)
			throws TsfaServiceException {
		logger.debug("addHxPatient(AddHxPatient addHxPatient={}) - start",
				hxPatientDto);

		AssertUtils.notNull(hxPatientDto);
		try {
			HxPatient hxPatient = new HxPatient();
			// add数据录入
			hxPatient.setCode(GUID.getPreUUID());
			hxPatient.setShopNo(hxPatientDto.getShopNo());
			hxPatient.setShopName(hxPatientDto.getShopName());
			hxPatient.setMerchantNo(hxPatientDto.getMerchantNo());
			hxPatient.setMerchantName(hxPatientDto.getMerchantName());
			hxPatient.setName(hxPatientDto.getName());
			hxPatient.setSex(hxPatientDto.getSex());
			hxPatient.setType(hxPatientDto.getType());
			hxPatient.setCaseNo(hxPatientDto.getCaseNo());
			hxPatient.setBirthDate(hxPatientDto.getBirthDate());
			hxPatient.setAge(hxPatientDto.getAge());
			hxPatient.setNationality(hxPatientDto.getNationality());
			hxPatient.setIdno(hxPatientDto.getIdno());
			hxPatient.setPhone(hxPatientDto.getPhone());
			hxPatient.setPhoneRemark(hxPatientDto.getPhoneRemark());
			hxPatient.setPhoneNo(hxPatientDto.getPhoneNo());
			hxPatient.setPhoneNoRemark(hxPatientDto.getPhoneNoRemark());
			hxPatient.setWechat(hxPatientDto.getWechat());
			hxPatient.setQqNo(hxPatientDto.getQqNo());
			hxPatient.setMail(hxPatientDto.getMail());
			hxPatient.setProvinceCode(hxPatientDto.getProvinceCode());
			hxPatient.setProvince(hxPatientDto.getProvince());
			hxPatient.setCityCode(hxPatientDto.getCityCode());
			hxPatient.setCity(hxPatientDto.getCity());
			hxPatient.setAreaCode(hxPatientDto.getAreaCode());
			hxPatient.setArea(hxPatientDto.getArea());
			hxPatient.setAddrDetail(hxPatientDto.getAddrDetail());
			hxPatient.setAddrInfo(hxPatientDto.getAddrInfo());
			hxPatient.setSource1Code(hxPatientDto.getSource1Code());
			hxPatient.setSource1(hxPatientDto.getSource1());
			hxPatient.setSource2Code(hxPatientDto.getSource2Code());
			hxPatient.setSource2(hxPatientDto.getSource2());
			hxPatient.setSource3Code(hxPatientDto.getSource3Code());
			hxPatient.setSource3(hxPatientDto.getSource3());
			hxPatient.setRemark(hxPatientDto.getRemark());
			hxPatient.setPastHistory(hxPatientDto.getPastHistory());
			hxPatient.setAllergyHistory(hxPatientDto.getAllergyHistory());
			hxPatient.setMedicationHistory(hxPatientDto.getMedicationHistory());
			hxPatient.setCreateTime(hxPatientDto.getCreateTime());
			hxPatient.setFirstMemberNo(hxPatientDto.getFirstMemberNo());
			hxPatient.setFirstMemberName(hxPatientDto.getFirstMemberName());
			hxPatient.setDutyMemberNo(hxPatientDto.getDutyMemberNo());
			hxPatient.setDutyMemberName(hxPatientDto.getDutyMemberName());
			hxPatient.setConsMemberNo(hxPatientDto.getConsMemberNo());
			hxPatient.setConsMemberName(hxPatientDto.getConsMemberName());
			hxPatient.setCreateId(hxPatientDto.getCreateId());
			hxPatient.setCreateDate(hxPatientDto.getCreateDate());
			hxPatient.setUpdateId(hxPatientDto.getUpdateId());
			hxPatient.setUpdateDate(hxPatientDto.getUpdateDate());
			hxPatient.setMemberNo(hxPatientDto.getMemberNo());
			hxPatient.setClueCode(hxPatientDto.getClueCode());
			hxPatientDao.insertSelective(hxPatient);
			logger.debug("addHxPatient(HxPatientDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增患者信息错误！", e);
			throw new TsfaServiceException(ErrorCode.HX_PATIENT_ADD_ERROR,
					"新增患者信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询患者信息
	 *
	 * @param findHxPatientPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<HxPatientDto> findHxPatients(FindHxPatientPage findHxPatientPage)
			throws TsfaServiceException {
		AssertUtils.notNull(findHxPatientPage);
		List<HxPatientDto> returnList = null;
		try {
			returnList = hxPatientDao.findHxPatients(findHxPatientPage);
		} catch (Exception e) {
			logger.error("查找患者信息信息错误！", e);
			throw new TsfaServiceException(
					ErrorCode.HX_PATIENT_NOT_EXIST_ERROR, "患者信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateHxPatient(HxPatientDto hxPatientDto)
			throws TsfaServiceException {
		logger.debug(
				"updateHxPatient(HxPatientDto hxPatientDto={}) - start", hxPatientDto); //$NON-NLS-1$

		AssertUtils.notNull(hxPatientDto);
		AssertUtils.notNullAndEmpty(hxPatientDto.getCode(), "Code不能为空");
		try {
			HxPatient hxPatient = new HxPatient();
			// update数据录入
			hxPatient.setCode(hxPatientDto.getCode());
			hxPatient.setShopNo(hxPatientDto.getShopNo());
			hxPatient.setShopName(hxPatientDto.getShopName());
			hxPatient.setMerchantNo(hxPatientDto.getMerchantNo());
			hxPatient.setMerchantName(hxPatientDto.getMerchantName());
			hxPatient.setName(hxPatientDto.getName());
			hxPatient.setSex(hxPatientDto.getSex());
			hxPatient.setType(hxPatientDto.getType());
			hxPatient.setCaseNo(hxPatientDto.getCaseNo());
			hxPatient.setBirthDate(hxPatientDto.getBirthDate());
			hxPatient.setAge(hxPatientDto.getAge());
			hxPatient.setNationality(hxPatientDto.getNationality());
			hxPatient.setIdno(hxPatientDto.getIdno());
			hxPatient.setPhone(hxPatientDto.getPhone());
			hxPatient.setPhoneRemark(hxPatientDto.getPhoneRemark());
			hxPatient.setPhoneNo(hxPatientDto.getPhoneNo());
			hxPatient.setPhoneNoRemark(hxPatientDto.getPhoneNoRemark());
			hxPatient.setWechat(hxPatientDto.getWechat());
			hxPatient.setQqNo(hxPatientDto.getQqNo());
			hxPatient.setMail(hxPatientDto.getMail());
			hxPatient.setProvinceCode(hxPatientDto.getProvinceCode());
			hxPatient.setProvince(hxPatientDto.getProvince());
			hxPatient.setCityCode(hxPatientDto.getCityCode());
			hxPatient.setCity(hxPatientDto.getCity());
			hxPatient.setAreaCode(hxPatientDto.getAreaCode());
			hxPatient.setArea(hxPatientDto.getArea());
			hxPatient.setAddrDetail(hxPatientDto.getAddrDetail());
			hxPatient.setAddrInfo(hxPatientDto.getAddrInfo());
			hxPatient.setSource1Code(hxPatientDto.getSource1Code());
			hxPatient.setSource1(hxPatientDto.getSource1());
			hxPatient.setSource2Code(hxPatientDto.getSource2Code());
			hxPatient.setSource2(hxPatientDto.getSource2());
			hxPatient.setSource3Code(hxPatientDto.getSource3Code());
			hxPatient.setSource3(hxPatientDto.getSource3());
			hxPatient.setRemark(hxPatientDto.getRemark());
			hxPatient.setPastHistory(hxPatientDto.getPastHistory());
			hxPatient.setAllergyHistory(hxPatientDto.getAllergyHistory());
			hxPatient.setMedicationHistory(hxPatientDto.getMedicationHistory());
			hxPatient.setCreateTime(hxPatientDto.getCreateTime());
			hxPatient.setFirstMemberNo(hxPatientDto.getFirstMemberNo());
			hxPatient.setFirstMemberName(hxPatientDto.getFirstMemberName());
			hxPatient.setDutyMemberNo(hxPatientDto.getDutyMemberNo());
			hxPatient.setDutyMemberName(hxPatientDto.getDutyMemberName());
			hxPatient.setConsMemberNo(hxPatientDto.getConsMemberNo());
			hxPatient.setConsMemberName(hxPatientDto.getConsMemberName());
			hxPatient.setCreateId(hxPatientDto.getCreateId());
			hxPatient.setCreateDate(hxPatientDto.getCreateDate());
			hxPatient.setUpdateId(hxPatientDto.getUpdateId());
			hxPatient.setUpdateDate(hxPatientDto.getUpdateDate());
			hxPatient.setMemberNo(hxPatientDto.getMemberNo());
			hxPatient.setClueCode(hxPatientDto.getClueCode());
			AssertUtils.notUpdateMoreThanOne(hxPatientDao
					.updateByPrimaryKeySelective(hxPatient));
			logger.debug("updateHxPatient(HxPatientDto) - end - return"); //$NON-NLS-1$
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("患者信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.HX_PATIENT_UPDATE_ERROR,
					"患者信息更新信息错误！", e);
		}
	}

	@Override
	public HxPatientDto findHxPatient(HxPatientDto hxPatientDto)
			throws TsfaServiceException {
		logger.debug(
				"findHxPatient(FindHxPatient findHxPatient={}) - start", hxPatientDto); //$NON-NLS-1$

		AssertUtils.notNull(hxPatientDto);
		AssertUtils.notAllNull(hxPatientDto.getCode(), "Code不能为空");
		try {
			HxPatient hxPatient = hxPatientDao.selectByPrimaryKey(hxPatientDto
					.getCode());
			if (hxPatient == null) {
				return null;
				// throw new
				// TsfaServiceException(ErrorCode.HX_PATIENT_NOT_EXIST_ERROR,"患者信息不存在");
			}
			HxPatientDto findHxPatientReturn = new HxPatientDto();
			// find数据录入
			findHxPatientReturn.setCode(hxPatient.getCode());
			findHxPatientReturn.setShopNo(hxPatient.getShopNo());
			findHxPatientReturn.setShopName(hxPatient.getShopName());
			findHxPatientReturn.setMerchantNo(hxPatient.getMerchantNo());
			findHxPatientReturn.setMerchantName(hxPatient.getMerchantName());
			findHxPatientReturn.setName(hxPatient.getName());
			findHxPatientReturn.setSex(hxPatient.getSex());
			findHxPatientReturn.setType(hxPatient.getType());
			findHxPatientReturn.setCaseNo(hxPatient.getCaseNo());
			findHxPatientReturn.setBirthDate(hxPatient.getBirthDate());
			findHxPatientReturn.setAge(hxPatient.getAge());
			findHxPatientReturn.setNationality(hxPatient.getNationality());
			findHxPatientReturn.setIdno(hxPatient.getIdno());
			findHxPatientReturn.setPhone(hxPatient.getPhone());
			findHxPatientReturn.setPhoneRemark(hxPatient.getPhoneRemark());
			findHxPatientReturn.setPhoneNo(hxPatient.getPhoneNo());
			findHxPatientReturn.setPhoneNoRemark(hxPatient.getPhoneNoRemark());
			findHxPatientReturn.setWechat(hxPatient.getWechat());
			findHxPatientReturn.setQqNo(hxPatient.getQqNo());
			findHxPatientReturn.setMail(hxPatient.getMail());
			findHxPatientReturn.setProvinceCode(hxPatient.getProvinceCode());
			findHxPatientReturn.setProvince(hxPatient.getProvince());
			findHxPatientReturn.setCityCode(hxPatient.getCityCode());
			findHxPatientReturn.setCity(hxPatient.getCity());
			findHxPatientReturn.setAreaCode(hxPatient.getAreaCode());
			findHxPatientReturn.setArea(hxPatient.getArea());
			findHxPatientReturn.setAddrDetail(hxPatient.getAddrDetail());
			findHxPatientReturn.setAddrInfo(hxPatient.getAddrInfo());
			findHxPatientReturn.setSource1Code(hxPatient.getSource1Code());
			findHxPatientReturn.setSource1(hxPatient.getSource1());
			findHxPatientReturn.setSource2Code(hxPatient.getSource2Code());
			findHxPatientReturn.setSource2(hxPatient.getSource2());
			findHxPatientReturn.setSource3Code(hxPatient.getSource3Code());
			findHxPatientReturn.setSource3(hxPatient.getSource3());
			findHxPatientReturn.setRemark(hxPatient.getRemark());
			findHxPatientReturn.setPastHistory(hxPatient.getPastHistory());
			findHxPatientReturn
					.setAllergyHistory(hxPatient.getAllergyHistory());
			findHxPatientReturn.setMedicationHistory(hxPatient
					.getMedicationHistory());
			findHxPatientReturn.setCreateTime(hxPatient.getCreateTime());
			findHxPatientReturn.setFirstMemberNo(hxPatient.getFirstMemberNo());
			findHxPatientReturn.setFirstMemberName(hxPatient
					.getFirstMemberName());
			findHxPatientReturn.setDutyMemberNo(hxPatient.getDutyMemberNo());
			findHxPatientReturn
					.setDutyMemberName(hxPatient.getDutyMemberName());
			findHxPatientReturn.setConsMemberNo(hxPatient.getConsMemberNo());
			findHxPatientReturn
					.setConsMemberName(hxPatient.getConsMemberName());
			findHxPatientReturn.setCreateId(hxPatient.getCreateId());
			findHxPatientReturn.setCreateDate(hxPatient.getCreateDate());
			findHxPatientReturn.setUpdateId(hxPatient.getUpdateId());
			findHxPatientReturn.setUpdateDate(hxPatient.getUpdateDate());
			findHxPatientReturn.setMemberNo(hxPatient.getMemberNo());
			findHxPatientReturn.setClueCode(hxPatient.getClueCode());
			logger.debug(
					"findHxPatient(HxPatientDto) - end - return value={}", findHxPatientReturn); //$NON-NLS-1$
			return findHxPatientReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找患者信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.HX_PATIENT_FIND_ERROR,
					"查找患者信息信息错误！", e);
		}

	}

	@Override
	public Page<HxPatientDto> findHxPatientPage(
			FindHxPatientPage findHxPatientPage) throws TsfaServiceException {
		logger.debug(
				"findHxPatientPage(FindHxPatientPage findHxPatientPage={}) - start", findHxPatientPage); //$NON-NLS-1$

		AssertUtils.notNull(findHxPatientPage);
		List<HxPatientDto> returnList = null;
		int count = 0;
		try {
			returnList = hxPatientDao.findHxPatientPage(findHxPatientPage);
			count = hxPatientDao.findHxPatientPageCount(findHxPatientPage);
		} catch (Exception e) {
			logger.error("患者信息不存在错误", e);
			throw new TsfaServiceException(
					ErrorCode.HX_PATIENT_FIND_PAGE_ERROR, "患者信息不存在错误.！", e);
		}
		Page<HxPatientDto> returnPage = new Page<HxPatientDto>(returnList,
				count, findHxPatientPage);

		logger.debug(
				"findHxPatientPage(FindHxPatientPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return returnPage;
	}

	@Override
	public String save(HxPatient patient) throws TsfaServiceException {
		if (patient == null)
			throw new TsfaServiceException(
					ErrorCode.HX_PATIENT_NOT_EXIST_ERROR, "患者线索参数错误");
		// 完整详情地址
		patient.setAddrInfo(patient.getProvince() + patient.getCity()
				+ patient.getArea() + patient.getAddrDetail());
		int res = 0;
		
		//1.微信客户则检测：检测微信客户建档否 2019.07.05 好乐美_APP_1.2
		if (!ObjectUtil.isEmpty(patient.getMemberNo())) {
			HxPatientDto param=new HxPatientDto();
			param.setMemberNo(patient.getMemberNo());
			param.setMerchantNo(patient.getMerchantNo());
			HxPatientDto findHxPatientByWx = this.findHxPatientByMemberNo(param);
			if (null != findHxPatientByWx && !findHxPatientByWx.getCode().equals(patient.getCode())
					) {
				throw new TsfaServiceException(ErrorCode.HX_PATIENT_UPDATE_ERROR, "微信客户已建档！");
			}
		}
		//2.姓名+手机号去重  2019.07.05  好乐美_APP_1.2
		if (ObjectUtil.isEmpty(patient.getCode())) {
			HxPatientDto param=new HxPatientDto();
			param.setPhone(patient.getPhone());
			param.setName(patient.getName());
			param.setMerchantNo(patient.getMerchantNo());
			HxPatientDto findHxPatient= this.findHxPatientByPhoneAndName(param);
			if (findHxPatient != null) {
				patient.setCode(findHxPatient.getCode());
			}
		}
		if (ObjectUtil.isEmpty(patient.getCode())) {
			// 添加验证
			if (ObjectUtil.isEmpty(patient.getMerchantNo()))
				throw new TsfaServiceException(
						ErrorCode.HX_PATIENT_NOT_EXIST_ERROR, "商户编号不能为空");
			if (ObjectUtil.isEmpty(patient.getMerchantName()))
				throw new TsfaServiceException(
						ErrorCode.HX_PATIENT_NOT_EXIST_ERROR, "商户名称不能为空");
			if (ObjectUtil.isEmpty(patient.getName()))
				throw new TsfaServiceException(
						ErrorCode.HX_PATIENT_NOT_EXIST_ERROR, "姓名不能为空");
			if (ObjectUtil.isEmpty(patient.getPhone())) {
				throw new TsfaServiceException(
						ErrorCode.HX_PATIENT_NOT_EXIST_ERROR, "手机不能为空");
			} else {
				if (!FormatUtil.matchMobile(patient.getPhone())) {
					throw new TsfaServiceException(
							ErrorCode.HX_PATIENT_NOT_EXIST_ERROR, "手机格式错误");
				}
			}
			
			// 添加
			patient.setCode(GUID.generateCode());
			patient.setCaseNo(GenerateNo.getInstance().getCaseNo(
					patient.getType()));
			patient.setAddrInfo(patient.getProvince() + patient.getCity()
					+ patient.getArea() + patient.getAddrDetail());
			patient.setCreateDate(new Date());
			res = hxPatientDao.insertSelective(patient);
		} else {
			//允许 修改memberNo则意味着允许换绑。
			// 修改
			patient.setCaseNo(null);
			patient.setUpdateDate(new Date());
			res = hxPatientDao.updateByPrimaryKeySelective(patient);
		}
		if (res == 0)
			throw new TsfaServiceException(
					ErrorCode.HX_PATIENT_NOT_EXIST_ERROR, "保存患者信息失败");
		return patient.getCode();
	}

	@Override
	public Page<HxPatientDto> list(PatientParams params)
			throws TsfaServiceException {
		if (ObjectUtil.isEmpty(params.getMemberNoMerchant())) {
			throw new TsfaServiceException(
					ErrorCode.HX_PATIENT_NOT_EXIST_ERROR, "商户编号不能为空");
		}
		List<HxPatientDto> list = null;
		int count = 0;
		try {
			count = hxPatientDao.queryPatientCount(params);
			list = hxPatientDao.queryPatientList(params);
		} catch (Exception e) {
			logger.error("查询线索列表错误", e);
			throw e;
		}
		return new Page<HxPatientDto>(list, count, params);
	}

	@Override
	public List<PatientServiceAdvisoryDto> advisoryrecords(String code) {
		AssertUtils.notAllNull(code, "Code不能为空");
		List<PatientServiceAdvisoryDto> list = null;
		try {
			list = patientServiceAdvisoryDao.queryAdvisoryRecords(code);
		} catch (Exception e) {
			logger.error("查询咨询记录错误", e);
			throw e;
		}
		return list;
	}

	@Override
	public List<PatientServiceDto> visitrecords(String code) {
		AssertUtils.notAllNull(code, "Code不能为空");
		List<PatientServiceDto> list = null;
		try {
			list = patientServiceDao.queryVisitRecords(code);
			if (ObjectUtil.isNotEmpty(list)) {
				for (PatientServiceDto patient : list) {
					// 查询项目名称
					List<PatientServiceChoose> chooses = patientServiceChooseDao
							.queryByServiceCode(patient.getCode());
					if (ObjectUtil.isNotEmpty(chooses)) {
						String project = "";
						for (PatientServiceChoose choose : chooses) {
							project += (choose.getProjectPropertyName() + ",");
						}
						project = project.substring(0, project.length() - 1);
						patient.setRemark2(project);
					}
					// 查询病例编号
					PatientMedical medical = patientMedicalDao
							.selectByPatientReservationCode(patient.getCode());
					if (medical != null) {
						patient.setMedicalNo(medical.getCode());
					} else {
						patient.setMedicalNo(null);
					}
				}
			}
		} catch (Exception e) {
			logger.error("查询就诊记录错误", e);
			throw e;
		}
		return list;
	}

	@Override
	public HxPatientDto findHxPatientByMemberNo(HxPatientDto hxPatientDto) throws TsfaServiceException {
		AssertUtils.notNull(hxPatientDto);
		AssertUtils.notNull(hxPatientDto.getMerchantNo(),"商户编号不能为空");
		AssertUtils.notNull(hxPatientDto.getMemberNo(),"客户编号不能为空");
		
		HxPatientDto rtData=null;
		List<HxPatientDto> returnList = null;
		try {
			FindHxPatientPage findHxPatientPage=new FindHxPatientPage();
			findHxPatientPage.setParam(hxPatientDto);
			returnList = hxPatientDao.findHxPatients(findHxPatientPage);
			if(returnList!=null && returnList.size()>0) {
				rtData = returnList.get(0);
				return rtData;
			}
		} catch (Exception e) {
			logger.error("查找患者信息信息错误！", e);
			throw new TsfaServiceException(
					ErrorCode.HX_PATIENT_NOT_EXIST_ERROR, "患者信息不存在");
		}
		return rtData;
	}

	@Override
	public HxPatientDto findHxPatientByPhoneAndName(HxPatientDto hxPatientDto) throws TsfaServiceException {
		AssertUtils.notNull(hxPatientDto);
		AssertUtils.notNull(hxPatientDto.getMerchantNo(),"商户编号不能为空");
		AssertUtils.notNull(hxPatientDto.getName(),"客户姓名不能为空");
		AssertUtils.notNull(hxPatientDto.getPhone(),"客户手机号不能为空");
		
		HxPatientDto rtData=null;
		List<HxPatientDto> returnList = null;
		try {
			
			FindHxPatientPage findHxPatientPage=new FindHxPatientPage();
			findHxPatientPage.setParam(hxPatientDto);
			returnList = hxPatientDao.findHxPatients(findHxPatientPage);
			if(returnList!=null && returnList.size()>0) {
				rtData = returnList.get(0);
				return rtData;
			}
		} catch (Exception e) {
			logger.error("查找患者信息信息错误！", e);
			throw new TsfaServiceException(
					ErrorCode.HX_PATIENT_NOT_EXIST_ERROR, "患者信息不存在");
		}
		return rtData;
	}

	@Override
	public void bindWx(HxPatientDto patientDto) throws TsfaServiceException {
//		AssertUtils.notNullAndEmpty(patientDto.getCode(),"患者编号不能为空！");
		AssertUtils.notNullAndEmpty(patientDto.getMerchantNo(),"商户编号不能为空！");
		AssertUtils.notNullAndEmpty(patientDto.getMemberNo(),"客户编号不能为空！");
		//1.检测微信客户建档否
		HxPatientDto findHxPatientByWx = this.findHxPatientByMemberNo(patientDto);
		if (null != findHxPatientByWx) {
			throw new TsfaServiceException(ErrorCode.HX_PATIENT_UPDATE_ERROR, "微信客户已建档！");
		}
		//2.确定患者
		String patientCode = null;
		if(StringUtils.isNotEmpty(patientDto.getCode())) {//患者绑定微信
			patientCode = patientDto.getCode();
			//2.1 检测患者是否绑定了微信
			HxPatientDto param=new HxPatientDto();
			param.setCode(patientDto.getCode());
			HxPatientDto findHxPatient= this.findHxPatient(param);
			
			if(StringUtils.isNotEmpty(findHxPatient.getMemberNo())) {
				throw new TsfaServiceException(ErrorCode.HX_PATIENT_UPDATE_ERROR, "患者已绑定微信！");
			}
		} else if (StringUtils.isNotEmpty(patientDto.getPhone()) && StringUtils.isNotEmpty(patientDto.getName())) {
			//姓名+手机号+商户号 查出患者 并绑定 用于认领后直通车用户绑定患者
			HxPatientDto param=new HxPatientDto();
			param.setPhone(patientDto.getPhone());
			param.setName(patientDto.getName());
			param.setMerchantNo(patientDto.getMerchantNo());
			param.setMemberNoIsNull("yes");
			HxPatientDto findHxPatient= this.findHxPatientByPhoneAndName(param);
			if (null != findHxPatient) {
				patientCode=findHxPatient.getCode();
			}
		} else {
			throw new TsfaServiceException(ErrorCode.HX_PATIENT_UPDATE_ERROR, "参数错误！");
		}
		
		//3.给患者绑定
		if (StringUtils.isNotEmpty(patientCode)) {// 患者绑定微信
			HxPatientDto hxPatientDto = new HxPatientDto();
			hxPatientDto.setCode(patientCode);
			hxPatientDto.setMemberNo(patientDto.getMemberNo());
			this.updateHxPatient(hxPatientDto);
		}
	}
	
	
}
