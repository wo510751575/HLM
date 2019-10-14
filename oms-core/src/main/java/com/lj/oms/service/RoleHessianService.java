package com.lj.oms.service;

import java.util.List;

import com.lj.oms.entity.sys.Role;

/**
 * 角色开放接口
 * @author wo510
 *
 */
public interface RoleHessianService {
	
	/**
	 * 根据用户获取角色列表
	 * @param userId
	 * @return
	 */
	List<Role> findByUserId(String userId);
	
}
