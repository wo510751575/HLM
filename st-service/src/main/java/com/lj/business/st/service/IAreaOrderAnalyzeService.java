package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.AreaOrderAnalyze.AddAreaOrderAnalyze;
import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyze;
import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyzeReturn;

import java.util.List;


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
public interface IAreaOrderAnalyzeService {
	
	/**
	 * 
	 *
	 * 方法说明：添加区域订单分析表信息
	 *
	 * @param addAreaOrderAnalyze
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addAreaOrderAnalyze(AddAreaOrderAnalyze addAreaOrderAnalyze) throws TsfaServiceException;

	/**
	 *
	 *
	 * 方法说明：查找区域订单
	 *
	 * @param findAreaOrderAnalyze
	 * @return
	 * @throws TsfaServiceException
	 *
	 */
	List<FindAreaOrderAnalyzeReturn> findAreaOrderAnalyzeList(FindAreaOrderAnalyze findAreaOrderAnalyze) throws TsfaServiceException;
	
    /**
     * 
     *
     * 方法说明：查找区域成单最多的区域(商户)
     *
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月18日
     *
     */
    public  List<FindAreaOrderAnalyzeReturn> findAreaCodeMaxNum(FindAreaOrderAnalyze findAreaOrderAnalyze);

}
