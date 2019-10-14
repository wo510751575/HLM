package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.dto.MaterialTypes;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月24日下午2:31:30
 */
public interface IMaterialTypesDao {

	/**
	 *@Desc 
	 *@return
	 *@return List<MaterialTypes>
	 *@author 贾光朝
	 *@createDate 2019年4月24日下午2:31:55
	 */
	List<MaterialTypes> getTypes();

}
