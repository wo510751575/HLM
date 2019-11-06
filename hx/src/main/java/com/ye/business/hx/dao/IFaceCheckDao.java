package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.FaceCheck;
import com.ye.business.hx.dto.FaceCheckDto;
import com.ye.business.hx.dto.FindFaceCheckPage;

public interface IFaceCheckDao {
    int deleteByPrimaryKey(String code);

    int insert(FaceCheck record);

    int insertSelective(FaceCheck record);

    FaceCheck selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(FaceCheck record);

    int updateByPrimaryKey(FaceCheck record);

	/**   
	 * @Title: findFaceChecks   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findFaceCheckPage
	 * @param: @return      
	 * @return: List<FaceCheckDto>      
	 * @throws   
	 */
	List<FaceCheckDto> findFaceChecks(FindFaceCheckPage findFaceCheckPage);

	/**   
	 * @Title: findFaceCheckPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findFaceCheckPage
	 * @param: @return      
	 * @return: List<FaceCheckDto>      
	 * @throws   
	 */
	List<FaceCheckDto> findFaceCheckPage(FindFaceCheckPage findFaceCheckPage);

	/**   
	 * @Title: findFaceCheckPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findFaceCheckPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findFaceCheckPageCount(FindFaceCheckPage findFaceCheckPage);
}