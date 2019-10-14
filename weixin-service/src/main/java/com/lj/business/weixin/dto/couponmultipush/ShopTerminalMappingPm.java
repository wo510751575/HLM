package com.lj.business.weixin.dto.couponmultipush;

import java.io.Serializable;

public class ShopTerminalMappingPm implements Serializable {
    private static final long serialVersionUID = -4259167157364795274L;

    /**
     * 终端微信
     */
    private String shopNoWx;
    
    /**
     * 终端微信下的客户数量
     */
    private Integer num;
    
    /**
     * 多个客户CODE(逗号隔开)
     */
    private String codes;
    
    /**
     * 多个客户微信(逗号隔开)
     */
    private String noWxs;

    public String getShopNoWx() {
        return shopNoWx;
    }

    public void setShopNoWx(String shopNoWx) {
        this.shopNoWx = shopNoWx;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getNoWxs() {
        return noWxs;
    }

    public void setNoWxs(String noWxs) {
        this.noWxs = noWxs;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ShopTerminalMappingPm [shopNoWx=");
        builder.append(shopNoWx);
        builder.append(", num=");
        builder.append(num);
        builder.append(", codes=");
        builder.append(codes);
        builder.append(", noWxs=");
        builder.append(noWxs);
        builder.append("]");
        return builder.toString();
    }

}
