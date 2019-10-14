package com.lj.business.cm.service;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.MaterialTypes;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月24日下午2:27:27
 */
public interface IMaterialTypesService {

	/**
	 *@Desc 
	 *@return
	 *@return List<MaterialTypes>
	 *@author 贾光朝
	 *@createDate 2019年4月24日下午2:28:41
	 */
	List<MaterialTypes> getTypes() throws TsfaServiceException;
}
