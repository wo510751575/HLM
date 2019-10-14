package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import com.lj.business.st.dto.AddEmployeeAnalyze;
import com.lj.business.st.dto.AddEmployeeAnalyzeReturn;
import com.lj.base.exception.TsfaServiceException;


/**
 * 
 * 
 * 类说明：员工画像
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月31日
 */
public interface IEmployeeAnalyzeService {
	
   /**
    * 
    *
    * 方法说明：新增员工画像信息
    *
    * @param addEmployeeAnalyze
    * @return
    * @throws TsfaServiceException
    *
    * @author 罗书明 CreateDate: 2017年7月31日
    *
    */
	public AddEmployeeAnalyzeReturn addEmployeeAnalyze(AddEmployeeAnalyze addEmployeeAnalyze) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：查询员工画像统计信息
	 *
	 * @param map
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年7月31日
	 *
	 */
	public List<Map<String,Object>> findEmployeeAnalyzeList(Map<String,Object> map)throws TsfaServiceException;
	

	

	

}
