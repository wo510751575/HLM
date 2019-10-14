package com.lj.business.member.service.impl;

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

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IForecastNameDao;
import com.lj.business.member.domain.ForecastName;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.forecastName.AddForecastName;
import com.lj.business.member.dto.forecastName.AddForecastNameReturn;
import com.lj.business.member.dto.forecastName.FindForecastName;
import com.lj.business.member.dto.forecastName.FindForecastNamePage;
import com.lj.business.member.dto.forecastName.FindForecastNamePageReturn;
import com.lj.business.member.dto.forecastName.FindForecastNameReturn;
import com.lj.business.member.dto.forecastName.UpdateForecastName;
import com.lj.business.member.dto.forecastName.UpdateForecastNameReturn;
import com.lj.business.member.service.IForecastNameService;
import com.lj.business.member.service.IPersonMemberService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭俊霖
 * 
 * 
 * CreateDate: 2017-11-01
 */
@Service
public class ForecastNameServiceImpl implements IForecastNameService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ForecastNameServiceImpl.class);
	

	@Resource
	private IForecastNameDao forecastNameDao;
	
	@Resource
	private IPersonMemberService personMemberService;
	
	
	@Override
	public AddForecastNameReturn addForecastName(
			AddForecastName addForecastName) throws TsfaServiceException {
		logger.debug("addForecastName(AddForecastName addForecastName={}) - start", addForecastName); 

		AssertUtils.notNull(addForecastName);
		AssertUtils.notNullAndEmpty(addForecastName.getMemberNo(),"客户编号不能为空");
		AssertUtils.notNullAndEmpty(addForecastName.getMemberNoGm(),"导购编号不能为空");
		AssertUtils.notNullAndEmpty(addForecastName.getName(),"姓名不能为空");
//		AssertUtils.notNullAndEmpty(addForecastName.getMobile(),"手机号不能为空");
		AssertUtils.notNullAndEmpty(addForecastName.getSex(),"性别不能为空");
		AssertUtils.notNullAndEmpty(addForecastName.getIntentAddress(),"意向旅游地不能为空");
		AssertUtils.notNullAndEmpty(addForecastName.getPersonCount(),"报名人数不能为空");
		try {
			
			//查询客户信息
			FindPersonMember findPersonMember=new FindPersonMember();
			findPersonMember.setMemberNo(addForecastName.getMemberNo());
			findPersonMember.setMemberNoGm(addForecastName.getMemberNoGm());
			FindPersonMemberReturn personMember = personMemberService.findPersonMemberByMGM(findPersonMember);
			if (personMember == null) {
				throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_NOT_EXIST_ERROR, "客户会员信息不存在");
			}
			
			//一个会员只能报一次名
			ForecastName fn = forecastNameDao.selectByMemberCode(personMember.getCode());
			if(fn!=null){
				throw new TsfaServiceException(ErrorCode.FORECAST_NAME_REPEAT_ERROR,"请勿重复预报名");
			}
			
			ForecastName forecastName = new ForecastName();
			//add数据录入
			forecastName.setCode(GUID.generateByUUID());
			forecastName.setMemberCode(personMember.getCode());
			forecastName.setMemberNo(addForecastName.getMemberNo());
			forecastName.setMemberName(personMember.getMemberName());
			forecastName.setName(addForecastName.getName());
			forecastName.setMobile(addForecastName.getMobile());
			forecastName.setSex(addForecastName.getSex());
			forecastName.setIntentAddress(addForecastName.getIntentAddress());
			forecastName.setPersonCount(addForecastName.getPersonCount());
//			forecastName.setProductLine(addForecastName.getProductLine());
			forecastName.setMemberNoGm(addForecastName.getMemberNoGm());
			forecastName.setMemberNameGm(personMember.getMemberNameGm());
			forecastName.setMerchantNo(personMember.getMerchantNo());
			forecastName.setCreateId(addForecastName.getMemberNo());
			forecastName.setCreateDate(new Date());
			forecastName.setRemark(addForecastName.getRemark());
			forecastName.setRemark2(addForecastName.getRemark2());
			forecastName.setRemark3(addForecastName.getRemark3());
			forecastName.setRemark4(addForecastName.getRemark4());
			forecastNameDao.insertSelective(forecastName);
			AddForecastNameReturn addForecastNameReturn = new AddForecastNameReturn();
			
			logger.debug("addForecastName(AddForecastName) - end - return value={}", addForecastNameReturn); 
			return addForecastNameReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增预报名信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FORECAST_NAME_ADD_ERROR,"新增预报名信息错误！",e);
		}
	}

	@Override
	public UpdateForecastNameReturn updateForecastName(
			UpdateForecastName updateForecastName)
			throws TsfaServiceException {
		logger.debug("updateForecastName(UpdateForecastName updateForecastName={}) - start", updateForecastName); 

		AssertUtils.notNull(updateForecastName);
		AssertUtils.notNullAndEmpty(updateForecastName.getCode(),"CODE不能为空");
		AssertUtils.notNullAndEmpty(updateForecastName.getName(),"姓名不能为空");
//		AssertUtils.notNullAndEmpty(updateForecastName.getMobile(),"手机号不能为空");
		AssertUtils.notNullAndEmpty(updateForecastName.getSex(),"性别不能为空");
		AssertUtils.notNullAndEmpty(updateForecastName.getIntentAddress(),"意向旅游地不能为空");
		AssertUtils.notNullAndEmpty(updateForecastName.getPersonCount(),"报名人数不能为空");
		try {
			ForecastName forecastName = new ForecastName();
			//update数据录入
			forecastName.setCode(updateForecastName.getCode());
			forecastName.setMemberCode(updateForecastName.getMemberCode());
			forecastName.setMemberNo(updateForecastName.getMemberNo());
			forecastName.setMemberName(updateForecastName.getMemberName());
			forecastName.setName(updateForecastName.getName());
			forecastName.setMobile(updateForecastName.getMobile());
			forecastName.setSex(updateForecastName.getSex());
			forecastName.setIntentAddress(updateForecastName.getIntentAddress());
			forecastName.setPersonCount(updateForecastName.getPersonCount());
			forecastName.setProductLine(updateForecastName.getProductLine());
			forecastName.setStartAddress(updateForecastName.getStartAddress());
			forecastName.setBudget(updateForecastName.getBudget());
			forecastName.setTourtime(updateForecastName.getTourtime());
			forecastName.setTravelStandard(updateForecastName.getTravelStandard());
			forecastName.setOnceAddress(updateForecastName.getOnceAddress());
			forecastName.setMemberNoGm(updateForecastName.getMemberNoGm());
			forecastName.setMemberNameGm(updateForecastName.getMemberNameGm());
			forecastName.setMerchantNo(updateForecastName.getMerchantNo());
			forecastName.setCreateId(updateForecastName.getCreateId());
			forecastName.setCreateDate(updateForecastName.getCreateDate());
			forecastName.setRemark(updateForecastName.getRemark());
			forecastName.setRemark2(updateForecastName.getRemark2());
			forecastName.setRemark3(updateForecastName.getRemark3());
			forecastName.setRemark4(updateForecastName.getRemark4());
			AssertUtils.notUpdateMoreThanOne(forecastNameDao.updateByPrimaryKeySelective(forecastName));
			UpdateForecastNameReturn updateForecastNameReturn = new UpdateForecastNameReturn();

			logger.debug("updateForecastName(UpdateForecastName) - end - return value={}", updateForecastNameReturn); 
			return updateForecastNameReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("预报名信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FORECAST_NAME_UPDATE_ERROR,"预报名信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindForecastNameReturn findForecastName(
			FindForecastName findForecastName) throws TsfaServiceException {
		logger.debug("findForecastName(FindForecastName findForecastName={}) - start", findForecastName); 

		AssertUtils.notNull(findForecastName);
		AssertUtils.notNullAndEmpty(findForecastName.getCode(),"CODE不能为空");
		try {
			ForecastName forecastName = forecastNameDao.selectByPrimaryKey(findForecastName.getCode());
			if(forecastName == null){
				throw new TsfaServiceException(ErrorCode.FORECAST_NAME_NOT_EXIST_ERROR,"预报名信息不存在");
			}
			FindForecastNameReturn findForecastNameReturn = new FindForecastNameReturn();
			//find数据录入
			findForecastNameReturn.setCode(forecastName.getCode());
			findForecastNameReturn.setMemberCode(forecastName.getMemberCode());
			findForecastNameReturn.setMemberNo(forecastName.getMemberNo());
			findForecastNameReturn.setMemberName(forecastName.getMemberName());
			findForecastNameReturn.setName(forecastName.getName());
			findForecastNameReturn.setMobile(forecastName.getMobile());
			findForecastNameReturn.setSex(forecastName.getSex());
			findForecastNameReturn.setIntentAddress(forecastName.getIntentAddress());
			findForecastNameReturn.setPersonCount(forecastName.getPersonCount());
			findForecastNameReturn.setProductLine(forecastName.getProductLine());
			findForecastNameReturn.setStartAddress(forecastName.getStartAddress());
			findForecastNameReturn.setBudget(forecastName.getBudget());
			findForecastNameReturn.setTourtime(forecastName.getTourtime());
			findForecastNameReturn.setTravelStandard(forecastName.getTravelStandard());
			findForecastNameReturn.setOnceAddress(forecastName.getOnceAddress());
			findForecastNameReturn.setMemberNoGm(forecastName.getMemberNoGm());
			findForecastNameReturn.setMemberNameGm(forecastName.getMemberNameGm());
			findForecastNameReturn.setMerchantNo(forecastName.getMerchantNo());
			findForecastNameReturn.setCreateId(forecastName.getCreateId());
			findForecastNameReturn.setCreateDate(forecastName.getCreateDate());
			findForecastNameReturn.setRemark(forecastName.getRemark());
			findForecastNameReturn.setRemark2(forecastName.getRemark2());
			findForecastNameReturn.setRemark3(forecastName.getRemark3());
			findForecastNameReturn.setRemark4(forecastName.getRemark4());
			
			logger.debug("findForecastName(FindForecastName) - end - return value={}", findForecastNameReturn); 
			return findForecastNameReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找预报名信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FORECAST_NAME_FIND_ERROR,"查找预报名信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindForecastNamePageReturn> findForecastNamePage(
			FindForecastNamePage findForecastNamePage)
			throws TsfaServiceException {
		logger.debug("findForecastNamePage(FindForecastNamePage findForecastNamePage={}) - start", findForecastNamePage); 

		AssertUtils.notNull(findForecastNamePage);
		List<FindForecastNamePageReturn> returnList=Lists.newArrayList();
		int count = 0;
		try {
//			returnList = forecastNameDao.findForecastNamePage(findForecastNamePage);
//			count = forecastNameDao.findForecastNamePageCount(findForecastNamePage);
		}  catch (Exception e) {
			logger.error("预报名信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.FORECAST_NAME_FIND_PAGE_ERROR,"预报名信息不存在错误.！",e);
		}
		Page<FindForecastNamePageReturn> returnPage = new Page<FindForecastNamePageReturn>(returnList, count, findForecastNamePage);

		logger.debug("findForecastNamePage(FindForecastNamePage) - end - return value={}", returnPage); 
		return  returnPage;
	}

    @Override
    public List<FindForecastNameReturn> findForecastNameByCondition(FindForecastName findForecastName) throws TsfaServiceException {
        logger.debug("findForecastNameByCondition(FindForecastName findForecastName={}) - start", findForecastName); 

        List<FindForecastNameReturn> returnList;
        try {
            returnList = forecastNameDao.findForecastNameByCondition(findForecastName);
        }  catch (Exception e) {
            logger.error("查找预报名信息信息错误！",e);
            throw new TsfaServiceException(ErrorCode.FORECAST_NAME_FIND_ERROR,"查找预报名信息信息错误！",e);
        }

        logger.debug("findForecastNameByCondition(FindForecastName) - end - return value={}", returnList); 
        return returnList;
    } 


}
