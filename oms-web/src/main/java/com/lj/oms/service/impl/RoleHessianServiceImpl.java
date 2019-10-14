package com.lj.oms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.oms.entity.sys.Role;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.RoleHessianService;
import com.lj.oms.service.sys.SystemService;

/**
 * 开放角色接口实现类
 * @author wo510
 *
 */
@Service
public class RoleHessianServiceImpl implements RoleHessianService {

	@Autowired
    private SystemService systemService;

	@Override
	public List<Role> findByUserId(String userId) {
		Role role = new Role();
		role.setUser(new User(userId));
		return systemService.findRole(role);
	}

	

}
