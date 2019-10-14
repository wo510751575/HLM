package com.lj.business.weixin.dto.friendsjob;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * 类说明：添加发送朋友圈任务
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2017年12月21日
 */
public class AddSendFriendsJob implements Serializable {
    private static final long serialVersionUID = 4607292372655170729L;
    

    private String code;


	/**
     * 商户编号 (必填).
     */
    private String merchantNo;
    /**
     * 商户编号 (必填).
     */
    private String merchantName;

    private String content;
    
	/**
     * 发送微信(必填，以英文分号分割).
     */
    private String noWxs;
    
    /**
     * 执行时间 (如果立即执行，会忽略此字段).
     */
    private Date executeTime;
    
    /**
     * 朋友圈类型
     */
    private String type; // 1 文字 2 图文，3音乐文字 4视频文字
    
    /**
     * 多图片地址
     */
    private String imgAddr;
    /**
     * 音乐或视频地址
     */
    private String resourcePath;
    
    /**
     * 1：启动任务（保存任务，启动多个任务），2：执行任务（立即执行，执行单个任务）
     */
    private Integer actionType;

    /**
                * 创建人级别 1 老板，2导购
     */
    private String createUserLevel;
    
    private String createId;
    private String createName;
    /**
     * 链接素材标题
     */
    private String shareTitle;
    /**
     * 自动评论内容
     */
    private String autoContent;
    /**
     * 是否自动评论 .
     */
    private Integer autoComment;
    /**
     * 商户发朋友圈的CODE
     */
    private String merchantJobCode;
    
    public Integer getAutoComment() {
		return autoComment;
	}

	public void setAutoComment(Integer autoComment) {
		this.autoComment = autoComment;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantJobCode() {
		return merchantJobCode;
	}

	public void setMerchantJobCode(String merchantJobCode) {
		this.merchantJobCode = merchantJobCode;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getAutoContent() {
		return autoContent;
	}

	public void setAutoContent(String autoContent) {
		this.autoContent = autoContent;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateUserLevel() {
		return createUserLevel;
	}

	public void setCreateUserLevel(String createUserLevel) {
		this.createUserLevel = createUserLevel;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}



	/**
     * 备注 .
     */
    private String remark;

    /**
     * 备注2 .
     */
    private String remark2;

    /**
     * 备注3 .
     */
    private String remark3;

    /**
     * 备注4 .
     */
    private String remark4;
    
    
    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }



    public String getNoWxs() {
        return noWxs;
    }

    public void setNoWxs(String noWxs) {
        this.noWxs = noWxs;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "AddSendFriendsJob [code=" + code + ", merchantNo=" + merchantNo + ", merchantName=" + merchantName
				+ ", content=" + content + ", noWxs=" + noWxs + ", executeTime=" + executeTime + ", type=" + type
				+ ", imgAddr=" + imgAddr + ", resourcePath=" + resourcePath + ", actionType=" + actionType
				+ ", createUserLevel=" + createUserLevel + ", createId=" + createId + ", createName=" + createName
				+ ", shareTitle=" + shareTitle + ", autoContent=" + autoContent + ", merchantJobCode=" + merchantJobCode
				+ ", remark=" + remark + ", remark2=" + remark2 + ", remark3=" + remark3 + ", remark4=" + remark4 + "]";
	}

}
