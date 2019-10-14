package com.lj.business.st.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.ICfAnalyzeChooseDao;
import com.lj.business.st.domain.CfAnalyzeChoose;
import com.lj.business.st.dto.CfAnalyzeChooseDto;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyze;
import com.lj.business.st.service.ICfAnalyzeChooseService;

/**
 * 
 * 
 * 类说明：实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年8月1日
 */

@Service
public class CfAnalyzeChooseServiceImpl implements ICfAnalyzeChooseService {
	
	private static final Logger logger = LoggerFactory.getLogger(CfAnalyzeChooseServiceImpl.class);
	
	@Resource
	private ICfAnalyzeChooseDao cfAnalyzeChooseDao;

	@Override
	public void insertSelective(CfAnalyzeChooseDto record)throws TsfaServiceException  {
	logger.debug("addBestGmChoose(AddBestGmChoose addBestGmChoose={}) - start", record); 
	AssertUtils.notNull(record);
	try {
		CfAnalyzeChoose cfAnalyzeChoose=new CfAnalyzeChoose();
		cfAnalyzeChoose.setCode(GUID.getPreUUID());
		cfAnalyzeChoose.setCodeList(record.getCodeList());
		cfAnalyzeChoose.setNameList(record.getNameList());
		cfAnalyzeChoose.setMerchantNo(record.getMerchantNo());
		cfAnalyzeChoose.setMemberNameGm(record.getMemberNameGm());
		cfAnalyzeChoose.setMemberNoGm(record.getMemberNoGm());
		cfAnalyzeChoose.setShopNo(record.getShopNo());
		cfAnalyzeChoose.setShopName(record.getShopName());
		cfAnalyzeChoose.setSeq(record.getSeq());
		cfAnalyzeChoose.setTypeList(record.getTypeList());
		cfAnalyzeChoose.setImgAddr(record.getImgAddr());
		cfAnalyzeChooseDao.insertSelective(cfAnalyzeChoose);
	} catch (TsfaServiceException e) {
		logger.error(e.getMessage(),e);
		throw e;
	}catch(Exception e){
		logger.error("新增跟进分析选择表错误！",e);
		throw new TsfaServiceException(ErrorCode.CF_ANALYZE_CHOOSE_ADD_ERROR,"新增跟进分析选择表错误！",e);
	}
		
	} 

	@Override
	public CfAnalyzeChooseDto selectByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("addBestGmChoose(AddBestGmChoose addBestGmChoose={}) - start", code); 
		AssertUtils.notNull(code);
		try {
			CfAnalyzeChoose cfAnalyzeChoose=cfAnalyzeChooseDao.selectByPrimaryKey(code);
			CfAnalyzeChooseDto cfAnalyzeChooseDto=new CfAnalyzeChooseDto();
			cfAnalyzeChooseDto.setCode(cfAnalyzeChoose.getCode());
			cfAnalyzeChooseDto.setCodeList(cfAnalyzeChoose.getCodeList());
			cfAnalyzeChooseDto.setNameList(cfAnalyzeChoose.getNameList());
			cfAnalyzeChooseDto.setMemberNameGm(cfAnalyzeChoose.getMemberNameGm());
			cfAnalyzeChooseDto.setMerchantNo(cfAnalyzeChoose.getMerchantNo());
			cfAnalyzeChooseDto.setMemberNoGm(cfAnalyzeChoose.getMemberNoGm());
			cfAnalyzeChooseDto.setSeq(cfAnalyzeChoose.getSeq());
			cfAnalyzeChooseDto.setShopNo(cfAnalyzeChoose.getShopNo());
			cfAnalyzeChooseDto.setShopName(cfAnalyzeChoose.getShopName());
			cfAnalyzeChooseDto.setTypeList(cfAnalyzeChoose.getTypeList());
			cfAnalyzeChooseDto.setCreateDate(cfAnalyzeChoose.getCreateDate());
			return cfAnalyzeChooseDto;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			logger.error("查询跟进分析选择表错误！",e);
			throw new TsfaServiceException(ErrorCode.CF_ANALYZE_CHOOSE_FIND_ERROR,"查询跟进分析选择表错误！",e);
		}
		
	}

	@Override
	public List<CfAnalyzeChooseDto> findCfAnalyzeChoose(
			FindCfAnalyze findCfAnalyze) throws TsfaServiceException {
		logger.debug("addBestGmChoose(AddBestGmChoose addBestGmChoose={}) - start", findCfAnalyze); 
		AssertUtils.notNull(findCfAnalyze);
		List<CfAnalyzeChooseDto> list=null;
		try {
			list=cfAnalyzeChooseDao.findCfAnalyzeChoose(findCfAnalyze);
		} catch (Exception e) {
			logger.error("查询跟进分析选择表错误！",e);
			throw new TsfaServiceException(ErrorCode.CF_ANALYZE_CHOOSE_FIND_ERROR,"查询跟进分析选择表错误！",e);
		}
		return list;
	}

	@Override
	public int deleteByPrimaryKey(String merchantNo) {
		logger.debug("deleteByPrimaryKey(String merchantNo={}) - start", merchantNo); 
		AssertUtils.notNull(merchantNo);
		int num=0;
		try {
			num=cfAnalyzeChooseDao.deleteByPrimaryKey(merchantNo);
		} catch (Exception e) {
			logger.error("删除跟进分析选择表错误！",e);
			throw new TsfaServiceException(ErrorCode.CF_ANALYZE_CHOOSE_FIND_ERROR,"删除跟进分析选择表错误！",e);
		}
		return num;
	}

}
