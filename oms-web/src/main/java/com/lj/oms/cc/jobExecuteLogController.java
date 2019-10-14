package com.lj.oms.cc;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.IPage;
import com.lj.cc.domain.JobExecuteLog;
import com.lj.cc.dto.JobExecuteLogParam;
import com.lj.cc.service.IJobExecuteFeedbackService;
/**
 * 
 * 
 * 类说明：job执行日志
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年10月19日
 */
@Controller
@RequestMapping(value = "${adminPath}/cc/jobExecuteLog")
public class jobExecuteLogController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(jobExecuteLogController.class);
	
	
   @Resource
   private IJobExecuteFeedbackService jobExecuteFeedbackService;
   
   
   /**
    * 
    *
    * 方法说明：调度信息查询
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param param
    * @return
    *
    * @author 罗书明 CreateDate: 2018年4月4日
    *
    */
   @RequestMapping(value={"list",""})
   public String list(Model model,Integer pageNo,Integer pageSize,JobExecuteLogParam param){
	   try {
		   if(pageNo!=null){
			   param.setStart((pageNo-1)*pageSize); 
		   }
		   if(pageSize!=null){
			   param.setLimit(pageSize);
		   }
		   IPage<JobExecuteLog> pages=jobExecuteFeedbackService.queryJobExecuteLogList(param);
		   List<JobExecuteLog> list=Lists.newArrayList();
		   list.addAll(pages.getRows());
		   com.ape.common.persistence.Page<JobExecuteLog> page=new com.ape.common.persistence.Page<JobExecuteLog>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
		   page.initialize();
		   model.addAttribute("page", page);
	} catch (Exception e) {
		logger.error("",e);
	}
	   return "modules/cc/jobExecuteLogList";
	   
   }

}
