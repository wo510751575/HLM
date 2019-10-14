package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

public class TmallBonusRecordDto implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** CODE*/
    private String code;

    /** 商户编号*/
    private String merchantNo;

    /** 会员编号*/
    private String memberNo;

    /** 会员姓名*/
    private String memberName;

    /** 会员微信*/
    private String noWx;

    /** 订单号*/
    private String orderNo;

    /** 订单金额*/
    private Long orderAmt;

    /** 红包金额*/
    private Long bonuxAmt;

    /** 发送红包时间*/
    private Date pushTime;

    /** 状态(Y:成功 N:失败)*/
    private String status;

    /** 备注1*/
    private String remark;

    /** 备注2 红包单号，用于重发*/
    private String remark2;

    /** 备注3 红包订单的微信单号*/
    private String remark3;

    /** 备注4*/
    private String remark4;

    /** 创建时间*/
    private Date orderDate;
    /** 旺旺号*/
    private String noWw;
    /** 商户订单号*/
    private String mchBillno;
    /** 微信单号*/
    private String sendListid;

    public String getNoWw() {
		return noWw;
	}

	public void setNoWw(String noWw) {
		this.noWw = noWw;
	}

	public String getMchBillno() {
		return mchBillno;
	}

	public void setMchBillno(String mchBillno) {
		this.mchBillno = mchBillno;
	}

	public String getSendListid() {
		return sendListid;
	}

	public void setSendListid(String sendListid) {
		this.sendListid = sendListid;
	}
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getNoWx() {
        return noWx;
    }

    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Long getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(Long orderAmt) {
        this.orderAmt = orderAmt;
    }

    public Long getBonuxAmt() {
        return bonuxAmt;
    }

    public void setBonuxAmt(Long bonuxAmt) {
        this.bonuxAmt = bonuxAmt;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

	@Override
	public String toString() {
		return "TmallBonusRecordDto [code=" + code + ", merchantNo=" + merchantNo + ", memberNo=" + memberNo
				+ ", memberName=" + memberName + ", noWx=" + noWx + ", orderNo=" + orderNo + ", orderAmt=" + orderAmt
				+ ", bonuxAmt=" + bonuxAmt + ", pushTime=" + pushTime + ", status=" + status + ", remark=" + remark
				+ ", remark2=" + remark2 + ", remark3=" + remark3 + ", remark4=" + remark4 + ", orderDate=" + orderDate
				+ "]";
	}
    
}
