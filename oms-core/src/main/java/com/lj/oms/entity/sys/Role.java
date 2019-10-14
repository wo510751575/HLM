package com.lj.oms.entity.sys;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ape.common.config.Global;
import com.ape.common.utils.IdGen;
import com.google.common.collect.Lists;
import com.lj.oms.entity.DataEntity;
import com.lj.oms.utils.UserUtils;

/**
 * 角色Entity
 */
public class Role extends DataEntity<Role> {
	
	private static final long serialVersionUID = 1L;
	private Office office;	// 归属机构
	private String name; 	// 角色名称
	private String enname;	// 英文名称
	private String roleType;// 权限类型
	private String dataScope;// 数据范围
	
	private String oldName; 	// 原角色名称
	private String oldEnname;	// 原英文名称
	private String sysData; 		//是否是系统数据
	private String useable; 		//是否是可用
	
	private User user;		// 根据用户ID查询角色列表

	private List<Menu> menuList = Lists.newArrayList(); // 拥有菜单列表
	private List<Office> officeList = Lists.newArrayList(); // 按明细设置数据范围

	// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
	/**
	 * 1：所有数据；
	 */
	public static final String DATA_SCOPE_ALL = "1";
	/**
	 * 2：所在公司及以下数据；
	 */
	public static final String DATA_SCOPE_COMPANY_AND_CHILD = "2";
	/**
	 * 3：所在公司数据；
	 */
	public static final String DATA_SCOPE_COMPANY = "3";
	/**
	 * 4：所在部门及以下数据；
	 */
	public static final String DATA_SCOPE_OFFICE_AND_CHILD = "4";
	/**
	 * 5：所在部门数据；
	 */
	public static final String DATA_SCOPE_OFFICE = "5";
	/**
	 * 8：仅本人数据；
	 */
	public static final String DATA_SCOPE_SELF = "8";
	/**
	 * 9：按明细设置
	 */
	public static final String DATA_SCOPE_CUSTOM = "9";
	/**
	 * 店员角色固定英文名
	 */
	public static final String SYS_CLERK = "SYS_CLERK";
	/**
	 * 店长角色固定英文名
	 */
	public static final String SYS_SHOP_MANAGER = "SYS_SHOP_MANAGER";
	
	/**模板角色ID：商户管理员*/
	public static final String ROLEID_MERCHANT_MANAGER="17736ea1cd674c05b717a390d6eb9a60";
	
	/**商户管理员*/
	public static final String ENNAME_MERCHANT_MANAGER="SYS_MERCHANT_MANAGER";
	
	/**医生*/
	public static final String ENNAME_SHOP_DOCTOR="SYS_SHOP_DOCTOR";
	
	/**咨询师*/
	public static final String ENNAME_SHOP_ADVISORY="SYS_SHOP_ADVISORY";
	
	/**护士*/
	public static final String ENNAME_SHOP_NURSE="SYS_SHOP_NURSE";
	
	/**模板角色ID：医生*/
	public static final String ROLEID_SHOP_DOCTOR="ca997cf82de94d2a840e9c7b1e7892d9";
	
	/**模板角色ID：咨询师*/
	public static final String ROLEID_SHOP_ADVISORY="9f752dae4a8a4699a618e8350f2aa52c";
	
	/**模板角色ID：护士*/
	public static final String ROLEID_SHOP_NURSE="f0a645a4a2884af49c3d60be308a9a4b";
	
	public Role() {
		super();
		this.dataScope = DATA_SCOPE_SELF;
		this.useable=Global.YES;
	}
	
	public Role(String id){
		super(id);
	}
	
	public Role(User user) {
		this();
		this.user = user;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public String getSysData() {
		return sysData;
	}

	public void setSysData(String sysData) {
		this.sysData = sysData;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}
	
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getOldEnname() {
		return oldEnname;
	}

	public void setOldEnname(String oldEnname) {
		this.oldEnname = oldEnname;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<String> getMenuIdList() {
		List<String> menuIdList = Lists.newArrayList();
		for (Menu menu : menuList) {
			menuIdList.add(menu.getId());
		}
		return menuIdList;
	}

	public void setMenuIdList(List<String> menuIdList) {
		menuList = Lists.newArrayList();
		for (String menuId : menuIdList) {
			Menu menu = new Menu();
			menu.setId(menuId);
			menuList.add(menu);
		}
	}

	public String getMenuIds() {
		return StringUtils.join(getMenuIdList(), ",");
	}
	
	public void setMenuIds(String menuIds) {
		menuList = Lists.newArrayList();
		if (menuIds != null){
			String[] ids = StringUtils.split(menuIds, ",");
			setMenuIdList(Lists.newArrayList(ids));
		}
	}
	
	public List<Office> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<Office> officeList) {
		this.officeList = officeList;
	}

	public List<String> getOfficeIdList() {
		List<String> officeIdList = Lists.newArrayList();
		for (Office office : officeList) {
			officeIdList.add(office.getId());
		}
		return officeIdList;
	}

	public void setOfficeIdList(List<String> officeIdList) {
		officeList = Lists.newArrayList();
		for (String officeId : officeIdList) {
			Office office = new Office();
			office.setId(officeId);
			officeList.add(office);
		}
	}

	public String getOfficeIds() {
		return StringUtils.join(getOfficeIdList(), ",");
	}
	
	public void setOfficeIds(String officeIds) {
		officeList = Lists.newArrayList();
		if (officeIds != null){
			String[] ids = StringUtils.split(officeIds, ",");
			setOfficeIdList(Lists.newArrayList(ids));
		}
	}
	
	/**
	 * 获取权限字符串列表
	 */
	public List<String> getPermissions() {
		List<String> permissions = Lists.newArrayList();
		for (Menu menu : menuList) {
			if (menu.getPermission()!=null && !"".equals(menu.getPermission())){
				permissions.add(menu.getPermission());
			}
		}
		return permissions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public void preInsert() {
		super.preInsert();
		if (!this.isNewRecord){
			setId(IdGen.uuid());
		}
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
			this.createBy = user;
		}
	}
	
	@Override
	public void preUpdate(){
		super.preUpdate();
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
		}
	}
}
