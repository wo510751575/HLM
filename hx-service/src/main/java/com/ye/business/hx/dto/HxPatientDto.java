package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class HxPatientDto implements Serializable { 

    /** 患者code*/
    private String code;

    /** 分店编号*/
    private String shopNo;

    /** 分店名称*/
    private String shopName;

    /** 商户编号*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 姓名*/
    private String name;

    /** 性别*/
    private String sex;

    /** 患者类型(PT普通LS临时)*/
    private String type;

    /** 病历号*/
    private String caseNo;

    /** 出生年月日*/
    private Date birthDate;

    /** 年龄*/
    private Integer age;

    /** 国籍*/
    private String nationality;

    /** 身份证*/
    private String idno;

    /** 手机号*/
    private String phone;

    /** 手机号备注*/
    private String phoneRemark;

    /** 电话号*/
    private String phoneNo;

    /** 电话号备注*/
    private String phoneNoRemark;

    /** 微信*/
    private String wechat;

    /** QQ号*/
    private String qqNo;

    /** 邮箱*/
    private String mail;

    /** 省code*/
    private String provinceCode;

    /** 省*/
    private String province;

    /** 市code*/
    private String cityCode;

    /** 市*/
    private String city;

    /** 区code*/
    private String areaCode;

    /** 区*/
    private String area;

    /** 详细地址*/
    private String addrDetail;

    /** 完整地址（省市区详细地址全部包含）*/
    private String addrInfo;

    /** 来源1code*/
    private String source1Code;

    /** 来源1*/
    private String source1;

    /** 来源2code*/
    private String source2Code;

    /** 来源2*/
    private String source2;

    /** 来源3code*/
    private String source3Code;

    /** 来源3*/
    private String source3;

    /** 患者备注*/
    private String remark;

    /** 既往史*/
    private String pastHistory;

    /** 过敏史*/
    private String allergyHistory;

    /** 用药史*/
    private String medicationHistory;

    /** 初诊时间*/
    private Date createTime;

    /** 初诊医生code*/
    private String firstMemberNo;

    /** 初诊医生name*/
    private String firstMemberName;

    /** 责任医生code*/
    private String dutyMemberNo;

    /** 责任医生name*/
    private String dutyMemberName;

    /** 咨询师code*/
    private String consMemberNo;

    /** 咨询师name*/
    private String consMemberName;

    /** 创建人*/
    private String createId;

    /** 创建日期*/
    private Date createDate;

    /** 更新人*/
    private String updateId;

    /** 更新日期*/
    private Date updateDate;
    /** 直通车客户编号*/
    private String memberNo;
    /** 直通车客户编号,yes 则查是空， no 则查非空 */
    private String memberNoIsNull;
    /** 线索号(fk:CLUE_CODE)*/
    private String clueCode;
    /** 导购微信*/
    private String noWxGm;
	/**
	 * 导购编号，非空
	 */
	private String memberNoGm;
	
    /** CODE  List */
    private List<String> codes;
    
    
    public List<String> getCodes() {
		return codes;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo == null ? null : caseNo.trim();
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno == null ? null : idno.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPhoneRemark() {
        return phoneRemark;
    }

    public void setPhoneRemark(String phoneRemark) {
        this.phoneRemark = phoneRemark == null ? null : phoneRemark.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getPhoneNoRemark() {
        return phoneNoRemark;
    }

    public void setPhoneNoRemark(String phoneNoRemark) {
        this.phoneNoRemark = phoneNoRemark == null ? null : phoneNoRemark.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo == null ? null : qqNo.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail == null ? null : addrDetail.trim();
    }

    public String getAddrInfo() {
        return addrInfo;
    }

    public void setAddrInfo(String addrInfo) {
        this.addrInfo = addrInfo == null ? null : addrInfo.trim();
    }

    public String getSource1Code() {
        return source1Code;
    }

    public void setSource1Code(String source1Code) {
        this.source1Code = source1Code == null ? null : source1Code.trim();
    }

    public String getSource1() {
        return source1;
    }

    public void setSource1(String source1) {
        this.source1 = source1 == null ? null : source1.trim();
    }

    public String getSource2Code() {
        return source2Code;
    }

    public void setSource2Code(String source2Code) {
        this.source2Code = source2Code == null ? null : source2Code.trim();
    }

    public String getSource2() {
        return source2;
    }

    public void setSource2(String source2) {
        this.source2 = source2 == null ? null : source2.trim();
    }

    public String getSource3Code() {
        return source3Code;
    }

    public void setSource3Code(String source3Code) {
        this.source3Code = source3Code == null ? null : source3Code.trim();
    }

    public String getSource3() {
        return source3;
    }

    public void setSource3(String source3) {
        this.source3 = source3 == null ? null : source3.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPastHistory() {
        return pastHistory;
    }

    public void setPastHistory(String pastHistory) {
        this.pastHistory = pastHistory == null ? null : pastHistory.trim();
    }

    public String getAllergyHistory() {
        return allergyHistory;
    }

    public void setAllergyHistory(String allergyHistory) {
        this.allergyHistory = allergyHistory == null ? null : allergyHistory.trim();
    }

    public String getMedicationHistory() {
        return medicationHistory;
    }

    public void setMedicationHistory(String medicationHistory) {
        this.medicationHistory = medicationHistory == null ? null : medicationHistory.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFirstMemberNo() {
        return firstMemberNo;
    }

    public void setFirstMemberNo(String firstMemberNo) {
        this.firstMemberNo = firstMemberNo == null ? null : firstMemberNo.trim();
    }

    public String getFirstMemberName() {
        return firstMemberName;
    }

    public void setFirstMemberName(String firstMemberName) {
        this.firstMemberName = firstMemberName == null ? null : firstMemberName.trim();
    }

    public String getDutyMemberNo() {
        return dutyMemberNo;
    }

    public void setDutyMemberNo(String dutyMemberNo) {
        this.dutyMemberNo = dutyMemberNo == null ? null : dutyMemberNo.trim();
    }

    public String getDutyMemberName() {
        return dutyMemberName;
    }

    public void setDutyMemberName(String dutyMemberName) {
        this.dutyMemberName = dutyMemberName == null ? null : dutyMemberName.trim();
    }

    public String getConsMemberNo() {
        return consMemberNo;
    }

    public void setConsMemberNo(String consMemberNo) {
        this.consMemberNo = consMemberNo == null ? null : consMemberNo.trim();
    }

    public String getConsMemberName() {
        return consMemberName;
    }

    public void setConsMemberName(String consMemberName) {
        this.consMemberName = consMemberName == null ? null : consMemberName.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getClueCode() {
		return clueCode;
	}

	public void setClueCode(String clueCode) {
		this.clueCode = clueCode;
	}

	public String getMemberNoIsNull() {
		return memberNoIsNull;
	}

	public void setMemberNoIsNull(String memberNoIsNull) {
		this.memberNoIsNull = memberNoIsNull;
	}

	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		return "HxPatientDto [code=" + code + ", shopNo=" + shopNo + ", shopName=" + shopName + ", merchantNo="
				+ merchantNo + ", merchantName=" + merchantName + ", name=" + name + ", sex=" + sex + ", type=" + type
				+ ", caseNo=" + caseNo + ", birthDate=" + birthDate + ", age=" + age + ", nationality=" + nationality
				+ ", idno=" + idno + ", phone=" + phone + ", phoneRemark=" + phoneRemark + ", phoneNo=" + phoneNo
				+ ", phoneNoRemark=" + phoneNoRemark + ", wechat=" + wechat + ", qqNo=" + qqNo + ", mail=" + mail
				+ ", provinceCode=" + provinceCode + ", province=" + province + ", cityCode=" + cityCode + ", city="
				+ city + ", areaCode=" + areaCode + ", area=" + area + ", addrDetail=" + addrDetail + ", addrInfo="
				+ addrInfo + ", source1Code=" + source1Code + ", source1=" + source1 + ", source2Code=" + source2Code
				+ ", source2=" + source2 + ", source3Code=" + source3Code + ", source3=" + source3 + ", remark="
				+ remark + ", pastHistory=" + pastHistory + ", allergyHistory=" + allergyHistory
				+ ", medicationHistory=" + medicationHistory + ", createTime=" + createTime + ", firstMemberNo="
				+ firstMemberNo + ", firstMemberName=" + firstMemberName + ", dutyMemberNo=" + dutyMemberNo
				+ ", dutyMemberName=" + dutyMemberName + ", consMemberNo=" + consMemberNo + ", consMemberName="
				+ consMemberName + ", createId=" + createId + ", createDate=" + createDate + ", updateId=" + updateId
				+ ", updateDate=" + updateDate + "]";
	}
    
    
}
