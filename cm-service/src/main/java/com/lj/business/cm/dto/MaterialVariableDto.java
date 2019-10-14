package com.lj.business.cm.dto;

import java.util.Date;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * 类说明：素材变量表
 * <p>
 * 详细描述：MATERIAL_VARIABLE表传参使用
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2017年12月20日
 */
public class MaterialVariableDto extends PageParamEntity{
	private static final long serialVersionUID = 1L;

	/**
     * CODE UUID
     */
    private String code;

    /**
     * 变量名 ${enjoy}
     */
    private String varName;

    /**
     * 值个数
     */
    private Integer varCount;

    /**
     * 变量值内容，用美元符号 $ 隔开
     */
    private String varContent;

    /**
     * 系统变量标识：0否、1是
     */
    private Integer sysFlag;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 创建人ID
     */
    private String createId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date upateDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注2
     */
    private String remark2;

    /**
     * 备注3
     */
    private String remark3;

    /**
     * 备注4
     */
    private String remark4;
    
    /**
     * 起始时间 yyyy-MM-dd HH:mm:ss
     */
    private Date startDate;
    
    /**
     * 截止时间 yyyy-MM-dd HH:mm:ss
     */
    private Date endDate;
    
    /**
     * 名称,变量值,内容等等
     */
    private String conditionStr;
    
    /**
     * 
     */
    private List<String> countList;
   
    public List<String> getCountList() {
		return countList;
	}

    
    
	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public void setCountList(List<String> countList) {
		this.countList = countList;
	}

	/**
     * CODE UUID
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE UUID
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 变量名 ${enjoy}
     */
    public String getVarName() {
        return varName;
    }

    /**
     * 变量名 ${enjoy}
     */
    public void setVarName(String varName) {
        this.varName = varName == null ? null : varName.trim();
    }

    /**
     * 值个数
     */
    public Integer getVarCount() {
        return varCount;
    }

    /**
     * 值个数
     */
    public void setVarCount(Integer varCount) {
        this.varCount = varCount;
    }

    /**
     * 变量值内容，用英文逗号隔开
     */
    public String getVarContent() {
        return varContent;
    }

    /**
     * 变量值内容，用英文逗号隔开
     */
    public void setVarContent(String varContent) {
        this.varContent = varContent == null ? null : varContent.trim();
    }

    /**
     * 系统变量标识：0否、1是
     */
    public Integer getSysFlag() {
        return sysFlag;
    }

    /**
     * 系统变量标识：0否、1是
     */
    public void setSysFlag(Integer sysFlag) {
        this.sysFlag = sysFlag;
    }

    /**
     * 商户编号
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 创建人ID
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人ID
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 更新时间
     */
    public Date getUpateDate() {
        return upateDate;
    }

    /**
     * 更新时间
     */
    public void setUpateDate(Date upateDate) {
        this.upateDate = upateDate;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 备注2
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注2
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    /**
     * 备注3
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注3
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    /**
     * 备注4
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注4
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }
    
	/**
     * 名称,变量值,内容等等
     */
	public String getConditionStr() {
		return conditionStr;
	}
	/**
     * 名称,变量值,内容等等
     */
	public void setConditionStr(String conditionStr) {
		this.conditionStr = conditionStr;
	}

    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_variable
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MaterialVariableDto other = (MaterialVariableDto) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getVarName() == null ? other.getVarName() == null : this.getVarName().equals(other.getVarName()))
            && (this.getVarCount() == null ? other.getVarCount() == null : this.getVarCount().equals(other.getVarCount()))
            && (this.getVarContent() == null ? other.getVarContent() == null : this.getVarContent().equals(other.getVarContent()))
            && (this.getSysFlag() == null ? other.getSysFlag() == null : this.getSysFlag().equals(other.getSysFlag()))
            && (this.getMerchantNo() == null ? other.getMerchantNo() == null : this.getMerchantNo().equals(other.getMerchantNo()))
            && (this.getCreateId() == null ? other.getCreateId() == null : this.getCreateId().equals(other.getCreateId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpateDate() == null ? other.getUpateDate() == null : this.getUpateDate().equals(other.getUpateDate()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getRemark2() == null ? other.getRemark2() == null : this.getRemark2().equals(other.getRemark2()))
            && (this.getRemark3() == null ? other.getRemark3() == null : this.getRemark3().equals(other.getRemark3()))
            && (this.getRemark4() == null ? other.getRemark4() == null : this.getRemark4().equals(other.getRemark4()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_variable
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getVarName() == null) ? 0 : getVarName().hashCode());
        result = prime * result + ((getVarCount() == null) ? 0 : getVarCount().hashCode());
        result = prime * result + ((getVarContent() == null) ? 0 : getVarContent().hashCode());
        result = prime * result + ((getSysFlag() == null) ? 0 : getSysFlag().hashCode());
        result = prime * result + ((getMerchantNo() == null) ? 0 : getMerchantNo().hashCode());
        result = prime * result + ((getCreateId() == null) ? 0 : getCreateId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpateDate() == null) ? 0 : getUpateDate().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getRemark2() == null) ? 0 : getRemark2().hashCode());
        result = prime * result + ((getRemark3() == null) ? 0 : getRemark3().hashCode());
        result = prime * result + ((getRemark4() == null) ? 0 : getRemark4().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_variable
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", varName=").append(varName);
        sb.append(", varCount=").append(varCount);
        sb.append(", varContent=").append(varContent);
        sb.append(", sysFlag=").append(sysFlag);
        sb.append(", merchantNo=").append(merchantNo);
        sb.append(", createId=").append(createId);
        sb.append(", createDate=").append(createDate);
        sb.append(", upateDate=").append(upateDate);
        sb.append(", remark=").append(remark);
        sb.append(", remark2=").append(remark2);
        sb.append(", remark3=").append(remark3);
        sb.append(", remark4=").append(remark4);
        sb.append("]");
        return sb.toString();
    }
}