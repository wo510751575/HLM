package com.lj.business.st.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IWorkBrDayChooseDao;
import com.lj.business.st.domain.WorkBrDayChoose;
import com.lj.business.st.dto.FindWorkDayGmIndex;
import com.lj.business.st.dto.FindWorkDayGmIndexListReturn;
import com.lj.business.st.dto.WorkBrDayChooseDto;
import com.lj.business.st.dto.WorkBrDayChoosePage;
import com.lj.business.st.service.IWorkBrDayChooseService;

/**
 * 
 * 
 * 类说明：日工作简报选择表 Service层接口实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月1日
 */
@Service
public class WorkBrDayChooseServiceimpl implements IWorkBrDayChooseService {

	private static final Logger logger = LoggerFactory.getLogger(WorkBrDayChooseServiceimpl.class);
	
	@Resource
	private IWorkBrDayChooseDao workBrDayChooseDao;
	
	public WorkBrDayChooseDto insertSelective(WorkBrDayChooseDto workBrDayChooseDto) {
		logger.debug("insertSelective(WorkBrDayChoose workBrDayChoose={}) - start", workBrDayChooseDto); 
		AssertUtils.notNull(workBrDayChooseDto);
		try {
			WorkBrDayChoose workBrDayChoose =new WorkBrDayChoose();
			workBrDayChoose.setCode(GUID.getPreUUID());
			workBrDayChoose.setMemberNameGm(workBrDayChooseDto.getMemberNameGm());
			workBrDayChoose.setMemberNoGm(workBrDayChooseDto.getMemberNoGm());
			workBrDayChoose.setMerchantNo(workBrDayChooseDto.getMerchantNo());
			workBrDayChoose.setCodeList(workBrDayChooseDto.getCodeList());
			workBrDayChoose.setNameList(workBrDayChooseDto.getNameList());
			workBrDayChoose.setShopName(workBrDayChooseDto.getShopName());
			workBrDayChoose.setShopNo(workBrDayChooseDto.getShopNo());
			workBrDayChoose.setTypeList(workBrDayChooseDto.getTypeList());
			workBrDayChoose.setSeq(workBrDayChooseDto.getSeq());
			workBrDayChoose.setImgAddr(workBrDayChooseDto.getImgAddr());
			workBrDayChoose.setCreateDate(workBrDayChooseDto.getCreateDate());
			workBrDayChooseDao.insertSelective(workBrDayChoose);
		return workBrDayChooseDto;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			logger.error("新增日工作简报选择表错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_BR_DAY_CHOOSE_ADD_ERROR,"新增日工作简报选择表错误！",e);
		}
		
	}



	
	public WorkBrDayChooseDto selectByPrimaryKey(String code) {
		logger.debug("selectByPrimaryKey(String code ={}) - start", code); 
		AssertUtils.notNull(code);
		try {
			WorkBrDayChoose workBrDayChoose= workBrDayChooseDao.selectByPrimaryKey(code);
			
			WorkBrDayChooseDto workBrDayChooseDto=new WorkBrDayChooseDto();
			workBrDayChooseDto.setCode(workBrDayChoose.getCode());
			workBrDayChooseDto.setMemberNameGm(workBrDayChoose.getMemberNameGm());
			workBrDayChooseDto.setMemberNoGm(workBrDayChoose.getMemberNoGm());
			workBrDayChooseDto.setMerchantNo(workBrDayChoose.getMerchantNo());
			workBrDayChooseDto.setCodeList(workBrDayChoose.getCodeList());
			workBrDayChooseDto.setNameList(workBrDayChoose.getNameList());
			workBrDayChooseDto.setShopName(workBrDayChoose.getShopName());
			workBrDayChooseDto.setShopNo(workBrDayChoose.getShopNo());
			workBrDayChooseDto.setSeq(workBrDayChoose.getSeq());
			workBrDayChooseDto.setCreateDate(workBrDayChoose.getCreateDate());			
			return workBrDayChooseDto;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			logger.error("查询日工作简报选择表错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_BR_DAY_CHOOSE_FIND_ERROR,"查询日工作简报选择表错误！",e);
		}
	
	}

	@Override
	public WorkBrDayChooseDto updateByPrimaryKeySelective(
			WorkBrDayChooseDto workBrDayChooseDto) {
		logger.debug("selectByPrimaryKey(String code ={}) - start", workBrDayChooseDto); 
		AssertUtils.notNull(workBrDayChooseDto);
		try {
			
			WorkBrDayChoose workBrDayChoose=new WorkBrDayChoose();
			workBrDayChoose.setCode(workBrDayChooseDto.getCode());
			workBrDayChoose.setMemberNameGm(workBrDayChooseDto.getMemberNameGm());
			workBrDayChoose.setMemberNoGm(workBrDayChooseDto.getMemberNoGm());
			workBrDayChoose.setMerchantNo(workBrDayChooseDto.getMerchantNo());
			workBrDayChoose.setCodeList(workBrDayChooseDto.getCodeList());
			workBrDayChoose.setNameList(workBrDayChooseDto.getNameList());
			workBrDayChoose.setShopName(workBrDayChooseDto.getShopName());
			workBrDayChoose.setShopNo(workBrDayChooseDto.getShopNo());
			workBrDayChoose.setSeq(workBrDayChooseDto.getSeq());
			workBrDayChoose.setCreateDate(workBrDayChooseDto.getCreateDate());
			workBrDayChooseDao.updateByPrimaryKeySelective(workBrDayChoose);	
			return workBrDayChooseDto;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			logger.error("新增日工作简报选择表错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_BR_DAY_CHOOSE_ADD_ERROR,"查询日工作简报选择表错误！",e);
		}
	
	}

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkBrDayChooseService#findWorkDayGmIndexByMNo(com.lj.business.st.dto.FindWorkDayGmIndex)
	 */
	@Override
	public List<FindWorkDayGmIndexListReturn> findWorkDayGmIndexByMNo(FindWorkDayGmIndex findWorkDayGmIndex) {
		AssertUtils.notNull(findWorkDayGmIndex);
		AssertUtils.notNullAndEmpty(findWorkDayGmIndex.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findWorkDayGmIndex.getStDate(),"统计日期不能为空");
		try{
			List<FindWorkDayGmIndexListReturn> list = new ArrayList<FindWorkDayGmIndexListReturn>();
			list = workBrDayChooseDao.findWorkDayGmIndexByMNo(findWorkDayGmIndex);
			return list;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查询日工作简报选择信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_BR_DAY_CHOOSE_FIND_ERROR,"查询日工作简报选择信息错误！",e);
		}
		
	}


	@Override
	public int deleteByPrimaryKey(String merchantNo) {
		logger.debug("deleteByPrimaryKey( String str ={}) - start", merchantNo); 
		AssertUtils.notNull(merchantNo);
		int num=0;
		try {
			num=workBrDayChooseDao.deleteByPrimaryKey(merchantNo);
		} catch (Exception e) {
			logger.error("删除日工作简报选择信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_BR_DAY_CHOOSE_FIND_ERROR,"删除日工作简报选择信息错误！",e);
		}
		return num;
	}

	@Override
	public List<FindWorkDayGmIndexListReturn> findWorkBrDayChooseList(
			FindWorkDayGmIndex findWorkDayGmIndex) {
		logger.debug("findWorkBrDayChooseList( FindWorkDayGmIndex findWorkDayGmIndex ={}) - start", findWorkDayGmIndex); 
		AssertUtils.notNull(findWorkDayGmIndex);
		List<FindWorkDayGmIndexListReturn> list=null;
		try {
			list=workBrDayChooseDao.findWorkBrDayChooseList(findWorkDayGmIndex);
		} catch (Exception e) {
			logger.error("查询日工作简报选择信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_BR_DAY_CHOOSE_FIND_ERROR,"查询日工作简报选择信息错误！",e);
		}
		return list;
	}
	
	

}
