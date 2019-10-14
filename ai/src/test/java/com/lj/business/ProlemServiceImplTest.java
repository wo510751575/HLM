package com.lj.business;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.ai.dto.MatchMerchantProblemDto;
import com.lj.business.ai.dto.MatchProblemReturn;
import com.lj.business.ai.dto.MerchantPreProblemDto;
import com.lj.business.ai.service.IProblemService;

public class ProlemServiceImplTest extends SpringTestCase {
	
	
	
	@Resource
	IProblemService problemService;

	@Test
	public void testMatchProblem(){
				MatchMerchantProblemDto matchMerchantProblemDto = new MatchMerchantProblemDto();
				matchMerchantProblemDto.setMerchantNo("111111111111111111111");
				matchMerchantProblemDto.setMemberNo("test_1231122222");
				matchMerchantProblemDto.setMemberName("alanluo");
				matchMerchantProblemDto.setProblemContent("产品大小");
		//		MatchProblemReturn<MerchantPreProblemDto> result = problemService.matchProblemAndAnswer(matchMerchantProblemDto);
		//		System.out.println(result);
	}
	
	@Test
	public void testSplitWords() throws Exception {
		List<String> answerWord = problemService.splitWords(",");
		System.out.println(answerWord);
		System.out.println(StringUtils.strip(answerWord.toString(),"[]"));
	}
}
