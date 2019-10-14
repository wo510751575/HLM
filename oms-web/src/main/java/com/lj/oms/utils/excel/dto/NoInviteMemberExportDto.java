package com.lj.oms.utils.excel.dto;

import java.io.Serializable;
import java.util.Date;

import com.lj.business.member.emus.Gender;
import com.lj.oms.utils.excel.annotation.ExcelField;
import com.lj.oms.utils.excel.fieldtype.GenderType;

/**
 * 
 * 
 * 类说明：非邀约型 客户导出DTO
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
public class NoInviteMemberExportDto implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -8475436664919196432L;

	
	 /**
     * 客户名称
     */
	@ExcelField(title="客户名称", align=0, sort=10)
    private String memberName;
	/** 微信OpenId. */
	@ExcelField(title="微信OpenId", align=0, sort=20)
    private String wxOpenId;
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
	@ExcelField(title="所在地区", align=0, sort=80)
	private String areaCode;
	
    /** 成单次数. */
	@ExcelField(title="成单次数", align=3, sort=140)
    private Integer successNum;
	/**
	   1.导购扫码添加
       2.客户扫码添加
       3.导购手动新增
       4.微信自动导入
	 */
	@ExcelField(title="添加方式", align=0, sort=150)
	private String addTypeStr;
	/**
	 * 客户标签
	 */
	@ExcelField(title="标签", align=0, sort=160)
	private String title;
	
	/**
	 * 客户分组
	 */
	@ExcelField(title="分组", align=0, sort=170)
	private String pmTypeName;
	
	/**
	 * 所属导购
	 */
	@ExcelField(title="所属导购", align=0, sort=180)
	private String memberNameGm;
	
    /**
	 * 录入时间
	 */
	@ExcelField(title="录入时间", align=0, sort=190)
	private Date createDate;
	
	
	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}
	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public String getWxOpenId() {
        return wxOpenId;
    }
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }
    /**
	 * @return the nickNameWx
	 */
	public String getNickNameWx() {
		return nickNameWx;
	}
	/**
	 * @param nickNameWx the nickNameWx to set
	 */
	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * @return the successNum
	 */
	public Integer getSuccessNum() {
		return successNum;
	}
	/**
	 * @param successNum the successNum to set
	 */
	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the sex
	 */
	public Gender getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(Gender sex) {
		this.sex = sex;
	}
	/**
	1.导购扫码添加
    2.客户扫码添加
    3.导购手动新增
    4.微信自动导入
	 */
	public String getAddTypeStr() {
		return addTypeStr;
	}
	/**
	1.导购扫码添加
    2.客户扫码添加
    3.导购手动新增
    4.微信自动导入
	 */
	public void setAddTypeStr(String addTypeStr) {
		this.addTypeStr = addTypeStr;
	}
	/**
	 * 标签
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 标签
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 分组
	 */
	public String getPmTypeName() {
		return pmTypeName;
	}
	/**
	 * 分组
	 */
	public void setPmTypeName(String pmTypeName) {
		this.pmTypeName = pmTypeName;
	}
	/**
	 * 所属导购
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}
	/**
	 * 所属导购
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}
	
}
