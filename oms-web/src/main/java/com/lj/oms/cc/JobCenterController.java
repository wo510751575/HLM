package com.lj.oms.cc;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.IPage;
import com.lj.cc.domain.JobCenter;
import com.lj.cc.dto.FindJobCenter;
import com.lj.cc.service.IJobMgrService;

/**
 * 
 * 
 * 类说明：定时任务
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
@RequestMapping(value = "${adminPath}/cc/jobCenter")
public class JobCenterController {
	private static final Logger logger = LoggerFactory.getLogger(JobCenterController.class);
	
	@Resource
	private IJobMgrService jobMgrService;
	
	@RequiresPermissions("cc:jobCenter:view")
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindJobCenter center){
		try {
			if(pageNo!=null){
				center.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				center.setLimit(pageSize);
			}
			IPage<JobCenter> pages=jobMgrService.queryJobList(center);
			List<JobCenter> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<JobCenter> page=new com.ape.common.persistence.Page<JobCenter>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("获取定时任务信息失败！");
		}
		   return "modules/cc/jobCenterList";
	}

}
