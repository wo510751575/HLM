package com.lj.oms.utils.excel.dto;

import java.io.Serializable;
import java.util.Date;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 类说明：学生数据信息导出
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2018年3月6日
 */
public class StudentAllDto implements Serializable {
   
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = -8127206544947157602L;

	/** 创建时间*/
	@ExcelField(title="日期", align=2, sort=2)
    private Date createDate;
	
   /* *//**
     * 微信OpendID
     *//*
	@ExcelField(title="微信OpendID", align=2, sort=3)
    private String wxOpenid;
    */
    
    /** 姓名*/
	@ExcelField(title="姓名", align=2, sort=4)
    private String studentName;

    /** 性别:
            男：MALE
            女：FEMALE
            未知：UNKNOWN*/
	@ExcelField(title="性别", align=2, sort=5)
    private String sex;

    /** 出生日期*/
	@ExcelField(title="出生日期", align=2, sort=6)
    private Date birthday;

    /** 身高CM*/
	@ExcelField(title="身高", align=3, sort=7)
    private Integer height;

    /** 体重KG*/
	@ExcelField(title="体重", align=3, sort=8)
    private Float weight;

    /** 政治面貌(party中共党员、member共青团员、mass群众)*/
	@ExcelField(title="政治面貌", align=2, sort=9)
    private String politicalStatus;

    /** 毕业院校*/
	@ExcelField(title="宣讲院校", align=2, sort=10)
    private String schoolName;

    /** 所属专业*/
	@ExcelField(title="专业", align=2, sort=11)
    private String major;

    /** 学历（postgraduate研究生，undergraduates本科，college大专）*/
	@ExcelField(title="学历", align=2, sort=12)
    private String education;

    /** 联系电话*/
	@ExcelField(title="联系电话", align=2, sort=13)
    private String telphone;
/*
    *//** 紧急联系人EMERGENCY CONTACT*//*
	@ExcelField(title="紧急联系人", align=2, sort=14)
    private String emgContact;

    *//** 紧急联系人电话*//*
	@ExcelField(title="紧急联系人电话", align=2, sort=15)
    private String emgTelphone;*/

    /** 邮箱*/
	@ExcelField(title="邮箱", align=2, sort=16)
    private String email;

/*    *//** 笔试成绩*//*
	@ExcelField(title="笔试成绩", align=3, sort=17)
    private Float writtenScore;*/

    /** 复试成绩（pass 通过，nopass 不通过）*/
	@ExcelField(title="复试成绩", align=2, sort=18)
    private String secondScore;

    /** 终面成绩（pass 通过，nopass 不通过）*/
	@ExcelField(title="终面成绩", align=2, sort=19)
    private String finalScore;
	
	/** offer申请*/
	@ExcelField(title="offer申请", align=2, sort=19)
	private String offerStatus;

	/**毕业院校*/
	@ExcelField(title="毕业院校", align=2, sort=20)
	private String school;
	
	
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

/*	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}*/

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

/*	public String getEmgContact() {
		return emgContact;
	}

	public void setEmgContact(String emgContact) {
		this.emgContact = emgContact;
	}

	public String getEmgTelphone() {
		return emgTelphone;
	}

	public void setEmgTelphone(String emgTelphone) {
		this.emgTelphone = emgTelphone;
	}*/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
/*
	public Float getWrittenScore() {
		return writtenScore;
	}

	public void setWrittenScore(Float writtenScore) {
		this.writtenScore = writtenScore;
	}*/

	public String getSecondScore() {
		return secondScore;
	}

	public void setSecondScore(String secondScore) {
		this.secondScore = secondScore;
	}

	public String getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}
	
	
    
}
