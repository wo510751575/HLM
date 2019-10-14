package com.lj.oms.member;

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
import com.lj.business.member.dto.FindMerchantPage;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.service.IMerchantService;

/**
 * 
 * 
 * 类说明：商户Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月14日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/merchant")
public class MerchantController {
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);

	@Resource
	private IMerchantService merchantService;		//商户服务
	

	/**
	 * 
	 *
	 * 方法说明：商户列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findMerchantPage
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequestMapping(value = {"list", ""})
	public String list( Model model,Integer pageNo,Integer pageSize,FindMerchantPage findMerchantPage) {
		try {
			if (pageNo != null) {
				findMerchantPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findMerchantPage.setLimit(pageSize);
			}
			Page<FindMerchantPageReturn> pages = merchantService.findMerchantPage(findMerchantPage);
			List<FindMerchantPageReturn> basePageReturns = Lists.newArrayList();
			basePageReturns.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindMerchantPageReturn> page = new com.ape.common.persistence.Page<FindMerchantPageReturn>(pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), basePageReturns);
			model.addAttribute("list", page);
		} catch (Exception e) {
			logger.error("获取商户信息错误！", e);
		}
	
		return "modules/member/merchantList";
	}
}
