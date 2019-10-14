package com.lj.business.weixin.dao;

import java.util.List;
import java.util.Set;

import com.lj.business.weixin.domain.ImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsPage;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsPageReturn;

public interface IImSensitiveWordsDao {
    int deleteByPrimaryKey(String code);

    int insert(ImSensitiveWords record);

    int insertSelective(ImSensitiveWords record);

    ImSensitiveWords selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ImSensitiveWords record);

    int updateByPrimaryKey(ImSensitiveWords record);

	List<FindImSensitiveWordsPageReturn> findImSensitiveWordsPage(FindImSensitiveWordsPage findImSensitiveWordsPage);

	int findImSensitiveWordsPageCount(FindImSensitiveWordsPage findImSensitiveWordsPage);
	
	public Set<String> findAllSensitiveWords();
}