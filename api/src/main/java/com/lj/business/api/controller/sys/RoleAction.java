package com.lj.business.api.controller.sys;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.lj.base.core.util.AssertUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.oms.entity.sys.Menu;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.Role;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.ISystemService;
import com.lj.oms.service.OfficeHessianService;

/**
 * 
 * *类说明：用户权限
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年6月20日
 */
@RestController
@RequestMapping("/sys/role")
public class RoleAction extends Action {

	@Resource
	private ISystemService systemService;

	@Autowired
	private OfficeHessianService officeHessianService;

	@Resource
	private IGuidMemberService guidMemberService; // 导购服务

	/**
	 * 
	 * *方法说明：打开角色分配对话框
	 *
	 * @param role
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年6月20日
	 */
	@RequestMapping(value = "usertorole.do")
	@ResponseBody
	public Object selectUserToRole(Role role, String token) {

		String userid = getLoginUserByToken(token);

		List<Office> rs = officeHessianService.findOfficeListByUserId(userid);

		return rs.stream().map(Office -> {
			Map<String, Object> temp = Maps.newHashMap();
			temp.put("id", Office.getId());
			temp.put("name", Office.getName());
//			temp.put("pId", Office.getParent() == null ? "0" : Office.getParent().getId());
			temp.put("pId", Office.getParentId());
			return temp;
		}).collect(Collectors.toList());
	}

	/**
	 * 
	 * *方法说明：根据部门编号获取用户列表
	 *
	 * @param officeId
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年6月20日
	 */
	@RequestMapping(value = "users.do")
	@ResponseBody
	public Object users(String officeId, String token) {

		AssertUtils.notAllNullAndEmpty(officeId, "部门ID不能为空");
		AssertUtils.notAllNullAndEmpty(token, "Token不能为空");

		String userid = getLoginUserByToken(token);

		User user = new User();
		user.setOffice(new Office(officeId));
		user.setId(userid);
		user.setHasGetApi(true);

		return systemService.findUser(user).stream().map(e -> {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", 0);
			map.put("name", e.getName());
			map.put("officeName", e.getOffice().getName());
			map.put("officeId", e.getOffice().getId());
			return map;
		}).collect(Collectors.toList());
	}
	
	/**
	 * 权限列表
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "findCurrentRole.do")
	@ResponseBody
	public GeneralResponse findCurrentRole(String token) {
		
		String userid = getLoginUserByToken(token);
		
		List<Menu> menuList = systemService.findRefreshMenuByUserid(userid);
		
		List<String> rsList = menuList.stream().map(ac -> ac.getPermission()).distinct().collect(Collectors.toList());
		
		return GeneralResponse.generateSuccessResponse(rsList);
	}

}
