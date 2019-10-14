package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.domain.OperationAnalysisDayBrief;
import com.lj.business.st.dto.FindOperateDayReport;
import com.lj.business.st.dto.OperationAnalysisDayBrief.AddOperationAnalysisDayBrief;
import com.lj.business.st.dto.OperationAnalysisDayBrief.AddOperationAnalysisDayBriefReturn;
import com.lj.business.st.dto.OperationAnalysisDayBrief.FindOperationAnalysisDayBrief;
import com.lj.business.st.dto.OperationAnalysisDayBrief.FindOperationAnalysisDayBriefReturn;

import java.util.List;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 邹磊
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface IOperationAnalysisDayBriefService {
	
	/**
	 * 
	 *
	 * 方法说明：添加运营分析报表摘要表信息
	 *
	 * @param addOperationAnalysisDayBrief
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public AddOperationAnalysisDayBriefReturn addOperationAnalysisDayBrief(AddOperationAnalysisDayBrief addOperationAnalysisDayBrief) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找运营分析报表摘要表信息
	 *
	 * @param findOperationAnalysisDayBrief
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindOperationAnalysisDayBriefReturn findOperationAnalysisDayBrief(FindOperationAnalysisDayBrief findOperationAnalysisDayBrief) throws TsfaServiceException;
    
	List<OperationAnalysisDayBrief> selectYesterdayList(FindOperateDayReport findOperateDayReport);
}
