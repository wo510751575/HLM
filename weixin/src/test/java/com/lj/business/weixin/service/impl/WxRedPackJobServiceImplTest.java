package com.lj.business.weixin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.weixin.dto.WxJobInfoDto;
import com.lj.business.weixin.dto.WxJobRedPackInfoDto;
import com.lj.business.weixin.emus.RedPackTypeEnum;
import com.lj.business.weixin.service.IWXJobHandler;
import com.lj.business.weixin.service.IWxJobInfoService;

public class WxRedPackJobServiceImplTest  extends SpringTestCase{
	
	
	@Resource
	IWxJobInfoService wxJobInfoService;
	
	@Resource
	IWXJobHandler wxjobHandler;
	
	
	@Test
	public void testAddWxJobInfo(){
		WxJobInfoDto wxJobInfoDto = new WxJobInfoDto();
		wxJobInfoDto.setCreateDate(new Date());
		wxJobInfoDto.setExecuteTime(new Date());
		wxJobInfoDto.setExecuteType("1");
		wxJobInfoDto.setJobLevel("1");
		wxJobInfoDto.setJobName("jobName");
		wxJobInfoDto.setJobDelayTime(1);
		wxJobInfoDto.setJobService("redPackJobHandler");
		wxJobInfoDto.setJobType(RedPackTypeEnum.NORMAL.getType());
		WxJobRedPackInfoDto wxJobRedPackInfoDto  = new WxJobRedPackInfoDto();
		wxJobRedPackInfoDto.setCreateDate(new Date());
		wxJobRedPackInfoDto.setRedpackContent("恭喜发财,红包拿来");
		wxJobRedPackInfoDto.setRedpackAmount(1L);
		wxJobRedPackInfoDto.setRedpackCount(1);
		wxJobRedPackInfoDto.setWxNoShop("Monster9402");
		List<String> details = new ArrayList<>();
		details.add("KENNYvip6688");
		wxJobRedPackInfoDto.setRedPackList(details);
		List<WxJobRedPackInfoDto> redpackList = new ArrayList<>();
		redpackList.add(wxJobRedPackInfoDto);
		wxJobInfoDto.setWxJobRedPackInfoDtoList(redpackList);
		wxJobInfoService.addWxJobInfo(wxJobInfoDto);
	
		
	}
	
	@Test
	public void testSend(){
		wxjobHandler.toHandler("LJ_88784a9726f1451d84e2437d5fed5eb3");
	}

}
