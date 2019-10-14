package com.lj.oms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ape.common.utils.StringUtils;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.oms.dao.sys.MenuDao;
import com.lj.oms.dao.sys.UserDao;
import com.lj.oms.entity.dto.hx.HxGuidDto;
import com.lj.oms.entity.sys.Menu;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.UserHessianService;
import com.lj.oms.service.sys.SystemService;

/**
 * 开放用户接口实现类
 * 
 * @author wo510
 *
 */
@Service
public class UserHessianServiceImpl implements UserHessianService {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserHessianServiceImpl.class);
	
	@Autowired
	private SystemService systemService;
	@Autowired
    private UserDao userDao;

	@Autowired
	private MenuDao menuDao;
	
	/**进入焕新菜单id*/
	private static final String HX_MENU_ID="ebba8b7c400d46f78c220f702a17ba99";
	
	@Autowired
	private UserCacheService userCacheService;
	
	/**
	 * 获取用户信息根据ID
	 */
	@Override
	public User findByUserId(String userId) {
		return systemService.getUser(userId);
	}
	
	/**
	 * 同步上级机构获取下级所有用户一次返回
	 * @return
	 */
	public String findLoginNameByParentOfficeId(String officeId){
		return userDao.findLoginNameByParentOfficeId(officeId);
	}

	@Override
	public List<HxGuidDto> findUsersByRoleEnname(HxGuidDto user) throws TsfaServiceException {
		List<HxGuidDto> users = userDao.findUsersByRoleEnname(user);
		return users;
	}

	/* (non-Javadoc)
	 * @see com.lj.oms.service.UserHessianService#findMenuList(java.lang.String)
	 */
	@Override
	public List<Menu> findMenuList(String memberNoGuid) throws TsfaServiceException {
		List<Menu> menuList = null;
		Menu m = new Menu();
		m.setUserId(memberNoGuid);
		Menu parent=new Menu();
		parent.setId(HX_MENU_ID);
		m.setParent(parent);
		menuList = menuDao.findByUserId(m);
		
		return menuList;
	}

	@Override
	public String createOmsToken(User user) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(user.getId(), "员工编号不能为空");
		
		String token = userCacheService.getTokenByUserId(user.getId());
		logger.error("用户已存在，返回原Token={}",token);
		if(StringUtils.isEmpty(token)) {
			User userGen = findByUserId(user.getId());
			token = userCacheService.addUserToken(userGen);
			logger.error("原Token为空，生成新token",token);
		}
		return token;
	}

}
