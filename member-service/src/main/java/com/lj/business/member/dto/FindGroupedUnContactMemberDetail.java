package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

public class FindGroupedUnContactMemberDetail implements Serializable {
    private static final long serialVersionUID = 791105546020884943L;

    /**
     * 客户编号  .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 微信号 .
     */
    private String noWx;
    
    /**
     * 昵称备注_微信 .
     */
    private String nickNameRemarkWx;
    
    /** 昵称备注_本地. */
    private String nickNameRemarkLocal;
    
    /**
     * 昵称_微信 .
     */
    private String nickNameWx;
    
    /**
     * 头像地址 .
     */
    private String headAddress;
    
    /**
     * 动态描述 .
     */
    private String behaviorDesc;
    /**
     * 行为时间 .
     */
    private Date behaviorDate;
    
    /**
     * 客户客户分类关联表CODE .
     */
    private String codePmTypePm;
    
    /**
     * 紧急客户
        是：Y
        否：N .
     */
    private String urgencyPm;

    /**
     * 手机号 .
     */
    private String mobile;
    
    /** 完成百分比. */
    private Double ratioClientInfo;
    
    /** 交叉客户数量. */
    private int repeatCount;
    
    /** 创建时间. */
    private Date createDate;
    
    /**
     * 消费次数（理发）
     */
    private int consumeCount;
    
    /**
     * 任务备注 .
     */
    private String remarkCom;
    
}
