package com.lj.oms.service;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.oms.entity.dto.hx.HxGuidDto;
import com.lj.oms.entity.sys.Menu;
import com.lj.oms.entity.sys.User;

/**
 * 用户开放接口
 * @author wo510
 *
 */
public interface UserHessianService {
	
	/**
	 * 根据shopNo查询终端所属机构
	 * @param shopNo
	 * @return
	 */
	User findByUserId(String userId);
	
	/**
	 *   根据门店及角色查找用户。
	 * @param user
	 * @return
	 * @throws TsfaServiceException
	 * @author 刘红艳 2019.03.11  add by 焕新
	 */
	public List<HxGuidDto> findUsersByRoleEnname(HxGuidDto user) throws TsfaServiceException;

	String findLoginNameByParentOfficeId(String officeId);
	/**
	 * 查询用户焕新H5的权限
	 * @param memberNoGuid 员工ID
	 * @return
	 */
	public List<Menu> findMenuList(String memberNoGuid)  throws TsfaServiceException;

	/**
	 * 创建oms免密登录的token
	 * @param user id非空
	 * @return
	 * @throws TsfaServiceException
	 */
	public String createOmsToken(User user) throws TsfaServiceException;
}
