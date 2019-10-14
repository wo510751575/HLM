package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.domain.GuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialPage;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialPageReturn;

public interface IGuidIntroduceMaterialDao {
    int deleteByPrimaryKey(String code);

    int insert(GuidIntroduceMaterial record);

    int insertSelective(GuidIntroduceMaterial record);

    GuidIntroduceMaterial selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(GuidIntroduceMaterial record);

    int updateByPrimaryKey(GuidIntroduceMaterial record);

	List<FindGuidIntroduceMaterialPageReturn> findGuidIntroduceMaterialPage(
			FindGuidIntroduceMaterialPage findGuidIntroduceMaterialPage);

	int findGuidIntroduceMaterialPageCount(
			FindGuidIntroduceMaterialPage findGuidIntroduceMaterialPage);

	int findCountMaterial(FindGuidIntroduceMaterial findGuidIntroduceMaterial);
}