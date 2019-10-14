package com.lj.business.member.service.impl;

import java.util.Date;
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

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IPersonMemberBaseDao;
import com.lj.business.member.dao.IPersonMemberDao;
import com.lj.business.member.domain.PersonMember;
import com.lj.business.member.domain.PersonMemberBase;
import com.lj.business.member.dto.AddPersonMemberBase;
import com.lj.business.member.dto.AddPersonMemberBaseReturn;
import com.lj.business.member.dto.DelPersonMemberBase;
import com.lj.business.member.dto.DelPersonMemberBaseReturn;
import com.lj.business.member.dto.EditPersonMember;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseList;
import com.lj.business.member.dto.FindPersonMemberBasePage;
import com.lj.business.member.dto.FindPersonMemberBasePageReturn;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.FindPersonMemberBaseReturnList;
import com.lj.business.member.dto.FindPersonMemberName;
import com.lj.business.member.dto.FindPersonMemberPage;
import com.lj.business.member.dto.FindPersonMemberPageReturn;
import com.lj.business.member.dto.UpdatePersonMemberBase;
import com.lj.business.member.dto.UpdatePersonMemberBaseRatioClientInfoDto;
import com.lj.business.member.dto.UpdatePersonMemberBaseReturn;
import com.lj.business.member.emus.MemberStatus;
import com.lj.business.member.emus.NameAuthFlag;
import com.lj.business.member.service.IPersonMemberBaseService;

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
 * CreateDate: 2017-06-14
 */
