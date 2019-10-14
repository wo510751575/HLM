package com.lj.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.ai.constant.ErrorCode;
import com.lj.business.ai.dto.MerchantPreAnswerDto;
import com.lj.business.ai.dto.MerchantPreProblemDto;
import com.lj.business.ai.dto.MerchantWordsDto;
import com.lj.business.ai.emuns.AnswerType;
import com.lj.business.ai.emuns.ProblemType;
import com.lj.business.ai.emuns.WordType;
import com.lj.business.ai.service.IMerchantPreProblemService;
import com.lj.business.ai.service.IMerchantWordsService;
import com.lj.business.ai.service.IProblemService;
import com.lj.business.constant.Constant;
import com.lj.business.dao.MerchantPreAnswerDao;
import com.lj.business.dao.MerchantPreProblemDao;
import com.lj.business.util.WordsUtil;


@Service
public class MerchantPreProblemServiceImpl implements IMerchantPreProblemService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MerchantPreProblemServiceImpl.class);
	
    @Resource
    IMerchantWordsService merchantWordsService;
    
    @Resource
	IProblemService problemService;

    @Resource
    MerchantPreProblemDao merchantPreProblemDao;

    @Resource
    MerchantPreAnswerDao merchantPreAnswerDao;

    @Resource
    WordsUtil wordsUtil;


    private static Logger LOG = LoggerFactory.getLogger(MerchantPreProblemServiceImpl.class);


    @Override
    public MerchantPreProblemDto addMerchantPreProblemService(MerchantPreProblemDto merchantPreProblemDto) {
    	AssertUtils.notNull(merchantPreProblemDto);
    	AssertUtils.notNull(merchantPreProblemDto.getAnswers());
		AssertUtils.notNullAndEmpty(merchantPreProblemDto.getMerchantNo(),"商户编号不能为空");
		AssertUtils.notNullAndEmpty(merchantPreProblemDto.getProblemWord(),"关键词不能为空");
		AssertUtils.notNullAndEmpty(merchantPreProblemDto.getAnswers().getAnswerContent(),"回答内容不能为空");
		
		try {
			//新增问题表
	    	merchantPreProblemDto.setCode(GUID.generateByUUID());
	        merchantPreProblemDto.setTargetCount(1);
	        merchantPreProblemDto.setProblemType(ProblemType.PRE.getValue());
	        merchantPreProblemDto.setStatus(Constant.NORMAL_STATUS);
	        merchantPreProblemDto.setCreateDate(new Date());
	        merchantPreProblemDto.setAnswerContent(merchantPreProblemDto.getAnswers().getAnswerContent());
	        merchantPreProblemDao.addMerchantPreProblem(merchantPreProblemDto);
	        
	        //新增回答表
	        MerchantPreAnswerDto merchantPreAnswerDto = new MerchantPreAnswerDto();
	        merchantPreAnswerDto.setCode(GUID.generateByUUID());
	        merchantPreAnswerDto.setMerchantNo(merchantPreProblemDto.getMerchantNo());
	        merchantPreAnswerDto.setProblemCode(merchantPreProblemDto.getCode());
	        merchantPreAnswerDto.setAnswerContent(merchantPreProblemDto.getAnswers().getAnswerContent());
	        List<String> answerWord = problemService.splitWords(merchantPreAnswerDto.getAnswerContent());
	        merchantPreAnswerDto.setAnswerWord(StringUtils.strip(answerWord.toString(),"[]"));
	        merchantPreAnswerDto.setAnswerTargetCount(1);
	        merchantPreAnswerDto.setAnswerScanCount(1);
	        merchantPreAnswerDto.setStatus(Constant.NORMAL_STATUS);
	        merchantPreAnswerDto.setCreateDate(new Date());
	        merchantPreAnswerDto.setAnswerType(AnswerType.PRE.getValue());
	        merchantPreAnswerDao.addMerchantPreAnswer(merchantPreAnswerDto);
	        
	        //新增关键词表
	        String[] words = merchantPreProblemDto.getProblemWord().split(",");
	        for (String word: words) {
	                MerchantWordsDto merchantWordsDto = new MerchantWordsDto();
	                merchantWordsDto.setCode(GUID.generateByUUID());
	                merchantWordsDto.setMerchantNo(merchantPreProblemDto.getMerchantNo());
	                merchantWordsDto.setProblemCode(merchantPreProblemDto.getCode());
	                merchantWordsDto.setAnswerCode(merchantPreAnswerDto.getCode());
	                merchantWordsDto.setWordType(WordType.PROBLEM_WORD.getValue());
	                merchantWordsDto.setWord(word);
	                merchantWordsDto.setCount(1);
	                merchantWordsDto.setStatus(Constant.NORMAL_STATUS);
	                merchantWordsDto.setCreateDate(new Date());
	                merchantWordsService.addMerchantWord(merchantWordsDto);
	        }
	        
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("新增商户预设问题信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_PRE_PROBLEM_ADD_ERROR,"新增商户预设问题信息错误！",e);
		}
        return merchantPreProblemDto;
    }
    
    @Override
    public List<MerchantPreProblemDto> matchPreProblem(MerchantPreProblemDto matchMerchantProblemDto,Page page) {
        LOG.info(" begin match problem  --- > {}",matchMerchantProblemDto);
        List<MerchantPreProblemDto> problemList = new ArrayList<>();
        AssertUtils.notNull(matchMerchantProblemDto);
        AssertUtils.notNullAndEmpty(matchMerchantProblemDto.getProblemContent(),"问题内容不能为空");
        List<String> words = wordsUtil.splitWords(matchMerchantProblemDto.getProblemContent());
        List<String> problemCodes = new ArrayList<String>();  //匹配成功的问题列表
        LOG.info(" match words  ---- >{}",words);
        String workMatchCode=null;
        for (String word:words) {   //逐个匹配关键词        	 
        	List<MerchantWordsDto> merchantWordsDtoList = merchantWordsService.matchMerchantWordByWords(word,matchMerchantProblemDto.getMerchantNo());
            LOG.debug(" match merchants  problem---- > :{}",words);
            for (MerchantWordsDto merchantWord:merchantWordsDtoList) {
            		//匹配预设问题 
            		if(WordType.PROBLEM_WORD.getValue().equals(merchantWord.getWordType())){
            			workMatchCode = merchantWord.getProblemCode();
            		}
            		//匹配预设答案
            		else if(WordType.ANSWER_WORD.getValue().equals(merchantWord.getWordType())){
            			MerchantPreAnswerDto preAnswer = merchantPreAnswerDao.getMerchantPreAnswer(merchantWord.getAnswerCode());
            			if(preAnswer!=null){
            				workMatchCode   = preAnswer.getProblemCode();
            			}
            		}	
            		if(!problemCodes.contains(workMatchCode)){
            			problemCodes.add(merchantWord.getProblemCode());
            		}else {
            			LOG.warn("  problem :{} is  null or match in list , be match next ",workMatchCode);
            		}
            }
            
        }
        
        if(problemCodes!=null&&problemCodes.size()!=0){
        	problemList =  merchantPreProblemDao.matchMerchantPreProblem(problemCodes, page, matchMerchantProblemDto.getMerchantNo());
        	for (MerchantPreProblemDto problemDto : problemList) {
				MerchantPreAnswerDto merchantPreAnswerDto =merchantPreAnswerDao.getMerchantPreAnswerByProblemCode(problemDto.getCode());
				problemDto.setAnswers(merchantPreAnswerDto);
			}
        }
        return problemList;
    }
    
    @Override
    public void updateMerchantPreProblem(MerchantPreProblemDto merchantPreProblemDto) {
		
		AssertUtils.notNull(merchantPreProblemDto);
    	AssertUtils.notNull(merchantPreProblemDto.getAnswers());
    	AssertUtils.notNullAndEmpty(merchantPreProblemDto.getCode(),"CODE不能为空");
		AssertUtils.notNullAndEmpty(merchantPreProblemDto.getMerchantNo(),"商户编号不能为空");
		AssertUtils.notNullAndEmpty(merchantPreProblemDto.getProblemWord(),"关键词不能为空");
		AssertUtils.notNullAndEmpty(merchantPreProblemDto.getAnswers().getAnswerContent(),"回答内容不能为空");
		
		logger.info("进入编辑");
		//更新问题表
		merchantPreProblemDao.updateMerchantPreProblem(merchantPreProblemDto);
		logger.info("进入编辑" + merchantPreProblemDto.getAnswerContent());
		//更新回答表
        MerchantPreAnswerDto merchantPreAnswerDto = new MerchantPreAnswerDto();
       // merchantPreAnswerDto.setCode(merchantPreProblemDto.getAnswers().getCode());
        merchantPreAnswerDto.setProblemCode(merchantPreProblemDto.getCode());
        merchantPreAnswerDto.setAnswerContent(merchantPreProblemDto.getAnswers().getAnswerContent());
        List<String> answerWord = problemService.splitWords(merchantPreAnswerDto.getAnswerContent());
        merchantPreAnswerDto.setAnswerWord(StringUtils.strip(answerWord.toString(),"[]"));
        merchantPreAnswerDto.setUpdateDate(new Date());
        merchantPreAnswerDao.updateMerchantPreAnswer(merchantPreAnswerDto);
		
        //清空对应关键词并重新新增关键词表
        MerchantWordsDto mw = new MerchantWordsDto();
        mw.setProblemCode(merchantPreProblemDto.getCode());
        merchantWordsService.delMerchantWord(mw);
        
        String[] words = merchantPreProblemDto.getProblemWord().split(",");
        for (String word: words) {
            MerchantWordsDto merchantWordsDto = new MerchantWordsDto();
            merchantWordsDto.setCode(GUID.generateByUUID());
            merchantWordsDto.setMerchantNo(merchantPreProblemDto.getMerchantNo());
            merchantWordsDto.setProblemCode(merchantPreProblemDto.getCode());
            merchantWordsDto.setAnswerCode(merchantPreAnswerDto.getCode());
            merchantWordsDto.setWordType(WordType.PROBLEM_WORD.getValue());
            merchantWordsDto.setWord(word);
            merchantWordsDto.setCount(1);
            merchantWordsDto.setStatus(Constant.NORMAL_STATUS);
            merchantWordsDto.setCreateDate(new Date());
            merchantWordsService.addMerchantWord(merchantWordsDto);
        }
        
    }
    
    @Override
    public Page<MerchantPreProblemDto> findMerchantPreProblemPage(MerchantPreProblemDto findMerchantPreProblemPage) throws TsfaServiceException{
        
        logger.debug("findMerchantPreProblemPage(MerchantPreProblemDto findMerchantPreProblemPage={}) - start", findMerchantPreProblemPage); //$NON-NLS-1$
		AssertUtils.notNull(findMerchantPreProblemPage);
		AssertUtils.notNullAndEmpty(findMerchantPreProblemPage.getMerchantNo(),"商户编号不能为空");
		List<MerchantPreProblemDto> returnList = null;
		int count = 0;
		try {
			returnList = merchantPreProblemDao.findMerchantPreProblemPage(findMerchantPreProblemPage);
			for (MerchantPreProblemDto problemDto:returnList) {
                problemDto.setAnswers(merchantPreAnswerDao.getMerchantPreAnswerByProblemCode(problemDto.getCode()));
                problemDto.setWords(merchantWordsService.findMerchantWordByProblemCode(problemDto.getCode()));
			}
			count = merchantPreProblemDao.findMerchantPreProblemPageCount(findMerchantPreProblemPage);
		}  catch (Exception e) {
			logger.error("商家预设问题信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_PRE_PROBLEM_FIND_PAGE_ERROR,"商家预设问题信息不存在错误.！",e);
		}
		Page<MerchantPreProblemDto> returnPage = new Page<MerchantPreProblemDto>(returnList, count, findMerchantPreProblemPage);

		logger.debug("findMerchantPreProblemPage(MerchantPreProblemDto) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
        
    }
    
	@Override
	public MerchantPreProblemDto getMerchantPreProblem(String code) {
		MerchantPreProblemDto merchantPreProblemDto =  merchantPreProblemDao.getMerchantPreProblem(code);
		if(merchantPreProblemDto != null){
			merchantPreProblemDto.setAnswers(merchantPreAnswerDao.getMerchantPreAnswerByProblemCode(merchantPreProblemDto.getCode()));
		}
		return merchantPreProblemDto;

	}
	
	@Override
	public MerchantPreProblemDto findMerchantPreProblem(MerchantPreProblemDto findMerchantPreProblem) throws TsfaServiceException {
		logger.debug("findMerchantPreProblem(MerchantPreProblemDto findMerchantPreProblem={}) - start", findMerchantPreProblem); //$NON-NLS-1$

		AssertUtils.notNull(findMerchantPreProblem);
		AssertUtils.notNullAndEmpty(findMerchantPreProblem.getCode(),"CODE不能为空");
		try {
			MerchantPreProblemDto merchantPreProblem = merchantPreProblemDao.selectByPrimaryKey(findMerchantPreProblem.getCode());
			if(merchantPreProblem == null){
				throw new TsfaServiceException(ErrorCode.MERCHANT_PRE_PROBLEM_NOT_EXIST_ERROR,"商户预设问题信息不存在");
			}
			merchantPreProblem.setAnswers(merchantPreAnswerDao.getMerchantPreAnswerByProblemCode(findMerchantPreProblem.getCode()));
			merchantPreProblem.setWords(merchantWordsService.findMerchantWordByProblemCode(findMerchantPreProblem.getCode()));
			logger.debug("findMerchantPreProblem(MerchantPreProblemDto) - end - return value={}", merchantPreProblem); //$NON-NLS-1$
			return merchantPreProblem;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找商户预设问题信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_PRE_PROBLEM_FIND_ERROR,"查找商户预设问题信息错误！",e);
		}
	}

	
}
