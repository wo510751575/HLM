package com.lj.business;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.ai.dto.MerchantPreAnswerDto;
import com.lj.business.ai.emuns.AnswerType;
import com.lj.business.ai.service.IMerchntpreAnswerService;

public class MerchantPreAnswerServiceImplTest extends SpringTestCase {


    @Resource
    IMerchntpreAnswerService merchntpreAnswerService;


    @Test
    public void testAddAnswer(){
        MerchantPreAnswerDto merchantPreAnswerDto = new MerchantPreAnswerDto();
        merchantPreAnswerDto.setMerchantNo("111111111111111111111");
        merchantPreAnswerDto.setProblemCode("LJ_2e6e5bb01b14421689d26d23b21be188");
        merchantPreAnswerDto.setAnswerContent("保证很好");
        merchantPreAnswerDto.setAnswerType(AnswerType.PRE.getValue());
        merchntpreAnswerService.addMerchantPreAnswer(merchantPreAnswerDto);
    }


}
