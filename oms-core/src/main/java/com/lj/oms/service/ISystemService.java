package com.lj.oms.service;

import java.util.List;

import com.ape.common.persistence.Page;
import com.lj.oms.entity.sys.Menu;
import com.lj.oms.entity.sys.Role;
import com.lj.oms.entity.sys.User;

public interface ISystemService {

	// 用户相关接口
	User getUser(String id);

	User getUserByLoginName(String loginName);
	
	Page<User> findUser(Page<User> page, User user);
	
	List<User> findUser(User user);

	List<User> findUserByOfficeId(String officeId);
	
	List<User> findUserByOfficeIdNoCache(String officeId);
	
	String saveUser(User user);
	List<User> findAllList(User user);
	
	void updateUserInfo(User user);
	
	void deleteUser(User user);
	
	void updatePasswordById(String id, String loginName, String newPassword);
	
	void updateUserLoginInfo(User user);
	
	// 角色相关接口
	Page<Role> findRole(Page<Role> page, Role role);
	
	List<Role> findAllRole();
	
	void saveRole(Role role);

	void deleteRole(Role role);
	
	Boolean outUserInRole(Role role, User user);
	
	User assignUserToRole(Role role, User user);

	
	// 菜单相关接口
	Menu getMenu(String id);

	List<Menu> findAllMenu();
	
	List<Menu> findMenuByUserid(String userid);
	
	/**
	 * 
	 * *方法说明：获取当前角色权限
	 *
	 * @param userid
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月15日
	 */
	List<Menu> findRefreshMenuByUserid(String userid);
	
	/**
	 * 
	 * *方法说明：验证权限标志
	 *
	 * @param userid
	 * @param permission
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	boolean verifyMenuPermission(String userid, String permission);
	
	void saveMenu(Menu menu);

	void updateMenuSort(Menu menu);

	void deleteMenu(Menu menu);
}
