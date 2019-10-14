package com.lj.business.st.service;

import java.util.List;
import java.util.Map;

import com.lj.business.st.dto.AddClientExpTotal;
import com.lj.business.st.dto.FindClientExpReturn;
import com.lj.business.st.dto.FindClientExpTotal;
import com.lj.business.st.dto.FindClientExpTotalReturn;

/**
 * 
 * 
 * 类说明：到店客户体验统计
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月28日
 */
public interface IClientExpTotalService {
	/**
	 * 
	 *
	 * 方法说明：新增到店体验客户
	 *
	 * @param record
	 *
	 * @author 罗书明 CreateDate: 2017年7月28日
	 *
	 */
	public void insertSelective(AddClientExpTotal addClientExpTotal);
	/**
	 * 
	 *
	 * 方法说明：查询到店体验客户
	 *
	 * @param map
	 *      
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月28日
	 *
	 */
	public  List<FindClientExpReturn> findClientExpTotal(Map<String,Object> map);

	FindClientExpTotalReturn findClientExpTotalList(FindClientExpTotal findClientExpTotal);
}
