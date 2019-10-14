package com.lj.business.api;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.api.controller.pay.PayAction;

public class PayActionTest extends SpringTestCase {
	
	
	
	private static Logger LOG = LoggerFactory.getLogger(PayActionTest.class);
	
	@Resource
	PayAction payAction;
	
	
	@Test
	public void testCreatePay(){
		/*PayOrderInfo payOrder  = new PayOrderInfo();
		payOrder.setOpenId("oLeZQwwxgZibwAh6inwlAHJhqzQc");
		payOrder.setPayMoney(1L);
		payOrder.setCreatePayIp("127.0.0.1");
||||||| .r13293
		PayOrderInfo payOrder  = new PayOrderInfo();
		payOrder.setOpenId("oLeZQwwxgZibwAh6inwlAHJhqzQc");
		payOrder.setPayMoney(1L);
		payOrder.setCreatePayIp("127.0.0.1");
=======
//		PayOrderInfo payOrder  = new PayOrderInfo();
//		payOrder.setOpenId("oLeZQwwxgZibwAh6inwlAHJhqzQc");
//		payOrder.setPayMoney(1L);
//		payOrder.setCreatePayIp("127.0.0.1");
>>>>>>> .r13391
		//payAction.createPay(payOrder);
		*/
		
	}
	
	
	

}
