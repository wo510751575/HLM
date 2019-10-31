package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.GumCheck;
import com.ye.business.hx.dto.FindGumCheckPage;
import com.ye.business.hx.dto.GumCheckDto;

public interface IGumCheckDao {
    int deleteByPrimaryKey(String code);

    int insert(GumCheck record);

    int insertSelective(GumCheck record);

    GumCheck selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(GumCheck record);

    int updateByPrimaryKey(GumCheck record);

	/**   
	 * @Title: findGumChecks   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findGumCheckPage
	 * @param: @return      
	 * @return: List<GumCheckDto>      
	 * @throws   
	 */
	List<GumCheckDto> findGumChecks(FindGumCheckPage findGumCheckPage);

	/**   
	 * @Title: findGumCheckPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findGumCheckPage
	 * @param: @return      
	 * @return: List<GumCheckDto>      
	 * @throws   
	 */
	List<GumCheckDto> findGumCheckPage(FindGumCheckPage findGumCheckPage);

	/**   
	 * @Title: findGumCheckPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findGumCheckPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findGumCheckPageCount(FindGumCheckPage findGumCheckPage);
}