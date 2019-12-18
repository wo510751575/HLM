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

import com.ye.business.hx.dto.FaceCheckDto;
import com.ye.business.hx.dto.FindFaceCheckPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IFaceCheckDao;
import com.ye.business.hx.domain.FaceCheck;
import com.ye.business.hx.service.IFaceCheckService;
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
public class FaceCheckServiceImpl implements IFaceCheckService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(FaceCheckServiceImpl.class);
	

	@Resource
	private IFaceCheckDao faceCheckDao;
	
	
	@Override
	public void addFaceCheck(
			FaceCheckDto faceCheckDto) throws TsfaServiceException {
		logger.debug("addFaceCheck(AddFaceCheck addFaceCheck={}) - start", faceCheckDto); 

		AssertUtils.notNull(faceCheckDto);
		try {
			FaceCheck faceCheck = new FaceCheck();
			//add数据录入
			faceCheck.setCode(faceCheckDto.getCode());
			faceCheck.setPositive(faceCheckDto.getPositive());
			faceCheck.setPositiveCode(faceCheckDto.getPositiveCode());
			faceCheck.setPositiveSmile(faceCheckDto.getPositiveSmile());
			faceCheck.setPositiveSmileCode(faceCheckDto.getPositiveSmileCode());
			faceCheck.setSide(faceCheckDto.getSide());
			faceCheck.setSideCode(faceCheckDto.getSideCode());
			faceCheck.setRight(faceCheckDto.getRight());
			faceCheck.setRightCode(faceCheckDto.getRightCode());
			faceCheck.setLetf(faceCheckDto.getLetf());
			faceCheck.setLetfCode(faceCheckDto.getLetfCode());
			faceCheck.setOther(faceCheckDto.getOther());
			faceCheck.setOtherCode(faceCheckDto.getOtherCode());
			faceCheck.setFrontalType(faceCheckDto.getFrontalType());
			faceCheck.setChinSocket(faceCheckDto.getChinSocket());
			faceCheck.setLipShape(faceCheckDto.getLipShape());
			faceCheck.setSymmetry(faceCheckDto.getSymmetry());
			faceCheck.setSmilePulling(faceCheckDto.getSmilePulling());
			faceCheck.setRelaxPulling(faceCheckDto.getRelaxPulling());
			faceCheck.setFaceShape(faceCheckDto.getFaceShape());
			faceCheck.setNasolabialAngle(faceCheckDto.getNasolabialAngle());
			faceCheck.setLabialGroove(faceCheckDto.getLabialGroove());
			faceCheck.setChinPosition(faceCheckDto.getChinPosition());
			faceCheck.setOthers(faceCheckDto.getOthers());
			faceCheck.setPatientNo(faceCheckDto.getPatientNo());
			faceCheck.setCreateDate(faceCheckDto.getCreateDate());
			faceCheck.setCreateId(faceCheckDto.getCreateId());
			faceCheck.setRemark(faceCheckDto.getRemark());
			faceCheckDao.insertSelective(faceCheck);
			logger.debug("addFaceCheck(FaceCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增面部检查信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FACE_CHECK_ADD_ERROR,"新增面部检查信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询面部检查信息
	 *
	 * @param findFaceCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<FaceCheckDto>  findFaceChecks(FindFaceCheckPage findFaceCheckPage)throws TsfaServiceException{
		AssertUtils.notNull(findFaceCheckPage);
		List<FaceCheckDto> returnList=null;
		try {
			returnList = faceCheckDao.findFaceChecks(findFaceCheckPage);
		} catch (Exception e) {
			logger.error("查找面部检查信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.FACE_CHECK_NOT_EXIST_ERROR,"面部检查信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateFaceCheck(
			FaceCheckDto faceCheckDto)
			throws TsfaServiceException {
		logger.debug("updateFaceCheck(FaceCheckDto faceCheckDto={}) - start", faceCheckDto); 

		AssertUtils.notNull(faceCheckDto);
		AssertUtils.notNullAndEmpty(faceCheckDto.getCode(),"Code不能为空");
		try {
			FaceCheck faceCheck = new FaceCheck();
			//update数据录入
			faceCheck.setCode(faceCheckDto.getCode());
			faceCheck.setPositive(faceCheckDto.getPositive());
			faceCheck.setPositiveCode(faceCheckDto.getPositiveCode());
			faceCheck.setPositiveSmile(faceCheckDto.getPositiveSmile());
			faceCheck.setPositiveSmileCode(faceCheckDto.getPositiveSmileCode());
			faceCheck.setSide(faceCheckDto.getSide());
			faceCheck.setSideCode(faceCheckDto.getSideCode());
			faceCheck.setRight(faceCheckDto.getRight());
			faceCheck.setRightCode(faceCheckDto.getRightCode());
			faceCheck.setLetf(faceCheckDto.getLetf());
			faceCheck.setLetfCode(faceCheckDto.getLetfCode());
			faceCheck.setOther(faceCheckDto.getOther());
			faceCheck.setOtherCode(faceCheckDto.getOtherCode());
			faceCheck.setFrontalType(faceCheckDto.getFrontalType());
			faceCheck.setChinSocket(faceCheckDto.getChinSocket());
			faceCheck.setLipShape(faceCheckDto.getLipShape());
			faceCheck.setSymmetry(faceCheckDto.getSymmetry());
			faceCheck.setSmilePulling(faceCheckDto.getSmilePulling());
			faceCheck.setRelaxPulling(faceCheckDto.getRelaxPulling());
			faceCheck.setFaceShape(faceCheckDto.getFaceShape());
			faceCheck.setNasolabialAngle(faceCheckDto.getNasolabialAngle());
			faceCheck.setLabialGroove(faceCheckDto.getLabialGroove());
			faceCheck.setChinPosition(faceCheckDto.getChinPosition());
			faceCheck.setOthers(faceCheckDto.getOthers());
			faceCheck.setPatientNo(faceCheckDto.getPatientNo());
			faceCheck.setCreateDate(faceCheckDto.getCreateDate());
			faceCheck.setCreateId(faceCheckDto.getCreateId());
			faceCheck.setRemark(faceCheckDto.getRemark());
			AssertUtils.notUpdateMoreThanOne(faceCheckDao.updateByPrimaryKeySelective(faceCheck));
			logger.debug("updateFaceCheck(FaceCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("面部检查信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FACE_CHECK_UPDATE_ERROR,"面部检查信息更新信息错误！",e);
		}
	}

	

	@Override
	public FaceCheckDto findFaceCheck(
			FaceCheckDto faceCheckDto) throws TsfaServiceException {
		logger.debug("findFaceCheck(FindFaceCheck findFaceCheck={}) - start", faceCheckDto); 

		AssertUtils.notNull(faceCheckDto);
		AssertUtils.notAllNull(faceCheckDto.getCode(),"Code不能为空");
		try {
			FaceCheck faceCheck = faceCheckDao.selectByPrimaryKey(faceCheckDto.getPatientNo());
			if(faceCheck == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.FACE_CHECK_NOT_EXIST_ERROR,"面部检查信息不存在");
			}
			FaceCheckDto findFaceCheckReturn = new FaceCheckDto();
			//find数据录入
			findFaceCheckReturn.setCode(faceCheck.getCode());
			findFaceCheckReturn.setPositive(faceCheck.getPositive());
			findFaceCheckReturn.setPositiveCode(faceCheck.getPositiveCode());
			findFaceCheckReturn.setPositiveSmile(faceCheck.getPositiveSmile());
			findFaceCheckReturn.setPositiveSmileCode(faceCheck.getPositiveSmileCode());
			findFaceCheckReturn.setSide(faceCheck.getSide());
			findFaceCheckReturn.setSideCode(faceCheck.getSideCode());
			findFaceCheckReturn.setRight(faceCheck.getRight());
			findFaceCheckReturn.setRightCode(faceCheck.getRightCode());
			findFaceCheckReturn.setLetf(faceCheck.getLetf());
			findFaceCheckReturn.setLetfCode(faceCheck.getLetfCode());
			findFaceCheckReturn.setOther(faceCheck.getOther());
			findFaceCheckReturn.setOtherCode(faceCheck.getOtherCode());
			findFaceCheckReturn.setFrontalType(faceCheck.getFrontalType());
			findFaceCheckReturn.setChinSocket(faceCheck.getChinSocket());
			findFaceCheckReturn.setLipShape(faceCheck.getLipShape());
			findFaceCheckReturn.setSymmetry(faceCheck.getSymmetry());
			findFaceCheckReturn.setSmilePulling(faceCheck.getSmilePulling());
			findFaceCheckReturn.setRelaxPulling(faceCheck.getRelaxPulling());
			findFaceCheckReturn.setFaceShape(faceCheck.getFaceShape());
			findFaceCheckReturn.setNasolabialAngle(faceCheck.getNasolabialAngle());
			findFaceCheckReturn.setLabialGroove(faceCheck.getLabialGroove());
			findFaceCheckReturn.setChinPosition(faceCheck.getChinPosition());
			findFaceCheckReturn.setOthers(faceCheck.getOthers());
			findFaceCheckReturn.setPatientNo(faceCheck.getPatientNo());
			findFaceCheckReturn.setCreateDate(faceCheck.getCreateDate());
			findFaceCheckReturn.setCreateId(faceCheck.getCreateId());
			findFaceCheckReturn.setRemark(faceCheck.getRemark());
			
			logger.debug("findFaceCheck(FaceCheckDto) - end - return value={}", findFaceCheckReturn); 
			return findFaceCheckReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找面部检查信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FACE_CHECK_FIND_ERROR,"查找面部检查信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FaceCheckDto> findFaceCheckPage(
			FindFaceCheckPage findFaceCheckPage)
			throws TsfaServiceException {
		logger.debug("findFaceCheckPage(FindFaceCheckPage findFaceCheckPage={}) - start", findFaceCheckPage); 

		AssertUtils.notNull(findFaceCheckPage);
		List<FaceCheckDto> returnList=null;
		int count = 0;
		try {
			returnList = faceCheckDao.findFaceCheckPage(findFaceCheckPage);
			count = faceCheckDao.findFaceCheckPageCount(findFaceCheckPage);
		}  catch (Exception e) {
			logger.error("面部检查信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.FACE_CHECK_FIND_PAGE_ERROR,"面部检查信息不存在错误.！",e);
		}
		Page<FaceCheckDto> returnPage = new Page<FaceCheckDto>(returnList, count, findFaceCheckPage);

		logger.debug("findFaceCheckPage(FindFaceCheckPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
