package com.lj.business.service.handler;

import java.util.Date;

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
import com.lj.business.ai.dto.MerchantPreProblemDto;
import com.lj.business.ai.service.ICustomProblemService;
import com.lj.business.ai.service.IMerchantPreProblemService;
import com.lj.business.util.SpringContext;
import com.lj.business.util.WordsUtil;

@Service
@Scope("prototype")
public class ConfirmProblemHandler extends AbsHandler {
	private static Logger LOG= LoggerFactory.getLogger(CustomProblemHandler.class);
 
	 @Resource
	 IMerchantPreProblemService merchantPreProblemService;
	 
	 
	 @Resource
	 ICustomProblemService customProblemService;
	 
	 @Resource
	 WordsUtil wordsUtil;
	
	@Override
	public MatchProblemReturn<MerchantPreProblemDto> handler(AbsHandler nextHandler, String sessionId) {
		  LOG.info("ConfirmProblemHandler.handler----->{}",sessionId);
		  MatchProblemReturn<MerchantPreProblemDto> matchProblemReturn = getMatchProblemReturn();
		  MatchMerchantProblemDto matchMerchantProblemDto = getMatchMerchantProblemDto();
		  AiCacheDto aiCacheDto =  getAiCacheDto();
	      boolean confirmFlag = wordsUtil.isConfirm(aiCacheDto, matchMerchantProblemDto.getProblemContent()); //  分析用户是否确认解决问题
	      if(confirmFlag){
	        	LOG.info("custom confirm problem  -- >{} , sessionID:{}",matchMerchantProblemDto.getProblemContent(),sessionId);
	        	matchProblemReturn.setMatchCode(ResultCode.CUSTOM_CONFIM);
	        	matchProblemReturn.setMatchMessage("用户确认解决");
	        	matchProblemReturn.setSessionId(sessionId);
	        	targetCustomProblem(aiCacheDto.getCustomProblemCode(),sessionId);
	            MerchantPreProblemDto merchantPreProblemDto =  merchantPreProblemService.getMerchantPreProblem(aiCacheDto.getProblemCode());
	        	merchantPreProblemDto.setTargetCount(merchantPreProblemDto.getTargetCount()+1);
	            merchantPreProblemService.updateMerchantPreProblem(merchantPreProblemDto);
	        	LOG.info(" this session id :{}  to be end , ",sessionId);
	         } else {
	        	 nextHandler.setMatchMerchantProblemDto(matchMerchantProblemDto);
	        	 nextHandler.setAiCacheDto(aiCacheDto);
	        	 matchProblemReturn = nextHandler.handler(SpringContext.getBean(MatchProblemHandler.class), sessionId);
	         }
		return matchProblemReturn;
	}
	 public void targetCustomProblem(String customProblemCode,String sessionId) {
	        LOG.info("target custom problem  :{}", customProblemCode);
	        AssertUtils.notNull(customProblemCode);
	        CustomProblemDto customProblemDto = customProblemService.getCustomProblemByCode(customProblemCode);
	    	customProblemDto.setIsTargetAnswer("1");  //自动回复解决
	        AiCacheDto aiCacheDto= getAiCacheDto();
	    	customProblemDto.setSessionsSeq(aiCacheDto.getCurrentCount());
	        customProblemDto.setCode(GUID.generateByUUID());
	        customProblemDto.setStatus("1");
	        customProblemDto.setIsTargetAnswer("1");  //自动解决客户问题
	        customProblemDto.setUpdateDate(new Date());
	        customProblemService.updateCustomProblem(customProblemDto);
	        LOG.info(" targetCustomProblem process success :{}",customProblemDto);      
	    }

}
