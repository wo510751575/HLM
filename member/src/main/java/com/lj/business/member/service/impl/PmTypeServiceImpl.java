package com.lj.business.member.service.impl;

import java.util.ArrayList;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IPersonMemberDao;
import com.lj.business.member.dao.IPmTypeDao;
import com.lj.business.member.domain.PersonMember;
import com.lj.business.member.domain.PmType;
import com.lj.business.member.dto.AddPmType;
import com.lj.business.member.dto.AddPmTypePmDto;
import com.lj.business.member.dto.AddPmTypeReturn;
import com.lj.business.member.dto.ChangePmType;
import com.lj.business.member.dto.ChangePmTypeHc;
import com.lj.business.member.dto.ChangePmTypeUngroup;
import com.lj.business.member.dto.ChangeUrgency;
import com.lj.business.member.dto.CheckPmTypeDto;
import com.lj.business.member.dto.CheckPmTypeReturn;
import com.lj.business.member.dto.DelPmType;
import com.lj.business.member.dto.DelPmTypeReturn;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.FindPmType;
import com.lj.business.member.dto.FindPmTypeIndex;
import com.lj.business.member.dto.FindPmTypeIndexReturn;
import com.lj.business.member.dto.FindPmTypePage;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.FindPmTypeReturn;
import com.lj.business.member.dto.FindUnContactPmType;
import com.lj.business.member.dto.UpdatePersonMember;
import com.lj.business.member.dto.UpdatePmType;
import com.lj.business.member.dto.UpdatePmTypeReturn;
import com.lj.business.member.emus.UrgentFlagType;
import com.lj.business.member.service.IGuidMemberIntegralService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmTypeService;

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
 * CreateDate: 2017-06-14
 */
