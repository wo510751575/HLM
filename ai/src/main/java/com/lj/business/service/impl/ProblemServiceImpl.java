package com.lj.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.business.ai.dto.AiCacheDto;
import com.lj.business.ai.dto.CustomProblemDto;
import com.lj.business.ai.dto.IncludedAnswerDto;
import com.lj.business.ai.dto.MatchMerchantProblemDto;
import com.lj.business.ai.dto.MerchantAutoReplyDto;
import com.lj.business.ai.dto.MerchantPreAnswerDto;
import com.lj.business.ai.dto.MerchantPreProblemDto;
import com.lj.business.ai.emuns.AnswerType;
import com.lj.business.ai.emuns.ProblemType;
import com.lj.business.ai.service.ICustomProblemService;
import com.lj.business.ai.service.IMerchantPreProblemService;
import com.lj.business.ai.service.IMerchntpreAnswerService;
import com.lj.business.ai.service.IProblemService;
import com.lj.business.config.AiConfig;
import com.lj.business.dao.MerchantAutoReplyDao;
import com.lj.business.dao.MerchantPreAnswerDao;
import com.lj.business.dao.MerchantPreProblemDao;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.service.handler.AiHandler;
import com.lj.business.service.handler.CustomProblemHandler;
import com.lj.business.util.WordsUtil;
import com.lj.distributecache.RedisCache;

@Service
public class ProblemServiceImpl implements IProblemService {

    private static Logger LOG = LoggerFactory.getLogger(ProblemServiceImpl.class);

    @Resource
	private IGuidMemberService guidMemberService;

    @Resource
    private MerchantAutoReplyDao merchantAutoReplyDao;
    
    @Resource
    IMerchantPreProblemService merchantPreProblemService;

    @Resource
    IMerchntpreAnswerService merchntpreAnswerService;

    @Resource
    ICustomProblemService customProblemService;
    
    @Resource
    MerchantPreAnswerDao merchantPreAnswerDao;
    
    @Resource
    WordsUtil wordsUtil;
    
    @Resource
    AiHandler aiHandler;
    
    @Resource
    AiConfig aiConfig;
    
    
    @Resource
    CustomProblemHandler customProblemHandler;
    
    @Resource
    MerchantPreProblemDao merchantPreProblemDao;
    @Resource
    RedisCache  redisCache;
  
    
    private final String REDISAUTOREPLYKEY= "REDIS-AUTOREPLY-KEY-";
    
    @Override
    public List<MerchantPreAnswerDto> matchProblemAndAnswer(MatchMerchantProblemDto matchMerchantProblemDto) {
    	LOG.info(" begin  matchProblemAndAnswer   next handler ---> customProblemHandler :{} ",matchMerchantProblemDto);
    /**	zlh 注销原AI逻辑
    	String sessionId  = aiHandler.getSesssionId(matchMerchantProblemDto.getMemberNo());
		AiCacheDto aiCacheDto = aiHandler.getSessionCache(sessionId,matchMerchantProblemDto.getMemberNo());
    	customProblemHandler.setMatchMerchantProblemDto(matchMerchantProblemDto);
    	customProblemHandler.setAiCacheDto(aiCacheDto);
	    MatchProblemReturn<MerchantPreProblemDto> matchProblemReturn = customProblemHandler.handler(SpringContext.getBean(ConfirmProblemHandler.class), sessionId);
	    MerchantPreProblemDto problemDto = null;
	    if(ResultCode.MATCH_SUCCESS == matchProblemReturn.getMatchCode()){
	    	problemDto = getProblem(matchProblemReturn.getResult());
	    	aiCacheDto.setProblemCode(problemDto.getCode());
	    }
        aiCacheDto.setCustomProblemCode(aiCacheDto.getCustomProblemCode());
        aiCacheDto.setMemberNo(aiCacheDto.getMemberNo());
	    aiHandler.cacheSession(aiCacheDto); 
	    LOG.debug(" return match proble and answer  :{}", matchProblemReturn);**/
    	
    	MerchantPreProblemDto merchantPreProblemDto =new MerchantPreProblemDto();
    	merchantPreProblemDto.setMerchantNo(matchMerchantProblemDto.getMerchantNo());
    	//优先查询全局
    	List<MerchantPreProblemDto> merchantPreProblemDtoList = merchantPreProblemDao.findIsAllMerchantPreProblemList(merchantPreProblemDto);
    	List<MerchantPreAnswerDto> asList =  new ArrayList<MerchantPreAnswerDto>();
    	
    	
    	
    	//查询非全局
    	List<MerchantPreProblemDto> list = merchantPreProblemDao.findMerchantPreProblemList(merchantPreProblemDto);
    	for(MerchantPreProblemDto mpd : list) {
    		if(mpd.getProblemWord() != null ) {
    			String strArray [] = mpd.getProblemWord().split(",");
    			LOG.info("问题：" + matchMerchantProblemDto.getProblemContent());
    			for(String s :strArray) {
    				LOG.info("关键词" + s);
    				//含有关键词
    				if(StringUtils.isNotEmpty(matchMerchantProblemDto.getProblemContent())
    						&& matchMerchantProblemDto.getProblemContent().contains(s)) {
    					LOG.info("已找到" + s);
    					//查询答案
    				  return merchantPreAnswerDao.selectMerchantPreAnswerByPropCode(mpd.getCode());
    				}
    			}
    		}
    	}
    	
    	//查询全局
    	for(MerchantPreProblemDto mpd : merchantPreProblemDtoList) {
 		   MerchantPreAnswerDto ans = new MerchantPreAnswerDto();
 		   LOG.info("已找到" + mpd.getAnswerContent());
 		   ans.setAnswerContent(mpd.getAnswerContent());
 		  //查询答案
 		   asList.add(ans);
		   return asList;//只需要返回一个答案
 	     }
        return null;
    }
   

