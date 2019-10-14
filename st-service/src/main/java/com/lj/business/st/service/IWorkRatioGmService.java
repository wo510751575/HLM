package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.domain.WorkRatioGm;
import com.lj.business.st.dto.AddWorkRatioGm;
import com.lj.business.st.dto.AddWorkRatioGmReturn;
import com.lj.business.st.dto.DelWorkRatioGm;
import com.lj.business.st.dto.DelWorkRatioGmReturn;
import com.lj.business.st.dto.FindFollowClientTotalIndex;
import com.lj.business.st.dto.FindOperateAnalyzeIndexReturn;
import com.lj.business.st.dto.FindOperateDayReport;
import com.lj.business.st.dto.FindWorkDayGmIndex;
import com.lj.business.st.dto.FindWorkDayGmIndexReturn;
import com.lj.business.st.dto.FindWorkRatioGm;
import com.lj.business.st.dto.FindWorkRatioGmPage;
import com.lj.business.st.dto.FindWorkRatioGmPageReturn;
import com.lj.business.st.dto.FindWorkRatioGmReturn;
import com.lj.business.st.dto.FindWrgTotal;
import com.lj.business.st.dto.FindWrgTotalReturn;
import com.lj.business.st.dto.UpdateWorkRatioGm;
import com.lj.business.st.dto.UpdateWorkRatioGmReturn;


/**
 * 类说明：接口类
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
public interface IWorkRatioGmService {
	

	/**
	 * 
	 *
	 * 方法说明：获取导购工作统计信息_H5用
	 *
	 * @param findWrgTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年7月31日
	 *
	 */
	public List<FindWrgTotalReturn> findWrgTotal(FindWrgTotal findWrgTotal) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：添加导购工作完成度统计信息
	 *
	 * @param addWorkRatioGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public AddWorkRatioGmReturn addWorkRatioGm(AddWorkRatioGm addWorkRatioGm) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购工作完成度统计信息
	 *
	 * @param findWorkRatioGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public FindWorkRatioGmReturn findWorkRatioGm(FindWorkRatioGm findWorkRatioGm) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除导购工作完成度统计信息
	 *
	 * @param delWorkRatioGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public DelWorkRatioGmReturn delWorkRatioGm(DelWorkRatioGm delWorkRatioGm) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购工作完成度统计信息
	 *
	 * @param updateWorkRatioGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public UpdateWorkRatioGmReturn updateWorkRatioGm(UpdateWorkRatioGm updateWorkRatioGm)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找导购工作完成度统计信息
	 *
	 * @param findWorkRatioGmPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public Page<FindWorkRatioGmPageReturn> findWorkRatioGmPage(FindWorkRatioGmPage findWorkRatioGmPage) throws TsfaServiceException;

	/**
	 *
	 * 方法说明：每天定时添加导购工作完成度统计信息
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月24日
	 *
	 */
	AddWorkRatioGmReturn addWorkRatioGm() throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：跟进统计首页
	 *
	 * @param findFollowClientTotalIndex
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年7月31日
	 *
	 */
	Double findFcTotalIndex(FindFollowClientTotalIndex findFollowClientTotalIndex) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询导购工作统计
	 *
	 * @param findFollowClientTotalIndex
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月31日
	 *
	 */
	List<WorkRatioGm> findWorkRatioGmList(FindFollowClientTotalIndex findFollowClientTotalIndex)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：根据导购号按照完成度查排名
	 *
	 * @param findFollowClientTotalIndex
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年7月31日
	 *
	 */
	Integer findGmIndex(FindFollowClientTotalIndex findFollowClientTotalIndex) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：日工作简报
	 *
	 * @param findWorkDayGmIndex
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年7月31日
	 *
	 */
	List<FindWorkDayGmIndexReturn> findWorkDayGmIndex(FindWorkDayGmIndex findWorkDayGmIndex) throws TsfaServiceException;

	/**
	 * 查询运营日报
	 * @param findOperateDayReport
	 * @return
	 * @throws TsfaServiceException
	 */
	FindOperateAnalyzeIndexReturn findOperateAnalyzeIndex(FindOperateDayReport findOperateDayReport) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据时间从导购工作统计中查询门店工作统计
	 *
	 * @param preday
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年8月21日
	 *
	 */
	public List<WorkRatioGm> findWorkRatioShopByDay(Date preday) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据时间和维度从导购工作统计中查询门店工作统计
	 *
	 * @param map
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年9月04日
	 *
	 */
	public List<WorkRatioGm> findWorkRatioByDimDay(Map<String, Object> map);

}
