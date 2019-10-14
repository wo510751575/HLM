package com.ye.business.hx.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.ye.business.hx.constant.HxConstant;
import com.ye.business.hx.dto.FindShopConfigPage;
import com.ye.business.hx.dto.ShopConfigDto;
import com.ye.business.hx.service.IShopConfigService;

/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
public class ShopConfigServiceImplTest extends SpringTestCase{

	@Resource
	IShopConfigService shopConfigService;



	/**
	 * 
	 *
	 * 方法说明：添加门店配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addShopConfig() throws TsfaServiceException{
		ShopConfigDto shopConfigDto = new ShopConfigDto();
		//add数据录入
//		shopConfigDto.setCode("Code");
//		shopConfigDto.setShopNo("ShopNo");
//		shopConfigDto.setShopName("ShopName");
		shopConfigDto.setMerchantNo("1");
//		shopConfigDto.setMerchantName("ROOT");
		shopConfigDto.setLableName("支付类型");
		shopConfigDto.setLableNo(HxConstant.CONFIG_LABLE_NO_PAY_TYPE);
		shopConfigDto.setParentCode(null);
//		shopConfigDto.setUpdateId("UpdateId");
//		shopConfigDto.setUpdateDate(new Date());
//		shopConfigDto.setCreateId("CreateId");
//		shopConfigDto.setCreateDate(new Date());
//		shopConfigDto.setIndexNo(2);
		
		shopConfigService.addShopConfig(shopConfigDto);
		
	}
	
	/***
	 * 患者预约项目
	 * @throws TsfaServiceException
	 */
	@Test
	public void addShopConfigPatientProject() throws TsfaServiceException{
		List<String> parentName=new ArrayList<>();
		parentName.add("正畸");
		parentName.add("种植");
		parentName.add("修复");
		parentName.add("口腔检查");
		parentName.add("拔牙");
		parentName.add("补牙");
		parentName.add("牙周");
		parentName.add("牙体");
		parentName.add("治疗");
		parentName.add("洁牙");
		parentName.add("其它");

		
		List<String> childName = new ArrayList<>();
		childName.add("做保持器或精调");
		childName.add("粘附件");
		childName.add("拆托槽");
		childName.add("拆附件");
		childName.add("免费拍片");
		childName.add("寄牙套");
		childName.add("树脂");
		childName.add("取模、拍照");
		childName.add("提醒换牙");
		childName.add("舌侧扣");
		childName.add("取模精调或做保持器");
		childName.add("取硅胶模");
		childName.add("弓丝戳嘴");
		childName.add("戴保持器定期复查");
		childName.add("戴牙套1，片切，粘附件");
		childName.add("托槽脱落");
		childName.add("扩弓器复诊");
		childName.add("正畸拔牙");
		childName.add("正畸咨询");
		childName.add("正畸复诊");
		childName.add("确认正畸方案");
		childName.add("粘上颌托槽");
		childName.add("粘全口托槽");
		childName.add("钢丝扎嘴");
		childName.add("隐形拿牙套");
		childName.add("隐形片切");
		childName.add("隐形确认动画");
		childName.add("隐形结束");
		childName.add("隐形附件脱落");
		childName.add("隐形附件脱落");
		childName.add("贴全口托槽");
		childName.add("戴保持器");
		childName.add("打支抗钉");
		childName.add("正畸拔牙");
		childName.add("拍照");
		
		childName.add("break");
		
		childName.add("戴牙复诊");
		childName.add("确认种植方案");
		childName.add("种植咨询");
		childName.add("种植复诊");
		childName.add("种植戴牙");
		childName.add("种植戴牙复查");
		childName.add("种植拆线");
		childName.add("种植手术");
		childName.add("种植二期");
		childName.add("种植取模");
		childName.add("种植取模修复");
		childName.add("种植定期");
		childName.add("种植");
		
		childName.add("break");
		
		childName.add("临时牙");
		childName.add("修复咨询");
		childName.add("冷光美白咨询");
		childName.add("戴临时牙");
		childName.add("戴临时牙复查");
		childName.add("戴牙后复诊");
		childName.add("戴贴面复诊");
		childName.add("拆冠");
		childName.add("戴牙");
		childName.add("试内冠");
		childName.add("试戴义齿");
		childName.add("试支架");
		childName.add("备牙");
		childName.add("修义齿");
		
		childName.add("break");
		
		childName.add("口腔检查");
		
		childName.add("break");
		
		childName.add("拔乳牙");
		childName.add("拔智齿");
		childName.add("拔牙");
		childName.add("拔牙咨询");
		childName.add("拔牙复诊");
		childName.add("拔牙拆线");
		
		childName.add("break");
		
		childName.add("试戴临时牙");
		childName.add("补牙复诊");
		
		childName.add("break");
		
		childName.add("牙周咨询");
		childName.add("牙周复查");
		childName.add("牙周治疗");
		childName.add("牙周上药");
		childName.add("牙周刮治");
		childName.add("龈上洁治");
		childName.add("龈下洁治");
		
		childName.add("break");
		
		childName.add("换药");
		childName.add("补牙");
		childName.add("根充");
		childName.add("根管治疗");
		childName.add("根管预备");
		
		childName.add("break");
		childName.add("根备");
		
		childName.add("break");
		
		childName.add("洁牙");
		
		childName.add("break");
		
		childName.add("涂氟");
		childName.add("休息");
		childName.add("分期到账");
		childName.add("复诊");
		childName.add("戴牙");
		childName.add("戴贴面");
		childName.add("拆矫正器");
		childName.add("检查");
		childName.add("洗牙复诊");
		childName.add("贴面咨询");
		childName.add("美团洁牙");
		childName.add("冷光美白");
		childName.add("免费洁牙");
		childName.add("不约");
		
		int j = 0;
		for (int i = 0; i < parentName.size(); i++) {
			ShopConfigDto pDto = new ShopConfigDto();
			pDto.setLableName(parentName.get(i));
			pDto.setParentCode("LJ_4cb7d94e95bc415d804af76fc5c418af");
			pDto.setIndexNo(i);
			pDto.setMerchantNo("1");
			ShopConfigDto rtpDto= shopConfigService.addShopConfig(pDto);
			
			for (; j < childName.size(); j++) {
				if(childName.get(j).equals("break")) {
					j++;
					break;
				}
				ShopConfigDto cDto = new ShopConfigDto();
				cDto.setLableName(childName.get(j));
				cDto.setParentCode(rtpDto.getCode());
				cDto.setIndexNo(j);
				cDto.setMerchantNo("1");
				ShopConfigDto ctpDto= shopConfigService.addShopConfig(cDto);
			}
		}
	}
	
