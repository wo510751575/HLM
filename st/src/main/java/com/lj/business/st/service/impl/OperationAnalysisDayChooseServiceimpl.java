package com.lj.business.st.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.lj.business.st.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IOperationAnalysisDayChooseDao;
import com.lj.business.st.domain.OperationAnalysisDayChoose;
import com.lj.business.st.service.IOperationAnalysisDayChooseService;
                  
/**
 * 
 * 
 * 类说明：运营分析报表选择表Service接口实现类
 *  OperationAnalysisDayChoose
 * 
 * <p>
 * 详细描述：
 *   WorkBrDayChoose
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月1日
 */
@Service
public class OperationAnalysisDayChooseServiceimpl implements IOperationAnalysisDayChooseService{
	
	@Resource
	private IOperationAnalysisDayChooseDao operationAnalysisDayChooseDao;
	   
	private static final Logger logger = LoggerFactory.getLogger(OperationAnalysisDayChooseServiceimpl.class);

	
	public OperationAnalysisDayChooseDto insertSelective(
			OperationAnalysisDayChooseDto operationAnalysisDayChoose) {
		logger.debug("insertSelective(OperationAnalysisDayChoose operationAnalysisDayChoose={}) - start", operationAnalysisDayChoose); 
		AssertUtils.notNull(operationAnalysisDayChoose);
		try {
			OperationAnalysisDayChoose operationAnalysisDayChooses=new OperationAnalysisDayChoose(); 			
			operationAnalysisDayChooses.setCode(GUID.getPreUUID());
			operationAnalysisDayChooses.setShopNo(operationAnalysisDayChoose.getShopNo());
			operationAnalysisDayChooses.setCodeList(operationAnalysisDayChoose.getCodeList());
			operationAnalysisDayChooses.setMemberNameGm(operationAnalysisDayChoose.getMemberNameGm());
			operationAnalysisDayChooses.setMemberNoGm(operationAnalysisDayChoose.getMemberNoGm());
			operationAnalysisDayChooses.setMerchantNo(operationAnalysisDayChoose.getMerchantNo()); 
			operationAnalysisDayChooses.setNameList(operationAnalysisDayChoose.getNameList());
			operationAnalysisDayChooses.setShopName(operationAnalysisDayChoose.getShopName());
			operationAnalysisDayChooses.setSeq(operationAnalysisDayChoose.getSeq());
			operationAnalysisDayChooses.setTypeList(operationAnalysisDayChoose.getTypeList());
			operationAnalysisDayChooses.setCreateDate(operationAnalysisDayChoose.getCreateDate());
			operationAnalysisDayChooses.setImgAddr(operationAnalysisDayChoose.getImgAddr());
			operationAnalysisDayChooseDao.insertSelective(operationAnalysisDayChooses);
			OperationAnalysisDayChooseDto operationAnalysisDayChooseDto=new OperationAnalysisDayChooseDto();
			return operationAnalysisDayChooseDto;
			
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			logger.error("新增运营分析报表选择表错误",e);
			throw new TsfaServiceException(ErrorCode.OPERATION_ANALYSIS_DAY_CHOOSE_ADD_ERROR,"新增运营分析报表选择表错误！",e);
		}
	
	}

	public OperationAnalysisDayChooseDto selectByPrimaryKey(String code) {
		logger.debug(" selectByPrimaryKey(String code)={}) - start", code); 
		AssertUtils.notNull(code);
		try {
			OperationAnalysisDayChoose operationAnalysisDayChoose=operationAnalysisDayChooseDao.selectByPrimaryKey(code);
			OperationAnalysisDayChooseDto operationAnalysisDayChooseDto=new OperationAnalysisDayChooseDto();
			operationAnalysisDayChooseDto.setCode(operationAnalysisDayChoose.getCode());
			operationAnalysisDayChooseDto.setShopNo(operationAnalysisDayChoose.getShopNo());
			operationAnalysisDayChooseDto.setCodeList(operationAnalysisDayChoose.getCodeList());
			operationAnalysisDayChooseDto.setMemberNameGm(operationAnalysisDayChoose.getMemberNameGm());
			operationAnalysisDayChooseDto.setMemberNoGm(operationAnalysisDayChoose.getMemberNoGm());
			operationAnalysisDayChooseDto.setMerchantNo(operationAnalysisDayChoose.getMerchantNo()); 
			operationAnalysisDayChooseDto.setNameList(operationAnalysisDayChoose.getNameList());
			operationAnalysisDayChooseDto.setShopName(operationAnalysisDayChoose.getShopName());
			operationAnalysisDayChooseDto.setTypeList(operationAnalysisDayChoose.getTypeList());
			operationAnalysisDayChooseDto.setImgAddr(operationAnalysisDayChoose.getImgAddr());
			operationAnalysisDayChooseDto.setCreateDate(operationAnalysisDayChoose.getCreateDate());			
			return operationAnalysisDayChooseDto;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			logger.error("查询运营分析报表选择表错误！",e);
			throw new TsfaServiceException(ErrorCode.OPERATION_ANALYSIS_DAY_CHOOSE_FIND_ERROR,"查询运营分析报表选择表错误！",e);
		}
		
	}





	@Override
	public List<OperationAnalysisDayChooseDto> findOperationAnalysisDayChoose(
			FindOperationAnalysisDayChoose findOperationAnalysisDayChoose) {
		logger.debug(" findOperationAnalysisDayChoose(FindOperationAnalysisDayChoose findOperationAnalysisDayChoose={}) - start", findOperationAnalysisDayChoose); 
		AssertUtils.notNull(findOperationAnalysisDayChoose);
	  List<OperationAnalysisDayChooseDto> list=null;
		try {
			list=operationAnalysisDayChooseDao.findOperationAnalysisDayChoose(findOperationAnalysisDayChoose);
		} catch (Exception e) {
			logger.error("查询运营分析报表选择表错误！",e);
			throw new TsfaServiceException(ErrorCode.OPERATION_ANALYSIS_DAY_CHOOSE_FIND_ERROR,"查询运营分析报表选择表错误！",e);
		}
		return list;
	}


	@Override
	public int deleteByMerchantNo(String merchantNo) {
		logger.debug(" deleteByMerchantNo(String merchantNo)={}) - start", merchantNo); 
		AssertUtils.notNull(merchantNo);
		int num=0;
		try {
			num=operationAnalysisDayChooseDao.deleteByMerchantNo(merchantNo);
		} catch (Exception e) {
			logger.error("查询运营分析报表选择表错误！",e);
			throw new TsfaServiceException(ErrorCode.OPERATION_ANALYSIS_DAY_CHOOSE_FIND_ERROR,"查询运营分析报表选择表错误！",e);
		}
		return num;
	}

	@Override
	public List<FindOperateAnalysisReturn> findOperationAnalysisChooseList(FindOperateDayReport findOperateDayReport) {
		AssertUtils.notNull(findOperateDayReport);
		AssertUtils.notNullAndEmpty(findOperateDayReport.getMerchantNo(), "商户编号不能为空");
		return operationAnalysisDayChooseDao.findOperationAnalysisDayChooseList(findOperateDayReport);
	}


}
