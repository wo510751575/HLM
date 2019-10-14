package com.lj.oms.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ape.common.persistence.CrudDao;
import com.ape.common.persistence.annotation.MyBatisDao;
import com.lj.oms.entity.dto.hx.HxGuidDto;
import com.lj.oms.entity.sys.User;

/**
 * 用户DAO接口
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {
	
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(User user);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	
	/**
     * 更新用户信息oms用户，lss二期bug修复
     * @param user
     * @return
     */
    public int updateUserInfo2(User user);

	/**
	 * 
	 *
	 * 方法说明：根据company.id获取所属用户
	 *
	 * @param user
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年12月15日
	 *
	 */
	public List<User> findMerchantNos(User user);
	/**
	 * 
	 *
	 * 方法说明：通过销售机构获取用户列表
	 *
	 * @param code
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年12月19日
	 *
	 */
	public List<User> findUserByOfficeCode(@Param("officeCode") String officeCode);
	/**
	 * 同步上级机构获取下级所有用户一次返回
	 * @param officeId
	 * @return
	 */
	String findLoginNameByParentOfficeId(@Param("officeId") String officeId);


	/**
	 * 
	 *
	 * 方法说明：根据company.id获取所属company.id的下级
	 *
	 * @param user
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年12月15日
	 *
	 */
	public List<HxGuidDto> findUsersByRoleEnname(HxGuidDto hxGuidDto);

}
