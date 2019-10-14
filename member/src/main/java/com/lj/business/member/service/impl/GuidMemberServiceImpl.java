package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lj.base.core.encryption.MD5;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.common.MemberConstants;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IGuidMemberDao;
import com.lj.business.member.domain.GuidMember;
import com.lj.business.member.domain.LoginCheck;
import com.lj.business.member.dto.AddGuidMember;
import com.lj.business.member.dto.AddGuidMemberDto;
import com.lj.business.member.dto.AddGuidMemberReturn;
import com.lj.business.member.dto.AddLoginCheck;
import com.lj.business.member.dto.DelGuidMember;
import com.lj.business.member.dto.DelGuidMemberReturn;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindShopGmDto;
import com.lj.business.member.dto.FindShopGmDtoReturn;
import com.lj.business.member.dto.FindShopGmReturn;
import com.lj.business.member.dto.GuidInfoShop;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.dto.UpdateGuidMember;
import com.lj.business.member.dto.UpdateGuidMemberReturn;
import com.lj.business.member.dto.UpdateManagerMember;
import com.lj.business.member.dto.UpdatePwdDto;
import com.lj.business.member.dto.addfriends.FindAllotGuidMember;
import com.lj.business.member.dto.addfriends.FindAllotGuidMemberReturn;
import com.lj.business.member.dto.addfriends.FindOtherAllotGuidMember;
import com.lj.business.member.dto.im.FindGuidByShopAndMember;
import com.lj.business.member.dto.im.FindGuidByShopAndMemberReturn;
import com.lj.business.member.emus.LockStatus;
import com.lj.business.member.emus.MemberType;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.ILoginCheckService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.kms.dto.EncryptRequest;
import com.lj.kms.dto.EncryptResponse;
import com.lj.kms.service.IEncryptor;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 *         CreateDate: 2017-06-14
 */
@Service
public class GuidMemberServiceImpl implements IGuidMemberService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GuidMemberServiceImpl.class);

	/** The guid member dao. */
	@Resource
	private IGuidMemberDao guidMemberDao;

	/** The login check service. */
	@Resource
	private ILoginCheckService loginCheckService;

	/** The i encryptor. */
	@Resource
	private IEncryptor iEncryptor;

