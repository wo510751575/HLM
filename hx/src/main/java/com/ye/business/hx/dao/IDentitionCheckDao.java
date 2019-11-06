package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.DentitionCheck;
import com.ye.business.hx.dto.DentitionCheckDto;
import com.ye.business.hx.dto.FindDentitionCheckPage;

public interface IDentitionCheckDao {
    int deleteByPrimaryKey(String code);

    int insert(DentitionCheck record);

    int insertSelective(DentitionCheck record);

    DentitionCheck selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(DentitionCheck record);

    int updateByPrimaryKey(DentitionCheck record);

	/**   
	 * @Title: findDentitionChecks   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findDentitionCheckPage
	 * @param: @return      
	 * @return: List<DentitionCheckDto>      
	 * @throws   
	 */
	List<DentitionCheckDto> findDentitionChecks(FindDentitionCheckPage findDentitionCheckPage);

	/**   
	 * @Title: findDentitionCheckPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findDentitionCheckPage
	 * @param: @return      
	 * @return: List<DentitionCheckDto>      
	 * @throws   
	 */
	List<DentitionCheckDto> findDentitionCheckPage(FindDentitionCheckPage findDentitionCheckPage);

	/**   
	 * @Title: findDentitionCheckPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findDentitionCheckPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findDentitionCheckPageCount(FindDentitionCheckPage findDentitionCheckPage);
}