package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.domain.VrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPage;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPageReturn;

public interface IVrMaterialCommenDao {
    int deleteByPrimaryKey(String code);

    int insert(VrMaterialCommen record);

    int insertSelective(VrMaterialCommen record);

    VrMaterialCommen selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(VrMaterialCommen record);

    int updateByPrimaryKey(VrMaterialCommen record);

	List<FindVrMaterialCommenPageReturn> findVrMaterialCommenPage(FindVrMaterialCommenPage findVrMaterialCommenPage);

	int findVrMaterialCommenPageCount(FindVrMaterialCommenPage findVrMaterialCommenPage);

	List<FindVrMaterialCommenPageReturn> findVrMaterialCommenOmsPage(FindVrMaterialCommenPage findVrMaterialCommenPage);

	int findVrMaterialCommenPageOmsCount(FindVrMaterialCommenPage findVrMaterialCommenPage);
}