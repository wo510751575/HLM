package com.lj.business;

import java.util.List;

import javax.annotation.Resource;

import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.ai.dto.MerchantPreAnswerDto;
import com.lj.business.ai.dto.MerchantPreProblemDto;
import com.lj.business.ai.emuns.AnswerType;
import com.lj.business.ai.emuns.ProblemType;
import com.lj.business.ai.service.IMerchantPreProblemService;
import com.lj.business.ai.service.IMerchntpreAnswerService;
import com.lj.business.ai.service.IProblemService;
import com.lj.business.util.WordsUtil;

public class MerchantPreProblemServiceImplTest extends SpringTestCase {

	@Resource
	IMerchantPreProblemService merchantPreProblemService;

	@Resource
	IMerchntpreAnswerService merchntpreAnswerService;

	@Resource
	WordsUtil wordUtil;
	
	@Resource
	IProblemService problemService;

	@Test
    public void testAddMerchantPreProblem(){
        MerchantPreProblemDto merchantPreProblemDto  = new MerchantPreProblemDto();
        MerchantPreAnswerDto merchantPreAnswerDto = new MerchantPreAnswerDto();
        merchantPreProblemDto.setMerchantNo("111111111111111111111");
        merchantPreProblemDto.setProblemType(ProblemType.PRE.getValue());
        merchantPreProblemDto.setProblemContent("你们产品怎么样试试?");
        merchantPreProblemDto.setProblemWord(wordUtil.splitWords(merchantPreProblemDto.getProblemContent()).toString());
        merchantPreProblemDto.setAnswers(merchantPreAnswerDto);
        merchantPreAnswerDto.setMerchantNo("111111111111111111111");
        merchantPreAnswerDto.setAnswerContent("保证很好11");
        merchantPreAnswerDto.setAnswerType(AnswerType.PRE.getValue());
        merchantPreProblemDto = merchantPreProblemService.addMerchantPreProblemService(merchantPreProblemDto);

    }

	@Test
	public void testMatchMerchantPreProblem() {
		MerchantPreProblemDto merchantPreProblemDto = new MerchantPreProblemDto();
		merchantPreProblemDto.setMerchantNo("fcecbfa097944565a58134d170f474af");
		merchantPreProblemDto.setProblemContent("我有一个大大问题");
		List<MerchantPreProblemDto> merchantPreProblemDtos = merchantPreProblemService
				.matchPreProblem(merchantPreProblemDto, new Page<>());
		System.out.println(merchantPreProblemDtos);
	}

	@Test
	public void testWord() {
		Result result = IndexAnalysis.parse("好的，谢谢");
		System.out.println(result);
	}
	
	@Test
	public void testSpilt() throws Exception {
		List<String> keyWords = problemService.splitWords("我有一个大大问题");
		System.out.println(keyWords);
	}
}
