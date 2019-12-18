package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.OrthodonticsTemplate;
import com.ye.business.hx.dto.FindOrthodonticsTemplatePage;
import com.ye.business.hx.dto.OrthodonticsTemplateDto;
import com.ye.business.hx.dto.OrthodonticsTemplateVo;

public interface IOrthodonticsTemplateDao {
    int deleteByPrimaryKey(String code);

    int insert(OrthodonticsTemplate record);

    int insertSelective(OrthodonticsTemplate record);

    OrthodonticsTemplate selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(OrthodonticsTemplate record);

    int updateByPrimaryKey(OrthodonticsTemplate record);

	/**   
	 * @Title: findOrthodonticsTemplates   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findOrthodonticsTemplatePage
	 * @param: @return      
	 * @return: List<OrthodonticsTemplateDto>      
	 * @throws   
	 */
	List<OrthodonticsTemplateVo> findOrthodonticsTemplates();

	/**   
	 * @Title: findOrthodonticsTemplatePage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findOrthodonticsTemplatePage
	 * @param: @return      
	 * @return: List<OrthodonticsTemplateDto>      
	 * @throws   
	 */
	List<OrthodonticsTemplateDto> findOrthodonticsTemplatePage(
			FindOrthodonticsTemplatePage findOrthodonticsTemplatePage);

	/**   
	 * @Title: findOrthodonticsTemplatePageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findOrthodonticsTemplatePage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findOrthodonticsTemplatePageCount(FindOrthodonticsTemplatePage findOrthodonticsTemplatePage);
}