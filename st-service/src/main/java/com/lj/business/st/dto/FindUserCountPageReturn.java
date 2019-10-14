package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年5月31日上午11:41:01
 */
public class FindUserCountPageReturn implements Serializable {

	private static final long serialVersionUID = 3896424249284955083L;
	private String  code;	//用户id
	private String  userId;	//用户id
	private String  name;	//姓名
	private String  company;//所属公司	
	private String  office;	//所属部门
	private String  shopWx;	//所属终端
	private int  totalMember;//客户总量
	private int  newMember;	//新增客户量
	private int  totalMemberPhone;	//手机号码客户量
	private int  newMemberPhone;	//新增手机号码客户量
	private int  chatCount;	//聊天数量
	private int  unChatCount;	//未聊天数量
	private int  chatTotal;	//聊天条数
	private Date createTime;
	private String  remark;	//备注
	private String  merchantNo;	//商户号
	
	
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
