package com.lj.business.service.handler;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.ai.common.ResultCode;
import com.lj.business.ai.dto.AiCacheDto;
import com.lj.business.ai.dto.CustomProblemDto;
import com.lj.business.ai.dto.MatchMerchantProblemDto;
import com.lj.business.ai.dto.MatchProblemReturn;
import com.lj.business.ai.dto.MerchantPreAnswerDto;
import com.lj.business.ai.dto.MerchantPreProblemDto;
import com.lj.business.ai.service.ICustomProblemService;
import com.lj.business.ai.service.IMerchantPreProblemService;
import com.lj.business.ai.service.IMerchntpreAnswerService;
import com.lj.business.constant.Constant;
import com.lj.business.util.SpringContext;
import com.lj.business.util.WordsUtil;

@Service
@Scope("prototype")
public class CustomProblemHandler extends AbsHandler {
	
	private static Logger LOG= LoggerFactory.getLogger(CustomProblemHandler.class);

	
	 @Resource
	 ICustomProblemService customProblemService;
	 
	 @Resource
	 IMerchantPreProblemService merchantPreProblemService;
	 
	 @Resource
	 IMerchntpreAnswerService merchntpreAnswerService;
	 
	 @Resource
	 WordsUtil wordsUtil;
	 
	 private MerchantPreProblemDto merchantPreProblemDto;
	 

	 /**
      * 保存客户问题
      */
	@Override
	public MatchProblemReturn<MerchantPreProblemDto> handler(AbsHandler nextHandler,String sessionId) {
	      CustomProblemDto customProblemDto = new CustomProblemDto();
		  MatchMerchantProblemDto matchMerchantProblemDto = getMatchMerchantProblemDto();
	      AiCacheDto aiCacheDto =getAiCacheDto();
		  MatchProblemReturn<MerchantPreProblemDto> matchProblemReturn = getMatchProblemReturn();
		  LOG.info(" begin match problem and answer  --- >content:{},merchantNo:{}", matchMerchantProblemDto.getProblemContent(), matchMerchantProblemDto.getMerchantNo());
	      AssertUtils.notNull(matchMerchantProblemDto);
	      AssertUtils.notNullAndEmpty(matchMerchantProblemDto.getMerchantNo(), "商户号不能为空");
	      AssertUtils.notNullAndEmpty(matchMerchantProblemDto.getProblemContent(), "客户问题不能为空");
	      AssertUtils.notNullAndEmpty(matchMerchantProblemDto.getMemberNo(), "客户NO不能为空");
	      nextHandler.setMatchMerchantProblemDto(matchMerchantProblemDto);
	      nextHandler.setAiCacheDto(aiCacheDto);
	      matchProblemReturn = nextHandler.handler(SpringContext.getBean(ConfirmProblemHandler.class), sessionId);
	      if(ResultCode.MATCH_SUCCESS == matchProblemReturn.getMatchCode()){
	    	  merchantPreProblemDto = getProblem(matchProblemReturn.getResult());
		      customProblemDto.setMerchantProblemCode(merchantPreProblemDto.getCode());
		      MerchantPreAnswerDto merchantPreAnswerDto =merchntpreAnswerService.getMerchantPreAnswerByProblemCode(merchantPreProblemDto.getCode());
		      if(merchantPreAnswerDto!=null){
		    	  customProblemDto.setMerchantAnswer(merchantPreAnswerDto.getAnswerContent());
		      }
	      }
	      customProblemDto.setSessionId(sessionId);
	      customProblemDto.setSessionsSeq(aiCacheDto.getCurrentCount());
	      customProblemDto.setCustomProblem(matchMerchantProblemDto.getProblemContent());
	      List<String> words = wordsUtil.splitWords(matchMerchantProblemDto.getProblemContent());
	      customProblemDto.setCustomProblemWord(words.toString());
	      customProblemDto.setMemberName(matchMerchantProblemDto.getMemberName());
	      customProblemDto.setMemberNo(matchMerchantProblemDto.getMemberNo());
	      customProblemDto.setMerchantNo(matchMerchantProblemDto.getMerchantNo());
	      customProblemDto.setIsTargetAnswer("0");
	      customProblemDto.setCode(GUID.generateCode());
	      customProblemDto.setStatus(Constant.NORMAL_STATUS);
	      customProblemDto.setCreateDate(new Date());
	      customProblemService.addCustomProblem(customProblemDto);
	      LOG.info(" match result :{}",matchProblemReturn);
	      matchProblemReturn.setSessionId(sessionId);
	      return matchProblemReturn;
	}
	
	   public MerchantPreProblemDto getProblem(List<MerchantPreProblemDto> problemDtos){
	    	StringBuffer sb =new StringBuffer();
	    	for (MerchantPreProblemDto merchantPreProblemDto : problemDtos) {
	    			return merchantPreProblemDto;
			}
	    	return null;
	   }

}
