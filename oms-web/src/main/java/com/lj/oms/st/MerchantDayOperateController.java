package com.lj.oms.st;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lj.oms.common.BaseController;
import com.lj.business.st.dto.MerchantDayOperateDto;
import com.lj.business.st.service.IMerchantDayOperationService;

@Controller
@RequestMapping(value = "${adminPath}/merchantDayOperate")
public class MerchantDayOperateController extends BaseController{
	
	@Resource
	private IMerchantDayOperationService merchantDayOperationService;
	
	/**
	 * 
	 *
	 * 方法说明：商户运营日报报表
	 *
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月27日
	 *
	 */
	@RequestMapping(value={"view"})
	public String findClientAnalyzeList(Model model, String merchantNo){
		MerchantDayOperateDto generatorMerChantDayOperate = merchantDayOperationService.generatorMerChantDayOperate(merchantNo);
		model.addAttribute("data", generatorMerChantDayOperate);
		return "modules/operate/merchantDayOperate";
	}

}
