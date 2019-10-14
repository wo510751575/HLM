package com.lj.oms.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ape.common.config.Global;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.base.core.util.StringUtils;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfoPage;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfoPageReturn;
import com.lj.business.member.emus.FitUpHouseType;
import com.lj.business.member.emus.FitUpStyle;
import com.lj.business.member.service.IFitUpInfoService;



@Controller
@RequestMapping(value="${adminPath}/fitUp")
public class FitUpController extends BaseController{
	
	@Resource
	private IFitUpInfoService fitUpInfoService;
	
	public static final String LIST = "modules/fitUp/list";
	
	
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo ,Integer pageSize,FindFitUpInfoPage findFitUpInfoPage){
		try {
			if(pageNo != null){
				findFitUpInfoPage.setStart((pageNo -1)*pageSize);
			}
			if(pageSize != null){
				findFitUpInfoPage.setLimit(pageSize);
			}
			
			//initDate(findJobseekerPage);
			
			findFitUpInfoPage.setSortDir(PageSortType.asc);
			Page<FindFitUpInfoPageReturn> pages = fitUpInfoService.findFitUpInfoPage(findFitUpInfoPage);
			for (FindFitUpInfoPageReturn findFitUpInfoPageReturn : pages.getRows()) {
				if(!StringUtils.isEmpty(findFitUpInfoPageReturn.getImgAddr())){
					String imgAddrList [] = findFitUpInfoPageReturn.getImgAddr().split(",");
					findFitUpInfoPageReturn.setImgAddrList(imgAddrList);
				}
			}
			List<FindFitUpInfoPageReturn> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindFitUpInfoPageReturn> page=
			new com.ape.common.persistence.Page<FindFitUpInfoPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			
			model.addAttribute("page", page);
			model.addAttribute("param", findFitUpInfoPage);
			//面试状态
			model.addAttribute("fitUpHouseType",FitUpHouseType.values());
			model.addAttribute("fitUpStyle",FitUpStyle.values() );
		} catch (Exception e) {
			logger.error("查询装修需求信息错误",e);
		}
		return LIST;
		
	}
	
	/*private void initDate(JobseekerDto jobseekerDto) {
		if(jobseekerDto.getStartTime()!=null){
			jobseekerDto.setStartTime(DateUtils.getDateByFirstSeconds(jobseekerDto.getStartTime()));
		}
		if(jobseekerDto.getEndTime()!=null){
			jobseekerDto.setEndTime(DateUtils.getDateByLastSeconds(jobseekerDto.getEndTime())); 
		}
	}*/
      

}
