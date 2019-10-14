package com.lj.business.member.domain;

import java.io.Serializable;
import java.util.Date;

public class GmAssistantShop implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 助手编号 .
     */
    private String assistantNo;

    /**
     * 助手名称 .
     */
    private String assistantName;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 创建人 .
     */
    private String createId;
    
    /**
     * 导购编号
     */
    private String memberNo;

 

	/**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark2;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark4;
    
    /**
     * tidCode .
     */
    private String tidCode;

    
    private String terminalCode;
    private String noWx;
    private String wxNickname;
    private String loginName;
    /**
     * 来源：下属微信
     */
    private String source;
    /**
     * 终端名称
     */
    private String shopName;
    /**
     * 导购头像
     */
    private String headUrl;
    
    private Integer count;
    
    
    public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMemberNo() {
 		return memberNo;
 	}

 	public void setMemberNo(String memberNo) {
 		this.memberNo = memberNo;
 	}
    public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getWxNickname() {
		return wxNickname;
	}

	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 助手编号 .
     *
     */
    public String getAssistantNo() {
        return assistantNo;
    }

    /**
     * 助手编号 .
     *
     */
    public void setAssistantNo(String assistantNo) {
        this.assistantNo = assistantNo == null ? null : assistantNo.trim();
    }

    /**
     * 助手名称 .
     *
     */
    public String getAssistantName() {
        return assistantName;
    }

    /**
     * 助手名称 .
     *
     */
    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName == null ? null : assistantName.trim();
    }

    /**
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 商户名称 .
     *
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称 .
     *
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

	/**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }
    /**
     * 备注 .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    public String getTidCode() {
		return tidCode;
	}

	public void setTidCode(String tidCode) {
		this.tidCode = tidCode;
	}

	@Override
	public String toString() {
		return "GmAssistantShop [code=" + code + ", assistantNo=" + assistantNo + ", assistantName=" + assistantName
				+ ", merchantNo=" + merchantNo + ", merchantName=" + merchantName + ", createId=" + createId
				+ ", memberNo=" + memberNo + ", createDate=" + createDate + ", remark=" + remark + ", remark2="
				+ remark2 + ", remark3=" + remark3 + ", remark4=" + remark4 + ", tidCode=" + tidCode + ", terminalCode="
				+ terminalCode + ", noWx=" + noWx + ", wxNickname=" + wxNickname + ", loginName=" + loginName
				+ ", source=" + source + ", shopName=" + shopName + ", headUrl=" + headUrl + ", count=" + count + "]";
	}

}