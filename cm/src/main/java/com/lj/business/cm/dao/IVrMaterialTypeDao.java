package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.domain.VrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypePage;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypePageReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeReturn;


public interface IVrMaterialTypeDao {
    int deleteByPrimaryKey(String code);

    int insert(VrMaterialType record);

    int insertSelective(VrMaterialType record);

    VrMaterialType selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(VrMaterialType record);

    int updateByPrimaryKey(VrMaterialType record);
    
    List<FindVrMaterialTypeReturn> findVrMaterialTypeReturnList(FindVrMaterialType findVrMaterialType);

	List<FindVrMaterialTypePageReturn> findVrMaterialTypePage(FindVrMaterialTypePage findVrMaterialTypePage);

	int findVrMaterialTypePageCount(FindVrMaterialTypePage findVrMaterialTypePage);

	List<Integer> findVrMaterialTypeShowIndexList(FindVrMaterialType findVrMaterialType);
}