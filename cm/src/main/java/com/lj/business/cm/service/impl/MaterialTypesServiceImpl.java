package com.lj.business.cm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lj.business.cm.dao.IMaterialTypesDao;
import com.lj.business.cm.dto.MaterialTypes;
import com.lj.business.cm.service.IMaterialTypesService;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月24日下午2:29:46
 */
@Service
public class MaterialTypesServiceImpl implements IMaterialTypesService {

	@Resource
	private IMaterialTypesDao materialTypesDao;
	
	@Override
	public List<MaterialTypes> getTypes() {
		List<MaterialTypes> list = materialTypesDao.getTypes();
		return list;
	}

}
