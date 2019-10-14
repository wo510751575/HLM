package com.lj.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.ai.dto.MerchantPreAnswerDto;

public interface MerchantPreAnswerDao {

        /**
         * 新增预设回答
         * @param merchantPreAnswerDto
         */
        void addMerchantPreAnswer(MerchantPreAnswerDto merchantPreAnswerDto);

        /**
         * 查询预设回答
         * @param merchantPreAnswerDto
         * @return
         */
        List<MerchantPreAnswerDto> findMerchantPreAnswerList(MerchantPreAnswerDto merchantPreAnswerDto);


        /**
         * 匹配答案
         * @param merchantNo
         * @param word
         * @return
         */
        List<MerchantPreAnswerDto> matchMerchantPreAnswer(@Param("merchantNo") String merchantNo, @Param("word") String word);
        
        /**
         * 
         * @param merchantNo
         * @param word
         * @return
         */
        List<MerchantPreAnswerDto> selectMerchantPreAnswerByPropCode(@Param("problemCode") String problemCode);
        /**
         * 回答命中
         * @param code
         */
        void targetAnswer(String code);

        /**
         * 修改预设答案
         * @param merchantPreAnswerDto
         */
        void updateMerchantPreAnswer(MerchantPreAnswerDto merchantPreAnswerDto);



        MerchantPreAnswerDto getMerchantPreAnswer(String code);


        MerchantPreAnswerDto getMerchantPreAnswerByProblemCode(String problemCode);


}
