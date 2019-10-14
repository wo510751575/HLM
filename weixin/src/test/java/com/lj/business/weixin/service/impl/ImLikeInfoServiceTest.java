package com.lj.business.weixin.service.impl;

import java.net.MalformedURLException;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.service.IImLikeInfoService;

public class ImLikeInfoServiceTest extends SpringTestCase {

	@Resource
	IImLikeInfoService iImLikeInfoService;

	@Test
	public void findImLikeAppNotRead() throws MalformedURLException, ClassNotFoundException {
		ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
		imLikeInfoDto.setNoWxShop("sharkpearl252");
		iImLikeInfoService.findImLikeAppNotRead(imLikeInfoDto);
	}

	@Test
	public void findImLikeWebNotRead() throws MalformedURLException, ClassNotFoundException {
		ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
		imLikeInfoDto.setNoWxShop("sharkpearl252");
		iImLikeInfoService.findImLikeWebNotRead(imLikeInfoDto);
	}

	@Test
	public void findImLikeAppNotReadCount() throws MalformedURLException, ClassNotFoundException {
		ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
		imLikeInfoDto.setNoWxShop("sharkpearl252");
		iImLikeInfoService.findImLikeAppNotReadCount(imLikeInfoDto);
	}

	@Test
	public void findImLikeWebNotReadCount() throws MalformedURLException, ClassNotFoundException {
		ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
		imLikeInfoDto.setNoWxShop("sharkpearl252");
		iImLikeInfoService.findImLikeWebNotReadCount(imLikeInfoDto);
	}
}
