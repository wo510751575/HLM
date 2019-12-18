package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.OrthodonticsProcessRecord;
import com.ye.business.hx.dto.FindOrthodonticsProcessRecordPage;
import com.ye.business.hx.dto.OrthodonticsProcessRecordDto;

public interface IOrthodonticsProcessRecordDao {
    int deleteByPrimaryKey(String code);

    int insert(OrthodonticsProcessRecord record);

    int insertSelective(OrthodonticsProcessRecord record);

    OrthodonticsProcessRecord selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(OrthodonticsProcessRecord record);

    int updateByPrimaryKey(OrthodonticsProcessRecord record);

	/**   
	 * @Title: findOrthodonticsProcessRecords   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findOrthodonticsProcessRecordPage
	 * @param: @return      
	 * @return: List<OrthodonticsProcessRecordDto>      
	 * @throws   
	 */
	List<OrthodonticsProcessRecordDto> findOrthodonticsProcessRecords(
			FindOrthodonticsProcessRecordPage findOrthodonticsProcessRecordPage);

	/**   
	 * @Title: findOrthodonticsProcessRecordPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findOrthodonticsProcessRecordPage
	 * @param: @return      
	 * @return: List<OrthodonticsProcessRecordDto>      
	 * @throws   
	 */
	List<OrthodonticsProcessRecordDto> findOrthodonticsProcessRecordPage(
			FindOrthodonticsProcessRecordPage findOrthodonticsProcessRecordPage);

	/**   
	 * @Title: findOrthodonticsProcessRecordPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findOrthodonticsProcessRecordPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findOrthodonticsProcessRecordPageCount(FindOrthodonticsProcessRecordPage findOrthodonticsProcessRecordPage);
}