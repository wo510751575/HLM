package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.GeneralCheck;
import com.ye.business.hx.dto.FindGeneralCheckPage;
import com.ye.business.hx.dto.GeneralCheckDto;

public interface IGeneralCheckDao {
    int deleteByPrimaryKey(String code);

    int insert(GeneralCheck record);

    int insertSelective(GeneralCheck record);

    GeneralCheck selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(GeneralCheck record);

    int updateByPrimaryKey(GeneralCheck record);

	/**   
	 * @Title: findGeneralChecks   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findGeneralCheckPage
	 * @param: @return      
	 * @return: List<GeneralCheckDto>      
	 * @throws   
	 */
	List<GeneralCheckDto> findGeneralChecks(FindGeneralCheckPage findGeneralCheckPage);

	/**   
	 * @Title: findGeneralCheckPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findGeneralCheckPage
	 * @param: @return      
	 * @return: List<GeneralCheckDto>      
	 * @throws   
	 */
	List<GeneralCheckDto> findGeneralCheckPage(FindGeneralCheckPage findGeneralCheckPage);

	/**   
	 * @Title: findGeneralCheckPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findGeneralCheckPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findGeneralCheckPageCount(FindGeneralCheckPage findGeneralCheckPage);
}