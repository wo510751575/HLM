package com.lj.business.service.handler;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.business.ai.common.ResultCode;
import com.lj.business.ai.dto.AiCacheDto;
import com.lj.business.ai.dto.MatchMerchantProblemDto;
import com.lj.business.ai.dto.MatchProblemReturn;
import com.lj.business.ai.dto.MerchantPreProblemDto;
import com.lj.business.ai.service.ICustomProblemService;
import com.lj.business.ai.service.IMerchantPreProblemService;
import com.lj.business.config.AiConfig;

@Service
@Scope("prototype")
public class MatchProblemHandler extends AbsHandler {
	
	 private static Logger LOG= LoggerFactory.getLogger(MatchProblemHandler.class);
 
	 @Resource
	 IMerchantPreProblemService merchantPreProblemService;
	 
	 
	 @Resource
	 ICustomProblemService customProblemService;
	 
	 @Resource
	 AiConfig aiConfig;
	
	@Override
	public MatchProblemReturn<MerchantPreProblemDto> handler(AbsHandler nextHandler, String sessionId) {
		  MatchProblemReturn<MerchantPreProblemDto> matchProblemReturn = getMatchProblemReturn();
		  MatchMerchantProblemDto matchMerchantProblemDto = getMatchMerchantProblemDto();
	      Page<MerchantPreProblemDto> page = new Page<MerchantPreProblemDto>();
	      AiCacheDto aiCacheDto = getAiCacheDto();
	      if(aiCacheDto.getCurrentCount() >= aiConfig.getMatchCount()){
        	  LOG.info(" sessionID :{} match count is max  to be sale match Count:{} ",sessionId,aiCacheDto.getCurrentCount());
        	  matchProblemReturn.setMatchCode(ResultCode.MAX_MATCH);
        	  matchProblemReturn.setMatchMessage("匹配次数超限");
        	  return matchProblemReturn;
        }
        /**
         * 匹配预设问题
         */
	    LOG.debug("aiConfig.getMatchParam() ----->{}  ,  currentCount --->{}",aiConfig.getMatchParam(),aiCacheDto);
	    
        Integer resultCount = aiConfig.getMatchParam().get(aiCacheDto.getCurrentCount());  //本次匹配响应数据条数
        if(resultCount == null ){
        	resultCount =aiConfig.getDefualtCount();
        }
//        page.setStart(aiCacheDto.getCurrentCount());
        page.setStart(0);
        page.setLimit(resultCount);
        LOG.info(" custom match session seq:{}", aiCacheDto.getCurrentCount());
        MatchMerchantProblemDto merchantPreProblemDto = new MatchMerchantProblemDto();
        merchantPreProblemDto.setMerchantNo(matchMerchantProblemDto.getMerchantNo());
        merchantPreProblemDto.setProblemContent(matchMerchantProblemDto.getProblemContent());
        List<MerchantPreProblemDto> problemList = merchantPreProblemService.matchPreProblem(merchantPreProblemDto,page);
        if(problemList==null ||problemList.size()==0){
        	LOG.warn(" not exits problem  with the :{}",matchMerchantProblemDto);
        	matchProblemReturn.setMatchCode(ResultCode.NOT_PROBLEM);
        	matchProblemReturn.setMatchMessage("没有匹配的问题,请重新查询");
        }
        else{
        	matchProblemReturn.setResult(problemList);
        	matchProblemReturn.setMatchCode(ResultCode.MATCH_SUCCESS);
        	matchProblemReturn.setMatchMessage("匹配成功");
        }
        LOG.debug(" match result :{} ",matchProblemReturn);
        return matchProblemReturn;
	}
	
	

}
