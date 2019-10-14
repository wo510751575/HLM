package com.lj.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.ai.dto.MerchantWordsDto;

public interface MerchantWordsDao {
    /**
     * 新增商户关键词
     * @param merchantWordsDto
     */
    void addMerchantWords(MerchantWordsDto merchantWordsDto);

    /**
     * 查询问题关键词
     * @param problemCode
     * @return
     */
    List<MerchantWordsDto> findMerchantWordByProblemCode(String problemCode);

    /**
     * 匹配关键词
     * @param word
     * @param wordType
     * @param merchantNo
     * @return
     */
     List<MerchantWordsDto> matchMerchantWordByWords(@Param("word") String word,@Param("merchantNo") String merchantNo);

	void deleteByPrimaryKey(String code);

	void deleteByProblemCode(String problemCode);

}
