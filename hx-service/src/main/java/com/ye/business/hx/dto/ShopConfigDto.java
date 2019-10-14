package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ShopConfigDto implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** CODE*/
    private String code;

    /** 门诊编号*/
    private String shopNo;

    /** 门诊名称*/
    private String shopName;

    /** 商户编号*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 标签名*/
    private String lableName;

    /** 标签NO*/
    private String lableNo;

    /** 父CODE*/
    private String parentCode;

    /** 更新人*/
    private String updateId;

    /** 更新时间*/
    private Date updateDate;

    /** 创建人*/
    private String createId;

    /** 创建时间*/
    private Date createDate;

    /** 排序*/
    private Integer indexNo;

    /** 备注*/
    private String remark;
    
    /** 英文名称*/
    private String enname;

    /**子集*/
    private List<ShopConfigDto> childs;
    
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

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName == null ? null : lableName.trim();
    }

    public String getLableNo() {
        return lableNo;
    }

    public void setLableNo(String lableNo) {
        this.lableNo = lableNo == null ? null : lableNo.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
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

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

	public List<ShopConfigDto> getChilds() {
		return childs;
	}

	public void setChilds(List<ShopConfigDto> childs) {
		this.childs = childs;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	@Override
	public String toString() {
		return "ShopConfigDto [code=" + code + ", shopNo=" + shopNo + ", shopName=" + shopName + ", merchantNo="
				+ merchantNo + ", merchantName=" + merchantName + ", lableName=" + lableName + ", lableNo=" + lableNo
				+ ", parentCode=" + parentCode + ", updateId=" + updateId + ", updateDate=" + updateDate + ", createId="
				+ createId + ", createDate=" + createDate + ", indexNo=" + indexNo + ", remark=" + remark + ", enname="
				+ enname + ", childs=" + childs + "]";
	}
    
    
}
