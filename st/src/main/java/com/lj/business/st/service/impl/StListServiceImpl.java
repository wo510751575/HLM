package com.lj.business.st.service.impl;

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

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IStListDao;
import com.lj.business.st.domain.StList;
import com.lj.business.st.dto.AddStList;
import com.lj.business.st.dto.AddStListReturn;
import com.lj.business.st.dto.CfAnalyzeChooseDto;
import com.lj.business.st.dto.DelStList;
import com.lj.business.st.dto.DelStListReturn;
import com.lj.business.st.dto.FindStList;
import com.lj.business.st.dto.FindStListPage;
import com.lj.business.st.dto.FindStListPageReturn;
import com.lj.business.st.dto.FindStListReturn;
import com.lj.business.st.dto.InitStListByMerchant;
import com.lj.business.st.dto.OperationAnalysisDayChooseDto;
import com.lj.business.st.dto.OperationDayChooseDto;
import com.lj.business.st.dto.UpdateStList;
import com.lj.business.st.dto.UpdateStListReturn;
import com.lj.business.st.dto.WorkBrDayChooseDto;
import com.lj.business.st.dto.bestGmChoose.AddBestGmChoose;
import com.lj.business.st.emus.TableList;
import com.lj.business.st.emus.TypeList;
import com.lj.business.st.service.IBestGmChooseService;
import com.lj.business.st.service.ICfAnalyzeChooseService;
import com.lj.business.st.service.IOperationAnalysisDayChooseService;
import com.lj.business.st.service.IOperationDayChooseService;
import com.lj.business.st.service.IStListService;
import com.lj.business.st.service.IWorkBrDayChooseService;

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
public class StListServiceImpl implements IStListService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(StListServiceImpl.class);
	

	@Resource
	private IStListDao stListDao;
	
	@Resource
	private IWorkBrDayChooseService workBrDayChooseService;
	
	@Resource
	private ICfAnalyzeChooseService cfAnalyzeChooseService;
	
	@Resource
	private IOperationDayChooseService operationDayChooseService;
	
	@Resource
	private IBestGmChooseService bestGmChooseService;
	
	@Resource
	private IOperationAnalysisDayChooseService operationAnalysisDayChooseService;
	
	
	@Override
	public AddStListReturn addStList(
			AddStList addStList) throws TsfaServiceException {
		logger.debug("addStList(AddStList addStList={}) - start", addStList); 

		AssertUtils.notNull(addStList);
		try {
			StList stList = new StList();
			//add数据录入
			stList.setCode(GUID.getPreUUID());
			stList.setNameList(addStList.getNameList());
			stList.setDesList(addStList.getDesList());
			stList.setStatus(addStList.getStatus());
			stList.setTypeList(addStList.getTypeList());
			stList.setUnitList(addStList.getUnitList());
			stList.setTableList(addStList.getTableList());
			stList.setImgAddr(addStList.getImgAddr());
			stList.setCreateDate(new Date());
			stListDao.insert(stList);
			AddStListReturn addStListReturn = new AddStListReturn();
			
			logger.debug("addStList(AddStList) - end - return value={}", addStListReturn); 
			return addStListReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增报表项目信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ST_LIST_ADD_ERROR,"新增报表项目信息错误！",e);
		}
	}
	

	@Override
	public UpdateStListReturn updateStList(
			UpdateStList updateStList)
			throws TsfaServiceException {
		logger.debug("updateStList(UpdateStList updateStList={}) - start", updateStList); //$NON-NLS-1$

		AssertUtils.notNull(updateStList);
		AssertUtils.notNullAndEmpty(updateStList.getCode(),"Code不能为空");
		try {
			StList stList = new StList();
			//update数据录入
			stList.setCode(updateStList.getCode());
			stList.setNameList(updateStList.getNameList());
			stList.setDesList(updateStList.getDesList());
			stList.setStatus(updateStList.getStatus());
			stList.setTypeList(updateStList.getTypeList());
			stList.setUnitList(updateStList.getUnitList());
			stList.setTableList(updateStList.getTableList());
			stList.setImgAddr(updateStList.getImgAddr());
			stList.setCreateDate(updateStList.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(stListDao.updateByPrimaryKeySelective(stList));
			UpdateStListReturn updateStListReturn = new UpdateStListReturn();

			logger.debug("updateStList(UpdateStList) - end - return value={}", updateStListReturn); //$NON-NLS-1$
			return updateStListReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("报表项目信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ST_LIST_UPDATE_ERROR,"报表项目信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindStListReturn findStList(
			FindStList findStList) throws TsfaServiceException {
		logger.debug("findStList(FindStList findStList={}) - start", findStList); //$NON-NLS-1$

		AssertUtils.notNull(findStList);
		AssertUtils.notAllNull(findStList.getCode(),"ID不能为空");
		try {
			StList stList = stListDao.selectByPrimaryKey(findStList.getCode());
			if(stList == null){
				throw new TsfaServiceException(ErrorCode.ST_LIST_NOT_EXIST_ERROR,"报表项目信息不存在");
			}
			FindStListReturn findStListReturn = new FindStListReturn();
			//find数据录入
			findStListReturn.setCode(stList.getCode());
			findStListReturn.setNameList(stList.getNameList());
			findStListReturn.setDesList(stList.getDesList());
			findStListReturn.setStatus(stList.getStatus());
			findStListReturn.setTypeList(stList.getTypeList());
			findStListReturn.setUnitList(stList.getUnitList());
			findStListReturn.setTableList(stList.getTableList());
			findStListReturn.setImgAddr(stList.getImgAddr());
			findStListReturn.setCreateDate(stList.getCreateDate());
			
			logger.debug("findStList(FindStList) - end - return value={}", findStListReturn); //$NON-NLS-1$
			return findStListReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找报表项目信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ST_LIST_FIND_ERROR,"查找报表项目信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindStListPageReturn> findStListPage(
			FindStListPage findStListPage)
			throws TsfaServiceException {
		logger.debug("findStListPage(FindStListPage findStListPage={}) - start", findStListPage); //$NON-NLS-1$

		AssertUtils.notNull(findStListPage);
		List<FindStListPageReturn> returnList;
		int count = 0;
		try {
			returnList = stListDao.findStListPage(findStListPage);
			count = stListDao.findStListPageCount(findStListPage);
		}  catch (Exception e) {
			logger.error("报表项目信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.ST_LIST_FIND_PAGE_ERROR,"报表项目信息不存在错误.！",e);
		}
		Page<FindStListPageReturn> returnPage = new Page<FindStListPageReturn>(returnList, count, findStListPage);

		logger.debug("findStListPage(FindStListPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public DelStListReturn delStList(DelStList delStList)
			throws TsfaServiceException {
		//TODO
		return null;
	}


	@Override
	public List<FindStListPageReturn> findAllVaildStList() throws TsfaServiceException {
		try {
			return stListDao.findAllVaildStList();
		} catch (Exception e) {
			logger.error("查询所有有效报表项目信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ST_LIST_FIND_ERROR,"查询所有有效报表项目信息错误！",e);
		}
	}


	@Override
	public void initStListByMerchant(InitStListByMerchant initStListByMerchant) throws TsfaServiceException {
		logger.debug("initStListByMerchant(InitStListByMerchant initStListByMerchant={}) - start", initStListByMerchant); //$NON-NLS-1$
		
		AssertUtils.notNull(initStListByMerchant);
		AssertUtils.notAllNull(initStListByMerchant.getMerchantNo(),"商户编号不能为空");
		
		// 查询所有有效报表项目信息
		List<FindStListPageReturn> stListReturns = this.findAllVaildStList();
		for (FindStListPageReturn st : stListReturns) {
			switch (TableList.valueOf(st.getTableList())) {
			case WORK_BR_DAY_LIST:				// 日工作简报
				this.addWorkBrDayList(st, initStListByMerchant);
				break;
			case CF_ANALYZE_CHOOSE:				// 跟进分析
				this.addCfAnalyzeChoose(st, initStListByMerchant);
				break;
			case OPERATION_ANALYSIS_DAY_CHOOSE:	// 运营分析
				this.addOperationAnalysisDayChoose(st, initStListByMerchant);
				break;
			case BEST_GM_CHOOSE:					// 优秀导购
				this.addBestGmChoose(st, initStListByMerchant);
				break;
			case OPERATION_DAY_CHOOSE:			// 运营日报
				this.addOperationDayChoose(st, initStListByMerchant);
				break;
			default:
				continue;
			}
		}
		
	} 

	/**
	 * 
	 *
	 * 方法说明：生成日工作简报选择记录
	 *
	 * @param st
	 * @param initStListByMerchant
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月19日
	 *
	 */
	private void addWorkBrDayList(FindStListPageReturn st, InitStListByMerchant initStListByMerchant) {
		WorkBrDayChooseDto workBrDayChooseDto = new WorkBrDayChooseDto();
		workBrDayChooseDto.setMerchantNo(initStListByMerchant.getMerchantNo());
		workBrDayChooseDto.setCodeList(st.getCode());
		workBrDayChooseDto.setNameList(st.getNameList());
		workBrDayChooseDto.setTypeList(st.getTypeList());
		workBrDayChooseDto.setImgAddr(st.getImgAddr());
		switch (TypeList.valueOf(st.getTypeList())) {
		case SALE:							//	销售额
			workBrDayChooseDto.setSeq(1);
			break;
		case ORDER:						//	成单客户数
			workBrDayChooseDto.setSeq(2);
			break;
		case SUCCESS:						//	成单客户数
			workBrDayChooseDto.setSeq(2);
			break;
		case NEW_CUSTOMER:				//	新增客户数
			workBrDayChooseDto.setSeq(3);
			break;
		case NEW:							//	新增客户数
			workBrDayChooseDto.setSeq(3);
			break;
		case INTENTION_CUSTOMER:			//	意向客户数
			workBrDayChooseDto.setSeq(4);
			break;
		case INTENTION:					//	意向客户数
			workBrDayChooseDto.setSeq(4);
			break;
		case ABANDON_CUSTOMER:			//	暂停跟进数
			workBrDayChooseDto.setSeq(5);
			break;
		case GIVEUP:						//	暂停跟进数
			workBrDayChooseDto.setSeq(5);
			break;
		default:
			workBrDayChooseDto.setSeq(99);
			break;
		}
		workBrDayChooseService.insertSelective(workBrDayChooseDto);
	}
	
	/**
	 * 
	 *
	 * 方法说明：生成跟进分析选择记录
	 *
	 * @param st
	 * @param initStListByMerchant
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月19日
	 *
	 */
	private void addCfAnalyzeChoose(FindStListPageReturn st, InitStListByMerchant initStListByMerchant) {
		CfAnalyzeChooseDto cfAnalyzeChooseDto = new CfAnalyzeChooseDto();
		cfAnalyzeChooseDto.setMerchantNo(initStListByMerchant.getMerchantNo());
		cfAnalyzeChooseDto.setCodeList(st.getCode());
		cfAnalyzeChooseDto.setNameList(st.getNameList());
		cfAnalyzeChooseDto.setTypeList(st.getTypeList());
		cfAnalyzeChooseDto.setImgAddr(st.getImgAddr());
		switch (TypeList.valueOf(st.getTypeList())) {
		case CLIENT_TYPE:					//	客户分类
			cfAnalyzeChooseDto.setSeq(1);
			break;
		case SOCIAL:						//	社交分析
			cfAnalyzeChooseDto.setSeq(2);
			break;
		case MATERIAL:						//	素材分析
			cfAnalyzeChooseDto.setSeq(3);
			break;
		case CLIENT_ANALYZE:				//	客户分析
			cfAnalyzeChooseDto.setSeq(4);
			break;
		case CLIENT_ACTION:				//	客户行为
			cfAnalyzeChooseDto.setSeq(5);
			break;
		default:
			cfAnalyzeChooseDto.setSeq(99);
			break;
		}
		cfAnalyzeChooseService.insertSelective(cfAnalyzeChooseDto);
	}
	
	/**
	 * 
	 *
	 * 方法说明：生成运营分析选择记录
	 *
	 * @param st
	 * @param initStListByMerchant
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月19日
	 *
	 */
	private void addOperationAnalysisDayChoose(FindStListPageReturn st, InitStListByMerchant initStListByMerchant) {
		OperationAnalysisDayChooseDto operationAnalysisDayChoose = new OperationAnalysisDayChooseDto();
		operationAnalysisDayChoose.setMerchantNo(initStListByMerchant.getMerchantNo());
		operationAnalysisDayChoose.setCodeList(st.getCode());
		operationAnalysisDayChoose.setNameList(st.getNameList());
		operationAnalysisDayChoose.setTypeList(st.getTypeList());
		operationAnalysisDayChoose.setImgAddr(st.getImgAddr());
		switch (TypeList.valueOf(st.getTypeList())) {
		case SALE_FUNNEL:					//	销售漏斗
			operationAnalysisDayChoose.setSeq(1);
			break;
		case CUSTOMER_PICTURE:			//	客户画像
			operationAnalysisDayChoose.setSeq(2);
			break;
		case FOLLOW:						//	跟进分析
			operationAnalysisDayChoose.setSeq(3);
			break;
		case AREA_CUSTOMER:				//	区域客户分析
			operationAnalysisDayChoose.setSeq(4);
			break;
		case AREA_ORDER:					//	区域成单分析
			operationAnalysisDayChoose.setSeq(5);
			break;
		case CUSTOMER_BEHAVIOR_AS:		//	客户行为分析
			operationAnalysisDayChoose.setSeq(6);
			break;
		default:
			operationAnalysisDayChoose.setSeq(99);
			break;
		}
		operationAnalysisDayChooseService.insertSelective(operationAnalysisDayChoose);
	}
	
	/**
	 * 
	 *
	 * 方法说明：生成优秀导购选择记录
	 *
	 * @param st
	 * @param initStListByMerchant
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月19日
	 *
	 */
	private void addBestGmChoose(FindStListPageReturn st, InitStListByMerchant initStListByMerchant) {
		AddBestGmChoose addBestGmChoose = new AddBestGmChoose();
		addBestGmChoose.setMerchantNo(initStListByMerchant.getMerchantNo());
		addBestGmChoose.setCodeList(st.getCode());
		addBestGmChoose.setNameList(st.getNameList());
		addBestGmChoose.setTypeList(st.getTypeList());
		addBestGmChoose.setImgAddr(st.getImgAddr());
		switch (TypeList.valueOf(st.getTypeList())) {
		case HARDWORKING_AWARD:			//	最勤快奖
			addBestGmChoose.setSeq(1);
			break;
		case NEW_CUSTOMER_MAX:			//	新增客户最多奖
			addBestGmChoose.setSeq(2);
			break;
		case BEST_SALES:					//	最牛销售奖
			addBestGmChoose.setSeq(3);
			break;
		case EXTRUDE_FOLLOW:				//	跟进效果突出奖
			addBestGmChoose.setSeq(4);
			break;
		case ACTIVITY_AWARD:				//	活动奖
			addBestGmChoose.setSeq(5);
			break;
		case CUSTOMER_MAX:				//	客户最多奖
			addBestGmChoose.setSeq(6);
			break;
		default:
			addBestGmChoose.setSeq(99);
			break;
		}
		bestGmChooseService.addBestGmChoose(addBestGmChoose);
	}
	
	/**
	 * 
	 *
	 * 方法说明：生成运营日报选择记录
	 *
	 * @param st
	 * @param initStListByMerchant
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月19日
	 *
	 */
	private void addOperationDayChoose(FindStListPageReturn st, InitStListByMerchant initStListByMerchant) {
		OperationDayChooseDto operationDayChooseDto = new OperationDayChooseDto();
		operationDayChooseDto.setMerchantNo(initStListByMerchant.getMerchantNo());
		operationDayChooseDto.setCodeList(st.getCode());
		operationDayChooseDto.setNameList(st.getNameList());
		operationDayChooseDto.setTypeList(st.getTypeList());
		operationDayChooseDto.setImgAddr(st.getImgAddr());
		switch (TypeList.valueOf(st.getTypeList())) {
		case TOTAL_CUSTOMER:				//	总客户量
			operationDayChooseDto.setSeq(1);
			break;
		case ORDER:						//	成单量
			operationDayChooseDto.setSeq(2);
			break;
		case VISIT_CUSTOMER:				//	到店体验量
			operationDayChooseDto.setSeq(3);
			break;
		case INTENTION_CUSTOMER:			//	意向客户数
			operationDayChooseDto.setSeq(4);
			break;
		case NEW_CUSTOMER:				//	新增客户量
			operationDayChooseDto.setSeq(5);
			break;
		case ABANDON_CUSTOMER:			//	暂停跟进客户量
			operationDayChooseDto.setSeq(6);
			break;
		default:
			operationDayChooseDto.setSeq(99);
			break;
		}
		operationDayChooseService.insertSelectAll(operationDayChooseDto);
	}
}
