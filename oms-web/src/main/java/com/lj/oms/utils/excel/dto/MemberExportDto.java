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
public class MemberExportDto implements Serializable{

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
	 * 所属导购 .
	 */
	@ExcelField(title="所属导购", align=0, sort=60)
	private String memberNameGm;
	
	/**
	   *所在地区
	   */
//	@ExcelField(title="所在地区", align=0, sort=80, dictType="erp_dict_1")
	@ExcelField(title="所在地区", align=0, sort=80)
	private String areaCode;
	
	  /**
     * 所属终端 .
     */
	@ExcelField(title="所属终端", align=0, sort=90)
    private String bomName;
	
	/**
	 * 所在楼盘 .
	 */
	@ExcelField(title="所在楼盘 ", align=0, sort=100)
	private String houses;
	
	/**
     * 客户来源
     */
	@ExcelField(title="客户来源", align=0, sort=110,fieldType=MemerSrcType.class)
    private MemerSourceType memberSrc;
	
	 /** 跟进次数. */
	@ExcelField(title="跟进次数", align=3, sort=120)
    private Integer followNum;
    
    /** 维护次数. */
	@ExcelField(title="维护次数", align=3, sort=130)
    private Integer keepNum;
    /** 成单次数. */
	@ExcelField(title="成单次数", align=3, sort=140)
    private Integer successNum;
    /**
	 * 录入时间
	 */
	@ExcelField(title="录入时间", align=2, sort=150)
	private String createDate;
	
	 /**
     * 旺旺
     */
	@ExcelField(title="旺旺", align=0, sort=160)
    private String noWw;
	
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
	 * @return the memberNameGm
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}
	/**
	 * @param memberNameGm the memberNameGm to set
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
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
	 * @return the bomName
	 */
	public String getBomName() {
		return bomName;
	}
	/**
	 * @param bomName the bomName to set
	 */
	public void setBomName(String bomName) {
		this.bomName = bomName;
	}
	/**
	 * @return the houses
	 */
	public String getHouses() {
		return houses;
	}
	/**
	 * @param houses the houses to set
	 */
	public void setHouses(String houses) {
		this.houses = houses;
	}
	/**
	 * @return the followNum
	 */
	public Integer getFollowNum() {
		return followNum;
	}
	/**
	 * @param followNum the followNum to set
	 */
	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}
	/**
	 * @return the keepNum
	 */
	public Integer getKeepNum() {
		return keepNum;
	}
	/**
	 * @param keepNum the keepNum to set
	 */
	public void setKeepNum(Integer keepNum) {
		this.keepNum = keepNum;
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
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
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
	 * @return the memberSrc
	 */
	public MemerSourceType getMemberSrc() {
		return memberSrc;
	}
	/**
	 * @param memberSrc the memberSrc to set
	 */
	public void setMemberSrc(MemerSourceType memberSrc) {
		this.memberSrc = memberSrc;
	}
	public String getNoWw() {
		return noWw;
	}
	public void setNoWw(String noWw) {
		this.noWw = noWw;
	}
	
}
