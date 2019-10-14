package com.lj.business.st.service.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.AddClientExpTotal;
import com.lj.business.st.dto.FindClientExpTotal;
import com.lj.business.st.dto.FindClientExpTotalReturn;
import com.lj.business.st.service.IClientExpTotalService;
import com.lj.business.st.util.TestHelp;

/**
 * 
 * 
 * 类说明：客户到店体验统计测试
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月28日
 */
public class ClientExpTest extends SpringTestCase {
  
	@Resource
	private IClientExpTotalService iClientExpTotalService;
	
	@Test
	public void testAdd()throws TsfaServiceException{
		AddClientExpTotal addClientExpTotal=new AddClientExpTotal();
		addClientExpTotal.setCode(GUID.getPreUUID());
		addClientExpTotal.setStDate(new Date());
		addClientExpTotal.setMemberNameGm("d");
		addClientExpTotal.setMemberNoGm("d");
		addClientExpTotal.setMerchantNo("d");
		addClientExpTotal.setNumAdd(22);
		addClientExpTotal.setShopName("df");
		addClientExpTotal.setShopNo("d");
		iClientExpTotalService.insertSelective(addClientExpTotal);
	}
	
	@Test
	public void testFind()throws TsfaServiceException{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("merchantNo", "e79d975846ee41ba8c3c55738fda66a9");
		map.put("startTime", "2017-08-06 00:00:00");
		map.put("endTime", "2017-09-06 23:59:59");
		/*List<Map<String,Object>> list=iClientExpTotalService.findClientExpTotal(map);
		System.out.println(list);*/
	}
	
	@Test
	public void findClientExpTotalList() throws Exception {
		FindClientExpTotal findClientExpTotal = new FindClientExpTotal();
		findClientExpTotal.setMerchantNo("2169d1820e254e86b110dff73e1bd58f");
		findClientExpTotal.setShopNo("LJ_4732bfda70fb4bc0b6ad0e38dcc4986b");
		findClientExpTotal.setBeginDate("2017-09-05 00:00:00");
		findClientExpTotal.setEndDate("2017-09-11 23:59:59");
		FindClientExpTotalReturn list = iClientExpTotalService.findClientExpTotalList(findClientExpTotal);
		List<Map<String, Object>> datas = list.getDatas();
		for (Map<String, Object> map : datas) {
			for (Entry<String, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
			System.out.println("=================");
		}
	}
	
}
