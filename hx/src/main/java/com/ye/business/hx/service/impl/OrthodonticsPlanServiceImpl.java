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

import com.ye.business.hx.dto.OrthodonticsPlanDto;
import com.ye.business.hx.dto.FindOrthodonticsPlanPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IOrthodonticsPlanDao;
import com.ye.business.hx.domain.OrthodonticsPlan;
import com.ye.business.hx.service.IOrthodonticsPlanService;
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
public class OrthodonticsPlanServiceImpl implements IOrthodonticsPlanService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(OrthodonticsPlanServiceImpl.class);
	

	@Resource
	private IOrthodonticsPlanDao orthodonticsPlanDao;
	
	
	@Override
	public void addOrthodonticsPlan(
			OrthodonticsPlanDto orthodonticsPlanDto) throws TsfaServiceException {
		logger.debug("addOrthodonticsPlan(AddOrthodonticsPlan addOrthodonticsPlan={}) - start", orthodonticsPlanDto); 

		AssertUtils.notNull(orthodonticsPlanDto);
		try {
			OrthodonticsPlan orthodonticsPlan = new OrthodonticsPlan();
			//add数据录入
			orthodonticsPlan.setCode(orthodonticsPlanDto.getCode());
			orthodonticsPlan.setType(orthodonticsPlanDto.getType());
			orthodonticsPlan.setCreateDate(orthodonticsPlanDto.getCreateDate());
			orthodonticsPlan.setRemark(orthodonticsPlanDto.getRemark());
			orthodonticsPlan.setRemark2(orthodonticsPlanDto.getRemark2());
			orthodonticsPlan.setRemark3(orthodonticsPlanDto.getRemark3());
			orthodonticsPlan.setRemark4(orthodonticsPlanDto.getRemark4());
			orthodonticsPlan.setContent(orthodonticsPlanDto.getContent());
			orthodonticsPlanDao.insertSelective(orthodonticsPlan);
			logger.debug("addOrthodonticsPlan(OrthodonticsPlanDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增正畸计划配置信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PLAN_ADD_ERROR,"新增正畸计划配置信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询正畸计划配置信息
	 *
	 * @param findOrthodonticsPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<OrthodonticsPlanDto>  findOrthodonticsPlans(FindOrthodonticsPlanPage findOrthodonticsPlanPage)throws TsfaServiceException{
		AssertUtils.notNull(findOrthodonticsPlanPage);
		List<OrthodonticsPlanDto> returnList=null;
		try {
			returnList = orthodonticsPlanDao.findOrthodonticsPlans(findOrthodonticsPlanPage);
		} catch (Exception e) {
			logger.error("查找正畸计划配置信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PLAN_NOT_EXIST_ERROR,"正畸计划配置信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateOrthodonticsPlan(
			OrthodonticsPlanDto orthodonticsPlanDto)
			throws TsfaServiceException {
		logger.debug("updateOrthodonticsPlan(OrthodonticsPlanDto orthodonticsPlanDto={}) - start", orthodonticsPlanDto); 

		AssertUtils.notNull(orthodonticsPlanDto);
		AssertUtils.notNullAndEmpty(orthodonticsPlanDto.getCode(),"Code不能为空");
		try {
			OrthodonticsPlan orthodonticsPlan = new OrthodonticsPlan();
			//update数据录入
			orthodonticsPlan.setCode(orthodonticsPlanDto.getCode());
			orthodonticsPlan.setType(orthodonticsPlanDto.getType());
			orthodonticsPlan.setCreateDate(orthodonticsPlanDto.getCreateDate());
			orthodonticsPlan.setRemark(orthodonticsPlanDto.getRemark());
			orthodonticsPlan.setRemark2(orthodonticsPlanDto.getRemark2());
			orthodonticsPlan.setRemark3(orthodonticsPlanDto.getRemark3());
			orthodonticsPlan.setRemark4(orthodonticsPlanDto.getRemark4());
			orthodonticsPlan.setContent(orthodonticsPlanDto.getContent());
			AssertUtils.notUpdateMoreThanOne(orthodonticsPlanDao.updateByPrimaryKeySelective(orthodonticsPlan));
			logger.debug("updateOrthodonticsPlan(OrthodonticsPlanDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("正畸计划配置信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PLAN_UPDATE_ERROR,"正畸计划配置信息更新信息错误！",e);
		}
	}

	

	@Override
	public OrthodonticsPlanDto findOrthodonticsPlan(
			OrthodonticsPlanDto orthodonticsPlanDto) throws TsfaServiceException {
		logger.debug("findOrthodonticsPlan(FindOrthodonticsPlan findOrthodonticsPlan={}) - start", orthodonticsPlanDto); 

		AssertUtils.notNull(orthodonticsPlanDto);
		AssertUtils.notAllNull(orthodonticsPlanDto.getCode(),"Code不能为空");
		try {
			OrthodonticsPlan orthodonticsPlan = orthodonticsPlanDao.selectByPrimaryKey(orthodonticsPlanDto.getCode());
			if(orthodonticsPlan == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PLAN_NOT_EXIST_ERROR,"正畸计划配置信息不存在");
			}
			OrthodonticsPlanDto findOrthodonticsPlanReturn = new OrthodonticsPlanDto();
			//find数据录入
			findOrthodonticsPlanReturn.setCode(orthodonticsPlan.getCode());
			findOrthodonticsPlanReturn.setType(orthodonticsPlan.getType());
			findOrthodonticsPlanReturn.setCreateDate(orthodonticsPlan.getCreateDate());
			findOrthodonticsPlanReturn.setRemark(orthodonticsPlan.getRemark());
			findOrthodonticsPlanReturn.setRemark2(orthodonticsPlan.getRemark2());
			findOrthodonticsPlanReturn.setRemark3(orthodonticsPlan.getRemark3());
			findOrthodonticsPlanReturn.setRemark4(orthodonticsPlan.getRemark4());
			findOrthodonticsPlanReturn.setContent(orthodonticsPlan.getContent());
			
			logger.debug("findOrthodonticsPlan(OrthodonticsPlanDto) - end - return value={}", findOrthodonticsPlanReturn); 
			return findOrthodonticsPlanReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找正畸计划配置信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PLAN_FIND_ERROR,"查找正畸计划配置信息信息错误！",e);
		}


	}

	
	@Override
	public Page<OrthodonticsPlanDto> findOrthodonticsPlanPage(
			FindOrthodonticsPlanPage findOrthodonticsPlanPage)
			throws TsfaServiceException {
		logger.debug("findOrthodonticsPlanPage(FindOrthodonticsPlanPage findOrthodonticsPlanPage={}) - start", findOrthodonticsPlanPage); 

		AssertUtils.notNull(findOrthodonticsPlanPage);
		List<OrthodonticsPlanDto> returnList=null;
		int count = 0;
		try {
			count = orthodonticsPlanDao.findOrthodonticsPlanPageCount(findOrthodonticsPlanPage);
			if(count>0) {
				returnList = orthodonticsPlanDao.findOrthodonticsPlanPage(findOrthodonticsPlanPage);
			}
		}  catch (Exception e) {
			logger.error("正畸计划配置信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PLAN_FIND_PAGE_ERROR,"正畸计划配置信息不存在错误.！",e);
		}
		Page<OrthodonticsPlanDto> returnPage = new Page<OrthodonticsPlanDto>(returnList, count, findOrthodonticsPlanPage);

		logger.debug("findOrthodonticsPlanPage(FindOrthodonticsPlanPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	
	@Override
	public void delete(OrthodonticsPlanDto orthodonticsPlanDto) throws TsfaServiceException {
		logger.debug("delete(OrthodonticsPlanDto orthodonticsPlanDto = {})-start",orthodonticsPlanDto);
		try {
			orthodonticsPlanDao.deleteByPrimaryKey(orthodonticsPlanDto.getCode());
		} catch (Exception e) {
			logger.error("删除正畸计划配置信息错误",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PLAN_FIND_PAGE_ERROR,"删除正畸计划配置信息错误.！",e);
		}
	} 


}
