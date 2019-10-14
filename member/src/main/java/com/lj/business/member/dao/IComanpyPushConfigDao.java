package com.lj.business.member.dao;

import java.util.List;
import com.lj.business.member.domain.ComanpyPushConfig;
import com.lj.business.member.dto.company.FindComanpyPushConfigPageReturn;
import com.lj.business.member.dto.company.FindComanpyPushConfigSelective;
import com.lj.business.member.dto.company.FindComanpyPushConfigSelectiveReturn;
import com.lj.business.member.dto.company.FindCompanyPushConfigPage;

public interface IComanpyPushConfigDao {

    int deleteByPrimaryKey(String code);

    int insert(ComanpyPushConfig record);

    int insertSelective(ComanpyPushConfig record);

    ComanpyPushConfig selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ComanpyPushConfig record);

    int updateByPrimaryKey(ComanpyPushConfig record);

    int findComanpyPushConfigPageCount(FindCompanyPushConfigPage findCompanyPushConfigPage);

    List<FindComanpyPushConfigPageReturn> findComanpyPushConfigPage(FindCompanyPushConfigPage findCompanyPushConfigPage);

    List<FindComanpyPushConfigSelectiveReturn> findComanpyPushConfigSelective(FindComanpyPushConfigSelective findComanpyPushConfigSelective);
}