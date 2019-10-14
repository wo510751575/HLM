package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.domain.VrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategoryPage;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategoryPageReturn;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategoryReturn;

public interface IVrMaterialCommenCategoryDao {
    int deleteByPrimaryKey(String code);

    int insert(VrMaterialCommenCategory record);

    int insertSelective(VrMaterialCommenCategory record);

    VrMaterialCommenCategory selectByPrimaryKey(String materialCode);

    int updateByPrimaryKeySelective(VrMaterialCommenCategory record);

    int updateByPrimaryKey(VrMaterialCommenCategory record);

	List<FindVrMaterialCommenCategoryPageReturn> findVrMaterialCommenCategoryPage(FindVrMaterialCommenCategoryPage findVrMaterialCommenCategoryPage);

	int findVrMaterialCommenCategoryPageCount(FindVrMaterialCommenCategoryPage findVrMaterialCommenCategoryPage);
	
    VrMaterialCommenCategory findByPrimaryKey(String materialCode);
    
    List<FindVrMaterialCommenCategoryReturn> findVrMaterialCommenCategory(FindVrMaterialCommenCategory findVrMaterialCommenCategory);
}