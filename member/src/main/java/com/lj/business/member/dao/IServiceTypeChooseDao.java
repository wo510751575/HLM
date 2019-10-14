package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.ServiceTypeChoose;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoosePage;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoosePageReturn;

public interface IServiceTypeChooseDao {
    int deleteByPrimaryKey(String code);

    int insert(ServiceTypeChoose record);

    int insertSelective(ServiceTypeChoose record);

    ServiceTypeChoose selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ServiceTypeChoose record);

    int updateByPrimaryKey(ServiceTypeChoose record);

	List<FindServiceTypeChoosePageReturn> findServiceTypeChoosePage(FindServiceTypeChoosePage findServiceTypeChoosePage);

	int findServiceTypeChoosePageCount(FindServiceTypeChoosePage findServiceTypeChoosePage);
}