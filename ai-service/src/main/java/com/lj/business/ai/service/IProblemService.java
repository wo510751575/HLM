package com.lj.business.ai.service;


import java.util.List;

import com.lj.business.ai.dto.IncludedAnswerDto;
import com.lj.business.ai.dto.MatchMerchantProblemDto;
import com.lj.business.ai.dto.MatchProblemReturn;
import com.lj.business.ai.dto.MerchantAutoReplyDto;
import com.lj.business.ai.dto.MerchantPreAnswerDto;
import com.lj.business.ai.dto.MerchantPreProblemDto;

public interface IProblemService {


    /**
     * 匹配相关问题以及答案
     * @param matchMerchantProblemDto
     * @param page
     * @return
     */
	List<MerchantPreAnswerDto> matchProblemAndAnswer(MatchMerchantProblemDto matchMerchantProblemDto);

    /**
                * 收录客户问题及导购答案
     * @param sessionId
     * @param guidAnswer
     */
    void includedAnswer(IncludedAnswerDto includedAnswerDto);
    

    List<String>  splitWords(String content);
    


    List<MerchantAutoReplyDto> selectAutoReplyList(String merchantNo,String gmNo);
    
    public void insertGmAutoStatus(String gmNo, String merchantNo, String memberGmName);
    
    public void deleteAutoReplyList(String gmNo);
    
    //获取自动回复内容
    public String getAutoContent(String content, String gmNo , String memberNo);
}