    @Override
    public List<String> splitWords(String content) {
        LOG.info(" split words content :{}", content);
        List<String> wordList = wordsUtil.splitWords(content);
        LOG.info(" split words list :{}", wordList);
        return wordList;
    }
    
    public MerchantPreProblemDto getProblem(List<MerchantPreProblemDto> problemDtos){
    	for (MerchantPreProblemDto merchantPreProblemDto : problemDtos) {
    			return merchantPreProblemDto;
		}
    	return null;
   }

    
	@Override
	public void includedAnswer(IncludedAnswerDto includedAnswerDto) {
		LOG.info(" includedAnswer   ----->{}",includedAnswerDto);
		AssertUtils.notNull(includedAnswerDto);
		AssertUtils.notNullAndEmpty(includedAnswerDto.getMemberNo(),"会员号不能为空");
		AssertUtils.notNullAndEmpty(includedAnswerDto.getGuidMemberNo(),"导购号不能为空");
		AssertUtils.notNullAndEmpty(includedAnswerDto.getGuidAnswer(),"导购回答不能为空");
        String sessionId  = aiHandler.getSesssionId(includedAnswerDto.getMemberNo());
        AiCacheDto aiCacheDto = aiHandler.getSessionCache(sessionId,includedAnswerDto.getMemberNo());
        LOG.info(" custom ai cache ----- >{}",aiCacheDto);
        AssertUtils.notNullAndEmpty(aiCacheDto.getCustomProblemCode(),"客户问题编码已过期");
        CustomProblemDto customProblemDto = customProblemService.getCustomProblemByCode(aiCacheDto.getCustomProblemCode());
        AssertUtils.notNull(customProblemDto,"客户问题不存在");
		MerchantPreProblemDto merchantPreProblemDto = new MatchMerchantProblemDto();
		merchantPreProblemDto.setMerchantNo(customProblemDto.getMerchantNo());
		merchantPreProblemDto.setMemberName(includedAnswerDto.getMemberName());
		merchantPreProblemDto.setMemberNo(includedAnswerDto.getMemberNo());
		merchantPreProblemDto.setProblemContent(customProblemDto.getCustomProblem());
		merchantPreProblemDto.setProblemType(ProblemType.AUTO.getValue());
		merchantPreProblemDto.setProblemWord(customProblemDto.getCustomProblemWord());
		merchantPreProblemDto.setTargetCount(1);
		merchantPreProblemDto = merchantPreProblemService.addMerchantPreProblemService(merchantPreProblemDto);
		
		LOG.debug("includedAnswer  save merchantPreProblem success :{} ",merchantPreProblemDto);
		MerchantPreAnswerDto merchantPreAnswerDto = new MerchantPreAnswerDto();
		merchantPreAnswerDto.setAnswerContent(includedAnswerDto.getGuidAnswer());
		merchantPreAnswerDto.setProblemCode(merchantPreProblemDto.getCode());
		merchantPreAnswerDto.setMerchantNo(customProblemDto.getMerchantNo());
		merchantPreAnswerDto.setAnswerType(AnswerType.AUTO.getValue());
		merchantPreAnswerDto.setGuidMemberNo(includedAnswerDto.getGuidMemberNo());
		merchantPreAnswerDto.setGuidMemberName(includedAnswerDto.getGuidMemberName());
		merchntpreAnswerService.addMerchantPreAnswer(merchantPreAnswerDto);
		LOG.debug("includedAnswer  addMerchantPreAnswer success :{} ",merchantPreProblemDto);

		customProblemDto.setGuidMemberName(includedAnswerDto.getGuidMemberName());
		customProblemDto.setGuidMemberNo(includedAnswerDto.getGuidMemberNo());
		customProblemDto.setIsTargetAnswer("2"); //人工解决
		customProblemDto.setMerchantAnswer(includedAnswerDto.getGuidAnswer());
		customProblemDto.setMerchantProblemCode(merchantPreProblemDto.getCode());
		customProblemService.updateCustomProblem(customProblemDto);
		LOG.debug("includedAnswer  update customProblem :{}",customProblemDto);
	}
	
