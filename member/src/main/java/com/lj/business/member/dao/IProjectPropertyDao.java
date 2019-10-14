package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.ProjectProperty;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyPage;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyPageReturn;

public interface IProjectPropertyDao {
    int deleteByPrimaryKey(String code);

    int insert(ProjectProperty record);

    int insertSelective(ProjectProperty record);

    ProjectProperty selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ProjectProperty record);

    int updateByPrimaryKey(ProjectProperty record);

	List<FindProjectPropertyPageReturn> findProjectPropertyPage(FindProjectPropertyPage findProjectPropertyPage);

	int findProjectPropertyPageCount(FindProjectPropertyPage findProjectPropertyPage);
}