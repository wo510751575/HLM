package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.business.member.emus.Gender;
import com.lj.business.member.emus.MemerSourceType;
import com.lj.oms.utils.excel.annotation.ExcelField;
import com.lj.oms.utils.excel.fieldtype.GenderType;
import com.lj.oms.utils.excel.fieldtype.MemerSrcType;

/**
 * 
 * 
 * 类说明：客户导出DTO
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年8月10日
 */
public class EMemberExportDto implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -8475436664919196432L;

	
	 /**
     * 客户名称
     */
	@ExcelField(title="客户名称", align=0, sort=10)
    private String memberName;
	/**
     * 昵称_微信 .
     */
	@ExcelField(title="微信昵称", align=0, sort=30)
    private String nickNameWx;
	 /**
     * 手机
     */
	@ExcelField(title="手机", align=0, sort=40)
    private String mobile;
	/**
     * 性别
		MALE("男"),
		FEMALE("女");
     */
	@ExcelField(title="性别", align=0, sort=50,fieldType=GenderType.class)
    private Gender sex;
	
	/**
	   *所在地区
	   */
//	@ExcelField(title="所在地区", align=0, sort=80, dictType="erp_dict_1")
	@ExcelField(title="所在地区", align=0, sort=80)
	private String areaCode;
	
	/**
     * 客户来源
     */
	@ExcelField(title="客户来源", align=0, sort=110,fieldType=MemerSrcType.class)
    private MemerSourceType memberSrc;
    /**
	 * 录入时间
	 */
	@ExcelField(title="录入时间", align=2, sort=150)
	private String createDate;
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getNickNameWx() {
		return nickNameWx;
	}
	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Gender getSex() {
		return sex;
	}
	public void setSex(Gender sex) {
		this.sex = sex;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public MemerSourceType getMemberSrc() {
		return memberSrc;
	}
	public void setMemberSrc(MemerSourceType memberSrc) {
		this.memberSrc = memberSrc;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EMemberExportDto [memberName=");
		builder.append(memberName);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", sex=");
		builder.append(sex);
//		builder.append(", shopName=");
//		builder.append(shopName);
		builder.append(", areaCode=");
		builder.append(areaCode);
		builder.append(", memberSrc=");
		builder.append(memberSrc);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}
	
}
