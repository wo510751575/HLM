package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.encryption.MD5;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dao.IGuidMemberDao;
import com.lj.business.member.domain.GuidMember;
import com.lj.business.member.dto.AddGuidMember;
import com.lj.business.member.dto.AddGuidMemberDto;
import com.lj.business.member.dto.AddPersonMember;
import com.lj.business.member.dto.AddPersonMemberBase;
import com.lj.business.member.dto.AddShop;
import com.lj.business.member.dto.DelGuidMember;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindShopGmDto;
import com.lj.business.member.dto.FindShopGmReturn;
import com.lj.business.member.dto.GuidInfoShop;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.dto.UpdateGuidMember;
import com.lj.business.member.dto.shopterminal.AddShopTerminal;
import com.lj.business.member.emus.MemberStatus;
import com.lj.business.member.emus.NameAuthFlag;
import com.lj.business.member.util.TestHelp;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class GuidMemberServiceImplTest extends SpringTestCase{

	@Resource
	GuidMemberServiceImpl guidMemberService;
	@Resource
	IGuidMemberDao guidMemberDao;

	@Resource
	PersonMemberBaseServiceImpl personMemberBaseService;

	@Resource
	PersonMemberServiceImpl personMemberService;

	@Resource
	ShopTerminalServiceImpl shopTerminalService;

//	@Resource
//	ShopServiceImpl shopService;


	/**
	 * 
	 *
	 * 方法说明：添加测试数据信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void addTestInfo() throws TsfaServiceException{

		for (int i = 0; i < 2000; i++) {
			String memberNoGm = GUID.generateByUUID();
			String shopNo = GUID.generateByUUID();
			String merchantNo = "5ea0c014cbbd49a6a56ab73c6d1772ae";
			String memberNo = GUID.generateByUUID();
			String version = "0010";
			String shopNoMerchant = "100"  + version;
			String mobile_gm = "1860000" + version;
			String noWx = "test_no_wx_1"+ version +"_";
			String noWxGm = "test_no_wx_gm_1"+ version +"_";
			String noWxSt = "test_no_wx_st_1"+ version +"_";
			String imei = "86603603950"+ version +"";
			
			AddShop addShop = new AddShop();
			//add数据录入
//			addShop.setShopNo(shopNo);
			addShop.setAreaCode("AreaCode");
			addShop.setCityAreaCode("CityAreaCode");
			addShop.setAddrInfo("AddrInfo");
			addShop.setLogoAddr("LogoAddr");
			addShop.setLongitude("Longitude");
			addShop.setLatitude("Latitude");
			addShop.setBizScope("BizScope");
			addShop.setQcord("Qcord");
			addShop.setCreateId("CreateId");
			addShop.setRemark4("Remark4");
			addShop.setRemark3("Remark3");
			addShop.setRemark("Remark");
			addShop.setRemark2("Remark2");
			addShop.setShopType("时尚店");
//			addShop.setShopName("湖南分店");
			
			addShop.setMemberNoMerchant(merchantNo);
			addShop.setMemberNameMerchant("敏华集团");
			addShop.setStatus("NORMAL");
			addShop.setTelephone("0755-2532110");
			addShop.setMobile("18733562651");
			addShop.setEmail("18733562651@139.com");
			addShop.setAddress("深圳市龙华区宝安大道780号");
			addShop.setAreaCode("1");
			addShop.setProvinceCode("2");
			addShop.setCityCode("3");
			addShop.setAddrInfo("深圳市龙华区宝安大道780号");
			addShop.setCreateId("安家管理员");
			
			addShop.setShopNoMerchant(shopNoMerchant + i);

//			Assert.assertNotNull(shopService.addShop(addShop ));

			AddGuidMember addGuidMember = new AddGuidMember();
			//add数据录入
			addGuidMember.setMemberNo(memberNoGm);
			addGuidMember.setMemberName("测试导购");
//			addGuidMember.setShopNo(shopNo);
//			addGuidMember.setShopName("分店1");
			addGuidMember.setMerchantNo(merchantNo);
			addGuidMember.setMerchantName("安家");
			addGuidMember.setStatus("NORMAL");
			addGuidMember.setJobNum("J0001");
			addGuidMember.setMobile(mobile_gm +i);
			addGuidMember.setEmail("Email");
			addGuidMember.setAreaCode("d");
			addGuidMember.setAreaName("d");
			addGuidMember.setNickName("NickName");
			addGuidMember.setAddress("Address");
			addGuidMember.setAge(26);
			addGuidMember.setGender("Gender");
			addGuidMember.setPwd(MD5.encryptByMD5("111111"));
			addGuidMember.setEncryptionCode("3");
			addGuidMember.setHeadAddress("HeadAddress");
			addGuidMember.setCreateId("CreateId");
			addGuidMember.setNoWx(noWxGm);
			Assert.assertNotNull(guidMemberService.addGuidMember(addGuidMember ));
			

			AddPersonMemberBase addPersonMemberBase = new AddPersonMemberBase();
			//add数据录入
			addPersonMemberBase.setCode(GUID.generateCode());
			addPersonMemberBase.setMemberNo(memberNo);
			addPersonMemberBase.setMemberName("MemberName");
			addPersonMemberBase.setStatus(MemberStatus.NORMAL);
			addPersonMemberBase.setCertTypeCode("身份证");
			addPersonMemberBase.setCertNo("362228199105053419");
			//addPersonMemberBase.setMobile("18888668905");
			addPersonMemberBase.setEmail("18888668905@139.com");
			addPersonMemberBase.setJob("IT");
			addPersonMemberBase.setAddress("西丽");
			addPersonMemberBase.setAge(26);
			addPersonMemberBase.setSex("男");
			addPersonMemberBase.setNameAuthFlag(NameAuthFlag.N);
			addPersonMemberBase.setPwd(MD5.encryptByMD5("123456"));
			addPersonMemberBase.setEncryptionCode("3");
			addPersonMemberBase.setMemberSrc("微信扫码");
			addPersonMemberBase.setOpenIdGzhWx("");
			addPersonMemberBase.setOpenIdXcxWx("");
			addPersonMemberBase.setNickNameWx("阳光");
			addPersonMemberBase.setCityWx("广东");
			addPersonMemberBase.setCountryWx("中国");
			addPersonMemberBase.setProvinceWx("深圳");
			addPersonMemberBase.setHeadAddress("http://");
			addPersonMemberBase.setSubsribeTime(new Date());
			addPersonMemberBase.setCreateId("导购");
			addPersonMemberBase.setCreateDate(new Date());
			addPersonMemberBase.setUpdateId("店长");
			addPersonMemberBase.setUpdateDate(new Date());
			addPersonMemberBase.setNoWx(noWx+i);
			//addPersonMemberBase.setNoWxAlias(noWxAlias);

			Assert.assertNotNull(personMemberBaseService.addPersonMemberBase(addPersonMemberBase ));

			AddPersonMember addPersonMember = new AddPersonMember();
			//add数据录入
			addPersonMember.setMemberNo(memberNo);
			addPersonMember.setMemberName("MemberName");
			addPersonMember.setMemberNoGm(memberNoGm);
			addPersonMember.setMemberNameGm("MemberNameGm");
//			addPersonMember.setShopNo(shopNo);
//			addPersonMember.setShopName("ShopName");
			addPersonMember.setMerchantNo(merchantNo);
			addPersonMember.setMerchantName("MerchantName");
			addPersonMember.setSpruceUp("SpruceUp");
			addPersonMember.setHouses("Houses");
			addPersonMember.setKeepEye("1");
			addPersonMember.setUrgencyPm("1");
			addPersonMember.setBomCode("BomCode");
			addPersonMember.setBomName("BomName");
			addPersonMember.setCreateId("CreateId");
			addPersonMember.setRemark4("Remark4");
			addPersonMember.setRemark3("Remark3");
			addPersonMember.setRemark2("Remark2");
			addPersonMember.setRemark("Remark");
			personMemberService.addPersonMember(addPersonMember );


			AddShopTerminal addShopTerminal = new AddShopTerminal();
			//add数据录入
			addShopTerminal.setMerchantNo(merchantNo);
			addShopTerminal.setMerchantName("海雅集团");
//			addShopTerminal.setShopNo(shopNo);
//			addShopTerminal.setShopName("天津市市区直营店");
			addShopTerminal.setNoWx(noWxSt + i);
			addShopTerminal.setWxNickname("lylj0725");
			addShopTerminal.setImei(imei + i);
			addShopTerminal.setStatus(1);
			addShopTerminal.setCreateId("CreateId");
			addShopTerminal.setCreateDate(new Date());
			addShopTerminal.setRemark("Remark");
			addShopTerminal.setRemark2("Remark2");
			addShopTerminal.setRemark3("Remark3");
			addShopTerminal.setRemark4("Remark4");

			Assert.assertNotNull(shopTerminalService.addShopTerminal(addShopTerminal ));

		}

	}


	
	/**
	 * 
	 *
	 * 方法说明：添加导购信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void addGuidMember() throws TsfaServiceException{
		AddGuidMember addGuidMember = new AddGuidMember();
		//add数据录入
		addGuidMember.setMemberNo("G0001");
		addGuidMember.setMemberName("段志鹏");
//		addGuidMember.setShopNo("S0001");
//		addGuidMember.setShopName("分店1");
		addGuidMember.setMerchantNo("086c40e17ed44e89a0482366841c63f2");
		addGuidMember.setMerchantName("安家");
		addGuidMember.setStatus("NORMAL");
		addGuidMember.setJobNum("J0001");
		addGuidMember.setMobile("18670275128");
		addGuidMember.setEmail("Email");
		addGuidMember.setAreaCode("d");
		addGuidMember.setAreaName("d");
		addGuidMember.setNickName("NickName");
		addGuidMember.setAddress("Address");
		addGuidMember.setAge(26);
		addGuidMember.setGender("Gender");
		addGuidMember.setPwd(MD5.encryptByMD5("668905"));
		addGuidMember.setEncryptionCode("3");
		addGuidMember.setHeadAddress("HeadAddress");
		addGuidMember.setCreateId("CreateId");
		
		Assert.assertNotNull(guidMemberService.addGuidMember(addGuidMember ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改导购信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void updateGuidMember() throws TsfaServiceException{
		UpdateGuidMember updateGuidMember = new UpdateGuidMember();
		//update数据录入
		updateGuidMember.setCode(GUID.getPreUUID());
		updateGuidMember.setMemberNo("MemberNo");
		updateGuidMember.setMemberName("MemberName");
//		updateGuidMember.setShopNo("ShopNo");
//		updateGuidMember.setShopName("ShopName");
		updateGuidMember.setMerchantNo("MerchantNo");
		updateGuidMember.setMerchantName("MerchantName");
		updateGuidMember.setStatus("Status");
		updateGuidMember.setJobNum("JobNum");
		updateGuidMember.setMobile("Mobile");
		updateGuidMember.setEmail("Email");
		updateGuidMember.setNickName("NickName");
		updateGuidMember.setAddress("Address");
		updateGuidMember.setAge(1);
		updateGuidMember.setGender("Gender");
		updateGuidMember.setPwd("Pwd");
		updateGuidMember.setEncryptionCode("EncryptionCode");
		updateGuidMember.setHeadAddress("HeadAddress");
		updateGuidMember.setCreateId("CreateId");
		updateGuidMember.setCreateDate(new Date());
		updateGuidMember.setUpdateId("UpdateId");
		updateGuidMember.setUpdateDate(new Date());

		guidMemberService.updateGuidMember(updateGuidMember );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void findGuidMember() throws TsfaServiceException{
		GuidMember recordGuidMember = new GuidMember();
		recordGuidMember.setMobile("18665435143");
		recordGuidMember.setMerchantNo(TestHelp.merchantNo_test);
		GuidMember guidMember = guidMemberDao.findGuidMember(recordGuidMember);
		System.out.println(guidMember.toString());
	}
	
	@Test
	public void findGuidMemberss() throws TsfaServiceException{
		UpdateGuidMember updateGuidMember = new UpdateGuidMember();
		updateGuidMember.setCode("LJ_0e6ed6cebc00424198e7b292d3af2487");
		GuidMemberReturnDto guidMemberReturnDto=guidMemberService.findGuidMemberByCode(updateGuidMember);
		System.out.println(guidMemberReturnDto);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void findGuidMemberPage() throws TsfaServiceException{
		FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
//		findGuidMemberPage.setShopName("南山分店");
		Page<FindGuidMemberPageReturn> page = guidMemberService.findGuidMemberPage(findGuidMemberPage);
		System.out.println(page.getRows());
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void findGuidMemberExport() throws TsfaServiceException{
		FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
		findGuidMemberPage.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		List<FindGuidMemberPageReturn> list = guidMemberService.findGuidMemberExport(findGuidMemberPage);
		System.out.println(list.size());
		Assert.assertNotNull(list);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除导购信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void delGuidMember() throws TsfaServiceException{
		DelGuidMember delGuidMember = new DelGuidMember();
		delGuidMember.setCode(GUID.getPreUUID());
		Assert.assertNotNull(guidMemberService.delGuidMember(delGuidMember));
		
	}
	
	@Test
	public void findGuidMembers() throws TsfaServiceException{
		FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
		findGuidMemberPage.setMerchantNo("LJ_1ee46680f96c4b3fbb8680ca7199579a");
		findGuidMemberPage.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
//		findGuidMemberPage.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
//		findGuidMemberPage.setStatus(MemberStatus.NORMAL.toString());
//		findGuidMemberPage.setStartTime(new Date());
//		findGuidMemberPage.setEndTime(new Date());
		findGuidMemberPage.setNoWx("laoerjiu123");
		findGuidMemberPage.setImei("0");
		List<FindGuidMemberPageReturn> page = guidMemberService.findGuidMembers(findGuidMemberPage);
		System.out.println(page.toString());
	}
	
	@Test
	public void findGuidMemberByCond() throws TsfaServiceException{
		FindGuidMember findGuidMemberPage = new FindGuidMember();
		FindGuidMemberReturn page = guidMemberService.findGuidMember(findGuidMemberPage);
		System.out.println(page.toString());
		Assert.assertNotNull(page);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购信息(个人中心).
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年8月11日
	 *
	 */
	@Test
	public void findGuid() throws TsfaServiceException{
		FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
		findGuidMemberDto.setMemberNo(TestHelp.memberNoGm_test);
		guidMemberService.findGuid(findGuidMemberDto);
	}
	
	/**
	 * 
	 *
	 * 方法说明：添加导购信息(个人中心)
	 *
	 *
	 * @author 邹磊 CreateDate: 2017年7月13日
	 *
	 */
	@Test
	public void insertGuidMember(){
		AddGuidMemberDto addGuidMemberDto = new AddGuidMemberDto();
		addGuidMemberDto.setCode(GUID.getPreUUID());
		addGuidMemberDto.setMemberNo(GUID.getPreUUID());
		addGuidMemberDto.setMemberName("邹磊");
//		addGuidMemberDto.setShopNo(GUID.getPreUUID());
//		addGuidMemberDto.setShopName("南山分店");
		addGuidMemberDto.setMerchantNo(GUID.getPreUUID());
		addGuidMemberDto.setMerchantName("敏华集团");
		addGuidMemberDto.setMobile("110");
		addGuidMemberDto.setEmail("110@qq.com");
		addGuidMemberDto.setAreaCode(GUID.getPreUUID());
		addGuidMemberDto.setProvinceCode(GUID.getPreUUID());
		addGuidMemberDto.setCityCode(GUID.getPreUUID());
		addGuidMemberDto.setCityAreaCode(GUID.getPreUUID());
		addGuidMemberDto.setAddress("西丽");
		addGuidMemberDto.setHeadAddress("http://");
		guidMemberService.addGuidMember(addGuidMemberDto);
	}
	/**
	 * 
	 *
	 * 方法说明：查找导购信息
	 *
	 *
	 * @author 邹磊 CreateDate: 2017年7月13日
	 *
	 */
	@Test
	public void findGuidMemberByCodeOrMemberNo(){
		FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
		findGuidMemberDto.setMemberNo("LJ_de4938eacb2e49b78d2d0c883da35862");
		guidMemberService.findGuid(findGuidMemberDto);;
	}
	
	@Test
	public void findGuidMemberCount(){
		FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
		findGuidMemberPage.setMerchantNo(TestHelp.merchantNo_test);
		findGuidMemberPage.setAgeFrom(20);
		findGuidMemberPage.setAgeTo(29);
		int count =guidMemberService.findGuidMemberCount(findGuidMemberPage);
		System.out.println(count);
	}
	@Test		
	public void findGuidInfoShop(){
	  FindGuidMemberDto findGuidMemberDto=new FindGuidMemberDto();
	  findGuidMemberDto.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
	  List<GuidInfoShop>  list=guidMemberService.findGuidInfoShop(findGuidMemberDto);
	  System.out.println(list.toString());
	}
	
	@Test
	public void testName() throws Exception {
		UpdateGuidMember member = new UpdateGuidMember();
		member.setCode("LJ_8fc11e26f0ae4cc49df95b72c831f9f5");
		member.setQcord("/headImg/660502888956675062.jpg");
		guidMemberService.updateGuidMember(member);
	}
	
	
}
