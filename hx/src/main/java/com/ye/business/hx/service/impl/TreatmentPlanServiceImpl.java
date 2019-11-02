package com.ye.business.hx.service.impl;

/**
 * Copyright &copy; 2018-2021  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;

import com.ye.business.hx.dto.TreatmentPlanDto;
import com.ye.business.hx.dto.FindTreatmentPlanPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.ITreatmentPlanDao;
import com.ye.business.hx.domain.TreatmentPlan;
import com.ye.business.hx.service.ITreatmentPlanService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
@Service
public class TreatmentPlanServiceImpl implements ITreatmentPlanService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(TreatmentPlanServiceImpl.class);
	

	@Resource
	private ITreatmentPlanDao treatmentPlanDao;
	
	
	@Override
	public void addTreatmentPlan(
			TreatmentPlanDto treatmentPlanDto) throws TsfaServiceException {
		logger.debug("addTreatmentPlan(AddTreatmentPlan addTreatmentPlan={}) - start", treatmentPlanDto); 

		AssertUtils.notNull(treatmentPlanDto);
		try {
			TreatmentPlan treatmentPlan = new TreatmentPlan();
			//add数据录入
			treatmentPlan.setCode(treatmentPlanDto.getCode());
			treatmentPlan.setName(treatmentPlanDto.getName());
			treatmentPlan.setStep(treatmentPlanDto.getStep());
			treatmentPlan.setStatus(treatmentPlanDto.getStatus());
			treatmentPlan.setCreateDate(treatmentPlanDto.getCreateDate());
			treatmentPlan.setRemark(treatmentPlanDto.getRemark());
			treatmentPlan.setRemark2(treatmentPlanDto.getRemark2());
			treatmentPlan.setRemark3(treatmentPlanDto.getRemark3());
			treatmentPlan.setRemark4(treatmentPlanDto.getRemark4());
			treatmentPlanDao.insertSelective(treatmentPlan);
			logger.debug("addTreatmentPlan(TreatmentPlanDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增治疗方案信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TREATMENT_PLAN_ADD_ERROR,"新增治疗方案信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询治疗方案信息
	 *
	 * @param findTreatmentPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<TreatmentPlanDto>  findTreatmentPlans(FindTreatmentPlanPage findTreatmentPlanPage)throws TsfaServiceException{
		AssertUtils.notNull(findTreatmentPlanPage);
		List<TreatmentPlanDto> returnList=null;
		try {
			returnList = treatmentPlanDao.findTreatmentPlans(findTreatmentPlanPage);
		} catch (Exception e) {
			logger.error("查找治疗方案信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.TREATMENT_PLAN_NOT_EXIST_ERROR,"治疗方案信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateTreatmentPlan(
			TreatmentPlanDto treatmentPlanDto)
			throws TsfaServiceException {
		logger.debug("updateTreatmentPlan(TreatmentPlanDto treatmentPlanDto={}) - start", treatmentPlanDto); 

		AssertUtils.notNull(treatmentPlanDto);
		AssertUtils.notNullAndEmpty(treatmentPlanDto.getCode(),"Code不能为空");
		try {
			TreatmentPlan treatmentPlan = new TreatmentPlan();
			//update数据录入
			treatmentPlan.setCode(treatmentPlanDto.getCode());
			treatmentPlan.setName(treatmentPlanDto.getName());
			treatmentPlan.setStep(treatmentPlanDto.getStep());
			treatmentPlan.setStatus(treatmentPlanDto.getStatus());
			treatmentPlan.setCreateDate(treatmentPlanDto.getCreateDate());
			treatmentPlan.setRemark(treatmentPlanDto.getRemark());
			treatmentPlan.setRemark2(treatmentPlanDto.getRemark2());
			treatmentPlan.setRemark3(treatmentPlanDto.getRemark3());
			treatmentPlan.setRemark4(treatmentPlanDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(treatmentPlanDao.updateByPrimaryKeySelective(treatmentPlan));
			logger.debug("updateTreatmentPlan(TreatmentPlanDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("治疗方案信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TREATMENT_PLAN_UPDATE_ERROR,"治疗方案信息更新信息错误！",e);
		}
	}

	

	@Override
	public TreatmentPlanDto findTreatmentPlan(
			TreatmentPlanDto treatmentPlanDto) throws TsfaServiceException {
		logger.debug("findTreatmentPlan(FindTreatmentPlan findTreatmentPlan={}) - start", treatmentPlanDto); 

		AssertUtils.notNull(treatmentPlanDto);
		AssertUtils.notAllNull(treatmentPlanDto.getCode(),"Code不能为空");
		try {
			TreatmentPlan treatmentPlan = treatmentPlanDao.selectByPrimaryKey(treatmentPlanDto.getCode());
			if(treatmentPlan == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.TREATMENT_PLAN_NOT_EXIST_ERROR,"治疗方案信息不存在");
			}
			TreatmentPlanDto findTreatmentPlanReturn = new TreatmentPlanDto();
			//find数据录入
			findTreatmentPlanReturn.setCode(treatmentPlan.getCode());
			findTreatmentPlanReturn.setName(treatmentPlan.getName());
			findTreatmentPlanReturn.setStep(treatmentPlan.getStep());
			findTreatmentPlanReturn.setStatus(treatmentPlan.getStatus());
			findTreatmentPlanReturn.setCreateDate(treatmentPlan.getCreateDate());
			findTreatmentPlanReturn.setRemark(treatmentPlan.getRemark());
			findTreatmentPlanReturn.setRemark2(treatmentPlan.getRemark2());
			findTreatmentPlanReturn.setRemark3(treatmentPlan.getRemark3());
			findTreatmentPlanReturn.setRemark4(treatmentPlan.getRemark4());
			
			logger.debug("findTreatmentPlan(TreatmentPlanDto) - end - return value={}", findTreatmentPlanReturn); 
			return findTreatmentPlanReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找治疗方案信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TREATMENT_PLAN_FIND_ERROR,"查找治疗方案信息信息错误！",e);
		}


	}

	
	@Override
	public Page<TreatmentPlanDto> findTreatmentPlanPage(
			FindTreatmentPlanPage findTreatmentPlanPage)
			throws TsfaServiceException {
		logger.debug("findTreatmentPlanPage(FindTreatmentPlanPage findTreatmentPlanPage={}) - start", findTreatmentPlanPage); 

		AssertUtils.notNull(findTreatmentPlanPage);
		List<TreatmentPlanDto> returnList=null;
		int count = 0;
		try {
			count = treatmentPlanDao.findTreatmentPlanPageCount(findTreatmentPlanPage);
			if(count>0) {
				returnList = treatmentPlanDao.findTreatmentPlanPage(findTreatmentPlanPage);
			}
		}  catch (Exception e) {
			logger.error("治疗方案信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.TREATMENT_PLAN_FIND_PAGE_ERROR,"治疗方案信息不存在错误.！",e);
		}
		Page<TreatmentPlanDto> returnPage = new Page<TreatmentPlanDto>(returnList, count, findTreatmentPlanPage);

		logger.debug("findTreatmentPlanPage(FindTreatmentPlanPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	
	@Override
	public void delete(TreatmentPlanDto treatmentPlanDto) throws TsfaServiceException {
		logger.debug("delete(TreatmentPlanDto treatmentPlanDto = {})-start",treatmentPlanDto);
		try {
			treatmentPlanDao.deleteByPrimaryKey(treatmentPlanDto.getCode());
		} catch (Exception e) {
			logger.error("删除治疗方案信息错误",e);
			throw new TsfaServiceException(ErrorCode.TREATMENT_PLAN_DELETE_ERROR,"删除治疗方案信息错误.！",e);
		}
	} 


}
