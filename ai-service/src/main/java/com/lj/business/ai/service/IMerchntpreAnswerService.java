package com.lj.business.ai.service;

import com.lj.business.ai.dto.MerchantPreAnswerDto;

import java.util.List;

public interface IMerchntpreAnswerService {
    /**
     * 新增商户预设答案
     *
     * @param merchantPreAnswerDto
     */
    void addMerchantPreAnswer(MerchantPreAnswerDto merchantPreAnswerDto);

    /**
     * 匹配商户预设答案
     *
     * @param merchantPreAnswerDto
     * @return
     */
    List<MerchantPreAnswerDto> matchMerchantPreAnswer(MerchantPreAnswerDto merchantPreAnswerDto);


    /**
     * 答案命中
     * @param code
     */
    void targetAnswer(String code);

    /**
     * 修改预设答案
     * @param merchantPreAnswerDto
     */
    void updateMerchantPreAnswer(MerchantPreAnswerDto merchantPreAnswerDto);
    
    
    
    
    MerchantPreAnswerDto getMerchantPreAnswerByProblemCode(String problemCode);

}
