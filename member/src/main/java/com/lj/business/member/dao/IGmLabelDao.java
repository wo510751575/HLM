package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.GmLabel;
import com.lj.business.member.dto.FindGmLabelPage;
import com.lj.business.member.dto.GmLabelDto;

public interface IGmLabelDao {
	int deleteByPrimaryKey(String code);

	int insertSelective(GmLabel record);

	GmLabel selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(GmLabel record);

	List<GmLabelDto> findGmLabels(FindGmLabelPage findGmLabelPage);

	List<GmLabelDto> findGmLabelPage(FindGmLabelPage findGmLabelPage);

	int findGmLabelPageCount(FindGmLabelPage findGmLabelPage);
}