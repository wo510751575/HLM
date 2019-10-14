package com.lj.business.st.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.domain.WorkBrDayChoose;
import com.lj.business.st.dto.FindWorkBrDayPage;
import com.lj.business.st.dto.FindWorkDayGmIndex;
import com.lj.business.st.dto.WorkBrDayChooseDto;
import com.lj.business.st.dto.WorkBrDayChoosePage;
import com.lj.business.st.service.IWorkBrDayChooseService;

/**
 * 
 * 
 * 类说明：测试类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月1日
 */
public class WorkBrDayChooseTest extends SpringTestCase {

	@Resource
	private IWorkBrDayChooseService iWorkBrDayChooseService;
	
	@Test
	public void addWorkBrDayChoose() throws TsfaServiceException{
		
		WorkBrDayChooseDto workBrDayChoose=new WorkBrDayChooseDto();
		

		workBrDayChoose.setMemberNameGm("d");
		workBrDayChoose.setMemberNoGm("010");
		workBrDayChoose.setMerchantNo("011d2");
		workBrDayChoose.setCodeList("0111");
		workBrDayChoose.setNameList("oms");
		workBrDayChoose.setShopName("龙华分店");
		workBrDayChoose.setShopNo("0113");	
		workBrDayChoose.setSeq(1);
		workBrDayChoose.setImgAddr("img");
		workBrDayChoose.setCreateDate(new Date());
		System.out.println(workBrDayChoose);
		//add数据录入
		iWorkBrDayChooseService.insertSelective(workBrDayChoose);
		
	}
	@Test
	public void updateWorkBrDayChoose() throws TsfaServiceException{
		
		WorkBrDayChooseDto workBrDayChoose=new WorkBrDayChooseDto();	
      
		workBrDayChoose.setCode(GUID.getPreUUID());
		workBrDayChoose.setMemberNameGm("ddsssd");
		workBrDayChoose.setMemberNoGm("010");
		workBrDayChoose.setMerchantNo("011d2");
		workBrDayChoose.setCodeList("0111");
		workBrDayChoose.setNameList("oms");
		workBrDayChoose.setShopName("龙华分店");
		workBrDayChoose.setShopNo("0113");	
		workBrDayChoose.setSeq(2);
		workBrDayChoose.setCreateDate(new Date());
		
		iWorkBrDayChooseService.updateByPrimaryKeySelective(workBrDayChoose);
		
	}
	@Test
	public void findWorkBrDay() throws TsfaServiceException{
		String merchantNo="e79d975846ee41ba8c3c55738fda66a9";
		FindWorkDayGmIndex findWorkDayGmIndex=new FindWorkDayGmIndex();
		findWorkDayGmIndex.setMerchantNo(merchantNo);
		iWorkBrDayChooseService.findWorkBrDayChooseList(findWorkDayGmIndex);
	

	
}
}