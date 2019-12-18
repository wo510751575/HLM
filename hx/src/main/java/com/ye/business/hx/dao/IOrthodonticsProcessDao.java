package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.OrthodonticsProcess;
import com.ye.business.hx.dto.FindOrthodonticsProcessPage;
import com.ye.business.hx.dto.OrthodonticsProcessDto;

public interface IOrthodonticsProcessDao {
    int deleteByPrimaryKey(String code);

    int insert(OrthodonticsProcess record);

    int insertSelective(OrthodonticsProcess record);

    List<OrthodonticsProcessDto> selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(OrthodonticsProcess record);

    int updateByPrimaryKey(OrthodonticsProcess record);

	/**   
	 * @Title: findOrthodonticsProcesss   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findOrthodonticsProcessPage
	 * @param: @return      
	 * @return: List<OrthodonticsProcessDto>      
	 * @throws   
	 */
	List<OrthodonticsProcessDto> findOrthodonticsProcesss(FindOrthodonticsProcessPage findOrthodonticsProcessPage);

	/**   
	 * @Title: findOrthodonticsProcessPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findOrthodonticsProcessPage
	 * @param: @return      
	 * @return: List<OrthodonticsProcessDto>      
	 * @throws   
	 */
	List<OrthodonticsProcessDto> findOrthodonticsProcessPage(FindOrthodonticsProcessPage findOrthodonticsProcessPage);

	/**   
	 * @Title: findOrthodonticsProcessPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findOrthodonticsProcessPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findOrthodonticsProcessPageCount(FindOrthodonticsProcessPage findOrthodonticsProcessPage);
}