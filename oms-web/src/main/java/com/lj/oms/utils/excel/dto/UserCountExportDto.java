package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年6月3日下午3:23:15
 */
public class UserCountExportDto implements Serializable {

	private static final long serialVersionUID = -6573780058868986456L;
	
	@ExcelField(title="员工ID", align=0, sort=10)
	private String  userId;	//员工ID
	
	@ExcelField(title="员工姓名", align=0, sort=20)
	private String  name;	//姓名
	
	@ExcelField(title="归属公司", align=0, sort=30)
	private String  company;//所属公司
	
	@ExcelField(title="归属部门", align=0, sort=40)
	private String  office;	//所属部门
	
	@ExcelField(title="所属终端", align=0, sort=50)
	private String  shopWx;	//所属终端
	
	@ExcelField(title="客户总量", align=0, sort=60)
	private int  totalMember;//客户总量
	
	@ExcelField(title="新增客户量", align=0, sort=70)
	private int  newMember;	//新增客户量
	
	@ExcelField(title="手机号码客户量", align=0, sort=80)
	private int  totalMemberPhone;	//手机号码客户量
	
	@ExcelField(title="新增手机号码客户量", align=0, sort=90)
	private int  newMemberPhone;	//新增手机号码客户量
	
	@ExcelField(title="聊天客户量", align=0, sort=100)
	private int  chatCount;	//聊天数量
	
	@ExcelField(title="未聊天客户量", align=0, sort=110)
	private int  unChatCount;	//未聊天数量
	
	@ExcelField(title="聊天条数", align=0, sort=120)
	private int  chatTotal;	//聊天条数

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getShopWx() {
		return shopWx;
	}

	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}

	public int getTotalMember() {
		return totalMember;
	}

	public void setTotalMember(int totalMember) {
		this.totalMember = totalMember;
	}

	public int getNewMember() {
		return newMember;
	}

	public void setNewMember(int newMember) {
		this.newMember = newMember;
	}

	public int getTotalMemberPhone() {
		return totalMemberPhone;
	}

	public void setTotalMemberPhone(int totalMemberPhone) {
		this.totalMemberPhone = totalMemberPhone;
	}

	public int getNewMemberPhone() {
		return newMemberPhone;
	}

	public void setNewMemberPhone(int newMemberPhone) {
		this.newMemberPhone = newMemberPhone;
	}

	public int getChatCount() {
		return chatCount;
	}

	public void setChatCount(int chatCount) {
		this.chatCount = chatCount;
	}

	public int getUnChatCount() {
		return unChatCount;
	}

	public void setUnChatCount(int unChatCount) {
		this.unChatCount = unChatCount;
	}

	public int getChatTotal() {
		return chatTotal;
	}

	public void setChatTotal(int chatTotal) {
		this.chatTotal = chatTotal;
	}
	
	
	

}
