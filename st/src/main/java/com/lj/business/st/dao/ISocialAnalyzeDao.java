package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.SocialAnalyze;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyze;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeReturn;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeTotal;

public interface ISocialAnalyzeDao {
    int deleteByPrimaryKey(String code);

    int insert(SocialAnalyze record);

    int insertSelective(SocialAnalyze record);

    SocialAnalyze selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SocialAnalyze record);

    int updateByPrimaryKey(SocialAnalyze record);
    
    List<FindSocialAnalyzeReturn> findSocialAnalyze(FindSocialAnalyzeTotal findSocialAnalyzeTotal);
    
    List<FindSocialAnalyzeReturn> findSocialAnalyzeApp(FindSocialAnalyzeTotal findSocialAnalyzeTotal);
    
    FindSocialAnalyzeReturn findSocialAnalyzeMax(FindSocialAnalyze findSocialAnalyze);
}