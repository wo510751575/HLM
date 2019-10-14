package com.lj.oms.weixin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.weixin.dto.FindImChatInfoSensitivePage;
import com.lj.business.weixin.dto.FindImChatInfoSensitivePageReturn;
import com.lj.business.weixin.service.IImChatInfoSensitiveService;

/**
 * 
 * 
 * 类说明：IM聊天记录敏感词记录
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2018年1月11日
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/ImChatInfoSensitive")
public class ImChatInfoSensitiveController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImChatInfoSensitiveController.class);

	@Resource
	private IImChatInfoSensitiveService imChatInfoSensitiveService;
	
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo ,Integer pageSize,FindImChatInfoSensitivePage findImChatInfoSensitivePage){
		try {
			if(pageNo != null){
				findImChatInfoSensitivePage.setStart((pageNo -1) * pageSize);
			}
			if(pageSize != null){
				findImChatInfoSensitivePage.setLimit(pageSize);
			}
			
			Page<FindImChatInfoSensitivePageReturn>  pages= imChatInfoSensitiveService.findImChatInfoSensitivePage(findImChatInfoSensitivePage);
			List<FindImChatInfoSensitivePageReturn> list = Lists.newArrayList();
		    list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindImChatInfoSensitivePageReturn> page
			= new com.ape.common.persistence.Page<FindImChatInfoSensitivePageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
		    page.initialize();
		    model.addAttribute("page",page);
		    model.addAttribute("findImChatInfoSensitivePage", findImChatInfoSensitivePage);
		} catch (Exception e) {
			logger.error("查找IM聊天记录敏感词信息错误！",e);
		}
		
		return "modules/weixin/ImChatInfoSensitiveList";
	}
}
