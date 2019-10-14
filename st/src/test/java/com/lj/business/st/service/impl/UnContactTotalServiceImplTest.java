package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.AddUnContactTotal;
import com.lj.business.st.dto.DelUnContactTotal;
import com.lj.business.st.dto.FindUnContactTotal;
import com.lj.business.st.dto.FindUnContactTotalInfo;
import com.lj.business.st.dto.FindUnContactTotalInfoReturn;
import com.lj.business.st.dto.FindUnContactTotalPage;
import com.lj.business.st.dto.FindUnContactTotalPageReturn;
import com.lj.business.st.dto.UpdateUnContactTotal;
import com.lj.business.st.service.IUnContactTotalService;
import com.lj.business.st.util.TestHelp;


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
public class UnContactTotalServiceImplTest extends SpringTestCase{

	@Resource
	IUnContactTotalService unContactTotalService;



	/**
	 * 
	 *
	 * 方法说明：添加未联系客户统计信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void addUnContactTotal() throws TsfaServiceException{
		AddUnContactTotal addUnContactTotal = new AddUnContactTotal();
		//add数据录入
		addUnContactTotal.setMerchantNo(TestHelp.merchantNo_test);
		addUnContactTotal.setMemberNoGm("fa49303dfaf34be2be9c8add34b630f8");
		addUnContactTotal.setMemberNameGm("卖沙发的小强");
		addUnContactTotal.setYizhouYiyue(1);
		addUnContactTotal.setYiyueSanyue(2);
		addUnContactTotal.setSanyueLiuyue(3);
		addUnContactTotal.setLiuyueEnd(4);
		Assert.assertNotNull(unContactTotalService.addUnContactTotal(addUnContactTotal ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改未联系客户统计信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void updateUnContactTotal() throws TsfaServiceException{
		UpdateUnContactTotal updateUnContactTotal = new UpdateUnContactTotal();
		//update数据录入
		updateUnContactTotal.setCode("Code");
		updateUnContactTotal.setMerchantNo("MerchantNo");
		updateUnContactTotal.setMemberNoGm("MemberNoGm");
		updateUnContactTotal.setMemberNameGm("MemberNameGm");
		updateUnContactTotal.setYizhouYiyue(1);
		updateUnContactTotal.setYiyueSanyue(2);
		updateUnContactTotal.setSanyueLiuyue(3);
		updateUnContactTotal.setLiuyueEnd(4);

		unContactTotalService.updateUnContactTotal(updateUnContactTotal );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找未联系客户统计信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void findUnContactTotal() throws TsfaServiceException{
		FindUnContactTotal findUnContactTotal = new FindUnContactTotal();
		findUnContactTotal.setCode("111");
		unContactTotalService.findUnContactTotal(findUnContactTotal);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找未联系客户统计信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void findUnContactTotalPage() throws TsfaServiceException{
		FindUnContactTotalPage findUnContactTotalPage = new FindUnContactTotalPage();
		Page<FindUnContactTotalPageReturn> page = unContactTotalService.findUnContactTotalPage(findUnContactTotalPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找未联系客户统计信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void findUnContactTotalList() throws TsfaServiceException{
		FindUnContactTotalInfo findUnContactTotalInfo = new FindUnContactTotalInfo();
		FindUnContactTotalInfoReturn page = unContactTotalService.findUnContactTotalInfo(findUnContactTotalInfo);
		//Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除未联系客户统计信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void delUnContactTotal() throws TsfaServiceException{
		DelUnContactTotal delUnContactTotal = new DelUnContactTotal();
		delUnContactTotal.setCode("111");
		Assert.assertNotNull(unContactTotalService.delUnContactTotal(delUnContactTotal));
		
	}
	
	
}
