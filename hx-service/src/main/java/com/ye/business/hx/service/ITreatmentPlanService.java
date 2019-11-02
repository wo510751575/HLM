package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.TreatmentPlanDto;
import com.ye.business.hx.dto.FindTreatmentPlanPage;


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
/**
 * 类说明：接口类
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
public interface ITreatmentPlanService {
	
	/**
	 * 
	 *
	 * 方法说明：添加治疗方案信息
	 *
	 * @param treatmentPlanDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addTreatmentPlan(TreatmentPlanDto treatmentPlanDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找治疗方案信息
	 *
	 * @param findTreatmentPlan
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public TreatmentPlanDto findTreatmentPlan(TreatmentPlanDto treatmentPlanDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询治疗方案信息
	 *
	 * @param findTreatmentPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<TreatmentPlanDto>  findTreatmentPlans(FindTreatmentPlanPage findTreatmentPlanPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改治疗方案信息
	 *
	 * @param updateTreatmentPlan
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateTreatmentPlan(TreatmentPlanDto treatmentPlanDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询治疗方案信息
	 *
	 * @param findTreatmentPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<TreatmentPlanDto> findTreatmentPlanPage(FindTreatmentPlanPage findTreatmentPlanPage) throws TsfaServiceException;

	/**   
	 * @Title: delete   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param treatmentPlanDto      
	 * @return: void      
	 * @throws   
	 */
	public void delete(TreatmentPlanDto treatmentPlanDto)throws TsfaServiceException;
	

}
