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
public class StudentMarkDto implements Serializable {
   
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = -8127206544947157602L;

	/** 创建时间*/
	@ExcelField(title="日期", align=2, sort=2)
    private Date createDate;
	
    /** 姓名*/
	@ExcelField(title="姓名", align=2, sort=4)
    private String studentName;

    /** 性别:
            男：MALE
            女：FEMALE
            未知：UNKNOWN*/
	@ExcelField(title="性别", align=2, sort=5)
    private String sex;

    /** 身高CM*/
	@ExcelField(title="身高", align=3, sort=7)
    private Integer height;

    /** 毕业院校*/
	@ExcelField(title="毕业院校", align=2, sort=10)
    private String schoolName;

    /** 联系电话*/
	@ExcelField(title="联系电话", align=2, sort=13)
    private String telphone;

    /** 笔试成绩*/
	@ExcelField(title="笔试成绩", align=3, sort=17)
    private Float writtenScore;

    /** 复试成绩（pass 通过，nopass 不通过）*/
	@ExcelField(title="复试成绩", align=2, sort=18)
    private String secondScore;

    /** 终面成绩（pass 通过，nopass 不通过）*/
	@ExcelField(title="终面成绩", align=2, sort=19)
    private String finalScore;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


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

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}


	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}


	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}


	public Float getWrittenScore() {
		return writtenScore;
	}

	public void setWrittenScore(Float writtenScore) {
		this.writtenScore = writtenScore;
	}

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