@Service
public class PmTypeServiceImpl implements IPmTypeService { 


	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PmTypeServiceImpl.class);


	/** The pm type dao. */
	@Resource
	private IPmTypeDao pmTypeDao;

	/** The person member dao. */
	@Resource
	private IPersonMemberDao personMemberDao;


	/** The person member service. */
	@Resource
	private IPersonMemberService personMemberService;

	@Resource
	private IGuidMemberIntegralService guidMemberIntegralService;

	@Resource
	private IGuidMemberService guidMemberService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmTypeService#addPmType(com.lj.business.member.dto.AddPmType)
	 */
	@Override
	public AddPmTypeReturn addPmType(
			AddPmType addPmType) throws TsfaServiceException {
		logger.debug("addPmType(AddPmType addPmType={}) - start", addPmType); 

		AssertUtils.notNull(addPmType);
		AssertUtils.notNullAndEmpty(addPmType.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(addPmType.getTypeName(), "分类名称不能为空");
		AssertUtils.notNullAndEmpty(addPmType.getPmTypeType(), "分类编码不能为空");
		try {
			PmType pmType = new PmType();
			//add数据录入
			pmType.setCode(GUID.generateCode());
			pmType.setMerchantNo(addPmType.getMerchantNo());
			pmType.setMemberNo(addPmType.getMemberNo());
			pmType.setMemberName(addPmType.getMemberName());
			pmType.setTypeName(addPmType.getTypeName());
			pmType.setPmTypeType(addPmType.getPmTypeType());
			pmType.setPmTypeDim(addPmType.getPmTypeDim());
			pmType.setStatus(StringUtils.toString(addPmType.getStatus(), "Y"));
			pmType.setSeq(addPmType.getSeq());
			pmType.setCreateId(addPmType.getCreateId());
			pmType.setRemark(addPmType.getRemark());
			pmType.setRemark2(addPmType.getRemark2());
			pmType.setRemark3(addPmType.getRemark3());
			pmType.setRemark4(addPmType.getRemark4());
			pmType.setFreValue(addPmType.getFreValue());
			pmTypeDao.insert(pmType);
			AddPmTypeReturn addPmTypeReturn = new AddPmTypeReturn();

			logger.debug("addPmType(AddPmType) - end - return value={}", addPmTypeReturn); 
			return addPmTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增客户分类表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_ADD_ERROR,"新增客户分类表信息错误！",e);
		}
	}


	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmTypeService#delPmType(com.lj.business.member.dto.DelPmType)
	 */
	@Override
	public DelPmTypeReturn delPmType(
			DelPmType delPmType) throws TsfaServiceException {
		logger.debug("delPmType(DelPmType delPmType={}) - start", delPmType); 

		AssertUtils.notNull(delPmType);
		AssertUtils.notNull(delPmType.getCode(),"ID不能为空！");
		try {
			pmTypeDao.deleteByPrimaryKey(delPmType.getCode());
			DelPmTypeReturn delPmTypeReturn  = new DelPmTypeReturn();

			logger.debug("delPmType(DelPmType) - end - return value={}", delPmTypeReturn); 
			return delPmTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除客户分类表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_DEL_ERROR,"删除客户分类表信息错误！",e);

		}
	}

	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmTypeService#updatePmType(com.lj.business.member.dto.UpdatePmType)
	 */
	@Override
	public UpdatePmTypeReturn updatePmType(UpdatePmType updatePmType)throws TsfaServiceException {
		logger.debug("updatePmType(UpdatePmType updatePmType={}) - start", updatePmType); 

		AssertUtils.notNull(updatePmType);
		AssertUtils.notNullAndEmpty(updatePmType.getCode(),"Code不能为空");
		try {
			PmType pmType = new PmType();
			//update数据录入
			pmType.setCode(updatePmType.getCode());
			pmType.setMerchantNo(updatePmType.getMerchantNo());
			pmType.setMemberNo(updatePmType.getMemberNo());
			pmType.setMemberName(updatePmType.getMemberName());
			pmType.setTypeName(updatePmType.getTypeName());
			pmType.setPmTypeType(updatePmType.getPmTypeType());
			pmType.setPmTypeDim(updatePmType.getPmTypeDim());
			pmType.setSeq(updatePmType.getSeq());
			pmType.setStatus(updatePmType.getStatus());
			pmType.setRemark(updatePmType.getRemark());
			pmType.setRemark2(updatePmType.getRemark2());
			pmType.setRemark3(updatePmType.getRemark3());
			pmType.setRemark4(updatePmType.getRemark4());
			pmType.setFreValue(updatePmType.getFreValue());
			AssertUtils.notUpdateMoreThanOne(pmTypeDao.updateByPrimaryKeySelective(pmType));
			UpdatePmTypeReturn updatePmTypeReturn = new UpdatePmTypeReturn();
			/**
			 * 类型名称如有变动
			 * 同步更新该商户分类下所有客户
			 * DZP 2018-12-10
			 */
			if(StringUtils.isNotEmpty(updatePmType.getOldPmTypeName()) 
					&& StringUtils.isNotEmpty(updatePmType.getTypeName()) 
					&&!updatePmType.getOldPmTypeName().equals(updatePmType.getTypeName())) {
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						try {
							personMemberDao.updatePmTypeAllMember(updatePmType.getMerchantNo(), updatePmType.getCode(), updatePmType.getTypeName());
						} catch (Exception e) {
							logger.error("同步更新所有客户分类出错 e={}",e);
						}
						
					}
				});
			}
			
			logger.debug("updatePmType(UpdatePmType) - end - return value={}", updatePmTypeReturn); 
			return updatePmTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error(" 客户分类表信息更新错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_UPDATE_ERROR," 客户分类表信息更新错误！",e);
		}
	}



	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmTypeService#findPmType(com.lj.business.member.dto.FindPmType)
	 */
	@Override
	public FindPmTypeReturn findPmType(
			FindPmType findPmType) throws TsfaServiceException {
		logger.debug("findPmType(FindPmType findPmType={}) - start", findPmType); 

		AssertUtils.notNull(findPmType);
		AssertUtils.notAllNull(findPmType.getCode(),"CODE不能为空");
		try {
			PmType pmType = pmTypeDao.selectByPrimaryKey(findPmType.getCode());
			if(pmType == null){
				return null;
			}
			FindPmTypeReturn findPmTypeReturn = new FindPmTypeReturn();
			//find数据录入
			findPmTypeReturn.setCode(pmType.getCode());
			findPmTypeReturn.setMerchantNo(pmType.getMerchantNo());
			findPmTypeReturn.setMemberNo(pmType.getMemberNo());
			findPmTypeReturn.setMemberName(pmType.getMemberName());
			findPmTypeReturn.setTypeName(pmType.getTypeName());
			findPmTypeReturn.setPmTypeType(pmType.getPmTypeType());
			findPmTypeReturn.setPmTypeDim(pmType.getPmTypeDim());
			findPmTypeReturn.setSeq(pmType.getSeq());
			findPmTypeReturn.setStatus(pmType.getStatus());
			findPmTypeReturn.setCreateId(pmType.getCreateId());
			findPmTypeReturn.setCreateDate(pmType.getCreateDate());
			findPmTypeReturn.setRemark(pmType.getRemark());
			findPmTypeReturn.setRemark2(pmType.getRemark2());
			findPmTypeReturn.setRemark3(pmType.getRemark3());
			findPmTypeReturn.setRemark4(pmType.getRemark4());
			findPmTypeReturn.setFreValue(pmType.getFreValue());
			logger.debug("findPmType(FindPmType) - end - return value={}", findPmTypeReturn); 
			return findPmTypeReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户分类表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_FIND_ERROR,"查找客户分类表信息错误！",e);
		}


	}

	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmTypeService#findPmTypeByMP(com.lj.business.member.dto.FindPmType)
	 */
	@Override
	public FindPmTypeReturn findPmTypeByMP(FindPmType findPmType) throws TsfaServiceException {
		logger.debug("findPmTypeByMP(FindPmType findPmType={}) - start", findPmType); 

		AssertUtils.notNull(findPmType);
		AssertUtils.notAllNull(findPmType.getMerchantNo(),"商户号不能为空");
		AssertUtils.notAllNull(findPmType.getPmTypeType(),"客户分类不能为空");
		try {
			PmType pmType = pmTypeDao.selectByMP(findPmType);
			if(pmType == null){
				//throw new TsfaServiceException(ErrorCode.PM_TYPE_NOT_EXIST_ERROR,"客户分类表信息不存在");
				return null;
			}
			FindPmTypeReturn findPmTypeReturn = new FindPmTypeReturn();
			//find数据录入
			findPmTypeReturn.setCode(pmType.getCode());
			findPmTypeReturn.setMerchantNo(pmType.getMerchantNo());
			findPmTypeReturn.setMemberNo(pmType.getMemberNo());
			findPmTypeReturn.setMemberName(pmType.getMemberName());
			findPmTypeReturn.setTypeName(pmType.getTypeName());
			findPmTypeReturn.setPmTypeType(pmType.getPmTypeType());
			findPmTypeReturn.setPmTypeDim(pmType.getPmTypeDim());
			findPmTypeReturn.setStatus(pmType.getStatus());
			findPmTypeReturn.setCreateId(pmType.getCreateId());
			findPmTypeReturn.setCreateDate(pmType.getCreateDate());
			findPmTypeReturn.setRemark(pmType.getRemark());
			findPmTypeReturn.setRemark2(pmType.getRemark2());
			findPmTypeReturn.setRemark3(pmType.getRemark3());
			findPmTypeReturn.setRemark4(pmType.getRemark4());
			findPmTypeReturn.setFreValue(pmType.getFreValue());
			logger.debug("findPmTypeByMP(FindPmType) - end - return value={}", findPmTypeReturn); 
			return findPmTypeReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户分类表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_FIND_ERROR,"查找客户分类表信息错误！",e);
		}


	}



	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmTypeService#findPmTypePage(com.lj.business.member.dto.FindPmTypePage)
	 */
	@Override
	public Page<FindPmTypePageReturn> findPmTypePage(
			FindPmTypePage findPmTypePage)
					throws TsfaServiceException {
		logger.debug("findPmTypePage(FindPmTypePage findPmTypePage={}) - start", findPmTypePage); 

		AssertUtils.notNull(findPmTypePage);
		List<FindPmTypePageReturn> returnList =null;
		int count = 0;
		try {
			count = pmTypeDao.findPmTypePageCount(findPmTypePage);
			if(count>0) {
				returnList = pmTypeDao.findPmTypePage(findPmTypePage);
			}
		}  catch (Exception e) {
			logger.error("客户分类表信息分页查询错误",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_FIND_PAGE_ERROR,"客户分类表信息分页查询错误.！",e);
		}
		Page<FindPmTypePageReturn> returnPage = new Page<FindPmTypePageReturn>(returnList, count, findPmTypePage);

		logger.debug("findPmTypePage(FindPmTypePage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmTypeService#findPmTypePages(com.lj.business.member.dto.FindPmTypePageReturn)
	 */
	public List<FindPmTypePageReturn> findPmTypePages(FindPmTypePageReturn findPmTypePageReturn) {
		logger.debug("findPmTypePages(FindPmTypePageReturn findPmTypePageReturn={}) - start", findPmTypePageReturn); 
		AssertUtils.notNull(findPmTypePageReturn);
		List<FindPmTypePageReturn> list;
		try {
			list=pmTypeDao.findPmTypePages(findPmTypePageReturn);
		} catch (Exception e) {
			logger.error("客户分类表信息分页查询错误",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_FIND_PAGE_ERROR,"客户分类表信息分页查询错误.！",e);
		}
		return list;
	}


	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmTypeService#findPmTypeIndex(com.lj.business.member.dto.FindPmTypeIndex)
	 */
	@Override
	public List<FindPmTypeIndexReturn> findPmTypeIndex(FindPmTypeIndex findPmTypeIndex) throws TsfaServiceException{
		logger.debug("findPmTypeIndex(FindPmTypeIndex findPmTypeIndex={}) - start", findPmTypeIndex); 

		AssertUtils.notNull(findPmTypeIndex);
		AssertUtils.notNullAndEmpty(findPmTypeIndex.getMerchantNo(),"商户编号不能为空");
		AssertUtils.notNullAndEmpty(findPmTypeIndex.getNoWxGm(),"微信号不能为空");
//		AssertUtils.notAllNullAndEmpty(findPmTypeIndex.getShopNo(),findPmTypeIndex.getMemberNoGm());
		try {
			List<FindPmTypeIndexReturn> returnList = pmTypeDao.findPmTypeIndex(findPmTypeIndex);
			logger.debug("findPmTypeIndex(FindPmTypeIndex) - end - return value={}", returnList); 
			return returnList;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户分类表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_FIND_ERROR,"查找客户分类表信息错误！",e);
		}

	}


	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmTypeService#changePmType(com.lj.business.member.dto.ChangePmType)
	 */
	@Override
	@Deprecated
	public void changePmType(ChangePmType changePmType)
			throws TsfaServiceException {
		logger.debug("changePmType(ChangePmType changePmType={}) - start", changePmType); 

		AssertUtils.notNull(changePmType);
		AssertUtils.notNullAndEmpty(changePmType.getMerchantNo(),"商户编号不能为空");
		if(StringUtils.isEmpty(changePmType.getCodePm()) && 
				(StringUtils.isEmpty(changePmType.getMemberNoGm()) || StringUtils.isEmpty(changePmType.getMemberNo()))){
			throw new IllegalArgumentException("（导购编号 && 客户编号）,客户表CODE 不能全部为空！");
		}
		/*if(StringUtils.isEmpty(changePmType.getPmTypeCode()) && 
				(changePmType.getPmTypeType() == null || StringUtils.isEmpty(changePmType.getMerchantNo()))){
			throw new IllegalArgumentException("（客户分类类型 && 商户编号）,客户分类CODE 不能全部为空！");
		}*/
		try {
			if(StringUtils.isEmpty(changePmType.getCodePm())){//客户表CODE
				PersonMember personMemberQuery = new PersonMember();
				personMemberQuery.setMemberNo(changePmType.getMemberNo());
				personMemberQuery.setMemberNoGm(changePmType.getMemberNoGm());
				PersonMember personMember = personMemberDao.selectByParamKey(personMemberQuery );
				changePmType.setCodePm(personMember.getCode());
			}
			/*if(StringUtils.isEmpty(changePmType.getPmTypeCode())){//客户分类CODE
				PmType pmTypeQuery = new PmType();
				pmTypeQuery.setMerchantNo(changePmType.getMerchantNo());
				pmTypeQuery.setPmTypeType(changePmType.getPmTypeType().toString());
				PmType pmType = pmTypeDao.selectByParamKey(pmTypeQuery);
				changePmType.setPmTypeCode(pmType.getCode());
			}*/

			//查找客户原来所属分类关联CODE
//			Map<String, String> map = new HashMap<String,String>();
//			map.put("merchantNo", changePmType.getMerchantNo());
//			map.put("codePm", changePmType.getCodePm());
//			Map<String, String> mapResult =  pmTypeDao.selectByParamKey_changePmType(map);
//
//			PmTypePm record  = new PmTypePm();
//			record.setCode(mapResult.get("CODE"));
//			record.setPmTypeCode(changePmType.getPmTypeCode());
//			pmTypePmDao.updateByPrimaryKeySelective(record);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("客户分类表信息更新错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_UPDATE_ERROR,"客户分类表信息更新错误！",e);
		}

		logger.debug("changePmType(ChangePmType) - end"); 
	}


	@Override
	public void changePmTypeHc(ChangePmTypeHc changePmTypeHc) throws TsfaServiceException {
		logger.debug("changePmTypeHc(ChangePmTypeHc changePmTypeHc={}) - start", changePmTypeHc); 

		AssertUtils.notNull(changePmTypeHc);
		AssertUtils.notNullAndEmpty(changePmTypeHc.getMerchantNo(),"商户编号不能为空");
		if(StringUtils.isEmpty(changePmTypeHc.getCodePm()) && 
				(StringUtils.isEmpty(changePmTypeHc.getMemberNoGm()) || StringUtils.isEmpty(changePmTypeHc.getMemberNo()))){
			throw new IllegalArgumentException("（导购编号 && 客户编号）,客户表CODE 不能全部为空！");
		}
		if(StringUtils.isEmpty(changePmTypeHc.getPmTypeCode()) && 
				(changePmTypeHc.getPmTypeType() == null || StringUtils.isEmpty(changePmTypeHc.getMerchantNo()))){
			throw new IllegalArgumentException("（客户分类类型 && 商户编号）,客户分类CODE 不能全部为空！");
		}
		try {
			if(StringUtils.isEmpty(changePmTypeHc.getCodePm())){//客户表CODE
				PersonMember personMemberQuery = new PersonMember();
				personMemberQuery.setMemberNo(changePmTypeHc.getMemberNo());
				personMemberQuery.setMemberNoGm(changePmTypeHc.getMemberNoGm());
				personMemberQuery.setShopWx(changePmTypeHc.getShopWx());
				PersonMember personMember = personMemberDao.selectByParamKey(personMemberQuery );
				changePmTypeHc.setCodePm(personMember.getCode());
			}
			if(StringUtils.isEmpty(changePmTypeHc.getPmTypeCode())){//客户分类CODE
				PmType pmTypeQuery = new PmType();
				pmTypeQuery.setMerchantNo(changePmTypeHc.getMerchantNo());
				pmTypeQuery.setPmTypeType(changePmTypeHc.getPmTypeType());
				PmType pmType = pmTypeDao.selectByParamKey(pmTypeQuery);
				changePmTypeHc.setPmTypeCode(pmType.getCode());
			}

			//查找客户原来所属分类关联CODE
//			Map<String, String> map = new HashMap<String,String>();
//			map.put("merchantNo", changePmTypeHc.getMerchantNo());
//			map.put("codePm", changePmTypeHc.getCodePm());
//			Map<String, String> mapResult =  pmTypeDao.selectByParamKey_changePmType(map);
//
//			PmTypePm record  = new PmTypePm();
//			record.setCode(mapResult.get("CODE"));
//			record.setPmTypeCode(changePmTypeHc.getPmTypeCode());
//			pmTypePmDao.updateByPrimaryKeySelective(record);
			UpdatePersonMember updatePersonMember = new UpdatePersonMember();
			updatePersonMember.setCode(changePmTypeHc.getCodePm());
			updatePersonMember.setPmTypeCode(changePmTypeHc.getPmTypeCode());
			personMemberService.updatePmType(updatePersonMember);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("客户分类表信息（美容美发）更新错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_UPDATE_ERROR,"客户分类表信息（美容美发）更新错误！",e);
		}

		logger.debug("changePmTypeHc(ChangePmTypeHc) - end"); 
	}





	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmTypeService#changeUrgency(com.lj.business.member.dto.ChangeUrgency)
	 */
	@Override
	public void changeUrgency(ChangeUrgency changeUrgency)throws TsfaServiceException {
		logger.debug("changeUrgency(ChangeUrgency changeUrgency={}) - start", changeUrgency); 

		AssertUtils.notNull(changeUrgency);
		if(StringUtils.isEmpty(changeUrgency.getPmTypePmCode()) && 
				(StringUtils.isEmpty(changeUrgency.getMemberNoGm()) || StringUtils.isEmpty(changeUrgency.getMemberNo()))){
			throw new IllegalArgumentException("（导购编号 && 客户编号） 客户客户分类关联表CODE  不能全部为空！");
		}
		try {
			PersonMember personMemberQuery = new PersonMember();
			personMemberQuery.setMemberNo(changeUrgency.getMemberNo());
			personMemberQuery.setMemberNoGm(changeUrgency.getMemberNoGm());
			PersonMember personMember = personMemberDao.selectByParamKey(personMemberQuery );
			if(UrgentFlagType.N.equals(changeUrgency.getUrgentFlagType())){
				logger.debug("取消紧急");
				if(StringUtils.isEmpty(changeUrgency.getPmTypePmCode())){//客户表CODE

//					PmType pmTypeQuery = new PmType();
//					pmTypeQuery.setMerchantNo(personMember.getMerchantNo());
//					pmTypeQuery.setPmTypeType(PmTypeType.URGENCY.toString());
//					PmType pmType = pmTypeDao.selectByParamKey(pmTypeQuery);

//					PmTypePm record = new PmTypePm();
//					record.setPmTypeCode(pmType.getCode());
//					record.setCodePm(personMember.getCode());
//					pmTypePmDao.deleteByParamKey(record );

					//紧急处理
					UpdatePersonMember updatePersonMember = new UpdatePersonMember();
					updatePersonMember.setMemberNo(personMember.getMemberNo());
					updatePersonMember.setMemberNoGm(personMember.getMemberNoGm());
					updatePersonMember.setUrgencyPm(UrgentFlagType.N.toString());
					personMemberService.updatePersonMemberByMGM(updatePersonMember);
				}else{
//					PmTypePm pmTypePm = pmTypePmDao.selectByPrimaryKey(changeUrgency.getPmTypePmCode());
//					pmTypePmDao.deleteByPrimaryKey(changeUrgency.getPmTypePmCode());

					//紧急处理
					UpdatePersonMember updatePersonMember = new UpdatePersonMember();
					updatePersonMember.setCode(personMember.getCode());
					updatePersonMember.setUrgencyPm(UrgentFlagType.N.toString());
					personMemberService.updatePersonMember(updatePersonMember);
				}
			}else if(UrgentFlagType.Y.equals(changeUrgency.getUrgentFlagType())){
				logger.debug("新增紧急");
				AssertUtils.notAllNullAndEmpty(changeUrgency.getMemberNoGm(), changeUrgency.getMemberNo());
				

				//紧急处理
				UpdatePersonMember updatePersonMember = new UpdatePersonMember();
				updatePersonMember.setMemberNo(personMember.getMemberNo());
				updatePersonMember.setMemberNoGm(personMember.getMemberNoGm());
				updatePersonMember.setUrgencyPm(UrgentFlagType.Y.toString());
				personMemberService.updatePersonMemberByMGM(updatePersonMember);
				
			}

		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("客户分类表信息更新错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_UPDATE_ERROR,"客户分类表信息更新错误！",e);
		}

		logger.debug("changeUrgency(ChangeUrgency) - end"); 
	}

	@Override
	public List<FindPmType> inqueryMemberOutOffGroupInfo(String merchantNo,String memberNo,String memberNoGm,String flag) throws TsfaServiceException {
		List<PmType> list = new ArrayList<>();
		List<FindPmType> retlist = new ArrayList<FindPmType>();
		list = pmTypeDao.inqueryNewMemberOutOffGroupInfo(merchantNo);
		if(list != null && list.size() > 0){
			for(PmType pt:list){
				FindPmType dto = new FindPmType();
				dto.setCode(pt.getCode());
				dto.setPmTypeType(pt.getPmTypeType());
				dto.setTypeName(pt.getTypeName());
				retlist.add(dto);
			}
		}
		return retlist;
	}



	@Override
	public void changePmType_app_ungroup(ChangePmTypeUngroup changePmTypeUngroup)
			throws TsfaServiceException {
		logger.debug("changePmType_app_ungroup(ChangePmTypeUngroup changePmTypeUngroup={}) - start", changePmTypeUngroup); 

		AssertUtils.notNull(changePmTypeUngroup);
		String pmTypeCode = changePmTypeUngroup.getPmTypeCode();
		AssertUtils.notNullAndEmpty(pmTypeCode,"客户分类CODE 不能为空");
		String merchantNo = changePmTypeUngroup.getMerchantNo();
		String memberNoGm = changePmTypeUngroup.getMemberNoGm();
		String memberNo = changePmTypeUngroup.getMemberNo();
		AssertUtils.notNullAndEmpty(merchantNo,"商户编号不能为空");
		AssertUtils.notNullAndEmpty(memberNoGm,"导购编号不能为空");
		AssertUtils.notNullAndEmpty(memberNo,"客户编号不能为空");
		try {
			/*Map<String, String> map = new HashMap<String, String>();
			map.put("memberNo", memberNo);
			map.put("memberNoGm", memberNoGm);
			String pmTypePmCode =  pmTypeDao.selectPmTypePmCode(map );

			ChangePmTypeApp changePmTypeApp = new ChangePmTypeApp();
			changePmTypeApp.setCode(pmTypePmCode);
			changePmTypeApp.setMemberNo(memberNo);
			changePmTypeApp.setMemberNoGm(memberNoGm);
			changePmTypeApp.setMerchantNo(merchantNo);
			changePmTypeApp.setPmTypeCode(changePmTypeUngroup.getPmTypeCode());
			this.changePmType_app(changePmTypeApp );*/
			
			//修改分类
			FindPersonMember findPersonMember = new FindPersonMember();
			findPersonMember.setMemberNo(memberNo);
			findPersonMember.setMemberNoGm(memberNoGm);
			FindPersonMemberReturn findPersonMemberReturn= personMemberService.findPersonMemberByMemberNoAndGM(findPersonMember);
			
			UpdatePersonMember updatePmType = new UpdatePersonMember();
			updatePmType.setCode(findPersonMemberReturn.getCode());
			updatePmType.setPmTypeCode(changePmTypeUngroup.getPmTypeCode());
			personMemberService.updatePmType(updatePmType);


		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("客户分类表信息更新错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_UPDATE_ERROR,"客户分类表信息更新错误！",e);
		}


		logger.debug("changePmType_app_ungroup(ChangePmTypeUngroup) - end"); 
	}


	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmTypeService#addPmTypePmRepeat(com.lj.business.member.dto.AddPmTypePmDto)
	 */
	@Override
	public void addPmTypePmRepeat(AddPmTypePmDto addPmTypePmDto) throws TsfaServiceException{
		logger.debug("addPmTypePmRepeat(AddPmTypePmDto addPmTypePmDto={}) - start", addPmTypePmDto); 

		AssertUtils.notNull(addPmTypePmDto);
		AssertUtils.notNullAndEmpty(addPmTypePmDto.getCodePm(), "会员code不能为空");
		AssertUtils.notNullAndEmpty(addPmTypePmDto.getPmTypeCode(), "分组code不能为空");
		try{
			/*FindPmTypePmReturn findPmTypePmReturn = pmTypePmDao.findByPmCodeAndPmTypeCode(addPmTypePmDto);
			if(findPmTypePmReturn == null){
				PmTypePm p1 = new PmTypePm();
				p1.setCode(GUID.getPreUUID());
				p1.setCodePm(addPmTypePmDto.getCodePm());
				p1.setPmTypeCode(addPmTypePmDto.getPmTypeCode());
				p1.setCreateDate(new Date());
				pmTypePmDao.insert(p1);
			}*/
			//修改分类
			UpdatePersonMember updatePmType = new UpdatePersonMember();
			updatePmType.setCode(addPmTypePmDto.getCodePm());
			updatePmType.setPmTypeCode(addPmTypePmDto.getPmTypeCode());
			personMemberService.updatePmType(updatePmType);
			
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("客户分类表信息添加错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_ADD_ERROR,"客户分类表信息添加错误！",e);
		}

		logger.debug("addPmTypePmRepeat() - end"); 
	}

	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IPmTypeService#addPmTypePm(com.lj.business.member.dto.AddPmTypePmDto)
	 */
	@Override
	public void addPmTypePm(AddPmTypePmDto addPmTypePmDto) throws TsfaServiceException {
		logger.debug("addPmTypePm(AddPmTypePmDto addPmTypePmDto={}) - start", addPmTypePmDto); 

		AssertUtils.notNull(addPmTypePmDto);
		AssertUtils.notNullAndEmpty(addPmTypePmDto.getCodePm(), "会员code不能为空");
		AssertUtils.notNullAndEmpty(addPmTypePmDto.getPmTypeCode(), "分组code不能为空");
		try{
			//修改分类
			UpdatePersonMember updatePmType = new UpdatePersonMember();
			updatePmType.setCode(addPmTypePmDto.getCodePm());
			updatePmType.setPmTypeCode(addPmTypePmDto.getPmTypeCode());
			personMemberService.updatePmType(updatePmType);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("客户分类表信息添加错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_ADD_ERROR,"客户分类表信息添加错误！",e);
		}

		logger.debug("addPmTypePm() - end"); 
	} 

	@Override
	public CheckPmTypeReturn checkPmType(CheckPmTypeDto checkPmTypeDto) throws TsfaServiceException {
		AssertUtils.notNull(checkPmTypeDto);
		AssertUtils.notNullAndEmpty(checkPmTypeDto.getMemberNo(), "会员编号不能为空");
		AssertUtils.notNullAndEmpty(checkPmTypeDto.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(checkPmTypeDto.getMerchantNo(), "商户编号不能为空");
		try{
			CheckPmTypeReturn checkPmTypeReturn = new CheckPmTypeReturn();
			FindPersonMember findPersonMember = new FindPersonMember();
			findPersonMember.setMemberNo(checkPmTypeDto.getMemberNo());
			findPersonMember.setMemberNoGm(checkPmTypeDto.getMemberNoGm());
			findPersonMember.setMerchantNo(checkPmTypeDto.getMerchantNo());
//			FindPersonMemberReturn findPersonMemberReturn = personMemberService.findPersonMemberByMGM(findPersonMember);
			/*if(findPersonMemberReturn != null){
				FindPmTypePmListByMGMDto findPmTypePmListByMGMDto = new FindPmTypePmListByMGMDto();
				findPmTypePmListByMGMDto.setCodePm(findPersonMemberReturn.getCode());
				List<FindPmTypePmListByMGMReturn> list = pmTypePmDao.findPmTypePmListByMGM(findPmTypePmListByMGMDto);
				if(list != null && list.size() > 0){
					//客户是否意向(到店)或者意向(非到店)
					//客户是否非意向
					//客户是否未分组
					for(FindPmTypePmListByMGMReturn findPmTypePmListByMGMReturn : list){
						if(PmTypeType.INTENTION.toString().equals( findPmTypePmListByMGMReturn.getPmTypeType())){
							checkPmTypeReturn.setIntention(true);
						}
						if(PmTypeType.INTENTION_N.toString().equals( findPmTypePmListByMGMReturn.getPmTypeType())){
							checkPmTypeReturn.setIntention(true);
						}

						if(PmTypeType.UNGROUP.toString().equals( findPmTypePmListByMGMReturn.getPmTypeType())){
							checkPmTypeReturn.setUngroup(true);
						}

						if(PmTypeType.OTHER.toString().equals( findPmTypePmListByMGMReturn.getPmTypeType())){
							checkPmTypeReturn.setOther(true);
						}
					}
				}
			}*/
			return checkPmTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("客户分类表信息查询错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_FIND_ERROR,"客户分类表信息查询错误！",e);
		}

	}


    @Override
    public List<FindPmTypeIndexReturn> findUnContactMemberPmTypes(
            FindUnContactPmType findUnContactPmType) throws TsfaServiceException {
        AssertUtils.notNull(findUnContactPmType);
        AssertUtils.notNullAndEmpty(findUnContactPmType.getMerchantNo(),"商户编号不能为空");
        AssertUtils.notAllNullAndEmpty(findUnContactPmType.getMemberNoGm(),"导购编号不能为空");
        
        try {
            List<FindPmTypeIndexReturn> returnList = pmTypeDao.findUnContactMemberPmTypes(findUnContactPmType);
            return returnList;
        }catch (TsfaServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("查询未联系顾客分组信息错误！",e);
            throw new TsfaServiceException(ErrorCode.UNCONTACT_PM_TYPE_ERROR,"查询未联系顾客分组信息错误！",e);
        }
    }


	@Override
	public int findCountByUngroup(FindPmTypeIndex findPmTypeIndex) throws TsfaServiceException {
		logger.debug("findCountByUngroup(FindPmTypeIndex findPmTypeIndex={}) - start", findPmTypeIndex); 

		AssertUtils.notNull(findPmTypeIndex);
		AssertUtils.notNullAndEmpty(findPmTypeIndex.getMerchantNo(),"商户编号不能为空");
		AssertUtils.notNullAndEmpty(findPmTypeIndex.getNoWxGm(), "终端微信不能为空");
		try {
			int count = pmTypeDao.findCountByUngroup(findPmTypeIndex);
			logger.debug("findCountByUngroup(FindPmTypeIndex) - end - return value={}", count); 
			return count;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户分类表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TYPE_FIND_ERROR,"查找客户分类表信息错误！",e);
		}
	}

}
