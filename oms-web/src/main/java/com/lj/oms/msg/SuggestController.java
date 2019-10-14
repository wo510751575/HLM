package com.lj.oms.msg;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.messagecenter.msg.dto.FindSuggestPageDto;
import com.lj.messagecenter.msg.dto.FindSuggestReturn;
import com.lj.messagecenter.msg.service.ISuggestService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：反馈信息表
 *  
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月3日
 */
@Controller
@RequestMapping(value = "${adminPath}/baseConfig/suggest")
public class SuggestController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(SuggestController.class);
    /**
     * 反馈信息服务
     */
	@Resource
	private ISuggestService suggestService;
	
	/**
	 * 
	 *
	 * 方法说明：反馈信息
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param suggestPageDto
	 * @return 服务提供分页展现反馈信息列表
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("baseConfig:suggest:view")
	@RequestMapping(value = {"list", ""})
	public String list( Model model,Integer pageNo,Integer pageSize,FindSuggestPageDto  suggestPageDto){	
		try {
			if(pageNo !=null){
				suggestPageDto.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				suggestPageDto.setLimit(pageSize);
			}		
			suggestPageDto.setMerchantNo(UserUtils.getMerchantNo());
			Page<FindSuggestPageDto>  pages=suggestService.findSuggestPage(suggestPageDto);
			List<FindSuggestPageDto> list =Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindSuggestPageDto> page=new com.ape.common.persistence.Page<FindSuggestPageDto>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
		} catch (Exception e) {
			logger.error("获取客户反馈信息错误！");
		}
	

		return "modules/baseConfig/suggestList";				
	}

	
	/**
	 * 
	 *
	 * 方法说明：反馈信息编辑form页面
	 *
	 * @param model
	 * @param findSuggestPageDto
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("baseConfig:suggest:view")
	@RequestMapping(value = "form")
	public String form(Model model,FindSuggestPageDto findSuggestPageDto){
		try {
			if(findSuggestPageDto !=null && findSuggestPageDto.getCode()!=null){
				FindSuggestReturn editSuggestDto= 	suggestService.selectByCode(findSuggestPageDto.getCode());
				model.addAttribute("data", editSuggestDto);
			}
		} catch (Exception e) {
			logger.error("查询客户反馈信息错误！");  
		}	
		return "modules/baseConfig/suggestForm";	
		
	}

	

	
}
