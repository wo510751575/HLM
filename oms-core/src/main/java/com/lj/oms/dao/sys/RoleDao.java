package com.lj.oms.dao.sys;

import java.util.List;

import com.ape.common.persistence.CrudDao;
import com.ape.common.persistence.annotation.MyBatisDao;
import com.lj.oms.entity.dto.hx.CopyMenuDto;
import com.lj.oms.entity.sys.Role;

/**
 * 角色DAO接口
 */
@MyBatisDao
public interface RoleDao extends CrudDao<Role> {

	public Role getByName(Role role);
	
	public Role getByEnname(Role role);

	/**
	 * 维护角色与菜单权限关系
	 * @param role
	 * @return
	 */
	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);
	
	/**
	 * 维护角色与公司部门关系
	 * @param role
	 * @return
	 */
	public int deleteRoleOffice(Role role);

	public int insertRoleOffice(Role role);

	/**
	 *@Desc 
	 *@param id
	 *@return
	 *@return List<Role>
	 *@author 贾光朝
	 *@createDate 2019年5月24日上午10:38:04
	 */
	public List<Role> findRolesByUserId(String id);

	/**根据角色模板给角色赋值权限菜单 */
	public int copyRoleMenu(CopyMenuDto dto);
	
	
}
