package com.lj.business.st.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.domain.OperationDayChoose;
import com.lj.business.st.dto.FindOperateDayReport;
import com.lj.business.st.dto.FindOperateDayReportReturn;
import com.lj.business.st.dto.FindOperationDayChoose;
import com.lj.business.st.dto.OperationDayChooseDto;
import com.lj.business.st.dto.OperationDayChoosePage;
import com.lj.business.st.service.IOperationDayChooseService;

/**
 * 
 * 
 * 类说明：运营日报表选择表（基础）
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
public class OperationDayChooseTest extends SpringTestCase{
	
	@Resource
	private IOperationDayChooseService iOperationDayChooseService;
	
	
	@Test
	public void addOperationDayChoose()throws TsfaServiceException{
		OperationDayChooseDto operationDayChoose=new OperationDayChooseDto();
		operationDayChoose.setCode(GUID.getPreUUID());
		operationDayChoose.setCodeList("dd");
		operationDayChoose.setMemberNameGm("ddfd");
		operationDayChoose.setMemberNoGm("ssf");
		operationDayChoose.setMerchantNo("dffddf");
		operationDayChoose.setNameList("dfdf");
		operationDayChoose.setShopName("dfdf");
		operationDayChoose.setShopNo("dff");
		operationDayChoose.setImgAddr("df");
		operationDayChoose.setSeq(1);
		operationDayChoose.setCreateDate(new Date());
		iOperationDayChooseService.insertSelectAll(operationDayChoose);
	}

	@Test
	public void selectList()throws TsfaServiceException{
/*		FindOperationDayChoose findOperationDayChoose=new FindOperationDayChoose();
		iOperationDayChooseService.findOperationDayChoose(findOperationDayChoose);*/
		FindOperateDayReport findOperateDayReport=new FindOperateDayReport();
		findOperateDayReport.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		 List<FindOperateDayReportReturn> list=iOperationDayChooseService.findOperationDayChooseList(findOperateDayReport);
		 System.out.println(list.toString());
			
	}
}
