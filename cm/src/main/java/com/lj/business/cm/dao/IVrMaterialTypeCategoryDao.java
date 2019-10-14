package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.domain.VrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryPage;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryPageReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryReturn;

public interface IVrMaterialTypeCategoryDao {
    int deleteByPrimaryKey(String code);

    int insert(VrMaterialTypeCategory record);

    int insertSelective(VrMaterialTypeCategory record);

    VrMaterialTypeCategory selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(VrMaterialTypeCategory record);

    int updateByPrimaryKey(VrMaterialTypeCategory record);

	List<FindVrMaterialTypeCategoryPageReturn> findVrMaterialTypeCategoryPage(FindVrMaterialTypeCategoryPage findVrMaterialTypeCategoryPage);

	int findVrMaterialTypeCategoryPageCount(FindVrMaterialTypeCategoryPage findVrMaterialTypeCategoryPage);
	
	List<FindVrMaterialTypeCategoryReturn> findVrMaterialTypeCategoryReturnList(FindVrMaterialTypeCategory findVrMaterialTypeCategory);
}