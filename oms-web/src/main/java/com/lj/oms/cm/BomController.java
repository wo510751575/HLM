package com.lj.oms.cm;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.cm.dto.EditBomDto;
import com.lj.business.cm.dto.FindBomPageDto;
import com.lj.business.cm.service.IBomService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：产品服务类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月14日
 */
@Controller
@RequestMapping(value = "${adminPath}/business/bom")
public class BomController  extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(BomController.class);
	
	/**
	 * 产品服务
	 */
	@Resource
	private  IBomService bomService;
	
	

	/**
	 * 
	 *
	 * 方法说明：产品查询
	 *
	 * @param model
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月22日
	 *
	 */
	@RequiresPermissions("business:bom:view")
	@RequestMapping(value = {"bomList", ""})
	public String bomList( Model model,FindBomPageDto findBomPageDto,Integer pageNo,Integer pageSize) {
		try {
			if(pageNo!=null){
				findBomPageDto.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findBomPageDto.setLimit(pageSize);
			}
			Page<FindBomPageDto> pages = bomService.findBomPage(findBomPageDto);
			List<FindBomPageDto> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindBomPageDto> page=new com.ape.common.persistence.Page<FindBomPageDto>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list); 
			page.initialize();
			model.addAttribute("page",page);
		} catch (Exception e) {
			logger.error("获取产品信息错误！");
		}	
		return "modules/business/bomList";
	}
	

	/**
	 * 
	 *
	 * 方法说明：
	 *  产品表查询
	 *  
	 * @param modle
	 * @param findWorkTaskList
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月27日
	 *
	 */
	@RequiresPermissions("business:bom:edit")
	@RequestMapping(value = "form")
	public String bomForm(Model modle,String code ){
		try {
			if(code!=null){
				EditBomDto bom = bomService.selectByCode(code);
				modle.addAttribute("data",bom);
			}
		} catch (Exception e) {
			logger.error("获取产品信息错误！");
		}	
		return "modules/business/bomForm";
	}
	/**
	 * 产品表新增
	 *
	 * 方法说明：
	 *
	 * @param addWorkTaskList
	 * @param mobel
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月27日
	 *
	 */
	@RequiresPermissions("business:bom:edit")
	@RequestMapping(value = "save")
	public String save(EditBomDto bom,Model mobel ,RedirectAttributes redirectAttributes){
		try {
			//创建人
		  bom.setCreateId(UserUtils.getUser().getName());
		  bom.setCreateDate(new Date());
		  bomService.addBom(bom);
		  addMessage(redirectAttributes, "保存产品'" + bom.getBomName()+ "'成功");
		} catch (Exception e) {
			logger.error("新增产品信息错误！");
		}	
		return "redirect:" + adminPath + "/business/bom/";
	}
	
	/**
	 *
	 * 方法说明：修改产品表
	 *
	 * @param redirectAttributes
	 * @param model
	 * @param updateWorkTaskList
	 * @return
	 *
	 * @author	邹磊 CreateDate: 2017年6月27日
	 *
	 */
	@RequiresPermissions("baseConfig:exGuider:edit")
	@RequestMapping(value = "edit")
	public String edit(RedirectAttributes redirectAttributes,Model  model,EditBomDto bom){
		try {
			bom.setCreateId(UserUtils.getUser().getName());
			bom.setCreateDate(new Date());
			bomService.editBom(bom);
			addMessage(redirectAttributes, "保存产品'" + bom.getBomName()+ "'成功");
		} catch (Exception e) {
			logger.error("保存产品信息错误！");
		}
		return "redirect:" + adminPath + "/business/bom/";
	}

}
