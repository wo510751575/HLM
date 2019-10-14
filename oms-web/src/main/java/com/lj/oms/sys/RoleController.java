package com.lj.oms.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.ape.common.persistence.Page;
import com.ape.common.utils.Collections3;
import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.Role;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.service.sys.SystemService;
import com.lj.oms.utils.UserUtils;

/**
 * 角色Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/role")
public class RoleController  extends BaseController{
	private static final String TRUE="true";
	private static final String FALSE="false";
	private static final String LIST="modules/sys/roleList";
	private static final String FORM="modules/sys/roleForm";
	private static final String REDIRECT_LIST="redirect:" +Global.getAdminPath() + "/sys/role/?repage";
	
	private static final String FORM_ROLE_ASSIGN="modules/sys/roleAssign";
	private static final String REDIRECT_FORM_ROLE_ASSIGN="redirect:" +Global.getAdminPath() + "/sys/role/assign";
	private static final String SELECT_USER_TO_ROLE="modules/sys/selectUserToRole";
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute("role")
	public Role get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getRole(id);
		}else{
			return new Role();
		}
	}
	
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = {"list", ""})
	public String list(Role role, HttpServletRequest request, HttpServletResponse response,Model model) {
//		List<Role> list = systemService.findAllRole();
//		model.addAttribute("list", list);
		
		Page<Role> page = systemService.findRole(new Page<Role>(request, response), role);
		page.initialize();
		model.addAttribute("page", page);
		model.addAttribute("role", role);
		return LIST;
	}

		
		
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "form")
	public String form(Role role, Model model) {
		if (role.getOffice()==null){
			role.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("role", role);
		model.addAttribute("menuList", systemService.findAllMenu());
		model.addAttribute("officeList", officeService.findAll());
		return FORM;
	}
	
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "save")
	public String save(Role role, Model model, RedirectAttributes redirectAttributes) {
		if(!UserUtils.getUser().isAdmin()&&role.getSysData().equals(Global.YES)){
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能修改此数据！");
			return REDIRECT_LIST;
		}
//		if (!beanValidator(model, role)){
//			return form(role, model);
//		}
		if (!TRUE.equals(checkName(role.getOldName(), role.getName()))){
			addMessage(model, "保存角色'" + role.getName() + "'失败, 角色名已存在");
			return form(role, model);
		}
//		if (!TRUE.equals(checkEnname(role.getOldEnname(), role.getEnname()))){
//			addMessage(model, "保存角色'" + role.getName() + "'失败, 英文名已存在");
//			return form(role, model);
//		}
		systemService.saveRole(role);
		addMessage(redirectAttributes, "保存角色'" + role.getName() + "'成功");
		return REDIRECT_LIST;
	}
	
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "delete")
	public String delete(Role role, RedirectAttributes redirectAttributes) {
		if(!UserUtils.getUser().isAdmin() && role.getSysData().equals(Global.YES)){
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能修改此数据！");
			return REDIRECT_LIST;
		}
		systemService.deleteRole(role);
		addMessage(redirectAttributes, "删除角色成功");
		return REDIRECT_LIST;
	}
	
	/**
	 * 角色分配页面
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "assign")
	public String assign(Role role, Model model) {
		List<User> userList = systemService.findUser(new User(new Role(role.getId())));
		model.addAttribute("userList", userList);
		return FORM_ROLE_ASSIGN;
	}
	
	/**
	 * 角色分配 -- 打开角色分配对话框
	 * @param role
	 * @param model
	 * @param checkUser 是否选择用户：yes ：2019-05-24 新增
	 * @return
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "usertorole")
	public String selectUserToRole(Role role, Model model, String checkUser) {
		List<User> userList = systemService.findUser(new User(new Role(role.getId())));
		model.addAttribute("role", role);
		
		
		model.addAttribute("checkUser", checkUser);

		model.addAttribute("userList", userList);
		model.addAttribute("selectIds", Collections3.extractToString(userList, "name", ","));
		
		model.addAttribute("officeList", officeService.findAll());
		return SELECT_USER_TO_ROLE;
	}
	
	/**
	 * 角色分配 -- 根据部门编号获取用户列表
	 * @param officeId
	 * @param response
	 * @return
	 */
	@RequiresPermissions("sys:role:view")
	@ResponseBody
	@RequestMapping(value = "users")
	public List<Map<String, Object>> users(String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = new User();
		user.setOffice(new Office(officeId));
		Page<User> page = systemService.findUser(new Page<User>(1, -1), user);
		for (User e : page.getList()) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", 0);
			map.put("name", e.getName());
			mapList.add(map);			
		}
		return mapList;
	}
	
	/**
	 * 角色分配 -- 从角色中移除用户
	 * @param userId
	 * @param roleId
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "outrole")
	public String outrole(String userId, String roleId, RedirectAttributes redirectAttributes) {
		Role role = systemService.getRole(roleId);
		User user = systemService.getUser(userId);
		if (UserUtils.getUser().getId().equals(userId)) {
			addMessage(redirectAttributes, "无法从角色【" + role.getName() + "】中移除用户【" + user.getName() + "】自己！");
		}else {
			if (user.getRoleList().size() <= 1){
				addMessage(redirectAttributes, "用户【" + user.getName() + "】从角色【" + role.getName() + "】中移除失败！这已经是该用户的唯一角色，不能移除。");
			}else{
				Boolean flag = systemService.outUserInRole(role, user);
				if (!flag) {
					addMessage(redirectAttributes, "用户【" + user.getName() + "】从角色【" + role.getName() + "】中移除失败！");
				}else {
					addMessage(redirectAttributes, "用户【" + user.getName() + "】从角色【" + role.getName() + "】中移除成功！");
				}
			}		
		}
		return REDIRECT_FORM_ROLE_ASSIGN+"id="+role.getId();
	}
	
	/**
	 * 角色分配
	 * @param role
	 * @param idsArr
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "assignrole")
	public String assignRole(Role role, String[] idsArr, RedirectAttributes redirectAttributes) {
		StringBuilder msg = new StringBuilder();
		int newNum = 0;
		for (int i = 0; i < idsArr.length; i++) {
			User user = systemService.assignUserToRole(role, systemService.getUser(idsArr[i]));
			if (null != user) {
				msg.append("<br/>新增用户【" + user.getName() + "】到角色【" + role.getName() + "】！");
				newNum++;
			}
		}
		addMessage(redirectAttributes, "已成功分配 "+newNum+" 个用户"+msg);
		return REDIRECT_FORM_ROLE_ASSIGN+"id="+role.getId();
	}

	/**
	 * 验证角色名是否有效
	 * @param oldName
	 * @param name
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String name) {
		if (name!=null && name.equals(oldName)) {
			return TRUE;
		} else if (name!=null && systemService.getRoleByName(name) == null) {
			return TRUE;
		}
		return FALSE;
	}

	/**
	 * 验证角色英文名是否有效
	 * @param oldName
	 * @param name
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkEnname")
	public String checkEnname(String oldEnname, String enname) {
		if (enname!=null && enname.equals(oldEnname)) {
			return TRUE;
		} else if (enname!=null && systemService.getRoleByEnname(enname) == null) {
			return TRUE;
		}
		return FALSE;
	}

}
