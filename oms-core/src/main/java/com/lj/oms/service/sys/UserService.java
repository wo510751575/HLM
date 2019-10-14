package com.lj.oms.service.sys;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lj.oms.dao.sys.UserDao;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.CrudService;

/**
 * 用户Service
 */
@Service
@Transactional(readOnly = true)
public class UserService extends CrudService<UserDao, User> {
	
	
	/**
	 * 同步上级机构获取下级所有用户一次返回
	 * @return
	 */
	public String findLoginNameByParentOfficeId(String officeId){
		return dao.findLoginNameByParentOfficeId(officeId);
	}
	

}
