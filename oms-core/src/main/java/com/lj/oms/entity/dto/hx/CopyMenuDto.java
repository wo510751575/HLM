/**
 * 
 */
package com.lj.oms.entity.dto.hx;

import java.io.Serializable;

/**
 * 类说明：根据角色模板给角色赋值权限菜单参数DTO。
 * 
 * <p>
 *   
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 *   
 * CreateDate:  2019年3月26日
 */
public class CopyMenuDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**新增的角色ID*/
	private String newRoleId;
	/**模板的角色ID*/
	private String templateRoleId;

	public String getNewRoleId() {
		return newRoleId;
	}

	public void setNewRoleId(String newRoleId) {
		this.newRoleId = newRoleId;
	}

	public String getTemplateRoleId() {
		return templateRoleId;
	}

	public void setTemplateRoleId(String templateRoleId) {
		this.templateRoleId = templateRoleId;
	}

	@Override
	public String toString() {
		return "CopyMenuDto [newRoleId=" + newRoleId + ", templateRoleId=" + templateRoleId + "]";
	}
	
}
