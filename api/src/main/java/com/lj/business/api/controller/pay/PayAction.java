package com.lj.business.api.controller.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lj.business.api.controller.Action;

//import com.lj.business.pay.dto.PayOrderReturn;
//import com.lj.business.pay.dto.PayRequestDto;
//import com.lj.business.pay.service.IPaymentService;


@Controller
@RequestMapping(value="/pay/")
public class PayAction extends Action{
	
	
	private static Logger LOG = LoggerFactory.getLogger(PayAction.class);
	
	
//	@Resource
//	public IPaymentService weixinPayService;
//	
//	@RequestMapping(value="createPay.do")
//	@ResponseBody
//	public PayOrderReturn createPay(PayRequestDto payRequest,HttpServletRequest request){
//		LOG.info("createPay (payOrder:{})",payRequest );
//		String clientIP = getRemoteHost(request);
//		payRequest.setClientIp(clientIP);
//		LOG.info("remote custom ip :{}",clientIP);
//		PayOrderReturn payOrderReturn = weixinPayService.createPay(payRequest);
//		LOG.info(" end createPay  result:{}",payOrderReturn);
//		return payOrderReturn;
//	}

}
