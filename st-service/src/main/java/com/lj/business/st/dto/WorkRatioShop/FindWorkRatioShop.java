package com.lj.business.st.dto.WorkRatioShop;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class FindWorkRatioShop implements Serializable {

    /**
     * Generate cron.
     *
     * @param
     * @param
     * @throws
     */
    private static final long serialVersionUID = -422368115347655327L;


    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 店长编号 .
     */
    private String memberNoSp;

    /**
     * 区域CODE .
     */
    private String areaCode;

    /**
     * 省CODE .
     */
    private String provinceCode;

    /**
     * 市CODE .
     */
    private String cityCode;

    /**
     * 市区CODE .
     */
    private String cityAreaCode;
    
    /**
     * 查询标志 .
     */
    private String flag;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 统计维度\r\n            商户：MERCHANT\r\n            区域：AREA\r\n            门店：SHOP
     */
    private String dimensionSt;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getMemberNoSp() {
        return memberNoSp;
    }

    public void setMemberNoSp(String memberNoSp) {
        this.memberNoSp = memberNoSp;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityAreaCode() {
        return cityAreaCode;
    }

    public void setCityAreaCode(String cityAreaCode) {
        this.cityAreaCode = cityAreaCode;
    }

    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = StringUtils.isBlank(flag) ? flag : "NUM_" + flag;
	}

	public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDimensionSt() {
        return dimensionSt;
    }

    public void setDimensionSt(String dimensionSt) {
        this.dimensionSt = dimensionSt;
    }

	@Override
	public String toString() {
		return "FindWorkRatioShop [merchantNo=" + merchantNo + ", shopNo="
				+ shopNo + ", memberNoSp=" + memberNoSp + ", areaCode="
				+ areaCode + ", provinceCode=" + provinceCode + ", cityCode="
				+ cityCode + ", cityAreaCode=" + cityAreaCode + ", flag="
				+ flag + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", dimensionSt=" + dimensionSt + "]";
	}
}