//	@Resource
//	private IUnContactTotalService unContactTotalService;

	
	@Resource
	private IManagerMemberService managerMemberService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IGuidMemberService#addGuidMember(com.lj
	 * .business.member.dto.AddGuidMember)
	 */
	@Override
	public AddGuidMemberReturn addGuidMember(AddGuidMember addGuidMember) throws TsfaServiceException {
		logger.debug("addGuidMember(AddGuidMember addGuidMember={}) - start", addGuidMember);
		AssertUtils.notNull(addGuidMember);
		try {
			GuidMember guidMember = new GuidMember();
			// add数据录入
			guidMember.setCode(GUID.getPreUUID());
			if(!StringUtils.isEmpty(addGuidMember.getMemberNo()))
				guidMember.setMemberNo(addGuidMember.getMemberNo());
			else
				guidMember.setMemberNo(GUID.generateByUUID());

			guidMember.setMemberName(addGuidMember.getMemberName());
			guidMember.setMerchantNo(addGuidMember.getMerchantNo());
			guidMember.setMerchantName(addGuidMember.getMerchantName());
			guidMember.setStatus(addGuidMember.getStatus());
			guidMember.setJobNum(addGuidMember.getJobNum());
			guidMember.setMobile(addGuidMember.getMobile());
			guidMember.setImei(addGuidMember.getImei());
			guidMember.setEmail(addGuidMember.getEmail());
			guidMember.setNickName(addGuidMember.getNickName());
			guidMember.setAreaCode(addGuidMember.getAreaCode());
			guidMember.setAreaName(addGuidMember.getAreaName());
			guidMember.setNoWx(addGuidMember.getNoWx());
			guidMember.setProvinceCode(addGuidMember.getProvinceCode());
			guidMember.setCityCode(addGuidMember.getCityCode());
			guidMember.setCityAreaCode(addGuidMember.getCityAreaCode());
			guidMember.setAddress(addGuidMember.getAddress());
			guidMember.setAge(addGuidMember.getAge());
			guidMember.setGender(addGuidMember.getGender());
			guidMember.setHeadAddress(addGuidMember.getHeadAddress());
			guidMember.setWorkDate(addGuidMember.getWorkDate());
			guidMember.setQcord(addGuidMember.getQcord());
			guidMember.setCreateId(addGuidMember.getCreateId());
			guidMember.setRemark4(addGuidMember.getRemark4());
			guidMember.setRemark3(addGuidMember.getRemark3());
			guidMember.setRemark2(addGuidMember.getRemark2());
			guidMember.setRemark(addGuidMember.getRemark());
			guidMember.setCreateDate(new Date());
			guidMember.setLoginName(addGuidMember.getLoginName());
			// 加密机加密
			EncryptRequest encryptRequest = new EncryptRequest();
			encryptRequest.setAppCode(MemberConstants.ENCRYPT_APPCODE);
			encryptRequest.setOriginalText(addGuidMember.getPwd());

			EncryptResponse encryptResponse = iEncryptor.encrypt(encryptRequest);
			guidMember.setPwd(encryptResponse.getCipherText());
			guidMember.setEncryptionCode(encryptResponse.getEncryptorId());

			guidMemberDao.insertSelective(guidMember);
			AddGuidMemberReturn addGuidMemberReturn = new AddGuidMemberReturn();
			addGuidMemberReturn.setCode(guidMember.getCode());
			// 新增登录检查
			AddLoginCheck addLoginCheck = new AddLoginCheck();
			addLoginCheck.setCode(GUID.generateCode());
			addLoginCheck.setMemberNo(guidMember.getMemberNo());
			addLoginCheck.setCycleLoginFailTimes(0);
			addLoginCheck.setLockStatus(LockStatus.NORMAL.toString());
			addLoginCheck.setMemberType(MemberType.GUID.toString());
			loginCheckService.addLoginCheck(addLoginCheck);


			logger.debug("addGuidMember(AddGuidMember) - end - return value={}",addGuidMemberReturn);
			return addGuidMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增导购表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_ADD_ERROR,
					"新增导购表信息错误！", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IGuidMemberService#delGuidMember(com.lj
	 * .business.member.dto.DelGuidMember)
	 */
	@Override
	public DelGuidMemberReturn delGuidMember(DelGuidMember delGuidMember)
			throws TsfaServiceException {
		logger.debug("delGuidMember(DelGuidMember delGuidMember={}) - start",
				delGuidMember);

		AssertUtils.notNull(delGuidMember);
		AssertUtils.notNull(delGuidMember.getCode(), "ID不能为空！");
		try {
			guidMemberDao.deleteByPrimaryKey(delGuidMember.getCode());
			DelGuidMemberReturn delGuidMemberReturn = new DelGuidMemberReturn();

			logger.debug(
					"delGuidMember(DelGuidMember) - end - return value={}", delGuidMemberReturn); 
			return delGuidMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除导购表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_DEL_ERROR,
					"删除导购表信息错误！", e);

		}
	}
	
	@Override
	public void delGuidMemberByMemberNo(String memberNo) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(memberNo, "导购编号不能为空！");
		try {
			guidMemberDao.deleteByMemberNo(memberNo);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e.getExceptionInfo());
			throw e;
		} catch (Exception e) {
			logger.error("删除导购表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_DEL_ERROR,"删除导购表信息错误！", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IGuidMemberService#updateGuidMember(com
	 * .lj.business.member.dto.UpdateGuidMember)
	 */
	@Override
	public UpdateGuidMemberReturn updateGuidMember(
			UpdateGuidMember updateGuidMember) throws TsfaServiceException {
		logger.debug(
				"updateGuidMember(UpdateGuidMember updateGuidMember={}) - start", updateGuidMember); 

		AssertUtils.notNull(updateGuidMember);
		AssertUtils.notAllNullAndEmpty(updateGuidMember.getCode(), updateGuidMember.getMemberNo(),"CODE和memberNo不能同时为空");
		try {
			GuidMember guidMember = new GuidMember();
			// update数据录入
			guidMember.setCode(updateGuidMember.getCode());
			guidMember.setMemberNo(updateGuidMember.getMemberNo());
			guidMember.setMemberName(updateGuidMember.getMemberName());
			guidMember.setMerchantNo(updateGuidMember.getMerchantNo());
			guidMember.setMerchantName(updateGuidMember.getMerchantName());
			guidMember.setStatus(updateGuidMember.getStatus());
			guidMember.setJobNum(updateGuidMember.getJobNum());
			guidMember.setMobile(updateGuidMember.getMobile());
			guidMember.setImei(updateGuidMember.getImei());
			guidMember.setEmail(updateGuidMember.getEmail());
			guidMember.setNickName(updateGuidMember.getNickName());
			guidMember.setAreaCode(updateGuidMember.getAreaCode());
			guidMember.setAreaName(updateGuidMember.getAreaName());
			guidMember.setProvinceCode(updateGuidMember.getProvinceCode());
			guidMember.setCityCode(updateGuidMember.getCityCode());
			guidMember.setAddress(updateGuidMember.getAddress());
			guidMember.setAge(updateGuidMember.getAge());
			guidMember.setGender(updateGuidMember.getGender());
			guidMember.setHeadAddress(updateGuidMember.getHeadAddress());
			guidMember.setWorkDate(updateGuidMember.getWorkDate());
			guidMember.setQcord(updateGuidMember.getQcord());
			guidMember.setUpdateId(updateGuidMember.getUpdateId());
			guidMember.setUpdateDate(new Date());
			guidMember.setNoWx(updateGuidMember.getNoWx());
			guidMember.setNoWxPersonal(updateGuidMember.getNoWxPersonal());
			guidMember.setLoginName(updateGuidMember.getLoginName());
			guidMember.setRemark3(updateGuidMember.getRemark3());
			guidMember.setAddress(updateGuidMember.getAddress());
			//初始化加密 
            if(updateGuidMember.getInitial() !=null){
            	if(updateGuidMember.getInitial().equals("Yes")){
     		       String  pwd = updateGuidMember.getMobile().substring(updateGuidMember.getMobile().length()-6);
     		       updateGuidMember.setPwd(MD5.encryptByMD5(pwd));
     			   EncryptRequest encryptRequest = new EncryptRequest();	// 加密机加密
     			   encryptRequest.setAppCode(MemberConstants.ENCRYPT_APPCODE);
     			   encryptRequest.setOriginalText(updateGuidMember.getPwd());
     		 
     			  EncryptResponse encryptResponse= iEncryptor.encrypt(encryptRequest);
     			  guidMember.setPwd(encryptResponse.getCipherText() );
     			  guidMember.setEncryptionCode(encryptResponse.getEncryptorId()); 
     			  
     			  if(updateGuidMember.getManagerCode()!=null){
     				 UpdateManagerMember updateManagerMember = new UpdateManagerMember();
         			 updateManagerMember.setPwd(guidMember.getPwd());
         			 updateManagerMember.setEncryptionCode(guidMember.getEncryptionCode());
         			 updateManagerMember.setCode(updateGuidMember.getManagerCode());
         			 managerMemberService.updateManagerMember(updateManagerMember);
     			  }
     			
     			  }
            }
			 
            if(StringUtils.isNotEmpty(updateGuidMember.getPwd())) {
            	// 加密机加密
    			EncryptRequest encryptRequest = new EncryptRequest();
    			encryptRequest.setAppCode(MemberConstants.ENCRYPT_APPCODE);
    			encryptRequest.setOriginalText(updateGuidMember.getPwd());

    			EncryptResponse encryptResponse = iEncryptor.encrypt(encryptRequest);
    			guidMember.setPwd(encryptResponse.getCipherText());
    			guidMember.setEncryptionCode(encryptResponse.getEncryptorId());
            }
			AssertUtils.notUpdateMoreThanOne(guidMemberDao.updateByPrimaryKeySelective(guidMember));
			
			if(StringUtils.isNotEmpty(updateGuidMember.getMemberNo())) {
				LoginCheck loginCheck = new LoginCheck();
				loginCheck.setMemberNo(updateGuidMember.getMemberNo());
				if(loginCheckService.findLoginCheck(loginCheck) == null) {
					// 新增登录检查
					AddLoginCheck addLoginCheck = new AddLoginCheck();
					addLoginCheck.setCode(GUID.generateCode());
					addLoginCheck.setMemberNo(guidMember.getMemberNo());
					addLoginCheck.setCycleLoginFailTimes(0);
					addLoginCheck.setLockStatus(LockStatus.NORMAL.toString());
					addLoginCheck.setMemberType(MemberType.GUID.toString());
					loginCheckService.addLoginCheck(addLoginCheck);
				}
			}
			
			UpdateGuidMemberReturn updateGuidMemberReturn = new UpdateGuidMemberReturn();

			logger.debug(
					"updateGuidMember(UpdateGuidMember) - end - return value={}", updateGuidMemberReturn); 
			return updateGuidMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("导购表信息更新错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_UPDATE_ERROR,
					"导购表信息更新错误！", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IGuidMemberService#findGuidMember(com.
	 * lj.business.member.dto.FindGuidMember)
	 */
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public FindGuidMemberReturn findGuidMember(FindGuidMember findGuidMember)
			throws TsfaServiceException {
		logger.debug("findGuidMember(FindGuidMember findGuidMember={}) - start", findGuidMember); 

		AssertUtils.notNull(findGuidMember);
		if(StringUtils.isEmpty(findGuidMember.getCode()) && StringUtils.isEmpty(findGuidMember.getMemberNo()) 
				&& StringUtils.isEmpty(findGuidMember.getMobile()) && StringUtils.isEmpty(findGuidMember.getNoWx())){
			logger.error("参数全部为空！");
			return null;
		}
		
		try {
			
			GuidMember guidMemberQuery = new GuidMember();
			guidMemberQuery.setCode(findGuidMember.getCode());
			guidMemberQuery.setMemberNo(findGuidMember.getMemberNo());
			guidMemberQuery.setMobile(findGuidMember.getMobile());
			guidMemberQuery.setNoWx(findGuidMember.getNoWx());
			guidMemberQuery.setMerchantNo(findGuidMember.getMerchantNo());//商户编号
			
			GuidMember guidMember = guidMemberDao.selectByParams(guidMemberQuery);
			if (guidMember == null) {
				logger.error("导购表信息不存在！参数={}",guidMemberQuery);
				return null;
			}
			FindGuidMemberReturn findGuidMemberReturn = new FindGuidMemberReturn();
			// find数据录入
			findGuidMemberReturn.setCode(guidMember.getCode());
			findGuidMemberReturn.setMemberNo(guidMember.getMemberNo());
			findGuidMemberReturn.setMemberName(guidMember.getMemberName());
			findGuidMemberReturn.setMerchantNo(guidMember.getMerchantNo());
			findGuidMemberReturn.setMerchantName(guidMember.getMerchantName());
			findGuidMemberReturn.setStatus(guidMember.getStatus());
			findGuidMemberReturn.setJobNum(guidMember.getJobNum());
			findGuidMemberReturn.setMobile(guidMember.getMobile());
			findGuidMemberReturn.setEmail(guidMember.getEmail());
			findGuidMemberReturn.setNickName(guidMember.getNickName());
			findGuidMemberReturn.setAddress(guidMember.getAddress());
			findGuidMemberReturn.setAge(guidMember.getAge());
			findGuidMemberReturn.setGender(guidMember.getGender());
			findGuidMemberReturn.setPwd(guidMember.getPwd());
			findGuidMemberReturn.setEncryptionCode(guidMember.getEncryptionCode());
			findGuidMemberReturn.setHeadAddress(guidMember.getHeadAddress());
			findGuidMemberReturn.setCreateId(guidMember.getCreateId());
			findGuidMemberReturn.setCreateDate(guidMember.getCreateDate());
			findGuidMemberReturn.setUpdateId(guidMember.getUpdateId());
			findGuidMemberReturn.setUpdateDate(guidMember.getUpdateDate());
			findGuidMemberReturn.setAreaCode(guidMember.getAreaCode());
			findGuidMemberReturn.setAreaName(guidMember.getAreaName());
			findGuidMemberReturn.setNoWx(guidMember.getNoWx());
			findGuidMemberReturn.setNoWxPersonal(guidMember.getNoWxPersonal());
			findGuidMemberReturn.setQcord(guidMember.getQcord());
			findGuidMemberReturn.setLoginName(guidMember.getLoginName());
			logger.debug(
					"findGuidMember(FindGuidMember) - end - return value={}", findGuidMemberReturn); 
			return findGuidMemberReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找导购表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR,
					"查找导购表信息错误！", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IGuidMemberService#findGuidMemberPage(
	 * com.lj.business.member.dto.FindGuidMemberPage)
	 */
	@Override
	public Page<FindGuidMemberPageReturn> findGuidMemberPage(
			FindGuidMemberPage findGuidMemberPage) throws TsfaServiceException {
		logger.debug(
				"findGuidMemberPage(FindGuidMemberPage findGuidMemberPage={}) - start", findGuidMemberPage); 

		AssertUtils.notNull(findGuidMemberPage);
		List<FindGuidMemberPageReturn> returnList = null;
		int count = 0;
		try {
			returnList = guidMemberDao.findGuidMemberPage(findGuidMemberPage);
			count = guidMemberDao.findGuidMemberPageCount(findGuidMemberPage);
		} catch (Exception e) {
			logger.error("导购表信息分页查询错误", e);
			throw new TsfaServiceException(
					ErrorCode.GUID_MEMBER_FIND_PAGE_ERROR, "导购表信息分页查询错误.！", e);
		}
		Page<FindGuidMemberPageReturn> returnPage = new Page<FindGuidMemberPageReturn>(
				returnList, count, findGuidMemberPage);

		logger.debug(
				"findGuidMemberPage(FindGuidMemberPage) - end - return value={}", returnPage); 
		return returnPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IGuidMemberService#findGuidMembers(com
	 * .lj.business.member.dto.FindGuidMemberPage)
	 */
	@Override
	public List<FindGuidMemberPageReturn> findGuidMembers(
			FindGuidMemberPage findGuidMemberPage) throws TsfaServiceException {
		AssertUtils.notNull(findGuidMemberPage);
		List<FindGuidMemberPageReturn> returnList = null;
		try {
			returnList = guidMemberDao.findGuidMembers(findGuidMemberPage);
		} catch (Exception e) {
			logger.error("导购表信息分页查询错误", e);
			throw new TsfaServiceException(
					ErrorCode.GUID_MEMBER_FIND_PAGE_ERROR, "导购表信息分页查询错误.！", e);
		}
		return returnList;
	}
	
	/**
	 * Find guid members no self.
	 *
	 * @param findGuidMemberPage the find guid member page
	 * @return the list
	 * @throws TsfaServiceException the tsfa service exception
	 */
	@Override
	public List<FindGuidMemberPageReturn> findGuidMembersNoSelf(FindGuidMemberPage findGuidMemberPage) throws TsfaServiceException {
		AssertUtils.notNull(findGuidMemberPage);
		List<FindGuidMemberPageReturn> returnList = null;
		try {
			returnList = guidMemberDao.findGuidMembersNoSelf(findGuidMemberPage);
		} catch (Exception e) {
			logger.error("导购表信息分页查询错误", e);
			throw new TsfaServiceException(
					ErrorCode.GUID_MEMBER_FIND_PAGE_ERROR, "导购表信息分页查询错误.！", e);
		}
		return returnList;
	}
	
	/**
	 * 查找个人中心导购信息.
	 *
	 * @param findGuidMemberDto
	 *            the find guid member dto
	 * @return the guid member return dto
	 */
	@Override
	public GuidMemberReturnDto findGuid(FindGuidMemberDto findGuidMemberDto) {
		AssertUtils.notNull(findGuidMemberDto);
		try {
			GuidMemberReturnDto guidMember = guidMemberDao.findGuid(findGuidMemberDto);
			GuidMemberReturnDto guidMemberReturnDto = new GuidMemberReturnDto();
			if(guidMember !=null){
				guidMemberReturnDto.setCode(guidMember.getCode());
				guidMemberReturnDto.setMemberNo(guidMember.getMemberNo());
				guidMemberReturnDto.setMemberName(guidMember.getMemberName());
				guidMemberReturnDto.setMerchantNo(guidMember.getMerchantNo());
				guidMemberReturnDto.setMerchantName(guidMember.getMerchantName());
				guidMemberReturnDto.setMobile(guidMember.getMobile());
				guidMemberReturnDto.setEmail(guidMember.getEmail());
				guidMemberReturnDto.setAreaCode(guidMember.getAreaCode());
				guidMemberReturnDto.setProvinceCode(guidMember.getProvinceCode());
				guidMemberReturnDto.setCityCode(guidMember.getCityCode());
				guidMemberReturnDto.setCityAreaCode(guidMember.getCityAreaCode());
				guidMemberReturnDto.setAddress(guidMember.getAddress());
				guidMemberReturnDto.setHeadAddress(guidMember.getHeadAddress());
				guidMemberReturnDto.setGender(guidMember.getGender());
				guidMemberReturnDto.setNoWxPersonal(guidMember.getNoWxPersonal());
				return guidMemberReturnDto;
			}
			return guidMemberReturnDto;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找导购表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR,
					"查找导购表信息错误！", e);
		}
	}

	/**
	 * 添加个人中心导购信息.
	 *
	 * @param addGuidMemberDto
	 *            the add guid member dto
	 */
	@Override
	public void addGuidMember(AddGuidMemberDto addGuidMemberDto) {
		logger.debug(
				"addGuidMemberDto(AddGuidMemberDto addGuidMemberDto={}) - start",
				addGuidMemberDto);
		AssertUtils.notNull(addGuidMemberDto);
		try {
			GuidMember guidMember = new GuidMember();
			guidMember.setCode(addGuidMemberDto.getCode());
			guidMember.setMemberNo(addGuidMemberDto.getMemberNo());
			guidMember.setMemberName(addGuidMemberDto.getMemberName());
//			guidMember.setShopNo(addGuidMemberDto.getShopNo());
//			guidMember.setShopName(addGuidMemberDto.getShopName());
			guidMember.setMerchantNo(addGuidMemberDto.getMerchantNo());
			guidMember.setMerchantName(addGuidMemberDto.getMerchantName());
			guidMember.setMobile(addGuidMemberDto.getMobile());
			guidMember.setEmail(addGuidMemberDto.getEmail());
			guidMember.setAreaCode(addGuidMemberDto.getAreaCode());
			guidMember.setProvinceCode(addGuidMemberDto.getProvinceCode());
			guidMember.setCityCode(addGuidMemberDto.getCityCode());
			guidMember.setCityAreaCode(addGuidMemberDto.getCityAreaCode());
			guidMember.setAddress(addGuidMemberDto.getAddress());
			guidMember.setHeadAddress(addGuidMemberDto.getHeadAddress());
			guidMember.setGender(addGuidMemberDto.getGender());
			guidMemberDao.addGuid(guidMember);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增导购表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_ADD_ERROR,
					"新增导购表信息错误！", e);
		}
	}

	/**
	 * 按主键查询导购个人信息
	 */
	@Override
	public GuidMemberReturnDto findGuidMemberByCode(
			UpdateGuidMember updateGuidMember) {
		logger.debug(
				"findGuidMember(UpdateGuidMember updateGuidMember={}) - start",
				updateGuidMember);
		AssertUtils.notNull(updateGuidMember);
		try {
			GuidMember guidMember = guidMemberDao.selectByPrimaryKey(updateGuidMember.getCode());
			GuidMemberReturnDto guidMemberReturnDto = new GuidMemberReturnDto();
			guidMemberReturnDto.setCode(guidMember.getCode());
			guidMemberReturnDto.setMemberNo(guidMember.getMemberNo());
			guidMemberReturnDto.setMemberName(guidMember.getMemberName());
//			guidMemberReturnDto.setShopNo(guidMember.getShopNo());
//			guidMemberReturnDto.setShopName(guidMember.getShopName());
			guidMemberReturnDto.setMerchantNo(guidMember.getMerchantNo());
			guidMemberReturnDto.setMerchantName(guidMember.getMerchantName());
			guidMemberReturnDto.setMobile(guidMember.getMobile());
			guidMemberReturnDto.setEmail(guidMember.getEmail());
			guidMemberReturnDto.setAreaCode(guidMember.getAreaCode());
			guidMemberReturnDto.setNoWx(guidMember.getNoWx());
			guidMemberReturnDto.setNoWxPersonal(guidMember.getNoWxPersonal());
			guidMemberReturnDto.setProvinceCode(guidMember.getProvinceCode());
			guidMemberReturnDto.setCityCode(guidMember.getCityCode());
			guidMemberReturnDto.setCityAreaCode(guidMember.getCityAreaCode());
			guidMemberReturnDto.setAddress(guidMember.getAddress());
			guidMemberReturnDto.setQcord(guidMember.getQcord());
			guidMemberReturnDto.setHeadAddress(guidMember.getHeadAddress());
			guidMemberReturnDto.setGender(guidMember.getGender());
			guidMemberReturnDto.setAddress(guidMember.getAddress());
			guidMemberReturnDto.setWorkDate(guidMember.getWorkDate());
			guidMemberReturnDto.setStatus(guidMember.getStatus());
			guidMemberReturnDto.setImei(guidMember.getImei());
			guidMemberReturnDto.setAge(guidMember.getAge());
			return guidMemberReturnDto;
		} catch (Exception e) {
			logger.error("导购表信息分页查询错误", e);
			throw new TsfaServiceException(
					ErrorCode.GUID_MEMBER_FIND_PAGE_ERROR, "导购表信息分页查询错误.！", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IGuidMemberService#findGuidMemberList(
	 * com.lj.business.member.dto.FindGuidMemberDto)
	 */
	@Override
	public List<GuidMemberReturnDto> findGuidMemberList(
			FindGuidMemberDto findGuidMemberDto) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(findGuidMemberDto.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findGuidMemberDto.getNoWx(), "微信不能为空");
		try {
			List<GuidMemberReturnDto> list = guidMemberDao
					.findGuidMemberList(findGuidMemberDto);
			return list;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增导购表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_ADD_ERROR,
					"新增导购表信息错误！", e);
		}
	}
	
	@Override
    public List<GuidMemberReturnDto> findGuidMemberSelective(FindGuidMemberDto findGuidMemberDto) throws TsfaServiceException {
        try {
            List<GuidMemberReturnDto> list = guidMemberDao.findGuidMemberSelective(findGuidMemberDto);
            return list;
        } catch (TsfaServiceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("查找导购表信息错误！", e);
            throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR, "查找导购表信息错误！", e);
        }
    }

	@Override
	public Page<FindGuidMemberPageReturn> findGuidMemberPage(int currentPage,
			int limit) {
		FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
		findGuidMemberPage.setLimit(limit);
		findGuidMemberPage.setStatus("NORMAL");
		findGuidMemberPage.setStart((currentPage - 1)
				* findGuidMemberPage.getLimit());
		return findGuidMemberPage(findGuidMemberPage);
	}


	@Override
	public int findGuidMemberCount(FindGuidMemberPage findGuidMemberPage) {
		
		int count =0;
		try {
			count =guidMemberDao.findGuidMemberPageCount(findGuidMemberPage);
			return count;
		} catch (Exception e) {
			logger.error("统计导购信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR,"统计导购信息错误！", e);
		}
	}

	@Override
	public int findGuidMemberByMerchantNo(FindGuidMemberPage findGuidMemberPage) {
		int count =0;
		try {
			count =guidMemberDao.findGuidMemberByMerchantNo(findGuidMemberPage);
			return count;
		} catch (Exception e) {
			logger.error("统计导购信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR,"统计导购信息错误！", e);
		}
	}

	@Override
	public List<GuidInfoShop> findGuidInfoShop(FindGuidMemberDto findGuidMemberDto) {
		List<GuidInfoShop> list=null;
		try {
			list=guidMemberDao.findGuidInfoShop(findGuidMemberDto);
		} catch (Exception e) {
			logger.error("查找导购表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR,
					"查找导购表信息错误！", e);
		}
		return list;
	}

	@Override
	public List<FindGuidMemberPageReturn> findGuidMemberExport(
			FindGuidMemberPage findGuidMemberPage) throws TsfaServiceException {
		
		List<FindGuidMemberPageReturn> returnList = null;
		try {
			returnList = guidMemberDao.findGuidMemberExport(findGuidMemberPage);
		} catch (Exception e) {
			logger.error("导购表信息查询错误", e);
			throw new TsfaServiceException(
					ErrorCode.GUID_MEMBER_FIND_ERROR, "导购表信息查询错误.！", e);
		}
		return returnList;
	}
	
	@Override
	public List<FindShopGmReturn> findShopGm(FindShopGmDto findShopGmDto) throws TsfaServiceException{
		AssertUtils.notNull(findShopGmDto);
		AssertUtils.notNullAndEmpty(findShopGmDto.getMerchantNo(),"商户号不能为空");
//		AssertUtils.notNullAndEmpty(findShopGmDto.getShopNo(),"分店编号不能为空");
		List<FindShopGmReturn> list = new ArrayList<FindShopGmReturn>();
		try{
			list = guidMemberDao.findShopGm(findShopGmDto);
			return list;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购表信息错误", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR,"查找导购表信息错误", e);
		}
	}
	
	@Override
	public List<FindShopGmReturn> findShopGmPy(FindShopGmDto findShopGmDto) throws TsfaServiceException{
		AssertUtils.notNull(findShopGmDto);
		AssertUtils.notNullAndEmpty(findShopGmDto.getMerchantNo(),"商户号不能为空");
//		AssertUtils.notNullAndEmpty(findShopGmDto.getShopNo(),"分店编号不能为空");
		List<FindShopGmReturn> list = new ArrayList<FindShopGmReturn>();
		try{
			list = guidMemberDao.findShopGmPy(findShopGmDto);
			return list;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购表信息错误", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR,"查找导购表信息错误", e);
		}
	}

	@Override
	public void updatePwd(UpdatePwdDto updatePwdDto)
			throws TsfaServiceException {
		logger.debug("updatePwd(UpdatePwdDto updatePwdDto={}) - start", updatePwdDto); 

		AssertUtils.notNull(updatePwdDto);
		AssertUtils.notNull(updatePwdDto.getCode(), "CODE不能为空");
		try {
			GuidMember guidMember = new GuidMember();
			guidMember.setCode(updatePwdDto.getCode());
			
			// 加密机加密
			EncryptRequest encryptRequest = new EncryptRequest();
			encryptRequest.setAppCode(MemberConstants.ENCRYPT_APPCODE);
			encryptRequest.setOriginalText(updatePwdDto.getPwd());

			EncryptResponse encryptResponse = iEncryptor.encrypt(encryptRequest);
			guidMember.setPwd(encryptResponse.getCipherText());
			 
			AssertUtils.notUpdateMoreThanOne(guidMemberDao.updateByPrimaryKeySelective(guidMember));

		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("导购表信息更新错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_UPDATE_ERROR,"导购表信息更新错误！", e);
		}
	}

	@Override
	public List<GuidMember> findGuidMember(GuidMember guidMember) throws TsfaServiceException {
		return guidMemberDao.findGuidMemberAllList(guidMember);
	}

	@Override
	public int findPersonUngroupCount(GuidMember guidMember) throws TsfaServiceException {
		return guidMemberDao.findPersonUngroupCount(guidMember);
	}

	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IGuidMemberService#findAllotGuidMember(com.lj.business.member.dto.addfriends.FindAllotGuidMember)
	 */
	@Override
	public List<FindAllotGuidMemberReturn> findAllotGuidMember(FindAllotGuidMember findAllotGuidMember) throws TsfaServiceException {
		AssertUtils.notNull(findAllotGuidMember);
		AssertUtils.notNullAndEmpty(findAllotGuidMember.getNoWxGm(), "导购微信号不能为空");
		try { 
			return guidMemberDao.findAllotGuidMember(findAllotGuidMember);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR,"查找导购表信息错误！", e);
		}
	}

	@Override
	public List<FindGuidByShopAndMemberReturn> findGuidByShopAndMember(FindGuidByShopAndMember findGuidByShopAndMember) {
		logger.debug("findGuidByShopAndMember(FindGuidByShopAndMember findGuidByShopAndMember={}) - start", findGuidByShopAndMember);
		
		AssertUtils.notNull(findGuidByShopAndMember);
		AssertUtils.notNullAndEmpty(findGuidByShopAndMember.getShopWx(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(findGuidByShopAndMember.getMemberNo(), "客户编号不能为空");
		
		List<FindGuidByShopAndMemberReturn> returnList = null;
		try {
			returnList = guidMemberDao.findGuidByShopAndMember(findGuidByShopAndMember);
		} catch(Exception e) {
			logger.error("查询终端下已添加指定微信客户的所有导购信息失败", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR, "查询终端下已添加指定微信客户的所有导购信息失败", e);
		}
		
		logger.debug("findGuidByShopAndMember(FindGuidByShopAndMember) - end - return value={}", returnList);
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IGuidMemberService#findAllotGuidMember(com.lj.business.member.dto.addfriends.FindAllotGuidMember)
	 */
	@Override
	public List<FindAllotGuidMemberReturn> findOtherAllotGuidMember(FindOtherAllotGuidMember findOtherAllotGuidMember) throws TsfaServiceException {
		AssertUtils.notNull(findOtherAllotGuidMember);
		AssertUtils.notNull(findOtherAllotGuidMember.getMemberNoGm(), "导购编号不能为空");
//		AssertUtils.notNull(findOtherAllotGuidMember.getShopNo(), "终端编号不能为空");
		try {
			//有客户微信号：只能分配给绑定了与当前导购同一微信号的同店其他导购
			if(StringUtils.isNotEmpty(findOtherAllotGuidMember.getNoWx())){
				String noWx=guidMemberDao.findWxByMemberNo(findOtherAllotGuidMember.getMemberNoGm());//导购微信
				if(StringUtils.isNotEmpty(noWx)){
					findOtherAllotGuidMember.setNoWxGm(noWx);
					return guidMemberDao.findOtherAllotGuidMember(findOtherAllotGuidMember);
				}else{
					throw new TsfaServiceException(ErrorCode.GUID_MEMBER_NOT_EXIST_ERROR, "导购微信号为空!");
				}
			}
			//没有客户微信号：只能分配给同店其他导购
			return guidMemberDao.findOtherAllotGuidMember(findOtherAllotGuidMember);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR,"查找导购表信息错误！", e);
		}
	}

	@Override
	public List<FindGuidMemberPageReturn> findGuidMemberNoWx(
			FindGuidMemberPage findGuidMemberPage) throws TsfaServiceException {
		AssertUtils.notNull(findGuidMemberPage.getNoWx(), "微信号不能为空");
//		AssertUtils.notNull(findGuidMemberPage.getShopNo(), "终端编号不能为空");
		List<FindGuidMemberPageReturn> list;
		try {
			list=guidMemberDao.findGuidMemberNoWx(findGuidMemberPage);		
			} catch (TsfaServiceException e) {
				logger.error(e.getMessage(), e);
				throw e;
		}catch(Exception e){
			logger.error("查找导购表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR,"查找导购表信息错误！", e);
		}
		return list;
	}
	
	
	@Override
	public FindShopGmDtoReturn findGmDto(String shopNo) throws TsfaServiceException {
		FindShopGmDtoReturn findShopGmDtoReturn = guidMemberDao.findGmDto(shopNo);
		return findShopGmDtoReturn;
	}
	
	@Override
	public FindShopGmDtoReturn findGmDtoByGmNo(String gmNo) throws TsfaServiceException {
		FindShopGmDtoReturn findShopGmDtoReturn = guidMemberDao.findGmDtoByGmNo(gmNo);
		return findShopGmDtoReturn;
	}

	@Override
	public List<FindGuidMemberPageReturn> findGuidMembersByShopWx(FindShopGmDto findShopGmDto) {
		AssertUtils.notNullAndEmpty(findShopGmDto.getNoWx(), "终端微信号不能为空");
		AssertUtils.notNullAndEmpty(findShopGmDto.getMerchantNo(), "商户号不能为空");
		List<FindGuidMemberPageReturn> list = null ;
		try {
			list = guidMemberDao.findGuidMembersByShopWx(findShopGmDto);
		} catch (TsfaServiceException e) {
		     logger.error(e.getExceptionInfo(),e);
		     throw e;
		}catch (Exception e) {
			logger.error("查找导购表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR,"查找导购表信息错误！", e);
		}
		return list;
	}
	
	@Override
	public void batchDelete(String[] ids) throws TsfaServiceException {
		logger.debug("batchDelete(ids={}) - start",ids.toString());
		try {
			guidMemberDao.batchDelete(ids);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e.getExceptionInfo());
			throw e;
		} catch (Exception e) {
			logger.error("批量删除员工错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_DEL_ERROR,"批量删除员工错误！", e);
		}
	}

	@Override
	public GuidMember findSingleGuidMember(GuidMember guidMember) throws TsfaServiceException {
		return guidMemberDao.findGuidMember(guidMember);
	}
}