	/***
	 * 患者来源
	 * @throws TsfaServiceException
	 */
	@Test
	public void addShopConfigUser_source() throws TsfaServiceException{
		List<String> parentName=new ArrayList<>();
		parentName.add("乐莎莎");

		parentName.add("美团");


		parentName.add("就医160");
		parentName.add("新氧");
		parentName.add("美柚");
		parentName.add("牙么么");
		parentName.add("转介绍");

		parentName.add("自主上门");

		parentName.add("医生自带");
		parentName.add("微信公众号");
		parentName.add("电话预约");
		parentName.add("其它");

		
		List<String> childName1 = new ArrayList<>();
		
		childName1.add("转介绍");
		childName1.add("break");
		childName1.add("美团团购");
		childName1.add("美团霸王餐");
		childName1.add("其他");
		childName1.add("break");
		childName1.add("break");
		childName1.add("break");
		childName1.add("break");
		childName1.add("break");
		childName1.add("员工转介绍");
		childName1.add("患者转介绍");
		childName1.add("break");
		childName1.add("家在附近");
		childName1.add("团购");
		childName1.add("break");
		childName1.add("break");
		childName1.add("break");
		childName1.add("break");
		childName1.add("break");
		
		

		
		List<String> childName2 = new ArrayList<>();
		childName2.add("员工转介绍");
		childName2.add("患者转介绍");
		childName2.add("break");
		
		int j = 0;
		int k=0;
		for (int i = 0; i < parentName.size(); i++) {
			ShopConfigDto pDto = new ShopConfigDto();
			pDto.setLableName(parentName.get(i));
			pDto.setParentCode("LJ_8d2c107844ed44699c67d38803edf879");
			pDto.setIndexNo(i);
			pDto.setMerchantNo("1");
			ShopConfigDto rtpDto= shopConfigService.addShopConfig(pDto);
			
			for (; j < childName1.size(); j++) {
				if(childName1.get(j).equals("break")) {
					System.out.println("j:"+j);
					j++;
					break;
				}
				ShopConfigDto cDto = new ShopConfigDto();
				cDto.setLableName(childName1.get(j));
				cDto.setParentCode(rtpDto.getCode());
				cDto.setIndexNo(j);
				cDto.setMerchantNo("1");
				ShopConfigDto ctpDto= shopConfigService.addShopConfig(cDto);
				
				for (; k < childName2.size(); k++) {
					if(childName2.get(k).equals("break")) {
						k++;
						break;
					}
					ShopConfigDto cDto2 = new ShopConfigDto();
					cDto2.setLableName(childName2.get(k));
					cDto2.setParentCode(ctpDto.getCode());
					cDto2.setIndexNo(k);
					cDto2.setMerchantNo("1");
					ShopConfigDto ctpDto2= shopConfigService.addShopConfig(cDto2);

				}
			}
		}
		
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：修改门店配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateShopConfig() throws TsfaServiceException{
		ShopConfigDto shopConfigDto = new ShopConfigDto();
		//update数据录入
		shopConfigDto.setCode("Code");
		shopConfigDto.setShopNo("ShopNo");
		shopConfigDto.setShopName("ShopName");
		shopConfigDto.setMerchantNo("MerchantNo");
		shopConfigDto.setMerchantName("MerchantName");
		shopConfigDto.setLableName("LableName");
		shopConfigDto.setLableNo("LableNo");
		shopConfigDto.setParentCode("ParentCode");
		shopConfigDto.setUpdateId("UpdateId");
		shopConfigDto.setUpdateDate(new Date());
		shopConfigDto.setCreateId("CreateId");
		shopConfigDto.setCreateDate(new Date());
		shopConfigDto.setIndexNo(2);

		shopConfigService.updateShopConfig(shopConfigDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门店配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopConfig() throws TsfaServiceException{
		ShopConfigDto findShopConfig = new ShopConfigDto();
		findShopConfig.setCode("1");
		ShopConfigDto data = shopConfigService.findShopConfig(findShopConfig);
		System.out.println(JsonUtils.jsonFromObject(data));
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门店配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopConfigPage() throws TsfaServiceException{
		FindShopConfigPage findShopConfigPage = new FindShopConfigPage();
		Page<ShopConfigDto> page = shopConfigService.findShopConfigPage(findShopConfigPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门店配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopConfigs() throws TsfaServiceException{
		FindShopConfigPage findShopConfigPage = new FindShopConfigPage();
		List<ShopConfigDto> page = shopConfigService.findShopConfigs(findShopConfigPage);
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void updateShopConfigIndexNo() {
		ShopConfigDto configDto=new ShopConfigDto();
		String typeJsons="[{\"code\":\"LJ_bf86c617aec340408cb3d4412d22341b\",\"indexNo\":2},{\"code\":\"LJ_b79d466c9e974898bbe045a4dbe30229\",\"indexNo\":1}]";
		List<ShopConfigDto> types = JSONArray.parseArray(typeJsons,ShopConfigDto.class);
		configDto.setChilds(types);
		shopConfigService.updateShopConfigIndexNo(configDto);
	}
	
}
