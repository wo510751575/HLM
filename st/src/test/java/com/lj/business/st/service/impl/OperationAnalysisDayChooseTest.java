package com.lj.business.st.service.impl;


import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.domain.OperationAnalysisDayChoose;
import com.lj.business.st.dto.FindOperationAnalysisDayChoose;
import com.lj.business.st.dto.OperationAnalysisDayChooseDto;
import com.lj.business.st.dto.OperationAnalysisDayChoosePage;
import com.lj.business.st.service.IOperationAnalysisDayChooseService;

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
public class OperationAnalysisDayChooseTest extends SpringTestCase{
	
	@Resource
	private IOperationAnalysisDayChooseService iOperationAnalysisDayChooseService;
		
	 @Test
     public void insertSelectiveTest()throws TsfaServiceException{
		 
		 OperationAnalysisDayChooseDto operationAnalysisDayChoose =new OperationAnalysisDayChooseDto();
		 operationAnalysisDayChoose.setCode(GUID.getPreUUID());
		 operationAnalysisDayChoose.setCodeList("dd");
		 operationAnalysisDayChoose.setMemberNameGm("ff");
		 operationAnalysisDayChoose.setMemberNoGm("ff");
		 operationAnalysisDayChoose.setMerchantNo("fa");
		 operationAnalysisDayChoose.setMerchantNo("fd");
		 operationAnalysisDayChoose.setShopNo("fd");
		 operationAnalysisDayChoose.setShopName("fdf");
		 operationAnalysisDayChoose.setCreateDate(new Date());
		 iOperationAnalysisDayChooseService.insertSelective(operationAnalysisDayChoose);
     }
	 
	 	@Test
		public void OperationAnalysisDayChoosed() throws TsfaServiceException{
			String code="LJ_69b741625ea64ba284b39302907bde63";
			iOperationAnalysisDayChooseService.selectByPrimaryKey(code);
		}
		@Test
		public void OperationAnalysisDayChoosedList(){
		FindOperationAnalysisDayChoose operationAnalysisDayChoose=new FindOperationAnalysisDayChoose();
		iOperationAnalysisDayChooseService.findOperationAnalysisDayChoose(operationAnalysisDayChoose);
		}
      
		@Test
		public void DelectOperationAnalysisDayChoosed(){
			String merchantNo=null;
			iOperationAnalysisDayChooseService.deleteByMerchantNo(merchantNo);
		}
}
