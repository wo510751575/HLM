package com.lj.business.ai.service;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.ai.dto.MerchantWordsDto;

public interface IMerchantWordsService {
    /**
     * 新增商户关键词
     *
     * @param merchantWordsDto
     */
    void addMerchantWord(MerchantWordsDto merchantWordsDto);

    /**
     * 查询问题关键词
     *
     * @param code
     * @return
     */
    List<MerchantWordsDto> findMerchantWordByProblemCode(String code);

    /**
     * 匹配关键词信息
     *
     * @param word
     * @return
     */
    List<MerchantWordsDto> matchMerchantWordByWords(String  word,String merchantNo);

    /**
	 * 
	 *
	 * 方法说明：根据问题code删除关键词信息
	 *
	 * @param merchantWordsDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月18日
	 *
	 */
	void delMerchantWord(MerchantWordsDto merchantWordsDto) throws TsfaServiceException;
}