@Service
public class PersonMemberBaseServiceImpl implements IPersonMemberBaseService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PersonMemberBaseServiceImpl.class);
	

	/** The person member base dao. */
	@Resource
	private IPersonMemberBaseDao personMemberBaseDao;
	
	@Resource
	private IPersonMemberDao personMemberDao;
	
	
	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPersonMemberBaseService#addPersonMemberBase(com.lj.business.member.dto.AddPersonMemberBase)
	 */
	@Override
	public AddPersonMemberBaseReturn addPersonMemberBase(
			AddPersonMemberBase addPersonMemberBase) throws TsfaServiceException {
		logger.debug("addPersonMemberBase(AddPersonMemberBase addPersonMemberBase={}) - start", addPersonMemberBase); 

		AssertUtils.notNull(addPersonMemberBase);
		try {
			//防止同手机号、同微信号 数据录入
			PersonMemberBase personMemberBaseQuery = new PersonMemberBase();
			personMemberBaseQuery.setNoWx(addPersonMemberBase.getNoWx());
			//personMemberBaseQuery.setNoWxAlias(addPersonMemberBase.getNoWxAlias());
			personMemberBaseQuery.setMobile(addPersonMemberBase.getMobile());
			int count = personMemberBaseDao.selectCountByParams(personMemberBaseQuery);
			if(count > 0 ){
				logger.error("客户会员基础表信息已存在错误");
				throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_EXIST_ERROR,"客户会员基础表信息已存在错误");
			}
			
			if(addPersonMemberBase.getStatus() == null)
				addPersonMemberBase.setStatus(MemberStatus.NORMAL);
			if(addPersonMemberBase.getNameAuthFlag() == null)
				addPersonMemberBase.setNameAuthFlag(NameAuthFlag.N);
			
			PersonMemberBase personMemberBase = new PersonMemberBase();
			//add数据录入
			personMemberBase.setCode(GUID.getPreUUID());
			if(!StringUtils.isEmpty(addPersonMemberBase.getMemberNo()))
				personMemberBase.setMemberNo(addPersonMemberBase.getMemberNo());
			else
				personMemberBase.setMemberNo(GUID.generateByUUID());

			personMemberBase.setMemberName(addPersonMemberBase.getMemberName());
			personMemberBase.setStatus(StringUtils.toStringNull(addPersonMemberBase.getStatus()));
			personMemberBase.setCertTypeCode(addPersonMemberBase.getCertTypeCode());
			personMemberBase.setCertNo(addPersonMemberBase.getCertNo());
			personMemberBase.setMobile(addPersonMemberBase.getMobile());
			personMemberBase.setImei(addPersonMemberBase.getImei());
			personMemberBase.setEmail(addPersonMemberBase.getEmail());
			personMemberBase.setJob(addPersonMemberBase.getJob());
			personMemberBase.setAddress(addPersonMemberBase.getAddress());
			personMemberBase.setAge(addPersonMemberBase.getAge());
			personMemberBase.setSex(addPersonMemberBase.getSex());
			personMemberBase.setNameAuthFlag(StringUtils.toStringNull(addPersonMemberBase.getNameAuthFlag()));
			personMemberBase.setPwd(addPersonMemberBase.getPwd());
			personMemberBase.setEncryptionCode(addPersonMemberBase.getEncryptionCode());
			personMemberBase.setMemberSrc(addPersonMemberBase.getMemberSrc());
			personMemberBase.setOpenIdGzhWx(addPersonMemberBase.getOpenIdGzhWx());
			personMemberBase.setOpenIdXcxWx(addPersonMemberBase.getOpenIdXcxWx());
			personMemberBase.setNoWx(addPersonMemberBase.getNoWx());
			personMemberBase.setNoWxAlias(addPersonMemberBase.getNoWxAlias());
			personMemberBase.setNickNameWx(addPersonMemberBase.getNickNameWx());
			personMemberBase.setCityWx(addPersonMemberBase.getCityWx());
			personMemberBase.setCountryWx(addPersonMemberBase.getCountryWx());
			personMemberBase.setProvinceWx(addPersonMemberBase.getProvinceWx());
			personMemberBase.setHeadAddress(addPersonMemberBase.getHeadAddress());
			personMemberBase.setSubsribeTime(addPersonMemberBase.getSubsribeTime());
			personMemberBase.setFamilyCode(addPersonMemberBase.getFamilyCode());
			personMemberBase.setFamilyName(addPersonMemberBase.getFamilyName());
			personMemberBase.setInterest(addPersonMemberBase.getInterest());
			personMemberBase.setAreaCode(addPersonMemberBase.getAreaCode());
			personMemberBase.setAreaName(addPersonMemberBase.getAreaName());
			personMemberBase.setProvinceCode(addPersonMemberBase.getProvinceCode());
			personMemberBase.setCityCode(addPersonMemberBase.getCityCode());
			personMemberBase.setCityAreaCode(addPersonMemberBase.getCityAreaCode());
			personMemberBase.setCreateId(addPersonMemberBase.getCreateId());
			personMemberBase.setCreateDate(new Date());
			personMemberBase.setNoWw(addPersonMemberBase.getNoWw());
			personMemberBaseDao.insertSelective(personMemberBase);
			AddPersonMemberBaseReturn addPersonMemberBaseReturn = new AddPersonMemberBaseReturn();
			addPersonMemberBaseReturn.setMemberNo(personMemberBase.getMemberNo());
			logger.debug("addPersonMemberBase(AddPersonMemberBase) - end - return value={}", addPersonMemberBaseReturn); 
			return addPersonMemberBaseReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增客户会员基础信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_ADD_ERROR,"新增客户会员基础信息错误！",e);
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPersonMemberBaseService#delPersonMemberBase(com.lj.business.member.dto.DelPersonMemberBase)
	 */
	@Override
	public DelPersonMemberBaseReturn delPersonMemberBase(
			DelPersonMemberBase delPersonMemberBase) throws TsfaServiceException {
		logger.debug("delPersonMemberBase(DelPersonMemberBase delPersonMemberBase={}) - start", delPersonMemberBase); 

		AssertUtils.notNull(delPersonMemberBase);
		AssertUtils.notNull(delPersonMemberBase.getCode(),"ID不能为空！");
		try {
			personMemberBaseDao.deleteByPrimaryKey(delPersonMemberBase.getCode());
			DelPersonMemberBaseReturn delPersonMemberBaseReturn  = new DelPersonMemberBaseReturn();

			logger.debug("delPersonMemberBase(DelPersonMemberBase) - end - return value={}", delPersonMemberBaseReturn); 
			return delPersonMemberBaseReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除客户会员基础表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_DEL_ERROR,"删除客户会员基础表信息错误！",e);

		}
	}

	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPersonMemberBaseService#updatePersonMemberBase(com.lj.business.member.dto.UpdatePersonMemberBase)
	 */
	@Override
	public UpdatePersonMemberBaseReturn updatePersonMemberBase(
			UpdatePersonMemberBase updatePersonMemberBase)
			throws TsfaServiceException {
		logger.debug("updatePersonMemberBase(UpdatePersonMemberBase updatePersonMemberBase={}) - start", updatePersonMemberBase); 

		AssertUtils.notNull(updatePersonMemberBase);
		AssertUtils.notNullAndEmpty(updatePersonMemberBase.getCode(),"CODE不能为空");
		try {
			PersonMemberBase personMemberBase = new PersonMemberBase();
			//update数据录入
			personMemberBase.setCode(updatePersonMemberBase.getCode());
			personMemberBase.setMemberNo(updatePersonMemberBase.getMemberNo());
			personMemberBase.setMemberName(updatePersonMemberBase.getMemberName());
			personMemberBase.setStatus(updatePersonMemberBase.getStatus());
			personMemberBase.setCertTypeCode(updatePersonMemberBase.getCertTypeCode());
			personMemberBase.setCertNo(updatePersonMemberBase.getCertNo());
			personMemberBase.setMobile(updatePersonMemberBase.getMobile());
			personMemberBase.setAddMobileDate(updatePersonMemberBase.getAddMobileDate());
			personMemberBase.setEmail(updatePersonMemberBase.getEmail());
			personMemberBase.setJob(updatePersonMemberBase.getJob());
			personMemberBase.setAddress(updatePersonMemberBase.getAddress());
			personMemberBase.setAge(updatePersonMemberBase.getAge());
			personMemberBase.setSex(updatePersonMemberBase.getSex());
			personMemberBase.setNameAuthFlag(updatePersonMemberBase.getNameAuthFlag());
			personMemberBase.setPwd(updatePersonMemberBase.getPwd());
			personMemberBase.setEncryptionCode(updatePersonMemberBase.getEncryptionCode());
			personMemberBase.setMemberSrc(updatePersonMemberBase.getMemberSrc());
			personMemberBase.setOpenIdGzhWx(updatePersonMemberBase.getOpenIdGzhWx());
			personMemberBase.setOpenIdXcxWx(updatePersonMemberBase.getOpenIdXcxWx());
			personMemberBase.setNoWx(updatePersonMemberBase.getNoWx());
			personMemberBase.setNoWxAlias(updatePersonMemberBase.getNoWxAlias());
			personMemberBase.setNickNameWx(updatePersonMemberBase.getNickNameWx());
			personMemberBase.setCityWx(updatePersonMemberBase.getCityWx());
			personMemberBase.setCountryWx(updatePersonMemberBase.getCountryWx());
			personMemberBase.setProvinceWx(updatePersonMemberBase.getProvinceWx());
			personMemberBase.setHeadAddress(updatePersonMemberBase.getHeadAddress());
			personMemberBase.setSubsribeTime(updatePersonMemberBase.getSubsribeTime());
			personMemberBase.setCityAreaCode(updatePersonMemberBase.getCityAreaCode());
			personMemberBase.setUpdateId(updatePersonMemberBase.getUpdateId());
			personMemberBase.setUpdateDate(new Date());
			personMemberBase.setBirthday(updatePersonMemberBase.getBirthday());
			personMemberBase.setAreaCode(updatePersonMemberBase.getAreaCode());
			personMemberBase.setAreaName(updatePersonMemberBase.getAreaName());
			personMemberBase.setCertTypeCode(updatePersonMemberBase.getCertTypeCode());
			personMemberBase.setCityAreaCode(updatePersonMemberBase.getCityAreaCode());
			personMemberBase.setCityCode(updatePersonMemberBase.getCityCode());
			personMemberBase.setProvinceCode(updatePersonMemberBase.getProvinceCode());
			personMemberBase.setRatioClientInfo(updatePersonMemberBase.getRatioClientInfo());
			personMemberBase.setConstellation(updatePersonMemberBase.getConstellation());
			personMemberBase.setWxOpenId(updatePersonMemberBase.getWxOpenId());
			personMemberBase.setNoWw(updatePersonMemberBase.getNoWw());
			personMemberBase.setOrderNo(updatePersonMemberBase.getOrderNo());
			AssertUtils.notUpdateMoreThanOne(personMemberBaseDao.updateByPrimaryKeySelective(personMemberBase));
			UpdatePersonMemberBaseReturn updatePersonMemberBaseReturn = new UpdatePersonMemberBaseReturn();

			logger.debug("updatePersonMemberBase(UpdatePersonMemberBase) - end - return value={}", updatePersonMemberBaseReturn); 
			return updatePersonMemberBaseReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("客户会员基础表信息更新错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_UPDATE_ERROR,"客户会员基础表信息更新错误！",e);
		}
	}

	/**
	 * 用户设置置顶
	 */
	public void updateSetUpUser(PersonMemberBase personMemberBase){
		try {
		     AssertUtils.notUpdateMoreThanOne(personMemberBaseDao.updateSetUpUser(personMemberBase));
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("客户会员基础表信息更新错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_UPDATE_ERROR,"客户会员用户设置取消置顶更新错误！",e);
		}
	}
	
	/**
	 * 用户设置取消置顶
	 */
	public void updateCancleSetUpUser(PersonMemberBase personMemberBase){
		try {
		     AssertUtils.notUpdateMoreThanOne(personMemberBaseDao.updateCancleSetUpUser(personMemberBase));
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("客户会员基础表信息更新错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_UPDATE_ERROR,"客户会员用户设置取消置顶更新错误！",e);
		}
	}

	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPersonMemberBaseService#findPersonMemberBase(com.lj.business.member.dto.FindPersonMemberBase)
	 */
	@Override
	public FindPersonMemberBaseReturn findPersonMemberBase(
			FindPersonMemberBase findPersonMemberBase) throws TsfaServiceException {
		logger.debug("findPersonMemberBase(FindPersonMemberBase findPersonMemberBase={}) - start", findPersonMemberBase); 
		AssertUtils.notNull(findPersonMemberBase);
//		if(StringUtils.isEmpty(findPersonMemberBase.getCode()) &&  StringUtils.isEmpty(findPersonMemberBase.getMemberNo()) 
//				&&  StringUtils.isEmpty(findPersonMemberBase.getMemberNoGm()) &&  StringUtils.isEmpty(findPersonMemberBase.getMerchantNo())
//				&&  StringUtils.isEmpty(findPersonMemberBase.getMobile()) &&  StringUtils.isEmpty(findPersonMemberBase.getNoWx())
//				&&  StringUtils.isEmpty(findPersonMemberBase.getNoQq())){
//			throw new IllegalArgumentException("参数不能全部为空！");
//		}
		
		try {
			PersonMemberBase personMemberBase = personMemberBaseDao.selectByParams(findPersonMemberBase);
			if(personMemberBase != null){
				/*throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_NOT_EXIST_ERROR,"客户会员基础表信息不存在错误");*/
			FindPersonMemberBaseReturn findPersonMemberBaseReturn = new FindPersonMemberBaseReturn();
			//find数据录入
			findPersonMemberBaseReturn.setCode(personMemberBase.getCode());
			findPersonMemberBaseReturn.setMemberNo(personMemberBase.getMemberNo());
			findPersonMemberBaseReturn.setMemberName(personMemberBase.getMemberName());
			findPersonMemberBaseReturn.setStatus(personMemberBase.getStatus());
			findPersonMemberBaseReturn.setCertTypeCode(personMemberBase.getCertTypeCode());
			findPersonMemberBaseReturn.setCertNo(personMemberBase.getCertNo());
			findPersonMemberBaseReturn.setMobile(personMemberBase.getMobile());
			findPersonMemberBaseReturn.setEmail(personMemberBase.getEmail());
			findPersonMemberBaseReturn.setJob(personMemberBase.getJob());
			findPersonMemberBaseReturn.setAddress(personMemberBase.getAddress());
			findPersonMemberBaseReturn.setAge(personMemberBase.getAge());
			findPersonMemberBaseReturn.setSex(personMemberBase.getSex());
			findPersonMemberBaseReturn.setNameAuthFlag(personMemberBase.getNameAuthFlag());
			findPersonMemberBaseReturn.setPwd(personMemberBase.getPwd());
			findPersonMemberBaseReturn.setEncryptionCode(personMemberBase.getEncryptionCode());
			findPersonMemberBaseReturn.setMemberSrc(personMemberBase.getMemberSrc());
			findPersonMemberBaseReturn.setOpenIdGzhWx(personMemberBase.getOpenIdGzhWx());
			findPersonMemberBaseReturn.setOpenIdXcxWx(personMemberBase.getOpenIdXcxWx());
			findPersonMemberBaseReturn.setNoWx(personMemberBase.getNoWx());
			findPersonMemberBaseReturn.setNoWxAlias(personMemberBase.getNoWxAlias());
			findPersonMemberBaseReturn.setNickNameWx(personMemberBase.getNickNameWx());
			findPersonMemberBaseReturn.setCityWx(personMemberBase.getCityWx());
			findPersonMemberBaseReturn.setCountryWx(personMemberBase.getCountryWx());
			findPersonMemberBaseReturn.setProvinceWx(personMemberBase.getProvinceWx());
			findPersonMemberBaseReturn.setHeadAddress(personMemberBase.getHeadAddress());
			findPersonMemberBaseReturn.setSubsribeTime(personMemberBase.getSubsribeTime());
			findPersonMemberBaseReturn.setCityAreaCode(personMemberBase.getCityAreaCode());
			findPersonMemberBaseReturn.setCreateId(personMemberBase.getCreateId());
			findPersonMemberBaseReturn.setCreateDate(personMemberBase.getCreateDate());
			findPersonMemberBaseReturn.setUpdateId(personMemberBase.getUpdateId());
			findPersonMemberBaseReturn.setUpdateDate(personMemberBase.getUpdateDate());
			findPersonMemberBaseReturn.setBirthday(personMemberBase.getBirthday());
			findPersonMemberBaseReturn.setRatioClientInfo(personMemberBase.getRatioClientInfo());
			findPersonMemberBaseReturn.setWxOpenId(personMemberBase.getWxOpenId());//返回唯一WX_OPENID
			findPersonMemberBaseReturn.setNoQQ(personMemberBase.getNoQQ());//返回QQ号
			findPersonMemberBaseReturn.setNoWw(personMemberBase.getNoWw());//返回ww号
			findPersonMemberBaseReturn.setOrderNo(personMemberBase.getOrderNo());	//返回淘宝订单号由H5录入
			logger.debug("findPersonMemberBase(FindPersonMemberBase) - end - return value={}", findPersonMemberBaseReturn); 
			return findPersonMemberBaseReturn;
		}
			return null;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户会员基础表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_ERROR,"查找客户会员基础表信息错误！",e);
		}
	}

	
	@Override
	public FindPersonMemberBaseReturn findPersonMemberBaseParams(
			FindPersonMemberBase findPersonMemberBase) throws TsfaServiceException {
		logger.debug("findPersonMemberBase(FindPersonMemberBase findPersonMemberBase={}) - start", findPersonMemberBase); 
		AssertUtils.notNull(findPersonMemberBase);
		AssertUtils.notNull(findPersonMemberBase.getMemberNoGm(),"导购编号不能为空！");
		try {
			PersonMemberBase personMemberBase = personMemberBaseDao.findPersonMemberBaseParams(findPersonMemberBase);
			if(personMemberBase == null){
				throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_NOT_EXIST_ERROR,"客户会员基础表信息不存在错误");
			}
			FindPersonMemberBaseReturn findPersonMemberBaseReturn = new FindPersonMemberBaseReturn();
			//find数据录入
			findPersonMemberBaseReturn.setMemberNo(personMemberBase.getMemberNo());
			findPersonMemberBaseReturn.setMemberName(personMemberBase.getMemberName());
			findPersonMemberBaseReturn.setStatus(personMemberBase.getStatus());
			findPersonMemberBaseReturn.setCertTypeCode(personMemberBase.getCertTypeCode());
			findPersonMemberBaseReturn.setCertNo(personMemberBase.getCertNo());
			findPersonMemberBaseReturn.setMobile(personMemberBase.getMobile());
			findPersonMemberBaseReturn.setEmail(personMemberBase.getEmail());
			findPersonMemberBaseReturn.setJob(personMemberBase.getJob());
			findPersonMemberBaseReturn.setAddress(personMemberBase.getAddress());
			findPersonMemberBaseReturn.setAge(personMemberBase.getAge());
			findPersonMemberBaseReturn.setSex(personMemberBase.getSex());
			findPersonMemberBaseReturn.setNameAuthFlag(personMemberBase.getNameAuthFlag());
			findPersonMemberBaseReturn.setPwd(personMemberBase.getPwd());
			findPersonMemberBaseReturn.setEncryptionCode(personMemberBase.getEncryptionCode());
			findPersonMemberBaseReturn.setMemberSrc(personMemberBase.getMemberSrc());
			findPersonMemberBaseReturn.setOpenIdGzhWx(personMemberBase.getOpenIdGzhWx());
			findPersonMemberBaseReturn.setOpenIdXcxWx(personMemberBase.getOpenIdXcxWx());
			findPersonMemberBaseReturn.setNoWx(personMemberBase.getNoWx());
			findPersonMemberBaseReturn.setNoWxAlias(personMemberBase.getNoWxAlias());
			findPersonMemberBaseReturn.setNickNameWx(personMemberBase.getNickNameWx());
			findPersonMemberBaseReturn.setCityWx(personMemberBase.getCityWx());
			findPersonMemberBaseReturn.setCountryWx(personMemberBase.getCountryWx());
			findPersonMemberBaseReturn.setProvinceWx(personMemberBase.getProvinceWx());
			findPersonMemberBaseReturn.setHeadAddress(personMemberBase.getHeadAddress());
			findPersonMemberBaseReturn.setSubsribeTime(personMemberBase.getSubsribeTime());
			findPersonMemberBaseReturn.setCityAreaCode(personMemberBase.getCityAreaCode());
			findPersonMemberBaseReturn.setCreateId(personMemberBase.getCreateId());
			findPersonMemberBaseReturn.setCreateDate(personMemberBase.getCreateDate());
			findPersonMemberBaseReturn.setUpdateId(personMemberBase.getUpdateId());
			findPersonMemberBaseReturn.setUpdateDate(personMemberBase.getUpdateDate());
			findPersonMemberBaseReturn.setBirthday(personMemberBase.getBirthday());
			findPersonMemberBaseReturn.setRatioClientInfo(personMemberBase.getRatioClientInfo());
			findPersonMemberBaseReturn.setNickNameRemarkLocal(personMemberBase.getNickNameRemarkLocal());
			findPersonMemberBaseReturn.setNoWw(personMemberBase.getNoWw());//返回ww号
			logger.debug("findPersonMemberBase(FindPersonMemberBase) - end - return value={}", findPersonMemberBaseReturn); 
			return findPersonMemberBaseReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户会员基础表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_ERROR,"查找客户会员基础表信息错误！",e);
		}
	}

	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPersonMemberBaseService#findPersonMemberBasePage(com.lj.business.member.dto.FindPersonMemberBasePage)
	 */
	@Override
	public Page<FindPersonMemberBasePageReturn> findPersonMemberBasePage(
			FindPersonMemberBasePage findPersonMemberBasePage)
			throws TsfaServiceException {
		logger.debug("findPersonMemberBasePage(FindPersonMemberBasePage findPersonMemberBasePage={}) - start", findPersonMemberBasePage); 

		AssertUtils.notNull(findPersonMemberBasePage);
		List<FindPersonMemberBasePageReturn> returnList=null;
		int count = 0;
		try {
			returnList = personMemberBaseDao.findPersonMemberBasePage(findPersonMemberBasePage);
			count = personMemberBaseDao.findPersonMemberBasePageCount(findPersonMemberBasePage);
		}  catch (Exception e) {
			logger.error("客户会员基础表信息分页查询错误",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_PAGE_ERROR,"客户会员基础表信息分页查询错误.！",e);
		}
		Page<FindPersonMemberBasePageReturn> returnPage = new Page<FindPersonMemberBasePageReturn>(returnList, count, findPersonMemberBasePage);

		logger.debug("findPersonMemberBasePage(FindPersonMemberBasePage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPersonMemberBaseService#findByMobile(java.lang.String)
	 */
	@Override
	public FindPersonMemberBaseReturn findByMobile(FindPersonMemberBase findPersonMemberBase) {

		AssertUtils.notNull(findPersonMemberBase);
		AssertUtils.notNullAndEmpty(findPersonMemberBase.getMobile());
		PersonMemberBase personMemberBase=null;
		FindPersonMemberBaseReturn findPersonMemberBaseReturn = new FindPersonMemberBaseReturn();
		try {
			personMemberBase = personMemberBaseDao.findByMobile(findPersonMemberBase);
	 			//find数据录入
	 			if(personMemberBase!=null){
	 				findPersonMemberBaseReturn.setCode(personMemberBase.getCode());
		 			findPersonMemberBaseReturn.setMemberNo(personMemberBase.getMemberNo());
		 			findPersonMemberBaseReturn.setMemberName(personMemberBase.getMemberName());
		 			findPersonMemberBaseReturn.setStatus(personMemberBase.getStatus());
		 			findPersonMemberBaseReturn.setCertTypeCode(personMemberBase.getCertTypeCode());
		 			findPersonMemberBaseReturn.setCertNo(personMemberBase.getCertNo());
		 			findPersonMemberBaseReturn.setMobile(personMemberBase.getMobile());
		 			findPersonMemberBaseReturn.setEmail(personMemberBase.getEmail());
		 			findPersonMemberBaseReturn.setJob(personMemberBase.getJob());
		 			findPersonMemberBaseReturn.setAddress(personMemberBase.getAddress());
		 			findPersonMemberBaseReturn.setAge(personMemberBase.getAge());
		 			findPersonMemberBaseReturn.setSex(personMemberBase.getSex());
		 			findPersonMemberBaseReturn.setNameAuthFlag(personMemberBase.getNameAuthFlag());
		 			findPersonMemberBaseReturn.setPwd(personMemberBase.getPwd());
		 			findPersonMemberBaseReturn.setEncryptionCode(personMemberBase.getEncryptionCode());
		 			findPersonMemberBaseReturn.setMemberSrc(personMemberBase.getMemberSrc());
		 			findPersonMemberBaseReturn.setOpenIdGzhWx(personMemberBase.getOpenIdGzhWx());
		 			findPersonMemberBaseReturn.setOpenIdXcxWx(personMemberBase.getOpenIdXcxWx());
		 			findPersonMemberBaseReturn.setNoWx(personMemberBase.getNoWx());
		 			findPersonMemberBaseReturn.setNoWxAlias(personMemberBase.getNoWxAlias());
		 			findPersonMemberBaseReturn.setNickNameWx(personMemberBase.getNickNameWx());
		 			findPersonMemberBaseReturn.setCityWx(personMemberBase.getCityWx());
		 			findPersonMemberBaseReturn.setCountryWx(personMemberBase.getCountryWx());
		 			findPersonMemberBaseReturn.setProvinceWx(personMemberBase.getProvinceWx());
		 			findPersonMemberBaseReturn.setHeadAddress(personMemberBase.getHeadAddress());
		 			findPersonMemberBaseReturn.setSubsribeTime(personMemberBase.getSubsribeTime());
		 			findPersonMemberBaseReturn.setCityAreaCode(personMemberBase.getCityAreaCode());
		 			findPersonMemberBaseReturn.setCreateId(personMemberBase.getCreateId());
		 			findPersonMemberBaseReturn.setCreateDate(personMemberBase.getCreateDate());
		 			findPersonMemberBaseReturn.setUpdateId(personMemberBase.getUpdateId());
		 			findPersonMemberBaseReturn.setUpdateDate(personMemberBase.getUpdateDate());
		 			findPersonMemberBaseReturn.setBirthday(personMemberBase.getBirthday());
		 			findPersonMemberBaseReturn.setNoWw(personMemberBase.getNoWw());//返回ww号
		 			return findPersonMemberBaseReturn;
	 			}else {
	 				return null;
				}
		   } catch (NullPointerException e) {
			logger.error("客户会员基础信息查询错误",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_PAGE_ERROR,"客户会员基础信息查询错误.！",e);
		}
		
	}

	@Override
	public List<FindPersonMemberName> findByCodeList(List<String> codeList) {
		AssertUtils.notNull(codeList);

		try {
			return this.personMemberBaseDao.findByCodeList(codeList);
		} catch (Exception e) {
			logger.error("客户会员基础信息查询错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_PAGE_ERROR, "客户会员基础信息查询错误.！", e);
		}
	}


	@Override
	public FindPersonMemberBaseReturnList findPersonMemberBaseList(
			FindPersonMemberBaseList findPersonMemberBaseList)throws TsfaServiceException {
		logger.debug("findPersonMemberBaseList(FindPersonMemberBaseList findPersonMemberBaseList={}) - start", findPersonMemberBaseList); 
		AssertUtils.notNull(findPersonMemberBaseList);
		FindPersonMemberBaseReturnList list=null;
		try {
			list=personMemberBaseDao.findPersonMemberBaseList(findPersonMemberBaseList);
		} catch (Exception e) {
			logger.error("客户会员基础信息查询错误",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_PAGE_ERROR,"客户会员基础信息查询错误.！",e);
		}
		return list;
	}


	@Override
	public FindPersonMemberBaseReturnList findPersonMemberBaseCounts(
			FindPersonMemberBaseList findPersonMemberBaseList)
			throws TsfaServiceException {
		AssertUtils.notNull(findPersonMemberBaseList);
		FindPersonMemberBaseReturnList findPersonMemberBaseReturnList=null;
		try {
			findPersonMemberBaseReturnList=personMemberBaseDao.findPersonMemberBaseCounts(findPersonMemberBaseList);
		} catch (Exception e) {
			logger.error("客户会员基础信息查询错误",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_PAGE_ERROR,"客户会员基础信息查询错误.！",e);
		}
		return findPersonMemberBaseReturnList;
	}


	@Override
	public void updatePersonMemberBaseRatioClientInfo(UpdatePersonMemberBaseRatioClientInfoDto dto) throws TsfaServiceException {
		AssertUtils.notNull(dto);
		try{
			personMemberBaseDao.updateRatioClientInfoByMemberNo(dto);
		} catch (Exception e) {
			logger.error("客户会员基础表信息更新错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_UPDATE_ERROR, "客户会员基础表信息更新错误！", e);
		}
	}


	  @Override
	  public FindPersonMemberBaseReturnList findPersonMemberMax()
			throws TsfaServiceException {
		logger.debug("findPersonMemberMax() - start"); 
		FindPersonMemberBaseReturnList findPersonMemberBaseReturnList=null;
		try {
			findPersonMemberBaseReturnList=personMemberBaseDao.findPersonMemberMax();
		} catch (Exception e) {
			logger.error("客户会员基础信息查询错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_PAGE_ERROR, "客户会员基础信息查询错误.！", e);
		}
		
		return findPersonMemberBaseReturnList;
	}


	@Override
	public List<FindPersonMemberBaseList> findPersonMemberBaseMemberCount(FindPersonMemberBase findPersonMemberBase)
			throws TsfaServiceException {
		logger.debug("findPersonMemberBaseMemberCount() - start"); 
		List<FindPersonMemberBaseList> findPersonMemberBaseList = null;
		try {
			findPersonMemberBaseList=personMemberBaseDao.findPersonMemberBaseMemberCount(findPersonMemberBase);
		} catch (Exception e) {
			logger.error("客户会员基础信息查询错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_PAGE_ERROR, "客户会员基础信息查询错误.！", e);
		}
		return findPersonMemberBaseList;
	}


	@Override
	public int findPersonMemberBaseNumAdd(
			FindPersonMemberBaseList findPersonMemberBaseList)
			throws TsfaServiceException {
		logger.debug("findPersonMemberBaseMemberCount() - start"); 
		AssertUtils.notNull(findPersonMemberBaseList);
		int count=0;
		try {
			count=personMemberBaseDao.findPersonMemberBaseNumAdd(findPersonMemberBaseList);
		} catch (Exception e) {
			logger.error("客户会员基础信息客户数量查询错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_PAGE_ERROR, "客户会员基础信息客户数量查询错误.！", e);
		}
		return count;
	}



	@Override
	public PersonMemberBase findMobileAndCode(
			FindPersonMemberBase findPersonMemberBase) {
		logger.debug("findMobileAndCode(FindPersonMemberBase findPersonMemberBase={}) - start", findPersonMemberBase);
		return personMemberBaseDao.findMobileAndCode(findPersonMemberBase);
	}



	@Override
	public FindPersonMemberBaseReturnList findPersonMemberBaseNums(
			FindPersonMemberBaseList personMemberBaseList)throws TsfaServiceException {
		AssertUtils.notNull(personMemberBaseList.getMerchantNo(),"商户编号不能为空！");
		List<FindPersonMemberBaseReturnList>  list =null;
		 FindPersonMemberBaseReturnList returnList = new FindPersonMemberBaseReturnList();
		try {
			int  intention = 0 , intentionN = 0 , success = 0;
			list=personMemberBaseDao.findPersonMemberBaseNums(personMemberBaseList);
			if(list.size()>0){
				for(FindPersonMemberBaseReturnList baseReturnList:list){
					
					if("INTENTION".equals(baseReturnList.getPmTypeType())){
						  intention +=baseReturnList.getMemberNo();
					}
					if("INTENTION_N".equals(baseReturnList.getPmTypeType())){
						  intentionN +=baseReturnList.getMemberNo();
					}
					if("SUCCESS".equals(baseReturnList.getPmTypeType())){
						success=baseReturnList.getMemberNo();
					}
					returnList.setMemberNoNum(baseReturnList.getMemberNoNum());
				}
				returnList.setIntentionNum(intention+intentionN);
				returnList.setSuccessNum(success);
				
			}
		} catch (Exception e) {
			logger.error("客户会员基础信息客户数量查询错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_PAGE_ERROR, "客户会员基础信息客户数量查询错误.！", e);
		}
		return returnList;
	}



	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPersonMemberBaseService#updatePersonMemberMobile(com.lj.business.member.dto.UpdatePersonMemberBase)
	 */
	@Override
	public UpdatePersonMemberBaseReturn updatePersonMemberMobile(UpdatePersonMemberBase updatePersonMemberBase) throws TsfaServiceException {
		logger.debug("updatePersonMemberMobile(UpdatePersonMemberBase updatePersonMemberBase={}) - start", updatePersonMemberBase); 

		AssertUtils.notNull(updatePersonMemberBase);
		AssertUtils.notNullAndEmpty(updatePersonMemberBase.getMemberNo(),"客户编号不能为空");
		AssertUtils.notNullAndEmpty(updatePersonMemberBase.getMobile(),"手机号不能为空");
		try {
			FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
			findPersonMemberBase.setMobile(updatePersonMemberBase.getMobile());
			PersonMemberBase personMemberBase = personMemberBaseDao.findByMobile(findPersonMemberBase);
			if(personMemberBase != null) {
				if(personMemberBase.getMemberNo().equals(updatePersonMemberBase.getMemberNo())) {
					logger.info("修改手机号与客户原手机号一致，不用修改");

					UpdatePersonMemberBaseReturn updatePersonMemberBaseReturn = new UpdatePersonMemberBaseReturn();
					logger.debug("updatePersonMemberMobile(UpdatePersonMemberBase) - end - return value={}", updatePersonMemberBaseReturn); 
					return updatePersonMemberBaseReturn;
				} else {
					throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_MOBILE_EXIST, "已有客户存在该手机号");
				}
			}
			
			//update数据录入
			personMemberBaseDao.updateMobile(updatePersonMemberBase);
			
			//同步修改PM表数据
			if(StringUtils.isNotEmpty(updatePersonMemberBase.getMemberName())) {
				PersonMember record = new PersonMember();
				record.setMemberNo(updatePersonMemberBase.getMemberNo());
				record.setMemberName(updatePersonMemberBase.getMemberName());
				personMemberDao.updatePersonMemberByCond(record);
			}
			UpdatePersonMemberBaseReturn updatePersonMemberBaseReturn = new UpdatePersonMemberBaseReturn();

			logger.debug("updatePersonMemberMobile(UpdatePersonMemberBase) - end - return value={}", updatePersonMemberBaseReturn); 
			return updatePersonMemberBaseReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("修改客户手机号错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_UPDATE_ERROR,"修改客户手机号错误！",e);
		}
	}

	@Override
	public UpdatePersonMemberBaseReturn updatePersonMemberWxInfoByNoWx(UpdatePersonMemberBase updatePersonMemberBase) throws TsfaServiceException {
		logger.debug("updatePersonMemberWxInfoByNoWx(UpdatePersonMemberBase updatePersonMemberBase={}) - start", updatePersonMemberBase); 

		AssertUtils.notNull(updatePersonMemberBase);
		AssertUtils.notNullAndEmpty(updatePersonMemberBase.getNoWx(),"客户微信不能为空");
		try {
			personMemberBaseDao.updatePersonMemberWxInfoByNoWx(updatePersonMemberBase);
			UpdatePersonMemberBaseReturn updatePersonMemberBaseReturn = new UpdatePersonMemberBaseReturn();

			logger.debug("updatePersonMemberWxInfoByNoWx(UpdatePersonMemberBase) - end - return value={}", updatePersonMemberBaseReturn); 
			return updatePersonMemberBaseReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("根据客户微信号修改客户微信基本信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_UPDATE_ERROR,"根据客户微信号修改客户微信基本信息错误！",e);
		}
	}



	@Override
	public Map<String, Object> getBaseInfoByMobile(Map<String, Object> map) {
		return personMemberBaseDao.getBaseInfoByMobile(map);
	}



	@Override
	public boolean initWxOpenIdByMerchantNo(String merchantNo) {
		logger.debug("initWxOpenIdByMerchantNo(String merchantNo={}) - start", merchantNo); 
		try {
			FindPersonMemberPage findPersonMemberPage = new FindPersonMemberPage();
			findPersonMemberPage.setMerchantNo(merchantNo);//商户编号
			List<FindPersonMemberPageReturn> list = personMemberDao.findPersonMemberList(findPersonMemberPage);
			if(list!=null && list.size()>0) {
				for (FindPersonMemberPageReturn dto : list) {
					String oldNoWx = dto.getNoWx();
					if(StringUtils.isEmpty(oldNoWx)) continue;
					UpdatePersonMemberBase updatePersonMemberBase = new UpdatePersonMemberBase();
					String wxOpenId = com.lj.base.core.util.WxOpenIdUtils.generateWxOpenId(oldNoWx);
					updatePersonMemberBase.setWxOpenId(wxOpenId);//设置wxOpenId
					updatePersonMemberBase.setNoWx(dto.getNoWx());//根据WX号修改
					personMemberBaseDao.updatePersonMemberWxInfoByNoWx(updatePersonMemberBase);
				}
			}
		} catch (TsfaServiceException e) {
			logger.error("initWxOpenIdByMerchantNo(String)", e);
			return false;
		}
		logger.debug("initWxOpenIdByMerchantNo(String) - end - return value={}", true); 
		return true;
	}

	@Override
	public FindPersonMemberBaseReturn findMemberBaseByNoWxOrAlias(String noWx, String alias) {
		logger.debug("findMemberBaseByNoWxOrAlias(String noWx={}, String alias={}) - start", noWx, alias); 

		AssertUtils.notAllNullAndEmpty(noWx, alias, "客户微信和别名不能同时为空");
		
		FindPersonMemberBaseReturn findPersonMemberBaseReturn = null;
		try {
			PersonMemberBase personMemberBase = personMemberBaseDao.findMemberBaseByNoWxOrAlias(noWx, alias);
			if(personMemberBase != null){
				findPersonMemberBaseReturn = new FindPersonMemberBaseReturn();
				//find数据录入
				findPersonMemberBaseReturn.setCode(personMemberBase.getCode());
				findPersonMemberBaseReturn.setMemberNo(personMemberBase.getMemberNo());
				findPersonMemberBaseReturn.setMemberName(personMemberBase.getMemberName());
				findPersonMemberBaseReturn.setStatus(personMemberBase.getStatus());
				findPersonMemberBaseReturn.setCertTypeCode(personMemberBase.getCertTypeCode());
				findPersonMemberBaseReturn.setCertNo(personMemberBase.getCertNo());
				findPersonMemberBaseReturn.setMobile(personMemberBase.getMobile());
				findPersonMemberBaseReturn.setEmail(personMemberBase.getEmail());
				findPersonMemberBaseReturn.setJob(personMemberBase.getJob());
				findPersonMemberBaseReturn.setAddress(personMemberBase.getAddress());
				findPersonMemberBaseReturn.setAge(personMemberBase.getAge());
				findPersonMemberBaseReturn.setSex(personMemberBase.getSex());
				findPersonMemberBaseReturn.setNameAuthFlag(personMemberBase.getNameAuthFlag());
				findPersonMemberBaseReturn.setPwd(personMemberBase.getPwd());
				findPersonMemberBaseReturn.setEncryptionCode(personMemberBase.getEncryptionCode());
				findPersonMemberBaseReturn.setMemberSrc(personMemberBase.getMemberSrc());
				findPersonMemberBaseReturn.setOpenIdGzhWx(personMemberBase.getOpenIdGzhWx());
				findPersonMemberBaseReturn.setOpenIdXcxWx(personMemberBase.getOpenIdXcxWx());
				findPersonMemberBaseReturn.setNoWx(personMemberBase.getNoWx());
				findPersonMemberBaseReturn.setNoWxAlias(personMemberBase.getNoWxAlias());
				findPersonMemberBaseReturn.setNickNameWx(personMemberBase.getNickNameWx());
				findPersonMemberBaseReturn.setCityWx(personMemberBase.getCityWx());
				findPersonMemberBaseReturn.setCountryWx(personMemberBase.getCountryWx());
				findPersonMemberBaseReturn.setProvinceWx(personMemberBase.getProvinceWx());
				findPersonMemberBaseReturn.setHeadAddress(personMemberBase.getHeadAddress());
				findPersonMemberBaseReturn.setSubsribeTime(personMemberBase.getSubsribeTime());
				findPersonMemberBaseReturn.setCityAreaCode(personMemberBase.getCityAreaCode());
				findPersonMemberBaseReturn.setCreateId(personMemberBase.getCreateId());
				findPersonMemberBaseReturn.setCreateDate(personMemberBase.getCreateDate());
				findPersonMemberBaseReturn.setUpdateId(personMemberBase.getUpdateId());
				findPersonMemberBaseReturn.setUpdateDate(personMemberBase.getUpdateDate());
				findPersonMemberBaseReturn.setBirthday(personMemberBase.getBirthday());
				findPersonMemberBaseReturn.setRatioClientInfo(personMemberBase.getRatioClientInfo());
				findPersonMemberBaseReturn.setWxOpenId(personMemberBase.getWxOpenId());//返回唯一WX_OPENID
				findPersonMemberBaseReturn.setNoWw(personMemberBase.getNoWw());//返回ww号
				findPersonMemberBaseReturn.setOrderNo(personMemberBase.getOrderNo());
			}
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("根据客户微信或别名查询客户基本信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_ERROR,"根据客户微信或别名查询客户基本信息错误！",e);
		}
		
		logger.debug("findMemberBaseByNoWxOrAlias(String, String) - end - return value={}", findPersonMemberBaseReturn); 
		return findPersonMemberBaseReturn;
	}



	@Override
	public String selectSetUp(String memberNo) {
		return personMemberBaseDao.selectSetUp(memberNo);
	}



	@Override
	public List<PersonMemberBase> findMemberBaseByMemberNos(FindPersonMemberBase personMemberBase)
			throws TsfaServiceException {
		logger.debug("findMemberBaseByMemberNos(FindPersonMemberBase personMemberBase={}) - start", personMemberBase); 
		AssertUtils.notNull(personMemberBase);
		try {
			List<PersonMemberBase> rt=personMemberBaseDao.selectByMemberNos(personMemberBase);

			logger.debug("findMemberBaseByMemberNos(FindPersonMemberBase) - end - return "); 
			return rt;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查询客户微信基本信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_UPDATE_ERROR,"根据客户微信号修改客户微信基本信息错误！",e);
		}
	}



	
	@Override
	public PersonMemberBase checkMobile(EditPersonMember editPersonMember) throws TsfaServiceException {
		logger.debug("checkMobile(EditPersonMember editPersonMember = {}) - start", editPersonMember);
		AssertUtils.notNull(editPersonMember);
		try {
			PersonMemberBase personMemberBase = personMemberBaseDao.checkMobile(editPersonMember);
			return personMemberBase;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查询客户微信基本信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_UPDATE_ERROR,"根据客户微信号修改客户微信基本信息错误！",e);
		}
		
	}

	
}