	/**
	 * 新增开启自动回复
	 */
	public void insertGmAutoStatus(String gmNo,String merchantNo, String memberGmName){
		//List<MerchantAutoReplyDto>  list = merchantAutoReplyDao.selectGmAutoStatus(gmNo);
		Map<String,String >  map = new HashMap<String, String>();
	    map.put("merchantNo", merchantNo);
	    map.put("memberGmNo", gmNo);
	    map.put("memberGmName", memberGmName);
	    map.put("code", GUID.generateCode());
		merchantAutoReplyDao.insertGmAutoStatus(map);
		//缓存保存开启自动回复的设置
		redisCache.set(REDISAUTOREPLYKEY+gmNo, gmNo);
		
	}
	
	/**
	 * 根据商户已开启列表
	 */
	public List<MerchantAutoReplyDto> selectAutoReplyList(String merchantNo, String gmNo){
		//List<MerchantAutoReplyDto>  list = merchantAutoReplyDao.selectGmAutoStatus(gmNo);
	    Map<String,String >  map = new HashMap<String, String>();
	    map.put("merchantNo", merchantNo);
	    map.put("memberGmNo", gmNo);
		return merchantAutoReplyDao.selectAutoReplyList(map);
		
	}

	/**
	 * 关闭自动回复
	 * @param gmNo
	 */
    public void deleteAutoReplyList(String gmNo) {
    	Map<String,String >  map = new HashMap<String, String>();
	    map.put("memberGmNo", gmNo);
    	merchantAutoReplyDao.deleteAutoReplyList(map);
    	redisCache.del(REDISAUTOREPLYKEY+gmNo);
    }
    
    /**
     * 获取自动回复内容
     */
    public String getAutoContent(String content, String gmNo , String memberNo) {
    	try {
    		Map<String,String >  map = new HashMap<String, String>();
	  	    map.put("memberGmNo", gmNo);
	  	    List<MerchantAutoReplyDto> list = merchantAutoReplyDao.selectAutoReplyList(map);
	  	    if(list != null && list.size() > 0) {
    	        MatchMerchantProblemDto matchMerchantProblemDto = new MatchMerchantProblemDto();
                String merchantNo = list.get(0).getMerchantNo();
				matchMerchantProblemDto.setMerchantNo(merchantNo);
				matchMerchantProblemDto.setProblemContent(content);
				matchMerchantProblemDto.setMemberNo(memberNo);
				LOG.info("开始查找匹配答案");
				List<MerchantPreAnswerDto> answerList=matchProblemAndAnswer(matchMerchantProblemDto);
				if(answerList != null && answerList.size() > 0){
					
	        		String html="";
		    	    for(MerchantPreAnswerDto answer : answerList) {
		    	    	html += "" + answer.getAnswerContent(); 
		    	    }
		    		
				    return html;
				}
				
	  	    }
	  	    return null;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("获取自动回复内容出错 e={}",e);
			return null;
		}
    	       

    }



}
