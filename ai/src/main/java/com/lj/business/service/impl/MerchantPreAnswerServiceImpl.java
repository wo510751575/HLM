package com.lj.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.ai.dto.MerchantPreAnswerDto;
import com.lj.business.ai.dto.MerchantWordsDto;
import com.lj.business.ai.emuns.WordType;
import com.lj.business.ai.service.IMerchantWordsService;
import com.lj.business.ai.service.IMerchntpreAnswerService;
import com.lj.business.constant.Constant;
import com.lj.business.dao.MerchantPreAnswerDao;
import com.lj.business.util.WordsUtil;

@Service
public class MerchantPreAnswerServiceImpl implements IMerchntpreAnswerService {

    private  static Logger LOG = LoggerFactory.getLogger(MerchantPreAnswerServiceImpl.class);

    @Resource
    MerchantPreAnswerDao merchantPreAnswerDao;

    @Resource
    WordsUtil wordsUtil;

    @Resource
    IMerchantWordsService merchantWordsService;

    @Override
    public void addMerchantPreAnswer(MerchantPreAnswerDto merchantPreAnswerDto) {
        List<String> words = wordsUtil.splitWords(merchantPreAnswerDto.getAnswerContent());
        merchantPreAnswerDto.setCode(GUID.generateCode());
        StringBuffer sb = new StringBuffer();
        for (String word:words) {
            sb.append(word).append(",");
            MerchantWordsDto merchantWordsDto = new MerchantWordsDto();
            merchantWordsDto.setCode(GUID.generateCode());
            merchantWordsDto.setAnswerCode(merchantPreAnswerDto.getCode());
            merchantWordsDto.setMerchantNo(merchantPreAnswerDto.getMerchantNo());
            merchantWordsDto.setWordType(WordType.ANSWER_WORD.getValue());
            merchantWordsDto.setCount(1);
            merchantWordsDto.setCreateDate(new Date());
            merchantWordsDto.setStatus(Constant.NORMAL_STATUS);
            merchantWordsDto.setWord(word);
            merchantWordsService.addMerchantWord(merchantWordsDto);
        }
        merchantPreAnswerDto.setAnswerTargetCount(1);
        merchantPreAnswerDto.setAnswerWord(sb.toString());
        merchantPreAnswerDto.setCreateDate(new Date());
        merchantPreAnswerDto.setStatus(Constant.NORMAL_STATUS);
        merchantPreAnswerDao.addMerchantPreAnswer(merchantPreAnswerDto);
    }
    @Override
    public List<MerchantPreAnswerDto> matchMerchantPreAnswer(MerchantPreAnswerDto merchantPreAnswerDto) {
        LOG.info(" begin match answer  --- > {}",merchantPreAnswerDto);
        List<MerchantPreAnswerDto> answerList = new ArrayList<>();
        AssertUtils.notNull(merchantPreAnswerDto);
        AssertUtils.notNullAndEmpty(merchantPreAnswerDto.getAnswerContent(),"问题内容不能为空");
        List<String> words = wordsUtil.splitWords(merchantPreAnswerDto.getAnswerContent());
        LOG.info(" match words  ---- >{}",words);
        for (String word:words) {
            List<MerchantWordsDto> merchantWordsDtoList = merchantWordsService.matchMerchantWordByWords(word,merchantPreAnswerDto.getMerchantNo());
            LOG.debug(" match merchants ---- > :{}",words);
            for (MerchantWordsDto merchantWord:merchantWordsDtoList) {
                answerList.add(merchantPreAnswerDao.getMerchantPreAnswer(merchantWord.getAnswerCode()));
            }
        }
        return answerList;    }
    @Override
    public void targetAnswer(String code) {
            merchantPreAnswerDao.targetAnswer(code);
    }
    @Override
    public void updateMerchantPreAnswer(MerchantPreAnswerDto merchantPreAnswerDto) {
           merchantPreAnswerDao.updateMerchantPreAnswer(merchantPreAnswerDto);
    }
	@Override
	public MerchantPreAnswerDto getMerchantPreAnswerByProblemCode(String problemCode) {
		 return merchantPreAnswerDao.getMerchantPreAnswerByProblemCode(problemCode);
	}


}
