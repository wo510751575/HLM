package com.lj.business.st.service.impl;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IOperationDayChooseDao;
import com.lj.business.st.domain.OperationDayChoose;
import com.lj.business.st.dto.FindOperateDayReport;
import com.lj.business.st.dto.FindOperateDayReportReturn;
import com.lj.business.st.dto.FindOperationDayChoose;
import com.lj.business.st.dto.OperationDayChooseDto;
import com.lj.business.st.service.IOperationDayChooseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * 
 * 类说明：运营日报表选择表（基础）
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
public class OperationDayChooseServiceImpl implements IOperationDayChooseService{
	
	private static final Logger logger = LoggerFactory.getLogger(OperationDayChooseServiceImpl.class);
	@Resource
	private IOperationDayChooseDao operationDayChooseDao;
	
	public OperationDayChooseDto insertSelectAll(
			OperationDayChooseDto operationDayChooseDto) {
		logger.debug("insertSelectAll(OperationDayChoose operationDayChoose={}) - start", operationDayChooseDto); 
		AssertUtils.notNull(operationDayChooseDto);
		try {
			
			OperationDayChoose operationDayChoose=new OperationDayChoose();		
			operationDayChoose.setCode(GUID.getPreUUID());
			operationDayChoose.setCodeList(operationDayChooseDto.getCodeList());
			operationDayChoose.setMemberNameGm(operationDayChooseDto.getMemberNameGm());
			operationDayChoose.setMemberNoGm(operationDayChooseDto.getMemberNoGm());
			operationDayChoose.setMerchantNo(operationDayChooseDto.getMerchantNo());
			operationDayChoose.setNameList(operationDayChooseDto.getNameList());
			operationDayChoose.setSeq(operationDayChooseDto.getSeq());
			operationDayChoose.setShopName(operationDayChooseDto.getShopName());
			operationDayChoose.setTypeList(operationDayChooseDto.getTypeList());
			operationDayChoose.setShopNo(operationDayChooseDto.getShopNo());
			operationDayChoose.setImgAddr(operationDayChooseDto.getImgAddr());
			operationDayChoose.setCreateDate(operationDayChooseDto.getCreateDate());
			operationDayChooseDao.insertSelective(operationDayChoose);
			return operationDayChooseDto;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			logger.error("新增运营分析报表选择表错误",e);
			throw new TsfaServiceException(ErrorCode.OPERATION_ANALYSIS_DAY_CHOOSE_FIND_ERROR,"新增运营分析报表选择表错误！",e);
		}
	}

	
	public List<OperationDayChooseDto> findOperationDayChoose(
			FindOperationDayChoose findOperationDayChoose ) {
		logger.debug("List<OperationDayChoosePage> (OperationDayChoosePage OperationDayChoosePage={}) - start", findOperationDayChoose); 
		AssertUtils.notNull(findOperationDayChoose);
		 List<OperationDayChooseDto> list;
		try {
			list=operationDayChooseDao.findOperationDayChoose(findOperationDayChoose);	    
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;    
		}catch(Exception e){
			logger.error("查询运营分析报表选择表错误",e);
			throw new TsfaServiceException(ErrorCode.OPERATION_ANALYSIS_DAY_CHOOSE_FIND_ERROR,"查询运营分析报表选择表错误！",e);
		}
		return list;
	}
   
	public OperationDayChooseDto selectByPrimaryKey(String code) {
		logger.debug("selectByPrimaryKey(String code={}) - start", code); 
		AssertUtils.notNull(code);
		try {
			OperationDayChoose operationDayChoose=operationDayChooseDao.selectByPrimaryKey(code);
			OperationDayChooseDto operationDayChooseDto=new OperationDayChooseDto();
			operationDayChooseDto.setCode(operationDayChoose.getCode());
			operationDayChooseDto.setCodeList(operationDayChoose.getCodeList());
			operationDayChooseDto.setMemberNameGm(operationDayChoose.getMemberNameGm());
			operationDayChooseDto.setMemberNoGm(operationDayChoose.getMemberNoGm());
			operationDayChooseDto.setMerchantNo(operationDayChoose.getMerchantNo());
			operationDayChooseDto.setNameList(operationDayChoose.getNameList());
			operationDayChooseDto.setSeq(operationDayChoose.getSeq());
			operationDayChooseDto.setShopName(operationDayChoose.getShopName());
			operationDayChooseDto.setShopNo(operationDayChoose.getShopNo());
			operationDayChooseDto.setImgAddr(operationDayChoose.getImgAddr());
			operationDayChooseDto.setTypeList(operationDayChoose.getTypeList());
			operationDayChooseDto.setCreateDate(operationDayChoose.getCreateDate());			
			return operationDayChooseDto;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			logger.error("查询运营分析报表选择表错误",e);
			throw new TsfaServiceException(ErrorCode.OPERATION_ANALYSIS_DAY_CHOOSE_FIND_ERROR,"查询运营分析报表选择表错误！",e);
		}

	}


	@Override
	public int deleteByPrimaryKey(String str) {
		logger.debug("List<OperationDayChoosePage> (OperationDayChoosePage OperationDayChoosePage={}) - start", str); 
		AssertUtils.notNull(str);
		int num=0;
		try {
			num=operationDayChooseDao.deleteByPrimaryKey(str);
		} catch (Exception e) {
			logger.error("删除运营分析报表选择表错误",e);
			throw new TsfaServiceException(ErrorCode.OPERATION_ANALYSIS_DAY_CHOOSE_FIND_ERROR,"删除运营分析报表选择表错误！",e);
		}
		return num;
	}

	@Override
	public List<FindOperateDayReportReturn> findOperationDayChooseList(FindOperateDayReport findOperateDayReport) {
		AssertUtils.notNull(findOperateDayReport);
		AssertUtils.notNullAndEmpty(findOperateDayReport.getMerchantNo(), "商户编号不能为空");
		return operationDayChooseDao.findOperationDayChooseList(findOperateDayReport);
	}

}
