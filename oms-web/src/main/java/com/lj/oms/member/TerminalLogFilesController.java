package com.lj.oms.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.oms.common.BaseController;
import com.caucho.hessian.client.HessianProxyFactory;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.DateUtils;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesPage;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesPageReturn;
import com.lj.business.member.service.ITerminalLogFilesService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.cc.service.ISystemInfoService;
import com.lj.distributecache.RedisCache;
import com.lj.oms.utils.CcUtils;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：终端日志文件Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年12月27日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/terminalLogFiles")
public class TerminalLogFilesController  extends BaseController{
   
	@Resource
	private  ITerminalLogFilesService terminalLogFilesService;


	@Resource 
	private ISystemInfoService systemInfo;
    
	@Resource 
	private RedisCache redisCache;
	
	@Autowired 
	ICommonService commonService;
	/**
	 * 
	 *
	 * 方法说明：查询终端日志列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findTerminalLogFilesPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:terminalLogFiles:view")
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindTerminalLogFilesPage findTerminalLogFilesPage){
		try {
			if(pageNo!=null){
				findTerminalLogFilesPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findTerminalLogFilesPage.setLimit(pageSize);
			}
			findTerminalLogFilesPage.setStartTime(DateUtils.getDateByFirstSeconds(findTerminalLogFilesPage.getStartTime()));
			findTerminalLogFilesPage.setEndTime(DateUtils.getDateByLastSeconds(findTerminalLogFilesPage.getEndTime()));
			if(!UserUtils.getUser().isAdmin()){
				findTerminalLogFilesPage.setMerchantNo(UserUtils.getMerchantNo());
			}
			Page<FindTerminalLogFilesPageReturn> pages=terminalLogFilesService.findTerminalLogFilesPage(findTerminalLogFilesPage);
			List<FindTerminalLogFilesPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindTerminalLogFilesPageReturn> page 
			= new com.ape.common.persistence.Page<FindTerminalLogFilesPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findTerminalLogFilesPage", findTerminalLogFilesPage);
		} catch (Exception e) {
			logger.error("终端日志文件信息获取异常！", e);
			
		}
		return "modules/member/terminalLogFilesList";
	}
	
	/**
	 * 
	 *
	 * 方法说明：向终端下发上传日志文件指令
	 *
	 * @param noWx
	 * @return resultMap
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月28日
	 *
	 */
	@RequestMapping(value ="getLogFiles" )
	@ResponseBody
	public Map<String,Object> getLogFiles(String imei){
		String lastFileDate = terminalLogFilesService.getLastFileDate(imei);
		ICommonService basic = commonService.getHessianCommonService(imei);
		basic.sendUploadTerminalLogFilesMessage(imei, lastFileDate);
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put("state", Boolean.TRUE);
		resultMap.put("msg", "已向终端下发上传日志指令，请稍候查询！");
		return resultMap;
	}
	

}
