package com.ye.business.hx.dao;

import java.util.Date;
import java.util.List;

import com.ye.business.hx.domain.ToothCheck;
import com.ye.business.hx.dto.FindToothCheckPage;
import com.ye.business.hx.dto.ToothCheckDto;

public interface IToothCheckDao {
    int deleteByPrimaryKey(String code);

    int insert(ToothCheck record);

    int insertSelective(ToothCheck record);

    ToothCheck selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ToothCheck record);

    int updateByPrimaryKey(ToothCheck record);

	/**   
	 * @Title: findToothChecks   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findToothCheckPage
	 * @param: @return      
	 * @return: List<ToothCheckDto>      
	 * @throws   
	 */
	List<ToothCheckDto> findToothChecks(FindToothCheckPage findToothCheckPage);

	/**   
	 * @Title: findToothCheckPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findToothCheckPage
	 * @param: @return      
	 * @return: List<ToothCheckDto>      
	 * @throws   
	 */
	List<ToothCheckDto> findToothCheckPage(FindToothCheckPage findToothCheckPage);

	/**   
	 * @Title: findToothCheckPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findToothCheckPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findToothCheckPageCount(FindToothCheckPage findToothCheckPage);

	/**   
	 * @Title: findTimeList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findToothCheckPage
	 * @param: @return      
	 * @return: List<Date>      
	 * @throws   
	 */
	List<Date> findTimeList(FindToothCheckPage findToothCheckPage);
}