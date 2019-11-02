package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.TreatmentPlan;
import com.ye.business.hx.dto.FindTreatmentPlanPage;
import com.ye.business.hx.dto.TreatmentPlanDto;

public interface ITreatmentPlanDao {
    int deleteByPrimaryKey(String code);

    int insert(TreatmentPlan record);

    int insertSelective(TreatmentPlan record);

    TreatmentPlan selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(TreatmentPlan record);

    int updateByPrimaryKey(TreatmentPlan record);

	/**   
	 * @Title: findTreatmentPlans   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findTreatmentPlanPage
	 * @param: @return      
	 * @return: List<TreatmentPlanDto>      
	 * @throws   
	 */
	List<TreatmentPlanDto> findTreatmentPlans(FindTreatmentPlanPage findTreatmentPlanPage);

	/**   
	 * @Title: findTreatmentPlanPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findTreatmentPlanPage
	 * @param: @return      
	 * @return: List<TreatmentPlanDto>      
	 * @throws   
	 */
	List<TreatmentPlanDto> findTreatmentPlanPage(FindTreatmentPlanPage findTreatmentPlanPage);

	/**   
	 * @Title: findTreatmentPlanPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findTreatmentPlanPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findTreatmentPlanPageCount(FindTreatmentPlanPage findTreatmentPlanPage);
}