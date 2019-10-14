package com.lj.business.cp.dto.coupon;

import java.io.Serializable;

public class FindMemberInfoByInviteCodeReturn implements Serializable {
    private static final long serialVersionUID = 7945580595673907702L;

    /**
     * 1:认领过的客户，2：未认领的客户
     */
    private Integer type;
    /**
     * shopNoWx 终端终端微信号
     */
    private String shopNoWx;
    /**
     * 优惠券规则CODE
     */
    private String couponRuleCode;
    /**
     * 客户编号
     */
    private String memberNo;
    /**
     * AddFriends的CODE
     */
    private String addFriendsCode;
    
    /**
     * 导购编号
     */
    private String memberNoGm;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getShopNoWx() {
        return shopNoWx;
    }

    public void setShopNoWx(String shopNoWx) {
        this.shopNoWx = shopNoWx;
    }

    public String getCouponRuleCode() {
        return couponRuleCode;
    }

    public void setCouponRuleCode(String couponRuleCode) {
        this.couponRuleCode = couponRuleCode;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getAddFriendsCode() {
        return addFriendsCode;
    }

    public void setAddFriendsCode(String addFriendsCode) {
        this.addFriendsCode = addFriendsCode;
    }

    public String getMemberNoGm() {
        return memberNoGm;
    }

    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindMemberInfoByInviteCodeReturn [type=");
        builder.append(type);
        builder.append(", shopNoWx=");
        builder.append(shopNoWx);
        builder.append(", couponRuleCode=");
        builder.append(couponRuleCode);
        builder.append(", memberNo=");
        builder.append(memberNo);
        builder.append(", addFriendsCode=");
        builder.append(addFriendsCode);
        builder.append(", memberNoGm=");
        builder.append(memberNoGm);
        builder.append("]");
        return builder.toString();
    }

}
