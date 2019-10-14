package com.lj.business.weixin.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.service.IImCommentInfoService;

public class ImCommentInfoServiceImplTest extends SpringTestCase {

	@Resource
	IImCommentInfoService imCommentInfoService;

	@Test
	public void findImCommentInfoAppNotReadCount() {
		ImCommentInfoDto imCommentInfoDto = new ImCommentInfoDto();
		imCommentInfoDto.setNoWxShop("sharkpearl252");
		imCommentInfoService.findImCommentInfoAppNotReadCount(imCommentInfoDto);
	}

	@Test
	public void findImCommentInfoWebNotReadCount() {
		ImCommentInfoDto imCommentInfoDto = new ImCommentInfoDto();
		imCommentInfoDto.setNoWxShop("sharkpearl252");
		imCommentInfoService.findImCommentInfoWebNotReadCount(imCommentInfoDto);
	}

}
