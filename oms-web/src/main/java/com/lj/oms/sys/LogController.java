package com.lj.oms.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ape.common.persistence.Page;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Log;
import com.lj.oms.service.sys.LogService;
import com.lj.oms.utils.UserUtils;

/**
 * 日志Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class LogController extends BaseController{

	@Autowired
	private LogService logService;
	
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = {"list", ""})
	public String list(Log log, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(!UserUtils.getUser().isAdmin()){
			log.setCreateBy(UserUtils.getUser());
		}
        Page<Log> page = logService.findPage(new Page<Log>(request, response), log); 
        model.addAttribute("page", page);
		return "modules/sys/logList";
	}

}
