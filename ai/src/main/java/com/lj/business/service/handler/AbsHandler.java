package com.lj.business.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.business.ai.dto.AiCacheDto;
import com.lj.business.ai.dto.MatchMerchantProblemDto;
import com.lj.business.ai.dto.MatchProblemReturn;
import com.lj.business.ai.dto.MerchantPreProblemDto;


public abstract class AbsHandler {

	private static Logger LOG = LoggerFactory.getLogger(AbsHandler.class);

	private MatchMerchantProblemDto matchMerchantProblemDto = null;
	
	private  MatchProblemReturn<MerchantPreProblemDto> matchProblemReturn = new MatchProblemReturn<>();
	
	private AiCacheDto aiCacheDto = null;
	

	public AiCacheDto getAiCacheDto() {
		return aiCacheDto;
	}

	public void setAiCacheDto(AiCacheDto aiCacheDto) {
		this.aiCacheDto = aiCacheDto;
	}

	public abstract MatchProblemReturn<MerchantPreProblemDto> handler(AbsHandler nextHandler,String sessionId);

	public MatchMerchantProblemDto getMatchMerchantProblemDto() {
		return matchMerchantProblemDto;
	}

	public void setMatchMerchantProblemDto(MatchMerchantProblemDto matchMerchantProblemDto) {
		this.matchMerchantProblemDto = matchMerchantProblemDto;
	}

	public MatchProblemReturn<MerchantPreProblemDto> getMatchProblemReturn() {
		return matchProblemReturn;
	}

	public void setMatchProblemReturn(MatchProblemReturn<MerchantPreProblemDto> matchProblemReturn) {
		this.matchProblemReturn = matchProblemReturn;
	}
	

}
