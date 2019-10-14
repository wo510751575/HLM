package com.lj.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.ai.constant.ErrorCode;
import com.lj.business.ai.dto.MerchantWordsDto;
import com.lj.business.ai.service.IMerchantWordsService;
import com.lj.business.dao.MerchantWordsDao;

@Service
public class MerchantWordServiceImpl implements IMerchantWordsService {

    private static Logger LOG = LoggerFactory.getLogger(MerchantWordServiceImpl.class);

    @Resource
    MerchantWordsDao merchantWordsDao;

    @Override
    public void addMerchantWord(MerchantWordsDto merchantWordsDto) {
         merchantWordsDao.addMerchantWords(merchantWordsDto);
    }

    @Override
    public List<MerchantWordsDto> findMerchantWordByProblemCode(String code) {
        return merchantWordsDao.findMerchantWordByProblemCode(code);
    }

    @Override
    public List<MerchantWordsDto> matchMerchantWordByWords(String word,String merchantNo) {
        return merchantWordsDao.matchMerchantWordByWords(word,merchantNo);
    }
    
  
    @Override
	public void delMerchantWord(MerchantWordsDto merchantWordsDto) throws TsfaServiceException {

		AssertUtils.notNull(merchantWordsDto);
		AssertUtils.notNullAndEmpty(merchantWordsDto.getProblemCode(),"问题CODE不能为空！");
		try {
			merchantWordsDao.deleteByProblemCode(merchantWordsDto.getProblemCode());
		}catch (TsfaServiceException e) {
			throw e;
		}  catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.MERCHANT_PRE_WORD_DEL_ERROR,"删除关键词信息错误！",e);

		}
	}
    
}
