package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.OrthodonticsPlan;
import com.ye.business.hx.dto.FindOrthodonticsPlanPage;
import com.ye.business.hx.dto.OrthodonticsPlanDto;

public interface IOrthodonticsPlanDao {
    int deleteByPrimaryKey(String code);

    int insert(OrthodonticsPlan record);

    int insertSelective(OrthodonticsPlan record);

    OrthodonticsPlan selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(OrthodonticsPlan record);

    int updateByPrimaryKeyWithBLOBs(OrthodonticsPlan record);

    int updateByPrimaryKey(OrthodonticsPlan record);

	/**   
	 * @Title: findOrthodonticsPlans   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findOrthodonticsPlanPage
	 * @param: @return      
	 * @return: List<OrthodonticsPlanDto>      
	 * @throws   
	 */
	List<OrthodonticsPlanDto> findOrthodonticsPlans(FindOrthodonticsPlanPage findOrthodonticsPlanPage);

	/**   
	 * @Title: findOrthodonticsPlanPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findOrthodonticsPlanPage
	 * @param: @return      
	 * @return: List<OrthodonticsPlanDto>      
	 * @throws   
	 */
	List<OrthodonticsPlanDto> findOrthodonticsPlanPage(FindOrthodonticsPlanPage findOrthodonticsPlanPage);

	/**   
	 * @Title: findOrthodonticsPlanPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findOrthodonticsPlanPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findOrthodonticsPlanPageCount(FindOrthodonticsPlanPage findOrthodonticsPlanPage);
}