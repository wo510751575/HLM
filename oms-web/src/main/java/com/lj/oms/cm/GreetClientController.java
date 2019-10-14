package com.lj.oms.cm;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.cm.dto.FindGreetClient;
import com.lj.business.cm.dto.FindGreetClientPage;
import com.lj.business.cm.dto.FindGreetClientPageReturn;
import com.lj.business.cm.dto.FindGreetClientReturn;
import com.lj.business.cm.service.IGreetClientService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：问候客户
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月24日
 */
@Controller
@RequestMapping(value = "${adminPath}/business/greetclient")
public class GreetClientController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(GreetClientController.class);
   
	@Resource
	private IGreetClientService greetClientService;
	
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo ,Integer pageSize,FindGreetClientPage findGreetClientPage){
		try {
			if(pageNo!=null){
				findGreetClientPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findGreetClientPage.setLimit(pageSize);
			}
		  findGreetClientPage.setMerchantNo(UserUtils.getUser().getCompany().getId());
		  Page<FindGreetClientPageReturn> pages=greetClientService.findGreetClientPage(findGreetClientPage);
		  List<FindGreetClientPageReturn> list=Lists.newArrayList();
		  list.addAll(pages.getRows());
		  com.ape.common.persistence.Page<FindGreetClientPageReturn> page=new com.ape.common.persistence.Page<FindGreetClientPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
		  page.initialize();
		  model.addAttribute("page",page);
		  model.addAttribute("findGreetClientPage",findGreetClientPage);
		} catch (Exception e) {
			logger.error("获取客户问候信息错误!");
		}
		return "modules/business/greetclientList";		
	}
	@RequestMapping(value="form")
	public String form(Model model,FindGreetClient findGreetClient){
		try {
			if(findGreetClient!=null && findGreetClient.getCode()!=null){
			  FindGreetClientReturn findGreetClientReturn= greetClientService.findGreetClient(findGreetClient);
			  model.addAttribute("data", findGreetClientReturn);				
			}
		} catch (Exception e) {
			logger.error("获取客户问候信息错误!");
		}
	    return  "modules/business/greetclientForm";
	}

}
