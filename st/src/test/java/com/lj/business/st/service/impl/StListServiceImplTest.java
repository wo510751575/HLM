package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.AddStList;
import com.lj.business.st.dto.FindStList;
import com.lj.business.st.dto.FindStListPage;
import com.lj.business.st.dto.FindStListPageReturn;
import com.lj.business.st.dto.InitStListByMerchant;
import com.lj.business.st.dto.UpdateStList;
import com.lj.business.st.emus.TypeList;
import com.lj.business.st.service.IStListService;


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
public class StListServiceImplTest extends SpringTestCase{

	@Resource
	private IStListService stListService;



	/**
	 * 
	 *
	 * 方法说明：添加报表项目信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *	//运营分析
	SALE_FUNNEL("销售漏斗"),
	AREA_ORDER("区域成单分析"),
	CUSTOMER_BEHAVIOR_ANALYSIS("客户行为分析"),
	CUSTOMER_PICTURE("客户画像"),
	FOLLOW("跟进分析"),
	AREA_CUSTOMER("区域客户分析"),
	//跟进分析
	SOCIAL_ANALYTICS("社交分析"),
	CUSTOMER_BEHAVIOR("客户行为"),
	CUSTOMER_ANALYSIS("客户分析"),
	MATERIAL_ANALYSIS("素材分析"),
	CUSTOMER_CLASS("客户分类"),
	//优秀导购
	HARDWORKING_AWARD("最勤快奖"),
	CUSTOMER_MAX("客户最多奖"),
	NEW_CUSTOMER_MAX("新增客户最多奖"),
	BEST_SALES("最牛销售奖"),
	EXTRUDE_FOLLOW("跟进效果突出奖"),
	ACTIVITY_AWARD("活动奖"),
	//日工作简报
	SALE("销售额"),
	INTENTION("意向客户数"),
	SUCCESS("成单客户数"),
	NEW_CUSTOMER("新增客户数"),
	GIVEUP("暂停跟进数"),
	//运营日报
	TOTAL_CUSTOMER("总客户量"),
	VISIT_CUSTOMER("到店体验量"),
	ORDER("成单量"),
	NEW_CUSTOMER("新增客户量"),
	INTENTION_CUSTOMER("意向客户量"),
	ABANDON_CUSTOMER("放弃客户")
	;
	 */
	@Test
	public void addStList() throws TsfaServiceException{
		AddStList addStList = new AddStList();
		//add数据录入
		for(int i=0;i<=28;i++){
		addStList.setNameList("区域成单分析");
		addStList.setDesList("区域成单分析报表");
		addStList.setStatus("Y");
		addStList.setTypeList("AREA_ORDER");
		addStList.setUnitList("GE");
		addStList.setTableList("OPERATION_ANALYSIS_DAY_CHOOSE");
		addStList.setImgAddr("//yyfx/Univariate_analysis.png");
		Assert.assertNotNull(stListService.addStList(addStList ));
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改报表项目信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateStList() throws TsfaServiceException{
		UpdateStList updateStList = new UpdateStList();
		//update数据录入
		updateStList.setCode("stListService");
		updateStList.setNameList("NameList");
		updateStList.setDesList("DesList");
		updateStList.setStatus("Y");
		updateStList.setTypeList("TypeList");
		updateStList.setUnitList("UnitList");
		updateStList.setTableList("TableList");
		updateStList.setImgAddr("ImgAddr");
		updateStList.setCreateDate(new Date());

		stListService.updateStList(updateStList );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找报表项目信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findStList() throws TsfaServiceException{
		FindStList findStList = new FindStList();
		findStList.setCode("Code");
		stListService.findStList(findStList);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找报表项目信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findStListPage() throws TsfaServiceException{
		FindStListPage findStListPage = new FindStListPage();
		findStListPage.setTypeList(TypeList.ABANDON_CUSTOMER.toString());
		Page<FindStListPageReturn> page = stListService.findStListPage(findStListPage);
		System.out.println(page.toString());
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：新增商户初始化统计报表
	 *
	 * @param initStListByMerchant
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月19日
	 *
	 */
	@Test
	public void testInitStListByMerchant() {
		InitStListByMerchant merchant = new InitStListByMerchant();
		merchant.setMerchantNo("8d38c994b2334fefa586757c9bbd1680");
		stListService.initStListByMerchant(merchant);
	}
	
	
}
