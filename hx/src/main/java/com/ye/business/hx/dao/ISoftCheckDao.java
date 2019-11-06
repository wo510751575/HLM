package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.SoftCheck;
import com.ye.business.hx.dto.FindSoftCheckPage;
import com.ye.business.hx.dto.SoftCheckDto;

public interface ISoftCheckDao {
    int deleteByPrimaryKey(String code);

    int insert(SoftCheck record);

    int insertSelective(SoftCheck record);

    SoftCheck selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SoftCheck record);

    int updateByPrimaryKey(SoftCheck record);

	/**   
	 * @Title: findSoftChecks   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findSoftCheckPage
	 * @param: @return      
	 * @return: List<SoftCheckDto>      
	 * @throws   
	 */
	List<SoftCheckDto> findSoftChecks(FindSoftCheckPage findSoftCheckPage);

	/**   
	 * @Title: findSoftCheckPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findSoftCheckPage
	 * @param: @return      
	 * @return: List<SoftCheckDto>      
	 * @throws   
	 */
	List<SoftCheckDto> findSoftCheckPage(FindSoftCheckPage findSoftCheckPage);

	/**   
	 * @Title: findSoftCheckPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findSoftCheckPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findSoftCheckPageCount(FindSoftCheckPage findSoftCheckPage);
}