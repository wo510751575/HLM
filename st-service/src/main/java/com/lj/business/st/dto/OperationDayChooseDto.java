package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class OperationDayChooseDto.
 */
public class OperationDayChooseDto implements Serializable{

	  /** Generate cron. */
	private static final long serialVersionUID = -4319574790186053955L;

	 /** The code. */
 	private String code;//code

	    /** The merchant no. */
    	private String merchantNo;//商户编号

	    /** The shop no. */
    	private String shopNo;//分店编号

	    /** The shop name. */
    	private String shopName;//分店名称

	    /** The member no gm. */
    	private String memberNoGm;//导购编号
	 
	    /** The member name gm. */
    	private String memberNameGm;//导购姓名

	    /** The code list. */
    	private String codeList;//项目code
	 
	    /** The name list. */
    	private String nameList;//项目名称
    	
        private String typeList;//项目类型
    	
	    public String getTypeList() {
			return typeList;
		}


		/**
		 * Sets the type list.
		 *
		 * @param typeList the type list
		 */
		public void setTypeList(String typeList) {
			this.typeList = typeList;
		}


		/** The seq. */
    	private Integer seq;//排序
    	
	     private String imgAddr;//图片地址
	    /** The create date. */
    	private Date createDate;//创建时间


	    public String getImgAddr() {
			return imgAddr;
		}


		/**
		 * Sets the img addr.
		 *
		 * @param imgAddr the img addr
		 */
		public void setImgAddr(String imgAddr) {
			this.imgAddr = imgAddr;
		}


		/**
    	 * Gets the code.
    	 *
    	 * @return the code
    	 */
    	public String getCode() {
	        return code;
	    }


	    /**
    	 * Sets the code.
    	 *
    	 * @param code the code
    	 */
    	public void setCode(String code) {
	        this.code = code == null ? null : code.trim();
	    }

	  
	    /**
    	 * Gets the merchant no.
    	 *
    	 * @return the merchant no
    	 */
    	public String getMerchantNo() {
	        return merchantNo;
	    }

	  
	    /**
    	 * Sets the merchant no.
    	 *
    	 * @param merchantNo the merchant no
    	 */
    	public void setMerchantNo(String merchantNo) {
	        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
	    }


	    /**
    	 * Gets the shop no.
    	 *
    	 * @return the shop no
    	 */
    	public String getShopNo() {
	        return shopNo;
	    }

	  
	    /**
    	 * Sets the shop no.
    	 *
    	 * @param shopNo the shop no
    	 */
    	public void setShopNo(String shopNo) {
	        this.shopNo = shopNo == null ? null : shopNo.trim();
	    }

	    /**
    	 * Gets the shop name.
    	 *
    	 * @return the shop name
    	 */
    	public String getShopName() {
	        return shopName;
	    }

	 
	    /**
    	 * Sets the shop name.
    	 *
    	 * @param shopName the shop name
    	 */
    	public void setShopName(String shopName) {
	        this.shopName = shopName == null ? null : shopName.trim();
	    }

	 
	    /**
    	 * Gets the member no gm.
    	 *
    	 * @return the member no gm
    	 */
    	public String getMemberNoGm() {
	        return memberNoGm;
	    }

	 
	    /**
    	 * Sets the member no gm.
    	 *
    	 * @param memberNoGm the member no gm
    	 */
    	public void setMemberNoGm(String memberNoGm) {
	        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
	    }

	    /**
    	 * Gets the member name gm.
    	 *
    	 * @return the member name gm
    	 */
    	public String getMemberNameGm() {
	        return memberNameGm;
	    }

	    /**
    	 * Sets the member name gm.
    	 *
    	 * @param memberNameGm the member name gm
    	 */
    	public void setMemberNameGm(String memberNameGm) {
	        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
	    }


	    /**
    	 * Gets the code list.
    	 *
    	 * @return the code list
    	 */
    	public String getCodeList() {
	        return codeList;
	    }

	    /**
    	 * Sets the code list.
    	 *
    	 * @param codeList the code list
    	 */
    	public void setCodeList(String codeList) {
	        this.codeList = codeList == null ? null : codeList.trim();
	    }


	    /**
    	 * Gets the name list.
    	 *
    	 * @return the name list
    	 */
    	public String getNameList() {
	        return nameList;
	    }


	    /**
    	 * Sets the name list.
    	 *
    	 * @param nameList the name list
    	 */
    	public void setNameList(String nameList) {
	        this.nameList = nameList == null ? null : nameList.trim();
	    }

	 
	    /**
    	 * Gets the seq.
    	 *
    	 * @return the seq
    	 */
    	public Integer getSeq() {
	        return seq;
	    }

	    /**
    	 * Sets the seq.
    	 *
    	 * @param seq the seq
    	 */
    	public void setSeq(Integer seq) {
	        this.seq = seq;
	    }


	    /**
    	 * Gets the create date.
    	 *
    	 * @return the creates the date
    	 */
    	public Date getCreateDate() {
	        return createDate;
	    }

	    /**
    	 * Sets the create date.
    	 *
    	 * @param createDate the creates the date
    	 */
    	public void setCreateDate(Date createDate) {
	        this.createDate = createDate;
	    }


	    @Override
		public String toString() {
			return "OperationDayChooseDto [code=" + code + ", merchantNo="
					+ merchantNo + ", shopNo=" + shopNo + ", shopName="
					+ shopName + ", memberNoGm=" + memberNoGm
					+ ", memberNameGm=" + memberNameGm + ", codeList="
					+ codeList + ", nameList=" + nameList + ", typeList="
					+ typeList + ", seq=" + seq + ", imgAddr=" + imgAddr
					+ ", createDate=" + createDate + "]";
		}

}
